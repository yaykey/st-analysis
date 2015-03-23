<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>

<html>
<head>
    <title>功能维度信息列表</title>
    <%@include file="/manager/commons/meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/manager/jsp/user/css/layout.css"/>">
    <script type='text/javascript' src='${base}/dwr/interface/refreshReport.js'></script>
	<script type='text/javascript' src='${base}/js/dwr/engine.js'> </script>
	<script type='text/javascript' src='${base}/js/dwr/util.js'> </script>
	 
	 
<style type="text/css">


.data-table input {
	text-align: center;
    width: 30px;
}
.data-table .write {
 	border-color:  #FFA500;
}
</style>
	 
   <script type="text/javascript">
	   $(document).ready(function(){
		   $("#checkall").click(function(){
				checkall($(":input[name='choiceItem']"));
			});
			$("#checknull").click(function(){
				checknull($(":input[name='choiceItem']"));
			});
			$('#btn_add').bind('click', addInfo); 
			$('#btn_del').bind('click', deleteInfo);
			//$('#btn_upd').bind('click', updateInfo); 
			$('#authorise_extend').bind('click', authoriseExtend); 
			/* $('#info_edit').bind('click', showEditInfo);  */
			$('#btn_return').bind('click', function() {
				var fid = $("#functionId").val();
				
				if (!fid) {
					fid = "";
				}
				
				window.location.href = "${base}/manager/widget/list_widget.feinno?functionId=" + fid;
			});
	   });
		
		function updateInfo(){
			if (checkboxCount(list.choiceItem) == 0) {
				alert("请选择一条要修改的信息");
				return false;
			} else if (checkboxCount(list.choiceItem) > 1) {
				alert("只能选择一条要修改的信息");
				return false;
			} else {
				var dt = new Date();
				$("#list").attr("action","${base}/manager/widgetDim/edit.feinno?dt"+dt);
				$("#list").submit();	
			}
			
		}
		
		function deleteInfo(){
			if (checkboxCount(list.choiceItem) == 0) {
				alert("请选择一条要删除的信息");
				return false;
			}else{
				if(!confirm("确定要删除选择的信息吗?")){
					return false;
				}
				var dt = new Date();
				$("#list").attr("action","${base}/manager/widgetDim/delete.feinno?dt"+dt);
				$("#list").submit();
			}
		}
		
		function addInfo(){
			
			window.location.href = "${base}/manager/widgetDim/getAddWidgetDim.feinno?widgetId="+
				$("#widgetId").attr("value")+"&dt="+new Date();
		}
		
		function authoriseExtend(widgetDimId){
			window.location.href = "${base}/manager/widgetDim/getWidgetDimExtend.feinno?widgetDimId="+widgetDimId+
					"&widgetId="+$("#widgetId").attr("value");
		}
		
		var click_checkbox="false";
		function checkbox_click(){
			click_checkbox = "true";
		}
		
		function trSelect(idTid) {
			
			if(click_checkbox != "true")
			{
				var tidObj = list.choiceItem;
				
				if(!tidObj.length)
				{
					
					if(tidObj.value == idTid)
					{
						if(tidObj.checked)
							tidObj.checked = false;
						else
							tidObj.checked = true;
					}
				}else{
					
					for(var i=0;i<tidObj.length ; i++)
					{
						
						if(tidObj[i].value == idTid)
						{
							if(tidObj[i].checked)
								tidObj[i].checked = false;
							else
								tidObj[i].checked = true;
						}
					}
				}
			}
			
			click_checkbox = "false";
			
		}
		
		var temp_opt_string = "";
		var temp_widget_dim_id = "";
		var temp_is_selected = "";
		var widget_dim_id = "";
		
		
		/* //显示编辑信息
		function showEditInfo(ids,us_table){
			var table_rows = us_table.rows;

			for(var i=1;i<table_rows.length;i++)
			{
				
				if($.trim(table_rows[i].cells[1].innerHTML)+"" == ids+""){
					
					
					widget_dim_id = $.trim(table_rows[i].cells[1].innerHTML);
					
					if(temp_widget_dim_id != "" && temp_widget_dim_id != widget_dim_id){ 
						cancleShowEditInfo(us_table);
					}
					
					temp_widget_dim_id = $.trim(table_rows[i].cells[1].innerHTML);
					temp_is_selected = $.trim(table_rows[i].cells[6].innerHTML);
					temp_opt_string = $.trim(table_rows[i].cells[8].innerHTML);
					
					var tem_ = "&nbsp;<span title=\"保存\"><a href=\"javascript:saveShowEditInfo('"+temp_widget_dim_id+"',tab)\">"+
					"<img src=\"${base}/manager/images/ok.gif\" border=\"0\"></a></span>"+ 
					"&nbsp;<span title=\"取消\"><a href=\"javascript:cancleShowEditInfo(tab)\">"+
					"<img src=\"${base}/manager/images/cancel.gif\" border=\"0\"></a></span>";

					table_rows[i].cells[6].innerHTML = "<input type=\"input\" id=\"widget_dim_id_"+temp_widget_dim_id+
						"\" maxLength=\"1\" name=\"widget_dim_id_"+temp_widget_dim_id+"\" value=\""+temp_is_selected+"\"  size='10'>";
					
					table_rows[i].cells[8].innerHTML = tem_;
					
				}
			}
		
		} */
		

		//取消编辑信息
		function cancleShowEditInfo(us_table){
			
			var table_rows = us_table.rows;
			for(var i=1;i<table_rows.length;i++)
			{
				if(temp_widget_dim_id==$.trim(table_rows[i].cells[1].innerHTML)){
					table_rows[i].cells[6].innerHTML = temp_is_selected;
					table_rows[i].cells[8].innerHTML = temp_opt_string;
				}
			}
		
		}
		

		//保存编辑信息
		var save_ids = "";
		function saveShowEditInfo(ids,us_table){
			if(confirm("是否确定要保存?")){
				var widget_dim_id_ = document.getElementById("widget_dim_id_"+ids).value;
		        if(widget_dim_id_.length==0)
		        {
		            alert('是否选择不能为空，请重新输入');
		            return ;
		     	}else if(!(/^[0,1]/g.test(widget_dim_id_)))
		     	{
		        	alert('是否选择的值只能是0或1，请重新输入');
		        	return ;
		     	}
		     	save_ids = ids;
		     	refreshReport.saveWidgetDimIsSelected(ids,widget_dim_id_,saveReturn);	
			}			
			return;
		}
		
		//输出保存信息
		function saveReturn(val)
		{
			
			if(val == "0"){
				alert("保存成功!");
				refreshUserInfo();
			}else if(val == "1"){
				alert("保存信息有空值!");
			}else if(val == "2"){
				alert("保存时出错!");
			}
			return;
			
		}
		
		//刷新用户信息
		function refreshUserInfo(){
			var table_rows = tab.rows;
			for(var i=1;i<table_rows.length;i++)
			{
				if(temp_widget_dim_id==$.trim(table_rows[i].cells[1].innerHTML)){
					table_rows[i].cells[6].innerHTML = document.getElementById("widget_dim_id_"+save_ids).value;
					table_rows[i].cells[8].innerHTML = temp_opt_string;
					temp_opt_string = "";
					temp_widget_dim_id = "";
					temp_is_selected = "";
					widget_dim_id = "";
				}
			}
		}
		
		
		
    </script>
</head>
<body style="background-color:#FCFAF3">
<form action="#" method="post" name="list" id="list">
	<input type="hidden" name="widgetId" value="${empty widgetId ? '': widgetId}" id="widgetId" />
	<input type="hidden" name="functionId" value="${empty functionId ? '': functionId}" id="functionId" />
	
	<div class="title-bar-top">
	  功能维度信息
	</div>
   <div class="tool-bar-top">
   		<input id="btn_fast_add_dindex" type="button" class="buttoncss"  value="快速分配指标"/>
   		
   		<input id="btn_add" type="button" class="buttoncss"  value="增加"/>
   		<input id="btn_del" type="button" class="buttoncss" value="删除"/>
		<input id="btn_return" type="button" class="buttoncss" value="返回" />
   </div>

   <div id="data-table" class="data-table" >
         <c:choose>
				 	<c:when test="${empty requestScope.widgetDimLinkBeanList}">
						无指定条件数据！
					</c:when>
					<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tab" >
						<tr class="test">
							<th width="8%"  nowrap>
								<a href="#" id="checkall" title="全选"><img id="check_all" border="0" src="${base}/manager/images/check_all.gif" width="13" height="13" alt="全选"></a>&nbsp;&nbsp;
				    			<a href="#" id="checknull" title="取消"><img id="check_none" border="0" src="${base}/manager/images/check_none.gif" width="13" height="13" alt="取消"></a>
							</th>
							<th  width="5%" nowrap>ID</th>
							<th  width="8%" nowrap>功能ID</th>
							<th  width="8%" nowrap>功能名称</th>
							<th  width="8%" nowrap>维度类别</th>
							<th  width="8%" nowrap>维度类型名称</th>
							<th  width="8%" nowrap>维度名称</th>
							<th  width="9%" nowrap>是否选择</th>
							<th  width="9%" nowrap>排序</th>
							<th  width="9%" nowrap>父维度</th>
							<th  width="8%" nowrap>分配指标</th>
							<!-- <th  width="6%" nowrap>操作</th> -->
						</tr>
							
							<c:forEach  var="WidgetDimBean" items="${requestScope.widgetDimLinkBeanList}">
								<tr class="tr_test" align="center"  onclick="return trSelect('${WidgetDimBean.id}')" style="CURSOR: hand">
									<td><input name="choiceItem" id="choiceItem" onclick="return checkbox_click()" type="checkbox" value="${WidgetDimBean.id}"></td>
									<td>
										${WidgetDimBean.id}
									</td>
									<td>
										${widgetId}
									</td>
									<td>
										${widget.name}
									</td>
									<td>
										<select onchange='changeWidgetDimType(this, "${WidgetDimBean.id}");'>
											<option value=0></option>
											<option value=1  ${(WidgetDimBean.typeGrade eq 1 ) ? ("selected"):("")}>静态维度</option>
											<option value=20 ${(WidgetDimBean.typeGrade eq 20) ? ("selected"):("")}>区间维度</option>
											<option value=21 ${(WidgetDimBean.typeGrade eq 21) ? ("selected"):("")}>区间日维度</option>
											<option value=22 ${(WidgetDimBean.typeGrade eq 22) ? ("selected"):("")}>区间周维度</option>
											<option value=23 ${(WidgetDimBean.typeGrade eq 23) ? ("selected"):("")}>区间月维度</option>
										</select>
										
									</td>
									<td>
										${WidgetDimBean.dimTypeName}
									</td>
									<td>
										${WidgetDimBean.dimName}
									</td>									
									<td>
										<input type="button" class="input btn isSelected" beanid="${WidgetDimBean.id}"
											value='${(WidgetDimBean.isSelected == 0) ? ("否") : ("是")}'>&nbsp;
									</td>
									<td>
										<input type="text" class="input order" beanid="${WidgetDimBean.id}" 
											value="${WidgetDimBean.sort}">&nbsp;								
									</td>
									<td>
										<select onchange='changeWidgetParentDim(this, "${WidgetDimBean.id}");'>
											<option value=null></option>
											<c:forEach  var="bean" items="${requestScope.widgetDimLinkBeanList}">
												<option value="${bean.dimTypeId}.${bean.dimId}" 
														${((bean.dimTypeId eq WidgetDimBean.parentDimTypeId) && (bean.dimId eq WidgetDimBean.parentId)) 
														? ("selected"):("")}>
													
													${bean.dimTypeName}.${bean.dimName}
												</option>
											</c:forEach>
										</select>
									</td>
									<td>
										<a href="javascript:authoriseExtend(${WidgetDimBean.id})" id="authorise_extend">分配指标</a>
									</td>
									<%-- <td >
										<a id="info_edit" href="javascript:showEditInfo('${WidgetDimBean.id}',tab)"><img src="${base}/manager/images/icon_edit.gif" border="0"></a>
									</td> --%>
								</tr>
							</c:forEach>
					</table>
				</c:otherwise>
		  </c:choose>
	</div>
</form>

<script type="text/javascript">

	function changeWidgetParentDim (nodeObj, beanId) {
		var vals,dimTypeId,dimId;
		
		try {
			vals = nodeObj.options[nodeObj.selectedIndex].value;
			if (vals && "null" != $.trim(vals)) {
				var temps = vals.split(".")
				dimTypeId = temps[0];
				dimId = temps[1];
			}
		} catch (ex) {
			dimTypeId = "NULL";
			dimId = "NULL";
		}
		
		if (!dimTypeId && !dimId) {
			dimTypeId = "NULL";
			dimId = "NULL";
		}
				
		jQuery.ajax({
			type : "POST",
			url : base + "/manager/widgetDim/updateWidgetDimJSON.feinno" ,
			dataType : 'text',
			data : {
				"dimTypeId" : dimTypeId,
				"dimId" : dimId,
				"beanid" : beanId
			},
			success : function(response) {
				$(nodeObj).addClass("write");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$(nodeObj).addClass("error");
			}
		});
	}

	function changeWidgetDimType(nodeObj, beanId) {
		
		var val = nodeObj.options[nodeObj.selectedIndex].value;
		
		jQuery.ajax({
			type : "POST",
			url : base + "/manager/widgetDim/updateWidgetDimJSON.feinno" ,
			dataType : 'text',
			data : {
				"type" : val,
				"beanid" : beanId
			},
			success : function(response) {
				$(nodeObj).addClass("write");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$(nodeObj).addClass("error");
			}
		});
	}
	
	$(document).ready(function() {
		$("#data-table").find("input.order").bind("blur", function () {
			var beanid = $(this).attr("beanid");
			var val = $(this).val();
			var self = this;
			jQuery.ajax({
				type : "POST",
				url : base + "/manager/widgetDim/updateWidgetDimJSON.feinno" ,
				dataType : 'text',
				data : {
					"order" : val,
					"beanid" : beanid
				},
				success : function(response) {
					$(self).addClass("write");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(self).addClass("error");
				}
			});
		});
		
		$("#data-table").find("input.isSelected").bind("click", function () {
			var beanid = $(this).attr("beanid");
			var txt = this.value;
			var self = this;
			var val = 0;
			
			if (txt == "否") {
				val = 1;
				this.value = "是";
			} else {
				val = 0;
				this.value = "否";
			}
			
			jQuery.ajax({
				type : "POST",
				url : base + "/manager/widgetDim/updateWidgetDimJSON.feinno" ,
				dataType : 'text',
				data : {
					"isSelected" : val,
					"beanid" : beanid
				},
				success : function(response) {
					$(self).addClass("write");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(self).addClass("error");
				}
			});
		});
		
		
		$("#btn_fast_add_dindex").bind('click', function(){
    		var dt = new Date();
    		popWindowCenter('${base}/manager/widgetDim/fastDistribution.feinno?widgetId='+$("#widgetId").attr("value")+'&dt='+dt, '选择指标', 752, 532);	    	
		});
		
	});//End
</script>

</body>

</html>