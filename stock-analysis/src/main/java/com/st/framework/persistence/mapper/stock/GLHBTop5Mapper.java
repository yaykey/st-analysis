package com.st.framework.persistence.mapper.stock;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.framework.module.stock.GLHBTop5;
import com.st.framework.module.stock.GLHBTop5Key;
import com.st.framework.module.stock.example.GLHBTop5Example;
import com.st.framework.persistence.mapper.BaseMapper;

public interface GLHBTop5Mapper extends
		BaseMapper<GLHBTop5Key, GLHBTop5, GLHBTop5Example> {
	List<GLHBTop5> nextList(@Param("secId") Long secId,
			@Param("dateId") Date dateId);

	List<GLHBTop5> prevList(@Param("secId") Long secId,
			@Param("dateId") Date dateId);
}