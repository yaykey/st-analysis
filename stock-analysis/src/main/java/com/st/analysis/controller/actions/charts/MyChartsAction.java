package com.st.analysis.controller.actions.charts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.codehaus.jackson.map.ObjectMapper;

import com.st.analysis.controller.actions.BaseAnalysisAction;
import com.st.analysis.utils.wave.WaveUtils;
import com.st.framework.controller.actions.BaseAction;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.example.GDetailExample;

@Namespace("/mycharts")
@Results( {
		@Result(name = "charts", location = "/charts/st.jsp")
		,@Result(name = "charts2", location = "/charts/st2.jsp")
})
public class MyChartsAction extends BaseAnalysisAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -681408160915941677L;

	
	
	@Action("charts")
	public String charts () throws ParseException {
		
		
		
		this.stockCode = "sz300002";
		this.dateId = 20141224;
		
		List<GDetail> list = getWaveList ();
		
//        try {
//            this.getResponse().setCharacterEncoding("UTF-8");
//            this.getResponse().getWriter().write(buffer.toString());
//            this.getResponse().getWriter().flush();
//            this.getResponse().getWriter().close();
//        } catch (Exception e) {
//            LOG.error("writeJson error!msg:\n" + e.getMessage());
//        }
		
		this.getRequest().setAttribute("jsonData", getJsonData(list).toString());
		
		this.getRequest().setAttribute("jsonData2", getJsonData(WaveUtils.getWaveOptimize(list, 0.01)).toString());
		
		
		return "charts";
	}
	
	@Action("charts2")
	public String charts2 () throws ParseException {
		
		this.stockCode = "sz300002";
		
		
		if (this.dateId == null) {
			this.dateId = 20141225;
		}
		
		this.startTime = "09:30:00";
//		this.startTime = "10:00:00";
//		this.startTime = "09:35:00";
//		this.startTime = "13:00:00";
//		this.startTime = "14:00:00";
		
		
//		this.endTime = "09:50:00";
//		this.endTime = "10:00:00";
//		this.endTime = "10:30:00";
//		this.endTime = "11:00:00";
//		this.endTime = "14:00:00";
		this.endTime = "15:00:00";
		
		List<GDetail> list = getWaveList ();
		
//        try {
//            this.getResponse().setCharacterEncoding("UTF-8");
//            this.getResponse().getWriter().write(buffer.toString());
//            this.getResponse().getWriter().flush();
//            this.getResponse().getWriter().close();
//        } catch (Exception e) {
//            LOG.error("writeJson error!msg:\n" + e.getMessage());
//        }
		
		this.getRequest().setAttribute("jsonData", getJsonData(list).toString());
		
		this.getRequest().setAttribute("jsonData2", getJsonData(WaveUtils.getWaveOptimize(list, 0)).toString());
		
//		this.getRequest().setAttribute("jsonData3", getJsonData(WaveUtils.getWaveOptimize(list, 0, true)).toString());
		
		this.getRequest().setAttribute("jsonData4", getJsonData(WaveUtils.getWaveOptimize(list, 0.01, true)).toString());
//		this.getRequest().setAttribute("jsonData2", getJsonData(WaveUtils.getWave(list)).toString());
		
		
		return "charts2";
	}
	
	private List<GDetail> getWaveList () {
		GDetailExample example = new GDetailExample();
		
		example.setDistinct(true);
		example.setOrderByClause("DATE_ID, TIME_ID");
		
//		example.setStockCode("sz300002");
		example.setStockCode(this.stockCode);
		
		GDetailExample.Criteria c = example.createCriteria();
		List<Integer> values = new ArrayList<Integer>();
//		values.add(20141118);
//		values.add(20141117);
		
		if (this.dateId != null) {
			values.add(dateId);
		}
		
		c.andDateIdIn(values);
		
		if (this.startTime != null) {
			c.andTimeIdGreaterThanOrEqualTo(startTime);
		}
		
		if (this.endTime != null) {
			c.andTimeIdLessThanOrEqualTo(this.endTime);
		}
		
//		List<GDetail> list = gDetailManager.selectByExample(example);
		List<GDetail> list = gDetailManager.selectWareByExample(example);
		
		return list;
	}
	
	private StringBuilder getJsonData (List<GDetail> list) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		
		StringBuilder buffer = new StringBuilder();
		
		buffer.append('[');
		GDetail detail = null;
		for (int i=0; i<list.size(); i++) {
			detail = list.get(i);
			
			if (i != 0) {
				buffer.append(',');
			}
			
			buffer.append('[');
			try {
				buffer.append(
					df.parse(
						detail.getDateId() + " " + detail.getTimeId()
					).getTime()
					+ 1000*60*60*8
				);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			buffer.append(',');
			buffer.append(detail.getPrice());
			buffer.append(']');
		}
		buffer.append(']');
		
		return buffer;
	}



	
}
