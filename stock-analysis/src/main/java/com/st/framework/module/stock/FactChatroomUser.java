package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;
import java.util.Date;

public class FactChatroomUser extends PersistentObject {
    private Integer id;

    private String userName;

    private String userUrl;

    private Date updDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl == null ? null : userUrl.trim();
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
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
        FactChatroomUser other = (FactChatroomUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserUrl() == null ? other.getUserUrl() == null : this.getUserUrl().equals(other.getUserUrl()))
            && (this.getUpdDate() == null ? other.getUpdDate() == null : this.getUpdDate().equals(other.getUpdDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserUrl() == null) ? 0 : getUserUrl().hashCode());
        result = prime * result + ((getUpdDate() == null) ? 0 : getUpdDate().hashCode());
        return result;
    }
}