<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
					$("#user-data").attr("action","${baseManager}/index/input.${actionExt}?type=editInfo&dIndexId=${dIndex.indexId}");
					$("#user-data").submit();
				});
				
			});
		</script>
		
	</head>
	<body>
		<form action="#" method="post" id="user-data" name="user-data">
			<div>
				<fieldset>
					<legend>指标信息</legend>
					<div class="user-infro-config">
						<img src="${baseManager}/jsp/selfinfo/images/settings.png"/> 
						<a class="editInfo" href="#" id="editInfo">指标信息设置</a>
					</div>
					<ul class="user-info-list">
	              		<li>
	               			<h3>基本资料</h3>
	               			<p>
	               				<span class="info-label">指标名称：</span>
	                  			<span class="info-content">${empty requestScope.dIndex ? '': requestScope.dIndex.indexName}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">指标类型：</span>
	               				<span class="info-content">${empty requestScope.dIndex ? '': requestScope.dIndex.indexType}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">计算类型：</span>
	                  			<span class="info-content">${empty requestScope.dIndex ? '': requestScope.dIndex.indexCalType}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">表达式：</span>
	                  			<span class="info-content">${empty requestScope.dIndex ? '': requestScope.dIndex.indexExpr}</span>
	               			</p>
	               			<p>
	               				<span class="info-label">指标描述：</span>
	                  			<span class="info-content">${empty requestScope.dIndex ? '': requestScope.dIndex.indexDsc}</span>
	               			</p>
							<p>
								<span class="info-label">生效时间：</span>
							    <span class="info-content">${empty requestScope.dIndex ? '': requestScope.effDate}</span>
							</p>
							<p>
						   	   <span class="info-label">失效时间：</span>
						       <span class="info-content">${empty requestScope.dIndex ? '': requestScope.expDate}</span>
						    </p>
						    <p>
						  	   <span class="info-label">更新时间：</span>
						       <span class="info-content">${empty requestScope.dIndex ? '': requestScope.updDate}</span>
						    </p>
	               		</li>
	               	</ul>
				</fieldset>
			</div>
		</form>
	</body>
</html>