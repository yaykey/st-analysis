package com.st.analysis.utils.stock.download;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;

import com.st.analysis.utils.DateUtils;
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
public class Download163DataUtils extends BaseDBUtils {

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

	private final static DateFormat DF_YEAR = new SimpleDateFormat("yyyy");

	private final static DateFormat DF_DAY = new SimpleDateFormat("yyyy-MM-dd");

	private final static DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");

	

	// sz300002_成交明细_2015-01-05.xls
	// http://quotes.money.163.com/cjmx/2015/20150504/1300002.xls
	private static String baseUrl = "http://quotes.money.163.com/cjmx/";
	
	//http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150518&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER
	
	
	//http://quotes.money.163.com/service/chddata.html?
	//code=0000001&start=19901219&end=20150518&
	//fields=
	//DATEID;ICODE,INAME;TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER
	//日期,股票代码,名称,收盘价,最高价,最低价,开盘价,前收盘,涨跌额,涨跌幅,成交量,成交金额
	//日期 	开盘价 	最高价 	最低价 	收盘价 	涨跌额 	涨跌幅(%) 	成交量(股) 	成交金额(元)
	private static String baseSavePath = LoadConfigUtils.getInstance()
			.getDownloadFilePath();

	
	
	public void download(Integer stockCode,
			String stockType, Date begin, Date end) {
		
		 List<NioDownload> list = getDownloadList(stockCode,
					stockType, begin, end);
		 
		 if (list != null && list.size() > 0) {
			 for (NioDownload nioDownload : list) {
				 System.out.println(nioDownload);
				 nioDownload.start();
			 }
		 }
		
	}
	
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

//		if (this.suspensionDays == null || this.suspensionDays.isEmpty()) {
//			this.suspensionDays = this.gDetailSuspensionManager
//					.selectSuspensionDays(DF_SIMPLE.format(begin),
//							DF_SIMPLE.format(end), "" + stockCode);
//
//		}

//		if (this.successDays == null || this.successDays.isEmpty()) {
//			this.successDays = this.factDownloadFileConfigManager
//					.selectStSuccessTimeId(DF_SIMPLE.format(begin),
//							DF_SIMPLE.format(end), "" + stockCode);
//		}

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

			if (!daysOff.contains(curdate)
					&& !suspensionDays.contains(curdateSimple)
					&& !successDays.contains(curdateSimple)) {
				year = DF_YEAR.format(curDate);
				

				filename = stockType.toLowerCase() + stockCode + "_成交明细_"
						+ curdate + ".xls";
				
				savePath = baseSavePath + "/" + stockType.toLowerCase()
						+ DateUtils.frontCompWithZore(stockCode, 6) + "/" + year + "/";

				// http://quotes.money.163.com/cjmx/2015/20150504/1300002.xls
				// private static String baseUrl =
				// "http://quotes.money.163.com/cjmx/";
				url = baseUrl + "" + year + "/" + DF_SIMPLE.format(curDate)
						+ "/1" + stockCode + ".xls";
				NioDownload nioDownload = new NioDownload(url, savePath, filename);
				nioDownload.setTranslateXlsFlag(true);
				
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
	
	@SuppressWarnings("deprecation")
	public static void main (String [] args) {
//		new Download163DataUtils().download(300001, "sz", new Date("2014/01/01"), new Date("2014/01/31"));
		new Download163DataUtils().download(000001, "sz", new Date("2014/01/01"), new Date("2014/01/31"));
	}
}
