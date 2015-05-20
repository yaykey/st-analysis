package com.st.framework.business.impl;

import java.util.List;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GQgqp;
import com.st.framework.module.stock.GQgqpKey;
import com.st.framework.module.stock.example.GQgqpExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GQgqpMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gQgqpManager")
public class GQgqpManager extends BaseManager<GQgqpKey, GQgqp, GQgqpExample> {
    @Autowired
    private GQgqpMapper gQgqpMapper;

    @Override
    public BaseMapper<GQgqpKey, GQgqp, GQgqpExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return gQgqpMapper;
    }
    
    public List<String> selectAllCode () {
    	
    	return gQgqpMapper.selectAllCode();
    }
}