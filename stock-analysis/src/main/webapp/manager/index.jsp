<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>${appTitle }</title>
		<%@include file="/manager/commons/meta.jsp"%>
	</head>
	<frameset rows="50,*" cols="*" frameborder="NO" border="0" framespacing="0">
		<frame src="${baseManager }/frame/top.jsp" name="topFrame" scrolling="NO" noresize>
		<frameset id="main_frm" name="main_frm" cols="155,10,*" framespacing="0"
			frameborder="NO" border="0">
			<frame src="${baseManager }/nav/nav.${actionExt}" name="leftFrame" scrolling="auto" noresize>
			<frame src="${baseManager }/frame/switch.jsp" name="switchFrame" scrolling="NO" noresize>
			<frame src="${baseManager }/selfinfo/list.${actionExt}" name="main">
		</frameset>
	</frameset>
	<noframes>
		<body>
	
		</body>
	</noframes>
</html>