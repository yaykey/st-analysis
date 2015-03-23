<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp" %>

<%
	//全局唯一id;	
	String onlyId = request.getParameter("onlyId");
	if (onlyId == null) {
		onlyId = "" + (int)(Math.random() * 10000);
	}
	
	request.setAttribute("onlyId", onlyId);
%>
<html>
	<head>
		<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible" />
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<link href="${base}/viewframe/css/style.css" rel="Stylesheet" />
		
		<script type="text/javascript" src="${base }/report/js/amcharts/amcharts.js"></script>
		<script type="text/javascript" src="${base }/report/js/amcharts/swfobject.js"></script>
	</head>
	<body>
		<div id="report-main" class="">
			<c:forEach var="widget" items="${requestScope.widgetList}" varStatus="status">
			<div id="report_widget_${status.index}"></div>
			<script type="text/javascript">
				$(document).ready(function() {
					//alert("");
					var loadURL = "${widget.actionUrl}";
					jQuery.ajaxSetup({ cache: true });
					jQuery.ajax({
						type : "POST",
						url : base + loadURL+"&functionId=${functionId}",
						dataType : 'text',
						data : {
							//,"nocache" : new Date().getTime()
						},
						success : function(response) {
							$("#report_widget_${status.index}").append(response);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							
						}
					});
				});
			</script>
			</c:forEach>
		</div>
		
		
		
		
		
	</body>
</html>