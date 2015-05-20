package com.st.framework.module.stock;

import java.util.Date;

public class GDividendScheme extends GDividendSchemeKey {
    private String securities;

    private Double delivering;

    private Double transfer;

    private Double dividend;

    private String schedule;

    private Date exDividendDate;

    private Date recordDate;

    private Date listingDate;

    private String preTaxBonus;

    private String nonGappEarning;

    private String bhSharesBeforeTaxDividend;

    private String bhSharesAfterTaxDividend;

    private String proportionBonus;

    private String capitalizationRatio;

    private String surplusReserveRatio;

    private String capitalReserveRatio;

    private String issuanceTarget;

    private String equityReferenceDay;

    private String lastTradingDay;

    private String arrivalDate;

    private String dividendDateOfTermination;

    private String meetingAnnouncementDate;

    private String rightsDeadline;

    private String allotmentRatio;

    private String shsPlacing;

    private String transferredRatio;

    private String turnValence;

    private String validAllotment;

    private String actualSharesNumber;

    private String sharesBeforIssue;

    private String actualRatio;

    private String shareSplitCount;

    private String foreignCurrencyExchangeRate;

    private String weightNote;

    public String getSecurities() {
        return securities;
    }

    public void setSecurities(String securities) {
        this.securities = securities == null ? null : securities.trim();
    }

    public Double getDelivering() {
        return delivering;
    }

    public void setDelivering(Double delivering) {
        this.delivering = delivering;
    }

    public Double getTransfer() {
        return transfer;
    }

    public void setTransfer(Double transfer) {
        this.transfer = transfer;
    }

    public Double getDividend() {
        return dividend;
    }

    public void setDividend(Double dividend) {
        this.dividend = dividend;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule == null ? null : schedule.trim();
    }

    public Date getExDividendDate() {
        return exDividendDate;
    }

    public void setExDividendDate(Date exDividendDate) {
        this.exDividendDate = exDividendDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public String getPreTaxBonus() {
        return preTaxBonus;
    }

    public void setPreTaxBonus(String preTaxBonus) {
        this.preTaxBonus = preTaxBonus == null ? null : preTaxBonus.trim();
    }

    public String getNonGappEarning() {
        return nonGappEarning;
    }

    public void setNonGappEarning(String nonGappEarning) {
        this.nonGappEarning = nonGappEarning == null ? null : nonGappEarning.trim();
    }

    public String getBhSharesBeforeTaxDividend() {
        return bhSharesBeforeTaxDividend;
    }

    public void setBhSharesBeforeTaxDividend(String bhSharesBeforeTaxDividend) {
        this.bhSharesBeforeTaxDividend = bhSharesBeforeTaxDividend == null ? null : bhSharesBeforeTaxDividend.trim();
    }

    public String getBhSharesAfterTaxDividend() {
        return bhSharesAfterTaxDividend;
    }

    public void setBhSharesAfterTaxDividend(String bhSharesAfterTaxDividend) {
        this.bhSharesAfterTaxDividend = bhSharesAfterTaxDividend == null ? null : bhSharesAfterTaxDividend.trim();
    }

    public String getProportionBonus() {
        return proportionBonus;
    }

    public void setProportionBonus(String proportionBonus) {
        this.proportionBonus = proportionBonus == null ? null : proportionBonus.trim();
    }

    public String getCapitalizationRatio() {
        return capitalizationRatio;
    }

    public void setCapitalizationRatio(String capitalizationRatio) {
        this.capitalizationRatio = capitalizationRatio == null ? null : capitalizationRatio.trim();
    }

    public String getSurplusReserveRatio() {
        return surplusReserveRatio;
    }

    public void setSurplusReserveRatio(String surplusReserveRatio) {
        this.surplusReserveRatio = surplusReserveRatio == null ? null : surplusReserveRatio.trim();
    }

    public String getCapitalReserveRatio() {
        return capitalReserveRatio;
    }

    public void setCapitalReserveRatio(String capitalReserveRatio) {
        this.capitalReserveRatio = capitalReserveRatio == null ? null : capitalReserveRatio.trim();
    }

    public String getIssuanceTarget() {
        return issuanceTarget;
    }

    public void setIssuanceTarget(String issuanceTarget) {
        this.issuanceTarget = issuanceTarget == null ? null : issuanceTarget.trim();
    }

    public String getEquityReferenceDay() {
        return equityReferenceDay;
    }

    public void setEquityReferenceDay(String equityReferenceDay) {
        this.equityReferenceDay = equityReferenceDay == null ? null : equityReferenceDay.trim();
    }

    public String getLastTradingDay() {
        return lastTradingDay;
    }

    public void setLastTradingDay(String lastTradingDay) {
        this.lastTradingDay = lastTradingDay == null ? null : lastTradingDay.trim();
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate == null ? null : arrivalDate.trim();
    }

    public String getDividendDateOfTermination() {
        return dividendDateOfTermination;
    }

    public void setDividendDateOfTermination(String dividendDateOfTermination) {
        this.dividendDateOfTermination = dividendDateOfTermination == null ? null : dividendDateOfTermination.trim();
    }

    public String getMeetingAnnouncementDate() {
        return meetingAnnouncementDate;
    }

    public void setMeetingAnnouncementDate(String meetingAnnouncementDate) {
        this.meetingAnnouncementDate = meetingAnnouncementDate == null ? null : meetingAnnouncementDate.trim();
    }

    public String getRightsDeadline() {
        return rightsDeadline;
    }

    public void setRightsDeadline(String rightsDeadline) {
        this.rightsDeadline = rightsDeadline == null ? null : rightsDeadline.trim();
    }

    public String getAllotmentRatio() {
        return allotmentRatio;
    }

    public void setAllotmentRatio(String allotmentRatio) {
        this.allotmentRatio = allotmentRatio == null ? null : allotmentRatio.trim();
    }

    public String getShsPlacing() {
        return shsPlacing;
    }

    public void setShsPlacing(String shsPlacing) {
        this.shsPlacing = shsPlacing == null ? null : shsPlacing.trim();
    }

    public String getTransferredRatio() {
        return transferredRatio;
    }

    public void setTransferredRatio(String transferredRatio) {
        this.transferredRatio = transferredRatio == null ? null : transferredRatio.trim();
    }

    public String getTurnValence() {
        return turnValence;
    }

    public void setTurnValence(String turnValence) {
        this.turnValence = turnValence == null ? null : turnValence.trim();
    }

    public String getValidAllotment() {
        return validAllotment;
    }

    public void setValidAllotment(String validAllotment) {
        this.validAllotment = validAllotment == null ? null : validAllotment.trim();
    }

    public String getActualSharesNumber() {
        return actualSharesNumber;
    }

    public void setActualSharesNumber(String actualSharesNumber) {
        this.actualSharesNumber = actualSharesNumber == null ? null : actualSharesNumber.trim();
    }

    public String getSharesBeforIssue() {
        return sharesBeforIssue;
    }

    public void setSharesBeforIssue(String sharesBeforIssue) {
        this.sharesBeforIssue = sharesBeforIssue == null ? null : sharesBeforIssue.trim();
    }

    public String getActualRatio() {
        return actualRatio;
    }

    public void setActualRatio(String actualRatio) {
        this.actualRatio = actualRatio == null ? null : actualRatio.trim();
    }

    public String getShareSplitCount() {
        return shareSplitCount;
    }

    public void setShareSplitCount(String shareSplitCount) {
        this.shareSplitCount = shareSplitCount == null ? null : shareSplitCount.trim();
    }

    public String getForeignCurrencyExchangeRate() {
        return foreignCurrencyExchangeRate;
    }

    public void setForeignCurrencyExchangeRate(String foreignCurrencyExchangeRate) {
        this.foreignCurrencyExchangeRate = foreignCurrencyExchangeRate == null ? null : foreignCurrencyExchangeRate.trim();
    }

    public String getWeightNote() {
        return weightNote;
    }

    public void setWeightNote(String weightNote) {
        this.weightNote = weightNote == null ? null : weightNote.trim();
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
        GDividendScheme other = (GDividendScheme) that;
        return (this.getStockCode() == null ? other.getStockCode() == null : this.getStockCode().equals(other.getStockCode()))
            && (this.getAnnouncementDate() == null ? other.getAnnouncementDate() == null : this.getAnnouncementDate().equals(other.getAnnouncementDate()))
            && (this.getSecurities() == null ? other.getSecurities() == null : this.getSecurities().equals(other.getSecurities()))
            && (this.getDelivering() == null ? other.getDelivering() == null : this.getDelivering().equals(other.getDelivering()))
            && (this.getTransfer() == null ? other.getTransfer() == null : this.getTransfer().equals(other.getTransfer()))
            && (this.getDividend() == null ? other.getDividend() == null : this.getDividend().equals(other.getDividend()))
            && (this.getSchedule() == null ? other.getSchedule() == null : this.getSchedule().equals(other.getSchedule()))
            && (this.getExDividendDate() == null ? other.getExDividendDate() == null : this.getExDividendDate().equals(other.getExDividendDate()))
            && (this.getRecordDate() == null ? other.getRecordDate() == null : this.getRecordDate().equals(other.getRecordDate()))
            && (this.getListingDate() == null ? other.getListingDate() == null : this.getListingDate().equals(other.getListingDate()))
            && (this.getPreTaxBonus() == null ? other.getPreTaxBonus() == null : this.getPreTaxBonus().equals(other.getPreTaxBonus()))
            && (this.getNonGappEarning() == null ? other.getNonGappEarning() == null : this.getNonGappEarning().equals(other.getNonGappEarning()))
            && (this.getBhSharesBeforeTaxDividend() == null ? other.getBhSharesBeforeTaxDividend() == null : this.getBhSharesBeforeTaxDividend().equals(other.getBhSharesBeforeTaxDividend()))
            && (this.getBhSharesAfterTaxDividend() == null ? other.getBhSharesAfterTaxDividend() == null : this.getBhSharesAfterTaxDividend().equals(other.getBhSharesAfterTaxDividend()))
            && (this.getProportionBonus() == null ? other.getProportionBonus() == null : this.getProportionBonus().equals(other.getProportionBonus()))
            && (this.getCapitalizationRatio() == null ? other.getCapitalizationRatio() == null : this.getCapitalizationRatio().equals(other.getCapitalizationRatio()))
            && (this.getSurplusReserveRatio() == null ? other.getSurplusReserveRatio() == null : this.getSurplusReserveRatio().equals(other.getSurplusReserveRatio()))
            && (this.getCapitalReserveRatio() == null ? other.getCapitalReserveRatio() == null : this.getCapitalReserveRatio().equals(other.getCapitalReserveRatio()))
            && (this.getIssuanceTarget() == null ? other.getIssuanceTarget() == null : this.getIssuanceTarget().equals(other.getIssuanceTarget()))
            && (this.getEquityReferenceDay() == null ? other.getEquityReferenceDay() == null : this.getEquityReferenceDay().equals(other.getEquityReferenceDay()))
            && (this.getLastTradingDay() == null ? other.getLastTradingDay() == null : this.getLastTradingDay().equals(other.getLastTradingDay()))
            && (this.getArrivalDate() == null ? other.getArrivalDate() == null : this.getArrivalDate().equals(other.getArrivalDate()))
            && (this.getDividendDateOfTermination() == null ? other.getDividendDateOfTermination() == null : this.getDividendDateOfTermination().equals(other.getDividendDateOfTermination()))
            && (this.getMeetingAnnouncementDate() == null ? other.getMeetingAnnouncementDate() == null : this.getMeetingAnnouncementDate().equals(other.getMeetingAnnouncementDate()))
            && (this.getRightsDeadline() == null ? other.getRightsDeadline() == null : this.getRightsDeadline().equals(other.getRightsDeadline()))
            && (this.getAllotmentRatio() == null ? other.getAllotmentRatio() == null : this.getAllotmentRatio().equals(other.getAllotmentRatio()))
            && (this.getShsPlacing() == null ? other.getShsPlacing() == null : this.getShsPlacing().equals(other.getShsPlacing()))
            && (this.getTransferredRatio() == null ? other.getTransferredRatio() == null : this.getTransferredRatio().equals(other.getTransferredRatio()))
            && (this.getTurnValence() == null ? other.getTurnValence() == null : this.getTurnValence().equals(other.getTurnValence()))
            && (this.getValidAllotment() == null ? other.getValidAllotment() == null : this.getValidAllotment().equals(other.getValidAllotment()))
            && (this.getActualSharesNumber() == null ? other.getActualSharesNumber() == null : this.getActualSharesNumber().equals(other.getActualSharesNumber()))
            && (this.getSharesBeforIssue() == null ? other.getSharesBeforIssue() == null : this.getSharesBeforIssue().equals(other.getSharesBeforIssue()))
            && (this.getActualRatio() == null ? other.getActualRatio() == null : this.getActualRatio().equals(other.getActualRatio()))
            && (this.getShareSplitCount() == null ? other.getShareSplitCount() == null : this.getShareSplitCount().equals(other.getShareSplitCount()))
            && (this.getForeignCurrencyExchangeRate() == null ? other.getForeignCurrencyExchangeRate() == null : this.getForeignCurrencyExchangeRate().equals(other.getForeignCurrencyExchangeRate()))
            && (this.getWeightNote() == null ? other.getWeightNote() == null : this.getWeightNote().equals(other.getWeightNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockCode() == null) ? 0 : getStockCode().hashCode());
        result = prime * result + ((getAnnouncementDate() == null) ? 0 : getAnnouncementDate().hashCode());
        result = prime * result + ((getSecurities() == null) ? 0 : getSecurities().hashCode());
        result = prime * result + ((getDelivering() == null) ? 0 : getDelivering().hashCode());
        result = prime * result + ((getTransfer() == null) ? 0 : getTransfer().hashCode());
        result = prime * result + ((getDividend() == null) ? 0 : getDividend().hashCode());
        result = prime * result + ((getSchedule() == null) ? 0 : getSchedule().hashCode());
        result = prime * result + ((getExDividendDate() == null) ? 0 : getExDividendDate().hashCode());
        result = prime * result + ((getRecordDate() == null) ? 0 : getRecordDate().hashCode());
        result = prime * result + ((getListingDate() == null) ? 0 : getListingDate().hashCode());
        result = prime * result + ((getPreTaxBonus() == null) ? 0 : getPreTaxBonus().hashCode());
        result = prime * result + ((getNonGappEarning() == null) ? 0 : getNonGappEarning().hashCode());
        result = prime * result + ((getBhSharesBeforeTaxDividend() == null) ? 0 : getBhSharesBeforeTaxDividend().hashCode());
        result = prime * result + ((getBhSharesAfterTaxDividend() == null) ? 0 : getBhSharesAfterTaxDividend().hashCode());
        result = prime * result + ((getProportionBonus() == null) ? 0 : getProportionBonus().hashCode());
        result = prime * result + ((getCapitalizationRatio() == null) ? 0 : getCapitalizationRatio().hashCode());
        result = prime * result + ((getSurplusReserveRatio() == null) ? 0 : getSurplusReserveRatio().hashCode());
        result = prime * result + ((getCapitalReserveRatio() == null) ? 0 : getCapitalReserveRatio().hashCode());
        result = prime * result + ((getIssuanceTarget() == null) ? 0 : getIssuanceTarget().hashCode());
        result = prime * result + ((getEquityReferenceDay() == null) ? 0 : getEquityReferenceDay().hashCode());
        result = prime * result + ((getLastTradingDay() == null) ? 0 : getLastTradingDay().hashCode());
        result = prime * result + ((getArrivalDate() == null) ? 0 : getArrivalDate().hashCode());
        result = prime * result + ((getDividendDateOfTermination() == null) ? 0 : getDividendDateOfTermination().hashCode());
        result = prime * result + ((getMeetingAnnouncementDate() == null) ? 0 : getMeetingAnnouncementDate().hashCode());
        result = prime * result + ((getRightsDeadline() == null) ? 0 : getRightsDeadline().hashCode());
        result = prime * result + ((getAllotmentRatio() == null) ? 0 : getAllotmentRatio().hashCode());
        result = prime * result + ((getShsPlacing() == null) ? 0 : getShsPlacing().hashCode());
        result = prime * result + ((getTransferredRatio() == null) ? 0 : getTransferredRatio().hashCode());
        result = prime * result + ((getTurnValence() == null) ? 0 : getTurnValence().hashCode());
        result = prime * result + ((getValidAllotment() == null) ? 0 : getValidAllotment().hashCode());
        result = prime * result + ((getActualSharesNumber() == null) ? 0 : getActualSharesNumber().hashCode());
        result = prime * result + ((getSharesBeforIssue() == null) ? 0 : getSharesBeforIssue().hashCode());
        result = prime * result + ((getActualRatio() == null) ? 0 : getActualRatio().hashCode());
        result = prime * result + ((getShareSplitCount() == null) ? 0 : getShareSplitCount().hashCode());
        result = prime * result + ((getForeignCurrencyExchangeRate() == null) ? 0 : getForeignCurrencyExchangeRate().hashCode());
        result = prime * result + ((getWeightNote() == null) ? 0 : getWeightNote().hashCode());
        return result;
    }
}