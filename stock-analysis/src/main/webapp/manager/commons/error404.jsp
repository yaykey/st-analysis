<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/manager/commons/meta.jsp"%>
	</head>

	<body>
		<div class="panelBox">
			<form action="#" method="post" name="form1" class="formcss">
				<div class="content">
					<div class="content_title_bg">
						
					</div>
					<div align="center">
						^_^不好意思^_^，出错啦！
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
