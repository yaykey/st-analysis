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
	
	
	
</style>

<body style="background-color: #FCFAF3">
<form id="frm" action="${base }/analysis/list.feinno" method="POST">
	<div id="search-bar">
		
		<table>
			
			<tr>
				<td>时间</td>
				<td><input type="button" id="prev-timeid" v="${prevtimeid }" value="<"></td>
				<td><input id="timeid" name="timeid" value="${timeid}" style="width:80px;"></td>
				<td><input type="button" id="next-timeid" v="${nexttimeid }" value=">"></td>
				<td><input type="submit" value="GO"></td>
			</tr>
			<tr>
				<td colspan="5">
					<c:forEach items="${hiddens}" var="item">
						<c:if test="${item.value != null && item.value == 1}">
							<input type="button" onclick="unhiddenhandler('${item.key}');"
								value="${item.key}">
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</table>
		
		
	</div>
	<div id="list-main" class="list-main">
		<table id="list-main-table" border="1" cellpadding="1" cellspacing="1">			
			<thead>
				<tr>
					<th>
						<input id="list-all-check" type="checkbox" title="全选">
					</th>
					<th>STOCK <input type="button" class="orderby"
						value="${(order.stock == null || order.stock == 0)?('-'):((order.stock == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.stock" value="${order.stock}"></th>
					<c:if test="${hiddens.date == null || hiddens.date == 0}">
						<th>DATE
							<div>
								<input type="button" class="list-hidden"
									onclick="hiddenhandler(this);" value="H">
								<input
									type="hidden" class="list-hiddens-hidden-date"
									name="hiddens.date"
									value="${(hiddens.date == null)?(0):(hiddens.date)}">
							</div>
						</th>
					</c:if>
					<c:if test="${hiddens.date != null && hiddens.date == 1}">
						<input type="hidden" class="list-hiddens-hidden-date"
							name="hiddens.date" value="${hiddens.date}">
					</c:if>

					<c:if test="${hiddens.open == null || hiddens.open == 0}">
						<th>OPEN
							<div>
								<input type="button" class="orderby"
									value="${(order.open == null || order.open == 0)?('-'):((order.open == 1)?('↑'):('↓'))}"
									onclick="orderby(this);"/>
								<input type="hidden"
									class="list-order-hidden" name="order.open"
									value="${order.open}"/>
								<input type="button"
									class="list-hidden" onclick="hiddenhandler(this);" value="H">
								<input type="hidden" class="list-hiddens-hidden-open"
									name="hiddens.open"
									value="${(hiddens.open == null)?(0):(hiddens.open)}"/>
								<input type="button" class="compare" name="compares.open"
									value="C"/>
							</div>
						</th>
					</c:if>
					<c:if test="${hiddens.open != null && hiddens.open == 1}">
						<input type="hidden" class="list-hiddens-hidden-open"
							name="hiddens.open" value="${hiddens.open}">
					</c:if>

					<c:if test="${hiddens.high == null || hiddens.high == 0}">
						<th>HIGH
							<div><input type="button" class="orderby"
							value="${(order.high == null || order.high == 0)?('-'):((order.high == 1)?('↑'):('↓'))}"
							onclick="orderby(this);"> <input type="hidden"
							name="order.high" value="${order.high}"> <input
							type="button" class="list-hidden" onclick="hiddenhandler(this);"
							value="H"> <input type="hidden"
							class="list-hiddens-hidden-high" name="hiddens.high"
							value="${(hiddens.high == null)?(0):(hiddens.high)}">
							</div>
						</th>
					</c:if>
					<c:if test="${hiddens.high != null && hiddens.high == 1}">
						<input type="hidden" class="list-hiddens-hidden-high"
							name="hiddens.high" value="${hiddens.high}">
					</c:if>
					<c:if test="${hiddens.low == null || hiddens.low == 0}">
					<th>LOW
						<div><input type="button" class="orderby"
						value="${(order.low == null || order.low == 0)?('-'):((order.low == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.low" value="${order.low}"> <input
						type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-low" name="hiddens.low"
						value="${(hiddens.low == null)?(0):(hiddens.low)}">
						</div>
					</th>
					</c:if>
					<c:if test="${hiddens.low != null && hiddens.low == 1}">
						<input type="hidden" class="list-hiddens-hidden-low"
							name="hiddens.low" value="${hiddens.low}">
					</c:if>
					<c:if test="${hiddens.close == null || hiddens.close == 0}">
					<th>CLOSE <input type="button" class="orderby"
						value="${(order.close == null || order.close == 0)?('-'):((order.close == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.close" value="${order.close}"> <input
						type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-close" name="hiddens.close"
						value="${(hiddens.close == null)?(0):(hiddens.close)}">
					</th>
					</c:if>
					<c:if test="${hiddens.close != null && hiddens.close == 1}">
						<input type="hidden" class="list-hiddens-hidden-close"
							name="hiddens.close" value="${hiddens.close}">
					</c:if>
					<c:if test="${hiddens.adjClose == null || hiddens.adjClose == 0}">
					<th>ADJCLOSE <input type="button" class="orderby"
						value="${(order.adjClose == null || order.adjClose == 0)?('-'):((order.adjClose == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.adjClose" value="${order.adjClose}"> <input
						type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-adjClose" name="hiddens.adjClose"
						value="${(hiddens.adjClose == null)?(0):(hiddens.adjClose)}">
					</th>
					</c:if>
					<c:if test="${hiddens.adjClose != null && hiddens.adjClose == 1}">
						<input type="hidden" class="list-hiddens-hidden-adjClose"
							name="hiddens.adjClose" value="${hiddens.adjClose}">
					</c:if>
					<c:if test="${hiddens.priceChanges == null || hiddens.priceChanges == 0}">
					<th>PRICECHANGES <input type="button" class="orderby"
						value="${(order.priceChanges == null || order.priceChanges == 0)?('-'):((order.priceChanges == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.priceChanges" value="${order.priceChanges}">
						<input
						type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-priceChanges" name="hiddens.priceChanges"
						value="${(hiddens.priceChanges == null)?(0):(hiddens.priceChanges)}">
					</th>
					</c:if>
					<c:if test="${hiddens.priceChanges != null && hiddens.priceChanges == 1}">
						<input type="hidden" class="list-hiddens-hidden-priceChanges"
							name="hiddens.priceChanges" value="${hiddens.priceChanges}">
					</c:if>
					<c:if test="${hiddens.highTimeId == null || hiddens.highTimeId == 0}">
					<th>HIGHTIMEID <input type="button" class="orderby"
						value="${(order.highTimeId == null || order.highTimeId == 0)?('-'):((order.highTimeId == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.highTimeId" value="${order.highTimeId}">
						<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-highTimeId" name="hiddens.highTimeId"
						value="${(hiddens.highTimeId == null)?(0):(hiddens.highTimeId)}">
					</th>
					</c:if>
					<c:if test="${hiddens.highTimeId != null && hiddens.highTimeId == 1}">
						<input type="hidden" class="list-hiddens-hidden-highTimeId"
							name="hiddens.highTimeId" value="${hiddens.highTimeId}">
					</c:if>
					<c:if test="${hiddens.lowTimeId == null || hiddens.lowTimeId == 0}">
					<th>LOWTIMEID <input type="button" class="orderby"
						value="${(order.lowTimeId == null || order.lowTimeId == 0)?('-'):((order.lowTimeId == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.lowTimeId" value="${order.lowTimeId}">
						<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-lowTimeId" name="hiddens.lowTimeId"
						value="${(hiddens.lowTimeId == null)?(0):(hiddens.lowTimeId)}">
					</th>
					</c:if>					
					<c:if test="${hiddens.lowTimeId != null && hiddens.lowTimeId == 1}">
						<input type="hidden" class="list-hiddens-hidden-lowTimeId"
							name="hiddens.lowTimeId" value="${hiddens.lowTimeId}">
					</c:if>
					<c:if test="${hiddens.amplitude == null || hiddens.amplitude == 0}">
					<th>AMPLITUDE <input type="button" class="orderby"
						value="${(order.amplitude == null || order.amplitude == 0)?('-'):((order.amplitude == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.amplitude" value="${order.amplitude}">
						<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-amplitude" name="hiddens.amplitude"
						value="${(hiddens.amplitude == null)?(0):(hiddens.amplitude)}">
					</th>
					</c:if>					
					<c:if test="${hiddens.amplitude != null && hiddens.amplitude == 1}">
						<input type="hidden" class="list-hiddens-hidden-amplitude"
							name="hiddens.amplitude" value="${hiddens.amplitude}">
					</c:if>
					<c:if test="${hiddens.highPer == null || hiddens.highPer == 0}">
					<th>HIGHPER <input type="button" class="orderby"
						value="${(order.highPer == null || order.highPer == 0)?('-'):((order.highPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.highPer" value="${order.highPer}">
					<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-highPer" name="hiddens.highPer"
						value="${(hiddens.highPer == null)?(0):(hiddens.highPer)}">
					</th>
					</c:if>
					<c:if test="${hiddens.highPer != null && hiddens.highPer == 1}">
						<input type="hidden" class="list-hiddens-hidden-highPer"
							name="hiddens.highPer" value="${hiddens.highPer}">
					</c:if>
					<c:if test="${hiddens.lowPer == null || hiddens.lowPer == 0}">
					<th>LOWPER <input type="button" class="orderby"
						value="${(order.lowPer == null || order.lowPer == 0)?('-'):((order.lowPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.lowPer" value="${order.lowPer}">
					<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-lowPer" name="hiddens.lowPer"
						value="${(hiddens.lowPer == null)?(0):(hiddens.lowPer)}">
					</th>
					</c:if>
					<c:if test="${hiddens.lowPer != null && hiddens.lowPer == 1}">
						<input type="hidden" class="list-hiddens-hidden-lowPer"
							name="hiddens.lowPer" value="${hiddens.lowPer}">
					</c:if>
					<c:if test="${hiddens.closePer == null || hiddens.closePer == 0}">
					<th>CLOSEPER <input type="button" class="orderby"
						value="${(order.closePer == null || order.closePer == 0)?('-'):((order.closePer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.closePer" value="${order.closePer}">
					<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-closePer" name="hiddens.closePer"
						value="${(hiddens.closePer == null)?(0):(hiddens.closePer)}">
					</th>
					</c:if>
					<c:if test="${hiddens.closePer != null && hiddens.closePer == 1}">
						<input type="hidden" class="list-hiddens-hidden-closePer"
							name="hiddens.closePer" value="${hiddens.closePer}">
					</c:if>
					<c:if test="${hiddens.openPer == null || hiddens.openPer == 0}">
					<th>OPENPER <input type="button" class="orderby"
						value="${(order.openPer == null || order.openPer == 0)?('-'):((order.openPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"> <input type="hidden"
						name="order.openPer" value="${order.openPer}">
					<input type="button" class="list-hidden" onclick="hiddenhandler(this);"
						value="H"> <input type="hidden"
						class="list-hiddens-hidden-openPer" name="hiddens.openPer"
						value="${(hiddens.openPer == null)?(0):(hiddens.openPer)}">
					</th>
					</c:if>
					<c:if test="${hiddens.openPer != null && hiddens.openPer == 1}">
						<input type="hidden" class="list-hiddens-hidden-openPer"
							name="hiddens.openPer" value="${hiddens.openPer}">
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.list}" var="st">
					<tr>
						<td>
							<div>
								<input class="list-check" type="checkbox" name="stocks" value="${st.stock}">
							</div>
						</td>
						<td><div><a class="list-stock" href="${base }/analysis/selectlist.feinno?stock=${st.stock}&timeid=${timeid}">${st.stock }</a></div></td>
						
						<c:if test="${hiddens.date == null || hiddens.date == 0}">
							<td><div><fmt:formatDate value="${st.date}" pattern="yyyy-MM-dd" /></div></td>
						</c:if>

						<c:if test="${hiddens.open == null || hiddens.open == 0}">
							<td><div>${st.open }</div></td>
						</c:if>
						<c:if test="${hiddens.high == null || hiddens.high == 0}">
							<td><div>${st.high}</div></td>
						</c:if>
						<c:if test="${hiddens.low == null || hiddens.low == 0}">
							<td><div>${st.low}</div></td>
						</c:if>
						<c:if test="${hiddens.close == null || hiddens.close == 0}">
							<td><div>${st.close}</div></td>
						</c:if>

						<c:if test="${hiddens.adjClose == null || hiddens.adjClose == 0}">
							<td><div>${st.adjClose}</div></td>
						</c:if>
						<c:if
							test="${hiddens.priceChanges == null || hiddens.priceChanges == 0}">
							<td><div>${st.priceChanges}</div></td>
						</c:if>
						<c:if
							test="${hiddens.highTimeId == null || hiddens.highTimeId == 0}">
							<td><div>${st.highTimeId}</div></td>
						</c:if>
						<c:if
							test="${hiddens.lowTimeId == null || hiddens.lowTimeId == 0}">
							<td><div>${st.lowTimeId}</div></td>
						</c:if>
						<c:if
							test="${hiddens.amplitude == null || hiddens.amplitude == 0}">
							<td><div>${st.amplitude}</div></td>
						</c:if>
						<c:if test="${hiddens.highPer == null || hiddens.highPer == 0}">
							<td><div>${st.highPer}</div></td>
						</c:if>
						<c:if test="${hiddens.lowPer == null || hiddens.lowPer == 0}">
							<td><div>${st.lowPer}</div></td>
						</c:if>
						<c:if test="${hiddens.closePer == null || hiddens.closePer == 0}">
							<td><div>${st.closePer}</div></td>
						</c:if>
						<c:if test="${hiddens.openPer == null || hiddens.openPer == 0}">
							<td><div>${st.openPer}</div></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>
<script type="text/javascript">
	function unhiddenhandler(type) {
		
		if (type) {			
			$("#list-main-table").find("thead").find(
					"input.list-hiddens-hidden-" + type).val(0);
		}
		

		$("#frm").submit();
	}

	function hiddenhandler(el) {
		var v = $(el).val();
		var hiddenEl = $($(el).next()[0]);

		//$("#list-main-table").find("thead").find("input.list-hiddens-hidden").val(0);

		hiddenEl.val(1);

		$("#frm").submit();
	}

	function orderby(el) {
		var v = $(el).val();
		var hiddenEl = $($(el).next()[0]);

		$("#list-main-table").find("thead").find("input.list-order-hidden")
				.val(0);

		if ("-" == v) {
			$(el).val("↓");
			hiddenEl.val(-1);
		} else if ("↓" == v) {
			$(el).val("↑");
			hiddenEl.val(1);
		} else if ("↑" == v) {
			$(el).val("-");
			hiddenEl.val(0);
		}

		$("#frm").submit();
	}

	$(document).ready(
			function() {
				$("#prev-timeid").click(function() {
					$("#timeid").val($(this).attr("v"));
					$("#frm").submit();
				});

				$("#next-timeid").click(function() {
					$("#timeid").val($(this).attr("v"));
					$("#frm").submit();
				});

				$("#list-all-check")
						.click(
								function() {
									if ($(this).attr("checked")) {
										$("#list-main-table").find("tbody")
												.find("input:checkbox").attr(
														"checked", true);
									} else {
										$("#list-main-table").find("tbody")
												.find("input:checkbox").attr(
														"checked", false);
									}
								});

			});
</script>
</body>
</html>