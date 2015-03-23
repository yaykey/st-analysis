package com.st.framework.module.stock;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FactProxy extends FactProxyKey {
    private String local;

    private Integer testSpeed;

    private Boolean active;

    private Integer loseFactor = 0;

    private Date testDate;

    private Date createDate;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local == null ? null : local.trim();
    }

    public Integer getTestSpeed() {
        return testSpeed;
    }

    public void setTestSpeed(Integer testSpeed) {
        this.testSpeed = testSpeed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getLoseFactor() {
        return loseFactor;
    }

    public void setLoseFactor(Integer loseFactor) {
        this.loseFactor = loseFactor;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        FactProxy other = (FactProxy) that;
        return (this.getProxyIp() == null ? other.getProxyIp() == null : this.getProxyIp().equals(other.getProxyIp()))
            && (this.getProxyPort() == null ? other.getProxyPort() == null : this.getProxyPort().equals(other.getProxyPort()))
            && (this.getLocal() == null ? other.getLocal() == null : this.getLocal().equals(other.getLocal()))
            && (this.getTestSpeed() == null ? other.getTestSpeed() == null : this.getTestSpeed().equals(other.getTestSpeed()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
            && (this.getLoseFactor() == null ? other.getLoseFactor() == null : this.getLoseFactor().equals(other.getLoseFactor()))
            && (this.getTestDate() == null ? other.getTestDate() == null : this.getTestDate().equals(other.getTestDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProxyIp() == null) ? 0 : getProxyIp().hashCode());
        result = prime * result + ((getProxyPort() == null) ? 0 : getProxyPort().hashCode());
        result = prime * result + ((getLocal() == null) ? 0 : getLocal().hashCode());
        result = prime * result + ((getTestSpeed() == null) ? 0 : getTestSpeed().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getLoseFactor() == null) ? 0 : getLoseFactor().hashCode());
        result = prime * result + ((getTestDate() == null) ? 0 : getTestDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
    
    public String toString () {
    	return ToStringBuilder.reflectionToString(this);
    }
}