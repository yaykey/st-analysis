package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.PStockMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PStockMapMapper {
    int countByExample(PStockMapExample example);

    int deleteByExample(PStockMapExample example);

    int deleteByPrimaryKey(PStockMapKey key);

    int insert(PStockMapKey record);

    int insertSelective(PStockMapKey record);

    List<PStockMapKey> selectByExample(PStockMapExample example);

    int updateByExampleSelective(@Param("record") PStockMapKey record, @Param("example") PStockMapExample example);

    int updateByExample(@Param("record") PStockMapKey record, @Param("example") PStockMapExample example);
}