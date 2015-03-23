package com.st.analysis.utils.download;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DownloadFileBean {

	private String remoteFileUrl;

	private String savePath;

	private String stockCode;
	
	private String stockType;

	private String timeId;

	public String getRemoteFileUrl() {
		return remoteFileUrl;
	}

	public void setRemoteFileUrl(String remoteFileUrl) {
		this.remoteFileUrl = remoteFileUrl;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

}
