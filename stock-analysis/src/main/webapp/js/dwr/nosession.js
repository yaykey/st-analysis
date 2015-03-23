function dwrLogout() {

	var loginUrl = base + "/manager/common/nosession.jsp";
	document.location.href = loginUrl;
	
//	var content = 
//		'<div class="s_tip_3" ' +
//				'style="background : url(' + 
//				base + '/viewframe/img/tip3.png) center bottom no-repeat; padding-bottom:10px;height: 130px;">'+
//			'<div class="s_tip_bg" style="height: 126px;"></div>'+
//			'<div class="s_tip_inner">'+
//				'<div class="clearfix">'+
//					'<em class="icon ic8" onclick="$.unblockUI();">&nbsp;</em>'+
//					'<strong>' +
//						'超时提示:' + 
//					'</strong>'+
//				'</div>'+
//				'<p class="icon_tip3">' +
//					'会话超时,请重新登录!' +
//				'</p>'+
//				'<div style="text-align: center;">' +
//					'<input type="button" onclick="$.unblockUI();" value="返回登录页">' +
//				'</div>' +
//			'</div>'+
//		'</div>';
//		
//		
//	var $m = $(content);
//	
//	$.blockUI({
//		message: $m, 
//		fadeIn: 700, 
//		fadeOut: 1000,
//		timeout : 5000, 
//		showOverlay: true,
//		onUnblock: function(){
//			var loginUrl = base + "/login.jsp";
//			if (top != window) {
//				top.location.href = loginUrl;
//			} else {
//				window.location.href = loginUrl;
//			}
//		},
//		css: {
//			width:		'30%',
//			top:		'40%',
//			left:		'35%',
//			textAlign:	'left',
//			border: 	'none',
//			padding:	'5px',
//			color:		'#fff'
//			,backgroundColor: 'none'
//		}
//	});
}