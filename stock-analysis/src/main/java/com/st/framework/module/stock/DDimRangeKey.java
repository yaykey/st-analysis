package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class DDimRangeKey extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1915223608137858749L;

	private Integer dimtypeId;

    private Integer dimId;

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
        DDimRangeKey other = (DDimRangeKey) that;
        return (this.getDimtypeId() == null ? other.getDimtypeId() == null : this.getDimtypeId().equals(other.getDimtypeId()))
            && (this.getDimId() == null ? other.getDimId() == null : this.getDimId().equals(other.getDimId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDimtypeId() == null) ? 0 : getDimtypeId().hashCode());
        result = prime * result + ((getDimId() == null) ? 0 : getDimId().hashCode());
        return result;
    }
}