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
	width: 20px;
}
</style>

<body style="background-color: #FCFAF3">
	<form id="frm" action="${base }/lhb/list.feinno" method="POST">
		<div id="search-bar">
			<table>
				<tr>
					<td>时间</td>
					
					<td><input type="submit" value="GO"></td>
				</tr>
			</table>
		</div>
		<div id="list-main" class="list-main">
			<table id="list-main-table" border="1" cellpadding="1"
				cellspacing="1">
				<thead>
					<tr>
						<th>STOCK CODE</th>
						<th width="70">DATE ID</th>
						<th>REASONS</th>
						<th>TOTAL AMOUNT</th>
						<th>TOTAL VOLUME</th>
						<th>TOTAL CAPITALPER</th>
						<th>A SHARE PER</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.reports}" var="rpt">
						<tr>
							<td>
								<a href='${base }/lhb/sec-relation.feinno?
									stockCode=${rpt.stockCode}
									&timeId=<fmt:formatDate value="${rpt.dateId}"
									pattern="yyyy-MM-dd" />
									&startTimeId=<fmt:formatDate value="${rpt.dateId}"
									pattern="yyyy-MM-dd"/>
									&endTimeId=${(rpt.next != null)?(rpt.next.dateId):("")}'
									
								>${rpt.stockCode}</a>
							
							</td>
							<td><fmt:formatDate value="${rpt.dateId}"
									pattern="yyyy-MM-dd E" /></td>
							<td>${rpt.reasons}</td>
							<td>${rpt.totalAmount}</td>
							<td>${rpt.totalVolume}</td>
							<td>${rpt.totalCapitalPer}</td>
							<td>${rpt.aSharePer}%</td>
						</tr>
						<tr>
							<td colspan="7"><%@ include
									file="/analysis/lhb/list-top5.jsp"%></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>

</body>
</html>