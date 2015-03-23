<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<html>
<head>
<title>角色省份列表</title>
<%@include file="/manager/commons/meta.jsp"%>
</head>
<body style="background-color:#FCFAF3">
<form action="#" method="post" name="list" id="list">
   <div class="search-bar">
      角色名称：<input name="roleName_" id="roleName_" type="text" size="20"  value="${empty requestScope.roleName_ ? '':requestScope.roleName_}" >
            <input type="button" value="查询" class="buttoncss"id="queryRole" name="queryRole"/>&nbsp;&nbsp;&nbsp;&nbsp;
   </div>

   <div class="data-table" >
         <c:choose>
				 	<c:when test="${empty requestScope.roleList}">
						无指定条件数据！
					</c:when>
					<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tab" >
						<tr class="test">

							<th  width="6%">角色ID</th>
							<th>角色名称</th>

							<th>分配客户端</th>
						</tr>
							
							<c:forEach  var="role" items="${requestScope.roleList}">
								<tr class="tr_test" align="center" style="CURSOR: hand">
									<td>${role.id}</td>
									<td>${role.name}</td>
									<td> 
 										<a href="javascript:authorProvince(${role.id})" id="authorise_ddim">分配省份</a> 
 									</td>
								</tr>
							</c:forEach>
							<%@ include file="/manager/commons/page.jsp"%>
					</table>
				</c:otherwise>
		  </c:choose>
	</div>
</form>

<script type="text/javascript">
	
	
	$(document).ready(function(){
		$("#queryRole").button().
        click(function(event){
        	queryRole();
        })
        
        $('#roleName_').bind('keyup', function(event){
			   if (event.keyCode=="13"){
					$('#list').attr('action', '${base}/manager/roleProvince/list.feinno');
					$('#list').submit();
			   }
			});
	});
		function queryRole(){
			$("#list").attr("action","${base}/manager/roleProvince/list.feinno");
			$("#list").submit();
		}
		
		function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				list.action = "${base}/manager/roleProvince/list.${actionExt}?page="
						+ pageNum;
				list.submit();
			}
		}
		
		function authorProvince(roleId){
			window.location.href = "${base}/manager/roleProvince/getRoleProvince.feinno?roleId="+roleId;
		}
	
</script>
</body>
</html>