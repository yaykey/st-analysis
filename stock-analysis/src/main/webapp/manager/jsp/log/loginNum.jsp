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
			
			.panelBox {
			
			}
			
			.panelBox .search{
				background-image: url("${baseManager}/jsp/selfinfo/images/search.png");
			    background-position: center center;
			    background-repeat: no-repeat;
			    width: 50px;
			    margin-left:20px;
			}
			
	</style>
		
	</head>

	<body>
		<div id="loginNum" class="panelBox">
			<div>
				<h2>登录统计TOP100</h2>
			</div>
			<form action="${baseManager}/log/loginNum.${actionExt}" method="post" name="listFunction" id="listFunction" class="formcss">
				
				<div class="content">

					<div class="divScrolling" style="margin-bottom: 5px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
						id="tab" class="tableborder">						
							<tr>
								<th>
								
									<div id="operateTime" style="float:left;">
					   				 	<input id="login_log_startDate" name="startDate"  type="text"   onFocus="var login_log_endDate=$dp.$('login_log_endDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){login_log_endDate.focus();},maxDate:'#F{$dp.$D(\'login_log_endDate\')}'})"/>
									            至
									 	<input id="login_log_endDate" name="endDate"  type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'login_log_startDate\')}'})"/>
					   				
					   				</div>
									<input type="button" id="searchButton" class="search" />	
								</th>
							</tr>
						</table>
					</div>

					<div class="divScrolling">
					
					
					
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder">
							<tbody>
																
								<tr>
									<th width="50" class="center">No.</th>
									<th class="center">登录ID</th>
									<th class="center">登录名</th>
									<th style="text-align: right;"><div id="PV_H">PV</div></th>
									<th style="text-align: right;"><div id="UV_H">UV</div></th>
									<th width=180 class="center">最后操作时间</th>
								</tr>
								
							
							<c:forEach items="${requestScope.list}" var="bean" varStatus="vstatus">
								<tr>
									<td align="center">${vstatus.index+1}</td>
				    				<td align="center">${bean.loginId}</td>
				    				<td align="center">${bean.loginName}</td>
									<td align="right"><div class="PV">${bean.pv}</div></td>
									<td align="right"><div class="UV">${bean.uv}</div></td>
									<td align="center"><fmt:formatDate value="${bean.lastOperateTime}" 
										pattern="EEEE yyyy-MM-dd HH:mm:ss"/></div></td>
									
								</tr>
							</c:forEach>
							
							
							</tbody>
						</table>
						
					</div>

				<div class="divScrolling" style="margin-top: 10px">
					
						<table width="32%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder" style="float: left;">
							<tbody>

								<tr>
									<th width="50" class="center">No.</th>
									<th class="center">浏览器</th>
									<th class="center">使用数</th>
								</tr>


								<c:forEach items="${requestScope.browserMappingList}" var="bean"
									varStatus="vstatus">
									<tr>
										<td align="center">${vstatus.index+1}</td>
										<td align="center">${bean.key}</td>
										<td align="center">${bean.value}</td>

									</tr>
								</c:forEach>


							</tbody>
						</table>
					
						<table width="32%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder" style="float: left;margin-left:5px;">
							<tbody>

								<tr>
									<th width="50" class="center">No.</th>
									<th class="center">操作系统</th>
									<th class="center">使用数</th>
								</tr>


								<c:forEach items="${requestScope.osMappingList}" var="bean"
									varStatus="vstatus">
									<tr>
										<td align="center">${vstatus.index+1}</td>
										<td align="center">${bean.key}</td>
										<td align="center">${bean.value}</td>

									</tr>
								</c:forEach>


							</tbody>
						</table>
						
						<table width="32%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder" style="float: left;margin-left:5px;">
							<tbody>

								<tr>
									<th width="50" class="center">No.</th>
									<th class="center">IP</th>
									<th class="center">使用数</th>
								</tr>


								<c:forEach items="${requestScope.ipMappingList}" var="bean"
									varStatus="vstatus">
									<tr>
										<td align="center">${vstatus.index+1}</td>
										<td align="center">${bean.key}</td>
										<td align="center">${bean.value}</td>

									</tr>
								</c:forEach>


							</tbody>
						</table>						
				</div>

				<!-- <div class="divScrolling" style="margin-top:20px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder">							
							<tr>
								<td></td>
								<td>时间(天)</td>
								<td>人员</td>
								<td>PV</td>
								<td>UV</td>
								<td>PV/UV</td>
							</tr>
							<tr>
								<td>总数</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>总数(除六日)</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>总数(六日)</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
								
						</table>
					</div> -->
					
					<div class="divScrolling" style="margin-top:20px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder">							
							<tr>
								<td>PV</td>
								<td>
								PV的英文是Page View的缩写，意思是“页面访问量”。用户每次刷新被计算一次pv。 
								</td>
							</tr>
							<tr>
								<td>UV</td>
								<td>
								UV的全称是Unique Visitor，意思是“独立访问者”。访问您网站的一台电脑客户端为一个访客。1小时之内，同一地址，多次访问，只算一次。 
								</td>
							</tr>
								
						</table>
					</div>
					
				</div>
			</form>
		</div>
	</body>
	
	<script type="text/javascript">
$(document).ready(function(){
	
	var root = $("#loginNum");
	
	var pvsNum=0,uvsNum=0;
		
	root.find(".PV").each(function(){
		pvsNum += parseInt($(this).text());
	});
	
	root.find(".UV").each(function(){
		uvsNum += parseInt($(this).text());
	});
	
	$("#PV_H").text("PV(" + pvsNum + ")");
	$("#UV_H").text("UV(" + uvsNum + ")");
	
	$("#login_log_startDate").val('${startDate}');
	$("#login_log_endDate").val('${endDate}');
	
	//点击查询
	$("#searchButton").click(function(){
		 
		//var startDate = $("#login_log_startDate").val();
		//var endDate = $("#login_log_endDate").val();
		
		//$('#listFunction').attr('action', '${baseManager}/log/loginNum.${actionExt}?startDate=' + startDate+'&endDate='+endDate);
		$('#listFunction').submit();
	});
});
	</script>
</html>