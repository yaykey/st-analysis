package com.st.analysis.utils.stock.finddata;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.module.stock.GIpo;
import com.st.framework.utils.db.BaseDBUtils;

public class Find163IPODataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Find163IPODataUtils.class);

	//http://quotes.money.163.com/data/ipo/shengou.html?reportdate=2015&page=2
	
	private final static String BASE_PATH = "http://quotes.money.163.com/data/ipo/shengou.html?reportdate=";
	
	private static int [] YEARS = {2015,2014,2012,2011,2010,2009,2008,2007,2006};
	
	public static void findIPOData () {
		for (int yearId : YEARS) {
			for (int page=0; page<3; page++) {
//				String url = BASE_PATH + yearId + "&page=" + page;
//				parseIPOURL(url);
				parseIPO(yearId, page);
			}
		}
	}
	
	public static void parseIPO (Integer yearId, Integer page) {
		String url = BASE_PATH + yearId;
		
		if (page != null) {
			url += "&page=" + page;
		}
		
		parseIPOURL(url, yearId);
	}
	
	private static void parseIPOURL (String htmlUrl, Integer yearId) {
		if (logger.isDebugEnabled()) {
			logger.debug("start");
		}

		Document doc = null;
		Connection connect = Jsoup.connect(htmlUrl)
				.timeout(10000);
		
		try {
			doc = connect.get();
			
			Element el = doc.getElementById("plate_performance");
			Elements trs = el.getElementsByTag("tbody").first().getElementsByTag("tr");
			
			final List<GIpo> list = new ArrayList<GIpo>();
			for (Element tr : trs) {
				list.add(parseTrElement(tr, yearId));
			}
			
			print(list);
			Global.threadPoolExecutor.execute(new Thread() {
				public void run () {
					gIpoManager.insertBatch(list);
				}
			});
			
			
		} catch (IOException e) {
			logger.warn("parseIPOURL(String, Integer) - exception ignored", e);
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("end");
		}
	}
	
	private static GIpo parseTrElement (Element tr, Integer yearId) {
		if (logger.isDebugEnabled()) {
			logger.debug("start");
		}

		GIpo ipo = new GIpo();
		ipo.setYearId(yearId);
		
		Elements tds = tr.getElementsByTag("td");
		
		ipo.setPurchaseCode(tds.get(1).text());
		ipo.setStockCode(tds.get(2).text());
		ipo.setStockName(tds.get(3).text());
		
		try {
			ipo.setOnlineIssueDate(Global.DF_DAY.parse(tds.get(4).text()));
		} catch (ParseException e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			ipo.setListingDate(Global.DF_DAY.parse(tds.get(5).text()));
		} catch (ParseException e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		ipo.setCirculation(Integer.parseInt(tds.get(6).text().replaceAll(",", "")));
		ipo.setOnlineCirculation(Integer.parseInt(tds.get(7).text().replaceAll(",", "")));
	
		try {
			ipo.setPurchaseLimit(Double.parseDouble(tds.get(8).text().replaceAll(",", "")));
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			ipo.setIssuePrice(Double.parseDouble(tds.get(9).text()));
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			ipo.setPeRatio(Double.parseDouble(tds.get(10).text()));
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			ipo.setFreezeFunds(Double.parseDouble(tds.get(11).text().replaceAll(",", "")));
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			ipo.setSuccessRate(
					Double.parseDouble(tds.get(12).text()
							.replace("%", "")));
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		try {
			String successNumber = 
					tds.get(13).getElementsByTag("a").attr("onclick");
			successNumber = 
					successNumber.substring(successNumber.indexOf("：")+3, successNumber.length()-4);
			successNumber = successNumber.replaceAll("\\\\n", ",");

			ipo.setSuccessNumber(successNumber);
		} catch (Exception e) {
			logger.warn("parseTrElement(Element) - exception ignored", e);
			
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("end");
		}
		return ipo;
	}
	
	public static void main (String [] args) {
//		parseIPO(2015,0);
//		parseIPO(2015,1);
//		parseIPO(2015,2);
//		parseIPO(2015,3);
		findIPOData();
	}
}
