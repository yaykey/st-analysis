package com.st.framework.business.impl.dim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.DStockIndex;
import com.st.framework.module.stock.example.DStockIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.DStockIndexMapper;

@Component("dStockIndexManager")
public class DStockIndexManager extends
		BaseManager<String, DStockIndex, DStockIndexExample> {

	@Autowired
	private DStockIndexMapper dStockIndexMapper;

	@Override
	public BaseMapper<String, DStockIndex, DStockIndexExample> getMapper() {

		return this.dStockIndexMapper;
	}

}
