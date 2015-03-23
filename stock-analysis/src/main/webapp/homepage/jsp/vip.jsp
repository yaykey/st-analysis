<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>VIP服务</title>
</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>

	<div class="ff_banner"></div>
	<!--banner end -->
	<div class="w1000">
		<dl class="service_con clearfix">
			<dt>
				<img src="${base }/homepage/images/service_img1.png" />
			</dt>
			<dd>
				<h2>行业数据</h2>
				<p>助您了解行业内应用的整体水平</p>
				<p>查看本应用在全体应用或同类应用中所处排名及趋势，有助于衡量您应用整体质量</p>
			</dd>
		</dl>
	</div>
	<!--行业数据 end -->
	<div class="bg_fafbfc">
		<div class="w1000">
			<dl class="service_con clearfix">
				<dt>
					<img src="${base }/homepage/images/service_img2.png" />
				</dt>
				<dd>
					<h2>定制报表与数据分析</h2>
					<p>数据定制化服务——智能查询系统</p>
					<p>灵活提取数据，计算用户关系，设定指标时间、指标条件，实时预览报表效果，随时了解报表生成进度</p>
				</dd>
			</dl>
		</div>
	</div>
	<!--定制报表与数据分析 end -->
	<div class="w1000">
		<dl class="service_con clearfix">
			<dt>
				<img src="${base }/homepage/images/service_img3.png" />
			</dt>
			<dd>
				<h2>通知订阅</h2>
				<p>通知订阅服务</p>
				<p>绑定飞信号的用户可接收免费消息或短信通知，无需登录平台即可随时随地了解应用数据异常情况</p>
			</dd>
		</dl>
	</div>
	<!--通知订阅 end -->
	<div class="bg_fafbfc">
		<div class="w1000">
			<dl class="service_con clearfix">
				<dt>
					<img src="${base }/homepage/images/service_img4.png" />
				</dt>
				<dd>
					<h2>数据明细查询</h2>
					<p>提供查询提取7天内统计数据明细的接口</p>
					<p>提供按照时间顺序查询错误和异常数据明细的接口</p>
				</dd>
			</dl>
		</div>
	</div>
	<!--数据明细查询 end -->
	<div class="w1000">
		<dl class="service_con clearfix">
			<dt>
				<img src="${base }/homepage/images/service_img5.png" />
			</dt>
			<dd>
				<h2>7*24技术支持</h2>
				<p>提供7x24小时在线技术支持</p>
				<p>提供产品整合技术方案的建议咨询，提供围绕统计分析平台的周边产品与技术架构设计咨询</p>
			</dd>
		</dl>
	</div>
	<!--7*24技术支持 end -->

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>

	<!-- <script>
$(function(){
	//导航
	$(".nav li").click(function(){
		$(".nav li").removeClass("cur");
		$(this).addClass("cur");			                                  												
	});		   
});
</script> -->
</body>
</html>
