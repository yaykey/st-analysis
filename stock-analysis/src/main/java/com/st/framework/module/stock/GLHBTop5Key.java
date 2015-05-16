package com.st.framework.module.stock;

import java.util.Date;

import com.st.framework.module.PersistentObject;

public class GLHBTop5Key extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7179926151240497335L;

	private String stockCode;

    private Date dateId;

    private Long secId;

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

    public Long getSecId() {
        return secId;
    }

    public void setSecId(Long secId) {
        this.secId = secId;
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
        GLHBTop5Key other = (GLHBTop5Key) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getSecId() == null ? other.getSecId() == null : this.getSecId().equals(other.getSecId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getSecId() == null) ? 0 : getSecId().hashCode());
        return result;
    }
}