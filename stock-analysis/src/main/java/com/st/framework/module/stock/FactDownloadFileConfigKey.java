package com.st.framework.module.stock;

import com.st.framework.module.PersistentObject;

public class FactDownloadFileConfigKey extends PersistentObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5724064188979396398L;

	private String stCode;

    private String timeId;

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode == null ? null : stCode.trim();
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId == null ? null : timeId.trim();
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
        FactDownloadFileConfigKey other = (FactDownloadFileConfigKey) that;
        return (this.getStCode() == null ? other.getStCode() == null : this.getStCode().equals(other.getStCode()))
            && (this.getTimeId() == null ? other.getTimeId() == null : this.getTimeId().equals(other.getTimeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStCode() == null) ? 0 : getStCode().hashCode());
        result = prime * result + ((getTimeId() == null) ? 0 : getTimeId().hashCode());
        return result;
    }
}