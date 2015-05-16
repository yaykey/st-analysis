package com.st.analysis.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.st.Global;
import com.st.framework.utils.db.BaseDBUtils;

public class DateUtils extends BaseDBUtils {
	
    /** 
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回 
     * @param sourceDate 
     * @param formatLength 
     * @return 重组后的数据 
     */  
    public static String frontCompWithZore(int sourceDate,int formatLength)  
    {
     /* 
      * 0 指前面补充零 
      * formatLength 字符总长度为 formatLength 
      * d 代表为正数。 
      */  
     String newString = String.format("%0"+formatLength+"d", sourceDate);  
     return  newString;  
    }  

	public static List<Integer> getTimeIds(Integer startDateId,
			Integer endDateId) {

		if (startDateId == null) {
			return null;
		}

		if (endDateId == null) {
			endDateId = Integer.parseInt(Global.DF_SIMPLE.format(new Date()));
		}

		Date begin = null, end = null;

		try {
			begin = Global.DF_SIMPLE.parse("" + startDateId);
			end = Global.DF_SIMPLE.parse("" + endDateId);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		List<Integer> result = new ArrayList<Integer>();
		List<Integer> removeList = factDateHolidayListManager
				.selectDaysOffTimeIds(begin, end);

		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);

		while (begin.compareTo(end) <= 0) {
			int week = cal.get(Calendar.DAY_OF_WEEK);
			// System.out.println(week);
			if (week == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
				begin = cal.getTime();
			} else if (week == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				begin = cal.getTime();
			}

			result.add(Integer.parseInt(Global.DF_SIMPLE.format(begin.getTime())));

			cal.add(Calendar.DAY_OF_MONTH, 1);

			begin = cal.getTime();
		}

		result.removeAll(removeList);

		return result;
	}
	
	public static List<String> getStringSimpleTimeIds(Date startDateId,
			Date endDateId) {
		List<Date> list = getTimeIds(startDateId, endDateId);
		
		List<String> result = new ArrayList<String>();
		
		if (list != null && list.size() > 0) {
			for (Date d : list) {
				result.add(Global.DF_SIMPLE.format(d));
			}
		}
		
		return result;
	}
	
	public static List<Date> getTimeIds(Date startDateId,
			Date endDateId) {

		if (startDateId == null) {
			return null;
		}

		if (endDateId == null) {
			endDateId = new Date();
		}

		Date begin = null, end = null;

		begin = startDateId;
		end = endDateId;

		List<Date> result = new ArrayList<Date>();
		List<Date> removeList = factDateHolidayListManager
				.selectDaysOffDateIds(begin, end);

		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);

		while (begin.compareTo(end) <= 0) {
			int week = cal.get(Calendar.DAY_OF_WEEK);
			// System.out.println(week);
			if (week == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
				begin = cal.getTime();
			} else if (week == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				begin = cal.getTime();
			}

			result.add(begin);

			cal.add(Calendar.DAY_OF_MONTH, 1);

			begin = cal.getTime();
		}

		if (removeList != null) {
			result.removeAll(removeList);
		}
		return result;
	}

	public static List<Integer> getYearDateIds(Date begin, Date end) {

		if (begin == null || end == null) {
			return null;
		}

		List<Integer> result = new ArrayList<Integer>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);

		do  {

			result.add(Integer.parseInt(Global.DF_YEAR.format(begin)));

			cal.add(Calendar.YEAR, 1);

			begin = cal.getTime();
		} while (Global.DF_YEAR.format(begin).compareTo(Global.DF_YEAR.format(end)) <= 0);

		return result;
	}

	public static void main(String[] args) {
		List<Integer> list = getTimeIds(20091030, 20091130);

		for (Integer timeid : list) {
			System.out.print(timeid + ",");
		}
	}

}
