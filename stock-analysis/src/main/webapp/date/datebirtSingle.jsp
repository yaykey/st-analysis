<%@ page language="java" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var birtType = ${birtType};
				var defaultDayType = 1;
				var dayType = (${dayReportId==null}?null:${dayReportId}+"");
				var monthType = (${monthlyReportId==null}?null:${monthlyReportId}+"");
				var weekDayType = (${weeklyReportId==null}?1:null);
				var data;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),weekStart = '201301',weekEnd = '201301',weekDate = new Date(),
					monthStart = new Date(),monthEnd = new Date(),weekRange = "201301-201301",
					hourStart = new Date(),hourEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid + "&weekflag=" + weekDayType,
					url:"${base}/common-week/getWeeks.${actionExt}",
					dataType: 'json',
					async: false,
					success:function(msg){
						data = msg;
						//虚拟报表各个分类时间区间
						dateRange = eval(msg)[0];
						for(var i=0; i<dateRange.length; i++){
							if(weekDayType == null){
								if(dateRange[i][0] == 1){
									dayStart = parseDate(dateRange[i][1], 1, null);
									dayEnd = parseDate(dateRange[i][2], 1, null);
								}else if(dateRange[i][0] == 2){
									monthStart = parseDate(dateRange[i][1], 2, 1);
									monthEnd = parseDate(dateRange[i][2], 2, 2);
								}else if(dateRange[i][0] == 4){
									weekStart = dateRange[i][1];
									weekEnd = dateRange[i][2];
									weekDate = parseDate(weekEnd.substring(0,4) + "0101",1,null);
								}
							}else{
								if(dateRange[i][0] == 1){
									dayStart = parseDate(dateRange[i][1], 1, null);
									dayEnd = parseDate(dateRange[i][2], 1, null);
								}else if(dateRange[i][0] == 2){
									monthStart = parseDate(dateRange[i][1], 2, 1);
									monthEnd = parseDate(dateRange[i][2], 2, 2);
								}
							}
							
						}
					}
				});
				/* Ajax取数据完毕  */
				calendar(1);
				
				
				function calendar(val){
					if(val==1){
						 $('#databirt_').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: dayEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 1,
							mode: 'single',
							view: 'days',
							onChange: function(formated){
								$('#databirt_').val(formated);
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > dayEnd.valueOf())||(date.valueOf() < dayStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						});
					}
				}
				
			});
	</script>
	
