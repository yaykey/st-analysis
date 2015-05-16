package com.st.framework.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDetailNoData;
import com.st.framework.module.stock.GDetailNoDataKey;
import com.st.framework.module.stock.example.GDetailNoDataExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDetailNoDataMapper;
import com.st.framework.utils.ConfigUtil;

@Component("gDetailNoDataManager")
public class GDetailNoDataManager extends
		BaseManager<GDetailNoDataKey, GDetailNoData, GDetailNoDataExample> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(GDetailNoDataManager.class);

	@Autowired
	private GDetailNoDataMapper gDetailNoDataMapper;

	@Override
	public BaseMapper<GDetailNoDataKey, GDetailNoData, GDetailNoDataExample> getMapper() {

		return gDetailNoDataMapper;
	}

	public void increaseBalance(GDetailNoData gDetailNoDataKey) {
		GDetailNoData gDetailNoData = this.selectByPrimaryKey(gDetailNoDataKey);

		if (gDetailNoData == null) {
			this.insert(gDetailNoDataKey);
		} else {
			gDetailNoData.setBalance(gDetailNoData.getBalance() + 1);

			if (gDetailNoData.getBalance() > 9) {
				gDetailNoData.setIsDeal(true);
			}

			this.updateByPrimaryKey(gDetailNoData);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<Integer> selectErrorDateIdsUnion(final String stockCode,
			List<Integer> yearDateIds) {

		if (stockCode == null) {
			return null;
		}

		if (yearDateIds == null || yearDateIds.size() == 0) {
			return selectErrorDateIds(stockCode, null);
		} else {
			final List<Integer> resutl = new ArrayList<Integer>();

			List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
			Callable<Integer> task = null;

			for (int i=0; i<yearDateIds.size(); i++) {
				final Integer yearDateId = yearDateIds.get(i);
//				task = new Callable<Integer>() {
//					@Override
//					public Integer call() throws Exception {
						List<Integer> list = selectErrorDateIds(stockCode, yearDateId);

						if (list != null) {
							resutl.addAll(list);
						}
//
//						return 0;
//					}
//				};

				tasks.add(task);
			}

//			try {
//				Global.threadPoolExecutor.invokeAll(tasks);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

			return resutl;
		}
	}

	public List<Integer> selectErrorDateIds(String stockCode, Integer yearDateId) {
		if (stockCode == null) {
			return null;
		}

		GDetailNoDataExample example = new GDetailNoDataExample();
		GDetailNoDataExample.Criteria c = example.createCriteria();

		if (stockCode.toUpperCase().indexOf("SZ") != -1
				|| stockCode.toUpperCase().indexOf("SH") != -1) {
			c.andStockCodeEqualTo(stockCode);
		} else {
			c.andStockCodeLike("%" + stockCode + "%");
		}

		//c.andBalanceLessThanOrEqualTo(5);

		if (yearDateId != null) {
			c.andDateIdLike(yearDateId + "%");
		}

		// c.andIsDealEqualTo(false);

		List<GDetailNoData> list = this.selectByExample(example);

		List<Integer> result = null;

		if (list != null && list.size() > 0) {
			result = new ArrayList<Integer>();
			for (GDetailNoData error : list) {
				result.add(error.getDateId());
			}
		}

		return result;
	}

	public static void main(String[] args) {
		GDetailNoDataManager gDetailNoDataManager = (GDetailNoDataManager) ConfigUtil
				.getHelper().getBean("gDetailNoDataManager");

		List list = gDetailNoDataManager.selectErrorDateIds("300001", 2014);

		System.out.println(list);
	}

}
