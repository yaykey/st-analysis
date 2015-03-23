package com.st.framework.module.stock;

public class DimCountry {
    private String ab2;

    private String ab3;

    private Integer code;

    private String ab;

    private String name;

    public String getAb2() {
        return ab2;
    }

    public void setAb2(String ab2) {
        this.ab2 = ab2 == null ? null : ab2.trim();
    }

    public String getAb3() {
        return ab3;
    }

    public void setAb3(String ab3) {
        this.ab3 = ab3 == null ? null : ab3.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab == null ? null : ab.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        DimCountry other = (DimCountry) that;
        return (this.getAb2() == null ? other.getAb2() == null : this.getAb2().equals(other.getAb2()))
            && (this.getAb3() == null ? other.getAb3() == null : this.getAb3().equals(other.getAb3()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getAb() == null ? other.getAb() == null : this.getAb().equals(other.getAb()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAb2() == null) ? 0 : getAb2().hashCode());
        result = prime * result + ((getAb3() == null) ? 0 : getAb3().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getAb() == null) ? 0 : getAb().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}