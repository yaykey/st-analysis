<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>综合智能应用处理平台</title>
		<%@include file="/manager/commons/meta.jsp"%>
	</head>
	
	<frameset >
		<frameset id="widgetMain_frm" name="widgetMain_frm" cols="125,10,500" framespacing="0"
			frameborder="NO" border="0">
			<frame src="${baseManager }/jsp/widget/ztree_widget.jsp" name="menuTreeFrame" scrolling="NO" >
			<frame src="${baseManager }/jsp/widget/switch.jsp" name="switchFrame" scrolling="NO" >
			<frame src="${baseManager }/widget/list_widget.${actionExt}?functionId=61" name="contentmain"  >
		</frameset>
	</frameset>
	<noframes>
		<body>
	
		</body>
	</noframes>
</html>