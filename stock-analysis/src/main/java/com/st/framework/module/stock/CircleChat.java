package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;
import java.util.Date;

public class CircleChat extends PersistentObject {
    private Integer id;

    private Integer roomeId;

    private Date dateId;

    private Date timeId;

    private String md5Code;

    private String content;

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

    public Date getDateId() {
        return dateId;
    }

    public void setDateId(Date dateId) {
        this.dateId = dateId;
    }

    public Date getTimeId() {
        return timeId;
    }

    public void setTimeId(Date timeId) {
        this.timeId = timeId;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code == null ? null : md5Code.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        CircleChat other = (CircleChat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomeId() == null ? other.getRoomeId() == null : this.getRoomeId().equals(other.getRoomeId()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getTimeId() == null ? other.getTimeId() == null : this.getTimeId().equals(other.getTimeId()))
            && (this.getMd5Code() == null ? other.getMd5Code() == null : this.getMd5Code().equals(other.getMd5Code()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomeId() == null) ? 0 : getRoomeId().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getTimeId() == null) ? 0 : getTimeId().hashCode());
        result = prime * result + ((getMd5Code() == null) ? 0 : getMd5Code().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}