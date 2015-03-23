package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactDateHoliday;
import com.st.framework.module.stock.example.FactDateHolidayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactDateHolidayMapper {
    int countByExample(FactDateHolidayExample example);

    int deleteByExample(FactDateHolidayExample example);

    int deleteByPrimaryKey(String festival);

    int insert(FactDateHoliday record);

    int insertSelective(FactDateHoliday record);

    List<FactDateHoliday> selectByExample(FactDateHolidayExample example);

    FactDateHoliday selectByPrimaryKey(String festival);

    int updateByExampleSelective(@Param("record") FactDateHoliday record, @Param("example") FactDateHolidayExample example);

    int updateByExample(@Param("record") FactDateHoliday record, @Param("example") FactDateHolidayExample example);

    int updateByPrimaryKeySelective(FactDateHoliday record);

    int updateByPrimaryKey(FactDateHoliday record);
}