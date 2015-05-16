package com.st.framework.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDetailIndex;
import com.st.framework.module.stock.GDetailIndexKey;
import com.st.framework.module.stock.example.GDetailIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDetailIndexMapper;

@Component("gDetailIndexManager")
public class GDetailIndexManager extends
		BaseManager<GDetailIndexKey, GDetailIndex, GDetailIndexExample> {

	@Autowired
	private GDetailIndexMapper gDetailIndexMapper;

	@Override
	public BaseMapper<GDetailIndexKey, GDetailIndex, GDetailIndexExample> getMapper() {

		return gDetailIndexMapper;
	}

}
