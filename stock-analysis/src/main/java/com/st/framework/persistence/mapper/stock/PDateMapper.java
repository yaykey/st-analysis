package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.PDate;
import com.st.framework.module.stock.example.PDateExample;
import com.st.framework.persistence.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//@Component("pDateMapper")
public interface PDateMapper extends BaseMapper<Integer, PDate, PDateExample> {
}