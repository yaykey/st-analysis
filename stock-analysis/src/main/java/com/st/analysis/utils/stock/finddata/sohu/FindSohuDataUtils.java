package com.st.analysis.utils.stock.finddata.sohu;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.st.Global;
import com.st.analysis.utils.DateUtils;
import com.st.analysis.utils.stock.bean.sohu.SohuBean;
import com.st.analysis.utils.stock.bean.sohu.SohuHqBean;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.utils.DateUtil;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.network.HttpStackManager;

public class FindSohuDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(FindSohuDataUtils.class);

	// private static String SohuUrl =
	// "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp&code=cn_300002&start=20141201&end=20150505";
	private static String SohuUrl = "http://q.stock.sohu.com/hisHq?stat=1&order=D&period=d&rt=jsonp";

	// private static final DateFormat DF_SIMPLE = new
	// SimpleDateFormat("yyyyMMdd");
	// private static final DateFormat DF_DAY = new
	// SimpleDateFormat("yyyy-MM-dd");

	// &code=cn_300002&start=20141201&end=20150505
	@SuppressWarnings("static-access")
	public String findSohuDate(String stockCode, Integer startDate,
			Integer endDate) {
		String url = SohuUrl + "&code=cn_" + stockCode + "&start=" + startDate
				+ "&end=" + endDate;
		System.out.println("url=" + url);
		String res = HttpStackManager.getInstance().findGetData(url);
		// System.out.println(res);
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

	public GStockDay findData(String stockCode, Integer dateId) {

		String res = findSohuDate(stockCode, dateId, dateId);

		res = res.replaceFirst("^callback\\(\\[", "")
				.replaceFirst("\\]\\)$", "")
				.replaceFirst("^\\{\"status\":[\\d]+,\"hq\":\\[\\[", "")
				.replaceFirst("\\]\\],\"code\".*$", "").trim();

		res = res.replaceAll("\"", "").replaceAll("%", "");

		System.out.println(res);

		try {
			res = res.replaceAll("\"", "").replaceAll("%", "");
			// String[] tmp = data.split(",");
			return createData(stockCode, res.split(","));

		} catch (Exception e) {
			logger.error("findData()", e);

			e.printStackTrace();
		}

		return null;
	}

	private void parseData(String stockCode, String data) {
		data = data.replaceFirst("^callback\\(\\[", "")
				.replaceFirst("\\]\\)$", "")
				// .replaceFirst("^\\{\"status\":[\\d]+,\"hq\":\\[\\[", "")
				.replaceFirst("^\\{\"status\":[\\d]+,", "")
				// .replaceFirst("\\]\\],\"code\".*$", "")
				.replaceFirst(",\"code\".*$", "").trim();

		// data = data.replaceAll("\"", "").replaceAll("%", "");
		data = data.replaceAll("%", "");
		data = "{" + data + "}";

		// System.out.println(data);

		try {
			ObjectMapper objectMapper = null;
			objectMapper = new ObjectMapper();
			SohuHqBean sohuBean = objectMapper
					.readValue(data, SohuHqBean.class);

			// GStockDay stockDay = null;
			// List<GStockDay> resultData = new ArrayList<GStockDay>();
			for (String[] tmp : sohuBean.getHq()) {
				// System.out.println(tmp[0] + " " + tmp[1]);
				final GStockDay stockDay = createData(stockCode, tmp);
				// resultData.add(stockDay);
				// gStockDayManager.insertOrUpdateSelective(stockDay);
				Global.threadPoolExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							gStockDayManager
									.updateByPrimaryKeySelective(stockDay);
						} catch (Exception ex) {
							gStockDayManager.insertOrUpdateSelective(stockDay);
						}
					}
				});
			}

			// gStockDayManager.insertBatch(resultData);
			// resultData.clear();
			// resultData = null;
		} catch (JsonParseException e) {
			// logger.warn(e.getMessage());
		} catch (JsonMappingException e) {
			logger.warn(e.getMessage());
		} catch (IOException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

	}
	
	public void checkAllData(String stockCode, Date begin, Date end) {
		
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void checkAllData(String stockCode) {

		List<Date> failDateList = gStockDayManager
				.selectFailTurnvolumeDate(stockCode);

		if (failDateList == null || failDateList.size() == 0) {
			{
				GStockDayExample example = new GStockDayExample();
				GStockDayExample.Criteria c = example.createCriteria();

				c.andStockEqualTo(stockCode);

				int cnt = gStockDayManager.countByExample(example);
				if (cnt != 0) {
					return;
				}
			}

			Calendar cal = Calendar.getInstance();

			// Date begin = new Date("2009/10/30");
			Date begin = null;

			DStock dstock = dStockManager.selectByPrimaryKey("" + stockCode);

			if (dstock != null) {
				if (dstock.getListingDate() != null) {
					begin = dstock.getListingDate();
				}
			}

			if (begin == null) {
				
				if (stockCode.compareTo("300000") > 0 &&  
						stockCode.compareTo("399999") < 0) {
					
					if (stockCode.compareTo("300419") <= 0) {
						begin = new Date("2009/10/30");
					} else {
						begin = new Date("2015/01/01");
					}
				} else {
					begin = new Date("2015/01/01");
				}
			}

			findSohuDataByPage(stockCode, begin, new Date());
		} else {
			Date begin = failDateList.get(0);

			for (int i = 1; i < failDateList.size(); i++) {

				if (DateUtil.getDaysDec(failDateList.get(i - 1),
						failDateList.get(i)) < 5) {
					continue;
				}

				// String res = "";
				// DateFormat df = new SimpleDateFormat("yyyyMMdd");
				//
				// res = findSohuDate(stockCode,
				// Integer.parseInt(df.format(begin)),
				// Integer.parseInt(df.format(failDateList.get(i))));
				// parseData(stockCode, res);

				findSohuDataByPage(stockCode, begin, failDateList.get(i));

				begin = failDateList.get(i);
			}

			if (begin.compareTo(begin) == 0) {
				// String res = "";
				// DateFormat df = new SimpleDateFormat("yyyyMMdd");
				// //Integer timeid = Integer.parseInt(df.format(d));
				// res = findSohuDate(stockCode,
				// Integer.parseInt(df.format(begin)),
				// Integer.parseInt(df.format(failDateList.get(failDateList.size()-1))));
				// parseData(stockCode, res);

				findSohuDataByPage(stockCode, begin,
						failDateList.get(failDateList.size() - 1));
			}
		}

	}

	public void findSohuDataByPage(final String stockCode, Date begin, Date end) {
		Calendar cal = Calendar.getInstance();
		// Date end = new Date();
		cal.setTime(begin);

		// this.gDetailNoDataManager.selectErrorDateIdsUnion(stockCode,
		// yearDateIds);

		while (begin.compareTo(end) <= 0) {
			cal.add(Calendar.DAY_OF_YEAR, 100);
			final Date bd = begin;
			final Date ed = cal.getTime();

			Global.threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
					String res = findSohuDate(stockCode,
							Integer.parseInt(DF_SIMPLE.format(bd)),
							Integer.parseInt(DF_SIMPLE.format(ed)));
					parseData(stockCode, res);
				}
			});

			begin = cal.getTime();
		}
	}

	public void appendData(String stockCode, Date begin, Date end) {
		Calendar cal = Calendar.getInstance();

		// Date begin = new Date("2014/01/01");
		// Date end = new Date();
		cal.setTime(begin);
		String res = "";
		while (begin.compareTo(end) <= 0) {
			cal.add(Calendar.DAY_OF_YEAR, 100);
			DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
			res = findSohuDate(stockCode,
					Integer.parseInt(DF_SIMPLE.format(begin)),
					Integer.parseInt(DF_SIMPLE.format(cal.getTime())));
			parseData(stockCode, res);

			begin = cal.getTime();

		}
	}

	private GStockDay createData(String stockCode, String[] data)
			throws Exception {
		GStockDay stockDay = null;
		try {
			// data = data.replaceAll("\"", "").replaceAll("%", "");
			// String[] tmp = data.split(",");

			stockDay = new GStockDay();
			// "2015-05-04","34.10","37.46","3.41","10.01%","33.80","37.46","329972","117074.52","4.52%"
			stockDay.setStock(stockCode);

			DateFormat DF_DAY = new SimpleDateFormat("yyyy-MM-dd");
			stockDay.setDate(DF_DAY.parse(data[0]));

			stockDay.setOpen(Double.parseDouble(data[1]));
			stockDay.setClose(Double.parseDouble(data[2]));

			stockDay.setPriceChanges(Double.parseDouble(data[3]));
			stockDay.setClosePer(Double.parseDouble(data[4]));

			stockDay.setLow(Double.parseDouble(data[5]));
			stockDay.setHigh(Double.parseDouble(data[6]));

			stockDay.setVolume(Integer.parseInt(data[7]));
			stockDay.setTurnVolume(Double.parseDouble(data[8]));

			try {
				stockDay.setTurnoverRate(Double.parseDouble(data[9]));
			} catch (Exception e) {
				logger.warn(stockCode + "" + e.getMessage());
			}
		} catch (Exception ex) {
			logger.error("createData()", ex);

			throw ex;
		}
		return stockDay;
	}

	public void appendTaskData(String stockCode) {
		Date maxDate = gStockDayManager.findMaxDateByCode(stockCode);
		Date begin = null;
		if (maxDate == null) {
			begin = new Date("2015/01/01");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(maxDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);

			begin = cal.getTime();
		}

		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, -1);

		if (begin.compareTo(new Date()) <= 0) {
			appendData(stockCode, begin, new Date());
		}
	}

	public static void main(String[] args) {
		// findSohuDate(300002, 20150501, 20150505);
		// GStockDay stockDay = findData(300002, 20150505);
		//
		// gStockDayManager.insertOrUpdateSelective(stockDay);

		// checkFailAllData(300001);

		String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
		//
		for (int i = 300450; i <= Integer.parseInt(maxStockCode); i++) {
			// for (int i = 300001; i <= 300001; i++) {

			new FindSohuDataUtils().checkAllData("" + i);

			System.out.println(i);
		}

		// for (int i = 300051; i <= 300100; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300101; i <= 300150; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		// for (int i = 300165; i <= 300200; i++) {
		//
		// checkAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300214; i <= 300250; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300265; i <= 300300; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300316; i <= 300350; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300386; i <= 300400; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }
		//
		// for (int i = 300401; i <= 300449; i++) {
		//
		// checkFailAllData(i);
		//
		// System.out.println(i);
		// }

		// checkFailAllData(300419);
		// String maxStockCode = dStockManager.selectMaxStockCodeByCYB();
		// for (int i=300001; i<=Integer.parseInt(maxStockCode); i++) {
		// checkFailAllData(i);
		// }
	}
}
