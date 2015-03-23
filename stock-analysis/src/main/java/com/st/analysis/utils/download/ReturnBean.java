package com.st.analysis.utils.download;



import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.st.framework.module.PersistentObject;



/**
 * The Class ReturnBean.
 */
public class ReturnBean extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1652498851352949237L;

	/**
	 * 操作是否成功;默认true;
	 */
	private boolean success = true;
	
//	private String 
	
	/**
	 * 下载文件名;
	 */
	private String filename;
	
//	/**
//	 * stock code;
//	 */
//	private String stCode;
	
	
	/**
	 * 操作返回信息;
	 */
	private String returnMessage;

	/**
	 * 下载文件bean;
	 */
	private DownloadFileBean downloadFileBean;

	/**
	 * Checks if is 操作是否成功.
	 *
	 * @return the 操作是否成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the 操作是否成功.
	 *
	 * @param success the new 操作是否成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the 下载文件bean;.
	 *
	 * @return the 下载文件bean;
	 */
	public DownloadFileBean getDownloadFileBean() {
		return downloadFileBean;
	}

	/**
	 * Sets the 下载文件bean;.
	 *
	 * @param downloadFileBean the new 下载文件bean;
	 */
	public void setDownloadFileBean(DownloadFileBean downloadFileBean) {
		this.downloadFileBean = downloadFileBean;
	}

	/**
	 * Gets the 操作返回信息;.
	 *
	 * @return the 操作返回信息;
	 */
	public String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * Sets the 操作返回信息;.
	 *
	 * @param returnMessage the new 操作返回信息;
	 */
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	/**
	 * Gets the 下载文件名;.
	 *
	 * @return the 下载文件名;
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the 下载文件名;.
	 *
	 * @param filename the new 下载文件名;
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String toString () {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

//	public String getStCode() {
//		return stCode;
//	}
//
//	public void setStCode(String stCode) {
//		this.stCode = stCode;
//	}
}
