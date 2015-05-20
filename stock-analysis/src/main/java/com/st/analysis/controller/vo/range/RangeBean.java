package com.st.analysis.controller.vo.range;

import com.st.framework.module.PersistentObject;

public class RangeBean extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891109093617635156L;

	
	private Integer timeDimId;
	
	private double price;
	
	private String nature;
	
	private Integer cjbs;
	
	private double sumVol;
	
	private double sumAmo;
	
	private Integer volDimId;

	public Integer getTimeDimId() {
		return timeDimId;
	}

	public void setTimeDimId(Integer timeDimId) {
		this.timeDimId = timeDimId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Integer getCjbs() {
		return cjbs;
	}

	public void setCjbs(Integer cjbs) {
		this.cjbs = cjbs;
	}

	public double getSumVol() {
		return sumVol;
	}

	public void setSumVol(double sumVol) {
		this.sumVol = sumVol;
	}

	public double getSumAmo() {
		return sumAmo;
	}

	public void setSumAmo(double sumAmo) {
		this.sumAmo = sumAmo;
	}

	public Integer getVolDimId() {
		return volDimId;
	}

	public void setVolDimId(Integer volDimId) {
		this.volDimId = volDimId;
	}
}
