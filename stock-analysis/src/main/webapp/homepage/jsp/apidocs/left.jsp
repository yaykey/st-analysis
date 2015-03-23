<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String apiType = request.getParameter("apiType");

	if (apiType == null) {
		apiType = "apibase";
	}

%>
<div class="sidebar">
	<dl class="">
		<dt>
			<a href="#">接入指南</a>
		</dt>
		<dd class='<%=("apibase".equalsIgnoreCase(apiType))?("cur"):("")%>'>
			<a href="${base }/homepage/jsp/apidocs.jsp?headerType=apidocs&apiType=apibase" target="_self">基础接入</a>
		</dd>
		<dd>
			<a href="#">高级功能</a>
		</dd>
	</dl>
	<dl>
		<dt>
			<a href="#">高级功能</a>
		</dt>
		<dd>
			<a href="#">自定义事件</a>
		</dd>
		<dd>
			<a href="#">漏斗模型</a>
		</dd>
		<dd>
			<a href="#">错误管理</a>
		</dd>
		<dd>
			<a href="#">其他</a>
		</dd>
	</dl>
	<dl>
		<dt>
			<a href="#">报表解读</a>
		</dt>
	</dl>
	<dl class="apidocs-faq">
		<dt>
			<a href="${base }/homepage/jsp/faq.jsp?headerType=apidocs" target="_self">FAQ</a>
		</dt>
	</dl>
</div>

<script type="text/javascript">
$(function(){
	
	var headerType = "<%=headerType%>";
	
	if ("faq" == headerType) {
		$(".sidebar dl.apidocs-faq").addClass("select");
		
		
	} else {
		$($(".sidebar dl")[0]).addClass("select");
	}
	
	$(".sidebar dl.apidocs-faq").bind("click",function(){
		
		var linkNode = $(this).find("a")[0];
		window.open($(linkNode).attr("href"), $(linkNode).attr("target"));
		
	});
	
	//左侧导航
	$(".sidebar dl").click(function(){
		$(this).addClass("select").siblings().removeClass("select")
	});
	
	
	$(".sidebar dl dd").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		$(this).parent().siblings().find("dd").removeClass("cur");
	});
});
</script>