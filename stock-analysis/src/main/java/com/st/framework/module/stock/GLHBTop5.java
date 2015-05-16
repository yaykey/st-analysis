package com.st.framework.module.stock;

public class GLHBTop5 extends GLHBTop5Key {
    private String secName;

    private Double buyAmount;

    private Double sellAmount;

    private Double netAmount;

    private String type;

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    public Double getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Double buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(Double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        GLHBTop5 other = (GLHBTop5) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getSecId() == null ? other.getSecId() == null : this.getSecId().equals(other.getSecId()))
            && (this.getSecName() == null ? other.getSecName() == null : this.getSecName().equals(other.getSecName()))
            && (this.getBuyAmount() == null ? other.getBuyAmount() == null : this.getBuyAmount().equals(other.getBuyAmount()))
            && (this.getSellAmount() == null ? other.getSellAmount() == null : this.getSellAmount().equals(other.getSellAmount()))
            && (this.getNetAmount() == null ? other.getNetAmount() == null : this.getNetAmount().equals(other.getNetAmount()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getSecId() == null) ? 0 : getSecId().hashCode());
        result = prime * result + ((getSecName() == null) ? 0 : getSecName().hashCode());
        result = prime * result + ((getBuyAmount() == null) ? 0 : getBuyAmount().hashCode());
        result = prime * result + ((getSellAmount() == null) ? 0 : getSellAmount().hashCode());
        result = prime * result + ((getNetAmount() == null) ? 0 : getNetAmount().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
}