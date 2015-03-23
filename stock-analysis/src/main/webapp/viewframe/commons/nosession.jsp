<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<%@include file="/commons/taglibs.jsp"%>
<%@include file="/commons/jquerylibs.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>综合数据应用平台-首页</title>
	<link href="${base }/viewframe/css/style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	var time = 0;
	var loginUrl = '${base}/homepage/jsp/login.jsp';//系统登录地址
	function redirect() {
		if (time == 3) {
			if (top != window) {
				top.location.href = loginUrl;
			} else {
				document.location.href = loginUrl;
			}

		} else {
			if (time <= 3) {
				document.getElementById("time").innerHTML = (3 - time);
			}
		}
		time++;
	}
	$(document).ready(function() {
		setInterval(redirect, 1000);
	});
</script>
<body>

	<div id="vf_header" class="hd clearfix">
		<h1 class="logo" title="综合数据应用平台">综合数据应用平台</h1>
	</div>

	<div class="data_box">
		<form name="editRole" id="editRole" method="POST" class="formcss">
			<s2:token name="role.token" />
			<input type="hidden" name="id" id="id" value="${requestScope.id}" />
			<div class="content">
				<div class="title2 bno">
					<h2>会话过期警告</h2>
				</div>
				<div class="divScrolling" id="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tab" class="table_new">
						<tbody>
							<tr>
								<td align="center" class="tableborder_add">
									<table width="80%" align="center" border="0" cellspacing="0"
										cellpadding="0">
										<tr>
											<td align="center" width="29%">
												<center>
													由于长时间不操作 会话已经失效，为了保证您能正常使用 <br />
													<span id="time">3</span>秒后自动跳转到综合数据应用平台登录首页
													<center>
										</tr>
									</table></td>
							</tr>
						</tbody>
					</table>

					<br>
				</div>
			</div>
		</form>
	</div>
</body>
</html>