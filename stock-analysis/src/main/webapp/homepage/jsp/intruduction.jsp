<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="/homepage/jsp/frame/meta.jsp"%>

<title>功能介绍</title>

</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>

	<div class="gn_banner"></div>
	<!--banner end -->

	<div class="w1000">
		<div class="role_con clearfix">
			<img src="${base }/homepage/images/role_img1.jpg" class="fl" />
			<div class="role_text fr">
				<h2>趋势概况</h2>
				<p>清晰展现应用的新增用户、活跃用户、启动次数、版本分布、行业指标等数据，方便您从整体掌控应用的运营情况及增长动态</p>
			</div>
		</div>
		<!--趋势概况 end -->
	</div>
	<div class="bg_fafbfc">
		<div class="w1000">
			<div class="role_con clearfix">
				<div class="role_text fl">
					<h2>时段对比分析</h2>
					<p>将两期或连续数期的同一指标进行对比，确定其增减变动的方向、数额和幅度，以该指标的变动趋势可以更清晰的看到用户的访问习惯和网站的运营效果</p>
				</div>
				<img src="${base }/homepage/images/role_img2.jpg" class="fr" />
			</div>
		</div>
	</div>
	<!--周期对比分析 end -->
	<div class="w1000">
		<div class="role_con clearfix">
			<img src="${base }/homepage/images/role_img3.jpg" class="fl" />
			<div class="role_text fr">
				<h2>留存分析</h2>
				<p>掌握任意选定时间周期内，来自不同渠道的新增用户在初次使用后一段时间内（周留存、月留存）的留存率，留存率的高低一定程度上反映了产品和用户质量的好坏</p>
			</div>
		</div>
	</div>
	<!--留存分析 end -->
	<div class="bg_fafbfc">
		<div class="w1000">
			<div class="role_con clearfix role_pb0">
				<div class="role_text fl">
					<h2>行为分析</h2>
					<p>针对性地分析用户在使用APP的页面访问路径、页面访问深度、使用时长等数据，了解用户的产品使用细节及行为特征，帮助您寻找产品改进的突破点，评估产品优化的效果</p>
				</div>
				<img src="${base }/homepage/images/role_img4.jpg" class="fr" />
			</div>
		</div>
	</div>
	<!--行为分析 end -->

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>

</body>
</html>
