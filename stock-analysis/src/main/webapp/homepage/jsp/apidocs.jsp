<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>标准帮助</title>

</head>

<body>
	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>
	
<div class="w1000 pt35 clearfix">
	<!-- left -->
	<%@include file="/homepage/jsp/apidocs/left.jsp"%>
    
    <div class="help_con">
    
    <!-- api文档 -->
    
	<style type="">
		.p2 {
	    	margin: 30px 0;
		}
	</style>
    <%@include file="/homepage/jsp/apidocs/api.jsp"%>    	
	<!--标准帮助 end -->


	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>


</body>
</html>
