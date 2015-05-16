package com.st.framework.module.stock;

import java.util.Date;

import com.st.framework.module.PersistentObject;

public class GLHBReportKey extends PersistentObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6393767838211338958L;

	private String stockCode;

    private Date dateId;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public Date getDateId() {
        return dateId;
    }

    public void setDateId(Date dateId) {
        this.dateId = dateId;
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
        GLHBReportKey other = (GLHBReportKey) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        return result;
    }
}