package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.factdate.FactDateHolidayManager;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.utils.db.BaseDBUtils;

public class CheckFailUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CheckFailUtils.class);

	static DateFormat df = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 
	 * @param stockCode
	 * @return
	 */
	public static List<Integer> getFailDateIdList(String stockCode,
			Integer startDateId, Integer endDateId) {

		List<Integer> dateDateId = gDetailManager.selectDetailActiveDateId(
				stockCode, startDateId, endDateId);
		if (logger.isInfoEnabled()) {
			logger.info("getFailDateIdList(String, Integer, Integer) - List<Integer> dateDateId="
					+ dateDateId);
		}

		List<Integer> holidayList = factDateHolidayManager.selectHolidayList(
				startDateId, endDateId);
		if (logger.isInfoEnabled()) {
			logger.info("getFailDateIdList(String, Integer, Integer) - List<Integer> holidayList="
					+ holidayList);
		}

		List<Integer> failList = new ArrayList<Integer>();

		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		Integer start = startDateId;
		Integer end = endDateId;

		try {
			cal.setTime(df.parse("" + start));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		while (start.compareTo(end) <= 0) {

			// logger.info(dateDateId.contains(start) + ":"
			// + holidayList.contains(start));

			if (!dateDateId.contains(start) && !holidayList.contains(start)) {
				if (cal.get(Calendar.DAY_OF_WEEK) == 7
						|| cal.get(Calendar.DAY_OF_WEEK) == 1) {

				} else {
					failList.add(start);
				}
			}

			cal.add(Calendar.DAY_OF_MONTH, 1);

			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}

			start = Integer.parseInt(df.format(cal.getTime()));
		}

		return failList;
	}

	public static void checkFail() {
		// gDetailManager
		// gStockDayManager
		Integer maxDateId = null;
		List<GStockDay> list = null;

		for (int i = 300001; i <= 300406; i++) {
			maxDateId = gStockDayManager.selectMaxDateId(i);
			if (logger.isInfoEnabled()) {
				logger.info("checkFail() - Integer maxDateId=" + maxDateId);
			}
			if (maxDateId != null) {
				try {
					list = gDetailManager.selectStockDay("sz" + i, maxDateId,
							null);
				} catch (Exception ex) {
					logger.warn(ex.getMessage());
				}
				if (logger.isInfoEnabled()) {
					logger.info("checkFail() - List<GStockDay> list=" + list);
				}
				if (list != null && list.size() > 0) {
					gStockDayManager.insertBatch(list);
				}
			}
		}

	}

	public static void main(String[] args) {
//		 checkFail();
		List list = getFailDateIdList("SZ300002", 20150123, 20150211);
//		if (logger.isInfoEnabled()) {
//			logger.info("main(String[]) - List list=" + list);
//		}
	}
}
