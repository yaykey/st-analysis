<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<html>
  <head>
    <%@ include file="/manager/commons/meta.jsp" %>
  </head>
  <body>
	<div class="panelBox">
		<form name="editRole" id="editRole" method="POST" class="formcss">
		<s2:token name="role.token"/>
		<input type="hidden" name="id" id="id" value="${requestScope.id}"/>
			<div class="content">
				<div class="content_title_bg">
				<div class="hideleft"  id="openleft"></div>
				<div class="content_title">未授权警告</div>
			</div>
			<div class="divScrolling" id="content">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tab" class="table_new">
				<tbody>
				   <tr>
					<td align="center" class="tableborder_add">
						<table width="80%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
	        				<td align="center" width="29%">
								您没有权限进行相关操作，请向管理员申请权限
							</td>
							</tr>
						</table>
					
					</td>
				  </tr>
				  </tbody>
				</table>
			
		<br>
	</div>
</div>
</form>
</div>
</body>
</html>