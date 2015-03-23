<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>选择省份</title>
    <%@include file="/manager/commons/meta_ztree.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script>
	
	
		
			
		$(function(){
			
			var frmGroupIds = window.opener.document.getElementById("moduleIds");
			var frmGroupNames = window.opener.document.getElementById("moduleNames");
			$("#role-save").bind("click",function() {returnValue(frmGroupIds, frmGroupNames);window.close();})
			$("#role-cancel").bind("click",cancel)
			userTreeInit()
			$('#btn_add').bind('click',function() {insertAllSelected(userSelectForm.lstSelected,"false");})
			$('#btn_remove').bind('click', function() {
				removeSelected(document.getElementById("lstSelected"));
			})
			
			$('#btn_removeAll').bind('click', function() {
				removeAll(document.getElementById("lstSelected"));
			})
			
			
			//initZTree()
	    })
	    
	    //设置树的名称
	    ztreeobj = "moduleTree";
	    function initZTree(val){
			zNodes = val;
			$.fn.zTree.init($("#"+ztreeobj), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
			
		}
	    
	    //添加已选中的用户
	    function insertAllSelected(addToSelectObj,parentAddFlag) {
			var zTree = $.fn.zTree.getZTreeObj(ztreeobj);
			var array = zTree.getCheckedNodes(true);
			var lstSelectedObj = addToSelectObj;
			for(var i=0; i<array.length;i++)
			{
				
				//如果parentAddFlag == "false",并且值为空，则说明是父节点并且不添加，此时跳过添加
				if(parentAddFlag == "false" && array[i].value=="")
					continue;
				
				var flag = true;
				for ( var j = 0; j < lstSelectedObj.length; j++)
				{
					
					if (lstSelectedObj[j].value == array[i].value)
					{
						flag = false;
						break;
					}
				}
				
				if (flag)
				{
					var oOption = document.createElement("OPTION");
					oOption.text = array[i].name;
					oOption.value = array[i].value;
					addToSelectObj.add(oOption);
				}
				
			}

		}
	    
	    function removeSelected(oSelect) {
	    	for (i = oSelect.childNodes.length - 1; i >= 0; i--) {
	    		var node = oSelect.childNodes[i];
	    		if (node.selected) {
	    			oSelect.removeChild(node);
	    		}
	    	}
	    }
	    
	    function removeAll(oSelect) {
	    	for (i = oSelect.childNodes.length - 1; i >= 0; i--) {
	    		oNode = oSelect.childNodes[i];
	    		oSelect.removeChild(oNode);
	    	}
	    }
	    
	    function returnValue(frmGroupIds, frmGroupNames) {
	    	frmGroupIds.value = "";
	    	frmGroupNames.value = "";
	    	var oSelect = document.getElementById("lstSelected");
	    	for ( var x = 0; x < oSelect.childNodes.length; x++) {
	    		if (oSelect.childNodes[x].value == null) {
	    			continue;
	    		}
	    		frmGroupIds.value += oSelect.childNodes[x].value + ",";
	    		frmGroupNames.value += oSelect.childNodes[x].innerHTML + "\n";
	    	}
	    	frmGroupIds.value.substr(0, frmGroupIds.value.length - 1);
	    	frmGroupNames.value.substr(0, frmGroupNames.value.length - 1);
	    }
	    
	    
	    function cancel(){
			if(!confirm("确定取消吗?")){
				return false;
			}
			window.close();
		}
		
		//初始化用户树
		function userTreeInit(){
			jQuery.ajax({
				type : "POST",
				url : '${base}/manager/roleProvince/getAllProvince.feinno?roleId=${requestScope.roleId}',
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime()
				},
				success : function(responseText) {
					var json=responseText;
					var data;
					eval("data="+json); 
					//var test = [{ id:1, pId:0, name:"数据中心", open:true,value:"1"},{ id:11, pId:1, name:"admin", value:"2"},{ id:12, pId:1, name:"test", value:"21"}];
					initZTree(data);
					//if (responseText == "ok") {
					//	$.tips('设置成功', '重置密码链接已发送至您的邮箱，请及时重置密码。');
					//}// else {
					//	//$.growlUI('设置失败',responseText,8000);
					//	$.tips('设置失败', responseText, 8000);
					//}					
				}
			});	
		}
		
		
		
</script>
</head>
<body>
<form  method="post" name="userSelectForm" id="addlist">
	<input type="hidden" name="roleId" value="${requestScope.roleId}" id="roleId" />
	<div class="title-bar-top">
	   给角色分配省份
	</div>
	<div id="content" >
		<table align="center" class="pop-window" valign="top" width="70%" height="150">
			<tr >
				<td width="580"  class="td-right">
					<div align="center">
						<strong>省份树</strong>
						<a href="#" id="checkall" title="全选">
									<img id="check_all" border="0"
										src="${base}/manager/images/check_all.gif" width="13"
										height="13" alt="全选"></a>
										
										&nbsp;&nbsp; 
										
									<a href="#" id="checknull" title="取消">
									<img id="check_none" border="0"
										src="${base}/manager/images/check_none.gif" width="13"
										height="13" alt="取消"></a>
					</div>
				</td>
				<td width="69"  class="td-center">
				</td>
				<td class="td-left" >
					<div align="left">
						<strong>结果集</strong>
					</div>
				</td>
			</tr>
			<tr >
				<td   class="td-right"  style="vertical-align:top">
					<div valign="top" align="right" style="height:400px;margin-left: 7px">
						<ul id="moduleTree" class="ztree"></ul>
					</div>
				</td>
				<td width="79"  class="td-center" style="vertical-align:top">
				 
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="24">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="24">
								<div align="center">
								</div>
							</td>
						</tr>
						<tr>
							<td height="21">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="21">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="21">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="20">
								<div align="center">
									<input name="add" type="button" class="button" id="btn_add"
										value="增加">
								</div>
							</td>
						</tr>
						<tr>
							<td height="21">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="23">
								<div align="center">
									<input name="remove" type="button" class="button"
										id="btn_remove" value="移除">
								</div>
							</td>
						</tr>
						<tr>
							<td height="22">
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td height="28">
								<div align="center">
									<input name="removeAll" type="button" class="button"
										id="btn_removeAll" value="全部移除">
								</div>
							</td>
						</tr>
						
					</table>
				</td>
				<td  class="td-left"  style="vertical-align:top">
					
					<select name="lstSelected" multiple id="lstSelected" style="width:215px;height:373px;margin-top: 9px">
						<c:forEach  var="module" items="${requestScope.provinces}">
							<option value="${module.provinceId}">${module.provinceName}</option> 
						</c:forEach>
					</select>
					
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<input type="button" value="提交" id="role-save" name="role-save"/>
		<input type="button" value="取消" id="role-cancel" name="role-cancel"/>
	</div>		
</form>
<script type="text/javascript">
	$(document).ready(function(){
		//表格点击选中复选框
		$("#checkall").click(function(){
			var zTree = $.fn.zTree.getZTreeObj(ztreeobj);
			zTree.checkAllNodes(true);
		});
		$("#checknull").click(function(){
			var zTree = $.fn.zTree.getZTreeObj(ztreeobj);
			zTree.checkAllNodes(false);
		});
	});
</script>
</body>
</html>