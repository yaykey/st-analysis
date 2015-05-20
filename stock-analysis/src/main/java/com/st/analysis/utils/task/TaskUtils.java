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
import com.st.analysis.utils.stock.download.DownloadQQDataUtils;
import com.st.analysis.utils.stock.download.DownloadSinaDataUtils;
import com.st.analysis.utils.stock.finddata.sina.FindSinaInfoUtils;
import com.st.analysis.utils.stock.finddata.sina.FindStockUtils;
import com.st.analysis.utils.stock.finddata.sohu.FindSohuDataUtils;
import com.st.framework.business.impl.dim.DStockManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

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

		// // findSinaInfo2DB();
		//
		// final DateFormat df = new SimpleDateFormat("yyyyMMdd");
		//
		// // String startDateId = "20110101";
		// // String endDateId = "20110531";
		//
		// // String startDateId = "20110601";
		// // String endDateId = "20111231";
		//
		// // String startDateId = "20140101";
		// // String endDateId = "20140531";
		//
		// // String startDateId = "20091030";
		// // String endDateId = "20100201";
		//
		// String startDateId = "20150501";
		// String endDateId = df.format(new Date());
		//
		final GDayDataUtil u = new GDayDataUtil();
		//
		// // ProxyUtils.checkDBProxySpeed();
		// // List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
		// // // ProxyUtils.setRandomProxy();
		// // FactProxy factProxy = proxyList.get(0);
		// // ProxyUtils.setProxy(factProxy.getProxyIp(),
		// // "" + factProxy.getProxyPort());
		// String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
		//
		// final String stockType = "SZ";
		//
		// // for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
		// // System.out.println(i);
		// // FindSohuDataUtils.appendTaskData(i);
		// // }
		//
		// for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
		// // for (int i = 300001; i <= 300350; i++) {
		// // for (int i = 300002; i <= 300002; i++) {
		// // for (int i = 300001; i <= 300100; i++) {
		// // for (int i = 300101; i <= 300200; i++) {
		// // for (int i = 300201; i <= 300300; i++) {
		// // for (int i = 300301; i <= 300350; i++) {
		// // for (int i = 300351; i <= 300400; i++) {
		// // for (int i = 300370; i <= Integer.parseInt(maxStockCode); i++) {
		// // for (int i = 300426; i <= 300426; i++) {
		// // for (int i = 300450; i <= 300450; i++) {
		// long d1 = System.currentTimeMillis();
		// try {
		// final int fi = i;
		// final String fstartDateId = startDateId;
		// final String fendDateId = endDateId;
		//
		// // CheckDetailUtils.appendStData("" + fi, stockType);
		// // new DownloadQQDataUtils().download(i, stockType);
		// // new DownloadQQDataUtils().download(i, stockType, new
		// Date("2015/05/18"), null);
		// Global.threadPoolExecutor.execute(new Runnable() {
		// @Override
		// public void run() {
		// DownloadSinaDataUtils.nioDownload("" + fi, stockType, new
		// Date("2015/05/01"), null, true);
		// // try {
		// // Thread.sleep(500);
		// // } catch (InterruptedException e2) {
		// //
		// // e2.printStackTrace();
		// // }
		// new DetailUtils().DetailFile2DB("" + fi, stockType);
		// // try {
		// // Thread.sleep(500);
		// // } catch (InterruptedException e2) {
		// //
		// // e2.printStackTrace();
		// // }
		// DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
		// try {
		// u.appendTimeDatePage("" + fi,stockType,
		// DF_SIMPLE.parse(fstartDateId), DF_SIMPLE.parse(fendDateId));
		// // Thread.sleep(500);
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		// try {
		// u.appendPerData("" + fi, df.parse(fstartDateId),
		// df.parse(fendDateId));
		// // Thread.sleep(500);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		//
		// new FindSohuDataUtils().checkAllData("" + fi);
		//
		// System.gc();
		// }
		// });
		//
		// //////
		// // Thread.sleep(500);
		// } catch (Exception e) {
		// logger.warn("main(String[]) - exception ignored", e);
		//
		// }
		// System.gc();
		// System.out.println(i + "\t->总耗时:" + (System.currentTimeMillis() -
		// d1));
		//
		// }

		// try {
		// Thread.sleep(5000);
		// Global.threadPoolExecutor.shutdown();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// gDetailManager.createGDetailTable("600000", "sh");
		// DownloadSinaDataUtils.nioDownload("600000", "sh", new
		// Date("2015/06/01"), null, true);
		// new DetailUtils().DetailFile2DB("600004", "sh");
		// u.appendTimeDatePage("600000","sh", new Date("2014/01/02"), new
		// Date());

		// u.appendPerData("600000", new Date("2014/01/02"), new Date());

		DStockExample example = new DStockExample();
		example.setOrderByClause("STOCK_CODE");
		DStockExample.Criteria c = example.createCriteria();

//		c.andStockTypeCodeEqualTo("sz");
		
//		c.andStockCodeLessThan("300000");
//
//		DStockExample.Criteria orc = example.or();
//		orc.andStockCodeGreaterThan("399999");

		Page pageInfo = dStockManager.selectPageByExample(example, 100);

		for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
			pageInfo.setPage(p);
			example.setPage(pageInfo);
			List<DStock> list = dStockManager.selectByExample(example);

			for (final DStock st : list) {

				// DownloadSinaDataUtils.nioDownload(st.getStockCode(),
				// st.getStockTypeCode(), new Date("2014/01/02"), null, true);
				Global.threadPoolExecutor.execute(new Runnable() {
					@Override
					public void run() {
						
						DownloadSinaDataUtils.nioDownload(st.getStockCode(),
								 st.getStockTypeCode(), new Date("2015/01/01"), null, true);
						
						DetailUtils detailUtils = new DetailUtils();
						detailUtils.DetailFile2DB(st.getStockCode(),
								st.getStockTypeCode());
						detailUtils = null;
						
						// DateFormat DF_SIMPLE = new
						// SimpleDateFormat("yyyyMMdd");
						// try {
						// u.appendTimeDatePage(st.getStockCode(),
						// st.getStockTypeCode(), new Date("2014/01/02"), new
						// Date());
						// // Thread.sleep(500);
						// } catch (Exception e1) {
						// e1.printStackTrace();
						// }
						// try {
						// u.appendPerData(st.getStockCode(), new
						// Date("2014/01/02"), new Date());
						// // Thread.sleep(500);
						// } catch (Exception e) {
						// e.printStackTrace();
						// }

						//System.gc();
						
					}
				});
			}
			
		}

		// for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
		// // for (int i = 300001; i <= 300350; i++) {
		// // for (int i = 300002; i <= 300002; i++) {
		// // for (int i = 300001; i <= 300100; i++) {
		// // for (int i = 300101; i <= 300200; i++) {
		// // for (int i = 300201; i <= 300300; i++) {
		// // for (int i = 300301; i <= 300350; i++) {
		// // for (int i = 300351; i <= 300400; i++) {
		// // for (int i = 300370; i <= Integer.parseInt(maxStockCode); i++) {
		// // for (int i = 300426; i <= 300426; i++) {
		// // for (int i = 300450; i <= 300450; i++) {
		// long d1 = System.currentTimeMillis();
		// try {
		// final int fi = i;
		// final String fstartDateId = startDateId;
		// final String fendDateId = endDateId;
		//
		// // CheckDetailUtils.appendStData("" + fi, stockType);
		// // new DownloadQQDataUtils().download(i, stockType);
		// // new DownloadQQDataUtils().download(i, stockType, new
		// Date("2015/05/18"), null);
		// Global.threadPoolExecutor.execute(new Runnable() {
		// @Override
		// public void run() {
		// DownloadSinaDataUtils.nioDownload("" + fi, stockType, new
		// Date("2015/05/01"), null, true);
		// // try {
		// // Thread.sleep(500);
		// // } catch (InterruptedException e2) {
		// //
		// // e2.printStackTrace();
		// // }
		// new DetailUtils().DetailFile2DB("" + fi, stockType);
		// // try {
		// // Thread.sleep(500);
		// // } catch (InterruptedException e2) {
		// //
		// // e2.printStackTrace();
		// // }
		// DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
		// try {
		// u.appendTimeDatePage("" + fi,stockType,
		// DF_SIMPLE.parse(fstartDateId), DF_SIMPLE.parse(fendDateId));
		// // Thread.sleep(500);
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		// try {
		// u.appendPerData("" + fi, df.parse(fstartDateId),
		// df.parse(fendDateId));
		// // Thread.sleep(500);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		//
		// new FindSohuDataUtils().checkAllData("" + fi);
		//
		// System.gc();
		// }
		// });
		//
		// //////
		// // Thread.sleep(500);
		// } catch (Exception e) {
		// logger.warn("main(String[]) - exception ignored", e);
		//
		// }
		// System.gc();
	}

}
