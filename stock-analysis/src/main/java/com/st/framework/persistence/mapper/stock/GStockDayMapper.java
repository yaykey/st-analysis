package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.GStockDayKey;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GStockDayMapper extends BaseMapper<GStockDayKey, GStockDay, GStockDayExample> {
    void insertBatch(@Param("list") List<GStockDay> list);
    
    void updatePriceChanges(@Param("stock") Integer stock);
    
    void batchUpdateMMByPrimaryKey(@Param("list") List<GStockDay> list);
}