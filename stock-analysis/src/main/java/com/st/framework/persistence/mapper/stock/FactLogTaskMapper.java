package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactLogTask;
import com.st.framework.module.stock.example.FactLogTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactLogTaskMapper {
    int countByExample(FactLogTaskExample example);

    int deleteByExample(FactLogTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FactLogTask record);

    int insertSelective(FactLogTask record);

    List<FactLogTask> selectByExample(FactLogTaskExample example);

    FactLogTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FactLogTask record, @Param("example") FactLogTaskExample example);

    int updateByExample(@Param("record") FactLogTask record, @Param("example") FactLogTaskExample example);

    int updateByPrimaryKeySelective(FactLogTask record);

    int updateByPrimaryKey(FactLogTask record);
}