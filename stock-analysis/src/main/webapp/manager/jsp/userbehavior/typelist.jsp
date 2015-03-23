<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
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
			<dl class="limit fl" >
				<dt>请输入区间段范围</dt>
				<dd  style="width:180px;"><a href="javascript:void(-1);" id="add_range_input" class="new_add fr">+新增</a></dd>
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
		<h2 style="text-align:left;"><a href="javascript:void(-1);" onclick="closePop('selItem_type');" class="close fr"></a><span>单选设置</span></h2>
		<div class="inp_con_b clearfix"  style="text-align:left;">
			<dl class="limit fl" style="height:300px;overflow:auto;overflow-x:hidden;">
				<dt>
					<table width="100%">
					<tr><td style="width:80px;height:25px;">ID</td><td id="selItemHeard">名称</td></tr>
					</table>
				</dt>
				<dd style="width:320px;">
					<a href="javascript:void(-1);" id="add_selitem_input" class="new_add fr">+新增</a>
				</dd>
			</dl>				
		</div>
		<div class="inp_con_b_1 clearfix"><button class="p_btn3 fl" id="saveSelItem">确定</button></div>
	</div>
	<div  id="selItemStr" style="display:none">
		<dd class="clearfix" style="width:340px;">
				<div class="fl" style="width:310px;">
					<input type="text" id="selItemId" onKeyUp="clearNoNum(event,this)" value="" onBlur="checkNum(this)" maxlength="15" class="inp_1 p_80"/>-<input type="text" value="" maxlength="50"  id="selItemName" class="inp_1 p_180"/>
				</div>
				<span id="selItemdel" class="fl" style=";width:15px;height:15px;"></span>
		</dd>
	</div>
	<div  id="selWenzhiStr" style="display:none">
		<dd class="clearfix" style="width:340px;">
				<div class="fl" style="width:310px;">
					<input type="text" id="selItemId" onKeyUp="clearNoNum(event,this)" value="" onBlur="checkWenZhiNum(this)" maxlength="15" class="inp_1 p_280"/><input type="hidden" id="selItemName"  value=""/>
				</div>
				<span id="selItemdel" class="fl" style=";width:15px;height:15px;"></span>
		</dd>
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
	    ['Id','chktype','Name','ParentId','datatype','regionvalue','statisid','flag','status','rangeclassid','rangeid','rangetype','selItemId','selItemName']
	];
	var dataTypeArr = [];//数据类型
	<c:forEach items="${requestScope.dataTypeList}" var="dataTypeBean">
		dataTypeArr[dataTypeArr.length] = {
			"dataTypeId":"${dataTypeBean.dataTypeId}","dataTypeName":"${dataTypeBean.dataType}"
			,"dataMin":"${dataTypeBean.dataMin}","dataMax":"${dataTypeBean.dataMax}"
		};
	</c:forEach>
	<c:forEach items="${requestScope.funList}" var="funBean">
		var arr = ["${funBean.itemId}","${funBean.itemLevel}","${funBean.itemName}","${funBean.itemParentId}",
		           "${funBean.dataTypeId}","${funBean.regionvalue}","${funBean.itemIdOrg}","${funBean.coreFlag}",
		           "${funBean.itemStatus}","${funBean.rangeClassId}","${funBean.rangeId}","${funBean.rangeType}",
		           "${funBean.valueMin}","${funBean.valueMax}","${funBean.selItemId}","${funBean.selItemName}"];
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

	$("#rangeStr").find("em").text($.trim($("#unitType").find("option:selected").attr("unit")));//区间列表初始化单位
	
	$("#unitType").bind("change",function(){//切换区间单位，区间列表单位跟着变动
		var unitstr = $(this).find("option:selected").attr("unit");
		$("#rangeStr").find("em").text(unitstr);
		
		$("#range_type").find("dl.limit").find("em").each(function(){
			$(this).text(unitstr);
		});
		
	});
	$("#add_range_input").bind("click",function(){//点击区间确定按钮
		var firstvalue,lastvalue;
		 $(this).parent().parent().find("dd[class='clearfix']").last().find("input").each(function(index){
			 if(index==0)
				 firstvalue = $(this).val();
			 else
				 lastvalue = $(this).val();
		 });
		 if($.trim(firstvalue).length<1||$.trim(lastvalue).length<1){
			 $.messager.alert("提示","请填写完整的区间值后，再新增区间!","error");
			 return ;
		 }
		 if(parseInt(firstvalue)>=parseInt(lastvalue)){
			 $.messager.alert("提示","前面的值不能大于或等于后面的值!","error");
			 return ;
		 }
		
		var rangeObj = $("#rangeStr").find("dd").clone(true);
		rangeObj.find("input").first().val(lastvalue);

		$(this).parent().before(rangeObj);
	});
	$("#add_selitem_input").bind("click",function(){//点击单选或文字图片确定按钮
		var dataType = $(this).attr("dataType");
		var rangeObj;
		if(dataType==16){
			rangeObj = $("#selWenzhiStr").find("dd").clone(true);
		}else{
			rangeObj = $("#selItemStr").find("dd").clone(true);
		}
		$(this).parent().before(rangeObj);
	});
	$("#rangedel").bind("click",function(){
		$(this).parent().remove();
	});
	$("#selItemdel").bind("click",function(){
		$(this).parent().remove();
	});
	$("#range_type #saveRange").bind("click",function(){
		var rangevalue = "";
		$("#range_type").find("dl.limit").find("em").each(function(i){
			if(rangevalue.length>0)
				rangevalue +="`";
			$(this).parent().find("input").each(function(index){
				if(index==0){
					rangevalue +=$(this).val();
				}else{
					rangevalue += ":"+$(this).val();
				}
			});
		});
		if($.trim(rangevalue).length<1){
			//表格中的区间值
			dataGridObj.setRangeValue("","","");
			closePop('range_type');
			return ;
		}
		var arr = rangevalue.split("`");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!=null){
				var carr = arr[i].split(":");
				if(carr.length==2){
					if(carr[0]==null||$.trim(carr[0]).length<1){
						$.messager.alert("提示","第"+(i+1)+"行区间值不能为空!","error");
						return ;
					}
					if(i!=arr.length-1){
						if(carr[1]==null||$.trim(carr[1]).length<1){
							$.messager.alert("提示","第"+(i+1)+"行区间值不能为空!","error");
							return ;
						}
					}else{
						if(carr[1]!=null&&$.trim(carr[1]).length>0){
							rangevalue +="`"+carr[1]+":";
						}
					}
				}
			}
		}
		//给区间赋值到表格中
		dataGridObj.setRangeValue($("#unitType").val(),rangevalue,$("#unitType").find("option:selected").text());
		closePop('range_type');
	});
	$("#selItem_type #saveSelItem").bind("click",function(){
		var chk = false;
		var selItemId = "",selItemName = "";
		var arr = [];
		var i;
		$("#selItem_type").find("dl.limit #selItemId").each(function(index){
			if(null==$(this).val()||$.trim($(this).val()).length<1){
				$.messager.alert("提示","第"+(index+1)+"行ID不能为空!","error");
				chk = true;
				return ;
			}
			if(arr.length>0){
				for(i=0;i<arr.length;i++){
					if(arr[i]==$(this).val()){
						$.messager.alert("提示","第"+(index+1)+"行ID不能重复!","error");
						chk = true;
						return ;
					}
				}
			}
			arr[arr.length] = $(this).val();
			if(selItemId.length>0){
				selItemId += "`";
			}
			selItemId += $(this).val();
		});
		if(chk){
			return ;
		}
		
		$("#selItem_type").find("dl.limit #selItemName").each(function(index){
			if(null==$(this).val()||$.trim($(this).val()).length<1){
				$.messager.alert("提示","第"+(index+1)+"行名称不能为空!","error");
				chk = true;
				return ;
			}
			
			if(selItemName.length>0){
				selItemName += "`";
			}
			selItemName += $(this).val();
		});
		if(chk){
			return ;
		}
		if($.trim(selItemId).length<1){
			//表格中的区间值
			dataGridObj.setSelItemValue("","","");
			closePop('selItem_type');
			return ;
		}
		//给区间赋值到表格中
		dataGridObj.setSelItemValue(selItemId,selItemName);
		closePop('selItem_type');
	});
});


/**
 * 组装区间编辑界面
 */
function viewRange(id,rangeclassid,rangeid,regionvalue,flag){
	openPop("range_type","h2");

	var rangeObj;
	if(rangeclassid!=null&&rangeclassid.length>0){
		$("#unitType").val(rangeclassid);
	}else{
		rangeclassid = $("#unitType").val();
	}
	$("#range_type").find("dl.limit").find("em").each(function(){
		$(this).parent().remove();
	});

	if(regionvalue!=null&&regionvalue.length>1){
		var arr = regionvalue.split("`");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!=null){
				var carr = arr[i].split(":");
				if(carr.length==2){
					rangeObj = $("#rangeStr").find("dd").clone(true);
					rangeObj.find("input").each(function(index){
						$(this).val(carr[index]);
					});
					$("#add_range_input").parent().before(rangeObj);
				}
			}
		}
	}else{
		$("#add_range_input").parent().before($("#rangeStr").find("dd").clone(true));
	}
	
	var unitstr = $("#unitType").find("option:selected").attr("unit");
	$("#range_type").find("dl.limit").find("em").each(function(){
		$(this).text(unitstr);
	});
}
/**
 * 组装单选或文字图片编辑界面
 */
function viewSelItem(id,dataType,selitemid,selitemName,flag){
	openPop("selItem_type","h2");
	$("#selItem_type").find("dl.limit #selItemId").each(function(){
		$(this).parent().parent().remove();
	});
	if(dataType==16){
		$("#selItem_type #selItemHeard").hide();
		$("#selItem_type h2 span").html("动态文字链");
	}else if(dataType==17){
		$("#selItem_type #selItemHeard").show();
		$("#selItem_type h2 span").html("单选设置");
	}
	$("#add_selitem_input").attr("dataType",dataType);
	var itemObj;
	if(selitemName!=null&&selitemName.length>1){
		var arr = selitemName.split("`");
		var carr = selitemid.split("`");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!=null){
				if(dataType==16){
					rangeObj = $("#selWenzhiStr").find("dd").clone(true);
				}else{
					rangeObj = $("#selItemStr").find("dd").clone(true);
				}
				
				rangeObj.find("#selItemId").val(carr[i]);
				rangeObj.find("#selItemName").val(arr[i]);
				$("#add_selitem_input").parent().before(rangeObj);
			}
		}
	}else{
		if(dataType==16){
			rangeObj = $("#selWenzhiStr").find("dd").clone(true);
		}else{
			rangeObj = $("#selItemStr").find("dd").clone(true);
		}
		$("#add_selitem_input").parent().before(rangeObj);
	}
}
function saveSelItem(itemid,datatype,selItemId,selItemName){
	var clientid = $("#clientid").val();
	var versionid = $("#versionid").val();
	jQuery.ajax({
		type : "post",
		url : base + '/manager/userbehavior/saveExtend.'+actionExt,
		dataType : 'json',
		data : {
			clientid : clientid,
			clientvsn: versionid,
			itemid : itemid,
			datatype : datatype,
			selItemId : selItemId,
			selItemName : selItemName
		},
		success : function(responseText) {
			if(responseText.chk==1){
				$.messager.alert("提示","设置出错!","error");
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$.messager.alert("提示","你没有此功能的权限!","error");
		}
	});
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
    //为了去除最后一个. 
    checkNum(obj);
    $(obj).parent().find("#selItemName").val(obj.value);
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
