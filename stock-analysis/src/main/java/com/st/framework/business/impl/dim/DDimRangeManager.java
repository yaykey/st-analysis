package com.st.framework.business.impl.dim;

import java.util.List;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.DDimRangeKey;
import com.st.framework.module.stock.example.DDimRangeExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.DDimRangeMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dDimRangeManager")
public class DDimRangeManager extends BaseManager<DDimRangeKey, DDimRange, DDimRangeExample> {
    @Autowired
    private DDimRangeMapper dDimRangeMapper;

    @Override
    public BaseMapper<DDimRangeKey, DDimRange, DDimRangeExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return dDimRangeMapper;
    }
    
    public List<DDimRange> selectByDimtypeId (Integer dimtypeId) {
    	DbContextHolder.setDefaultDbType();
    	DDimRangeExample example = new DDimRangeExample();
    	
    	DDimRangeExample.Criteria c = example.createCriteria();
    	c.andDimtypeIdEqualTo(dimtypeId);
    	
    	
    	return this.selectByExample(example);
    }
}