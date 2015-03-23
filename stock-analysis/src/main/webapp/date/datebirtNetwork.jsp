<%@ page language="java" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(
			function(){
				var defaultDayType = ${defaultDayType};
				/* Ajax取数据完毕  */
				calendar(defaultDayType);
				
				$('#rpt_day').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				$('#rpt_month').click(function(){
					var val = $(this).attr("value");
					calendar(val);
				});
				
				function calendar(val){
					if(val==1){
						var ms = $('#databirt_5').attr("start");
						var me = $('#databirt_5').attr("end");
						var dayStart = parseDate(ms, 1, null);
						var dayEnd = parseDate(me, 1, null);
						
						 $('#databirt_5').DatePicker({
							format:'Ymd',
							date: new Date(),
							current: dayEnd,
							starts: 1,
							position: 'bottom_right',
							calendars: 2,
							mode: 'range',
							view: 'days',
							onChange: function(formated){
								$('#databirt_5').val(formated.join('-'));
							},
							/* 此处为当前日期之后不可选 */
							onRender: function(date) {
								return {
									disabled: (date.valueOf() > dayEnd.valueOf())||(date.valueOf() < dayStart.valueOf()),
									className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
								}
							}

						});
						
					}else if(val==2){
						/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
						$('#monthbirt').css('display', 'inline');
						$('#databirt').css('display', 'none');
						$('#databirt_').css('display', 'none');
						$('#databirt_1').css('display', 'none');
						$('#databirt_2').css('display', 'none');
						$('#databirt_3').css('display', 'none');
						$('#databirt_4').css('display', 'none');
						var ms = $('#monthbirt').attr("start");
						var me = $('#monthbirt').attr("end");
						var ms1 = $('#monthbirt_').attr("start");
						var me1 = $('#monthbirt_').attr("end");
						var ms2 = $('#monthbirt_1').attr("start");
						var me2 = $('#monthbirt_1').attr("end");
						var ms3 = $('#monthbirt_2').attr("start");
						var me3 = $('#monthbirt_2').attr("end");
						var ms4 = $('#monthbirt_3').attr("start");
						var me4 = $('#monthbirt_3').attr("end");
						var ms5 = $('#monthbirt_4').attr("start");
						var me5 = $('#monthbirt_4').attr("end");
						
						var monthStart,monthEnd,monthStart_,monthEnd_,monthStart_1,monthEnd_1,monthStart_2,monthEnd_2,
						monthStart_3,monthEnd_3,monthStart_4,monthEnd_4;
						
						if(ms != null){
							monthStart = parseDate(ms, 1, null);
							monthEnd = parseDate(me, 1, null);
							$('#monthbirt').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd.valueOf())||(date.valueOf() < monthStart.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							});
						}
						if(ms1 != null){
							monthStart_ = parseDate($('#monthbirt_').attr("start"), 1, null);
							monthEnd_ = parseDate($('#monthbirt_').attr("end"), 1, null);
							$('#monthbirt_').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd_,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt_').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd_.valueOf())||(date.valueOf() < monthStart_.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							}); 
						}
						if(ms2 != null){
							monthStart_1 = parseDate($('#monthbirt_1').attr("start"), 1, null);
							monthEnd_1 = parseDate($('#monthbirt_1').attr("end"), 1, null);
							$('#monthbirt_1').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd_1,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt_1').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd_1.valueOf())||(date.valueOf() < monthStart_1.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							}); 
						}
						if(ms3 != null){
							monthStart_2 = parseDate($('#monthbirt_2').attr("start"), 1, null);
							monthEnd_2 = parseDate($('#monthbirt_2').attr("end"), 1, null);
							$('#monthbirt_2').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd_2,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt_2').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd_2.valueOf())||(date.valueOf() < monthStart_2.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							});
						}
						if(ms4 != null){
							monthStart_3 = parseDate($('#monthbirt_3').attr("start"), 1, null);
							monthEnd_3 = parseDate($('#monthbirt_3').attr("end"), 1, null);
							$('#monthbirt_3').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd_3,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt_3').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd_3.valueOf())||(date.valueOf() < monthStart_3.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							});
						}
						if(ms5 != null){
							monthStart_4 = parseDate($('#monthbirt_4').attr("start"), 1, null);
							monthEnd_4 = parseDate($('#monthbirt_4').attr("end"), 1, null);
							$('#monthbirt_4').DatePicker({
								format:'Ym',
								date: new Date(),
								current: monthEnd_4,
								starts: 1,
								position: 'bottom_right',
								calendars: 2,
								mode: 'range',
								view: 'moths',
								onChange: function(formated, dates){
									$('#monthbirt_4').val(formated.join('-'));
								},
								/* 此处为当前日期之后不可选 */
								onRender: function(date) {
									return {
										disabled: (date.valueOf() > monthEnd_4.valueOf())||(date.valueOf() < monthStart_4.valueOf()),
										className: date.valueOf() == new Date().valueOf() ? 'datepickerSpecial' : false
									}
								}

							}); 
						}
						
					}
				}
			});
	</script>
	<!-- birt 时间控件样式/下拉框  开始  -->
	<style type="text/css" media="screen">
		.inp_cal{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 1px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px 
		}
		
		select{
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		} 
	</style>
	<!-- birt  时间控件样式/下拉框  结束  -->
