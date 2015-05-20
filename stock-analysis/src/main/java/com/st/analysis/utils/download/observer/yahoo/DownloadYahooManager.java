package com.st.analysis.utils.download.observer.yahoo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.st.analysis.utils.download.observer.NioDownload;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

public class DownloadYahooManager extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(DownloadYahooManager.class);

	protected static GStockDayManager gStockDayManager = (GStockDayManager) getHelper()
			.getBean("gStockDayManager");

	// private String startDate;
	//
	// private String endDate;

	private List<DStock> stockList;

	private String remoteSinaDetailUrl;

	// private String clazz;

	public DownloadYahooManager() {

	}

	public DownloadYahooManager(List<DStock> stockList) {
		this.stockList = stockList;
	}

	// public DownloadYahooManager(String startDate, String endDate) {
	// this.startDate = startDate;
	// this.endDate = endDate;
	//
	//
	// }

	public void startDown() {
		if (logger.isDebugEnabled()) {
			logger.debug("startDown() - start");
		}

		// for (Object obj : stockList) {
		// if (obj instanceof DStock) {
		// this.clazz = DStock.class.getName();
		// System.out.println((DStock) obj);
		// }
		// }

		// http://table.finance.yahoo.com/table.csv?s=300002.sz

		NioDownload nioDownload = null;
		String remoteUrl = "http://table.finance.yahoo.com/table.csv?s=";
		String params = "";

		String baseSavePath = LoadConfigUtils.getInstance()
				.getDownloadFilePath();
		baseSavePath += "/detail";
		for (DStock dstock : this.stockList) {
			try {
				params = dstock.getStockCode() + "."
						+ dstock.getStockTypeCode();
				nioDownload = new NioDownload(remoteUrl + params, baseSavePath,
						dstock.getStockTypeCode() + dstock.getStockCode()
								+ ".csv");
				nioDownload.start();
			} catch (Exception ex) {
				logger.error("startDown() - exception ignored", ex);

			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("startDown() - end");
		}
	}

	public void mThreadDown(List<DStock> stocklist, int m) {

		int pageSize = (int) Math.ceil(stocklist.size() / (double) m);
		Page page = new Page(stocklist.size(), pageSize);

		for (int i = 1; i <= page.getTotalPage(); i++) {
			page.setPage(i);

			DownThread thread = new DownThread(stocklist.subList(
					page.getFirstItemPos(),
					(int) (page.getFirstItemPos() + page.getMaxItemNum())));

			thread.start();

		}

		stocklist = null;
	}

	public synchronized List<DStock> getStockList() {
		return stockList;
	}

	public synchronized void setStockList(List<DStock> stockList) {

		// super.setChanged(); // 设置变化点
		// super.notifyObservers(stockList);// 被改变

		this.stockList = stockList;
	}

	public String getRemoteSinaDetailUrl() {
		return remoteSinaDetailUrl;
	}

	public void setRemoteSinaDetailUrl(String remoteSinaDetailUrl) {
		this.remoteSinaDetailUrl = remoteSinaDetailUrl;
	}

	// public String getClazz() {
	// return clazz;
	// }
	//
	// public void setClazz(String clazz) {
	// this.clazz = clazz;
	// }

	public void YahooData2DB() {
		// gStockDayManager
		// 路径
		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(filePath + "/detail");
		if (!f.exists()) {
			System.out.println(filePath + " not exists");
			return;
		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			// for (int i = 0; i < 2; i++) {
			File fs = fa[i];
			if (f.isHidden() == false && !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {
					System.out.println(fs.getName() + " [目录]");
				} else {
					// System.out.println(fs.getName());

					if (isDetailCSVFile(fs.getName())) {
						DetailCSV2DB(fs);
						fs = null;
					}
				}
			}
		}

		f = null;

		System.gc();
	}

	public static void DetailCSV2DB(File csvFile) {
		Date d1 = new Date();
		// /sz300001.csv

		String fileName = csvFile.getName();

		Integer stockCode = Integer.parseInt(fileName.substring(2, 8));

		String stockCodeType = fileName.substring(0, 2).toUpperCase();

		// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\downloadFile\sz300001\2010\sz300001_成交明细_2010-01-07.xls

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bw = null;

		GStockDay gStockDay = null;

		String line = null;
		String[] temp = null;

		try {
			fis = new FileInputStream(csvFile);
			isr = new InputStreamReader(fis);

			bw = new BufferedReader(isr);

			// 因为不知道有几行数据，所以先存入list集合中
			line = bw.readLine();

			try {
				while ((line = bw.readLine()) != null) {

					line = line.trim();
					if ("".equals(line)) {
						continue;
					}
					// line = line.replaceAll("[\\t]*", " ");
					temp = line.split(",");

					gStockDay = new GStockDay();

					// Date,Open,High,Low,Close,Volume,Adj Close
					// 2014-11-27,21.85,22.17,21.40,21.73,7218600,21.73

					gStockDay.setStock("" + stockCode);
//					protected static DateFormat df_simple = new SimpleDateFormat("yyyyMMdd");

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					gStockDay.setDate(df.parse(temp[0]));

					gStockDay.setOpen(Double.parseDouble(temp[1]));
					gStockDay.setHigh(Double.parseDouble(temp[2]));
					gStockDay.setLow(Double.parseDouble(temp[3]));
					gStockDay.setClose(Double.parseDouble(temp[4]));
					gStockDay.setVolume(Integer.parseInt(temp[5]));
					gStockDay.setAdjClose(Double.parseDouble(temp[6]));

					gStockDayManager.insertBatch(gStockDay);
				}

				gStockDayManager.insertFlushBatch();
			} catch (Exception e) {

				// System.out.println(line);
				logger.error(fileName + "->文件错误", e);

				// 删除数据;

			}

			line = null;
			temp = null;
			stockCode = null;

			// factDownloadFileConfigManager.insertBatch(factDownloadFileConfig);

		} catch (IOException e) {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ex) {
				e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException ex) {
				e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				e.printStackTrace();
			}
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		Date d2 = new Date();
		System.out.println(fileName + "\t" + (d2.getTime() - d1.getTime()));
	}

	public static boolean isDetailCSVFile(String fileName) {

		Pattern pat = Pattern.compile("^[\\w]{2}[\\d]{6}\\.csv$");
		Matcher mat = pat.matcher(fileName);

		return mat.find();
	}

	public static void main(String[] args) {
//		 DownloadYahooManager downloadManager = new DownloadYahooManager();
//		 downloadManager.YahooData2DB();

		// FactProxy proxy = ProxyUtils.selectProxyTopN(5).get(3);
		//
		// ProxyUtils.setProxy(proxy.getProxyIp(), "" + proxy.getProxyPort());
		//
		List<DStock> list = new ArrayList<DStock>();

		for (int i = 300002; i <= 300002; i++) {
			DStock dstock = new DStock();
			dstock.setStockCode("" + i);
			dstock.setStockTypeCode("sz");

			list.add(dstock);
		}

		DownloadYahooManager downloadManager = new DownloadYahooManager();
		downloadManager.mThreadDown(list, 1);
//		downloadManager.YahooData2DB();

		// Thread thread = new Thread () {
		// @Override
		// public void run() {
		//
		// super.run();
		//
		// DownloadYahooManager downloadManager = new
		// DownloadYahooManager(list);
		// downloadManager.startDown();
		// }
		// };

		// thread.start();

	}

}

class DownThread extends Thread {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DownThread.class);

	private List<DStock> stocklist = null;

	public DownThread(List<DStock> stocklist) {
		this.stocklist = stocklist;
	}

	@Override
	public void run() {
		System.out.println("stocklist" + stocklist);

		DownloadYahooManager downloadManager = new DownloadYahooManager(
				stocklist);
		downloadManager.startDown();
	}

	public List<DStock> getStocklist() {
		return stocklist;
	}

	public void setStocklist(List<DStock> stocklist) {
		this.stocklist = stocklist;
	}

}
