package com.st.analysis.utils.stock.finddata;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.exceptions.DataNotGeneratedException;
import com.st.framework.module.stock.DStockIndex;
import com.st.framework.module.stock.GDetailIndex;
import com.st.framework.module.stock.example.DStockIndexExample;
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

	public static void findIndexBeginDateId() {
		DStockIndexExample example = new DStockIndexExample();
		example.setOrderByClause("INDEX_ID");

		Page pageInfo = dStockIndexManager.selectPageByExample(example, 20);
		// code=0000001&start=19901219&end=20150518
		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);

			example.setPage(pageInfo);

			List<DStockIndex> list = dStockIndexManager
					.selectByExample(example);

			for (DStockIndex dsIndex : list) {

				String url = baseLsjysjUrl + dsIndex.getIndexId() + ".html";

				Date beginDate = findBeginData(url);

				if (beginDate != null) {
					dsIndex.setBeginDate(beginDate);
					dStockIndexManager.updateByPrimaryKey(dsIndex);
				}

			}

		}
	}

	public static Date findBeginData(String htmlUrl) {

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

	public static void findAllIndexData() {

		DStockIndexExample example = new DStockIndexExample();
		example.setOrderByClause("INDEX_ID");

		Page pageInfo = dStockIndexManager.selectPageByExample(example, 20);
		// code=0000001&start=19901219&end=20150518
		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);

			example.setPage(pageInfo);

			List<DStockIndex> list = dStockIndexManager
					.selectByExample(example);

			for (DStockIndex dsIndex : list) {

				String start = Global.DF_SIMPLE.format(dsIndex.getBeginDate());
				String end = Global.DF_SIMPLE.format(new Date());

				String url = baseUrl + dsIndex.getIndexType()
						+ dsIndex.getIndexId();
				url += "&start=" + start + "&end=" + end;

				parseURLData(url);
			}

		}

	}

	public static void parseURLData(String url) {
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
				System.out.println(line);
			} else {
				throw new DataNotGeneratedException("无数据");
			}
			List<GDetailIndex> dataList = new ArrayList<GDetailIndex>();
			while ((line = br.readLine()) != null) {
				
				System.out.println(line);
				
				dataList.add(parseLineData(line));
			}

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
	
	private static GDetailIndex parseLineData (String line) throws ParseException {
		GDetailIndex gdetailIndex = new GDetailIndex();
		String[] arrs = line.split(",");
		
		gdetailIndex.setDateid(Global.DF_DAY.parse(arrs[0]));
//		gdetailIndex.setIcode(arrs[1]));
		
		return gdetailIndex;
	}

	public static void main(String[] args) {
//		findIndexBeginDateId();
		findAllIndexData();
	}
}
