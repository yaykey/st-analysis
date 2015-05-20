package com.st.framework.business.impl;

import org.apache.log4j.Logger;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.analysis.controller.vo.MMBean;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanRequest;
import com.st.analysis.controller.vo.timerange.TimeRangeCountBeanResponse;
import com.st.analysis.utils.DateUtils;
import com.st.framework.business.impl.fact.FactActiveDateIdIndexManager;
import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.persistence.mapper.stock.GDetailMapper;
import com.st.framework.utils.ConfigUtil;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("gDetailManager")
public class GDetailManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GDetailManager.class);
	
	@Autowired
	private GDetailMapper gDetailMapper;
	
	@Autowired
	private FactActiveDateIdIndexManager factActiveDateIdIndexManager;

	public void setDbType (String stockType) {
		if ("sh".equalsIgnoreCase(stockType)) {
			DbContextHolder.setGSHDbType();
		} else if ("sz".equalsIgnoreCase(stockType)) {
			DbContextHolder.setGSZDbType();
		}
		
		
		
	}
	
	public void setDbType (GDetailExample example) {
		if ("sz".equalsIgnoreCase(example.getStockType())) {
			DbContextHolder.setGSZDbType();
			return;
		}
		
		if ("sh".equalsIgnoreCase(example.getStockType())) {
			DbContextHolder.setGSHDbType();
			return;
		}
		
		if (example.getStockCode().indexOf("sh") != -1) {
			DbContextHolder.setGSHDbType();
			return;
		}
		
		if (example.getStockCode().indexOf("sz") != -1) {
			DbContextHolder.setGSZDbType();
			return;
		}
	}
	
	public int countByExample(GDetailExample example) {
		
		if (example == null) {
			return 0;
		}
		
		setDbType(example);
		
		return this.gDetailMapper.countByExample(example);
	}

	public int deleteByExample(GDetailExample example) {
		setDbType(example);
		return this.gDetailMapper.deleteByExample(example);
	}

	public int insert(GDetail record, String stockType) {
		setDbType(stockType);
		return this.gDetailMapper.insert(record);
	}

	public int insertSelective(GDetail record, String stockType) {
		setDbType(stockType);
		return this.gDetailMapper.insertSelective(record);
	}

	public List<GDetail> selectByExample(GDetailExample example) {
		setDbType(example);
		return this.gDetailMapper.selectByExample(example);
	}

	public int updateByExampleSelective(GDetail record, GDetailExample example) {
		setDbType(example);
		return this.gDetailMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(GDetail record, GDetailExample example) {
		setDbType(example);
		return this.gDetailMapper.updateByExample(record, example);
	}

	public void createGDetailTable(String stockCode, String stockType) {
		
		setDbType(stockType);
		
		if (this.isDetailTable(stockCode, stockType) == false) {
			this.gDetailMapper.createGDetailTable(stockType.toUpperCase() + stockCode);
		}
	}

	public String selectDetailTableName(String stockCode, String stockType) {
		setDbType(stockType);
		return this.gDetailMapper.selectDetailTableName(stockType + stockCode);
	}

	public boolean isDetailTable(String stockCode, String stockType) {
		
		if (this.selectDetailTableName(stockCode, stockType) != null) {
			return true;
		} else {
			return false;
		}

	}

	public void insertBatch(String stockCode, String stockType, List<GDetail> list) {
		setDbType(stockType);
		
		if (list != null && list.size() > 0) {
			this.gDetailMapper.insertBatch(stockType + stockCode, list);
			
			List<FactActiveDateIdIndex> activeIdxs = new ArrayList<FactActiveDateIdIndex>();
			
			for (GDetail detail : list) {
				FactActiveDateIdIndex idx = new FactActiveDateIdIndex();
				idx.setStockCode(Integer.parseInt(stockCode));
				idx.setDateId(detail.getDateId());
				activeIdxs.add(idx);
			}
			
			factActiveDateIdIndexManager.insertBatch(activeIdxs);
			activeIdxs.clear();
			activeIdxs = null;
			
		}
		
		

		list.clear();
		list = null;

		System.gc();
	}

	public void insertBatchAsynchronous(final String stockCode,final String stockType,
			final List<GDetail> list) {
		
		setDbType(stockType);
		
		if (list != null && list.size() > 0) {
			Global.threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					if (Global._ctx == null) {
						Global._ctx = ConfigUtil.getHelper();
					}

					GDetailManager gDetailManager = (GDetailManager) Global._ctx
							.getBean("gDetailManager");

					gDetailManager.insertBatch(stockCode,stockType, list);
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

	public void insertBatch(String stockCode,String stockType, GDetail gDetail) {
		if (logger.isDebugEnabled()) {
			logger.debug("insertBatch(String, GDetail) - start");
		}
		
		setDbType(stockType);
		
		synchronized (maxBatchSize) {
			if (batchList == null) {
				batchList = new ArrayList<GDetail>();
			}
			batchList.add(gDetail);
			maxBatchSize++;

			if (maxBatchSize >= 5000) {
				try {
					this.insertBatch(stockCode, stockType, batchList);
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

	public void insertFlushBatch(String stockCode, String stockType) {
	
		synchronized (maxBatchSize) {
			if (batchList != null && batchList.size() > 0) {
				try {
					this.insertBatch(stockCode, stockType, batchList);
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

//	public void insertStringBatch(String sql) {
//		
//		if (logger.isDebugEnabled()) {
//			logger.debug("insertStringBatch(String) - start");
//		}
//
//		try {
//			this.gDetailMapper.insertStringBatch(sql);
//		} catch (Exception ex) {
//			logger.error("insertStringBatch(String)", ex);
//
//			throw new RuntimeException(ex);
//		}
//
//		sql = null;
//		// System.gc();
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("insertStringBatch(String) - end");
//		}
//	}

	public void deleteErrorData(String stockCode,String stockType, Integer dateId) {
		
		GDetailExample example = new GDetailExample();
		
		example.setStockCode(stockType + stockCode);
		example.setStockType(stockType);
		
		GDetailExample.Criteria c = example.createCriteria();
		c.andDateIdEqualTo(dateId);
		
		setDbType(example);
		
		this.deleteByExample(example);

		example = null;
		c = null;
	}

	public List<GStockDay> selectStockDay(String stockCode,String stockType,
			Integer startDateId, Integer endDateId) {
		setDbType(stockType);
		return this.gDetailMapper.selectStockDay(stockType + stockCode, startDateId,
				endDateId);
	}
	
	public List<Integer> selectDetailActiveDateId(String stockCode, String stockType, List<Integer> dateIds) {
		
		if (dateIds == null || dateIds.size() == 0) {
			return null;
		}
		
		try {
			setDbType(stockType);
			return this.gDetailMapper.selectDetailActiveDateId(stockType.toUpperCase() + stockCode,
					null, null, dateIds);
		} catch (Exception ex) {
			logger.warn("selectDetailActiveDateId(String, String, Integer, Integer) - exception ignored", ex);

		}
		return null;
	}

	public List<Integer> selectDetailActiveDateId(String stockCode, String stockType, Integer startDateId,
			Integer endDateId) {
		
		try {
			
			List<Integer> dateIds = DateUtils
					.getTimeIds(startDateId, endDateId);
			

			setDbType(stockType);
			return this.gDetailMapper.selectDetailActiveDateId(stockType.toUpperCase() + stockCode,
					startDateId, endDateId, dateIds);
		} catch (Exception ex) {
			logger.warn("selectDetailActiveDateId(String, String, Integer, Integer) - exception ignored", ex);

		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<MMBean> selectMMBaseData(String stockCode,String stockType, List dateIds) {
		
		setDbType(stockType);
		return this.gDetailMapper.selectMMBaseData(stockType + stockCode, dateIds);
	}

	public List<GDetail> selectWareByExample(GDetailExample example) {
		setDbType(example);
		return this.gDetailMapper.selectWareByExample(example);
	}

	
	public void insertAnalysisData (Integer rptId, Integer dateId, 
			Map<String,Integer> indexIdMap, Integer timeDimtypeId,List<DDimRange> volDimList, 
			String stockType, String stockCode) {
		setDbType(stockType);
		gDetailMapper.insertAnalysisData(rptId, dateId, indexIdMap, timeDimtypeId, volDimList, stockType, stockCode);
	}

	public void deleteByDateId(String stockCode, String stockType,
			Integer dateId) {
		setDbType(stockType);
		
		if (stockCode == null || "".equals(stockCode.trim())
				|| stockType == null || "".equals(stockType.trim())
			|| dateId == null) {
			return;
		}
		
		this.gDetailMapper.deleteByDateId(stockCode, stockType, dateId);
	}
}
