package com.st.analysis.utils.stock;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	private static final DateFormat DF_DAY = new SimpleDateFormat("yyyy-MM-dd");

	// &code=cn_300002&start=20141201&end=20150505
	@SuppressWarnings("static-access")
	public static String findSohuDate(Integer stockCode, Integer startDate,
			Integer endDate) {
		String url = SohuUrl + "&code=cn_" + stockCode + "&start=" + startDate
				+ "&end=" + endDate;

		String res = HttpStackManager.getInstance().findGetData(url);
//		System.out.println(res);
		return res;

		// System.out.println(res);
		//
		// try {
		// ObjectMapper objectMapper = null;
		// objectMapper = new ObjectMapper();
		// SohuBean sohuBean = objectMapper.readValue(res, SohuBean.class);
		//
		// //System.out.println(sohuBean);
		//
		// for (String [] tmp : sohuBean.getHq()) {
		// System.out.println(tmp[0]);
		// }
		//
		// } catch (JsonParseException e) {
		// e.printStackTrace();
		// } catch (JsonMappingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static GStockDay findData(Integer stockCode, Integer dateId) {

		String res = findSohuDate(stockCode, dateId, dateId);

		res = res.replaceFirst("^callback\\(\\[", "")
				.replaceFirst("\\]\\)$", "")
				.replaceFirst("^\\{\"status\":[\\d]+,\"hq\":\\[\\[", "")
				.replaceFirst("\\]\\],\"code\".*$", "").trim();

		res = res.replaceAll("\"", "").replaceAll("%", "");

		System.out.println(res);

		try {
			res = res.replaceAll("\"", "").replaceAll("%", "");
//			String[] tmp = data.split(",");
			return createData(stockCode, res.split(","));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static void parseData (Integer stockCode, String data) {
		data = data.replaceFirst("^callback\\(\\[", "")
				.replaceFirst("\\]\\)$", "")
//				.replaceFirst("^\\{\"status\":[\\d]+,\"hq\":\\[\\[", "")
				.replaceFirst("^\\{\"status\":[\\d]+,", "")
//				.replaceFirst("\\]\\],\"code\".*$", "")
				.replaceFirst(",\"code\".*$", "")
				.trim();

//		data = data.replaceAll("\"", "").replaceAll("%", "");
		data = data.replaceAll("%", "");
		data = "{" + data + "}";
 
		System.out.println(data);

		try {
			ObjectMapper objectMapper = null;
			objectMapper = new ObjectMapper();
			SohuHqBean sohuBean = objectMapper.readValue(data, SohuHqBean.class);

			GStockDay stockDay = null;
			for (String[] tmp : sohuBean.getHq()) {
//				System.out.println(tmp[0] + " " + tmp[1]);
				stockDay = createData(stockCode, tmp);
				
				gStockDayManager.insertOrUpdateSelective(stockDay);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void checkFailData (Integer stockCode) {
		Calendar cal = Calendar.getInstance();
		
		Date begin = new Date("2014/01/01");		
		Date end = new Date();
		cal.setTime(begin);
		String res = "";
		while (begin.compareTo(end) <= 0) {
			cal.add(Calendar.DAY_OF_YEAR, 100);
			res = findSohuDate(
					stockCode,
					Integer.parseInt(DF_SIMPLE.format(begin)),
					Integer.parseInt(DF_SIMPLE.format(cal.getTime()))
				);
			parseData(stockCode, res);
			
			begin = cal.getTime();
			
		}
		
		
	}

	private static GStockDay createData(Integer stockCode, String [] data) {
		GStockDay stockDay = null;
		try {
//			data = data.replaceAll("\"", "").replaceAll("%", "");
//			String[] tmp = data.split(",");

			stockDay = new GStockDay();
			// "2015-05-04","34.10","37.46","3.41","10.01%","33.80","37.46","329972","117074.52","4.52%"
			stockDay.setStock(stockCode);
			stockDay.setDate(DF_DAY.parse(data[0]));

			stockDay.setOpen(Double.parseDouble(data[1]));
			stockDay.setClose(Double.parseDouble(data[2]));

			stockDay.setPriceChanges(Double.parseDouble(data[3]));
			stockDay.setClosePer(Double.parseDouble(data[4]));

			stockDay.setLow(Double.parseDouble(data[5]));
			stockDay.setHigh(Double.parseDouble(data[6]));

			stockDay.setVolume(Integer.parseInt(data[7]));
			stockDay.setTurnVolume(Double.parseDouble(data[8]));

			stockDay.setTurnoverRate(Double.parseDouble(data[9]));
		} catch (Exception ex) {
			return null;
		}
		return stockDay;
	}

	public static void main(String[] args) {
		// findSohuDate(300002, 20150501, 20150505);
//		GStockDay stockDay = findData(300002, 20150505);
//
//		gStockDayManager.insertOrUpdateSelective(stockDay);
		
		
		
		checkFailData(300001);
		
		for (int i=300003; i<300419; i++) {
			checkFailData(i);
		}
	}
}
