var dataGridObj;//表格对象
function openPop(openid,handle){
	if(handle){
		$("#"+openid).draggable({handle: handle});
	}else{
		$("#"+openid).draggable();
	}
	jQuery.blockUI({ message: $("#"+openid), css: {border: 'none'},overlayCSS: { opacity:'0.2' }});
}
function closePop(closeid){
	$("#"+closeid).droppable();
	$.unblockUI();
}
/**
 * 报表分类点击事件
 * @param typeid
 * @param obj
 */
function funStype(classId){
	dataGridObj = null;
	progressRpt();
	$("#static_report").find(".active_1").each(function(){
		$(this).removeClass("active_1").addClass("a4");
	});
	$("#classid_"+classId).removeClass("a4");
	$("#classid_"+classId).removeClass("a5");
	$("#classid_"+classId).addClass("active_1");

	$("#static_function").empty();
	$("#static_type").empty();
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/stype.'+actionExt,
		dataType : 'html',
		data : {
			"classId" : classId,
			"clientTypeId" : $("#clientid").val(),
			"clientVsn" : $("#versionid").val()
		},
		success : function(responseText) {
			closeRpt();
			$("#static_type").html(responseText);
			$("#saveVsn").hide();
		}
	});

}

function funOperateStype(classId){
	dataGridObj = null;
	progressRpt();
	$("#static_report").find(".active_1").each(function(){
		$(this).removeClass("active_1").addClass("a4");
	});
	$("#classid_"+classId).removeClass("a4");
	$("#classid_"+classId).removeClass("a5");
	$("#classid_"+classId).addClass("active_1");

	$("#static_function").empty();
	$("#static_type").empty();
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/operate/stype.'+actionExt,
		dataType : 'html',
		data : {
			"classId" : classId,
			"clientTypeId" : $("#clientid").val(),
			"clientVsn" : $("#versionid").val()
		},
		success : function(responseText) {
			closeRpt();
			$("#static_type").html(responseText);
			$("#saveVsn").hide();
		}
	});

}
/**
 * 报表点击事件
 * @param typeid
 * @param obj
 */
function operateTypelist(rptid){
	progressRpt();
	$("#static_type").find(".active_2").each(function(){
		$(this).removeClass("active_2").addClass("a7");
	});

	$("#rptid_"+rptid).removeClass("a7");
	$("#rptid_"+rptid).removeClass("a8");
	$("#rptid_"+rptid).addClass("active_2");

	$("#static_function").empty();
	var newflag = $("#versionid").find("option:selected").attr("newflag");
	
	var srcType = $("#clientid").find("option:selected").attr("srcType");
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/operate/typelist.'+actionExt,
		dataType : 'html',
		data : {
			"clientTypeId" : $("#clientid").val(),
			//"clientVsn" : $("#versionid").val(),
			"rptid" : rptid,
			"srcType":srcType,
			"newflag" : newflag
		},
		success : function(responseText) {
			closeRpt();
			$("#static_function").html(responseText);
			var newflag = $("#versionid").find("option:selected").attr("newflag");
			if(newflag==0){
				$("#saveVsn").show();
			}
		}
	});
}

/**
 * 报表点击事件
 * @param typeid
 * @param obj
 */
function typelist(rptid){
	progressRpt();
	$("#static_type").find(".active_2").each(function(){
		$(this).removeClass("active_2").addClass("a7");
	});

	$("#rptid_"+rptid).removeClass("a7");
	$("#rptid_"+rptid).removeClass("a8");
	$("#rptid_"+rptid).addClass("active_2");

	$("#static_function").empty();
	var newflag = $("#versionid").find("option:selected").attr("newflag");
	
	var srcType = $("#clientid").find("option:selected").attr("srcType");
	jQuery.ajax({
		type : "POST",
		url : base + '/manager/userbehavior/typelist.'+actionExt,
		dataType : 'html',
		data : {
			"clientTypeId" : $("#clientid").val(),
			"clientVsn" : $("#versionid").val(),
			"rptid" : rptid,
			"srcType":srcType,
			"newflag" : newflag
		},
		success : function(responseText) {
			closeRpt();
			$("#static_function").html(responseText);
			var newflag = $("#versionid").find("option:selected").attr("newflag");
			if(newflag==0){
				$("#saveVsn").show();
			}
		}
	});
}


function saveTypelist(savechk){
	progressRpt("数据正在保存，请稍等...");
	if(savechk==1){
		$.messager.confirm("提示","数据提交后，将不能进行修改，确定提交?",function(result){
			if(result){
				saveFun(savechk);
			}else{
				closeRpt();
			}
		});
	}else{
		saveFun(savechk);
	}
}
/**
 * 
 * @param savechk0保存 1提交
 */
function saveFun(savechk){
	if(dataGridObj!=null){
		dataGridObj.endEditing();
	}
	
	var reportArr = [];
	var info = "";
	if($("#static_function").html().length>1){
		var nodes = $('#reportFunTable').treegrid('getChildren');
		if(nodes){
			var nodes_1;
			var rowindex;
			var ids = ",";
			var titles = ",";
			var reindex;
			var reindex;
			var arr;
			var parentid="";
			for(var i=0; i<nodes.length; i++){
				if(nodes[i].status==ReportDataGrid.defaults.ITEM_STATUS_DEL){
					continue;
				}
				if(nodes[i].name==null||$.trim(nodes[i].name).length<1){
					info += "第"+(i+1)+"行";
					if(nodes[i].chktype==4){
						info += "【分组名称】不能为空";
					}else{
						info += "【功能点名称】不能为空";
					}
					info += "<br/>";
					continue;
				}
				if(nodes[i].chktype!=4&&nodes[i].status==1){
					if(nodes[i].datatype==null||$.trim(nodes[i].datatype).length<1){
						info += "第"+(i+1)+"行功能点【数据类型】不能为空<br/>";
						continue;
					}
					if(nodes[i].statisid==null||$.trim(nodes[i].statisid).length<1){
						info += "第"+(i+1)+"行功能点【功能点ID】不能为空<br/>";
						continue;
					}
				}
				if(nodes[i].chktype==4){
					nodes_1 = $('#reportFunTable').treegrid('getChildren',nodes[i].id);
					if(nodes_1==null||nodes_1.length<1){
						info += "第"+(i+1)+"行分组【"+nodes[i].name+"】下面没有二级功能点<br/>";
						continue;
					}
				}
				if(nodes[i].chktype!=4){
					if(nodes[i].valueMin==null||nodes[i].valueMin.length<1){
						info += "第"+(i+1)+"行功能点【"+nodes[i].name+"】下限值不能为空<br/>";
						continue;
					}
					if(nodes[i].valueMax==null||nodes[i].valueMax.length<1){
						info += "第"+(i+1)+"行功能点【"+nodes[i].name+"】上限值不能为空<br/>";
						continue;
					}
				}
				if(nodes[i].chktype!=4){
					if(ids.indexOf(","+nodes[i].statisid+",")!=-1){
						reindex = ids.substring(ids.indexOf(","+nodes[i].statisid+","));
						reindex = reindex.substring(reindex.indexOf(":")+1);
						reindex = reindex.substring(0,reindex.indexOf(":"));
						info += "第"+reindex+"和第"+(i+1)+"行功能点id重复<br/>";
						continue;
					}
					parentid = nodes[i].parentid;
					if(nodes[i].parentid==null||$.trim(nodes[i].parentid).length<1||nodes[i].chktype==2){
						parentid = "parentid";
					}
					if(titles.indexOf(","+parentid+",")!=-1){
						arr = titles.split(","+parentid+",");
						for(var j=0;j<arr.length;j++){
							if(arr[j]!=null&&$.trim(arr[j]).length>3&&arr[j].indexOf(":")==0){
								var rname = arr[j].substring(arr[j].indexOf(":")+1);
								rname = rname.substring(rname.indexOf(":")+1);
								rname = rname.substring(0,rname.indexOf(":"));
								if($.trim(rname)==$.trim(nodes[i].name)){
									reindex = arr[j].substring(arr[j].indexOf(":")+1);
									reindex = reindex.substring(0,reindex.indexOf(":"));
									info += "第"+reindex+"和第"+(i+1)+"行功能点名称不能重复<br/>";
									continue;
								}
							}
						}
					}
					ids +=nodes[i].statisid+",:"+(i+1)+":,";
					titles +=parentid+",:"+(i+1)+":"+nodes[i].name+":,";
					
				}
				/**
				 * 获取序号,如果记录拖动后，传到后台的记录不是前台看到的顺序，所以序号要从前台传到后台
				 */
				rowindex = $('#reportFunTable').treegrid('getPanel').find('tr[node-id='+nodes[i].id+']').find(".datagrid-cell-rownumber").html();
				reportArr[reportArr.length]={
					"id":nodes[i].id, //功能点或分组id
					"chktype":nodes[i].chktype,//功能点类型 2:一级功能点 3:二级功能点 4:分组  
					"name":nodes[i].name,//功能点或分组名称
					"parentid":nodes[i].parentid,//父id
					"datatype":nodes[i].datatype,//数据类型
					"regionvalue":nodes[i].regionvalue,//区间值
					"statisid":nodes[i].statisid,//功能点id
					"flag":nodes[i].flag,//是否核心功能点
					"status":nodes[i].status,//状态
					"rangeclassid":nodes[i].rangeclassid,//区间类型ID
					"rangeid":nodes[i].rangeid,//区间ID
					"rangetype":nodes[i].rangetype,//区间类型名称
					"selItemId":nodes[i].selItemId,//单选或文字链ID集合
					"selItemName":nodes[i].selItemName,//单选或文字链名称集合
					"valueMin":nodes[i].valueMin,//下限值
					"valueMax":nodes[i].valueMax,//上限值
					"rowindex":rowindex//获取序号
				};
			}
		}
	}

	if(info.length>1){
		closeRpt();
		$.messager.alert("提示",info,"error");
		return ;
	}
	
	var urlstr = base + '/manager/userbehavior/saveTypelist.'+actionExt;
	if(savechk==1){
		urlstr = base + '/manager/userbehavior/submitTypelist.'+actionExt;
	}
	
	jQuery.ajax({
		type : "POST",
		url : urlstr,
		dataType : 'json',
		data : {
			"jsonRecord" : escape($.toJSON(reportArr)),
			"clientid" : $("#clientid").val(),
			"clientvsn": $("#versionid").val(),
			"rptid" : $("#rptid").val()
		},
		success : function(responseText) {
			closeRpt();
			if(responseText.chk==0){
				if(savechk==1){
					$.messager.alert("提示","提交成功!","info");
					$("#versionid").find("option:selected").attr("newflag","1");
					changeVersion();
				}else{
					$.messager.alert("提示","保存成功!","info");
				}
			}else{
				if(savechk==1){
					$.messager.alert("提示","功能点ID重复，提交不成功!","info");
				}else{
					$.messager.alert("提示","功能点ID重复，保存失败!","info");
				}
			}
		},
		error:function(){
			closeRpt();
			$.messager.alert("提示","您没有提交权限，提交不成功!","error");
		}
	});
}
function progressRpt(title){
	if(title==null||$.trim(title).length<1){
		title = "数据加载中，请稍等...";
	}
	var str = "<div id=\"bar_background\" class=\"bar_background\" style=\"width:"+($(document).width()-5)+"px;height:"+$(document).height()+"px;\"></div>";
	str+="<div id=\"progressBar_m\" class=\"progressBar_m\" style=\"left:"+($(document).width()/2-90)+"px;top:"+($(document).scrollTop()+200)+"px;\">"+title+"</div> ";
	$("body").append(str);
}
function closeRpt(){
	$("#bar_background").remove();
	$("#progressBar_m").remove();
}
/**搜索点击报表类型*/
function searchRptType(){
	dataGridObj = null;
	if($("#searchId").attr("flag")==1){
		if($.trim($("#searchId").attr("classId")).length>0){
			funStype($("#searchId").attr("classId"));
		}else{
			$("#searchId").attr("flag",0);
		}
	}
}
/**搜索点击报表*/
function searchRpt(){
	if($("#searchId").attr("flag")==1){
		if($.trim($("#searchId").attr("rptId")).length>0){
			typelist($("#searchId").attr("rptId"));
		}else{
			$("#searchId").attr("flag",0);
		}
	}
}
/**搜索功能点*/
function searchFun(){
	if($("#searchId").attr("flag")==1){
		var searchid = $("#searchId").attr("itemId2");
		if($.trim(searchid).length>0){
			 $('#reportFunTable').treegrid('select',searchid);
		}else{
			searchid = $("#searchId").attr("groupId");
			if($.trim(searchid).length>0){
				$('#reportFunTable').treegrid('select',searchid);
			}else{
				searchid = $("#searchId").attr("itemId1");
				if($.trim(searchid).length>0){
					$('#reportFunTable').treegrid('select',searchid);
				}
			}
		}
		var obj = $(".datagrid-body-inner").find("tr[node-id="+searchid+"]");
		if(obj!=null){
			var top = obj.position().top;
			if(top>0){
				window.scroll(0,top);
			}
		}
		$("#searchId").attr("flag",0);
	}
}
/**
 * 只能输入数字
 * @param obj
 */
function isNumber(obj){
	obj.value=obj.value.replace(/\D/g,'');
}