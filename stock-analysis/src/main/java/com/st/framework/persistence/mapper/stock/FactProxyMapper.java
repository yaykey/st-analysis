package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.FactProxyKey;
import com.st.framework.module.stock.example.FactProxyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactProxyMapper {
    int countByExample(FactProxyExample example);

    int deleteByExample(FactProxyExample example);

    int deleteByPrimaryKey(FactProxyKey key);

    int insert(FactProxy record);

    int insertSelective(FactProxy record);

    List<FactProxy> selectByExample(FactProxyExample example);

    FactProxy selectByPrimaryKey(FactProxyKey key);

    int updateByExampleSelective(@Param("record") FactProxy record, @Param("example") FactProxyExample example);

    int updateByExample(@Param("record") FactProxy record, @Param("example") FactProxyExample example);

    int updateByPrimaryKeySelective(FactProxy record);

    int updateByPrimaryKey(FactProxy record);
}