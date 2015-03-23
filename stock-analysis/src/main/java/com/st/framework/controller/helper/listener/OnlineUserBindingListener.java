package com.st.framework.controller.helper.listener;
//package com.feinno.circle.framework.controller.helper.listener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionBindingEvent;
//import javax.servlet.http.HttpSessionBindingListener;
//
//import com.feinno.circle.framework.controller.UserSessionInfo;
//
//public class OnlineUserBindingListener implements HttpSessionBindingListener{
//
//	private String username;
//	
//	public OnlineUserBindingListener (String username) {
//		this.username = username;
//	}
//
//	@Override
//	public void valueBound(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession(); 
//		
//		UserSessionInfo userInfo = (UserSessionInfo) session.getAttribute("userSessionInfo");
//		
//	     ServletContext application = session.getServletContext();   
//	  
//	    // 把用户名放入在线列表   
//	     List onlineUserList = (List) application.getAttribute("onlineUserList");   
//	    // 第一次使用前，需要初始化   
//	    if (onlineUserList == null) {   
//	         onlineUserList = new ArrayList();   
//	         application.setAttribute("onlineUserList", onlineUserList);   
//	     }   
//	     onlineUserList.add(this.username);
//		
//	}
//
//	@Override
//	public void valueUnbound(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession();   
//	     ServletContext application = session.getServletContext();   
//	  
//	    // 从在线列表中删除用户名   
//	     List onlineUserList = (List) application.getAttribute("onlineUserList");   
//	     onlineUserList.remove(this.username);   
//	  
//	     System.out.println(this.username + "退出。");   
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//}
