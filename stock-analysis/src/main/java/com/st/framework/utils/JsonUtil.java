/**
 * @(#)JsonUtil.java  2011-5-30
 */
package com.st.framework.utils;

import java.io.IOException;
import java.io.OutputStream;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类 <code>JsonUtil</code>
 * 
 * 处理json的工具类
 * 
 * 基于重用的ObjectMapper更高效
 * 
 * @author wangwenyao@feinno.com
 * @version 2011-5-30
 */
public class JsonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将对象以json格式写入输出流中
	 * @param out
	 * @param value
	 */
	public static void write(OutputStream out, Object value) {

		try {
			mapper.writeValue(out, value);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	
}
