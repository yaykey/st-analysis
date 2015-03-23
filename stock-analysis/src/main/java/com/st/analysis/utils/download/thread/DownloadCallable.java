package com.st.analysis.utils.download.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.analysis.utils.download.ReturnBean;
import com.st.framework.utils.LoadConfigUtils;

import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;

import java.io.File;

import java.io.IOException;

import java.io.RandomAccessFile;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 负责文件下载的类
 */
public class DownloadCallable implements Callable<ReturnBean> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DownloadCallable.class);

	/**
	 * 
	 * 待下载的文件
	 */

	private String url = null;

	/**
	 * 
	 * 本地文件名;
	 */

	private String fileName = null;

	/**
	 * 保存路径;
	 */
	private String savePath = "";

	/**
	 * 
	 * 偏移量
	 */

	private long offset = 0;

	/**
	 * 
	 * 分配给本线程的下载字节数
	 */

	private long length = 0;

//	/**
//	 * 
//	 * 回调
//	 * 
//	 */
//	private Runnable callback;

	public DownloadCallable(String url, String savePath) {
		this.url = url;
		this.savePath = savePath;
	}

	public ReturnBean call() {
		if (logger.isDebugEnabled()) {
			logger.debug("run() - start");
		}

		ReturnBean returnBean = new ReturnBean();

		BufferedInputStream bis = null;
		RandomAccessFile raf = null;

		try {

			HttpURLConnection httpConnection = (HttpURLConnection) new URL(
					this.url).openConnection();

			httpConnection.setRequestMethod("GET");

			int responseCode = httpConnection.getResponseCode();

			/*
			 * if (logger.isInfoEnabled()) {
			 * logger.info("getRemoteFileSize(String) - int responseCode=" +
			 * responseCode); }
			 */

			if (responseCode >= 400) {
				throw new RuntimeException("Web服务器响应错误 " + responseCode);
			}

			try {
				this.fileName = this.getRemoteFileName(httpConnection);
			} catch (Exception ex) {
				returnBean.setSuccess(false);
				logger.warn(ex.getMessage());
			}

			// httpConnection.setRequestProperty("RANGE", "bytes=" + this.offset
			//
			// + "-" + (this.offset + this.length - 1));
			//
			// System.out.println("RANGE bytes=" + this.offset + "-"
			// + (this.offset + this.length - 1));

			bis = new BufferedInputStream(httpConnection.getInputStream());

			byte[] buff = new byte[1024];
			int bytesRead;

			if (this.fileName != null) {

				File newPathFile = new File(savePath);

				if (!newPathFile.exists()) {
					newPathFile.mkdirs();
				}

				File newFile = new File(savePath + "/" + this.fileName);

				if (!newFile.exists()) {
					newFile.createNewFile();
				}

				raf = new RandomAccessFile(newFile, "rw");

				while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
					raf.seek(this.offset);
					raf.write(buff, 0, bytesRead);
					this.offset = this.offset + bytesRead;
				}

				raf.close();

				returnBean.setFilename(this.fileName);
			} else {
				String error = "";
				StringBuffer buffer = new StringBuffer();

				while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
					// raf.seek(this.offset);
					// raf.write(buff, 0, bytesRead);
					// this.offset = this.offset + bytesRead;

					buffer.append(new String(buff, 0, bytesRead, "GBK"));
				}

				error = buffer.toString();

				// error = new String(error.getBytes("GBK"), "UTF-8");

				// logger.info(error);
				error = error.replaceAll("</?script.*>", "").replaceAll(
						"[\r\n]*", "");

				// String pstr = "\"([^\"|[\u4e00-\u9fa5]]+)\"";
				String pstr = "alert\\(\"(.*)\"\\);";
				Pattern pattern = Pattern.compile(pstr);

				// Pattern pattern = Pattern.compile("window\\.close");
				Matcher matcher = pattern.matcher(error);

				// logger.error("matcher.matches()=========" + matcher.find());

				if (matcher.find()) {
					// logger.error("matcher.groupCount()=========" +
					// matcher.groupCount());
					//
					// for (int i=0; i<matcher.groupCount(); i++) {
					// error = matcher.group(i);
					// logger.error(i + ":" + error);
					// }
					error = matcher.group(1);
					// logger.error(1 + ":" + error);

				}

				//logger.info(error);

				returnBean.setSuccess(false);
				returnBean.setReturnMessage(error);
			}

			bis.close();

		} catch (IOException ioe) {
			logger.error("run()", ioe);

			ioe.printStackTrace();

			logger.error(this.url);

			try {
				raf.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			returnBean.setSuccess(false);

			throw new RuntimeException("下载文件异常", ioe);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("run() - end");
		}

		return returnBean;
	}

	protected String getRemoteFileName(HttpURLConnection httpConnection) {
		String filename = null;

		try {
			// Content-Disposition
			// attachment; filename="sz300002_³É½»Ã÷Ï¸_2014-01-02.xls"
			filename = httpConnection.getHeaderField("Content-Disposition");

			filename = filename.trim();

			filename = filename.replaceAll("^attachment; filename=\"", "");

			filename = filename.replaceAll("\"$", "");

			filename = new String(filename.getBytes("iso8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException", e);
		} catch (NullPointerException e) {
			logger.warn(httpConnection.getURL());
			throw new RuntimeException("获得下载文件名异常", e);
		}

		return filename;
	}

	public String getFileName() {
		// synchronized (fileName) {
		return fileName;
		// }
	}

	public void setFileName(String fileName) {
		// synchronized (fileName) {
		this.fileName = fileName;
		// }
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

//	public Runnable getCallback() {
//		return callback;
//	}
//
//	public void setCallback(Runnable callback) {
//		this.callback = callback;
//	}

}
