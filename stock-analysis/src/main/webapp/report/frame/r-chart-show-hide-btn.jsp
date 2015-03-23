<%@ page language="java"  pageEncoding="UTF-8"%>

<style type="text/css">

.data_box .close,.data_box .open {
	width: 57px;
	height: 24px;
	float: right;
	margin-right: 0px;
}

.data_box .close {
	background: url('${base}/report/jsp/css/images/close.png') no-repeat right;
	
}

.data_box .open {
	background: url('${base}/report/jsp/css/images/open.png') no-repeat right;	
}
</style>

<div  id="close-${widgetId}" class="close"></div>
<div  id="open-${widgetId}" class="open" style="display:none;"></div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#close-${widgetId}").click(function(){
			$("#chart-${widgetId}").hide(300);
			//$("#vf_chart_toolbar").hide();
			
			
			$(this).siblings(".toolbar").hide();
			
			$(this).hide();
			$("#open-${widgetId}").show();
			
			
		});
		
		$("#open-${widgetId}").click(function(){
			//$("#vf_chart_toolbar").show();
			$("#chart-${widgetId}").show(300);
			
			$(this).siblings(".toolbar").show();
			
			$(this).hide();
			$("#close-${widgetId}").show();
		});
	});
</script>