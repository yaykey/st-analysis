package com.st.analysis.utils.stock.bean.sohu;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SohuHqBean {
	
	private List<String[]> hq;

	public List<String[]> getHq() {
		return hq;
	}

	public void setHq(List<String[]> hq) {
		this.hq = hq;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
