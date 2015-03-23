<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
	<script type="text/javascript">
		$(document).ready(
			function(){
				//隔行换色
			  $('table tbody tr:odd').addClass('odd'); //奇数行
		      $('table tbody tr:even').addClass('even'); //偶数行
		      	//选中
		      $('tbody>tr',this).click(function(){
		    	  var hasSelected = $(this).hasClass('selected');
		    	  $(this)[hasSelected?"removeClass":"addClass"]('selected').find(":checkbox").attr('checked',!hasSelected);
		      }); 

				//查询框点击
			  $("#condition").each(function() {
			        //保存当前文本框的值
			        var vdefault = this.value;
			        $(this).focus(function() {
			            //获得焦点时，如果值为默认值，则设置为空
			            if (this.value == "请输入查询的部门名") {
			                this.value = "";
			                $("#condition").addClass("term");
			            }
			        });
			        $(this).blur(function() {
			            //失去焦点时，如果值为空，则设置为默认值
			            if (this.value == "") {
			            	$("#condition").removeClass("term");
			                this.value = "请输入查询的部门名";
			                
			            }
			        });
			    });
			  	$('#condition').bind('keyup', function(event){
				   if (event.keyCode=="13"){
					   var condition = $("#condition").val();
					   if(condition=="请输入查询的部门名"){
							condition = "";
						}
						$('#department_data').attr('action', '${baseManager}/department/list.${actionExt}?departmentName_=' + condition);
						$('#department_data').submit();
				   }
				});
				
				//点击查询
				$("#search").click(function(){
					var condition = $("#condition").val();
					if(condition=="请输入查询的部门名"){
						condition = "";
					}
					$('#department_data').attr('action', '${baseManager}/department/list.${actionExt}?departmentName_=' + condition);
					$('#department_data').submit();
				});
			  
				//表格点击选中复选框
				$("#checkall").click(function(){
					checkall($(":input[name='choiceItem']"));
					$('table tbody tr').addClass('selected')
				});
				$("#checknull").click(function(){
					checknull($(":input[name='choiceItem']"));
					$('table tbody tr').removeClass('selected')
				});
				
				//添加
				$('#department-add').click(function(){
					$('#department_data').attr('action', '${baseManager}/department/toAdd.${actionExt}');
					$('#department_data').submit();
				});
				//修改
				$('#department-edit').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个要修改的部门");
						return false;
					} else if ($("input:checked").size() > 1) {
						alert("一次只能选择一个要修改的部门");
						return false;
					}else{
						$('#department_data').attr('action', '${baseManager}/department/input.${actionExt}?type=edit&departmentName_=${empty requestScope.departmentName_ ? '请输入查询的部门名':requestScope.departmentName_}');
						$('#department_data').submit();
					}
				});
				//删除
				$('#department-delete').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个删除的部门");
						return false;
					}else{
						if(!confirm("确定要删除选择的部门吗?")){
							return false;
						}
						var userId = [];
						$("input:checked").each(function(i,val){
							userId[i] = $(this).val();
						});
						$('#department_data').attr('action', '${baseManager}/department/delete.${actionExt}?items='+userId);
						$('#department_data').submit();
					}
				});
			});
		
		function showDetail(departmentId){
			department_data.action = "${baseManager}/department/view.${actionExt}?departmentId="+departmentId;
			department_data.submit();
		}
	</script>
  </head>
  
  <body>
  <form action="#" method="post" id="department_data" name="department_data">
	    <ul class="user-operation">
	    	<li>
	    		<p>
	   				<font color="#4F6B72">部门名称：</font>
	   				<input type="text" class="condition" id="condition" value="${empty requestScope.departmentName_ ? '请输入查询的部门名':requestScope.departmentName_}" />
	   				<input type="button" id="search" class="search" style="background-image: url('${baseManager}/jsp/selfinfo/images/search.png'); "/>&nbsp;&nbsp;
	   				<input type="button" id="department-add" value="添加"/>
	    			<input type="button" id="department-edit" value="修改"/>
	    			<input type="button" id="department-delete" value="删除"/>
	    		</p>
	    	</li>
	    	
	    </ul>
	    <div class="user-list">
	    	<c:choose>
			 	<c:when test="${empty requestScope.departmentList}">
					无指定条件数据！
				</c:when>
				<c:otherwise>
					<table id="users" class="users">
			    		<thead>
			    			<tr>
			    				<th>
			    					<a href="#" id="checkall" title="全选"><img id="check_all" border="0" src="${base}/manager/images/check_all.gif" width="13" height="13" alt="全选"></a>&nbsp;&nbsp;
							    	<a href="#" id="checknull" title="取消"><img id="check_none" border="0" src="${base}/manager/images/check_none.gif" width="13" height="13" alt="取消"></a>
			    				</th>
			    				<th>部门ID</th>
			    				<th>部门名称</th>
<!-- 			    				<th>电子邮件</th> -->
			    				<th>所属部门</th>
<!-- 			    				<th>角色</th> -->
			    				<th>其他</th>
			    			</tr>
			    		</thead>
			    		<tbody>
				    		<c:forEach items="${requestScope.departmentList}" var="department">
				    			<tr>
				    				<td>
				    					<input name="choiceItem" id="choiceItem" type="checkbox" value="${department.id}">
				    				</td>
				    				<td>${department.id }</td>
<%-- 				    				<td>${user.loginId }</td> --%>
				    				<td>${department.name }</td>
				    				<td>${department.department.name}</td>
<%-- 				    				<td>${user.emailAddress }</td> --%>
<%-- 				    				<td>${user.department.name }</td> --%>
<!-- 				    				<td> -->
<%-- 				    					<c:if test="${user.roles != null }"> --%>
<%-- 				    						<c:forEach var="role" items="${user.roles }" > --%>
<%-- 				    							${role.name } --%>
<%-- 				    						</c:forEach> --%>
<%-- 				    					</c:if> --%>
<!-- 				    				</td> -->
				    				<td>
				    					<input type="button" onclick="showDetail(${department.id})" value="查看详情"/>
				    				</td>
				    			</tr>
				    		</c:forEach>
				    		
				    		<%@ include file="/manager/commons/page.jsp"%>
			    		</tbody>
			    	</table>
				</c:otherwise>
			</c:choose>
    	</div>
    </form>
    
    <script type="text/javascript">
	    function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				var condition_ = $("#condition").val();
				if(condition_=="请输入查询的部门名"){
					condition_ = "";
				}
				
				department_data.action = "${baseManager}/department/list.${actionExt}?page="
						+ pageNum+"&departmentName_="+condition_;
				department_data.submit();
			}
		}
    </script>
  </body>
</html>
