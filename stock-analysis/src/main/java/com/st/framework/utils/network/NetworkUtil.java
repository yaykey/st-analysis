/*
 * 文件名： NetworkUtil.java
 * 
 * 创建日期： 2012-3-19
 *
 * 原始作者: <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 */
package com.st.framework.utils.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 常用获取客户端信息的工具;
 * 
 *
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 * @version $Revision: 1.1 $
 *
 * @since 2012-3-19
 */
public final class NetworkUtil {
	
	/** Logger for this class. */
	private static final Log logger = LogFactory.getLog(NetworkUtil.class);

	/**
	 * 获取客户端MAC地址(有一定缺陷);.
	 * 
	 * @param ipAddress
	 *            the ip address
	 * @return the mac address
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static String getMacAddress(String ipAddress)
			throws IOException {
		 if (logger.isDebugEnabled()) {
		 logger.debug("getMacAddress(String) - start");
		 }
		String macAddress = null;
		try {
			String command = "nbtstat -a " + ipAddress;
			Process pp = Runtime.getRuntime().exec(command);
			InputStreamReader isr = new InputStreamReader(pp.getInputStream());
			LineNumberReader lnr = new LineNumberReader(isr);
			String strLine = null;
			do {
				strLine = lnr.readLine();
				 if (logger.isInfoEnabled()) {
				 System.out.println(strLine);
				 }
				if (strLine != null && strLine.indexOf("MAC Address") > 1) {
					macAddress = strLine.substring(strLine
							.indexOf("MAC Address") + 14, strLine.length());
					break;
				}
			}
			while (strLine != null);
		}
		catch (IOException e) {
			macAddress = null;
			logger
					.error("getMacAddress(String ipAddress=" + ipAddress + ")",
							e);
		}

		 if (logger.isInfoEnabled()) {
		 logger.info("getMacAddress(String ipAddress=" + ipAddress + ") - end - return value=" + macAddress);
		 }
		return macAddress;
	}

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;.
	 * 
	 * @param request
	 *            the request
	 * @return the ip address
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static String getIpAddress(HttpServletRequest request)
			throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger
					.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
							+ ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger
							.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
									+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger
							.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
									+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger
							.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
									+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger
							.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
									+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger
							.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
									+ ip);
				}
			}
		}
		else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
	/**
	 * 
	 * 判断是否为email格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		
		if (email == null || "".equals(email.trim())) {
			return false;
		}
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
		return email.matches(EMAIL_REGEX);
	}
}
