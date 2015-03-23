<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>综合数据应用平台</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta name="keywords"
			content="飞信,中国移动飞信,手机飞信,飞信官网,手机飞信下载,飞信下载,免费短信,飞信客户端,飞信手机客户端" />
		<meta name="description"
			content="手机飞信官网作为中国移动飞信业务的官方下载网站，提供手机飞信下载，注册，入门，答疑等服务和支持。提供飞信用户在互联网上的博客、相册、群组等延伸功能。" />
				
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache,must-revalidate">
		<meta http-equiv="expires" content="0">
		<script src="${base}/js/jquery/jquery-1.5.1.js" type="text/javascript"></script>
		<script src="${base}/manager/js/blockUI-2.31.js" type="text/javascript"></script>
		<link href="${base}/login/css/style.css?v=120105" rel="stylesheet" type="text/css" />
		<style type="text/css">
			a{
				text-decoration: none;
				cursor: default;
			}
		</style>
	</head>
	<body class="body_bg bor" style="">
		<div class="login_wrap">
		<div class="login_bg"></div>
			<!-- 输入验证码-->
			
			<div class="pop" id="sms_acquire"  style="display:none; cursor: default">
				<form  action="${base}/sms/validate.${actionExt}" method="POST">
				
				<input type="hidden" name="loginId" value="${loginId}"/>
				
                <div class="tit">
                    <h2>短信验证</h2>
                    <span id="close"></span>
                </div>
				<div class="msg_text">
					<div class="top_t">短信已发送，请在下方输入验证码</div>
					<div class="yz_box">验证手机：<var id="mobile">136******43</var>&nbsp;&nbsp;&nbsp;<a class="retrieve" id="btn_acquire" href="javascript:void(0);">重新获取(<span id="txt_interval">59</span>)</a></div>
					<div class="remark">如验证手机号码变更，请联系综合数据应用平台管理员为您修改。</div>

				</div>
				<ul class="login mar_t30">
					<li>
						<label>验证码：</label><input id="randomNumeric" name="randomNumeric" type="password" class="input_text2" value=""/>
					</li>
                </ul>
                <div class="btn_save"><input type="button" id="btn_submit" class="btn btn1" /></div>
                </form>
            </div>
          
            <!--验证码错误 -->
			<div class="pop" id="sms_error" style="display:none; cursor: default">
                <div class="tit">
                    <h2>短信验证</h2>
                </div>
				<div class="msg_text">
					<p>由于短信验证码发送失败、超时等其他不安全因素<br/>为保证数据安全，已被禁止访问！</p>
					<p>请检查输入信息是否正确,或联系数据平台管理员为您重新开通数据平台访问权限。</p>
				    <div class="btn_save"><input type="button" id="btn_return" class="btn btn1" /></div>
				</div>
            </div>
		</div>
		
		<div class="copyright">
		<p>
			<!--  &copy; 2011 飞信数据平台 新媒传信版权所有-->
		</p>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			popModal("${tip}");
			$('#randomNumeric').val('');
		});
		function timeInterval(){
			var time = $("#txt_interval").text();
        	time--;
        	if(time < 0){
        		var btn_acquire = $("#btn_acquire");
        		btn_acquire.empty().removeClass("retrieve").addClass("retrieve_l");
        		btn_acquire.html('重新获取');
        		btn_acquire.attr("href","javascript:reacquire();");
        	}else{
        		$("#txt_interval").text(time);
        		setTimeout(timeInterval,1000);
        	}
        	
		}
		
		function reacquire(){
			var btn_acquire = $("#btn_acquire");
			btn_acquire.html("重新获取(<span id=\"txt_interval\">59</span>)");
			btn_acquire.removeClass("retrieve_l").addClass("retrieve");
			btn_acquire.attr("href","javascript:void();");
			$.ajax({
				type: "POST",
			    url: base + "/sms/acquire." + actionExt,
				//data: "mobile:${mobile}",
				data: {
					"loginId" : "${loginId}"
				},
				success: function(data){
					if(data == "error"){
						$.unblockUI({ message: $('#sms_acquire')}); 
						$.blockUI({ message: $('#sms_error'), css: { width: '372px' }}); 
					}
				}
			});
			setTimeout(timeInterval,1000);
		}
		function popModal(tip){
			if(tip == "error"){
				$.blockUI({ message: $('#sms_error'), css: { width: '372px' }}); 
				 $('#btn_return').click(function() { 
			            window.location.href= base + "/login.jsp";
		         }); 
			}else{
				//var regex = /^(\d{3})(\d{6})(\d{2})$/;
				//var result = "${mobile}".replace(regex, "$1******$3");
				
				var result = "${confusionMobile}";
				
				$("#mobile").text(result);
				$.blockUI({ message: $('#sms_acquire'), css: { width: '372px' }}); 
				$('#btn_submit').click(function() { 
					 document.forms[0].submit();
		        }); 
	            $('#close').click(function() { 
		            window.location.href= base + "/login.jsp";
		        }); 
	            
	            setTimeout(timeInterval,1000);
			}
		
		}
	</script>
	</body>
</html>
