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
				<h2>报表统计</h2>
			</div>
			<form action="${baseManager}/log/functionVisitor.${actionExt}" method="post" name="listFunction" id="listFunction" class="formcss">
				
				<div class="content">

					<div class="divScrolling" style="margin-bottom: 5px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
						id="tab" class="tableborder">						
							<tr>
								<th>
								
									<div id="operateTime" style="float:left;">
					   				 	<input id="function_visitor_startDate" name="startDate"  type="text"   onFocus="var function_visitor_endDate=$dp.$('function_visitor_endDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){function_visitor_endDate.focus();},maxDate:'#F{$dp.$D(\'function_visitor_endDate\')}'})"/>
									            至
									 	<input id="function_visitor_endDate" name="endDate"  type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'function_visitor_startDate\')}'})"/>
					   				
					   				</div>
									<input type="button" id="searchButton" class="search" />	
								</th>
							</tr>
						</table>
					</div>

					<div class="divScrolling">
					
					
					
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder" style="">
							<tbody>
								<tr>
									<td colspan="7">
										<h2>常用报表top100</h2>
									</td>
								</tr>
								<tr>
									<th width="30" class="center">No.</th>
									<th width="100" class="center">分类</th>
									<th width="35" class="center">报表ID</th>
									<th class="center">报表名称</th>
									<th class="center"><div id="PV_H">PV</div></th>
									<th class="center"><div id="UV_H">UV</div></th>
									<th width=180 class="center">最后操作时间</th>
								</tr>
								
							
							<c:forEach items="${requestScope.listTop}" var="bean" varStatus="vstatus">
								<tr>
									<td align="center">${vstatus.index+1}</td>
									<td align="center">${bean.mname}</td>
				    				<td align="center">${bean.fid}</td>
				    				<td align="center">${bean.fname}</td>
									<td align="center"><div class="PV">${bean.pv}</div></td>
									<td align="center"><div class="UV">${bean.uv}</div></td>
									<td align="center"><fmt:formatDate value="${bean.lastOperateTime}" 
										pattern="EEEE yyyy-MM-dd HH:mm:ss"/></div></td>
									
								</tr>
							</c:forEach>
							
							
							</tbody>
						</table>
					</div>

				
					
					<div class="divScrolling" style="margin-top:20px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="1"
							id="tab" class="tableborder" style="">
							<tbody>
								<tr>
									<td colspan="7">
										<h2>不常用报表top100</h2>
									</td>
								</tr>
								<tr>
									<th width="30" class="center">No.</th>
									<th width="100" class="center">分类</th>
									<th width="35" class="center">报表ID</th>
									<th class="center">报表名称</th>
									<th class="center"><div id="PV_H">PV</div></th>
									<th class="center"><div id="UV_H">UV</div></th>
									<th width=180 class="center">最后操作时间</th>
								</tr>
								
							
							<c:forEach items="${requestScope.listBottom}" var="bean" varStatus="vstatus">
								<tr>
									<td align="center">${vstatus.index+1}</td>
									<td align="center">${bean.mname}</td>
				    				<td align="center">${bean.fid}</td>
				    				<td align="center">${bean.fname}</td>
									<td align="center"><div class="PV">${bean.pv}</div></td>
									<td align="center"><div class="UV">${bean.uv}</div></td>
									<td align="center"><fmt:formatDate value="${bean.lastOperateTime}" 
										pattern="EEEE yyyy-MM-dd HH:mm:ss"/></div></td>
									
								</tr>
							</c:forEach>
							
							
							</tbody>
						</table>
						
					</div>

				
					
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
	
	
	
	$("#function_visitor_startDate").val('${startDate}');
	$("#function_visitor_endDate").val('${endDate}');
	
	//点击查询
	$("#searchButton").click(function(){
		 
		//var startDate = $("#function_visitor_startDate").val();
		//var endDate = $("#function_visitor_endDate").val();
		
		//$('#listFunction').attr('action', '${baseManager}/log/functionVisitor.${actionExt};
		$('#listFunction').submit();
	});
});
	</script>
</html>