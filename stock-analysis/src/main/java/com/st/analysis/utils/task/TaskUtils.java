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
import com.st.analysis.utils.stock.FindSinaInfoUtils;
import com.st.analysis.utils.stock.FindStockUtils;
import com.st.framework.business.impl.DStockManager;
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

		String startDateId = "20150323";
		String endDateId = df.format(new Date());
		final GDayDataUtil u = new GDayDataUtil();

		// ProxyUtils.checkDBProxySpeed();
		List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
		// ProxyUtils.setRandomProxy();
		FactProxy factProxy = proxyList.get(0);
		ProxyUtils.setProxy(factProxy.getProxyIp(),
				"" + factProxy.getProxyPort());
		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();

		final String stockType = "sz";

		for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
			// for (int i = 300117; i <= Integer.parseInt(maxStockCode); i++) {
//			 for (int i = 300002; i <= 300002; i++) {
			// for (int i = 300101; i <= 300200; i++) {
			// for (int i = 300201; i <= 300250; i++) {
			// for (int i = 300251; i <= 300300; i++) {
			// for (int i = 300301; i <= 300350; i++) {
			// for (int i = 300201; i <= Integer.parseInt(maxStockCode); i++) {
			// for (int i = 300351; i <= 300400; i++) {

			try {
				final int fi = i;
				final String fstartDateId = startDateId;
				final String fendDateId = endDateId;
				
//				Global.threadPoolExecutor.execute(new Runnable() {					
//					@Override
//					public void run() {
						
						CheckDetailUtils.appendStData("" + fi, stockType);
						DetailUtils.DetailFile2DB("" + fi, stockType);
						
						u.appendTimeDate("SZ" + fi, fstartDateId, fendDateId);
						try {
							u.appendPerData(fi, df.parse(fstartDateId), df.parse(fendDateId));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
//					}
//				});
					
//					
//				CheckDetailUtils.appendStData("" + i, stockType);
//				DetailUtils.DetailFile2DB("" + i, stockType);
//				final int fi = i;
//				final String fstartDateId = startDateId;
//				final String fendDateId = endDateId;
////				Global.threadPoolExecutor.execute(new Runnable() {					
////					@Override
////					public void run() {
//						u.appendTimeDate("SZ" + fi, fstartDateId, fendDateId);						
////					}
////				});
//								
////				u.appendTimeDate("SZ" + i, startDateId, endDateId);
////				Global.threadPoolExecutor.execute(new Runnable() {					
////					@Override
////					public void run() {
////						try {
//							u.appendPerData(fi, df.parse(fstartDateId), df.parse(fendDateId));
////						} catch (ParseException e) {
////							e.printStackTrace();
////						}
////					}
////				});
				
				// System.out.println("Global.threadPoolExecutor.getActiveCount()="
				// + Global.threadPoolExecutor.getActiveCount());
				// } catch (ParseException e) {
				// logger.warn("main(String[]) - exception ignored", e);

				 Thread.sleep(1000);
			} catch (Exception e) {
				logger.warn("main(String[]) - exception ignored", e);

			}
		}

		try {
			Thread.sleep(5000);
			Global.threadPoolExecutor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
