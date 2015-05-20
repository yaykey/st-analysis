package com.st.analysis.controller.actions.charts;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.jcraft.jsch.Logger;
import com.st.Global;
import com.st.analysis.controller.actions.BaseAnalysisAction;
import com.st.analysis.controller.vo.charts.AsBean;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GStockDayExample;
import com.st.framework.utils.DateUtil;

@Namespace("/analysis")
@Results({ @Result(name = "aw", location = "/analysis/wave/aw.jsp") })
public class MyAnalysisAction extends BaseAnalysisAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269008539878815902L;

	//>=100手 >=200手 >=500手 >=1000手 >=2000手 >=5000手 >=10000手
	private int [] valRange  = {100,200,500,1000,2000,5000,10000};
	
	@Action("aw")
	public String analysisWave() {

		initData();

		DStock dStock = dStockManager.selectByPrimaryKey(this.stockCode);
		this.getRequest().setAttribute("stock", dStock);
		List<GStockDay> dataList = null;
		AsBean asBean = null;
		{
			GStockDayExample gStockDayExample = new GStockDayExample();
			gStockDayExample.setOrderByClause("DATE desc");

			appendExample(gStockDayExample);

			List<GStockDayExample.Criteria> list = gStockDayExample.getOredCriteria();			
			for (GStockDayExample.Criteria c : list) {
				if (this.stockCode != null) {
					c.andStockEqualTo(this.stockCode);
				}
			}
			
			dataList = this.gStockDayManager.selectByExample(gStockDayExample);

			if (dataList != null && dataList.size() > 0) {
				asBean = new AsBean(dataList);
				

				int ln = dataList.size();
				asBean.setSize(ln);
				for (int i = 0; i < ln; i++) {

					if (i < ln - 1) {
						dataList.get(i).setDateDec(
								DateUtil.getDaysDec(dataList.get(i).getDate(),
										dataList.get(i + 1).getDate()));
					}
				}
			}
		}
		
		{// other example
			GStockDayExample gStockDayExample = new GStockDayExample();
			gStockDayExample.setOrderByClause("STOCK asc, DATE desc");

			appendExample(gStockDayExample);
			
			List<GStockDayExample.Criteria> list = gStockDayExample.getOredCriteria();			
			for (GStockDayExample.Criteria c : list) {
				if (this.stockCode != null) {
					c.andStockNotEqualTo((this.stockCode));
					c.andOpenPerGreaterThanOrEqualTo(-11.0);
					c.andClosePerLessThanOrEqualTo(-9.5);
					
					c.andOpenPerLessThan(-1.0);
					
					//c.andHighPerGreaterThanOrEqualTo(0.0);
				}
			}

			if (dataList == null) {
				dataList = new ArrayList<GStockDay>();
			}
			
			List<GStockDay> ortherList = this.gStockDayManager.selectByExample(gStockDayExample);
			
			if (ortherList != null && ortherList.size() > 0) {
				asBean.setOtherSize(ortherList.size());
//				System.out.println(ortherList.size());
				List<GStockDay> removeList = new ArrayList<GStockDay>();
				
				for (GStockDay std : ortherList) {
										
					if (std.getNextDay() != null && std.getNextDay().getOpenPer() != null) {
						if (std.getNextDay().getOpenPer() < 0) {
							asBean.getNextCountInfo().countInfo(std.getNextDay());
							
						} else {
							removeList.add(std);
						}
					}
				}
				ortherList.removeAll(removeList);
				
				dataList.addAll(ortherList);
			}
			
			
		}
		
		this.getRequest().setAttribute("infobean", asBean);
		this.getRequest().setAttribute("list", dataList);

		return "aw";
	}
	
	private void appendExample(GStockDayExample gStockDayExample) {
		GStockDayExample.Criteria orhighc = null;
		GStockDayExample.Criteria orlowc = null;

		if (this.highPer != null) {
			orhighc = gStockDayExample.or();

//			if (this.stockCode != null) {
//				orhighc.andStockEqualTo(Integer.parseInt(this.stockCode));
//			}

			if (this.startDate != null) {
				try {
					orhighc.andDateGreaterThanOrEqualTo(DF_DATE_ID
							.parse(this.startDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.endDate != null) {
				try {
					orhighc.andDateLessThanOrEqualTo(DF_DATE_ID
							.parse(this.endDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			orhighc.andHighPerGreaterThanOrEqualTo(this.highPer);
		}

		if (this.lowPer != null) {
			orlowc = gStockDayExample.or();

//			if (this.stockCode != null) {
//				orlowc.andStockEqualTo(Integer.parseInt(this.stockCode));
//			}

			if (this.startDate != null) {
				try {
					orlowc.andDateGreaterThanOrEqualTo(DF_DATE_ID
							.parse(this.startDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.endDate != null) {
				try {
					orlowc.andDateLessThanOrEqualTo(DF_DATE_ID
							.parse(this.endDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			orlowc.andLowPerLessThanOrEqualTo(this.lowPer);
		}
	}

	private void initData() {
		if ("".equals(this.startDate)) {
			this.startDate = null;
		}

		if ("".equals(this.endDate)) {
			this.endDate = null;
		}

		if ("".equals(this.stockCode)) {
			this.stockCode = null;
		}

		if (this.stockCode == null) {
			this.stockCode = "300002";
		}

		if (this.startDate == null) {
			this.startDate = "2015-01-01";
		}

		if (this.endDate == null) {
			this.endDate = Global.DF_DAY.format(new Date());
		}

		if (this.lowPer == null) {
			this.lowPer = -8d;
		}

		// if (this.highPer == null) {
		// this.highPer = -8d;
		// }

	}

}
