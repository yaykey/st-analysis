<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>指标配置列表</title>
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
			            if (this.value == "请输入查询的指标名") {
			                this.value = "";
			                $("#condition").addClass("term");
			            }
			        });
			        $(this).blur(function() {
			            //失去焦点时，如果值为空，则设置为默认值
			            if (this.value == "") {
			            	$("#condition").removeClass("term");
			                this.value = "请输入查询的指标名";
			                
			            }
			        });
			    });
			  	$('#condition').bind('keyup', function(event){
				   if (event.keyCode=="13"){
					   var condition = $("#condition").val();
					   if(condition=="请输入查询的指标名"){
							condition = "";
						}
						$('#list').attr('action', '${baseManager}/index/list.${actionExt}?indexName_=' + condition);
						$('#list').submit();
				   }
				});
				
				//点击查询
				$("#search").click(function(){
					var condition = $("#condition").val();
					if(condition=="请输入查询的指标名"){
						condition = "";
					}
					$('#list').attr('action', '${baseManager}/index/list.${actionExt}?indexName_=' + condition);
					$('#list').submit();
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
				$('#add').click(function(){
					$('#list').attr('action', '${baseManager}/index/toAdd.${actionExt}');
					$('#list').submit();
				});
				//修改
				$('#update').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个要修改的指标");
						return false;
					} else if ($("input:checked").size() > 1) {
						alert("一次只能选择一个要修改的指标");
						return false;
					}else{
						$('#list').attr('action', '${baseManager}/index/input.${actionExt}?type=edit');
						$('#list').submit();
					}
				});
				//删除
				$('#delete').click(function(){
					if ($("input:checked").size() == 0) {
						alert("请选择一个删除的指标");
						return false;
					}else{
						if(!confirm("确定要删除选择的指标吗?")){
							return false;
						}
						var userId = [];
						$("input:checked").each(function(i,val){
							userId[i] = $(this).val();
						});
						$('#list').attr('action', '${baseManager}/index/delete.${actionExt}?items='+userId);
						$('#list').submit();
					}
				});
			});
		
		function showDetail(indexId){
			list.action = "${baseManager}/index/view.${actionExt}?indexId="+indexId;
			list.submit();
		}
	</script>
  </head>
  
  <body>
  <form action="#" method="post" id="list" name="list">
	    <ul class="user-operation">
	    	<li>
	    		<p>
	   				<font color="#4F6B72">指标名称</font>
	   				<input type="text" class="condition" id="condition" value="${empty requestScope.indexName_ ? '请输入查询的指标名':requestScope.indexName_}" />
	   				<input type="button" id="search" class="search" style="background-image: url('${baseManager}/jsp/selfinfo/images/search.png'); "/>
	   				&nbsp;&nbsp;
	   				<input type="button" id="add" value="添加"/>
	    			<input type="button" id="update" value="修改"/>
	    			<input type="button" id="delete" value="删除"/>
	    		</p>
	    	</li>
	    	
	    </ul>
	    <div class="user-list">
	    	<c:choose>
			 	<c:when test="${empty requestScope.indexList}">
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
			    				<th>指标ID</th>
			    				<th>指标名称</th>
				    			<th>指标类型</th> 
			    				<th>计算类型</th>
			    				<th>其他</th>
			    			</tr>
			    		</thead>
			    		<tbody>
				    		<c:forEach items="${requestScope.indexList}" var="index">
				    			<tr>
				    				<td>
				    					<input name="choiceItem" id="choiceItem" type="checkbox" value="${index.indexId}">
				    				</td>
				    				<td>${index.indexId}</td>
 				    				<td>${index.indexName}</td>
				    				<td>${index.indexType}</td>
				    				<td>${index.indexCalType}</td>
				    				<td>
				    					<input type="button" onclick="showDetail(${index.indexId})" value="查看详情"/>
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
				if(condition_=="请输入查询的指标名"){
					condition_ = "";
				}
				
				list.action = "${baseManager}/index/list.${actionExt}?page="
						+ pageNum+"&indexName_="+condition_;
				list.submit();
			}
		}
    </script>
  </body>
</html>
