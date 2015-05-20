package com.st.framework.module.stock;

public class GQgqp extends GQgqpKey {
    private String name;

    private String comment;

    private Double close;

    private Double netChange;

    private String netChangePer;

    private Double prevClose;

    private Double open;

    private Double high;

    private Double low;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getNetChange() {
        return netChange;
    }

    public void setNetChange(Double netChange) {
        this.netChange = netChange;
    }

    public String getNetChangePer() {
        return netChangePer;
    }

    public void setNetChangePer(String netChangePer) {
        this.netChangePer = netChangePer == null ? null : netChangePer.trim();
    }

    public Double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(Double prevClose) {
        this.prevClose = prevClose;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
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
        GQgqp other = (GQgqp) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getClose() == null ? other.getClose() == null : this.getClose().equals(other.getClose()))
            && (this.getNetChange() == null ? other.getNetChange() == null : this.getNetChange().equals(other.getNetChange()))
            && (this.getNetChangePer() == null ? other.getNetChangePer() == null : this.getNetChangePer().equals(other.getNetChangePer()))
            && (this.getPrevClose() == null ? other.getPrevClose() == null : this.getPrevClose().equals(other.getPrevClose()))
            && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()))
            && (this.getHigh() == null ? other.getHigh() == null : this.getHigh().equals(other.getHigh()))
            && (this.getLow() == null ? other.getLow() == null : this.getLow().equals(other.getLow()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getClose() == null) ? 0 : getClose().hashCode());
        result = prime * result + ((getNetChange() == null) ? 0 : getNetChange().hashCode());
        result = prime * result + ((getNetChangePer() == null) ? 0 : getNetChangePer().hashCode());
        result = prime * result + ((getPrevClose() == null) ? 0 : getPrevClose().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        result = prime * result + ((getHigh() == null) ? 0 : getHigh().hashCode());
        result = prime * result + ((getLow() == null) ? 0 : getLow().hashCode());
        return result;
    }
}