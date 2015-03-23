<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/manager/commons/meta.jsp"%>
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
		if(time <= 3){
			document.getElementById("time").innerHTML = (3 - time);
		}		
	}
	time++;
}
function redirect1() {
		if (top != window) {
			top.location.href = loginUrl;
		} else {
			document.location.href = loginUrl;
		}

	
}
$(document).ready(function() {
	setInterval(redirect, 1000);
});
</script>
	<body>
	<div class="panelBox">
		<form name="editRole" id="editRole" method="POST" class="formcss">
		<s2:token name="role.token"/>
		<input type="hidden" name="id" id="id" value="${requestScope.id}"/>
			<div class="content">
				<div class="content_title_bg">
				<div class="hideleft"  id="openleft"></div>
<!-- 				<div class="content_title">会话过期警告</div> -->
			</div>
			<div class="nosessionClass" id="content" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tab" class="table_new">
				<tbody>
				   <tr>
					<td align="center" class="tableborder_add">
						<div class="sessionContentDiv">
							<table>
								<tr>
									<td><span  class="loginText">喵喵!登录超时了!起来重新登录吧</span>~~</td>
								</tr>
								<tr>
								
		        				<td align="center">
									<span id="time" class="loginText" style="color: red">3</span>
									<a href="javascript:void(0);" class="loginText" target="_self" onclick="redirect1();">秒后跳转综合数据应用平台<font color="red">登录</font>首页</a>
								</td>
								</tr>
							</table>
						</div>
													
					
					</td>
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