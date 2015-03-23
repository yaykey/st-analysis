package com.st.framework.business.impl.factdate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.st.framework.persistence.mapper.stock.FactDateAlmanacMapper;
import com.st.framework.persistence.mapper.stock.FactDateHolidayListMapper;
import com.st.framework.persistence.mapper.stock.FactDateHolidayMapper;

public class BaseFactDateManager {

	
	@Autowired
	protected FactDateHolidayMapper factDateHolidayMapper;
	
	@Autowired
	protected FactDateHolidayListMapper factDateHolidayListMapper;
	
	@Autowired
	protected FactDateAlmanacMapper factDateAlmanacMapper;
	
	
	protected  final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	protected  final DateFormat dfs = new SimpleDateFormat("yyyy-M-d");

	protected  final DateFormat dfms = new SimpleDateFormat("yyyy-MM");

	protected  final DateFormat dfmcn = new SimpleDateFormat("yyyy年M月");
	
}
