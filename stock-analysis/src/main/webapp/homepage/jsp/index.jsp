<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>首页</title>

</head>

<body>

	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>

	<div id="ad">
		<ul>
			<li><div class="img1">
					<p class="banner_btn">
						<a href="#" class="btn_l">立即使用</a><a href="#" class="btn_r">体验DEMO</a>
					</p>
				</div>
			</li>
			<li><div class="img2">
					<p class="img2_banner_btn">
						<a href="#" class="btn_l">立即使用</a><a href="#" class="btn_r">体验DEMO</a>
					</p>
				</div>
			</li>
			<li><div class="img3">
					<p class="img2_banner_btn">
						<a href="#" class="btn_l">立即使用</a><a href="#" class="btn_r">体验DEMO</a>
					</p>
				</div>
			</li>
		</ul>
		<div class="btn" id="btn">
			<a href="javascript:;" class="active"></a> <a href="javascript:;"></a>
			<a href="javascript:;"></a>
		</div>
	</div>
	<!--banner end -->
	<div class="w1000">
		<ul class="list clearfix">
			<li><span class="img1"></span> <i>用户留存分析</i>
				<p>掌握每日(周/月)的新增用户在初次使用后一段时间内的留存率</p></li>
			<li><span class="img2"></span> <i>用户群分析</i>
				<p>按用户属性、行为单独或组合对用户进行划分，分析目标人群的数据</p></li>
			<li><span class="img3"></span> <i>用户行为分析</i>
				<p>了解用户的产品使用细节及行为特征，评估产品优化的效果</p></li>
			<li><span class="img4"></span> <i>渠道效果追踪</i>
				<p>通过数据对比评估不同渠道的用户质量和活跃程度，衡量推广效果</p></li>
		</ul>
	</div>
	<!--中间内容 end -->

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>

	<script>
$(function(){
	/* //导航
	$(".nav li").click(function(){
		$(".nav li").removeClass("cur");
		$(this).addClass("cur");			                                  												
	}); */
	
	
	//banner轮播图	   
		var oDiv = $('#ad');
		var oUl = oDiv.find('ul');
		var aLi =   oUl.find('li');
		var aImg =  oUl.find('div');
		
		var oBtn = $('#btn');
		var aA = oBtn.find('a');
		
		var imgWidth = 1920;
		var iNow = 0;
		var iNow2 = 0;
		
		oUl.width(aImg.length * imgWidth);
		
		function toReSize(){
			
			var veiwWidth = document.documentElement.clientWidth;
			
			if(veiwWidth>1000){
				oUl.find('div').each(function(){
					$(this).css("left",- (imgWidth - veiwWidth)/2);				   
									   
				});
			}
		
		}
		
		toReSize();
		
		$(window).resize(function(){
			toReSize();
			
		});
		
		oBtn.find('a').each(function(){
			$(this).click(function(){
				oBtn.find('a').each(function(){
					$(this).removeClass("active");			   
				});	
				$(this).addClass("active");
				oUl.stop().animate({ 'left' : - $(this).index() * imgWidth},500);
	
				iNow = iNow2 = $(this).index();
			});					 
		});
		
		oDiv.hover(function (){
			clearInterval( timer );
		}, autoPlay);
		
		function autoPlay(){
			timer = setInterval(toRun,3000);
		}
		autoPlay();
		
		function toRun(){
		
			if(iNow == aLi.length-1){
				oUl.find('li:first').css('position' , 'relative');
				oUl.find('li:first').css('left',aLi.length * imgWidth );
				iNow = 0;
			}
			else{
				iNow++;
			}
			
			iNow2++;
			
			oBtn.find('a').each(function(){
					$(this).removeClass("active");			   
			});	
			
			aA[iNow].className = 'active';
			
			
			oUl.animate({ 'left' : - iNow2 * imgWidth},200,function(){
			
				if(iNow==0){
					oUl.find('li:first').css('position' , 'static');
					oUl.css("left",0);
					iNow2 = 0;
				}
			});
		}		  
	
		   
});
</script>
</body>
</html>
