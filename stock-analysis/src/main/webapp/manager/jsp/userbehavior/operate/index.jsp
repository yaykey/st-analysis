<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>综合数据应用平台-首页</title>



<link rel="stylesheet" type="text/css" href="${base}/manager/jsp/userbehavior/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${base}/manager/jsp/userbehavior/js/easyui/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${base}/manager/jsp/userbehavior/js/easyui/icon.css"/>

<script type="text/javascript" src="${base}/manager/jsp/userbehavior/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/manager/jsp/userbehavior/js/gridTable_report_operate.js"></script>
<script type="text/javascript" src="${base}/manager/jsp/userbehavior/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${base}/manager/jsp/userbehavior/js/jquery.form.js"></script>
<script type="text/javascript" src="${base}/js/jquery-blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="${base}/manager/jsp/userbehavior/js/windowpop.js"></script>
<script type="text/javascript" src="${base}/js/utils/preview.js"></script>

<style type="text/css">

.plat_pop .new_board p {
    background : transparent;    
}

.plat_pop .new_board p.p_ico1 {
	background: url("${base}/manager/jsp/userbehavior/images/updown_ico.jpg") no-repeat scroll 1px 11px rgba(0, 0, 0, 0);
    height: 40px;
    line-height: 40px;
    padding-left: 35px;
}

</style>

<script type="text/javascript">
//js用于IE6bug select 透过弹层
var ie = !-[1,];
var ie6=ie&&!window.XMLHttpRequest;
$(function(){
if(ie6){
   $(".bgiframe").bgiframe();
  
	}
});

function validataExts(sender){
// 	var extend = sender;
	
// 	var clientHtml = $("#clientid").find("option:selected").text();
// 	var clientVsn =  $("#versionid").find("option:selected").text();
// 	var fileName = clientHtml+"_"+clientVsn+"_mould.xlsx";
	
	var fileNameSource = $("#f_file").val();
// 	var lastIndexOf = fileNameSource.lastIndexOf("\\");
// 	if(lastIndexOf==-1){
		
// 	}else{
// 		fileNameSource = fileNameSource.substring(lastIndexOf+1);
// 	}
	
	var point = fileNameSource.lastIndexOf(".");  
	  
	 var type = fileNameSource.substr(point+1);  
	 if(type=="xls"||type=="xlsx"){
		 	return true;
		}else{
			$.messager.alert("提示","请导入excel文件","info");
			return false;
		}
// 	if(fileNameSource==fileName){
// 		return true;
// 	}else{
// 		$.messager.alert("提示",'上传文件要求和模板同名',"info");
// 		return false;
// 	}
}

/**
下面是调用者的代码，要求名称一致。这里的"dimId"，"clientVsn"，"classId"和上面searchCallback中的名字一致
*/
function searchClass(params) {
	$("#searchId").attr("classId",params.classId);
	$("#searchId").attr("rptId",params.rptId);
	$("#searchId").attr("itemId1",params.itemId1);
	$("#searchId").attr("groupId",params.groupId);
	$("#searchId").attr("itemId2",params.itemId2);
	$("#searchId").attr("flag",1);
	if($.trim(params.classId).length>0){
		changeSearch();
	}else{
		$.messager.alert("提示","请输入搜索关键字!","info");
	}
}
//导入报表的时候检查，如果没有选择客户端和版本则不让导入
function check(){
	var clientid = $("#clientid").html();
	var versionid = $("#versionid").html();

	if(clientid==""||versionid==""){
		$.messager.alert("提示","请先选择客户端和版本信息","info");
		return false;
	}else {
		var clientid = $("#clientid").val();
		var versionid = $("#versionid").val();
		var url =" ${base}/manager/import2/at/operate/import2.feinno?clientid="+clientid;
		$("#f_form").attr("action",url);
		var sender = $("#f_file").val();
		var result = validataExts(sender);
	    if(result){
// 	    	alert(result);
// 	    	$("#excelSubmit").attr("disabled",true);
	    	closePop('excelImport');
		    $("#f_form").submit();
		    return true;
	    }else{
	    	return false;
	    }
	}
}

function exportExcelClick(){
	var clientid = $("#clientid").val();
	//var versionid = $("#versionid").val();
	var clientHtml = $("#clientid").find("option:selected").text();
	if(clientid==null){
		$.messager.alert("提示","请选择客户端","info");
		return;
	}
	
	/* if(versionid==null){
		$.messager.alert("提示","请选择版本","info");
		return;
	} */
	
	var downloadUrl ="${base}/servlet/operate/dowloadData?clientid="+clientid+"&clientHtml="+encodeURIComponent(clientHtml);
	openPop("excelExport","h2");
	
	$("#exportExcel").attr("href",downloadUrl);
	
	/* var time2 = setInterval(
	function(){
	  		   jQuery.get(base + '/manager/import2/chkExport.'+actionExt+'?random='+Math.random()+new Date().getMilliseconds(),function(result){
	  			   var obj =  eval("(" + result + ")");
	  			   if(obj.chk==1){//表示结束，不用监听了
	  				   clearInterval(time2);
	  			   	   closePop('excelExport');
	  			   }
	  		   });
	},100); */
	
	window.setTimeout(function(){
		closePop('excelExport');
	}, 500);
}


function downloadMoudle(){
	var clientid = $("#clientid").val();
	var versionid = $("#versionid").val();
	var clientHtml = $("#clientid").find("option:selected").text();
	var url = "${base}/servlet/operate/dowloadMould?clientid="+clientid+"&clientName="+encodeURIComponent(clientHtml);
	try {
		$("#vfm_dowload2").attr("action",url);
		$("#vfm_dowload2").submit();
		$("#dowloadMould").html("下载中，请稍后...");
		$("#dowloadMould").attr("onclick", "");
		//ie默认度缓存，所以图片一直卡在那里不懂，哎，讨厌的玩意，加个时间撮就好了
		/* var time2 = setInterval(
				function(){
					  		   jQuery.get(base + '/manager/import2/chkExcel.'+actionExt+'?random='+Math.random()+new Date().getMilliseconds(),function(result){
					  			   var obj =  eval("(" + result + ")");
			// 		  			   alert(obj.chk);
					  			 	$("#downloadExcelP").removeClass("p_ico1").addClass("p_ico2");
					  			   if(obj.chk==0){//0表示结束
					  				   clearInterval(time2);
					  				 $("#dowloadMould").html("新增功能点模板下载");
					  				 $("#dowloadMould").attr("onclick", "downloadMoudle()");
					  				$("#downloadExcelP").removeClass("p_ico2").addClass("p_ico1");
					  			   }
					  		   });
				},100); */
		
		window.setTimeout(function(){
			  
			$("#dowloadMould").html("模板下载");
			$("#dowloadMould").attr("onclick", "downloadMoudle()");
			//$("#downloadExcelP").removeClass("p_ico2").addClass("p_ico1");
			
		}, 500);
		
	} catch (ex) {
		alert(ex);
	}
}


var clientidParamToSearch = $("#clientid").val();
var versionidParmToSearch = $("#versionid").val();


</script>
</head>
<body class="body_bg">
<div class="box clearfix">	
	<div class="main_a main_spe" style="text-align:center;">
	<div class="top_con">
		<div class="title3">
			<h2>功能点管理</h2>
		</div>
		<div class="func clearfix">
				<div class="client fl clearfix">
					<label class="fl lab_1">选择客户端：</label>
					<select class="sel_1 fl" name="clientid" id="clientid">
							<c:forEach items="${requestScope.clientTypeList}" var="client">
								<option value="${client.dimId}" srcType="${client.srcType}">${client.dimName}</option>
							</c:forEach>
					</select>
				</div>
				<!-- <div class="client fl clearfix">
					<label class="fl lab_1">选择版本号：</label>
					<select class="sel_1 fl" name="versionid" id="versionid">
					</select>
				</div>
				<div class="edition fl">
				<p class="version clearfix">
					<a href="javascript:void(-1);" onclick="addVesion(this);" class="a1 fl">创建新版本</a>
					<span id="update_version1" class="fl" style="display:none"><a href="javascript:void(-1);" onclick="updateVesion(this);" class="modify">修改版本号</a></span>
					<span id="update_version2"  class="fl"><a href="javascript:void(-1);" class="modify" style="color:#A9A9A9">修改版本号</a></span>
				</p>
				</div> -->
		</div>
	</div>
		<div class="bottom_con" >
			<div class="excel clearfix">
			
			
			
				<div style='position:absolute;left: 25;'>		
					<a href="javascript:void(-1)"  id="inportRpt1" class="a3 fl" onclick="toDownloadExcelModule(this);" >导入新增功能点</a>
				    <a href="javascript:void(-1)" id="inportRpt2" class="a3 fl" style="color:#A9A9A9; display:none;" >导入新增功能点</a>
				</div>		
				
				<div style="position: absolute;left: 190px">		
					<a href="#" class="a3 fl" id="exportExcel" onclick="exportExcelClick();">导出功能点</a>
				</div>
				<div>
					<%--@include file="/search/search.jsp"--%>
				</div>
			</div>
			<div id="static_report" class="version_4 clearfix"></div>
			<div id="static_type" class="version_4 clearfix"></div>	
			<div  id="static_function" class="send_message"></div>
		</div>	
		<p id="saveSubmit" class="btn_b" style="display:none;">
			<a href="javascript:saveTypelist(0);" class="baocun fl" style="display:none" id="saveVsn">保存</a><a href="javascript:saveTypelist(1);"  class="baocun fl">提交</a>
		</p>
	</div><!-- //main -->
</div><!-- //box -->
<br/><br/>
<!-- <div class="plat_pop" id="add_vision">
	<span class="t_round" ></span>
	<div class="z_con">
		<h2><a href="javascript:void(-1);" onclick="closePop('add_vision');" class="close fr"></a> 创建新版本号</h2>
		<div class="inp_con clearfix">
			<label class="fl">版本号</label><input type="text" value="" style="height:30px !important;"  class="fl inp_1 p_4"/><button class="fl n_btn">创建</button>
		</div>
	</div>
	<span class="b_round"></span>
</div> -->

<div class="plat_pop"  id="excelExport">
	<div class="new_board">
		<div class="messageDiv">下载中，请稍后...</div>
		<p class="p_ico3"></p>
	
	</div>
</div>
<div class="plat_pop" id="excelImport">
	<span class="t_round" ></span>
	<div class="z_con">
		<h2 ><a href="javascript:void(-1);" onclick="closePop('excelImport');" class="close fr"></a>导入新增功能点</h2>
		<div class="inp_con clearfix">
			<div class="new_board">
				<p class="p_ico1" id="downloadExcelP" >
					<a onclick="downloadMoudle();" id="dowloadMould" onmouseover="alertPop();" onmouseout="removePop();" >模板下载</a>
					<!-- 浮出的提示内容 -->
					<table   id="dowloadMouldTable" 
							style='display: none; z-index: 100; position: absolute; border: 0px none; border-collapse: collapse; 
							opacity: 1; top: -30px; left: -9px;/* width: 550px; height: 300px; */'>
						<tbody> 
							<tr>
								<td style='width:18px; height:18px; background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/1.png);'></td>	
								<td style='background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/2.png); background-repeat:repeat-x;'></td>	
								<td style='width:18px; height:18px; background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/3.png);'></td>
							</tr>
							<tr>	
								<td style='background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/4.png); background-repeat:repeat-y;'>
								</td>	
								<td style="background-color: white">	
									<a>如果下载的模板只读</a><br />
									<a>解决办法1：则点击右键--&gt;属性--&gt;去掉只读	</a><br />
									<a>解决办法2：另存一份</a>
<!-- 									<img src="${base}/jsp/userbehavior/images/example1.png"></img> -->
								</td>	
								<td style='background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/5.png); background-repeat:repeat-y;'></td>
							</tr>
							<tr>	
								<td style='width:18px; height:20px; background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/6.png); background-repeat:repeat-x;'></td>	
								<td style='background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/7.png); background-repeat:repeat-x; text-align:center;'>
								<img src='${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/tail2.png' alt='' style='border:0px' height='20' width='24'></td>	
								<td style='width:18px; height:20px; background-image:url(${base}/manager/jsp/userbehavior/js/popup/bp_images/azure/8.png); background-repeat:repeat-x;'></td>
							</tr>
						</tbody>
					</table>
				</p>
				<form id="vfm_dowload2" action="" style="display:none;" method="post" target="_blank">				
					<input type="submit"/>
				</form>
			</div>
			<div class="inp_con clearfix">
				<p class="clearfix">
					<button class="fl u_btn">选择文件</button>
					<input type="text" value="请选择编辑好的模板上传"  class="fl inp_1 p_col p_4" id="visionInput"/>
					<a href="#" class="refresh  fl"></a>
				</p>
				
				<form name='f_form' id='f_form' target='frame' action='' enctype='multipart/form-data' method='post' >
				     <input id='f_file' name='importExcel' size="1" type='file'  onchange="inputfileToVisionInput();"
					     style='position:absolute; top:155px; left:50px; height:30px;  filter:alpha(opacity:0);opacity: 0; ' 
					     accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
				     <input type="button" value="提交" class="sub_btn fr" disabled="disabled" id="excelSubmit" onclick="return check();"/>
			    </form>
			    
			</div>
<!-- 			<div > -->
<!-- 					<font color="orange">操作提示：1.下载模版 2.编辑模板 3.选择编辑好的模板文件</font> -->
<!-- 					<font color="red" style="float: left">&nbsp;注&nbsp;&nbsp;意&nbsp;:&nbsp;文件名不能修改，否则不能上传</font> -->
<!-- 			</div> -->
		</div>
	</div>
	<span class="b_round"></span>
</div>
<div class="plat_pop" id="update_vision">
	<div class="t_round"></div>
	<div class="z_con">
		<h2><a href="javascript:void(-1);" onclick="closePop('update_vision');" class="close fr"></a> 修改版本号</h2>
		<div class="inp_con clearfix">
			<label class="fl">版本号</label><input type="text" value="" style="height:30px !important;"  class="fl inp_1 p_4"/><button class="fl n_btn">确定</button>
		</div>
	</div>
	<span class="b_round"></span>
</div>
<div id="report_menu" class="easyui-menu" style="width:120px;"></div>
<div style="display:none" id="searchId"  flag="0"  classId="" rptId="" itemId1="" groupId="" itemId2=""></div>
</body>
</html>
<script type="text/javascript">

function inputfileToVisionInput(){
	var fileName = $("#f_file").val();
	if(fileName.lastIndexOf("\\")!=-1){
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
	}
	
	$("#visionInput").val(fileName);
	$("#excelSubmit").attr("disabled",false);
}

var funcManage=$("#function_manage");//功能管理
var staticReport=$("#static_report");//会话统计类报表

function alertPop(){
	
	$("#dowloadMouldTable").show();
}
function removePop(){
	$("#dowloadMouldTable").hide();
}
$(document).ready(function() {
	$("#clientid").bind('change', function(){//切换客户端
		changeClient();
	});
	
	/* $("#versionid").bind('change', function(){//切换版本
		changeVersion();
	}); */
	funcManage.bind('click', function(){ //功能管理点击
		changeClas();
	});
	
	
	changeClas();
	$(".box").css({"height":($("body").height()+200)});
});


/**
 * 客户端切换版本
 */
/*
function changeClient(){
	progressRpt();
	clientidParamToSearch = $("#clientid").val();
	versionidParmToSearch = $("#versionid").val();
	
	var clientid = $("#clientid").val();
	$("#versionid").empty();
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/versionQuery.'+actionExt,
		dataType : 'json',
		data : {
			"clientid" : clientid
		},
		success : function(responseText) {
			closeRpt();
			for(var i=0;i<responseText.list.length;i++){
				if(i==0){
					$("#versionid").attr("maxvalue",responseText.list[i].clientVsn);
				}
				$("#versionid").append("<option value='"+responseText.list[i].clientVsn+"' newflag='"+responseText.list[i].newFlag+"'>"+responseText.list[i].clientVsn+"</option>");
			}
			changeVersion();
		}
	});
}
*/


/**版本切换*/

/*
function changeVersion(){
	clientidParamToSearch = $("#clientid").val();//搜索用到
	versionidParmToSearch = $("#versionid").val();//搜索用到
	
	$("#static_report").empty();
	$("#static_type").empty();
	$("#static_function").empty();
	var newflag = $("#versionid").find("option:selected").attr("newflag");
	if(newflag==0){
		$("#update_version1").css({"display":""});
		$("#update_version2").css({"display":"none"});
		$("#saveSubmit").css({"display":""});
		$("#inportRpt2").css({"display":"none"});
		$("#inportRpt1").css({"display":""});
	}else{
		$("#update_version1").css({"display":"none"});
		$("#update_version2").css({"display":""});
		$("#saveSubmit").css({"display":"none"});
		$("#inportRpt2").css({"display":""});
		$("#inportRpt1").css({"display":"none"});
	}
	$("#saveVsn").css({display:"none"});
	initSearch();
	if(newflag!=null){
		changeClas();
	}
}
*/



/**点击功能点管理*/
function changeClas(){
	progressRpt();
	staticReport.empty();
	$("#static_type").empty();
	$("#static_function").empty();
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/operate/report.'+actionExt,
		dataType : 'html',
		data : {
			"clientTypeId" : $("#clientid").val()
		},
		success : function(responseText) {
			closeRpt();
			staticReport.html(responseText);
		}
	});
}

function changeSearch(){
	searchRptType();
}


function toDownloadExcelModule(obj){
	var clientid = $("#clientid").html();
	var versionid = $("#versionid").html();

	if(clientid==""){
		$.messager.alert("提示","请选择客户端","info");
		return false;
	}if(versionid==""){
		$.messager.alert("提示","请选择版本号","info");
		return false;
	}
	openPop("excelImport","h2");
}


function initSearch(){
	$("#searchId").attr("classId","");
	$("#searchId").attr("rptId","");
	$("#searchId").attr("itemId1","");
	$("#searchId").attr("groupId","");
	$("#searchId").attr("itemId2","");
	$("#searchId").attr("flag",0);
	$("#search-text").css({"color":"#999"});
	$("#search-text").val("输入要查找的名称或功能点ID");
	
}

$.messager.defaults.ok="确定";
$.messager.defaults.cancel="关闭";

</script>