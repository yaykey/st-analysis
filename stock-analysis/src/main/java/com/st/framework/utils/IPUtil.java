package com.st.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.Global;
//import com.feinno.circle.framework.business.impl.IpWhiteManager;
//import com.feinno.circle.framework.module.IpWhite;
//import com.feinno.circle.framework.module.example.IpWhiteExample;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//import com.feinno.framework.business.interfaces.ip.IUiIpWhiteManager;
//import com.feinno.framework.model.ip.UiIpWhite;

public class IPUtil {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(IPUtil.class);
	
	
	private static final Set<String> intranet = new HashSet<String>();
	
//	static{
//		String ip_prefix = null;
//		
//		ip_prefix ="114.255.238.";//公司内部地址段
//		for(int i= 245; i <= 254; i++ ){
//			intranet.add(ip_prefix + i);
//		}
//		
//		ip_prefix = "60.247.77.";//公司内部地址段
//		for(int i= 250; i <= 254; i++ ){
//			intranet.add(ip_prefix + i);
//		}
//		
//		ip_prefix = "60.194.14.";//公司内部地址段
//		for(int i= 245; i <= 254; i++ ){
//			intranet.add(ip_prefix + i);
//		}
//		
//		intranet.add("127.0.0.1");//本机
//		intranet.add("localhost");//本机
//		intranet.add("10");//内网段
//		intranet.add("192");//内网段
//	}
	
	
	/**
	 * 加载数据库中配置的IP; 
	 * 检查Ip规则是否符合规定;
	 * xxx.xxx.xxx.xxx 指定Ip地址;
	 * xxx.xxx.xxx.xxx-xxx 网段;
	 * xxx 内网段,如(192,10);
	 * 127.0.0.1;
	 * localhost;
	 * 
	 */
	public static void loadIp () {
		if (logger.isInfoEnabled()) {
			logger.info("loadIp() - 加载配置ip --- 开始");
		}
		
//		IpWhiteManager ipWhiteManager = (IpWhiteManager)Global._ctx.getBean("ipWhiteManager");
//		
//		//List<IpWhite> ipWhites = IpWhiteManager.getAll();
//		IpWhiteExample ipWhiteExample = new IpWhiteExample();
//		ipWhiteExample.setOrderByClause("SORT");
//		
//		List<IpWhite> ipWhites = ipWhiteManager.selectByExample(ipWhiteExample);
//		
//		for (IpWhite ipWhite : ipWhites) {
//			if (ipWhite != null) {
//				//验证ip配置规则;
//				if (validateIpRule(ipWhite.getIp())) {
//					
//					logger.info("配置ip地址:" + ipWhite.getIp());
//					
//					if ((byte)3 == ipWhite.getType()) {
//						/**
//						 * xxx.xxx.xxx.xxx-xxx 网段;
//						 * ip规则之前已经验证过,这里只是提取ip数字字符串;
//						 */
//						String regIp = 
//							"^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.)(\\d{1,3})-(\\d{1,3})$";						
//						Pattern pattern = Pattern.compile(regIp);						
//						Matcher matcher = pattern.matcher(ipWhite.getIp());						
//						if (matcher.find()) {
//							String ip_prefix = matcher.group(1);//公司内部地址段
//							for(int i = Integer.parseInt(matcher.group(2)); i <= Integer.parseInt(matcher.group(3)); i++ ){
//								intranet.add(ip_prefix + i);
//							}
//						} else {
//							if (logger.isWarnEnabled()) {
//								logger.warn("IP配置错误:ip=" + ipWhite.getIp() + ";ipType=" + ipWhite.getType());
//							}							
//						}
//					
//					} else if ((byte)1 == ipWhite.getType() || (byte)2 == ipWhite.getType()) {
//						/**
//						 * 127.0.0.1,localhost 本机;
//						 * 10,192 内网段;
//						 */
//						intranet.add(ipWhite.getIp());
//					} else {
//						if (logger.isWarnEnabled()) {
//						logger.warn("IP配置错误:ip=" + ipWhite.getIp() + ";ipType=" + ipWhite.getType());
//						}
//					}
//				} else {
//					if (logger.isWarnEnabled()) {
//					logger.warn("IP配置错误:ip=" + ipWhite.getIp());
//					}
//				}
//			}
//		}
//		
//		if (logger.isInfoEnabled()) {
//			validateSelf();
//		}
		
		if (logger.isInfoEnabled()) {
			logger.info("loadIp() - 加载配置ip --- 结束");
		}
	}
	
	/**
	 * 重新加载数据库中配置的IP
	 */
	public static void refreshIp () {
		intranet.clear();
		loadIp();
	}
	
	/**
	 * 添加IP后进行自测,查看ip是否合法;
	 */
	public static void validateSelf () {
		if (logger.isInfoEnabled()) {
			logger.info("loadIp() - 对已加载的ip进行验证");
		}
		
		Iterator<String> it = intranet.iterator();
		
		while (it.hasNext()) {
			String ip = it.next();
			logger.info("ip=" + ip + "\t;validate=" + validateIpRule(ip));
		}
	}
		
	/**
	 * 检查Ip规则是否符合规定;
	 * xxx.xxx.xxx.xxx 指定Ip地址;
	 * xxx.xxx.xxx.xxx-xxx 网段;
	 * xxx 内网段,如(192,10);
	 * 127.0.0.1;
	 * localhost;
	 * 
	 * @param ipAddress
	 * @return true 符合规则; false 不符合规则;
	 */
	public static boolean validateIpRule (String ipAddress) {

		if (ipAddress == null) {
			return false;
		} else {
			int ipRuleType = validateIpRuleType(ipAddress);
			
			/*
			 * 1:固定IP
			 * 2:公司内网段IP前缀;
			 * 3:IP地址段区间;
			 * -1:不可识别;
			 */
			if (ipRuleType == 1
					|| ipRuleType ==2
					|| ipRuleType ==3) {
				return true;
			}
		} 
		
		return false;
	}
	
	
	
	/**
	 * 1:固定IP
	 * 2:公司内网段IP前缀;
	 * 3:IP地址段区间;
	 * -1:不可识别;
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static int validateIpRuleType (String ipAddress) {
		
		//本机;
		if ("127.0.0.1".equals(ipAddress) || "localhost".equals(ipAddress)) {
			return 1;
		}
		
		// xxx 内网段[1,255],如(192,10);
		String regStrInnerIp = 
			"^([1-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})$";
		if (Pattern.compile(regStrInnerIp).matcher(ipAddress).find()) {
			return 2;
		}
		
		// xxx.xxx.xxx.xxx 指定Ip地址; 1.0.0.0-255.255.255.255
		String regStr1 = 
			"^" +
			"([1-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})" +
			"$";
		
		//固定IP
		if (Pattern.compile(regStr1).matcher(ipAddress).find()) {					
			return 1;
		}
		
		//xxx.xxx.xxx.xxx-xxx 网段;
		String regStr2 = 
			"^" +
			"([1-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})\\." +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})" +
				"-" +
			"([0-9]{1}|[1-9]{1}\\d{1}|1\\d{2}|2[0-4]{1}\\d{1}|25[0-5]{1})" +
			"$";
		
		if (Pattern.compile(regStr2).matcher(ipAddress).find()) {
			String [] ipNums = ipAddress.split("\\.")[3].split("-");
			if (Integer.parseInt(ipNums[0]) <= Integer.parseInt(ipNums[1]) 
					&& Integer.parseInt(ipNums[0]) >= 0
					&& Integer.parseInt(ipNums[1]) <= 255
			) {
				return 3;
			}
		}
		
		return -1;
	}
	
	/**
	 * Ip validate.
	 *
	 * @param ip ip地址 
	 * @return true, 如果是外网ip
	 */
	public static boolean isInternetIP(String ip){
		

		boolean isInternet = true;
		if (intranet.contains(ip)) {
			isInternet = false;
		}else if (intranet.contains(ip.split("\\.")[0])) {
			isInternet = false;
		}

		return isInternet;
	}
	
	/**
	 * 
	 * appendHttp:
	 * 对IP增加http://,如果已经有则不加;
	 *
	 * @param ip
	 * @return  
	 * @return String  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-12
	 */
	public static String appendHttp (String ip) {		
		if (ip != null) {
			ip = ip.trim();
			
			if (ip.toLowerCase().indexOf("http://") != 0) {
				ip = "http://" + ip;
			}			
		}
		
		return ip;
	}
	
	/**
	 * 
	 * getLocalIps:
	 * 
	 * 获得本地IP地址列表
	 *
	 * @return  
	 * @return String  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-22
	 */
	public static String getLocalIps () {
		if (logger.isDebugEnabled()) {
			logger.debug("getLocalIps() - start");
		}

		String localIps = "";
		Enumeration<NetworkInterface> allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			logger.error("getLocalIps()", e);
		}

		InetAddress inetAddress = null;

		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = allNetInterfaces
					.nextElement();
			
			logger.info(netInterface.getName());
			
			Enumeration<InetAddress> addresses = netInterface
					.getInetAddresses();
			
			while (addresses.hasMoreElements()) {
				inetAddress = addresses.nextElement();
				
				if (inetAddress != null && inetAddress instanceof Inet4Address) {
					logger.debug("本机的IP = " + inetAddress.getHostAddress());
					if (!"127.0.0.1".equals(inetAddress.getHostAddress())) {
						localIps += inetAddress.getHostAddress() + ',';
					}
				}
			}
		}
		
		if (localIps.length() > 0) {
			localIps = localIps.substring(0,localIps.length()-1);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLocalIps() - end");
		}
		return localIps;
	}
	
	public static void main(String[] args) {
		if (logger.isInfoEnabled()) {
			logger.info("main() - start");
		}

		//String[] nums = "127.0.0.1".split("\\.");
		//System.out.println(nums[0]);
		// 公司将会再启用一条新的带宽链路，所以出口IP地址将会改变。有一些部门需要调整下服务器的白名单设置。
		// 新的出口IP地址为： 60.194.14.245-254  这10个地址；
		// 原有联通出口地址保持不变：114.255.238.245-254这10个地址；
		// 原有电信出口地址保持不变：60.247.77.250-254这5个地址；

		String [] ipStrs = {
				"114.255.238.251",
				"127.0.0.1",
				"192.168.0.1",
				"114.255.238.245",
				"114.25.238.245",
				"14.204.200.24",
				"60.194.14.254",
				"60.194.14.255",
				"60.247.77.250",				
				"60.247.77.255",
				"199.0.0.1",
				"123.400.4.1",
				"0.40.100.1",
				"10.40.100.1",				
				"10.0.0.0",
				"10.00.0.0",
				"10.660.0.0",
				"0.00.0.0",
				"0.0.0.0",
				"1.0.0.0",
				"255.255.255.255",
				"123.255.255.245-255",
				"123.255.255.245-235",
				"03.255.255.225-235",
				null,
				"1.0.0.1a",
				"10",
				"255",
				"192",
				"9",
				"1",
				"0",
				"01"
			};
		
		
		Date d1 = new Date();
		for (String ip : ipStrs) {
			System.out.println("ip=" + ip + "\t:" + validateIpRule(ip));
		}
		
		Date d2 = new Date();		
		long s = d2.getTime()-d1.getTime();		
		System.out.println("花费时间s=" + s + "毫秒");
		
		
		String ipStr = "123.255.255.245-255";
		
		System.out.println("ipStr=" + ipStr + "\t:" + validateIpRule(ipStr));
		
		String regIp = 
			"^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.)(\\d{1,3})-(\\d{1,3})$";
		
		Pattern pattern = Pattern.compile(regIp);
		
		Matcher matcher = pattern.matcher(ipStr);
		
		if (matcher.find()) {
			int ln = matcher.groupCount();		
			for (int i=0; i<=ln; i++) {
				logger.info("i=" + i + ";\t" + matcher.group(i));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("main() - end");
		}
	}
}
