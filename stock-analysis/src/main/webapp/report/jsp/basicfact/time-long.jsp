<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%-- <%@ include file="/commons/jquerylibs.jsp"%> --%>
<html>
	<head>
		<%-- <script type="text/javascript" src="${base}/report/js/highchart/highcharts.js"></script> --%>
	</head>
	<body>
		<div class="area-list-container l" style="wigth:49%; margin-left:10px; margin-top: 20px;">
			<div class="section-title">使用概况 - 使用时长趋势</div>
			<div id="ControlBarWrapper" class="control-bar-wrapper">
				<div id="ControlBar" class="control-bar clearfix bg-iframe">
					<div class="l date-select-bar">
						<select id="u178">
							<option value="指标：新增用户" selected="">指标：人均使用时长</option>
							<option value="指标：活跃用户">指标：次均使用时长</option>
						</select>
					</div>
					<div id="DateSelectBar" class="r date-select-bar">
	                      <a class="cur" href="#0">过去7天</a>
	                      <a class="" href="#-1">过去30天</a>
	                 </div>
                 </div>
			</div>
			<div></div>
			<div id="container_timelong" style="height: 300px; margin: 0 auto"></div>
		</div>
	</body>
 <script type="text/javascript">
	$(function () {
	    $('#container_timelong').highcharts({
	        title: {
	            text: '',
	            x: -20
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },  
	        xAxis: {
	        	 categories: ['1', '2', '3', '4', '5', '6','7', '8', '9', '10', '11', '12' ]
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	      /*  legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        }, */
	        credits: {
	        	enabled: false
	           
	        },
	        series: [{
	            name: '人均使用时长',
	            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	        }/* , {
	            name: 'New York',
	            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
	        }, {
	            name: 'Berlin',
	            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
	        },  {
	            name: 'London',
	            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
	        } */]
	    }); 
	});
</script> 
</html>