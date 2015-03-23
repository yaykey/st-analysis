
(function($){
	var WeekPicker = function(){
		var	ids = {},
		views = {
			years: 'datepickerViewYears',
			weeks: 'datepickerViewWeeks'
		},
		tpl = {
			wrapper: '<div class="datepickerWeek"><div class="datepickerContainers"><table cellspacing="0" cellpadding="0" class="tableContent"><tbody><tr></tr></tbody></table></div></div>',
			head: [
				'<td>',
				'<table cellspacing="0" cellpadding="0">',
					'<thead>',
						'<tr>',
							'<th width="40%" align="right" class="datepickerGoPrev"><a href="#"><img src="../date/images/arrow_left.png" style="border:none;"></span></a></th>',
							'<th class="datepickerMonth" align="center"><span></span></th>',
							'<th width="40%" align="left" class="datepickerGoNext"><a href="#"><img src="../date/images/arrow_right.png" style="border:none;"></a></th>',
						'</tr>',
					'</thead>',
				'</table></td>'
			],
			space : '<td class="datepickerSpace"><div style="height:100%;width:2px;"></div></td>',
			weeks: [
				'<tbody class="datepickerWeeks">',
				'</tbody>'
			]
		},
		defaults = {
				flat: false,
				starts: 1,
				lastSel: false,
				mode: 'single',
				view: 'weeks',
				calendars: 1,
				data: {},
				format: 'Y-m-d',
				position: 'bottom',
				eventName: 'click',
				onRender: function(){return {};},
				onChange: function(){return true;},
				onShow: function(){return true;},
				onBeforeShow: function(){return true;},
				onHide: function(){return true;},
				locale: {
					//days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
					//daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
					//daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
					//months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
					//monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
					//weekMin: '周'
				}
			},
		fill = function(el) {
			var options = $(el).data('datepickerWeek');
			var cal = $(el);
			var currentCal = Math.floor(options.calendars/2), date, dow, tblCal;
			//必须给html赋初始值，否则后面直接+html显示undefined
			var html = "";
			cal.find('td>table tbody').remove();
			for (var i = 0; i < options.calendars; i++) {
				date = new Date(options.current);
				date.addMonths(-currentCal + i);
				tblCal = cal.find('table').eq(i+1);
				switch (tblCal[0].className) {
					case 'datepickerViewWeeks':
						dow = formatDate(date, 'Y');
						break;
				} 
				tblCal.find('thead tr:first th:eq(1) span').text(dow);
				
				html = tmpls(tpl.weeks.join(''), null) + html;
				tblCal.append(html);
			}
		},
		fillbody = function(el){
			var options = $(el).data('datepickerWeek');
			var weekId = options.id;
			if(undefined!=options.data){
				var item = eval(options.data);
				var year = item[1];
				var ymonth = 0, value;
				var count = 1;
				$("<tr><td colspan='3'><table cellspacing='0' cellpadding='0' class='tableMain'><tbody>").appendTo("#"+ weekId +" table.datepickerViewWeeks tbody");
				for(var i = 0; i <= item[2]-1; i++){
					/*var month = item[0][i].monthId;
					var week = item[0][i].weekId;
					var weekRange = item[0][i].weekName;*/
					var month = item[0][i].MONTH_ID;
					var week = item[0][i].WEEK_ID;
					var weekRange = item[0][i].WEEK_NAME;
					if(ymonth != month && month < 7){
						$("<tr id='month"+month+"'><td bgcolor='#5A9FE6' class='tmonth' class='lab'>" + month + "月</td>").appendTo("#"+ weekId +" table.tableMain tbody");
					} 
					if(week < 10){
						value = '0'+ week;
					}else{
						value = week;
					}
					value = year + value;
					var fromUser = options.onRender(value);
					if(month < 7){
						if(ymonth==month){
							count++;
						}
						if(fromUser.selected || options.date == value || options.mode == 'range' && value >= options.date[0] && value <= options.date[1]){
							$("<td class='content selected' ><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+month);
						}else if (fromUser.disabled) {
							$("<td class='content datepickerDisabled' ><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+month);
						}else{
							$("<td class='content'><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+month);
						}
						
						if(count < 5 && ymonth != month && ymonth != 0){
							count = 1;
							$("<td class='content'><span></span></td>").appendTo("#"+ weekId +" #month"+(month-1));
						}
						if(count == 5 && ymonth != month && ymonth != 0){
							count = 1;
						}
					}
					if(ymonth != month && month >= 7){
						$("<td id='month"+month+"' class='tmonth' class='lab' bgcolor='#5A9FE6'>" + month + "月</td>").appendTo("#"+ weekId +" #month"+(month-6));
					}
					if(month>=7){
						if(ymonth==month){
							count++;
						}
						if(fromUser.selected || options.date == value || options.mode == 'range' && value >= options.date[0] && value <= options.date[1]){
							$("<td class='content selected'><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+(month-6));
						}else if (fromUser.disabled) {
							$("<td class='content datepickerDisabled'><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+(month-6));
						}else{
							$("<td class='content'><a href='#' title='" + weekRange + "'><span>第"+week+"周</span><a></td>").appendTo("#"+ weekId +" #month"+(month-6));
						}
						if(count < 5 && ymonth != month ){
							count = 1;
							if(month==7){
								$("<td class='content'><span></span></td>").appendTo("#"+ weekId +" #month"+(month-1));
							}else{
								$("<td class='content'><span></span></td>").appendTo("#"+ weekId +" #month"+(month-7));
							}
							
						}
						if(count == 5 && ymonth != month ){
							count = 1;
						}
						if(count == 4 && ymonth == month && ymonth == 12 && i == item[2]-1){
							//$("<td class='content'><span></span></td>").appendTo("#"+ weekId +" #month"+(month-6));
						}
					}
					ymonth = month;
				}
			}
		},
		click = function(ev) {
			if ($(ev.target).is('IMG')) {
				ev.target = ev.target.parentNode;
			}
			if ($(ev.target).is('span')) {
				ev.target = ev.target.parentNode;
			}
			var el = $(ev.target);
			if (el.is('a')) {
				ev.target.blur();
				if (el.hasClass('datepickerDisabled')) {
					return false;
				}
				var options = $(this).data('datepickerWeek');
				var parentEl = el.parent();
				var tblEl = parentEl.parent().parent().parent();
				var tblIndex = $('table', this).index(tblEl.get(0)) - 1;
				var tmp = new Date(options.current);
				var changed = false;
				var fillIt = false;
				var close = true;
				if (parentEl.is('th')) {
					if (parentEl.parent().parent().is('thead')) {
						switch (tblEl.get(0).className) {
							case 'datepickerViewWeeks':
								options.current.addYears(parentEl.hasClass('datepickerGoPrev') ? -1 : 1);
								var item = eval(options.data);
								var minYear = item[3];
								var maxYear = item[4];
								if(options.current.getFullYear() > maxYear){
									options.current.addYears(-1);
									$(".datepickerGoNext").attr("title","年份到达末端");
								}else{
									$(".datepickerGoNext").removeAttr("title");
								}
								
								if(options.current.getFullYear() < minYear){
									options.current.addYears(1);
									$(".datepickerGoPrev").attr("title","年份到达顶端");
								}else{
									$(".datepickerGoPrev").removeAttr("title");
								}
								options.data = getWeekData(options);
								break;
						}
						fillIt = true;
						close = true;
					}
				} else if (parentEl.is('td') && !parentEl.hasClass('datepickerDisabled')) {
					switch (tblEl.get(0).className) {
						case 'datepickerViewYears':
							options.current.setFullYear(parseInt(el.text(), 10));
							tblEl.get(0).className = 'datepickerViewMonths';
							break;
						default:
							var text = el.text().replace(/[^0-9]/ig,""); ;
							var val = parseInt(text, 10);
							//tmp.addYears(tblIndex - Math.floor(options.calendars/2));
							var year = tmp.getFullYear();
							var week;
							if(text.length<=1){
								text = '0'+ text;
							}
							week = year + text;
							switch (options.mode) {
								case 'range':
									if (!options.lastSel) {
										options.date[0] = week;
									}
									val = week;
									 if (val < options.date[0]) {
										 options.date[1] = options.date[0];
										 options.date[0] = val;
									 } else {
										options.date[1] = val;
									 }

									options.lastSel = !options.lastSel;
									close = options.lastSel;
									break;
								default:
									options.date[0] = week;
									break;
							}
							break;
					}
					fillIt = true;
					changed = true;
				}
				if (fillIt) {
					fill(this);
					fillbody(this);
				}
				if (changed) {
					options.onChange.apply(this, prepareDate(options));
				}
				/* 在选区间第二次选择结束后将控件div隐藏并清除选中的区域-------------开始 */
				if(!close){
					//隐藏
					$('#' + options.id).hide('slow');
					//清除
					if (options.mode != 'single') {
						options.date = [];
						//fill(this);
					}
				}
				/* 在选区间第二次选择结束后将控件div隐藏并清除选中的区域-------------结束 */
				
			}
			return false;
		},
		getWeekData = function(options){
			var data ;
			$.ajax({
				type:"post",
				url: base + "/common-week/getWeeks.feinno?year=" + options.current.getFullYear(),
				//data:"",
				dataType:'json',
				async: false,
				success:function(msg){
					data = msg;
				}
			});
			return data;
		},
		extendDate = function(options) {
			if (Date.prototype.tempDate) {
				return;
			}
			Date.prototype.tempDate = null;
			Date.prototype.months = options.months;
			Date.prototype.monthsShort = options.monthsShort;
			Date.prototype.days = options.days;
			Date.prototype.daysShort = options.daysShort;
			/**Date.prototype.getMonthName = function(fullName) {
				return this[fullName ? 'months' : 'monthsShort'][this.getMonth()];
			};
			Date.prototype.getDayName = function(fullName) {
				return this[fullName ? 'days' : 'daysShort'][this.getDay()];
			};**/
			Date.prototype.addDays = function (n) {
				this.setDate(this.getDate() + n);
				this.tempDate = this.getDate();
			};
			Date.prototype.addMonths = function (n) {
				if (this.tempDate == null) {
					this.tempDate = this.getDate();
				}
				this.setDate(1);
				this.setMonth(this.getMonth() + n);
				this.setDate(Math.min(this.tempDate, this.getMaxDays()));
			};
			Date.prototype.addYears = function (n) {
				if (this.tempDate == null) {
					this.tempDate = this.getDate();
				}
				this.setDate(1);
				this.setFullYear(this.getFullYear() + n);
				this.setDate(Math.min(this.tempDate, this.getMaxDays()));
			};
			Date.prototype.getMaxDays = function() {
				var tmpDate = new Date(Date.parse(this)),
					d = 28, m;
				m = tmpDate.getMonth();
				d = 28;
				while (tmpDate.getMonth() == m) {
					d ++;
					tmpDate.setDate(d);
				}
				return d - 1;
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
		},
		parseDate = function (date, format) {
			if (date.constructor == Date) {
				return new Date(date);
			}
			var parts = date.split(/\W+/);
			var against = format.split(/\W+/), d, m, y, h, min, now = new Date();
			for (var i = 0; i < parts.length; i++) {
				switch (against[i]) {
					case 'd':
					case 'e':
						d = parseInt(parts[i],10);
						break;
					case 'm':
						m = parseInt(parts[i], 10)-1;
						break;
					case 'Y':
					case 'y':
						y = parseInt(parts[i], 10);
						y += y > 100 ? 0 : (y < 29 ? 2000 : 1900);
						break;
					case 'H':
					case 'I':
					case 'k':
					case 'l':
						h = parseInt(parts[i], 10);
						break;
					case 'P':
					case 'p':
						if (/pm/i.test(parts[i]) && h < 12) {
							h += 12;
						} else if (/am/i.test(parts[i]) && h >= 12) {
							h -= 12;
						}
						break;
					case 'M':
						min = parseInt(parts[i], 10);
						break;
				}
			}
			return new Date(
				y === undefined ? now.getFullYear() : y,
				m === undefined ? now.getMonth() : m,
				d === undefined ? now.getDate() : d,
				h === undefined ? now.getHours() : h,
				min === undefined ? now.getMinutes() : min,
				0
			);
		},
		formatDate = function(date, format) {
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
		},
		layout = function (el) {
			var options = $(el).data('datepickerWeek');
			var cal = $('#' + options.id);
			var tbl = cal.find('table:first').get(0);
			var width = tbl.offsetWidth;
			var height = tbl.offsetHeight;
			cal.css({
				width: width + options.extraWidth + 'px',
				height: height + options.extraHeight + 'px'
			}).find('div.datepickerContainers').css({
				width: width + 'px',
				height: height + 'px'
			});
		},
		prepareDate = function (options) {
			var tmp;
			if (options.mode == 'single') {
//				tmp = new Date(options.date);
//				return [formatDate(tmp, options.format), tmp, options.el];
				tmp = options.date;
				return tmp;
			} else {
				tmp = [[],[], options.el];
				$.each(options.date, function(nr, val){
					var date = val;
					tmp[0].push(val);
					tmp[1].push(date);
				});
				return tmp;
			}
		},
		getViewport = function () {
			var m = document.compatMode == 'CSS1Compat';
			return {
				l : window.pageXOffset || (m ? document.documentElement.scrollLeft : document.body.scrollLeft),
				t : window.pageYOffset || (m ? document.documentElement.scrollTop : document.body.scrollTop),
				w : window.innerWidth || (m ? document.documentElement.clientWidth : document.body.clientWidth),
				h : window.innerHeight || (m ? document.documentElement.clientHeight : document.body.clientHeight)
			};
		},
		isChildOf = function(parentEl, el, container) {
			if (parentEl == el) {
				return true;
			}
			if (parentEl.contains) {
				return parentEl.contains(el);
			}
			if ( parentEl.compareDocumentPosition ) {
				return !!(parentEl.compareDocumentPosition(el) & 16);
			}
			var prEl = el.parentNode;
			while(prEl && prEl != container) {
				if (prEl == parentEl)
					return true;
				prEl = prEl.parentNode;
			}
			return false;
		},
		show = function (ev) {
			var cal = $('#' + $(this).data('datepickerId'));
			if (!cal.is(':visible')) {
				var calEl = cal.get(0);
				fill(calEl);
				fillbody(calEl);
				var options = cal.data('datepickerWeek');
				options.onBeforeShow.apply(this, [cal.get(0)]);
				var pos = $(this).offset();
				var viewPort = getViewport();
				var top = pos.top;
				var left = pos.left;
				//var oldDisplay = $.curCSS(calEl, 'display');
				cal.css({
					visibility: 'hidden',
					display: 'block'
				});
				layout(calEl);
				switch (options.position){
					case 'top':
						top -= calEl.offsetHeight;
						break;
					case 'left':
						left -= calEl.offsetWidth;
						break;
					case 'right':
						left += this.offsetWidth;
						break;
					case 'bottom':
						top += this.offsetHeight;
						break;
					case 'bottom_right':
						top += this.offsetHeight;
						left = left - calEl.offsetWidth + this.offsetWidth;
						break;
				}
				if (top + calEl.offsetHeight > viewPort.t + viewPort.h) {
					top = pos.top  - calEl.offsetHeight;
				}
				if (top < viewPort.t) {
					top = pos.top + this.offsetHeight + calEl.offsetHeight;
				}
				if (left + calEl.offsetWidth > viewPort.l + viewPort.w) {
					left = pos.left - calEl.offsetWidth;
				}
				if (left < viewPort.l) {
					left = pos.left + this.offsetWidth
				}
				cal.css({
					visibility: 'visible',
					display: 'block',
					top: top + 'px',
					left: left + 'px'
				});
				if (options.onShow.apply(this, [cal.get(0)]) != false) {
					cal.show();
				}
				$(document).bind('mousedown', {cal: cal, trigger: this}, hide);
			}
			return false;
		},
		hide = function (ev) {
			if (ev.target != ev.data.trigger && !isChildOf(ev.data.cal.get(0), ev.target, ev.data.cal.get(0))) {
				if (ev.data.cal.data('datepickerWeek').onHide.apply(this, [ev.data.cal.get(0)]) != false) {
					ev.data.cal.hide();
					var options = ev.data.cal.data('datepickerWeek');
					options.lastSel = false;
					var id = ev.data.cal.selector;
//					$("div").remove(".datepickerWeek");
//					ev.data.cal.remove();
//					$("div" +id).siblings().remove(".datepickerWeek")
				}
				$(document).unbind('mousedown', hide);
			}
		};
	return {
		init: function(options){
			options = $.extend({}, defaults, options||{});
			extendDate(options.locale);
			options.calendars = Math.max(1, parseInt(options.calendars,10)||1);
			options.mode = /single|multiple|range/.test(options.mode) ? options.mode : 'single';
			return this.each(function(){
				if (!$(this).data('datepickerWeek')) {
					options.el = this;
					if (!options.current) {
						options.current = new Date();
					} else {
						options.current = parseDate(options.current, options.format);
					} 
					options.current.setDate(1);
					options.current.setHours(0,0,0,0);
					var id = 'datepicker_' + parseInt(Math.random() * 1000), cnt;
					options.id = id;
					$(this).data('datepickerId', options.id);
					var cal = $(tpl.wrapper).attr('id', id).bind('click', click).data('datepickerWeek', options);
					if (options.className) {
						cal.addClass(options.className);
					}
					var html = '';
					for (var i = 0; i < options.calendars; i++) {
						cnt = options.starts;
						if (i > 0) {
							html += tpl.space;
						}
						html += tmpls(tpl.head.join(''), null);
					}
					cal
						.find('tr:first').append(html)
							.find('table').addClass(views[options.view]);
					
					if (options.flat) {
						fill(cal.get(0));
						cal.appendTo(this).show().css('position', 'relative');
						fillbody(cal.get(0));
						layout(cal.get(0));
					} else {
						cal.appendTo(document.body);
						$(this).bind(options.eventName, show);
//						show(options);
					}
					
				}
			});
		},
		showPicker: function() {
			return this.each( function () {
				if ($(this).data('datepickerId')) {
					show.apply(this);
				}
			});
		},
		hidePicker: function() {
			return this.each( function () {
				if ($(this).data('datepickerId')) {
					$('#' + $(this).data('datepickerId')).hide();
				}
			});
		},
		setDate: function(date, shiftTo){
			return this.each(function(){
				if ($(this).data('datepickerId')) {
					var cal = $('#' + $(this).data('datepickerId'));
					var options = cal.data('datepickerWeek');
					options.date = date;
					if (options.date.constructor == String) {
						options.date = parseDate(options.date, options.format);
						options.date.setHours(0,0,0,0);
					}
					if (options.mode != 'single') {
						if (options.date.constructor != Array) {
							options.date = [options.date.valueOf()];
							if (options.mode == 'range') {
								options.date.push(((new Date(options.date[0])).setHours(23,59,59,0)).valueOf());
							}
						} else {
							for (var i = 0; i < options.date.length; i++) {
								options.date[i] = (parseDate(options.date[i], options.format).setHours(0,0,0,0)).valueOf();
							}
							if (options.mode == 'range') {
								options.date[1] = ((new Date(options.date[1])).setHours(23,59,59,0)).valueOf();
							}
						}
					} else {
						options.date = options.date.valueOf();
					}
					if (shiftTo) {
						options.current = new Date (options.mode != 'single' ? options.date[0] : options.date);
					}
					fill(cal.get(0));
				}
			});
		},
		getDate: function(formated) {
			if (this.size() > 0) {
				return prepareDate($('#' + $(this).data('datepickerId')).data('datepickerWeek'))[formated ? 0 : 1];
			}
		},
		clear: function(){
			return this.each(function(){
				if ($(this).data('datepickerId')) {
					var cal = $('#' + $(this).data('datepickerId'));
					var options = cal.data('datepickerWeek');
					if (options.mode != 'single') {
						options.date = [];
						fill(cal.get(0));
					}
				}
			});
		},
		fixLayout: function(){
			return this.each(function(){
				if ($(this).data('datepickerId')) {
					var cal = $('#' + $(this).data('datepickerId'));
					var options = cal.data('datepickerWeek');
					if (options.flat) {
						layout(cal.get(0));
					}
				}
			});
		}
	};
}();
$.fn.extend({
	WeekPicker: WeekPicker.init,
	WeekPickerHide: WeekPicker.hidePicker,
	WeekPickerShow: WeekPicker.showPicker,
	WeekPickerSetDate: WeekPicker.setDate,
	WeekPickerGetDate: WeekPicker.getDate,
	WeekPickerClear: WeekPicker.clear,
	WeekPickerLayout: WeekPicker.fixLayout
});
})(jQuery);


(function(){
//  var cache = {};
 
  this.tmpls = function tmpls(str, data){
    // Figure out if we're getting a template, or if we need to
    // load the template - and be sure to cache the result.
	  return obj(str);
//    var fn = !/\W/.test(str) ?
//      cache[str] = cache[str] ||
//        tmpl(document.getElementById(str).innerHTML) : obj(str);
     
      // Generate a reusable function that will serve as a template
      // generator (and which will be cached).
//      new Function("obj",
//        "var p=[],print=function(){p.push.apply(p,arguments);};" +
//       
//        // Introduce the data as local variables using with(){}
//        "with(obj){p.push('" +
//       
//        // Convert the template into pure JavaScript
//        str
//          .replace(/[\r\t\n]/g, " ")
//          .split("<%").join("\t")
//          .replace(/((^|%>)[^\t]*)'/g, "$1\r")
//          .replace(/\t=(.*?)%>/g, "',$1,'")
//          .split("\t").join("');")
//          .split("%>").join("p.push('")
//          .split("\r").join("\\'")
//      + "');}return p.join('');");
   
    // Provide some basic currying to the user
//    return data ? fn( data ) : fn;
  };
  //将上面方法中重新new function()抽出组成一个方法，否则ie调用会直接显示为匿名函数显示到页面上
  function obj(str){
	var p=[],print=function(){
		p.push.apply(p,arguments);
	};
	with(obj){
		p.push( str.replace(/[\r\t\n]/g, " ").split("<%").join("\t").replace(/((^|%>)[^\t]*)'/g, "$1\r")
          .replace(/\t=(.*?)%>/g, "',$1,'")
          .split("\t").join("');")
          .split("%>").join("p.push('")
          .split("\r").join("\\'")
		);
      }
      return p.join('');
  }
})();