package com.st.analysis.utils.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.st.Global;
import com.st.analysis.controller.vo.MMBean;
import com.st.analysis.utils.stock.DetailUtils;
import com.st.framework.business.impl.GDetailManager;
//import com.st.framework.business.impl.rpt.RptTrendManager;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.GStockDayKey;
//import com.st.framework.module.stock.RptTrendKey;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.module.stock.example.GStockDayExample;

public class GDayDataUtil extends DetailUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(GDayDataUtil.class);

	// private DecimalFormat decf = new DecimalFormat(".##");

	// protected static RptTrendManager rptTrendManager = (RptTrendManager)
	// getHelper()
	// .getBean("rptTrendManager");

	public void appendPerData(Integer stock) {
		appendPerData(stock, null, null);
	}

	public void appendPerData(Integer stock, Date startDate, Date endDate) {

		Long timeBegainId = System.currentTimeMillis();
		
		GStockDayExample example = new GStockDayExample();
		example.setOrderByClause("STOCK, DATE");

		GStockDayExample.Criteria c = example.createCriteria();
		c.andStockEqualTo(stock);

		c.andHighPerIsNull();
		
		if (startDate != null) {
			c.andDateGreaterThanOrEqualTo(startDate);
		}

		if (endDate != null) {
			c.andDateLessThanOrEqualTo(endDate);
		}

		List<GStockDay> list = gStockDayManager.selectByExample(example);

		if (list == null || list.size() <= 1) {
			return;
		}

		Long updatePriceChangesTimeId = System.currentTimeMillis();
		gStockDayManager.updatePriceChanges(stock);
		System.out.println("appendPerData->updatePriceChanges->" + (System.currentTimeMillis()-updatePriceChangesTimeId));

		GStockDay st = null;
//		GStockDay pst = list.get(0);
		GStockDay pst = null;

		for (int i = 0; i < list.size(); i++) {

			st = list.get(i);
			// logger.info(st);

			if (st.getOpen().equals(st.getClose())
					&& st.getOpen().equals(st.getHigh())
					&& st.getOpen().equals(st.getLow())) { // stop

				st.setOpenPer(0d);
				st.setClosePer(0d);
				st.setHighPer(0d);
				st.setLowPer(0d);
				st.setAmplitude(0d);

				gStockDayManager.updateByPrimaryKeySelective(st);

				continue;
			}

			if (st.getOpenPer() == null || st.getClosePer() == null 
					|| st.getHighPer() == null || st.getLowPer() == null
					|| st.getAmplitude() == null) {
				try {
					pst = st.getPrevDay();
					if (pst != null) {
						st.setOpenPer((st.getOpen() - pst.getClose()) / pst.getClose()
								* 100);
						st.setClosePer((st.getClose() - pst.getClose())
								/ pst.getClose() * 100);
						st.setHighPer((st.getHigh() - pst.getClose()) / pst.getClose()
								* 100);
						st.setLowPer((st.getLow() - pst.getClose()) / pst.getClose()
								* 100);
						st.setAmplitude((st.getHigh() - st.getLow()) / pst.getClose()
								* 100);

						gStockDayManager.updateByPrimaryKeySelective(st);
					}
					
				} catch (Exception ex) {

					logger.warn(st);
					logger.warn(pst);
					throw new RuntimeException(ex);
				}
			}
			
		}
		
		Long timeEndId = System.currentTimeMillis();
		
		System.out.println("appendPerData->" + (timeEndId-timeBegainId));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void appendTimeDate(String stockCode, String startDateId,
			String endDateId) {
		
		
		Date d1 = new Date(), d2 = null;
		List<String> dateIds = (List<String>)getDateIds(startDateId, endDateId);
//		if (logger.isInfoEnabled()) {
//			logger.info("appendTimeDate - list=" + dateIds);
//		}
		{
			GStockDayExample example = new GStockDayExample();
			example.setDistinct(true);
			
			GStockDayExample.Criteria c = example.createCriteria();
			
			if (startDateId != null) {
				try {
					c.andDateGreaterThanOrEqualTo(df_simple.parse(startDateId));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if (endDateId != null) {
				try {
					c.andDateLessThanOrEqualTo(df_simple.parse(endDateId));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			List<String> vaildDateIds = gStockDayManager.selectValidDateByExample(example);
			
			List<String> vaildDateIdsSimple = new ArrayList<String>();
			
			if (vaildDateIds != null) {
				for (String tmp : vaildDateIds) {
					vaildDateIdsSimple.add(tmp.replaceAll("-", ""));
				}
			}
			
			dateIds.removeAll(vaildDateIdsSimple);
		}
		Long selectMMTimeId = System.currentTimeMillis();
		List<MMBean> list = null;
		
//		dateIds.retainAll(gStockDayManager.selectMMValidTimeId(stockCode, startDateId, endDateId));
		
		try {
			list = gDetailManager.selectMMBaseData(stockCode, dateIds);
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		}
		System.out.println("appendTimeDate->selectMMTimeId->" + (System.currentTimeMillis() - selectMMTimeId));
		
		if (list == null) {
			return;
		}
		
		
//		if (logger.isInfoEnabled()) {
//			logger.info("appendTimeDate list=" + list);
//		}
		GStockDay gd = null;
		GStockDayKey key = null;
		//@SuppressWarnings("unused")
//		List<GStockDay> gdList = new ArrayList<GStockDay>();
		for (MMBean mm : list) {
			try {
				key = new GStockDayKey();
				key.setStock(Integer.parseInt(stockCode.substring(2)));

				try {
					key.setDate(df_simple.parse("" + mm.getDateId()));
				} catch (ParseException e) {
					logger.error("appendTimeDate(String, String, String)", e);

					e.printStackTrace();
				}

				gd = gStockDayManager.selectByPrimaryKey(key);

				if (gd == null) {
					//logger.info("key=" + key);
					List<GStockDay> tmpList = gDetailManager.selectStockDay(
							stockCode, mm.getDateId(), mm.getDateId());
//					if (logger.isInfoEnabled()) {
//						logger.info("appendTimeDate(String, String, String) - List<GStockDay> tmpList="
//								+ tmpList);
//					}

					if (tmpList != null && tmpList.size() > 0) {
						try {
							//gStockDayManager.insert(tmpList.get(0));
							gStockDayManager.insertOrUpdate(tmpList.get(0));
						} catch (DuplicateKeyException e) {
							logger.warn(
									"appendTimeDate(String, String, String) - exception ignored",
									e);

						}
					}
				} else {
					if (gd.getHighTimeId() == null || gd.getLowTimeId() == null) {
						gd.setHighTimeId(mm.getMaxTimeId());
						gd.setLowTimeId(mm.getMinTimeId());

						gStockDayManager.updateByPrimaryKeySelective(gd);
					}
					// gdList.add(gd);
				}

				mm = null;
			} catch (Exception ex) {
				logger.error("appendTimeDate(String, String, String)", ex);

				throw new RuntimeException(ex);
			}
		}

		d2 = new Date();

		// if (logger.isInfoEnabled()) {
		// logger.info("appendTimeDate(String, String, String) - List<GStockDay> gdList="
		// + gdList);
		// }

		// gStockDayManager.batchUpdateMMByPrimaryKey(gdList);

		System.out.println("appendTimeDate->" + (d2.getTime() - d1.getTime()));

	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List getDateIds(String startDateId, String endDateId) {
		List list = new ArrayList();

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(df_simple.parse(startDateId));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		String start = startDateId;
		String end = endDateId;

		while (start.compareTo(end) <= 0) {
			list.add(start);

			cal.add(Calendar.DAY_OF_MONTH, 1);

			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}

			start = df_simple.format(cal.getTime());
		}

		return list;
	}

	public static void appendRptTrend(Integer stCode, Date startDate,
			Date endDate) {

		// GStockDayExample example = new GStockDayExample();
		//
		// example.setOrderByClause("DATE");
		//
		// GStockDayExample.Criteria c = example.createCriteria();
		//
		// c.andStockEqualTo(stCode);
		// c.andDateBetween(startDate, endDate);
		//
		// List<GStockDay> list = gStockDayManager.selectByExample(example);
		//
		// for (GStockDay stDay : list) {
		// innsertRptTrend(stDay);
		// }
	}

	// private static void innsertRptTrend (GStockDay stDay) {
	//
	// RptTrendKey trend = null;
	//
	// Double openPer = stDay.getOpenPer();
	// Double closePer = stDay.getClosePer();
	//
	// if (openPer > 0 && closePer > 0) {//++
	//
	// } else if (openPer < 0 && closePer < 0) {//--
	//
	// } else if (openPer > 0 && closePer < 0) {//+-
	//
	// } else if (openPer < 0 && closePer > 0) {//-+
	//
	// } else if (openPer.equals(new Double(0)) && closePer > 0) {//=+
	//
	// } else if (openPer.equals(new Double(0)) && closePer < 0) {//=-
	//
	// } else if (openPer > 0 && closePer.equals(new Double(0))) {//+=
	//
	// } else if (openPer < 0 && closePer.equals(new Double(0))) {//-=
	//
	// }
	//
	// }

	/**
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) {
		GDayDataUtil u = new GDayDataUtil();

		String startDateId = "20100101";
		String endDateId = "20141223";

		for (int i = 300001; i < 300406; i++) {
			u.appendTimeDate("SZ" + i, startDateId, endDateId);

			u.appendPerData(i);
		}

	}

}
