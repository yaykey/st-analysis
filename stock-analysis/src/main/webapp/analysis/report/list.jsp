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
<form id="frm" action="${base }/analysis/list.feinno" method="GET">
	<div id="search-bar">
		
		<table>
			
			<tr>
				<td>时间</td>
				<td><input type="button" id="prev-timeid" v="${prevtimeid }" value="<"></td>
				<td><input id="timeid" name="timeid" value="${timeid}" style="width:80px;"></td>
				<td><input type="button" id="next-timeid" v="${nexttimeid }" value=">"></td>
				<td><input type="submit" value="GO"></td>
			</tr>			
		</table>
		
		
	</div>
	<div id="list-main" class="list-main">
		<table id="list-main-table" border="1" cellpadding="1" cellspacing="1">
			
			<thead>
				<input id="list-hiddens" type="hidden" name="hiddens">
				<tr>
					<th><input id="list-all-check" type="checkbox" title="全选"></th>
					<th>STOCK						
						<input type="button" class="orderby"
						value="${(order.stock == null || order.stock == 0)?('-'):((order.stock == 1)?('↑'):('↓'))}"
						onclick="orderby(this);">
						<input type="hidden" name="order.stock" value="${order.stock}">
					</th>
					
					<c:if test="${hiddens.open == null || hiddens.open == 0}">
						<th>OPEN
							<div>
								<input type="button" class="orderby"
								value="${(order.open == null || order.open == 0)?('-'):((order.open == 1)?('↑'):('↓'))}"
								onclick="orderby(this);">
								<input type="hidden" name="order.open" value="${order.open}">
								<input type="button" class="hidden" v="open" onclick="hiddenhandler(this);" value="H">
								<input type="button" class="compare" name="compares.open" value="C">
							</div>
						</th>
					</c:if>
					
					
					<th>HIGH
						<input type="button" class="orderby"
						value="${(order.high == null || order.high == 0)?('-'):((order.high == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.high" value="${order.high}">
					</th>
					<th>LOW
						<input type="button" class="orderby"
						value="${(order.low == null || order.low == 0)?('-'):((order.low == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.low" value="${order.low}"></th>
					<th>CLOSE
						<input type="button" class="orderby"
						value="${(order.close == null || order.close == 0)?('-'):((order.close == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.close" value="${order.close}"></th>
					<th>ADJCLOSE
						<input type="button" class="orderby"
						value="${(order.adjClose == null || order.adjClose == 0)?('-'):((order.adjClose == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.adjClose" value="${order.adjClose}"></th></th>
					<th>PRICECHANGES
						<input type="button" class="orderby"
						value="${(order.priceChanges == null || order.priceChanges == 0)?('-'):((order.priceChanges == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.priceChanges" value="${order.priceChanges}"></th>
					<th>HIGHTIMEID
						<input type="button" class="orderby"
						value="${(order.highTimeId == null || order.highTimeId == 0)?('-'):((order.highTimeId == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.highTimeId" value="${order.highTimeId}"></th>
					<th>LOWTIMEID
						<input type="button" class="orderby"
						value="${(order.lowTimeId == null || order.lowTimeId == 0)?('-'):((order.lowTimeId == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.lowTimeId" value="${order.lowTimeId}"></th>
					<th>AMPLITUDE
						<input type="button" class="orderby"
						value="${(order.amplitude == null || order.amplitude == 0)?('-'):((order.amplitude == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.amplitude" value="${order.amplitude}"></th>
					<th>HIGHPER
						<input type="button" class="orderby"
						value="${(order.highPer == null || order.highPer == 0)?('-'):((order.highPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.highPer" value="${order.highPer}"></th>
					<th>LOWPER
						<input type="button" class="orderby"
						value="${(order.lowPer == null || order.lowPer == 0)?('-'):((order.lowPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.lowPer" value="${order.lowPer}"></th>
					<th>CLOSEPER
						<input type="button" class="orderby"
						value="${(order.closePer == null || order.closePer == 0)?('-'):((order.closePer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.closePer" value="${order.closePer}"></th>
					<th>OPENPER
						<input type="button" class="orderby"
						value="${(order.openPer == null || order.openPer == 0)?('-'):((order.openPer == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.openPer" value="${order.openPer}"></th>
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
						
						<c:if test="${hiddens.open == null || hiddens.open == 0}">
						<td><div>${st.open }</div></td>
						</c:if>
						
						<td><div>${st.high}</div></td>
						<td><div>${st.low}</div></td>
						<td><div>${st.close}</div></td>
						<td><div>${st.adjClose}</div></td>
						<td><div>${st.priceChanges}</div></td>
						<td><div>${st.highTimeId}</div></td>
						<td><div>${st.lowTimeId}</div></td>
						<td><div>${st.amplitude}</div></td>
						<td><div>${st.highPer}</div></td>
						<td><div>${st.lowPer}</div></td>
						<td><div>${st.closePer}</div></td>
						<td><div>${st.openPer}</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>
<script type="text/javascript">
	
	function hiddenhandler (el) {
		
	}
	
	function orderby (el) {
		var v = $(el).val();
		var hiddenEl = $($(el).next()[0]);
		
		$("#list-main-table").find("thead").find("input:hidden").val(0);
		
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
	
	$(document).ready(function() {
		$("#prev-timeid").click(function(){
			$("#timeid").val($(this).attr("v"));
			$("#frm").submit();
		});
		
		$("#next-timeid").click(function(){
			$("#timeid").val($(this).attr("v"));
			$("#frm").submit();
		});
		
		
		$("#list-all-check").click(function(){
			if ($(this).attr("checked")) {
				$("#list-main-table").find("tbody").find("input:checkbox").attr("checked", true);
			} else {
				$("#list-main-table").find("tbody").find("input:checkbox").attr("checked", false);
			}
		});
		
	});
</script>
</body>
</html>