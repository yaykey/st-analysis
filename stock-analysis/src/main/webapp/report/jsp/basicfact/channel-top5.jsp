<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
	</head>
	<body>
		<div class="area-list-container l" style="wigth:49%; margin-top: 20px;">
			<div class="section-title">渠道效果概况 - 昨日top5渠道</div>
			<div id="ControlBarWrapper" class="control-bar-wrapper">
  					 <div id="ControlBar" class="control-bar clearfix bg-iframe">
  					 	<div class="l date-select-bar">
						<select id="u178">
						<option value="指标：新增用户" selected="">指标：新增用户</option>
						<option value="指标：活跃用户">指标：活跃用户</option>
						<option value="指标：启动次数">指标：启动次数</option>
						<option value="指标：累计启动用户数">指标：累计启动用户数</option>
						<option value="指标：单次使用时长">指标：单次使用时长</option>
						</select>
  					 	</div>
                    </div>
			</div>
			<div></div>
			<div id="container_channel" style=" height: 300px; margin: 0 auto"></div>
		</div>
	</body>
 <script type="text/javascript">
		$(function() {
			$('#container_channel').highcharts({
				chart: {
					type: 'bar'
				},
				colors: ['#2f7ed8', '#8bbc21', '#910000', '#1aadce', '#921AFF',
				         '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a'],
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					categories: ['机锋市场', '应用汇', '豌豆荚', '安卓市场', '百度'],
					title: {
						text: ''
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: '',
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					}
				},
				tooltip: {
					valueSuffix: ' millions'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				
				/* legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 100,
					floating: true,
					borderWidth: 1,
					backgroundColor: '#FFFFFF',
					shadow: true
				}, */
				credits: {
					enabled: false
				},
				series: [ {
					name: '前一日',
					data: [133, 156, 947, 408, 6]
				},  {
					name: '昨天',
					data: [973, 914, 4054, 732, 34]
				}]
			});
		});
</script>
</html>		