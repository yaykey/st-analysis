package com.st.framework.module.stock;

import org.springframework.beans.BeanUtils;

import com.st.analysis.utils.stock.bean.netease.StockIndexBean;

public class GDetailIndex extends GDetailIndexKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7647644152818533421L;

	private String iname;

    private Double tclose;

    private Double high;

    private Double low;

    private Double topen;

    private Double lclose;

    private Double chg;

    private Double pchg;

    private Long voturnover;

    private Long vaturnover;
    
    public GDetailIndex () {
    	
    }
    
    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname == null ? null : iname.trim();
    }

    public Double getTclose() {
        return tclose;
    }

    public void setTclose(Double tclose) {
        this.tclose = tclose;
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

    public Double getTopen() {
        return topen;
    }

    public void setTopen(Double topen) {
        this.topen = topen;
    }

    public Double getLclose() {
        return lclose;
    }

    public void setLclose(Double lclose) {
        this.lclose = lclose;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Double getPchg() {
        return pchg;
    }

    public void setPchg(Double pchg) {
        this.pchg = pchg;
    }

    public Long getVoturnover() {
        return voturnover;
    }

    public void setVoturnover(Long voturnover) {
        this.voturnover = voturnover;
    }

    public Long getVaturnover() {
        return vaturnover;
    }

    public void setVaturnover(Long vaturnover) {
        this.vaturnover = vaturnover;
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
        GDetailIndex other = (GDetailIndex) that;
        return (this.getIcode() == null ? other.getIcode() == null : this.getIcode().equals(other.getIcode()))
            && (this.getDateid() == null ? other.getDateid() == null : this.getDateid().equals(other.getDateid()))
            && (this.getIname() == null ? other.getIname() == null : this.getIname().equals(other.getIname()))
            && (this.getTclose() == null ? other.getTclose() == null : this.getTclose().equals(other.getTclose()))
            && (this.getHigh() == null ? other.getHigh() == null : this.getHigh().equals(other.getHigh()))
            && (this.getLow() == null ? other.getLow() == null : this.getLow().equals(other.getLow()))
            && (this.getTopen() == null ? other.getTopen() == null : this.getTopen().equals(other.getTopen()))
            && (this.getLclose() == null ? other.getLclose() == null : this.getLclose().equals(other.getLclose()))
            && (this.getChg() == null ? other.getChg() == null : this.getChg().equals(other.getChg()))
            && (this.getPchg() == null ? other.getPchg() == null : this.getPchg().equals(other.getPchg()))
            && (this.getVoturnover() == null ? other.getVoturnover() == null : this.getVoturnover().equals(other.getVoturnover()))
            && (this.getVaturnover() == null ? other.getVaturnover() == null : this.getVaturnover().equals(other.getVaturnover()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIcode() == null) ? 0 : getIcode().hashCode());
        result = prime * result + ((getDateid() == null) ? 0 : getDateid().hashCode());
        result = prime * result + ((getIname() == null) ? 0 : getIname().hashCode());
        result = prime * result + ((getTclose() == null) ? 0 : getTclose().hashCode());
        result = prime * result + ((getHigh() == null) ? 0 : getHigh().hashCode());
        result = prime * result + ((getLow() == null) ? 0 : getLow().hashCode());
        result = prime * result + ((getTopen() == null) ? 0 : getTopen().hashCode());
        result = prime * result + ((getLclose() == null) ? 0 : getLclose().hashCode());
        result = prime * result + ((getChg() == null) ? 0 : getChg().hashCode());
        result = prime * result + ((getPchg() == null) ? 0 : getPchg().hashCode());
        result = prime * result + ((getVoturnover() == null) ? 0 : getVoturnover().hashCode());
        result = prime * result + ((getVaturnover() == null) ? 0 : getVaturnover().hashCode());
        return result;
    }
}