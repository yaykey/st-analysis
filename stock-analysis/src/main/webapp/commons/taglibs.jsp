<%@ page language="java" import="com.st.Global" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${pageContext.request.serverPort eq 80}">
		<c:set var="base"
			value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
	</c:when>
	<c:otherwise>
		<c:set var="base"
			value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
	</c:otherwise>
</c:choose>
<c:set var="baseManager" value="${base}/manager"/>
<c:set var="baseLoading" value="${base}/commons/default-loading.jsp"/>
<c:set var="actionExt" value="<%=Global.ACTION_EXT%>"/>
<c:set var="appTitle" value="<%=Global.APP_TITLE%>"/>
<c:set var="resource_v" value="<%=Global.RESOURCE_V%>"/>
<!--  c:set var="platformName" value="<%//=Global.PLATFORM_NAME%>"/-->

<script language="JavaScript">
	var base = "${base}";
	var baseManager = "${baseManager}";
	var baseLoading = "${baseLoading}";
	var actionExt ="${actionExt}";
	
	var ctx ="${ctx}";	
	
	
	var loadContent = 
		'<div class="vf-loading">' +
		'<img title="loading" src="${base}/viewframe/images/loading.gif">' +
			'正在加载,请稍候' +
		'</div>';
		
		
	function loadError (opt) {
		try {
			if (opt && opt.XMLHttpRequest && opt.el) {
				//alert(opt.textStatus + ":" + opt.opt.XMLHttpRequest.status);
				if (opt.XMLHttpRequest.status == "404") {
					$(opt.el).empty();
					$(opt.el).append("<div class='error'>地址错误!</div>");
				} else if (opt.XMLHttpRequest.status == "505") {
					$(opt.el).empty();
					$(opt.el).append("<div class='error'>服务器错误!</div>");
				} else {
					$(opt.el).empty();
					$(opt.el).append("<div style='display:none;'>" +
						"XMLHttpRequest.status=" + opt.XMLHttpRequest.status +					
						"</div>");
				}
			}
		} catch (ex) {}
		
	}
</script>

<link rel="icon" href="${base}/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${base}/favicon.ico" type="image/x-icon" />