<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>下载</title>

</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>

<div class="down_con">
	<img src="${base }/homepage/images/down_img.png" class="down_img"/>
    <ul class="mt84 clearfix">
    	<li class="fl">
        	<a href="${base}/homepage/sdk/AndroidSdk.rar" class="android_btn">
        		<img src="${base }/homepage/images/android_btn.png" />
        	</a>
            <span>Android 版本号：1.0.1</span>
            <a href="${base }/homepage/jsp/apidocs.jsp?headerType=apidocs&apiType=apibase" target="_self">Android 开发指南</a>
            <a href="#">Android Demo 查看</a>
        </li>
        <li class="fr">
        	<a href="#" class="default"><img src="${base }/homepage/images/ios_btn.png" /></a>
        	<span>敬 请 期 待  . . .</span>
        </li>
    </ul>
</div>
<!--下载 end -->


	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>

<!-- <script>
$(function(){
	//导航
	$(".nav li").click(function(){
		$(".nav li").removeClass("cur");
		$(this).addClass("cur");			                                  												
	});		   
});
</script> -->
</body>
</html>
