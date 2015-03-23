<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>个人信息</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/layout.css"/>" rel="stylesheet" type="text/css"/>
		<style type="text/css">
			body {
				background-color: white;
			}
		</style>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$("#editInfo").click(function(){
					$("#user-data").attr("action","${baseManager}/department/input.${actionExt}?type=editInfo&departmentId=${department.id}");
					$("#user-data").submit();
				});
				
			});
		</script>
	</head>
	<body>
		<form action="#" method="post" id="user-data" name="user-data">
			<div>
				<fieldset>
					<legend>部门信息</legend>
					<div class="user-infro-config">
						<img src="${baseManager}/jsp/selfinfo/images/settings.png"/> 
						<a class="editInfo" href="#" id="editInfo">部门信息设置</a>
					</div>
					<ul class="user-info-list">
	              		<li>
	               			<h3>基本资料</h3>
	               			<p>
	               				<span class="info-label">部门名称：</span>
	                  			<span class="info-content">${empty requestScope.department ? '': requestScope.department.name}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">是否显示：</span>
	               				<c:if test="${department.showEnable== 0  }">
	               					<span class="info-content">否</span>
	               				</c:if>
	               				<c:if test="${department.showEnable== 1  }">
	               					<span class="info-content">是</span>
	               				</c:if>
	               			</p>
	               			<p>
	               				<span class="info-label">显示排序：</span>
	                  			<span class="info-content">${empty requestScope.department ? '': requestScope.department.sort}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">上级部门：</span>
	                  			<span class="info-content">${empty requestScope.department ? '': requestScope.department.department.name}</span>
	               			</p>
	               			
	               		</li>
	               	</ul>
				</fieldset>
			</div>
		</form>
	</body>
</html>