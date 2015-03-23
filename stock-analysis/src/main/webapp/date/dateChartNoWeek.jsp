<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${base }/date/js/datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function(){
				
				/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
				var rptid = ${vrtlId};
				var weekflag= ${weekflag};
				var data;
				var dateRange,dayStart = new Date(),dayEnd = new Date(),
					monthStart = new Date(),monthEnd = new Date();
				$.ajax({
					type:"post",
					data:"year=" + new Date().getFullYear() + "&rptid=" +　rptid + "&weekflag=" + weekflag ,
					url:"${base}/common-week/getWeeks.${actionExt}",
					dataType: 'json',
					async: false,
					success:function(msg){
						data = msg;
						//虚拟报表个分类时间区间
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
						$('#data').css('display', 'inline');
						$('#month').css('display', 'none');
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
						$('#month').css('display', 'inline');
						$('#data').css('display', 'none');
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
					}
				}
				
				
		     function hideToSearch(){
				siden = true;
				var root = $("#r-main");
				var dIndex=root.find("#dIndex");
			    var time=root.find("#time");
			    
			    var timeType = time.find(":selected")[0].value;
	            var dIndexIds=dIndex.find(":selected");
	            var startTime=20120101;
	            var endTime=20120801;
	            var data =root.find("#data");
	            var month =root.find("#month");
		            
	            //指标为空 页面提示
	            if(dIndexIds.length==0){
	            	alert('指标为空，请重新选择！');
	            	return false;
	            }
		                   	
				//指标
				var dIndex_Ids = "";
				for (var i=0; i<dIndexIds.length; i++) {
					dIndex_Ids += dIndexIds[i].value;
				 	if (i != dIndexIds.length-1) {
				 		dIndex_Ids += ',';
				 	}
				}
	               
                if(timeType==1)//日报
                {
                   var value=data[0].value.split("-");
                   startTime=value[0];
                   endTime=value[1];
                }else if(timeType==2){
                	 var value=month[0].value.split("-");
                     startTime=value[0];
                     endTime=value[1];
                }
	              
                //日或小时的timeType都为1
                if(timeType==1||timeType==11)
                	timeType=1;
                $("#chart-${widgetId}").empty();
    			$("#chart-${widgetId}").append(loadContent);

    			jQuery.ajaxSetup({
    				cache : true
    			});
				jQuery.ajax({
					type : "POST",
					url : base + "/report/amcharts/examine/getChart.feinno?vrtlId=${vrtlId}&tableName=${tableName}"+
							"&acur=${acur}&dataType=${dataType}&xmlType=${xmlType}&widgetId=${widgetId}&functionId=${functionId}"+
							"&refreshFlag=1&reportType=out"+
							"&province=86&carrId=-1&indexId="+dIndex_Ids+
							"&startTime="+startTime+"&endTime="+endTime+"&timeType="+timeType+"&queryType=1&weekflag=1",
					dataType : 'text',
					data : {
					},
					success : function(response) { 
						$("#chart-${widgetId}").empty();
						$("#chart-${widgetId}").append(response);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						
					}
				});
			}
				
			});
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
		    background-color:white;
		    border-radius:0px 
		}
	</style>
	<!-- 主图  时间控件样式  结束  -->

