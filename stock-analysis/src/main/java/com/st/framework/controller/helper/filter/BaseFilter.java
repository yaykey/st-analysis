/*
 * File Name：BaseFilter.java
 * Package Name：com.feinno.framework.controller.helper.filter
 *
 * Function： filter基本方法; 
 *
 *   ver     date      author               department
 * ──────────────────────────────────————————————————————————————
 *   V1.0   2012-11-27    yzy		  DATA BUSINESS DEPARTMENT
 *
 * Copyright (c) 2012, Feinno Communication Tech All Rights Reserved.
*/

package com.st.framework.controller.helper.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import com.st.Global;


/**
 * ClassName:BaseFilter
 * Reason:	 filter基本方法
 *
 * @author   yangzhenyu
 * @version  V1.0.0
 * @since    1.0
 * @Date	 2012-11-27		下午06:07:25
 *
 */
public abstract class BaseFilter implements Filter {

	/**请求类型*/
	protected static final String REQUEST_JSP = "jsp";

	/**
	 * action扩展名;
	 */
	protected static final String REQUEST_ACTION = Global.ACTION_EXT;
	
	/**
	 * dwr扩展名;
	 */
	protected static final String REQUEST_DWR = "dwr";

	/**
	 * 其他类型;
	 */
	protected static final String REQUEST_OTHER = "other";
	
	/**
	 * 获得应用的地址，例如：http://127.0.0.1:8080/dataplatform
	 * 
	 * @param request
	 * @return
	 */
	protected String getRootPath(HttpServletRequest request) {

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 80) {
			basePath += ":" + request.getServerPort() + path;
		} else {
			basePath += path;
		}
		return basePath;
	}

	/**
	 * 获得请求的所在的名称空间，例如：http://localhost:8080/dataplatform/report/reportModel/
	 * test.jsp
	 * 
	 * 返回/report/reportModel
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 * @return 请求的Action所在的名称空间
	 */
	protected String getRequestActionNameSpace(HttpServletRequest request) {

		String absoluteUrl = request.getRequestURL().toString();
		String nameSpaceStr = absoluteUrl.substring(getRootPath(request)
				.length(), absoluteUrl.lastIndexOf("/"));
		if (nameSpaceStr.trim().length() == 0 || nameSpaceStr == null) {
			nameSpaceStr = "/";
		}
		return nameSpaceStr;
	}

	/**
	 * 获得请求的路径
	 * 
	 * @param request
	 * @return
	 */
	protected String getRequestPath(HttpServletRequest request) {

		String absoluteUrl = request.getRequestURL().toString();
		String nameSpaceStr = absoluteUrl.substring(getRootPath(request)
				.length());
		if (nameSpaceStr.trim().length() == 0 || nameSpaceStr == null) {
			nameSpaceStr = "/";
		}
		return nameSpaceStr;
	}

	/**
	 * 获得请求类型;
	 * 
	 * getRequestTypeName:
	 *
	 * @param request
	 * @return  
	 * @return String  
	 * @throws 
	 * @since 　Ver 1.0
	 * DATE: 2012-11-27
	 */
	protected String getRequestTypeName(HttpServletRequest request) {
		// 获得请求的绝对路径
		String absoluteUrl = request.getRequestURL().toString();
		if (absoluteUrl.lastIndexOf(".") != -1) {
			if (absoluteUrl.substring(absoluteUrl.lastIndexOf(".") + 1).equals(
					REQUEST_JSP)) {
				return REQUEST_JSP;
			} else if (absoluteUrl.substring(absoluteUrl.lastIndexOf(".") + 1)
					.equals(REQUEST_ACTION)) {
				return REQUEST_ACTION;
			} else if (absoluteUrl.substring(absoluteUrl.lastIndexOf(".") + 1)
						.equals(REQUEST_DWR)) {
					return REQUEST_DWR;
			} else {
				return REQUEST_OTHER;
			}
		} else {
			return REQUEST_OTHER;
		}
	}
	
	
	/**
	 * 
	 * getRequestActionNameSubSpace:
	 *
	 * @param request
	 * @return  
	 * @return String  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-1-25
	 */
	private String getRequestActionNameSubSpace(HttpServletRequest request) {
		String absoluteUrl = request.getRequestURL().toString();
		String nameSpaceStr = absoluteUrl.substring(getRootPath(request).length(), absoluteUrl.lastIndexOf(".portal"));
		return nameSpaceStr;
	}

	/**
	 * 获得请求的Action的名字，例如：userOperator!addInput.action
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 请求的Action的名字
	 */
	private String getRequestActionName(HttpServletRequest request) {
		String absoluteUrl = request.getRequestURL().toString();

		String actionName = absoluteUrl.substring(
				absoluteUrl.lastIndexOf("/") + 1, absoluteUrl.length());

		return actionName;
	}
	
}

