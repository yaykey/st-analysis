<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
<%@include file="/manager/commons/meta_ztree_widget.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">

<style type="text/css">
.data-table td {
	word-break: break-all;
}
.treeDIV{
background-image:url(../../../../ztree/images/wait.gif);
background-repeat: no-repeat;
  background-position: center ;
}
</style>


</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script type="text/javascript" src="${base}/manager/js/public.js"></script>
<body style="background-color: #FCFAF3" class="treeDIV" id="treeBody">
		
						<div valign="top" align="right"
							style="margin-left: 7px">
							<ul id="ddimTree" class="ztree"></ul>
						</div>
					
	<script type="text/javascript">
	//alert("aaaabbb");
	userTreeInit();

		//设置树的名称
	    ztreeobj = "ddimTree";
	    function initZTree(val){
	    //	alert("cccc");
			zNodes = val;
			$.fn.zTree.init($("#"+ztreeobj), setting, zNodes);
			setCheck();
			//alert("bbb");
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
			
		}
		//初始化用户树
		function userTreeInit(){
		//	alert("bbb");
			 jQuery.ajax({
				type : "POST",
				url : '${base}/manager/widgetDim/getWidgetTree.feinno?widgetId=${requestScope.widgetId}',
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime(),
				    "nocheck":true
				},
				success : function(responseText) {
					$("#treeBody").removeClass("treeDIV");
					var json=responseText;
					var data;
					//alert("aaaaaaaaaaaaaa"+json);
					/* var json =[{ id:1001, pId:0, name:"用户规模", open:true,value:""},{ id:10011, pId:10011, name:"全用户规模", value:"61.29.21"},{
					id:10012, pId:1001, name:"飞信用户规模", open:true,value:"64.29.21"},{ id:100121, pId:10012, name:"PC飞信各客户端活跃用户数", open:true,value:"65.29.21"}];
				 */	
				 	eval("data="+json); 
				// alert("dd============");
					//var test = [{ id:1, pId:0, name:"数据中心", open:true,value:"1"},{ id:11, pId:1, name:"admin", value:"2"},{ id:12, pId:1, name:"test", value:"21"}];
					initZTree(data);
					//alert("dd");
					//if (responseText == "ok") {
					//$.tips('设置成功', '重置密码链接已发送至您的邮箱，请及时重置密码。');
					//}// else {
					//	//$.growlUI('设置失败',responseText,8000);
					//	$.tips('设置失败', responseText, 8000);
					//}					
				}
			});	 
		}
	</script>
</body>
</html>