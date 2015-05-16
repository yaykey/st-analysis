package com.st.framework.module.stock;

import java.util.Date;

public class GDetailIndexKey {
    private String icode;

    private Date dateid;

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode == null ? null : icode.trim();
    }

    public Date getDateid() {
        return dateid;
    }

    public void setDateid(Date dateid) {
        this.dateid = dateid;
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
        GDetailIndexKey other = (GDetailIndexKey) that;
        return (this.getIcode() == null ? other.getIcode() == null : this.getIcode().equals(other.getIcode()))
            && (this.getDateid() == null ? other.getDateid() == null : this.getDateid().equals(other.getDateid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIcode() == null) ? 0 : getIcode().hashCode());
        result = prime * result + ((getDateid() == null) ? 0 : getDateid().hashCode());
        return result;
    }
}