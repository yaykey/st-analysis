package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.GQgqp;
import com.st.framework.module.stock.GQgqpKey;
import com.st.framework.module.stock.example.GQgqpExample;
import com.st.framework.persistence.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GQgqpMapper extends BaseMapper<GQgqpKey, GQgqp, GQgqpExample> {
	
	@Select("SELECT distinct code FROM G_QGQP order by code")
	List<String> selectAllCode ();
}