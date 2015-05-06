package com.st.framework.business.impl.factdate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.framework.module.stock.FactDateHolidayList;
import com.st.framework.module.stock.FactDateHolidayListKey;
import com.st.framework.module.stock.example.FactDateHolidayListExample;
import com.st.framework.persistence.mapper.stock.FactDateHolidayListMapper;

@Component("factDateHolidayListManager")
public class FactDateHolidayListManager {

	@Autowired
	protected FactDateHolidayListMapper factDateHolidayListMapper;

	public int countByExample(FactDateHolidayListExample example) {
		return this.factDateHolidayListMapper.countByExample(example);
	}

	public int deleteByExample(FactDateHolidayListExample example) {

		return this.factDateHolidayListMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(FactDateHolidayListKey key) {

		return this.factDateHolidayListMapper.deleteByPrimaryKey(key);
	}

	public int insert(FactDateHolidayList record) {

		return this.factDateHolidayListMapper.insert(record);
	}

	public int insertSelective(FactDateHolidayList record) {

		return this.factDateHolidayListMapper.insertSelective(record);
	}

	public List<FactDateHolidayList> selectByExample(
			FactDateHolidayListExample example) {

		return this.factDateHolidayListMapper.selectByExample(example);
	}

	public FactDateHolidayList selectByPrimaryKey(FactDateHolidayListKey key) {

		return this.factDateHolidayListMapper.selectByPrimaryKey(key);
	}

	public int updateByExampleSelective(FactDateHolidayList record,
			FactDateHolidayListExample example) {

		return this.factDateHolidayListMapper.updateByExampleSelective(record,
				example);
	}

	public int updateByExample(FactDateHolidayList record,
			FactDateHolidayListExample example) {

		return this.factDateHolidayListMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactDateHolidayList record) {

		return this.factDateHolidayListMapper
				.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactDateHolidayList record) {

		return this.factDateHolidayListMapper.updateByPrimaryKey(record);
	}

	public List<String> selectDaysOff(String startDate, String endDate) {
		return this.factDateHolidayListMapper.selectDaysOff(startDate, endDate);
	}
	
	public List<String> selectDaysOff(Date startDate, Date endDate) {
		return this.factDateHolidayListMapper.selectDaysOff(Global.DF_DAY.format(startDate), 
				Global.DF_DAY.format(endDate));
	}

}