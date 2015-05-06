package com.st.analysis.controller.actions.charts;

import java.text.ParseException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

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

	@Action("aw")
	public String analysisWave() {

		this.stockCode = "300002";
		
		this.startDate = "2014-10-01";
//		this.endDate = "2015-04-30";
		
		DStock dStock = dStockManager.selectByPrimaryKey(this.stockCode);
		this.getRequest().setAttribute("stock", dStock);
		
		{
			GStockDayExample gStockDayExample = new GStockDayExample();
			gStockDayExample.setOrderByClause("DATE DESC");
//			GStockDayExample.Criteria c = gStockDayExample.createCriteria();
			
			GStockDayExample.Criteria orc1 = gStockDayExample.or();
			GStockDayExample.Criteria orc2 = gStockDayExample.or();
			
			if (this.stockCode != null) {
				orc1.andStockEqualTo(Integer.parseInt(this.stockCode));
				orc2.andStockEqualTo(Integer.parseInt(this.stockCode));
			}

			if (this.startDate != null) {
				try {
					orc1.andDateGreaterThanOrEqualTo(DF_DATE_ID
							.parse(this.startDate));
					orc2.andDateGreaterThanOrEqualTo(DF_DATE_ID
							.parse(this.startDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (this.endDate != null) {
				try {
					orc1.andDateLessThanOrEqualTo(DF_DATE_ID.parse(this.endDate));
					orc2.andDateLessThanOrEqualTo(DF_DATE_ID.parse(this.endDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if (this.highPer != null) {
				orc1.andHighPerGreaterThanOrEqualTo(this.highPer);
			}
			
			if (this.lowPer != null) {
				orc2.andLowPerLessThanOrEqualTo(this.lowPer);
				
			}

			List<GStockDay> dataList = this.gStockDayManager
					.selectByExample(gStockDayExample);
			
//			for (GStockDay gStockDay : dataList) {
//				System.out.println(gStockDay);
//				System.out.println(gStockDay.getPrevDay());
//				System.out.println(gStockDay.getNextDay());
//			}
			
			
			if (dataList != null && dataList.size() > 0) {
				AsBean asBean = new AsBean(dataList);
				this.getRequest().setAttribute("infobean", asBean);
				
				int ln = dataList.size();
				for (int i=0; i<ln; i++) {
					
					if (i < ln-1) {
						dataList.get(i).setDateDec(DateUtil.getDaysDec(
								dataList.get(i).getDate(), 
								dataList.get(i+1).getDate()));
					}
				}
			}
			
			
			
			this.getRequest().setAttribute("list", dataList);
		}

		return "aw";
	}

}
