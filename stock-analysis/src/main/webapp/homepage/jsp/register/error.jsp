<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>注册成功</title>

</head>

<body>
	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>

	
	<div class="bg_fafbfc">
		<div class="w1000">
			<dl class="service_con clearfix">				
				<dd>
					<h2>注册失败</h2>
					<p>${tip}</p>
					
				</dd>
			</dl>
		</div>
	</div>
	<!--注册失败 end -->

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>
	
</body>
</html>
