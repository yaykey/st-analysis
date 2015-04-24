package com.st.analysis.utils.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.dao.DuplicateKeyException;

import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.business.impl.DStockManager;
import com.st.framework.business.impl.PStockMapManager;
import com.st.framework.business.impl.dim.DDimManager;
import com.st.framework.business.impl.dim.DDimtypeManager;
import com.st.framework.business.impl.fact.FactSinaParamMapManager;
import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.FactSinaParamMap;
import com.st.framework.module.stock.example.FactSinaParamMapExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.page.Page;

public class FindStockUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(FindStockUtils.class);

	private static DStockManager dStockManager = (DStockManager) getHelper()
	.getBean("dStockManager");

	private static FactSinaParamMapManager factSinaParamMapManager = (FactSinaParamMapManager) getHelper()
			.getBean("factSinaParamMapManager");

	public static void validityData() {

		FactSinaParamMapExample example = new FactSinaParamMapExample();

		// example.setOrderByClause("s_i, s_a, s_c, s_t");
		example.setOrderByClause("ID asc");

		FactSinaParamMapExample.Criteria c = example.createCriteria();
		c.andIsValidityIsNull();
		c.andIdGreaterThan(931392);

		Page page = factSinaParamMapManager.selectPageByExample(example, 50);

		List<FactSinaParamMap> paramList = null;

		Date d1 = null;
		Date d2 = null;

		logger.info("getTotalPage=" + page.getTotalPage());
		// http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=diyu_1100&s_c=&s_t=sz_a&s_z=
		String htmlUrl = "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?num=60&";
		// VALIDITY USE
		// s_i=&s_a=&s_c=&s_t=&s_z=&p=1";
		String param = "";

		Document doc = null;
		Element el = null, pgs = null, tbodyEl = null;

		int p;

		int pointer = 0;

		int sleepTime = 10;

		for (int i = 0; i <= page.getTotalPage(); i++) {
			// for (int i=1000; i<=1; i++) {

			pointer++;

			if (pointer >= 10) {
				try {
					pointer = 0;
					logger.info("休眠" + sleepTime);
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					logger.error(e);
					throw new RuntimeException(e);
				}
			}

			page.setPage(i);
			example.setPage(page);

			d1 = new Date();
			paramList = factSinaParamMapManager
					.selectOptimizeByExample(example);
			// paramList = factSinaParamMapManager.selectByExample(example);

			for (FactSinaParamMap paramMap : paramList) {
				param = "";

				param += "s_i=";
				if (paramMap.getsI() != null) {
					param += paramMap.getsI();
				}

				param += "&s_a=";
				if (paramMap.getsA() != null) {
					param += paramMap.getsA();
				}

				param += "&s_c=";
				if (paramMap.getsC() != null) {
					param += paramMap.getsC();
				}

				param += "&s_t=";
				if (paramMap.getsT() != null) {
					param += paramMap.getsT();
				}

				try {

					// logger.info(htmlUrl + param);
					// ProxyUtils.setRandomProxy();
					doc = Jsoup.connect(htmlUrl + param).post();

					Thread.sleep(1);

				} catch (SocketTimeoutException e) {

					// throw new RuntimeException("获得代理服务器失败", e);

					logger.warn("获得服务器失败", e);

					continue;
				} catch (InterruptedException e) {
					logger.error(e);
					continue;
				} catch (IOException e) {
					logger.error(e);
					continue;
				}

				el = doc.getElementById("dataTable");

				paramMap.setUpdDate(null);

				// logger.info("tr.size=" +
				// el.select("tbody").select("tr").size());

				if (el.select("tbody").select("tr").size() == 0) {
					paramMap.setIsValidity(false);
				} else {
					paramMap.setIsValidity(true);
					paramMap.setP(1);

					// if (el.select("tbody").select("tr").size() >= 60) {
					// logger.info(htmlUrl + param);
					// }

					pgs = doc.getElementsByClass("pages").first();

					if (pgs != null) {
						if (pgs.getElementsByClass("page").size() >= 3) {
							p = pgs.getElementsByClass("page").size() - 2;

							// logger.info("p=" + p);
							FactSinaParamMap cloneParamMap = paramMap.clone();
							cloneParamMap.setIsValidity(true);
							// if (logger.isInfoEnabled()) {
							// logger.info("validityData() - FactSinaParamMap cloneParamMap="
							// + cloneParamMap);
							// }

							for (int j = 2; j <= p; j++) {
								cloneParamMap.setP(j);
								cloneParamMap.setId(null);

								// if (logger.isInfoEnabled()) {
								// logger.info("cloneParamMap="
								// + cloneParamMap);
								// }
								try {
									factSinaParamMapManager
											.insert(cloneParamMap);
								} catch (Exception e) {
									logger.error(e);
								}
							}

						}
					}

				}

				// logger.info("paramMap=" + paramMap);

				factSinaParamMapManager.updateByPrimaryKey(paramMap);

			}

			d2 = new Date();

			logger.info("耗时:" + (d2.getTime() - d1.getTime()));
		}

	}

	public static void findSinaSTType2DB() {
		if (logger.isDebugEnabled()) {
			logger.debug("findSTType2DB() - start");
		}

		// List<String> urlList = new ArrayList();

		// String htmlUrl =
		// "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?";
		// VALIDITY USE
		// s_i=&s_a=&s_c=&s_t=&s_z=&p=1";
		// String param = "";

		// List<DDimtype> dimtypeList = dDimtypeManager.selectAll();

		// List<DDim> siList = dDimManager.selectSinaSIDimAndDimIdNotIn();
		// List<Integer> addList = new ArrayList<Integer>();
		// addList.add(1001011);
		// addList.add(1001048);

		// List<DDim> siList = dDimManager.selectAddSinaSIDim(addList);

		List<DDim> siList = dDimManager.selectByDimtypeId(1001);
		List<DDim> saList = dDimManager.selectByDimtypeId(1002);
		List<DDim> scList = dDimManager.selectByDimtypeId(1003);
		List<DDim> stList = dDimManager.selectByDimtypeId(1004);

		FactSinaParamMap factSinaParamMap = null;

		List<FactSinaParamMap> batchList = new ArrayList<FactSinaParamMap>();

		int batchMax = 1000;
		int i = 0;

		Date d1 = new Date();
		Date d2 = null;

		for (DDim siDim : siList) {

			logger.info("si=" + siDim);

			for (DDim saDim : saList) {
				for (DDim scDim : scList) {
					for (DDim stDim : stList) {
						for (int j = 0; j < 4; j++) {
							factSinaParamMap = new FactSinaParamMap();

							if (j == 0) {
								factSinaParamMap.setsI(siDim.getDimCode());
							} else if (j == 1) {
								factSinaParamMap.setsA(saDim.getDimCode());
							} else if (j == 2) {
								factSinaParamMap.setsC(scDim.getDimCode());
							} else if (j == 3) {
								factSinaParamMap.setsT(stDim.getDimCode());
							}

							i++;
							batchList.add(factSinaParamMap);

							try {
								if (i == batchMax) {
									factSinaParamMapManager
											.insertBatch(batchList);
									i = 0;
									batchList.clear();
									// d2 = new Date();
									// logger.info(batchMax + "耗时="
									// + (d2.getTime() - d1.getTime()));
									// d1 = new Date();
								}

								// factSinaParamMapManager.insert(factSinaParamMap);
							} catch (DuplicateKeyException ex) {
								logger.warn(ex);
								i = 0;
								batchList.clear();

							} catch (Exception ex) {
								logger.error(ex);
								throw new RuntimeException(ex);
							}
						}
					}
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findSTType2DB() - end");
		}
	}
	
	//private static String htmlUrlSZA = "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sz_a&s_z=&p=1&num=5000";

	public static void findSinaNewStock2DB () {
		if (logger.isDebugEnabled()) {
			logger.debug("findSinaNewStock2DB() - start");
		}

		String [] htmlUrlSZ = 
			{
				"http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sz_a&s_z=&p=1&num=1000",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sz_a&s_z=&p=2&num=1000",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sz_b&s_z=&p=1&num=1000"
			};
		
		String [] htmlUrlSH = 
		{
			"http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sh_a&s_z=&num=1000",
			"http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/qgqp/index.phtml?s_i=&s_a=&s_c=&s_t=sh_b&s_z=&num=1000"
		};
		
		for (String sinaHtml : htmlUrlSZ) {
			parseSinaHtml(sinaHtml, "sz");
		}
		
		for (String sinaHtml : htmlUrlSH) {
			parseSinaHtml(sinaHtml, "sh");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findSinaNewStock2DB() - end");
		}
	}
	
	
	
	private static void parseSinaHtml (String sinaHtml, String stockType) {
		if (logger.isDebugEnabled()) {
			logger.debug("parseSinaHtml(String, String) - start");
		}

		Document doc;
		try {
			doc = Jsoup.connect(sinaHtml).get();
		} catch (IOException e) {
			logger.error("findSina()", e);

			throw new RuntimeException("获得代理服务器失败", e);
		}
		
		Elements trs = doc.getElementById("dataTable").getElementsByTag("tbody").first().getElementsByTag("tr");
		int i=0;
		for (Element tr : trs) {
			Elements tds = tr.getElementsByTag("td");
			String stockCode = tds.get(0).getElementsByTag("a").first().text();

			String stName = tds.get(1).getElementsByTag("a").first().text();

			i++;
			//logger.info(i + ":" + stockCode + stName);
			
			DStock stock = dStockManager.selectByPrimaryKey(stockCode);
			
			if (stock == null) {
				stock = new DStock();
				stock.setStockCode(stockCode);
				stock.setStockName(stName);
				stock.setStockTypeCode(stockType);
				
				dStockManager.insert(stock);
				if (logger.isInfoEnabled()) {
					logger.info("parseSinaHtml(String, String) - DStock stock=" + stock);
				}
			}
			
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findSina() - end");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start");
		}

		//findSinaSTType2DB();
		findSinaNewStock2DB();
		//validityData();

		//findSinaSZA();
		
		//destroyFactory();

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end");
		}
	}

}
