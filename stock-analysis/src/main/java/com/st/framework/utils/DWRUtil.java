package com.st.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class DWRUtil {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DWRUtil.class);

	public static final String[][] DWRServletFiles = { 
		{ "/dwr/interface/", "PageQueryCondition.js" } ,
		{ "/wro/", "dwr.js" } 
	};

	public static final String localFilePath = "/js/dwr/";

	/**
	 * 外部使用接口.
	 * 将dwr servlet生成的文件保存到本地.
	 * 
	 * @param basePath
	 * @param realPath
	 */
	public static void Servlet2File(String basePath, String realPath) {
		if (logger.isDebugEnabled()) {
			logger.debug("Servlet2File(String, String) - start");
		}

		try {
			taskFile(basePath, realPath);
		} catch (Exception ex) {			
			logger.warn("Servlet2File(String, String) - Exception ex=" + ex.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Servlet2File(String, String) - end");
		}
	}

	/**
	 * 使用线程操作.
	 * 
	 * @param basePath
	 * @param realPath
	 */
	private static void taskFile(final String basePath, final String realPath) {
		Thread thread = new Thread() {

			public void run() {

				super.run();

				for (int i=0; i<DWRServletFiles.length; i++) {
					
					String[] servletFile = DWRServletFiles[i];
					refreshFile(basePath, realPath, servletFile);

				}
			}

		};

		thread.start();
	}

	/**
	 * 内部方法;生成本地文件;
	 * 
	 * @param basePath
	 * @param realPath
	 * @param servletFile
	 */
	private static void refreshFile(String basePath, String realPath,
			String[] servletFile) {
		if (logger.isDebugEnabled()) {
			logger.debug("refreshFile() - start");
		}

		RandomAccessFile file = null;
		try {

			// ---本地文件---//
			String fileNamePath = realPath + localFilePath + servletFile[1];
			file = new RandomAccessFile(fileNamePath, "rw");
			file.seek(0); // 从0开始
			file.setLength(0); // 清空历史数据;

			// ----dwr servlet 文件---//
			URL url = new URL(basePath + servletFile[0] + servletFile[1]);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			// --读取保存数据--//
			String str;
			while ((str = in.readLine()) != null) {
				file.writeBytes(str);
			}

			in.close();

		} catch (JsonGenerationException e) {
			logger.error("refreshFile() - exception ignored", e);
		} catch (JsonMappingException e) {
			logger.error("refreshFile() - exception ignored", e);
		} catch (IOException e) {
			logger.error("refreshFile() - exception ignored", e);

		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				logger.error("refreshFile(Long)", e);

				e.printStackTrace();

			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("refreshFile() - end");
		}
	}
}
