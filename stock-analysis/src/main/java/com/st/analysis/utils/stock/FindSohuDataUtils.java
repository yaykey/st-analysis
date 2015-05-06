package com.st.analysis.utils.stock;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.st.analysis.utils.stock.bean.SohuBean;
import com.st.analysis.utils.stock.bean.SohuHqBean;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.network.HttpStackManager;

public class FindSohuDataUtils extends BaseDBUtils {

	// private static String SohuUrl =
	// "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp&code=cn_300002&start=20141201&end=20150505";
	private static String SohuUrl = "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp";

	private static final DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
	
	// &code=cn_300002&start=20141201&end=20150505
	@SuppressWarnings("static-access")
	public static String findSohuDate(Integer stockCode, Integer startDate,
			Integer endDate) {
		String url = SohuUrl + "&code=cn_" + stockCode + "&start=" + startDate
				+ "&end=" + endDate;

		String res = HttpStackManager.getInstance().findGetData(url);
		System.out.println(res);
		return res;
		
//		System.out.println(res);
//
//		try {
//			ObjectMapper objectMapper = null;
//			objectMapper = new ObjectMapper();
//			SohuBean sohuBean = objectMapper.readValue(res, SohuBean.class);
//
//			//System.out.println(sohuBean);
//			
//			for (String [] tmp : sohuBean.getHq()) {
//				System.out.println(tmp[0]);
//			}
//			
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static GStockDay findData (Integer stockCode, Integer dateId) {
		
		String res = findSohuDate(stockCode, dateId, dateId);
		
		res = res.replaceFirst("^callback\\(\\[", "")
				.replaceFirst("\\]\\)$", "")
				.replaceFirst("^\\{\"status\":[\\d]+,\"hq\":\\[\\[", "")
				.replaceFirst("\\]\\],\"code\".*$", "").trim();
		
		System.out.println(res);
		
		try {
			String [] tmp = res.split(",");
			
			GStockDay stockDay = new GStockDay();
			
			stockDay.setStock(stockCode);
			stockDay.setDate(DF_SIMPLE.parse("" + dateId));
			stockDay.setOpen(Double.parseDouble(tmp[1]));
			stockDay.setClose(Double.parseDouble(tmp[2]));
			stockDay.setLow(Double.parseDouble(tmp[5]));
			stockDay.setHigh(Double.parseDouble(tmp[6]));
			stockDay.setVolume(Integer.parseInt(tmp[7]));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	

	public static void main(String[] args) {
		//findSohuDate(300002, 20150501, 20150505);
		findData(300002, 20150505);
	}
}
