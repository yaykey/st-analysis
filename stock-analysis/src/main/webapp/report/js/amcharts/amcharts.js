//加载图形
function loadAmCharts(opt) {
	/**			// 以下的所有参数在调用是不可缺少一个
	 *************************************************************************************************************************************************************** 	
	 * 			//判断数据是否生成，如果数据生成为0否则为1
	 *           nosum : '0',
	 * 		    // 随机数
	 *		    random_number : <%=random_number%>,
	 * 
	 * 			// 图形的宽度
	 * 			width : "100%",
	 * 
	 * 			//图形的高度
	 * 			height : 300,
	 * 			
	 * 			//图形的背景色,16进制
	 * 			color : "#FFFFFF",
	 *			//页签的顺序（00-99），必须为2位
	 *			index : ‘00’,
	 * 
	 *			//需要加载的配置文件
	 *			xml : base+"/xml/amline_settings.xml",
	 *			//需要加载的配置文件类型（0：文件，1：链接）
	 *			xml_type : 0,
	 * 
	 *			//需要加载数据文件
	 *			csv : base+"/txt/data0.txt",
	 *			//需要加载的数据文件类型（0：文件，1：链接）
	 *			csv_type : 0,
	 * 
	 *		   //需要的swf文件位置
	 *			swf : base+"/amCharts/amcharts/amline/amline/amline.swf",
	 * 
	 * 		   //取的插件的位置
	 * 		    path : base+"/amCharts/amcharts/amline/amline/",
	 * 
	 *		   //图形后追加的按钮，没有按钮可以为空（type="D":代表日，type="T"：代表小时）
	 *		    button : "<br><button value='11' type='D'>11天</button><button value='21' type='D'>21天</button><button value='31' type='D'>31天</button>" 
	 *****************************************************************************************************************************************************************
	 */
	 //$("#tabs-" + opt.index + "-" + opt.random_number).html("<p><a href='http://www.adobe.com/go/getflashplayer'><img src='" +base+ "/report/images/get_flash_player.gif' alt='下载 Adobe Flash player' title='下载 Adobe Flash player' /></a></p>");
	if (opt.nosum == '1') {
		$("#chart-" + opt.index).html(
				"<font color='red'>数据还未生成！</font>");
	} else {
		//图形的id生成规则:amline+图形的页签顺序(00-99)+页面的随机数
		var so = new SWFObject(opt.swf, "amline" + opt.index
				+ opt.random_number, opt.width, opt.height, "10", opt.color,"expressInstall.swf");
		   /**
			*	"Opaque" 使应用程序隐藏页面上位于它后面的所有内容。
			*	so.addParam("wmode", "opaque");
			*	"Transparent"使 HTML 页的背景可以透过应用程序的所有透明部分显示出来，并且可能会降低动画性能。
			*	"Opaque "和"Transparent "都可与 HTML 层交互，从而允许 SWF 文件上方的层遮蔽应用程序。
			*	这两种选项之间的差异在于"Transparent"允许透明，因此，如果 SWF 文件的某一部分是透明的，
			*	则 SWF 文件下方的 HTML 层可以透过该部分显示出来，而"opaque"则不会显示。
		    */
		so.addParam("wmode", "transparent");
		
		so.addVariable("path", opt.path);//图形的swf所在路径（便于操作插件）
		if (opt.xml_type == '0') {
			so.addVariable("settings_file", escape(opt.xml));//本地配置文件
		} else if (opt.xml_type == '1') {
			so.addVariable("chart_settings", opt.xml);// 动态加载配置文件
		}
		if (opt.csv_type == '0') {
			so.addVariable("data_file", escape(opt.csv));//本地数据
		} else if (opt.csv_type == '1') {
			so.addVariable("chart_data", opt.csv); // 动态加载数据
		}
		
		//chart_id生成规则(chart_id很重要，图形加载成功后会一直带着这个id)：line+图形的页签顺序(00-99)+页面的随机数
		so.addVariable("chart_id", "line" + opt.index + opt.random_number);
		so.addVariable("loading_settings", "正在加载配置,请稍后......");
		so.addVariable("loading_data", "正在加载数据,请稍后......");
		so.addVariable("preloader_color", "#000000");//图形背景色
		so.write("chart-" + opt.index);//取对应页签
		//$("#tabs-" + opt.index + "-" + opt.random_number).append(opt.button).append("<input type='hidden' name='start' id='start' value=''>");//对应页签生成图形后再后面追加按钮
	}
}

//图形加载成功后初始化按钮
function amChartInited(chart_id) {
	var random = chart_id.substring(6);
	var random_a = chart_id.substring(4, 6);
	var flashMovie;
	flashMovie = $("#amline" + random_a + random)[0];//取图形对象
	var $tab=$("#tabs-" + random_a + "-" + random);
	var totime = $tab.find("input").first().val();//取隐藏域的最大时间（隐藏域位于时间按钮后面）
	//绑定按钮操作
	
	$tab.find("button").unbind("click");
	$tab.find("button").bind("click", function() {
		 var stamp = $(this).attr("stamp");//取按钮的代表的时间类型（type="D":代表日，type="T"：代表小时）
		 if(stamp == 'execl'){//判断时候为导出按钮	
		 	$(this).attr("style", "color:blue");	
			if($(this).attr('paramId')=='0'){
				 var url=base+"/report/birt/extract."+actionExt+"?vrtlId="+$(this).attr('vrtlId');
				 document.location=url;
				 return false;
			}else{
				var url=base+"/report/birt/extract."+actionExt+"?vrtlId="+$(this).attr('vrtlId')+"&paramId="+$(this).attr('paramId');
				document.location=url;
				return false;
			}
		 }else{//判断时候为日期控制按钮
			$(this).attr("style", "color:red").siblings().removeAttr("style");//点击的按钮字体标红
			var day = 0;
			if (stamp == 'D') {//天
					var d = parseInt($(this).val());//处理按钮上的数据
					if (d == 0) {
						day = totime.substring(0, 8) + "01";//取当月
					} else {
						day = AddDays(totime, d);//默认最大日期向前推动天数
					}
			} else if (stamp == 'T') {//时
				day = AddTimes(totime, parseInt($(this).val()));//加载时间（同时在线日专用）
			}
			//加载时间按钮动作的图形响应
			$tab.find("input").last().val(day);
			flashMovie.setZoom(day, totime);	
		 }	
	});	
	
	//默认点击第一个按钮(如果第一个按钮为导出，则默认不点击)
	var $firstButton=$tab.find("button").first();
	if($firstButton.attr("stamp")=='execl'){
		return false;
	}else{
		$firstButton.click();
	}
}

//初始化图形加载开始时间和结束时间到隐藏域
function amGetZoom(chart_id, from, to) {
	var random = chart_id.substring(6);
	var random_a = chart_id.substring(4, 6);
	$("#tabs-" + random_a + "-" + random + " input").first().val(to);
}

//天图形时间处理（stamp="D"专用）
function AddDays(date, value) {
	var d = date.replace(/-/g, "/");
	var data = new Date(d);
	var datetime = new Date(data.getTime() - value * 24 * 60 * 60 * 1000);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1;//js从0开始取 
	var date = datetime.getDate();
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	return year + "-" + month + "-" + date;
}

//时图形时间处理（stamp="T"专用）
function AddTimes(datetime, value) {
	var date = datetime.substring(0, 10);
	var d = date.replace(/-/g, "/");
	var data = new Date(d);
	var datetime = new Date(data.getTime() - value * 24 * 60 * 60 * 1000);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1;//js从0开始取 
	var date = datetime.getDate();
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	return year + "-" + month + "-" + date + " 00-01时";
}

//图形加载错误触发
function amError(chart_id, message) {
	//处理一种特殊情况，防止图形卡死不动（No data for selected period）
	// 其他错误发生直接显示全部图例（防止时间按钮出错，时间按钮的天数大于实际拥有的天数）
	var random = chart_id.substring(6);
	var random_a = chart_id.substring(4, 6);
	var $tab=$("#tabs-" + random_a + "-" + random);
	var flashMovie;
	flashMovie = $("#am" + chart_id)[0];
	
	if (message == 'No data for selected period') {
		return false;
	} else if(message == 'Incorrect values'){
		var starttime = $tab.find("input").last().val();
		var totime = $tab.find("input").first().val();
		var stamp = $tab.find("button").first().attr("stamp");
		var day = 0;
		if (stamp == 'D') {//天
			day = AddDays(starttime, -1);//默认最大日期向前推动天数
		} else if (stamp == 'T') {//时
			day = AddTimes(starttime,-1);//加载时间（同时在线日专用）
		}
		//加载时间按钮动作的图形响应
		$tab.find("input").last().val(day);
		flashMovie.setZoom(day, totime);
	}else {
		$tab.find("input").last().val("");
		flashMovie.showAll();//显示全部
	}
}