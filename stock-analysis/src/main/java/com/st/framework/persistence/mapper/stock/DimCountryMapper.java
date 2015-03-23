package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.DimCountry;
import com.st.framework.module.stock.example.DimCountryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DimCountryMapper {
    int countByExample(DimCountryExample example);

    int deleteByExample(DimCountryExample example);

    int deleteByPrimaryKey(String ab2);

    int insert(DimCountry record);

    int insertSelective(DimCountry record);

    List<DimCountry> selectByExample(DimCountryExample example);

    DimCountry selectByPrimaryKey(String ab2);

    int updateByExampleSelective(@Param("record") DimCountry record, @Param("example") DimCountryExample example);

    int updateByExample(@Param("record") DimCountry record, @Param("example") DimCountryExample example);

    int updateByPrimaryKeySelective(DimCountry record);

    int updateByPrimaryKey(DimCountry record);
}