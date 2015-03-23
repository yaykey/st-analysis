<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>综合智能应用处理平台</title>
		<%@include file="/manager/commons/meta.jsp"%>
	</head>
		<frameset id="widgetMain_frm" name="widgetMain_frm" cols="125,10,500" framespacing="0"
			frameborder="NO" border="0">
			<frame src="${baseManager }/jsp/function/ztree_function.jsp" name="menuTreeFrame" scrolling="NO" id="menuTreeFrame" >
			<frame src="${baseManager }/jsp/function/switch.jsp" name="switchFrame" scrolling="NO" >
			<frame src="${baseManager }/function/list.${actionExt}" name="contentmain"  >
		</frameset>
		<noframes>
			<body>
			</body>
		</noframes>
</html>