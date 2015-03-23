<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<%@ include file="/manager/commons/meta.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/manager/jsp/user/css/layout.css"/>">
</head>
<style type="text/css">
.data-table td {
	word-break: break-all;
}
.fixed{
	position: fixed;
	top: 0px;
	z-index: 100;
	background-color: white;
	_position:absolute;
	_top:expression(eval(document.documentElement.scrollTop));
	box-shadow:0 4px 4px rgba(0,0,0,0.1);
	border-top: none;
	margin-top: 0px;
	width: 94%;
	margin-left: 21px;
}
.search-bar{

}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#functionName').bind('keyup', function(event){
		   if (event.keyCode=="13"){
			   var functionName = $("#functionName").val();
			   if(functionName=="请输入查询的功能名称"){
				   functionName = "";
				}
				$('#listFunction').attr('action', '${baseManager}/function/list.${actionExt}?functionName='+functionName);
				$('#listFunction').submit();
		   }
		});
});
	

</script>
<body style="background-color: #FCFAF3">
	<form action="#" method="post" name="listFunction" id="listFunction">
		<div class="search-bar">
			功能名称： <input id="functionName" name="functionName" type="text"
				size="20"
				value="${empty requestScope.functionName? '':requestScope.functionName}" />
			<input id="btn_search" type="button" value="查询"
				class="ui-button ui-widget ui-state-default ui-corner-all" />&nbsp;&nbsp;&nbsp;&nbsp;
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tab" style="border-collapse: collapse;">
						<thead>
							<tr class="test">
								<th width="9%"><a href="javascript:void(0)"
									id="a_selectall" title="全选"><img id="check_all" border="0"
										src="${baseManager}/images/check_all.gif" width="13"
										height="13" alt="全选"> </a>&nbsp;&nbsp; <a
									href="javascript:void(0)" id="a_deselect" title="取消"><img
										id="check_none" border="0"
										src="${baseManager}/images/check_none.gif" width="13"
										height="13" alt="取消"> </a></th>
								<th width="7%" class="center">功能ID</th>
								<th width="13%" class="center">功能名称</th>
								<th width="30%" class="center">功能URL</th>
								<th width="7%" class="center">读取缓存</th>
								<th width="7%" class="center">是否上线</th>
								<th width="7%" class="center">是否隐藏</th>
								<th width="9%" class="center">所属模块</th>
								<th width="5%" class="center">排序</th>
								<th width="13%" class="center">功能类型</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.functionList}" var="function">
								<tr class="tr_test" align="center"
									onclick="return trSelect('${function.id}')"
									style="CURSOR: hand">
									<td><input type="checkbox" name="uid"
										value="${function.id}" onclick="return checkbox_click()"
										id="uid" /></td>
									<td>${function.id}</td>
									<td>${function.name}</td>
									<td>${function.actionUrl}</td>
									<td>${function.readCache eq 1 ? '否' : ''}
										${function.readCache eq 0 ? '是' : ''}</td>
									<td>${function.onlineStatus eq 1 ? '是' : ''}
										${function.onlineStatus eq 0 ? '否' : ''}</td>
									<td>${function.isHide eq true ? '是' : ''}
										${function.isHide eq false ? '否' : ''}</td>
									<td><c:forEach items="${requestScope.moduleList}"
											var="module">
									${module.id eq function.moduleId ? module.name : ''}</c:forEach></td>

									<td>${function.sort}</td>
									<%-- <td>${function.functionType}</td> --%>
									<td><c:choose>
											<c:when test="${function.functionType eq 1}">后台功能</c:when>
											<c:when test="${function.functionType eq 2}">前台功能</c:when>
											<c:when test="${function.functionType eq 21}">前台报表</c:when>
											<c:when test="${function.functionType eq 22}">首页报表</c:when>
											<c:when test="${function.functionType eq 23}">自定义报表</c:when>
											<c:otherwise>${function.functionType}</c:otherwise>
										</c:choose>
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

		$(document).ready(function() {
			var navH = $(".search-bar").offset().top;
			var naHei = $(".search-bar").height();
			var screenheight = window.screen.availHeight;
			$(window).scroll(function(){
				
				var scroH = $(this).scrollTop();
				var scrollHeight = $(this).height();
				var sH = scroH + screenheight;

				//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
				//(滚动距离加上屏幕显示高度比实际高度小则固定，如若无此条件，在火狐下会闪动)

				if(scroH>=navH && (sH < scrollHeight || scroH>(naHei+navH))){

					$(".search-bar").addClass("fixed");

				}else if(scroH<navH){

					$(".search-bar").removeClass("fixed");

				}

			});
			$('#btn_add').bind(
							'click',
							function() {
								listFunction.action = "${baseManager}/function/input.${actionExt}?type=add";
								listFunction.submit();
							});
			$('#btn_batchAdd').bind(
							'click',
							function() {
								window.location.href = baseManager
										+ "/jsp/function/batchAddFunction.jsp";
							});
			$('#btn_search').bind('click', checkSearch);
			$('#btn_modify').bind('click', checkModify);
			$('#btn_remove').bind('click', checkRemove);
			$('#a_selectall').bind('click', function() {
				checkall(listFunction.uid);
			});
			$('#a_deselect').bind('click', function() {
				checknull(listFunction.uid);
			});
							
			$("#tab").find("tbody").find("tr").find("td:eq(8)").dblclick(dbclick);
							
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
    				var id = tdNode.parent().find("td:eq(1)").text();//当前td行的id
				    				
    				jQuery.ajax({
    					type : "POST",
    					url : base + "/manager/function/editInPage.feinno",
    					data : {
    						"inputText" : inputText,
    						"id" : id
    					},
    					success : function(response){
//				    		alert("ajax success!");
    					},
    					error : function(XMLHttpRequest, textStatus, errorThrown){
//				    		alert("ajax error!");
    					}
    				});
    			});
			}
		});
		
		
		
		function goPage(pageNum) {
			if (isInteger(pageNum, "请使用整型！")) {
				
				var functionName = $("#functionName").val();
				listFunction.action = "${baseManager}/function/list.${actionExt}?page="
						+ pageNum ;
				listFunction.submit();
			}
		}

		function checkSearch() {
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

			listFunction.action = "${baseManager}/function/list.${actionExt}";
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
				listFunction.action = "${baseManager}/function/input.${actionExt}?type=modify&functionName=${empty requestScope.functionName? '':requestScope.functionName}";
				listFunction.submit();
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
				listFunction.action = "${baseManager}/function/remove.${actionExt}";
				listFunction.submit();
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
			//setContent();
		}
		window.onresize = function() {
			//setContent();
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
		
	</script>
</body>
</html>