<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			$('#moduleName').bind('keyup', function(event){
				   if (event.keyCode=="13"){
					   var moduleName = $("#moduleName").val();
					   if(moduleName=="请输入查询的功能名称"){
						   reportName = "";
						}
						$('#listModule').attr('action', '${baseManager}/module/list.${actionExt}?moduleName='+moduleName);
						$('#listModule').submit();
				   }
				});
		});
			

		</script>
<body style="background-color: #FCFAF3">
	<div class="panelBox">
		<form action="#" method="post" name="listModule" id="listModule">
			<input type="hidden" name="orderBy" id="orderBy"
				value="${requestScope.module.sort}" />
			<div class="search-bar">
				模块名称： <input id="moduleName" name="moduleName" type="text" size="20"
					value="${empty requestScope.moduleName? '':requestScope.moduleName}" />
				<input id="btn_search" type="button" value="查询"
					class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btn_batchAdd" type="button" value="批量添加"
					class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btn_add" type="button" value="添加"
					class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;<input
					id="btn_modify" type="button" value="修改"
					class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btn_remove" type="button" value="删除"
					class="ui-button ui-widget ui-state-default ui-corner-all" />
			</div>
			<div class="data-table" id="content">
				<c:choose>
					<c:when test="${empty requestScope.moduleList}">
						无指定条件数据！
					</c:when>
					<c:otherwise>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							id="tab" style="border-collapse: collapse;">
							<thead>
								<tr class="test">
									<th >
										<a href="#" id="a_selectall" title="全选"><img id="check_all" border="0"src="${baseManager}/images/check_all.gif" width="13"height="13" alt="全选"> </a>&nbsp;&nbsp;
										<a href="#" id="a_deselect" title="取消"><img id="check_none" border="0" src="${baseManager}/images/check_none.gif" width="13" height="13" alt="取消"> </a>
									</th>
									
									<th class="center">模块ID</th>
									<th  class="center">模块名称</th>
									<th  class="center">模块URL</th>
									<th  class="center">图片URL</th>
									<th  class="center">模块级别</th>
									<th  class="center">父节点ID</th>
									<th  class="center">模块类别</th>
									<th  class="center">排序</th>
								</tr>
							</thead>
							<tbody>
								
								<c:forEach items="${requestScope.moduleList}" var="Module">
									<tr class="tr_test" align="center"
										onclick="return trSelect('${Module.id}')" style="CURSOR: hand">
										<td><input type="checkbox" name="uid"
											value="${Module.id}" onclick="return checkbox_click()"
											id="uid" />
										</td>
										<td>${Module.id}</td>
										<td>${Module.name}</td>
										<td>${Module.actionUrl}</td>
										<td>${Module.imgUrl}</td>
										<td>${Module.moduleLevel}</td>
										<td>${Module.parentId}</td>
										<td>${Module.viewType}</td>
										<td>${Module.sort}</td>
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
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#btn_add')
									.bind(
											'click',
											function() {
												listModule.action = "${baseManager}/module/input.${actionExt}?type=add";
												listModule.submit();
											});
							$('#btn_batchAdd')
									.bind(
											'click',
											function() {
												window.location.href = baseManager
														+ "/jsp/module/batchAddModule.jsp";
											});
							$('#btn_search').bind('click', checkSearch);
							$('#moduleName').bind('keyup', function(event){
								if(event.keyCode=="13"){
									checkSearch();
								}
							});
							$('#btn_modify').bind('click', checkModify);
							$('#btn_remove').bind('click', checkRemove);
							$('#a_selectall').bind('click', function() {
								checkall(listModule.uid);
							});
							$('#a_deselect').bind('click', function() {
								checknull(listModule.uid);
							});
						});
		function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				listModule.action = "${baseManager}/module/list.${actionExt}?page="
						+ pageNum;
				listModule.submit();
			}
		}
		function checkSearch() {
			var moduleName = listModule.moduleName;

			if ($.trim(moduleName.value) == '') {
				alert("检索条件不能为空");
				moduleName.focus();
				return;
			} else {
				if (moduleName.value.length > 1000) {
					alert("检索条件字符数不能大于1000");
					moduleName.focus();
					return;
				}
			}
			if (isiIllegalChar(moduleName, '检索条件含有非法字符!')) {
				return;
			}

			listModule.action = "${baseManager}/module/list.${actionExt}";
			listModule.submit();

		}
		function checkModify() {
			if (checkboxCount(listModule.uid) == 0) {
				alert("请选择一个要修改的模块");
				return false;
			} else if (checkboxCount(listModule.uid) > 1) {
				alert("只能选择一个模块进行修改");
				return false;
			} else {
				listModule.action = "${baseManager}/module/input.${actionExt}?type=modify&moduleName=${empty requestScope.moduleName? '':requestScope.moduleName}";
				listModule.submit();
			}
		}

		function checkRemove() {
			if (checkboxCount(listModule.uid) == 0) {
				alert("请选择要删除的模块");
				return false;
			} else {
				var toDel = confirm("确认要执行该删除操作？！");
				if (!toDel) {
					return;
				}
				listModule.action = "${baseManager}/module/remove.${actionExt}";
				listModule.submit();
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
						contentElement.style.height = (windowHeight - (37))
								+ 'px';
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
			eval(targ + ".location='"
					+ selObj.options[selObj.selectedIndex].value + "'");
			if (restore)
				selObj.selectedIndex = 0;
		}

		var click_checkbox = "false";
		function checkbox_click() {
			click_checkbox = "true";
		}
		function trSelect(idTid) {
			if (click_checkbox != "true") {
				var tidObj = listModule.uid;
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
	</script>
</body>
</html>