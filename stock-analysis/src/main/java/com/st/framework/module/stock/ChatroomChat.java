package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;
import java.util.Date;

public class ChatroomChat extends PersistentObject {
    private Integer id;

    private Integer circleId;

    private Date dateId;

    private Date timeId;

    private Integer userId;

    private String userName;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
        ChatroomChat other = (ChatroomChat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCircleId() == null ? other.getCircleId() == null : this.getCircleId().equals(other.getCircleId()))
            && (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getTimeId() == null ? other.getTimeId() == null : this.getTimeId().equals(other.getTimeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCircleId() == null) ? 0 : getCircleId().hashCode());
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getTimeId() == null) ? 0 : getTimeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}