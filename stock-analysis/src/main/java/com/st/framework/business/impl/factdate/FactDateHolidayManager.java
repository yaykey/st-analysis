package com.st.framework.business.impl.factdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.st.analysis.controller.vo.baidudate.HolidayBean;
import com.st.framework.module.stock.FactDateHoliday;
import com.st.framework.module.stock.FactDateHolidayList;
import com.st.framework.module.stock.example.FactDateHolidayExample;

@Component("factDateHolidayManager")
public class FactDateHolidayManager extends BaseFactDateManager {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(FactDateHolidayManager.class);

	public int countByExample(FactDateHolidayExample example) {

		return this.factDateHolidayMapper.countByExample(example);
	}

	public int deleteByExample(FactDateHolidayExample example) {

		return this.factDateHolidayMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(String festival) {

		return this.factDateHolidayMapper.deleteByPrimaryKey(festival);
	}

	public int insert(FactDateHoliday record) {

		return this.factDateHolidayMapper.insert(record);
	}

	public int insertSelective(FactDateHoliday record) {

		return this.factDateHolidayMapper.insertSelective(record);
	}

	public List<FactDateHoliday> selectByExample(FactDateHolidayExample example) {

		return this.factDateHolidayMapper.selectByExample(example);
	}

	public FactDateHoliday selectByPrimaryKey(String festival) {

		return this.factDateHolidayMapper.selectByPrimaryKey(festival);
	}

	public int updateByExampleSelective(FactDateHoliday record,
			FactDateHolidayExample example) {

		return this.factDateHolidayMapper.updateByExampleSelective(record,
				example);
	}

	public int updateByExample(FactDateHoliday record,
			FactDateHolidayExample example) {

		return this.factDateHolidayMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactDateHoliday record) {

		return this.factDateHolidayMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactDateHoliday record) {

		return this.factDateHolidayMapper.updateByPrimaryKey(record);
	}

	public void insertHolidayJSONObject(JSONObject holidayJSONObject) {
		HolidayBean holiday = (HolidayBean) JSONObject.toBean(
				holidayJSONObject, HolidayBean.class);

		try {
			holiday.setFestival(df.format(dfs.parse(holiday.getFestival())));
		} catch (ParseException e) {
			logger.error("insertHolidayArray(JSONArray)", e);

			throw new RuntimeException("Holiday日期解析异常", e);
		}

		try {
			factDateHolidayMapper.insert(holiday);
		} catch (DuplicateKeyException e) {
			logger.warn("日期重复 " + e.getMessage());
		}

		List<MorphDynaBean> list = holiday.getList();

		if (list != null) {
			for (MorphDynaBean morphDynaBean : list) {
				FactDateHolidayList factDateHolidayList = new FactDateHolidayList();

				factDateHolidayList.setFestival(holiday.getFestival());
				factDateHolidayList.setStatus(Integer
						.parseInt((String) morphDynaBean.get("status")));

				try {
					factDateHolidayList.setDate(df.format(dfs
							.parse((String) morphDynaBean.get("date"))));

				} catch (ParseException e) {
					logger.error("insertHolidayArray(JSONArray)", e);

					throw new RuntimeException("HolidayList日期解析异常", e);
				}

				try {
					factDateHolidayListMapper.insert(factDateHolidayList);
				} catch (DuplicateKeyException e) {
					logger.warn("日期重复 " + e.getMessage());
				}
			}
		}
	}

	/**
	 * 保存HolidayArray
	 * 
	 * @param holidayArray
	 *            net.sf.json.JSONArray
	 */
	public void insertHolidayArray(JSONArray holidayArray) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertHolidayArray(JSONArray) - start");
		}

		if (holidayArray != null) {
			for (int i = 0; i < holidayArray.size(); i++) {
				JSONObject object = (JSONObject) holidayArray.get(i);

				try {
					insertHolidayJSONObject(object);
				} catch (DuplicateKeyException e) {
					logger.warn("日期重复 " + e.getMessage());
					continue;
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertHolidayArray(JSONArray) - end");
		}
	}

	/**
	 * 获得节假日日期列表eg:2014-10-01
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<String> selectHolidayList(String startDate, String endDate) {

		FactDateHolidayExample example = new FactDateHolidayExample();

		example.setDistinct(true);

		FactDateHolidayExample.Criteria c = example.createCriteria();

		c.andFestivalBetween(startDate, endDate);

		List<FactDateHoliday> list = this.selectByExample(example);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		List<String> hoidayList = new ArrayList<String>();
		for (FactDateHoliday holiday : list) {
			try {
				cal.setTime(df.parse(holiday.getFestival()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < holiday.getListnumbaidu(); i++) {
				hoidayList.add(df.format(cal.getTime()));

				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		return hoidayList;
	}

	/**
	 * 获得节假日日期列表eg:20141001
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Integer> selectHolidayList(Integer startDate, Integer endDate) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = null;
		List<Integer> res = new ArrayList<Integer>();
		try {
			list = this.selectHolidayList(df2.format(df.parse("" + startDate)),
					df2.format(df.parse("" + endDate)));

			for (String d : list) {
				res.add(Integer.parseInt(df.format(df2.parse(d))));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return res;
	}
}
