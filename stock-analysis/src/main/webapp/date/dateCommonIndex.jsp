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
				    var dataType =root.find("#dataType");
					var client=root.find("#client");
					var operators=root.find("#operators");//运营商
					var dIndex=root.find("#dIndex");
					var time=root.find("#time");
					var monitor=root.find("#monitor");
		        	var dataTypeId =dataType.find(":selected")[0].value;
		        	var timeType = 1;
		            var clientIds = client.find("option:selected");
		            var monitorIds= monitor.find("option:selected");
		           // var operatorIds=operators.find(":selected");
		            var dIndexIds=dIndex.find("option:selected");
		           // var cityIds=city.find("option:selected");
		            var startTime=20120101;
		            var endTime=20120801;
		            var data =root.find("#data");
		           // var month =root.find("#month");
	  
		            //维度
		            var client_Ids = "";
					var client_Types = "";
		            var dimId_dimTypeId="";
					var dimId_dimTypeIdA="";
		            for(var i=0;i<clientIds.length;i++){
		            	dimId_dimTypeId +=clientIds[i].value+",";
		            	var dimIds=clientIds[i].value.split("_");
						client_Ids += dimIds[0]+",";
						client_Types = client_Types.replace(dimIds[1]+",","")+dimIds[1]+",";
		            }
		
		            //截取字符串
		            client_Types= client_Types.substring(0,client_Types.length-1);
					client_Ids= client_Ids.substring(0,client_Ids.length-1);
			        dimId_dimTypeId= dimId_dimTypeId.substring(0,dimId_dimTypeId.length-1);
	                
			        var selected = 0;
					var strDimType='';
					var strIndex ='';
					var strDim ='';
					var dIndex_id=$("#dIndex")[0];
					for(var i=0;i<dIndex_id.options.length;i++)
					{ 
						if(dIndex_id[i].selected==true)
						{
							selected++;
							var dIndexValue = dIndex_id.options[i].value;
							var arr = dIndexValue.split('_'); 
							var indexId = arr[0];
							var dimId = arr[1];
							var dimType = arr[2];
							strIndex = strIndex + indexId +',';
							//var indexId=dIndex_id.options[i].value;
							strDimType=strDimType+dimId+'_'+dimType+',';
							//strDim= strDim++',';
						}
					}
					strIndex= strIndex.substring(0,strIndex.length-1);		
					strDimType= strDimType.substring(0,strDimType.length-1);
					var dimStr='';
			        if(dataTypeId==3){//说明二级维度多选
			            var bl=strDimType.split(",");
			            for(var k=0;k<bl.length;k++){
			            	 dimStr+=bl[k]+"_"+dimId_dimTypeId+",";
			            }
			         }if(dataTypeId==1){//说明一级维度多选
			        	 var bl=dimId_dimTypeId.split(",");
				            for(var k=0;k<bl.length;k++){
				            	 dimStr+=strDimType+"_"+bl[k]+",";
				            }
			         }if(dataTypeId==2){//说明一级维度多选
			        	 var bl=dimId_dimTypeId.split(",");
				            for(var k=0;k<bl.length;k++){
				            	 dimStr+=strDimType+"_"+bl[k]+",";
				            }
			         }
			         
			         dimStr= dimStr.substring(0,dimStr.length-1); 
						
			       //监控类型
						var monitor_Ids = "";
						for (var i=0; i<monitorIds.length; i++) {
								monitor_Ids += monitorIds[i].value;
							 if (i != monitorIds.length-1) {
								 monitor_Ids += ',';
							 }
						}
		           //维度为空 页面提示
			        if(client_Ids.length==0){
			            	alert('活跃类型为空，请重新操作！');
			            	return false;
			        }
		            
		            //指标为空 页面提示
		            if(dIndexIds.length==0){
		            	alert('指标为空，请重新选择！');
		            	return false;
		            }
		            
		            //监控类型为空 页面提示
		            if(monitor_Ids.length==0){
		            	alert('监控类型为空，请重新选择！');
		            	return false;
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
	                
	                jQuery.ajaxSetup({ cache: true });
					jQuery.ajax({
						type : "POST",
						url : base + "/report/monitor/amcharts/getMonitorIndexChart.feinno?vrtlId=${vrtlId}&tableName=${tableName}&acur=${acur}&dataType=${dataType}&xmlType=${xmlType}&widgetId=${widgetId}&functionId=${functionId}&refreshFlag=1"+
									"&dstcId=86"+"&carrId=-1"+"&dimTypeId="+client_Types+"&dimId="+client_Ids+"&indexId="+strIndex+
									"&startTime="+startTime+"&endTime="+endTime+"&timeType="+timeType+"&dimTypeAndId="+dimStr+"&queryType="+dataTypeId+"&moniType="+monitor_Ids,
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

