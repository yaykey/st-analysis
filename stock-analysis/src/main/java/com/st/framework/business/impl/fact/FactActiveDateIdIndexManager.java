package com.st.framework.business.impl.fact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.FactActiveDateIdIndexKey;
import com.st.framework.module.stock.example.FactActiveDateIdIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.FactActiveDateIdIndexMapper;

@Component("factActiveDateIdIndexManager")
public class FactActiveDateIdIndexManager
		extends
		BaseManager<FactActiveDateIdIndexKey, FactActiveDateIdIndex, FactActiveDateIdIndexExample> {

	@Autowired
	private FactActiveDateIdIndexMapper factActiveDateIdIndexMapper;

	@Override
	public BaseMapper<FactActiveDateIdIndexKey, FactActiveDateIdIndex, FactActiveDateIdIndexExample> getMapper() {

		return factActiveDateIdIndexMapper;
	}

}
