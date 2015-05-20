package com.st.framework.business.impl;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.RRange;
import com.st.framework.module.stock.RRangeKey;
import com.st.framework.module.stock.example.RRangeExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.RRangeMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rRangeManager")
public class RRangeManager extends BaseManager<RRangeKey, RRange, RRangeExample> {
    @Autowired
    private RRangeMapper rRangeMapper;

    @Override
    public BaseMapper<RRangeKey, RRange, RRangeExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return rRangeMapper;
    }
}