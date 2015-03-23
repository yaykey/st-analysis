<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
<%-- <%@include file="/manager/commons/meta_ztree.jsp"%> --%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">

<style type="text/css">
.data-table td {
	word-break: break-all;
}

.content {
    border-bottom: 1px solid #8DB3E2;
    border-left: 1px solid #8DB3E2;
    border-right: 1px solid #8DB3E2;
    /* height: 100%; */
    width: 99%;
}

.content_title_bg {
    background: none no-repeat scroll 0 0 #CAE8EA;
    color: #003399;
    font-family: '宋体';
    font-size: 12px;
    height: 28px;
    line-height: 28px;
    padding-left: 5px;
}
.content_title {
    padding-left: 10px;
}
.divScrolling {
    overflow-x: hidden;
    overflow-y: auto;
    padding: 0 10px;
}


.tableborder {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    border-collapse: collapse;
}
.search-bar_widget{
   
    border-bottom: #c1cbfa 1px groove;
    text-align: right;
    margin-right: auto;
    margin-left: auto;
    width:95%;
    margin-top:5px;
    margin-bottom:5px;
    font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72;
}
</style>


</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">
<script type="text/javascript" src="${base}/manager/js/public.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$('#widgetName').bind('keyup', function(event){
				   if (event.keyCode=="13"){
					   var widgetName = $("#widgetName").val();
					   if(widgetName=="请输入查询的功能名称"){
						   widgetName = "";
						}
						$('#listWidget').attr('action', '${baseManager}/widget/list_widget.${actionExt}?functionId=61&widgetName='+widgetName);
						$('#listWidget').submit();
				   }
				});
		});
			

		</script>

<body style="background-color: #FCFAF3">
	<form action="#" method="post" name="listWidget" id="listWidget">
		<div class="content">
			<div class="content_title_bg">
				<div id="openleft" class="hideleft"></div>
				<div class="content_title">Widget管理</div>
			</div>
			<div style="">
				<table class="search-bar_widget">
					<tr>
						<td>WIDGET名称：<input id="widgetName" name="widgetName"
							type="text" value="${empty requestScope.widgetName? '':requestScope.widgetName}" size="20" />
						</td>
						<td><input id="btn_search" type="button" value="查询"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>
						<td><input id="btn_add" type="button" value="添加"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>
						<td><input id="btn_batchAdd" type="button" value="批量添加"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>

					<!-- </tr>
					<tr>
						<td></td> -->
						<td><input id="btn_modify" type="button" value="修改"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>
						<td><input id="btn_remove" type="button" value="删除"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>
						<td><input id="btn_manageDim" type="button" value="分配维度"
							class="ui-button ui-widget ui-state-default ui-corner-all" />
						</td>
					</tr>
				</table>
			</div>
			<div id="content" class="divScrolling" style="height: 157px; overflow: auto;">
			
			<%-- <input type="hidden" name="widgetId" id="widgetId" value="${requestScope.widget.id}" /> --%>
			<%-- <input type="hidden" name="orderBy" id="orderBy" value="${requestScope.widget.sort}" /> --%>
			<input type="hidden" name="functionId" id="functionId" value="${requestScope.functionId}" />
			<div class="data-table" >
				<table width="100%" class="tableborder" border="0" cellspacing="0" cellpadding="0"
					id="tab">
					<tr class="test">

						<th width="4%" class="center">所属模块</th>
						<th width="4%" class="center">所属报表</th>

						<th width="3%" nowrap><a href="javascript:void(0)"
							id="a_selectall" title="全选"><img id="check_all" border="0"
								src="${baseManager}/images/check_all.gif" width="13" height="13"
								alt="全选"> </a>&nbsp;&nbsp; <a href="javascript:void(0)"
							id="a_deselect" title="取消"><img id="check_none" border="0"
								src="${baseManager}/images/check_none.gif" width="13"
								height="13" alt="取消"> </a>
						</th>

						<th width="2%" class="center">Widget Id</th>
						<th width="5%" class="center">Widget名称</th>
						<th width="20%" class="center">功能URL</th>
						<th width="2%" class="center">排序</th>
						<th width="2%" class="center">类型</th>
						<th width="2%" class="center">分配操作</th>						
					</tr>
					<c:forEach items="${requestScope.widgetList}" var="widget">
						<tr class="tr_test" align="center"
							onclick="return trSelect('${widget.id}')" style="CURSOR: hand">

							<td>${widget.moduleName}</td>
							<td>${widget.functionName}</td>

							<td><input type="checkbox" name="wid" value="${widget.id}"
								onclick="return checkbox_click()" id="wid" />
							</td>

							<td>${widget.id}</td>
							<td>${widget.name}</td>
							<td>${widget.actionUrl}</td>
							<td>${widget.sort}</td>
							<td>${widget.widgetType}</td>
							<td>
								<input type="button" onclick='return departmentDefault("${widget.id}");' value="部门默认维度">
								<%-- <c:if test="${widget.widgetType == 2}">
									<input type="button" onclick='return departmentDefault("${widget.id}");' value="部门默认维度">
								</c:if> --%>
							</td>
						</tr>
					</c:forEach>
					<%@ include file="/manager/commons/page.jsp"%>
				</table>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	//alert("aaaabbb");
	//userTreeInit();

		$(document)
				.ready(
						function() {
							$('#btn_add')
									.bind(
											'click',
											function() {
												listWidget.action = "${baseManager}/widget/input.${actionExt}?type=add";
												listWidget.submit();
											});
							$('#btn_batchAdd')
									.bind(
											'click',
											function() {
												window.location.href = baseManager
														+ "/jsp/widget/batchAddWidget.jsp";
											});
							$('#btn_search').bind('click', checkSearch);
							$('#widgetName').bind('keyup', function(event){
								if(event.keyCode=="13"){
									checkSearch();
								}
							});
							$('#btn_modify').bind('click', checkModify);
							$('#btn_remove').bind('click', checkRemove);
							$('#btn_manageDim').bind('click', manageDim);
							$('#a_selectall').bind('click', function() {
								checkall(listWidget.wid);
							});
							
							$('#a_deselect').bind('click', function() {
								checknull(listWidget.wid);
							});
							
						});
		function goPage(pageNum) {
			var fid = $("#functionId").val();
			if (isInteger(pageNum, "请使用整型！")) {
				listWidget.action = "${baseManager}/widget/list_widget.${actionExt}?functionId="+fid+"&&page="
						+ pageNum;
				listWidget.submit();
			}
		}

		function checkSearch() {
			var widgetName = listWidget.widgetName;
			
			if (widgetName.value.length > 1000) {
				alert("检索条件字符数不能大于1000");
				widgetName.focus();
				return;
			}
			
			if (isiIllegalChar(widgetName, '检索条件含有非法字符!')) {
				return;
			}

			listWidget.action = "${baseManager}/widget/list_widget.${actionExt}?functionId=61";
			listWidget.submit();

		}

		function checkModify() {
			var fid = $("#functionId").val();
			if (checkboxCount(listWidget.wid) == 0) {
				alert("请选择一个要修改的widget");
				return false;
			} else if (checkboxCount(listWidget.wid) > 1) {
				alert("只能选择一个widget进行修改");
				return false;
			} else {
				listWidget.action = "${baseManager}/widget/input.${actionExt}?type=modify&functionId=" + fid+"&widgetName=${empty requestScope.widgetName? '':requestScope.widgetName}";
				listWidget.submit();
			}
		}
		
		//分配维度		
		function manageDim() {
			if (checkboxCount(listWidget.wid) == 0) {
				alert("请选择一个要分配的维度");
				return false;
			} else if (checkboxCount(listWidget.wid) > 1) {
				alert("只能选择一个功能进行分配");
				return false;
			} else {
				listWidget.action = "${baseManager}/widgetDim/list.${actionExt}";
				listWidget.submit();
			}
		}

		function checkRemove() {
			if (checkboxCount(listWidget.wid) == 0) {
				alert("请选择要删除的widget");
				return false;
			} else {
				var toDel = confirm("确认要执行该删除操作？！");
				if (!toDel) {
					return;
				}
				listWidget.action = "${baseManager}/widget/delete.${actionExt}";
				listWidget.submit();
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
					//var contentHeight = contentElement.offsetHeight;
					if(contentElement != null){
						if (windowHeight - (88) >= 0) {
							contentElement.style.height = (windowHeight - (97))
									+ 'px';
						}
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
				var tidObj = listWidget.wid;
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
		
		/* //设置树的名称
	    ztreeobj = "ddimTree";
	    function initZTree(val){
	    	alert("cccc");
			zNodes = val;
			$.fn.zTree.init($("#"+ztreeobj), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
			
		}
		//初始化用户树
		function userTreeInit(){
			alert("bbb");
			 jQuery.ajax({
				type : "POST",
				url : '${base}/manager/widgetDim/getAllDDim.feinno?widgetId=${requestScope.widgetId}',
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime(),
				    "nocheck":true
				},
				success : function(responseText) {
					var json=responseText;
					var data;
					eval("data="+json); 
					//var test = [{ id:1, pId:0, name:"数据中心", open:true,value:"1"},{ id:11, pId:1, name:"admin", value:"2"},{ id:12, pId:1, name:"test", value:"21"}];
					initZTree(data);
					//if (responseText == "ok") {
					//$.tips('设置成功', '重置密码链接已发送至您的邮箱，请及时重置密码。');
					//}// else {
					//	//$.growlUI('设置失败',responseText,8000);
					//	$.tips('设置失败', responseText, 8000);
					//}					
				}
			});	 
		} */
	
		
		function departmentDefault(wid) {
			listWidget.action = "${baseManager}/widgetDim/listDepartmentWidgetDim.${actionExt}" + "?widgetId=" + wid;
			listWidget.submit();
		}
		
		
		</script>

</body>
</html>