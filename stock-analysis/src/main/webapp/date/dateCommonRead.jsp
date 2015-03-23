<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(document).ready(function(){
				function initTagData(){
					var monthStart = new Date(),monthEnd = new Date();
					/* 通过Ajax取到本年度的周数据，及其某张虚拟报表的时间区域并格式日期样式 */
					$.ajax({
						type:"post",
						url:"${base}/common-week/getMonth.${actionExt}",
						dataType: 'json',
						async: false,
						success:function(msg){
							data = eval(msg);
							monthStart = parseDate(data[0].min, 2, 1);
							monthEnd = parseDate(data[0].max, 2, 2);
						}
					});
					$('div.label_btn2').text(date_formart(monthEnd,'Y-m'));
					/* Ajax取数据完毕  */
					calendar(monthStart,monthEnd);
				}
				function calendar(monthStart,monthEnd){
					/* 设置日/月输入框可见，周输入框不可见，并将日/月输入框清空  */
					$('div.label_btn2').DatePicker({
						format:'Y-m',
						date: new Date(),
						current: monthEnd,
						starts: 1,
						calendars: 1,
						mode: 'sigle',
						view: 'moths',
						onChange: function(formated, dates){
							$("div.label_btn2").text(formated);
							TagLib.changeDate();
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
				initTagData();
				
		});
	</script>
	<!-- 主图   时间控件样式  开始  -->
	<style type="text/css" media="screen">
		.inp_cala{
		    background:url(${base}/report/jsp/css/images/big_data.png) no-repeat center right ;
		    border: 1px solid #cdd7d9;
		    padding: 2px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    height: 14px;
		    background-color:white;
		    border-radius:0px
		}
	</style>
	<!-- 主图  时间控件样式  结束  -->

