package com.st.framework.persistence.mapper.stock;

import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanRequest;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanResponse;
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
    
    List<TimeRangeCountBeanResponse> selectByTimeRange(
    		@Param("timeClause") String timeClause,
    		@Param("timeRanges") List<TimeRangeCountBeanRequest> list,
    		@Param("example") GStockDayExample example);
}