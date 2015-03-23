package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.GDetailSuspension;
import com.st.framework.module.stock.GDetailSuspensionKey;

import com.st.framework.module.stock.example.GDetailSuspensionExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GDetailSuspensionMapper extends BaseMapper<GDetailSuspensionKey, GDetailSuspension, GDetailSuspensionExample> {
    void insertBatch(@Param("list") List<GDetailSuspension> list);
}