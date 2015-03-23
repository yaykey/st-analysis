/*
 * 文件名： Global.java
 * 
 * 创建日期： 2011-4-18
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;

//import com.feinno.fdc.compute.config.MailConfig;
//import com.feinno.fdc.compute.config.SynConfig;
//import com.feinno.fdc.compute.config.MailConfig.MailConfigInfo;
//import com.feinno.fdc.compute.utils.FDCUtils;
//import com.feinno.fdc.compute.utils.hadooputil.HadoopFsInfo;
import com.st.framework.utils.LoadConfigUtils;


/**
 * 系统的全局变量.
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 * @version $Revision: 1.1 $
 * @Date 下午12:00:10
 * @since 2011-4-18
 */
public class Global {
	
	/** Logger for this class. */
	private static final Log logger = LogFactory.getLog(Global.class);

	

	/** 系统中的根路径. */
	public static String ROOT;

	/** 系统中的WEB-INFO. */
	public static String WEBINFO;

	/** 系统中的WEB根目录. */
	public static String WEB_CONTEXT_PATH;

	/** TOMCAT临时目录. */
	public static String TOMCAT_TEMP;

	/** TOMCAT根路径. */
	public static String TOMCAT_HOME;

	/** RAQ目录. */
	public static String RAQ;

	/** 下载文件目录. */
	public static String DOWN_LOAD;

	/** The FIL e_ upload. */
	public static String FILE_UPLOAD;

	/** The LOCA l_ ip. */
	public static String LOCAL_IP;

    /** 系统运行最大任务数. */
    public static final Integer SYS_MAX_TASK=10;

	/** spring上下文. */
	public static ApplicationContext _ctx = null;

	/** The TOMCA t_ port. */
	public static String TOMCAT_PORT = null;

	/** PAGESIZE 每页记录数. */
	public static final int PAGESIZE = 15;

	/** VIEWTYPE_FRONT 功能模块显示类型：前台. */
	public static final String VIEWTYPE_FRONT = "frontType";
	
	/** VIEWTYPE_BACK 功能模块显示类型：后台. */
	public static final String VIEWTYPE_BACK = "backType";

	/** MODULE_LEVEL_ONE 业务模块级别：一级模块. */
	public static final int MODULE_LEVEL_ONE = 1;

	/** MODULE_LEVEL_TWO 业务模块级别：二级模块. */
	public static final int MODULE_LEVEL_TWO = 2;

	/** MODULE_LEVEL_THREE业务模块级别：三级模块. */
	public static final int MODULE_LEVEL_THREE = 3;
	
	/** CHARSET 页面编码字符集. */
	public static final String CHARSET = "utf-8";

	/** ACTION_EXT 请求后缀名. */
	public static final String ACTION_EXT = "feinno";

	/** NAMESPACE_REPORT 获取报表的请求的命名空间. */
	public static final String NAMESPACE_REPORT = "/report/reportModel";
	
	/** ZERO   自然数 0. */
	public static final int ZERO = 0;

	/** 系统超管登陆id;. */
	public static final String ADMIN_LOGIN_ID = "admin";
	
	/** 超级管理员角色名称;. */
	public static final String ADMIN_ROLE_NAME = "超级管理员";
	
	/** 前台角色名称;. */
	public static final String FRONT_ROLE_NAME = "frontUser";

	
	
	
	/** 应用服务域名；. */
	public static String APP_DOMAIN_NAME;
	
	/** 应用TITLE. */
	public static String APP_TITLE;
	
	
	/** MAIL设置 */
	/**
	 * SMTP服务器地址
	 */
	public static String SMTP_HOST = LoadConfigUtils.getInstance()
			.getSmtpHost();

	/**
	 * 登陆SMTP的用户名
	 */
	public static String SMTP_USER = LoadConfigUtils.getInstance()
			.getSmtpUser();

	/**
	 * 登陆SMTP的密码
	 */
	public static String SMTP_PASSWORD = LoadConfigUtils.getInstance()
			.getSmtpPassword();
	
	/**
	 * 邮件修改密码链接失效时间;
	 */
	public static int MAIL_RESET_PASSWORD_EXPIRED = 60*30;

	/**
	 * 邮件的发送人地址
	 */
	public static String MAIL_FROM = LoadConfigUtils.getInstance()
			.getMailFrom();

	/** 系统中的根路径. */
	public static String TAG_PATH;

    
	
	/**
	 * Inits the path.
	 *
	 * @param realPath the real path
	 */
	public static void initPath(String realPath) {

		ROOT = realPath;
		WEBINFO = ROOT + "WEB-INF" + File.separator;
		TOMCAT_TEMP = System.getProperty("java.io.tmpdir") + File.separator;
		if (System.getProperty("java.io.tmpdir").lastIndexOf("\\") > 0) {
			TOMCAT_HOME = System.getProperty("java.io.tmpdir").substring(0,
					System.getProperty("java.io.tmpdir").lastIndexOf("\\"));
		} else if (System.getProperty("java.io.tmpdir").lastIndexOf("/") > 0) {
			TOMCAT_HOME = System.getProperty("java.io.tmpdir").substring(0,
					System.getProperty("java.io.tmpdir").lastIndexOf("/"));
		} else {
			TOMCAT_HOME = System.getProperty("java.io.tmpdir");
		}
		RAQ = ROOT + "report" + File.separator + "reportFiles" + File.separator;
		DOWN_LOAD = ROOT + "files" + File.separator;
		FILE_UPLOAD = ROOT + "upload" + File.separator;
		TAG_PATH= ROOT + "report" + File.separator+"tag" + File.separator;
		initLocalIp();
	}

	/**
	 * 初始化本机IP.
	 */
	public static void initLocalIp() {
		try {
			java.net.InetAddress inet = java.net.InetAddress.getLocalHost();
			LOCAL_IP = inet.getHostAddress();
		} catch (Exception e) {

		}

	}

	/**
	 * Inits the tomcat port.
	 */
	public static void initTomcatPort() {
		if (logger.isDebugEnabled()) {
			logger.debug("initTomcatPort() - start");
		}

		try {

//			String flag = "false";
			String fileName = (TOMCAT_HOME + "/conf/server.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File(fileName));
			Element element = doc.getRootElement();// 获取根元素
			getInfo(element);
		} catch (Exception e) {
//			logger.warn("initTomcatPort() - exception ignored", e);
			logger.warn("initTomcatPort() - exception ignored" + e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("initTomcatPort() - end");
		}
	}

	/**
	 * Gets the info.
	 *
	 * @param root the root
	 * @return the info
	 * @throws Exception the exception
	 */
	public static void getInfo(Element root) throws Exception {

		Iterator<Element> iter = root.elementIterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			// System.out.println(element.getName());
			if (element.getName().equals("Connector")) {
				if (element.attributeValue("protocol") != null
						&& element.attributeValue("protocol").toLowerCase()
								.indexOf("http") >= 0) {
					TOMCAT_PORT = element
							.attributeValue("tomcatPort");
				}
			}
			getInfo(element);// 自己调用自己 递归方法
		}

	}

	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * JS,CSS等静态资源自动版本号,保证系统更新是,用户浏览器自动更新.
	 * 
	 * 20140715
	 */
	public static final String RESOURCE_V = new SimpleDateFormat("yyyyMMdd").format(new Date());
		

	public static Integer [][] STOCK_PER_RANGE_LEGEND = {
		{9,},{8,9},{7,8},{6,7},{5,6},{4,5},{3,4},{2,3},{1,2},{0,1}
		,{-1,0},{-1,-2},{-2,-3},{-3,-4},{-4,-5},{-5,-6},{-6,-7},{-7,-8},{-8,-9},{-9,}
	};
	
	
	
}
