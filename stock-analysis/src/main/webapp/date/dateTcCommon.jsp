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
							current:weekDate,
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
									hideToSearch();
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
				siden = true;
				var root = $("#r-main");
			      var dataType =root.find("#dataType");
				var province=root.find("#province");
				var operators=root.find("#operators");//运营商
				var dIndex=root.find("#dIndex");
				var time=root.find("#time");
					
			      var dataTypeId =dataType.find(":selected");
		        	var timeType = time.find(":selected")[0].value;
		            var provinceIds=province.find(":selected");
		            var operatorIds=operators.find(":selected");
		            var dIndexIds=dIndex.find(":selected");
		            var startTime=20120101;
		            var endTime=20120801;
		            var data =root.find("#data");
		            var month =root.find("#month");
		            var week =root.find("#week");//周数据
		            var hour =root.find("#hour");
		            
	
		            //指标为空 页面提示
		            if(dIndexIds.length==0){
		            	alert('您选择的维度匹配不到相同的指标，请重新选择！');
		            	return false;
		            }
		            
		            //运营商为空页面提示
		            if(operatorIds.length==0){
		            	alert('运营商为空，请重新操作！');
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
		         	
				//供应商
				var operator_Ids = "";
				for (var i=0; i<operatorIds.length; i++) {
					    operator_Ids += operatorIds[i].value;
					 if (i != operatorIds.length-1) {
					 	operator_Ids += ',';
					 }
				}
					
				//指标
				var dIndex_Ids = "";
				var dimTypeAndId='';
				var dimIds ='';
				var dimTypeIds ='';
			
				for (var i=0; i<dIndexIds.length; i++) {
					if(dIndexIds[i].selected==true)
					{
						var v=dIndexIds[i].value;
						var v1=v.split('_');
						dIndex_Ids+=v1[0]+',';
						dimIds+=v1[1]+',';
						dimTypeIds+=v1[2]+',';
						dimTypeAndId+=v1[1]+'_'+v1[2]+',';
					}
				}
				dIndex_Ids= dIndex_Ids.substring(0,dIndex_Ids.length-1);		
				dimTypeAndId= dimTypeAndId.substring(0,dimTypeAndId.length-1);
				dimIds= dimIds.substring(0,dimIds.length-1);		
				dimTypeIds= dimTypeIds.substring(0,dimTypeIds.length-1);
		         
	          
	                if(timeType==1)//日报
	                {
	                   var value=data[0].value.split("-");
	                   startTime=value[0];
	                   endTime=value[1];
	                }else if(timeType==2){
	                	 var value=month[0].value.split("-");
	                     startTime=value[0];
	                     endTime=value[1];
	                }else if(timeType==4){//周报
	                   var value=week[0].value.split("-");
	                   startTime=value[0];
	                   endTime=value[1];
	                }else if(timeType==11){//小时
	                    var value=hour[0].value.split("-");
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
						 url : base + "/report/amcharts/getTcChart.feinno?vrtlId=${vrtlId}&tableName=${tableName}&acur=${acur}&dataType=${dataType}&xmlType=${xmlType}&widgetId=${widgetId}&functionId=${functionId}&refreshFlag=1"+
					      "&province="+province_Ids+"&city=&carrId="+operator_Ids+"&dimTypeId="+dimTypeIds+"&dimId="+dimIds+"&indexId="+dIndex_Ids+
					      "&startTime="+startTime+"&endTime="+endTime+"&timeType="+timeType+"&dimTypeAndId="+dimTypeAndId+"&queryType="+dataTypeId[0].value, 
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

