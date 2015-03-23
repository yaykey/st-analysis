package com.st.framework.business.impl.factdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;


import com.st.framework.module.stock.FactDateAlmanac;

import com.st.framework.module.stock.example.FactDateAlmanacExample;

@Component("factDateAlmanacManager")
public class FactDateAlmanacManager extends BaseFactDateManager {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(FactDateAlmanacManager.class);

	@Autowired
	protected FactDateHolidayManager factDateHolidayManager;

	public int countByExample(FactDateAlmanacExample example) {

		return this.factDateAlmanacMapper.countByExample(example);
	}

	public int deleteByExample(FactDateAlmanacExample example) {

		return this.factDateAlmanacMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(String date) {

		return this.factDateAlmanacMapper.deleteByPrimaryKey(date);
	}

	public int insert(FactDateAlmanac record) {

		return this.factDateAlmanacMapper.insert(record);
	}

	public int insertSelective(FactDateAlmanac record) {

		return this.factDateAlmanacMapper.insertSelective(record);
	}

	public List<FactDateAlmanac> selectByExample(FactDateAlmanacExample example) {

		return this.factDateAlmanacMapper.selectByExample(example);
	}

	public FactDateAlmanac selectByPrimaryKey(String date) {

		return this.factDateAlmanacMapper.selectByPrimaryKey(date);
	}

	public int updateByExampleSelective(FactDateAlmanac record,
			FactDateAlmanacExample example) {

		return this.factDateAlmanacMapper.updateByExampleSelective(record,
				example);
	}

	public int updateByExample(FactDateAlmanac record,
			FactDateAlmanacExample example) {

		return this.factDateAlmanacMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactDateAlmanac record) {

		return this.factDateAlmanacMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactDateAlmanac record) {

		return this.factDateAlmanacMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	 * 插入百度Date
	 * 
	 * @param dataJsonObject
	 */
	public void insertDataJsonObject(JSONObject dataJsonObject) {
		JSONArray almanacArray = dataJsonObject.getJSONArray("almanac");

		try {
			this.insertAlmanacArray(almanacArray);
		} catch (Exception e) {
			throw new RuntimeException("Almanac错误", e);
		}

		try {
			if (dataJsonObject.containsKey("holiday")) {
				try {
					JSONArray holidayArray = dataJsonObject
							.getJSONArray("holiday");
					factDateHolidayManager.insertHolidayArray(holidayArray);
				} catch (JSONException e) {
					logger.warn("holiday" + e.getMessage());

					factDateHolidayManager
							.insertHolidayJSONObject(dataJsonObject
									.getJSONObject("holiday"));

				}
			}
		} catch (DuplicateKeyException e) {
			logger.warn("日期重复 " + e.getMessage());
		
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("holiday异常", e);

			throw new RuntimeException("holiday异常", e);
		}
	}

	/**
	 * 
	 * 保存AlmanacArray
	 * 
	 * @param almanacArray
	 *            net.sf.json.JSONArray
	 */
	public void insertAlmanacArray(JSONArray almanacArray) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertAlmanacArray(JSONArray) - start");
		}

		Calendar calWeek = Calendar.getInstance();

		for (int i = 0; i < almanacArray.size(); i++) {
			JSONObject object = (JSONObject) almanacArray.get(i);

			FactDateAlmanac almanac = (FactDateAlmanac) JSONObject.toBean(
					object, FactDateAlmanac.class);

			try {
				calWeek.setTime(dfs.parse(almanac.getDate()));
				almanac.setDate(df.format(calWeek.getTime()));
				almanac.setWeek(calWeek.get(Calendar.DAY_OF_WEEK));
			} catch (ParseException e) {
				logger.error("insertAlmanacArray(JSONArray)", e);

				throw new RuntimeException("Almanac日期解析异常", e);
			}

			// if (logger.isInfoEnabled()) {
			// logger.info(almanac);
			// }

			try {
				factDateAlmanacMapper.insert(almanac);
			} catch (DuplicateKeyException e) {
				logger.warn("日期重复 " + e.getMessage());
				continue;
			} catch (Exception ex) {
				logger.error("insertAlmanacArray(JSONArray)", ex);

				throw new RuntimeException("Almanac异常", ex);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertAlmanacArray(JSONArray) - end");
		}
	}
}
