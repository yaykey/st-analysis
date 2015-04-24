package com.st.analysis.utils.network;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;

import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.example.FactProxyExample;
import com.st.framework.utils.ConfigUtil;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.network.TelnetUtil;
import com.st.framework.utils.page.Page;

/**
 * 比较器类
 * 
 * @author yzy
 * 
 */
class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(MapValueComparator.class);

	@Override
	public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {
		return me1.getValue().compareTo(me2.getValue());
	}
}

public class ProxyUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ProxyUtils.class);

	public static List<String> findProxyList() {
		return findProxyList(null);
	}
	
	

	public static List<String> findProxyList(String type) {

		// http://cn-proxy.com/
		String htmlUrl = "";
		
		if ("kr".equals(type)) {
			htmlUrl = "http://pachong.org/area/short/name/kr.html";
			
		} else if ("cn".equals(type)) {
			htmlUrl = "http://pachong.org/area/short/name/cn.html";
		} else if ("us".equals(type)) {
			htmlUrl = "http://pachong.org/area/short/name/us.html";
		} else if ("in".equals(type)) {
			htmlUrl = "http://pachong.org/area/short/name/in.html";
		} else {
			htmlUrl = "http://cn-proxy.com/";
		}
		// String htmlUrl = "http://cn-proxy.com/";

		//int bat,chick,goat,cat,fish;
		
		Document doc;
		try {
			//doc = Jsoup.connect(htmlUrl).get();
			doc =  Jsoup.parse(new URL(htmlUrl), 50000);
		} catch (IOException e) {
			throw new RuntimeException("获得代理服务器失败", e);
		}
		
		Elements scs = doc.getElementsByTag("script");
		
		Map<String, Integer> mapToken = new HashMap<String, Integer>();
		String strTmp = "";
		for (Element sc : scs) {
			strTmp = sc.outerHtml()
				.replaceAll("<(/)?script( type=\"text/javascript\")?( src=.*)?>", "")
				.replaceAll("<script async.*", "");
				
			
			//var snake=6239+6338;var calf=6395+4444^snake;var dog=1153+8060^calf;var worm=8573+3432^dog;var rat=8214+1140^worm;

			if (strTmp != null && !strTmp.isEmpty()) {
				String regParams = "var [\\w]+=[\\d]+\\+[\\d]+;var [\\w]+=[\\d]+\\+[\\d]+\\^[\\w]+;.*";
				Pattern pattern = Pattern.compile(regParams);
				Matcher matcher = pattern.matcher(strTmp);
				
				if (matcher.find()) {
//					System.out.println("---------------------------");
//					System.out.println(strTmp);
//					System.out.println("---------------------------");
					break;
				}
			}			
		}
		
		String [] arrs = strTmp.replaceAll("var ", "").split(";");
		if (logger.isInfoEnabled()) {
			logger.info("findProxyList(String) - String[] arrs=" + arrs.length);
		}
		if (arrs.length>1) {
			String reg1 = "([\\w]+)=([\\d]+)\\+([\\d]+)";
			String reg2 = "([\\w]+)=([\\d]+)\\+([\\d]+)\\^([\\w]+)";
			{
				Pattern pattern = Pattern.compile(reg1);
				Matcher matcher = pattern.matcher(arrs[0]);
				
				if (matcher.find()) {
//					for (int i=0; i<=matcher.groupCount(); i++) {
//						
//						System.out.println(matcher.group(i));
//					}
					mapToken.put(matcher.group(1), Integer.parseInt(matcher.group(2)) + Integer.parseInt(matcher.group(3)));
				}
				
			}
			
			for (int i=1; i<arrs.length; i++) {
				Pattern pattern = Pattern.compile(reg2);
				Matcher matcher = pattern.matcher(arrs[i]);
				
				if (matcher.find()) {
//					for (int i=0; i<=matcher.groupCount(); i++) {
//						
//						System.out.println(matcher.group(i));
//					}
					mapToken.put(matcher.group(1), 
							Integer.parseInt(matcher.group(2)) + 
							Integer.parseInt(matcher.group(3))^mapToken.get(matcher.group(4))
					);
				}
			}
		}
		
		
		
		//System.out.println(mapToken);

		doc.getElementsByTag("head").remove();
		//doc.getElementsByTag("script").remove();
		try {
			doc.getElementById("header").remove();
		} catch (Exception ex) {
		}
		try {
			doc.getElementById("footer").remove();
		} catch (Exception ex) {
		}
		try {
			doc.getElementsByTag("thead").remove();
		} catch (Exception ex) {
		}
		try {
			doc.getElementsByTag("tfoot").remove();
		} catch (Exception ex) {
		}

		// logger.info(el);

		Elements trs = doc.getElementsByClass("tb").select("tbody")
				.select("tr");

		Element tr = null;
		String mapVal = "";
		String reg3 = "\\(([\\d]+)\\^([\\w]+)\\)\\+([\\d]+)";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < trs.size(); i++) {
			tr = trs.get(i);

			mapVal = tr.child(1).text() + ",";
			
			//mapVal += tr.child(2).outerHtml() + ",";
			
			String portStr = tr.child(2).outerHtml()
				.replaceAll("<(/)?(td|script)>", "")
				.replaceAll("document\\.write\\(", "")
				.replaceAll("\\);", "");
			//System.out.println("portStr=" + portStr);
			Pattern pattern = Pattern.compile(reg3);
			Matcher matcher = pattern.matcher(portStr);
			
			if (matcher.find()) {
				//for (int j=0; i<matcher.groupCount()-; j++) {
					
					//System.out.println(matcher.group());
					//System.out.println(matcher.group(0));
//					System.out.println(matcher.group(1));
//					System.out.println(matcher.group(2));
//					System.out.println(matcher.group(3));
//				//}
//					System.out.println("---------------");
//				System.out.println(matcher.group(1));
//				System.out.println(mapToken.get(matcher.group(2)));
//				System.out.println(matcher.group(3));
//				System.out.println("---------------");
				
				mapVal += 
					((Integer.parseInt(matcher.group(1))^mapToken.get(matcher.group(2)))
						+ Integer.parseInt(matcher.group(3))) + ",";
			}
			
			//mapVal += portStr + ",";
			
			mapVal += tr.child(3).text().trim() + ",";
			mapVal += tr.child(4).text().trim() + ",";
			mapVal += tr.child(5).text().trim();
			// if (logger.isInfoEnabled()) {
			// logger.info("findProxyList() - String mapVal=" + mapVal);
			// }

			list.add(mapVal);
		}

		return list;
	}

	/**
	 * 
	 * 找到测试过的代理服务器
	 * 
	 * @param connectTimeout
	 * @return
	 */
	public static Map<String, Integer> findProxyMapTest(List<String> proxyList,
			Integer connectTimeout) {

		if (proxyList == null) {
			proxyList = findProxyList();
		}

		String[] tempProxy = null;
		Date date1 = null, date2 = null;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		for (String proxy : proxyList) {
			tempProxy = proxy.split(",");

			try {
				date1 = new Date();
				TelnetUtil telnetTest = new TelnetUtil(tempProxy[0],
						Integer.parseInt(tempProxy[1]), connectTimeout);

				telnetTest.disconnect();
				// System.out.println(tr.text() + " "
				// + (date2.getTime() - date1.getTime()));

				setProxy(tempProxy[0], tempProxy[1]);
				testProxy(connectTimeout);
				date2 = new Date();

				map.put(proxy, (int) (date2.getTime() - date1.getTime()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		map = sortMapByValue(map);

		return map;

	}

	/**
	 * 
	 * 找到测试过的代理服务器
	 * 
	 * @param connectTimeout
	 * @return
	 */
	public static Map<String, Integer> findProxyMapTest(Integer connectTimeout, String type) {

		List<String> list = findProxyList(type);

		String[] tempProxy = null;

		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		int speed = -1;

		for (String proxy : list) {
			tempProxy = proxy.split(",");

			try {
				speed = testProxySpeed(tempProxy[0], tempProxy[1],
						connectTimeout);
				// if (logger.isInfoEnabled()) {
				// logger.info("findProxyMapTest(Integer) - int speed=" +
				// speed);
				// }
			} catch (RuntimeException ex) {
				logger.warn(ex.getMessage());
				continue;
			} catch (Exception ex) {
				ex.printStackTrace();
				continue;
			}

			map.put(proxy, speed);
		}
		map = sortMapByValue(map);

		return map;

	}

	/**
	 * 
	 * 测试代理速度;
	 * 
	 * @param proxyIp
	 * @param proxyPort
	 * @param connectTimeout
	 * @return
	 * @throws Exception
	 */
	public static Integer testProxySpeed(String proxyIp, String proxyPort,
			Integer connectTimeout) throws Exception {

		Date date1 = null, date2 = null;

		date1 = new Date();
		TelnetUtil telnetTest = new TelnetUtil(proxyIp,
				Integer.parseInt(proxyPort), connectTimeout);

		telnetTest.disconnect();

		setProxy(proxyIp, proxyPort);
		// try {
		testProxy(connectTimeout);
		// } catch (RuntimeException e) {
		// logger.warn(e.getMessage());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		date2 = new Date();

		Integer returnInteger = (int) (date2.getTime() - date1.getTime());

		return returnInteger;
	}

//	/**
//	 * @param args
//	 * @throws IOException
//	 */
//	public static Map<String, Integer> findProxyMapTest() {
//		return findProxyMapTest(null);
//	}

	public static List<String> findProxyListTest(Integer connectTimeout, String type) {
		Map<String, Integer> map = findProxyMapTest(connectTimeout, type);
		if (logger.isInfoEnabled()) {
			logger.info("findProxyListTest(Integer) - Map<String,Integer> map="
					+ map);
		}

		if (map == null) {
			return null;
		}

		List<String> list = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			// System.out.println(entry.getKey() + "--->" + entry.getValue());
			try {
				list.add(entry.getKey() + "," + entry.getValue());
			} catch (NullPointerException ex) {
				logger.warn(ex);
			}
		}

		return list;
	}

	public static List<String> findProxyListTest() {
		return findProxyListTest(null, null);
	}

	/**
	 * 使用 Map按value进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());
		Collections.sort(entryList, new MapValueComparator());
		Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
		Map.Entry<String, Integer> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}

	/**
	 * 
	 * find代理,经过测速后保存.
	 * 
	 * 默认超时时间200.
	 * 
	 */
	public static void findProxy2DB(Integer connectTimeout, String type) {
		List<String> list = ProxyUtils.findProxyListTest(connectTimeout, type);
		if (logger.isInfoEnabled()) {
			logger.info("main(String[]) - List<String> list=" + list);
		}

		if (list == null) {
			return;
		}

		String[] tempProxy = null;

		FactProxy factProxy = null;

		for (String proxy : list) {
			tempProxy = proxy.split(",");

			factProxy = new FactProxy();

			factProxy.setProxyIp(tempProxy[0]);
			factProxy.setProxyPort(Integer.parseInt(tempProxy[1]));
			factProxy.setLocal(tempProxy[2]);

			factProxy.setActive(true);

			// try {
			// //factProxy.setCreateDate(Global.df.parse(tempProxy[3]));
			// } catch (ParseException e) {
			// throw new RuntimeException("时间解析异常", e);
			// }

			factProxy.setTestDate(new Date());
			//factProxy.setTestSpeed(Integer.parseInt(tempProxy[4]));

			try {
				factProxyManager.saveOrUpdate(factProxy);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("Proxy保存异常", ex);
			}
		}
	}

	public static void setProxy(String host, String port) {
		if (logger.isDebugEnabled()) {
			logger.debug("setProxy(String, String) - start");
		}
		logger.info("host=" + host + ":port=" + port);
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);

		if (logger.isDebugEnabled()) {
			logger.debug("setProxy(String, String) - end");
		}
	}

	/**
	 * 
	 */
	public static void destroyFactory() {
		ConfigUtil.destroyFactory();
	}

	/**
	 * 测试代理有效性及速度
	 * 
	 * @param connectTimeout
	 */
	private static void testProxy(Integer connectTimeout) {

		try {
			// String testUrl = "http://www.baidu.com";
			//String testUrl = "http://market.finance.sina.com.cn/downxls.php?date=2014-01-02&symbol=sz300002";
			String testUrl = "http://www.google.com.hk";

			URL u = new URL(testUrl);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();

			if (connectTimeout != null) {
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(connectTimeout);
			}

			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());

			byte[] buff = new byte[1024];

			@SuppressWarnings("unused")
			int bytesRead;

			while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {

				// raf.seek(this.offset);

				// raf.write(buff, 0, bytesRead);

				// this.offset = this.offset + bytesRead;

				// System.out.println(new String(buff, 0, bytesRead));

			}

			bis.close();

		} catch (SocketTimeoutException ex) {
			throw new RuntimeException("代理测速超时", ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("连接异常", ex);
		}

	}

	/**
	 * 
	 * 获得速度最快的top5
	 * 
	 * @return
	 */
	public static List<FactProxy> selectProxyTop5() {

		return selectProxyTopN(5);
	}

	/**
	 * 
	 * topN
	 * 
	 * @param topNum
	 * @return
	 */
	public static List<FactProxy> selectProxyTopN(int topNum) {
		FactProxyExample example = new FactProxyExample();

		example.setDistinct(true);
		example.setOrderByClause("TEST_SPEED");

		example.setStart(0);
		example.setPageSize(topNum);

		FactProxyExample.Criteria criteria = example.createCriteria();

		criteria.andActiveEqualTo(true);
		// criteria.andTestSpeedLessThan(100);

		return factProxyManager.selectByExample(example);
	}

	private static List<FactProxy> topNList = null;

	/**
	 * 
	 * @param num
	 */
	public static void setRandomProxy(int num) {
		Random random = new Random();

		if (topNList == null || (topNList != null && topNList.size() != num)) {
			topNList = selectProxyTopN(num);
		}

		FactProxy factProxy = topNList.get(random.nextInt(num));

		setProxy(factProxy.getProxyIp(), "" + factProxy.getProxyPort());
	}

	/**
	 * 
	 */
	public static void setRandomProxy() {
		setRandomProxy(5);
	}

	public static void checkDBProxySpeed() {

		FactProxyExample example = new FactProxyExample();

		example.setOrderByClause("PROXY_IP, PROXY_PORT");

		Page page = factProxyManager.selectPageByExample(example, 10);
		example.setPage(page);

		List<FactProxy> list = null;

		int speed = -1;

		for (int i = 1; i <= page.getTotalPage(); i++) {
			page.setPage(i);
			example.setPage(page);
			list = factProxyManager.selectByExample(example);

			// if (logger.isInfoEnabled()) {
			// logger.info(list);
			// }

			for (FactProxy factProxy : list) {
				try {
					speed = testProxySpeed(factProxy.getProxyIp(), ""
							+ factProxy.getProxyPort(), 1000);

					factProxy.setTestDate(new Date());
					factProxy.setActive(true);
					factProxy.setTestSpeed(speed);

					int loseFactor = factProxy.getLoseFactor();
					loseFactor--;

					if (loseFactor < 0) {
						loseFactor = 0;
					}

					factProxy.setLoseFactor(loseFactor);

					factProxyManager.updateByPrimaryKeySelective(factProxy);

				} catch (Exception e) {

					logger.warn("测速失败 " + e.getMessage() + factProxy.toString());

					factProxy.setTestDate(new Date());
					factProxy.setActive(false);

					int loseFactor = factProxy.getLoseFactor();
					loseFactor++;
					factProxy.setLoseFactor(loseFactor);

					factProxyManager.updateByPrimaryKeySelective(factProxy);
				}
			}

		}

	}

	public static void main(String[] args) throws ParseException {

//		String[] locs = {"", "cn","kr","us","in"};
//		
//		for (String loc : locs) {
//			List<String> list = findProxyList(loc);
//			if (logger.isInfoEnabled()) {
//				logger.info("main(String[]) - List<FactProxy> list=" + list);
//			}
//		}
		
		
		//findProxy2DB(500, "in");

//		 checkDBProxySpeed();

//		List<FactProxy> factProxylist = selectProxyTop5();
		List<FactProxy> factProxylist = selectProxyTopN(50);
		if (logger.isInfoEnabled()) {
			logger.info("factProxylist=" + factProxylist);
		}
		
		for (FactProxy factProxy : factProxylist) {
			System.out.println(factProxy.getLocal() + " " + factProxy.getProxyIp() + " " + factProxy.getProxyPort());
		}

		// destroyFactory();

	}
}
