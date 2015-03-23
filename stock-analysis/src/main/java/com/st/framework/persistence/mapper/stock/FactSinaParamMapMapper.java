package com.st.framework.persistence.mapper.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.framework.module.stock.FactSinaParamMap;
import com.st.framework.module.stock.example.FactSinaParamMapExample;
import com.st.framework.persistence.mapper.BaseMapper;



public interface FactSinaParamMapMapper 
	extends BaseMapper<Integer,FactSinaParamMap,FactSinaParamMapExample> {
    
	void insertBatch(@Param("list") List<FactSinaParamMap> list);
	
	
	/**
	 * 
	 * 优化查询.
	 * 
	 * 二次ID查询.
	 * 
	 * @param example
	 * @return
	 */
	List<FactSinaParamMap> selectOptimizeByExample(FactSinaParamMapExample example);
	
}