<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>给功能新增维度信息</title>
    <%@include file="/manager/commons/meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<%-- <script type="text/javascript" src="${base}/js/DateTimeCalendar.js"></script> --%>
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
    		var fid = $("#functionId").val();
    		popWindowCenter('${base}/manager/widgetDim/getDimTree.feinno?widgetId='+$("#widgetId").val() + '&functionId=' + fid +'&dt='+dt, '选择维度信息', 552, 522);
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
		$("#addlist").attr("action","${base}/manager/widgetDim/addDDims.feinno");
		$("#addlist").submit();
	}
</script>
</head>
<body>
<form  method="post" name="addlist" id="addlist">
	<input type="hidden" name="widgetId" value="${requestScope.widgetId}" id="widgetId" />
	<div class="title-bar-top">
	   新增维度信息
	</div>
	<div  id="content" >
		<table class="pop-window" align="center" width="70%">
			<tr>
				<td class="td-right">
					功能名称:
				</td>
				<td nowrap  class="td-left">
					${empty requestScope.widget ? "": requestScope.widget.name}
				</td>
			</tr>
			<tr>
				<td class="td-right">
					己选择维度信息:
				</td>
				<td nowrap class="td-left">
					<textarea name="ddimNames" id="ddimNames"
						cols="20" rows="10" readonly="readonly"><c:forEach  var="ddim" 
						items="${requestScope.widgetDimLinkBeanList}"
						>${ddim.dimName}_${ddim.isSelected}<%out.print("\n");%></c:forEach></textarea>
					<input type="hidden" name="ddimIds"
						id="ddimIds"
						value='<c:forEach var="ddim" 
						items="${requestScope.widgetDimLinkBeanList}">${ddim.id}.${ddim.dimTypeId}.${ddim.dimId}.${ddim.parentId},</c:forEach>' />
					<input type="hidden" name="is_multiple_choice"
						id="is_multiple_choice"
						value="" />
					<input type="button" id="btn_select"	value="分配维度">
				</td>
			</tr>
			<tr>
				<td class="td-right">
					是否选择:
				</td>
				<td nowrap  class="td-left">
					<input type="checkbox" value="1" id="isSelected" name="isSelected">
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