package com.st.analysis.utils.stock;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.st.Global;
import com.st.analysis.utils.DateUtils;
import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.fact.FactActiveDateIdIndexManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.example.DStockExample;

public class ActiveTimeIdsUtils extends DetailUtils {

	public static ThreadPoolExecutor threadPoolExecutor;
	public static BlockingQueue<Runnable> AnsyTaskQueue;

	static {
		AnsyTaskQueue = new ArrayBlockingQueue<Runnable>(10);
		// corePoolSize - 池中所保存的线程数，包括空闲线程。
		// maximumPoolSize - 池中允许的最大线程数。
		// keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
		// unit - keepAliveTime 参数的时间单位。
		// workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
		threadPoolExecutor = new ThreadPoolExecutor(1, 3, 3L, TimeUnit.SECONDS,
				AnsyTaskQueue, new ThreadPoolExecutor.CallerRunsPolicy());
	}

	@SuppressWarnings("unchecked")
	public static void findActimeIds2DB() {

		DStockExample example = new DStockExample();
		example.setOrderByClause("STOCK_CODE");
		
		DStockExample.Criteria c = example.createCriteria();
		c.andStockCodeGreaterThanOrEqualTo("000709");
//		c.andStockCodeEqualTo("000417");
		
		List<DStock> stockList = dStockManager.selectByExample(example);

		for (final DStock st : stockList) {
			System.out.println(st.getStockTypeCode() + st.getStockCode()
					+ "\t准备");

//			threadPoolExecutor.execute(new Runnable() {
//
//				@Override
//				public void run() {

					long d1 = System.currentTimeMillis();
					
					List<Integer> list = null;
//					GDetailManager gDetailManager = (GDetailManager) getHelper()
//							.getBean("gDetailManager");
//					FactActiveDateIdIndexManager factActiveDateIdIndexManager = (FactActiveDateIdIndexManager) getHelper()
//							.getBean("factActiveDateIdIndexManager");

					int startDateId = 20150102;
					int endDateId = Integer.parseInt(Global.DF_SIMPLE.format(new Date()));
					
//					int startDateId = 20140102;
//					int endDateId = 20141231;
					
					long d2 = System.currentTimeMillis();
					
					List<Integer> years = null;
					
					try {
						years = DateUtils.getYearDateIds(Global.DF_SIMPLE().parse("" + startDateId), Global.DF_SIMPLE().parse("" + endDateId));
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
//					System.out.println(st.getStockTypeCode()
//							+ st.getStockCode() + "\t years->耗时:"
//							+ (System.currentTimeMillis() - d2));
					
					d2 = System.currentTimeMillis();
					
					List<Integer> removeList = factActiveDateIdIndexManager.selectActiveDateIdByYearIds(Integer.parseInt(st.getStockCode()), years);
					
					System.out.println(st.getStockTypeCode()
							+ st.getStockCode() + "\t removeList.size=" + removeList.size() + "->耗时:"
							+ (System.currentTimeMillis() - d2));
					

					d2 = System.currentTimeMillis();
					
					List<Integer> dateIds = DateUtils
							.getTimeIds(startDateId, endDateId);
					
					if (removeList != null) {
						dateIds.removeAll(removeList);
					}
					
					System.out.println(st.getStockTypeCode()
							+ st.getStockCode() + "\t dateIds.size()=" + dateIds.size());
					
					list = (List<Integer>) gDetailManager
							.selectDetailActiveDateId(st.getStockCode(), st.getStockTypeCode(), dateIds);
					if (list != null) {
					System.out.println(st.getStockTypeCode()
							+ st.getStockCode() + "\t selectDetailActiveDateId->list.size()=" + list.size() + "->耗时:"
							+ (System.currentTimeMillis() - d2));
					}
					
					
					if (list != null && list.size() > 0) {
						d2 = System.currentTimeMillis();
						List<FactActiveDateIdIndex> idxs = new ArrayList<FactActiveDateIdIndex>();

						for (Integer dateId : list) {
							FactActiveDateIdIndex index = new FactActiveDateIdIndex();
							index.setStockCode(Integer.parseInt(st
									.getStockCode()));
							index.setDateId(dateId);

							idxs.add(index);
						}
						
						
						factActiveDateIdIndexManager.insertBatch(idxs);
						
						System.out.println(st.getStockTypeCode()
								+ st.getStockCode() + "\t factActiveDateIdIndexManager.insertBatch->idxs.size=" + idxs.size() + "->耗时:"
								+ (System.currentTimeMillis() - d2));
						
						idxs.clear();
						idxs = null;
					}
					
					

					System.out.println(st.getStockTypeCode()
							+ st.getStockCode() + "\t 总耗时:"
							+ (System.currentTimeMillis() - d1));

//				}
//			});
		}

	}

	public static void main(String[] args) {
		findActimeIds2DB();
	}
}
