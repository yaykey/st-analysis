package com.st.framework.business.impl.fact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.FactSinaParamMap;
import com.st.framework.module.stock.example.FactSinaParamMapExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.FactSinaParamMapMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("factSinaParamMapManager")
public class FactSinaParamMapManager
		extends
		BaseManager<Integer, FactSinaParamMap, FactSinaParamMapExample> {

	@Autowired
	private FactSinaParamMapMapper factSinaParamMapMapper;

	@Override
	public BaseMapper<Integer, FactSinaParamMap, FactSinaParamMapExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return this.factSinaParamMapMapper;
	}
	
	public void insertBatch (List<FactSinaParamMap> list) {
		DbContextHolder.setDefaultDbType();
		this.factSinaParamMapMapper.insertBatch(list);
	}
	
	/**
	 * 
	 * 优化查询.
	 * 
	 * 二次ID查询.
	 * 
	 * @param example
	 * @return
	 */
	public List<FactSinaParamMap> selectOptimizeByExample (FactSinaParamMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factSinaParamMapMapper.selectOptimizeByExample(example);
	}

}
