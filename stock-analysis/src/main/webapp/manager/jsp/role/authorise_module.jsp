<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>给角色分配模块</title>
    <%@include file="/manager/commons/meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script>

    $(function(){
        $("#role-save").button()
            .click(function(event){
            submitbutton();
        })
        $("#role-cancel").button()
            .click(function(event){
            cancel();
        })
    	$('#btn_select').bind('click', function() {
    		popWindowCenter('${base}/manager/role/authorise/getRoleModuleTree.feinno?roleId='+$("#roleId").attr("value"), '选择模块', 552, 522);
    	});
    })
    
    
    function cancel(){
		if(!confirm("确定取消吗?")){
			return false;
		}
		$("#addlist").attr("action","${base}/manager/role/list.feinno");
		$("#addlist").submit();
	}
	
	function submitbutton(){
		
		if(!confirm("确定提交吗?")){
			return false;
		}
		$("#addlist").attr("action","${base}/manager/role/authorise/addModules.feinno");
		$("#addlist").submit();
	}
</script>
</head>
<body>
<form  method="post" name="addlist" id="addlist">
	<input type="hidden" name="roleId" value="${requestScope.roleId}" id="roleId" />
	<div class="title-bar-top">
	   给角色分配模块
	</div>
	<div  id="content" >
		<table class="pop-window" align="center" width="70%">
			<tr>
				<td class="td-right">
					角色名称:
				</td>
				<td nowrap  class="td-left">
					${empty requestScope.role ? "": requestScope.role.name}
				</td>
			</tr>
			<tr>
				<td class="td-right">
					所有模块:
				</td>
				<td nowrap class="td-left">
					<textarea name="moduleNames" id="moduleNames"
						cols="20" rows="10" readonly="readonly"><c:forEach  var="module" items="${requestScope.moduleList}">${module.name}<%out.print("\n");%></c:forEach></textarea>
					<input type="hidden" name="moduleIds"
						id="moduleIds"
						value="<c:forEach  var="module" items="${requestScope.moduleList}">${module.id},</c:forEach>" />
					<input type="button" id="btn_select"	value="分配模块">
				</td>
			</tr>
			
		</table>
	</div>
	<div align="center">
		<input type="button" value="提交" id="role-save" name="role-save"/>
		<input type="button" value="取消" id="role-cancel" name="role-cancel"/>
	</div>		
</form>
</body>
</html>