package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.analysis.utils.network.ProxyUtils;
import com.st.analysis.utils.stock.download.DownloadSinaDataUtils;
import com.st.framework.module.stock.DStock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckDetailUtils extends DetailUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CheckDetailUtils.class);

	public static void appendStData (String stockCode, String stockType) {
		
//		String startTime = factDownloadFileConfigManager.selectLastSuccessTimeId(stockCode.substring(2));
		String startTime = factDownloadFileConfigManager.selectLastSuccessTimeId(stockCode);
//		if (logger.isInfoEnabled()) {
//			logger.info("appendStData(String) - String startTime=" + startTime);
//		}
		
		if (startTime == null) {
			
			
//			DStock dStock = dStockManager.selectByPrimaryKey(stockCode.substring(2));
			DStock dStock = dStockManager.selectByPrimaryKey(stockCode);
			
			if (dStock == null) {
				return;
			}
			
			//0:无该记录;1:上市正常交易;2:未上市; 3:退市
			if (dStock.getStockState() == 3) {
				return;
			}
			
			if (dStock.getListingDate() == null) {
				return;
			}
			
			try {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dStock.getListingDate());
				cal.add(Calendar.DAY_OF_MONTH, -1);
//				protected static DateFormat df_simple = new SimpleDateFormat("yyyyMMdd");

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				startTime = df.format(cal.getTime());
				if (logger.isInfoEnabled()) {
					logger.info("appendStData(getListingDate startTime=" + startTime);
				}
				
				
			} catch (Exception ex) {
				//startTime = "2010-01-01";
			}
		}
		
		startTime = "2009-10-30";
//		logger.info("appendStData(String) - String startTime=" + startTime);
		
		if (startTime == null) {
			return;
		}
		
		String endTime = null;
		
		Calendar cal = Calendar.getInstance();
		
		try {
//			protected static DateFormat df_simple = new SimpleDateFormat("yyyyMMdd");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(df.parse(startTime));
			cal.add(Calendar.DAY_OF_MONTH, 1);
			
			startTime = df.format(cal.getTime());
			
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			
			endTime = df.format(cal.getTime());
			
			//endTime = "2014-12-03";
			
//			if (logger.isInfoEnabled()) {
//				logger.info("appendStData(String) - String endTime=" + endTime);
//			}
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		
		if (startTime == null || endTime == null) {
			return;
		}
				
		DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
				endTime, stockType + stockCode);
		
		
		
		//tb.downSTFile();
		
//		tb.downNioFailSTFile();
		tb.downNioSTFile();
		
		tb = null;
		
	}
	
	public static void appendStData (String stockCode,String stockType, String timeId) {
		DownloadSinaDataUtils tb = new DownloadSinaDataUtils(timeId,
				timeId, stockType + stockCode);
		tb.downNioSTFile();
		
		tb = null;
	}
	
	public static void main (String [] args) {
//		appendStData("300007");
//		appendStData("300001");
		
//		appendStData("300014");
//		ProxyUtils.setProxy("111.1.36.9", "80");
		
		for (int i=300002; i<=300002; i++) {
			appendStData("" + i, "sz");
			new DetailUtils().DetailFile2DB("" + i, "sz");
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
//		appendStData("sz300002");
		
//		appendStData("sz300002", "2014-12-19");
//		appendStData("sz300002", "2014-12-22");
//		appendStData("sz300002");
//		DetailUtils.DetailFile2DB("sz300002");
		
	}
}

class DownThread extends Thread {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DownThread.class);
	
	/**
	 * 300275
	 */
	private String stockCode;
	
	private String stockType;
	
	public DownThread() {
		
	}
	
	public DownThread(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public void run() {
		CheckDetailUtils.appendStData(this.stockCode, this.stockType);
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
}
