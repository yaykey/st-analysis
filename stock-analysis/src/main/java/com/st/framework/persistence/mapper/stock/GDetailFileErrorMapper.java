package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.GDetailFileError;
import com.st.framework.module.stock.GDetailFileErrorKey;
import com.st.framework.module.stock.example.GDetailFileErrorExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GDetailFileErrorMapper extends BaseMapper<GDetailFileErrorKey, GDetailFileError, GDetailFileErrorExample> {
    void insertBatch(@Param("list") List<GDetailFileError> list);
}