<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<%@ page import="com.st.Global"%>

<html>
<head>

</head>
<style type="text/css">
	.orderby {
		width : 20px;
	}
	
	.list-main {
	
	}
	
	.list-main .high{
		background-color: darksalmon;
	}
	
	.list-main .low{
		background-color: lightskyblue;
	}
	
	.list-main td.sub-high{
		color: darksalmon;
	}
	
	.list-main td.sub-low{
		color: lightskyblue;
	}
	
</style>

<body style="background-color: #FCFAF3">
<form id="frm" action="${base }/analysis/aw.feinno" method="POST">
	
	<div class="info">
		<table border="1" cellpadding="1" cellspacing="1">
			<tr>
				<td>highPerAvg</td>				
				<td>
				<fmt:formatNumber value="${infobean.highPerAvg }" pattern="#.###"/>
				</td>
			</tr>
			<tr>
				<td>lowPerAvg</td>				
				<td>
					<fmt:formatNumber value="${infobean.lowPerAvg }" pattern="#.###"/>
				</td>
			</tr>
			<tr>
				<td>openAvg</td>				
				<td>
				<fmt:formatNumber value="${infobean.openAvg }" pattern="#.###"/>
				</td>
			</tr>
			<tr>
				<td>closeAvg</td>				
				<td>
				<fmt:formatNumber value="${infobean.closeAvg }" pattern="#.###"/>
				</td>
			</tr>
			<tr>
				<td>dateAvg</td>				
				<td>
				<fmt:formatNumber value="${infobean.dateAvg }" pattern="#.###"/>
				</td>
			</tr>
			
		</table>
	</div>
	<div id="list-main" class="list-main">
		<table id="list-main-table" border="1" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<%@ include file="/analysis/wave/thead.jsp"%>
					<th>MA5</th>
					<th>MA10</th>
				</tr>
			</thead>
			<%@ include file="/analysis/wave/tbody.jsp"%>
		</table>
	</div>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		var flag
		$("#list-main-table").find("tbody").find("tr").each(function(){
			
			
			
			/* $(this).find("td.diff").each(function(){
				sum = sum + parseInt($(this).text());				
				//alert(sum);
			});
			
			
			
			$($(this).find("td.SUM-DATE")[0]).append(sum); */
		});
	});
</script>
</body>
</html>