///**
// * SynchronousUtil.java
// * com.feinno.circle.framework.utils
// * Function： 负载机同步工具 
// *
// *   ver     date      author               department
// * ──────────────────────────────────————————————————————————————
// *   V1.0   2013-11-14    yzy		  DATA BUSINESS DEPARTMENT
// *
// * Copyright (c) 2013, Feinno Communication Tech All Rights Reserved.
//*/
//
//
//package com.st.framework.utils;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.io.IOException;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.methods.GetMethod;
//
//
///**
// * ClassName:SynchronousUtil
// * Reason:	 负载数据同步工具
// *
// * @author   yzy
// * @version  
// * @since    Ver 1.0.0
// * @Date	 2013	2013-11-14		上午11:16:30
// * @see 	 
// */
//public class SynchronousUtil {
//	/**
//	 * Logger for this class
//	 */
//	private static final Log logger = LogFactory.getLog(SynchronousUtil.class);
//
//	/**
//	 * 
//	 * SynchronousRoleRights:
//	 *
//	 * 同步负载机全局权限
//	 *
//	 * @param serverPort 负载机端口号
//	 * @param contextPath 工程路径
//	 * @return  
//	 * @return String  
//	 * @throws 
//	 * @since 　Ver 1.1
//	 * DATE: 2013-11-21
//	 */
//	public static String SynchronousRoleRights (String serverPort, String contextPath) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousRoleRights() - start");
//		}
//
//		String result = Synchronous(serverPort, contextPath, "/manager/synchronousRoleRights.feinno");
//		if (logger.isInfoEnabled()) {
//			logger.info("SynchronousRoleRights() - String result=" + result);
//		}
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousRoleRights() - end");
//		}
//		return result;
//	}
//	
//	/**
//	 * 
//	 * SynchronousIpWhite:
//	 * 
//	 * 同步负载机IP白名单;
//	 *
//	 * @param serverPort 负载机端口号
//	 * @param contextPath 工程路径
//	 * @return  
//	 * @return String  
//	 * @throws 
//	 * @since 　Ver 1.1
//	 * DATE: 2013-11-21
//	 */
//	public static String SynchronousIpWhite (String serverPort, String contextPath) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousIpWhite() - start");
//		}
//		
//				
//		String result = Synchronous(serverPort, contextPath, "/manager/SynchronousIP.feinno");
//		if (logger.isInfoEnabled()) {
//			logger.info("SynchronousIpWhite() - String result=" + result);
//		}
//				
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousIpWhite() - end");
//		}
//		return result;
//	}
//	
//	/**
//	 * 获得在线用户数;
//	 * 
//	 * @param serverPort
//	 * @param contextPath
//	 * @return
//	 */
//	public static String SynchronousOnlineUser (String serverPort, String contextPath) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousOnlineUser(String, String) - start");
//		}
//		
//		String result = Synchronous(serverPort, contextPath, "/manager/online/getSessionJSONList.feinno");
//		if (logger.isInfoEnabled()) {
//			logger.info("SynchronousOnlineUser(String, String) - String result=" + result);
//		}
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("SynchronousOnlineUser(String, String) - end");
//		}
//		return result;
//	}
//	
//	
//	/**
//	 * 
//	 * Synchronous:
//	 * 
//	 * 内部方法
//	 * 请求负载机的应用
//	 *
//	 * @param serverPort
//	 * @param contextPath
//	 * @param appPath
//	 * @return  
//	 * @return String  
//	 * @throws 
//	 * @since 　Ver 1.1
//	 * DATE: 2013-11-21
//	 */
//	private static String Synchronous (String serverPort, String contextPath, String appPath) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("Synchronous() - start");
//		}
//
//		if (contextPath == null) {
//			contextPath = "";
//		}
//		
//		if (serverPort == null) {
//			serverPort = "80";
//		}
//		
//		String result = "";
//		
//		
//		StringBuffer buffer = new StringBuffer();
//				
//		HttpClient client = new HttpClient();// 定义client对象
//
//		client.getHttpConnectionManager().getParams()
//				.setConnectionTimeout(15000);// 设置连接超时时间为15秒（连接初始化时间）
//		
//		client.getParams().setContentCharset("UTF-8");
//		
//		GetMethod method = null;
//		int statusCode = -1;
//		
//		String urlStr = null;
//		
//		//负载应用IP地址;
//		String[] path = LoadConfigUtils.getInstance().getLoadBalancePath();
//		
//		buffer.append("[");
//		
//		//for (String ip : path) {
//		for (int i=0; i<path.length; i++) {
//			
//			if (i != 0) {
//				buffer.append(",");
//			}
//			buffer.append("{");
//			
//			String ip = path[i];
//			ip = ip.trim();
//			
//			//map.put("ip", ip);
//			buffer.append("\"ip\":\"" + ip + "\"");
//			
//			if (IPUtil.validateIpRule(ip)) {
//								
//				if ( !"80".equals(serverPort) ) {
//					ip += ":" + serverPort + contextPath;
//				} else {
//					ip += contextPath;
//				}
//				
//				urlStr = IPUtil.appendHttp(ip) + appPath;
//								
//				method = new GetMethod(urlStr);
//				 
//				method.addRequestHeader("Content-Type", "text/html; charset=UTF-8");  
//				
//				try {
//					statusCode = client.executeMethod(method);
//				} catch (HttpException e) {
//					logger.error("Synchronous(String) - exception ignored", e);					
//				} catch (IOException e) {
//					logger.error("Synchronous(String) - exception ignored", e);
//				}
//				
//				// 状态，一般200为OK状态，其他情况会抛出如404,500,403等错误
//				if (statusCode != HttpStatus.SC_OK) {
//					//result = ip + ":失败;statusCode=" + statusCode;
//					result = "失败;statusCode=" + statusCode;
//					logger.error("Synchronous() - 远程访问失败。 String urlStr=" + urlStr);
//				} else {
//					result = "成功";
//					if (logger.isDebugEnabled()) {
//						logger.debug("Synchronous() - 远程访问成功。 String urlStr=" + urlStr);
//					}					
//				}
//				
//				//map.put("result", result);
//				buffer.append(",\"result\":\"" + result + "\"");
//				
//				// 输出反馈结果
//				try {
//					String bodyStr = method.getResponseBodyAsString();
//										
//					buffer.append(",\"body\":" + bodyStr);
//					
//				} catch (IOException e) {
//					logger.error("Synchronous()", e);	
//				}
//			}
//			
//			buffer.append("}");
//		}
//
//		client.getHttpConnectionManager().closeIdleConnections(1);
//				
//		buffer.append("]");
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("Synchronous() - end");
//		}
//		return buffer.toString();
//	}
//	
//}

