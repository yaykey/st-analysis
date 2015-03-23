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
				var province=root.find("#province");
				var client=root.find("#client");
				var operators=root.find("#operators");//运营商
				var dIndex=root.find("#dIndex");
				var time=root.find("#time");
				var city=root.find("#city");
				var extendDimType=root.find("#extendDimType");
				var extendDim=root.find("#extendDim");
				
				var dataTypeId =dataType.find(":selected")[0].value;
		        	var timeType = time.find(":selected")[0].value;
		            var clientIds = client.find("option:selected");
		            var provinceIds=province.find(":selected");
		            var extendDimTypeIds=extendDimType.find(":selected");
		            var extendDimIds=extendDim.find(":selected");
		            var dIndexIds=dIndex.find(":selected");
		            var cityIds=city.find("option:selected");
		            var startTime=20120101;
		            var endTime=20120801;
		            var data =root.find("#data");
		            var month =root.find("#month");
		            
	   
		            //维度
		            var client_Ids = "";
					var client_Types = "";
		            var dimId_dimTypeId="";
	   
		            for(var i=0;i<clientIds.length;i++){
					    dimId_dimTypeId +=clientIds[i].value+",";
		            	var dimIds=clientIds[i].value.split("_");
						client_Ids +=clientIds[i].value+"," 
		            }

		            //截取字符串
		            client_Types= client_Types.substring(0,client_Types.length-1);
				client_Ids= client_Ids.substring(0,client_Ids.length-1);
			      dimId_dimTypeId= dimId_dimTypeId.substring(0,dimId_dimTypeId.length-1);
	                
			        var tDimId="";
			        var tDimTypeId="";
			        var tDimId_tDimTypeId="";
			        if(extendDimTypeIds.length>0&&(extendDimIds.length>0&&extendDim.attr("disabled")!="disabled"&&extendDim[0].value!='')){//取extendDim
			        	for(var i=0;i<extendDimIds.length;i++){
			        		    tDimId_tDimTypeId +=extendDimIds[i].value+",";
				            }
			        }else if(extendDimTypeIds.length>0&&(extendDim.attr("disabled")=="disabled"||extendDim[0].value=='')){//取extendDimType
			        	 for(var i=0;i<extendDimTypeIds.length;i++){
			        		    tDimId_tDimTypeId +=extendDimTypeIds[i].value+",";
				         }
			        }

			        tDimId_tDimTypeId= tDimId_tDimTypeId.substring(0,tDimId_tDimTypeId.length-1);
			        
			        var dimStr='';
			        if(dataTypeId==6||dataTypeId==7){//说明二级维度多选
			            var bl=tDimId_tDimTypeId.split(",");
			            for(var k=0;k<bl.length;k++){
			            	 dimStr+=bl[k]+"_"+dimId_dimTypeId+",";
			            }
			           
			         }if(dataTypeId==1||dataTypeId==2||dataTypeId==3||dataTypeId==5){//说明一级维度多选
			        	 var bl=dimId_dimTypeId.split(",");
				            for(var k=0;k<bl.length;k++){
				            	 dimStr+=tDimId_tDimTypeId+"_"+bl[k]+",";
				            }
			         }
			         dimStr= dimStr.substring(0,dimStr.length-1); 


		           //维度为空 页面提示
			        if(client_Ids.length==0){
			            	alert('维度为空，请重新操作！');
			            	return false;
			        }
		            
		            //指标为空 页面提示
		            if(dIndexIds.length==0){
		            	alert('指标为空，请重新选择！');
		            	return false;
		            }
		            
		            var value=$("#dIndexValue").val();
	        	      if(value.indexOf(",")!=-1){
	     	    	         alert('您选择的指标为不同数据类型，请重新选择!');
	     	    	         return false;
	        	      }

					//省份
		            var province_Ids = "";
		            if(province.attr("disabled")!="disabled"){
						for (var i=0; i<provinceIds.length; i++) {
							    province_Ids += provinceIds[i].value;
							 if (i != provinceIds.length-1) {
							 	province_Ids += ',';
							 }
						}
		            }

		         	
					//地市
		            var city_Ids = "";
					if(city.attr("disabled")!="disabled"){
						for (var i=0; i<cityIds.length; i++) {
							 if(cityIds[i].value!=''){
							    city_Ids += cityIds[i].value;
							 if (i != cityIds.length-1) {
							 	city_Ids += ',';
							 }
							 }
						  }
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
						url : base + "/report/amcharts/getNearChart.feinno?vrtlId=${vrtlId}&tableName=${tableName}&acur=${acur}&dataType=${dataType}&xmlType=${xmlType}&widgetId=${widgetId}&functionId=${functionId}&refreshFlag=1"+
						"&province="+province_Ids+"&city="+city_Ids+"&dimId="+client_Ids+"&indexId="+dIndex_Ids+
						"&startTime="+startTime+"&endTime="+endTime+"&timeType="+timeType+"&dimTypeAndId="+dimStr+"&queryType="+dataTypeId,
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

