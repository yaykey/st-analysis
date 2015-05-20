package com.st.framework.business.impl;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.PDate;
import com.st.framework.module.stock.example.PDateExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.PDateMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pDateManager")
public class PDateManager extends BaseManager<Integer, PDate, PDateExample> {
    @Autowired
    private PDateMapper pDateMapper;

    @Override
    public BaseMapper<Integer, PDate, PDateExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return pDateMapper;
    }
}