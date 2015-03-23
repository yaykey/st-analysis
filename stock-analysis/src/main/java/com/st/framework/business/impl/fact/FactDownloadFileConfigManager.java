package com.st.framework.business.impl.fact;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.FactDownloadFileConfigKey;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.FactDownloadFileConfigMapper;

@Component("factDownloadFileConfigManager")
public class FactDownloadFileConfigManager
		extends
		BaseManager<FactDownloadFileConfigKey, FactDownloadFileConfig, FactDownloadFileConfigExample> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(FactDownloadFileConfigManager.class);

	@Autowired
	private FactDownloadFileConfigMapper factDownloadFileConfigMapper;

	@Override
	public BaseMapper<FactDownloadFileConfigKey, FactDownloadFileConfig, FactDownloadFileConfigExample> getMapper() {

		return this.factDownloadFileConfigMapper;
	}

	private static List<FactDownloadFileConfig> batchList = new ArrayList<FactDownloadFileConfig>();
	private static int maxBatchSize = 0;

	public void insertBatch(List<FactDownloadFileConfig> list) {
		this.factDownloadFileConfigMapper.insertBatch(list);
	}

	public void insertBatch(FactDownloadFileConfig factDownloadFileConfig) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertBatch(FactDownloadFileConfig) - start");
		}

		synchronized (batchList) {
			batchList.add(factDownloadFileConfig);
			maxBatchSize++;

			if (maxBatchSize > 1000) {
				try {
					this.insertBatch(batchList);
				} catch (DuplicateKeyException e) {
					logger.debug(e);
				}

				batchList.clear();
				maxBatchSize = 0;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertBatch(FactDownloadFileConfig) - end");
		}
	}

	public void flushInsertBatch() {
		if (batchList.size() > 0) {
			try {
				this.insertBatch(batchList);
			} catch (DuplicateKeyException e) {
				logger.debug(e);
			}

			batchList.clear();
			maxBatchSize = 0;
		}
	}

	public List<String> selectStFailTimeId(String startTime, String endTime,
			String stCode) {
		return this.factDownloadFileConfigMapper.selectStFailTimeId(startTime,
				endTime, stCode);
	}

	public List<String> selectSuccessTimeId(String startTime, String endTime) {
		return this.selectStSuccessTimeId(startTime, endTime, null);
	}

	public List<String> selectStSuccessTimeId(String startTime, String endTime,
			String stCode) {

		return this.factDownloadFileConfigMapper.selectStSuccessTimeId(
				startTime, endTime, stCode);
	}
	
	public String selectLastSuccessTimeId (String stockCode) {
		
		FactDownloadFileConfigExample example = new FactDownloadFileConfigExample();
		
		example.setDistinct(true);
		
		example.setOrderByClause("TIME_ID desc");
		
		example.setStart(0);
		example.setPageSize(1);
		
		FactDownloadFileConfigExample.Criteria c = example.createCriteria();
		c.andFailEqualTo(false);
		c.andStCodeEqualTo(stockCode);
		
		List<FactDownloadFileConfig> list = this.selectByExample(example);
		
		if (list != null && list.size() > 0) {
			return list.get(0).getTimeId();
		} else {
			return null;
		}
	}
}
