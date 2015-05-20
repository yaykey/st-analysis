package com.st.framework.business.impl.room;

import com.st.framework.business.BaseBLOBsManager;
import com.st.framework.module.stock.CircleChatDetail;
import com.st.framework.module.stock.example.CircleChatDetailExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import com.st.framework.persistence.mapper.stock.CircleChatDetailMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("circleChatDetailManager")
public class CircleChatDetailManager extends BaseBLOBsManager<Integer, CircleChatDetail, CircleChatDetailExample> {
    @Autowired
    private CircleChatDetailMapper circleChatDetailMapper;

    @Override
    public BaseBLOBsMapper<Integer, CircleChatDetail, CircleChatDetailExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return circleChatDetailMapper;
    }
}