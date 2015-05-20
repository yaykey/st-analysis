package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.PMapKey;
import com.st.framework.module.stock.example.PMapExample;
import com.st.framework.persistence.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//@Component("pMapMapper")
public interface PMapMapper extends BaseMapper<PMapKey, PMapKey, PMapExample> {
}