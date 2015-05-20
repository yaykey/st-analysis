package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.RRange;
import com.st.framework.module.stock.RRangeKey;
import com.st.framework.module.stock.example.RRangeExample;
import com.st.framework.persistence.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//@Component("rRangeMapper")
public interface RRangeMapper extends BaseMapper<RRangeKey, RRange, RRangeExample> {
}