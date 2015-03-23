<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%> 
<%@include file="/manager/commons/meta_ztree.jsp"%>
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
					$("#signupform").attr("action","${baseManager}/index/modify.${actionExt}?type=edit");
	    		});
	    		$('#btn_back').bind('click', function() {
	    			window.location.href = '${baseManager}/index/list.${actionExt}';
	    		});
	    		
/*	    		jQuery.validator.addMethod("numberWord", function(value, element) {
				    var chrnum = /^\d+$/;//正整数+0
					return this.optional(element) || (chrnum.test(value));
				}, "请输入正整数或0");
*/				
				
				var validator = $("#signupform").validate({
					rules: {
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
	               	<h3>信息设置</h3>
	           	</li>
	    	</ul>
	        <ul class="navigationTabs">
	            <li><a href="#base">指标资料 <label style="color: red;"> *</label></a></li>
<!-- 	            <li><a href="#detail">详细信息</a></li> -->
	        </ul>
	        <form action="#" method="post" id="signupform" name="signupform">
	        	<input type="hidden" id="indexId" name="indexId" value="${empty requestScope.dIndex ? '': requestScope.dIndex.indexId}" />
	        	<input type="hidden" id="type" name="type" value="${empty requestScope.type ? '': requestScope.type}"/>
	        	<%-- <input type="hidden" id="role" name="role" value="${empty requestScope.user ? '': requestScope.user.role.id}"/> --%>
		        <div class="tabsContent">
		            <div class="tab">
		            	<p>
	           				<span class="info-label"><label> * </label>指标名称：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexName" name="indexName" value="${dIndex.indexName}"/><label id="indexName"> </label>
	              			</span>
	              		</p>
	              		<p>
	              			
	              			<span class="info-label"><label> * </label>指标类型：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexType" name="indexType" value="${dIndex.indexType}"/><label id="indexType"> </label>
	              			</span>
	              		</p>
	              		<p>
	              			
	              			<span class="info-label"><label> * </label>计算类型：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexCalType" name="indexCalType" value="${dIndex.indexCalType}"/><label id="indexCalType"> </label>
	              			</span>
	              	</p>
	              	<p>
	              			<span class="info-label">表达式：</span>
	              			<span class="info-content">
	              				<input type="text" id="indexExpr" name="indexExpr" value="${dIndex.indexExpr}"/><label id="indexExpr"> </label>
	              			</span>
	              	</p>
	              	<p>		
	              			<span class="info-label">描述：</span>
	              			<span class="info-content">
	              				<textarea name="indexDsc" rows="3" cols="40">${dIndex.indexDsc}</textarea>
	              			</span>
	              	</p>
	              			<span class="info-label"><label> * </label>生效时间：</span>
	              			<span class="info-content">
	              				 <input id="d5221" name="startTime"  type="text"   onFocus="var d5222=$dp.$('d5222');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" value="${effDate}"/>
	              					至
	              				  <input id="d5222" name="endTime"  type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5221\')}'})" value="${expDate}"/>
	              			</span>
	              				 
	           			</p>
		            </div>
		        </div>
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
	        </form>
    	</div>
    	
	</body>
</html>