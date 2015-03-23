<!DOCTYPE HTML>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/commons/jquerylibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<!-- <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script> -->

		<script type="text/javascript">
		
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'spline'
            },
            //title: {},
            title: {
                text: ' '
            },
            //subtitle: {
            //    text: 'Irregular time data in Highcharts JS'
            //},
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: { // don't display the dummy year
                    month: '%e. %b',
                    year: '%b'
                },
                //title: {
                //    text: 'Date'
                //}
                title: {
                    text: ''
                }
            },
            yAxis: {
                title: {
                    text: 'Snow depth (m)'
                }
            	//, min: 0
            },
            tooltip: {
                /* headerFormat: '<b>{series.name}</b><br>',
                pointFormat: '{point.x:%e. %b}: {point.y:.2f} m' */
            	headerFormat: '<b>{point.y:.2f}</b><br>',
                pointFormat: '{point.x: %H:%M:%S}'
            },

            series: [
				{
				    name: 'Winter',
				    // Define the data points. All series have a dummy year
				    // of 1970/71 in order to be compared on the same x axis. Note
				    // that in JavaScript, months start at 0 for January, 1 for February etc.
				    data: eval("${jsonData}")
				},
				{
                name: 'Winter Optimize 2',
                // Define the data points. All series have a dummy year
                // of 1970/71 in order to be compared on the same x axis. Note
                // that in JavaScript, months start at 0 for January, 1 for February etc.
                data: eval("${jsonData2}")
				},
				//{
	            //    name: 'Winter Optimize 3',
	            //    // Define the data points. All series have a dummy year
	            //    // of 1970/71 in order to be compared on the same x axis. Note
	            //    // that in JavaScript, months start at 0 for January, 1 for February etc.
	            //    data: eval("${jsonData3}")
				//},
				{
	                name: 'Winter Optimize 4',
	                // Define the data points. All series have a dummy year
	                // of 1970/71 in order to be compared on the same x axis. Note
	                // that in JavaScript, months start at 0 for January, 1 for February etc.
	                data: eval("${jsonData4}")
				}
            ]
        });
    });
    

		</script>
	</head>
	<body>
<script src="${base }/js/Highcharts-4.0.3/js/highcharts.src.js"></script>
<script src="${base }/js/Highcharts-4.0.3/js/modules/exporting.js"></script>

<div id="container" style="min-width: 1000px; height: 500px; margin: 0 auto"></div>

<script type="text/javascript">
$(function () {
	var legend = $("#container .highcharts-legend");
	
	$(window.document).scroll(function () {
		scrollLeftHandler();
    });
	
	function scrollLeftHandler () {
		var scrollLeft = $(document).scrollLeft();
	       
        console.info("scrollLeft=" + scrollLeft);
        
        //transform=translate(1742,562)
        
        legend.attr("transform", "translate(" + (500 + scrollLeft) + ",562)");
	}
	scrollLeftHandler();
});
</script>
	</body>
</html>
