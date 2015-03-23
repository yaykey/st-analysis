

var cookieTime = 30*3;//3个月

function keyDown(e) {
	temp = window.event || e;
	var code = temp.keyCode || temp.which;
	if (code == 13) {
		login();
	}
}
function reloadCode() {
	document.getElementById('image_code').src = base + "/manager/codeImage?"
			+ Math.random();
}

$(document).ready(function() {
	
	$(document.body).bind('keydown', keyDown);

	$('#btn_submit').bind('click', login);
	$('#image_code').bind('click', reloadCode);

	$('#btn_loginId').bind('focus', inputFocus);
	$('#btn_loginId').bind('blur', inputBlur);

	$('#btn_password').bind('keypress', detectCapsLock);
	$('#btn_password').bind('focus', inputFocus);
	$('#btn_password').bind('blur', inputBlur);

	$('#btn_code').bind('focus', inputFocus);
	$('#btn_code').bind('blur', inputBlur);
	
	
	
	$('#btn_forgetpassword').bind('click',forgetpassword);
	
	$('#btn_savepassword').bind('click', function(){
//		if (this.checked == true) {
//			$.cookie('checked', true, {expires:cookieTime});
//		} else {
//			$.removeCookie('checked', true);
//		}
	});
		
	if ($.cookie('checked') == 'true') {
		$('#btn_savepassword')[0].checked = true;
		
		var cookieUserName = $.cookie('username').trim();
		
		if (cookieUserName) {
			$('#btn_loginId')[0].value = cookieUserName;
			$('#btn_password')[0].value = $.cookie('password').trim();
		}
	} else {
		$('#btn_loginId')[0].value = '';
		$('#btn_password')[0].value = '';
	}
	
	
});

function inputFocus() {
	$(this).addClass('input-selected');
}

function inputBlur() {
	$(this).removeClass('input-selected');
}

function showTip(tip) {
	if (tip) {
		alert(tip);
	}
}
function login() {

	/*
	if ($.trim($("#btn_loginId").val()) == "") {
		alert("用户名不能为空");
		$("#btn_loginId").focus();
		return false;
	}
	if ($.trim($("#btn_password").val()) == "") {
		alert("密码不能为空");
		$("#btn_password").focus();
		return false;
	}
	 */

	/*
	if ($.trim($("#btn_code").val()) == "") {			
		alert("验证码不能为空");
		$("#btn_code").focus();
		return false;
	}
	 */
	
	if ($('#btn_savepassword')[0].checked == true) {
		//alert('1');
		$.cookie('username', $('#btn_loginId').val(), {expires:cookieTime,path:ctx});
		$.cookie('checked', true, {expires:cookieTime,path:ctx});
		$.cookie('password', $('#btn_password').val(), {expires:cookieTime,path:ctx});		
	} else {
		//alert('2');
		$.removeCookie('username',{expires:-1});
		$.removeCookie('checked',{expires:-1});
		$.removeCookie('password',{expires:-1});
	}

	var dt = new Date();
	$('#loginForm')[0].dt.value = dt;
	$('#loginForm').submit();
	
}

function detectCapsLock(event) {
	var e = event || window.event;
	var keyCode = e.keyCode || e.which; // 按键的keyCode 
	var isShift = e.shiftKey || (keyCode == 16) || false; // shift键是否按住
	var capsLockAndNoShift = (keyCode >= 65 && keyCode <= 90) && !isShift; // Caps Lock 打开，且没有按住shift键 
	var capsLockAndShift = (keyCode >= 97 && keyCode <= 122) && isShift; // Caps Lock 打开，且按住shift键
	if (capsLockAndNoShift || capsLockAndShift) {
		alert("Caps Lock被打开");
	}
}

function forgetpassword () {
	$.blockUI({
		message: $('#form_forget_password')
		,css: { 
            border: 'none',
            width : 'auto'
		}
	}); 
	 
	$("#btn_forget_password_cancel").bind('click', function(){
		$.unblockUI();
	});
	
	$("#forget_password_close").bind('click', function(){
		$.unblockUI();
	});
	$("#btn_forget_password").bind('click',function(){
		$("#showMessage").css('display','block');
	});
	var validator = $("#form_forget_password").validate({
		rules : {
			email : {
//				required : true
//				, remote : {
//					url : base + '/account/checkEmail.feinno',
//					type : "post"
//				}
			}
		},
		errorPlacement : function(error, element) {
//			element.next().empty();
//			element.parent().next().empty();
//			error.appendTo(element.next());
		},
		messages : {
			email : {
				remote: "邮箱错误"
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
					$("#showMessage").css('display','none');
					if (responseText == "ok") {
						$.tips('设置成功', '重置密码链接已发送至您的邮箱，请及时重置密码。');
					}else if(responseText == "邮件发送失败!"){
						$.tips('邮件发送失败', "可能邮件服务器出了点问题，请联系管理员", 8000);
					}else {
						//$.growlUI('设置失败',responseText,8000);
						$.tips('设置失败', responseText, 8000);
					}			
				}
			});			
		},
		success : function(label) {
//			label.html("&nbsp;").addClass("checked");
//			label.removeClass("error");
//			label.parent().parent().next().empty();
//			label.parent().parent().next().append(label.clone(true));
//			label.remove();
		}
	});
	
    //setTimeout($.unblockUI, 2000); 
}



$("#btn_loginId").focus();

//function showTip(tip){
//	if (tip){
//		$.growlUI('操作提示', tip); 
//	}
//}

/**
 * 异步请求的同时遮盖相关区域，被遮盖的div必须设定class为blockUI
 */
function asyncByBlockUI(url,msg){
	msg = msg ? msg :  '正在处理......';
	$('div.blockUI').block({
		message: '<h3><img src="'+ baseManager +'/images/busy.gif" />'+msg+'</h3>', 
		css: { border: '3px solid #a00' }
	});
	$.getJSON(url,function(data){
		$('div.blockUI').unblock();
		if(data){
			$.growlUI('操作提示^_^', '<p>' + data+ '</p>'); 
		}
	});
}


