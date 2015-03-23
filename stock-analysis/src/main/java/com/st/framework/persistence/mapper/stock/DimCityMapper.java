package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.DimCity;
import com.st.framework.module.stock.example.DimCityExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface DimCityMapper {
    int countByExample(DimCityExample example);

    int deleteByExample(DimCityExample example);

    int deleteByPrimaryKey(Integer cityId);

    int insert(DimCity record);

    int insertSelective(DimCity record);

    List<DimCity> selectByExample(DimCityExample example);

    DimCity selectByPrimaryKey(Integer cityId);

    int updateByExampleSelective(@Param("record") DimCity record, @Param("example") DimCityExample example);

    int updateByExample(@Param("record") DimCity record, @Param("example") DimCityExample example);

    int updateByPrimaryKeySelective(DimCity record);

    int updateByPrimaryKey(DimCity record);
}