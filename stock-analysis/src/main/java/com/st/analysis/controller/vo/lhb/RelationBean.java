package com.st.analysis.controller.vo.lhb;

import java.util.List;

import com.st.framework.module.PersistentObject;

public class RelationBean extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5783659885996172290L;

	private int total;

	private List<TopBean> rows;
	
	private List footer;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<TopBean> getRows() {
		return rows;
	}

	public void setRows(List<TopBean> rows) {
		this.rows = rows;
		if (this.rows != null) {
			this.setTotal(this.rows.size());
		}
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
