<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<%@include file="/commons/jquerylibs.jsp" %>
<%@include file="/manager/commons/meta_ztree.jsp"%>

<html>
<head>
    <title>功能维度信息列表</title>
    <link href="<c:url value="/manager/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <%-- <%@include file="/manager/commons/meta.jsp"%> --%>
    <script src="<c:url value="/manager/js/public.js"/>" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
    <script type='text/javascript' src='${base}/dwr/interface/refreshReport.js'></script>
	<script type='text/javascript' src='${base}/js/dwr/engine.js'> </script>
	<script type='text/javascript' src='${base}/js/dwr/util.js'> </script>
	
	<script src="<c:url value="/manager/jsp/user/js/jquery.easyui.min.js"/>" type="text/javascript"></script>
	<link href="<c:url value="/manager/jsp/user/css/easyui.css"/>" rel="stylesheet" type="text/css"/>
	 
<style type="text/css">
	.data-table input {
		text-align: center;
	    width: 30px;
	}
	.data-table .write {
	 	border-color:  #FFA500;
	}
	ul.ztree {
		width: 220px;
		height: 300px;
		margin-left: 5px;
	}
</style>
	 
   <script type="text/javascript">
	   $(document).ready(function(){
		   
		   $("#checkall").click(function(){
				checkall($(":input[name='choiceItem']"));
			});
			$("#checknull").click(function(){
				checknull($(":input[name='choiceItem']"));
			});
			
			$('#btn_add_department').bind('click',updateDeparementInfo);
			
			$('#btn_return').bind('click', function() {
				var fid = $("#functionId").val();
				
				if (!fid) {
					fid = "";
				}
				
				window.location.href = "${base}/manager/widget/list_widget.feinno?functionId=" + fid;
			});
	   });
		
	   var setting = {
			check: {
				enable: true,
				chkboxType : { "Y" : "", "N" : "" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		function userTreeInit(val){
			jQuery.ajax({
				type : "POST",
				url : '${base}/manager/widgetDim/getDepartment.${actionExt}?dimId=' + val,
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime()
				},
				success : function(responseText) {
					var json=responseText;
					//将字符串转成数组
					initZTree(eval(json));
				}
			});	
		}
		//设置树
	    function initZTree(val){
			var zNodes = val
			$.fn.zTree.init($("#department"), setting, zNodes);
		} 
		
		function updateDeparementInfo(){
			if (checkboxCount(list.choiceItem) == 0) {
				alert("请选择一条信息");
				return false;
			} else if (checkboxCount(list.choiceItem) > 1) {
				alert("只能选择一条信息");
				return false;
			} else {
				
				//获取选中的维度Id
				var dimId = [];
				$("input:checked").each(function(i,val){
					dimId[i] = $(this).val();
				});
				
				userTreeInit(dimId);
				
				//将消息提示框中的ok/cancel换成中文
				$.extend($.messager.defaults,{  
				　　ok:"确定",  
				　　cancel:"取消"  
				});
				
				$('#dep').dialog({  
					buttons:[{
						text:'提交',
						handler:function(){
							
							//将选中的部门放到dep数组 中
							var dep = [], j = 0;
							var zTree = $.fn.zTree.getZTreeObj("department");
							var array = zTree.getCheckedNodes(true);
							for(var i=0; i<array.length;i++){
								if(array[i].value!=""){
									dep[j] = array[i].value;
									j++;
								}
							}
							
							//关联部门
							$.ajax({
								type : "POST",
								url : '${base}/manager/widgetDim/updateDepartmentWidgetDim.feinno?dep=' + dep + '&dimId=' 
										+ dimId + '&widgetId=' + $('#widgetId').val(),
								dataType : 'text',
								success : function(responseText) {
									if(responseText == 'false'){
										//$('#prompt').dialog('open');
										$.messager.confirm('操作提示',' 已经有部门存在默认的维度，请重新部门分配或点击【确定】则将已分配的部门信息删除，并添加到当前选中维度，点击【取消】则不做任何操作！',function(r){  
										    if (r){  
										        $.ajax({
										        	type : "POST",
													url : '${base}/manager/widgetDim/update.feinno?dep=' + dep + '&dimId=' 
															+ dimId + '&widgetId=' + $('#widgetId').val(),
													dataType : 'text',
													data : {
														"nocache" : new Date().getTime()
													},
													success : function(responseText) {
														if(responseText){
															$.messager.progress('close');
															$('#dep').dialog('close');
															list.action = "${baseManager}/widgetDim/listDepartmentWidgetDim.${actionExt}";
															list.submit();
														}
													}
										        	
										        });  
										    }  
										});
									}else{
										$('#dep').dialog('close');
										list.action = "${baseManager}/widgetDim/listDepartmentWidgetDim.${actionExt}";
										list.submit();
									}
									
								}
								
							});
							
						}
					},{
						text:'关闭',
						handler:function(){
							$('#dep').dialog('close');
						}
					}]
				});  
				$('#dep').dialog('open');
			}
			
		}
		
		var click_checkbox="false";
		function checkbox_click(){
			click_checkbox = "true";
		}
		
		function trSelect(idTid) {
			
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
			
		}
		
				
		
    </script>
</head>
<body style="background-color:#FCFAF3">
<form action="#" method="post" name="list" id="list">
	<input type="hidden" name="widgetId" value="${empty widgetId ? '': widgetId}" id="widgetId" />
	<input type="hidden" name="functionId" value="${empty functionId ? '': functionId}" id="functionId" />
	
	<div class="title-bar-top">
	  功能维度信息
	</div>
   <div class="tool-bar-top">
   		<input id="btn_add_department" type="button" class="buttoncss" value="分配部门" />		
		<input id="btn_return" type="button" class="buttoncss" value="返回" />
   </div>

   <div id="data-table" class="data-table" >
         <c:choose>
				 	<c:when test="${empty requestScope.widgetDimLinkBeanList}">
						无指定条件数据！
					</c:when>
					<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tab" >
						<tr class="test">
							<th width="8%"  nowrap>
								<a href="#" id="checkall" title="全选"><img id="check_all" border="0" src="${base}/manager/images/check_all.gif" width="13" height="13" alt="全选"></a>&nbsp;&nbsp;
				    			<a href="#" id="checknull" title="取消"><img id="check_none" border="0" src="${base}/manager/images/check_none.gif" width="13" height="13" alt="取消"></a>
							</th>
							<th  width="5%" nowrap>ID</th>
							<th  width="8%" nowrap>WIDGET ID</th>
							<th  width="8%" nowrap>功能名称</th>
							<th  width="8%" nowrap>维度名称</th>
							<th width="20%" class="center">部门默认显示</th>
						</tr>
							
							<c:forEach  var="WidgetDimBean" items="${requestScope.widgetDimLinkBeanList}">
								<tr class="tr_test" align="center"  onclick="return trSelect('${WidgetDimBean.id}')" style="CURSOR: hand">
									<td><input name="choiceItem" id="choiceItem" onclick="return checkbox_click()" type="checkbox" value="${WidgetDimBean.id}"></td>
									<td>
										${WidgetDimBean.id}
									</td>
									<td>
										${widgetId}
									</td>
									<td>
										${widget.name}
									</td>
									<td>
										${WidgetDimBean.dimName}
									</td>									
									<td>
										<c:if test="${WidgetDimBean.defaultDepartments != null}">
											<c:forEach var="department" items="${WidgetDimBean.defaultDepartments}">
												${department.name}&nbsp;
											</c:forEach>
										</c:if>
									</td>
								</tr>
							</c:forEach>
					</table>
				</c:otherwise>
		  </c:choose>
	</div>
</form>

<div id="dep" class="easyui-dialog" title="部门选择" style="width:260px;height:400px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">  
   <ul id="department" class="ztree"></ul>
</div>  
 

</body>

</html>