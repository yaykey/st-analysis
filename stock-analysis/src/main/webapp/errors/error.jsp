<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	</head>

	<body>
		<div class="panelBox">
			<form action="#" method="post" name="form1" class="formcss">
				<div class="content">
					<div class="content_title_bg">
						
					</div>
					<div align="center">
						由于服务器繁忙，您访问的页面没有响应，请稍后重试!
						<br />
						<s:actionerror />
						<br />
						<s:property value="exception" />
						<br />
						<s:property value="exceptionStack" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
