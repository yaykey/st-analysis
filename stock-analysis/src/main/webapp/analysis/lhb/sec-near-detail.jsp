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

tr.cur-date td {
	background-color: #c5c5c5;
}

tr.sec-next {
	
}

tr.sec-prev {
	
}

td.total-sell-amount,
td.total-net-amount,
td.total-buy-amount {
	text-align: right;
}

td.total-buy-amount {

}

td.total-sell-amount {

}

td.total-net-amount {

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

			<c:if test="${top5 != nul && top5.nextList != null}">

				<div style="padding: 0px 0px 10px 10px;">
					<table class="" border="1" cellpadding="1" cellspacing="1">
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
							<TH>TOTAL BUY AMOUNT</TH>
							<TH>TOTAL SELL AMOUNT</TH>
							<TH>TOTAL NET AMOUNT</TH>
						</tr>

						<c:forEach items="${top5.nextList}" var="nt">
							<tr class="sec-next">
								<td align="center"><fmt:formatDate value="${nt.dateId}"
										pattern="yyyy-MM-dd E" /></td>
								<td>${nt.secId }</td>
								<td>${nt.secName }</td>
								<td class="buy-amount" align="right">${nt.buyAmount }</td>
								<td class="sell-amount" align="right">${nt.sellAmount }</td>
								<td class="net-amount" align="right">${nt.netAmount }</td>
								<td>${fn:toUpperCase(nt.type)}</td>
								<td>${nt.stockCode }</td>
								<td>${nt.stock.stockName }</td>
								<td class="total-buy-amount"></td>
								<td class="total-sell-amount"></td>
								<td class="total-net-amount"></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="9">
								<div id="sec-next-statistics"
									style="float: right; padding: 10px 10px 10px 0px">
									<table class="" border="1" cellpadding="1" cellspacing="1">
										<tr>
											<TH>TOTAL BUY AMOUNT</TH>
											<TH>TOTAL SELL AMOUNT</TH>
											<TH>TOTAL NET AMOUNT</TH>
										</tr>
										<tr>
											<td><fmt:formatNumber
													value="${top5.nextStatistics.totalBuyAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.nextStatistics.totalBuyAmount/(top5.nextStatistics.totalBuyAmount + top5.nextStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
											<td><fmt:formatNumber
													value="${top5.nextStatistics.totalSellAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.nextStatistics.totalSellAmount/(top5.nextStatistics.totalBuyAmount + top5.nextStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
											<td><fmt:formatNumber
													value="${top5.nextStatistics.totalNetAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.nextStatistics.totalNetAmount/(top5.nextStatistics.totalBuyAmount + top5.nextStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
										</tr>
									</table>
								</div>
							</td>
							<TH>TOTAL BUY AMOUNT</TH>
							<TH>TOTAL SELL AMOUNT</TH>
							<TH>TOTAL NET AMOUNT</TH>
						</tr>
						<tr class="cur-date">
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
							<td class=""></td>
							<td class=""></td>
							<td class=""></td>
						</tr>
						<tr>
							<td colspan="9">
								<div id="sec-prev-statistics"
									style="float: right; padding: 10px 10px 10px 0px">
									<table class="" border="1" cellpadding="1" cellspacing="1">
										<tr>
											<TH>TOTAL BUY AMOUNT</TH>
											<TH>TOTAL SELL AMOUNT</TH>
											<TH>TOTAL NET AMOUNT</TH>
										</tr>
										<tr>
											<td><fmt:formatNumber
													value="${top5.prevStatistics.totalBuyAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.prevStatistics.totalBuyAmount/(top5.prevStatistics.totalBuyAmount + top5.prevStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
											<td><fmt:formatNumber
													value="${top5.prevStatistics.totalSellAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.prevStatistics.totalSellAmount/(top5.prevStatistics.totalBuyAmount + top5.prevStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
											<td><fmt:formatNumber
													value="${top5.prevStatistics.totalNetAmount}"
													pattern="#,###.##" /> (<fmt:formatNumber
													value="${(top5.prevStatistics.totalNetAmount/(top5.prevStatistics.totalBuyAmount + top5.prevStatistics.totalSellAmount))*100}"
													pattern="#.##" />%)</td>
										</tr>
									</table>
								</div>
							</td>
							<TH width="70">TOTAL BUY AMOUNT</TH>
							<TH width="70">TOTAL SELL AMOUNT</TH>
							<TH width="70">TOTAL NET AMOUNT</TH>
						</tr>
						<c:forEach items="${top5.prevList}" var="pv">
							<tr class="sec-prev">
								<td align="center"><fmt:formatDate value="${pv.dateId}"
										pattern="yyyy-MM-dd E" /></td>
								<td>${pv.secId }</td>
								<td>${pv.secName }</td>
								<td class="buy-amount" align="right">${pv.buyAmount }</td>
								<td class="sell-amount" align="right">${pv.sellAmount }</td>
								<td class="net-amount" align="right">${pv.netAmount }</td>
								<td>${fn:toUpperCase(pv.type)}</td>
								<td>${pv.stockCode }</td>
								<td>${pv.stock.stockName }</td>
								<td class="total-buy-amount"></td>
								<td class="total-sell-amount"></td>
								<td class="total-net-amount"></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			var ptr;
			//alert($("#list-main").find("tr.sec-prev").length);
			var prevTrs = $("#list-main").find("tr.sec-prev");
			for (var i=(prevTrs.length-1); i>=0; i--) {
				
				var tr = prevTrs[i];
				var buyAmount = $(tr).find(".buy-amount").text();
				var sellAmount = $(tr).find(".sell-amount").text();
				var netAmount = $(tr).find(".net-amount").text();
				
				var ptotalBuyAmount=0.0, ptotalSellAmount=0.0, ptotalNetAmount=0.0;
				
				if (ptr) {
					ptotalBuyAmount = new Number($(ptr).find(".total-buy-amount").text().replace(/\,/, ""));
					ptotalSellAmount = new Number($(ptr).find(".total-sell-amount").text().replace(/\,/, ""));
					ptotalNetAmount = new Number($(ptr).find(".total-net-amount").text().replace(/\,/, ""));
				}
				
				//alert(ptr + ":" +ptotalBuyAmount);
				
				$(tr).find(".total-buy-amount").text(
						numeral(new Number($(tr).find(".buy-amount").text().replace(/\,/, "")) + ptotalBuyAmount).format('0,0.00'));
				$(tr).find(".total-sell-amount").text(
						numeral(new Number($(tr).find(".sell-amount").text().replace(/\,/, "")) + ptotalSellAmount).format('0,0.00'));
				$(tr).find(".total-net-amount").text(
						numeral(new Number($(tr).find(".net-amount").text().replace(/\,/, "")) + ptotalNetAmount).format('0,0.00'));
				
				ptr = tr;
			}
			ptr = null;
			var nextTrs = $("#list-main").find("tr.sec-next");
			for (var i=(nextTrs.length-1); i>=0; i--) {
				
				var tr = nextTrs[i];
				var buyAmount = $(tr).find(".buy-amount").text();
				var sellAmount = $(tr).find(".sell-amount").text();
				var netAmount = $(tr).find(".net-amount").text();
				
				var ptotalBuyAmount=0.0, ptotalSellAmount=0.0, ptotalNetAmount=0.0;
				
				if (ptr) {
					ptotalBuyAmount = new Number($(ptr).find(".total-buy-amount").text().replace(/\,/, ""));
					ptotalSellAmount = new Number($(ptr).find(".total-sell-amount").text().replace(/\,/, ""));
					ptotalNetAmount = new Number($(ptr).find(".total-net-amount").text().replace(/\,/, ""));
				}
				
				//alert(ptr + ":" +ptotalBuyAmount);
				
				$(tr).find(".total-buy-amount").text(
						numeral(new Number($(tr).find(".buy-amount").text().replace(/\,/, "")) + ptotalBuyAmount).format('0,0.00'));
				$(tr).find(".total-sell-amount").text(
						numeral(new Number($(tr).find(".sell-amount").text().replace(/\,/, "")) + ptotalSellAmount).format('0,0.00'));
				$(tr).find(".total-net-amount").text(
						numeral(new Number($(tr).find(".net-amount").text().replace(/\,/, "")) + ptotalNetAmount).format('0,0.00'));
				
				ptr = tr;
			}
		});
	</script>
</body>
</html>