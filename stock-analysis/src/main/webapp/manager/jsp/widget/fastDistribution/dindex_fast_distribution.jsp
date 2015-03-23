<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/manager/commons/meta.jsp"%>
<html>
<head>
<title>快速分配指标功能</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">

</head>
<body style="background-color: #FCFAF3">
	<form method="post" name="dIndexSelectForm" id="addlist">
		<input type="hidden" name="widgetId"
			value="${requestScope.widgetId}" id="widgetId" />
		<div class="title-bar-top">给维度分配指标</div>
		<div class="data-table" id="content">
			<table align="center" class="pop-window" valign="top" width="80%"
				height="150">
				<tr>
					<td width="48%" class="td-right">
						<div align="center">
							<strong>维度</strong>
						</div>
					</td>
					<td width="20" class="td-center"></td>
					<td width="48%" class="td-left">
						<div align="left">
							<strong>指标</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="border-collapse: collapse;">
				    		<thead>
				    			<tr class="test">
				    				<th width="20">
				    					<a href="#" id="dim_checkall" title="全选"><img id="dim_check_all" border="0" src="${base}/manager/images/check_all.gif" width="13" height="13" alt="全选"></a>&nbsp;&nbsp;
								    	<a href="#" id="dim_checknull" title="取消"><img id="dim_check_none" border="0" src="${base}/manager/images/check_none.gif" width="13" height="13" alt="取消"></a>
				    				</th>
				    				<th>维度名称</th>
				    			</tr>
				    		</thead>
			    		</table>
			    	</td>
			    	<td width="20">&nbsp;</td>
			    	<td>
			    		<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="border-collapse: collapse;">
				    		<thead>
				    			<tr class="test">
				    				<th width="20">
				    					<a href="#" id="dIndex_checkall" title="全选">
				    						<img id="dIndex_check_all" border="0" src="${base}/manager/images/check_all.gif" 
				    								width="13" height="13" alt="全选">
				    					</a>&nbsp;&nbsp;
								    	<a href="#" id="dIndex_checknull" title="取消">
								    		<img id="dIndex_check_none" border="0" src="${base}/manager/images/check_none.gif" 
								    				width="13" height="13" alt="取消">
								    	</a>
				    				</th>
				    				<th>指标名称</th>
				    			</tr>
				    		</thead>
			    		</table>
			    	</td>
				</tr>
				<tr>
					<td class="td-right" style="vertical-align: top; height:300px;">
						<div style="overflow-y:scroll;height:100%;">
							<table id="dim_table" width="100%" border="0" cellspacing="0" cellpadding="0"
									style="border-collapse: collapse;">
								<tbody>
								<c:forEach var="dim" items="${requestScope.dimList}">
									<tr class="tr_test" style="CURSOR: hand">
										<td width="20">
											<input name="choiceDims" type="checkbox" value="${dim.id}" dimTypeId="${dim.dimTypeId}" dimId="${dim.dimId}">
										</td>										
										<td>
											${dim.dimName}
										</td>
									</tr>
								</c:forEach>							
								</tbody>
							</table>
						</div>
					</td>
					<td width="20" class="td-center" style="vertical-align: top">

						<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
							
							<tr>
								<td height="21">
									<div align="center"></div></td>
							</tr>
							<tr>
								<td height="20">
									<div align="center">
										<input name="add" type="button" class="button" id="btn_add"
											value="增加">
									</div></td>
							</tr>
							<tr>
								<td height="21">
									<div align="center"></div></td>
							</tr>
							<tr>
								<td height="23">
									<div align="center">
										<input name="remove" type="button" class="button"
											id="btn_remove" value="移除">
									</div></td>
							</tr>
							<tr>
								<td height="22">
									<div align="center"></div></td>
							</tr>
							<tr>
								<td height="28">
									<div align="center">
										<input name="removeAll" type="button" class="button"
											id="btn_removeAll" value="全部移除">
									</div></td>
							</tr>

						</table> -->
					</td>
					<td class="td-left" style="vertical-align: top;">
						<div style="overflow-y:scroll;height:100%;">
							<table id="dIndex_table" width="100%" border="0" cellspacing="0" cellpadding="0"
									style="border-collapse: collapse;">
								<tbody>
									<c:forEach var="dIndex" items="${requestScope.indexList}">
										<tr class="tr_test" style="CURSOR: hand">
											<td width="20">
												<input type="checkbox" name="choiceDIndexs" value="${dIndex.indexId}" index_id="${dIndex.indexId}">
											</td>
											<td>
												${dIndex.indexName}
											</td>
										</tr>
									</c:forEach>							
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<input type="button" value="提交" id="fast-save" name="fast-save" />
			<input type="button" value="取消" id="fast-cancel" name="fast-cancel" />
		</div>
	</form>
<script type="text/javascript">
	$(document).ready(function(){
			
			//隔行换色
		  $('table tbody tr:odd').addClass('odd'); //奇数行
	      $('table tbody tr:even').addClass('even'); //偶数行
	      
	      //选中
	      $('#dim_table>tbody>tr').click(function(){
	    	  var hasSelected = $(this).hasClass('selected');
	    	  $(this)[hasSelected?"removeClass":"addClass"]('selected').find(":checkbox").attr('checked',!hasSelected);
	      });
	      
	      $('#dIndex_table>tbody>tr').click(function(){
	    	  var hasSelected = $(this).hasClass('selected');
	    	  $(this)[hasSelected?"removeClass":"addClass"]('selected').find(":checkbox").attr('checked',!hasSelected);
	      });
	
	    //表格点击选中复选框
		$("#dim_checkall").click(function(){
			checkall($(":input[name='choiceDims']"));
			$('table tbody tr').addClass('selected')
		});
		$("#dim_checknull").click(function(){
			checknull($(":input[name='choiceDims']"));
			$('table tbody tr').removeClass('selected')
		});
		
		$("#dIndex_checkall").click(function(){
			checkall($(":input[name='choiceDIndexs']"));
			$('table tbody tr').addClass('selected')
		});
		$("#dIndex_checknull").click(function(){
			checknull($(":input[name='choiceDIndexs']"));
			$('table tbody tr').removeClass('selected')
		});
		
		
        $("#fast-save").bind('click', function(){
        	if(!confirm("确定提交吗?")){
				return false;
			}
			$("#addlist").attr("action","${base}/manager/widgetDim/saveFastDDindex.feinno");
			$("#addlist").submit();
			
			window.close();
        });
        
        $("#fast-cancel").bind('click', function(){
        	if(!confirm("确定取消吗?")){
				return false;
			}
			
			/* $("#addlist").attr("action","${base}/manager/widgetDim/list.feinno");
			$("#addlist").submit(); */
			
			window.close();
        });
        
	});
	
</script>
</body>
</html>