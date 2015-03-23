<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<html>
<head>
   
    <%@include file="/manager/commons/meta.jsp"%>
</head>
<BODY BGCOLOR=#FCFAF3 leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table  height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="10" valign="top" background="${baseManager}/images/left_right_bg.gif">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" background="${baseManager}/images/left_right_bg.gif">
                <tr >
                    <td width="10" height="10"><IMG SRC="${baseManager}/images/left_right_jiao.gif" WIDTH=10 HEIGHT=10></td>
                </tr> 
                <tr id="id1" >
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                    	<IMG id="switchImg" SRC="${baseManager}/images/menu_switch_close.gif" WIDTH=10 HEIGHT=70 onClick="switchMenu();" 
                    	style="cursor: pointer;">
                    </td>
                </tr>
                <tr id="id2">
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script type="text/javascript">

	function switchMenu () {
		var leftFrame =  parent.document.getElementById("menuTreeFrame");
	 	leftFrame = leftFrame.contentWindow.document.getElementById("ddimTree");
		var mainWindow = window.parent.document.getElementById("widgetMain_frm");
		var btn = document.getElementById("switchImg");
		var cols = mainWindow.getAttribute("cols");
		//\menu_switch_expand.gif
		if (cols == "125,10,500") {
			mainWindow.setAttribute("cols", "0,10,500");
// 			leftFrame.className = "";//除去属性
			btn.setAttribute("src", "${baseManager}/images/menu_switch_expand.gif");
		} else {
			mainWindow.setAttribute("cols", "125,10,500");
// 			leftFrame.className='ztree';
			btn.setAttribute("src", "${baseManager}/images/menu_switch_close.gif");
		}
	}
	var th = (550-80)/2;//80是抛去中间图片和顶部小角的大小
   	th = th + "px";
    	$("#id1").css("height",th);
    	$("#id2").css("height",th);
</script>
</body>
</html>