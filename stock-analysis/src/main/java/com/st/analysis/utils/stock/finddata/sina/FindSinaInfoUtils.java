package com.st.analysis.utils.stock.finddata.sina;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.dao.DuplicateKeyException;

import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

public class FindSinaInfoUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(FindSinaInfoUtils.class);

	public static void findStockState() {

		DStockExample example = new DStockExample();

		example.setOrderByClause("STOCK_CODE asc");

		DStockExample.Criteria c = example.createCriteria();
		c.andTotalCapitalIsNull();

		Page pageInfo = dStockManager.selectPageByExample(example, 100);

		if (logger.isInfoEnabled()) {
			logger.info("findStockState() - Page pageInfo=" + pageInfo);
		}

		List<DStock> list = null;

		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);
			example.setPage(pageInfo);

			logger.info("p" + p);

			list = dStockManager.selectByExample(example);

			for (DStock stock : list) {
				findStockState(stock.getStockTypeCode(), stock.getStockCode());
			}
		}

	}

	public static void findStockState(String stockType, String stockCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findStock(String, String) - start");
		}

		if (stockType == null || stockCode == null || "".equals(stockType)
				|| "".equals(stockCode)) {
			logger.warn(stockType + stockCode);
			return;
		}

		// http://finance.sina.com.cn/realstock/company/sh600001/jsvar.js
		// var lta = 281645.6569;//流通A股,老数据保留
		// var lastfive = 3758.3396;//过去5个交易日平均每分钟成交量
		// var flag = 1; //判断标志
		// var totalcapital = 281645.6569; //总股本TOTAL_CAPITAL
		// var currcapital = 281645.6569; //流通股本CURR_CAPITAL
		// var curracapital = 0; //流通A股
		// var currbcapital = 0; //流通B股
		// var a_code = 'sh600001'; //流通A股代码
		// var b_code = ''; //流通B股代码
		// var papercode = 'sh600001'; //当前页面个股代码
		// var exchangerate = 0; //汇率
		// var fourQ_mgsy = -0.0079;//最近四个季度每股收益和
		// var lastyear_mgsy = 0.0710;//前一年每股收益和
		// var price_5_ago = 0;//5日前收盘价格
		// var price_10_ago = 0;//10日前收盘价格
		// var price_20_ago = 0;//20日前收盘价格
		// var price_60_ago = 0;//60日前收盘价格
		// var price_120_ago = 0;//120日前收盘价格
		// var price_250_ago = 0;//250日前收盘价格
		// var mgjzc = 4.4100;//最近报告的每股净资产
		// var stock_state = 3;//个股状态（0:无该记录; 1:上市正常交易; 2:未上市; 3:退市）
		// var trans_flag = 0;//是否显示涨跌停价（1:显示 0:不显示）
		// var profit = 2.0089;//最近年度净利润
		// var profit_four = -0.2212;//最近四个季度净利润
		// var stockType = 'A'; //股票类型 A-A股 B-B股 I-指数
		// var stockname = ''; //股票名称
		// var corr_hkstock = ''; //相关港股代码
		// var corr_bdc = ''; //相关债券可转换债
		// var corr_bde = ''; //相关债券普通企业债

		DStock stock = dStockManager.selectByPrimaryKey(stockCode);

		if (stock == null) {
			return;
		}

		// http://finance.sina.com.cn/realstock/company/sh600001/jsvar.js
		String remoteUrl = "http://finance.sina.com.cn/realstock/company/"
				+ stockType + stockCode + "/jsvar.js";
		URL url = null;
		URLConnection conn = null;
		InputStreamReader isr = null;
		InputStream is = null;
		BufferedReader bw = null;
		try {
			url = new URL(remoteUrl);

			conn = url.openConnection();

			is = conn.getInputStream();

			isr = new InputStreamReader(is, "GBK");

			bw = new BufferedReader(isr);
			String line = null;

			while ((line = bw.readLine()) != null) {
				// System.out.println(line);

				if (line.indexOf("var lta") != -1) {// 流通A股,老数据保留
					line = line.replaceAll("[^\\d\\.]*", "");

				} else if (line.indexOf("var totalcapital") != -1) {// 总股本
					line = line.replaceAll("[^\\d\\.]*", "");

					stock.setTotalCapital(Double.parseDouble(line));
				} else if (line.indexOf("var currcapital") != -1) {// 流通股本
					line = line.replaceAll("[^\\d\\.]*", "");

					stock.setCurrCapital(Double.parseDouble(line));
				} else if (line.indexOf("var stock_state") != -1) {// 个股状态（0:无该记录;
																	// 1:上市正常交易;
																	// 2:未上市;
																	// 3:退市）
					line = line.substring(0, line.indexOf(';'));

					line = line.replaceAll("[^\\d\\.]*", "");

					if ("0".equals(line)
							|| "1".equals(line)
							|| "2".equals(line)
							|| "3".equals(line)) {
						stock.setStockState(Byte.parseByte(line));
					}
				}

			}
			// logger.info(stock);
			dStockManager.updateByPrimaryKey(stock);
		} catch (MalformedURLException e) {
			logger.error("findStock(String, String) - exception ignored", e);

		} catch (IOException e) {
			logger.error("findStock(String, String) - exception ignored", e);

		} finally {
			try {
				bw.close();
				isr.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findStock(String, String) - end");
		}
	}

	// sz000001//sz300406
	public static void findSinaA2DB(int startStockCode, int endStockCode, String stockType) {
		DStock stock = null;
		String stockCode = null;
		Document doc = null;

		// http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/300002.phtml
		String htmlUrl = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/";
		String param = "";

		for (int i = startStockCode; i <= endStockCode; i++) {

			stockCode = "" + (1000000 + i); // 补零;
			stockCode = stockCode.substring(1);
			
			stock = dStockManager.selectByPrimaryKey(stockCode);
			findSinaStockInfo2DB (stock);

			// if (logger.isInfoEnabled()) {
			// logger.info("findSinaSHA2DB(int, int, Integer) - DStock stock="
			// + stock);
			// }

			// ----------------------------//
		}

	}
	
	public static void findSinaStockInfo2DB () {
		DStockExample example = new DStockExample();

		example.setOrderByClause("STOCK_CODE asc");

		DStockExample.Criteria c = example.createCriteria();
		//c.andTotalCapitalIsNull();

		c.andStockCodeLessThan("990000");
		//c.andStockNameNotLike("%B");
		Page pageInfo = dStockManager.selectPageByExample(example, 100);

		if (logger.isInfoEnabled()) {
			logger.info("findStockState() - Page pageInfo=" + pageInfo);
		}

		List<DStock> list = null;

		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);
			example.setPage(pageInfo);

			logger.info("p" + p);

			list = dStockManager.selectByExample(example);

			for (DStock stock : list) {
				//findStockState(stock.getStockTypeCode(), stock.getStockCode());
				
				findSinaStockInfo2DB(stock);
			}
		}
	}
	
	public static void findSinaStockInfo2DB (DStock stock) {
		if (stock == null) {
			return;
		}
		
		//DStock stock = null;
		//String stockCode = null;
		Document doc = null;

		// http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/300002.phtml
		String htmlUrl = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/";
		String param = "";
		
		//stockCode = "" + (1000000 + i); // 补零;
		//stockCode = stockCode.substring(1);
		String stockCode = stock.getStockCode();
		String stockType = stock.getStockTypeCode();
		
		if (stock == null) {
			
			return;
			
		} else {
//			if (stock.getTotalCapital() != null ) {
//				continue;
//			}
			//0:无该记录;1:上市正常交易;2:未上市; 3:退市
//			if (stock.getStockState() != null && 
//					(stock.getStockState() == 3
//					|| stock.getStockState() == 0
//					|| stock.getStockState() == 1
//					|| stock.getStockState() == 2
//			)) {
//				continue;
//			}
			if (stock.getCompanyName() != null 
				&& stock.getCompanyEnName() != null
				&& stock.getIntroduction() != null
				//&& stock.getIssuePrice() != null
			) {
				return;
			}
		}

		// http://finance.sina.com.cn/realstock/company/sh600001/jsvar.js
		String remoteUrl = "http://finance.sina.com.cn/realstock/company/"
				+ stockType + stockCode + "/jsvar.js";
		URL url = null;
		URLConnection conn = null;
		InputStreamReader isr = null;
		InputStream is = null;
		BufferedReader bw = null;
		try {
			url = new URL(remoteUrl);

			conn = url.openConnection();

			is = conn.getInputStream();

			isr = new InputStreamReader(is, "GBK");

			bw = new BufferedReader(isr);
			String line = null;

			while ((line = bw.readLine()) != null) {
				// System.out.println(line);

				if (line.indexOf("var lta") != -1) {// 流通A股,老数据保留
					line = line.replaceAll("[^\\d\\.]*", "");

				} else if (line.indexOf("var totalcapital") != -1) {// 总股本
					line = line.replaceAll("[^\\d\\.]*", "");

					stock.setTotalCapital(Double.parseDouble(line));
				} else if (line.indexOf("var currcapital") != -1) {// 流通股本
					line = line.replaceAll("[^\\d\\.]*", "");

					stock.setCurrCapital(Double.parseDouble(line));
				} else if (line.indexOf("var stock_state") != -1) {// 个股状态（0:无该记录;
																	// 1:上市正常交易;
																	// 2:未上市;
																	// 3:退市）
					line = line.substring(0, line.indexOf(';'));

					line = line.replaceAll("[^\\d\\.]*", "");

//					if ("3".equals(line)) {
//						stock.setIsDelisting(true);
//					}
					if ("0".equals(line)
							|| "1".equals(line)
							|| "2".equals(line)
							|| "3".equals(line)) {
						stock.setStockState(Byte.parseByte(line));
					}
				}

			}
			// logger.info(stock);
			// dStockManager.updateByPrimaryKey(stock);

			try {
				bw.close();
				isr.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			logger.error("findStock(String, String) - exception ignored", e);

		} catch (IOException e) {
			// logger.warn("findStock(String, String) - exception ignored",
			// e);

			try {
				if (bw != null) {
					bw.close();
				}

				if (isr != null) {
					isr.close();
				}

				if (is != null) {
					is.close();
				}

			} catch (Exception ex) {
				// ex.printStackTrace();
			}

			return;
		}
		// ----------------------------//

		// DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

		param = stockCode + ".phtml";

		// stock = dStockManager.selectByPrimaryKey("" + stockCode);

//		PStockMapKey pStockMap = new PStockMapKey();
//		pStockMap.setStockCode("" + stockCode);
//		pStockMap.setDimId(listedDimId); // A股
//		try {
//			pStockMapManager.insert(pStockMap);
//		} catch (DuplicateKeyException ex) {
//			logger.debug(ex.getMessage());
//		}

		// if (stock == null) {
		// stock = new DStock();
		// stock.setStockCode("" + stockCode);

		try {
			Connection connect = Jsoup.connect(htmlUrl + param).timeout(
					5000);
			doc = connect.get();
			// if (logger.isInfoEnabled()) {
			// logger.info("findSinaSHA2DB(int, int, Integer) - Document doc="
			// + doc);
			// }

		} catch (IOException e) {
			logger.warn("一次尝试:" + htmlUrl + param);
			try {
				doc = Jsoup.connect(htmlUrl + param).get();
				// if (logger.isInfoEnabled()) {
				// logger.info("findSinaSHA2DB(int, int, Integer) - Document doc="
				// + doc);
				// }

			} catch (IOException ex) {

				logger.warn(htmlUrl + param);
				logger.warn("获得服务器失败", ex);

				return;
			}
		}

		// 股票名称
		String stockName = doc.getElementById("stockName").text().trim();
		stockName = stockName.substring(0, stockName.length() - 11);
		if (!"".equals(stockName)) {
			stock.setStockName(stockName);
		} else {
			return;
		}
		setStock(stock, doc);

		// } else {
		// continue;
		// }

		// if (logger.isInfoEnabled()) {
		// logger.info("findSinaCompanyInfo2DB() - DStock stock=" + stock);
		// }

		// dStockManager.updateByPrimaryKey(stock);
		
		if (logger.isInfoEnabled()) {
			logger.info("findSinaA2DB(int, int, Integer, String) - DStock stock=" + stock);
		}
		
		dStockManager.insertOrUpdate(stock);
		if (logger.isInfoEnabled()) {
			logger.info("DStock stock=" + stock);
		}
	}

	public static void findSinaSHA2DB(int startStockCode, int endStockCode,
			Integer listedDimId) {

		Document doc = null;

		// http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/300002.phtml
		String htmlUrl = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/";
		String param = "";

		// DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		DStock stock = null;
		for (int stockCode = startStockCode; stockCode <= endStockCode; stockCode++) {

			param = stockCode + ".phtml";

			stock = dStockManager.selectByPrimaryKey("" + stockCode);

			PStockMapKey pStockMap = new PStockMapKey();
			pStockMap.setStockCode("" + stockCode);
			pStockMap.setDimId(listedDimId); // 沪市A股
			try {
				pStockMapManager.insert(pStockMap);
			} catch (DuplicateKeyException ex) {
				logger.debug(ex.getMessage());
			}

			if (stock == null) {
				stock = new DStock();
				stock.setStockCode("" + stockCode);

				try {
					Connection connect = Jsoup.connect(htmlUrl + param)
							.timeout(5000);
					doc = connect.get();
					// if (logger.isInfoEnabled()) {
					// logger.info("findSinaSHA2DB(int, int, Integer) - Document doc="
					// + doc);
					// }

				} catch (IOException e) {
					logger.warn("一次尝试:" + htmlUrl + param);
					try {
						doc = Jsoup.connect(htmlUrl + param).get();
						// if (logger.isInfoEnabled()) {
						// logger.info("findSinaSHA2DB(int, int, Integer) - Document doc="
						// + doc);
						// }

					} catch (IOException ex) {

						logger.warn(htmlUrl + param);
						logger.warn("获得服务器失败", ex);

						continue;
					}
				}

				// 股票名称
				String stockName = doc.getElementById("stockName").text()
						.trim();
				stockName = stockName.substring(0, stockName.length() - 11);
				if (!"".equals(stockName)) {
					stock.setStockName(stockName);
				} else {
					continue;
				}
				setStock(stock, doc);

			} else {
				continue;
			}

			// if (logger.isInfoEnabled()) {
			// logger.info("findSinaCompanyInfo2DB() - DStock stock=" + stock);
			// }

			// dStockManager.updateByPrimaryKey(stock);
			dStockManager.insertOrUpdate(stock);
			if (logger.isInfoEnabled()) {
				logger.info("findSinaSHA2DB(int, int, Integer) - DStock stock="
						+ stock);
			}

		}

	}

	private static void setStock(DStock stock, Document doc) {
		// (300002.SZ)
		DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		String stType = doc.getElementById("stockName")
				.getElementsByTag("span").first().text().substring(8, 10)
				.toLowerCase();
		stock.setStockTypeCode(stType);

		Elements trs = doc.getElementById("comInfo1").getElementsByTag("tr");

		// 公司名称
		String companyName = trs.get(0).getElementsByTag("td").get(1).text();

		stock.setCompanyName(companyName);

		// 公司英文名称：
		String companyEnName = trs.get(1).getElementsByTag("td").get(1).text();
		stock.setCompanyEnName(companyEnName);

		// 上市市场：
		String listedMarket = trs.get(2).getElementsByTag("td").get(1).text();
		stock.setListedMarket(listedMarket);

		// 上市日期：
		try {
			String listingDate = trs.get(2).getElementsByTag("td").get(3)
					.text();
			if (!"--".equals(listingDate)) {
				stock.setListingDate(dfDate.parse(listingDate));
			}
		} catch (ParseException e) {
			logger.warn(e);
		}
		// 发行价格
		String issuePrice = trs.get(3).getElementsByTag("td").get(1).text()
				.trim();
		if (!"".equals(issuePrice)) {
			stock.setIssuePrice(Double.parseDouble(issuePrice));
		}

		// 主承销商
		String leadUnderwriter = trs.get(3).getElementsByTag("td").get(3)
				.text();
		stock.setLeadUnderwriter(leadUnderwriter);

		// 成立日期
		try {
			String establishDate = trs.get(4).getElementsByTag("td").get(1)
					.text().trim();
			if (!"".equals(establishDate)) {
				stock.setEstablishDate(dfDate.parse(establishDate));
			}
		} catch (ParseException e) {
			logger.warn(e);
		}

		try {
			// 注册资本
			String registeredCapital = trs.get(4).getElementsByTag("td").get(3)
					.text();
			registeredCapital = registeredCapital.replaceAll("[^\\d]*", "");
			stock.setRegisteredCapital(Integer.parseInt(registeredCapital));
		} catch (Exception e) {
			logger.warn(e);
		}

		// 组织形式
		String organization = trs.get(5).getElementsByTag("td").get(3).text();
		stock.setOrganization(organization);

		// 注册地址
		String registeredAddresses = trs.get(17).getElementsByTag("td").get(1)
				.text();
		stock.setRegisteredAddresses(registeredAddresses);

		// 办公地址
		String officeAddress = trs.get(18).getElementsByTag("td").get(1).text();
		stock.setOfficeAddress(officeAddress);

		// 公司简介
		String introduction = trs.get(19).getElementsByTag("td").get(1).text();
		stock.setIntroduction(introduction);

		// 经营范围
		String scope = trs.get(20).getElementsByTag("td").get(1).text();
		stock.setScope(scope);
	}

	public static void findSinaCompanyInfo2DB() {
		if (logger.isDebugEnabled()) {
			logger.debug("findSinaCompanyInfo2DB() - start");
		}

		DStockExample example = new DStockExample();

		example.setOrderByClause("STOCK_CODE");

		DStockExample.Criteria c = example.createCriteria();
		c.andStockTypeCodeIsNull();

		Page page = dStockManager.selectPageByExample(example, 20);

		List<DStock> list = null;
		Document doc = null;

		// http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/300002.phtml
		String htmlUrl = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/";
		String param = "";

		DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

		// for (int p=1; p<=page.getTotalPage(); p++) {
		for (int p = 1; p <= page.getTotalPage(); p++) {
			page.setPage(p);
			example.setPage(page);
			list = dStockManager.selectByExample(example);

			for (DStock stock : list) {

				param = stock.getStockCode() + ".phtml";

				try {
					doc = Jsoup.connect(htmlUrl + param).post();
					// (300002.SZ)
					String stType = doc.getElementById("stockName")
							.getElementsByTag("span").first().text()
							.substring(8, 10).toLowerCase();
					stock.setStockTypeCode(stType);

					Elements trs = doc.getElementById("comInfo1")
							.getElementsByTag("tr");

					// 公司名称
					String companyName = trs.get(0).getElementsByTag("td")
							.get(1).text();
					stock.setCompanyName(companyName);

					// 公司英文名称：
					String companyEnName = trs.get(1).getElementsByTag("td")
							.get(1).text();
					stock.setCompanyEnName(companyEnName);

					// 上市市场：
					String listedMarket = trs.get(2).getElementsByTag("td")
							.get(1).text();
					stock.setListedMarket(listedMarket);

					// 上市日期：
					try {
						String listingDate = trs.get(2).getElementsByTag("td")
								.get(3).text();
						if (!"--".equals(listingDate)) {
							stock.setListingDate(dfDate.parse(listingDate));
						}
					} catch (ParseException e) {
						logger.warn(e);
					}
					// 发行价格
					String issuePrice = trs.get(3).getElementsByTag("td")
							.get(1).text().trim();
					if (!"".equals(issuePrice)) {
						stock.setIssuePrice(Double.parseDouble(issuePrice));
					}

					// 主承销商
					String leadUnderwriter = trs.get(3).getElementsByTag("td")
							.get(3).text();
					stock.setLeadUnderwriter(leadUnderwriter);

					// 成立日期
					try {
						String establishDate = trs.get(4)
								.getElementsByTag("td").get(1).text();
						stock.setEstablishDate(dfDate.parse(establishDate));
					} catch (ParseException e) {
						logger.warn(e);
					}

					try {
						// 注册资本
						String registeredCapital = trs.get(4)
								.getElementsByTag("td").get(3).text();
						registeredCapital = registeredCapital.replaceAll(
								"[^\\d]*", "");
						stock.setRegisteredCapital(Integer
								.parseInt(registeredCapital));
					} catch (Exception e) {
						logger.warn(e);
					}

					// 组织形式
					String organization = trs.get(5).getElementsByTag("td")
							.get(3).text();
					stock.setOrganization(organization);

					// 注册地址
					String registeredAddresses = trs.get(17)
							.getElementsByTag("td").get(1).text();
					stock.setRegisteredAddresses(registeredAddresses);

					// 办公地址
					String officeAddress = trs.get(18).getElementsByTag("td")
							.get(1).text();
					stock.setOfficeAddress(officeAddress);

					// 公司简介
					String introduction = trs.get(19).getElementsByTag("td")
							.get(1).text();
					stock.setIntroduction(introduction);

					// 经营范围
					String scope = trs.get(20).getElementsByTag("td").get(1)
							.text();
					stock.setScope(scope);
					// if (logger.isInfoEnabled()) {
					// logger.info("findSinaCompanyInfo2DB() - DStock stock=" +
					// stock);
					// }

					// dStockManager.updateByPrimaryKey(stock);
					dStockManager.insertOrUpdateSelective(stock);

				} catch (IOException e) {

					logger.warn("获得服务器失败", e);

					continue;
				}
				/*
				 * catch (ParseException e) { throw new
				 * RuntimeException("日期解析异常", e); //e.printStackTrace(); }
				 */
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug("findSinaCompanyInfo2DB() - end");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// // 沪市A股
		// findSinaSHA2DB(600000, 603993, 1004001);
		//
		// for (int i=600000; i<= 600010; i++) {
		// findStockState("sh", "" + i);
		// }

//		findStockState();
		// //sz000001//sz300406
		//002731
//		findSinaA2DB(512, 2731, 1004003, "sz");
//		findSinaA2DB(300001, 300100, 1004003, "sz");
//		findSinaA2DB(300101, 300201, 1004003, "sz");
//		findSinaA2DB(300201, 300301, 1004003, "sz");
//		findSinaA2DB(300301, 300401, 1004003, "sz");
		//findSinaA2DB(300001, 300419, 1004003, "sz");
		findSinaStockInfo2DB();
		
//		new Thread() {public void run() {
//			findSinaA2DB(300001, 300100, 1004003, "sz");
//		}}.start();
//		new Thread() {public void run() {
//			findSinaA2DB(300101, 300201, 1004003, "sz");
//		}}.start();
//		new Thread() {public void run() {
//			findSinaA2DB(300201, 300301, 1004003, "sz");
//		}}.start();
//		new Thread() {public void run() {
//			findSinaA2DB(300301, 300401, 1004003, "sz");
//		}}.start();
//		new Thread() {public void run() {
//			findSinaA2DB(300401, 300406, 1004003, "sz");
//		}}.start();
	}

}
