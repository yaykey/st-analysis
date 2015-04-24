package com.st.analysis.controller.vo.timerange;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TimeRangeCountBeanRequest {

	private String rangeTimeId;

	private String rangeName;
	
	public TimeRangeCountBeanRequest () {}

	public TimeRangeCountBeanRequest (String rangeTimeId, String rangeName) {
		this.rangeTimeId = rangeTimeId;
		this.rangeName = rangeName;
	}
	
	public String getRangeTimeId() {
		return rangeTimeId;
	}

	public void setRangeTimeId(String rangeTimeId) {
		this.rangeTimeId = rangeTimeId;
	}

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}
	
	public String toString () {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
