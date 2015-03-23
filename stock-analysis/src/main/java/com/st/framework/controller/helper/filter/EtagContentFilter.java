package com.st.framework.controller.helper.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.zip.CRC32;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * ClassName:EtagFilter Reason: ETag 拦截器
 * 
 * @author yangzhenyu
 * @version
 * @since Ver 1.0.0
 * @Date 2012 2012-11-27 下午03:21:54
 * @see
 */
public class EtagContentFilter extends BaseFilter {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(EtagContentFilter.class);

	/**
	 * 配置文件的根节点
	 */
	private static Element root;

	/** ETag 缓存路径的请求集合 */
	private static List<String> etagRes = null;

	/**
	 * 资源失效时间,单位小时; 默认24小时失效;
	 */
	private static int failedTime = 1000*60*60*24;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

//		String actionNameSpace = getRequestActionNameSpace(httpRequest);
//		logger.info("doFilter(ServletRequest, ServletResponse, FilterChain) - String actionNameSpace="
//				+ actionNameSpace);

//		String url = httpRequest.getRequestURL().toString();
//		logger.info("doFilter(ServletRequest, ServletResponse, FilterChain) - String url="
//				+ url);

//		logger.info("httpRequest.getServletPath()="
//				+ httpRequest.getServletPath());

		if (isETagURL(httpRequest)) {
			appendEtag(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig config) throws ServletException {
		this.getETagResources();
	}

	@SuppressWarnings("static-access")
	public void destroy() {
		this.etagRes = null;
	}

	/**
	 * 
	 * appendEtag:对header增加ETag
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @return void
	 * @throws
	 * @since 　Ver 1.0 DATE: 2012-11-27
	 */
	private void appendEtag(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		WapperedResponse wapper = new WapperedResponse(httpResponse);

		chain.doFilter(request, wapper);
		
//		if (logger.isInfoEnabled()) {
//			String url = httpRequest.getRequestURL().toString();
//			logger.info("appendEtag(ServletRequest, ServletResponse, FilterChain) - String url=" + url);
//		}
		

		byte[] bytes = wapper.getResponseData();

		CRC32 crc = new CRC32();
		crc.update(bytes);
		String token = ("W/\"" + bytes.length + "-" + crc.getValue() + '"');
		logger.info("appendEtag - String token=" + token);

		httpResponse.setHeader("ETag", token);

		// always store the ETag in the header
		String previousToken = httpRequest.getHeader("If-None-Match");

//		logger.info("appendEtag - String previousToken=" + previousToken);

		long adddaysM = failedTime;
		long header = httpRequest.getDateHeader("If-Modified-Since");
//		logger.info("appendEtag(ServletRequest, ServletResponse, FilterChain) - long header="
//				+ new Date(header));

		long now = System.currentTimeMillis();
//		logger.info("appendEtag(ServletRequest, ServletResponse, FilterChain) - long now="
//				+ now);
//
//		logger.info("(header + adddaysM )=" + (header + adddaysM)
//				+ ";now=" + now + ";###" + ((header + adddaysM) - now));

		//判断是否返回304;
		if (previousToken != null && previousToken.equals(token)
				&& (header + adddaysM) > now) {
			// compare previous token with current one

			// System.out.println("ETag match: returning 304 Not Modified");
			httpResponse.sendError(HttpServletResponse.SC_NOT_MODIFIED);
			// use the same date we sent when we created the ETag the first time
			// through
			httpResponse.setHeader("Last-Modified",
					httpRequest.getHeader("If-Modified-Since"));
		} else {
			// first time through - set last modified time to now
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MILLISECOND, 0);
			Date lastModified = cal.getTime();
			if (logger.isInfoEnabled()) {
				logger.info("appendEtag() - Date lastModified=" + lastModified);
			}
			
			httpResponse.setDateHeader("Last-Modified", lastModified.getTime());
						
//			logger.info("Writing body content");
			
			httpResponse.setContentLength(bytes.length);

			ServletOutputStream sos = httpResponse.getOutputStream();

			sos.write(bytes);
			sos.flush();
			sos.close();
		}

	}

	/**
	 * 内部类;
	 * 
	 * ClassName:WapperedResponse Reason: 获得拦截内容流
	 * 
	 * @author yangzhenyu
	 * @version EtagContentFilter
	 * @since Ver 1.0.0
	 * @Date 2012 2012-11-28 上午10:12:42
	 * @see
	 */
	class WapperedResponse extends HttpServletResponseWrapper {
		private ByteArrayOutputStream buffer = null;

		private ServletOutputStream out = null;

		private PrintWriter writer = null;

		protected String type;

		public WapperedResponse(HttpServletResponse resp) throws IOException {
			super(resp);
			buffer = new ByteArrayOutputStream();// 真正存储数据的流
			out = new WapperedOutputStream(buffer);
			writer = new PrintWriter(new OutputStreamWriter(buffer, "utf-8"));
			// System.out.println("1===="+super.getContentType());
		}

		// 重载父类获取outputstream的方法
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// System.out.println("3===="+super.getContentType());
			return out;
		}

		// 重载父类获取writer的方法
		@Override
		public PrintWriter getWriter() throws UnsupportedEncodingException {
			// System.out.println("2===="+super.getContentType());
			type = super.getContentType();

			return writer;
		}

		// 重载父类获取flushBuffer的方法
		@Override
		public void flushBuffer() throws IOException {
			if (out != null) {
				out.flush();
			}
			if (writer != null) {
				writer.flush();
			}
		}

		@Override
		public void reset() {
			buffer.reset();
		}

		/**
		 * 获得数据byte数组;
		 * 
		 * getResponseData:
		 * 
		 * @return
		 * @throws IOException
		 * @return byte[]
		 * @throws
		 * @since 　Ver 1.1 DATE: 2012-11-27
		 */
		public byte[] getResponseData() throws IOException {
			// System.out.println("44="+super.getContentType());
			flushBuffer();// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
			return buffer.toByteArray();
		}

		// 内部类，对ServletOutputStream进行包装
		private class WapperedOutputStream extends ServletOutputStream {
			private ByteArrayOutputStream bos = null;

			public WapperedOutputStream(ByteArrayOutputStream stream)
					throws IOException {
				bos = stream;
			}

			@Override
			public void write(int b) throws IOException {
				bos.write(b);
			}

			@Override
			public boolean canWrite() {
				
				return false;				
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {
			}
		}
	}
	
	/**
	 * 内部方法;
	 * 
	 * 判断是否拦截;
	 * 
	 * isETagURL:
	 *
	 * @param request
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-1-25
	 */
	private boolean isETagURL (HttpServletRequest request) {
		String temp = null;
		String uri = request.getRequestURI();
//		if (logger.isInfoEnabled()) {
//			logger.info("isETagURL() - String uri=" + uri);
//		}
		
//		String tn = this.getRequestTypeName(request);
//		if (logger.isInfoEnabled()) {
//			logger.info("isETagURL() - String tn=" + tn);
//		}
		
		if (REQUEST_ACTION.equalsIgnoreCase(this.getRequestTypeName(request))
				|| REQUEST_JSP.equalsIgnoreCase(this.getRequestTypeName(request))
//				|| REQUEST_DWR.equalsIgnoreCase(this.getRequestTypeName(request))
		) {
			for (int i=0; i<etagRes.size(); i++) {
				temp = etagRes.get(i);
				if (uri.indexOf(temp) >= 0) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * 
	 * load:载入配置文件
	 * 
	 * @return void
	 * @throws
	 * @since 　Ver 1.0 DATE: 2012-11-27
	 */
	public void load() {

		// 构造一个SAXReader对象
		SAXReader reader = new SAXReader();
		try {
			// 获得xml文件的Document对象
			Document document = reader.read(this.getClass()
					.getResourceAsStream("/etag.xml"));
			// 获得Document对象的根节点
			this.root = document.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取资源信息列表,调用之前需要先执行load()方法;
	 * 
	 * getETagResources:
	 * 
	 * @return void
	 * @throws
	 * @since 　Ver 1.1 DATE: 2012-11-28
	 */
	@SuppressWarnings("static-access")
	public void getETagResources() {
		logger.info("getETagResources() - start");

		this.load();
		try {
			if (etagRes == null) {
				etagRes = new ArrayList<String>();
				Iterator<Element> it = root.elementIterator("etagurl");

				Element tmpElement = null;
				while (it.hasNext()) {
					tmpElement = (Element) it.next();
					etagRes.add(tmpElement.getTextTrim());
				}
			}

			this.failedTime = 1000*60*60*Integer.parseInt(root.element("failedTime")
					.getTextTrim());
		} catch (Exception ex) {
			logger.error("getETagResources() - exception ignored", ex);

		}

		logger.info("getETagResources() - end");
	}

}
