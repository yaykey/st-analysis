package com.st.framework.module.stock;

public class FactActiveDateIdIndex extends FactActiveDateIdIndexKey {
    private Integer dateYearId;

    public Integer getDateYearId() {
        return dateYearId;
    }

    public void setDateYearId(Integer dateYearId) {
        this.dateYearId = dateYearId;
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
        FactActiveDateIdIndex other = (FactActiveDateIdIndex) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getDateYearId() == null ? other.getDateYearId() == null : this.getDateYearId().equals(other.getDateYearId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getDateYearId() == null) ? 0 : getDateYearId().hashCode());
        return result;
    }
}