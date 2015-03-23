package com.st.framework.module.stock;

import java.util.Date;



import com.st.framework.module.PersistentObject;

public class FactSinaParamMap extends PersistentObject implements Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4815922343221768293L;

	private Integer id;

    private String sI;

    private String sA;

    private String sC;

    private String sT;

    private Integer p;

    private Boolean isValidity;

    private Boolean isUse;

    private Date updDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsI() {
        return sI;
    }

    public void setsI(String sI) {
        this.sI = sI == null ? null : sI.trim();
    }

    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA == null ? null : sA.trim();
    }

    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC == null ? null : sC.trim();
    }

    public String getsT() {
        return sT;
    }

    public void setsT(String sT) {
        this.sT = sT == null ? null : sT.trim();
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Boolean getIsValidity() {
        return isValidity;
    }

    public void setIsValidity(Boolean isValidity) {
        this.isValidity = isValidity;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
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
        FactSinaParamMap other = (FactSinaParamMap) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getsI() == null ? other.getsI() == null : this.getsI().equals(other.getsI()))
            && (this.getsA() == null ? other.getsA() == null : this.getsA().equals(other.getsA()))
            && (this.getsC() == null ? other.getsC() == null : this.getsC().equals(other.getsC()))
            && (this.getsT() == null ? other.getsT() == null : this.getsT().equals(other.getsT()))
            && (this.getP() == null ? other.getP() == null : this.getP().equals(other.getP()))
            && (this.getIsValidity() == null ? other.getIsValidity() == null : this.getIsValidity().equals(other.getIsValidity()))
            && (this.getIsUse() == null ? other.getIsUse() == null : this.getIsUse().equals(other.getIsUse()))
            && (this.getUpdDate() == null ? other.getUpdDate() == null : this.getUpdDate().equals(other.getUpdDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getsI() == null) ? 0 : getsI().hashCode());
        result = prime * result + ((getsA() == null) ? 0 : getsA().hashCode());
        result = prime * result + ((getsC() == null) ? 0 : getsC().hashCode());
        result = prime * result + ((getsT() == null) ? 0 : getsT().hashCode());
        result = prime * result + ((getP() == null) ? 0 : getP().hashCode());
        result = prime * result + ((getIsValidity() == null) ? 0 : getIsValidity().hashCode());
        result = prime * result + ((getIsUse() == null) ? 0 : getIsUse().hashCode());
        result = prime * result + ((getUpdDate() == null) ? 0 : getUpdDate().hashCode());
        return result;
    }
    
	public FactSinaParamMap clone() {
		FactSinaParamMap o = null;
		try {
			o = (FactSinaParamMap) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		o.setId(null);
		
		return o;
	}

//    public String toString() {
//		ToStringBuilder strBuilder = new ToStringBuilder(this);
//		if (id != null) {
//			strBuilder.append("id", this.getId());
//		}
//		return strBuilder.toString();
//	}
}