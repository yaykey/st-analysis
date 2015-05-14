package com.st.analysis.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.st.Global;
import com.st.framework.utils.db.BaseDBUtils;

public class DateUtils extends BaseDBUtils {

	public static List<Integer> getTimeIds (Integer startDateId,
			Integer endDateId) {
		
		if (startDateId == null) {
			return null;
		}
		
		if (endDateId == null) {
			endDateId = Integer.parseInt(Global.DF_SIMPLE.format(new Date()));
		}
		
		Date begin = null, end = null;
		
		try {
			begin = Global.DF_SIMPLE.parse("" +startDateId);
			end = Global.DF_SIMPLE.parse("" +endDateId);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> removeList = factDateHolidayListManager.selectDaysOffTimeIds(begin, end);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		
		while (begin.compareTo(end) <= 0) {
			
			result.add(Integer.parseInt(Global.DF_SIMPLE.format(begin.getTime())));
			
			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			cal.add(Calendar.DAY_OF_MONTH, 1);
			
			begin = cal.getTime();
		}
		
		result.removeAll(removeList);
		
		
		return result;
	}
	
	public static void main (String [] args) {
		List<Integer> list = getTimeIds(20150501, 20150510);
		
		for (Integer timeid : list) {
			System.out.print(timeid + ",");
		}
	}
}
