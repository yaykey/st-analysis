package com.st.analysis.utils.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.st.Global;
import com.st.analysis.utils.analysis.GDayDataUtil;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.analysis.utils.stock.CheckDetailUtils;
import com.st.analysis.utils.stock.DetailUtils;
import com.st.analysis.utils.stock.DownloadQQDataUtils;
import com.st.analysis.utils.stock.DownloadSinaDataUtils;
import com.st.analysis.utils.stock.finddata.FindSinaInfoUtils;
import com.st.analysis.utils.stock.finddata.FindSohuDataUtils;
import com.st.analysis.utils.stock.finddata.FindStockUtils;
import com.st.framework.business.impl.dim.DStockManager;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.utils.db.BaseDBUtils;

public class TaskUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(TaskUtils.class);

	private static DStockManager dStockManager = (DStockManager) getHelper()
			.getBean("dStockManager");

	public static void findSinaInfo2DB() {

		FindStockUtils.findSinaNewStock2DB();

		FindSinaInfoUtils.findSinaStockInfo2DB();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// findSinaInfo2DB();

		final DateFormat df = new SimpleDateFormat("yyyyMMdd");

//		String startDateId = "20110101";
//		String endDateId = "20110531";
		
//		String startDateId = "20110601";
//		String endDateId = "20111231";
		
//		String startDateId = "20140101";
//		String endDateId = "20140531";
		
//		String startDateId = "20091030";
//		String endDateId = "20100201";
		
		String startDateId = "20091030";
		String endDateId = df.format(new Date());
		
		final GDayDataUtil u = new GDayDataUtil();

		// ProxyUtils.checkDBProxySpeed();
//		List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
//		// ProxyUtils.setRandomProxy();
//		FactProxy factProxy = proxyList.get(0);
//		ProxyUtils.setProxy(factProxy.getProxyIp(),
//				"" + factProxy.getProxyPort());
		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();

		final String stockType = "SZ";

//		for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
//			System.out.println(i);
//			FindSohuDataUtils.appendTaskData(i);
//		}
		
		for (int i = 300308; i <= Integer.parseInt(maxStockCode); i++) {
//		for (int i = 300001; i <= 300350; i++) {
//			 for (int i = 300002; i <= 300002; i++) {
//		for (int i = 300001; i <= 300100; i++) {
//			 for (int i = 300101; i <= 300200; i++) {
//			 for (int i = 300201; i <= 300300; i++) {
//			 for (int i = 300301; i <= 300350; i++) {
//		for (int i = 300351; i <= 300400; i++) {
//		for (int i = 300401; i <= Integer.parseInt(maxStockCode); i++) {
//		for (int i = 300426; i <= 300426; i++) {
//		for (int i = 300001; i <= 300001; i++) {
			long d1 = System.currentTimeMillis();
			try {
				final int fi = i;
				final String fstartDateId = startDateId;
				final String fendDateId = endDateId;
					
//				CheckDetailUtils.appendStData("" + fi, stockType);
//				new DownloadQQDataUtils().download(i, stockType);
//				new DownloadQQDataUtils().download(i, stockType, new Date("2014/08/19"), null);
				
//				DownloadSinaDataUtils.nioDownloadAsynchronous("" + fi, stockType, null, null);
//				DownloadSinaDataUtils.nioDownload("" + fi, stockType, null, null, false);
				
//				DetailUtils.DetailFile2DB("" + fi, stockType);
//				
//				u.appendTimeDate(stockType + fi, fstartDateId, fendDateId);
				u.appendTimeDatePage(stockType + fi, Global.DF_SIMPLE.parse(fstartDateId), Global.DF_SIMPLE.parse(fendDateId));
				try {
					u.appendPerData(fi, df.parse(fstartDateId), df.parse(fendDateId));
				} catch (ParseException e) {
					e.printStackTrace();
				}
////
//				 Thread.sleep(1000);
			} catch (Exception e) {
				logger.warn("main(String[]) - exception ignored", e);

			}
			System.gc();
			System.out.println(i + "\t->总耗时:" + (System.currentTimeMillis() - d1));
			
		}

//		try {
//			Thread.sleep(5000);
//			Global.threadPoolExecutor.shutdown();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
