package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class CircleChatDetail extends PersistentObject {
    private Integer id;

    private Integer roomeId;

    private Integer circleId;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomeId() {
        return roomeId;
    }

    public void setRoomeId(Integer roomeId) {
        this.roomeId = roomeId;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        CircleChatDetail other = (CircleChatDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomeId() == null ? other.getRoomeId() == null : this.getRoomeId().equals(other.getRoomeId()))
            && (this.getCircleId() == null ? other.getCircleId() == null : this.getCircleId().equals(other.getCircleId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomeId() == null) ? 0 : getRoomeId().hashCode());
        result = prime * result + ((getCircleId() == null) ? 0 : getCircleId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }
}