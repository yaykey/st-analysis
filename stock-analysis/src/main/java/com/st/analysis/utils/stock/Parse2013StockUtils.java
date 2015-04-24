package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.dao.DuplicateKeyException;

import com.st.framework.business.impl.DStockManager;
import com.st.framework.business.impl.PStockMapManager;
import com.st.framework.business.impl.dim.DDimManager;
import com.st.framework.business.impl.dim.DDimtypeManager;
import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.DDimtype;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.utils.db.BaseDBUtils;

public class Parse2013StockUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(Parse2013StockUtils.class);

	

	public static void parse2013() {

		Document doc = null;
		try {
			File in = new File("a.html");
			doc = Jsoup.parse(in, "UTF-8", "");

			// if (logger.isInfoEnabled()) {
			// logger.info("parse2013() - Document doc=" + doc);
			// }

			Element el = doc.getElementsByClass("MsoNormalTable").get(0);

			Elements trs = el.getElementsByTag("tr");
			Element tr = null;
			Elements tds = null;

			logger.info("trs.size()=" + trs.size());

			StringBuffer buffer = new StringBuffer();

			Map<String, String> typeLevel1 = new HashMap();

			int ln = trs.size();
			for (int i = 0; i < ln; i++) {
				// for (int i=1; i<5; i++) {
				tr = trs.get(i);
				// System.out.println(tr.toString());
				// System.out.println();

				for (Element td : tr.getElementsByTag("td")) {
					buffer.append(td.text().replaceAll(" ", "")
							.replaceAll(" ", ""));
					buffer.append(',');
				}

				buffer.append('\r');

			}

			System.out.println(buffer.toString());

		} catch (IOException e) {

			throw new RuntimeException("获得代理服务器失败", e);
		}
	}

	public static void parse2013File() {

		try {
			FileInputStream fis = new FileInputStream("st.txt");
			InputStreamReader isr = new InputStreamReader(fis, "GBK");

			BufferedReader bw = new BufferedReader(isr);
			String line = null;
			// 因为不知道有几行数据，所以先存入list集合中
			line = bw.readLine();

			// System.out.println(line);

			String[] temp = null;

			DDimtype dimtype = new DDimtype();
			DDim dim = new DDim();

			dimtype.setDimtypeId(2000);
			int dimtypeId = 0, dimId = 0;
			String dimtypeName = null;

			while ((line = bw.readLine()) != null) {

				System.out.println(line);

				temp = line.split(",");

				if (temp.length == 5) {// 农、林、牧、渔业(A),01,农业,000998,隆平高科
					DDimtype dimtypeClone = dimtype.clone();
					dimtypeId = dimtypeClone.getDimtypeId();
					dimtypeId++;
					dimtypeClone.setDimtypeId(dimtypeId);
					dimtypeClone.setDimtypeName(temp[0]);
					dimtypeClone.setDimtypeCode(temp[0].substring(
							temp[0].length() - 2, temp[0].length() - 1));
					dimtype.setDimtypeId(dimtypeId);

					dimtypeName = dimtypeClone.getDimtypeName();
					//System.out.println("dimtypeName=" + dimtypeName);
					//System.out.println("dimtypeClone=" + dimtypeClone);
					try {
						dDimtypeManager.insert(dimtypeClone);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}

					DDim dimClone = dim.clone();
					dimId = dimtypeId * 1000;
					dimId++;
					dimClone.setDimId(dimId);
					dimClone.setDimCode(temp[1]);
					dimClone.setDimName(temp[2]);
					dimClone.setDimtypeId(dimtypeId);
					dimClone.setDimtypeName(dimtypeClone.getDimtypeName());

					//System.out.println("dimClone=" + dimClone);
					try {
						dDimManager.insert(dimClone);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}

					DStock stock = new DStock();
					stock.setStockCode(temp[3]);
					stock.setStockName(temp[4]);

					try {
						dStockManager.insert(stock);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
					// System.out.println("stock=" + stock);

					PStockMapKey stockMap = new PStockMapKey();
					stockMap.setDimId(dimId);
					stockMap.setStockCode(stock.getStockCode());

					try {
						pStockMapManager.insert(stockMap);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
					 //System.out.println(line);
				} else if (temp.length == 2) { // 002041,登海种业
					//System.out.println(line);
					
					DStock stock = new DStock();
					stock.setStockCode(temp[0]);
					stock.setStockName(temp[1]);
					try {
						dStockManager.insert(stock);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
					// System.out.println("stock=" + stock);

					// System.out.println(line);

					PStockMapKey stockMap = new PStockMapKey();
					stockMap.setDimId(dimId);
					stockMap.setStockCode(stock.getStockCode());

					try {
						pStockMapManager.insert(stockMap);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
				} else if (temp.length == 4) {// 03,畜牧业,000735,罗牛山
					//System.out.println("dimtypeName" + dimtypeName);

					//System.out.println(line);

					dimId++;

					DDim newDim = new DDim();
					newDim.setDimtypeId(dimtypeId);
					newDim.setDimtypeName(dimtypeName);

					newDim.setDimId(dimId);
					newDim.setDimCode(temp[0]);
					newDim.setDimName(temp[1]);

					//System.out.println("newDim=" + newDim);
					try {
						dDimManager.insert(newDim);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}

					DStock stock = new DStock();
					stock.setStockCode(temp[2]);
					stock.setStockName(temp[3]);

					try {
						dStockManager.insert(stock);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
					// System.out.println("stock=" + stock);

					PStockMapKey stockMap = new PStockMapKey();
					stockMap.setDimId(dimId);
					stockMap.setStockCode(stock.getStockCode());

					try {
						pStockMapManager.insert(stockMap);
					} catch (DuplicateKeyException ex) {
						logger.debug(ex.getMessage());
					}
				} else {
					System.out.println(line);
				}

			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			parse2013File();
		} catch (DuplicateKeyException ex) {
			logger.debug(ex.getMessage());
		}
	}
}
