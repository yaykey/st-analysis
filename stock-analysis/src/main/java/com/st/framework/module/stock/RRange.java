package com.st.framework.module.stock;

public class RRange extends RRangeKey {
    private Integer volValue;

    private Integer amoValue;

    private Integer cjbs;

    private Double price;

    public Integer getVolValue() {
        return volValue;
    }

    public void setVolValue(Integer volValue) {
        this.volValue = volValue;
    }

    public Integer getAmoValue() {
        return amoValue;
    }

    public void setAmoValue(Integer amoValue) {
        this.amoValue = amoValue;
    }

    public Integer getCjbs() {
        return cjbs;
    }

    public void setCjbs(Integer cjbs) {
        this.cjbs = cjbs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        RRange other = (RRange) that;
        return (this.getRptId() == null ? other.getRptId() == null : this.getRptId().equals(other.getRptId()))
            && (this.getIndexId() == null ? other.getIndexId() == null : this.getIndexId().equals(other.getIndexId()))
            && (this.getVolDimtypeId() == null ? other.getVolDimtypeId() == null : this.getVolDimtypeId().equals(other.getVolDimtypeId()))
            && (this.getVolDimId() == null ? other.getVolDimId() == null : this.getVolDimId().equals(other.getVolDimId()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getTimeDimtypeId() == null ? other.getTimeDimtypeId() == null : this.getTimeDimtypeId().equals(other.getTimeDimtypeId()))
            && (this.getTimeDimId() == null ? other.getTimeDimId() == null : this.getTimeDimId().equals(other.getTimeDimId()))
            && (this.getVolValue() == null ? other.getVolValue() == null : this.getVolValue().equals(other.getVolValue()))
            && (this.getAmoValue() == null ? other.getAmoValue() == null : this.getAmoValue().equals(other.getAmoValue()))
            && (this.getCjbs() == null ? other.getCjbs() == null : this.getCjbs().equals(other.getCjbs()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
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
        result = prime * result + ((getVolValue() == null) ? 0 : getVolValue().hashCode());
        result = prime * result + ((getAmoValue() == null) ? 0 : getAmoValue().hashCode());
        result = prime * result + ((getCjbs() == null) ? 0 : getCjbs().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }
}