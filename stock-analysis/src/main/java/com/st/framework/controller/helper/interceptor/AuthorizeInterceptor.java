/**
 * @(#)AuthorizeInterceptor.java  2011-4-26
 */
package com.st.framework.controller.helper.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.st.framework.business.annotations.Verify;
import com.st.framework.controller.UserSessionInfo;
//import com.st.framework.module.Function;
//import com.st.framework.module.Role;
//import com.st.framework.utils.cache.RoleRightsMap;

/**
 * 类 <code>AuthorizeInterceptor</code>
 * 
 * 用于权限校验的拦截处理,权限校验的实现在Action上基于@Verify注解
 * 
 * @author wangwenyao@feinno.com
 * @version 2011-4-26
 */
public class AuthorizeInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AuthorizeInterceptor.class);

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1353264384190199071L;

	private static final Logger LOG = LoggerFactory
			.getLogger(AuthorizeInterceptor.class);

	private static final String NO_SESSION = "nosession";

	private static final String NO_AUTHORITY = "noauthority";

	/*
	 * (non-Jsdoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		UserSessionInfo userSessionInfo = (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
//		if (userSessionInfo != null) {
//			
//			if ("admin".equals(userSessionInfo.getUserInfo().getUsername())) {
//				// 不需要校验
//				return invocation.invoke();
//			}
//			
//			if (isVerify(invocation)) {//是否进行校验
//				String actionUrl = invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName();
//				if (logger.isDebugEnabled()) {
//					logger.debug("intercept() - String actionUrl=" + actionUrl);
//				}
//
//				boolean verify = false;
//				List<Role> roles = userSessionInfo.getRoles();
//				if(roles != null && !roles.isEmpty()){
//					for(Role role: roles){
//						List<Function> functions = RoleRightsMap.get(role.getId());
//						if(verifyAuthority(actionUrl, functions)){ //通过授权校验
//							return invocation.invoke();
//						}
//					}
//					if (!verify) {
//						return NO_AUTHORITY;
//					}
//				}
//			} else {// 不需要校验
//				return invocation.invoke();
//			}
//			
//		}
//		return NO_SESSION;
		
		return invocation.invoke();
	}


//	/**
//	 * 权限校验
//	 * 
//	 * @param actionUrl
//	 *            为namespace/actionName形式
//	 * @param roles
//	 * @return
//	 */
//	private boolean verifyAuthority(String actionUrl, List<Function> functions) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("进行权限校验，请求执行操作的URL:" + actionUrl);
//		}
//		if (functions == null || functions.isEmpty()) {
//			return false;
//		}
//		for (Function function : functions) {
//			if (actionUrl.equals(function.getActionUrl())) {
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * 判断是否校验权限，基于类或方法上是否有注解@Verify,方法上的注解拥有最高优先级
	 * 
	 * @param invocation
	 * @return
	 */
	private boolean isVerify(ActionInvocation invocation) {
		String methodName = invocation.getProxy().getMethod();
		Object action = invocation.getAction();
		Verify methodVerify = null;
		try {
			methodVerify = action.getClass().getMethod(methodName,
					new Class[] {}).getAnnotation(Verify.class);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		if (methodVerify != null) {
			return methodVerify.value();
		} else {
			Verify classVerify = action.getClass().getAnnotation(Verify.class);
			if (classVerify != null) {
				return classVerify.value();
			}
		}
		return true;
	}

}
