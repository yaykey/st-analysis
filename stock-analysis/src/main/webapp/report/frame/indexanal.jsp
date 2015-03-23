<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jquerylibs.jsp"%>
<head>
	<title>新媒传信移动统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="新媒传信移动统计是一款专业免费的移动应用统计分析工具，支持android，ios平台。">
	<meta name="keywords" content="新媒传信移动统计;移动应用统计;移动APP统计;Android统计;iOS统计;iphone统计">
	<link href="${base}/report/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/report/js/highchart/highcharts.js"></script>
	
	<style type="text/css">
	
		#app-register {
			position: absolute;
			width__ : 640px;
			height__: 500px;
			z-index: 999;
			border: 1px solid #808080;
			background-color: #fff;
			left : 30%;
			top : 20%;
			
		}
		
		#app-register .app-register-setup1,
		#app-register .app-register-setup2 {
			padding: 0 1px;
			border-bottom: 1px solid #808080;
			overflow: hidden;
		}
		
		#app-register .app-register-setup1{
			
		}
		
		#app-register .app-register-setup2{
		
		}
		
		#app-register .app-register-setup1 .app-register-tab1,
		#app-register .app-register-setup1 .app-register-tab2,
		#app-register .app-register-setup2 .app-register-tab1,
		#app-register .app-register-setup2 .app-register-tab2{
			height: 31px;
			width : 315px;
			background-repeat: no-repeat;
			text-align: center;
			float: left;			
		}
		
		#app-register .app-register-setup1 .app-register-tab1,
		#app-register .app-register-setup2 .app-register-tab2{
			background-image: url("${base}/report/images/appkey-tabs.png");
		}
		
		#app-register .app-register-setup1 .app-register-tab1{
			background-position: 0px 1px;
		}
		
		#app-register .app-register-setup1 .app-register-tab2{
			 width: 303px;
		}
		
		#app-register .app-register-setup2 .app-register-tab1{
			width: 303px;
		}
		
		#app-register .app-register-setup2 .app-register-tab2{
			background-position: right -34px;
		}
		
		#app-register .app-register-setup1 .app-register-tab1 span,
		#app-register .app-register-setup1 .app-register-tab2 span,
		#app-register .app-register-setup2 .app-register-tab1 span,
		#app-register .app-register-setup2 .app-register-tab2 span{
			line-height: 26px;
			margin-left: 120px;
			float: left;
		}
		
		#app-register .app-register-setup1 .app-register-tab2 span {
			margin-left: 109px;
		}
		
		#app-register .content {
		
		}
		
		#app-register .content .lab,
		#app-register .content .val{
			margin : 20px 0px;
			line-height : 22px;
		}
		
		#app-register .content .lab{
			float : right;
			margin-right: 20px;
		}
		
		#app-register .content .val{
			float : left;
		}
		
		#app-register .content .appkey-btn {
			border: 1px solid #A9A9A9;
		    margin: 0 15px;
		    text-align: center;
		    line-height : 22px;
		    color: #696969;
		    padding: 5px 15px;
		    cursor: pointer;
		}
		
		#app-register .content .error {
			margin-bottom: 0px;
			padding : 1px;
		}
		
		#app-register .content label.error {
			margin-left: 20px;
		}
	</style>
	
</head>
<body>
	<div class="header">
		<div class="header-content">
			<div class="logo">
				<img src="${base}/report/images/logo.png" alt="飞信移动统计">
			</div>
			<div class="top-nav">
				<a href="http://mtj.baidu.com/web/welcome/news" target="_blank"
					class="trackable">${userSessionInfo.userInfo.username}</a>|
					<!-- <a href="#" target="_blank" class="trackable">我的应用</a>| -->
					<a id="addapp" href="javascript:void(0);" class="trackable">添加新应用</a>|
					<a href="${base }" target="_self">返回首页</a>
			</div>
			<div class="menu-nav">
				<ul>
					<li class="menu-nav-cur menu-nav-secondary"><a class="nav"
						href="javascript:void(0);">统计分析</a></li>
					<li class="menu-nav-secondary"><a class="nav"
						href="#">应用管理</a></li>
				</ul>
			</div>
			<div class="site-selector" id="SiteListContainer">
				<a id="applist" class="combobox" href="javascript:void(0)"> <span class="text ellipsis"
					title="Demo(iOS)">Demo(iOS)</span><b class="arrow"></b> </a>
				<div id="appdiv" class="options-container layer bg-iframe"
					style="display: none;">
					<ul class="options" id="SiteList">
						<li><a
							href="javascript:window.location%20=%20window.location.href.replace(/appId=(\d*)/,'appId=19')"
							title="Demo(iOS)">Demo(iOS)</a>
						</li>
						<li><a
							href="javascript:window.location%20=%20window.location.href.replace(/appId=(\d*)/,'appId=19')"
							title="Demo(iOS)">Demo(Android)</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="body clearfix">
		<div style="" class="aside" id="aside-menu">
			<div class="title" id="div-toggle">
				<h1>我的导航</h1>
				<a href="javascript:void(0)" title="收起" class="fold recordable" id="menu-toggle"></a>
			</div>
			<div class="minibar recordable" id="minibar" style="display: none; position: absolute; top:0px; height: 576px;">
				<a href="javascript:void(0)" title="展开" class="unfold" id="menu-untoggle"></a>
			</div>
			<ul id="indextree" style="height:650px;" class="nav">
				<%@include file="../frame/left.jsp"%>
			</ul>
		</div>

		<div class="misc">
			<div class="feedback">
				<div class="feedback-form" style="display: none;" id="feedbackform">
					<form id="FeedbackSubmitForm">
						<p>
							<select id="FeedbackType">
								<option selected="selected" value="反馈类型">反馈类型</option>
								<option value="使用问题">使用问题</option>
								<option value="产品建议">产品建议</option>
								<option value="商务合作">商务合作</option>
							</select>
						</p>
						<p>
							<input class="gray" id="FeedbackEmail" defaultvalue="请输入邮箱"
								value="请输入邮箱" type="text">
						</p>
						<p>
							<textarea class="gray" id="FeedbackContent"
								defaultvalue="请您把意见和建议写在这里，有助于我们提供更好的服务">请您把意见和建议写在这里，有助于我们提供更好的服务</textarea>
						</p>
						<input class="button" id="FeedbackSubmit" value="提交" type="submit">
					</form>
				</div>
				<span class="icons"> 
					<a href="javascript:void(0)" title="反馈问题" id="feedback" class="feedback-icon trackable"></a>
					<a title="帮助中心" target="_blank" href="" id="Help"></a>
				</span>
			</div>
			<div class="back-to-top" id="BackToTop" style="display: none">
				<a href="#Top" class="trackable" memo="{id:'back2top'}"></a>
			</div>
		</div>
		<div class="main" id="idxdiv">
			<div class="content clearfix">
				<div class="title-bar clearfix">
					<h1 id="idxname" class="l">应用概况</h1>
				</div>
			</div>
		</div>
		<div class="main" id="main-body">

			
			
		</div>
		<div class="footer">
			<span>©2014 feition</span>
			<a href="javascript:void(0)" target="_blank">飞信首页</a>
			<a href="javascript:void(0)" target="_blank">关于我们</a>
			<a href="javascript:void(0)" target="_blank">使用前必读</a>
		</div>

		<!-- appkey register -->
		<div id="app-register" style="display: none;">
			<div class="app-register-setup1">
				<div class="app-register-tabs">
					<div class="app-register-tab1">
						<span>添加新应用</span>
					</div>
					<div class="app-register-tab2">
						<span>生成appKey</span>
					</div>
				</div>
			</div>
			<div class="app-register-setup2" style="display: none;">
				<div class="app-register-tabs">
					<div class="app-register-tab1">
						<span>添加新应用</span>
					</div>
					<div class="app-register-tab2">
						<span>生成appKey</span>
					</div>
				</div>
			</div>
			<div class="content">
				<form id="appkey-form" action="#" method="post">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="160">
								<div class="lab">应用名</div>
							</td>
							<td>
								<div class="val">
									<input name="app.appname" type="text" style="width : 180px;" maxlength="40">
								</div>
							</td>
						</tr>
						<tr class="appkey-tr" style="display:none;">
							<td width="160">
								<div class="lab">appkey</div>
							</td>
							<td>
								<div class="val">
									<input id="appkey-key" name="app.appkey" type="text" disabled="disabled" 
											style="border: none; background: transparent; width:250px;">
								</div>
							</td>
						</tr>
						<tr>
							<td width="160">
								<div class="lab">应用平台</div>
							</td>
							<td>
								<div class="val">
									<label>
										<input name="app.plateformId" value="1" checked="checked" type="radio">
										android
									</label>
									<label disabled="disabled">
										<input disabled="disabled" type="radio">
										iOS
									</label>
								</div>
							</td>
						</tr>
						<tr>
							<td width="160">
								<div class="lab">应用类型</div>
							</td>
							<td>
								<div class="val">
									<select name="app.appType" style="width : 150px;">
										<option value="1">其他</option>
										<option value="2">娱乐</option>
										<option value="3">工具</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="160" valign="top">
								<div class="lab" style="margin-bottom : 95px;">应用描述</div>
							</td>
							<td>
								<div class="val">
									<textarea name="app.description" rows="5" cols="60"></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="center">
								<div style="padding : 10px 10px 30px 10px;">
									<!-- <a href="javascript:void(0);" class="appkey-btn appkey-btn-next">下一步</a> -->
									<!-- <a href="javascript:void(0);" class="appkey-btn appkey-btn-submit" 
											style="display:none;">完成</a> -->
									<!-- <a href="javascript:void(0);" class="appkey-btn appkey-btn-cancel">取消</a> -->
									
									<input type="button" class="appkey-btn appkey-btn-next" value="下一步">
									<input type="submit" class="appkey-btn appkey-btn-submit" 
											style="display:none;" value="完成">									
									<input type="reset" class="appkey-btn appkey-btn-reset" 
											style="display:none;" value="重置">
									<input type="button" class="appkey-btn appkey-btn-cancel" value="取消">
								</div>
							</td>
						</tr>						
					</table>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		
	 	$("#indextree a[mflag='1']").bind("click", function(){
			if($(this).attr("class")=="recordable open"){
				$(this).attr("class","recordable close");
				$(this).parent().find("ul").hide(800);
			}else{
				$(this).attr("class","recordable open");
				$(this).parent().find("ul").show(800);
			}
		});
	 	
	 	$("#indextree a[fflag='1']").bind("click", function(){
	 		
	 		$("#indextree li[class='selected']").removeClass("selected");
	 		$(this).parent().addClass("selected");
	 		$("#idxname").html($(this).html());
	 		jQuery.ajaxSetup({ cache: true });
			jQuery.ajax({
				type : "POST",
				url : base + $(this).attr("url")+"?fid="+$(this).attr("id"),
				dataType : 'text',
				data : {
					//,"nocache" : new Date().getTime()
				},
				success : function(response) {
					$("#main-body").empty();
					$("#main-body").append(response);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					
				}
			});
		});
		
	 	$("#indextree a:first").click();
	 	
		$("#menu-toggle").bind("click", function(){
			$("#div-toggle").hide();
			$("#indextree").hide();
			$("#minibar").show();
			$("#main-body").css("margin-left","65px");
			$("#aside-menu").css("width","35px");
			
		});
		$("#menu-untoggle").bind("click", function(){
			$("#div-toggle").show();
			$("#indextree").show();
			$("#minibar").hide();
			$("#main-body").css("margin-left","260px");
			$("#aside-menu").css("width","239px");
		});
		
		$("#applist").bind("click", function(){
			if($(this).hasClass("selected")){
				$(this).removeClass("selected");
				$(this).find("b").removeClass("selected");
				$("#appdiv").hide();
			}else{
				$(this).addClass("selected");
				$(this).find("b").addClass("selected");
				$("#appdiv").show();
			}
		});
		
		$("#feedback").bind("click", function(){
			if($(this).hasClass("open")){
				$(this).removeClass("open");
				$("#feedbackform").hide();
			}else{
				$(this).addClass("open");
				$("#feedbackform").show();
			}
		});
		
		
		$("#addapp").click(function(){
			var appRegister = $("#app-register");
			
			if (appRegister.css("display") == "none") {
				$(appRegister).show(500);
			} else {
				appRegisterCloseHandler();
			}
			
		});
		
		$("#app-register").find(".appkey-btn-cancel").click(function(){
			appRegisterCloseHandler();
		});
		
		$("#app-register").find(".appkey-btn-next").click(function(){
			$("#app-register").find(".appkey-tr").show(500);
			$("#app-register").find(".appkey-btn-next").hide(100);
			$("#app-register").find(".appkey-btn-submit").show(100);
			
			$("#app-register").find(".app-register-setup1").hide();
			$("#app-register").find(".app-register-setup2").show();
			
			jQuery.ajax({
				type : "POST",
				url : base + "/manager/app/generateAppkey.feinno",
				dataType : 'text',
				data : {
					"nocache" : new Date().getTime()
				},
				success : function(responseText) {
					$("#appkey-key").val(responseText);
				}
			});
			
		});
		
		
        
		jQuery.validator.addMethod("numberWord", function(value, element) {
			var chrnum = /^([a-zA-Z0-9\.]+)$/;
			return this.optional(element) || (chrnum.test(value));
		}, "请输入2-30位字母、数字");
		
		var validateAppkey = $("#appkey-form").validate({
			rules : {
				"app.appname" : {
					required : true,
					minlength : 2,
					maxlength : 30,
					numberWord: true,
					remote : {
						url : base + '/manager/app/checkAppname.feinno',
						type : "post"
					}
				},
				"app.appkey" : {
					required : true
				}
				
			},			
			messages : {
				"app.appname" : {
					required : "请输入2-30位字母、数字",
					minlength: jQuery.format("请输入至少 {0}位数字、字母"),
					maxlength: jQuery.format("请输入最多 {0}位数字、字母"),
					remote: "此名称已经存在"
				}
			},
			submitHandler : function(form) {
				var iden = validateAppkey.form();
				//var iden = true;
				
				if(iden == false){
					
				}else{
					jQuery.ajax({
						type : "POST",
						url : base + '/manager/app/appAdd.feinno',
						dataType : 'text',
						data : {
							"nocache" : new Date().getTime(),
							"app.appname" : form["app.appname"].value,
							"app.appkey" : form["app.appkey"].value,
							"app.plateformId" : form["app.plateformId"].value,
							"app.appType" : form["app.appType"].value,
							"app.description" : form["app.description"].value
						},
						success : function(responseText) {
							appRegisterCloseHandler();		
						}
					});
				}
			}
		});
		
		
		function appRegisterCloseHandler () {
			$("#app-register").hide(500);
			
			$("#app-register").find(".appkey-tr").hide();
			$("#app-register").find(".appkey-btn-next").show();
			$("#app-register").find(".appkey-btn-submit").hide();
			
			$("#app-register").find(".app-register-setup1").show();
			$("#app-register").find(".app-register-setup2").hide();
			
			$("#appkey-form").find("input:text").val("");
			$("#appkey-form").find("textarea").val("");
			
			//validateAppkey.resetForm();
			
			$("#appkey-form").find("label.error").remove();
			
			$("#appkey-form").find(".error").removeClass("error");
		}
		
		/* $("#app-register").find(".appkey-btn-submit").click(function(){
			var iden = validateAppkey.form();
			//alert("iden=" + iden);
			if(iden == false){
				
			}else{
				$("#appkey-form").submit();
			}
		}); */
		
	 });
</script>
 

</body>