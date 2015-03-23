<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${base }/date/js/datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function(){
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var dataType = ${dataType};
				var data;
				var dateRange,dayStart = new Date(),weekStart = '201301',weekEnd = '201301',weekDate = new Date(),
				monthStart = new Date(),monthEnd = new Date(),weekRange = "201301",hourStart = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" + rptid ,
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
				var op = $("#time option:selected");
				calendar(op.val());
				
				$('#time').change(function(){
					var val = $(this).val();
					calendar(val);
				});
				var siden = false;
				function calendar(val){
					if(val==1){
						var def_date = new Date(dayEnd);
						//def_date.addMonths(-1);
						var start_date = date_formart(def_date,'Ymd');
						var end_date = date_formart(def_date,'Ymd');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#hour').css('display', 'none');
						$('#data').css('display', 'inline');
						$('#month').css('display', 'none');
						$('#week').css('display', 'none');
						$('#data').val(end_date);
						
						 $('#data').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: dayEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 1,
							mode: 'single',
							view: 'days',
							onBeforeShow:function(){
								siden = false;
							},
							onChange: function(formated){
								$('#data').val(formated);
								$(this).hide();
								changeSearch();
							},
							onHide:function(){
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
						$('#hour').css('display', 'none');
						$('#month').css('display', 'inline');
						$('#data').css('display', 'none');
						$('#week').css('display', 'none');
						$('#month').val(start_date);
						
						$('#month').DatePicker({
							format:'Ym',
							date: new Date(),
							current: monthStart,
							starts: 1,
							position: 'bottom_right',
							calendars: 1,
							mode: 'single',
							view: 'moths',
							onBeforeShow:function(){
								siden = false;
							},
							onChange: function(formated, dates){
								$('#month').val(formated);
								$(this).hide();
								changeSearch();
							},
							onHide:function(){
								//hideToSearch();
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
						$('#hour').css('display', 'none');
						$('#data').css('display', 'none');
						$('#month').css('display', 'none');
						$('#week').css('display', 'inline');
						$('#week').val(weekRange);
						
						$('#week').WeekPicker({
							format:'m/d/Y',
							date: ['201201'],
							current: weekDate,
							starts: 1,
							position: 'bottom_right',
							mode: 'single',
							data: data,
							onBeforeShow:function(){
								siden = false
							},
							onChange: function(formated){
								$('#week').val(formated);
								$(this).hide();
								changeSearch();
							},
							onHide:function(){
								//hideToSearch();
							},
							onRender: function(date) {
								return {
									disabled: (date.valueOf() < weekStart)||(date.valueOf() > weekEnd),
									className: date.valueOf() == weekEnd ? 'datepickerSpecial' : false
								}
							}
						});
					}else if(val==11){
						var end_date = date_formart(hourEnd,'Ymd');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#hour').css('display', 'inline');
						$('#data').css('display', 'none');
						$('#month').css('display', 'none');
						$('#week').css('display', 'none');
						$('#hour').val(end_date + "-" +end_date);
						
						 $('#hour').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: hourEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'single',
							view: 'days',
							onBeforeShow:function(){
								siden = false;
							},
							onChange: function(formated){
								$('#hour').val(formated.join('-'));
								$(this).hide();
								changeSearch();
							},
							onHide:function(){
								//hideToSearch();
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
				}
				function changeSearch() {
					var vrtlId = ${vrtlId};
					var dstcId = "";
					var value = $("#data").val();
					var tdimId = $("#operatorschart").val();
					//startTime = value[0];
					//endTime = value[0];
					jQuery.ajaxSetup({
						cache : true
					});
					jQuery
							.ajax({
								type : "POST",
								url : base
										+ "/report/newnear/chartAnalysis.feinno?vrtlId=${vrtlId}&tableName=${tableName}&designName=${designName}"
										//+ designName
										+ "&widgetId=${widgetId}&dimTypeId=${dimTypeId}&dimId=${dimId}&indexId=${indexId}&mergeFlag=${mergeFlag}&birtType=1&freshFlag=1"
										+ "&dstcId=86" + "&carrId=-1" + "&timeType=1"
										+ "&tdimTypeId=${photoType}" + "&tdimId=" + tdimId
										+ "&startTime=" + value + "&endTime="
										+ value,
								dataType : 'text',
								data : {
								//,"nocache" : new Date().getTime()
								},
								success : function(response) {
									$("#birt-${widgetId}").empty();
									$("#birt-${widgetId}").append(response);
								},
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {

								}
							});
				}

				
			}
		);
	</script>
	<!-- birt 时间控件样式/下拉框  开始  -->
	<style type="text/css" media="screen">
		.inp_cala{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px 
		}
	</style>
	<!-- birt  时间控件样式/下拉框  结束  -->
