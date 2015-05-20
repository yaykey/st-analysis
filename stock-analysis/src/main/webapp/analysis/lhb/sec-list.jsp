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

tr.cur-date {

}

tr.cur-date td{
	background-color: #c5c5c5;
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
		<div class="list-main">
			<table class="" border="1" cellpadding="1" cellspacing="1"
				width="100%" style="margin-top: 10px; margin-bottom: 10px;">
				<thead>
					<tr>
						<TH width="80" align="center">DATE ID</TH>
						<TH>SEC ID</TH>
						<TH>SEC NAME</TH>
						<TH>BUY AMOUNT</TH>
						<TH>SELL AMOUNT</TH>
						<TH>NET AMOUNT</TH>
						<th width="50">TYPE</th>
						<th width="70">STOCK CODE</th>
						<th width="70">STOCK NAME</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="top5">
						<tr>
							<td align="center"><fmt:formatDate value="${top5.dateId}"
									pattern="yyyy-MM-dd E" /></td>
							<td>${top5.secId }</td>
							<td>${top5.secName }</td>
							<td align="right">${top5.buyAmount }</td>
							<td align="right">${top5.sellAmount }</td>
							<td align="right">${top5.netAmount }</td>
							<td>${fn:toUpperCase(top5.type)}</td>
							<td>${top5.stockCode }</td>
							<td>${top5.stock.stockName }</td>

						</tr>
						<c:if test="${top5.noticeStock != null}">
							<tr>
								<td colspan="9">
									<div style="padding-left: 10px; padding-bottom: 10px;">
										<table border="1" cellpadding="1" cellspacing="1">
											<tr>
												<TH>DATE ID</TH>
												<TH>STOCK</TH>
												<TH>OPEN</TH>
												<TH>HIGH</TH>
												<TH>LOW</TH>
												<TH>VOLUME</TH>
												<TH>HIGH TIME ID</TH>
												<TH>LOW TIME ID</TH>
												<TH>HIGH PER</TH>
												<TH>LOW PER</TH>
												<TH>CLOSE PER</TH>
												<TH>TURN VOLUME</TH>
												<TH>TURN OVER RATE</TH>
											</tr>
											
											<c:forEach items="${top5.noticeStock.prev5Day}" var="st">
												<tr>
												<td><fmt:formatDate value="${st.date}"
														pattern="yyyy-MM-dd E" /></td>
												<td>${st.stock}</td>
												<td>${st.open}</td>
												<td>${st.high}</td>
												<td>${st.low}</td>
												<td>${st.volume}</td>
												<td>${st.highTimeId}</td>
												<td>${st.lowTimeId}</td>
												<td>${st.highPer}</td>
												<td>${st.lowPer}</td>
												<td>${st.closePer}</td>
												<td>${st.turnVolume}</td>
												<td>${st.turnoverRate}</td>
												</tr>
											</c:forEach>
											
											<tr class="cur-date">
												<td><fmt:formatDate value="${top5.noticeStock.date}"
														pattern="yyyy-MM-dd E" /></td>
												<td>${top5.noticeStock.stock}</td>
												<td>${top5.noticeStock.open}</td>
												<td>${top5.noticeStock.high}</td>
												<td>${top5.noticeStock.low}</td>
												<td>${top5.noticeStock.volume}</td>
												<td>${top5.noticeStock.highTimeId}</td>
												<td>${top5.noticeStock.lowTimeId}</td>
												<td>${top5.noticeStock.highPer}</td>
												<td>${top5.noticeStock.lowPer}</td>
												<td>${top5.noticeStock.closePer}</td>
												<td>${top5.noticeStock.turnVolume}</td>
												<td>${top5.noticeStock.turnoverRate}</td>
											</tr>
											<c:forEach items="${top5.noticeStock.next5Day}" var="st">
												<tr>
												<td><fmt:formatDate value="${st.date}"
														pattern="yyyy-MM-dd E" /></td>
												<td>${st.stock}</td>
												<td>${st.open}</td>
												<td>${st.high}</td>
												<td>${st.low}</td>
												<td>${st.volume}</td>
												<td>${st.highTimeId}</td>
												<td>${st.lowTimeId}</td>
												<td>${st.highPer}</td>
												<td>${st.lowPer}</td>
												<td>${st.closePer}</td>
												<td>${st.turnVolume}</td>
												<td>${st.turnoverRate}</td>
												</tr>
											</c:forEach>
											
										</table>
									</div>
								</td>
							</tr>
						</c:if>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>

</body>
</html>