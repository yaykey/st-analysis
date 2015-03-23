<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>个人信息</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/layout.css"/>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div class="content">
			<fieldset>
				<legend>编辑个人信息</legend>
				<div class="user-infro-config">
					<img src="${baseManager}/jsp/selfinfo/images/settings.png"/> <a href="${baseManager}/selfinfo/input.${actionExt}">个人信息设置</a>
					<img src="${baseManager}/jsp/selfinfo/images/password.png"/> <a href="${baseManager}/selfinfo/editpwd.${actionExt}">密码修改</a>
				</div>
				<ul class="user-info-list">
              		<li>
               			<h3>基本资料</h3>
               			<p>
               				<span class="info-label">登录名：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.username}</span>
               			</p>
               			<%-- <p>
               				<span class="info-label">用户姓名：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.name}</span>
               			</p> --%>
               			<p>
               				<span class="info-label">电子邮件：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.email}</span>
               			</p>
               			<p>
               				<span class="info-label">手机号码：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.mobile}</span>
               			</p>
               			<p>
               				<span class="info-label">角色：</span>
                  			<span class="info-content">
                  				<c:if test="${requestScope.roles != null }">
                  					<c:forEach items="${requestScope.roles }" var="role">
                  						${role.name }
                  					</c:forEach>
                  				</c:if>
                  			</span>
               			</p>
               			<%-- <p>
               				<span class="info-label">省份：</span>
                  			<span class="info-content">${empty requestScope.provinceName ? '': requestScope.provinceName}</span>
               			</p> --%>
               			<%-- <p>
               				<span class="info-label">所属部门：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.department.name}</span>
               			</p> --%>
               		</li>
               		<%-- <li>
               			<h3>详细信息</h3>
               			<p>
               				<span class="info-label">公司名称：</span>
                  			<span class="info-content">${empty requestScope.user ? '': requestScope.user.company}</span>
               			</p>
               		</li> --%>
               	</ul>
			</fieldset>
		</div>
		<div class="editpwd">
			
		</div>
	</body>
</html>