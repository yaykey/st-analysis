/**
 *
 * 
 */
(function ($) {
	var DatePicker = function () {
		var	ids = {},
			views = {
				years: 'datepickerViewYears',
				moths: 'datepickerViewMonths',
				days: 'datepickerViewDays'
			},
			tpl = {
				wrapper: '<div class="datepicker"><div class="datepickerContainer"><table cellspacing="0" cellpadding="0"><tbody><tr></tr></tbody></table></div></div>',
				head: [
					'<td>',
					'<table cellspacing="0" cellpadding="0">',
						'<thead>',
							'<tr>',
								'<th class="datepickerGoPrev"><a href="#"><span><%=prev%></span></a></th>',
								'<th colspan="6" class="datepickerMonth"><a href="#"><span></span></a></th>',
								'<th class="datepickerGoNext"><a href="#"><span><%=next%></span></a></th>',
							'</tr>',
							'<tr class="datepickerDoW">',
							'<th><span><%=week%></span></th>',
							'<th><span><%=day1%></span></th>',
							'<th><span><%=day2%></span></th>',
							'<th><span><%=day3%></span></th>',
							'<th><span><%=day4%></span></th>',
							'<th><span><%=day5%></span></th>',
							'<th><span><%=day6%></span></th>',
							'<th><span><%=day7%></span></th>',
						'</tr>',
						'</thead>',
					'</table></td>'
				],
				space : '<td class="datepickerSpace"><div></div></td>',
				days: [
					'<tbody class="datepickerDays">',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[0].week%></span></th>',
							'<td class="<%=weeks[0].days[0].classname%>"><a href="#"><span><%=weeks[0].days[0].text%></span></a></td>',
							'<td class="<%=weeks[0].days[1].classname%>"><a href="#"><span><%=weeks[0].days[1].text%></span></a></td>',
							'<td class="<%=weeks[0].days[2].classname%>"><a href="#"><span><%=weeks[0].days[2].text%></span></a></td>',
							'<td class="<%=weeks[0].days[3].classname%>"><a href="#"><span><%=weeks[0].days[3].text%></span></a></td>',
							'<td class="<%=weeks[0].days[4].classname%>"><a href="#"><span><%=weeks[0].days[4].text%></span></a></td>',
							'<td class="<%=weeks[0].days[5].classname%>"><a href="#"><span><%=weeks[0].days[5].text%></span></a></td>',
							'<td class="<%=weeks[0].days[6].classname%>"><a href="#"><span><%=weeks[0].days[6].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[1].week%></span></th>',
							'<td class="<%=weeks[1].days[0].classname%>"><a href="#"><span><%=weeks[1].days[0].text%></span></a></td>',
							'<td class="<%=weeks[1].days[1].classname%>"><a href="#"><span><%=weeks[1].days[1].text%></span></a></td>',
							'<td class="<%=weeks[1].days[2].classname%>"><a href="#"><span><%=weeks[1].days[2].text%></span></a></td>',
							'<td class="<%=weeks[1].days[3].classname%>"><a href="#"><span><%=weeks[1].days[3].text%></span></a></td>',
							'<td class="<%=weeks[1].days[4].classname%>"><a href="#"><span><%=weeks[1].days[4].text%></span></a></td>',
							'<td class="<%=weeks[1].days[5].classname%>"><a href="#"><span><%=weeks[1].days[5].text%></span></a></td>',
							'<td class="<%=weeks[1].days[6].classname%>"><a href="#"><span><%=weeks[1].days[6].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[2].week%></span></th>',
							'<td class="<%=weeks[2].days[0].classname%>"><a href="#"><span><%=weeks[2].days[0].text%></span></a></td>',
							'<td class="<%=weeks[2].days[1].classname%>"><a href="#"><span><%=weeks[2].days[1].text%></span></a></td>',
							'<td class="<%=weeks[2].days[2].classname%>"><a href="#"><span><%=weeks[2].days[2].text%></span></a></td>',
							'<td class="<%=weeks[2].days[3].classname%>"><a href="#"><span><%=weeks[2].days[3].text%></span></a></td>',
							'<td class="<%=weeks[2].days[4].classname%>"><a href="#"><span><%=weeks[2].days[4].text%></span></a></td>',
							'<td class="<%=weeks[2].days[5].classname%>"><a href="#"><span><%=weeks[2].days[5].text%></span></a></td>',
							'<td class="<%=weeks[2].days[6].classname%>"><a href="#"><span><%=weeks[2].days[6].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[3].week%></span></th>',
							'<td class="<%=weeks[3].days[0].classname%>"><a href="#"><span><%=weeks[3].days[0].text%></span></a></td>',
							'<td class="<%=weeks[3].days[1].classname%>"><a href="#"><span><%=weeks[3].days[1].text%></span></a></td>',
							'<td class="<%=weeks[3].days[2].classname%>"><a href="#"><span><%=weeks[3].days[2].text%></span></a></td>',
							'<td class="<%=weeks[3].days[3].classname%>"><a href="#"><span><%=weeks[3].days[3].text%></span></a></td>',
							'<td class="<%=weeks[3].days[4].classname%>"><a href="#"><span><%=weeks[3].days[4].text%></span></a></td>',
							'<td class="<%=weeks[3].days[5].classname%>"><a href="#"><span><%=weeks[3].days[5].text%></span></a></td>',
							'<td class="<%=weeks[3].days[6].classname%>"><a href="#"><span><%=weeks[3].days[6].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[4].week%></span></th>',
							'<td class="<%=weeks[4].days[0].classname%>"><a href="#"><span><%=weeks[4].days[0].text%></span></a></td>',
							'<td class="<%=weeks[4].days[1].classname%>"><a href="#"><span><%=weeks[4].days[1].text%></span></a></td>',
							'<td class="<%=weeks[4].days[2].classname%>"><a href="#"><span><%=weeks[4].days[2].text%></span></a></td>',
							'<td class="<%=weeks[4].days[3].classname%>"><a href="#"><span><%=weeks[4].days[3].text%></span></a></td>',
							'<td class="<%=weeks[4].days[4].classname%>"><a href="#"><span><%=weeks[4].days[4].text%></span></a></td>',
							'<td class="<%=weeks[4].days[5].classname%>"><a href="#"><span><%=weeks[4].days[5].text%></span></a></td>',
							'<td class="<%=weeks[4].days[6].classname%>"><a href="#"><span><%=weeks[4].days[6].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<th class="datepickerWeek"><span><%=weeks[5].week%></span></th>',
							'<td class="<%=weeks[5].days[0].classname%>"><a href="#"><span><%=weeks[5].days[0].text%></span></a></td>',
							'<td class="<%=weeks[5].days[1].classname%>"><a href="#"><span><%=weeks[5].days[1].text%></span></a></td>',
							'<td class="<%=weeks[5].days[2].classname%>"><a href="#"><span><%=weeks[5].days[2].text%></span></a></td>',
							'<td class="<%=weeks[5].days[3].classname%>"><a href="#"><span><%=weeks[5].days[3].text%></span></a></td>',
							'<td class="<%=weeks[5].days[4].classname%>"><a href="#"><span><%=weeks[5].days[4].text%></span></a></td>',
							'<td class="<%=weeks[5].days[5].classname%>"><a href="#"><span><%=weeks[5].days[5].text%></span></a></td>',
							'<td class="<%=weeks[5].days[6].classname%>"><a href="#"><span><%=weeks[5].days[6].text%></span></a></td>',
						'</tr>',
					'</tbody>'
				],
				months: [
					'<tbody class="<%=className%>">',
						'<tr>',
							'<td colspan="2" class="<%=moths[0].classname%>"><a href="#"><span><%=moths[0].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[1].classname%>"><a href="#"><span><%=moths[1].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[2].classname%>"><a href="#"><span><%=moths[2].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[3].classname%>"><a href="#"><span><%=moths[3].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<td colspan="2" class="<%=moths[4].classname%>"><a href="#"><span><%=moths[4].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[5].classname%>"><a href="#"><span><%=moths[5].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[6].classname%>"><a href="#"><span><%=moths[6].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[7].classname%>"><a href="#"><span><%=moths[7].text%></span></a></td>',
						'</tr>',
						'<tr>',
							'<td colspan="2" class="<%=moths[8].classname%>"><a href="#"><span><%=moths[8].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[9].classname%>"><a href="#"><span><%=moths[9].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[10].classname%>"><a href="#"><span><%=moths[10].text%></span></a></td>',
							'<td colspan="2" class="<%=moths[11].classname%>"><a href="#"><span><%=moths[11].text%></span></a></td>',
						'</tr>',
					'</tbody>'
				],
				years: [
					'<tbody class="<%=className%>">',
						'<tr>',
							'<td colspan="2"><a href="#"><span><%=data[0]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[1]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[2]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[3]%></span></a></td>',
						'</tr>',
						'<tr>',
							'<td colspan="2"><a href="#"><span><%=data[4]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[5]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[6]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[7]%></span></a></td>',
						'</tr>',
						'<tr>',
							'<td colspan="2"><a href="#"><span><%=data[8]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[9]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[10]%></span></a></td>',
							'<td colspan="2"><a href="#"><span><%=data[11]%></span></a></td>',
						'</tr>',
					'</tbody>'
				],
				time:[
				      '<tr class="start time">',
				      	'<td colspan="3">开始时间</td>',
				      	'<td colspan="2">',
				      		'<select class="hour">',
				      			'<option value="00">00时</option>',
				      			'<option value="01">01时</option>',
				      			'<option value="02">02时</option>',
				      			'<option value="03">03时</option>',
				      			'<option value="04">04时</option>',
				      			'<option value="05">05时</option>',
				      			'<option value="06">06时</option>',
				      			'<option value="07">07时</option>',
				      			'<option value="08">08时</option>',
				      			'<option value="09">09时</option>',
				      			'<option value="10">10时</option>',
				      			'<option value="11">11时</option>',
				      			'<option value="12">12时</option>',
				      			'<option value="13">13时</option>',
				      			'<option value="14">14时</option>',
				      			'<option value="15">15时</option>',
				      			'<option value="16">16时</option>',
				      			'<option value="17">17时</option>',
				      			'<option value="18">18时</option>',
				      			'<option value="19">19时</option>',
				      			'<option value="20">20时</option>',
				      			'<option value="21">21时</option>',
				      			'<option value="22">22时</option>',
				      			'<option value="23">23时</option>',
				      		'</select>',
				      	'</td>',
				      	'<td colspan="2">',
					      	'<select class="minute">',
				      			'<option value="00">00分</option>',
				      			'<option value="01">01分</option>',
				      			'<option value="02">02分</option>',
				      			'<option value="03">03分</option>',
				      			'<option value="04">04分</option>',
				      			'<option value="05">05分</option>',
				      			'<option value="06">06分</option>',
				      			'<option value="07">07分</option>',
				      			'<option value="08">08分</option>',
				      			'<option value="09">09分</option>',
				      			'<option value="10">10分</option>',
				      			'<option value="11">11分</option>',
				      			'<option value="12">12分</option>',
				      			'<option value="13">13分</option>',
				      			'<option value="14">14分</option>',
				      			'<option value="15">15分</option>',
				      			'<option value="16">16分</option>',
				      			'<option value="17">17分</option>',
				      			'<option value="18">18分</option>',
				      			'<option value="19">19分</option>',
				      			'<option value="20">20分</option>',
				      			'<option value="21">21分</option>',
				      			'<option value="22">22分</option>',
				      			'<option value="23">23分</option>',
				      			'<option value="24">24分</option>',
				      			'<option value="25">25分</option>',
				      			'<option value="26">26分</option>',
				      			'<option value="27">27分</option>',
				      			'<option value="28">28分</option>',
				      			'<option value="29">29分</option>',
				      			'<option value="30">30分</option>',
				      			'<option value="31">31分</option>',
				      			'<option value="32">32分</option>',
				      			'<option value="33">33分</option>',
				      			'<option value="34">34分</option>',
				      			'<option value="35">35分</option>',
				      			'<option value="36">36分</option>',
				      			'<option value="37">37分</option>',
				      			'<option value="38">38分</option>',
				      			'<option value="39">39分</option>',
				      			'<option value="40">40分</option>',
				      			'<option value="41">41分</option>',
				      			'<option value="42">42分</option>',
				      			'<option value="43">43分</option>',
				      			'<option value="44">44分</option>',
				      			'<option value="45">45分</option>',
				      			'<option value="46">46分</option>',
				      			'<option value="47">47分</option>',
				      			'<option value="48">48分</option>',
				      			'<option value="49">49分</option>',
				      			'<option value="50">50分</option>',
				      			'<option value="51">51分</option>',
				      			'<option value="52">52分</option>',
				      			'<option value="53">53分</option>',
				      			'<option value="54">54分</option>',
				      			'<option value="55">55分</option>',
				      			'<option value="56">56分</option>',
				      			'<option value="57">57分</option>',
				      			'<option value="58">58分</option>',
				      			'<option value="59">59分</option>',
			      			'</select>',
				      	'</td>',
				      '</tr><tr class="end time">',
				      	'<td colspan="3">结束时间</td>',
				      	'<td colspan="2">',
				      		'<select class="hour">',
				      		'<option value="00">00时</option>',
			      			'<option value="01">01时</option>',
			      			'<option value="02">02时</option>',
			      			'<option value="03">03时</option>',
			      			'<option value="04">04时</option>',
			      			'<option value="05">05时</option>',
			      			'<option value="06">06时</option>',
			      			'<option value="07">07时</option>',
			      			'<option value="08">08时</option>',
			      			'<option value="09">09时</option>',
			      			'<option value="10">10时</option>',
			      			'<option value="11">11时</option>',
			      			'<option value="12">12时</option>',
			      			'<option value="13">13时</option>',
			      			'<option value="14">14时</option>',
			      			'<option value="15">15时</option>',
			      			'<option value="16">16时</option>',
			      			'<option value="17">17时</option>',
			      			'<option value="18">18时</option>',
			      			'<option value="19">19时</option>',
			      			'<option value="20">20时</option>',
			      			'<option value="21">21时</option>',
			      			'<option value="22">22时</option>',
			      			'<option value="23" selected="selected">23时</option>',
				      		'</select>',
				      	'</td>',
				      	'<td colspan="2">',
					      	'<select class="minute">',
					      	'<option value="00">00分</option>',
			      			'<option value="01">01分</option>',
			      			'<option value="02">02分</option>',
			      			'<option value="03">03分</option>',
			      			'<option value="04">04分</option>',
			      			'<option value="05">05分</option>',
			      			'<option value="06">06分</option>',
			      			'<option value="07">07分</option>',
			      			'<option value="08">08分</option>',
			      			'<option value="09">09分</option>',
			      			'<option value="10">10分</option>',
			      			'<option value="11">11分</option>',
			      			'<option value="12">12分</option>',
			      			'<option value="13">13分</option>',
			      			'<option value="14">14分</option>',
			      			'<option value="15">15分</option>',
			      			'<option value="16">16分</option>',
			      			'<option value="17">17分</option>',
			      			'<option value="18">18分</option>',
			      			'<option value="19">19分</option>',
			      			'<option value="20">20分</option>',
			      			'<option value="21">21分</option>',
			      			'<option value="22">22分</option>',
			      			'<option value="23">23分</option>',
			      			'<option value="24">24分</option>',
			      			'<option value="25">25分</option>',
			      			'<option value="26">26分</option>',
			      			'<option value="27">27分</option>',
			      			'<option value="28">28分</option>',
			      			'<option value="29">29分</option>',
			      			'<option value="30">30分</option>',
			      			'<option value="31">31分</option>',
			      			'<option value="32">32分</option>',
			      			'<option value="33">33分</option>',
			      			'<option value="34">34分</option>',
			      			'<option value="35">35分</option>',
			      			'<option value="36">36分</option>',
			      			'<option value="37">37分</option>',
			      			'<option value="38">38分</option>',
			      			'<option value="39">39分</option>',
			      			'<option value="40">40分</option>',
			      			'<option value="41">41分</option>',
			      			'<option value="42">42分</option>',
			      			'<option value="43">43分</option>',
			      			'<option value="44">44分</option>',
			      			'<option value="45">45分</option>',
			      			'<option value="46">46分</option>',
			      			'<option value="47">47分</option>',
			      			'<option value="48">48分</option>',
			      			'<option value="49">49分</option>',
			      			'<option value="50">50分</option>',
			      			'<option value="51">51分</option>',
			      			'<option value="52">52分</option>',
			      			'<option value="53">53分</option>',
			      			'<option value="54">54分</option>',
			      			'<option value="55">55分</option>',
			      			'<option value="56">56分</option>',
			      			'<option value="57">57分</option>',
			      			'<option value="58">58分</option>',
			      			'<option value="59" selected="selected">59分</option>',
			      			'</select>',
				      	'</td>',
				      '</tr>',
				      '<tr class="time"><td colspan="8" style="text-align:right"><a id="sub" href="#">确定</a></td></tr>'
				]
			},
			defaults = {
				flat: false,
				starts: 1,
				maxDay:"",					//区选的时候，最多选择天数，默认空则不控制
				prev: '&#9664;',
				next: '&#9654;',
				lastSel: false,
				mode: 'single',
				view: 'days',
				time: false,				//选择时间是否显示
				areaToHide: true,			//选完区域后隐藏
				calendars: 1,
				linkage: false,				//是否联动，默认不联动
				format: 'Y-m-d',
				position: 'bottom',
				eventName: 'click',
				dateArr: [],
				curDate: null,		//存储currentDate
				onRender: function(){return {};},
				onChange: function(){return true;},
				onShow: function(){return true;},
				onBeforeShow: function(){return true;},
				onHide: function(){return true;},
				onSub: function(){return true},
				locale: {
					days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
					daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
					daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
					months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
					monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
					weekMin: '周'
				}
			},
			fill = function(el, index, dataArr) {
				var options = $(el).data('datepicker');
				var cal = $(el);
				//返回值为小于等于其数值参数的最大整数值。Math.floor(options.calendars/2)
				var currentCal = Math.floor(options.calendars/2), date, data, dow, month, cnt = 0, week, days, indic, 
				indic2, html, tblCal;
				cal.find('td>table tbody').remove();
				
				switch (options.view) {
					case 'days':
						//delete tpl.months ;
						break;
					case 'moths':
						//delete tpl.days;
						break;
				}
				for (var i = 0; i < options.calendars; i++) {
					if(!options.linkage && index == undefined && options.curDate == null){
						options.curDate = new Date(options.current);
						date = new Date(options.current);
					}else if(index == undefined && options.curDate != null){
						date = new Date(options.curDate);
					}else{
						date = new Date(options.current);
					}
					
					switch (options.view) {
						case 'days':
							if(dataArr==null && !options.linkage){
								date.addMonths(-currentCal + i);
								var mdate = new Date(date);
								options.dateArr[i] = mdate;
							}else if(index != undefined){
								date = new Date(dataArr[i]);
							}else{
								date.addMonths(-currentCal + i);
							}
							break;
						case 'moths':
							if(dataArr==null && !options.linkage){
								date.addYears(-currentCal + i);
								var mdate = new Date(date);
								options.dateArr[i] = mdate;
							}else if(index != undefined){
								date = new Date(dataArr[i]);
							}else{
								date.addYears(-currentCal + i);
							}
							break;
					}
					
					tblCal = cal.find('table').eq(i+1);
					tblCal.attr('id', 'datepicker_'+ i);
					switch (tblCal[0].className) {
						case 'datepickerViewDays':
							dow = formatDate(date, 'B, Y');
							break;
						case 'datepickerViewMonths':
							switch (options.view){
								case 'days':
									dow = date.getFullYear();
									break;
								case 'moths':
									dow = formatDate(date, 'Y');
									break;
								default :
									break;
							}
							break;
						case 'datepickerViewYears':
							dow = (date.getFullYear()-6) + ' - ' + (date.getFullYear()+5);
							break;
					} 
					tblCal.find('thead tr:first th:eq(1) span').text(dow);
					dow = date.getFullYear()-6;
					data = {
						data: [],
						className: 'datepickerYears'
					}
					//此处12的来由是填充年份，一共是3行4列，故得12
					for ( var j = 0; j < 12; j++) {
						data.data.push(dow + j);
					}
					html = tmpl(tpl.years.join(''), data);
					
					switch (options.view) {
						case 'days':
							//如果是日期模式，则设置date的日期为1号开始
							date.setDate(1);
							data = {weeks:[], test: 10};
							month = date.getMonth();
							//date.getDay()获取一周中的某一天（0-6）
							var dow = (date.getDay() - options.starts) % 7;
							date.addDays(-(dow + (dow < 0 ? 7 : 0)));
							week = -1;
							cnt = 0;
							//42的由来：除掉周的那一列，一共是6行7列，6*7=42，然后在算出的日期中判断是否是本月还是上月下月
							while (cnt < 42) {
								indic = parseInt(cnt/7,10);
								indic2 = cnt%7;
								if (!data.weeks[indic]) {
									week = date.getWeekNumber();
									data.weeks[indic] = {
										week: week,
										days: []
									};
								}
								data.weeks[indic].days[indic2] = {
									text: date.getDate(),
									classname: []
								};
								if (month != date.getMonth()) {
									data.weeks[indic].days[indic2].classname.push('datepickerNotInMonth');
								}
								if (date.getDay() == 0) {
									data.weeks[indic].days[indic2].classname.push('datepickerSunday');
								}
								if (date.getDay() == 6) {
									data.weeks[indic].days[indic2].classname.push('datepickerSaturday');
								}
								var fromUser = options.onRender(date);
								var val = date.valueOf();
								if (fromUser.selected || options.date == val || $.inArray(val, options.date) > -1 || (options.mode == 'range' && val >= options.date[0] && val <= options.date[1])) {
									data.weeks[indic].days[indic2].classname.push('datepickerSelected');
								}
								if (fromUser.disabled) {
									data.weeks[indic].days[indic2].classname.push('datepickerDisabled');
								}
								if (fromUser.className) {
									data.weeks[indic].days[indic2].classname.push(fromUser.className);
								}
								data.weeks[indic].days[indic2].classname = data.weeks[indic].days[indic2].classname.join(' ');
								cnt++;
								date.addDays(1);
							}
							html = tmpl(tpl.days.join(''), data) + html;
							if(options.time && options.calendars==1 && tblCal[0].className=='datepickerViewDays'){
								data = {
										data: null,
										className:'datepickerDays'
								};
								html += tmpl(tpl.time.join(''), data);
							}
							data = {
								data: options.locale.monthsShort,
								className: 'datepickerMonths'
							};
							html = tmpl(tpl.years.join(''), data) + html;
							tblCal.append(html);
							break;
						case 'moths':
							//如果是月份模式，则设置开始的月份为1月
							date.setMonth(0);
							date.setDate(1);
							data = {moths:[], className: 'datepickerMonths'};
							cnt = 0;
							//12个月份
							while(cnt < 12) {
								data.moths[cnt] = {
									text: options.locale.monthsShort[cnt],
									classname: []
								};
								/**
								 * var fromUser = options.onRender(date);
								 *	date.getMonth() 返回0-11中的数值
								 */
								var fromUser = options.onRender(date);
								var val = date.valueOf();
								var month = date.getMonth() + 1;
								/**
								 * //确定第一个参数在数组中的位置(如果没有找到则返回 -1 )
								 */
								if (fromUser.selected || options.date == val || $.inArray(val, options.date) > -1 || (options.mode == 'range' && val >= options.date[0]&& val <= options.date[1])) {
									data.moths[cnt].classname.push('datepickerMonthSeleted');
								}else if (fromUser.disabled) {
									data.moths[cnt].classname.push('datepickerDisabled');
								}else if (fromUser.className) {
									data.moths[cnt].classname.push(fromUser.className);
								}else {
									data.moths[cnt].classname.push('');
								}
								data.moths[cnt].classname = data.moths[cnt].classname.join(' ');
								cnt++;
								date.addMonths(1)
							}
							//html = tmpl(tpl.days.join(''), data) + html;
							//data = {
							//	data: options.locale.monthsShort,
							//	className: 'datepickerMonths'
							//};
							html = tmpl(tpl.months.join(''), data) + html;
							tblCal.append(html);
							break;
					}
				}
			},
			click = function(ev) {
				if ($(ev.target).is('span')) {
					ev.target = ev.target.parentNode;
				}
				var el = $(ev.target);
				var achanged = false;				
				if (el.is('a') && !el.is('a#sub')) {
					ev.target.blur();
					if (el.hasClass('datepickerDisabled')) {
						return false;
					}
					var options = $(this).data('datepicker');
					var parentEl = el.parent();
					var tblEl = parentEl.parent().parent().parent();
					var tblIndex = $('table', this).index(tblEl.get(0)) - 1;
					var tmp ;
					if(!options.linkage){
						tmp = new Date(options.dateArr[tblIndex]);
						options.current = new Date(tmp);
					}else{
						tmp = new Date(options.current);
					}
					var changed = false;
					var fillIt = false;
					var close = true;
					var index = -1;	//点击第index个日历  0开始 -1为无操作
					if (parentEl.is('th')) {
						if (parentEl.hasClass('datepickerWeek') && options.mode == 'range' && !parentEl.next().hasClass('datepickerDisabled')) {
							var val = parseInt(parentEl.next().text(), 10);
							if(!options.linkage){
								tmp.addMonths(tblIndex - Math.floor(options.calendars/2));
							}
							if (parentEl.next().hasClass('datepickerNotInMonth')) {
								tmp.addMonths(val > 15 ? -1 : 1);
							}
							tmp.setDate(val);
							options.date[0] = (tmp.setHours(0,0,0,0)).valueOf();
							tmp.setHours(23,59,59,0);
//							tmp.addDays(6);
							for(var i=0; i<6; i++){
								if(tmp.valueOf()<new Date(options.current).valueOf()){
									tmp.addDays(1);
								}
							}
							options.date[1] = tmp.valueOf();
							fillIt = true;
							changed = true;
							options.lastSel = false;
							close = false;
						} else if (parentEl.hasClass('datepickerMonth')) {
							if(options.linkage){
								tmp.addMonths(tblIndex - Math.floor(options.calendars/2));
							}
							switch (tblEl.get(0).className) {
								case 'datepickerViewDays':
									tblEl.get(0).className = 'datepickerViewMonths';
									el.find('span').text(tmp.getFullYear());
									if(options.time){
										$("tr.time").css({"display":"none"});
									}
									break;
								case 'datepickerViewMonths':
									switch (options.view) {
										case 'days':
											tblEl.get(0).className = 'datepickerViewYears';
											el.find('span').text((tmp.getFullYear()-6) + ' - ' + (tmp.getFullYear()+5));
											if(options.time){
												$("tr.time").css({"display":"none"});
											}
											break;
										case 'moths':
											tmp.addYears(tblIndex - Math.floor(options.calendars/2));
											tblEl.get(0).className = 'datepickerViewYears';
											el.find('span').text((tmp.getFullYear()-6) + ' - ' + (tmp.getFullYear()+5));
											break;
									}
									break;
								case 'datepickerViewYears':
									switch (options.view) {
										case 'days':
											tblEl.get(0).className = 'datepickerViewDays';
											el.find('span').text(formatDate(tmp, 'B, Y'));
											if(options.time){
												$("tr.time").css({"display":""});
											}
											break;
										case 'moths':
											tblEl.get(0).className = 'datepickerViewMonths';
											el.find('span').text(tmp.getFullYear());
											if(options.time){
												$("tr.time").css({"display":"none"});
											}
											break;
									}
									break;
							}
						} else if (parentEl.parent().parent().is('thead')) {
							switch (tblEl.get(0).className) {
								case 'datepickerViewDays':
									options.current.addMonths(parentEl.hasClass('datepickerGoPrev') ? -1 : 1);
									if(!options.linkage){
										var id = parentEl.parent().parent().parent().attr("id").split("_");
										index = id[1];
										options.dateArr[index] = new Date(options.current);
									}
									break;
								case 'datepickerViewMonths':
									options.current.addYears(parentEl.hasClass('datepickerGoPrev') ? -1 : 1);
									if(!options.linkage){
										var id = parentEl.parent().parent().parent().attr("id").split("_");
										index = id[1];
										options.dateArr[index] = new Date(options.current);;
									}
									break;
								case 'datepickerViewYears':
									options.current.addYears(parentEl.hasClass('datepickerGoPrev') ? -12 : 12);
									if(!options.linkage){
										var id = parentEl.parent().parent().parent().attr("id").split("_");
										index = id[1];
										options.dateArr[index] = new Date(options.current);;
									}
									break;
							}
							
							fillIt = true;
							close = true;
						}
					} else if (parentEl.is('td') && !parentEl.hasClass('datepickerDisabled')) {
						switch (tblEl.get(0).className) {
							 case 'datepickerViewMonths':
								 switch(options.view){
										case 'days':
											options.current.setMonth(tblEl.find('tbody.datepickerMonths td').index(parentEl));
											options.current.setFullYear(parseInt(tblEl.find('thead th.datepickerMonth span').text(), 10));
											if(options.linkage){
												options.current.addMonths(Math.floor(options.calendars/2) - tblIndex);
											}
											tblEl.get(0).className = 'datepickerViewDays';
											if(options.time){
												$("tr.time").css({"display":"none"});
											}
											if(!options.linkage){
												var id = parentEl.parent().parent().parent().attr("id").split("_");
												index = id[1];
												options.dateArr[index] = new Date(options.current);;
											}
											break;
										case 'moths':
											changed = true;
											options.current.setMonth(tblEl.find('tbody.datepickerMonths td').index(parentEl));
											//options.current.setFullYear(parseInt(tblEl.find('thead th.datepickerMonth span').text(), 10));
											//options.current.addMonths(Math.floor(options.calendars/2) - tblIndex);
											//var val = el.text();
											//此处必须重新赋值,否则为原来调用日期插件传入的date
											tmp = new Date(options.current);
											if(options.linkage){
												tmp.addYears(tblIndex - Math.floor(options.calendars/2));
											}
											if(!options.linkage){
												var id = parentEl.parent().parent().parent().attr("id").split("_");
												index = id[1];
												options.dateArr[index] = new Date(options.current);;
											}
											tmp.setDate(1);
											switch (options.mode) {
												case 'multiple':
													//val = (tmp.setHours(0,0,0,0)).valueOf();
													//设置日期的起始时间（从1号开始）
													val= tmp.setDate(1);
													if ($.inArray(val, options.date) > -1) {
														$.each(options.date, function(nr, dat){
															if (dat == val) {
																//删除
																options.date.splice(nr,1);
																return false;
															}
														});
													} else {
														options.date.push(val);
													}
													break;
												case 'range':
													if (!options.lastSel) {
														options.date[0] = tmp.setDate(1);
													}
													val = tmp.setDate(options.current.getMaxDays());
													 if (val < options.date[0]) {
														 options.date[1] = new Date(options.date[0]).setDate(new Date(options.date[0]).getMaxDays());
														 options.date[0] = new Date(val).setDate(1);
													 } else {
														options.date[1] = val;
													 }

													options.lastSel = !options.lastSel;
													close = options.lastSel;
													break;
												default:
													options.date = tmp.valueOf();
													close = false;
													break;
											}
											break;
											
								 };
								 break;
							case 'datepickerViewYears':
								if(!options.linkage){
									var id = parentEl.parent().parent().parent().attr("id").split("_");
									index = id[1];
									options.current.setFullYear(parseInt(el.text(), 10));
									options.dateArr[index] = new Date(options.current);;
								}else{
									options.current.setFullYear(parseInt(el.text(), 10));
								}
								tblEl.get(0).className = 'datepickerViewMonths';
								if(options.time){
									$("tr.time").css({"display":"none"});
								}
								break;
							default:
								if(!options.linkage){
									var id = tblEl.attr("id").split("_");
									index = id[1];
								}
								
								changed = true;
								var maxVal;
								//parseInt(el.text(), 10)将el.text()的字符串转化成10进制的整数
								var val = parseInt(el.text(), 10);
								if(options.linkage){
									tmp.addMonths(tblIndex - Math.floor(options.calendars/2));
								}
								if (parentEl.hasClass('datepickerNotInMonth')) {
									tmp.addMonths(val > 15 ? -1 : 1);
								}
								tmp.setDate(val);
								switch (options.mode) {
									case 'multiple':
										val = (tmp.setHours(0,0,0,0)).valueOf();
										if ($.inArray(val, options.date) > -1) {
											$.each(options.date, function(nr, dat){
												if (dat == val) {
													//删除（在nr的位置删除一项）
													options.date.splice(nr,1);
													return false;
												}
											});
										} else {
											options.date.push(val);
										}
										break;
									case 'range':
										if (!options.lastSel) {
											options.date[0] = (tmp.setHours(0,0,0,0)).valueOf();
										}
										val = (tmp.setHours(23,59,59,0)).valueOf();
										//86399000 是一天的毫秒数-1，即86400000（60*60*1000*24）-1000
										if(options.maxDay != ""){
											maxVal = 86399000 * options.maxDay;
										}
										
										if (val < options.date[0]) {
											var t1 = options.date[0] + 86399000;
											var t0 = val - 86399000;
											if(options.maxDay != ""){
												if(t1 - t0 > maxVal){
													t1 = options.date[0] + 86399000;
													t0 = t1 - maxVal;
												}
												options.date[1] = t1;
												options.date[0] = t0;
											}else{
												options.date[1] = t1;
												options.date[0] = t0;
											}
											
										} else {
											if(options.maxDay != ""){
												var t0 = options.date[0];
												var t1 = val;
												if(t1 - t0 > maxVal){
													t0 = options.date[0];
													t1 = t0 + maxVal;
												}
												options.date[1] = t1;
												options.date[0] = t0;
											}else{
												options.date[1] = val;
											}
											
										}
										options.lastSel = !options.lastSel;
										close = options.lastSel;
										break;
									default:
										options.date = tmp.valueOf();
										break;
								}
								break;
						}
						fillIt = true;
//						changed = true;
					}
					if (fillIt) {
						if(index != -1){
							fill(this, index, options.dateArr);
							index = -1;
						}else{
							fill(this);
						}
					}
					if (changed) {
						if(options.time){
							options.onChange.apply(this, prepareDate(options));
						}else{
							options.onChange.apply(this, prepareDate(options));
						}
					}
					/* 在选区间第二次选择结束后将控件div隐藏并清除选中的区域-------------开始 */
					if(options.areaToHide){
						if(!close){
							//隐藏
							$('#' + options.id).hide('slow');
							//清除
							if (options.mode != 'single') {
								options.date = [];
								//fill(this);
							}else{
								options.date = [];
							}
						}
					}
					/* 在选区间第二次选择结束后将控件div隐藏并清除选中的区域-------------结束 */
					achanged = changed;
				}
				//点击下拉框
				/* chrome,ie8 为select  ie9 为option ff两者都可以 */
				if(el.is('select') || el.is('option')){
					var startHour, startMinute, endHour, endMinute, parentEl;
					var options = $(this).data('datepicker');
					if(el.is('option')){
						 parentEl = el.parent();
					}else{
						parentEl = el/*.parent()*/;
					}
					var tr = parentEl.parent().parent();
					if(tr.hasClass("start")){
						if(parentEl.hasClass("hour")){
							startHour = parentEl.val();
							startMinute = parentEl.parent().next().find("select").val();
						}
						if(parentEl.hasClass("minute")){
							startHour = parentEl.parent().prev().find("select").val();
							startMinute = parentEl.val();
						}
						endHour = tr.next().find("select.hour").val();
						endMinute = tr.next().find("select.minute").val();
					}
					if(tr.hasClass("end")){
						if(parentEl.hasClass("hour")){
							endHour = parentEl.val();
							endMinute = parentEl.parent().next().find("select").val();
						}
						if(parentEl.hasClass("minute")){
							endHour = parentEl.parent().prev().find("select").val();
							endMinute = parentEl.val();
						}
						startHour = tr.prev().find("select.hour").val();
						startMinute = tr.prev().find("select.minute").val();
					}
					//var tmp = new Date(options.date[0]);
					options.date[0] = (new Date(options.date[0]).setHours(startHour,startMinute,0,0)).valueOf();
					options.date[1] = (new Date(options.date[1]).setHours(endHour,endMinute,0,0)).valueOf();
					
					options.onChange.apply(this, prepareDate(options));
					
					
				}
				//如果有时间选择，点击确定，隐藏控件
				if(el.is('a#sub')){
					var options = $(this).data('datepicker');
					if(options.date[0]<=options.date[1]){
						options.onChange.apply(this, prepareDate(options));
						//$('#' + options.id).hide();
						options.onSub.apply(this, $('#' + options.id).hide());
							
					}else{
						$('tr.end td select.hour').css({"border":"1px solid red"});
						return;
					}
					
					
				}
				return false;
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
			extendDate = function(options) {
				if (Date.prototype.tempDate) {
					return;
				}
				Date.prototype.tempDate = null;
				Date.prototype.months = options.months;
				Date.prototype.monthsShort = options.monthsShort;
				Date.prototype.days = options.days;
				Date.prototype.daysShort = options.daysShort;
				Date.prototype.getMonthName = function(fullName) {
					return options[fullName ? 'months' : 'monthsShort'][this.getMonth()];
				};
				Date.prototype.getDayName = function(fullName) {
					return this[fullName ? 'days' : 'daysShort'][this.getDay()];
				};
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
			
			
			prepareDate = function (options) {
				var tmp;
				if (options.mode == 'single') {
					tmp = new Date(options.date);
					return [formatDate(tmp, options.format), tmp, options.el];
				} else {
					tmp = [[],[], options.el];
					$.each(options.date, function(nr, val){
						var date = new Date(val);
						tmp[0].push(formatDate(date, options.format));
						tmp[1].push(date);
					});
					return tmp;
				}
			},
			layout = function (el) {
				var options = $(el).data('datepicker');
				var cal = $('#' + options.id);
//				if (!options.extraHeight) {
//					var divs = $(el).find('div');
//					options.extraHeight = divs.get(0).offsetHeight + divs.get(1).offsetHeight;
//					options.extraWidth = divs.get(2).offsetWidth + divs.get(3).offsetWidth;
//				}
				var tbl = cal.find('table:first').get(0);
				var width = tbl.offsetWidth;
				var height = tbl.offsetHeight;
				cal.css({
					width: width + options.extraWidth + 'px',
					height: height + options.extraHeight + 'px'
				}).find('div.datepickerContainer').css({
					//width: width + 'px',
					//height: height + 'px'
				});
				cal.css({
					width: width + 'px'//,
					//height: height + 'px'
				});
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
					var options = cal.data('datepicker');
					options.onBeforeShow.apply(this, [cal.get(0)]);
					var pos = $(this).offset();
					var viewPort = getViewport();
					var top = pos.top;
					var left = pos.left;
					var width = pos.width;
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
					$(this).data('datepickerId', $(this).data('datepickerId'));
					$(document).bind('mousedown', {cal: cal, trigger: this}, hide);
				}
				return false;
			},
			hide = function (ev) {
				if (ev.target != ev.data.trigger && !isChildOf(ev.data.cal.get(0), ev.target, ev.data.cal.get(0))) {
					if (ev.data.cal.data('datepicker').onHide.apply(this, [ev.data.cal.get(0)]) != false) {
						ev.data.cal.hide();
						var options = ev.data.cal.data('datepicker');
						options.lastSel = false;
						var id = ev.data.cal.selector;
//						ev.data.cal.remove();
//						$("div").remove(".datepicker");
//						$("div" +id).siblings().remove(".datepicker")
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
					if (!$(this).data('datepicker')) {
						options.el = this;
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
						if (!options.current) {
							options.current = new Date();
						} else {
							options.current = parseDate(options.current, options.format);
						} 
						//若有下句，不可选的日期，在点击周数的时候则被选择且月份的可选区域有错
//						options.current.setDate(1);
						options.current.setHours(0,0,0,0);
						var id = 'datepicker_' + parseInt(Math.random() * 1000), cnt;
						options.id = id;
						$(this).data('datepickerId', options.id);
						var cal = $(tpl.wrapper).attr('id', id).bind('click', click).data('datepicker', options);
						if (options.className) {
							cal.addClass(options.className);
						}
						var html = '';
						for (var i = 0; i < options.calendars; i++) {
							cnt = options.starts;
							if (i > 0) {
								html += tpl.space;
							}
							html += tmpl(tpl.head.join(''), {
									week: options.locale.weekMin,
									prev: options.prev,
									next: options.next,
									day1: options.locale.daysMin[(cnt++)%7],
									day2: options.locale.daysMin[(cnt++)%7],
									day3: options.locale.daysMin[(cnt++)%7],
									day4: options.locale.daysMin[(cnt++)%7],
									day5: options.locale.daysMin[(cnt++)%7],
									day6: options.locale.daysMin[(cnt++)%7],
									day7: options.locale.daysMin[(cnt++)%7]
								});
						}
						cal
							.find('tr:first').append(html)
								.find('table').addClass(views[options.view]);
						fill(cal.get(0));
						if (options.flat) {
							cal.appendTo(this).show().css('position', 'relative');
							layout(cal.get(0));
						} else {
							cal.appendTo(document.body);
							//给input加上click事件
							$(this).bind(options.eventName, show);
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
						var options = cal.data('datepicker');
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
					return prepareDate($('#' + $(this).data('datepickerId')).data('datepicker'))[formated ? 0 : 1];
				}
			},
			clear: function(){
				return this.each(function(){
					if ($(this).data('datepickerId')) {
						var cal = $('#' + $(this).data('datepickerId'));
						var options = cal.data('datepicker');
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
						var options = cal.data('datepicker');
						if (options.flat) {
							layout(cal.get(0));
						}
					}
				});
			}
		};
	}();
	$.fn.extend({
		DatePicker: DatePicker.init,
		DatePickerHide: DatePicker.hidePicker,
		DatePickerShow: DatePicker.showPicker,
		DatePickerSetDate: DatePicker.setDate,
		DatePickerGetDate: DatePicker.getDate,
		DatePickerClear: DatePicker.clear,
		DatePickerLayout: DatePicker.fixLayout
	});
})(jQuery);

(function(){
  var cache = {};
 
  this.tmpl = function tmpl(str, data){
    // Figure out if we're getting a template, or if we need to
    // load the template - and be sure to cache the result.
    var fn = !/\W/.test(str) ?
      cache[str] = cache[str] ||
        tmpl(document.getElementById(str).innerHTML) :
     
      // Generate a reusable function that will serve as a template
      // generator (and which will be cached).
      new Function("obj",
        "var p=[],print=function(){p.push.apply(p,arguments);};" +
       
        // Introduce the data as local variables using with(){}
        "with(obj){p.push('" +
       
        // Convert the template into pure JavaScript
        str
          .replace(/[\r\t\n]/g, " ")
          .split("<%").join("\t")
          .replace(/((^|%>)[^\t]*)'/g, "$1\r")
          .replace(/\t=(.*?)%>/g, "',$1,'")
          .split("\t").join("');")
          .split("%>").join("p.push('")
          .split("\r").join("\\'")
      + "');}return p.join('');");
   
    // Provide some basic currying to the user
    return data ? fn( data ) : fn;
  };
})();