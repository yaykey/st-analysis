package com.st.framework.module.stock;

import java.util.Date;

public class DStock {
    private String stockCode;

    private String stockTypeCode;

    private String stockName;

    private String pinyinCode;

    private String nameCode;

    private String companyName;

    private String companyEnName;

    private Double issuePrice;

    private Double firstDayOpeningPrice;

    private Double firstDayClosingPrice;

    private String registeredAddresses;

    private String officeAddress;

    private Date listingDate;

    private Double marketCapitalization;

    private Double circulatedStockValue;

    private Double totalCapital;

    private Double currCapital;

    private String listedMarket;

    private String leadUnderwriter;

    private Date establishDate;

    private Integer registeredCapital;

    private String organization;

    private String introduction;

    private String scope;

    private Byte stockState;

    private Date updDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockTypeCode() {
        return stockTypeCode;
    }

    public void setStockTypeCode(String stockTypeCode) {
        this.stockTypeCode = stockTypeCode == null ? null : stockTypeCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode == null ? null : nameCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyEnName() {
        return companyEnName;
    }

    public void setCompanyEnName(String companyEnName) {
        this.companyEnName = companyEnName == null ? null : companyEnName.trim();
    }

    public Double getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(Double issuePrice) {
        this.issuePrice = issuePrice;
    }

    public Double getFirstDayOpeningPrice() {
        return firstDayOpeningPrice;
    }

    public void setFirstDayOpeningPrice(Double firstDayOpeningPrice) {
        this.firstDayOpeningPrice = firstDayOpeningPrice;
    }

    public Double getFirstDayClosingPrice() {
        return firstDayClosingPrice;
    }

    public void setFirstDayClosingPrice(Double firstDayClosingPrice) {
        this.firstDayClosingPrice = firstDayClosingPrice;
    }

    public String getRegisteredAddresses() {
        return registeredAddresses;
    }

    public void setRegisteredAddresses(String registeredAddresses) {
        this.registeredAddresses = registeredAddresses == null ? null : registeredAddresses.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public Double getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(Double marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Double getCirculatedStockValue() {
        return circulatedStockValue;
    }

    public void setCirculatedStockValue(Double circulatedStockValue) {
        this.circulatedStockValue = circulatedStockValue;
    }

    public Double getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(Double totalCapital) {
        this.totalCapital = totalCapital;
    }

    public Double getCurrCapital() {
        return currCapital;
    }

    public void setCurrCapital(Double currCapital) {
        this.currCapital = currCapital;
    }

    public String getListedMarket() {
        return listedMarket;
    }

    public void setListedMarket(String listedMarket) {
        this.listedMarket = listedMarket == null ? null : listedMarket.trim();
    }

    public String getLeadUnderwriter() {
        return leadUnderwriter;
    }

    public void setLeadUnderwriter(String leadUnderwriter) {
        this.leadUnderwriter = leadUnderwriter == null ? null : leadUnderwriter.trim();
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Integer registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public Byte getStockState() {
        return stockState;
    }

    public void setStockState(Byte stockState) {
        this.stockState = stockState;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
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
        DStock other = (DStock) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getStockTypeCode() == null ? other.getStockTypeCode() == null : this.getStockTypeCode().equals(other.getStockTypeCode()))
            && (this.getStockName() == null ? other.getStockName() == null : this.getStockName().equals(other.getStockName()))
            && (this.getPinyinCode() == null ? other.getPinyinCode() == null : this.getPinyinCode().equals(other.getPinyinCode()))
            && (this.getNameCode() == null ? other.getNameCode() == null : this.getNameCode().equals(other.getNameCode()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCompanyEnName() == null ? other.getCompanyEnName() == null : this.getCompanyEnName().equals(other.getCompanyEnName()))
            && (this.getIssuePrice() == null ? other.getIssuePrice() == null : this.getIssuePrice().equals(other.getIssuePrice()))
            && (this.getFirstDayOpeningPrice() == null ? other.getFirstDayOpeningPrice() == null : this.getFirstDayOpeningPrice().equals(other.getFirstDayOpeningPrice()))
            && (this.getFirstDayClosingPrice() == null ? other.getFirstDayClosingPrice() == null : this.getFirstDayClosingPrice().equals(other.getFirstDayClosingPrice()))
            && (this.getRegisteredAddresses() == null ? other.getRegisteredAddresses() == null : this.getRegisteredAddresses().equals(other.getRegisteredAddresses()))
            && (this.getOfficeAddress() == null ? other.getOfficeAddress() == null : this.getOfficeAddress().equals(other.getOfficeAddress()))
            && (this.getListingDate() == null ? other.getListingDate() == null : this.getListingDate().equals(other.getListingDate()))
            && (this.getMarketCapitalization() == null ? other.getMarketCapitalization() == null : this.getMarketCapitalization().equals(other.getMarketCapitalization()))
            && (this.getCirculatedStockValue() == null ? other.getCirculatedStockValue() == null : this.getCirculatedStockValue().equals(other.getCirculatedStockValue()))
            && (this.getTotalCapital() == null ? other.getTotalCapital() == null : this.getTotalCapital().equals(other.getTotalCapital()))
            && (this.getCurrCapital() == null ? other.getCurrCapital() == null : this.getCurrCapital().equals(other.getCurrCapital()))
            && (this.getListedMarket() == null ? other.getListedMarket() == null : this.getListedMarket().equals(other.getListedMarket()))
            && (this.getLeadUnderwriter() == null ? other.getLeadUnderwriter() == null : this.getLeadUnderwriter().equals(other.getLeadUnderwriter()))
            && (this.getEstablishDate() == null ? other.getEstablishDate() == null : this.getEstablishDate().equals(other.getEstablishDate()))
            && (this.getRegisteredCapital() == null ? other.getRegisteredCapital() == null : this.getRegisteredCapital().equals(other.getRegisteredCapital()))
            && (this.getOrganization() == null ? other.getOrganization() == null : this.getOrganization().equals(other.getOrganization()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getScope() == null ? other.getScope() == null : this.getScope().equals(other.getScope()))
            && (this.getStockState() == null ? other.getStockState() == null : this.getStockState().equals(other.getStockState()))
            && (this.getUpdDate() == null ? other.getUpdDate() == null : this.getUpdDate().equals(other.getUpdDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getStockTypeCode() == null) ? 0 : getStockTypeCode().hashCode());
        result = prime * result + ((getStockName() == null) ? 0 : getStockName().hashCode());
        result = prime * result + ((getPinyinCode() == null) ? 0 : getPinyinCode().hashCode());
        result = prime * result + ((getNameCode() == null) ? 0 : getNameCode().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCompanyEnName() == null) ? 0 : getCompanyEnName().hashCode());
        result = prime * result + ((getIssuePrice() == null) ? 0 : getIssuePrice().hashCode());
        result = prime * result + ((getFirstDayOpeningPrice() == null) ? 0 : getFirstDayOpeningPrice().hashCode());
        result = prime * result + ((getFirstDayClosingPrice() == null) ? 0 : getFirstDayClosingPrice().hashCode());
        result = prime * result + ((getRegisteredAddresses() == null) ? 0 : getRegisteredAddresses().hashCode());
        result = prime * result + ((getOfficeAddress() == null) ? 0 : getOfficeAddress().hashCode());
        result = prime * result + ((getListingDate() == null) ? 0 : getListingDate().hashCode());
        result = prime * result + ((getMarketCapitalization() == null) ? 0 : getMarketCapitalization().hashCode());
        result = prime * result + ((getCirculatedStockValue() == null) ? 0 : getCirculatedStockValue().hashCode());
        result = prime * result + ((getTotalCapital() == null) ? 0 : getTotalCapital().hashCode());
        result = prime * result + ((getCurrCapital() == null) ? 0 : getCurrCapital().hashCode());
        result = prime * result + ((getListedMarket() == null) ? 0 : getListedMarket().hashCode());
        result = prime * result + ((getLeadUnderwriter() == null) ? 0 : getLeadUnderwriter().hashCode());
        result = prime * result + ((getEstablishDate() == null) ? 0 : getEstablishDate().hashCode());
        result = prime * result + ((getRegisteredCapital() == null) ? 0 : getRegisteredCapital().hashCode());
        result = prime * result + ((getOrganization() == null) ? 0 : getOrganization().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getScope() == null) ? 0 : getScope().hashCode());
        result = prime * result + ((getStockState() == null) ? 0 : getStockState().hashCode());
        result = prime * result + ((getUpdDate() == null) ? 0 : getUpdDate().hashCode());
        return result;
    }
}