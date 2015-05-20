package com.st.framework.module.stock;

import java.util.Date;

public class PDate {
    private Integer rptId;

    private Date startDateId;

    private Date endDateId;

    private Date uptDate;

    public Integer getRptId() {
        return rptId;
    }

    public void setRptId(Integer rptId) {
        this.rptId = rptId;
    }

    public Date getStartDateId() {
        return startDateId;
    }

    public void setStartDateId(Date startDateId) {
        this.startDateId = startDateId;
    }

    public Date getEndDateId() {
        return endDateId;
    }

    public void setEndDateId(Date endDateId) {
        this.endDateId = endDateId;
    }

    public Date getUptDate() {
        return uptDate;
    }

    public void setUptDate(Date uptDate) {
        this.uptDate = uptDate;
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
        PDate other = (PDate) that;
        return (this.getRptId() == null ? other.getRptId() == null : this.getRptId().equals(other.getRptId()))
            && (this.getStartDateId() == null ? other.getStartDateId() == null : this.getStartDateId().equals(other.getStartDateId()))
            && (this.getEndDateId() == null ? other.getEndDateId() == null : this.getEndDateId().equals(other.getEndDateId()))
            && (this.getUptDate() == null ? other.getUptDate() == null : this.getUptDate().equals(other.getUptDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRptId() == null) ? 0 : getRptId().hashCode());
        result = prime * result + ((getStartDateId() == null) ? 0 : getStartDateId().hashCode());
        result = prime * result + ((getEndDateId() == null) ? 0 : getEndDateId().hashCode());
        result = prime * result + ((getUptDate() == null) ? 0 : getUptDate().hashCode());
        return result;
    }
}