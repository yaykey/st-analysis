package com.st.framework.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDetailIndex;
import com.st.framework.module.stock.GDetailIndexKey;
import com.st.framework.module.stock.example.GDetailIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDetailIndexMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("gDetailIndexManager")
public class GDetailIndexManager extends
		BaseManager<GDetailIndexKey, GDetailIndex, GDetailIndexExample> {

	@Autowired
	private GDetailIndexMapper gDetailIndexMapper;

	@Override
	public BaseMapper<GDetailIndexKey, GDetailIndex, GDetailIndexExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return gDetailIndexMapper;
	}
	
	public Date getLastDate (String indexId) {
		DbContextHolder.setDefaultDbType();
		GDetailIndexExample example = new GDetailIndexExample();
		
		example.setStart(0);
		example.setPageSize(1);
		
		//example.setDistinct(true);
		example.setOrderByClause("DATEID desc");
		
		GDetailIndexExample.Criteria c = example.createCriteria();
		c.andIcodeEqualTo(indexId);
		
		List<GDetailIndex> list = this.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0).getDateid();
		}
		
	}

}
