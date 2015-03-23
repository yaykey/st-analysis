<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>修改密码</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/tab.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/user/js/jquery.validate.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/user/js/dtooltip.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/validate.css"/>" rel="stylesheet" type="text/css"/>
	    <style>
	    	.edit-password {
				width:95%;
				height: 400px;
				margin-left: 3%;
			}
			body {
	    		background-color: white;
	    		text-align: left;
	    	}
	    	.user-info-config p{
				line-height:36px;
				font-size:14px;
				zoom:1;
				overflow:hidden;
			}
			.user-info-config .info-label{
				float:left;
				width:93px;
				padding-right:16px;
				text-align:right;
			}
			.user-info-config .info-content{
				display:block;
				margin-left:89px;
			}
			.dtooltip {
			  	background: #FFF;
			  	border: 1px solid #A63100;
			  	border-radius: 5px 5px;
			  	padding: 8px;
			  	-moz-border-radius: 5px;
			  	-webkit-border-radius: 5px;
		 	 	box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
			  	-webkit-box-shadow: 5px 5px rgba(0, 0, 0, 0.1);
			  	-moz-box-shadow: 5px 5px rgba(0, 0, 0, 0.1);
			} 
		</style> 
		<script type="text/javascript">
			$(document).ready(function(){
				$('#newPassword').dTooltip({
					  angle: 180, 
					  content: '请输入6-20位字母、数字'
				});
				$('#confPassword').dTooltip({
					  angle: 180, 
					  content: '请输入6-20位字母、数字'
				});
				$('#btn_back').bind('click', function() {
					window.location.href = '${baseManager}/user/view.${actionExt}?uid=' + $('#userId').val();
	    		});
				//表单验证
				jQuery.validator.addMethod("numberWord", function(value, element) {
					var chrnum = /^([a-zA-Z0-9!@]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "请输入6-20位字母、数字"); 
				var validator = $("#signupform").validate({
					rules: {
						newPassword: {
							required: true,
							numberWord: true,
							minlength: 6,
							maxlength: 20
						},
						confPassword: {
							required: true,
							numberWord: true,
							minlength: 6,
							maxlength: 20,
							equalTo: "#newPassword"
						}
					},
					messages: {
						newPassword: {
							required:"请输入新密码",
							minlength: jQuery.format("请输入至少{0}位字母、数字"),
							maxlength: jQuery.format("请输入最多{0}位字母、数字")
						},
						confPassword: {
							required: "请输入确认密码",
							minlength: jQuery.format("请输入至少{0}位字母、数字"),
							maxlength: jQuery.format("请输入最多{0}位字母、数字"),
							equalTo: "输入与新密码不一致"
						}
					}
				});
			});
		</script>
	</head>
	<body>
		<form action="${baseManager}/user/modifypwd.${actionExt}" method="post" name="signupform" id="signupform">
			<input type="hidden" id="userId" name="userId" value="${empty requestScope.userId ? '': requestScope.userId}" />
			<div class="edit-password">
				<ul class="user-info-config">
		        	<li>
		               	<h3>修改密码</h3>
			           	<p>
		       				<span class="info-label">原密码：</span>
		          			<span class="info-content">
		          				<input type="password" readonly="readonly" id="password" name="password" value="${empty requestScope.pwd ? '': requestScope.pwd}" style="color: #CDCDCD;"/>
		          			</span>
		       			</p>
		       			<p>
		       				<span class="info-label">新密码：</span>
		          			<span class="info-content">
		          				<input type="password" id="newPassword" name="newPassword"/>
		          			</span>
		       			</p>
		       			<p>
		       				<span class="info-label">确认密码：</span>
		          			<span class="info-content">
		          				<input type="password" id="confPassword" name="confPassword"/> <label id="cpassword" > </label>
		          			</span>
		       			</p>
	       			</li>
		    	</ul>
			</div>
			<div align="center" class="info-button">
	        	<input type="submit" value="保存修改" id="btn_submit" />
	        	<input type="button" value="返回" id="btn_back"/>
	        </div>
		</form>
	</body>
</html>