/*
 * 文件名： ConfigUtil.java
 * 
 * 创建日期： 2011-4-11
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 配置文件搜索类
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision: 1.1 $
 *
 * @Date 上午11:16:58
 *
 * @since 2011-4-11
 */
public class ConfigUtil {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ConfigUtil.class);

	private static final String WEB_CONFIG_ROOT_NAME = "/WEB-INF";
	
	public static final String ABSOLUTE_CLASS_PATH = ConfigUtil.class.getClassLoader().getResource("").getFile();
	
	public static final String ABSOLUTE_WEBAPPLICATON_PATH = StringUtils.substringBefore(ABSOLUTE_CLASS_PATH, "WEB-INF");
	
	/**
	 * 获取配置文件绝对路径
	 * 
	 * @param configurationNameStart 配置文件名称开始字符串
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] getAbsoluteConfigurationPath(String configurationNameStart) {
		if(logger.isInfoEnabled()){
			logger.info("搜索目录=="+ABSOLUTE_WEBAPPLICATON_PATH) ;
		}
		Collection<File> col = FileUtils.listFiles(new File(ABSOLUTE_WEBAPPLICATON_PATH), new String[] { "xml" }, true);
		List<String> absolutePathList = new ArrayList<String>();
		for (File file : col)
			if (StringUtils.startsWith(file.getName(), configurationNameStart))
				absolutePathList.add(file.getAbsolutePath());
		String[] absolutePathArray = new String[absolutePathList.size()];
		return absolutePathList.toArray(absolutePathArray);
	}

	/**
	 * 获取配置文件相对路径, 即: /WEB-INF/classes开头的路径
	 * 
	 * @param configurationNameStart 配置文件名称开始字符串
	 * @return
	 */
	public static String[] getConfiguationPath(String configurationNameStart) {
		String[] absolutePathArray = getAbsoluteConfigurationPath(configurationNameStart);
		String[] pathArray = new String[absolutePathArray.length];
		for (int i = 0; i < absolutePathArray.length; i++)
			pathArray[i] = "WEB-INF" + StringUtils.substringAfter(absolutePathArray[i], "WEB-INF");
		return pathArray;
	}
	
	
	/**
	 * 获得classpath
	 * 
	 * @param name
	 *            The resource name
	 * 
	 * @return
	 */
	private static String getClassPath(String name) {
		try {
			return URLDecoder.decode(ConfigUtil.class.getClassLoader().getResource(name).getPath(), Charset.defaultCharset().toString());
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 在使用maven做test类的时候就不要WEB-INF了
	 * 
	 * @param testDir
	 * 
	 * @return 返回配置文件的根目录
	 */
	public static String getTestApplicationConfigPath(String testDir){
		String classPath = getClassPath(testDir);
		return StringUtils.substringBefore(classPath, WEB_CONFIG_ROOT_NAME);
	}
	
	private static AbstractApplicationContext factory = null;
	
	public static AbstractApplicationContext getHelper () {
		
		if (factory == null) {
			factory=new FileSystemXmlApplicationContext(ConfigUtil.getAbsoluteConfigurationPath("spring"));

		}
		
		return factory;
	}
	
	public static void destroyFactory () {
		
		if (factory != null) {			
			factory.stop();
			factory.close();
		}
		
	}
	
}
