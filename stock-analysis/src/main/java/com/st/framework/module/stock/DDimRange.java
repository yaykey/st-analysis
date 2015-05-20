package com.st.framework.module.stock;

public class DDimRange extends DDimRangeKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7633954630496658889L;

	private String dimtypeName;

    private String dimName;

    private Integer dimIdBegin;

    private Integer dimIdEnd;

    private String dimtypeCode;

    private String description;

    public String getDimtypeName() {
        return dimtypeName;
    }

    public void setDimtypeName(String dimtypeName) {
        this.dimtypeName = dimtypeName == null ? null : dimtypeName.trim();
    }

    public String getDimName() {
        return dimName;
    }

    public void setDimName(String dimName) {
        this.dimName = dimName == null ? null : dimName.trim();
    }

    public Integer getDimIdBegin() {
        return dimIdBegin;
    }

    public void setDimIdBegin(Integer dimIdBegin) {
        this.dimIdBegin = dimIdBegin;
    }

    public Integer getDimIdEnd() {
        return dimIdEnd;
    }

    public void setDimIdEnd(Integer dimIdEnd) {
        this.dimIdEnd = dimIdEnd;
    }

    public String getDimtypeCode() {
        return dimtypeCode;
    }

    public void setDimtypeCode(String dimtypeCode) {
        this.dimtypeCode = dimtypeCode == null ? null : dimtypeCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        DDimRange other = (DDimRange) that;
        return (this.getDimtypeId() == null ? other.getDimtypeId() == null : this.getDimtypeId().equals(other.getDimtypeId()))
            && (this.getDimId() == null ? other.getDimId() == null : this.getDimId().equals(other.getDimId()))
            && (this.getDimtypeName() == null ? other.getDimtypeName() == null : this.getDimtypeName().equals(other.getDimtypeName()))
            && (this.getDimName() == null ? other.getDimName() == null : this.getDimName().equals(other.getDimName()))
            && (this.getDimIdBegin() == null ? other.getDimIdBegin() == null : this.getDimIdBegin().equals(other.getDimIdBegin()))
            && (this.getDimIdEnd() == null ? other.getDimIdEnd() == null : this.getDimIdEnd().equals(other.getDimIdEnd()))
            && (this.getDimtypeCode() == null ? other.getDimtypeCode() == null : this.getDimtypeCode().equals(other.getDimtypeCode()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDimtypeId() == null) ? 0 : getDimtypeId().hashCode());
        result = prime * result + ((getDimId() == null) ? 0 : getDimId().hashCode());
        result = prime * result + ((getDimtypeName() == null) ? 0 : getDimtypeName().hashCode());
        result = prime * result + ((getDimName() == null) ? 0 : getDimName().hashCode());
        result = prime * result + ((getDimIdBegin() == null) ? 0 : getDimIdBegin().hashCode());
        result = prime * result + ((getDimIdEnd() == null) ? 0 : getDimIdEnd().hashCode());
        result = prime * result + ((getDimtypeCode() == null) ? 0 : getDimtypeCode().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}