package com.st.analysis.utils.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.st.analysis.utils.analysis.GDayDataUtil;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.analysis.utils.stock.CheckDetailUtils;
import com.st.analysis.utils.stock.DetailUtils;
import com.st.analysis.utils.stock.FindSinaInfoUtils;
import com.st.analysis.utils.stock.FindStockUtils;
import com.st.framework.business.impl.DStockManager;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.utils.network.BaseDBUtils;

public class TaskUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(TaskUtils.class);

	private static DStockManager dStockManager = (DStockManager) getHelper()
	.getBean("dStockManager");
	
	public static void findSinaInfo2DB () {
		
		FindStockUtils.findSinaNewStock2DB();
		
		FindSinaInfoUtils.findSinaStockInfo2DB();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//findSinaInfo2DB();
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		String startDateId = "20150201";
		String endDateId = df.format(new Date());
		GDayDataUtil u = new GDayDataUtil();

//		ProxyUtils.checkDBProxySpeed();
		List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
		//ProxyUtils.setRandomProxy();
		FactProxy factProxy = proxyList.get(0);
		ProxyUtils.setProxy(factProxy.getProxyIp(), "" + factProxy.getProxyPort());
		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
		
		String stockType = "sz";
		
		for (int i = 300001; i <= Integer.parseInt(maxStockCode); i++) {
//		for (int i = 300117; i <= Integer.parseInt(maxStockCode); i++) {
//		for (int i = 300001; i <= 300100; i++) {
//		for (int i = 300101; i <= 300200; i++) {
//		for (int i = 300201; i <= 300250; i++) {
//		for (int i = 300251; i <= 300300; i++) {
//		for (int i = 300301; i <= 300350; i++) {
//		for (int i = 300201; i <= Integer.parseInt(maxStockCode); i++) {
//		for (int i = 300351; i <= 300400; i++) {

			try {
				CheckDetailUtils.appendStData("" + i, stockType);
				DetailUtils.DetailFile2DB("" + i, stockType);

				u.appendTimeDate("SZ" + i, startDateId, endDateId);
				u.appendPerData(i, df.parse(startDateId), df.parse(endDateId));
//			} catch (ParseException e) {
//				logger.warn("main(String[]) - exception ignored", e);

				Thread.sleep(1000);
			} catch (Exception e) {
				logger.warn("main(String[]) - exception ignored", e);

			}
		}

	}

}
