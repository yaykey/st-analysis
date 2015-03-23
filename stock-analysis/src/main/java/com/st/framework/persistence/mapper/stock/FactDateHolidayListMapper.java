package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactDateHolidayList;
import com.st.framework.module.stock.FactDateHolidayListKey;
import com.st.framework.module.stock.example.FactDateHolidayListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactDateHolidayListMapper {
    int countByExample(FactDateHolidayListExample example);

    int deleteByExample(FactDateHolidayListExample example);

    int deleteByPrimaryKey(FactDateHolidayListKey key);

    int insert(FactDateHolidayList record);

    int insertSelective(FactDateHolidayList record);

    List<FactDateHolidayList> selectByExample(FactDateHolidayListExample example);

    FactDateHolidayList selectByPrimaryKey(FactDateHolidayListKey key);

    int updateByExampleSelective(@Param("record") FactDateHolidayList record, @Param("example") FactDateHolidayListExample example);

    int updateByExample(@Param("record") FactDateHolidayList record, @Param("example") FactDateHolidayListExample example);

    int updateByPrimaryKeySelective(FactDateHolidayList record);

    int updateByPrimaryKey(FactDateHolidayList record);
    
    List<String> selectDaysOff (@Param("startDate") String startDate, @Param("endDate") String endDate);
}