package com.st.framework.persistence.mapper.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.FactActiveDateIdIndexKey;
import com.st.framework.module.stock.example.FactActiveDateIdIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;

public interface FactActiveDateIdIndexMapper extends BaseMapper<FactActiveDateIdIndexKey, FactActiveDateIdIndex, FactActiveDateIdIndexExample> {
   
	
	List<Integer> selectActiveDateId(@Param("stockCode") Integer stockCode, @Param("dateYearId") Integer dateYearId, @Param("dateYearIds") List dateYearIds);
	
}