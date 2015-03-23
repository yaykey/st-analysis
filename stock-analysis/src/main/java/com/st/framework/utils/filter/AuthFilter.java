package com.st.framework.utils.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 过滤sql注入,非法请求,url刷新过快
 *
 * <p>类名:AuthFilter.java</p>
 * <p>描述信息:</p>
 */
public class AuthFilter implements Filter
{

	/**常量*/
	/**操作日志对象*/
	private static Log logger = LogFactory.getLog(AuthFilter.class);

	/**请求类型*/
	private static final String REQUEST_JSP = "jsp";

	private static final String REQUEST_ACTION = "do";

	private static final String REQUEST_OTHER = "other";

	/**不受访问的请求集合*/
	public static ArrayList unProtectedRes = null;

	/**配置文件的根节点*/
	private static Element root;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig chain) throws javax.servlet.ServletException
	{

		getUnprotectedResources();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException,
			javax.servlet.ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		try
		{
			//获得请求URL
			String url = httpRequest.getRequestURL().toString();
			String param = httpRequest.getQueryString();
			
			boolean isUnprotected = isUnprotectedUrl(httpRequest);
//			System.out.println("------------isUnprotected------------------"+isUnprotected);
			if (isUnprotected)
			{
				try{
					
					chain.doFilter(request, response);
					
				}catch(Exception ee){
					
					ee.printStackTrace();
				}
//				httpResponse.sendRedirect(getRootPath(httpRequest));
				return;
			}else{
				httpResponse.sendRedirect(getRootPath(httpRequest));
			}
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否是不受权限保护的
	 * 
	 * @param request
	 * @return
	 */
	private boolean isUnprotectedUrl(HttpServletRequest request)
	{

		//如果在不受访问资源中,返回true
		for (int i = 0; i < unProtectedRes.size(); i++)
		{
			String temp = (String) unProtectedRes.get(i);
//			System.out.println("URL ======="+this.getRequestActionNameSpace(request));
			if (!this.getRequestActionNameSpace(request).equals("/") && temp.indexOf(this.getRequestActionNameSpace(request))>=0)
			{
				return false;
			}
			
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy()
	{

		this.unProtectedRes = null;
		this.root = null;
	}

	/**
	 * 载入配置文件
	 */
	public void load()
	{

		//构造一个SAXReader对象
		SAXReader reader = new SAXReader();
		try
		{
			//获得xml文件的Document对象
			Document document = reader
					.read(this.getClass().getResourceAsStream(
							"/auth.xml"));
			//获得Document对象的根节点
			this.root = document.getRootElement();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取不受访问限制的资源信息列表,调用之前需要先执行load()方法;
	 */
	public void getUnprotectedResources()
	{

		this.load();
		if (unProtectedRes == null)
		{
			unProtectedRes = new ArrayList();
			Iterator it = root.elementIterator("unprotectedurl");
			Element tmpElement = null;
			while (it.hasNext())
			{
				tmpElement = (Element) it.next();
				unProtectedRes.add(tmpElement.getTextTrim());
			}
		}
	}
	

	/**
	 * 获得应用的地址，例如：http://127.0.0.1:8080/dataplatform
	 * 
	 * @param request
	 * @return
	 */
	private String getRootPath(HttpServletRequest request)
	{

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 80)
		{
			basePath += ":" + request.getServerPort() + path;
		} else
		{
			basePath += path;
		}
		return basePath;
	}

	/**
	 * 获得请求的所在的名称空间，例如：http://localhost:8080/dataplatform/report/reportModel/test.jsp
	 * 
	 * 返回/report/reportModel
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 * @return 请求的Action所在的名称空间
	 */
	private String getRequestActionNameSpace(HttpServletRequest request)
	{

		String absoluteUrl = request.getRequestURL().toString();
		String nameSpaceStr = absoluteUrl.substring(getRootPath(request)
				.length(), absoluteUrl.lastIndexOf("/"));
		if (nameSpaceStr.trim().length() == 0 || nameSpaceStr == null)
		{
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
	private String getRequestPath(HttpServletRequest request)
	{

		String absoluteUrl = request.getRequestURL().toString();
		String nameSpaceStr = absoluteUrl.substring(getRootPath(request)
				.length());
		if (nameSpaceStr.trim().length() == 0 || nameSpaceStr == null)
		{
			nameSpaceStr = "/";
		}
		return nameSpaceStr;
	}
}
