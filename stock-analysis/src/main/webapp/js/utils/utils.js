



//
///** get short form String
// * 截断字符串,附加上结束字符,得到缩略形式的字符串.例如:将"abcdefg"变成"abcd......"
// * @param {int} len The max length of String
// * @param {String} alt alternative string adding after the short form,defalut is "......"
//*/
//String.prototype.getShortForm=function(len,alt){
//	var tempStr=this;
//	
//	if(this.getLength()>len){
//		if(!alt) {
//			alt="......";
//		}
//		
//		var i=0;
//		for(var z=0;z<len;z++){
//			if(tempStr.charCodeAt(z)>255){
//				i=i+2;
//			}else{
//				i=i+1;
//			}
//			
//			if(i>=len){
//				tempStr=tempStr.slice(0,(z + 1))+alt;
//				break;
//			}
//		}
//		return tempStr;
//	}else{
//		return this+"";
//	}
//};
//
//
/**get real length of String,include two-byte characters
*得到字符串长度,正确处理双字节字符
*/
String.prototype.getLength = function(){
		var text = this.replace(/[^\x00-\xff]/g,"**");
		return text.length;	
};

/**
 * 用正则表达式将前后空格（包括占两个字符的中文空格）；
 * BY YangZY
 * 2007-4-28
 */
String.prototype.trim  =  function() {
        //  用正则表达式将前后空格（包括占两个字符的中文空格）；
        //  用空字符串替代。
        return  this.replace(/(^[\s\u3000]*)|([\s\u3000]*$)/g,  "");
};
//
///**
//*parse json data
//*/
//String.prototype.parseJSON=function(){
//	try{
//		return!(/[^,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]/.test(this.replace(/"(\\.|[^"\\])*"/g,'')))&&eval('('+this+')');
//	}catch(e){return false;}
//};


/**
 * 格式化浮点数字有效小数位; 
 */
Number.prototype.format = function (pos) {
	if (pos !=0 && !(pos)) {
		pos = 2;
	}
	return Math.round(this*Math.pow(10, pos)) / Math.pow(10, pos);
};

///**
//*return i18n value in App.Loc
//*/
//function _(str) {
//	return _getText(str);
//}
//
///**
//*private function for _(str) use only
//*/
//function _getText(str) {
//	if (App.Loc[str]) str = App.Loc[str];
//	return str;
//}


//###############################################################Date########################

/**
*时间对象的格式化
*/
Date.prototype.format = function(format){ //author: meizz
	var o = {
		"M+" : 	this.getMonth()+1, 	//month
		"d+" : 	this.getDate(),    	//day
		"h+" : 	this.getHours(),   	//hour
	   	 "m+" : 	this.getMinutes(), 	//minute
	    	"s+" : 	this.getSeconds(), //second
	    	"q+" : 	Math.floor((this.getMonth()+3)/3),  //quarter
	    	"S"  : 	this.getMilliseconds() //millisecond
  	};
  
  	if(/(y+)/.test(format)) {
  		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  	}
 
  	for(var k in o) {
  		if(new RegExp("("+ k +")").test(format)) {
    		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
  		}
  	}
	return format;
};
//###############################################################Date########################
//
//var Utils = {};
//
///**
// * 将字符串转化为Date对象; 
// * @param dateStr 时间字符串;
// * @return 返回一个时间对象; 
// */
//Utils.parseDate = function (dateStr) {
//	var pDate;
//	try {
//		
////		test("isGMT=" + isGMT(dateStr) + ": isUTC=" + isUTC(dateStr) + 
////				": isCST=" + isCST(dateStr) + ": isTZD=" + isTZD(dateStr) +
////				": isCustom=" + isCustom(dateStr));
//		
//		if (isGMT(dateStr))  {
//			pDate = new Date(dateStr);
//		} else if (isCustom(dateStr)) {
//			pDate = new Date(dateStr.replace(/\-/g, "/").replace(/.0$/g,""));
//		} else if (isTZD(dateStr)) {
//			pDate = parseTZD(dateStr);
//		} else if (isCST(dateStr)) {
//			pDate = new Date(dateStr);
//			pDate.setHours(pDate.getHours() - (8+6));
//		} else {
//			pDate = new Date(dateStr);
//		}
//
//		if (isNaN( pDate.getTime())) {
//			return dateStr;
//		}
//		
////		test("curTime=" + (new Date().getTime()) + 
////				":pastTIme=" + pDate.getTime() + 
////				":dec=" + (new Date().getTime() - pDate.getTime()));
//		
//	} catch (ex) {
//		test("RPT try::"  + ex);
//		return dateStr;
//	}
//	
//	return pDate;
//	
//	function parseTZD (cTime) {
//		/**
//		*	012345678901234567890123456789
//		*	2006-07-16T19:20:30.45+01:00
//		*	1994-11-05T08:15:30-05:00 	corresponds to November 5, 1994, 8:15:30 am, US Eastern Standard Time.
//		*	1994-11-05T13:15:30Z 		corresponds to the same instant.
//		*/
//		var tStr1 = cTime.substr(0,19).replace(/\-/g, "/");
//		var tDate = new Date(tStr1.replace("T", " "));
//		var tTimes = cTime.substr(20).split(":");
//		switch (cTime.substr(19,1)) {
//			case "Z":
//			case "z":
//				break;
//			case "+":
//				tDate.setHours(tDate.getHours() + tTimes[0]);
//				tDate.setMinutes(tDate.getMinutes() + tTimes[1]);
//				tDate.setSeconds(tDate.getSeconds() + tTimes[2]);
//				break;
//			case "-":
//				tDate.setHours(tDate.getHours() - tTimes[0]);
//				tDate.setMinutes(tDate.getMinutes() - tTimes[1]);
//				tDate.setSeconds(tDate.getSeconds() - tTimes[2]);
//				break;
//		}
//		return tDate;
//	}
//	
//	/**
//	*"Thu, 28 Sep 2006 03:39:12 GMT"
//	*/
//	function isGMT(cTime) {
//		//return ((cTime.search(/GMT/i) == -1) ? (false) : (true));
//		return (/^[a-zA-Z]{3},\s\d{2}\s[a-zA-Z]{3}\s\d{4}\s\d{2}:\d{2}:\d{2}/.test(cTime));
//   	}
//	
//	/**
//	*"Wed Sep 27 00:00:00 UTC+0800 2006"
//	*
//	*/
//	function isUTC (cTime) {
//		//test("UTC=" + cTime.search(/\+/i) + "::" + cTime.search(/T/i));
//		return (((((cTime.search(/\+/i) != -1) || (cTime.search(/\-/i) != -1) && (cTime.search(/\,/i) != -1))) && (((cTime.search(/\,/i) == -1) && (cTime.search(/UTC/i) != -1)) || (cTime.search(/T/i) == -1))) ? (true) : (false));
//	}
//	
//	/**
//	*"Thu Sep 28 13:42:33 CST 2006"
//	*/
//	function isCST (cTime) {
//		//return ((cTime.search(/CST/i) == -1) ? (false) : (true));
//		return (/^[a-zA-Z]{3}\s[a-zA-Z]{3}\s\d{1,2}\s\d{2}:\d{2}:\d{2}\sCST\s\d{4}/.test(cTime));
//	}
//	/**
//	*	"2006-07-16T19:20:30.45+01:00";
//	*	Examples
//	*	1994-11-05T08:15:30-05:00 	corresponds to November 5, 1994, 8:15:30 am, US Eastern Standard Time.
//	*	1994-11-05T13:15:30Z 		corresponds to the same instant.
//	*/
//	function isTZD (cTime) {
////		cTime = cTime.replace(/\s+/g,"");
////		return (((cTime.search(/T/i) != -1) && (cTime.search(/-/i) != -1)) ? (true) : (false));
//		return /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[\+\-Zz]/.test(cTime);
//	}
//	/**
//	 * yyyy-MM-dd hh:mm:ss
//	 */
//	function isCustom (cTime) {
//		//test("isCustom" + cTime);
//		//test(/\-/g);
//		//test("search::" + cTime.search(/-/i) + cTime.search(/:/i));
////		if ((cTime.search(/-/i) != -1) 
////			&& (cTime.search(/:/i) != -1)
////			&& (cTime.search(/\,/i) == -1)
////			&& (cTime.search(/\T/i) == -1)
////		){
//		//if ((cTime.search(/[-:]/i) != -1) && (cTime.search(/[\,T]/i) == -1)) {
//		if (/^\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2}/.test(cTime)) {
//			//alert("true");
//			return true;
//		} else {
//			//alert("false");
//			return false;
//		}
//	}
//		
//};

//--------------------------------------------------------------------------------------------//


//
///**
// * 
// * 获得cookie
// * @param key cookie字段名;
// * @return 返回cookie内容;
// * @author YangZY
// * @since 2008-10-21
// */
//Utils.getCookie = function (key) {
//	var tmp,reg=new RegExp("(^| )"+key+"=([^;]*)(;|$)","gi");
//	if(tmp=reg.exec(document.cookie))return(tmp[2]);
//	return null;
//};
//
///**
// * 设置cookie值;
// * @param key cookie字段名;
// * @param value cookie字段值;
// * @param days 失效时间(单位天);
// */
//Utils.setCookie = function (key, value, days) {
//	var cookieContent = key + '=' + value;
//	
//	/*
//	 * 设置失效时间;
//	 */
//	if (parseInt(days)) {
//		/*获取当前时间*/
//		var tdate = new Date();
//		/*
//		 * 重新设置时间对象;
//		 */
//		tdate.setTime(tdate.getTime()+parseInt(days)*24*3600*1000);
//		
//		cookieContent += '; expires=' + tdate.toGMTString();
//	}
//	document.cookie = cookieContent;
//};

//function $c (tagName) {
//	return document.createElement(tagName);
//}



///**
// * zoom : {
// * 	u : 0,//up
// *  d : 0,//down
// *  l : 0,//left
// *  r : 0//right
// * }
// */
//
//function isMouseIn (evt, elm, zoom) {
//	
//	if (!elm || !evt) {
//		return false;
//	}
//	
//	zoom =  zoom || {u:0,d:0,l:0,r:0};
//	zoom.u = zoom.u || 0;
//	zoom.d = zoom.d || 0;
//	zoom.r = zoom.r || 0;
//	zoom.l = zoom.l || 0;
//	
//	//debug.info('u=' + zoom.u + ':d=' + zoom.d + ':l=' + zoom.l + ':r=' + zoom.r);
//	
//	var mx = evt.pageX;
//	var my = evt.pageY;
//	
//	var jqe = $(elm);
//	var pos = jqe.position();
//	var ew = jqe.width();
//	var eh = jqe.height();
//	
//	if (mx > (pos.left - zoom.l)&& mx < (pos.left + ew + zoom.r) &&
//			my > (pos.top - zoom.u) && my < (pos.top + eh + zoom.d)) {
//				
//		return true;
//	}
//	
//	return false;	
//} 
////---------------------------//
//
//function _ (str) {
//	return str;
//}

//
//Object.extend = function(destination, source) {
//  for (var property in source)
//    destination[property] = source[property];
//  return destination;
//};


/**
 * 扩展jQuery函数;
 * $.within()
 * 
 */
(function($){
	
	$.extend({
		within : function (elms, event) {
			
			if (!elms || !event) {
				
				//debug.info("elm或event为空");
				
				return false;
			}
			
			if (!jQuery.isArray(elms)) {
				elms = [elms];
			}
			
			for (var i=0; i<elms.length; i++) {
				var elm = elms[i];
				var pos = $(elm).offset();
				
				if (pos == null) {
					
					//debug.info("pos为空");
					return false;
				}
							
				if (event.pageX >= (pos.left) && 
						event.pageX <= (pos.left + $(elm).outerWidth(false)) &&
						event.pageY >= (pos.top) && 
						event.pageY <= (pos.top + $(elm).outerHeight(false))
				){
					return true;
				} 
			}
			
			
			return false;
		}
	});
	
})(jQuery);

/**
 * 扩展jQuery函数;
 * $.tips()
 * 
 */
(function($){
	
	$.extend({
		tips : function  (title, message, timeout) {
			if (!title) {
				title = '提示';
			}
			
			var content = 
				'<div class="s_tip_3" ' +
						'style="background : url(' + 
						base + '/login/img/tip3.png) center bottom no-repeat; padding-bottom:10px;height: 130px;">'+
					'<div class="s_tip_bg" style="height: 126px;"></div>'+
					'<div class="s_tip_inner">'+
						'<div class="clearfix">'+
							'<em class="icon ic8" onclick="$.unblockUI();">&nbsp;</em>'+
							'<strong>' +
								title + 
							'</strong>'+
						'</div>'+
						'<p class="icon_tip3">' +
							message +
						'</p>'+
					'</div>'+
				'</div>';
				
				
			var $m = $(content);
			if (timeout == undefined) timeout = 5000;
			$.blockUI({
				message: $m, 
				fadeIn: 700, 
				fadeOut: 1000,
				timeout : timeout, 
				showOverlay: false,
				//onUnblock: onClose,
				css: {
					width:		'30%',
					top:		'40%',
					left:		'35%',
					textAlign:	'left',
					border: 	'none',
					padding:	'5px',
					color:		'#fff'
					,backgroundColor: 'none'
				}
			});
		}
	});
	
})(jQuery);


//========== REQUEST ====================================================================================================

var Request = new Object();

Request.send = function(url, method, callback, data, urlencoded) {
	var req;	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = function() {
		if (req.readyState == 4) {// only if req shows "loaded"
			if (req.status < 400) {// only if "OK"
				(method=="POST") ? callback(req) : callback(req,data);
			} else {
				alert(null, url+_("error")+":\n" + req.status+ "/" + req.statusText);
			}
		}
	}
	if (method=="POST") {
		req.open("POST", url, true);
		
		/*
		 * application/x-www-form-urlencoded：窗体数据被编码为名称/值对。这是标准的编码格式。
		 * multipart/form-data：窗体数据被编码为一条消息，页上的每个控件对应消息中的一个部分。 
		 * text/plain：窗体数据以纯文本形式进行编码，其中不含任何控件或格式字符。
		 */		
		if (urlencoded) req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');		
		req.send(data);
	} else {
		req.open("GET", url, true);
		req.send(null);
	}
	
	return req;
}

Request.sendRawPOST = function(url, data, callback) {
	Request.send(url, "POST", callback, data, false);
}
Request.sendPOST = function(url, data, callback) {
	Request.send(url, "POST", callback, data, true);
}
Request.sendGET = function(url, callback, args) {
	return Request.send(url, "GET", callback, args);
}

//---------------------JS截取字符长度（按字节）-----------------------//
function autoAddEllipsis(str, sub_length){   
    //var sub_length = 80 ;   
    var temp1 = str.replace(/[^\x00-\xff]/g,"**");//精髓   
    var temp2 = temp1.substring(0,sub_length);   
    //找出有多少个*   
    var x_length = temp2.split("\*").length - 1 ;   
    var hanzi_num = x_length /2 ;   
    sub_length = sub_length - hanzi_num ;//实际需要sub的长度是总长度-汉字长度   
    var res = str.substring(0,sub_length);
    var end;
    if(sub_length < str.length ){   
        end = res+".." ;   
    }else{    
        end = res ;   
    }   
    return end ;   
}  








