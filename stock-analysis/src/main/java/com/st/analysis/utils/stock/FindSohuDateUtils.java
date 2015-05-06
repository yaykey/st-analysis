package com.st.analysis.utils.stock;

import com.st.framework.utils.network.HttpStackManager;

public class FindSohuDateUtils extends DetailUtils {

	
//	private static String SohuUrl = "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp&code=cn_300002&start=20141201&end=20150505";
	private static String SohuUrl = "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp";
	
	//&code=cn_300002&start=20141201&end=20150505
	public static void findSohuDate(Integer stockCode, Integer startDate, Integer endDate) {
		String url = SohuUrl + "&code=cn_" + stockCode + "&start=" + startDate + "&end=" + endDate;
		
		
	}
}
