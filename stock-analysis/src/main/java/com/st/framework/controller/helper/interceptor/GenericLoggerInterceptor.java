/*
 * 文件名： GenericLoggerInterceptor.java
 * 
 * 创建日期： 2012-3-17
 *
 * 原始作者: <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 */
package com.st.framework.controller.helper.interceptor;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;


//import com.feinno.circle.framework.module.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
//import com.st.analysis.module.PubUser;
//import com.st.framework.business.impl.OperatelogManager;
import com.st.framework.controller.UserSessionInfo;
import com.st.framework.controller.helper.interceptor.annotations.GenericLogger;
import com.st.framework.controller.helper.interceptor.enums.OperateMark;
//import com.st.framework.module.Operatelog;

/**
 * 记录系统日志的拦截器;
 * 
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 * 
 * @version $Revision: 1.2 $
 * 
 * @since 2012-3-17
 */
public class GenericLoggerInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -692120738061647890L;

	
//	@Autowired
//	private OperatelogManager operatelogManager;
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(GenericLoggerInterceptor.class);

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("intercept() - start");
		}

		String returnString = invocation.invoke();

		// After;

		String nameSpace = invocation.getProxy().getNamespace();

//		if (null != nameSpace && nameSpace.startsWith("/manager")) {
//			addManagerLogger(invocation);
//		} else {
//			addGenericLogger(invocation);
//		}

		if (logger.isDebugEnabled()) {
			logger.debug("intercept() - end");
		}
		return returnString;
	}
//
//	
////	/**
////	 * 记录后台操作;
////	 * 
////	 * @param invocation
////	 * @throws Exception
////	 */
//	private void addManagerLogger(ActionInvocation invocation) throws Exception {
//		if (logger.isDebugEnabled()) {
//			logger.debug("addManagerLogger() - start");
//		}
//
//		String methodName = invocation.getProxy().getMethod();
//		String actionURL = invocation.getProxy().getNamespace() + "/"
//				+ invocation.getProxy().getActionName();
//
//		Object action = invocation.getAction();
//
//		GenericLogger genericLogger = action.getClass()
//				.getMethod(methodName, new Class[0])
//				.getAnnotation(GenericLogger.class);
//
//		// 判断是否主动标注,不进行记录;
//		if (genericLogger != null && genericLogger.isGenericLogger() == false) {
//			return;
//		}
//
//		String className = action.getClass().getName();
//
//		HttpServletRequest request = (HttpServletRequest) invocation
//				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
//
//		UserSessionInfo userSessionInfo = (UserSessionInfo) request
//				.getSession().getAttribute("userSessionInfo");
//		PubUser userInfo = null;
//		
//		if (userSessionInfo != null) {
//			userInfo = userSessionInfo.getUserInfo();
//		}
//		
//		Operatelog operateLog = new Operatelog();
//
//		if (userInfo != null) {
//			operateLog.setLoginId(userInfo.getUsername());
//			operateLog.setLoginName(userInfo.getUsername());
//			operateLog.setUserId(userInfo.getId());
//		}
//		
//		Integer records = (Integer) action.getClass()
//				.getMethod("getOperateRecords", new Class[0])
//				.invoke(action, new Object[0]);
//		operateLog.setOperateRecords(records);
//
//		String operateMessage = (String) action.getClass()
//				.getMethod("getOperateMessage", new Class[0])
//				.invoke(action, new Object[0]);
//		operateLog.setOperateMessage(operateMessage);
//
//		Integer fid = null;
//		try {
//			fid = (Integer) action.getClass()
//				.getMethod("getFid", new Class[0])
//				.invoke(action, new Object[0]);
//		} catch (Exception ex) {
//			
//		}
//		
//		if (fid != null) {
//			operateLog.setFunctionId(fid);
//		}
//		
//		if (genericLogger != null) {
//
//			/**
//			 * 是否记录action URL;
//			 */
//			if (genericLogger.isActionURL()) {
//				operateLog.setActionUrl(actionURL);
//			}
//
//			/**
//			 * 是否记录class名称;
//			 */
//			if (genericLogger.isClass()) {
//				operateLog.setClazz(className);
//			}
//
//			/**
//			 * 是否记录method名称;
//			 */
//			if (genericLogger.isMethod()) {
//				operateLog.setMethod(methodName);
//			}
//
//			/**
//			 * 记录IP
//			 */
//			if (genericLogger.isRealIp()) {
//				String realIp = (String) action.getClass()
//						.getMethod("getRealIP", new Class[0])
//						.invoke(action, new Object[0]);
//				operateLog.setRealIp(realIp);
//			}
//
//			/**
//			 * 时候记录用户client端情况;
//			 */
//			if (genericLogger.isUserAgent()) {
//				operateLog.setUserAgent(request.getHeader("User-Agent"));
//			}
//
//			operateLog
//					.setOperateDescription(genericLogger.operateDescription());
//			operateLog.setOperateMark(genericLogger.operateMark().toString());
//		} else {
//			operateLog.setActionUrl(actionURL);
//			operateLog.setClazz(className);
//			operateLog.setMethod(methodName);
//
//			String realIp = (String) action.getClass()
//					.getMethod("getRealIP", new Class[0])
//					.invoke(action, new Object[0]);
//
//			operateLog.setOperateRecords(records);
//			operateLog.setRealIp(realIp);
//
//			operateLog.setUserAgent(request.getHeader("User-Agent"));
//			operateLog.setOperateMark(OperateMark.OPERATE_DEFAULT.toString());
//		}
//
//		try {
//			this.operatelogManager.insert(operateLog);
//		} catch (Exception ex) {
//			logger.error("addManagerLogger() - exception ignored", ex);
//		}
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("addManagerLogger() - end");
//		}
//	}
//
//	private void addGenericLogger(ActionInvocation invocation) throws Exception {
//		String methodName = invocation.getProxy().getMethod();
//		Object action = invocation.getAction();
//		GenericLogger genericLogger = action.getClass()
//				.getMethod(methodName, new Class[0])
//				.getAnnotation(GenericLogger.class);
//
//		if (null != genericLogger && genericLogger.isGenericLogger()) {
//
//			HttpServletRequest request = (HttpServletRequest) invocation
//					.getInvocationContext().get(
//							ServletActionContext.HTTP_REQUEST);
//
//			UserSessionInfo userSessionInfo = (UserSessionInfo) request
//					.getSession().getAttribute("userSessionInfo");
//
//			PubUser userInfo = null;
//			
//			if (userSessionInfo != null) {
//				userInfo = userSessionInfo.getUserInfo();
//			}
//			
//
//			Operatelog operateLog = new Operatelog();
//
//			/**
//			 * 是否记录action URL;
//			 */
//			if (genericLogger.isActionURL()) {
//				String actionURL = invocation.getProxy().getNamespace() + "/"
//						+ invocation.getProxy().getActionName();
//				operateLog.setActionUrl(actionURL);
//			}
//
//			/**
//			 * 是否记录class名称;
//			 */
//			if (genericLogger.isClass()) {
//				String className = action.getClass().getName();
//				operateLog.setClazz(className);
//			}
//
//			/**
//			 * 是否记录method名称;
//			 */
//			if (genericLogger.isMethod()) {
//				operateLog.setMethod(methodName);
//			}
//
//			if (userInfo != null) {
//				operateLog.setLoginId(userInfo.getUsername());
//				operateLog.setUserId(userInfo.getId());
//				operateLog.setLoginName(userInfo.getUsername());
//			}
//			
//			operateLog.setOperateMark(genericLogger.operateMark().toString());
//			operateLog.setOperateDescription(genericLogger.operateDescription()
//					.toString());
//			Integer records = (Integer) action.getClass()
//					.getMethod("getOperateRecords", new Class[0])
//					.invoke(action, new Object[0]);
//			operateLog.setOperateRecords(records);
//
//			String operateMessage = (String) action.getClass()
//					.getMethod("getOperateMessage", new Class[0])
//					.invoke(action, new Object[0]);
//			operateLog.setOperateMessage(operateMessage);
//
//			Integer fid = null;
//			
//			try {
//				fid = (Integer) action.getClass()
//					.getMethod("getFid", new Class[0])
//					.invoke(action, new Object[0]);
//			} catch (Exception ex) {
//				
//			}
//			
//			if (fid != null) {
//				operateLog.setFunctionId(fid);
//			}
//			
//			//operateLog.setIsEnable((short)0);
//				
//			
//			/**
//			 * 记录IP
//			 */
//			if (genericLogger.isRealIp()) {
//				String realIp = (String) action.getClass()
//						.getMethod("getRealIP", new Class[0])
//						.invoke(action, new Object[0]);
//				operateLog.setRealIp(realIp);
//			}
//
//			/**
//			 * 时候记录用户client端情况;
//			 */
//			if (genericLogger.isUserAgent()) {
//				operateLog.setUserAgent(request.getHeader("User-Agent"));
//			}
//
//			try {
//				
//				this.operatelogManager.insert(operateLog);
//			} catch (Exception ex) {
//				logger.warn("intercept() - exception ignored", ex);
//			}
//
//		}
//
//	}

//	@SuppressWarnings("unchecked")
//	private IOperateLogManager<OperateLog> getOperateLogManager() {
//		return (IOperateLogManager<OperateLog>) Global._ctx
//				.getBean("operateLogManager");
//	}
}
