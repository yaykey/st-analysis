package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;

import com.st.Global;
import com.st.analysis.utils.download.DownloadFileBean;
import com.st.analysis.utils.download.ReturnBean;
import com.st.analysis.utils.download.observer.NioDownload;
import com.st.analysis.utils.download.thread.BaseCallable;
import com.st.analysis.utils.download.thread.Consume;
import com.st.analysis.utils.download.thread.Producer;
import com.st.analysis.utils.download.thread.SyncStack;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.business.impl.GDetailFileErrorManager;
import com.st.framework.business.impl.GDetailSuspensionManager;
import com.st.framework.business.impl.fact.FactDownloadFileConfigManager;
import com.st.framework.business.impl.fact.FactLogTaskManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.FactLogDownload;
import com.st.framework.module.stock.FactLogTask;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.GDetailFileError;
import com.st.framework.module.stock.GDetailSuspension;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.module.stock.example.GDetailFileErrorExample;
import com.st.framework.module.stock.example.GDetailSuspensionExample;
import com.st.framework.module.stock.example.PStockMapExample;
import com.st.framework.persistence.mapper.stock.FactDateHolidayListMapper;
import com.st.framework.persistence.mapper.stock.FactLogDownloadMapper;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.db.BaseDBUtils;
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
public class DownloadQQDataUtils extends BaseDBUtils {

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

	protected static GDetailFileErrorManager gDetailFileErrorManager = (GDetailFileErrorManager) getHelper()
			.getBean("gDetailFileErrorManager");

	/**
	 * 休息日
	 */
	private List<String> daysOff = new ArrayList<String>();

	/**
	 * 无数据停盘日
	 */
	private List<String> suspensionDays = new ArrayList<String>();

	/**
	 * 成功下载
	 */
	private List<String> successDays = new ArrayList<String>();

	private final static DateFormat DF_YEAR = new SimpleDateFormat("yyyy");

	private final static DateFormat DF_DAY = new SimpleDateFormat("yyyy-MM-dd");

	private final static DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");

	private boolean checkFailFlag = false;

	// sz300002_成交明细_2015-01-05.xls
	// http://quotes.money.163.com/cjmx/2015/20150504/1300002.xls
	// http://stock.gtimg.cn/data/index.php?appn=detail&action=download&c=sz300002&d=20150508
	private static String baseUrl = "http://stock.gtimg.cn/data/index.php?appn=detail&action=download&c=";

	private static String baseSavePath = LoadConfigUtils.getInstance()
			.getDownloadFilePath();

	public void download(Integer stockCode, String stockType) {
		String startTime = factDownloadFileConfigManager
				.selectLastSuccessTimeId("" + stockCode);

		if (startTime == null) {
			if (stockCode >= 3000419) {
				startTime = "2015-02-17";
			} else {
				DStock dstock = dStockManager.selectByPrimaryKey("" + stockCode);
				
				startTime = DF_DAY.format(dstock.getListingDate());
			}
		}
		
//		DStock dstock = dStockManager.selectByPrimaryKey("" + stockCode);		
//		startTime = DF_DAY.format(dstock.getListingDate());

		startTime = "2009-10-30";
		
		Date begin = null;

		try {
			begin = DF_DAY.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		download(stockCode, stockType, begin, null);

	}

	public void checkFailData() {
		{
			GDetailFileErrorExample example = new GDetailFileErrorExample();
			List<GDetailFileError> list = gDetailFileErrorManager
					.selectByExample(example);

			for (final GDetailFileError err : list) {
				// Global.threadPoolExecutor.execute(new Thread() {
				// public void run() {
				Integer stockCode = Integer.parseInt(err.getStockCode()
						.replace("sz", "").replace("SZ", ""));
				String stockType = "sz";
				Date date = null;

				try {
					date = DF_SIMPLE.parse("" + err.getDateId());

					new DownloadQQDataUtils().download(stockCode, stockType,
							date, date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// }
				// });
			}
		}
		{
			GDetailSuspensionExample example = new GDetailSuspensionExample();
			List<GDetailSuspension> list = gDetailSuspensionManager
					.selectByExample(example);

			for (final GDetailSuspension err : list) {
				// Global.threadPoolExecutor.execute(new Thread() {
				// public void run() {
				Integer stockCode = Integer.parseInt(err.getStockCode()
						.replace("sz", "").replace("SZ", ""));
				String stockType = "sz";
				Date date = null;

				try {
					date = DF_SIMPLE.parse("" + err.getDateId());

					new DownloadQQDataUtils().download(stockCode, stockType,
							date, date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// }
				// });
			}
		}
	}

	public void download(Integer stockCode, String stockType, Date begin,
			Date end) {

		List<NioDownload> list = getDownloadList(stockCode, stockType, begin,
				end);

		if (list != null && list.size() > 0) {
			for (final NioDownload nioDownload : list) {
				// System.out.println(nioDownload);
				Global.threadPoolExecutor.execute(new Thread() {
					public void run() {
						nioDownload.start();
					}
				});

			}
		}

	}

	@SuppressWarnings("static-access")
	private List<NioDownload> getDownloadList(Integer stockCode,
			String stockType, Date begin, Date end) {

		if (begin == null) {
			return null;
		}

		if (end == null) {
			end = new Date();
		}

		if (this.daysOff == null || this.daysOff.isEmpty()) {
			this.daysOff = this.factDateHolidayListMapper.selectDaysOff(
					DF_DAY.format(begin), DF_DAY.format(end));
		}

		if (this.suspensionDays == null || this.suspensionDays.isEmpty()) {
			this.suspensionDays = this.gDetailSuspensionManager
					.selectSuspensionDays(DF_SIMPLE.format(begin),
							DF_SIMPLE.format(end), "" + stockCode);

		}

		if (this.successDays == null || this.successDays.isEmpty()) {
			this.successDays = this.factDownloadFileConfigManager
					.selectStSuccessTimeId(DF_DAY.format(begin),
							DF_DAY.format(end), "" + stockCode);
		}

		String filename = "", savePath = "", url = "", year = "", curdate = "", curdateSimple = "";

		Date curDate = begin;
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);

		if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			cal.add(Calendar.DAY_OF_MONTH, 2);
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		curDate = cal.getTime();

		List<NioDownload> dataList = new ArrayList<NioDownload>();

		while (curDate.compareTo(end) <= 0) {
			curdateSimple = DF_SIMPLE.format(curDate);
			curdate = DF_DAY.format(curDate);

			if (!daysOff.contains(curdate) && !suspensionDays.contains(curdate)
					&& !successDays.contains(curdate)) {
				year = DF_YEAR.format(curDate);

				filename = stockType.toLowerCase() + stockCode + "_成交明细_"
						+ curdate + ".xls";

				savePath = baseSavePath + "/" + stockType.toLowerCase()
						+ stockCode + "/" + year + "/";

				// http://quotes.money.163.com/cjmx/2015/20150504/1300002.xls
				// private static String baseUrl =
				// "http://quotes.money.163.com/cjmx/";
				// http://stock.gtimg.cn/data/index.php?appn=detail&action=download&c=sz300002&d=20150508
				url = baseUrl + "" + stockType.toLowerCase() + stockCode
						+ "&d=" + DF_SIMPLE.format(curDate);
				NioDownload nioDownload = new NioDownload(url, savePath,
						filename);
				nioDownload.setDateId(Integer.parseInt(curdateSimple));
				nioDownload.setStockCode("" + stockCode);
				nioDownload.setStockType(stockType);

				dataList.add(nioDownload);
			}

			cal.add(Calendar.DAY_OF_MONTH, 1);

			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}

			curDate = cal.getTime();
		}

		return dataList;
	}

	public List<String> getSuspensionDays() {
		return suspensionDays;
	}

	public void setSuspensionDays(List<String> suspensionDays) {
		this.suspensionDays = suspensionDays;
	}

	public List<String> getSuccessDays() {
		return successDays;
	}

	public void setSuccessDays(List<String> successDays) {
		this.successDays = successDays;
	}

	public static void main(String[] args) {
		// new DownloadQQDataUtils().download(300002, "sz",
		// new Date("2015/05/01"), null);

		new DownloadQQDataUtils().checkFailData();
	}

	public boolean isCheckFailFlag() {
		return checkFailFlag;
	}

	public void setCheckFailFlag(boolean checkFailFlag) {
		this.checkFailFlag = checkFailFlag;
	}
}
