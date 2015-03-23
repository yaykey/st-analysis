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
<body class="treeDIV" id="treeBody">
		
	<div >
		<ul id="ddimTree" class="ztree" ></ul>
	</div>
					
	<script type="text/javascript">
		userTreeInit();
		//设置树的名称
	    ztreeobj = "ddimTree";
	    function initZTree(val){
			zNodes = val;
			$.fn.zTree.init($("#"+ztreeobj), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
			
		}
		//初始化用户树
		function userTreeInit(){
			 jQuery.ajax({
				type : "POST",
				url : '${base}/manager/module/getModuleTree.feinno',
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime(),
				    "nocheck":true
				},
				beforeSend:function(XMLHttpRequest){
// 					alert('1');
// 					
// 					alert("2");
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
	</script>
</body>
</html>