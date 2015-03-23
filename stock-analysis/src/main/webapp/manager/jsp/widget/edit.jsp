<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑Widget信息</title>
<%@ include file="/manager/commons/meta.jsp"%>
<script type="text/javascript" src="${base}/js/DateTimeCalendar.js"></script>

<style type="text/css">

	#content table td{
		white-space:nowrap;
	}
</style>

</head>

<body>

	<form name="editWidget" id="editWidget"
		action="${baseManager}/widget/${empty widgetInfo ? 'add' : 'modify'}.${actionExt}?widgetName=${widgetName}"
		method="POST">
		<div class="title-bar-top">编辑Widget信息</div>
		<input type="hidden" name="wid"
			value="${empty widgetInfo ? '': widgetInfo.id}" id="wid" />
		<%-- <input type="hidden" name="functionId"
			value="${empty widgetInfo ? '': widgetInfo.id}" id="functionId" /> --%>
		
		<div id="content">
			<table class="pop-window" align="center" width="70%">
				<tr>
					<td class="td-right"><font color="red">*</font>功能名称:</td>
					<td><input type="text" name="name" id="name" class="ipt_txt"
						maxlength="255"
						value="${empty widgetInfo ? '':widgetInfo.name}" /></td>
				</tr>
				<tr>
					<td class="td-right">功能URL :</td>
					<td colspan="3">
						<input type="text" name="actionURL" id="actionURL"
						class="ipt_txt" maxlength="1000" style="width:100%"
						value="${empty widgetInfo ? '':widgetInfo.actionUrl}" />
					</td>
				</tr>

				<tr>
					<td class="td-right"><font color="red">*</font>所属报表:</td>
					<td>
						<select name="functionId" id="functionId" size="1">
							<c:forEach items="${requestScope.moduleList}" var="module">
								<c:forEach items="${module.functions}" var="function">
									<c:choose>
										<c:when test="${function.functionType eq 21}">
											<option value="${function.id}"
											${function.id eq widgetInfo.functionId ? 'selected="selected"' : ''}>${module.name}.${function.name}</option>
										</c:when>										
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:forEach>							
						</select>
					</td>
					<td class="td-right">
						<font color="red">*</font>排序:</td>
					<td>
						<input type="text" name="sort" id="sort" class="ipt_txt"
							maxlength="5" value="${empty widgetInfo ? '':widgetInfo.sort}" />
					</td>
				</tr>
				<tr>
					<td class="td-right"><font color="red">*</font>级别:</td>
					<td>
						<select id="level" name="level">
							<option value="0">请选择</option>
							<option value="1" ${widgetInfo.level==1 ? "selected":""}>1</option>
							<option value="2" ${widgetInfo.level==2 ? "selected":""}>2</option>
							<option value="3" ${widgetInfo.level==3 ? "selected":""}>3</option>
							<option value="4" ${widgetInfo.level==4 ? "selected":""}>4</option>
							
						</select>
					</td>
					<td class="td-right"><font color="red">*</font>父节点:</td>
					<td>
						<select name="parentWidgetId" id="parentWidgetId" size="1">
							<option value="">请选择</option>
							<c:if test="${widgetInfo.level == 1||widgetInfo.level == 0}">
								<option value="0" selected="selected">0</option>
							</c:if>
							<c:forEach items="${requestScope.widgetList}" var="widget">
								<option value="${widget.id}" ${widget.id eq
									widgetInfo.parentId ? 'selected="selected"' : ''}>${widget.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td-right">功能类型:</td>
					<td>
						<select name="widgetType" id="widgetType" size="1">
							<option value="1"
								${widgetInfo.widgetType eq 1 ? 'selected="selected"' : ''}>1</option>
							<option value="2"
								${widgetInfo.widgetType eq 2 ? 'selected="selected"' : ''}>2</option>
							<option value="3"
								${widgetInfo.widgetType eq 3 ? 'selected="selected"' : ''}>3</option>
					</select>
					</td>
				</tr>
			</table>
		</div>
		<div align="center">

			<input type="button" name="btn_submit" id="btn_submit" value="提交"  class="ui-button ui-widget ui-state-default ui-corner-all" />
			<input type="button" name="btn_return" id="btn_return" value="返回"  class="ui-button ui-widget ui-state-default ui-corner-all" />
		</div>
	</form>

	<script type="text/javascript">
		$(document).ready(
			function() {
				$('#btn_submit').bind('click', check);
				$('#btn_return').bind('click',function() {
					var fid= $("#functionId").val();
					window.location.href = '${baseManager}/widget/list_widget.${actionExt}?functionId=' + fid;
				});
				
				$('#level').change(function(){
					var level = $(this).val();
					if(level==0){
						$("#parentWidgetId").empty();
						$("#parentWidgetId").append("<option value='0'>请选择</option>");
					}else if(level==1){
						$("#parentWidgetId").empty();
						$("#parentWidgetId").append("<option value='0'>0</option>");
					}else{
						$.ajax({
							type:"post",
							data:"level=" + (level-1) ,
							url:"${base}/manager/widget/parentWidget.${actionExt}",
							dataType: 'json',
							success:function(msg){
								var data = eval(msg);
								$("#parentWidgetId").empty();
								for(var i=0 ; i<data.length ; i++){
									var id = data[i].id;
									var name = data[i].name;
									var str = "<option value='" + id + "' ${"+ id +" eq moduleInfoBean.parentId ? 'selected' : ''} >" + name + "</option>";
									$("#parentWidgetId").append(str);
								}
							}
						});
					}
				});
			});

		function check() {
			var name = editWidget.name;
			var sort = editWidget.sort;
			var level = editWidget.level;
			var parentWidgetId = editWidget.parentWidgetId;
			var regExp = new RegExp("[^0-9]", "i");
			var pat = new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5\+]", "i");

			if ($.trim(name.value) == '') {
				alert("功能名称不能为空");
				name.focus();
				return;
			} else {
				if (name.value.length > 255) {
					alert("功能名称字符数不能大于255");
					name.focus();
					return;
				}
			}

			if ($.trim(sort.value) == '') {
				alert("排序字段不能为空");
				sort.focus();
				return;
			}
			if (regExp.test(sort.value) == true) {
				alert("排序字段只能由数字组成");
				sort.focus();
				return;
			}
			if (sort.value > 32767) {
				alert("排序字段数值不能大于32767");
				sort.focus();
				return;
			}
			if (level.value == '0') {
				alert("请选择级别");
				level.focus();
				return;
			}
			if (parentWidgetId.value == '') {
				alert("请选择父节点");
				parentWidgetId.focus();
				return;
			}

			editWidget.submit();
		}
	</script>
</body>
</html>