<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link href="${base}/manager/css/main_log.css" rel="stylesheet" type="text/css" />
	<link href="${base}/manager/css/css_log.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<c:url value="${base}/manager/jsp/user/css/layout.css"/>">
	
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
		$(document).ready(function(){
			var res = eval('(' + '${onlineUser}' + ')');
			
			
			function loadData (items) {
				
				
				
				var el = $("#content");
				
				var content = "";
				
				
				for (var i=0; i<items.length; i++) {
					var item = items[i];
					var body = item.body;
					var result = item.result;
					
					//alert(body);
					//alert(body.online_user_list);
					//alert(body.online_user_list.length);
					
					var onlineUsers = body.online_user_list;
					content += 
					'<div style="padding:3px; margin:20px 0px; border: 1px solid #8db3e2;">' + 
						'<div style="padding:2px 2px;">';
						
					content += 
						'<table width="100%" border="0" cellspacing="1" cellpadding="1" class="tableborder">' +
							'<tr>' +
								'<th width="130">服务器IP</th>' + 
								'<th>服务器启动时间</th>' + 
								'<th width="130">历史登录用户数</th>' + 
								'<th width="130">最大在线用户数</th>' + 
								'<th>最大在线用户数时间</th>' + 
								'<th width="130">当前在线用户数</th>' + 
							'</tr>' +
							'<tr>' +
								'<td>' + item.ip + '</td>' + 
								'<td>' + body.start_date + '</td>' + 
								'<td>' + body.total_history_count + '</td>' + 
								'<td>' + body.max_online_count + '</td>' + 
								'<td>' + body.max_online_count_date + '</td>' + 
								'<td>' + body.current_login_count + '</td>' + 
							'</tr>' + 
						'</table>';
						
					content += 
						'</div>' + 
						'<div style="padding:3px;">';
					content += 
						'<table width="100%" border="0" cellspacing="1" cellpadding="1" class="tableborder">';
												
					content += 
						'<tr>'+
							'<th>SESSIONID</th>'+
							'<th>登录ID</th>'+
							'<th>用户名</th>'+
							'<th>登录时间</th>'+
						'</tr>';
					
					for (var j=0; j<onlineUsers.length; j++) {
						var onlineUser = onlineUsers[j];
						
						content += 
						'<tr>'+
							'<td>' + onlineUser.id + '</td>'+
							'<td width="130">' + onlineUser.loginId + '</td>'+
							'<td width="130">' + onlineUser.userName + '</td>'+
							'<td>' + onlineUser.creationTime + '</td>'+
						'</tr>';
						
					}
					
					content += 
						'</table>' + 
						'</div>' + 
					'</div>';
				}
				
				
					
				el[0].innerHTML = content;
			}
			
			loadData(res);
		});
	</script>
		 
	</head>

	<body>
	<div class="panelBox">
		<form action="#" method="post" name="listFunction" id="listFunction"
			class="formcss">
			<div class="content">
				<div class="divScrolling" id="content"></div>
			</div>
		</form>
	</div>
</body>
</html>