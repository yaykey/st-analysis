package com.st.framework.business.impl.room;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.ThsOwner;
import com.st.framework.module.stock.example.ThsOwnerExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.ThsOwnerMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("thsOwnerManager")
public class ThsOwnerManager extends BaseManager<Integer, ThsOwner, ThsOwnerExample> {
    @Autowired
    private ThsOwnerMapper thsOwnerMapper;

    @Override
    public BaseMapper<Integer, ThsOwner, ThsOwnerExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return thsOwnerMapper;
    }
}