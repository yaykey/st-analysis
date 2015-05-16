package com.st.framework.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GLHBSecurities;
import com.st.framework.module.stock.example.GLHBSecuritiesExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GLHBSecuritiesMapper;

@Component("gLHBSecuritiesManager")
public class GLHBSecuritiesManager extends
		BaseManager<Long, GLHBSecurities, GLHBSecuritiesExample> {

	@Autowired
	protected GLHBSecuritiesMapper gLHBReportMapper;

	@Override
	public BaseMapper<Long, GLHBSecurities, GLHBSecuritiesExample> getMapper() {
		return gLHBReportMapper;
	}

}
