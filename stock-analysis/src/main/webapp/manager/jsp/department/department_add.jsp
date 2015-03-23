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
					$("#signupform").attr("action","${baseManager}/department/add.${actionExt}");
	    		});
	    		$('#btn_back').bind('click', function() {
	    			window.location.href = '${baseManager}/department/list.${actionExt}';
	    		});
	    		

	
				jQuery.validator.addMethod("numberWord", function(value, element) {
// 					var chrnum = /^([a-zA-Z0-9]+)$/;
				    var chrnum = /^\d+$/;//正整数+0
					return this.optional(element) || (chrnum.test(value));
				}, "请输入正整数或0---------------------这里的提示不显示，肿么回事");
				
				
				var validator = $("#signupform").validate({
					rules: {
						departmentName: {
							required: true
						},
						sort:{
							required: true,
							numberWord: true
						}
						
					},
					messages: {

						departmentName: "请输入部门名称",
						parent_Id:"请选择上级部门",
						sort:"请输入显示顺序,显示顺序只能是正整数或0"
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
	               	<h3>添加部门</h3>
	           	</li>
	    	</ul>
	        <ul class="navigationTabs">
	            <li><a href="#base">部门资料 <label style="color: red;"> *</label></a></li>
	        </ul>
	        <form action="#" method="post" id="signupform" name="signupform">
		        <div class="tabsContent">
		            <div class="tab">
		            	<p>
	           				<span class="info-label"><label> * </label>部门名称：</span>
	              			<span class="info-content">
	              				<input type="text" id="departmentName" name="departmentName"/><label id="departmentName"> </label>
	              			</span>
	           			</p>
		            	<p>
	           				<span class="info-label">是否显示：</span>
	              			<span class="info-content">
	            				<select id="show_Enable" name="show_Enable">
	            					<option value="">-- 请选择 --</option>
	            					<option value="0">否</option>
	            					<option value="1" selected="selected">是</option>
								</select>
								<label> </label>
	              			</span>
	           			</p>
	           			<p>
	           				<span class="info-label"><label> * </label>显示排序：</span>
	              			<span class="info-content">
	              				<input type="text" id="sort" name="sort"/><label id="sort"> </label>
	              			</span>
	           			</p>
		            	<p>
	           				<span class="info-label">上级部门：</span>
	              			<span class="info-content">
	            				<select id="parent_Id" name="parent_Id">
	            					<option value="0">无上级部门</option>
	            					<c:forEach items="${requestScope.departmentList}" var="department">
	            						<option value="${department.id}">${department.name }</option>
	            					</c:forEach>
								</select>
								<label> </label>
	              			</span>
	           			</p>
		        </div>
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
		        </div>
	        </form>
    	</div>
    	
	</body>
</html>