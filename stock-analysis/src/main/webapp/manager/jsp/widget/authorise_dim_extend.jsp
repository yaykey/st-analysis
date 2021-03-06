<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>给维度分配指标</title>
    <%@include file="/manager/commons/meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script type="text/javascript" src="${base}/js/DateTimeCalendar.js"></script>
<script>

    $(function(){
        $("#role-save").button()
            .click(function(event){
            submitbutton();
        })
        $("#role-cancel").button()
            .click(function(event){
            cancel();
        })
    	$('#btn_select').bind('click', function() {
    		var dt = new Date();
    		popWindowCenter('${base}/manager/widgetDim/getWidgetDimExtendTree.feinno?widgetDimId='+$("#widgetDimId").attr("value")+'&dt='+dt, '选择指标', 552, 532);
    	});
    })
    
    
    function cancel(){
		if(!confirm("确定取消吗?")){
			return false;
		}
		$("#addlist").attr("action","${base}/manager/widgetDim/list.feinno");
		$("#addlist").submit();
	}
	
	function submitbutton(){
		
		if(!confirm("确定提交吗?")){
			return false;
		}
		$("#addlist").attr("action","${base}/manager/widgetDim/addWidgetDimExtends.feinno");
		$("#addlist").submit();
	}
</script>
</head>
<body>
<form  method="post" name="addlist" id="addlist">
	<input type="hidden" name="widgetId" value="${requestScope.widgetId}" id="widgetId" />
	<input type="hidden" name="widgetDimId" value="${requestScope.widgetDimId}" id="widgetDimId" />
	<div class="title-bar-top">
	   给维度分配指标
	</div>
	<div  id="content" >
		<table class="pop-window" align="center" width="70%">
			<tr>
				<td class="td-right">
					维度名称:
				</td>
				<td nowrap  class="td-left">
					${empty requestScope.widgetDimName ? "": requestScope.widgetDimName}
				</td>
			</tr>
			<tr>
				<td class="td-right">
					所有指标值<br>
					<red>(1为选中，0为未选中)</red>:
				</td>
				<td nowrap class="td-left">
					<textarea name="dindexNames" id="dindexNames"
						cols="20" rows="10" readonly="readonly"><c:forEach  var="dimExtend" items="${requestScope.dimExtendList}">${dimExtend.indexName}_${dimExtend.isSelected}<%out.print("\n");%></c:forEach></textarea>
					<input type="hidden" name="dindexIds"
						id="dindexIds"
						value="<c:forEach  var="dimExtend" items="${requestScope.dimExtendList}">${dimExtend.id}.${dimExtend.indexId}.${dimExtend.isSelected},</c:forEach>" />
					<input type="button" id="btn_select"	value="分配指标">
				</td>
			</tr>
			
		</table>
	</div>
	<div align="center">
		<input type="button" value="提交" id="role-save" name="role-save"/>
		<input type="button" value="取消" id="role-cancel" name="role-cancel"/>
	</div>		
</form>
</body>
</html>