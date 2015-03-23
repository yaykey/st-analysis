<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>FAQ</title>

</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>
	
<div class="w1000 pt35 clearfix">
	
	<%
		headerType = "faq";
	%>
	<!-- left -->
	<%@include file="/homepage/jsp/apidocs/left.jsp"%>
	
    <div class="help_con">
    	<h2>SDK logo数据包发送策略？</h2>
        <p>每次启动时发送数据包，本次产生的所有数据在下次启动时发送；在wifi状态下发送，非wifi联网情况下缓存到本地。</p>
        <h2>SDK安装后多久能看到数据？</h2>
        <p>成功安装并发送数据包后的第二日可看到前一日数据。</p>
        <h2>如何识别一个用户？</h2>
        <p>一个设备即一个用户。Android系统通过IMEI号+MAC地址标识设备的唯一性。</p>
        <h2>如何判断一次新的启动？</h2>
        <p>用户启动应用之后，退出应用或应用置于后台运行，30s内再次启动，算作同一次启动，30s后再次启动，则认为是一次新的启动。</p>
        <h2>如何区分不同渠道数据？</h2>
        <p>通过在代码中调用接口设置或在Manifest.xml配置不同的渠道市场。</p> 
	<br/><br/> 
    </div>
</div>
<!--FAQ end -->

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>
	
<script>
$(function(){
	//导航
	/* $(".nav li").click(function(){
		$(".nav li").removeClass("cur");
		$(this).addClass("cur");			                                  												
	}); */
	
	//左侧导航
	$(".sidebar dl").click(function(){
		$(this).addClass("select").siblings().removeClass("select")
	})
	$(".sidebar dl dd").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		$(this).parent().siblings().find("dd").removeClass("cur");
	});
});
</script>
</body>
</html>
