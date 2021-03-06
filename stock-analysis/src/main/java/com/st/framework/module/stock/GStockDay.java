package com.st.framework.module.stock;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.st.Global;

public class GStockDay extends GStockDayKey {
    private Double open;

    private Double high;

    private Double low;

    private Double close;

    private Integer volume;

    private Double adjClose;

    private Double priceChanges;

    private String highTimeId;

    private String lowTimeId;

    private Double amplitude;

    private Double highPer;

    private Double lowPer;

    private Double closePer;

    private Double openPer;
    
    private GStockDay prevDay;
    
    private GStockDay nextDay;

    private Double turnVolume;

    private Double turnoverRate;

    private List<GStockDay> prev5Day;
    
    private List<GStockDay> next5Day;
    
    private List<GStockDay> prev10Day;
    
    private List<GStockDay> next10Day;
    
    private Double MA5;
    
    private Double MA10;
    
    private int dateDec;
    
    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public Double getPriceChanges() {
        return priceChanges;
    }

    public void setPriceChanges(Double priceChanges) {
        this.priceChanges = priceChanges;
    }

    public String getHighTimeId() {
        return highTimeId;
    }

    public void setHighTimeId(String highTimeId) {
        this.highTimeId = highTimeId == null ? null : highTimeId.trim();
    }

    public String getLowTimeId() {
        return lowTimeId;
    }

    public void setLowTimeId(String lowTimeId) {
        this.lowTimeId = lowTimeId == null ? null : lowTimeId.trim();
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    public Double getHighPer() {
        return highPer;
    }

    public void setHighPer(Double highPer) {
        this.highPer = highPer;
    }

    public Double getLowPer() {
        return lowPer;
    }

    public void setLowPer(Double lowPer) {
        this.lowPer = lowPer;
    }

    public Double getClosePer() {
        return closePer;
    }

    public void setClosePer(Double closePer) {
        this.closePer = closePer;
    }

    public Double getOpenPer() {
        return openPer;
    }

    public void setOpenPer(Double openPer) {
        this.openPer = openPer;
    }

    public Double getTurnVolume() {
        return turnVolume;
    }

    public void setTurnVolume(Double turnVolume) {
        this.turnVolume = turnVolume;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
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
        GStockDay other = (GStockDay) that;
        return (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()))
            && (this.getHigh() == null ? other.getHigh() == null : this.getHigh().equals(other.getHigh()))
            && (this.getLow() == null ? other.getLow() == null : this.getLow().equals(other.getLow()))
            && (this.getClose() == null ? other.getClose() == null : this.getClose().equals(other.getClose()))
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getAdjClose() == null ? other.getAdjClose() == null : this.getAdjClose().equals(other.getAdjClose()))
            && (this.getPriceChanges() == null ? other.getPriceChanges() == null : this.getPriceChanges().equals(other.getPriceChanges()))
            && (this.getHighTimeId() == null ? other.getHighTimeId() == null : this.getHighTimeId().equals(other.getHighTimeId()))
            && (this.getLowTimeId() == null ? other.getLowTimeId() == null : this.getLowTimeId().equals(other.getLowTimeId()))
            && (this.getAmplitude() == null ? other.getAmplitude() == null : this.getAmplitude().equals(other.getAmplitude()))
            && (this.getHighPer() == null ? other.getHighPer() == null : this.getHighPer().equals(other.getHighPer()))
            && (this.getLowPer() == null ? other.getLowPer() == null : this.getLowPer().equals(other.getLowPer()))
            && (this.getClosePer() == null ? other.getClosePer() == null : this.getClosePer().equals(other.getClosePer()))
            && (this.getOpenPer() == null ? other.getOpenPer() == null : this.getOpenPer().equals(other.getOpenPer()))
            && (this.getTurnVolume() == null ? other.getTurnVolume() == null : this.getTurnVolume().equals(other.getTurnVolume()))
            && (this.getTurnoverRate() == null ? other.getTurnoverRate() == null : this.getTurnoverRate().equals(other.getTurnoverRate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        result = prime * result + ((getHigh() == null) ? 0 : getHigh().hashCode());
        result = prime * result + ((getLow() == null) ? 0 : getLow().hashCode());
        result = prime * result + ((getClose() == null) ? 0 : getClose().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getAdjClose() == null) ? 0 : getAdjClose().hashCode());
        result = prime * result + ((getPriceChanges() == null) ? 0 : getPriceChanges().hashCode());
        result = prime * result + ((getHighTimeId() == null) ? 0 : getHighTimeId().hashCode());
        result = prime * result + ((getLowTimeId() == null) ? 0 : getLowTimeId().hashCode());
        result = prime * result + ((getAmplitude() == null) ? 0 : getAmplitude().hashCode());
        result = prime * result + ((getHighPer() == null) ? 0 : getHighPer().hashCode());
        result = prime * result + ((getLowPer() == null) ? 0 : getLowPer().hashCode());
        result = prime * result + ((getClosePer() == null) ? 0 : getClosePer().hashCode());
        result = prime * result + ((getOpenPer() == null) ? 0 : getOpenPer().hashCode());
        result = prime * result + ((getTurnVolume() == null) ? 0 : getTurnVolume().hashCode());
        result = prime * result + ((getTurnoverRate() == null) ? 0 : getTurnoverRate().hashCode());
        return result;
    }
    
    public String toString () {
//    	ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    	
//    	builder.append("stock", this.getStock());
//    	builder.append("date", this.getDate());
//    	
//    	builder.append("high", this.getHigh());
//    	builder.append("low", this.getLow());
//    	
//    	builder.append("highper", this.getHighPer());
//    	builder.append("lowper", this.getLowPer());
    	
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append("GStockDay");
    	builder.append("[");
    	
    	builder.append("stock=" + this.getStock());
    	
    	builder.append(",date=");
    	if (this.getDate() != null) {
    		builder.append(Global.DF_DAY.format(this.getDate()));
    	}
    	
    	builder.append(",high=" + this.getHigh());
    	builder.append(",low=" + this.getLow());
    	
    	builder.append(",highper=" + this.getHighPer());
    	builder.append(",lowper=" + this.getLowPer());
    	
    	builder.append("]");
    	
		return builder.toString();
    }

//    public String toString() {		
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
//	}
    
	public GStockDay getPrevDay() {
		return prevDay;
	}

	public void setPrevDay(GStockDay prevDay) {
		this.prevDay = prevDay;
	}

	public GStockDay getNextDay() {
		return nextDay;
	}

	public void setNextDay(GStockDay nextDay) {
		this.nextDay = nextDay;
	}

	public List<GStockDay> getPrev5Day() {
		return prev5Day;
	}

	public void setPrev5Day(List<GStockDay> prev5Day) {
		this.prev5Day = prev5Day;
	}

	public List<GStockDay> getNext5Day() {
		return next5Day;
	}

	public void setNext5Day(List<GStockDay> next5Day) {
		this.next5Day = next5Day;
	}

	public List<GStockDay> getPrev10Day() {
		return prev10Day;
	}

	public void setPrev10Day(List<GStockDay> prev10Day) {
		this.prev10Day = prev10Day;
	}

	public List<GStockDay> getNext10Day() {
		return next10Day;
	}

	public void setNext10Day(List<GStockDay> next10Day) {
		this.next10Day = next10Day;
	}

	public Double getMA5() {
		return MA5;
	}

	public void setMA5(Double mA5) {
		MA5 = mA5;
	}

	public Double getMA10() {
		return MA10;
	}

	public void setMA10(Double mA10) {
		MA10 = mA10;
	}

	public int getDateDec() {
		return dateDec;
	}

	public void setDateDec(int dateDec) {
		this.dateDec = dateDec;
	}

	

	
}