/*
 * 文件名： Log4JFilter.java
 * 
 * 创建日期： 2008-1-28
 *
 * Copyright(C) 2008, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.controller.helper.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.st.framework.controller.UserSessionInfo;

/**
 * 给Log4J设置参数的Filter
 * 
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 2008-1-28
 */
public class Log4JFilter implements Filter {

	protected final static String DEFAULT_USERID = "anonymous";
	
	protected final static String PROPERTY_IP = "ip";
	
	protected final static String PROPERTY_LOGIN_ID = "loginId";
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		MDC.put(PROPERTY_IP, request.getRemoteAddr());
//		MDC.put(PROPERTY_LOGIN_ID, getLoginId(session));
		
		chain.doFilter(request, response);
		
		MDC.remove(PROPERTY_IP);
		MDC.remove(PROPERTY_LOGIN_ID);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {

	}


//	/**
//	 * 获取MDC中loginId变量的值
//	 * @param session
//	 * @return 用户的loginId，如果尚未登录返回"anonymous"
//	 */
//	protected String getLoginId(HttpSession session) {
//		String returnValue = DEFAULT_USERID;
//		UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute("userSessionInfo");
//		if (userSessionInfo != null) {
//			returnValue = userSessionInfo.getUserInfo().getLoginId();
//		}
//
//		return returnValue;
//	}
}
