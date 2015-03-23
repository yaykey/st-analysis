<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>选择功能</title>
    <%@include file="/manager/commons/meta_ztree.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script>
	/*下面的变量在初始化页面的时候加载赋值
	*/
	var allData;
	var treeObj; 
	var treeId = "functionTree";//树id;
	 //设置树的名称
  
	
		$(function(){
			
			var frmGroupIds = window.opener.document.getElementById("functionIds")
			var frmGroupNames = window.opener.document.getElementById("functionNames")
			$("#role-save").bind("click",function() {returnValue(frmGroupIds, frmGroupNames);window.close();})
			$("#role-cancel").bind("click",cancel)
			userTreeInit();
			$('#btn_add').bind('click',function() {insertAllSelected(userSelectForm.lstSelected,"false");})
			$('#btn_remove').bind('click', function() {
				removeSelected(document.getElementById("lstSelected"));
			})
			
			$('#btn_removeAll').bind('click', function() {
				removeAll(document.getElementById("lstSelected"));
			})
			//initZTree()
	    })
	    
	    function getFontCss(treeId, treeNode) {  
		    return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};  
		}
		var setting = {
				check: {
					enable: true,
					chkStyle: "checkbox",
					radioType: "all"
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: 0
					}
				},  
		        view :{ 
		        	showIcon: true,
		            fontCss: getFontCss  
		        }  
			};
	   
	   
	    function initZTree(val){
			zNodes = val;
			$.fn.zTree.init($("#"+treeId), setting, zNodes);
			
		}
	    
	    //添加已选中的用户
	    function insertAllSelected(addToSelectObj,parentAddFlag) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
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
				url : '${base}/manager/role/authorise/getAllFunction.feinno?roleId=${requestScope.roleId}',
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime()
				},
				success : function(responseText) {
					var json=responseText;
					var data;
					data = eval(json); 
// 					data=[{ id:20014, pId:2001, name:"Widget管理"},{ id:200141, pId:20014, name:"Widget列表"},{ id:200142, pId:20014, name:"Widget添加"},{ id:200143, pId:20014, name:"Widget添加保存"},{ id:200144, pId:20014, name:"Widget修改"},{ id:200145, pId:20014, name:"Widget修改保存"},{ id:200146, pId:20014, name:"Widget删除"},{ id:1200112, pId:120011, name:"检查oemTag唯一性"}]
					allData = data;
					initZTree(data);
					treeObj = $.fn.zTree.getZTreeObj(treeId);
				}
			});	
		}
		
		//搜索方式一、高亮显示
		//使用搜索数据 加高亮显示功能，需要2步  
		//1.在tree的setting 的view 设置里面加上 fontCss: getFontCss 设置  
		//2.在ztree容器上方，添加一个文本框，并添加onkeyup事件，该事件调用固定方法  changeColor(id,key,value）  
		//  id指ztree容器的id，一般为ul，key是指按ztree节点的数据的哪个属性为条件来过滤,value是指过滤条件，该过滤为模糊过滤  
		function changeColor(id,key,value){
			if(value==""){
				return;
			}
		    treeId = id;  
		    var treeObj = $.fn.zTree.getZTreeObj(treeId); 
		    var Nodes = treeObj.getNodes();
		    nodeList = treeObj.transformToArray(Nodes);
		    
		    updateNodes(false);  
		    if(value != ""){  
		        nodeList = treeObj.getNodesByParamFuzzy(key, value);  
		        if(nodeList && nodeList.length>0){  
		            updateNodes(true);  
		        }  
		    }  
		}  
		function updateNodes(highlight) {  
		    var treeObj = $.fn.zTree.getZTreeObj(treeId);  
		    for( var i=0; i<nodeList.length;  i++) {  
		        nodeList[i].highlight = highlight;  
		        treeObj.updateNode(nodeList[i]);  
		    }  
		} 
		
		var searchContent = $("#dicKey").val();
		//1. 查找 level = 2 & name 中包含 "test" 的节点数据
		function filter(node) {
    	    return (node.level == 2 && node.name.indexOf(searchContent)>-1);
    	}
		//搜索方式二:直接只显示需要的节点
		function onlyShowResult(treeId,key,value){
			
			initZTree(allData);//先显示所有的节点
		    if(value != ""){
// 		    	var node =  treeObj.getNodesByFilter(filter, true); // 仅查找一个节点
// 		    	var selectNodeList = treeObj.getNodesByFilter(filter); // 查找节点集合
		    	var selectNodeList = treeObj.getNodesByParamFuzzy(key, value);  //搜索到的节点,
		        if(selectNodeList.length==0){
		        	return;
		        }
		        $.fn.zTree.destroy(treeId);//先销毁旧树
		        //下面接着组装新树的json数据，因为如果直接用selectNodeList则，总会有重复的问题，很无奈啊，估计是ztree的bug
		        var selectNodeListReal="[";
		        for(var i = 0;i<selectNodeList.length;i++){
		        	var nodeItem = selectNodeList[i];
		        	var id = 	nodeItem.id;
		        	var pId = 	nodeItem.pId;
		        	var value = nodeItem.value;
		        	var name = 	nodeItem.name;
		        	selectNodeListReal =selectNodeListReal+ "{id:"+id+",open:true,pId:"+pId+",name:'"+name+"',value:'"+value+"'},"; 
		        }
		        selectNodeListReal = selectNodeListReal+"]";
				var data = eval(selectNodeListReal);
				initZTree(data);//重新加载树
				
		    }  
		    
		}
		
		
</script>
</head>
<body>
<form  method="post" name="userSelectForm" id="addlist">
	<input type="hidden" name="roleId" value="${requestScope.roleId}" id="roleId" />
	<div class="title-bar-top">
	   给角色分配功能
	</div>
	<div id="content" >
		<table align="center" class="pop-window" valign="top" width="70%" height="150">
			<tr >
				<td width="580"  class="td-right">
					<div align="center">
						<strong>模块组树</strong>
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
					<div style="text-align:left;margin:5px;height: 15px;position: absolute;left: 125;top: 0px">
						树搜索：<input type="text" id="dicKey" 
						onkeyup="onlyShowResult('functionTree','name',this.value)"/>
					</div> 
					<div valign="top" align="right" style="height:400px;margin-left: 7px">
						<ul id="functionTree" class="ztree"></ul>
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
						<c:forEach  var="function" items="${requestScope.functionList}">
							<option value="${function.id}">${function.name}</option> 
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
</body>
</html>