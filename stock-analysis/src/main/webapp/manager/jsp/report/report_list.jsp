<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>



	<frameset id="widgetMain_frm" name="widgetMain_frm" cols="125,10,500" framespacing="0" frameborder="NO" border="0">
		<frame src="${baseManager }/jsp/report/ztree_report.jsp" name="menuTreeFrame" scrolling="NO" >
		<frame src="${baseManager }/jsp/report/switch.jsp" name="switchFrame" scrolling="NO" >
		<frame id="frame_content" frameborder="0" src="${baseManager}/report/reportList.feinno?functionName=${reportName}" ></iframe>
	</frameset>
	<noframes>
		<body>
	
		</body>
	</noframes>

</html>