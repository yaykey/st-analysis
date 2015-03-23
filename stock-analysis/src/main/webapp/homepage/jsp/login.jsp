<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>登录</title>

<style type="">

#form_forget_password td label.error,
.register_table td label.error {
    color: #c00d0d;
    float: left;
    font-size: 13px;
    line-height: 49px;
    margin-left: 15px;
}

#form_forget_password td label.error {
	line-height: normal;
	float : none;
}

</style>

</head>

<body>
	<!-- header -->
	
	
	<%@include file="/homepage/jsp/frame/header.jsp"%>

	<div class="login_con">
		<form id="login-form" action="${base }/homepage/validate.feinno" method="post">
	    	<table width="100%" border="0" class="register_table">
		    	<tr>
		    		<td>
			    		<input id="loginId" maxlength="40" name="loginId" type="text" 
					    		class="text_w349 text_c" value="请输入注册邮箱/手机号"
								onfocus="if (value =='请输入注册邮箱/手机号'){value =''}"
								onblur="if (value ==''){value='请输入注册邮箱/手机号'}" />
						<!-- <span>请输入账号</span> -->
		    		</td>
		    	</tr>
				<tr>
		    		<td>
						<input id="passwd" maxlength="20" name="passwd" type="text" 
								class="text_w349 text_c" value="请输入密码"
								onfocus="if (value =='请输入密码'){value =''; this.type='password';}"
								onblur="if (value ==''){value='请输入密码'; this.type='text';}" />
						<!-- <span>请输入密码</span> -->
					</td>
		    	</tr>
		    	<tr>
		    		<td>
						<a id="login-submit" href="javascript:void(0);" class="login_btn">登录</a>
					</td>
		    	</tr>
		    	<tr>
		    		<td>
						<p>
							<a href="${base }/homepage/jsp/register.jsp?headerType=register" target="_self">现在注册</a>
							<a id="btn_forgetpassword" href="javascript:void(0);">忘记密码</a>
						</p>
					</td>
		    	</tr>
			</table>
		</form>
	</div>
	<!--登录 end -->
	
	
	<!-- -------------------------           忘记密码的邮箱找回                                     ---------------------------------------- -->

	<div
		style="display: none; border: none !important; width: auto !important;">
		<form id="form_forget_password" name="formForgetPassword" action="">
			<div class="help_con" 
					style="width:auto;padding: 0 20px 20px 20px; background-color: #fbfbfb">
				<h2>忘记密码</h2>
				<h3>如忘记密码，系统将给您的登录名邮箱发送邮件。请前往邮箱查看，然后修改密码。</h3>
				<div style="display:block; padding-bottom: 10px;">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<div>
								<label>确认邮箱：</label>
								<input id="input_email" name="email" type="text" class="input_tx" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<p id="showMessage" style="display: none;">
									系统正在为您下发邮件（可能需要几分钟时间），请停留在此页面耐心等待...</p>
							</td>
						</tr>
						<tr>
							<td>
								<p>
								<input id="btn_forget_password" type="button" value="确定"
								class="input_bt btn_confirm">
								<input id="btn_forget_password_cancel" type="button" value="关闭"
									class="input_bt btn_cancle">
								</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		/* //表单验证
		jQuery.validator.addMethod("telphoneValid", function(value, element) {
			var tel = /^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|155|156|157|158|159|180|182|183|185|186|187|188|189)\d{8}$/;
		    return tel.test(value) || this.optional(element);
		}, "请输入正确的手机号码");
		
		jQuery.validator.addMethod("numberWord", function(value, element) {
			var chrnum = /^([a-zA-Z0-9\.]+)$/;
			return this.optional(element) || (chrnum.test(value));
		}, "请输入4-20位字母、数字"); */

		var tip = "${tip}";
		//alert(tip);
		if (tip) {
			var $m = $('<div class="growlUI"></div>');
			$m.append('<h1>登录提示!</h1>');
			$m.append('<h2>' + tip + '</h2>');

			$.blockUI({
				message : $m,
				fadeIn : 700,
				fadeOut : 1000,
				centerY : false,
				timeout : 5000,
				showOverlay : false,
				//onUnblock: onClose,
				css : $.blockUI.defaults.growlCSS
			});
		}

		var validator = $("#login-form").validate({
			rules : {
				"loginId" : {
					required : true
				},
				"passwd" : {
					required : true
				}
			},
			messages : {
				"loginId" : {
					required : "请输入账号"
				},
				"passwd" : {
					required : "请输入密码"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

		$('#login-submit').click(function() {
			var iden = validator.form();

			if (iden == false) {

			} else {

				var loginIdVal = $("#loginId").val();
				var passwdVal = $("#passwd").val();
				//请输入注册邮箱/手机号
				//请输入密码

				if (loginIdVal == "请输入注册邮箱/手机号" || passwdVal == "请输入密码") {

				} else {
					$('#login-form').submit();
				}

			}
		});
		
		var forgetPasswordValidator;
		function forgetpassword() {
			$.blockUI({
				message : $('#form_forget_password'),
				css : {
					border : 'none',
					width : 'auto'
				}
			});

			$("#btn_forget_password_cancel").bind('click', function() {
				$.unblockUI();
			});

			$("#forget_password_close").bind('click', function() {
				$.unblockUI();
			});
			
			/* $("#btn_forget_password").bind('click', function() {
				$("#showMessage").css('display', 'block');
			}); */
			
			
			
			forgetPasswordValidator = $("#form_forget_password").validate({
				rules : {
					email : {
						required : true
					//					, remote : {
					//						url : base + '/account/checkEmail.feinno',
					//						type : "post"
					//					}
					}
				},
				/* errorPlacement : function(error, element) {
					//				element.next().empty();
					//				element.parent().next().empty();
					//				error.appendTo(element.next());
				}, */
				messages : {
					email : {
						required : "请输入邮箱",
						remote : "邮箱错误"
					}
				},
				submitHandler : function(form) {
					jQuery.ajax({
						type : "POST",
						url : base + '/account/forgetPassword.feinno',
						dataType : 'text',
						data : {
							"nocache" : new Date().getTime(),
							"email" : $('#input_email').val()
						},
						success : function(responseText) {
							$("#showMessage").css('display', 'none');
							if (responseText == "ok") {
								$.growlUI('设置成功', '重置密码链接已发送至您的邮箱，请及时重置密码。');
								
							} else if (responseText == "邮件发送失败!") {
								$.growlUI('邮件发送失败', "可能邮件服务器出了点问题，请联系管理员", 8000);
							} else {
								$.growlUI('设置失败', responseText, 8000);
							}
						}
					});
				},
				success : function(label) {
					//				label.html("&nbsp;").addClass("checked");
					//				label.removeClass("error");
					//				label.parent().parent().next().empty();
					//				label.parent().parent().next().append(label.clone(true));
					//				label.remove();
				}
			});
			
			

			//setTimeout($.unblockUI, 2000); 
		}
		
		$("#btn_forget_password").bind('click', function() {
			
			var iden = forgetPasswordValidator.form();

			if (iden == false) {

			} else {

				
				$('#form_forget_password').submit();
				
				//$("#showMessage").css('display', 'block');
			}
			
		});

		$('#btn_forgetpassword').bind('click', forgetpassword);
	});
</script>

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>

</body>
</html>
