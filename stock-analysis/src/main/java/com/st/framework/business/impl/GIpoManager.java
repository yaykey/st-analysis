package com.st.framework.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseBLOBsManager;
import com.st.framework.module.stock.GIpo;
import com.st.framework.module.stock.example.GIpoExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import com.st.framework.persistence.mapper.stock.GIpoMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("gIpoManager")
public class GIpoManager extends BaseBLOBsManager<String, GIpo, GIpoExample> {

	@Autowired
	protected GIpoMapper gIpoMapper;

	@Override
	public BaseBLOBsMapper<String, GIpo, GIpoExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return gIpoMapper;
	}

}
