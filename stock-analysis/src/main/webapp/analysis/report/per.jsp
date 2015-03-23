<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<%@ page import="com.st.Global"%>

<html>
<head>

</head>
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<body style="background-color: #FCFAF3">

	<div id="search-bar">
		<form action="${base }/analysis/per.action" method="POST">
			<table>
			<tr>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>CLOSE_PER</td>
			</tr>
			<tr>
				<td><input name="startDate"></td>
				<td><input name="endDate"></td>
				<td><input name="closePerRange"></td>
			</tr>
		</table>
		</form>
		
	</div>
	<table>
	<%
		for (int i=0; i<Global.STOCK_PER_RANGE_LEGEND.length; i++) {
			
		}
	%>	
	</table>
</body>
</html>