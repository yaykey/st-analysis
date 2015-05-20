package com.st.analysis.utils.stock.finddata.netease;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.st.Global;
import com.st.analysis.utils.stock.bean.netease.StockIndexBean;
import com.st.analysis.utils.stock.bean.netease.StockIndexMapBean;
import com.st.framework.exceptions.DataNotGeneratedException;
import com.st.framework.module.stock.DStockIndex;
import com.st.framework.module.stock.GDetailIndex;
import com.st.framework.module.stock.example.DStockIndexExample;
import com.st.framework.utils.DateUtil;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.network.HttpStackManager;
import com.st.framework.utils.page.Page;

public class Find163IndexDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(Find163IndexDataUtils.class);

	// http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150518&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER

	// http://quotes.money.163.com/service/chddata.html?
	// code=0000001&start=19901219&end=20150518&
	// fields=
	// DATEID;ICODE,INAME;TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER
	// 日期,股票代码,名称,收盘价,最高价,最低价,开盘价,前收盘,涨跌额,涨跌幅,成交量,成交金额
	// 日期 开盘价 最高价 最低价 收盘价 涨跌额 涨跌幅(%) 成交量(股) 成交金额(元)

	private static String baseUrl = "http://quotes.money.163.com/service/chddata.html?";
	static {
		baseUrl += "fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
		baseUrl += "&code=";
	}

	// http://quotes.money.163.com/trade/lsjysj_zhishu_000016.html
	// http://quotes.money.163.com/trade/lsjysj_zhishu_399001.html

	private static String baseLsjysjUrl = "http://quotes.money.163.com/trade/lsjysj_zhishu_";

	// http://quotes.money.163.com/old/#query=sz
	// http://quotes.money.163.com/old/#query=sh
	// http://quotes.money.163.com/hs/service/hsindexrank.php?host=/hs/service/hsindexrank.php&page=0&query=IS_INDEX:true;EXCHANGE:CNSESH&fields=no,SYMBOL,NAME&sort=SYMBOL&count=1000&type=query
	// http://quotes.money.163.com/hs/service/hsindexrank.php?host=/hs/service/hsindexrank.php&page=0&query=IS_INDEX:true;EXCHANGE:CNSESZ&fields=no,SYMBOL,NAME&sort=SYMBOL&count=1000&type=query
	private static String basezb = "http://quotes.money.163.com/hs/service/hsindexrank.php?host=/hs/service/hsindexrank.php&page=0";
	static {
		basezb += "&fields=SYMBOL,NAME&sort=SYMBOL&count=1000&type=query";
		basezb += "&query=";
		basezb += "IS_INDEX:true;";
		basezb += "EXCHANGE:CNSE";
	}

	@SuppressWarnings("static-access")
	public void findZBIndex(String queryType) {
		String url = basezb + queryType.toUpperCase();

		String json = HttpStackManager.getInstance().findGetData(url);
//		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();
		StockIndexMapBean mapBean = null;

		try {
			mapBean = mapper.readValue(json, StockIndexMapBean.class);

			final List<DStockIndex> list = mapBean.getDSIndexList(queryType);

			// Global.threadPoolExecutor.execute(new Thread(){
			// public void run () {
			// dStockIndexManager.insertBatch(list);
			// }
			// });

			print(list);

		} catch (JsonParseException e) {
			logger.warn("findZBIndex(String) - exception ignored", e);

		} catch (JsonMappingException e) {
			logger.warn("findZBIndex(String) - exception ignored", e);

		} catch (IOException e) {
			logger.warn("findZBIndex(String) - exception ignored", e);

		}

		// System.out.println(mapBean);
	}

	public void findIndexBeginDateId() {
		DStockIndexExample example = new DStockIndexExample();
		example.setOrderByClause("INDEX_ID");
		DStockIndexExample.Criteria c = example.createCriteria();
		c.andBeginDateIsNull();

		Page pageInfo = dStockIndexManager.selectPageByExample(example, 100);
		// code=0000001&start=19901219&end=20150518
		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);

			example.setPage(pageInfo);

			List<DStockIndex> list = dStockIndexManager
					.selectByExample(example);

			for (final DStockIndex dsIndex : list) {

				Global.threadPoolExecutor.execute(new Thread() {
					public void run() {
						String url = baseLsjysjUrl + dsIndex.getIndexId()
								+ ".html";

						Date beginDate = findBeginData(url);

						if (beginDate != null) {
							dsIndex.setBeginDate(beginDate);
							dStockIndexManager.updateByPrimaryKey(dsIndex);
						}

						System.out.println(dsIndex.getIndexId() + ":"
								+ beginDate + "->url=" + url);
					}
				});

			}

		}
	}

	public Date findBeginData(String htmlUrl) {

		Document doc = null;
		Connection connect = Jsoup.connect(htmlUrl).timeout(10000);

		try {
			doc = connect.get();

			Element el = doc.getElementById("dropBox1");

			Elements ipts = el.select("input[type=text]");
			Element elipt = ipts.first();

			String beginDate = elipt.val();

			Date returnDate = Global.DF_DAY.parse(beginDate);

			return returnDate;
		} catch (IOException e) {
			logger.warn("findBeginData(String) - exception ignored", e);

		} catch (ParseException e) {
			logger.warn("findBeginData(String) - exception ignored", e);

		}

		return null;
	}

	public void findAllIndexData(String queryType) {

		DStockIndexExample example = new DStockIndexExample();
		example.setOrderByClause("INDEX_ID");
		DStockIndexExample.Criteria c = example.createCriteria();

		c.andBeginDateIsNotNull();

		if (queryType != null && ("main".equalsIgnoreCase(queryType)
				|| "sh".equalsIgnoreCase(queryType)
				|| "sz".equalsIgnoreCase(queryType))
		) {
			c.andQueryTypeEqualTo(queryType.toLowerCase());
		}
		
		Page pageInfo = dStockIndexManager.selectPageByExample(example, 100);
		// code=0000001&start=19901219&end=20150518
		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);

			example.setPage(pageInfo);

			List<DStockIndex> list = dStockIndexManager
					.selectByExample(example);
			Date beginDate = null;
			for (DStockIndex dsIndex : list) {

				beginDate = gDetailIndexManager.getLastDate(dsIndex.getIndexId());
				
				if (beginDate == null) {
					beginDate = dsIndex.getBeginDate();
				}
				//System.out.println(DateUtil.getDaysDec(beginDate, new Date()));
				if (DateUtil.getDaysDec(beginDate, new Date()) <= 2) {
					continue;
				}
				System.out.println(DateUtil.getDaysDec(beginDate, new Date()));
				String start = Global.DF_SIMPLE.format(beginDate);
				String end = Global.DF_SIMPLE.format(new Date());
				
				
				
				final String url = baseUrl + dsIndex.getIndexType()
						+ dsIndex.getIndexId() + "&start=" + start + "&end="
						+ end;

				Global.threadPoolExecutor.execute(new Thread() {
					public void run() {
						try {
							new Find163IndexDataUtils().parseURLData(url);
							System.out.println(url);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				});

			}

		}

	}
	
	

	public void parseURLData(String url) {
		CloseableHttpClient httpClient = HttpStackManager.getInstance()
				.getHttpclient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		HttpEntity entity = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			is = entity.getContent();
			isr = new InputStreamReader(is, "GBK");

			br = new BufferedReader(isr);

			String line = "";

			if ((line = br.readLine()) != null) {
//				System.out.println(line);
			} else {
				throw new DataNotGeneratedException("无数据");
			}

			List<GDetailIndex> dataList = new ArrayList<GDetailIndex>();
			while ((line = br.readLine()) != null) {

//				System.out.println(line);

				try {
					dataList.add(parseLineData(line));
				} catch (Exception ex) {
					System.out.println(line);
				}
			}

			gDetailIndexManager.insertBatch(dataList);

		} catch (ClientProtocolException e) {
			logger.warn("parseURLData(String) - exception ignored", e);

		} catch (IOException e) {
			logger.warn("parseURLData(String) - exception ignored", e);

		} catch (DataNotGeneratedException e) {
			logger.warn("parseURLData(String) - exception ignored", e);

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.warn("parseURLData(String) - exception ignored", e);
				}
			}

			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					logger.warn("parseURLData(String) - exception ignored", e);
				}
			}
		}
	}

	private GDetailIndex parseLineData(String line)
			throws ParseException, DataNotGeneratedException {
		GDetailIndex gdetailIndex = new GDetailIndex();
		String[] arrs = line.split(",");

		// 日期,股票代码,名称,收盘价,最高价,最低价,开盘价,前收盘,涨跌额,涨跌幅,成交量,成交金额
		// 2015-05-18,'000001,上证指数,4283.491,4324.826,4260.509,4277.895,4308.691,-25.2,-0.5849,380057452,5.94559493076e+11
		// DATEID;ICODE,INAME;TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER

		gdetailIndex.setDateid(Global.DF_DAY.parse(arrs[0]));
		
		if (gdetailIndex.getDateid().compareTo(new Date())>0) {
			throw new DataNotGeneratedException("解析时间错误->line=" + line);
		}
		
		gdetailIndex.setIcode(arrs[1].substring(1));
		gdetailIndex.setIname(arrs[2]);
		gdetailIndex.setTclose(Double.parseDouble(arrs[3]));
		gdetailIndex.setHigh(Double.parseDouble(arrs[4]));
		gdetailIndex.setLow(Double.parseDouble(arrs[5]));
		gdetailIndex.setTopen(Double.parseDouble(arrs[6]));
		try {
			gdetailIndex.setLclose(Double.parseDouble(arrs[7]));
		} catch (Exception e) {
			logger.warn(e.getMessage());

		}

		try {
			gdetailIndex.setChg(Double.parseDouble(arrs[8]));
		} catch (Exception e) {
			logger.warn(e.getMessage());

		}

		try {
			gdetailIndex.setPchg(Double.parseDouble(arrs[9]));
		} catch (Exception e) {
			logger.warn(e.getMessage());

		}

		try {
			gdetailIndex.setVoturnover(Long.parseLong(arrs[10]));
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			
		}
		
		try {
			gdetailIndex.setVaturnover(new BigDecimal(arrs[11]).longValue());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		}

		return gdetailIndex;
	}

	public static void main(String[] args) {
		// findIndexBeginDateId();
		new Find163IndexDataUtils().findAllIndexData("main");
		// findZBIndex("sh");
	}
}
