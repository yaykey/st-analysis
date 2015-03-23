<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<%@include file="/manager/commons/meta_ztree.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>修改个人信息</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/tab.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/selfinfo/js/eye.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/selfinfo/js/tab.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/user/js/jquery.easyui.min.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/easyui.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/user/js/jquery.validate.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/validate.css"/>" rel="stylesheet" type="text/css"/>		
		<style type="text/css">
			.tip {
				font-size: 12px;
				color: red;
				margin: 5px 20px;
				width:400px;
				display: inline-block;
				padding: 5px 10px 6px;
				text-decoration: none;
				border-radius: 8px;
				-moz-border-radius: 8px;
				-webkit-border-radius: 8px;
				-moz-box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				-webkit-box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				box-shadow: 2px 2px 5px rgba(0,0,0,0.5);
				border: 1px solid rgba(0,0,0,0.25);
				position: relative;
			}
		</style>
		<script type="text/javascript">
    		var setting = {
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "all"
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			}
			/*
			//初始化用户树
			function userTreeInit(){
				jQuery.ajax({
					type : "POST",
					url : '${base}/manager/user/getAllRole.${actionExt}?roleId=' + $("#role").val(),
					dataType : 'text',
					data : {
						"nocache" : new Date().getTime()
					},
					success : function(responseText) {
						var json=responseText;
						//将字符串转成数组
						initZTree(eval(json));
					}
				});	
			}
    		*/
    		
			/*
    		//设置树
		    function initZTree(val){
				var zNodes = val
				$.fn.zTree.init($("#userTree"), setting, zNodes);
			}
    		*/
			
		    $(document).ready(function() {
		    	
	    		$('#btn_back').bind('click', function() {
	    			window.location.href = '${baseManager}/selfinfo/list.${actionExt}';
	    		});
	    		//提示信息
	    		var type = $("#type").val();
	    		if(type=="add"){
	    			$(".tip").show();
	    		}
	    		if(type=="edit"){
	    			$(".tip").hide();
	    		}
	    		//部门下拉框
	    		$("select[name=department] option").each(function(){
	    	    	if($(this).val() == $("#dep").val()){
	    			   $(this).attr("selected","selected");
	    			  }
	    		});
	    		
	    		/*
	    		//初始化角色树
				userTreeInit();
	    		
				//默认角色窗口关闭
				$('#dlg').dialog('close');
				//打开角色窗口
				$("#rolename").click(function(){
					$('#dlg').dialog('open');
				});
				//树界面确定按钮
				$("#btn_sub").click(function(){
					var treeObj = $.fn.zTree.getZTreeObj("userTree");
					var nodes = treeObj.getCheckedNodes(true);
					for (var i=0, l=nodes.length; i < l; i++) {
						$("#role").attr("value", nodes[i].value);
						$('#dlg').dialog('close');
						$("#rolename").attr("value", nodes[i].name);
					}
				});
				*/
				//表单验证
				jQuery.validator.addMethod("telphoneValid", function(value, element) {
				    var tel = /^(130|131|132|133|134|135|136|137|138|139|150|152|152|153|155|156|157|158|159|180|182|185|186|187|188|189)\d{8}$/;
				    return tel.test(value) || this.optional(element);
				}, "请输入正确的手机号码");
				jQuery.validator.addMethod("numberWord", function(value, element) {
					var chrnum = /^([a-zA-Z0-9]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "请输入6-20位字母、数字");
				var validator = $("#signupform").validate({
					rules: {
						loginID: {
							required: true,
							numberWord: true,
							minlength: 6,
							maxlength: 20,
							remote: {
								type : "POST",
		    					url : '${base}/manager/user/verifyLogin.${actionExt}?type=edit',
		    					data : {
		    						"nocache" : new Date().getTime(),
		    						loginID: function() {
		    							return $("#loginID").val();
		    						},
		    						userId: function() {
		    							return $("#userId").val();
		    						}
		    					}
							}
						},
						name: {
							required: true,
							minlength: 2
						},
						email: {
							required: true,
							email: true,
							remote: {
								type : "POST",
		    					url : '${base}/manager/user/verifyEmail.${actionExt}?type=edit',
		    					data : {
		    						"nocache" : new Date().getTime(),
		    						email: function() {
		    							return $("#email").val();
		    						},
			    					userId: function() {
		    							return $("#userId").val();
		    						}
		    					}
							}
						},
						//rolename: {
						//	required: true
						//},
						department: {
							required: true
						},
						mobileNumber: {
							required: true,
							telphoneValid:true
						}
					},
					messages: {
						loginID: {
							required: " 请输入6-20位字母、数字",
							minlength: jQuery.format("请输入至少 {0}位数字、字母"),
							maxlength: jQuery.format("请输入最多 {0}位数字、字母"),
							remote: "此用户名已经存在"
						},
						name: "请输入用户姓名",
						email: {
							required: "请输入正确的电子邮箱",
							minlength: "请输入正确的电子邮箱",
							remote: "此电子邮箱已存在"
						},
						rolename: "请选择角色",
						department: "请选择所属部门",
						mobileNumber:{
							required: "请输入正确的手机号码",
							telphoneValid: "请输入正确的手机号码"
						}
					},
					 submitHandler:function(form){
				            form.submit();
				     } 
				});
				$('#btn_submit').click(function(){
					var iden = validator.form();
					if(iden == false){
						$(".tip").css('display', 'block');
						$(".tip").css('color', 'red');
						$(".tip").html('请将基本资料填写正确');
					}else{
						$(".tip").css('display', 'none');
					}
				});
	    	});
		</script>
	</head>
	<body>
		<div class="wrapper">
			<ul class="user-info-config">
	        	<li>
	               	<h3>个人信息设置</h3>
	           	</li>
	    	</ul>
	        <ul class="navigationTabs">
	            <li><a href="#base">基本资料 <label style="color: red;"> *</label></a></li>
	            <li><a href="#detail">详细信息</a></li>
	        </ul>
	        <form action="${baseManager}/selfinfo/modify.${actionExt}" method="post" id="signupform" name="signupform">
	        <input type="hidden" id="userId" name="userId" value="${empty requestScope.user ? '': requestScope.user.id}" />
	        	<%-- <input type="hidden" id="role" name="role" value="${empty requestScope.user ? '': requestScope.user.role.id}"/> --%>
		        <div class="tabsContent">
		            <div class="tab">
		                <p>
	           				<span class="info-label"><label> * </label>登录名：</span>
	              			<span class="info-content">
	              				<input type="text" id="loginID" name="loginID" value="${empty requestScope.user ? '': requestScope.user.username}"/><label> </label>
	              			</span>
	           			</p>
	           			<%-- <p>
	           				<span class="info-label"><label> * </label>用户姓名：</span>
	              			<span class="info-content">
								<input type="text" id="name" name="name" value="${empty requestScope.user ? '': requestScope.user.username}"/><label> </label>
							</span>
	           			</p> --%>
	           			<p>
	           				<span class="info-label"><label> * </label>电子邮件：</span>
	              			<span class="info-content">
	              				<input type="text" id="email" name="email" value="${empty requestScope.user ? '': requestScope.user.email}"/><label> </label>
	              			</span>
	           			</p>
	           			<%-- <p>
	           				<span class="info-label"><label> * </label>角色：</span>
	              			<span class="info-content">
	              				<input type="text" id="rolename" readonly="readonly" name="rolename" value="${empty requestScope.user ? '': requestScope.user.role.name}"/><label> </label>
	              			</span>
	           			</p> --%>
	           			 <p>
	         				<span class="info-label"><label> * </label>手机号码：</span>
	            			<span class="info-content">
	            				<input type="text" id="mobileNumber" name="mobileNumber" value="${empty requestScope.user ? '': requestScope.user.mobile}"/><label> </label>
	            			</span>
	         			</p>
	           			<%-- <p>
	         				<span class="info-label"><label> * </label>所属部门：</span>
	            			<span class="info-content">
								<input type="hidden" id="dep" name="dep" value="${empty requestScope.user ? '': requestScope.user.department.id}"/>
	            				<select id="department" name="department">
	            					<option value="">-- 请选择 --</option>
	            					<option value="1">数据中心</option>
	            					<option value="2">PC产品部</option>
	            					<option value="3">无线产品部</option>
	            					<option value="4">飞聊产品部</option>
	            					<option value="5">同窗产品部</option>
	            					<option value="6">短信产品部</option>
	            					<option value="7">WAP产品部</option>
	            					<option value="8">其他</option>
								</select><label> </label>
							</span>
	         			</p> --%>
		            </div>
		            <%-- <div class="tab">
		            	<div class="tip" style="display: none;">
		            	
		            	</div>
	         			<p>
	         				<span class="info-label">公司名称：</span>
	            			<span class="info-content">
								<input type="text" id="company" name="company" value="${empty requestScope.user ? '': requestScope.user.company}"/><label> </label>
							</span>
	         			</p>
		            </div> --%>
		        </div>
		        <!-- <div>
		        	<div id="dlg" class="easyui-dialog" title="角色选择" style="width:270px;height:480px;padding:10px"
						data-options="iconCls: 'icon-save',buttons: '#dlg-buttons'">
						<ul id="userTree" class="ztree"></ul>
					</div>
					
					<div id="dlg-buttons">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="btn_sub">确定</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
					</div>
		        </div> -->
	        
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存修改" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
	        </form>
    	</div>
	</body>
</html>