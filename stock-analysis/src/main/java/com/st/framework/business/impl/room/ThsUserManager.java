package com.st.framework.business.impl.room;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.ThsUser;
import com.st.framework.module.stock.example.ThsUserExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.ThsUserMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("thsUserManager")
public class ThsUserManager extends BaseManager<Integer, ThsUser, ThsUserExample> {
    @Autowired
    private ThsUserMapper thsUserMapper;

    @Override
    public BaseMapper<Integer, ThsUser, ThsUserExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return thsUserMapper;
    }
}