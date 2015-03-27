<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<%@ page import="com.st.Global"%>

<html>
<head>

</head>
<style type="text/css">
	.orderby {
		width : 20px;
	}
	
	
	
</style>


<script type="text/javascript">
var str = "<tr parent='11' pp='22'><td>1</td><td>2</td><td>3</td></tr>";

var elNode = $(str);
$(elNode).find("td:last").html("ggg") 

alert($(elNode).html());

	
</script>
</body>
</html>