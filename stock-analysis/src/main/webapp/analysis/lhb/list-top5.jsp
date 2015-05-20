<%@ page pageEncoding="UTF-8"%>
<div style="padding-left:10px;">
<table class="sub-list-main-table-buy" border="1" cellpadding="1"
	cellspacing="1" width="100%" style="margin-top:10px; margin-bottom:10px;">
	<thead>
		<tr>
			<th colspan="5">buy</th>
		</tr>
		<tr>
			<TH>DATE</TH>
			<TH>SEC ID</TH>
			<TH  width="50%">SEC NAME</TH>
			<TH>BUY AMOUNT</TH>
			<TH>SELL AMOUNT</TH>
			<TH>NET AMOUNT</TH>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rpt.buyTop5}" var="top5">
			<tr>
				<td>
					<a href='${base}/lhb/sec-near-detail.feinno?secid=${top5.secId }
						&timeId=<fmt:formatDate value="${top5.dateId}" pattern="yyyy-MM-dd" />
						&stockCode=${top5.stockCode}'>
						<fmt:formatDate value="${top5.dateId}" pattern="yyyy-MM-dd" />
					</a>
				</td>				
				<td>
					<a href="${base}/lhb/sec-list.feinno?secid=${top5.secId }&stockCode=${stockCode}">${top5.secId }</a>
				</td>
				<td>${top5.secName }</td>
				<td>${top5.buyAmount }</td>
				<td>${top5.sellAmount }</td>
				<td>${top5.netAmount }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<table class="sub-list-main-table-sell" border="1" cellpadding="1"
	cellspacing="1" width="100%" style="margin-top:10px; margin-bottom:10px;">
	<thead>
		<tr>
			<th colspan="5">sell</th>
		</tr>
		<tr>
			<TH>DATE</TH>
			<TH>SEC ID</TH>
			<TH width="50%">SEC NAME</TH>
			<TH>BUY AMOUNT</TH>
			<TH>SELL AMOUNT</TH>
			<TH>NET AMOUNT</TH>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rpt.sellTop5}" var="top5">
			<tr>
				<td>
					<a href='${base}/lhb/sec-near-detail.feinno?secid=${top5.secId }
						&timeId=<fmt:formatDate value="${top5.dateId}" pattern="yyyy-MM-dd" />
						&stockCode=${top5.stockCode}'>
						<fmt:formatDate value="${top5.dateId}" pattern="yyyy-MM-dd" />
					</a>
				</td>
				<td>
					<a href="${base}/lhb/sec-list.feinno?secid=${top5.secId }&stockCode=${stockCode}">${top5.secId }</a>
				</td>
				<td>${top5.secName }</td>
				<td>${top5.buyAmount }</td>
				<td>${top5.sellAmount }</td>
				<td>${top5.netAmount }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>