<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${base }/date/js/datepicker.js"></script>
	<script type="text/javascript">
	/**
	*	BMP监控
	**/
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				/* var rptid = ${vrtlId};
				var data;
				var weekflag= 1;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),weekStart = '201301',weekEnd = '201301',
				monthStart = new Date(),monthEnd = new Date(),weekRange = "201301-201301",hourStart = new Date(), hourEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid + "&weekflag=" + weekflag ,
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
				}); */
				var dayTime,minute;
				dayTime = new Date();
				minute = dayTime.getMinutes()-5;
				dayTime.setMinutes(minute);
				var dayEnd = dayTime;
				
				/* Ajax取数据完毕  */
				calendar(1);
				
				function calendar(val){
					var iden = false;
					if(val==1){
						var start_date = date_formart(new Date(new Date(dayEnd).setHours(00,00)),'Ymd H:M');
						var end_date = date_formart(new Date(new Date(dayEnd)),'Ymd H:M');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#data').css('display', 'inline');
						$('#month').css('display', 'none');
						$('#week').css('display', 'none');
						$('#monthbirt').css('display', 'none');
						$('#weekbirt').css('display', 'none');
						$('#data').val(start_date + "-" +end_date);
						$('#databirt').val(start_date + "-" +end_date);
						
						/**
						**	图形使用
						**/
						$('#data').DatePicker({
							format:'Ymd H:M',
							date: dayEnd,
							current: dayEnd,
							maxDay: 7,
							starts: 1,
							time: true,
							areaToHide: false,
							position: 'bottom',
							calendars: 1,
							mode: 'range',
							view: 'days',
							onChange: function(formated){
								$("#onehour").removeClass("button_on");
								$("#today").removeClass("button_on");
								$('#data').val(formated.join('-'));
							},
							onSub: function(){
								hideToSearch();
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > dayEnd.valueOf())/* ||(date.valueOf() < dayStart.valueOf()) */,
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						});
						
						/**
						*
						*	birt 报表使用
						**/
						$('#databirt').DatePicker({
							format:'Ymd H:M',
							date: dayEnd,
							current: dayEnd,
							maxDay: 7,
							starts: 1,
							time: true,
							areaToHide: false,
							position: 'bottom',
							calendars: 1,
							mode: 'range',
							view: 'days',
							onChange: function(formated){
								$('#databirt').val(formated.join('-'));
							},
							onSub: function(){
								hideToSearch();
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > dayEnd.valueOf())/* ||(date.valueOf() < dayStart.valueOf()) */,
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						});
					}
				}
				
				function hideToSearch(){
					//alert(11);
				}
				
			}
		);
	</script>
	<!-- 主图   时间控件样式  开始  -->
	<style type="text/css" media="screen">
		.inp_cala{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 18px;
		    margin-left: 3px;
		    margin-top: 3px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px
		}
		.inp_cal{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 16px;
		    background-color:white;
		    border-radius:0px
		}
		.s_style{
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    border-radius: 4px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 23px;
		}
	</style>
	<!-- 主图  时间控件样式  结束  -->
