<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
	</head>
<body>
		<div class="section">
			<div class="section-title">时段分析:</div>
			<div id="ControlBarWrapper" class="control-bar-wrapper">
  					 <div id="ControlBar" class="control-bar clearfix bg-iframe">
  					 	<div class="l date-select-bar">
  					 		<select id="u80">
								<option value="指标：新增用户" selected="">指标：新增用户</option>
								<option value="指标：活跃用户">指标：活跃用户</option>
								<option value="指标：启动次数">指标：启动次数</option>
							</select>
  					 	</div>
       				<div id="DateSelectBar" class="r date-select-bar">
                        <a class="cur" href="#0">前一日对比</a>
                        <a class="" href="#-1">上周同期对比</a>
                        <a class="" href="#-6">上月同期对比</a>
                       </div>
                    </div>
			</div>
			<div id="container_timeperiod" style="height: 300px;"></div>
		</div>
	</body>	
 <script type="text/javascript">
	$(document).ready(function() {
		$('#u80').bind("change", function(){
			
			alert("111");
			
		});
	});
 
 
 
	 $(function () {
	    $('#container_timeperiod').highcharts({
	    	
				colors: ['#2f7ed8', '#8bbc21', '#910000', '#1aadce', '#921AFF',
				         '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a'],
	            title: {
	                text: '',
	                x: -20 //center
	            },
	            subtitle: {
	                text: '',
	                x: -20
	            },
	            xAxis: {
	                categories: ['1', '2', '3', '4', '5', '6',
	                    '7', '8', '9', '10', '11', '12']
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
	            
		        credits: {
		        	enabled: false
		        },
	            /* legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'middle',
	                borderWidth: 0
	            }, */
	            series: [{
	                name: 'Tokyo',
	                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	            }, {
	                name: 'New York',
	                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
	            }, {
	                name: 'Berlin',
	                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
	            }, {
	                name: 'London',
	                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
	            }]
	    	});
	});
</script> 
</html>