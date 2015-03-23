<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
<%@include file="/manager/commons/meta_ztree_widget.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">

<style type="text/css">
.treeDIV{
background-image:url(../../../ztree/images/wait.gif);
background-repeat: no-repeat;

background-position: center;

}
</style>




</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script type="text/javascript" src="${base}/manager/js/public.js"></script>
<body style="background-color: #FCFAF3" class="treeDIV" id="treeBody">
		
		<div id="left_report" class="view_report">
			<ul id="reportTree" class="ztree"></ul> 
	  	</div>
	
					
	<script type="text/javascript">
	$(document).ready(
			function() {
				
				userTreeInit();
    			
    			var setting = {
   					view: {
   						showIcon: showIconForTree
   					},
   					data: {
   						simpleData: {
   							enable: true
   						}
   					},
   					callback: {
   						onClick: viewReport
   					}
   				};
    			
    			function showIconForTree(treeId, treeNode) {
    				return !treeNode.isParent;
    			};

    			//设置树的名称
    		    function initZTree(val){
    				zNodes = val;
    				$.fn.zTree.init($("#reportTree"), setting, zNodes);
    			}
    			//初始化用户树
    			function userTreeInit(){
    				 jQuery.ajax({
    					type : "POST",
    					url : '${base}/manager/report/getAllReport.feinno?widgetId=${requestScope.widgetId}',
    					dataType : 'text',
    					data : {
    						"nocache" : new Date().getTime(),
    					    "nocheck":true
    					},
    					success : function(responseText) {
    						$("#treeBody").removeClass("treeDIV");
    						var json=responseText;
    						var data;
    					 	eval("data="+json); 
    						initZTree(data);
    					}
    				});	 
    			}
    			
    			function viewReport(event, treeId, treeNode, clickFlag){
    				var pid, ids, s;
    				ids = treeNode.value.toString();
    				s = ids.indexOf (".");
    				var id= new Array()
    				if(s > 0){
    					id = ids.split(".");
    					var size = id.length;
    					
    					pid = id[0];
    					var obj = parent.document.getElementById("frame_content");
    					if(treeNode.isParent){
							//有子节点
    						//$('.report').attr("src","${base}/manager/report/reportList.feinno?functionParentId=" + pid + "&type=part");
    						obj.src = "${base}/manager/report/reportList.feinno?reportId=" + pid + "&type=part";
						}else{
							//叶子节点
// 							alert(obj.attr("src"));
							obj.src = "${base}/manager/report/reportList.feinno?functionId=" + pid + "&type=part";
						}
    				}else{
    					//顶级菜单
    					pid = ids;
    					obj.src = "${base}/manager/report/reportList.feinno?reportId=" + pid + "&type=part";
    				}
    				
    			}
    			
			})
	</script>
</body>
</html>