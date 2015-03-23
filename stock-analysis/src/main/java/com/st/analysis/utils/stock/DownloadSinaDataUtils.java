package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;

import com.st.analysis.utils.download.DownloadFileBean;
import com.st.analysis.utils.download.ReturnBean;
import com.st.analysis.utils.download.observer.NioDownload;
import com.st.analysis.utils.download.thread.BaseCallable;
import com.st.analysis.utils.download.thread.Consume;
import com.st.analysis.utils.download.thread.Producer;
import com.st.analysis.utils.download.thread.SyncStack;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.business.impl.GDetailSuspensionManager;
import com.st.framework.business.impl.fact.FactDownloadFileConfigManager;
import com.st.framework.business.impl.fact.FactLogTaskManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.FactLogDownload;
import com.st.framework.module.stock.FactLogTask;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.module.stock.example.PStockMapExample;
import com.st.framework.persistence.mapper.stock.FactDateHolidayListMapper;
import com.st.framework.persistence.mapper.stock.FactLogDownloadMapper;
import com.st.framework.utils.LoadConfigUtils;

import com.st.framework.utils.network.BaseDBUtils;
import com.st.framework.utils.page.Page;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The Class TaskBeanUtils.
 */
public class DownloadSinaDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(DownloadSinaDataUtils.class);

	/**
	 * The fact log task manager.
	 */
	private static FactLogTaskManager factLogTaskManager = (FactLogTaskManager) getHelper()
			.getBean("factLogTaskManager");

	/**
	 * The fact date holiday list mapper.
	 */
	private static FactDateHolidayListMapper factDateHolidayListMapper = (FactDateHolidayListMapper) getHelper()
			.getBean("factDateHolidayListMapper");

	private static FactLogDownloadMapper factLogDownloadMapper = (FactLogDownloadMapper) getHelper()
			.getBean("factLogDownloadMapper");

	private static FactDownloadFileConfigManager factDownloadFileConfigManager = (FactDownloadFileConfigManager) getHelper()
			.getBean("factDownloadFileConfigManager");
	
	private static GDetailSuspensionManager gDetailSuspensionManager = (GDetailSuspensionManager) getHelper()
	.getBean("gDetailSuspensionManager");

	/**
	 * 数据开始时间:天
	 */
	private String startDate;

	/**
	 * 数据结束时间:天
	 */
	private String endDate;

	/**
	 * 股票代码
	 */
	private String stockCode;
	
	private String stockType;

	/**
	 * 上市日期
	 */
	private String listingDate;

	/**
	 * 需要下载的股票列表
	 */
	private List<DStock> stockList;

	/**
	 * 需要下载的股票列表
	 */
	private List<String> stockCodes;

	/**
	 * 休息日
	 */
	private static List<String> daysOff = new ArrayList<String>();
	
	/**
	 * 无数据停盘日
	 */
	private List<String> suspensionDays = new ArrayList<String>();
	
	/**
	 * 成功下载
	 */
	private List<String> successDays = new ArrayList<String>();

	/**
	 * 新浪数据
	 */
	private String remoteServiceUrl = "http://market.finance.sina.com.cn/downxls.php";

	/**
	 * The df.
	 */
	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public DownloadSinaDataUtils() {

	}

	public DownloadSinaDataUtils(String startDate, String stockCode) {
		this.startDate = startDate;
		this.endDate = startDate;
		this.stockCode = stockCode;

	}

	public DownloadSinaDataUtils(String startDate, String endDate, DStock stock) {
		this.startDate = startDate;
		this.endDate = endDate;

		if (stock != null) {
			this.stockCode = stock.getStockTypeCode() + stock.getStockCode();
			this.listingDate = df.format(stock.getListingDate());
		}

		if (this.listingDate != null) {
			if (this.startDate == null) {
				this.startDate = this.listingDate;
			} else {
				try {
					if (df.parse(this.listingDate).getTime() > df.parse(
							this.startDate).getTime()) {
						this.startDate = this.listingDate;
					}
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
	}

	public DownloadSinaDataUtils(String startDate, String endDate,
			String stockCode) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.stockCode = stockCode;

	}

	public DownloadSinaDataUtils(String startDate, String endDate,
			List<DStock> stockList) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.stockList = stockList;
	}

	/**
	 * 
	 * 获得下载url列表;
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public List<DownloadFileBean> getUrlList() {
		List<DownloadFileBean> urlList = new ArrayList<DownloadFileBean>();

		String start = this.startDate;
		String end = this.endDate;

		Calendar cal = Calendar.getInstance();

		try {
			cal.setTime(df.parse(start));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		if (this.daysOff == null || this.daysOff.isEmpty()) {
			this.daysOff = this.factDateHolidayListMapper.selectDaysOff(start, end);
			if (logger.isInfoEnabled()) {
				logger.info("getUrlList() - List<String> daysOff=" + daysOff);
			}
		}
		
		if (this.suspensionDays == null || this.suspensionDays.isEmpty()) {
			this.suspensionDays = this.gDetailSuspensionManager.selectSuspensionDays(start, end, this.stockCode);
			if (logger.isInfoEnabled()) {
				logger.info("getUrlList() - List<String> suspensionDays=" + suspensionDays);
			}
		}
		
		if (this.successDays == null || this.successDays.isEmpty()) {
			this.successDays = this.factDownloadFileConfigManager.selectStSuccessTimeId(start, end, this.stockCode);
			if (logger.isInfoEnabled()) {
				logger.info("getUrlList() - List<String> successDays=" + successDays);
			}
		}
		
		String remoteFileUrl = "";
		String savePath = "";
		if (this.stockCode != null) {
			while (start.compareTo(end) <= 0) {
				if (!daysOff.contains(start) && !suspensionDays.contains(start) && !successDays.contains(start) ) {

					DownloadFileBean downloadFileBean = new DownloadFileBean();

					downloadFileBean.setTimeId(start);
					downloadFileBean.setStockCode(this.stockCode);

					// savePath =
					// LoadConfigUtils.getInstance().getDownloadFilePath() +
					// "/" + this.stockCode + "/" + start.substring(0, 4);

					savePath = "/" + this.stockCode + "/"
							+ start.substring(0, 4);

					downloadFileBean.setSavePath(savePath);

					remoteFileUrl = remoteServiceUrl + "?date=" + start
							+ "&symbol=" + this.stockCode;
					// if (logger.isInfoEnabled()) {
					logger.debug("getUrlList() - String remoteFileUrl="
							+ remoteFileUrl);
					// }

					downloadFileBean.setRemoteFileUrl(remoteFileUrl);

					urlList.add(downloadFileBean);
				}

				cal.add(Calendar.DAY_OF_MONTH, 1);
				
				if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
					cal.add(Calendar.DAY_OF_MONTH, 2);
				} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
					cal.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				start = df.format(cal.getTime());
			}
		}

		if (this.stockList != null) {
			for (DStock stock : stockList) {

				// 重新初始化
				start = this.startDate;
				end = this.endDate;

				try {
					cal.setTime(df.parse(start));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				while (start.compareTo(end) <= 0) {
					if (!daysOff.contains(start)) {

						DownloadFileBean downloadFileBean = new DownloadFileBean();

						downloadFileBean.setTimeId(start);
						downloadFileBean.setStockCode(this.stockCode);

						// savePath =
						// LoadConfigUtils.getInstance().getDownloadFilePath() +
						// "/" + this.stockCode + "/" + start.substring(0, 4);

						savePath = "/" + stock.getStockTypeCode()
								+ stock.getStockCode() + "/"
								+ start.substring(0, 4);

						downloadFileBean.setSavePath(savePath);

						remoteFileUrl = remoteServiceUrl + "?date=" + start
								+ "&symbol=" + stock.getStockTypeCode()
								+ stock.getStockCode();
						// if (logger.isInfoEnabled()) {
						logger.debug("getUrlList() - String remoteFileUrl="
								+ remoteFileUrl);
						// }

						downloadFileBean.setRemoteFileUrl(remoteFileUrl);

						urlList.add(downloadFileBean);
					}

					cal.add(Calendar.DAY_OF_MONTH, 1);
					start = df.format(cal.getTime());
				}
			}
		}

		return urlList;
	}

	/**
	 * Gets the 数据开始时间:天.
	 * 
	 * @return the 数据开始时间:天
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the 数据开始时间:天.
	 * 
	 * @param startDate
	 *            the new 数据开始时间:天
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the 数据结束时间:天.
	 * 
	 * @return the 数据结束时间:天
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the 数据结束时间:天.
	 * 
	 * @param endDate
	 *            the new 数据结束时间:天
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the 股票代码.
	 * 
	 * @return the 股票代码
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * Sets the 股票代码.
	 * 
	 * @param stockCode
	 *            the new 股票代码
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	/**
	 * Gets the 新浪数据.
	 * 
	 * @return the 新浪数据
	 */
	public String getRemoteServiceUrl() {
		return remoteServiceUrl;
	}

	/**
	 * Sets the 新浪数据.
	 * 
	 * @param remoteServiceUrl
	 *            the new 新浪数据
	 */
	public void setRemoteServiceUrl(String remoteServiceUrl) {
		this.remoteServiceUrl = remoteServiceUrl;
	}

	public void downSTFile() {
		downSTFile(false);
	}

	/**
	 * 下载st文件
	 * 
	 * @param startTime
	 * @param endTime
	 * @param stCode
	 */
	public void downSTFile(boolean isFast) {
		List<String> daysOff = factDateHolidayListMapper.selectDaysOff(
				this.startDate, this.endDate);

		// if (logger.isInfoEnabled()) {
		logger.debug("List daysOff=" + daysOff);
		// }

		// TaskBeanUtils tb = new TaskBeanUtils(startTime, endTime, stCode);
		//
		// tb.setDaysOff(daysOff);
		//
		// List<DownloadFileBean> list = tb.getUrlList();

		this.setDaysOff(daysOff);

		List<DownloadFileBean> list = this.getUrlList();

		// if (logger.isInfoEnabled()) {
		// logger.info("main(String[]) - List<DownloadFileBean> list=" + list);
		// }

		// //String remoteFileUrl =
		// "http://market.finance.sina.com.cn/downxls.php?date=2014-01-02&symbol=sz300002";
		//
		//

		ExecutorService threadPool = new ThreadPoolExecutor(2, 30, 30L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30),
				new ThreadPoolExecutor.DiscardPolicy());

		SyncStack ss = new SyncStack(list);// 建造一个装馒头的框

		if (isFast == true) {
			ss.setIsFast(true);
		}

		// List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
		// ss.setProxyList(proxyList);

		Producer p = new Producer(ss);// 新建一个生产者，使之持有框
		Consume c = new Consume(ss);// 新建一个消费者，使之持有同一个框

		List<BaseCallable> tasks = new ArrayList<BaseCallable>();

		tasks.add(p);
		tasks.add(c);

		try {
			List<Future<List<ReturnBean>>> results = threadPool
					.invokeAll(tasks);
			// if (logger.isInfoEnabled()) {
			// logger.info("downSTFile() - List results.size="
			// + results.size());
			// }

			for (Future<List<ReturnBean>> future : results) {
				List<ReturnBean> returnBeanList = future.get();
				for (ReturnBean returnBean : returnBeanList) {
					logger.debug(returnBean);

					DownloadFileBean downloadFileBean = returnBean
							.getDownloadFileBean();

					FactLogDownload factLogDownload = new FactLogDownload();
					factLogDownload.setIsSuccess(returnBean.isSuccess());
					factLogDownload.setStTime(downloadFileBean.getTimeId());
					factLogDownload.setStCode(downloadFileBean.getStockCode());
					factLogDownload.setDescription(returnBean
							.getReturnMessage());
					factLogDownload.setFileUrl(downloadFileBean
							.getRemoteFileUrl());

					try {
						factLogDownloadMapper.insert(factLogDownload);
					} catch (DuplicateKeyException ex) {
						logger.info(ex.getMessage());
					} catch (Exception ex) {
						logger.error(ex);
					}

					if (factLogDownload.getIsSuccess()) {
						FactDownloadFileConfig factDownloadFileConfig = new FactDownloadFileConfig();

						factDownloadFileConfig.setFileName(returnBean
								.getFilename());
						factDownloadFileConfig.setFilePath(downloadFileBean
								.getSavePath());
						factDownloadFileConfig.setStCode(downloadFileBean
								.getStockCode());
						factDownloadFileConfig.setTimeId(downloadFileBean
								.getTimeId());

						try {
							factDownloadFileConfigManager
									.insert(factDownloadFileConfig);
						} catch (DuplicateKeyException ex) {
							logger.info(ex.getMessage());
						} catch (Exception ex) {
							logger.error(ex);
						}
					}

				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}

		{
			FactLogTask logTask = new FactLogTask();

			logTask.setStartTime(startDate);
			logTask.setEndTime(endDate);
			logTask.setStCode(this.stockCode);

			factLogTaskManager.insert(logTask);
		}
	}

	/**
	 * Gets the 休息日.
	 * 
	 * @return the 休息日
	 */
	public List<String> getDaysOff() {
		return daysOff;
	}

	/**
	 * Sets the 休息日.
	 * 
	 * @param daysOff
	 *            the new 休息日
	 */
	public void setDaysOff(List<String> daysOff) {
		this.daysOff = daysOff;
	}

	public static void DownloadData(String startTime, String endTime,
			Integer dimId, List<String> notInValues, boolean isFast) {

		// String startTime = "2010-01-01";
		// String endTime = "2010-12-31";
		// String stCode = "sz300002";

		// DStockExample example = new DStockExample();
		//
		// List<DStock> stockList = dStockManager.selectByExample(example);

		List<DStock> list = dStockManager.selectByDimAndNotIn(dimId,
				notInValues);

		DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
				endTime, list);

		tb.downSTFile(isFast);

	}

	public static void muDownloadData(String startTime, String endTime,
			Integer dimId, List<String> notIdStockCodeValues, int mu,
			boolean isFast) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		Date endDate = null;

		int days = 0;
		try {
			cal.setTime(df.parse(startTime));
			startDate = cal.getTime();

			cal.setTime(df.parse(endTime));
			endDate = cal.getTime();
			days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
			logger.info("days=" + days);

			int pageSize = (int) Math.ceil(days / mu);
			Page pageInfo = new Page(days, pageSize);
			if (logger.isInfoEnabled()) {
				logger.info("Page pageInfo=" + pageInfo);
			}

			String start = null, end = null;
			for (int p = 1; p <= pageInfo.getTotalPage(); p++) {
				pageInfo.setPage(p);

				cal.setTime(df.parse(startTime));
				cal.add(Calendar.DAY_OF_MONTH, pageInfo.getFirstItemPos());

				start = df.format(cal.getTime());

				cal.add(Calendar.DAY_OF_MONTH, pageInfo.getPageSize());

				end = df.format(cal.getTime());

				System.out.println(start + ":" + end + ":" + dimId + ":"
						+ notIdStockCodeValues + ":" + isFast);

				// DownloadData(start, end, dimId, notIdStockCodeValues,
				// isFast);
			}

			// endDate.getTime()-startDate.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public List<DownloadFileBean> getCheckFailDataURL() {
		FactDownloadFileConfigExample example = new FactDownloadFileConfigExample();
		example.setOrderByClause("ST_CODE, TIME_ID");

		FactDownloadFileConfigExample.Criteria c = example.createCriteria();
		c.andFailEqualTo(true);

		if (this.stockCode != null) {
			c.andStCodeEqualTo(this.stockCode);
		}

		if (this.stockList != null) {
			List<String> values = new ArrayList<String>();

			for (DStock dstock : this.stockList) {
				values.add(dstock.getStockTypeCode().toLowerCase()
						+ dstock.getStockCode());
			}

			c.andStCodeIn(values);
		}

		if (this.stockCodes != null) {
			c.andStCodeIn(this.stockCodes);
		}

		List<FactDownloadFileConfig> list = factDownloadFileConfigManager
				.selectByExample(example);

		// String remoteUrl =
		// "http://http://market.finance.sina.com.cn/downxls.php?";
		// String params = "date=2014-01-16&symbol=sz300002";

		List<DownloadFileBean> urlList = new ArrayList<DownloadFileBean>();

		for (FactDownloadFileConfig downloadFile : list) {
			// logger.info(downloadFile);

			DownloadFileBean downloadFileBean = new DownloadFileBean();

			downloadFileBean.setTimeId(downloadFile.getTimeId());
			downloadFileBean.setStockCode(downloadFile.getStCode());
			
			// savePath =
			// LoadConfigUtils.getInstance().getDownloadFilePath() +
			// "/" + this.stockCode + "/" + start.substring(0, 4);
			// String savePath =
			// savePath = "/" + this.stockCode + "/"
			// + start.substring(0, 4);

			downloadFileBean.setSavePath(downloadFile.getFilePath());

			String remoteFileUrl = remoteServiceUrl + "?date="
					+ downloadFileBean.getTimeId() + "&symbol="
					+ downloadFile.getStCode();
			// if (logger.isInfoEnabled()) {
			logger.debug("getUrlList() - String remoteFileUrl=" + remoteFileUrl);
			// }

			downloadFileBean.setRemoteFileUrl(remoteFileUrl);

			urlList.add(downloadFileBean);

		}

		return urlList;
	}

	public void downNioSTFile() {

		List<DownloadFileBean> list = this.getUrlList();

		downNioSTFile(list);
	}

	public void downNioFailSTFile() {

		List<DownloadFileBean> list = this.getCheckFailDataURL();
		if (logger.isInfoEnabled()) {
			logger.info("downNioFailSTFile() - List<DownloadFileBean> list="
					+ list);
		}

		downNioSTFile(list);
	}

	private String baseSavePath = LoadConfigUtils.getInstance()
			.getDownloadFilePath();

	public void downNioSTFile(List<DownloadFileBean> list) {

		// String baseSavePath = LoadConfigUtils.getInstance()
		// .getDownloadFilePath();

		 
		
		NioDownload nioDownload = null;

		for (DownloadFileBean dfBean : list) {
			try {
//				nioDownload = new NioDownload(dfBean.getRemoteFileUrl(),
//						baseSavePath + dfBean.getSavePath(),
//						dfBean.getStockCode() + "_" + dfBean.getTimeId()
//								+ ".xls");
				nioDownload = new NioDownload(dfBean.getRemoteFileUrl(),
						baseSavePath + dfBean.getSavePath(),null);
				
				nioDownload.setStockCode(dfBean.getStockCode());
				
				nioDownload.setDateId(
						Integer.parseInt(dfBean.getTimeId().replaceAll("-", "")));
				nioDownload.setStockType(dfBean.getStockType());
				
				nioDownload.start();
			} catch (Exception ex) {
				logger.warn(dfBean.getStockCode() + "->" + ex);
			}
		}
	}

	public void downFailSTFile() {
		// List<String> daysOff = factDateHolidayListMapper.selectDaysOff(
		// this.startDate, this.endDate);

		List<DownloadFileBean> list = this.getCheckFailDataURL();

		ExecutorService threadPool = new ThreadPoolExecutor(2, 30, 30L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30),
				new ThreadPoolExecutor.DiscardPolicy());

		SyncStack ss = new SyncStack(list);// 建造一个装馒头的框

		// List<FactProxy> proxyList = ProxyUtils.selectProxyTop5();
		// ss.setProxyList(proxyList);

		Producer p = new Producer(ss);// 新建一个生产者，使之持有框
		Consume c = new Consume(ss);// 新建一个消费者，使之持有同一个框

		List<BaseCallable> tasks = new ArrayList<BaseCallable>();

		tasks.add(p);
		tasks.add(c);

		try {
			List<Future<List<ReturnBean>>> results = threadPool
					.invokeAll(tasks);
			// if (logger.isInfoEnabled()) {
			// logger.info("downSTFile() - List results.size="
			// + results.size());
			// }

			for (Future<List<ReturnBean>> future : results) {
				List<ReturnBean> returnBeanList = future.get();

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}

	}

	public List<DStock> getStockList() {
		return stockList;
	}

	public void setStockList(List<DStock> stockList) {
		this.stockList = stockList;
	}

	public String getListingDate() {
		return listingDate;
	}

	public void setListingDate(String listingDate) {
		this.listingDate = listingDate;
	}

	public static void main(String[] args) {
		// ProxyUtils.checkDBProxySpeed();

		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils();
		//
		// tb.downFailSTFile();

		// CheckFailData ();

		// String startTime = "2010-01-01";
		// String endTime = "2010-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		// muDownloadData(startTime, endTime, 1004005, values, 100, true);

		// ProxyUtils.findProxy2DB();
		//
		// ProxyUtils.checkDBProxySpeed();
		//
		// Date d1 = new Date();
		// String startTime = "2010-01-01";
		// String endTime = "2010-05-31";
		// String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, stCode);
		//
		// tb.downSTFile(true);
		// Date d2 = new Date();

		// String startTime = "2010-01-01";
		// String endTime = "2010-12-31";
		// String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, stCode);
		//
		// tb.downSTFile(true);
		// Date d2 = new Date();

		// System.out.println(d2.getTime()-d1.getTime());
		// List<String> values = new ArrayList<String>();
		//
		// for (int i = 300001; i < 300307; i++) {
		// values.add("" + i);
		// }
		//
		// List<DStock> list = dStockManager.selectByDimAndNotIn(1004005,
		// values);
		//
		// for (DStock dstock : list) {
		// logger.info(dstock);
		//
		// {
		// String startTime = "2010-01-01";
		// String endTime = "2010-05-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		//
		// {
		// String startTime = "2010-06-01";
		// String endTime = "2010-12-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		//
		// // ------------------------------//
		// {
		// String startTime = "2011-01-01";
		// String endTime = "2011-05-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// {
		// String startTime = "2011-06-01";
		// String endTime = "2011-12-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// // ------------------------------//
		// {
		// String startTime = "2012-01-01";
		// String endTime = "2012-05-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// {
		// String startTime = "2012-06-01";
		// String endTime = "2012-12-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		//
		// // ------------------------------//
		// {
		// String startTime = "2013-01-01";
		// String endTime = "2013-05-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// {
		// String startTime = "2013-06-01";
		// String endTime = "2013-12-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		//
		// // ------------------------------//
		// {
		// String startTime = "2014-01-01";
		// String endTime = "2014-05-31";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// {
		// String startTime = "2014-06-01";
		// String endTime = "2014-11-21";
		// // String stCode = "sz300003";
		//
		// DownloadSinaDataUtils tb = new DownloadSinaDataUtils(startTime,
		// endTime, dstock);
		//
		// tb.downSTFile(true);
		// logger.info(startTime + "~" + endTime);
		//
		// try {
		// System.out.println("sleep10000");
		// Thread.sleep(10000);
		// tb = null;
		//
		// } catch (InterruptedException e) {
		// }
		// }
		// }

		// String startTime = "2010-01-01";
		// String endTime = "2010-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);
		//
		// String startTime = "2011-01-01";
		// String endTime = "2011-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);

		// String startTime = "2012-01-01";
		// String endTime = "2012-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);

		// String startTime = "2013-01-01";
		// String endTime = "2013-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);

		// String startTime = "2014-01-01";
		// String endTime = "2014-11-21";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);
		// {
		// Thread thread = new Thread() {
		// public void run() {
		// String startTime = "2011-01-01";
		// String endTime = "2011-12-31";
		//
		// List<String> values = new ArrayList<String>();
		// values.add("300001");
		// values.add("300002");
		//
		// DownloadData(startTime, endTime, 1004005, values);
		// }
		// };
		// thread.start();
		// }
		// destroyFactory();
	}

	public List<String> getStockCodes() {
		return stockCodes;
	}

	public void setStockCodes(List<String> stockCodes) {
		this.stockCodes = stockCodes;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public List<String> getSuspensionDays() {
		return suspensionDays;
	}

	public void setSuspensionDays(List<String> suspensionDays) {
		this.suspensionDays = suspensionDays;
	}

}
