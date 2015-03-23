<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%
	String headerType = request.getParameter("headerType");
	
	if (headerType == null) {
		headerType = "index";
	}

%>

<style>

.top p a {
    float: left;
    height: 28px;
    line-height: 28px;
    margin: 0 5px;
    text-align: center;
    width: 59px;
}

.top .bar a.register,
.top .bar a.login {   
    background-position: left top;
    color : #3b6da7;
}


.top .bar a.select {
	background-position: left bottom;
	color : #fff;
}

.top .bar a:HOVER {
	background-position: left bottom;
	color : #fff;
}

</style>

<div class="top">
	<h1>
		<!-- <a href="javascript:void(0);"> -->
			<img src="${base}/homepage/images/logo.png" />
		<!-- </a> -->
	</h1>
	
	<ul class="nav">
		<%-- <a href="${base }/index.jsp?headerType=index" target="_self">
			<li class='<%=("index".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			首页
			</li>
		</a> --%>
		<a href="javascript:void(0);" onclick='$(this).find("form").submit();'>
			<li class='<%=("index".equalsIgnoreCase(headerType))?("cur"):("")%>'>
				首页
				<form action="${base }/index.jsp" target="_self" method="post">
					<input type="hidden" name="headerType" value="index">
					<input type="submit" value="" style="display: none;">
				</form>
			</li>
		</a>
		
		<a href="${base }/homepage/jsp/intruduction.jsp?headerType=intruduction" target="_self">
			<li class='<%=("intruduction".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			功能介绍
			</li>
		</a>
		<a href="${base }/homepage/jsp/application.jsp?headerType=application" target="_self">
			<li class='<%=("application".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			行业应用
			</li>
		</a>
		<a href="${base }/homepage/jsp/vip.jsp?headerType=vip" target="_self">
			<li class='<%=("vip".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			VIP服务
			</li>
		</a>
		<a href="${base }/homepage/jsp/apidocs.jsp?headerType=apidocs" target="_self">
			<li class='<%=("apidocs".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			帮助文档
			</li>
		</a>
		<a href="${base }/homepage/jsp/download.jsp?headerType=download" target="_self">
			<li class='<%=("download".equalsIgnoreCase(headerType))?("cur"):("")%>'>
			SDK下载
			</li>
		</a>
			
	</ul>
	<p class="bar">
		<a href="${base }/homepage/jsp/login.jsp?headerType=login" target="_self" 
			class='<%=("login".equalsIgnoreCase(headerType))?("login select"):("login")%>'>登录</a>
		<a href="${base }/homepage/jsp/register.jsp?headerType=register" target="_self" 
			class='<%=("register".equalsIgnoreCase(headerType))?("register select"):("register")%>'>注册</a>
	</p>
</div>
<!--top end -->

