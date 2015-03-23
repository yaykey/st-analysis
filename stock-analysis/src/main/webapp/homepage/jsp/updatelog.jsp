<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>更新日志</title>

<style type="">

.help_con {
	padding-left: 100px;
}

.updatelog {

}
/* 
.updatelog table{
	float: left;
    width: 500px;
    margin-left: 80px;
}

.updatelog table tr{

}

.updatelog table th{
	color: #656565;
    font-weight: bold;
}

.updatelog table td{
	text-align: center;
} */

.updatelog .log{
	font-size: 16px;
}

.updatelog .log .log-date{
	float: left;
    font-weight: bold;
    margin : 0px 10px;
    color : #656565;
}

.updatelog .log .log-version{
	background-image: url("${base}/homepage/images/w93_h28btn.png");
	background-position: left top;
	background-repeat: no-repeat;
	width : 93px;
	height : 28px;
	text-align: center;
	float : left;
	font-size: 12px;
	color: #fff;
	margin : 0px 10px;
}

.updatelog .log .log-desc{
	margin-left : 10px;
}


</style>
	
</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>


	<div class="w1000 pt35 clearfix">
		<div class="help_con">
			<h2>版本更新日志</h2>
			
			
			
			<div class="updatelog">
				<div class="log">
					<div style="clear: both; overflow: hidden; margin-bottom: 20px; margin-top: 15px;">
						<p class="log-date">2014.11.11</p>
						<p class="log-version">Android 1.0</p>
					</div>
					<div style="clear: both;">
						<p class="log-desc">
							对外发布基于Android的第一个版本
						</p>
					</div>
				</div>
						
						
			</div>

		</div>
	</div>

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>


</body>
</html>
