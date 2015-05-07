package com.st.analysis.controller.vo.charts;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.st.framework.module.stock.GStockDay;
import com.st.framework.utils.DateUtil;

public class AsBean {

	private Double highPerAvg;

	private Double lowPerAvg;
	
	private Double openAvg;
	
	private Double closeAvg;
	
	private float dateAvg;
	
	private float dateHighAvg;
	
	private float dateLowAvg;
	
	private int size;
	
	private int otherSize;
	
	private CountBean nextCountInfo = new CountBean();
	
	public AsBean() {
	}

	public AsBean(List<GStockDay> list) {

		if (list == null || list.size() == 0) {
			return;
		}

		Double sumHighPer = 0d;
		Double sumLowPer = 0d;
		
		Double sumOpen = 0d;
		Double sumClose = 0d;
		
		float sumDate = 0f;
		
		Calendar cal = Calendar.getInstance();
		int ln = list.size();
		GStockDay gStockDay = null;
		
		int sumHighDate = 0;
		int sumLowDate = 0;
		
		for (int i=0; i<ln; i++) {
			gStockDay = list.get(i);
			
			sumHighPer += gStockDay.getHighPer();
			sumLowPer += gStockDay.getLowPer();
			
			sumOpen += gStockDay.getOpen();
			sumClose += gStockDay.getClose();
			
			if (i < ln-1) {
				
				sumDate += DateUtil.getDaysDec(
						list.get(i).getDate(), 
						list.get(i+1).getDate());
			}
		}
		
		this.setHighPerAvg(sumHighPer / ln);
		this.setLowPerAvg(sumLowPer / ln);
		this.setOpenAvg(sumOpen / ln);
		this.setCloseAvg(sumClose / ln);
		this.setDateAvg(sumDate / ln);
	}

	public Double getHighPerAvg() {
		return highPerAvg;
	}

	public void setHighPerAvg(Double highPerAvg) {
		this.highPerAvg = highPerAvg;
	}

	public Double getLowPerAvg() {
		return lowPerAvg;
	}

	public void setLowPerAvg(Double lowPerAvg) {
		this.lowPerAvg = lowPerAvg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

	public Double getOpenAvg() {
		return openAvg;
	}

	public void setOpenAvg(Double openAvg) {
		this.openAvg = openAvg;
	}

	public Double getCloseAvg() {
		return closeAvg;
	}

	public void setCloseAvg(Double closeAvg) {
		this.closeAvg = closeAvg;
	}

	public float getDateAvg() {
		return dateAvg;
	}

	public void setDateAvg(float dateAvg) {
		this.dateAvg = dateAvg;
	}

	public float getDateHighAvg() {
		return dateHighAvg;
	}

	public void setDateHighAvg(float dateHighAvg) {
		this.dateHighAvg = dateHighAvg;
	}

	public float getDateLowAvg() {
		return dateLowAvg;
	}

	public void setDateLowAvg(float dateLowAvg) {
		this.dateLowAvg = dateLowAvg;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOtherSize() {
		return otherSize;
	}

	public void setOtherSize(int otherSize) {
		this.otherSize = otherSize;
	}

	public CountBean getNextCountInfo() {
		return nextCountInfo;
	}

	public void setNextCountInfo(CountBean nextCountInfo) {
		this.nextCountInfo = nextCountInfo;
	}
	
}
