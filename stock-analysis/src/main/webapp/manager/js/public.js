function checkall(obj_box) {
	if (obj_box + "." != "undefined.") {
		if (obj_box.length + "." != "undefined.") {
			for (i = 0; i < obj_box.length; i++) {
				if (!obj_box[i].disabled) {
					obj_box[i].checked = "checked";
				}
			}
		} else {
			if (!obj_box.disabled) {
				obj_box.checked = "checked";
			}
		}
	}
}

function checknull(obj_box) {
	if (obj_box + "." != "undefined.") {
		if (obj_box.length + "." != "undefined.") {
			for (i = 0; i < obj_box.length; i++) {
				obj_box[i].checked = "";
			}
		} else {
			obj_box.checked = "";
		}
	}
}
function isInteger(strNum, msg) {
	if (strNum.search(/^(0|[1-9]\d*)$/) != -1) {
		return true;
	} else {
		alert(msg);
		return false;
	}
}
function isEmail(strEmail, msg) {
	if (strEmail
			.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		alert(msg);
		return false;
	}
}

function getCheckAlls(){
	var str="[";
	var num = 0;
	var checks = $("input[name='choiceItem']");
	$.each(checks,function(index,ele){
		if(ele.checked){
			num = num+1;
			str=str+ ele.value+",";
		}
	});
	if(num == 0){
		alert("没有选择任何选项，请选择！");
		return str='0';
	}
	str = str.substring(0, str.length-1)+"]";
	return str;
}

function checkboxCount(obj_box) {
	check = 0;
	if (obj_box + "." != "undefined.") {
		if (obj_box.length + "." != "undefined.") {
			for (i = 0; i < obj_box.length; i++) {
				if (obj_box[i].checked) {
					check = check + 1;
				}
			}
		} else {
			if (obj_box.checked) {
				check = 1;
			}
		}
	}
	return check;
}

function popWindowCenter(htmlurl, name, wid, heig) {
	scWidth = screen.width / 2 - wid / 2;
	scHeight = screen.height / 2 - heig / 2;
	window
			.open(
					htmlurl,
					name,
					"toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,"
							+ ",left="
							+ scWidth
							+ ",top="
							+ scHeight
							+ ",width=" + wid + ",height=" + heig);
}

function popWindowCenterNormal(htmlurl, name, wid, heig) {
	scWidth = screen.width / 2 - wid / 2;
	scHeight = screen.height / 2 - heig / 2;
	window
			.open(
					htmlurl,
					name,
					"toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,"
							+ ",left="
							+ scWidth
							+ ",top="
							+ scHeight
							+ ",width=" + wid + ",height=" + heig);
}

function popWindowCenterCanScroll(htmlurl, name, wid, heig) {
	scWidth = screen.width / 2 - wid / 2;
	scHeight = screen.height / 2 - heig / 2;
	window
			.open(
					htmlurl,
					name,
					"toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,"
							+ ",left="
							+ scWidth
							+ ",top="
							+ scHeight
							+ ",width=" + wid + ",height=" + heig);
}

//校验时间（格式为： 2011-01-01 15:30:30）
function isDateTime(param){
	//年的范围为1000-2999
	var pattern = /^[1-2]\d{3}[\-]([0][0-9]|[1][0-2])[\-]([0-2][0-9]|[3][0-1])[\s]([0-1][0-9]|[2][0-3])[\:]([0-5][0-9])[\:]([0-5][0-9])$/;
	if (pattern.test(param)) {
		return true;
	}
	return false;
	
}
