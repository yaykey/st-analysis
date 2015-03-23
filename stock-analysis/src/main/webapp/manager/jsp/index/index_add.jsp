<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%-- <%@include file="/manager/commons/meta.jsp"%> --%>
<%@include file="/commons/jquerylibs.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<title>信息</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/tab.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/selfinfo/js/eye.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/selfinfo/js/tab.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/user/js/jquery.easyui.min.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/easyui.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/user/js/jquery.validate.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/validate.css"/>" rel="stylesheet" type="text/css"/>
		
		<script src="<c:url value="/manager/js/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script type="text/javascript" src="${base }/date/js/datepicker.js"></script>
		
		<style type="text/css">
			.tip {
				font-size: 12px;
				color: red;
				margin: 5px 20px;
				width:400px;
				display: inline-block;
				padding: 5px 10px 6px;
				text-decoration: none;
				border-radius: 8px;
				-moz-border-radius: 8px;
				-webkit-border-radius: 8px;
				-moz-box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				-webkit-box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				border: 1px solid rgba(0,0,0,0.25);
				position: relative;
			}
		</style>
		<script type="text/javascript">
    		var setting = {
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "all"
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			}
		    $(document).ready(function() {
		    	//表单提交
	    		$('#btn_submit').bind('click', function(){
					$("#signupform").attr("action","${baseManager}/index/add.${actionExt}");
	    		});
	    		$('#btn_back').bind('click', function() {
	    			window.location.href = '${baseManager}/index/list.${actionExt}';
	    		});
	    		
	    		
    			jQuery.validator.addMethod("numberWord", function(value, element) {
				    var chrnum = /^\d+$/;//正整数+0
					return this.optional(element) || (chrnum.test(value));
				}, "请输入正整数或0");
				
				
				var validator = $("#signupform").validate({
					rules: {
						indexId: {
							required: true,
							numberWord: true
						},
						indexName:{
							required: true,
						},
						indexType:{
							required: true,
						},
						indexCalType:{
							required: true,
						},
						startTime:{
							required: true,
						},
						endTime:{
							required: true,
						}
						
					},
					messages: {
						indexId: "请输入指标ID(整数)",
						indexName: "请输入指标名称",
						indexType: "请输入指标类型",
						indexCalType: "请输入指标计算类型",
						startTime: "请输入生效时间",
						endTime: "请输入失效时间"
					},
					 submitHandler:function(form){
				            form.submit();
				     } 
				});
				
				$('#btn_submit').click(function(){
					var iden = validator.form();
					if(iden == false){
						$("#xtip").css('display', 'block');
						$("#xtip").css('color', 'red');
						$("#xtip").html('请将基本资料填写正确');
					}else{
						$("#xtip").css('display', 'none');
					}
				});

	    	});
		</script>
	</head>
	<body>
		<div class="wrapper">
			<ul class="user-info-config">
	        	<li>
	               	<h3>添加指标</h3>
	           	</li>
	    	</ul>
	        <ul class="navigationTabs">
	            <li><a href="#base">指标资料 <label style="color: red;"> *</label></a></li>
	        </ul>
	        <form action="#" method="post" id="signupform" name="signupform">
		        <div class="tabsContent">
		            <div class="tab">
<!-- 		            <p >      页面不够用，不应用css样式-->
	           				<span class="info-label"><label> * </label>指标ID：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexId" name="indexId"/><label id="indexId"> </label>
	              			</span>
	              			<br>
	              			<span class="info-label"><label> * </label>指标名称：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexName" name="indexName"/><label id="indexName"> </label>
	              			</span>
	              			<br>
	              			<span class="info-label"><label> * </label>指标类型：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexType" name="indexType"/><label id="indexType"> </label>
	              			</span>
	              			<br>
	              			<span class="info-label"><label> * </label>计算类型：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexCalType" name="indexCalType"/><label id="indexCalType"> </label>
	              			</span>
	              			<br>
	              			<span class="info-label">表达式：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexExpr" name="indexExpr"/><label id="indexExpr"> </label>
	              			</span>
	              			<br>
	              			<span class="info-label">描述：</span>
	              			<span class="info-content">
	              				<textarea name="indexDsc" rows="2" cols="40"></textarea>
	              			</span>
	              			
	              			<br/>
	              			<span class="info-label"><label> * </label>生效时间：</span>
	              			<span class="info-content">
		              	    	<input type="text" id="d5221" name="startTime"    
		              	    			onFocus="var d5222=$dp.$('d5222');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
		              	    			<label id="effTime"> </label>
		              	    			至
		              	    			<input type="text" id="d5222" name="endTime"   
		              					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
		              					<label id="exfTime"> </label>
		              		</span>
		              		<br/>
		              		<!-- <span class="info-label"><label> * </label>失效时间：</span>
		              			<span class="info-content">
		              			<input type="text" id="d5222" name="endTime"   
		              					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
		              					<label id="exfTime"> </label>
	              			</span> -->
<!-- 	           		</p>  -->
	           		</div>
		        </div>
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
	        </form>
    	</div>
    	
    	<script type="text/javascript">
    	
    	$(document).ready(function(){    	
	    	
			
    		$('#date').DatePicker({
				format:'Ymd',
				date: new Date(),
				//current: hourEnd,
				starts: 1,
				position: 'bottom_right',
				calendars: 2,
				mode: 'single',
				view: 'days',
				onBeforeShow:function(){
					//iden = false;
					//siden = false;
				},
				onChange: function(formated){
					$('#date').val(formated.join('-'));
					//if(iden){
						//$('#queryButton').click();
						//setTimeout(function(){hideToSearch();},800); 
					//}
					//iden = true;
				},
				onHide:function(){
					//if(iden==true && siden == false){
					//	hideToSearch();
					//}
				},
				/* 此处为当前日期之后不可选 */
				onRender: function(date) {
					//return {
					//	disabled: (date.valueOf() > hourEnd.valueOf())||(date.valueOf() < hourStart.valueOf()),
					//	className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
					//}
					return {}
				}

			});
    	
    	});
    	
    	
    	</script>
    	
	</body>
</html>