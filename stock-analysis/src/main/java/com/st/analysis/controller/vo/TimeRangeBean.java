package com.st.analysis.controller.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TimeRangeBean {

	private Date startTimeId;

	private Date endTimeId;

	public Date getStartTimeId() {
		return startTimeId;
	}

	public void setStartTimeId(Date startTimeId) {
		this.startTimeId = startTimeId;
	}

	public Date getEndTimeId() {
		return endTimeId;
	}

	public void setEndTimeId(Date endTimeId) {
		this.endTimeId = endTimeId;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
