package com.st.analysis.controller.actions.charts;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;


import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.controller.actions.ConventionAction;
import com.st.framework.module.stock.example.GStockDayExample;

@Namespace("/analysis")
@InterceptorRef("common-params")
@Results({
		@Result(name = "per", location = "/analysis/report/per.jsp"),
		@Result(name = "list", location = "/analysis/report/list.jsp"),
		@Result(name = "select-list", location = "/analysis/report/select-list.jsp") })
public class AnalysisAction extends ConventionAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AnalysisAction.class);
		

	@Autowired
	private GStockDayManager gStockDayManager;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2890609790434307797L;

	/**
	 * yyyy-MM-dd
	 */
	private String timeid;

	private String prevtimeid;

	private String nexttimeid;

	private Integer stock;

	private List<Integer> stocks;

	// private AnalysisOrderBean order = new AnalysisOrderBean();

	// private String orderby;

	private Map<String, Integer> order = new HashMap<String, Integer>();

	private Map<String, String> compares;

	private Map<String, Integer> hiddens;
	
	private Map<String, Map<String, String>> conditions;

	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Action("list")
	public String stlist() throws ParseException {
		// timeid = "2015-03-18";

		logger.info("compares=" + compares);
		logger.info("conditions=" + conditions);

		appendTimeId();

		GStockDayExample example = new GStockDayExample();

		example.setDistinct(true);

		example.setOrderByClause("STOCK");

		GStockDayExample.Criteria c = example.createCriteria();

		c.andDateEqualTo(df.parse(timeid));

		if (stocks != null) {
			c.andStockIn(stocks);
		}

		// this.getRequest().setAttribute("list",
		// gStockDayManager.selectList(df.parse(timeid), order));
		this.getRequest().setAttribute("list",
				gStockDayManager.selectListByExample(example, order));

		if (logger.isDebugEnabled()) {
			logger.debug("stlist() - end");
		}
		return "list";
	}

	private void appendTimeId() throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("appendTimeId() - start");
		}

		if (timeid == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -2);
			timeid = df.format(c.getTime());
		}
		{
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(timeid));

			c.add(Calendar.DAY_OF_MONTH, -1);

			this.prevtimeid = df.format(c.getTime());

			c.add(Calendar.DAY_OF_MONTH, 2);

			this.nexttimeid = df.format(c.getTime());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("appendTimeId() - end");
		}
	}

	@Action("selectlist")
	public String selectList() throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectList() - start");
		}

		appendTimeId();

		if (this.stock != null) {
			GStockDayExample example = new GStockDayExample();

			example.setDistinct(true);

			example.setOrderByClause("DATE");

			GStockDayExample.Criteria c = example.createCriteria();

			c.andStockEqualTo(stock);

			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(timeid));

			cal.add(Calendar.DAY_OF_MONTH, -20);
			c.andDateGreaterThanOrEqualTo(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 41);
			c.andDateLessThanOrEqualTo(cal.getTime());

			this.getRequest().setAttribute("list",
					gStockDayManager.selectListByExample(example, order));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectList() - end");
		}
		return "select-list";
	}

	@Action("per")
	public String per() {

		return "per";
	}

	public String getTimeid() {
		return timeid;
	}

	public void setTimeid(String timeid) {
		this.timeid = timeid;
	}

	public String getPrevtimeid() {
		return prevtimeid;
	}

	public void setPrevtimeid(String prevtimeid) {
		this.prevtimeid = prevtimeid;
	}

	public String getNexttimeid() {
		return nexttimeid;
	}

	public void setNexttimeid(String nexttimeid) {
		this.nexttimeid = nexttimeid;
	}

	public Map<String, Integer> getOrder() {
		return order;
	}

	public void setOrder(Map<String, Integer> order) {
		this.order = order;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List getStocks() {
		return stocks;
	}

	public void setStocks(List stocks) {
		this.stocks = stocks;
	}

	public Map<String, String> getCompares() {
		return compares;
	}

	public void setCompares(Map<String, String> compares) {
		this.compares = compares;
	}

	public Map<String, Integer> getHiddens() {
		return hiddens;
	}

	public void setHiddens(Map<String, Integer> hiddens) {
		this.hiddens = hiddens;
	}

	public Map<String, Map<String, String>> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Map<String, String>> conditions) {
		this.conditions = conditions;
	}

	

	

}
