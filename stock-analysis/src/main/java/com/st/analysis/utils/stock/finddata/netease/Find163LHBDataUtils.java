package com.st.analysis.utils.stock.finddata.netease;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.module.stock.GLHBReport;
import com.st.framework.module.stock.GLHBTop5;
import com.st.framework.utils.db.BaseDBUtils;

public class Find163LHBDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Find163LHBDataUtils.class);
	
	
	//http://quotes.money.163.com/trade/lhb_300002.html
	// TRADING VOLUME TOTAL VOLUME TOTAL CAPITAL A SHARE
	// BUY AMOUNT
	
	//http://quotes.money.163.com/trade/lhb_300002.html
	private static String LHB_BASE_URL = "http://quotes.money.163.com/trade/lhb_";
	
	public void findLhbData (String stockCode) {
		Document doc = null;
		
		String htmlUrl = LHB_BASE_URL + stockCode + ".html";
		
		Connection connect = Jsoup.connect(htmlUrl)
				.timeout(10000);
		
		try {
			doc = connect.get();
			
			Element el = doc.getElementsByClass("inner_box").first();
			
			Elements rpts = el.getElementsByClass("info_panel2");
			Elements top5s = el.getElementsByClass("limit_sale");
			
			Element rpt = null, top5 = null;
			GLHBReport report = null;
			List<GLHBTop5> top5List = null;
			for (int i=0; i<rpts.size(); i++) {
				rpt = rpts.get(i);
				top5 = top5s.get(i);
				
				report = parseReport(rpt, stockCode);
				
				gLHBReportManager.insertOrUpdate(report);
				
				//System.out.println(report);
				
				top5List = parseTop5List(top5, stockCode, report.getDateId());
				
				gLHBTop5Manager.insertBatch(top5List);
			}
			
			
		} catch (IOException e) {
			logger.warn("findLhbData(String) - exception ignored", e);
			
		}
	}
	
	private List<GLHBTop5> parseTop5List (Element top5, String stockCode, Date dateId) {
		Elements trs = top5.getElementsByTag("tbody").first().getElementsByTag("tr");
		
		List<GLHBTop5> list = new ArrayList<GLHBTop5>();
		
		list.add(parseTop5(trs.get(1), stockCode, dateId, "buy"));
		list.add(parseTop5(trs.get(2), stockCode, dateId, "buy"));
		list.add(parseTop5(trs.get(3), stockCode, dateId, "buy"));
		list.add(parseTop5(trs.get(4), stockCode, dateId, "buy"));
		list.add(parseTop5(trs.get(5), stockCode, dateId, "buy"));
		
		list.add(parseTop5(trs.get(7), stockCode, dateId, "sell"));
		list.add(parseTop5(trs.get(8), stockCode, dateId, "sell"));
		list.add(parseTop5(trs.get(9), stockCode, dateId, "sell"));
		list.add(parseTop5(trs.get(10), stockCode, dateId, "sell"));
		list.add(parseTop5(trs.get(11), stockCode, dateId, "sell"));
		
		return list;
	}
	
	private GLHBTop5 parseTop5 (Element top5tr, String stockCode, Date dateId, String type) {
		GLHBTop5 top5 = new GLHBTop5();
		
		top5.setDateId(dateId);
		top5.setStockCode(stockCode);
		top5.setType(type);
		
		Elements tds = top5tr.getElementsByTag("td");
		String compName = tds.get(0).text();
		String compId = tds.get(0).getElementsByTag("a").first()
				.attr("href");
		compId = compId.substring(compId.indexOf("agencylist_") + 11, compId.length()-6);
		
		
		top5.setSecId(Long.parseLong(compId));
		top5.setSecName(compName);
		
		top5.setBuyAmount(Double.parseDouble(tds.get(1).text().trim()));
		top5.setSellAmount(Double.parseDouble(tds.get(2).text().trim()));
		top5.setNetAmount(Double.parseDouble(tds.get(3).text().trim()));
		
		return top5;
	}
	
	private GLHBReport parseReport (Element rpt, String stockCode) {
		
		GLHBReport report = new GLHBReport();
		report.setStockCode(stockCode);
				
		Elements trs = rpt.getElementsByTag("tr");
		
		{
			Elements tds = trs.get(0).getElementsByTag("td");
			
			report.setReasons(tds.get(0).text());
			String dateId = tds.get(1).text().trim().substring(5);
			try {
				DateFormat DF_DAY = new SimpleDateFormat("yyyy-MM-dd");
				report.setDateId(DF_DAY.parse(dateId));
			} catch (ParseException e) {
				System.out.println(tds);
				if (logger.isInfoEnabled()) {
					logger.info("parseReport(Element, String) - Elements tds=" + tds);
				}

				System.out.println("dateId" + dateId);
				if (logger.isInfoEnabled()) {
					logger.info("parseReport(Element, String) - String dateId=" + dateId);
				}

				e.printStackTrace();
			}
		}
		
		{
			Elements tds = trs.get(1).getElementsByTag("td");
			
			String amount = tds.get(0).text().substring(5);
			amount = amount.substring(0, amount.length() - 2);
			report.setTotalAmount(Double.parseDouble(amount));
			
			// 	总成交量：5285.59万股(占总股本：3.98% 占流通A股4.31%) 
			String volume = tds.get(1).text().trim().substring(5);
			
			String totalVolume = volume.substring(0, volume.indexOf("万股"));
			String totalCapitalPer = volume.substring(volume.indexOf("占总股本：")+5, volume.indexOf("% 占流通A股"));
			String aSharePer = volume.substring(volume.indexOf("占流通A股") + 5, volume.length()-2);
			
			report.setTotalVolume(Double.parseDouble(totalVolume));
			report.setTotalCapitalPer(Double.parseDouble(totalCapitalPer));
			report.setaSharePer(Double.parseDouble(aSharePer));
		}
		
		
		return report;
	}
	
	public static void main (String [] args) {
		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
		for (int i=300001; i<=Integer.parseInt(maxStockCode); i++) {
			final int fi = i;
			Global.threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                	long d = System.currentTimeMillis();
        			new Find163LHBDataUtils().findLhbData("" + fi);
        			System.out.println(fi + "\t->耗时:" + (System.currentTimeMillis() - d));
                }
            });
			
		}
		
	}
}
