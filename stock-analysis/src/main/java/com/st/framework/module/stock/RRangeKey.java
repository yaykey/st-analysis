package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class RRangeKey extends PersistentObject {
    private Integer rptId;

    private Integer indexId;

    private Integer volDimtypeId;

    private Integer volDimId;

    private Integer dateId;

    private Integer stockCode;

    private Integer timeDimtypeId;

    private Integer timeDimId;

    public Integer getRptId() {
        return rptId;
    }

    public void setRptId(Integer rptId) {
        this.rptId = rptId;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getVolDimtypeId() {
        return volDimtypeId;
    }

    public void setVolDimtypeId(Integer volDimtypeId) {
        this.volDimtypeId = volDimtypeId;
    }

    public Integer getVolDimId() {
        return volDimId;
    }

    public void setVolDimId(Integer volDimId) {
        this.volDimId = volDimId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getStockCode() {
        return stockCode;
    }

    public void setStockCode(Integer stockCode) {
        this.stockCode = stockCode;
    }

    public Integer getTimeDimtypeId() {
        return timeDimtypeId;
    }

    public void setTimeDimtypeId(Integer timeDimtypeId) {
        this.timeDimtypeId = timeDimtypeId;
    }

    public Integer getTimeDimId() {
        return timeDimId;
    }

    public void setTimeDimId(Integer timeDimId) {
        this.timeDimId = timeDimId;
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
        RRangeKey other = (RRangeKey) that;
        return (this.getRptId() == null ? other.getRptId() == null : this.getRptId().equals(other.getRptId()))
            && (this.getIndexId() == null ? other.getIndexId() == null : this.getIndexId().equals(other.getIndexId()))
            && (this.getVolDimtypeId() == null ? other.getVolDimtypeId() == null : this.getVolDimtypeId().equals(other.getVolDimtypeId()))
            && (this.getVolDimId() == null ? other.getVolDimId() == null : this.getVolDimId().equals(other.getVolDimId()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getTimeDimtypeId() == null ? other.getTimeDimtypeId() == null : this.getTimeDimtypeId().equals(other.getTimeDimtypeId()))
            && (this.getTimeDimId() == null ? other.getTimeDimId() == null : this.getTimeDimId().equals(other.getTimeDimId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRptId() == null) ? 0 : getRptId().hashCode());
        result = prime * result + ((getIndexId() == null) ? 0 : getIndexId().hashCode());
        result = prime * result + ((getVolDimtypeId() == null) ? 0 : getVolDimtypeId().hashCode());
        result = prime * result + ((getVolDimId() == null) ? 0 : getVolDimId().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getTimeDimtypeId() == null) ? 0 : getTimeDimtypeId().hashCode());
        result = prime * result + ((getTimeDimId() == null) ? 0 : getTimeDimId().hashCode());
        return result;
    }
}