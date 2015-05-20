package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.ThsUser;
import com.st.framework.module.stock.example.ThsUserExample;
import com.st.framework.persistence.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThsUserMapper extends BaseMapper<Integer, ThsUser, ThsUserExample> {
}