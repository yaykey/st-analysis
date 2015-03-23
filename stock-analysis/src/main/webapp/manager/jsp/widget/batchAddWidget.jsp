<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
</head>
<body style="background-color: #FCFAF3">
	<div class="panelBox">
		<form name="batchAddForm" id="batchAddForm"
			action="${baseManager}/widget/batchAdd.${actionExt}" method="POST"
			enctype="multipart/form-data">
			<div class="search-bar-batch">
				请选择文件:
				<s:file name="upload" id="upload" label="File"/>
			</div>
			<div class="tool-bar-top-batch" id="content">
				<input type="button" name="btn_submit" id="btn_submit" value="提交"
					class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_return" id="btn_return" value="返回"
					class="ui-button ui-widget ui-state-default ui-corner-all" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#btn_submit').bind('click', check);
							$('#btn_return')
									.bind(
											'click',
											function() {
												window.location.href = '${baseManager}/widget/list_widget.${actionExt}?functionId=61';
											});
//							showTip("${tip}")
						});
		function check() {
			var xmlFile = batchAddForm.upload;
			if ($.trim(xmlFile.value) == '') {
				alert("请选择文件");
				xmlFile.focus();
				return;
			}

			batchAddForm.submit();
		}
	//-->
	</script>
</body>
</html>