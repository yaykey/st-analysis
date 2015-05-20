package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;
import java.util.Date;

public class ThsOwner extends PersistentObject {
    private Integer id;

    private String roomName;

    private String roomUrl;

    private String roomeType;

    private Date updDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl == null ? null : roomUrl.trim();
    }

    public String getRoomeType() {
        return roomeType;
    }

    public void setRoomeType(String roomeType) {
        this.roomeType = roomeType == null ? null : roomeType.trim();
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
        ThsOwner other = (ThsOwner) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomName() == null ? other.getRoomName() == null : this.getRoomName().equals(other.getRoomName()))
            && (this.getRoomUrl() == null ? other.getRoomUrl() == null : this.getRoomUrl().equals(other.getRoomUrl()))
            && (this.getRoomeType() == null ? other.getRoomeType() == null : this.getRoomeType().equals(other.getRoomeType()))
            && (this.getUpdDate() == null ? other.getUpdDate() == null : this.getUpdDate().equals(other.getUpdDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomName() == null) ? 0 : getRoomName().hashCode());
        result = prime * result + ((getRoomUrl() == null) ? 0 : getRoomUrl().hashCode());
        result = prime * result + ((getRoomeType() == null) ? 0 : getRoomeType().hashCode());
        result = prime * result + ((getUpdDate() == null) ? 0 : getUpdDate().hashCode());
        return result;
    }
}