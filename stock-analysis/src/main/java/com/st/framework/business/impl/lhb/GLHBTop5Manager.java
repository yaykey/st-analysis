package com.st.framework.business.impl.lhb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GLHBSecurities;
import com.st.framework.module.stock.GLHBTop5;
import com.st.framework.module.stock.GLHBTop5Key;
import com.st.framework.module.stock.example.GLHBTop5Example;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GLHBTop5Mapper;
import com.st.framework.utils.ConfigUtil;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("gLHBTop5Manager")
public class GLHBTop5Manager extends
		BaseManager<GLHBTop5Key, GLHBTop5, GLHBTop5Example> {

	@Autowired
	protected GLHBTop5Mapper gLHBReportMapper;

	@Override
	public BaseMapper<GLHBTop5Key, GLHBTop5, GLHBTop5Example> getMapper() {
		DbContextHolder.setDefaultDbType();
		return gLHBReportMapper;
	}

	public void insertBatch(final List<GLHBTop5> batchList) {
		if (batchList != null && batchList.size() > 0) {
			DbContextHolder.setDefaultDbType();
			gLHBReportMapper.insertBatch(batchList);

			Global.threadPoolExecutor.execute(new Thread() {
				public void run() {
					try {
						List<GLHBSecurities> secList = new ArrayList<GLHBSecurities>();
						for (GLHBTop5 top5 : batchList) {
							secList.add(new GLHBSecurities(top5.getSecId(),
									top5.getSecName()));
						}

						if (Global._ctx == null) {
							Global._ctx = ConfigUtil.getHelper();
						}

						GLHBSecuritiesManager gLHBSecuritiesManager = (GLHBSecuritiesManager) Global._ctx
								.getBean("gLHBSecuritiesManager");
						gLHBSecuritiesManager.insertBatchSynchronized(secList);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}
	}
	
	public List<GLHBTop5> nextList (Long secId, Date dateId) {
		DbContextHolder.setDefaultDbType();
		return this.gLHBReportMapper.nextList(secId, dateId);
	}
	
	public List<GLHBTop5> prevList (Long secId, Date dateId) {
		DbContextHolder.setDefaultDbType();
		return this.gLHBReportMapper.prevList(secId, dateId);
	}
	
	public List<GLHBTop5> selectRelationList (String stockCode, Date dateId, 
			Date startDateId, Date endDateId) {
		
		if (startDateId == null || dateId == null || stockCode == null) {
			return null;
		}
		DbContextHolder.setDefaultDbType();
		List<Long> secIds = this.selectSecIds(stockCode, dateId);
		
		if (secIds == null || secIds.size() == 0) {
			return null;
		}
		
		GLHBTop5Example example = new GLHBTop5Example();
		
		example.setOrderByClause("DATE_ID desc");
		
		GLHBTop5Example.Criteria c = example.createCriteria();
		
		c.andDateIdGreaterThan(startDateId);
		c.andSecIdIn(secIds);
		
		if (endDateId != null) {
			c.andDateIdLessThan(endDateId);
		}
		
		return this.selectByExample(example);
	}
	
	public List<Long> selectSecIds (String stockCode, Date dateId) {
		DbContextHolder.setDefaultDbType();
		GLHBTop5Example example = new GLHBTop5Example();
		
		GLHBTop5Example.Criteria c = example.createCriteria();
		c.andStockCodeEqualTo(stockCode);
		c.andDateIdEqualTo(dateId);
		
		List<GLHBTop5> list = this.selectByExample(example);
		
		List<Long> res = null;
		
		if (list != null && list.size() > 0) {
			res = new ArrayList<Long>();
			
			for (GLHBTop5 top : list) {
				res.add(top.getSecId());
			}
		}
		
		return res;
	}

}
