package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class DDimtype extends PersistentObject implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 655892243912956807L;

	private Integer dimtypeId;

	private String dimtypeName;

	private String dimtypeCode;

	public Integer getDimtypeId() {
		return dimtypeId;
	}

	public void setDimtypeId(Integer dimtypeId) {
		this.dimtypeId = dimtypeId;
	}

	public String getDimtypeName() {
		return dimtypeName;
	}

	public void setDimtypeName(String dimtypeName) {
		this.dimtypeName = dimtypeName == null ? null : dimtypeName.trim();
	}

	public String getDimtypeCode() {
		return dimtypeCode;
	}

	public void setDimtypeCode(String dimtypeCode) {
		this.dimtypeCode = dimtypeCode == null ? null : dimtypeCode.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		DDimtype other = (DDimtype) that;
		return (this.getDimtypeId() == null ? other.getDimtypeId() == null
				: this.getDimtypeId().equals(other.getDimtypeId()))
				&& (this.getDimtypeName() == null ? other.getDimtypeName() == null
						: this.getDimtypeName().equals(other.getDimtypeName()))
				&& (this.getDimtypeCode() == null ? other.getDimtypeCode() == null
						: this.getDimtypeCode().equals(other.getDimtypeCode()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDimtypeId() == null) ? 0 : getDimtypeId().hashCode());
		result = prime
				* result
				+ ((getDimtypeName() == null) ? 0 : getDimtypeName().hashCode());
		result = prime
				* result
				+ ((getDimtypeCode() == null) ? 0 : getDimtypeCode().hashCode());
		return result;
	}

	public DDimtype clone() {
		DDimtype o = null;
		try {
			o = (DDimtype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return o;
	}
}