<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<html>
<head>
   
    <%@include file="/manager/commons/meta.jsp"%>
</head>
<body  BGCOLOR=#FCFAF3 leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="130"><IMG SRC="${baseManager}/images/icrm_logo.gif" WIDTH=130 HEIGHT=50></td>
        <td background="${baseManager}/images/top_bg.gif">
        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="5">
            <tr>
                <td nowrap class="top-info">&nbsp;</td>
                <td nowrap class="pop-window">
                	<div align="right"> 当前用户：${userSessionInfo.userInfo.username}</div>
                </td>
                <td nowrap class="top-info">&nbsp;</td>
                <td nowrap class="pop-window" width=60>
                	<div align="center">
                		<a href="${base}/main/index.feinno" target="_blank">前台页面</a>
                	</div>
                </td>
                <td nowrap class="pop-window" width=50>
                	<div align="center">
                		<a href="javascript:void(0);" onclick="redirect();" target="_self">退出</a>
                	</div>
                </td>
                <td nowrap class="top-info">&nbsp;</td>
            </tr>
        </table>
        </td>
    </tr>
</table>
<script type="text/javascript">
	function redirect() {
		var loginUrl = '${base}/logout.feinno';//系统登录地址
		if (top != window) {
			top.location.href = loginUrl;
		} else {
			document.location.href = loginUrl;
		}
	}
</script>
</body>
</html>