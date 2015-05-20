/*
 * 文件名： BaseAction.java
 * 
 * 创建日期： 2011-4-14
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.controller.actions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有ACTION的基类
 *
 */
public abstract class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 3055081950028503769L;

	/**
	 * 操作记录影响数据条数;
	 * -1:表示不记录影响数据库记录数;
	 */
	private Integer operateRecords = -1;
	
	/**
	 * 保存操作记录额外信息;
	 */
	private String operateMessage = "";
	
	/**
	 * function id;.
	 */
	protected Integer fid;

	

	/**
	 * 加入值到cookies里
	 *
	 * @param key 键
	 *
	 * @param value 值
	 */
	public void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");// 这个要设置
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(365 * 24 * 60 * 60);
		this.getResponse().addCookie(cookie);
	}

	public void addCookie(String key, String value,int time) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");// 这个要设置
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(time);
		this.getResponse().addCookie(cookie);
	}

	/**
	 * 根据键获取对应的cookie对象
	 *
	 * @param key 对应的key
	 *
	 * @return key对应的cookie值
	 */
	public Cookie getCookie(String key){
		Cookie[] cookies = this.getRequest().getCookies() ;
		for(Cookie temp : cookies){
			if(temp.getName().equals(key)){
				return temp ;
			}
		}
		return null ;
	}

	/**
	 * 获得cookie对应的值
	 *
	 * @param key 对应的键
	 *
	 * @return 返回值
	 */
	public String getCookieValue(String key){
		Cookie cookie = this.getCookie(key) ;
		if(cookie!=null){
			return cookie.getValue() ;
		}
		return null ;
	}

	/**
	 * Gets the 操作记录影响数据条数; -1:表示不记录影响数据库记录数;.
	 * 
	 * @return 返回 operateRecords。
	 */
	public Integer getOperateRecords() {
		return operateRecords;
	}

	/**
	 * Sets the 操作记录影响数据条数; -1:表示不记录影响数据库记录数;.
	 * 
	 * @param operateRecords
	 *            要设置的 operateRecords。
	 */
	public void setOperateRecords(Integer operateRecords) {
		this.operateRecords = operateRecords;
	}
	
	/**
	 * 
	 * appendOperateRecords:
	 * 
	 * 累加影响记录条数;
	 *
	 * @param appendOperateRecords  
	 * @return void  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-12
	 */
	public void appendOperateRecords(Integer appendOperateRecords) {
		if (this.operateRecords == -1) {
			this.operateRecords = 0;
		}
		
		this.operateRecords += appendOperateRecords;
	}

	/**
	 * Gets the 保存操作记录额外信息;.
	 * 
	 * @return 返回 operateMessage。
	 */
	public String getOperateMessage() {
		return operateMessage;
	}

	/**
	 * Sets the 保存操作记录额外信息;.
	 * 
	 * @param operateMessage
	 *            要设置的 operateMessage。
	 */
	public void setOperateMessage(String operateMessage) {
		this.operateMessage = operateMessage;
	}
	
	/**
	 * 
	 * appendOperateMessage:
	 * 
	 * 累加,操作信息;
	 *
	 * @param appendOperateMessage  
	 * @return void  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-12
	 */
	public void appendOperateMessage (String appendOperateMessage) {
		this.operateMessage += appendOperateMessage + ";";
	}
	
	/**
	 * 得到HttpRequest
	 * 
	 * @return 获得的HttpRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到HttpSession
	 * 
	 * @return 获得的HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 得到HttpResponse
	 * 
	 * @return 获得的HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 得到上下文路径，例如：/mylearningii
	 * 
	 * @return 上下文路径
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * 得到服务全路径，
	 * 
	 * @return 服务全路径
	 */
	public String getBasePath() {
		String path = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName();

		if ( this.getRequest().getServerPort() != 80 ) {
			basePath += ":" + this.getRequest().getServerPort() + path;
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
	public String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * 获得用户的真实IP
	 * 
	 * @return 用户的真实IP
	 */
	public String getRealIP() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		
		return ip;
	}
	
	/**
	 * 获得请求的referer
	 * 
	 * @return referer
	 */
	public String getReferer() {
		String referer = getRequest().getHeader("referer");
		if (referer != null && !referer.equalsIgnoreCase("")) {
			referer = referer.substring(0, referer.lastIndexOf("/")+1);
		} else {
			referer = "";
		}
		
		return referer;
	}

	/**
	 * 
	 * writeJson:
	 * 
	 * 向页面输出JSON对象;
	 *
	 * @param obj  
	 * @return void  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-14
	 */
    public void writeJson(Object obj) {

        String strJson = null;
        try {

            ObjectMapper om = new ObjectMapper();
            try {
                strJson = om.writeValueAsString(obj);
            } catch (Exception e) {
                System.out.println("Error when translate object to json\n" + e.getMessage());
                e.printStackTrace();
            }


            this.getResponse().setCharacterEncoding("UTF-8");
            this.getResponse().getWriter().write(strJson);
            this.getResponse().getWriter().flush();
            this.getResponse().getWriter().close();
        } catch (Exception e) {
            LOG.error("writeJson error!msg:\n" + e.getMessage());
        }
    }

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	
}
