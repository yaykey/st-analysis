package com.st.analysis.controller.vo.timerange;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TimeRangeCountBeanResponse {

	private String rangeName;
	
	private int rangeCount;

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public int getRangeCount() {
		return rangeCount;
	}

	public void setRangeCount(int rangeCount) {
		this.rangeCount = rangeCount;
	}
	
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
