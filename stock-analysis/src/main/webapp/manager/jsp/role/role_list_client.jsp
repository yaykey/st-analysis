<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>角色客户端列表</title>
    <%@include file="/manager/commons/meta.jsp"%>
    <script>
		$(function(){
		   
		   	$("#role-add").button().
		            click(function(event){
		               addRole();
		            })
		    $("#role-del").button().
		            click(function(event){
		            	deleteRole();
		            })
		    $("#role-upd").button().
		            click(function(event){
		               updateRole();
		            })
		    $("#queryRole").button().
		            click(function(event){
		            	queryRole();
		            })
		   // $("a[name='updateRole']").each(function(index,domEle){
		//		$(domEle).click(function(){
		//			updateRole(index);
		//		});
		//	});
		//	$("a[name='deleteRole']").each(function(index,domEle){
		//		$(domEle).click(function(){
		//			deleteRole(index);
		//		});
		//	});
		//	$("#queryRole").click(function(){
		//		queryRole();
		//	});
		})
		
		$(document).ready(function(){		
			$("#checkall").click(function(){
				checkall($(":input[name='choiceItem']"));
			});
			$("#checknull").click(function(){
				checknull($(":input[name='choiceItem']"));
			});
		});
		
		function queryRole(){
			
			$("#list").attr("action","${base}/manager/role/listAndClient.feinno");
			$("#list").submit();
		}
		
		function updateRole(){
			if (checkboxCount(list.choiceItem) == 0) {
				alert("请选择一个要修改的角色");
				return false;
			} else if (checkboxCount(list.choiceItem) > 1) {
				alert("只能选择一个要修改的角色");
				return false;
			} else {
				//var role_id = $("input[name='choiceItem']:eq("+index+")").attr("value");
				var dt = new Date();
				$("#list").attr("action","${base}/manager/role/edit.feinno?dt"+dt);
				$("#list").submit();	
			}
			
		}
		

		function goPage(pageNum) {
			
			if (isInteger(pageNum, "请使用整型！")) {
				list.action = "${base}/manager/role/listAndClient.${actionExt}?page="
						+ pageNum;
				list.submit();
			}
		}
		
		
		function deleteRole(){
			if (checkboxCount(list.choiceItem) == 0) {
				alert("请选择一个要删除的角色");
				return false;
			} else if (checkboxCount(list.choiceItem) > 1) {
				alert("一次只能选择一个要删除的角色");
				return false;
			}else{
				if(!confirm("确定要删除选择的角色吗?")){
					return false;
				}
				var dt = new Date();
				$("#list").attr("action","${base}/manager/role/delete.feinno?dt"+dt);
				$("#list").submit();
			}
		}
		
		function addRole(){
			window.location.href = "${base}/manager/jsp/role/role_add.jsp?dt="+new Date();
		}
		
		function authoriseUser(roleId,rName){
			window.location.href = "${base}/manager/role/authorise/getRoleUser.feinno?roleName="+rName+"&roleId="+roleId;
		}
		function authoriseModule(roleId){
			window.location.href = "${base}/manager/role/authorise/getRoleModule.feinno?roleId="+roleId;
		}
		function authoriseFunction(roleId){
			window.location.href = "${base}/manager/role/authorise/getRoleFunction.feinno?roleId="+roleId;
		}
		
		function authoriseDDim(roleId){
			window.location.href = "${base}/manager/role/authorise/getRoleDDim.feinno?roleId="+roleId;
		}
		
		function authorClient(roleId){
			window.location.href = "${base}/manager/role/authorise/getRoleClient.feinno?roleId="+roleId;
		}
		
		var click_checkbox="false";
		function checkbox_click(){
			click_checkbox = "true";
		}
		
		/* function trSelect(idTid) {
			if(click_checkbox != "true")
			{
				var tidObj = list.choiceItem;
				if(!tidObj.length)
				{
					if(tidObj.value == idTid)
					{
						if(tidObj.checked)
							tidObj.checked = false;
						else
							tidObj.checked = true;
					}
				}else{
					for(var i=0;i<tidObj.length ; i++)
					{
						if(tidObj[i].value == idTid)
						{
							if(tidObj[i].checked)
								tidObj[i].checked = false;
							else
								tidObj[i].checked = true;
						}
					}
				}
			}
			click_checkbox = "false";
			
		} */
		
    </script>
</head>
<body style="background-color:#FCFAF3">
<form action="#" method="post" name="list" id="list">
   <div class="search-bar">
      角色名称：<input name="roleName_" id="roleName_" type="text" size="20"  value="${empty requestScope.roleName_ ? '':requestScope.roleName_}" >
            <input type="button" value="查询" class="buttoncss"id="queryRole" name="queryRole"/>&nbsp;&nbsp;&nbsp;&nbsp;
<!-- 			<input type="button"  value="增加" id="role-add"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 			<input type="button"  value="修改" id="role-upd"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 			<input type="button" value="删除" id="role-del"/> -->
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
<%-- 									<td><input name="choiceItem" id="choiceItem" onclick="return checkbox_click()" type="checkbox" value="${role.id}"></td> --%>
									<td>${role.id}</td>
									<td>${role.name}</td>
<%-- 									<td>${role.roleLevel}</td> --%>
<%-- 									<td>${role.viewType}</td> --%>
									<!--td><fmt:formatDate value="${role.createDate}" pattern="yyyy-MM-dd"/></td-->
<!-- 									<td> -->
<%-- 										<a href="javascript:authoriseUser(${role.id},'${role.name}')" id="authorise_user">分配用户</a> --%>
<!-- 									</td> -->
<!-- 									<td> -->
<%-- 										<a href="javascript:authoriseModule(${role.id})" id="authorise_module">分配模块</a>/<a href="javascript:authoriseFunction(${role.id})" id="authorise_function">分配功能</a> --%>
<!-- 									</td> -->
<!-- 									<td> -->
<%-- 										<a href="javascript:authoriseDDim(${role.id},'${role.name}')" id="authorise_ddim">分配维度维值</a> --%>
<!-- 									</td> -->
										
										<td> 
 										<a href="javascript:authorClient(${role.id})" id="authorise_ddim">分配客户端</a> 
 									</td>
								</tr>
							</c:forEach>
							<%@ include file="/manager/commons/page.jsp"%>
					</table>
				</c:otherwise>
		  </c:choose>
	</div>
	<!--
    <div class="search-bar">

                   (3/10) <button>前页</button>
                    <button>后页</button>
    </div>-->
</form>

</body>

</html>