package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;
import java.util.Date;

public class GDividendSchemeKey extends PersistentObject {
    private String stockCode;

    private Date announcementDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
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
        GDividendSchemeKey other = (GDividendSchemeKey) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getAnnouncementDate() == null ? other.getAnnouncementDate() == null : this.getAnnouncementDate().equals(other.getAnnouncementDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getAnnouncementDate() == null) ? 0 : getAnnouncementDate().hashCode());
        return result;
    }
}