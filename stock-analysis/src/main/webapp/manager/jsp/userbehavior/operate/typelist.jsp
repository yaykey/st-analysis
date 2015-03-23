<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${base }/manager/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${base}/manager/js/My97DatePicker/skin/WdatePicker.css"/>



<style>

.plat_pop .t_round {
	width : auto;
	display:none;
}


.plat_pop .z_con {
	width : auto;
	background: none repeat scroll 0 0 #fff;
    border: 1px solid blue;
}

.plat_pop .inp_con_b {
	width : auto;
	padding: 10px 20px 20px;
}

.plat_pop .inp_con_b_1 {
	width : auto;
}

.plat_pop .b_round {
	width : auto;
	display:none;
}

.plat_pop dl.limit {
	margin-left : 0px;
}


.plat_pop .z_con h2 a {
	margin-top: 8px;
}


.plat_pop dl.limit dt {
	margin : 0px;
}

#item_ex_table {

}

#item_ex_table .item-id{
	width : 120px;
}

#item_ex_table .item-name{
	width : 150px;
}

#item_ex_table .item-ex-id{
	width : 120px;
}

#item_ex_table .item-ex-name{
	width : 150px;
}

#item_ex_table .item-ex-eff{
	width : 70px;
}

#item_ex_table .item-ex-exp{
	width : 70px;
}

#item_ex_table .item-del {
	background: url("${base}/manager/jsp/userbehavior/images/pop_del.jpg") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    cursor: pointer;
        
    margin-top: 5px;
    width: 13px;
    height: 13px;
    
    margin-left: 10px;
    margin-right: 10px;
}

#item_ex_table .item-edit {
	border__: 1px solid #c5c5c5;
	background-image: url("${base}/manager/jsp/userbehavior/images/item-edit.png");
	background-repeat: no-repeat;
	background-position: right bottom;
}

#item_ex_table td{
	margin__ : 1px;
	padding: 1px;
}

#item_ex_table tr.item-tr {
	
}

#item_ex_table tr.item-tr td input{
	background-color: transparent;
}

#item_ex_table tr.item-tr-nochange {

}

#item_ex_table .item-ex-eff,
#item_ex_table .item-ex-exp,
#item_ex_table .item-id,
#item_ex_table .item-name{
	border: 1px solid #d5d5d5;
}

#item_ex_table tr.item-tr-add td{
	background: bisque;
}

#item_ex_table tr.item-tr-edit td{
	background: yellow;
}

#item_ex_table tr.item-tr-delete td{
	background: orangered;
}

#item_ex_table tr.item-tr-delete td input{
	text-decoration: line-through;
}

.ui-datepicker select.ui-datepicker-month {
    width: 37%;
}

.ui-datepicker select.ui-datepicker-year {
    width: 37%;
}

</style>

<div style="text-align:center;padding-top:20px;padding-bottom:20px;">
	<table id="reportFunTable"></table>
	<input type="hidden" id="rptid" value="${rptid}">
</div>
<div class="plat_pop" id="range_type">
	<span class="t_round"></span>
	<div class="z_con">
		<h2 style="text-align:left;"><a href="javascript:void(-1);" onclick="closePop('range_type');" class="close fr"></a> <span>划分区间段</span></h2>
		<div class="inp_con_b clearfix"  style="text-align:left;">
			<dl class="unit fl">
				<dt>单位</dt>
				<dd><select id="unitType">
					<c:forEach items="${requestScope.rangeTypeList}" var="rangeTypeList">
						<option value="${rangeTypeList.rangeClassId}" unit="${rangeTypeList.rangeUnit}">${rangeTypeList.rangeType}(${rangeTypeList.rangeUnit})</option>
					</c:forEach>
				</select>
				</dd>
			</dl>
			<dl class="limit fl" style="overflow-y__: scroll !important;">
				<dt>请输入区间段范围</dt>
				<dd  style="width:180px;">
					<a href="javascript:void(-1);" id="add_range_input" class="new_add fr">+新增</a>
				</dd>
			</dl>				
		</div>
		<div class="inp_con_b_1 clearfix"><button class="p_btn3 fl" id="saveRange">确定</button></div>
	</div>
	<div  id="rangeStr" style="display:none">
	<dd class="clearfix" style="width:230px;">
			<div class="fl" style="width:150px;">
				<input type="text" value="" onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)" maxlength="20" class="inp_1 p_80"/>-<input type="text" value=""  onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)" maxlength="20" class="inp_1 p_80"/>
			</div>
			<em class="fl" style="width:55px;height:23px;padding-left:4px;">s</em>
			<span id="rangedel" class="fl" style="width:15px;height:15"></span>
	</dd>
	</div>
	<span class="b_round"></span>
</div>
<div class="plat_pop" id="selItem_type">
	<span class="t_round"></span>
	<div class="z_con">
		<%-- <form id="selItem_form" action="${base }/manager/userbehavior/operate/saveExtend.feinno" method="post"> --%>
			<h2 style="text-align:left;">
				<a href="javascript:void(-1);" onclick="closePop('selItem_type');" class="close fr"></a><span>单选设置</span>
			</h2>
			<div class="inp_con_b clearfix"  style="text-align:left;">
				<dl class="limit fl" style="height:300px;overflow:scroll; overflow-x:hidden;">
					<dt>
						<table id="item_ex_table" class="item-table" 
								cellPadding="1" cellSpacing="1" border="0">
							<thead>
								<tr>
									<th style="width:80px;height:25px;">功能点名称</th>
									<th >功能点ID</th>
									<th >扩展功能ID</th>
									<th >扩展功能名称</th>
									<th >开始时间</th>
									<th >结束时间</th>
									<th>
										<input id="selItem_itemId" type="hidden" name="itemId">
										<input id="selItem_itemName" type="hidden" name="itemName">
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</dt>
					<dd style="width__:320px;">
						<a href="javascript:void(-1);" id="add_selitem_input" class="new_add fr">+新增</a>
					</dd>
				</dl>				
			</div>
			<div class="inp_con_b_1 clearfix"><button class="p_btn3 fl" id="saveSelItem" style="margin-left:400px;">确定</button></div>
		<!-- </form> -->
	</div>
	<!-- <div  id="selItemStr" style="display:none">
		<dd class="clearfix" style="width:340px;">
				<div class="fl" style="width:310px;">
					<input type="text" id="selItemId" onKeyUp="clearNoNum(event,this)" value="" onBlur="checkNum(this)" maxlength="15" class="inp_1 p_80"/>-<input type="text" value="" maxlength="50"  id="selItemName" class="inp_1 p_180"/>
				</div>
				<span id="selItemdel" class="fl" style=";width:15px;height:15px;"></span>
		</dd>
	</div> -->
	<div  id="selWenzhiStr" style="display : none">
		<!-- <dd class="clearfix" style="width:340px;">
				<div class="fl" style="width:310px;">
					<input type="text" id="selItemId" onKeyUp="clearNoNum(event,this)" value="" onBlur="checkWenZhiNum(this)" maxlength="15" class="inp_1 p_280"/><input type="hidden" id="selItemName"  value=""/>
				</div>
				<span id="selItemdel" class="fl" style=";width:15px;height:15px;"></span>
		</dd> -->
		
		<!-- item-tr-add item-tr-edit item-tr-delete -->
		<table>
			<tr class="item-tr item-tr-nochange">				
				<td>
					<input type="hidden" name="clientTypeId" class="item-clientTypeId"/>
					<input type="hidden" name="rptId" class="item-rptId"/>
					<input type="hidden" name="dataTypeId" class="item-dataTypeId" value="16"/>
					
					<input type="hidden" name="oldExId" class="item-ex-oldid"/>
					
					<input type="hidden" name="lineNumber" class="item-lineNumber"/>
					
					<input type="text" name="itemName" class="item-name inp_1" readonly maxlength="50" value=""/>
				</td>
				<td>
					<input type="text" name="itemId" class="item-id inp_1 " readonly maxlength="20" value=""/>
				</td>
				<td>
					<input type="text" name="exId" class="item-ex-id inp_1" onKeyUp="clearNoNum(event,this)" maxlength="20" value=""/>
				</td>
				<td>
					<input type="text" name="exName" class="item-ex-name inp_1" maxlength="50" value=""/>
				</td>
				<td>
					<input type="text" name="effDate" class="item-ex-eff inp_1" onchange="checkHandler(this);" onFocus="WdatePicker({startDate__:'2014-01-01',alwaysUseStartDate__:true,dateFmt:'yyyy-MM-dd'});" />
				</td>
				<td>
					<input type="text" name="expDate" class="item-ex-exp inp_1" onchange="checkHandler(this);" onFocus="WdatePicker({startDate__:'2014-01-01',alwaysUseStartDate__:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
				<td>
					<div class="fl item-del" style=";width:15px;height:15px;"></div>
				</td>
			</tr>
		</table>
	</div>
	<span class="b_round"></span>
</div>
<div id="view_Help" class="table_pop bgiframe" style="display:none;">
	<table cellspacing="0" cellpadding="0" class="table_b">
	<th>数据类型</th><th>指标名称</th><th>指标口径</th>
	 <c:forEach items="${requestScope.dataTypeList}" var="typeBean">
	      	<c:choose>  
		    <c:when test="${fn:length(typeBean.indexList)>1}">  
		    <tr>
		        <td style="width:80px;text-align:center;" rowspan="${fn:length(typeBean.indexList)+1}">${typeBean.dataType}</td>
		    </tr>
		    <c:forEach items="${typeBean.indexList}" var="dindex">
			     <tr>
			        <td style="width:120px;padding-left:5px">${dindex.indexName}</td>
			        <td style="padding-left:5px">${dindex.indexDsc}</td>
			     </tr>
	     	</c:forEach>
		  </c:when>  
		   <c:otherwise>  
		       <tr>
		        <td style="width:80px;text-align:center;">${typeBean.dataType}</td>
		        <c:forEach items="${typeBean.indexList}" var="dindex">
			        <td style="width:120px;padding-left:5px">${dindex.indexName}</td>
			        <td style="padding-left:5px">${dindex.indexDsc}</td>
			     </c:forEach>
		    	</tr>
		  	</c:otherwise>  
			</c:choose>  
	  </c:forEach>
	</table>
</div>

<script>
dataGridObj = null;
$(document).ready(function() {
	var classtype = $("#static_report").find(".active_1").attr("classtype");//获取报表类型的类型(1 报表 0 虚拟报表)
	
	//功能点数组
	var dataArr = [
	    ['Id','chktype','Name','ParentId','datatype','regionvalue','statisid','flag','status','rangeclassid','rangeid','rangetype',
	     'selItemId','selItemName'
	     ,'selItemExId','selItemExName'
	     ,'selItemEffDate','selItemExpDate'
	     ]
	];
	var dataTypeArr = [];//数据类型
	<c:forEach items="${requestScope.dataTypeList}" var="dataTypeBean">
		dataTypeArr[dataTypeArr.length] = {
			"dataTypeId":"${dataTypeBean.dataTypeId}","dataTypeName":"${dataTypeBean.dataType}"
			,"dataMin":"${dataTypeBean.dataMin}","dataMax":"${dataTypeBean.dataMax}"
		};
	</c:forEach>
	<c:forEach items="${requestScope.funList}" var="funBean">
		var arr = ["${funBean.itemId}","${funBean.itemLevel}","${funBean.itemName}",
		           "${funBean.itemParentId}",
		           "${funBean.dataTypeId}","${funBean.regionvalue}",
		           "${funBean.itemIdOrg}",
		           "${funBean.coreFlag}",
		           "${funBean.itemStatus}","${funBean.rangeClassId}","${funBean.rangeId}","${funBean.rangeType}",
		           "${funBean.valueMin}","${funBean.valueMax}"
		           ,"${funBean.selItemId}","${funBean.selItemName}"
		           ,"${funBean.selItemExId}","${funBean.selItemExName}"
		           ,"${funBean.selItemEffDate}","${funBean.selItemExpDate}"
		           ];
		dataArr[dataArr.length] = arr;
	</c:forEach>
	
	
	
	var titleStr = "${rptTitle}功能点列表";
	<c:if test="${editflag==1}">
		titleStr+="<span style=\"color:red;margin-left:20px;\">已有人在进行编辑，为避免重复提交覆盖，请稍后再试。</span>"
	</c:if>
 	dataGridObj = ReportDataGrid.init("reportFunTable",dataArr,titleStr,dataTypeArr,"${newflag}",classtype);
 	
 	$("body").bind("click",function(){
 		if($("#view_Help")!=null&&$("#view_Help").attr("type_help")!=null){
 			closeHelp();
 		}
 	});

	
	$("#add_selitem_input").bind("click",function(){	//增加扩展功能点空行;
		
		var clientTypeId = $("#clientid option:selected").val();
		var classId = $("#report_static").find(".active_1").attr("id").substr(8);
		var rptId = $("#static_rpt_type").find(".active_2").attr("id").substr(6);
		
		rangeObj = $("#selWenzhiStr").find("tr").clone(true);
			
		
		$(rangeObj).removeClass("item-tr-nochange");
		$(rangeObj).addClass("item-tr-add");
		
		rangeObj.find(".item-clientTypeId").val(clientTypeId);
		rangeObj.find(".item-rptId").val(rptId);
		
		rangeObj.find(".item-id").val($("#selItem_itemId").val());
		rangeObj.find(".item-name").val($("#selItem_itemName").val());
		
		rangeObj.find(".item-ex-id").bind("focus", focusHandler);
		rangeObj.find(".item-ex-id").bind("blur", blurHandler);
		
		rangeObj.find(".item-ex-name").bind("focus", focusHandler);
		rangeObj.find(".item-ex-name").bind("blur", blurHandler);
		
		rangeObj.find(".item-ex-eff").bind("focus", focusHandler);
		rangeObj.find(".item-ex-eff").bind("blur", blurHandler);
		
		rangeObj.find(".item-ex-exp").bind("focus", focusHandler);
		rangeObj.find(".item-ex-exp").bind("blur", blurHandler);
		
		$("#item_ex_table").find("tbody").append(rangeObj);
	});
	
	//删除扩展功能点
	$("#selWenzhiStr").find(".item-del").bind("click",function(){
		var className = $($(this).parents(".item-tr")[0]).attr("class");
		
		if (className.indexOf("item-tr-delete") >= 0) {	//恢复删除
			$(this).parents("tr.item-tr")
			.removeClass("item-tr-delete");
		}else if (className.indexOf("item-tr-nochange") >= 0 ||
				className.indexOf("item-tr-edit") >= 0) {	//增加删除标记
			$(this).parents("tr.item-tr")
				//.removeClass("item-tr-nochange")
				//.removeClass("item-tr-edit")
				.addClass("item-tr-delete");			
		} else if (className.indexOf("item-tr-add") >= 0) {	//直接删除
			$($(this).parents(".item-tr")[0]).remove();	
		}		
	});
	
	
	
	$("#selItem_type #saveSelItem").bind("click",function(){
		// var chk = false;
		//var selItemId = "",selItemName = "";
		//var arr = [];
		//var i;
		
		
		//给扩展功能点赋值到表格中
		dataGridObj.setSelItemValue();
		
		
	});
});




/**
 * 组装单选或文字图片编辑界面
 */
//function viewSelItem(id,dataType,selitemid,selitemName,flag){
function viewSelItem(opt,flag){
	
	/* viewSelItem({
		id : field.id,
		dataType : field.datatype,
		selItemId : field.selItemId,
		selItemName : field.selItemName,
		
		selItemExId : field.selItemExId,
		selItemExName : field.selItemExName,
		
		selItemEffDate : field.selItemEffDate,
		selItemExpDate : field.selItemExpDate
	}); */
	
	
	openPop("selItem_type","h2");
	
	//if ($("#item_ex_table").find("tr").length > 1) {
	//	return;
	//}
	
	$("#item_ex_table").find("tbody").empty();
	
	/* $("#selItem_type").find("dl.limit #selItemId").each(function(){
		$(this).parent().parent().remove();
	}); */
	
	//if(opt.dataType==16){
		$("#selItem_type #selItemHeard").hide();
		$("#selItem_type h2 span").html("动态文字链");
	//}
	
	$("#add_selitem_input").attr("dataType",opt.dataType);
	
	var clientTypeId = $("#clientid option:selected").val();
	var classId = $("#report_static").find(".active_1").attr("id").substr(8);
	var rptId = $("#static_rpt_type").find(".active_2").attr("id").substr(6);
	
	$("#selItem_itemId").val(opt.id);
	$("#selItem_itemName").val(opt.name);
	
	
	var itemObj;
	var selItemNames, selItemIds, selItemExIds, selItemExNames, selItemEffDates, selItemExpDates;
	
	//alert(opt.selItemName + ":" + opt.selItemName.length);
	
	if(opt.selItemName!=null && opt.selItemName.length>1){
		//alert(opt.selItemName);
		selItemNames = opt.selItemName.split("`");
		selItemIds = opt.selItemId.split("`");
		
		selItemExIds = opt.selItemExId.split("`");

		selItemExNames = opt.selItemExName.split("`");
		
		try {
			selItemEffDates = opt.selItemEffDate.split("`");
		} catch (ex){}
		
		try {
			selItemExpDates = opt.selItemExpDate.split("`");
		} catch (ex){}
		
		//alert(arr.length);
		for(var i=0;i<selItemIds.length;i++){
			
			if(selItemIds[i]!=null){
								
				rangeObj = $("#selWenzhiStr").find("tr").clone(true);
				
				rangeObj.find(".item-clientTypeId").val(clientTypeId);
				rangeObj.find(".item-rptId").val(rptId);
				
				
				rangeObj.find(".item-id").val(selItemIds[i]);
				rangeObj.find(".item-name").val(selItemNames[i]);
				
				
				
				try {
					rangeObj.find(".item-ex-id").val(selItemExIds[i]);
					rangeObj.find(".item-ex-name").val(selItemExNames[i]);
					
					rangeObj.find(".item-ex-id").attr("oldValue", selItemExIds[i]);
					rangeObj.find(".item-ex-name").attr("oldValue", selItemExNames[i]);
					
					rangeObj.find(".item-ex-oldid").val(selItemExIds[i]);
					
					rangeObj.find(".item-ex-eff").val(selItemEffDates[i]);
					rangeObj.find(".item-ex-exp").val(selItemExpDates[i]);
					
					rangeObj.find(".item-ex-eff").attr("oldValue", selItemEffDates[i]);
					rangeObj.find(".item-ex-exp").attr("oldValue", selItemExpDates[i]);
					
					rangeObj.find(".item-ex-id").bind("focus", focusHandler);
					rangeObj.find(".item-ex-id").bind("blur", blurHandler);
					
					rangeObj.find(".item-ex-name").bind("focus", focusHandler);
					rangeObj.find(".item-ex-name").bind("blur", blurHandler);
					
					rangeObj.find(".item-ex-eff").bind("focus", focusHandler);
					rangeObj.find(".item-ex-eff").bind("blur", blurHandler);
					
					rangeObj.find(".item-ex-exp").bind("focus", focusHandler);
					rangeObj.find(".item-ex-exp").bind("blur", blurHandler);
					
					
				} catch (ex) {}
				
				
				$("#item_ex_table").find("tbody").append(rangeObj);
			}
		}
	}else{
		//if(dataType==16){
		//	rangeObj = $("#selWenzhiStr").find("dd").clone(true);
		//}
		//
		//else{
		//	rangeObj = $("#selItemStr").find("dd").clone(true);
		//}
		
		//$("#add_selitem_input").parent().before(rangeObj);
		
		rangeObj = $("#selWenzhiStr").find("tr").clone(true);
		
		rangeObj.find(".item-id").val(opt.id);
		rangeObj.find(".item-name").val(opt.name);
		
		
		rangeObj.find(".item-clientTypeId").val(clientTypeId);
		rangeObj.find(".item-rptId").val(rptId);
		
		
		$(rangeObj).removeClass("item-tr-nochange");
		$(rangeObj).addClass("item-tr-add");
		
		$("#item_ex_table").find("tbody").append(rangeObj);
	}
}

function focusHandler () {
	//$(this).attr("oldValue", $(this).attr("value"));
}

function blurHandler () {
	checkHandler (this)
}

function checkHandler (obj) {
	var oldValue = $(obj).attr("oldValue");
	
	//alert(oldValue + ":" + $(obj).val());
	
	if (oldValue != $(obj).val()) {
		var className = $($(obj).parents(".item-tr")[0]).attr("class");
		
		if (className.indexOf("item-tr-nochange") >= 0) {
			$(obj).parents("tr.item-tr")
				.removeClass("item-tr-nochange")
				.addClass("item-tr-edit");			
		}
		
		if ($(obj).attr("class").indexOf("item-ex-id") >= 0) {
			checkWenZhiNum(obj);
		} else if ($(obj).attr("class").indexOf("item-ex-name") >= 0) {
			checkWenZhiName(obj);
		} else if ($(obj).attr("class").indexOf("item-ex-eff") >= 0) {
			checkDate (obj);
		} else if ($(obj).attr("class").indexOf("item-ex-exp") >= 0) {
			checkDate (obj);
		}
	}
	
	checkEdit (obj);
}

function checkEdit (obj) {
	var oldValue = $(obj).attr("oldValue");
	if (oldValue != $(obj).val()) {
		//if (oldValue && $(obj).val()) {
		$(obj).addClass("item-edit");
		//}
	} else {
		$(obj).removeClass("item-edit");
	}
}

function checkDate (obj) {
	var isSuccess = true;
	if ($(obj).attr("class").indexOf("item-ex-exp") >= 0) {
		var eff = $($(obj).parents(".item-tr")[0]).find(".item-ex-eff").val();
		var exp = obj.value;
		//alert(eff + ":" + exp);
		if (eff && exp && eff > exp) {
			isSuccess = false;
			$.messager.alert("提示","失效时间要大于生效时间","error");
		}
	}
	
	return isSuccess;
}

function saveSelItem(itemid,datatype){
	
	var clientTypeId = $("#clientid option:selected").val();
	var classId = $("#report_static").find(".active_1").attr("id").substr(8);
	var rptId = $("#static_rpt_type").find(".active_2").attr("id").substr(6);
	
	$("#item_ex_table").find("tr.item-tr").each(function(index){
		
		$(this).find("input.item-lineNumber").each(function(){
			$(this).val(index + 1);
		});
		
		$(this).find("input").each(function(){
			$(this).attr("name", $(this).attr("name").replace(/^[\w\d\[\]]+\./, ""));
		});
	});
	
	$("#item_ex_table").find("tr.item-tr-add").each(function(index){
		$(this).find("input").each(function(){
			$(this).attr("name", ("addItems[" + index + "]." + $(this).attr("name")));
		});
	});
	
	$("#item_ex_table").find("tr.item-tr-edit").each(function(index){
		$(this).find("input").each(function(){
			$(this).attr("name", ("editItems[" + index + "]." + $(this).attr("name")));
		});
	});
	
	$("#item_ex_table").find("tr.item-tr-delete").each(function(index){
		$(this).find("input").each(function(){
			$(this).attr("name", ("deleteItems[" + index + "]." + $(this).attr("name")));
		});
	});
	
	if (validateItem()) {
		var postData = {};
		
		$("#item_ex_table").find("tr.item-tr").find("input").each(function(){
			
			var k = $(this).attr("name");
			var v = $(this).val();
			
			postData[k] = v;
		});
		
		postData.clientTypeId = clientTypeId;	
		postData.classId = classId;	
		postData.rptId = rptId;
		
		
		jQuery.ajax({
			type : "post",
			url : base + '/manager/userbehavior/operate/saveExtend.feinno',
			dataType : 'json',
			data : postData,
			success : function(responseText) {
				/*
				if(responseText.chk==1){
					$.messager.alert("提示","设置出错!","error");
				}
				*/
				
				var selItemId="", selItemName="", 
					selItemExId="", selItemExName="", 
					selItemEffDate="", selItemExpDate="";
				
				var trs = $("#item_ex_table").find("tr.item-tr").not("tr.item-tr-delete");
				
				$(trs).find(".item-id").each(function(){
					selItemId += $(this).val() + "`";
				});
				
				$(trs).find(".item-name").each(function(){
					selItemName += $(this).val() + "`";
				});
								
				$(trs).find(".item-ex-id").each(function(){
					selItemExId += $(this).val() + "`";
				});
				
				$(trs).find(".item-ex-name").each(function(){
					selItemExName += $(this).val() + "`";
				});
				
				$(trs).find(".item-ex-eff").each(function(){
					selItemEffDate += $(this).val() + "`";
				});
				
				$(trs).find(".item-ex-exp").each(function(){
					selItemExpDate += $(this).val() + "`";
				});
				
				selItemId = selItemId.replace(/\`$/ig,"");
				selItemName = selItemName.replace(/\`$/ig,"");
				
				selItemExId = selItemExId.replace(/\`$/ig,"");
				selItemExName = selItemExName.replace(/\`$/ig,"");
				
				selItemEffDate = selItemEffDate.replace(/\`$/ig,"");
				selItemExpDate = selItemExpDate.replace(/\`$/ig,"");
				
				var opt = {
					"selItemId" : selItemId,
					"selItemName" : selItemName,
					
					"selItemExId" : selItemExId,
					"selItemExName" : selItemExName,
					
					"selItemEffDate" : selItemEffDate,
					"selItemExpDate" : selItemExpDate	
				}
				
				dataGridObj.updateSelItemValue(opt);
				
				closePop('selItem_type');
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.messager.alert("提示","你没有此功能的权限!","error");
			}
		});
	} else {
		
	}
	
}

function validateInputNull (obj) {
	var isSuccess = true;
	
	var v = null;
	if (obj != null) 
		v = $(obj).val();
	
	if (!v || "" == $.trim(v))
		isSuccess = false;
	
	if (isSuccess == false)
		$.messager.alert("提示","数据不能为空","error");
	
	return isSuccess;
}

function validateItem () {
	var isSuccess = true;
	
	var iptNotNulls = $("#item_ex_table").find("tr.item-tr").find(":text");
	
	for (var i=0; i<iptNotNulls.size(); i++) {
		var ipt = iptNotNulls[i];
		
		isSuccess = validateInputNull(ipt);
		
		if (isSuccess == false) {
			return isSuccess;
		}		
	}
	
	var iptExIds = $("#item_ex_table").find("tr.item-tr")
			.find("input.item-ex-id").filter(".item-edit");
	
	for (var i=0; i<iptExIds.size(); i++) {
		var ipt = iptExIds[i];
				
		isSuccess = checkWenZhiNum(ipt);
		
		if (isSuccess == false) {
			return isSuccess;
		}
	}
	
	if (isSuccess == false) {
		return isSuccess;
	}
	
	var iptExNames = $("#item_ex_table").find("tr.item-tr")
			.find("input.item-ex-name").filter(".item-edit");
	
	for (var i=0; i<iptExNames.size(); i++) {
		var ipt = iptExNames[i];
				
		isSuccess = checkWenZhiName(ipt);
		
		if (isSuccess == false) {
			return isSuccess;
		}
	}
	
	if (isSuccess == false) {
		return isSuccess;
	}
	
	/* var iptEffs = $("#item_ex_table").find("tr.item-tr").find("input.item-ex-eff");
	
	for (var i=0; i<iptEffs.size(); i++) {
		var ipt = iptEffs[i];
		
		isSuccess = validateInputNull(ipt);
		
		if (isSuccess == false) {
			return isSuccess;
		}
	} */
	
	var iptExps = $("#item_ex_table").find("tr.item-tr").find("input.item-ex-exp");
	
	for (var i=0; i<iptExps.size(); i++) {
		var ipt = iptExps[i];
						
		isSuccess = checkDate(ipt);
		
		if (isSuccess == false) {
			return isSuccess;
		}
	}
	
	
	return isSuccess;
}

function clearNoNum(event,obj){ 
    //响应鼠标事件，允许左右方向键移动 
    event = window.event||event; 
    if(event.keyCode == 37 | event.keyCode == 39){ 
        return; 
    } 
    //先把非数字的都替换掉，除了数字和. 
    obj.value = obj.value.replace(/[^\d.]/g,""); 
    //必须保证第一个为数字而不是. 
    obj.value = obj.value.replace(/^\./g,""); 
    //保证只有出现一个.而没有多个. 
    obj.value = obj.value.replace(/\.{2,}/g,"."); 
    //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
} 
function checkNum(obj){ 
    //为了去除最后一个. 
    obj.value = obj.value.replace(/\.$/g,""); 
}
/**
 * 检测文字链ID,同时给文字链名称赋值，名称跟ID同名
 */
function checkWenZhiNum(obj){
	
	var isSuccess = true;
	
    //为了去除最后一个. 
    checkNum(obj);
    //$(obj).parent().find("#selItemName").val(obj.value);
    
    var itemTr = $(obj).parent().parent();
    
    var className = $(itemTr).attr("class");
    
    if (className.indexOf("item-tr-delete") < 0 
    		&& (className.indexOf("item-tr-edit") >= 0 || className.indexOf("item-tr-add") >= 0)) {
    	
    	
    	var iptExIds = $("#item_ex_table").find("tr.item-tr").find(".item-ex-id");
    	
    	for (var i=0; i<iptExIds.length; i++){
    		var that = iptExIds[i];
    		
    		if (that != obj && obj.value == $(that).val()) {
    			isSuccess = false;
    			break;
    		}
    	}
    	
    	
    	if (isSuccess) {
    		var clientTypeId = $(itemTr).find(".item-clientTypeId").val();
            var rptId = $(itemTr).find(".item-rptId").val();
            var itemId = $(itemTr).find(".item-id").val();
            var dataTypeId = $(itemTr).find(".item-dataTypeId").val();
            
            //alert("clientTypeId=" + clientTypeId + "rptId=" + rptId + "itemId=" + itemId);
            
            var postCkData = {
            	"exId" : obj.value,
            	"clientTypeId" : clientTypeId,
            	"rptId" : rptId,
            	"itemId" : itemId,
            	"dataTypeId" : dataTypeId
            }
            
            jQuery.ajax({
        		type : "post",
        		url : base + '/manager/userbehavior/operate/checkEx.feinno',
        		//dataType : 'json',
        		dataType : 'text',
        		async : false,
        		data : postCkData,
        		success : function(responseText) {
        			//isSuccess = false;
        			//alert("checkWenZhiNum");
        			if (isSuccess == true) {
        				if(responseText){
        					isSuccess = false;
        					responseText = 
        						responseText.replace(/^\"/ig,'').replace(/\"$/ig,'');
        					
            				$.messager.alert("提示",responseText,"error");
            			}
        			}
        		}
        	});
    	} else {
    		$.messager.alert("提示","扩展功能点ID重复","error");
    	}
    		 
    	
    }
    
    return isSuccess;
}



function checkWenZhiName(obj){
	
	var isSuccess = true;
	
    //为了去除最后一个. 
    //checkNum(obj);
    //$(obj).parent().find("#selItemName").val(obj.value);
    
    var itemTr = $(obj).parent().parent();
    
    var className = $(itemTr).attr("class");
    
    if (className.indexOf("item-tr-delete") < 0 
    		&& (className.indexOf("item-tr-edit") >= 0 || className.indexOf("item-tr-add") >= 0)) {
    	
    	
    	
		var iptExNames = $("#item_ex_table").find("tr.item-tr").find(".item-ex-name");
    	
    	for (var i=0; i<iptExNames.length; i++){
    		var that = iptExNames[i];
    		
    		//alert((that != obj) + ":" + (obj.value == $(that).val()) 
    		//		+ ";" + obj.value + ":" + $(that).val());
    		
    		if (that != obj && obj.value == $(that).val()) {
    			isSuccess = false;
    			break;
    		}
    	}
    	
    	//alert("isSuccess == true->" + (isSuccess == true));
    	if (isSuccess == true) {
    		var clientTypeId = $(itemTr).find(".item-clientTypeId").val();
            var rptId = $(itemTr).find(".item-rptId").val();
            var itemId = $(itemTr).find(".item-id").val();
            var dataTypeId = $(itemTr).find(".item-dataTypeId").val();
            
            //alert("clientTypeId=" + clientTypeId + "rptId=" + rptId + "itemId=" + itemId);
            
            var postCkData = {
            	"exName" : obj.value,
            	"clientTypeId" : clientTypeId,
            	"rptId" : rptId,
            	"itemId" : itemId,
            	"dataTypeId" : dataTypeId
            }
            
            jQuery.ajax({
        		type : "post",
        		url : base + '/manager/userbehavior/operate/checkEx.feinno',
        		//dataType : 'json',
        		dataType : 'text',
        		async : false,
        		data : postCkData,
        		success : function(responseText) {
        			//isSuccess = false;
        			//alert(isSuccess);
        			
        			if (isSuccess == true) {        				
        				if(responseText){
        					isSuccess = false;
            				$.messager.alert("提示",responseText,"error");
            			}
        			}
        		}
        	});
    	} else {
    		$.messager.alert("提示","扩展功能点名称重复","error");
    	}
    		 
    	
    }
    
    return isSuccess;
}

function dataTypeHelp(obj){
	if($("#view_Help")!=null&&$("#view_Help").attr("type_help")!=null&&$("#view_Help").attr("type_help")=="1")
		return ;
	
	var top = $(obj).offset().top+10;
	if ($.browser.msie) {
		var body = (document.compatMode&&document.compatMode.toLowerCase() == "css1compat")?document.documentElement:document.body;
		top +=body.scrollTop;
	}
	$("#view_Help").css({top:top});
	$("#view_Help").show();
	setTimeout(function(){
		$("#view_Help").attr("type_help","1");
	},200)
}
function closeHelp(){
	if($("#view_Help")!=null&&$("#view_Help").attr("type_help")!=null&&$("#view_Help").attr("type_help")=="1"){
		$("#view_Help").hide();
		$("#view_Help").attr("type_help","0");
	}
	
}
</script>
