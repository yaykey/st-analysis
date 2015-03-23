<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<html>
<head>
   
    <%@include file="/manager/commons/meta.jsp"%>

</head>
<BODY BGCOLOR=#FCFAF3 leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
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
<!-- 刚开始的时候，这段代码写在了最上面，
结果发现诡异的现象：结果是实际高度的一半，而且，如果前面不添加alert(),则js脚本报错，后来知道原因是：写在上面的时候 ，前面的那个父frame还没有加载完，导致数据不对，同时对象也获取不到，也有问题
其实后来明白，添加了alert能获取 到一个错误值，其实也是因为他占用了时间，才能能他加载完。，
所以以后的注意点是：
1.对于这种有先后加载顺序依赖的，需要调整的js位置，让他正确的加载。
2.不同iframe直接跨域访问，直接parent.frames["leftFrame"]跨越到父亲页面，如果在那个页面操作一样。。。。

 -->
<script type="text/javascript">
	function switchMenu () {
		var mainWindow = window.top.document.getElementById("main_frm");
		var btn = document.getElementById("switchImg");
		var cols = mainWindow.getAttribute("cols");
		//\menu_switch_expand.gif
		if (cols == "155,10,*") {
			mainWindow.setAttribute("cols", "5,10,*");
			btn.setAttribute("src", "${baseManager}/images/menu_switch_expand.gif");
		} else {
			mainWindow.setAttribute("cols", "155,10,*");
			btn.setAttribute("src", "${baseManager}/images/menu_switch_close.gif");
		}
	}
// 	setTimeout(function(){//给个缓冲时间，让父页面加载完成
// 		var menuHigh = parent.document.getElementById("leftFrame");
// 		 menuHigh = menuHigh.contentWindow.document.getElementById("mynav").offsetHeight;
// 		var high = parent.frames["leftFrame"].document.getElementById("mynav").offsetHeight;
// 		var high = parent.frames["leftFrame"].document.documentElement.clientHeight;
// 		alert(menuHigh);//确实是可以获取懂啊到了，可是由于有加载延迟，所以总是算错，还是写死好了。
	   	var th = (552-80)/2;//80是抛去中间图片和顶部小角的大小
	   	th = th + "px";
	    	$("#id1").css("height",th);
	    	$("#id2").css("height",th);
// 	},100);
   </script>
</ody>
</html>