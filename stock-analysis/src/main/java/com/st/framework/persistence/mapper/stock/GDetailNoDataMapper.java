package com.st.framework.persistence.mapper.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.framework.module.stock.GDetailNoData;
import com.st.framework.module.stock.GDetailNoDataKey;
import com.st.framework.module.stock.example.GDetailNoDataExample;
import com.st.framework.persistence.mapper.BaseMapper;


public interface GDetailNoDataMapper extends BaseMapper<GDetailNoDataKey, GDetailNoData, GDetailNoDataExample> {
	void insertBatch(@Param("list") List<GDetailNoData> list);
}