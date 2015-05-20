<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<%@ include file="/commons/highlibs.jsp"%>
<%@ page import="com.st.Global"%>

<html>
<head>

</head>
<style type="text/css">
	.orderby {
		width : 20px;
	}
	
	.list-select {
	
	}
	
	.list-select td{
		background-color: #ddd;
	}
</style>

<body style="background-color: #FCFAF3">

<div id="container" style="height: 400px; min-width: 310px"></div>

<form id="frm" action="${base }/analysis/selectlist.feinno" method="GET">
	<div id="search-bar">
		
		<table>
			<input type="hidden" name="stock" value="${stock}">
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
				<tr>
					<%-- <th>STOCK						
						<input type="button" class="orderby"
						value="${(order.stock == null || order.stock == 0)?('-'):((order.stock == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.stock" value="${order.stock}">
					</th> --%>
					<th>
					DATE
					<input type="button" class="orderby"
						value="${(order.date == null || order.date == 0)?('-'):((order.date == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.date" value="${order.date}">
					</th>
					<th>OPEN
						<input type="button" class="orderby"
						value="${(order.open == null || order.open == 0)?('-'):((order.open == 1)?('↑'):('↓'))}"
						onclick="orderby(this);"
						>
						<input type="hidden" name="order.open" value="${order.open}">
					</th>
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
						<%-- <td><div>${st.stock }</div></td> --%>
						<td><div class="list-date"><fmt:formatDate value="${st.date}" pattern="yyyy-MM-dd" /></div></td>
						<td><div>${st.open }</div></td>
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
	
	function orderby (el) {
		var v = $(el).val();
		var hiddenEl = $(el).next();
		
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
		
		$("#list-main-table").find("tbody").find(".list-date").each(function(){
			var selectTimeId = $(this).text();
			
			if (selectTimeId == "${timeid}") {
				$($(this).parents("tr")[0]).addClass("list-select");
			}
		});
	});
</script>
<script type="text/javascript">
$(function () {
    var seriesOptions = [],
        seriesCounter = 0,
        names = ['MSFT', 'AAPL', 'GOOG'],
        // create the chart when all data is loaded
        createChart = function () {

            $('#container').highcharts('StockChart', {

                rangeSelector: {
                    selected: 4
                },

                yAxis: {
                    labels: {
                        formatter: function () {
                            return (this.value > 0 ? ' + ' : '') + this.value + '%';
                        }
                    },
                    plotLines: [{
                        value: 0,
                        width: 2,
                        color: 'silver'
                    }]
                },

                plotOptions: {
                    series: {
                        compare: 'percent'
                    }
                },

                tooltip: {
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
                    valueDecimals: 2
                },

                series: seriesOptions
            });
        };

    $.each(names, function (i, name) {

        $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=' + name.toLowerCase() + '-c.json&callback=?',    function (data) {

            seriesOptions[i] = {
                name: name,
                data: data
            };

            // As we're loading the data asynchronously, we don't know what order it will arrive. So
            // we keep a counter and create the chart when all the data is loaded.
            seriesCounter += 1;

            if (seriesCounter === names.length) {
                createChart();
            }
        });
    });
});
		</script>
</body>
</html>