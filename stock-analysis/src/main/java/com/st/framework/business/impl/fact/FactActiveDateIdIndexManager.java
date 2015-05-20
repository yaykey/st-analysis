package com.st.framework.business.impl.fact;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.FactActiveDateIdIndexKey;
import com.st.framework.module.stock.example.FactActiveDateIdIndexExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.FactActiveDateIdIndexMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("factActiveDateIdIndexManager")
public class FactActiveDateIdIndexManager
		extends
		BaseManager<FactActiveDateIdIndexKey, FactActiveDateIdIndex, FactActiveDateIdIndexExample> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FactActiveDateIdIndexManager.class);

	@Autowired
	private FactActiveDateIdIndexMapper factActiveDateIdIndexMapper;

	@Override
	public BaseMapper<FactActiveDateIdIndexKey, FactActiveDateIdIndex, FactActiveDateIdIndexExample> getMapper() {
		DbContextHolder.setDefaultDbType();
		return factActiveDateIdIndexMapper;
	}

	
	public synchronized List selectActiveDateId (Integer stockCode, Integer dateYearId) {
		DbContextHolder.setDefaultDbType();
		if (stockCode == null) {
			return new ArrayList(0);
		}
		try {
			return factActiveDateIdIndexMapper.selectActiveDateId(stockCode, dateYearId, null);
		} catch (Exception ex) {
			logger.error("selectActiveDateId(Integer, Integer)", ex);

			return new ArrayList(0);
		}
	}
	
	public synchronized List<Integer> selectActiveDateIdByYearIds (Integer stockCode, List dateYearIds) {
		DbContextHolder.setDefaultDbType();
		if (stockCode == null) {
			return new ArrayList(0);
		}
		try {
			return factActiveDateIdIndexMapper.selectActiveDateId(stockCode, null, dateYearIds);
		} catch (Exception ex) {
			return new ArrayList(0);
		}
	}
}
