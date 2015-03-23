/*
 * 文件名： SpringXmlWebApplicationContext.java
 * 
 * 创建日期： 2011-4-11
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.utils.config;

import org.springframework.web.context.support.XmlWebApplicationContext;

import com.st.framework.utils.ConfigUtil;

/**
 * 搜索指定的目录下Spring WebContext的配置文件,用于Spring调用
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision: 1.1 $
 *
 * @Date 上午11:14:28
 *
 * @since 2011-4-11
 */
public class SpringXmlWebApplicationContext extends XmlWebApplicationContext{

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.web.context.support.XmlWebApplicationContext#
	 * getDefaultConfigLocations()
	 */
	protected String[] getDefaultConfigLocations() {
		String[] returnStringArray = ConfigUtil.getConfiguationPath("spring") ;
		return returnStringArray ;
	}
}
