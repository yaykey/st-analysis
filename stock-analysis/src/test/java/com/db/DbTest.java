package com.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanRequest;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanResponse;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.utils.db.BaseDBUtils;

public class DbTest extends BaseDBUtils {

	public static void main(String[] args) {
		List<TimeRangeCountBeanRequest> list = new ArrayList<TimeRangeCountBeanRequest>();
		list.add(new TimeRangeCountBeanRequest("09:25:00", "09:25:00-09:30:00"));
		
		//list.add(new TimeRangeCountBeanRequest("09:30:00", "09:25:00-09:30:00"));

		String[][] hss = { 
				{"09", "30"}, {"10", "0"}, {"11", "0", "30"}
				, {"13", "0"}, {"14", "0"}, {"15", "0", "0"}
		};
		String[] ms = { "00", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
				"39", "40", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
				"59" };
		
		String timeIdTemp = "";
		String timeNameTemp = "";
		
		for (int i=0; i<hss.length; i++) {
			String [] hs = hss[i];
			int begin = 0;
			int end = 59;
			
			begin = Integer.parseInt(hs[1]);
			
			if (hs.length == 3) {
				end = Integer.parseInt(hs[2]);
			}
			
			for (int j=begin; j<= end; j++) {
				timeIdTemp = hs[0] + ":" + ms[j] + ":00";
				
				if ("11:30:00".equals(timeIdTemp)) {
					timeNameTemp = "11:30:00";
				} else if ("15:00:00".equals(timeIdTemp)) {
					timeNameTemp = timeIdTemp + "+";					
				} else  if (!"15:00:00".equals(timeIdTemp)) {
					timeNameTemp = timeIdTemp;
					timeNameTemp += "-";
					if (j<59) {
						timeNameTemp += hs[0] + ":" + ms[j+1] + ":00";
					} else {
						timeNameTemp += hss[i+1][0] + ":00:00";
					}
				}
				
				list.add(new TimeRangeCountBeanRequest(timeIdTemp, timeNameTemp));
			}
		}
		

//		list.add(new TimeRangeCountBeanRequest("15:00:00", "15:00:00+"));

		for (TimeRangeCountBeanRequest timeRangeCountBeanRequest : list) {
			System.out.println(timeRangeCountBeanRequest);
		}
		
		GStockDayExample example = new GStockDayExample();
		GStockDayExample.Criteria c = example.createCriteria();
		c.andHighTimeIdIsNotNull();
		
		c.andHighPerGreaterThanOrEqualTo(2D);
		c.andHighPerLessThan(3D);
		c.andDateGreaterThanOrEqualTo(new Date("2014/06/01"));
		
		List<TimeRangeCountBeanResponse> rangeCountList = gStockDayManager.selectByTimeRange("high_time_id", list, example);
		
		for (TimeRangeCountBeanResponse timeRangeCountBeanResponse : rangeCountList) {
			System.out.println(timeRangeCountBeanResponse);
		}
	}
}
