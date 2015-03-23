package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactDateAlmanac;
import com.st.framework.module.stock.example.FactDateAlmanacExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactDateAlmanacMapper {
    int countByExample(FactDateAlmanacExample example);

    int deleteByExample(FactDateAlmanacExample example);

    int deleteByPrimaryKey(String date);

    int insert(FactDateAlmanac record);

    int insertSelective(FactDateAlmanac record);

    List<FactDateAlmanac> selectByExample(FactDateAlmanacExample example);

    FactDateAlmanac selectByPrimaryKey(String date);

    int updateByExampleSelective(@Param("record") FactDateAlmanac record, @Param("example") FactDateAlmanacExample example);

    int updateByExample(@Param("record") FactDateAlmanac record, @Param("example") FactDateAlmanacExample example);

    int updateByPrimaryKeySelective(FactDateAlmanac record);

    int updateByPrimaryKey(FactDateAlmanac record);
}