<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<html>
<head>

</head>
<style type="text/css">
.orderby {
	width: 20px;
}

.list-main {
	min-width: 950px;
}

table#list-main-table {
	border : 1px solid #5c5c5c;
}

.list-main .high {
	background-color: darksalmon;
}

.list-main .low {
	background-color: lightskyblue;
}

.list-main td.sub-high-per {
	color: darksalmon;
}

.list-main td.sub-low-per {
	color: lightskyblue;
}

.list-main td.close-per {
	background-color: darkolivegreen;
}

.aw-toolbar {
	
}

thead.float-thead {
	padding : 0px;
	margin : 0px;
}

thead.float-thead th,
thead.float-thead td {
	padding : 0px;
	margin : 0px;
}
thead.float-thead tr {
	background: none repeat scroll 0 0 #f5f5f5;
	display: table;
	position: fixed;
	top: 0px;
	width__: 100%;
}

.list-main .thead-stock,
.list-main .tbody-stock {
	width: 66px;
}

.list-main .thead-date,
.list-main .tbody-date {
	width: 82px;
}

.list-main .thead-datedec,
.list-main .tbody-datedec {
	width: 65px;
}

.list-main .thead-sumdate,
.list-main .tbody-sumdate {
	width: 78px;
}

.list-main .thead-open,
.list-main .tbody-open {
	width: 57px;
}

.list-main .thead-close,
.list-main .tbody-close {
	width: 57px;
}

.list-main .thead-high,
.list-main .tbody-high {
	width: 57px;
}

.list-main .thead-low,
.list-main .tbody-low {
	width: 57px;
}

.list-main .thead-hightimeid,
.list-main .tbody-hightimeid {
	width: 82px;
}

.list-main .thead-lowtimeid,
.list-main .tbody-lowtimeid {
	width: 82px;
}

.list-main .thead-highper,
.list-main .tbody-highper {
	width: 60px;
}

.list-main .thead-lowper,
.list-main .tbody-lowper {
	width: 60px;
}

.list-main .thead-openper,
.list-main .tbody-openper {
	width: 64px;
}

.list-main .thead-closeper,
.list-main .tbody-closeper {
	width: 66px;
}

.list-main .thead-ma5,
.list-main .tbody-ma5 {
	width: 60px;
}

.list-main .thead-ma10,
.list-main .tbody-ma10 {
	width: 60px;
}

.list-main .thead-amplitude,
.list-main .tbody-amplitude {
	width: 82px;
}

.list-main #list-main-table th,
.list-main #list-main-table td,
.list-main .sub-table th,
.list-main .sub-table td {
	border : 1px solid #aaaaaa;
}


</style>

<body style="background-color: #FCFAF3">
<form id="frm" action="${base }/analysis/aw.feinno" method="POST">
	<div class="aw-toolbar">
		<table border="1" cellpadding="1" cellspacing="1">
			<tr>
				<td>
					begin
					<input type="text" name="startDate" 
						value="${(startDate == null)?(''):(startDate)}">
				</td>
				<td>
					end
					<input type="text" name="endDate" 
						value="${(endDate == null)?(''):(endDate)}">
				</td>
				<td>
					<input type="submit" value="GO">
				</td>
			</tr>
		</table>
	</div>
	
	<%@ include file="/analysis/wave/info.jsp"%>
	
	<div id="list-main" class="list-main">
		<table id="list-main-table" border="0"
				cellpadding="0" cellspacing="0" width__="100%">
			<thead class="list-thead">
				<tr>
					<th class="thead-stock">STOCK</th>
					<th class="thead-date">DATE</th>
					<!-- <th class="thead-datedec">datedec</th>
					<th class="thead-sumdate">sumdate</th> -->
					<th class="thead-open">open</th>
					<th class="thead-close">close</th>
					<th class="thead-high">high</th>
					<th class="thead-low">low</th>
					<th class="thead-hightimeid">hightimeid</th>
					<th class="thead-lowtimeid">lowtimeid</th>
					<th class="thead-highper">highper</th>
					<th class="thead-lowper">lowper</th>
					<th class="thead-openper">openper</th>
					<th class="thead-closeper">closeper</th>
					<!-- <th class="thead-ma5">MA5</th>
					<th class="thead-ma10">MA10</th> -->
					<th class="thead-amplitude">amplitude</th>
					
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
		
		var position = $("#list-main-table").position();		
		/* $("#list-main-table").find("thead.list-thead")
			.css("top", position.top); */
		
		$(window).scroll( function() {
			//console.log($(window).scrollTop());
			var scrollTop = $(window).scrollTop();
			if (position.top < scrollTop) {
				$("#list-main-table").find("thead.list-thead").addClass("float-thead");				
			} else {
				$("#list-main-table").find("thead.list-thead").removeClass("float-thead");
			}
		});
	});
</script>
</body>
</html>