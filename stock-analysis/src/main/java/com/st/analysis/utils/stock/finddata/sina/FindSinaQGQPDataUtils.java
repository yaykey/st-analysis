package com.st.analysis.utils.stock.finddata.sina;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.GQgqp;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

public class FindSinaQGQPDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FindSinaQGQPDataUtils.class);

	//http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/singleqgqp/index.phtml?symbol=300002&num=1000
	String QGQP_BASE_URL = "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/singleqgqp/index.phtml?num=2000&symbol=";
	
	
	public void findData (String stockCode) {
		Document doc = null;
		String htmlUrl = QGQP_BASE_URL + stockCode;
		
		Connection connect = Jsoup.connect(htmlUrl)
				.timeout(10000);
		
		try {
			doc = connect.get();
			
			Element el = doc.getElementById("dataTable");
			
			if (el == null) {
				return;
			}
			
			Elements trs = el.select("tbody").first().select("tr");
			
			if (trs != null && trs.size() > 0) {
				for (Element tr : trs) {
					parselData (tr, stockCode) ;
				}
			}
			
			
		} catch (IOException e) {
			logger.warn("findData(String) - exception ignored", e);
			
		} catch (NullPointerException e) {
			logger.warn(e.getMessage());
		}
	}


	private void parselData(Element tr, String stockCode) {
		Elements tds = tr.getElementsByTag("td");
//代码 		名称 		日期 			当日千股千评 			收盘价 	涨跌额 	涨跌幅(%) 昨收 		今开 		最高 		最低
//300002 	神州泰岳 	2015-06-03 	走势强于大盘,建议持仓 	39.08 	0.83 	2.1699 	38.25 	38.28 	39.69 	37.52
		GQgqp qgqp = new GQgqp();
		qgqp.setSourceType("sina");
		
		qgqp.setCode(tds.get(0).text());
		qgqp.setName(tds.get(1).text());
		try {
			qgqp.setDate(Global.DF_DAY().parse(tds.get(2).text()));
		} catch (ParseException e) {
			logger.warn("parselData(Element, String) - exception ignored", e);
		}
		
		qgqp.setComment(tds.get(3).text());
		qgqp.setClose(Double.parseDouble(tds.get(4).text()));
		qgqp.setNetChange(Double.parseDouble(tds.get(5).text()));
		qgqp.setNetChangePer(tds.get(6).text());
		qgqp.setPrevClose(Double.parseDouble(tds.get(7).text()));
		qgqp.setOpen(Double.parseDouble(tds.get(8).text()));
		qgqp.setHigh(Double.parseDouble(tds.get(9).text()));
		qgqp.setClose(Double.parseDouble(tds.get(10).text()));
		
		if (logger.isInfoEnabled()) {
			logger.info(qgqp);
		}
		
		gQgqpManager.insertOrUpdateSelective(qgqp);
		
	}
	
	public void findAllData () throws InterruptedException {
		DStockExample example = new DStockExample();
		example.setOrderByClause("STOCK_CODE");
		Page page  = dStockManager.selectPageByExample(example, 100);
		
		List<String> allCode = gQgqpManager.selectAllCode();
		
		for (int p=1; p<=page.getTotalPage(); p++) {
			page.setPage(p);
			
			example.setPage(page);
			
			List<DStock> list = dStockManager.selectByExample(example);
			
			for (DStock ds : list) {
				if (allCode != null && allCode.size() > 0 && allCode.contains(ds.getStockCode())) {
					continue;
				}
				new FindSinaQGQPDataUtils().findData(ds.getStockCode());
				Thread.sleep(500);
			}
			
		}
		
		//for (int i=300454; i<=Integer.parseInt(maxStockCode); i++) {
//			new FindSinaQGQPDataUtils().findData("" + i);
		//}
	}
	
	public static void main (String [] args) throws InterruptedException {
//		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
//
//		Page page  = dStockManager.selectPageByExample(example, pageSize)
//		
//		for (int i=300454; i<=Integer.parseInt(maxStockCode); i++) {
//			new FindSinaQGQPDataUtils().findData("" + i);
//		}
		
		new FindSinaQGQPDataUtils().findAllData();
		
	}
	
}
