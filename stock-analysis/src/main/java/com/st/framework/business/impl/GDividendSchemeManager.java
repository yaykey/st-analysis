package com.st.framework.business.impl;

import java.util.List;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDividendScheme;
import com.st.framework.module.stock.GDividendSchemeKey;
import com.st.framework.module.stock.example.GDividendSchemeExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDividendSchemeMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gDividendSchemeManager")
public class GDividendSchemeManager extends BaseManager<GDividendSchemeKey, GDividendScheme, GDividendSchemeExample> {
    @Autowired
    private GDividendSchemeMapper gDividendSchemeMapper;

    @Override
    public BaseMapper<GDividendSchemeKey, GDividendScheme, GDividendSchemeExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return gDividendSchemeMapper;
    }
    
    public List<String> selectAllCode() {
    	DbContextHolder.setDefaultDbType();
    	return this.gDividendSchemeMapper.selectAllCode();
    }
}