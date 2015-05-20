package com.st.analysis.controller.actions.lhb;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;

import com.st.Global;
import com.st.analysis.controller.actions.BaseAnalysisAction;
import com.st.analysis.controller.vo.lhb.TopBean;
import com.st.analysis.controller.vo.lhb.RelationBean;
import com.st.analysis.controller.vo.lhb.StatisticsBean;
import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.module.stock.GLHBReport;
import com.st.framework.module.stock.GLHBTop5;
import com.st.framework.module.stock.GLHBTop5Key;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.GStockDayKey;
import com.st.framework.module.stock.example.GLHBReportExample;
import com.st.framework.module.stock.example.GLHBTop5Example;

@Namespace("/lhb")
@Results({
		@Result(name = "list", location = "/analysis/lhb/list.jsp"),
		@Result(name = "sec-list", location = "/analysis/lhb/sec-list.jsp"),
		@Result(name = "sec-detail", location = "/analysis/lhb/sec-detail.jsp"),
		@Result(name = "sec-near-detail", location = "/analysis/lhb/sec-near-detail.jsp"),
		@Result(name = "sec-relation", location = "/analysis/lhb/sec-relation.jsp")
})
public class LHBAction extends BaseAnalysisAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LHBAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7436997375799963830L;

	private Long secid;

	@Action("list")
	public String lhb() {

		if (this.stockCode == null) {
			this.stockCode = "300002";
		}

		{
			GLHBReportExample example = new GLHBReportExample();
			example.setOrderByClause("DATE_ID desc");

			GLHBReportExample.Criteria c = example.createCriteria();

			c.andStockCodeEqualTo(this.stockCode);

			List<GLHBReport> reports = this.gLHBReportManager
					.selectByExample(example);

			this.getRequest().setAttribute("reports", reports);

		}
		return "list";
	}

	@Action("sec-list")
	public String seclist() {

		GLHBTop5Example example = new GLHBTop5Example();
		example.setOrderByClause("DATE_ID desc");

		GLHBTop5Example.Criteria c = example.createCriteria();
		c.andSecIdEqualTo(secid);

		if (this.startDate == null) {
			this.startDate = "2015-01-01";
		}

		if (this.startDate != null) {
			try {
				c.andDateIdGreaterThanOrEqualTo(Global.DF_DAY
						.parse(this.startDate));
			} catch (ParseException e) {
				logger.warn("seclist() - exception ignored", e);

			}
		}

		if (this.endDate != null) {
			try {
				c.andDateIdLessThanOrEqualTo(Global.DF_DAY.parse(this.endDate));
			} catch (ParseException e) {
				logger.warn("seclist() - exception ignored", e);
			}
		}

		List<GLHBTop5> list = gLHBTop5Manager.selectByExample(example);

		if (list != null && list.size() > 0) {
			List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();

			for (final GLHBTop5 top5 : list) {

				tasks.add(new Callable<Integer>() {
					public Integer call() {

						GStockDayManager gStockDayManager = (GStockDayManager) Global._ctx
								.getBean("gStockDayManager");
						GStockDayKey key = new GStockDayKey();
						key.setStock((stockCode));
						key.setDate(top5.getDateId());

						GStockDay gsd = gStockDayManager
								.selectByPrimaryKey(key);

						// System.out.println(gsd);

						top5.setNoticeStock(gsd);

						return 0;
					}
				});
			}

			if (tasks.size() > 0) {
				try {
					Global.threadPoolExecutor.invokeAll(tasks);
				} catch (InterruptedException e) {
					logger.warn("seclist() - exception ignored", e);
				}
			}

		}

		this.getRequest().setAttribute("list", list);

		return "sec-list";
	}

	@Action("sec-detail")
	public String secDetail() {
		GLHBTop5Example example = new GLHBTop5Example();

		example.setOrderByClause("DATE_ID desc");

		GLHBTop5Example.Criteria c = example.createCriteria();
		c.andDateIdEqualTo(this.timeId);
		c.andSecIdEqualTo(secid);

		List<GLHBTop5> list = this.gLHBTop5Manager.selectByExample(example);

		this.getRequest().setAttribute("list", list);

		return "sec-detail";
	}

	@Action("sec-near-detail")
	public String nearDetail() {
		GLHBTop5Key key = new GLHBTop5Key();
		key.setSecId(secid);
		key.setDateId(timeId);
		key.setStockCode(stockCode);
		
		GLHBTop5 top5 = this.gLHBTop5Manager.selectByPrimaryKey(key);

		this.getRequest().setAttribute("top5", top5);
		
		top5.setPrevStatistics(new StatisticsBean(top5.getPrevList()));
		top5.setNextStatistics(new StatisticsBean(top5.getNextList()));

		return "sec-near-detail";
	}
	
	@Action("sec-relation")
	public String relation () {
		
//		List<GLHBTop5> list = this.gLHBTop5Manager
//				.selectRelationList(stockCode, this.timeId, this.startTimeId, this.endTimeId);
//		
//		this.getRequest().setAttribute("list", list);
		
		return "sec-relation";
	}
	
	@Action("sec-relation-json")
	public void relationJson () {
		
		List<GLHBTop5> list = this.gLHBTop5Manager
				.selectRelationList(stockCode, this.timeId, this.startTimeId, this.endTimeId);
		
		
		RelationBean relationBean = new RelationBean();
		if (list != null) {
			List<TopBean> data = new ArrayList<TopBean>();
			
			Map<String, Object> averageMap = new HashMap<String, Object>();
			Map<String, Object> totalMap = new HashMap<String, Object>();
			Map<String, Object> titleMap = new HashMap<String, Object>();
			
			double totalbuyAmount = 0.0;
			double totalsellAmount = 0.0;
			double totalnetAmount = 0.0;
			
			for (GLHBTop5 top : list) {
				TopBean bean = new TopBean();
				
				bean.setStockCode(top.getStockCode());
				bean.setSecId(top.getSecId());
				bean.setSecName(top.getSecName());				
				bean.setDateId(Global.DF_DAY.format(top.getDateId()));
				bean.setBuyAmount(top.getBuyAmount());
				bean.setSellAmount(top.getSellAmount());
				bean.setNetAmount(top.getNetAmount());
				bean.setType(top.getType());
				bean.setStockName(top.getStock().getStockName());
				
				totalbuyAmount += top.getBuyAmount();
				totalsellAmount += top.getSellAmount();
				totalnetAmount += top.getNetAmount();
				
				data.add(bean);
			}
			
			
			titleMap.put("secName", "Title");
			titleMap.put("buyAmount", "Buy Amount");
			titleMap.put("sellAmount", "Sell Amount");
			titleMap.put("netAmount", "Net Amount");
			
			DecimalFormat df = new DecimalFormat("#######.##");
			
			totalMap.put("secName", "Total");
			totalMap.put("buyAmount", df.format(totalbuyAmount));
			totalMap.put("sellAmount", df.format(totalsellAmount));
			totalMap.put("netAmount", df.format(totalnetAmount));
			
			int ln = data.size();
			
			
			
			averageMap.put("secName", "Average");
			averageMap.put("buyAmount", df.format(totalbuyAmount/ln));
			averageMap.put("sellAmount", df.format(totalsellAmount/ln));
			averageMap.put("netAmount", df.format(totalnetAmount/ln));
			
			List<Map<String, Object>> footer = new ArrayList<Map<String, Object>>();
			
			footer.add(titleMap);
			footer.add(totalMap);
			footer.add(averageMap);
			
			
			relationBean.setFooter(footer);
			relationBean.setRows(data);
			
		}
		
		this.writeJson(relationBean);
		
	}

	public Long getSecid() {
		return secid;
	}

	public void setSecid(Long secid) {
		this.secid = secid;
	}

}
