package com.st.framework.business.impl.lhb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GLHBReport;
import com.st.framework.module.stock.GLHBReportKey;
import com.st.framework.module.stock.example.GLHBReportExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GLHBReportMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("gLHBReportManager")
public class GLHBReportManager extends
		BaseManager<GLHBReportKey, GLHBReport, GLHBReportExample> {

	@Autowired
	protected GLHBReportMapper gLHBReportMapper;

	@Override
	public BaseMapper<GLHBReportKey, GLHBReport, GLHBReportExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return gLHBReportMapper;
	}

}
