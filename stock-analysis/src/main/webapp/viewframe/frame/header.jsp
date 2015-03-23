<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.st.framework.module.*"%>
<div id="vf_header" class="hd clearfix">
	<h1 class="logo" style="margin-top: 0;" title="综合数据应用平台">综合数据应用平台</h1>
	<ul class="nav clearfix">
	
		<%
			List<Role> roles = userSessionInfo.getRoles();
			boolean isManager = false;
			for (Role r:roles) {
				if ("backType".equals(r.getViewType())) {
					isManager = true;
					break;
				}
			}
		%>
		<c:forEach items="${requestScope.kpiGraphList}" var="function">
			<li class="">				
				<em>
					<a target="_blank" href="${base}/${function.actionUrl}">${function.name}</a>
				</em>
			</li>
		</c:forEach>
		
		<c:if test='<%=isManager || ("admin".equals(userSessionInfo.getLoginId()))%>'>
			<li class="">
				<i class="icon home"></i>
				<em>
					<a target="_blank" href="${baseManager}/main.${actionExt}">管理后台</a>
				</em>
			</li>
		</c:if>
		
		
		
		<%-- <li id="vf_home_li" class='nav-tab ${(loadType eq "homeReport") ? " on" : ""}' style="display:none;">
			<em>
				<form id="homeReportFrm" class="homeReportFrm" action="${base}/main/index.feinno" method="POST">
					<input type="hidden" name="loadType" value="homeReport">
					<input type="submit" value="" style="display:none;">
					<i class="icon home"></i>
					<a id="vf_home" href="javascript:void(0);">我的首页</a>
				</form>				
			</em>
		</li> --%>
		
		<!--
		<li>
			<i class="icon help"></i>
			<em>
				<a href="#">帮助</a>
			</em>
		</li>
		-->
		<li style="width__:104px;" id="vf_setting_li" class='nav-tab ${(loadType eq "setting") ? " on" : ""}'>
			<em>
				<form id="homeReportFrm" class="homeReportFrm" action="${base}/main/index.feinno" method="POST">
					<input type="hidden" name="loadType" value="setting">
					<input type="submit" value="" style="display:none;">
					<a id="vf_setting" href="javascript:void(0);">${userSessionInfo.userInfo.username}</a>
				</form>		
				
			</em>
		</li>
		<li>
			<a target="_self" href="${base }/logout.${actionExt}" class="quit">退出</a>
		</li>
	</ul>
</div>
<script language="JavaScript">
	$(document).ready(function() {
		var linkLis = $("#vf_header").find("ul.nav").find("li:not(.on)");
		linkLis.bind("mouseover", function(){
			$(this).addClass("on");
		});
		
		linkLis.bind("mouseout", function(){
			$(this).removeClass("on");
		});
		
		//-------我的首页-------//
		$("#vf_home_li").bind("click", function(){
			var homeFrm = $(this).find("form");
			homeFrm.submit();
		});
		
		//-------当前登录用户-------//
		$("#vf_setting_li").bind("click", function(){
			var homeFrm = $(this).find("form");
			homeFrm.submit();
		});
		
		/* //-------我的首页-------//
		$(".logo").bind("click", function(){
			showMap();
		}); */
		
		/* 
		var homelURL = "/viewframe/home/list.feinno";
		$("#vf_home").bind("click", function(){
			$("#vf_content").append("数据加载中!");
			
			jQuery.ajaxSetup({ cache: true });
			jQuery.ajax({
				type : "GET",
				url : base + homelURL,
				dataType : 'text',
				data : {
					"type" : "homeType"
				},
				success : function(response) {
					$("#vf_content").empty();
					$("#vf_content").append(response);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					var content = "";
					if (XMLHttpRequest.status == "404") {
						content = "<div>地址错误!</div>";
					} else if (XMLHttpRequest.status == "500") {
						content = "<div>服务器错误!</div>";
					} else {
						content = textStatus + ":" + errorThrown
					}
					
					$("#vf_content").empty();
					$("#vf_content").append(content);
				}
			});
		}); */
		
	});
</script>
<!-- //hd -->