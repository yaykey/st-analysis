<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/manager/commons/meta.jsp"%>
<html>
<head>
	<style type="text/css">
		body{
			background-color: white;
		}
		div.data-table{
			width: 100%;
			height: 98%;
		}
		div.search{
			text-align: right;
			padding: 2px;
		}
		div.fixed{
			position: fixed;
			top: 0px;
			z-index: 100;
			background-color: white;
			_position:absolute;
			_top:expression(eval(document.documentElement.scrollTop));
			box-shadow:0 4px 4px rgba(0,0,0,0.1);
			border-top: none;
			margin-top: 0px;
			width: 98%;
		}
		
		#functionName{
			border: 1px solid #CDD7D9;
    		border-radius: 8px 8px 8px 8px;
    		padding: 5px;
		}
		table.tab {
			width: 100%;
		}
		table.tab tbody tr.odd td {
			background-color:#EAF5F9;
		}
		table.tab tbody tr.even td {
			background-color:#FFF;
		}
		table.tab tbody tr.selected td{ 
			background:#FFFFCC;
			color:#000;
		}
		table.tab tbody tr input{
			border: 1px solid #cdd7d9;
   		    padding: 3px;
  		    border-radius: 8px;
		} 
		table td{
			word-break: break-all;
		}
		
	</style> 
	
	<link rel="stylesheet" type="text/css" href="${baseManager }/jsp/user/css/easyui.css">
 	<script type="text/javascript" src="${baseManager }/jsp/user/js/jquery.easyui.min.js"></script>
</head>
<body>
	<form action="#" method="post" name="listFunction" id="listFunction">
		<div class="search">
		<input id="functionParentId" name="functionParentId" type="hidden" value="${empty requestScope.functionParentId? '':requestScope.functionParentId}" />
		<input id="functionId" name="functionId" type="hidden" value="${empty requestScope.functionId? '':requestScope.functionId}" />
		<input id="reportId" name="reportId" type="hidden" value="${empty requestScope.reportId? '':requestScope.reportId}" />
		<input id="type" name="type" type="hidden" value="${empty requestScope.type? '':requestScope.type}" />
			报表名称： <input id="functionName" name="functionName" type="text" size="20"
				value="${empty requestScope.functionName? '':requestScope.functionName}" />
				
			<a href="#" class="easyui-menubutton" data-options="menu:'#search'">查询</a>
			<div id="search" style="width:150px;">
				<div id="allSearch">全局查询</div>
				<div id="partSearch">在选中节点下查询</div>
			</div>
				
			<!-- <input id="btn_search" type="button" value="查询"
				class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp; -->
			<input id="btn_batchAdd" type="button" value="批量添加"
				class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btn_add" type="button" value="添加"
				class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btn_modify" type="button" value="修改"
				class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btn_remove" type="button" value="删除"
				class="ui-button ui-widget ui-state-default ui-corner-all" />
		</div>

		<input type="hidden" name="functionId" id="functionId"
			value="${requestScope.function.id}" /> <input type="hidden"
			name="orderBy" id="orderBy" value="${requestScope.function.sort}" />
		<div class="data-table" id="content">
			<c:choose>
				<c:when test="${empty requestScope.functionList}">
						无指定条件数据！
					</c:when>
				<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tab" class="tab" style="border-collapse: collapse;">
						<thead>
							<tr class="test">
								<th width="8%">
									<a href="javascript:void(0)" id="a_selectall" title="全选">
										<img id="check_all" border="0" src="${baseManager}/images/check_all.gif" width="13"
										height="13" alt="全选"> </a>
									<a href="javascript:void(0)" id="a_deselect" title="取消">
										<img id="check_none" border="0" src="${baseManager}/images/check_none.gif" width="13"
										height="13" alt="取消"> 
									</a>
								</th>
								<th width="8%" class="center">报表ID</th>
								<th width="22%" class="center">报表名称</th>
								<th width="34%" class="center">报表URL</th>
								<th width="7%" class="center">读取缓存</th>
								<th width="7%" class="center">是否上线</th>
								<th width="6%" class="center">排序</th>
								<th width="10%" class="center">所属模块</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.functionList}" var="function">
								<tr class="tr_test" align="center"
									onclick="return trSelect('${function.id}')"
									style="CURSOR: hand">
									<td><input type="checkbox" name="uid" id="uid"
										value="${function.id}" onclick="return checkbox_click()"
										id="uid" /></td>
									<td>${function.id}</td>
									<td>${function.name}</td>
									<td>${function.actionUrl}</td>
									<td>${function.readCache eq 1 ? '否' : ''}
										${function.readCache eq 0 ? '是' : ''}</td>
									<td>${function.onlineStatus eq 1 ? '是' : ''}
										${function.onlineStatus eq 0 ? '否' : ''}</td>
									<td>${function.sort}</td>
									<td><c:forEach items="${requestScope.moduleList}"
											var="module">
									${module.id eq function.moduleId ? module.name : ''}</c:forEach></td>

									
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
		$(document).ready(
			function() {
				var navH = $(".search").offset().top;
				var naHei = $(".search").height();
				var screenheight = window.screen.availHeight;
				var scrollHeight = $(this).height();
				$(window).scroll(function(){
					
					var scroH = $(this).scrollTop();
					var sH = scroH + screenheight;
					
					//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
					//(滚动距离加上屏幕显示高度比实际高度小则固定，如若无此条件，在火狐下会闪动)

					if(scroH>=navH && (sH < scrollHeight || scroH>(naHei+navH))){

						$(".search").addClass("fixed");

					}else if(scroH<navH){

						$(".search").removeClass("fixed");

					}

				});
				
				//隔行换色
				  $('table tbody tr:odd').addClass('odd'); //奇数行
			      $('table tbody tr:even').addClass('even'); //偶数行
			      	//选中
			      $('tbody>tr',this).click(function(){
			    	  var hasSelected = $(this).hasClass('selected');
			    	  $(this)[hasSelected?"removeClass":"addClass"]('selected').find(":checkbox").attr('checked',!hasSelected);
			      }); 
				
				$('#btn_add').bind('click',function() {
					listFunction.action = "${baseManager}/report/input.${actionExt}?type=add";
					listFunction.submit();
					self.parent.location = "${baseManager}/report/input.${actionExt}?type=add";
				});
				$('#btn_batchAdd').bind('click',function() {
					//window.location.href = baseManager + "/jsp/report/batchAddReport.jsp";
					self.parent.location = baseManager + "/jsp/report/batchAddReport.jsp";
				});
				//$('#btn_search').bind('click', checkSearch);
				$('#allSearch').bind('click', checkALLSearch);
				$('#functionName').bind('keyup', function(event){
					if(event.keyCode=="13"){
						checkALLSearch();
					}
					
				});
				$('#partSearch').bind('click', checkPartSearch);
				$('#btn_modify').bind('click', checkModify);
				$('#btn_remove').bind('click', checkRemove);
				$('#a_selectall').bind('click', function() {
					checkall(listFunction.uid);
				});
				$('#a_deselect').bind('click', function() {
					checknull(listFunction.uid);
				});
			});
		
		function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				
				var functionName = $("#functionName").val();
				listFunction.action = "${baseManager}/report/reportList.${actionExt}?page=" + pageNum ;
				listFunction.submit();
			}
		}

		function checkALLSearch() {
			var functionName = listFunction.functionName;
			if ($.trim(functionName.value) == '') {
				alert("检索条件不能为空");
				functionName.focus();
				return;
			} else {
				if (functionName.value.length > 1000) {
					alert("检索条件字符数不能大于1000");
					functionName.focus();
					return;
				}
			}
			if (isiIllegalChar(functionName, '检索条件含有非法字符!')) {
				return;
			}
			
			listFunction.action = "${baseManager}/report/reportList.${actionExt}?type=all";
			listFunction.submit();

		}

		function checkPartSearch() {
			var functionParentId = $('#functionParentId').val();
			var functionId = $('#functionId').val();
			var reportId = $('#reportId').val();
			
			var functionName = listFunction.functionName;
			if ($.trim(functionName.value) == '') {
				alert("检索条件不能为空");
				functionName.focus();
				return;
			} else {
				if (functionName.value.length > 1000) {
					alert("检索条件字符数不能大于1000");
					functionName.focus();
					return;
				}
			}
			if (isiIllegalChar(functionName, '检索条件含有非法字符!')) {
				return;
			}
			if(reportId == "" && functionId == ""){
				alert("请选择树节点");
				return;
			}
			//清空之前条件
			
			listFunction.action = "${baseManager}/report/reportList.${actionExt}?type=part";
			listFunction.submit();
		}

		function checkModify() {
			if (checkboxCount(listFunction.uid) == 0) {
				alert("请选择一个要修改的功能");
				return false;
			} else if (checkboxCount(listFunction.uid) > 1) {
				alert("只能选择一个功能进行修改");
				return false;
			} else {
				//listFunction.action = "${baseManager}/report/input.${actionExt}?type=modify";
				//listFunction.submit();
				self.parent.location = "${baseManager}/report/input.${actionExt}?type=modify&uid=" + $("input[type='checkbox']:checked").val()+"&functionName=${empty requestScope.functionName? '':requestScope.functionName}";
			}
		}

		function checkRemove() {
			if (checkboxCount(listFunction.uid) == 0) {
				alert("请选择要删除的功能");
				return false;
			} else {
				var toDel = confirm("确认要执行该删除操作？！");
				if (!toDel) {
					return;
				}

				var arr_v = new Array();

				$("input[type='checkbox']:checked").each(function(){
				 
				     arr_v.push($(this).val()); 
				});
				$.ajax({
					type : "POST",
					url : '${baseManager}/report/remove.${actionExt}?uid=' + arr_v,
					dataType : 'text',
					data : {
						"nocache" : new Date().getTime(),
					    "nocheck":true
					},
					success : function(responseText) {
						var iden=responseText;
						if(iden=="true"){
							self.parent.location = "${base}/manager/report/list.feinno";
						}
					}
				});
				//listFunction.action = "${baseManager}/report/remove.${actionExt}";
				//listFunction.submit();
				//刷新父页面
				//self.parent.location = "${base}/manager/report/list.feinno"
			}
		}
		function aa() {
			var Ptr = document.getElementById("tab").getElementsByTagName("tr");

			for ( var i = 1; i < Ptr.length + 1; i++) {
				if (i % 2 > 0) {
					Ptr[i - 1].className = "t2";
				} else {
					Ptr[i - 1].className = "t1";
				}
			}

			for ( var i = 0; i < Ptr.length; i++) {
				Ptr[i].onmouseover = function() {
					this.tmpClass = this.className;
					this.className = "t3";
				};
				Ptr[i].onmouseout = function() {
					this.className = this.tmpClass;
				};
			}
		}
		function getWindowHeight() {
			var windowHeight = 0;
			if (typeof (window.innerHeight) == 'number') {
				windowHeight = window.innerHeight;

			} else {
				if (document.documentElement
						&& document.documentElement.clientHeight) {
					windowHeight = document.documentElement.clientHeight;
				} else {
					if (document.body && document.body.clientHeight) {
						windowHeight = document.body.clientHeight;
					}
				}
			}
			return windowHeight;
		}

		function setContent() {
			if (document.getElementById) {
				var windowHeight = getWindowHeight();
				if (windowHeight > 0) {
					var contentElement = document.getElementById('content');
					var contentHeight = contentElement.offsetHeight;
					if (windowHeight - (88) >= 0) {
						contentElement.style.height = (windowHeight - (37)) + 'px';
					}

				}
			}
		}

		window.onload = function() {
			setContent();
		}
		window.onresize = function() {
			setContent();
		}

		function MM_jumpMenu(targ, selObj, restore) { //v3.0
			eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value + "'");
			if (restore)
				selObj.selectedIndex = 0;
		}

		var click_checkbox = "false";
		function checkbox_click() {
			click_checkbox = "true";
		}
		function trSelect(idTid) {
			if (click_checkbox != "true") {
				var tidObj = listFunction.uid;
				if (!tidObj.length) {
					if (tidObj.value == idTid) {
						if (tidObj.checked)
							tidObj.checked = false;
						else
							tidObj.checked = true;
					}
				} else {
					for ( var i = 0; i < tidObj.length; i++) {
						if (tidObj[i].value == idTid) {
							if (tidObj[i].checked)
								tidObj[i].checked = false;
							else
								tidObj[i].checked = true;
						}
					}
				}
			}
			click_checkbox = "false";
		}
		//校验非法字符
		function isiIllegalChar(obj, msg) {
			var pat = new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5\+]", "i");
			if (pat.test(obj.value) == true) {
				alert(msg);
				obj.focus();
				return true;
			} else {
				return false;
			}
		}
		
		
		$("#tab").find("tbody").find("tr").find("td:eq(6)").dblclick(dbclick);
		function dbclick(){
			var td = $(this);//保存当前的td节点
			var text = td.text();//当前td节点的内容
			td.html("");//清空td里的内容
			var input = $("<input>");//建立一个文本框
			input.attr("value",text);//给文本框加初始值
			input.width(td.width()-10);
			td.append(input);//将文本框加到td中
			input.get(0).select();//文本框中的值选中
			
			input.blur(function(){
				var inputNode = $(this);//输入框节点
				var inputText = inputNode.val();//保存输入的值
				var tdNode = inputNode.parent();//td节点
				tdNode.html(inputText);//给td赋值
				var id = tdNode.parent().find("td:eq(1)").text();
//				alert("id=" + id);
				jQuery.ajax({
					type : "POST",
					url : base + "/manager/report/editInPage.feinno",
					data : {
						"inputText" : inputText,
						"id" : id
					},
					success : function(response){
//						alert("success!");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
//						alert("error");
					}
				});
			});
		}
	</script>
</body>
</html>