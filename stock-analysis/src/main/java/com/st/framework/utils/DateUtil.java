package com.st.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.st.Global;
import com.st.framework.utils.db.BaseDBUtils;

public class DateUtil extends BaseDBUtils {

	public final static String[][] TimeRanges = { 
		{ "09:25:00", "09:30:00" },
		{ "09:30:00", "11:30:00" }, 
		{ "13:00:00", "15:00:00" },
		{ "15:00:00", "15:01:00" } };

	public final static int [] pers = {
		1,2,3,4,5,6,7,8,9,10,11
	};
	
	public static int getDaysDec (Date d1, Date d2) {
		
		//long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
		
		Date beginDate=null, endDate=null;
		
		if (d1.compareTo(d2) < 0) {
			beginDate = d1;
			endDate = d2;
		} else {
			beginDate = d2;
			endDate = d1;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		if (cal.get(Calendar.DAY_OF_WEEK) == 7) {//六
			cal.add(Calendar.DAY_OF_YEAR, 2);
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {//日
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		List<String> daysOff = 
				factDateHolidayListManager.selectDaysOff(beginDate, endDate);
		
		int days = 0;
		while (cal.getTime().compareTo(endDate) <= 0) {
			int day_of_week = cal.get(Calendar.DAY_OF_WEEK);			
			if (day_of_week == 7) {//六
				cal.add(Calendar.DAY_OF_YEAR, 2);
				continue;
			} else if (day_of_week == 1) {//日
				cal.add(Calendar.DAY_OF_YEAR, 1);
				continue;
			}
			
			if (!daysOff.contains(Global.DF_DAY.format(cal.getTime()))) {
				days++;
			}
			
						
//			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		
//		float days = (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24); 
		
		return days;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getRangeTimes(int sec) {

		List list = new ArrayList<Date>();

		DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
		//Calendar cal = Calendar.getInstance();
		sec = sec * 1000;
		long start = 0, end = 0;
		String p = "", t = "";
		for (int i = 0; i < TimeRanges.length; i++) {
			try {
				start = dftime.parse(TimeRanges[i][0]).getTime();
				end = dftime.parse(TimeRanges[i][1]).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			while (start <= end) {

				p = dftime.format(new Date(start));
				if (!p.equals(t)) {
					list.add(p);
				}
				t = p;

				start += sec;
			}

		}

		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
//		List list = DateUtil.getRangeTimes(60 * 5);

//		System.out.println(list.size() + ":" + list);
		
		int days = getDaysDec(new Date("2015/05/01"), new Date("2015/05/11"));
		System.out.println(days);
	}
}
