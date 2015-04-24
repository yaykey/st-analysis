package com.st.framework.business.impl;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanRequest;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanResponse;
import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.GStockDayKey;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GStockDayMapper;
import com.st.framework.utils.ConfigUtil;
import com.st.framework.utils.DateUtil;

@Component("gStockDayManager")
public class GStockDayManager extends
		BaseManager<GStockDayKey, GStockDay, GStockDayExample> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(GStockDayManager.class);

	@Autowired
	protected GStockDayMapper gStockDayMapper;

	@Override
	public BaseMapper<GStockDayKey, GStockDay, GStockDayExample> getMapper() {

		return this.gStockDayMapper;
	}

	protected final static DateFormat dfDateId = new SimpleDateFormat(
			"yyyyMMdd");

	private static Object lock = new Object();

	public int insert(GStockDay gStockDay) {
		synchronized (lock) {
			return this.getMapper().insert(gStockDay);
		}
	}

	public void insertOrUpdate(GStockDay gStockDay) {
		if (gStockDay == null) {
			return;
		}

		try {
			this.insert(gStockDay);
		} catch (DuplicateKeyException ex) {
			this.updateByPrimaryKey(gStockDay);
		}
	}

	public Integer selectMaxDateId(Integer stockCode) {
		GStockDayExample example = new GStockDayExample();

		example.setDistinct(true);
		example.setOrderByClause("DATE desc");

		example.setStart(0);
		example.setPageSize(1);

		GStockDayExample.Criteria c = example.createCriteria();
		c.andStockEqualTo(stockCode);

		List<GStockDay> list = this.selectByExample(example);

		if (list != null && list.size() > 0) {
			return Integer.parseInt(dfDateId.format(list.get(0).getDate()));
		}

		return null;
	}

	public void updatePriceChanges(Integer stock) {
		this.gStockDayMapper.updatePriceChanges(stock);
	}

	public void batchUpdateMMByPrimaryKey(List<GStockDay> list) {
		this.gStockDayMapper.batchUpdateMMByPrimaryKey(list);
	}

	@SuppressWarnings("unchecked")
	public List<List<GStockDay>> selectRangePer(Integer stockCode, int sec,
			Date startDateId, Date endDateId, String type) {

		List<String> timeIds = DateUtil.getRangeTimes(sec);

		return this.selectRangePer(stockCode, timeIds, startDateId, endDateId,
				type);
	}

	public List<List<GStockDay>> selectRangePer(Integer stockCode,
			List<String> timeIds, Date startDateId, Date endDateId, String type) {

		// List<String> timeIds = DateUtil.getRangeTimes(60*5);

		List<List<GStockDay>> list = new ArrayList<List<GStockDay>>();

		List<GStockDay> days = null;

		for (int i = 0; i < timeIds.size() - 1; i++) {
			GStockDayExample example = new GStockDayExample();

			example.setOrderByClause("DATE");

			GStockDayExample.Criteria c = example.createCriteria();

			c.andStockEqualTo(stockCode);

			c.andDateGreaterThanOrEqualTo(startDateId);
			c.andDateLessThanOrEqualTo(endDateId);

			if ("hightimeid".equalsIgnoreCase(type)) {
				c.andHighTimeIdGreaterThanOrEqualTo(timeIds.get(i));
				c.andHighTimeIdLessThan(timeIds.get(i + 1));
			} else if ("lowtimeid".equalsIgnoreCase(type)) {
				c.andLowTimeIdGreaterThanOrEqualTo(timeIds.get(i));
				c.andLowTimeIdLessThan(timeIds.get(i + 1));
			}

			days = this.selectByExample(example);

			list.add(days);
		}

		return list;
	}

	public List<GStockDay> selectList(Date dateid, Map<String, Integer> order) {
		GStockDayExample example = new GStockDayExample();

		example.setDistinct(true);

		example.setOrderByClause("STOCK");

		GStockDayExample.Criteria c = example.createCriteria();

		c.andDateEqualTo(dateid);

		appendOrderBy(example, order);

		return this.selectByExample(example);
	}

	public List<GStockDay> selectListByExample(GStockDayExample example,
			Map<String, Integer> order) {

		appendOrderBy(example, order);

		return this.selectByExample(example);
	}

	private void appendOrderBy(GStockDayExample example,
			Map<String, Integer> order) {
		if (order != null) {
			String orderByStr = "";

			if (order.get("date") != null && order.get("date") != 0) {
				if (order.get("date") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "DATE asc";
				} else if (order.get("date") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "DATE desc";
				}
			}

			if (order.get("stock") != null && order.get("stock") != 0) {
				if (order.get("stock") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "STOCK asc";
				} else if (order.get("stock") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "STOCK desc";
				}
			}

			if (order.get("open") != null && order.get("open") != 0) {
				if (order.get("open") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "open asc";
				} else if (order.get("open") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "open desc";
				}
			}

			if (order.get("high") != null && order.get("high") != 0) {
				if (order.get("high") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "high asc";
				} else if (order.get("high") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "high desc";
				}
			}

			if (order.get("low") != null && order.get("low") != 0) {
				if (order.get("low") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "low asc";
				} else if (order.get("low") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "low desc";
				}
			}

			if (order.get("close") != null && order.get("close") != 0) {
				if (order.get("close") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "close asc";
				} else if (order.get("close") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "close desc";
				}
			}

			if (order.get("adjClose") != null && order.get("adjClose") != 0) {
				if (order.get("adjClose") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "adj_close asc";
				} else if (order.get("adjClose") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "adj_close desc";
				}
			}

			if (order.get("priceChanges") != null
					&& order.get("priceChanges") != 0) {
				if (order.get("priceChanges") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "price_changes asc";
				} else if (order.get("priceChanges") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "price_changes desc";
				}
			}

			if (order.get("highTimeId") != null && order.get("highTimeId") != 0) {
				if (order.get("highTimeId") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "HIGH_TIME_ID asc";
				} else if (order.get("highTimeId") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "HIGH_TIME_ID desc";
				}
			}

			if (order.get("lowTimeId") != null && order.get("lowTimeId") != 0) {
				if (order.get("lowTimeId") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "LOW_TIME_ID asc";
				} else if (order.get("lowTimeId") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "LOW_TIME_ID desc";
				}
			}

			if (order.get("amplitude") != null && order.get("amplitude") != 0) {
				if (order.get("amplitude") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "amplitude asc";
				} else if (order.get("amplitude") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "amplitude desc";
				}
			}

			if (order.get("highPer") != null && order.get("highPer") != 0) {
				if (order.get("highPer") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "HIGH_PER asc";
				} else if (order.get("highPer") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "HIGH_PER desc";
				}
			}

			if (order.get("lowPer") != null && order.get("lowPer") != 0) {
				if (order.get("lowPer") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "LOW_PER asc";
				} else if (order.get("lowPer") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "LOW_PER desc";
				}
			}

			if (order.get("closePer") != null && order.get("closePer") != 0) {
				if (order.get("closePer") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "CLOSE_PER asc";
				} else if (order.get("closePer") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "CLOSE_PER desc";
				}
			}

			if (order.get("openPer") != null && order.get("openPer") != 0) {
				if (order.get("openPer") == 1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "OPEN_PER asc";
				} else if (order.get("openPer") == -1) {
					if (!"".equals(orderByStr)) {
						orderByStr += ",";
					}
					orderByStr += "OPEN_PER desc";
				}
			}

			if (!"".equals(orderByStr)) {
				example.setOrderByClause(orderByStr);
			}
		}
	}

	public List<GStockDay> selectList(Date dateid, String orderby) {
		GStockDayExample example = new GStockDayExample();

		example.setDistinct(true);

		example.setOrderByClause("STOCK");

		GStockDayExample.Criteria c = example.createCriteria();

		c.andDateEqualTo(dateid);

		if (orderby != null && !"".equals(orderby)) {
			example.setOrderByClause(orderby);
		}

		return this.selectByExample(example);
	}

	public void selectPer(Date startDate, Date endDate, Double startPer,
			Double endPer) {
		GStockDayExample example = new GStockDayExample();

		example.setDistinct(true);
		example.setOrderByClause("DATE desc");

		GStockDayExample.Criteria c = example.createCriteria();

		if (startDate != null) {
			c.andDateGreaterThanOrEqualTo(startDate);
		}

		if (endDate != null) {
			c.andDateLessThanOrEqualTo(endDate);
		}

		if (startPer != null) {
			c.andClosePerGreaterThanOrEqualTo(startPer);
		}

		if (endPer != null) {
			c.andClosePerLessThanOrEqualTo(endPer);
		}

		List<GStockDay> list = this.selectByExample(example);
	}

	public List selectMMValidTimeId(String stockCode,
			String startDateId, String endDateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectMMValidTimeId(String, String, String) - start");
		}

		GStockDayExample example = new GStockDayExample();

		GStockDayExample.Criteria c = example.createCriteria();

		if (startDateId != null && !"".equals(startDateId.trim())) {
			try {
				c.andDateGreaterThanOrEqualTo(dfDateId.parse(startDateId));
			} catch (ParseException e) {
				logger.warn(
						"selectMMValidTimeId(String, String, String) - exception ignored",
						e);

			}
		}

		if (endDateId != null && !"".equals(endDateId.trim())) {
			try {
				c.andDateLessThanOrEqualTo(dfDateId.parse(endDateId));
			} catch (ParseException e) {
				logger.warn(
						"selectMMValidTimeId(String, String, String) - exception ignored",
						e);

			}
		}
		
		c.andHighTimeIdIsNotNull();
		c.andLowTimeIdIsNotNull();
		
		c.andHighPerIsNotNull();
		c.andLowPerIsNotNull();
		c.andClosePerIsNotNull();
		c.andOpenPerIsNotNull();
		
		List<GStockDay> list = this.selectByExample(example);
		
		if (list != null && list.size() > 0) {
			List timeids = new ArrayList();
			for (GStockDay gStockDay : list) {
				timeids.add(dfDateId.format(gStockDay.getDate()));
			}
			
			return timeids;
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("selectMMValidTimeId(String, String, String) - end");
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		GStockDayManager gStockDayManager = (GStockDayManager) ConfigUtil
				.getHelper().getBean("gStockDayManager");

		List<String> timeIds = DateUtil.getRangeTimes(60 * 1);
		{
			List<List<GStockDay>> list = gStockDayManager.selectRangePer(
					300002, timeIds, new Date("2014/01/01"), new Date(
							"2014/12/31"), "hightimeid");

			if (logger.isInfoEnabled()) {
				logger.info("main(String[]) - List<String> timeIds=" + timeIds);
			}

			if (logger.isInfoEnabled()) {
				logger.info("main(String[]) - List list=" + list);
			}
			List<GStockDay> days = null;
			for (int i = 0; i < list.size(); i++) {
				days = list.get(i);
				System.out.println(timeIds.get(i) + " " + days.size());
			}
		}
		// ---------------------//
		{
			List<List<GStockDay>> list = gStockDayManager.selectRangePer(
					300002, timeIds, new Date("2014/01/01"), new Date(
							"2014/12/31"), "lowtimeid");

			if (logger.isInfoEnabled()) {
				logger.info("main(String[]) - List<String> timeIds=" + timeIds);
			}

			if (logger.isInfoEnabled()) {
				logger.info("main(String[]) - List list=" + list);
			}
			List<GStockDay> days = null;
			for (int i = 0; i < list.size(); i++) {
				days = list.get(i);
				System.out.println(timeIds.get(i) + " " + days.size());
			}
			// TREND
		}
	}

	
	public List<TimeRangeCountBeanResponse> selectByTimeRange(String timeClause,
			List<TimeRangeCountBeanRequest> list,
			GStockDayExample example) {
		return this.gStockDayMapper.selectByTimeRange(timeClause, list, example);
	}
	
}
