<%@ page language="java" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var birtType = ${birtType};
				var data;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),weekStart = '201301',weekEnd = '201301',weekDate = new Date(),
					monthStart = new Date(),monthEnd = new Date(),weekRange = "201301-201301", hourStart = new Date(),hourEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid ,
					url:"${base}/common-week/getWeeks.${actionExt}",
					dataType: 'json',
					async: false,
					success:function(msg){
						data = msg;
						//虚拟报表各个分类时间区间
						dateRange = eval(msg)[5];
						//周 默认区间
						weekRange = eval(msg)[6];
						for(var i=0; i<dateRange.length; i++){
							if(dateRange[i][0] == 1){
								if(birtType==3){
									hourStart = parseDate(dateRange[i][1], 1, null);
									hourEnd = parseDate(dateRange[i][2], 1, null);
								}
								if(birtType==2||birtType==6||birtType==7||birtType==4||birtType==8||birtType==11||birtType==13||birtType==12){
									dayStart = parseDate(dateRange[i][1], 1, null);
									dayEnd = parseDate(dateRange[i][2], 1, null);
								}
								/* if(birtType==9){
									dayStart =parseDate(dateRange[i][2],1,null);
									dayEnd = parseDate(dateRange[i][2], 1, null);
									alert(dayStart+"sdfasf"+dayEnd);
								} */
							}else if(dateRange[i][0] == 2){
								monthStart = parseDate(dateRange[i][1], 2, 1);
								monthEnd = parseDate(dateRange[i][2], 2, 2);
							}else if(dateRange[i][0] == 4){
								weekStart = dateRange[i][1];
								weekEnd = dateRange[i][2];
								weekDate = parseDate(weekEnd.substring(0,4) + "0101",1,null);
							}
						}
					}
				});
				/* Ajax取数据完毕  */
				if(birtType==2||birtType==6||birtType==7||birtType==4||birtType==8||birtType==11||birtType==13||birtType==12){
						var op = $('#rpt_day_');
						calendar(op.attr("value"));
				}
				if(birtType==3){
					var op = $('#rpt_hour_');
					calendar(op.attr("value"));
				}
				
				
				$('#rpt_hour_').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				$('#rpt_day_').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				$('#rpt_month_').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				$('#rpt_week_').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				
				function calendar(val){
					if(val==1){
						$('#databirt_').css('display', 'inline');
						$('#monthbirt_').css('display', 'none');
						$('#weekbirt_').css('display', 'none');
						if(birtType==2||birtType==6||birtType==7||birtType==4||birtType==8||birtType==11||birtType==13||birtType==12){
							var def_date = new Date(dayEnd);
							def_date.addMonths(-1);
							var start_date = date_formart(def_date,'Ymd');
							var end_date = date_formart(dayEnd,'Ymd');
							/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
							$('#databirt_').val(end_date + "-" +end_date);
							 $('#databirt_').DatePicker({
								format:'Ymd',
								date: new Date(),
								current: dayEnd,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'days',
								onChange: function(formated){
									$('#databirt_').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > dayEnd.valueOf())||(date.valueOf() < dayStart.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							});
						}else{
							var end_date = date_formart(hourEnd,'Ymd');
							/* 设置日/月输入框不可见，周输入框可见，并将周输入框清空  */
							$('#databirt_').val(end_date + "-" +end_date);
							$('#databirt_').DatePicker({
								format:'Ymd',
								date: new Date(),
								current: hourEnd,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'days',
								onChange: function(formated){
									$('#databirt_').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > hourEnd.valueOf())||(date.valueOf() < hourStart.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}
							});
							
						}
						
					}else if(val==2){
						var def_date = new Date(monthEnd);
						def_date.addYears(-1);
						var start_date = date_formart(def_date,'Ym');
						var end_date = date_formart(monthEnd,'Ym');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#monthbirt_').css('display', 'inline');
						$('#databirt_').css('display', 'none');
						$('#weekbirt_').css('display', 'none');
						$('#monthbirt_').val(start_date + "-" +end_date);
						
						$('#monthbirt_').DatePicker({
							format:'Ym',
							date: new Date(),
							current: monthEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'moths',
							onChange: function(formated, dates){
								$('#monthbirt_').val(formated.join('-'));
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > monthEnd.valueOf())||(date.valueOf() < monthStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						}); 
					}else if(val==4){
						/* 设置日/月输入框不可见，周输入框可见，并将周输入框清空  */
						$('#databirt_').css('display', 'none');
						$('#monthbirt_').css('display', 'none');
						$('#weekbirt_').css('display', 'inline');
						$('#weekbirt_').val(weekRange);
						
						$('#weekbirt_').WeekPicker({
							//flat: true,
							format:'m/d/Y',
							date: ['201201'],
							current: weekDate,
							starts: 1,
							position: 'bottom_right',
							mode: 'range',
							data: data,
							onChange: function(formated){
								$('#weekbirt_').val(formated.join('-'));
							},
							onRender: function(date) {
								return {
									disabled: (date.valueOf() < weekStart)||(date.valueOf() > weekEnd),
									className: date.valueOf() == weekEnd ? 'datepickerSpecial' : false
								}
							}
						});
					}/* else if(val==11){
						var end_date = date_formart(hourEnd,'Ymd');
						/* 设置日/月输入框不可见，周输入框可见，并将周输入框清空  
						$('#hourbirt').css('display', 'inline');
						$('#databirt').css('display', 'none');
						$('#monthbirt').css('display', 'none');
						$('#weekbirt').css('display', 'none');
						$('#hourbirt').val(end_date + "-" +end_date);
						
						$('#hourbirt').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: hourEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'days',
							onChange: function(formated){
								$('#hourbirt').val(formated.join('-'));
							},
							/* 此处为当前日期之后不可选 
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > hourEnd.valueOf())||(date.valueOf() < hourStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}
						});
					} */
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
