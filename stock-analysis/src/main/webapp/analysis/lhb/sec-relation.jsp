<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<%@ include file="/commons/easyuilibs.jsp"%>
<%@ page import="com.st.Global"%>

<html>
<head>

</head>
<style type="text/css">
.orderby {
	width: 20px;
}

tr.cur-date {
	
}

tr.cur-date td {
	background-color: #c5c5c5;
}

tr.sec-next {
	
}

tr.sec-prev {
	
}

td.total-sell-amount, td.total-net-amount, td.total-buy-amount {
	text-align: right;
}

td.total-buy-amount {
	
}

td.total-sell-amount {
	
}

td.total-net-amount {
	
}


.datagrid-cell, 
.datagrid-cell-group, 
.datagrid-header-rownumber, 
.datagrid-cell-rownumber {
    white-space: normal;
    word-wrap: normal;
}
</style>

<body style="background-color: #FCFAF3">
	<form id="frm" action="${base }/lhb/sec-list.feinno" method="POST">
		<div id="search-bar">
			<input type="hidden" name="secid" value="${secid}">
			<table>
				<tr>
					<td>时间</td>
					<td><input type="text"
						style="width: 100px; text-align: center;" name="startDate"
						value="${startDate }"></td>
					<td><input type="text"
						style="width: 100px; text-align: center;" name="endDate"
						value="${(endDate == null)?(''):(endDate) }"></td>
					<td><input type="submit" value="GO"></td>

				</tr>
			</table>
		</div>
		<div id="list-main" class="list-main">

			<div style="padding: 0px 0px 10px 10px;">
				<table class="easyui-datagrid" style="width_:800px;height_:250px" 
						border="1" cellpadding="1" cellspacing="1" 
						data-options="singleSelect:true,collapsible:true,
				url:'${base }/lhb/sec-relation-json.feinno?stockCode=${stockCode}&timeId=<fmt:formatDate 
				value="${timeId }" pattern="yyyy-MM-dd"/>&startTimeId=<fmt:formatDate 
				value="${startTimeId }" pattern="yyyy-MM-dd"/>&endTimeId=<fmt:formatDate 
				value="${endTimeId }" pattern="yyyy-MM-dd"/>',
				method:'get',remoteSort:false,multiSort:true,
				rownumbers: true,showFooter: true">
				<thead>
					<tr>
						<th data-options="field:'dateId',width:80,sortable:true">DATE ID</th>
						<th data-options="field:'secId',width:60,sortable:true">SEC ID</th>
						<th data-options="field:'secName',width:160,align:'left',sortable:true">SEC NAME</th>
						<th data-options="field:'buyAmount',width:100,align:'right',sortable:true">BUY AMOUNT</th>
						<th data-options="field:'sellAmount',width:100,align:'right',sortable:true">SELL AMOUNT</th>
						<th data-options="field:'netAmount',width:100,align:'right',sortable:true">NET AMOUNT</th>
						<th data-options="field:'type',width:40,align:'center',sortable:true">TYPE</th>
						<th data-options="field:'stockCode',width:80,align:'center',sortable:true">ST CODE</th>
						<th data-options="field:'stockName',width:80,align:'center',sortable:true">ST NAME</th>
					</tr>
				</thead>
					

					<%-- <c:forEach items="${list}" var="top">
						<tr class="">
							<td align="center"><fmt:formatDate value="${top.dateId}"
									pattern="yyyy-MM-dd E" /></td>
							<td>${top.secId }</td>
							<td>${top.secName }</td>
							<td class="buy-amount" align="right">${top.buyAmount }</td>
							<td class="sell-amount" align="right">${top.sellAmount }</td>
							<td class="net-amount" align="right">${top.netAmount }</td>
							<td>${fn:toUpperCase(top.type)}</td>
							<td>${top.stockCode }</td>
							<td>${top.stock.stockName }</td>
						</tr>
					</c:forEach> --%>

				</table>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</body>
</html>