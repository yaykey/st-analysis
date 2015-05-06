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
					<!-- <th>STOCK</th> -->
					<th>DATE</th>
					<th>DateDec</th>
					<th>SUMDATE</th>
					<th>open</th>
					<th>close</th>
					<th>high</th>
					<th>low</th>
					<th>HIGHTIMEID</th>
					<th>LOWTIMEID</th>
					<th>HIGHPER</th>
					<th>LOWPER</th>
					<th>MA5</th>
					<th>MA10</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.list}" var="st">
					<tr class="${(st.highPer>5)?('high'):('')} ${(st.lowPer<-5)?('low'):('')}">
						<%-- <td>
							${st.stock}
						</td> --%>
						<td>
							<div><fmt:formatDate value="${st.date}" pattern="yyyy-MM-dd" /></div>
						</td>
						<td class="diff">
							${st.dateDec}
						</td>
						<td class="SUM-DATE">
						</td>
						<td>
							${st.open}
						</td>
						<td>
							${st.close}
						</td>
						<td>
							${st.high}
						</td>
						<td>
							${st.low}
						</td>
						<td>
							${st.highTimeId}
						</td>
						<td>
							${st.lowTimeId}
						</td>
						<td>
							${st.highPer}
						</td>
						<td>
							${st.lowPer}
						</td>
						<td>
							<fmt:formatNumber value="${st.MA5}" pattern="#.#####"/>
						</td>
						<td>
							<fmt:formatNumber value="${st.MA10}" pattern="#.#####"/>
						</td>						
					</tr>
				</c:forEach>
			</tbody>
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