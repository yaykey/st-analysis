function showDetail(uid){
	user_data.action = base + "/manager/user/view."+actionExt+"?uid="+uid;
	user_data.submit();
}
function goPage(pageNum) {
	if (isInteger(pageNum, "请使用整型！")) {
		var condition_ = $("#condition").val();
		if(condition_=="请输入查询的用户名"){
			condition_ = "";
		}
		if( $("#department").val()==""){
			user_data.action = base + "/manager/user/list."+actionExt+"?page="+ pageNum+"&userName_="+condition_;
		}else{
			user_data.action = base + "/manager/user/list."+actionExt+"?page="+ pageNum+"&userName_="+condition_+"&department=" + $("#department").val();
		}
		user_data.submit();
	}
}
function loseStauts(obj,userid,ifEnable){
	var info = "";
	if(ifEnable==0){
		info = "确定要将此用户置为失效状态吗?";
	}else{
		info = "确定要将此用户置为生效状态吗?";
	}
	if(!confirm(info)){
		return false;
	}
	jQuery.ajax({
		type : "POST",
		url : base + "/manager/user/effStauts."+actionExt,
		dataType : 'json',
		data : {
			"useridArr" : userid,
			"ifEnable" :ifEnable
		},
		success : function(responseText) {
			if(responseText.chk==0){
				var str = "";
				if(ifEnable==0){
					str = "<span class=\"double_right_tab1 fr\">失效</span>";
					str +="<span class=\"double_left_tab1 fr\" onclick=\"loseStauts(this,'"+userid+"',1)\">激活</span>";
				}else{
					str = " <span class=\"double_right_tab2 fr\" onclick=\"loseStauts(this,'"+userid+"',0)\">失效</span>";
					str +="<span class=\"double_left_tab2 fr\">激活</span>";
				}
				$(obj).parent().html(str);
			}else{
				if(ifEnable==1){
					alert("激活失败");
				}else{
					alert("失效失败");
				}
			}
		},
		error:function(){
			alert("激活失败");
		}
	});
}
function batchStauts(ifEnable){
	var info = "";
	if(ifEnable==0){
		info = "确定置为失效状态吗?";
	}else{
		info = "确定置为生效状态吗?";
	}
	if(!confirm(info)){
		return false;
	}
	info = "";
	var obj;
	var userId = [];
	$("input:checked").each(function(i){
		userId[i] = $(this).val();
		if(ifEnable==0){
			obj = $(this).parent().parent().find(".double_right_tab2");
			if(obj.parent().html()==null){
				info += "用户名称为【"+$(this).attr("realname")+"】为失效状态，不能重复失效\n";
			}
		}else{
			obj = $(this).parent().parent().find(".double_right_tab1");
			if(obj.parent().html()==null){
				info += "用户名称为【"+$(this).attr("realname")+"】为激活状态，不能重复激活\n";
			}
		}
	});
	if(info.length>1){
		alert(info);
		return false;
	}
	
	jQuery.ajax({
		type : "POST",
		url : base + "/manager/user/effStauts."+actionExt,
		dataType : 'json',
		data : {
			"useridArr" : userId.join(","),
			"ifEnable" :ifEnable
		},
		success : function(responseText) {
			if(responseText.chk==0){
				var str = "";
				
				$("input:checked").each(function(i,val){
					if(ifEnable==0){
						obj = $(this).parent().parent().find(".double_right_tab2");
						str = "<span class=\"double_right_tab1 fr\">失效</span>";
						str +="<span class=\"double_left_tab1 fr\" onclick=\"canselStauts(this,'"+$(this).val()+"',1)\">激活</span>";
					}else{
						obj = $(this).parent().parent().find(".double_right_tab1");
						str = " <span class=\"double_right_tab2 fr\" onclick=\"canselStauts(this,'"+$(this).val()+"',0)\">失效</span>";
						str +="<span class=\"double_left_tab2 fr\">激活</span>";
					}
					$(obj).parent().html(str);
				});
			}else{
				if(ifEnable==1){
					alert("激活失败");
				}else{
					alert("失效失败");
				}
			}
		},
		error:function(){
			alert("激活失败");
		}
	});
}
function changeDate(obj,userid){
	jQuery.ajax({
		type : "POST",
		url : base + "/manager/user/effDate."+actionExt,
		dataType : 'json',
		data : {
			"useridArr" : userid,
			"effDate" :$(obj).val()
		},
		success : function(responseText) {
			
		}
	});
}