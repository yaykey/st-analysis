<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base}/manager/css/main_log.css" rel="stylesheet" type="text/css" />
	<link href="${base}/manager/css/css_log.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<c:url value="${base}/manager/jsp/user/css/layout.css"/>">
	<script src="<c:url value="/manager/js/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
	
	<style type="text/css">
			.tableborder td {
				vertical-align: top;
			}
			.grid-table {
				vertical-align: top;
				word-break: break-all;
			}
			.grid-table th,
			.grid-table td {
				border-top : none;
				border-bottom__ : none;
				border-left__ : none;
				border-right : none;
			}
	</style>
		
	<script type="text/javascript">
		$(document).ready(
				function(){
					$('#a_selectall').bind('click', function() {
						checkall(listFunction.choiceItem);
					});
					$('#a_deselect').bind('click', function() {
						checknull(listFunction.choiceItem);
					});
					//查询框点击
				  	$("#condition").each(function() {
				        //保存当前文本框的值
				        var vdefault = this.value;
				        $(this).focus(function() {
				            //获得焦点时，如果值为默认值，则设置为空
				            if (this.value == "请输入查询条件") {
				                this.value = "";
				                $("#condition").addClass("term");
				            }
				        });
				        $(this).blur(function() {
				            //失去焦点时，如果值为空，则设置为默认值
				            if (this.value == "") {
				            	$("#condition").removeClass("term");
				                this.value = "请输入查询条件";
				                
				            }
				        });
				    });
					
					
				  	$('#condition').bind('keyup', function(event){
					   if (event.keyCode=="13"){
						   var value = $("#condition").val();
						   var column = $("#column").val();
						   if(value=="请输入查询条件"){
								value = "";
							}
							$('#listFunction').attr('action', '${baseManager}/log/list.${actionExt}?column=' + column+'&value='+value);
							$('#listFunction').submit();
					   }
					});
					
					//点击查询
					$("#searchButton").click(function(){
						 var value = $("#condition").val();
						   var column = $("#column").val();
						   if(column==""){
							   alert("请选择查询字段");
							   return false;
						   }
						   if(value=="请输入查询条件"){
								value = "";
							}
						 
							$('#listFunction').attr('action', '${baseManager}/log/list.${actionExt}?column=' + column+'&value='+value);
							$('#listFunction').submit();
					});
				  
					//删除
					$('#log-delete').click(function(){
						if ($("input:checked").size() == 0) {
							alert("请选择一个删除的日志");
							return false;
						}else{
							if(!confirm("确定要删除选择的日志吗?")){
								return false;
							}
							var userId = [];
							$("input:checked").each(function(i,val){
								userId[i] = $(this).val();
							});
							$('#listFunction').attr('action', '${baseManager}/log/delete.${actionExt}?items='+userId);
							$('#listFunction').submit();
							
						}
					});
					
					var column = $("#columnHidden").val();
					if(column!=""&&column!="OPERATE_TIME"){
 						$("#column option[value='"+column+"']").attr("selected","selected");	
 					}else if(column=="OPERATE_TIME"){
 						$("#column option[value='"+column+"']").attr("selected","selected");
 						
 						$("#condition").css("display","none");
 						$("#operateTime").css("display","block");
 						var startTime = $("#startTimeHidden").val();
 						var endTime = $("#endTimeHidden").val();
 						$("#d5221").val(startTime);
 						$("#d5222").val(endTime);
 					}
				});
		
		function showTime(value){
			if(value=="OPERATE_TIME"){
				$("#condition").css('display','none'); 
				$("#operateTime").css('display','block'); 

			}else{
				$("#condition").css("display","block");
				$("#operateTime").css("display","none");
			}
		}
	
		
		var click_checkbox="false";
		function checkbox_click(){
			click_checkbox = "true";
		}
		function trSelect(idTid) {
			if(click_checkbox != "true")
			{
				var tidObj = listFunction.choiceItem;
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
		
		
		function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				var value = $("#condition").val();
				var column = $("#column").val();
				if(value=="请输入查询条件"&&"OPERATE_TIME"!=column||column==null||""==column){//没有填写查询条件,二者只要有一个没有填写
					listFunction.action = "${baseManager}/log/list.${actionExt}?page="
						+ pageNum;
					listFunction.submit();
				}else{//输入了查询条件
						if("OPERATE_TIME"!=column){
							listFunction.action = "${baseManager}/log/list.${actionExt}?page="
								+ pageNum+"&column="+column+"&value="+value;
							listFunction.submit();
						}else{
							var startTime = $("#d5221").val();
							var endTime = $("#d5222").val();
							if(""!=startTime&&""!=endTime){//始末时间都有
								listFunction.action = "${baseManager}/log/list.${actionExt}?page="
									+ pageNum+"&column=OPERATE_TIME&startTime="+startTime+"&endTime="+endTime;
								listFunction.submit();
							}else{//时间不全，和没填一样
								listFunction.action = "${baseManager}/log/list.${actionExt}?page="
									+ pageNum;
								listFunction.submit();
							}
						}
				}
			}
		}

		</script>
		 
	</head>

	<body>
		<div class="panelBox">
			<form action="#" method="post" name="listFunction" id="listFunction" class="formcss">
				
				<div class="content">

					<div class="divScrolling" id="content">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder">
							<tbody>
								<tr class="trHeader">
									<th colSpan="13" class="tdHeader">
										<div style="text-align: left;">
											<table class="user-operation" border="0">
											<input type="hidden" value="${column}" id="columnHidden">
											<input type="hidden" value="${startTime}" id="startTimeHidden">
											<input type="hidden" value="${endTime}" id="endTimeHidden">
											<tr>
												<td align="center"><font color="#4F6B72">查询字段：</font></td>
												<td align="center">
												<select id="column" name="column" onchange="showTime(this.value)" >
								   					<option></option>
								   					<option value="LOGIN_ID">登录ID</option>
								   					<option value="LOGIN_NAME" >登录名</option>
								   					<option value="OPERATE_MARK" >OPERATE_MARK</option>
								   					<option value="OPERATE_DESCRIPTION" >功能描述</option>
								   					<option value="METHOD" >method</option>
								   					<option value="CLAZZ" >clazz</option>
								   					<option value="ACTION_URL">Action URL</option>
								   					<option value="REAL_IP">IP</option>
								   					<option value="USER_AGENT">客户端信息</option>
								   					<option value="OPERATE_MESSAGE" >运行信息</option>
								   					<option value="OPERATE_TIME" >操作时间</option>
								   				</select>
		   										</td>
												<td>
													<input type="text"  id="condition" value="${empty requestScope.value ? '请输入查询条件':requestScope.value}" />
											
													<div style="display: none;" id="operateTime" >
									   				 <input id="d5221" name="startTime"  type="text"   onFocus="var d5222=$dp.$('d5222');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
													            至
													 <input id="d5222" name="endTime"  type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d5221\')}'})"/>
									   				
									   				
									   				</div>
												</td>
												<td>
													<input type="button" id="searchButton" class="search" style="background-image: url('${baseManager}/jsp/selfinfo/images/search.png'); "/>&nbsp;&nbsp;
		    										<input type="button" id="log-delete" value="删除"/>
												</td>
											</tr>
											</table>
										</div>
										
										<div class="tabletitle">
											日志信息列表
										</div>
									</th>
								</tr>
								<tr>
									<th width="10">
										<a href="javascript:void(0)" id="a_selectall" title="全选"><img
												id="check_all" border="0"
												src="${baseManager}/images/check_all.gif" width="13"
												height="13" alt="全选">
										</a>&nbsp;&nbsp;
										<a href="javascript:void(0)" id="a_deselect" title="取消"><img
												id="check_none" border="0"
												src="${baseManager}/images/check_none.gif" width="13"
												height="13" alt="取消">
										</a>
									</th>
									
									<th width="90" style="padding-left: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%" height="100%">
											<tr>
												<th class="center">登录ID</th>
											</tr>
											<tr>
												<th class="center">登录名</th>
											</tr>
										</table>							
									</th>
									
									
									<th style="padding-left: 0px">
										<table class="grid-table" cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
											<tr>
												<th colspan="2" class="center" style="word-break:break-all;">clazz</th>
											</tr> 
											<tr>
												<th class="center" width="50%">Action URL</th>
												<th class="center" width="50%">method</th>
											</tr>
										</table>
									</th>
									
									<th width="100" style="padding-left: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%" height="100%">
											<tr>
												<th class="center">Mark</th>
											</tr>
											<tr>
												<th class="center">功能描述</th>
											</tr>
										</table>							
									</th>
									
									<th width="100" style="padding-left: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%" height="100%">
											<tr>
												<th class="center">运行信息</th>		
											</tr>
											<tr>
												<th class="center">记录数</th>
											</tr>
										</table>							
									</th>
									
									<th style="padding-left: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%" height="100%">
											<tr>
												<th class="center">IP</th>
											</tr>
											<tr>
												<th class="center">客户端信息</th>
											</tr>
										</table>							
									</th>
									<th width="70" class="center">操作时间</th>
									
								</tr>
							
							<c:forEach items="${requestScope.operateLogList}" var="operateLog">
								<tr onclick="return trSelect('${operateLog.id}')" style="CURSOR: hand">
									
										
									<td>
				    					<input type="checkbox" name="choiceItem" id="choiceItem" value="${operateLog.id}" onclick="return checkbox_click()">
				    				</td>
									
									<td style="padding-left: 0px;padding-right: 0px; word-break:break-all;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%">
											<tr>
												<td>${operateLog.loginId}</td>
											</tr>
											<tr>
											<td>${operateLog.loginName}</td>
											</tr>
										</table>
									</td>
										
									<td width="300" style="padding-left: 0px;padding-right: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%">
											<tr>
												<td colspan="2" style="word-break:break-all;text-align: right;">${operateLog.clazz}</td>
											</tr>
											<tr>
												<td width="50%">${operateLog.actionUrl}</td>
												<td width="50%">${operateLog.method}</td>
											</tr>
										</table>
									</td>
									<td style="padding-left: 0px;padding-right: 0px;word-break:break-all;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%">
											<tr>
												<td>${operateLog.operateMark}</td>
											</tr>
											<tr>
											<td>${operateLog.operateDescription}</td>
											</tr>
										</table>
									</td>										
										
									<td style="padding-left: 0px;padding-right: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%">
											<tr>
												<td>${operateLog.operateMessage}</td>		
											</tr>
											<tr>
											<td>${operateLog.operateRecords}</td>
											</tr>
										</table>
									</td>
										
									<td style="padding-left: 0px;padding-right: 0px;">
										<table class="grid-table" cellPadding="0" cellSpacing="0" border="0" width="100%">
											<tr>
												<td><span style="font-weight: bold;">IP:</span>${operateLog.realIp}</td>
										</tr>
										<tr>
											<td><span style="font-weight: bold;">UserAgent:</span>${operateLog.userAgent}</td>
											</tr>
										</table>
									</td>
									<td>
										<fmt:formatDate value="${operateLog.operateTime}" 
										pattern="EEEE yyyy-MM-dd HH:mm:ss"/>
									</td>	
															
								</tr>
							</c:forEach>
							
							<%@ include file="/manager/commons/page.jsp"%>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>