package com.st.framework.module.stock;

import java.util.Date;

import com.st.framework.module.PersistentObject;

public class GIpo extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8467456064109463265L;

	private String stockCode;

    private String purchaseCode;

    private String stockName;

    private Integer yearId;

    private Date onlineIssueDate;

    private Date listingDate;

    private Integer circulation;

    private Integer onlineCirculation;

    private Double purchaseLimit;

    private Double issuePrice;

    private Double peRatio;

    private Double freezeFunds;

    private Double successRate;

    private String successNumber;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode == null ? null : purchaseCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Date getOnlineIssueDate() {
        return onlineIssueDate;
    }

    public void setOnlineIssueDate(Date onlineIssueDate) {
        this.onlineIssueDate = onlineIssueDate;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public Integer getCirculation() {
        return circulation;
    }

    public void setCirculation(Integer circulation) {
        this.circulation = circulation;
    }

    public Integer getOnlineCirculation() {
        return onlineCirculation;
    }

    public void setOnlineCirculation(Integer onlineCirculation) {
        this.onlineCirculation = onlineCirculation;
    }

    public Double getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(Double purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    public Double getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(Double issuePrice) {
        this.issuePrice = issuePrice;
    }

    public Double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    public Double getFreezeFunds() {
        return freezeFunds;
    }

    public void setFreezeFunds(Double freezeFunds) {
        this.freezeFunds = freezeFunds;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public String getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(String successNumber) {
        this.successNumber = successNumber == null ? null : successNumber.trim();
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
        GIpo other = (GIpo) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getPurchaseCode() == null ? other.getPurchaseCode() == null : this.getPurchaseCode().equals(other.getPurchaseCode()))
            && (this.getStockName() == null ? other.getStockName() == null : this.getStockName().equals(other.getStockName()))
            && (this.getYearId() == null ? other.getYearId() == null : this.getYearId().equals(other.getYearId()))
            && (this.getOnlineIssueDate() == null ? other.getOnlineIssueDate() == null : this.getOnlineIssueDate().equals(other.getOnlineIssueDate()))
            && (this.getListingDate() == null ? other.getListingDate() == null : this.getListingDate().equals(other.getListingDate()))
            && (this.getCirculation() == null ? other.getCirculation() == null : this.getCirculation().equals(other.getCirculation()))
            && (this.getOnlineCirculation() == null ? other.getOnlineCirculation() == null : this.getOnlineCirculation().equals(other.getOnlineCirculation()))
            && (this.getPurchaseLimit() == null ? other.getPurchaseLimit() == null : this.getPurchaseLimit().equals(other.getPurchaseLimit()))
            && (this.getIssuePrice() == null ? other.getIssuePrice() == null : this.getIssuePrice().equals(other.getIssuePrice()))
            && (this.getPeRatio() == null ? other.getPeRatio() == null : this.getPeRatio().equals(other.getPeRatio()))
            && (this.getFreezeFunds() == null ? other.getFreezeFunds() == null : this.getFreezeFunds().equals(other.getFreezeFunds()))
            && (this.getSuccessRate() == null ? other.getSuccessRate() == null : this.getSuccessRate().equals(other.getSuccessRate()))
            && (this.getSuccessNumber() == null ? other.getSuccessNumber() == null : this.getSuccessNumber().equals(other.getSuccessNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getPurchaseCode() == null) ? 0 : getPurchaseCode().hashCode());
        result = prime * result + ((getStockName() == null) ? 0 : getStockName().hashCode());
        result = prime * result + ((getYearId() == null) ? 0 : getYearId().hashCode());
        result = prime * result + ((getOnlineIssueDate() == null) ? 0 : getOnlineIssueDate().hashCode());
        result = prime * result + ((getListingDate() == null) ? 0 : getListingDate().hashCode());
        result = prime * result + ((getCirculation() == null) ? 0 : getCirculation().hashCode());
        result = prime * result + ((getOnlineCirculation() == null) ? 0 : getOnlineCirculation().hashCode());
        result = prime * result + ((getPurchaseLimit() == null) ? 0 : getPurchaseLimit().hashCode());
        result = prime * result + ((getIssuePrice() == null) ? 0 : getIssuePrice().hashCode());
        result = prime * result + ((getPeRatio() == null) ? 0 : getPeRatio().hashCode());
        result = prime * result + ((getFreezeFunds() == null) ? 0 : getFreezeFunds().hashCode());
        result = prime * result + ((getSuccessRate() == null) ? 0 : getSuccessRate().hashCode());
        result = prime * result + ((getSuccessNumber() == null) ? 0 : getSuccessNumber().hashCode());
        return result;
    }
}