<!DOCTYPE HTML>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/commons/jquerylibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highstock Example</title>
<script src="${base }/js/Highstock-2.0.3/js/highstock.src.js"></script>
<script src="${base }/js/Highstock-2.0.3/js/modules/exporting.src.js"></script>
		<!-- <script type="text/javascript" src="../js/jquery/jquery-1.8.1.min.js"></script> -->
		<style type="text/css">
${demo.css}
		</style>
		<script type="text/javascript">
$(function() {
	
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	
	// Create the chart
	$('#container').highcharts('StockChart', {
		chart : {
			events : {
				load : function() {

					// set up the updating of the chart each second
					var series = this.series[0];
					/* setInterval(function() {
						var x = (new Date()).getTime(), // current time
						y = Math.round(Math.random() * 100);
						series.addPoint([x, y], true, true);
					}, 1000); */
				}
			}
		},
		
		rangeSelector: {
			buttons: [{
				//count: 1,
				type: 'all',
				text: '1d'
			},{
				count: 60,
				type: 'second',
				text: '1m'
			},{
				count: 1,
				type: 'minute',
				text: '1M'
			}, {
				count: 5,
				type: 'minute',
				text: '5M'
			}, {
				type: 'all',
				text: 'All'
			}],
			inputEnabled: false,
			selected: 0
		},
		
		title : {
			text : 'Live random data'
		},
		
		exporting: {
			enabled: false
		},
		tooltip : {
			//dateTimeLabelFormats : "second",
			xDateFormat : "%H:%M:%S",
			valueDecimals : 2
		},
		xAxis : {
			type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b',
                
            },
            title: {
                text: 'Date'
            }
			/* events : {
				afterSetExtremes : afterSetExtremes
			}, */
			//minRange: 3600 * 1000 // one hour
			,minRange: 60 * 1000
		},
		/* yAxis: {
	    	labels: {
	    		formatter: function() {
	    			return (this.value > 0 ? '+' : '') + this.value + '%';
	    		}
	    	},
	    	plotLines: [{
	    		value: 0,
	    		width: 2,
	    		color: 'silver'
	    	}]
	    }, */
		series : [{
			name : '',
			data : 
			    eval("${jsonData}")
			
			,
			shadow : true,
			type: 'spline',
			marker : {
				enabled : false
				,radius : 3
			}
			/* data : (function() {
				// generate an array of random data
				var data = [], time = (new Date()).getTime(), i;

				for( i = -999; i <= 0; i++) {
					data.push([
						time + i * 1000,
						Math.round(Math.random() * 100)
					]);
				}
				return data;
			})() */
		}]
	});

});

		</script>
	</head>
	<body>


<div id="container" style="height: 400px; min-width: 310px"></div>
	</body>
</html>
