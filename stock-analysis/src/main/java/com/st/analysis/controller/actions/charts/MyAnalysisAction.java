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
@Results({ 
	@Result(name = "aw", location = "/analysis/wave/aw.jsp")
})
public class MyAnalysisAction extends BaseAnalysisAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269008539878815902L;

	@Action("aw")
	public String analysisWave() {

		this.stockCode = "300002";

		this.startDate = "2014-10-01";
		// this.endDate = "2015-04-30";

		DStock dStock = dStockManager.selectByPrimaryKey(this.stockCode);
		this.getRequest().setAttribute("stock", dStock);
		this.lowPer = -8d;
		this.highPer = null;

		{
			GStockDayExample gStockDayExample = new GStockDayExample();
			gStockDayExample.setOrderByClause("DATE DESC");

			GStockDayExample.Criteria orhighc = null;
			GStockDayExample.Criteria orlowc = null;

			if (this.highPer != null) {
				orhighc = gStockDayExample.or();

				if (this.stockCode != null) {
					orhighc.andStockEqualTo(Integer.parseInt(this.stockCode));
				}

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

				if (this.stockCode != null) {
					orlowc.andStockEqualTo(Integer.parseInt(this.stockCode));
				}

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

			List<GStockDay> dataList = this.gStockDayManager
					.selectByExample(gStockDayExample);

			if (dataList != null && dataList.size() > 0) {
				AsBean asBean = new AsBean(dataList);
				this.getRequest().setAttribute("infobean", asBean);

				int ln = dataList.size();
				for (int i = 0; i < ln; i++) {

					if (i < ln - 1) {
						dataList.get(i).setDateDec(
								DateUtil.getDaysDec(dataList.get(i).getDate(),
										dataList.get(i + 1).getDate()));
					}
				}
			}

			this.getRequest().setAttribute("list", dataList);
		}

		return "aw";
	}
	

}
