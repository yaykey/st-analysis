package com.st.analysis.controller.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.business.impl.dim.DStockManager;
import com.st.framework.business.impl.lhb.GLHBReportManager;
import com.st.framework.business.impl.lhb.GLHBSecuritiesManager;
import com.st.framework.business.impl.lhb.GLHBTop5Manager;
import com.st.framework.controller.actions.BaseAction;


public class BaseAnalysisAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8856073886278012592L;

	@Autowired
	protected GDetailManager gDetailManager;
	
	@Autowired
	protected GStockDayManager gStockDayManager;
	
	@Autowired
	protected DStockManager dStockManager;
	
	@Autowired
	protected GLHBTop5Manager gLHBTop5Manager;
	
	@Autowired
	protected GLHBSecuritiesManager gLHBSecuritiesManager;
	
	@Autowired
	protected GLHBReportManager gLHBReportManager;
	
	public static final DateFormat DF_DATE_ID = new SimpleDateFormat("yyyy-MM-dd");
	
	protected Integer dateId;
	
	protected Date timeId;
	
	protected Date startTimeId;
	
	protected Date endTimeId;
	
	protected String stockCode;
	
	protected String startTime;
	
	protected String endTime;
	
	protected String startDate;
	
	protected String endDate;
	
	protected Double highPer;
	
	protected Double lowPer;

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public Date getTimeId() {
		return timeId;
	}

	public void setTimeId(Date timeId) {
		this.timeId = timeId;
	}

	public Date getStartTimeId() {
		return startTimeId;
	}

	public void setStartTimeId(Date startTimeId) {
		this.startTimeId = startTimeId;
	}

	public Date getEndTimeId() {
		return endTimeId;
	}

	public void setEndTimeId(Date endTimeId) {
		this.endTimeId = endTimeId;
	}

	
}
