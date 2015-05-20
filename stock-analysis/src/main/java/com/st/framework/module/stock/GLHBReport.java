package com.st.framework.module.stock;

import java.util.List;

public class GLHBReport extends GLHBReportKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5525517128328295962L;

	private String reasons;

    private Double totalAmount;

    private Double totalVolume;

    private Double totalCapitalPer;

    private Double aSharePer;
    
    private List<GLHBTop5> buyTop5;
    
    private List<GLHBTop5> sellTop5;
    
    private GLHBReport next;

    public List<GLHBTop5> getBuyTop5() {
		return buyTop5;
	}

	public void setBuyTop5(List<GLHBTop5> buyTop5) {
		this.buyTop5 = buyTop5;
	}

	public List<GLHBTop5> getSellTop5() {
		return sellTop5;
	}

	public void setSellTop5(List<GLHBTop5> sellTop5) {
		this.sellTop5 = sellTop5;
	}

	public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons == null ? null : reasons.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalCapitalPer() {
        return totalCapitalPer;
    }

    public void setTotalCapitalPer(Double totalCapitalPer) {
        this.totalCapitalPer = totalCapitalPer;
    }

    public Double getaSharePer() {
        return aSharePer;
    }

    public void setaSharePer(Double aSharePer) {
        this.aSharePer = aSharePer;
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
        GLHBReport other = (GLHBReport) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getReasons() == null ? other.getReasons() == null : this.getReasons().equals(other.getReasons()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getTotalVolume() == null ? other.getTotalVolume() == null : this.getTotalVolume().equals(other.getTotalVolume()))
            && (this.getTotalCapitalPer() == null ? other.getTotalCapitalPer() == null : this.getTotalCapitalPer().equals(other.getTotalCapitalPer()))
            && (this.getaSharePer() == null ? other.getaSharePer() == null : this.getaSharePer().equals(other.getaSharePer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getReasons() == null) ? 0 : getReasons().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getTotalVolume() == null) ? 0 : getTotalVolume().hashCode());
        result = prime * result + ((getTotalCapitalPer() == null) ? 0 : getTotalCapitalPer().hashCode());
        result = prime * result + ((getaSharePer() == null) ? 0 : getaSharePer().hashCode());
        return result;
    }

	public GLHBReport getNext() {
		return next;
	}

	public void setNext(GLHBReport next) {
		this.next = next;
	}

	
}