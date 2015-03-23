<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${base }/date/js/datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var dataType = ${dataType};
				var data;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),weekStart = '201301',weekEnd = '201301',weekDate = new Date(),
				monthStart = new Date(),monthEnd = new Date(),weekRange = "201301-201301",hourStart = new Date(), hourEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid ,
					url:"${base}/common-week/getWeeks.${actionExt}",
					dataType: 'json',
					async: false,
					success:function(msg){
						data = msg;
						//虚拟报表个分类时间区间
						dateRange = eval(msg)[5];
						//周 默认区间
						weekRange = eval(msg)[6];
						for(var i=0; i<dateRange.length; i++){
							if(dateRange[i][0] == 1){
								if(dataType==3){
									hourStart = parseDate(dateRange[i][1], 1, null);
									hourEnd = parseDate(dateRange[i][2], 1, null);
								}
								dayStart = parseDate(dateRange[i][1], 1, null);
								dayEnd = parseDate(dateRange[i][2], 1, null);
							}else if(dateRange[i][0] == 2){
								monthStart = parseDate(dateRange[i][1], 2, 1);
								monthEnd = parseDate(dateRange[i][2], 2, 2);
							}else{
								weekStart = dateRange[i][1];
								weekEnd = dateRange[i][2];
								weekDate = parseDate(weekEnd.substring(0,4) + "0101",1,null);
							}
						}
					}
				});
				/* Ajax取数据完毕  */
				var op = $("#timeSpan a.selected");
				calendar("date",$(op).attr("timetype"));
				
				$("#timeSpan a").bind('click', function(e){
					calendar("date",$(this).attr("timetype"));
				});
				
				var siden = false;
				function calendar(id,val){
					var iden = false;
					if(val==1){
						var def_date = new Date(dayEnd);
						def_date.addMonths(-1);
						var start_date = date_formart(def_date,'Ymd');
						var end_date = date_formart(dayEnd,'Ymd');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$("#"+id).val(start_date + "-" +end_date);
						$("#"+id).unbind("click");
						$("#"+id).unbind("change");
						 $("#"+id).DatePicker({
							format:'Ymd',
							date: new Date(),
							current: dayEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'days',
							onBeforeShow:function(){
								iden = false;
								siden = false;
							},
							onChange: function(formated){
								$("#"+id).val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							onHide:function(){
								if(iden==true && siden == false){
									hideToSearch();
								}
								
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
						$("#"+id).val(start_date + "-" +end_date);
						$("#"+id).unbind("click");
						$("#"+id).unbind("chang");
						$("#"+id).DatePicker({
							format:'Ym',
							date: new Date(),
							current: monthEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'moths',
							onBeforeShow:function(){
								iden = false;
								siden = false;
							},
							onChange: function(formated, dates){
								$("#"+id).val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							onHide:function(){
								if(iden==true && siden == false){
									hideToSearch();
								}
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
						$("#"+id).val(weekRange);
						
						$("#"+id).unbind("click");
						$("#"+id).unbind("chang");
						$("#"+id).WeekPicker({
							//flat: true,
							format:'m/d/Y',
							date: ['201201'],
							current: weekDate,
							starts: 1,
							position: 'bottom_right',
							mode: 'range',
							data: data,
							/* onBeforeShow:function(){
								iden = false;
								siden = false
							}, */
							onChange: function(formated){
								$("#"+id).val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							/* onHide:function(){
								if(iden==true && siden == false){
									hideToSearch();
								}
							}, */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() < weekStart)||(date.valueOf() > weekEnd),
									className: date.valueOf() == weekEnd ? 'datepickerSpecial' : false
								}
							}
						});
					}
				}
				
				function hideToSearch(){
					
				}
				
			}
		);
	</script>
	<!-- 主图   时间控件样式  开始  -->
	<style type="text/css" media="screen">
		.inp_cala{
		    background:url(../date/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px
		}
	</style>
	<!-- 主图  时间控件样式  结束  -->

