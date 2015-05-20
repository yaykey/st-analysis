package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.DDimRangeKey;
import com.st.framework.module.stock.example.DDimRangeExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DDimRangeMapper extends BaseMapper<DDimRangeKey, DDimRange, DDimRangeExample> {
}