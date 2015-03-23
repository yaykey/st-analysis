$(function(){
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份
			"d+" : this.getDate(), //日
			"h+" : this.getHours(), //小时
			"m+" : this.getMinutes(), //分
			"s+" : this.getSeconds(), //秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
			"S" : this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			return fmt;
	}
	Date.prototype.addDays = function(d){
		this.setDate(this.getDate() + d);
	};
	Date.prototype.addWeeks = function(w){
		this.addDays(w * 7);
	};
	Date.prototype.addMonths= function(m){
		var d = this.getDate();
		this.setMonth(this.getMonth() + m);

		if (this.getDate() < d)
			this.setDate(0);
	};
	Date.prototype.addYears = function(y) {
		var m = this.getMonth();
		this.setFullYear(this.getFullYear() + y);

		if (m < this.getMonth()) {
			this.setDate(0);
		}
	};
	Date.prototype.getFirstDay = function() {
		var tmpDate = new Date(Date.parse(this));
		tmpDate.setDate(1);
		return tmpDate.getDay();
	};
	Date.prototype.getWeekNumber = function() {
		var tempDate = new Date(this);
		tempDate.setDate(tempDate.getDate() - (tempDate.getDay() + 6) % 7 + 3);
		var dms = tempDate.valueOf();
		tempDate.setMonth(0);
		tempDate.setDate(4);
		return Math.round((dms - tempDate.valueOf()) / (604800000)) + 1;
	};
	Date.prototype.getDayOfYear = function() {
		var now = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0, 0);
		var then = new Date(this.getFullYear(), 0, 0, 0, 0, 0);
		var time = now - then;
		return Math.floor(time / 24*60*60*1000);
	};
}) ;
function date_formart(date, format) {
	if(undefined!=date){
		var m = date.getMonth();
		var d = date.getDate();
		var y = date.getFullYear();
		var wn = date.getWeekNumber();
		var w = date.getDay();
		var s = {};
		var hr = date.getHours();
		var pm = (hr >= 12);
		var ir = (pm) ? (hr - 12) : hr;
		var dy = date.getDayOfYear();
		if (ir == 0) {
			ir = 12;
		}
		var min = date.getMinutes();
		var sec = date.getSeconds();
		var parts = format.split(''), part;
		for ( var i = 0; i < parts.length; i++ ) {
			part = parts[i];
			switch (parts[i]) {
				case 'a':
					part = date.getDayName();
					break;
				case 'A':
					part = date.getDayName(true);
					break;
				case 'b':
					part = date.getMonthName();
					break;
				case 'B':
					part = date.getMonthName(true);
					break;
				case 'C':
					part = 1 + Math.floor(y / 100);
					break;
				case 'd':
					part = (d < 10) ? ("0" + d) : d;
					break;
				case 'e':
					part = d;
					break;
				case 'H':
					part = (hr < 10) ? ("0" + hr) : hr;
					break;
				case 'I':
					part = (ir < 10) ? ("0" + ir) : ir;
					break;
				case 'j':
					part = (dy < 100) ? ((dy < 10) ? ("00" + dy) : ("0" + dy)) : dy;
					break;
				case 'k':
					part = hr;
					break;
				case 'l':
					part = ir;
					break;
				case 'm':
					part = (m < 9) ? ("0" + (1+m)) : (1+m);
					break;
				case 'M':
					part = (min < 10) ? ("0" + min) : min;
					break;
				case 'p':
				case 'P':
					part = pm ? "PM" : "AM";
					break;
				case 's':
					part = Math.floor(date.getTime() / 1000);
					break;
				case 'S':
					part = (sec < 10) ? ("0" + sec) : sec;
					break;
				case 'u':
					part = w + 1;
					break;
				case 'w':
					part = w;
					break;
				case 'y':
					part = ('' + y).substr(2, 2);
					break;
				case 'Y':
					part = y;
					break;
			}
			parts[i] = part;
		}
		return parts.join('');
	}
}

function parseDate(date, type, order){
	if(type == 1){
		var year = date.substr(0,4);
		var month = date.substr(4,2);
		var day = date.substr(6);
		return new Date(year, month-1, day);
	}else{
		var year = date.substr(0,4);
		var month = date.substr(4);
		var day = "01";
		return new Date(year, month-1, day);
	}
}