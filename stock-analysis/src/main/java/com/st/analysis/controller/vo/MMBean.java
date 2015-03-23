package com.st.analysis.controller.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MMBean {

	private String dateIdStr;

	private Integer dateId;

	private double maxPrice;

	private double minPrice;

	private String maxTimeId;

	private String minTimeId;

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxTimeId() {
		return maxTimeId;
	}

	public void setMaxTimeId(String maxTimeId) {
		this.maxTimeId = maxTimeId;
	}

	public String getMinTimeId() {
		return minTimeId;
	}

	public void setMinTimeId(String minTimeId) {
		this.minTimeId = minTimeId;
	}

	public String toString() {
//		return ToStringBuilder.reflectionToString(this,
//				ToStringStyle.SIMPLE_STYLE);
		
		return ToStringBuilder.reflectionToString(this);
	}

	public String getDateIdStr() {
		return dateIdStr;
	}

	public void setDateIdStr(String dateIdStr) {
		this.dateIdStr = dateIdStr;
	}

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

}
