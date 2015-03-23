<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑功能信息</title>
<%@ include file="/manager/commons/meta.jsp"%>
<script type="text/javascript">
	var optionOne = null;
	var optionTwo = null;
	<c:forEach items="${requestScope.moduleOneList}" var="moduleOne">
	optionOne = optionOne
			+ "<option value='${moduleOne.id}' ${moduleOne.id eq moduleInfoBean.parentId ? 'selected' : ''} >${moduleOne.name}</option>";
	</c:forEach>
	<c:forEach items="${requestScope.moduleTwoList}" var="moduleTwo">
	optionTwo = optionTwo
			+ "<option value='${moduleTwo.id}' ${moduleTwo.id eq moduleInfoBean.parentId ? 'selected' : ''} >${moduleTwo.name}</option>";
	</c:forEach>
	$(function() {
		$("#level").change(
				function() {
					$value = $(this).val();
					if ($value == '1') {
						$("#parentModuleId").empty();
						$("#parentModuleId").append(
								"<option value='0'>0</option>");
					} else if ($value == '2') {
						$("#parentModuleId").empty();
						$("#parentModuleId").append(
								"<option value=''>请选择</option>");
						$("#parentModuleId").append(optionOne);

					} else if ($value == '3') {
						$("#parentModuleId").empty();
						$("#parentModuleId").append(
								"<option value=''>请选择</option>");
						$("#parentModuleId").append(optionTwo);

					}
				});
	});
</script>
</head>
<body>

	<form name="editModule" id="editModule"
		action="${baseManager}/module/${empty moduleInfoBean.id ? 'add' : 'modify'}.${actionExt}?moduleName=${moduleName}"
		method="POST">
		<div class="title-bar-top">编辑模块信息</div>
		<input type="hidden" name="uid"
			value="${empty moduleInfoBean ? '': moduleInfoBean.id}" id="uid" />
		<input type="hidden" name="moduleId"
			value="${empty moduleInfoBean ? '': moduleInfoBean.id}" id="moduleId" />
		<div id="content">
			<table class="pop-window" align="center" width="70%">
				<tr>
					<td class="td-right"><font color="red">*</font>模块名称:</td>
					<td><input type="text" name="name" id="name" class="ipt_txt"
						maxlength="255"
						value="${empty moduleInfoBean ? '':moduleInfoBean.name}" />
					</td>

					<td class="td-right">模块URL:</td>
					<td><input type="text" name="actionURL" id="actionURL"
						class="ipt_txt" maxlength="255"
						value="${empty moduleInfoBean ? '':moduleInfoBean.actionUrl}" />
					</td>
				</tr>

				<tr>
					<td class="td-right">图片URL:</td>
					<td><input type="text" name="imgURL" id="imgURL"
						class="ipt_txt" maxlength="255"
						value="${empty moduleInfoBean ? '':moduleInfoBean.imgUrl}" />
					</td>
					<td class="td-right"><font color="red">*</font>模块级别:</td>
					<td><select id="level" name="level">
							<option value="0">请选择</option>
							<option value="1" ${moduleInfoBean.moduleLevel==1 ? "selected":""}>1</option>
							<option value="2" ${moduleInfoBean.moduleLevel==2 ? "selected":""}>2</option>
							<option value="3" ${moduleInfoBean.moduleLevel==3 ? "selected":""}>3</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="td-right"><font color="red">*</font>父节点:</td>
					<td><select name="parentModuleId" id="parentModuleId" size="1">
							<c:if test="${moduleInfoBean.moduleLevel == 1}">
								<option value="0">0</option>
							</c:if>
							<c:forEach items="${requestScope.moduleList}" var="module">
								<option value="${module.id}" ${module.id eq
									moduleInfoBean.parentId ? 'selected="selected"' : ''}>${module.name}</option>
							</c:forEach>
					</select>
					</td>
					<td class="td-right"><font color="red">*</font>模块类别:</td>
					<td><select name="viewType" id="viewType" size="1">
							<!-- <option value="">请选择</option> -->
							<option value="backType" ${moduleInfoBean.viewType=="backType" ? "selected":""}>后台</option>
							<option value="frontType" ${moduleInfoBean.viewType=='frontType' ? 'selected':''}>前台</option>
							<option value="frontReportType" ${moduleInfoBean.viewType=='frontReportType' ? 'selected':''}>报表</option>
							<option value="frontHomeReportType" ${moduleInfoBean.viewType=='frontHomeReportType' ? 'selected':''}>首页报表</option>
							<option value="userDefinedReportType" ${moduleInfoBean.viewType=='userDefinedReportType' ? 'selected':''}>自定义报表</option>
							<option value="userDefinedBackType" ${moduleInfoBean.viewType=='userDefinedBackType' ? 'selected':''}>自定后台类型</option>
					</select></td>
				</tr>
				<tr>
					<td class="td-right"><font color="red">*</font>排序:</td>
					<td><input type="text" name="sort" id="sort" class="ipt_txt"
						maxlength="6"
						value="${empty moduleInfoBean ? '':moduleInfoBean.sort}" />
					</td>
				</tr>
			</table>
		</div>
		<div align="center">

			<input type="button" name="btn_submit" id="btn_submit" value="提交"
				class="ui-button ui-widget ui-state-default ui-corner-all" /> <input
				type="button" name="btn_return" id="btn_return" value="返回"
				class="ui-button ui-widget ui-state-default ui-corner-all" />
		</div>
	</form>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#btn_submit').bind('click', check);
							$('#btn_return')
									.bind(
											'click',
											function() {
												window.location.href = '${baseManager}/module/list.${actionExt}?moduleName=${moduleName}';
											});
		<!--					showTip("${tip}");	-->
						});
		function check() {

			var name = editModule.name;
			var actionURL = editModule.actionURL;
			var imgURL = editModule.imgURL;
			var level = editModule.level;
			var parentModuleId = editModule.parentModuleId;
			var sort = editModule.sort;

			var regExp = new RegExp("[^0-9]", "i");

			if ($.trim(name.value) == '') {
				alert("模块名称不能为空");
				name.focus();
				return;
			} else {
				if (name.value.length > 255) {
					alert("模块名称字符数不能大于255");
					name.focus();
					return;
				}
			}
			/* 	if ($.trim(actionURL.value) == '') {
					alert("模块URL不能为空");
					actionURL.focus();
					return;
				} else { */
			if (actionURL.value.length > 255) {
				alert("模块URL字符数不能大于255");
				actionURL.focus();
				return;
			}
			//}
			/* if ($.trim(imgURL.value) == '') {
				alert("图片URL不能为空");
				imgURL.focus();
				return;
			} else { */
			if (imgURL.value.length > 255) {
				alert("图片URL字符数不能大于255");
				imgURL.focus();
				return;
			}
			//}
			if (level.value == '0') {
				alert("请选择模块级别");
				level.focus();
				return;
			}
			if (parentModuleId.value == '') {
				alert("请选择父节点");
				parentModuleId.focus();
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
			editModule.submit();
		}
	//-->
	</script>
</body>
</html>