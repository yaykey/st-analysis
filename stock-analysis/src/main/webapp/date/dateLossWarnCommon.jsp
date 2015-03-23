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
				var op = $("#time option:selected");
				calendar(op.val());
				
				$('#time').change(function(){
					var val = $(this).val();
					calendar(val);
				});
				
				var siden = false;
				function calendar(val){
					var iden = false;
					if(val==1){
						var def_date = new Date(dayEnd);
						def_date.addMonths(-1);
						var start_date = date_formart(def_date,'Ymd');
						var end_date = date_formart(dayEnd,'Ymd');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#hour').css('display', 'none');
						$('#data').css('display', 'inline');
						$('#month').css('display', 'none');
						$('#week').css('display', 'none');
						$('#data').val(start_date + "-" +end_date);
						
						 $('#data').DatePicker({
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
								$('#data').val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							onHide:function(){
								if(iden==true && siden == false){
									//hideToSearch();
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
						def_date.addMonths(-5);
						var start_date = date_formart(def_date,'Ym');
						var end_date = date_formart(monthEnd,'Ym');
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#hour').css('display', 'none');
						$('#month').css('display', 'inline');
						$('#data').css('display', 'none');
						$('#week').css('display', 'none');
						$('#month').val(start_date + "-" +end_date);
						
						$('#month').DatePicker({
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
								$('#month').val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							onHide:function(){
								if(iden==true && siden == false){
									//hideToSearch();
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
						$('#hour').css('display', 'none');
						$('#data').css('display', 'none');
						$('#month').css('display', 'none');
						$('#week').css('display', 'inline');
						$('#week').val(weekRange);
						
						$('#week').WeekPicker({
							//flat: true,
							format:'m/d/Y',
							date: ['201201'],
							current: weekDate,
							starts: 1,
							position: 'bottom_right',
							mode: 'range',
							data: data,
							onBeforeShow:function(){
								iden = false;
								siden = false
							},
							onChange: function(formated){
								$('#week').val(formated.join('-'));
								if(iden){
									//$('#queryButton').click();
									setTimeout(function(){hideToSearch();},800); 
								}
								iden = true;
							},
							onHide:function(){
								if(iden==true && siden == false){
									//hideToSearch();
								}
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
							mode: 'range',
							view: 'days',
							onBeforeShow:function(){
								iden = false;
								siden = false;
							},
							onChange: function(formated){
								$('#hour').val(formated.join('-'));
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
									disabled: (date.valueOf() > hourEnd.valueOf())||(date.valueOf() < hourStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						});
					}
				}
				
				function hideToSearch(){
			        /* var rootda = $("#report-main");
			        var dataTypeda=rootda.find("#dataType");
				    var usertypeda=rootda.find("#usertypechart");
				    var losslevelda=rootda.find("#losslevelchart");
				    var clientda=rootda.find("#clientchart");
				    var provinceda=rootda.find("#provincechart");
					var dIndexda=rootda.find("#dIndexchart");
					var timeda=rootda.find("#time");
					
					var dataTypeId =dataTypeda.find(":selected");
		        	var timeType = timeda.find(":selected")[0].value;
		            var usertypeIds = usertypeda.multiselect('getChecked');
		            var losslevelIds=losslevelda.multiselect('getChecked');
		            var clientIds=clientda.multiselect('getChecked');
		            var provinceIds=provinceda.multiselect('getChecked');
		            var dIndexIds=dIndexda.multiselect('getChecked');
		            var month =rootda.find("#month");
		            

		         	// 用户类型
		            var usertype_Ids = "";
					for (var i=0; i<usertypeIds.length; i++) {
						usertype_Ids += usertypeIds[i].value;
						 if (i != usertypeIds.length-1) {
							 usertype_Ids += ',';
						 }
					}
					// 流失概率
					var lossleve_Ids = "";
					for (var i=0; i<losslevelIds.length; i++) {
						lossleve_Ids += losslevelIds[i].value;
						 if (i != losslevelIds.length-1) {
							 lossleve_Ids += ',';
						 }
					}
					
					// 客户端
		            var client_Ids = "";
					for (var i=0; i<clientIds.length; i++) {
						client_Ids += clientIds[i].value;
						 if (i != clientIds.length-1) {
							 client_Ids += ',';
						 }
					}
		            
		          	// 省份
		            var province_Ids = "";
					for (var i=0; i<provinceIds.length; i++) {
						 province_Ids += provinceIds[i].value;
						 if (i != provinceIds.length-1) {
						 	province_Ids += ',';
						 }
					}
					
		          	// 指标
		            var index_Ids = "";
					for (var i=0; i<dIndexIds.length; i++) {
						index_Ids += dIndexIds[i].value;
						 if (i != dIndexIds.length-1) {
							 index_Ids += ',';
						 }
					}
		            
					
					// check
		           if(usertypeIds.length==0){
		           		alert("请选择客户类型");
		           		return ;
		           }
			    	
		           if(clientIds.length==0){
		              	alert("请选择客户端");
		              	return ;
		              }
		   	    	
		   	    	if(provinceIds.length==0){
		   	    		alert("请选择省份");
		   	    		return ;
		   	    	}
					
	               	var value=month[0].value.split("-");
	                var startTime=value[0];
	                var endTime=value[1];

	                $("#chart-${widgetId}").empty();
	                $("#chart-${widgetId}").append(loadContent);
	                
	                jQuery.ajaxSetup({ cache: true });
					jQuery.ajax({
						type : "POST",
						url : base + "/report/amcharts/getChartLossWarn.feinno?vrtlId=${vrtlId}&tableName=${tableName}&acur=${acur}&dataType=${dataType}&xmlType=${xmlType}&widgetId=${widgetId}&functionId=${functionId}&refreshFlag=1"+
									"&dstcId="+province_Ids+"&carrId=-1&odimTypeId=201&odimId="+client_Ids+"&tdimTypeId=619&tdimId="+usertype_Ids+"&indexId="+index_Ids+"&markFlag="+lossleve_Ids+
									"&startTime="+startTime+"&endTime="+endTime+"&timeType="+timeType+"&queryType="+dataTypeId[0].value,
						dataType : 'text',
						data : {
							//,"nocache" : new Date().getTime()
						},
						success : function(response) {
							$("#chart-${widgetId}").empty();
							$("#chart-${widgetId}").append(response);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							
						}
					}); */
					
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
		    height: 14px;
		    background-color:white;
		    border-radius:0px
		}
	</style>
	<!-- 主图  时间控件样式  结束  -->

