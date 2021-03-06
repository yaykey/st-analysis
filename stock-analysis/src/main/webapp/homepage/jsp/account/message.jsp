<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

		<%@include file="/homepage/jsp/frame/meta.jsp"%>
		<title>错误</title>
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
			.main_bottom1{
				height:3px;font-size:1px;line-height:1px;background:url(${base}/manager/jsp/user/images/bg.png) no-repeat;
				background-position:0 -22px;height:31px;
			}
		</style>
	</head>

	<body>
		<!-- header -->
		<%@include file="/homepage/jsp/frame/header.jsp"%>
	
		<div class="main">
			<div class="main_con1" style="border-top:1px solid #C8C8C8;">
				<div class="" style="padding:45px 0 65px 240px">
					<form id="modify_password_form" name="modify_password_form" action="${base}/account/modifyPassword.feinno" method="post">
						 <ul class="main_form">
								<li class="mr">
									<c:if test="${title != null}">${title}</c:if>
									<c:if test="${title == null}">错误提示:</c:if>    
								</li>
								<li>
						          ${tip}
								</li>
							</ul>
					</form>
				</div>
			</div>
			 <div class="clear"></div>
  			<div class="main_bottom1"></div>
		</div>
		
		<!-- footer -->
		<%@include file="/homepage/jsp/frame/footer.jsp"%>
	</body>
</html>
