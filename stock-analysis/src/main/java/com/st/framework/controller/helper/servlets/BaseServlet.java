/*
 * File Name：BaseServlet.java
 * Package Name：com.feinno.circle.framework.controller.helper.servlets
 *
 * Function： TODO 
 *
 *   ver     date      author               department
 * ──────────────────────────────────————————————————————————————
 *   V1.0   2013-1-24    yzy		  DATA BUSINESS DEPARTMENT
 *
 * Copyright (c) 2013, Feinno Communication Tech All Rights Reserved.
*/

package com.st.framework.controller.helper.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.st.framework.utils.encrypt.impl.EncryptorMD5Impl;

/**
 * ClassName:BaseServlet
 * Reason:	 TODO
 *
 * @author   yzy
 * @version  V1.0.0
 * @since    1.0
 * @Date	 2013-1-24		下午04:56:06
 *
 */
public abstract class BaseServlet extends HttpServlet {

	/**
	 * serialVersionUID:TODO
	 */
	
	private static final long serialVersionUID = -3701910943004873830L;

	protected static EncryptorMD5Impl encryptor = new EncryptorMD5Impl();
	
	/**
	 * 得到服务全路径，
	 * 
	 * @return 服务全路径
	 */
	protected String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();

		if ( request.getServerPort() != 80 ) {
			basePath += ":" + request.getServerPort() + path;
		} else {
			basePath += path;
		}

		return basePath;
	}
		
	/**
	 * 得到应用的绝对地址，例如：D:\Eclipse\workspace\CourseCreater\web\
	 * 
	 * @return 应用的绝对地址
	 */
	protected String getRealPath() throws Exception{		
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * 获得用户的真实IP
	 * 
	 * @return 用户的真实IP
	 */
	protected String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		return ip;
	}
	
	/**
	 * 获得请求的referer
	 * 
	 * @return referer
	 */
	protected String getReferer(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		if (referer != null && !referer.equalsIgnoreCase("")) {
			referer = referer.substring(0, referer.lastIndexOf("/")+1);
		} else {
			referer = "";
		}
		
		return referer;
	}
}

