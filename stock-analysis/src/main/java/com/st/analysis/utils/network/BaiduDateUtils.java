package com.st.analysis.utils.network;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.framework.business.impl.factdate.FactDateAlmanacManager;
import com.st.framework.business.impl.factdate.FactDateHolidayManager;
import com.st.framework.utils.db.BaseDBUtils;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaiduDateUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BaiduDateUtils.class);

	// protected static FactDateHolidayManager factDateHolidayManager =
	// (FactDateHolidayManager) getHelper()
	// .getBean("factDateHolidayManager");

	// protected static FactDateHolidayListMapper factDateHolidayListMapper =
	// (FactDateHolidayListMapper) getHelper()
	// .getBean("factDateHolidayListMapper");

	protected static FactDateAlmanacManager factDateAlmanacManager = (FactDateAlmanacManager) getHelper()
			.getBean("factDateAlmanacManager");

	protected static FactDateHolidayManager factDateHolidayManager = (FactDateHolidayManager) getHelper()
			.getBean("factDateHolidayManager");

	public static String findBaiduDateJSONStr(String queryDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("findBaiduDate() - start");
		}

		// http://opendata.baidu.com/api.php?query=2014年2月&co=&resource_id=6018

		// String url =
		// "http://opendata.baidu.com/api.php?query=2014年2月&co=&resource_id=6018";
		String url = "http://opendata.baidu.com/api.php?co=&resource_id=6018&query=";
		url += queryDate;
		if (logger.isInfoEnabled()) {
			logger.info("findBaiduDateJSONStr(String) - String url=" + url);
		}

		InputStreamReader isr = null;
		StringWriter writer = new StringWriter();
		try {

			HttpURLConnection httpConnection = (HttpURLConnection) new URL(url)
					.openConnection();

			httpConnection.setRequestMethod("GET");

			int responseCode = httpConnection.getResponseCode();

			if (responseCode >= 400) {
				throw new RuntimeException("Web服务器响应错误");
			}

			isr = new InputStreamReader(httpConnection.getInputStream(), "GBK");

			char[] cbuff = new char[1024];

			int bytesRead;

			while ((bytesRead = isr.read(cbuff, 0, cbuff.length)) != -1) {

				writer.write(cbuff, 0, bytesRead);

			}

			// logger.info(writer.toString());

			isr.close();

		} catch (IOException ioe) {
			logger.error("run()", ioe);

			ioe.printStackTrace();

			logger.error(url);

			try {
				isr.close();
			} catch (IOException e) {
				logger.error("findBaiduDate()", e);

				e.printStackTrace();
			}

			throw new RuntimeException("获取文件异常", ioe);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findBaiduDate() - end");
		}

		return writer.toString();
	}

	private static final DateFormat dfms = new SimpleDateFormat("yyyy-MM");

	private static final DateFormat dfmcn = new SimpleDateFormat("yyyy年M月");

	public static void findBaiduDate2DB(String startDate, String endDate) {

		Calendar cal = Calendar.getInstance();

		// Calendar calWeek = Calendar.getInstance();

		try {
			cal.setTime(dfms.parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String str = null;
		while (startDate.compareTo(endDate) <= 0) {

			str = BaiduDateUtils.findBaiduDateJSONStr(dfmcn.format(cal.getTime()));

			str = str.replaceAll("list#num#baidu", "listnumbaidu");

			// "desc"->"description"
			str = str.replaceAll(",[\r\n ]*\"desc\"[\r\n ]*:",
					",\"description\":");

			JSONObject jsonObject = JSONObject.fromObject(str);

			JSONObject dataJsonObject = jsonObject.getJSONArray("data")
					.getJSONObject(0);

			factDateAlmanacManager.insertDataJsonObject(dataJsonObject);

			// ---------------------------//
			cal.add(Calendar.MONTH, 1);
			startDate = dfms.format(cal.getTime());

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		findBaiduDate2DB("2016-1", "2016-12");

		//destroyFactory();
	}

}
