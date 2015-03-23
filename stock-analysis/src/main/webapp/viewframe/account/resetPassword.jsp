<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>

<html>
	<head>
		<title>重置密码</title>
		
		<style type="text/css">
			body{
				padding:0;
				margin:0;
				font-size: 14px;
			}
		
			ol,ul{list-style:none;}
			.header{
				width:960px;
				height:110px;
				margin:0 auto;
				position:relative;
				z-index:999;
			}
			.wrap_header{
				float:left;
				text-align:right;
				line-height:24px;
				padding-top:10px;
				width:500px;
			}
			.main{
				width:960px;
				margin:0 auto;
			}
			.main_con1 {
				border:1px solid #C8C8C8;
				border-top:none;
				border-bottom:none;
				height:auto!important; 
				min-height:100px; 
			}
			/***表单***/ 
			.main_form{
				padding:10px 0
			}
			.main_form li{
				padding:6px 0; 
				clear:left;
				line-height:200%;
			}
			.main_form li:after{
				content:".";
				height:0;
				display:block;
				clear:both;
				visibility:hidden;
			}
			.fm_left{
				float:left;
				text-align:right;
				padding-right:10px;
				width:80px;
			}
			.fm_right{
				float:left;
			}
			.input_tx{
				width:220px;
				border: 1px solid #cdd7d9;
			 	padding: 5px;
			    border-radius: 8px;
			    -webkit-border-radius: 8px;
			    -moz-border-radius: 8px;
			}
			.main_bottom1{
				height:3px;font-size:1px;line-height:1px;background:url(${base}/manager/jsp/user/images/bg.png) no-repeat;
				background-position:0 -22px;height:31px;
			}
			.input_sub{
				line-height:27px;
				background: url("${base}/viewframe/images/sp_bg.png") no-repeat;
				background-position:-125px -76px;
				height:25px;
				width: 66px;
				cursor:pointer;
				border:none;
			}
			
		</style>
	</head>

	<body>
		<div class="header">
			<div class="wrap_header">
				<a href="${base }/login.jsp"><img src="${base }/viewframe/images/login.png" class="logo" alt="飞信综合数据应用平台" title="飞信综合数据应用平台" border="0" height="95" width="438"></a> 
			</div>
		</div>
		<div class="main">
			<div class="main_con1" style="border-top:1px solid #C8C8C8;">
				<div class="" style="padding:45px 0 65px 240px">
					<form id="modify_password_form" name="modify_password_form" action="${base}/account/modifyPassword.feinno" method="POST">
						 <ul class="main_form">
								<li class="mr">
									 <div class="fm_left">电子邮件：</div>
            						<div class="fm_right">${email }</div>       
								</li>
								<li>
						            <div class="fm_left">新密码：</div>
						            <div class="fm_right">
										<input id="newPassword" name="newPassword" type="password" class="input_tx" value=""/>
										<span class="cor_grey">不少于6个字符，数字、字母组合。区分大小写</span>
						            </div>
								</li>
								<li>
						            <div class="fm_left">确认密码：</div>
						            <div class="fm_right">			
										<input id="confirmPassword" name="confirmPassword" type="password" class="input_tx" value=""/>
									</div>       
								</li>
								 <li>
						            <div class="fm_left">&nbsp;</div>
						            <div class="fm_right">
										<span class="f_btn ">
											<input type="hidden" name="email" value="${email }">
											<input type="hidden" name="active" value="${active }">
											<input type="hidden" name="dt" id="dt" value="" />
											<input type="button" id="btn_submit" class="input_sub" value="">
										</span> 
									</div>
						          </li>
							</ul>
					</form>
				</div>
			</div>
			 <div class="clear"></div>
  			<div class="main_bottom1"></div>
		</div>
		
		<script type="text/javascript">
		$(document).ready(function() {
			
			$('#newPassword').val("");
			$('#confirmPassword').val("");
			
			function modify() {
			
				if ($.trim($("#newPassword").val()) == "") {
					alert("新密码不能为空");
					$("#newPassword").focus();
					return false;
				}
				if ($.trim($("#newPassword").val()).length < 6) {
					alert("不少于6个字符，数字、字母组合。区分大小写");
					$("#newPassword").focus();
					return false;
				}
				if ($.trim($("#newPassword").val()) != $.trim($("#confirmPassword").val())) {
					alert("两次密码输入不一致");			
					return false;
				}
				
				
				var dt = new Date();
				$('#modify_password_form')[0].dt.value = dt;
				$('#modify_password_form').submit();
				
			}
			
			$('#btn_submit').bind('click', modify);
		});
		</script>
				
	</body>
</html>
