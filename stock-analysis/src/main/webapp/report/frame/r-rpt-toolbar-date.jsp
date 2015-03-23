<%@ page language="java"  pageEncoding="UTF-8"%>
<div class="fr">
	<a href="javascript:void(0)" id="select_time" class="btn2 fr"></a>
	
</div>
<div class="date fr">
	<input type="text" id="databirt"  class="inp_cal" readonly="readonly"  style="width: 125px">		
	<input type="text" id="monthbirt" class="inp_cal" readonly="readonly" style="width: 125px">	
	<input type="text" id="weekbirt"  class="inp_cal" readonly="readonly"  style="width: 125px">	
</div>

<div class="fr">
	<c:if test="${dayReportId!=null}">
		<a href="javascript:void(0)" id="rpt_day" value="1"
			class="beh_btn beh_b_on">日</a>
	</c:if>
	<c:if test="${weeklyReportId!=null}">
		<a href="javascript:void(0)" id="rpt_week" value="4" class="beh_btn">周</a>
	</c:if>
	<c:if test="${monthlyReportId!=null}">
		<a href="javascript:void(0)" id="rpt_month" value="2" class="beh_btn">月</a>
	</c:if>
</div>
