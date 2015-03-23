<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/manager/js/easyui/easyui.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/manager/js/easyui/icon.css'/>"/>
	<script type="text/javascript" src="<c:url value='/manager/js/My97DatePicker/WdatePicker.js'/>"></script>
	<script type="text/javascript" src="<c:url value="/manager/jsp/user/js/user.js"/>"></script>
  </head>
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
			            if (this.value == "请输入查询的用户名") {
			                this.value = "";
			                $("#condition").addClass("term");
			            }
			        });
			        $(this).blur(function() {
			            //失去焦点时，如果值为空，则设置为默认值
			            if (this.value == "") {
			            	$("#condition").removeClass("term");
			                this.value = "请输入查询的用户名";
			                
			            }
			        });
			    });
			  	$('#condition').bind('keyup', function(event){
				   if (event.keyCode=="13"){
					   var condition = $("#condition").val();
					   if(condition=="请输入查询的用户名"){
							condition = "";
						}
						$('#user_data').attr('action', '${baseManager}/user/list.${actionExt}?userName_=' + condition);
						$('#user_data').submit();
				   }
				});
				
				//点击查询
				$("#search").click(function(){
					var condition = $("#condition").val();
					if(condition=="请输入查询的用户名"){
						condition = "";
					}
					$('#user_data').attr('action', '${baseManager}/user/list.${actionExt}?userName_=' + condition);
					$('#user_data').submit();
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
				$('#user-add').click(function(){
					$('#user_data').attr('action', '${baseManager}/user/toAdduser.${actionExt}');
					$('#user_data').submit();
				});
				//修改
				$('#user-edit').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个要修改的用户");
						return false;
					} else if ($("input:checked").size() > 1) {
						alert("一次只能选择一个要修改的用户");
						return false;
					}else{
						$('#user_data').attr('action', '${baseManager}/user/input.${actionExt}?type=edit&userName_=${userName_}');
						$('#user_data').submit();
					}
				});
				//删除
				$('#user-delete').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个删除的用户");
						return false;
					}else{
						if(!confirm("确定要删除选择的用户吗?")){
							return false;
						}
						var userId = [];
						$("input:checked").each(function(i,val){
							userId[i] = $(this).val();
						});
						$('#user_data').attr('action', '${baseManager}/user/delete.${actionExt}?items='+userId);
						$('#user_data').submit();
					}
				});
				//失效
				$('#user-Lose').click(function(){
					batchStauts(0);
				});
				//生效
				$('#user-Effect').click(function(){
					batchStauts(1);
				});
				
				
				//此处根据action中传来的json串，插入页面中的省份列
				/* var tmp =  ${requestScope.buffer};
				var i=0;
				$("table tbody tr").each(function(){
					var userId = $(this).find("td:eq(1)").text();//检测是否是有效行
					var td = $(this).find("td:eq(6)");
					if(userId != null && userId != ""){
						td.html(tmp[i].provinceName);
						i++;
					}
				}); */
				
				
			});
		
	
	</script>
  <body>
  <div  class="version_4">
  <h2  class="tit_1" style="text-align:left;">
  	用户管理
  <span style="float:right;padding-right:10px">
	    <a id="user-add" class="l-btn l-btn-plain" href="javascript:void(0)">
		<span class="l-btn-left">
			<span class="l-btn-text icon-add l-btn-icon-left">新增</span>
		</span>
		</a>
		<a id="user-edit" class="l-btn l-btn-plain" href="javascript:void(0)">
		<span class="l-btn-left">
			<span class="l-btn-text icon-edit l-btn-icon-left">修改</span>
		</span>
		</a>
		 <a  id="user-delete" class="l-btn l-btn-plain" href="javascript:void(0)">
		<span class="l-btn-left">
			<span class="l-btn-text icon-cancel l-btn-icon-left">删除</span>
		</span>
		</a>
		 <a  id="user-Effect" class="l-btn l-btn-plain" href="javascript:void(0)">
		<span class="l-btn-left">
			<span class="l-btn-text icon-ok l-btn-icon-left">生效</span>
		</span>
		</a>
		 <a  id="user-Lose" class="l-btn l-btn-plain" href="javascript:void(0)">
		<span class="l-btn-left">
			<span class="l-btn-text icon-no l-btn-icon-left">失效</span>
		</span>
		</a>
   </span>
  </h2>
   <div class="list_bg">
  <form action="#" method="post" id="user_data" name="user_data">
	    <ul class="user-operation">
	    	<li>
	    		<%-- <font color="#4F6B72">状态：</font>
   				<select name="efflose"  id="efflose">
   				<option value="">请选择</option>
   				<option value="1" <c:if test="${ifEnable==1}">selected</c:if>>激活</option>
   				<option value="0" <c:if test="${ifEnable==0}">selected</c:if>>失效</option>
   				</select> --%>
   				<%-- <font color="#4F6B72">失效日期：</font>
   				<input type="text" style="width:90px;" maxlength="10" onfocus="WdatePicker({skin:'whyGreen'})" id="statDate" name="statDate" value="${empty requestScope.statDate? '':requestScope.statDate}" />到
   				<input type="text" style="width:90px;" maxlength="10" onfocus="WdatePicker({skin:'whyGreen'})" id="endDate" name="endDate" value="${empty requestScope.endDate? '':requestScope.endDate}" /> --%>
   				<font color="#4F6B72">用户名称：</font>
   				<input type="text" class="condition" id="condition" value="${empty requestScope.userName_ ? '请输入查询的用户名':requestScope.userName_}" />
   				<input type="hidden" class="condition" id="department" value="${requestScope.departmentId }" />
   				<input type="button" id="search" class="search" style="background-image: url('${baseManager}/jsp/selfinfo/images/search.png'); "/>&nbsp;&nbsp;
	    	</li>
	    	
	    </ul>
	    <div class="user-list">
	    	<c:choose>
			 	<c:when test="${empty requestScope.userList}">
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
			    				<th width="40px">用户ID</th>
			    				<!-- <th>登陆ID</th> -->
			    				<th width="80px">用户名称</th>
			    				<th>电子邮件</th>
			    				<!-- <th  width="80px">所属部门</th> -->
			    				<!-- <th  width="50px">省份</th> -->
			    				<th>角色</th>
			    				<th>创建日期</th>
			    				<th width="100px">激活状态</th>
			    				<th>其他</th>
			    			</tr>
			    		</thead>
			    		<tbody>
				    		<c:forEach items="${requestScope.userList}" var="user">
				    			<tr>
				    				<td>
				    					<input name="choiceItem" id="choiceItem" realname="${user.username }" type="checkbox" value="${user.id}">
				    					<input name="username" id="loginId" type="hidden" value="${user.username}">
				    				</td>
				    				<td>${user.id }</td>
				    				<%-- <td>${user.loginId }</td> --%>
				    				<td>${user.username }</td>
				    				<td>${user.email }</td>
				    				<%-- <td>${user.department.name }</td> --%>
				    				<!-- <td>&nbsp;</td> -->
				    				<td>
				    					<c:if test="${user.roles != null }">
				    						<c:forEach var="role" items="${user.roles }" >
				    							${role.name }
				    						</c:forEach>
				    					</c:if>
				    				</td>
				    				<%-- <td>
				    				<input type="text" style="width:90px;" maxlength="10" id="expDate_${user.id}" onChange="changeDate(this,'${user.id}');" 
				    					onfocus="WdatePicker({skin:'whyGreen'})" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${user.expDate}" type="both" />">
				    				</td> --%>
				    				<td>
				    				<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${user.createTime}" />
				    				</td>
				    				<td>
				    				<c:choose>
										<c:when test="${user.isEffect == 0}">
			   							 <span class="double_right_tab1 fr">失效</span>
			   							 <span class="double_left_tab1 fr" onclick="loseStauts(this,'${user.id}',1)">激活</span>
										</c:when>
										<c:otherwise>
			   							 <span class="double_right_tab2 fr" onclick="loseStauts(this,'${user.id}',0)">失效</span>
			   							 <span class="double_left_tab2 fr">激活</span>
										</c:otherwise>
									</c:choose>
				    				</td>
				    				<td>
				    					<input type="button" onclick="showDetail(${user.id})" value="查看详情"/>
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
    </div>
    </div>
  </body>
</html>
