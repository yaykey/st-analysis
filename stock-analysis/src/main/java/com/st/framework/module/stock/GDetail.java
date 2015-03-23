package com.st.framework.module.stock;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.st.framework.module.PersistentObject;

public class GDetail extends PersistentObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3235861294164950297L;

	private Integer dateId;

    private String timeId;

    private Double price;

    private Integer vol;

    private Integer amo;

    private String nature;

    private Double priceChanges;
    
    /**
     * H:high;L:low;
     */
    private String waveType;

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId == null ? null : timeId.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public Integer getAmo() {
        return amo;
    }

    public void setAmo(Integer amo) {
        this.amo = amo;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public Double getPriceChanges() {
        return priceChanges;
    }

    public void setPriceChanges(Double priceChanges) {
        this.priceChanges = priceChanges;
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
        GDetail other = (GDetail) that;
        return (this.getDateId() == null ? other.getDateId() == null : this.getDateId().equals(other.getDateId()))
            && (this.getTimeId() == null ? other.getTimeId() == null : this.getTimeId().equals(other.getTimeId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getVol() == null ? other.getVol() == null : this.getVol().equals(other.getVol()))
            && (this.getAmo() == null ? other.getAmo() == null : this.getAmo().equals(other.getAmo()))
            && (this.getNature() == null ? other.getNature() == null : this.getNature().equals(other.getNature()))
            && (this.getPriceChanges() == null ? other.getPriceChanges() == null : this.getPriceChanges().equals(other.getPriceChanges()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDateId() == null) ? 0 : getDateId().hashCode());
        result = prime * result + ((getTimeId() == null) ? 0 : getTimeId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getVol() == null) ? 0 : getVol().hashCode());
        result = prime * result + ((getAmo() == null) ? 0 : getAmo().hashCode());
        result = prime * result + ((getNature() == null) ? 0 : getNature().hashCode());
        result = prime * result + ((getPriceChanges() == null) ? 0 : getPriceChanges().hashCode());
        return result;
    }
    
    public String toString () {
    	return ToStringBuilder.reflectionToString(this);
    }

	public String getWaveType() {
		return waveType;
	}

	public void setWaveType(String waveType) {
		this.waveType = waveType;
	}

		
}