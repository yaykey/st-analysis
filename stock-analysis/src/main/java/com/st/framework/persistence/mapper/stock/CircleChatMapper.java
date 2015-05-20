package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.CircleChat;
import com.st.framework.module.stock.example.CircleChatExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface CircleChatMapper extends BaseBLOBsMapper<Integer, CircleChat, CircleChatExample> {

	@Select("select ID, ROOME_ID, DATE_ID, TIME_ID, MD5_CODE from FACT_THS_CIRCLE_CHAT where MD5_CODE = #{md5Code}")
	@ResultMap("BaseResultMap")
	CircleChat selectByMD5(@Param("md5Code") String md5Code);
}