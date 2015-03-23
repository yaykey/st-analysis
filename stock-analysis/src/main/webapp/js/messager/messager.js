/**
 * 
 */
JMessage = {
	alert:function(title,content,fn,cssType){
		
		var l = $(document).width();
		l = (l-300)/2;
		var cssObj = {width:300,left:l,top:250};
		if(cssType!=null){
			if(cssType.top!=null){
				cssObj.top = cssType.top;
			}
			if(cssType.left!=null){
				cssObj.left = cssType.left;
			}
		}

		var root = $("<div class=\"pop-msg-box\"></div>");
		var tit = $("<div class=\"tit\"></div>");
		var close = $("<a href=\"javascript:void(-1)\" class=\"shut\" title=\"关闭\"></a>");
		tit.append("<span class=\"title\">"+title+"</span>");
		tit.append(close);
		root.append(tit);
		root.append("<p class=\"txt\">"+content+"</p>");
		var button = $("<div class=\"button\"></div>");
		var sure = $("<a href=\"javascript:void(-1);\" class=\"b-sure\">确定</a>");
		button.append(sure);
		root.append(button);
		root.css({width:cssObj.width,left:cssObj.left,top:cssObj.top});
		
		var bgStyle = "width:"+($(document).width()-5)+"px;height:"+$(document).height()+"px;";
		var background = $("<div class=\"pop-bg\" style=\""+bgStyle+"\"></div>");
		$("body").append(root);
		$("body").append(background);
		close.bind("click",function(){
			if(fn!=null){
				fn();
			}
			background.remove();
			root.remove();
			return false;
		});
		sure.bind("click",function(){
			if(fn!=null){
				fn();
			}
			background.remove();
			root.remove();
			//jQuery.unblockUI();
			return false;
		});
	},
	confirm:function(title,content,fn,cssType){
		var l = $(document).width();
		l = (l-300)/2;
		var cssObj = {width:300,left:l,top:250};
		if(cssType!=null){
			if(cssType.top!=null){
				cssObj.top = cssType.top;
			}
			if(cssType.left!=null){
				cssObj.left = cssType.left;
			}
		}

		var root = $("<div class=\"pop-msg-box\"></div>");
		var tit = $("<div class=\"tit\"></div>");
		var close = $("<a href=\"javascript:void(-1)\" class=\"shut\" title=\"关闭\"></a>");
		tit.append("<span class=\"title\">"+title+"</span>");
		tit.append(close);
		root.append(tit);
		root.append("<p class=\"txt\">"+content+"</p>");
		var button = $("<div class=\"button\"></div>");
		var sure = $("<a href=\"javascript:void(-1);\" class=\"b-sure\">确定</a>");
		var cancel = $("<a href=\"javascript:void(-1);\" class=\"b-cancel\">取消</a>");
		button.append(sure);
		button.append(cancel);
		root.append(button);
		root.css({width:cssObj.width,left:cssObj.left,top:cssObj.top});
		
		var bgStyle = "width:"+($(document).width()-5)+"px;height:"+$(document).height()+"px;";
		var background = $("<div class=\"pop-bg\" style=\""+bgStyle+"\"></div>");
		$("body").append(background);
		$("body").append(root);
		
		close.bind("click",function(){
			background.remove();
			root.remove();
			return false;
		});
		sure.bind("click",function(){
			if(fn!=null){
				fn();
			}
			background.remove();
			root.remove();
			return false;
		});
		cancel.bind("click",function(){
			background.remove();
			root.remove();
			return false;
		});
	}
}