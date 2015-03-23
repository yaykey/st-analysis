<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.st.framework.controller.UserSessionInfo"%>
<%@ include file="/commons/taglibs.jsp"%>

<%
	//全局唯一id;	
	String onlyId = request.getParameter("onlyId");
	if (onlyId == null) {
		onlyId = "" + (int)(Math.random() * 10000);
	}
	
	request.setAttribute("onlyId", onlyId);
	String dimTypeId = request.getParameter("dimTypeId");
	String dimId = request.getParameter("dimId");
	
	UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute("userSessionInfo");
	Integer defaultDataProvinceId = 0;
%>

		<c:forEach var="widget" items="${requestScope.widgetList}" varStatus="status">
			<div id="report_widget_${status.index}"  class=""></div>
			<script type="text/javascript">
				$(document).ready(function() {
					//alert("");
					var loadURL = "${widget.actionUrl}";
					
					var defaultDataProvinceId = "<%=defaultDataProvinceId%>";
					
					loadURL = loadURL.replace(/&province=86/g,"&province=" + defaultDataProvinceId);
					loadURL = loadURL.replace(/&dstcId=86/g,"&dstcId=" + defaultDataProvinceId);
										
					loadURL = encodeURI (loadURL);
					//alert("r-main->url=" + loadURL);
					
					$("#report_widget_${status.index}").append(loadContent);
					
					jQuery.ajaxSetup({ cache: true });
					jQuery.ajax({
						type : "POST",
						url : base + loadURL+"&functionId=${widget.functionId}"+"&widgetId=${widget.id}"+"&clientTypeId=${clientTypeId}",
						dataType : 'text',
						data : {
							//,"nocache" : new Date().getTime()
						},
						success : function(response) {
							$("#report_widget_${status.index}").empty();
							$("#report_widget_${status.index}").append(response);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							loadError({
								"XMLHttpRequest" :  XMLHttpRequest,
								"textStatus" : textStatus,
								"errorThrown" : errorThrown,
								"el" : $("#report_widget_${status.index}")
							});
						}
					});
				});
			</script>
		</c:forEach>
