package com.st.analysis.utils.stock.finddata.sina;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.GDividendScheme;
import com.st.framework.module.stock.GDividendSchemeKey;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

public class FindSinaDividendUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FindSinaDividendUtils.class);

	//http://vip.stock.finance.sina.com.cn/corp/go.php/vISSUE_ShareBonus/stockid/300002.phtml
	private static String  SHAREBONUS_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vISSUE_ShareBonus/stockid/";
	
	//http://vip.stock.finance.sina.com.cn/corp/view/vISSUE_ShareBonusDetail.php?stockid=300002&type=1&end_date=2015-05-30
	//stockid=300002&end_date=2015-05-30
	private static String  SHAREBONUS_DETAIL_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/view/vISSUE_ShareBonusDetail.php?type=1&";
	
	private void findDataDetail (String stockCode, Date endDate) {
		Document doc = null;
		String htmlUrl = SHAREBONUS_DETAIL_BASE_URL + 
				"stockid=" + stockCode + "&end_date=" + Global.DF_DAY().format(endDate);
		
		Connection connect = Jsoup.connect(htmlUrl)
				.timeout(10000);
		
		try {
			doc = connect.get();
			
			Element el = doc.getElementById("sharebonusdetail");
			
			Elements trs = el.select("tbody").first().select("tr");
			
			
			parseDetailData (trs, stockCode, endDate) ;
			
			
		} catch (IOException e) {
			logger.warn("findData(String) - exception ignored", e);
		}
	}
	
	private void parseDetailData(Elements trs, String stockCode, Date endDate) {
		GDividendSchemeKey key = new GDividendSchemeKey();
		key.setStockCode(stockCode);
		key.setAnnouncementDate(endDate);
		
		GDividendScheme dividend = gDividendSchemeManager.selectByPrimaryKey(key);
		
		dividend.setPreTaxBonus(trs.get(0).getElementsByTag("td").last().text());
		dividend.setNonGappEarning(trs.get(1).getElementsByTag("td").last().text());
		
		dividend.setBhSharesBeforeTaxDividend(trs.get(2).getElementsByTag("td").last().text());
		dividend.setBhSharesAfterTaxDividend(trs.get(3).getElementsByTag("td").last().text());
		
		dividend.setProportionBonus(trs.get(4).getElementsByTag("td").last().text());
		dividend.setCapitalizationRatio(trs.get(5).getElementsByTag("td").last().text());
		dividend.setSurplusReserveRatio(trs.get(6).getElementsByTag("td").last().text());
		dividend.setCapitalReserveRatio(trs.get(7).getElementsByTag("td").last().text());
		
		dividend.setIssuanceTarget(trs.get(8).getElementsByTag("td").last().text());
		dividend.setEquityReferenceDay(trs.get(9).getElementsByTag("td").last().text());
		dividend.setLastTradingDay(trs.get(10).getElementsByTag("td").last().text());
		
		//11 登记日 DATE_RECORD
		//12 除息日 EX_DIVIDEND_DATE
		
		dividend.setArrivalDate(trs.get(13).getElementsByTag("td").last().text());
		
		dividend.setDividendDateOfTermination(trs.get(14).getElementsByTag("td").last().text());
		
//		/15 上市日 LISTING_DATE
		try {
			dividend.setListingDate(Global.DF_DAY().parse(trs.get(15).getElementsByTag("td").last().text()));
		} catch (ParseException e) {
			
		}
		
		dividend.setMeetingAnnouncementDate(trs.get(16).getElementsByTag("td").last().text());
		
		dividend.setRightsDeadline(trs.get(17).getElementsByTag("td").last().text());
		dividend.setAllotmentRatio(trs.get(18).getElementsByTag("td").last().text());
		dividend.setShsPlacing(trs.get(19).getElementsByTag("td").last().text());
		dividend.setTransferredRatio(trs.get(20).getElementsByTag("td").last().text());
		
		dividend.setTurnValence(trs.get(21).getElementsByTag("td").last().text());
		dividend.setValidAllotment(trs.get(22).getElementsByTag("td").last().text());
		
		dividend.setActualSharesNumber(trs.get(23).getElementsByTag("td").last().text());
		dividend.setShareSplitCount(trs.get(24).getElementsByTag("td").last().text());
		
		dividend.setActualRatio(trs.get(25).getElementsByTag("td").last().text());
		dividend.setShareSplitCount(trs.get(26).getElementsByTag("td").last().text());
		
		dividend.setForeignCurrencyExchangeRate(trs.get(27).getElementsByTag("td").last().text());
		dividend.setWeightNote(trs.get(28).getElementsByTag("td").last().text());
		
		if (logger.isInfoEnabled()) {
			logger.info("parseDetailData(Elements, String, Date) - GDividendScheme dividend=" + dividend);
		}
		
		gDividendSchemeManager.updateByPrimaryKeySelective(dividend);
	}

	public void findData (String stockCode) {
		Document doc = null;
		String htmlUrl = SHAREBONUS_BASE_URL + stockCode  + ".phtml";
		Connection connect = Jsoup.connect(htmlUrl)
				.timeout(10000);
		
		try {
			doc = connect.get();
			
			Element el = doc.getElementById("sharebonus_1");
			
			Elements trs = el.select("tbody").first().select("tr");
			
			for (Element tr : trs) {
				parseTrDoc(tr, stockCode);
			}
			
		} catch (IOException e) {
			logger.warn("findData(String) - exception ignored", e);
		}
	}
	
	private void parseTrDoc (Element tr, String stockCode) {
		
		DStock st = dStockManager.selectByPrimaryKey(stockCode);
		
		if (st == null) {
			return;
		}
		
		Elements tds = tr.getElementsByTag("td");
		
		if (tds.size() < 2) {
			return;
		}
		
		GDividendScheme dividend = new GDividendScheme();
		dividend.setStockCode(stockCode);
		dividend.setSecurities(st.getStockName());
		
		try {
			dividend.setAnnouncementDate(Global.DF_DAY().parse(tds.get(0).text()));
		} catch (ParseException e) {
			logger.warn("parentTrDoc(Element, String) - exception ignored", e);
		}
		dividend.setDelivering(Double.parseDouble(tds.get(1).text()));
		dividend.setTransfer(Double.parseDouble(tds.get(2).text()));
		dividend.setDividend(Double.parseDouble(tds.get(3).text()));
		dividend.setSchedule(tds.get(4).text());
		if (logger.isInfoEnabled()) {
			logger.info("parentTrDoc(Element, String) - GDividendScheme dividend=" + dividend);
		}

		try {
			dividend.setExDividendDate(Global.DF_DAY().parse(tds.get(5).text()));
		} catch (ParseException e) {
			
		}
		try {
			dividend.setRecordDate(Global.DF_DAY().parse(tds.get(6).text()));
		} catch (ParseException e) {
			
		}
		
		try {
			dividend.setListingDate(Global.DF_DAY().parse(tds.get(7).text()));
		} catch (ParseException e) {
			
		}
		
		gDividendSchemeManager.insertOrUpdateSelective(dividend);
		
		findDataDetail (stockCode, dividend.getAnnouncementDate());
	}
	
	public void findAllData () throws InterruptedException {
		DStockExample example = new DStockExample();
		example.setOrderByClause("STOCK_CODE");
		Page page  = dStockManager.selectPageByExample(example, 100);
		
		List<String> allCode = gDividendSchemeManager.selectAllCode();
		
		for (int p=1; p<=page.getTotalPage(); p++) {
			page.setPage(p);
			
			example.setPage(page);
			
			List<DStock> list = dStockManager.selectByExample(example);
			
			for (DStock ds : list) {
				if (allCode != null && allCode.size() > 0 && allCode.contains(ds.getStockCode())) {
					continue;
				}
				new FindSinaDividendUtils().findData(ds.getStockCode());
				Thread.sleep(500);
			}
			
		}
		
		//for (int i=300454; i<=Integer.parseInt(maxStockCode); i++) {
//			new FindSinaQGQPDataUtils().findData("" + i);
		//}
	}
	
	public static void main (String [] args) throws InterruptedException {
//		for (int i=300400; i<=300499; i++) {
//			new FindSinaDividendUtils().findData("" + i);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				
//			}
//		}
		
		new FindSinaDividendUtils().findAllData();
		
	}
}
