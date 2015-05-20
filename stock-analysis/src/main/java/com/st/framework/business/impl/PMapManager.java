package com.st.framework.business.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.st.Global;
import com.st.analysis.utils.DateUtils;
import com.st.framework.business.BaseManager;
import com.st.framework.business.impl.dim.DDimRangeManager;
import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.PMapKey;
import com.st.framework.module.stock.example.PMapExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.PMapMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pMapManager")
public class PMapManager extends BaseManager<PMapKey, PMapKey, PMapExample> {
	@Autowired
	private PMapMapper pMapMapper;

	

	@Override
	public BaseMapper<PMapKey, PMapKey, PMapExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return pMapMapper;
	}

	public List<PMapKey> selectRangeMap() {
		DbContextHolder.setDefaultDbType();
		PMapExample example = new PMapExample();

		example.setOrderByClause("INDEX_ID, DIMTYPE_ID, DIM_ID");

		PMapExample.Criteria c = example.createCriteria();
		c.andRptIdEqualTo(3001001);

		return this.selectByExample(example);
	}

}