package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class PMapKey extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1629727277837915596L;

	private Integer rptId;

    private Integer dimtypeId;

    private Integer dimId;

    private Integer indexId;

    public Integer getRptId() {
        return rptId;
    }

    public void setRptId(Integer rptId) {
        this.rptId = rptId;
    }

    public Integer getDimtypeId() {
        return dimtypeId;
    }

    public void setDimtypeId(Integer dimtypeId) {
        this.dimtypeId = dimtypeId;
    }

    public Integer getDimId() {
        return dimId;
    }

    public void setDimId(Integer dimId) {
        this.dimId = dimId;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
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
        PMapKey other = (PMapKey) that;
        return (this.getRptId() == null ? other.getRptId() == null : this.getRptId().equals(other.getRptId()))
            && (this.getDimtypeId() == null ? other.getDimtypeId() == null : this.getDimtypeId().equals(other.getDimtypeId()))
            && (this.getDimId() == null ? other.getDimId() == null : this.getDimId().equals(other.getDimId()))
            && (this.getIndexId() == null ? other.getIndexId() == null : this.getIndexId().equals(other.getIndexId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRptId() == null) ? 0 : getRptId().hashCode());
        result = prime * result + ((getDimtypeId() == null) ? 0 : getDimtypeId().hashCode());
        result = prime * result + ((getDimId() == null) ? 0 : getDimId().hashCode());
        result = prime * result + ((getIndexId() == null) ? 0 : getIndexId().hashCode());
        return result;
    }
}