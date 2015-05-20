package com.st.analysis.controller.vo.lhb;

import java.util.Date;

import com.st.framework.module.PersistentObject;

public class TopBean extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8637268415477884349L;

	private String stockCode;
	
	private String stockName;

    private String dateId;

    private Long secId;
	
	private String secName;

	private double buyAmount = 0.0;

	private double sellAmount = 0.0;

	private double netAmount = 0.0;

	private String type;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public Long getSecId() {
		return secId;
	}

	public void setSecId(Long secId) {
		this.secId = secId;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public double getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(double buyAmount) {
		this.buyAmount = buyAmount;
	}

	public double getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(double sellAmount) {
		this.sellAmount = sellAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	
}
