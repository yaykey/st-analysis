/**
 * @(#)AuthorizeInterceptor.java  2011-4-26
 */
package com.st.framework.controller.helper.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.st.framework.controller.UserSessionInfo;

/**
 * 
 * 
 *
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 * @version $Revision: 1.1 $
 *
 * @since 2011-6-24
 */
public class JsonNoSessionInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(JsonNoSessionInterceptor.class);

	

	private static final long serialVersionUID = 6830064580665294322L;
	
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		UserSessionInfo userSessionInfo = (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		
		if (userSessionInfo == null) {
			
			if (logger.isInfoEnabled()) {
				logger.info("intercept(ActionInvocation) - session过期!");
			}
			
			PrintWriter out = response.getWriter();
			
			out.print("nosession");
			out.close();
			
			return null;
		} else {
			return invocation.invoke();
		}
	}



}
