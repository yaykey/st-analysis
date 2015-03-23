<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>


<html>
<head>
	<link href="${base }/manager/jsp/selfinfo/css/layout.css" rel="stylesheet" type="text/css" />
	<%-- <link href="<c:url value="${base }/manager/jsp/user/css/validate.css"/>" rel="stylesheet" type="text/css"/> --%>
	<%-- <script src="<c:url value="${base }/js/jquery/jquery-1.8.0.js"/>" type="text/javascript"></script> --%>
	<%-- <script src="<c:url value="${base }/manager/jsp/user/js/jquery.validate.js"/>" type="text/javascript"></script> --%>
	
	<style type="text/css">
		body{
			text-align: left !important;
		}
		li{
			list-style: none;
		}
		.user-info-list p{
			line-height: 30px;
			font-size:14px;
			overflow:hidden;
			padding: 3px;
		}
		.user-info-list .info-content{
			text-align: left;
		}
		.user-info-config p{
			line-height:36px;
			font-size:14px;
			overflow:hidden;
			padding: 3px;
		}
		.user-info-config .info-label{
			width:70px;
			text-align:right;
			padding-right:10px;
			padding-left: 5px;
		}
		.user-info-config .info-content{
			text-align: left;
		}
		.title2 span {
			float: left;
		}
		.error{
			padding: 0;
			font-size: 11px;
			margin-bottom: 0px;
		}
		input.error{
			width: 110px;
		}
		label.error {
			border: none;
		}
		#btn_submit {
			background-image: url("../viewframe/account/config.jpg");
			width: 101px;
			height:27px;
			border: none;
			margin-bottom: 3px;
		}
		.toolbar .layer {
			background:#FCFCFC;
			position: absolute;
			border: 1px solid #DCDCDC;
			margin: 10px;
			text-align: left;
		}
		.toolbar .layer .arrow {
			display: block;
			width: 15px;
			height: 8px;
			background: url(../viewframe/images/sp_icon.png) no-repeat 0 -223px;
			position: absolute;
			top: -8px;
			left: 34px;
		}
		.toolbar .text{
			margin-top: 15px;
		}
		.toolbar .layer h3 {
			background: #F6F6F6;
			border-bottom: 1px solid #EAEAEA;
			height: 26px;
			line-height: 26px;
			padding-left: 18px;
			font-weight: normal;
			color: #6d6d6d;
		}
		.btn {
			border: none;
			background: url(../viewframe/images/sp_bg.png) no-repeat -63px -76px;
			display: inline-block;
			width: 62px;
			height: 25px;
			line-height: 25px;
			text-align: center;
			color: #013659;
			_margin-top: 3px;
			margin-left: 455px;
		}
		.b_on {
			background-position: 0 -76px;
			color: #FFF;
		}
		.pas{
			border: 1px solid #CDCDCD;
		  	border-radius: 5px 5px;
		  	padding: 4px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(
			function(){
				//表单验证
				jQuery.validator.addMethod("numberWord", function(value, element) {
					var chrnum = /^([a-zA-Z0-9]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "请输入6-20位字母  、数字"); 
				var validator = $("#signupform").validate({
					rules: {
						password: {
							required: true,
							remote: {
								type : "POST",
		    					url : '${base}/manager/selfinfo/verifyOrUpdatePwd.${actionExt}?type=verify',
		    					data : {
		    						"nocache" : new Date().getTime(),
		    						password: function() {
		    							return $("#password").val();
		    						},
		    						userId: function() {
		    							return $("#userId").val();
		    						}
		    					}
							}
						},
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
						password: {
							required:"请输入原密码",
							remote:"原密码输入错误"
						},
						newPassword: {
							required:"请输入新密码",
							minlength: jQuery.format("请输入6-20位字母、数字"),
							maxlength: jQuery.format("请输入6-20位字母、数字")
						},
						confPassword: {
							required: "请输入确认密码",
							minlength: jQuery.format("请输入6-20位字母、数字"),
							maxlength: jQuery.format("请输入6-20位字母、数字"),
							equalTo: "输入与新密码不一致"
						}
					},
					//提交修改密码
					submitHandler : function() {
						 $.ajax({
							type:"post",
							data:"userId=" +$('#userId').val() + "&password=" +　$('#newPassword').val(),
							url:"${base}/manager/selfinfo/verifyOrUpdatePwd.${actionExt}?type=update",
							dataType: 'json',
							success:function(msg){
								 $("#explain_id").toggle('normal', function(){
								    	$('#password').val('');
								    	$('#newPassword').val('');
								    	$('#confPassword').val('');
								    	validator.resetForm();
								    }
								 );
							}
						}); 
					}
				});
			
				//显示修改密码div
			   var iden = true;
			  $("#showData").bind('click', function(){
				  var pos = $(this).offset();
				  var left = pos.left;
				  $(".layer").css({left: left-20 + 'px'});
				 
				    $("#explain_id").slideToggle('normal', function(){
				    	if(iden){
				    		$('#showData').addClass('b_on');
				    		iden = false;
				    	}else{
				    		$('#showData').removeClass('b_on');
				    		iden = true;
				    	}
				    	$('#password').val('');
				    	$('#newPassword').val('');
				    	$('#confPassword').val('');
				    	validator.resetForm();
				    });
			   });
			}		
		
		);
	</script>
</head>

<body>
	<div class="data_box" style="height: 600px;">
		<div class="title2 bno">
			<h2>个人设置</h2>
		</div>
		<div id="vf_chart_toolbar" class="toolbar clearfix">
			<p style="text-align: left">个人资料<input type="button" value="修改密码" id="showData" class="btn"/></p>
			<div id="explain_id" class="layer bgiframe" style="display: none;width: 420px;height:210px;">
				<i class="arrow"></i>
				<h3> 修改密码 </h3>
				<form name="signupform" id="signupform">
					<input type="hidden" id="userId" name="userId" value="${userSessionInfo.userInfo.id}" />
					<div class="text" >
						<ul class="user-info-config">
				        	<li>
					           	<p>
				       				<span class="info-label">原密码：</span>
				          			<span class="info-content">
				          				<input type="password" id="password" name="password" class="pas" /><label class="password"></label>
				          			</span>
				       			</p>
				       			<p>
				       				<span class="info-label">新密码：</span>
				          			<span class="info-content">
				          				<input type="password" id="newPassword" name="newPassword" class="pas"/> <label class="newPassword"></label>
				          			</span>
				       			</p>
				       			<p>
				       				<span class="info-label">确认密码：</span>
				          			<span class="info-content">
				          				<input type="password" id="confPassword" name="confPassword" class="pas"/> <label class="confPassword"> </label>
				          			</span>
				       			</p>
			       			</li>
				    	</ul>
					</div>
					<div align="center" class="info-button">
			        	<input type="submit" value="" id="btn_submit" />
			        </div>
				</form>
			</div>
		</div>
		<div class="chart_cont">
			<ul class="user-info-list">
				<li>
					<%-- <p>
						<span class="info-label">登录ID：</span> <span class="info-content">${userSessionInfo.userInfo.loginId}</span>
					</p> --%>
					<p>
						<span class="info-label">用户姓名：</span> <span class="info-content">${userSessionInfo.userInfo.username}</span>
					</p>
					<p>
						<span class="info-label">手机号码：</span> <span class="info-content">${userSessionInfo.userInfo.mobile}</span>
					</p>
					<p>
						<span class="info-label">电子邮件：</span> <span class="info-content">${userSessionInfo.userInfo.email}</span>
					</p>
					<%-- <p>
						<span class="info-label">所属部门：</span> <span class="info-content">${userSessionInfo.userInfo.department.name}</span>
					</p> --%>
					<p>
						<span class="info-label">角色：</span> <span class="info-content">
							 <c:if test="${userSessionInfo.userInfo.roles != null }">
								<c:forEach items="${userSessionInfo.userInfo.roles }" var="role">
									${role.name }
								</c:forEach>
							</c:if> 
						</span>
					</p></li>
			</ul>
		</div>
	</div>
</body>
</html>
