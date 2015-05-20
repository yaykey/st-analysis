package com.st.framework.business.impl.dim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.DDimtype;
import com.st.framework.module.stock.example.DDimtypeExample;
import com.st.framework.persistence.mapper.stock.DDimtypeMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("dDimtypeManager")
public class DDimtypeManager extends
		BaseManager<Integer, DDimtype, DDimtypeExample> {

	@Autowired
	private DDimtypeMapper dDimtypeMapper;

	public DDimtypeMapper getMapper() {
		DbContextHolder.setDefaultDbType();
		return this.dDimtypeMapper;
	}

}
