<%@page import="com.st.framework.controller.UserSessionInfo"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String url = (String) request.getAttribute("url");
	String loadType = (String)request.getAttribute("loadType");
	
	//System.out.println("main.jsp->url=" + url);
%>
    <script src="${base}/js/jquery-messager/yanue.pop.js"></script>
   	<link type="text/css" href="${base }/report/jsp/bmpmonitor/css/layout.css" rel="stylesheet"  />
<style>
<!--
.vf-loading {
	overflow: hidden;
	padding: 2px;
	height : 250px;
	background__: #f9f9f9;
	padding-top: 90px;
	text-align: center;
	color: #999999;
}
-->
</style>

<div id="vf_main" class="main">
	<div id="vf_nav" class="path"></div>
	<div id=vf_content></div>
	<div id="vf_user_defined" style="display:none;">
		<iframe id="frmUserDefined" name="frmUserDefined" frameborder="0" 
				scrolling="auto" marginheight="0" marginwidth="0"
				width="100%" height="1200"
				>					
		</iframe>
	</div>
		
</div>


<script language="JavaScript">
$(document).ready(function() {
	
	var mid = "<%=mid%>";
	var fid = "<%=fid%>";
	var parentid = "<%=parentid%>";
	var dimTypeId = "<%=dimTypeId%>";
	var dimId = "<%=dimId%>";
	var photoType = "<%=photoType%>";
	var photoId = "<%=photoId%>";

	<%-- //var pid = "<%=pid%>";
	//var isLeaf = "<%=isLeaf%>"; --%>
	
	var loadType="<%=loadType%>";
	//alert(loadType);
	//alert("fid=" + fid + ":mid=" + mid + ":pid=" + pid + ":isLeaf=" + isLeaf);
	var fNode,mNode,loadURL;
	
	if (loadType == "homeReport") {
		loadURL = "/viewframe/home/list.feinno";
		
		var navContent = "";
		
		navContent += '<span>我的首页</span>';
		
		$("#vf_nav").empty();
		$("#vf_nav").append(navContent);
	} else if (loadType == "setting") {
		
		$("#vf_nav").empty();
		
		$("#vf_nav").empty();
// 		$("#vf_nav").load( base + '/viewframe/account/myself.jsp');修改添加一个时间撮，让浏览器不要从缓存里读取数据，而且从服务器去请求
		$("#vf_nav").load( base + '/viewframe/account/myself.jsp',{"ssssss" : new Date()});
	} else if ((!$.trim(mid) || !$.trim(fid) || mid == "null" || fid == "null")) {
		var defaultGroup = $("#nav-tree").find("dl.nav-report-group")[0];
		
		//alert(defaultGroup);
		
		mid = parseInt($(defaultGroup).attr("id"));
		
		var liNode = $(defaultGroup).find("li")[0];
		$(liNode).addClass("on");
		
		$(liNode).unbind("mouseover");
		$(liNode).unbind("mouseout");
		
		
		
		fid = parseInt($(liNode).attr("id"));
		
		fNode = $("#" + fid + "_f_li");
		mNode = $("#" + mid + "_m_dl");
		
		var spanNode = mNode.find("dt.nav-report-group-title").find("span");
		spanNode.removeClass("shut");
		spanNode.addClass("ope");
		
		var ddNode = mNode.find(".nav-report-list");
		
		ddNode.show();
		
		
		loadURL = fNode.attr("url");
		
		loadNav();		
	} else {
		loadNav();
	}
	
	//alert("mid=" + mid + ":fid=" + fid);
	
	
	
	
	
	function loadNav () {
		if(loadType != "setting"){
			var navContent = "";
			
			fNode = $("#" + fid + "_f_li");
			mNode = $("#" + mid + "_m_dl");
			loadURL = fNode.attr("url");
			var mName = $.trim(mNode.find(".nav-report-group-title").find("span").text());
			var fName = $.trim(fNode.find("a").text());
			
			
			navContent += '<span>' + mName + '</span> > ';
			
			var pid = fNode.attr("pid");
			var isLeaf = fNode.attr("leaf");
			
			if (isLeaf == 1 && pid != null && "null" != pid) {
				var pLiNode = $("#" + pid + "_f_li");
				var pNmae = pLiNode.find(".nav-title").text();
				navContent += '<a target="_self" href="' + base + '/main/change.feinno' + '?mid=' + mid + '&fid=' + pid + '">' + pNmae + '</a> > ';
			}
			
			//navContent += '<a target="_self" href="' + base + '/main/index.feinno' + '?mid=' + mid + '&fid=' + fid + '">' + fName + '</a>';
			navContent += '<span>' + fName + '</span>';
			
			$("#vf_nav").empty();
			$("#vf_nav").append(navContent);
			$("#vf_nav").append("<a id='indexask' class='ask' href='javascript:void(0);'></a>");
		} else {
			
			/* $("#vf_nav").empty();
			$("#vf_nav").load( base + '/viewframe/account/myself.jsp'); */
			
		}
	}
	
	
	if(loadType != "setting"){
		
				
		//$("#vf_content").append("数据加载中!");
		$("#vf_content").append(loadContent);
		
		
		
		 if ($.trim(loadURL)) {
			jQuery.ajaxSetup({ cache: true });
			jQuery.ajax({
				type : "GET",
				url : base + loadURL,
				dataType : 'text',
				data : {
					"fid" : fid,
					"mid" : mid,
					"parentid" : parentid,
					"dimTypeId" : dimTypeId,
					"dimId" : dimId,
					"photoType" : photoType,
					"photoId" : photoId
					//,"nocache" : new Date().getTime()
				},
				success : function(response) {
					window.setTimeout(function(){
						$("#vf_content").empty();
						$("#vf_content").append(response);
					},1);
					var pop=new Pop();
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					var content = "";
					if (XMLHttpRequest.status == "404") {
						content = "<div>地址错误!</div>";
					} else if (XMLHttpRequest.status == "500") {
						content = "<div>服务器错误!</div>";
					} else {
						//content = textStatus + ":" + errorThrown
						content = 
							"<div style='display:none;'>" +
								"XMLHttpRequest.status=" + XMLHttpRequest.status +
								";textStatus=" + textStatus +
								";errorThrown=" + errorThrown +
							"</div>";
					}
					
					$("#vf_content").empty();
					$("#vf_content").append(content);
				}
			});
		} else {
			$("#vf_content").empty();
			$("#vf_content").append("<div>地址为空!</div>");
		}
	}
	
	
	
	
	
});
</script>
<!-- //main -->