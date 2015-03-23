<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/homepage/jsp/frame/meta.jsp"%>
<title>注册</title>

<style type="">

.register_table td label.error {
	color: #c00d0d;
    float: left;
    font-size: 13px;
    line-height: 49px;
    margin-left: 15px;
}


</style>

</head>

<body>
	<!-- header -->
	<%@include file="/homepage/jsp/frame/header.jsp"%>
	
<div class="register_con">
	<div class="register_conl">
    	<h2>注册</h2>
    	<form id="register-form" action="${base }/homepage/register.feinno" method="post">
	    	<table width="100%" border="0" class="register_table">
	          <!-- <tr>
	            <th width="173"><i>*</i>用户名</th>
	            <td>
	            	<input id="username" name="userInfo.username" type="text" maxlength="20" class="text_w349"/>
	            	
	            </td>
	          </tr> -->
	          <tr>
	            <th width="173"><i>*</i>邮箱地址</th>
	            <td>
	            	<input id="email" name="userInfo.email" type="text" maxlength="40" class="text_w349"/>
	            	<!-- <span style="display:none;">请输入有效Email地址</span> -->
	            </td>
	          </tr>
	          <tr>
	            <th><i>*</i>登录密码</th>
	            <td>
	            	<input id="passwd" name="userInfo.passwd" type="password" maxlength="16" class="text_w349"/>
	            	<!-- <span style="display:none;">请输入6-16位数字、字母组成的密码</span> -->
	            </td>
	          </tr>
	          <tr>
	            <th><i>*</i>确认密码</th>
	            <td>
	            	<input id="confirmPasswd" name="confirmPasswd" type="password" maxlength="16" class="text_w349"/>
	            	<!-- <span style="display:none;">两次密码输入不一致</span> -->
	            </td>
	          </tr>
	          <tr>
	            <th><i>*</i>手机号码</th>
	            <td>
	            	<input id="mobile" name="userInfo.mobile" type="text" class="text_w349"/>
	            	<!-- <span style="display:none;">请输入手机号</span> -->
	            </td>
	          </tr>
	          <tr>
	            <th>公司、组织或个人名</th>
	            <td><input id="company" name="userInfo.company" type="text" class="text_w349"/></td>
	          </tr>
	          <!-- <tr>
	            <th><i>*</i>短信验证码</th>
	            <td><input type="text" class="text_w169"/><a href="#" class="code_btn">获取短信验证码</a></td>
	          </tr> -->
	          
	          <tr>
	            <th></th>
	            <td>
	            <a id="register-submit" href="javascript:void(0);" class="terms_btn"
	            		style="text-align__: left;cursor__: default">	            	
	            	<!-- <label> -->
	            	<!-- <input type="checkbox" style="margin:0px 10px;"/> -->
	            	<!-- </label> -->
	            	阅读并同意服务条款,注册
	            </a>
	            <i>
	            	<a href="${base }/homepage/jsp/terms.jsp?headerType=terms" target="_blank">服务条款</a>
	            	<!-- <input type="submit" style="display:none;" /> -->
	            </i>
	            </td>
	          </tr>
	          <tr>
	            <th></th>
	            <td>
	            	<div class="tip" style="display: none;" id="xtip"></div>
	            </td>
	          </tr>
	        </table>
        </form>
        
    </div>
    <div class="register_conr">
    	<p>开始你的旅程</p>
        <p>已注册过，直接 <a href="${base }/homepage/jsp/login.jsp" target="_self">登录</a></p>
    </div>
</div>
<!--注册 end -->

<script type="text/javascript">
$(document).ready(function() {
	
		
	//表单验证
	jQuery.validator.addMethod("telphoneValid", function(value, element) {
		var tel = /^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|155|156|157|158|159|180|182|183|185|186|187|188|189)\d{8}$/;
	    return tel.test(value) || this.optional(element);
	}, "请输入正确的手机号码");
	
	jQuery.validator.addMethod("numberWord", function(value, element) {
		var chrnum = /^([a-zA-Z0-9\.]+)$/;
		return this.optional(element) || (chrnum.test(value));
	}, "请输入4-20位字母、数字");
	
	var validator = $("#register-form").validate({
		rules: {
			/* "userInfo.username": {
				required: true,
				numberWord: true,
				minlength: 4,
				maxlength: 20,
				remote: {
					type : "POST",
					url : '${base}/homepage/verifyUsername.${actionExt}',
					data : {
						"nocache" : new Date().getTime(),
						"username" : function() {
							return $("#username").val();
						}
					}
				}
			}, */
			"userInfo.email": {
				required: true,
				email: true,
				remote: {
					type : "POST",
					url : '${base}/homepage/verifyEmail.${actionExt}',
					data : {
						"nocache" : new Date().getTime(),
						"email" : function() {
							return $("#email").val();
						}
					}
				}
			},
			"userInfo.passwd": {
				required: true,
				numberWord: true,
				minlength: 6,
				maxlength: 16
			},
			confirmPasswd: {
				required: true,
				numberWord: true,
				minlength: 6,
				maxlength: 16,
				equalTo: "#passwd"
			},
			"userInfo.mobile": {
				telphoneValid:true,
				required: true
			}
		},
		messages: {
			/* "userInfo.username" : {
				required: "请输入4-20位字母、数字",
				minlength: jQuery.format("请输入至少 {0}位数字、字母"),
				maxlength: jQuery.format("请输入最多 {0}位数字、字母"),
				remote: "此用户名已经存在"
			}, */
			"userInfo.email" : {
				required: "请输入正确的电子邮箱",
				remote: "此电子邮箱已存在"
			},
			"userInfo.passwd" : {
				required: "请输入6-16位字符",
				minlength : jQuery.format("请输入最少{0}个字符"),
				maxlength : jQuery.format("请输入最大{0}个字符")
			},
			confirmPasswd: {
				required: "请输入6-16位字符",
				minlength : jQuery.format("请输入最少{0}个字符"),
				maxlength : jQuery.format("请输入最大{0}个字符"),
				equalTo : "两次密码输入不一致"
			},
			"userInfo.mobile" :{
				required: "请输入正确的手机号码",
				telphoneValid: "请输入正确的手机号码"
			}
		},
		 submitHandler:function(form){
			
	     	form.submit();
			
	     } 
	});
	
	$('#register-submit').click(function(){
		var iden = validator.form();
		if(iden == false){
			$("#xtip").css('display', 'block');
			$("#xtip").css('color', 'red');
			$("#xtip").html('请将基本资料填写正确');
		}else{
			$("#xtip").css('display', 'none');
			//$("#btn_submit").attr('disabled','disabled');
			//$('#register-form').submit();
		}
	});
	
});
</script>

	<!-- footer -->
	<%@include file="/homepage/jsp/frame/footer.jsp"%>
	
</body>
</html>
