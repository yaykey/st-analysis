<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.zip.CRC32"%>
<%@ page import="com.st.framework.utils.encrypt.impl.EncryptorBase64Impl"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/commons/jquerylibs.jsp"%>
<%@ page import="com.feinno.fdc.report.framework.controller.UserSessionInfo"%>
<%
	UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute("userSessionInfo");
	Integer userId = userSessionInfo.getUserInfo().getId();
	//Long mobileNo = userSessionInfo.getUserInfo().getMobileNumber();
	//String emailAddress = userSessionInfo.getUserInfo().getEmailAddress();
	//String realname = userSessionInfo.getUserInfo().getName();
	
	Long mobileNo = 0L;
	String emailAddress = "";
	String realname = "";
	
	Integer mid = (Integer)request.getAttribute("mid");
	Integer fid = (Integer)request.getAttribute("fid");
	String parentid = (String)request.getAttribute("parentid");
	String dimTypeId = (String)request.getAttribute("dimTypeId");
	String dimId = (String)request.getAttribute("dimId");
	String photoType = (String)request.getAttribute("photoType");
	String photoId = (String)request.getAttribute("photoId");

	EncryptorBase64Impl base64 = new EncryptorBase64Impl();
	
	//String c = userId + "_" + mobileNo + "_" + new Date().getTime();
	String privRols = "";
	String c = "";
	//for(int i=0;i<userSessionInfo.getRoles().size();i++){
	//	if(userSessionInfo.getRoles().get(i).getId()>900&&userSessionInfo.getRoles().get(i).getId()<932){
	//		privRols+=userSessionInfo.getRoles().get(i).getId()+",";
	//	}
	//}
		
	//c = base64.encrypt(c);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${appTitle}</title>
<link href="${base }/viewframe/css/style.css?v=<%=Global.RESOURCE_V%>" rel="stylesheet" type="text/css" />
</head>
<body class="body_bg">
	
	<!-- header -->
	<%@include file="/viewframe/frame/header.jsp"%>
	
	<div class="box clearfix">
	
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td id="frm-left-td" style="padding-left:10px; vertical-align: top; min-width: 15px;">
					<!-- left -->
					<%@include file="/viewframe/frame/left.jsp"%>
				</td>				
				<td style="padding-left:3px; padding-right:10px; vertical-align: top; width:100%;">
					<!-- main -->
					<%@include file="/viewframe/frame/main.jsp"%>
				</td>
			</tr>
		</table>
	
		
	
		
		
		<!-- footer -->
		<%@include file="/viewframe/frame/footer.jsp"%>
	</div>
	<div id="mapProgress" style="display:none;">
		<div style='background:url(${base}/map/image/bg/bg.jpg) no-repeat;background-color:#00379C;width:1000px;height:700px;position:'>
			<div style='background:url(${base}/map/image/progress_2.gif) no-repeat;width:95px;height:95px;position:absolute;left:420px;top:200px;'></div>
		</div>
	</div>
	<!-- //box -->
	<script language="JavaScript">
	
	</script>

</body>
</html>