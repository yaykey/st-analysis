package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.CircleChatDetail;
import com.st.framework.module.stock.CircleChatDetailWithBLOBs;
import com.st.framework.module.stock.example.CircleChatDetailExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CircleChatDetailMapper extends BaseBLOBsMapper<Integer, CircleChatDetail, CircleChatDetailExample> {
}