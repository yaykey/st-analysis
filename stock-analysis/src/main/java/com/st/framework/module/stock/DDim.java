package com.st.framework.module.stock;

import java.util.Date;

public class DDim extends DDimKey implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5583153905330069578L;

	private String dimtypeName;

	private String dimCode;

	private String dimName;

	private Date updDate;

	public String getDimtypeName() {
		return dimtypeName;
	}

	public void setDimtypeName(String dimtypeName) {
		this.dimtypeName = dimtypeName == null ? null : dimtypeName.trim();
	}

	public String getDimCode() {
		return dimCode;
	}

	public void setDimCode(String dimCode) {
		this.dimCode = dimCode == null ? null : dimCode.trim();
	}

	public String getDimName() {
		return dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName == null ? null : dimName.trim();
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
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
		DDim other = (DDim) that;
		return (this.getDimtypeId() == null ? other.getDimtypeId() == null
				: this.getDimtypeId().equals(other.getDimtypeId()))
				&& (this.getDimId() == null ? other.getDimId() == null : this
						.getDimId().equals(other.getDimId()))
				&& (this.getDimtypeName() == null ? other.getDimtypeName() == null
						: this.getDimtypeName().equals(other.getDimtypeName()))
				&& (this.getDimCode() == null ? other.getDimCode() == null
						: this.getDimCode().equals(other.getDimCode()))
				&& (this.getDimName() == null ? other.getDimName() == null
						: this.getDimName().equals(other.getDimName()))
				&& (this.getUpdDate() == null ? other.getUpdDate() == null
						: this.getUpdDate().equals(other.getUpdDate()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDimtypeId() == null) ? 0 : getDimtypeId().hashCode());
		result = prime * result
				+ ((getDimId() == null) ? 0 : getDimId().hashCode());
		result = prime
				* result
				+ ((getDimtypeName() == null) ? 0 : getDimtypeName().hashCode());
		result = prime * result
				+ ((getDimCode() == null) ? 0 : getDimCode().hashCode());
		result = prime * result
				+ ((getDimName() == null) ? 0 : getDimName().hashCode());
		result = prime * result
				+ ((getUpdDate() == null) ? 0 : getUpdDate().hashCode());
		return result;
	}

	public DDim clone() {
		DDim o = null;
		try {
			o = (DDim) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return o;
	}
}