/*
 * 文件名： CookieUtil.java
 * 
 * 创建日期： 2012-8-27
 *
 * 原始作者: <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 */
package com.st.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil.
 * 
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 * @version $Revision: 1.1 $
 * @since 2012-8-27
 */
public class CookieUtil {

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		String returnStr = "";
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String cName = null;
		String cValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				cName = sCookie.getName();
				cValue = sCookie.getValue();
				if (cName.equals(cookieName))
					returnStr = cValue;
			}

		}
		return returnStr;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param cookieTime
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, int cookieTime) {
		
		setCookie(request, response, cookieName, cookieValue, cookieTime, null);
	}
	
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, int cookieTime, String path) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(cookieTime);
		
		response.addCookie(cookie);
		
		if (path != null) {
			cookie.setPath(path);
		}
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void clearCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
