<%@ page language="java" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var birtType = ${birtType};
				var weekflag = ${weekflag};
				var data;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),
					monthStart = new Date(),monthEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid +"&weekflag=" +weekflag ,
					url:"${base}/common-week/getWeeks.${actionExt}",
					dataType: 'json',
					async: false,
					success:function(msg){
						data = msg;
						//虚拟报表各个分类时间区间
						dateRange = eval(msg)[0];
						for(var i=0; i<dateRange.length; i++){
							if(dateRange[i][0] == 1){
								dayStart = parseDate(dateRange[i][1], 1, null);
								dayEnd = parseDate(dateRange[i][2], 1, null);
							}else if(dateRange[i][0] == 2){
								monthStart = parseDate(dateRange[i][1], 2, 1);
								monthEnd = parseDate(dateRange[i][2], 2, 2);
							}
						}
					}
				});
				/* Ajax取数据完毕  */
				if(birtType==2||birtType==6||birtType==7||birtType==4||birtType==8){
						var op = $('#rpt_day');
						calendar(op.attr("value"));
				}
				if(birtType==3){
					var op = $('#rpt_hour');
					calendar(op.attr("value"));
				}
				$('#rpt_day').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				$('#rpt_month').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				
				function calendar(val){
					if(val==1){
						$('#daybirt').css('display', 'inline');
						$('#monthbirt').css('display', 'none');
						var def_date = new Date(dayEnd);
						//def_date.addMonths(-1);
						def_date.setDate(1);
						var start_date = date_formart(def_date,'Ymd');
						var end_date = date_formart(dayEnd,'Ymd');
						/* 设置日/月输入框不可见，周输入框可见，并将周输入框清空  */
						$('#daybirt').val(start_date + "-" +end_date);
						$('#daybirt').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: dayEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'days',
							onChange: function(formated){
								$('#daybirt').val(formated.join('-'));
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > dayEnd.valueOf())||(date.valueOf() < dayStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}
						});
					}else if(val==2){
						var def_date = new Date(monthEnd);
						def_date.addYears(-1);
						var start_date = date_formart(def_date,'Ym');
						var end_date = date_formart(monthEnd,'Ym');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#monthbirt').css('display', 'inline');
						$('#daybirt').css('display', 'none');
						$('#monthbirt').val(start_date + "-" +end_date);
						
						$('#monthbirt').DatePicker({
							format:'Ym',
							date: new Date(),
							current: monthEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'moths',
							onChange: function(formated, dates){
								$('#monthbirt').val(formated.join('-'));
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > monthEnd.valueOf())||(date.valueOf() < monthStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						}); 
					}
				}
				
			}
		);
	</script>
	<!-- birt 时间控件样式/下拉框  开始  -->
	<style type="text/css" media="screen">
		.inp_cal{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px 
		}
		
		select{
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    border-radius: 4px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		}
	</style>
	<!-- birt  时间控件样式/下拉框  结束  -->
