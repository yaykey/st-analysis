<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>添加用户</title>
    <%@include file="/manager/commons/meta.jsp"%>
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
    })
    
    
    function cancel(){
		if(!confirm("确定取消吗?")){
			return false;
		}
		$("#roleName").val("");
		$("#addlist").attr("action","${base}/manager/role/list.feinno");
		$("#addlist").submit();
	}
	
	function submitbutton(){
		var name = $("#roleName").attr("value");
		if(name==''){
			alert("角色名称不能为空，请填写！");
			return false;
		}
		
		var roleLevel = $("#roleLevel").attr("value");
		if(roleLevel==''){
			alert("角色层级不能为空，请填写！");
			return false;
		}
		if (!/^\d{1,2}$/g.test(roleLevel)) {
			alert("角色层级只能输入数字！");
			return false;
		}
		if(!confirm("确定提交吗?")){
			return false;
		}
		$("#addlist").attr("action","${base}/manager/role/add.feinno");
		$("#addlist").submit();
	}
</script>
</head>
<body>
<form  method="post" name="addlist" id="addlist">
	<div class="title-bar-top">
	   添加角色
	</div>
	<div  id="content" >
		<table class="pop-window" align="center" width="70%">
			<tr>
				<td class="td-right"><font color="red">*</font>角色名称：</td>
				<td>
					<input name="roleName" id="roleName" type="text" size="20" maxlength="25">
				</td>
				
				<td class="td-right"><font color="red">*</font>显示类型：</td>
				<td>
					<select name="viewType" id="viewType">
						<option value="frontType" selected="selected">frontType</option>
						<option value="backType">backType</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="td-right"><font color="red">*</font>角色层级：</td>
				<td>
					<input name="roleLevel" id="roleLevel" type="text" size="20" maxlength="2">
				</td>
				<td class="td-right"> 上级角色：</td>
				<td>
					<select name="parentId" id="parentId">
						<option value="" selected="selected"> </option>
						<option value="3">VP</option>
						<option value="4">总监</option>
					</select>
				</td>
			</tr>
			<!-- 
			<tr style="height: 30">
				<td class="td-right">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
				<td colspan="4">
					<textarea id="description" name="description" rows="3" cols="40" ></textarea>
				</td>
			</tr> -->
		</table>
	</div>
	<div align="center">
		<input type="button" value="提交" id="role-save" name="role-save"/>
		<input type="button" value="取消" id="role-cancel" name="role-cancel"/>
	</div>		
</form>
</body>
</html>