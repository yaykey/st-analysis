package com.st.framework.controller.helper.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.st.framework.controller.UserSessionInfo;

public class OnlineUserListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(OnlineUserListener.class);

	// @Override
	public void sessionCreated(HttpSessionEvent event) {

		// logger.info("sessionCreated(HttpSessionEvent) - start");
		//
		//
		// // HttpSession session = event.getSession();
		// // ServletContext application = session.getServletContext();
		// // // 取得登录的用户名
		// // UserSessionInfo userInfo = (UserSessionInfo)
		// session.getAttribute("userSessionInfo");
		// // String loginid = userInfo.getLoginId();
		// // // 从在线列表中删除用户名
		// // List onlineUserList = (List)
		// application.getAttribute("onlineUserList");
		// // onlineUserList.add(userInfo);
		// // System.out.println(loginid + "创建。");
		//
		//
		// logger.info("sessionCreated(HttpSessionEvent) - end");
		// //新创建的session
		// HttpSession session = (HttpSession)event.getSession();
		//
		// //保存session
		// ApplicationConstants.ONLINE_USER_LIST.add(session);
		// //在线人数++
		// ApplicationConstants.TOTAL_HISTORY_COUNT++;
		// //如果超过最大在线人数 更新 更新时间
		// if(ApplicationConstants.ONLINE_USER_LIST.size()>
		// ApplicationConstants.MAX_ONLINE_COUNT){
		// //更新最大在线人数
		// ApplicationConstants.MAX_ONLINE_COUNT =
		// ApplicationConstants.ONLINE_USER_LIST.size();
		// //更新日期
		// ApplicationConstants.MAX_ONLINE_COUNT_DATE = new Date();
		// }
	}

	// @Override
	public void sessionDestroyed(HttpSessionEvent event) {

		// logger.info("sessionDestroyed(HttpSessionEvent) - start");
		//
		//
		// HttpSession session = event.getSession();
		// ServletContext application = session.getServletContext();
		// // 取得登录的用户名
		// UserSessionInfo userInfo = (UserSessionInfo)
		// session.getAttribute("userSessionInfo");
		// if (userInfo != null) {
		// String loginid = userInfo.getLoginId();
		// // 从在线列表中删除用户名
		// List onlineUserList = (List)
		// application.getAttribute("onlineUserList");
		// onlineUserList.remove(userInfo);
		// System.out.println(loginid + "超时退出。");
		//
		// //session.getCreationTime()
		// }
		//
		//
		//
		// // logger.info("sessionDestroyed(HttpSessionEvent) - end");
		// //即将被销毁的session
		// HttpSession session = event.getSession();
		// //从map中将sesion的索引删除
		// ApplicationConstants.ONLINE_USER_LIST.remove(session);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if ("userSessionInfo".equals(event.getName())) {
			
			// 新创建的session
			HttpSession session = (HttpSession) event.getSession();

			// 保存session
			ApplicationConstants.ONLINE_USER_LIST.add(session);

			ApplicationConstants.CURRENT_LOGIN_COUNT = ApplicationConstants.ONLINE_USER_LIST
					.size();

			// 历史访客总数++
			ApplicationConstants.TOTAL_HISTORY_COUNT++;

			// 如果超过最大在线人数 更新 更新时间
			if (ApplicationConstants.ONLINE_USER_LIST.size() > ApplicationConstants.MAX_ONLINE_COUNT) {
				// 更新最大在线人数
				ApplicationConstants.MAX_ONLINE_COUNT = ApplicationConstants.ONLINE_USER_LIST
						.size();
				// 更新日期
				ApplicationConstants.MAX_ONLINE_COUNT_DATE = new Date();
			}

		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if ("userSessionInfo".equals(event.getName())) {
			HttpSession session = event.getSession();

			// ServletContext application = session.getServletContext();

			// List onlineUserList = (List)
			// application.getAttribute("onlineUserList");
			//
			// onlineUserList.remove(userInfo);

			ApplicationConstants.ONLINE_USER_LIST.remove(session);

			ApplicationConstants.CURRENT_LOGIN_COUNT = ApplicationConstants.ONLINE_USER_LIST
					.size();

			// 取得登录的用户名
			UserSessionInfo userInfo = (UserSessionInfo) event.getValue();
			
//			logger.info(userInfo.getLoginId() + "超时退出。");

		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}

}
