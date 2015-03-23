<%@ page language="java"  pageEncoding="UTF-8"%>
<div id="chart-${widgetId}" class="chart_cont" >
<script type="text/javascript">			
		var strCsv = '';
		var csv = "${csv}";
		var xml = "${xml}";
	    for (var i = 0; i < csv.split("##").length; i++) {
	        strCsv = strCsv +csv.split("##")[i] + "\n";
	    }
	    
		if(csv!='' &&　csv!=null && xml!='' &&　xml!=null){
     		loadAmCharts({ 
     			nosum : '0',
				width  : "100%",
				height : '250',	
				color  : "#FFFFFF",
				index  : '${widgetId}',
				xml    : xml,
				xml_type : '1',  // 0:本地，   1:读的数据流
				csv    : strCsv,
				csv_type : '1',
				swf :  "${base}"+"${swf}",
				path : "${base}"+"${path}"
			});
		}else{
			loadAmCharts({
				nosum : '1',
				index : '${widgetId}'
			});
		}
	</script>			
</div>