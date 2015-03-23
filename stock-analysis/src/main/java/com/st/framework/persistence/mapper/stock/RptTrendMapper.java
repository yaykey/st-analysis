package com.st.framework.persistence.mapper.stock;


import com.st.framework.module.stock.RptTrendKey;
import com.st.framework.module.stock.example.RptTrendExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RptTrendMapper extends BaseMapper<RptTrendKey, RptTrendKey, RptTrendExample> {
    void insertBatch(@Param("list") List<RptTrendKey> list);
}