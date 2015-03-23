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
		    $(document).ready(function() {
		    	//表单提交
	    		$('#btn_submit').bind('click', function(){
					$("#signupform").attr("action","${baseManager}/department/modify.${actionExt}?type=edit&departmentName_=${departmentName_}");
	    		});
	    		$('#btn_back').bind('click', function() {
	    			window.location.href = '${baseManager}/department/list.${actionExt}?departmentName_=${departmentName_}';
	    		});
	    		
	    		//部门下拉框
// 	    		$("select[name=department] option").each(function(){
// 	    	    	if($(this).val() == $("#dep").val()){
// 	    			   $(this).attr("selected","selected");
// 	    			  }
// 	    		});
	    		
				//表单验证
// 				jQuery.validator.addMethod("telphoneValid", function(value, element) {
// 					var tel = /^(130|131|132|133|134|135|136|137|138|139|150|152|152|153|157|158|159|180|182|185|186|187|188|189)\d{8}$/;
// 				    return tel.test(value) || this.optional(element);
// 				}, "请输入正确的手机号码");
				jQuery.validator.addMethod("numberWord", function(value, element) {
// 					var chrnum = /^([a-zA-Z0-9]+)$/;
				    var chrnum = /^\d+$/;//正整数+0
					return this.optional(element) || (chrnum.test(value));
				}, "请输入正整数或0");
				var validator = $("#signupform").validate({
					rules: {
// 						loginID: {
// 							required: true,
//  							numberWord: true,
// 							minlength: 6,
// 							maxlength: 20,
// 							remote: {
// 								type : "POST",
// 		    					url : '${base}/manager/department/verifyDepartmentName.${actionExt}',
// 		    					data : {
// 		    						"nocache" : new Date().getTime(),
// 		    						loginID: function() {
// 		    							return $("#loginID").val();
// 		    						},
// 		    						userId: function() {
// 		    							return $("#userId").val();
// 		    						}
// 		    					}
// 							}
// 						},
// 						name: {
// 							required: true,
// 							minlength: 2
// 						},
// 						email: {
// 							required: true,
// 							email: true,
// 							remote: {
// 								type : "POST",
// 		    					url : '${base}/manager/user/verifyEmail.${actionExt}',
// 		    					data : {
// 		    						"nocache" : new Date().getTime(),
// 		    						email: function() {
// 		    							return $("#email").val();
// 		    						},
// 			    					userId: function() {
// 		    							return $("#userId").val();
// 		    						}
// 		    					}
// 							}
// 						},
						/* rolename: {
							required: true
						}, */
// 						department: {
// 							required: true
// 						},
// 						mobileNumber: {
// 							telphoneValid:true,
// 							required: true
// 						},
						departmentName: {
							required: true
						},
						sort:{
							required: true,
							numberWord: true
						}
						
					},
					messages: {
// 						loginID: {
// 							required: "请输入6-20位字母、数字",
// 							minlength: jQuery.format("请输入至少 {0}位数字、字母"),
// 							maxlength: jQuery.format("请输入最多 {0}位数字、字母"),
// 							remote: "此用户名已经存在"
// 						},
						departmentName: "请输入部门名称",
						parent_Id:"请选择上级部门",
						sort:"请输入显示顺序,显示顺序只能是正整数或0"
					
// 						email: {
// 							required: "请输入正确的电子邮箱",
// 							minlength: "请输入正确的电子邮箱",
// 							remote: "此电子邮箱已存在"
// 						},
// 						rolename: "请选择角色",
// 						department: "请选择所属部门",
// 						mobileNumber:{
// 							required: "请输入正确的手机号码",
// 							telphoneValid: "请输入正确的手机号码"
// 						}
						
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
	            <li><a href="#base">部门资料 <label style="color: red;"> *</label></a></li>
<!-- 	            <li><a href="#detail">详细信息</a></li> -->
	        </ul>
	        <form action="#" method="post" id="signupform" name="signupform">
	        	<input type="hidden" id="departmentId" name="departmentId" value="${empty requestScope.department ? '': requestScope.department.id}" />
	        	<input type="hidden" id="type" name="type" value="${empty requestScope.type ? '': requestScope.type}"/>
	        	<%-- <input type="hidden" id="role" name="role" value="${empty requestScope.user ? '': requestScope.user.role.id}"/> --%>
		        <div class="tabsContent">
		            <div class="tab">
		            	<p>
	           				<span class="info-label"><label> * </label>部门名称：</span>
	              			<span class="info-content">
	              				<input type="text" id="departmentName" name="departmentName" value="${department.name}"/><label id="departmentName"> </label>
	              			</span>
	           			</p>
		            	<p>
	           				<span class="info-label">是否显示：</span>
	              			<span class="info-content">
	            				<select id="show_Enable" name="show_Enable">
	            					<c:choose>
	            						<c:when test="${department.showEnable eq 0 }">
	            							<option value="0" selected="selected">否</option>
	            							<option value="1">是</option>
	            						</c:when>
	            						<c:otherwise>
		            						<option value="0">否</option>
		            						<option value="1" selected="selected">是</option>
	            						</c:otherwise>
	            					</c:choose>
								</select>
								<label> </label>
	              			</span>
	           			</p>
	           			<p>
	           				<span class="info-label"><label> * </label>显示排序：</span>
	              			<span class="info-content">
	              				<input type="text" id="sort" name="sort" value="${department.sort}"/><label id="sort"> </label>
	              			</span>
	           			</p>
		            	<p>
	           				<span class="info-label">上级部门：</span>
	              			<span class="info-content">
	            				<select id="parent_Id" name="parent_Id">
	            					<option value="0">无上级部门</option>
	            					<c:forEach items="${requestScope.departmentList}" var="departments">
           								<option value="${departments.id}" ${department.parentId eq departments.id?'selected="selected"' :''}>
           									${departments.name }
           								</option>
	            					</c:forEach>
								</select>
								<label> </label>
	              			</span>
	           			</p>
		            	
		            </div>
		        </div>
		        <div align="center" class="info-button">
		        	<input type="submit" value="保存" id="btn_submit" />
		        	<input type="button" value="返回" id="btn_back"/>
		        </div>
	        </form>
    	</div>
    	
	</body>
</html>