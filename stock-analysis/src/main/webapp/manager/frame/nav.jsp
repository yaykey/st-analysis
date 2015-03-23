<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.st.framework.module.*" %>
<html>
<head>
   
    <%@include file="/manager/commons/meta.jsp"%>
    <script type="text/javascript">
    	var selectColor = "red";//被选中后的颜色，可以自己配置
    	$(document).ready(function(){
    		 $( "#mynav" ).accordion({
             	autoHeight: false//自动调整高度
             });
    	});
        function changeColor(obj){
        	$("li a").css("color","").css("font-size","").css("font-weight","");//先把之前的都颜色都去掉
        	$(obj).css("color",selectColor).css("font-size","14px").css("font-weight","bold");
        }
    </script>
    
    <style type="text/css">
    	#mynav ul {
    		margin : 0px;
    		padding : 0px;
    	}
    	
    	#mynav ul li{
    		word-break: keep-all;
    		list-style: none;
    	}
    	
    </style>
</head>

<body bgcolor=#FCFAF3 style="margin-left: 0;margin-top: 0;margin-right: 0" >
<table style="border: 0; width: 100%;background-image: url('${baseManager}/images/left_left_bg.gif');">
    <tr >
    <td>
        <div id="mynav" style="font-size: 9pt; width: 100%;">
        	<%
        		List<Module> list = (List<Module>)request.getAttribute("list");
        	
        		for (Module m : list) {
        			%>
        			<h3><%=m.getName()%></h3>
        			<div>
        				<ul>
        			<%
        				if (m.getModules() != null) {
        					for (Module cm : m.getModules()) {
          						if ("userDefinedBackType".equalsIgnoreCase(cm.getViewType())) {
        							%>
            						<li>
    	        						<a href="<%=cm.getActionUrl()%>" target="main">
    	        							<%=cm.getName()%>
    	        						</a>
    	        					</li>
            						<%
        						} else {
        							%>
            						<li >
    	        						<a href="${base }/<%=cm.getActionUrl()%>.${actionExt}" target="main" onclick="changeColor(this);">
    	        							<%=cm.getName()%>
    	        						</a>
    	        					</li>
            						<%
        						} 
        					}
        				}
        			%>
        				</ul>
        			</div>
        			<%
        		}
        	%>
        </div>
    </td>
    </tr>
</table>

</body>
</html>


