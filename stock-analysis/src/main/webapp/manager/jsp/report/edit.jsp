<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/manager/commons/meta.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>编辑报表信息</title>
	<link href="<c:url value="${baseManager }/jsp/selfinfo/css/layout.css"/>" rel="stylesheet" type="text/css"/>
	<script src="<c:url value="/ztree/js/jquery.ztree.core-3.5.js"/>" type="text/javascript" ></script>
    <script src="<c:url value="/ztree/js/jquery.ztree.excheck-3.5.js"/>" type="text/javascript" ></script>
	<style type="text/css">
		.info-label label{
			color: red;
		}
		.info-label{
			font-weight: bold;
			color: #4F6B72;
			font-size: 12px;
		}
		.info-content input {
			width: 320px;
			border: 1px solid #cdd7d9;
		    padding: 3px; 
		    border-radius: 8px;
		    -webkit-border-radius: 8px;
		    -moz-border-radius: 8px;
		    -moz-box-sizing: border-box;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
			line-height: 16px;
			height:28px;
		}
		.info-content select {
			width:320px;
			border: 1px solid #cdd7d9;
		 	padding: 5px;
		    border-radius: 8px;
		    -webkit-border-radius: 8px;
		    -moz-border-radius: 8px;
		    line-height: 16px;
			height:28px;
		}
	</style>
</head>
<body>
	<form name="editFunction" id="editFunction" action="${baseManager}/report/${empty functionInfo ? 'add' : 'modify'}.${actionExt}?reportName=${empty requestScope.functionName? '':requestScope.functionName}"
		method="POST">
		<input type="hidden" name="uid" value="${empty functionInfo ? '': functionInfo.id}" id="uid" />
		
		<ul class="user-info-list">
      		<li>
      			<h3>报表信息</h3>
       			<p>
       				<span class="info-label"><label> * </label>报表名称:</span>
          			<span class="info-content">
          				<input type="text" name="name" id="name" class="ipt_txt" maxlength="255"
							value="${empty functionInfo ? '':functionInfo.name}" />
					</span>
       			</p>
       			<p>
       				<span class="info-label">报表URL:</span>
          			<span class="info-content">
          				<input type="text" name="actionURL" id="actionURL" class="ipt_txt" maxlength="1000"
							value="${empty functionInfo ? '':functionInfo.actionUrl}" />
					</span>
       			</p>
       			<p>
       				<span class="info-label">读取缓存:</span>
          			<span class="info-content">
          				<select name="readCache" id="readCache" size="1">
							<option value="">请选择</option>
							<option value="0" ${functionInfo.readCache eq 0 ? 'selected="selected"' : ''}>是</option>
							<option value="1" ${functionInfo.readCache eq 1 ? 'selected="selected"' : ''}>否</option>
						</select>
					</span>
       			</p>
       			<p>
       				<span class="info-label">是否上线:</span>
          			<span class="info-content">
          				<select name="onlineStatus" id="onlineStatus" size="1">
							<option value="">请选择</option>
							<option value="1" ${functionInfo.onlineStatus eq 1 ? 'selected="selected"' : ''}>是</option>
							<option value="0" ${functionInfo.onlineStatus eq 0 ? 'selected="selected"' : ''}>否</option>
						</select>
					</span>
       			</p>
       			<p>
       				<span class="info-label"><label> * </label>所属模块:</span>
          			<span class="info-content">
          				<select name="moduleId" id="moduleId" size="1">
							<option value="0">请选择</option>
							<c:forEach items="${requestScope.moduleList}" var="module">
								<option value="${module.id}"
									${module.id eq functionInfo.moduleId ? 'selected="selected"' : ''}>${module.name}</option>
							</c:forEach>
						</select>
					</span>
       			</p>
       			<p>
       				<span class="info-label"><label> * </label>排序:</span>
          			<span class="info-content">
          				<input type="text" name="sort" id="sort" class="ipt_txt" maxlength="5" 
          					value="${empty functionInfo ? '':functionInfo.sort}" />
					</span>
       			</p>
       			<p>
       				<span class="info-label"><label> * </label>报表类型:</span>
          			<span class="info-content">
          				<select name="functionType" id="functionType" size="1" onchange="show()">
							<option value="">请选择</option>
							<option value="1" ${functionInfo.functionType eq 1 ? 'selected="selected"' : ''}>后台</option>
							<option value="2" ${functionInfo.functionType eq 2 ? 'selected="selected"' : ''}>前台</option>
							<option value="21" ${functionInfo.functionType eq 21 ? 'selected="selected"' : ''}>前台报表</option>
							<option value="22" ${functionInfo.functionType eq 22 ? 'selected="selected"' : ''}>首页报表</option>
							<option value="23" ${functionInfo.functionType eq 23 ? 'selected="selected"' : ''}>自定义报表</option>
						</select>
					</span>
       			</p>
       		</li>
       		
				<li id="test1">
		 					<h3>首页报表信息</h3>
							<p>
				  				<span class="info-label">列数设置:</span>
				     			<span class="info-content">
			   						<select name="cols" id="cols" size="1">
										<option value="">请选择</option>
										<option value="1"
											${(functionExtend != null && functionExtend.cols eq 1 )? 'selected="selected"' : ''}>1</option>
										<option value="2"
											${(functionExtend != null && functionExtend.cols eq 2 )? 'selected="selected"' : ''}>2</option>							
									</select>
								</span>
							</p>
  				</li>
			
				<li id="test2">
 					<h3>前台报表父子关系设置</h3>
					<p>
		  				<span class="info-label">叶子节点:</span>
		     			<span class="info-content">
	   						<select name="isLeaf" id="isLeaf" size="1">
								<option value="">请选择</option>
								<option value="0"
									${(functionReportExtend != null && functionReportExtend.isLeaf eq 0 )? 'selected="selected"' : ''}>否</option>
								<option value="1"
								${(functionReportExtend != null && functionReportExtend.isLeaf eq 1 )? 'selected="selected"' : ''}>是</option>							
							</select>
						</span>
					</p>
					<p>
		  				<span class="info-label">父节点:</span>
		     			<span class="info-content">
	   						<select name="pid" id="pid" size="1">
								<option value="">请选择</option>
								<c:forEach items="${requestScope.functionList}" var="function">
									<option value="${function.id}" 
										${(functionReportExtend.parentId eq function.id) ? ' selected="selected" ' : ''}
									>${function.name}</option>
								</c:forEach>				
							</select>
						</span>
					</p>
  				</li>
      	</ul>
		<div align="center">

			<input type="button" name="btn_submit" id="btn_submit" value="提交"  class="ui-button ui-widget ui-state-default ui-corner-all" />
			<input type="button" name="btn_return" id="btn_return" value="返回"  class="ui-button ui-widget ui-state-default ui-corner-all" />
		</div>
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
				$('#btn_submit').bind('click', check);
				$('#btn_return').bind(
					'click',
					function() {
						window.location.href = '${base}/manager/report/list.feinno?reportName=${empty requestScope.functionName? '':requestScope.functionName}';
					});
 				$("#test1").hide();
 				$("#test2").hide();
 				
				var funcType =$("#functionType").val();
				if(funcType == 21){
					$("#test2").show();
				}
				if(funcType == 22){
					$("#test1").show();
				}
				
			});

		function check() {

			var name = editFunction.name;
			var moduleId = editFunction.moduleId;
			var sort = editFunction.sort;
			var functionType = editFunction.functionType;
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
			if(functionType.value == ''){
				alert("请选择报表类型");
				functionType.focus();
				return;
			}

			editFunction.submit();
			//刷新父页面
			//self.parent.location = "${base}/manager/report/list.feinno";
			
		}
		
		function show(){
			var value = $("#functionType").val();
			
//			alert(value);
			if(value == "21"){
				$("#test1").hide();
				$("#test2").show();
			}else if(value == 22){
				$("#test2").hide();
				$("#test1").show();
			}else{
				$("#test1").hide();
				$("#test2").hide();
			}
		}
	</script>
</body>
</html>