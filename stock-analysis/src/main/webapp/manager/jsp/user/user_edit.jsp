<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%> 
<%@include file="/manager/commons/meta_ztree.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>信息</title>
		<link href="<c:url value="/manager/jsp/selfinfo/css/tab.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/selfinfo/js/eye.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/selfinfo/js/tab.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/manager/jsp/user/js/jquery.easyui.min.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/easyui.css"/>" rel="stylesheet" type="text/css"/>
		<script src="<c:url value="/manager/jsp/user/js/jquery.validate.js"/>" type="text/javascript"></script>
		<link href="<c:url value="/manager/jsp/user/css/validate.css"/>" rel="stylesheet" type="text/css"/>
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
			/* //初始化用户树
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
			//设置树
		    function initZTree(val){
				var zNodes = val
				$.fn.zTree.init($("#userTree"), setting, zNodes);
			} */
			
		    $(document).ready(function() {
		    	//表单提交
		    	var type = $("#type").val();
	    		$('#btn_submit').bind('click', function(){
					$("#signupform").attr("action","${baseManager}/user/modify.${actionExt}?type=" + type +"&userName_=${userName_}");
	    		});
	    		$('#btn_back').bind('click', function() {
	    			if("editInfo" == type){
	    				window.location.href = '${baseManager}/user/view.${actionExt}?uid=' + $('#userId').val()+'&userName_=${userName_}';
	    			}else{
	    				window.location.href = '${baseManager}/user/list.${actionExt}?&userName_=${userName_}';
	    			}
	    			
	    		});
	    		//
	    		//if($("#loginID").val().toLocaleLowerCase()=="admin") && type=="edit"){
	    		//	$(this).attr("readonly", "readonly");
	    		//}
	    		
	    		
	    		
	    		
	    		
	    		/* //初始化角色树
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
				}); */
				//表单验证
				jQuery.validator.addMethod("telphoneValid", function(value, element) {
					var tel = /^(130|131|132|133|134|135|136|137|138|139|150|151|152|153|155|156|157|158|159|180|182|183|185|186|187|188|189)\d{8}$/;
				    return tel.test(value) || this.optional(element);
				}, "请输入正确的手机号码");
				jQuery.validator.addMethod("numberWord", function(value, element) {
					var chrnum = /^([a-zA-Z0-9\.]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "请输入4-20位字母、数字");
				var validator = $("#signupform").validate({
					rules: {
						/* loginID: {
							required: true,
							numberWord: true,
							minlength: 4,
							maxlength: 20,
							remote: {
								type : "POST",
		    					url : '${base}/manager/user/verifyLogin.${actionExt}?type=' + type,
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
						}, */
						username: {
							required: true,
							minlength: 2
						},
						email: {
							required: true,
							email: true,
							remote: {
								type : "POST",
		    					url : '${base}/manager/user/verifyEmail.${actionExt}?type='+ type,
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
						/* rolename: {
							required: true
						}, */
						/* department: {
							required: true
						}, */
						mobile: {
							required: true,
							telphoneValid:true
						}
					},
					messages: {
						username: {
							required: "请输入4-20位字母、数字",
							minlength: jQuery.format("请输入至少 {0}位数字、字母"),
							maxlength: jQuery.format("请输入最多 {0}位数字、字母"),
							remote: "此用户名已经存在"
						},
						//username: "请输入用户姓名",
						email: {
							required: "请输入正确的电子邮箱",
							minlength: "请输入正确的电子邮箱",
							remote: "此电子邮箱已存在"
						},
						//rolename: "请选择角色",
						//department: "请选择所属部门",
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
						$("#xtip").css('display', 'block');
						$("#xtip").css('color', 'red');
						$("#xtip").html('请将基本资料填写正确');
					}else{
						$("#xtip").css('display', 'none');
					}
				});
	    	});
		</script>
	</head>
	<body>
		<div class="wrapper">
			<ul class="user-info-config">
	        	<li>
	               	<h3>信息设置</h3>
	           	</li>
	    	</ul>
	        <ul class="navigationTabs">
	            <li><a href="#base">基本资料 <label style="color: red;"> *</label></a></li>
	            <!-- <li><a href="#detail">详细信息</a></li> -->
	        </ul>
	        <form action="#" method="post" id="signupform" name="signupform">
	        	<input type="hidden" id="userId" name="userId" value="${empty requestScope.user ? '': requestScope.user.id}" />
	        	<input type="hidden" id="type" name="type" value="${empty requestScope.type ? '': requestScope.type}"/>
	        	<input type="hidden" id="ifEnable" name="ifEnable" value="${empty requestScope.user&&requestScope.user.isEffect ? '1': requestScope.user.isEffect}"/>
	        	<%-- <input type="hidden" id="role" name="role" value="${empty requestScope.user ? '': requestScope.user.role.id}"/> --%>
		        <div class="tabsContent">
		            <div class="tab">
		                <%-- <p>
	           				<span class="info-label"><label> * </label>登录名：</span>
	              			<span class="info-content">
	              				<input type="text" id="loginID" name="loginID" value="${empty requestScope.user ? '': requestScope.user.loginId}"/><label class="lab" id="lab"> </label>
	              			</span>
	           			</p> --%>
	           			<p>
	           				<span class="info-label"><label> * </label>用户姓名：</span>
	              			<span class="info-content">
	              				<input type="text" id="name" name="username" value="${empty requestScope.user ? '': requestScope.user.username}"/><label id="username"> </label>
	              			</span>
	           			</p>
	           			<p>
	           				<span class="info-label"><label> * </label>电子邮件：</span>
	              			<span class="info-content">
	              				<input type="text" id="email" name="email" value="${empty requestScope.user ? '': requestScope.user.email}"/><label id="emailaddress"> </label>
	              			</span>
	           			</p>
	           			<%-- <p>
	           				<span class="info-label"><label> * </label>角色：</span>
	              			<span class="info-content">
	              				<input type="text" id="rolename" readonly="readonly" name="rolename" value="${empty requestScope.user ? '': requestScope.user.role.name}"/><label id="role"> </label>
	              			</span>
	           			</p> --%>
	           			 <p>
	         				<span class="info-label"><label> * </label>手机号码：</span>
	            			<span class="info-content">
	            				<input type="text" id="mobileNumber" name="mobile" value="${empty requestScope.user ? '': requestScope.user.mobile}"/><label> </label>
	            			</span>
	         			</p>
	           			<%-- <p>
	         				<span class="info-label"><label> * </label>所属部门：</span>
	            			<span class="info-content">
	            				<select id="department" name="department">
	            					<c:forEach items="${requestScope.deps}" var="departments">
           								<option value="${departments.id}" ${user.departmentId eq departments.id?'selected="selected"' :''}>
           									${departments.name }
           								</option>
	            					</c:forEach>
	            					
	            					
	            					
	            					
								</select><label> </label>
	            			</span>
	         			</p> --%>
	         			<%-- <p>
	         				<span class="info-label"><label> * </label>省份：</span>
	            			<span class="info-content">
	            				<select id="provinceValue" name="provinceValue">
	            					<c:forEach items="${requestScope.provinces}" var="provinceInfo">
           								<option value="${provinceInfo.provinceId}" ${user.provinceId eq provinceInfo.provinceId?'selected="selected"' :''}>
           									${provinceInfo.provinceId }(${provinceInfo.provinceName })
           								</option>
	            					</c:forEach>
	            					
	            					
	            					
	            					
								</select><label> </label>
	            			</span>
	         			</p> --%>
		            </div>
		            <%-- <div class="tab">
		            	<div class="tip" style="display: none;" id="xtip">
		            	
		            	</div>
	         			<p>
	         				<span class="info-label">公司名称：</span>
	            			<span class="info-content">
	            				<input type="text" id="company" name="company" value="${empty requestScope.user ? '': requestScope.user.company}"/><label> </label>
	            			</span>
	         			</p>
		            </div> --%>
		        </div>
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
	        </form>
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
    	</div>
    	
	</body>
</html>