package com.st.framework.module.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FactDateHolidayList extends FactDateHolidayListKey {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        FactDateHolidayList other = (FactDateHolidayList) that;
        return (this.getFestival() == null ? other.getFestival() == null : this.getFestival().equals(other.getFestival()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFestival() == null) ? 0 : getFestival().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
    
    public String toString () {
    	return ToStringBuilder.reflectionToString(this);
    }
}