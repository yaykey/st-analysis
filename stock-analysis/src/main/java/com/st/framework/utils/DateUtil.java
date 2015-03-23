package com.st.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public final static String[][] TimeRanges = { 
		{ "09:25:00", "09:30:00" },
		{ "09:30:00", "11:30:00" }, 
		{ "13:00:00", "15:00:00" },
		{ "15:00:00", "15:01:00" } };

	public final static int [] pers = {
		1,2,3,4,5,6,7,8,9,10,11
	};
	
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
		List list = DateUtil.getRangeTimes(60 * 5);

		System.out.println(list.size() + ":" + list);
	}
}
