package com.st.framework.business.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.analysis.controller.vo.MMBean;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanRequest;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanResponse;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.persistence.mapper.stock.GDetailMapper;
import com.st.framework.utils.ConfigUtil;

@Component("gDetailManager")
public class GDetailManager {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(GDetailManager.class);

	@Autowired
	private GDetailMapper gDetailMapper;

	// @Autowired
	// public SqlSessionFactory sqlSessionFactory;

	public int countByExample(GDetailExample example) {

		return this.gDetailMapper.countByExample(example);
	}

	public int deleteByExample(GDetailExample example) {

		return this.gDetailMapper.deleteByExample(example);
	}

	public int insert(GDetail record) {

		return this.gDetailMapper.insert(record);
	}

	public int insertSelective(GDetail record) {

		return this.gDetailMapper.insertSelective(record);
	}

	public List<GDetail> selectByExample(GDetailExample example) {

		return this.gDetailMapper.selectByExample(example);
	}

	public int updateByExampleSelective(GDetail record, GDetailExample example) {

		return this.gDetailMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(GDetail record, GDetailExample example) {

		return this.gDetailMapper.updateByExample(record, example);
	}

	public void createGDetailTable(String stockCode) {
		if (this.isDetailTable(stockCode) == false) {
			this.gDetailMapper.createGDetailTable(stockCode);
		}
	}

	public String selectDetailTableName(String stockCode) {
		return this.gDetailMapper.selectDetailTableName(stockCode);
	}

	public boolean isDetailTable(String stockCode) {

		if (this.selectDetailTableName(stockCode) != null) {
			return true;
		} else {
			return false;
		}

	}

	public void insertBatch(String stockCode, List<GDetail> list) {
		
		if (list != null && list.size() > 0) {
			this.gDetailMapper.insertBatch(stockCode, list);
		}
		
		list.clear();
		list = null;

		// System.gc();
	}
	
	public void insertBatchAsynchronous(final String stockCode, final List<GDetail> list) {
		
		if (list != null && list.size() > 0) {
			Global.threadPoolExecutor.execute(new Runnable() {				
				@Override
				public void run() {
					if (Global._ctx == null) {
						Global._ctx = ConfigUtil.getHelper();
					}
					
					GDetailManager gDetailManager = (GDetailManager)Global._ctx.getBean("gDetailManager");
										
					gDetailManager.insertBatch(stockCode, list);
				}
			});
			
		}
		if (list != null) {
			list.clear();
		}
	}

	// private static List<GDetail> batchList = null;
	// private static Integer maxBatchSize = 0;

	private List<GDetail> batchList = null;
	private Integer maxBatchSize = 0;

	public void insertBatch(String stockCode, GDetail gDetail) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertBatch(String, GDetail) - start");
		}

		synchronized (maxBatchSize) {
			if (batchList == null) {
				batchList = new ArrayList<GDetail>();
			}
			batchList.add(gDetail);
			maxBatchSize++;

			if (maxBatchSize >= 5000) {
				try {
					this.insertBatch(stockCode, batchList);
				} catch (DuplicateKeyException e) {
					logger.debug(e);
				}

				batchList.clear();
				batchList = null;
				maxBatchSize = 0;

				// System.gc();

				// try {
				// Thread.sleep(10);
				// } catch (InterruptedException e) {
				// }
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertBatch(String, GDetail) - end");
		}
	}

	public void insertFlushBatch(String stockCode) {
		synchronized (maxBatchSize) {
			if (batchList != null && batchList.size() > 0) {
				try {
					this.insertBatch(stockCode, batchList);
				} catch (DuplicateKeyException e) {
					logger.debug(e);
					logger.info(e);
				}

				// batchList.clear();
				// batchList = null;
				// maxBatchSize=0;
			}

			System.gc();
		}
	}

	public void insertStringBatch(String sql) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertStringBatch(String) - start");
		}

		try {
			this.gDetailMapper.insertStringBatch(sql);
		} catch (Exception ex) {
			logger.error("insertStringBatch(String)", ex);

			throw new RuntimeException(ex);
		}

		sql = null;
		// System.gc();

		if (logger.isDebugEnabled()) {
			logger.debug("insertStringBatch(String) - end");
		}
	}

	public void deleteErrorData(String stockCode, Integer dateId) {
		GDetailExample example = new GDetailExample();
		example.setStockCode(stockCode);
		GDetailExample.Criteria c = example.createCriteria();
		c.andDateIdEqualTo(dateId);

		this.deleteByExample(example);

		example = null;
		c = null;
	}

	public List<GStockDay> selectStockDay(String stockCode,
			Integer startDateId, Integer endDateId) {
		return this.gDetailMapper.selectStockDay(stockCode, startDateId,
				endDateId);
	}
	
	public List<Integer> selectDetailActiveDateId (String stockCode,Integer startDateId,
			Integer endDateId) {
		return this.gDetailMapper.selectDetailActiveDateId(stockCode, startDateId,
				endDateId);
	}
	
	@SuppressWarnings("rawtypes")
	public List<MMBean> selectMMBaseData(String stockCode, List dateIds) {
		return this.gDetailMapper.selectMMBaseData(stockCode, dateIds);
	}

	public List<GDetail> selectWareByExample(GDetailExample example) {	
		return this.gDetailMapper.selectWareByExample(example);
	}

	
}
