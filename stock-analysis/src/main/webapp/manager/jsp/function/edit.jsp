<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑功能信息</title>
<%@ include file="/manager/commons/meta.jsp"%>
</head>
<body>

	<form name="editFunction" id="editFunction"
		action="${baseManager}/function/${empty functionInfo ? 'add' : 'modify'}.${actionExt}?functionName=${functionName}"
		method="POST">
		<div class="title-bar-top">编辑功能信息</div>
		<input type="hidden" name="uid"
			value="${empty functionInfo ? '': functionInfo.id}" id="uid" />
		<div id="content">
			<table class="pop-window" align="center" width="70%">
				<tr>
					<td class="td-right"><font color="red">*</font>功能名称:</td>
					<td><input type="text" name="name" id="name" class="ipt_txt"
						maxlength="255"
						value="${empty functionInfo ? '':functionInfo.name}" /></td>

					<td class="td-right">功能URL :</td>
					<td><input type="text" name="actionURL" id="actionURL"
						class="ipt_txt" maxlength="1000"
						value="${empty functionInfo ? '':functionInfo.actionUrl}" /></td>
				</tr>

				<tr>
					<td class="td-right">读取缓存:</td>
					<td><select name="readCache" id="readCache" size="1">
							<option value="">请选择</option>
							<option value="0"
								${functionInfo.readCache eq 0 ? 'selected="selected"' : ''}>是</option>
							<option value="1"
								${(functionInfo.readCache == null || functionInfo.readCache eq 1) ? 'selected="selected"' : ''}>否</option>
					</select></td>
					<td class="td-right">是否上线:</td>
					<td><select name="onlineStatus" id="onlineStatus" size="1">
							<option value="">请选择</option>
							<option value="1"
								${(functionInfo.onlineStatus == null || functionInfo.onlineStatus eq 1) ? 'selected="selected"' : ''}>是</option>
							<option value="0"
								${functionInfo.onlineStatus eq 0 ? 'selected="selected"' : ''}>否</option>
					</select></td>
				</tr>
				<tr>
					<td class="td-right">是否隐藏:</td>					
					<td>
						<select name="isHide" id="isHide" size="1">
							<option value="">请选择</option>
							<option value="true"
								${functionInfo.isHide eq true ? 'selected="selected"' : ''}>是</option>
							<option value="false"
								${(functionInfo.isHide == null || functionInfo.isHide eq false) ? ('selected="selected"') : ('')}>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td-right"><font color="red">*</font>所属模块:</td>
					<td><select name="moduleId" id="moduleId" size="1">
							<option value="0">请选择</option>
							<c:forEach items="${requestScope.moduleList}" var="module">
								<option value="${module.id}"
									${module.id eq functionInfo.moduleId ? 'selected="selected"' : ''}>${module.name}</option>
							</c:forEach>
					</select></td>
					<td class="td-right"><font color="red">*</font>排序:</td>
					<td><input type="text" name="sort" id="sort" class="ipt_txt"
						maxlength="5" value="${empty functionInfo ? '':functionInfo.sort}" />
					</td>
				</tr>
				<tr>
					<td class="td-right">功能类型:</td>
					<td>
						<select name="functionType" id="functionType" size="1" onchange="show();">
							<option value="">请选择</option>
							<option value="1"
								${functionInfo.functionType eq 1 ? 'selected="selected"' : ''}>后台</option>
							<option value="2"
								${functionInfo.functionType eq 2 ? 'selected="selected"' : ''}>前台</option>
							<option value="21"
								${functionInfo.functionType eq 21 ? 'selected="selected"' : ''}>前台报表</option>
							<option value="22"
								${functionInfo.functionType eq 22 ? 'selected="selected"' : ''}>首页报表</option>
							<option value="23"
								${functionInfo.functionType eq 23 ? 'selected="selected"' : ''}>自定义报表</option>
						</select>
					</td>
				</tr>
				
				<tr id="test1">
					<td class="td-right">报表列数:</td>
					<td>
						<select name="cols" id="cols" size="1">
						<option value="">请选择</option>
						<option value="1"
							${(functionExtend != null && functionExtend.cols eq 1 )? 'selected="selected"' : ''}>1</option>
						<option value="2"
							${(functionExtend != null && functionExtend.cols eq 2 )? 'selected="selected"' : ''}>2</option>							
					</select>
					</td>
				</tr>
					
					<tr><td>&nbsp;</td></tr>
					<tr id="test2">
						<td class="td-right" colspan="1">父子关系:</td>
					</tr>
					<tr id="test3">
						<td class="td-right">叶子节点:</td>
						<td>
							<select name="isLeaf" id="isLeaf" size="1">
							<option value="">请选择</option>
							<option value="0"
								${(functionReportExtend != null && functionReportExtend.isLeaf eq 0 )? 'selected="selected"' : ''}>否</option>
							<option value="1"
								${(functionReportExtend != null && functionReportExtend.isLeaf eq 1 )? 'selected="selected"' : ''}>是</option>							
							</select>
						</td>
					</tr>
					<tr id="test4">
						<td class="td-right">父节点:</td>
						<td>
							<select name="pid" id="pid" size="1">
								<option value="">请选择</option>
								<c:forEach items="${requestScope.functionList}" var="function">
									<option value="${function.id}" 
										${(functionReportExtend.parentId eq function.id) ? ' selected="selected" ' : ''}
									>${function.name}</option>
								</c:forEach>				
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
		$(document)
				.ready(
						function() {
							$("#test1").hide();
							$("#test2").hide();
							$("#test3").hide();
							$("#test4").hide();
							var value = $("#functionType").val();
							if(value == 21){
								$("#test2").show();
								$("#test3").show();
								$("#test4").show();
							}
							if(value == 22){
								$("#test1").show();
							}
							
							$('#btn_submit').bind('click', check);
							$('#btn_return')
									.bind(
											'click',
											function() {
												window.location.href = '${baseManager}/function/list.${actionExt}?functionName=${empty requestScope.functionName? '':requestScope.functionName}';
											});
						});

		function check() {

			var name = editFunction.name;
			var moduleId = editFunction.moduleId;
			var sort = editFunction.sort;
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

			if (moduleId.value == '0') {
				alert("请选择所属模块");
				moduleId.focus();
				return;
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

			editFunction.submit();
		}
		function show(){
			var value = $("#functionType").val();
			if(value == 22){
				$("#test2").hide();
				$("#test3").hide();
				$("#test4").hide();
				$("#test1").show();
			}else if(value == 21){
				$("#test1").hide();
				$("#test2").show();
				$("#test3").show();
				$("#test4").show();
			}else{
				$("#test1").hide();
				$("#test2").hide();
				$("#test3").hide();
				$("#test4").hide();
			}
		}
	</script>
</body>
</html>