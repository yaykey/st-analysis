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
				url : '${base}/manager/department/getDepartmentTree.feinno',
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
// 					var json = '[{id:1001,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=1",target:"contentmain",pId:0,name:"数据中心",open:true,value:"0.0"},{id:10011,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=2",target:"contentmain",pId:1001,name:"PC产品部",open:true,value:"1.1"},{id:100111,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=53",target:"contentmain",pId:10011,name:"111111111111111111",open:true,value:"2.2"},{id:10012,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=44",target:"contentmain",pId:1001,name:"数据一级部门1",open:true,value:"1.1"},{id:100121,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=45",target:"contentmain",pId:10012,name:"数据二级部门1",open:true,value:"44.44"},{id:1001211,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=50",target:"contentmain",pId:100121,name:"三级部门1",open:true,value:"45.45"},{id:100122,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=47",target:"contentmain",pId:10012,name:"数据二级部门2",open:true,value:"44.44"},{id:100123,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=48",target:"contentmain",pId:10012,name:"数据二级部门3",open:true,value:"44.44"},{id:10013,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=3",target:"contentmain",pId:1001,name:"无线产品部",open:true,value:"1.1"},{id:10014,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=49",target:"contentmain",pId:1001,name:"数据一级部门2",open:true,value:"1.1"},{id:10015,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=4",target:"contentmain",pId:1001,name:"飞聊产品部",open:true,value:"1.1"},{id:10016,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=5",target:"contentmain",pId:1001,name:"同窗产品部",open:true,value:"1.1"},{id:10017,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=6",target:"contentmain",pId:1001,name:"短信产品部",open:true,value:"1.1"},{id:10018,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=7",target:"contentmain",pId:1001,name:"WAP产品部",open:true,value:"1.1"},{id:10019,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=38",target:"contentmain",pId:1001,name:"移动-北窗",open:true,value:"1.1"},{id:100110,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=39",target:"contentmain",pId:1001,name:"VP",open:true,value:"1.1"},{id:100111,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=40",target:"contentmain",pId:1001,name:"pad产品部",open:true,value:"1.1"},{id:100112,url:"http://127.0.0.1:8080/fdc/manager/user/searchUserByDepartment.feinno?departmentId=8",target:"contentmain",pId:1001,name:"其它",open:true,value:"1.1"}]';
					var data;
				 	eval("data="+json); 
					initZTree(data);
				}
			});	 
		}
	</script>
</body>
</html>