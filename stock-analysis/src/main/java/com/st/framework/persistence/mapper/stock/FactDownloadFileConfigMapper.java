package com.st.framework.persistence.mapper.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.FactDownloadFileConfigKey;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.persistence.mapper.BaseMapper;

public interface FactDownloadFileConfigMapper
		extends
		BaseMapper<FactDownloadFileConfigKey, FactDownloadFileConfig, FactDownloadFileConfigExample> {

	void insertBatch(@Param("list") List<FactDownloadFileConfig> list);

	List<String> selectTimeId(@Param("startTime") String startTime,
			@Param("endTime") String endTime);

	List<String> selectStFailTimeId(@Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("stCode") String stCode);

	List<String> selectStSuccessTimeId(@Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("stCode") String stCode);

}