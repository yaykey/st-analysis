<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
	<h2 class="tit_1" style="text-align:left;">报表</h2>
	<div>
		<ul id="static_rpt_type" class="v4_btn clearfix">
			<c:forEach items="${requestScope.reportContentList}" var="reportContent">
				<li id="rptidMenu" rptid="${reportContent.rptId}" itemcount="${reportContent.itemcount}" imageFilename="${reportContent.imageFilename}" rptname="${reportContent.rptName}" onclick="operateTypelist('${reportContent.rptId}');">
					<a href="javascript:void(-1);"  id="rptid_${reportContent.rptId}" class="a7 sty1">
						<c:choose>  
					    <c:when test="${fn:length(reportContent.rptName) > 8}">  
					        <c:out value="${fn:substring(reportContent.rptName, 0, 8)}" />  
					    </c:when>  
					   <c:otherwise>  
					      ${reportContent.rptName}
					  </c:otherwise>  
					  </c:choose>
					</a>
				</li>
			</c:forEach>
			<li style="display:none;"><a id="addreport_Rpt" href="javascript:void(-1)" class="a9 sty1">+添加报表</a>
				<div id="bg" class="bg" style="display: none;"></div>
			</li>
		</ul>
		<div id="show_base" rptchk="1" class="stop" style="position: absolute;display: none; z-index: 100;">
			<input id="param_rpt_id" type="hidden" value="">
			<input id="param_rpt_name" type="hidden" value="">
			<input id="imageFilename" type="hidden" value="">
			<a href="javascript:void(-1);"  style="float: left;padding-left:5px;"  id="veiw_rename">重命名</a>
			<div id="bg" class="bg" style="display: none;float: left;"></div>
			<!-- <span style="float: left;padding-left:5px">|</span><a class="col_6"  style="float: left;padding-left:5px;" href="javascript:void(-1);" id="previewImage">预览图片</a>
			<span style="float: left;padding-left:5px">|</span><a href="javascript:void(-1);"  style="float: left;padding-left:5px;padding-right:5px" class="col_6" id="reUploadImg">修改图片</a> -->
			<span  style="float: left;padding-right:5px;" id="delReport"><span style="float: left;">|</span><a href="javascript:void(-1);"  style="float: left;padding-left:5px;">删除</a></span>
		</div>
	</div>

	<div id="add_report" class="plat_pop">
		<span class="t_round"></span>
		<form id='rptImgForm'  action='saveReport.feinno' enctype='multipart/form-data' method='post'>
		<div class="z_con">
			<h2>
				<a id="close_report" class="close fr" href="javascript:void(-1);"></a> <span>添加报表</span>
			</h2>
				<div class="inp_con clearfix">
					<p class="clearfix">
						<label>报表名称 ：</label> <input class="fl inp_1 p_4" type="text" name="reptName"  id="reptName" maxlength="16" value=""/>
						<!-- <div id="item_org_Id" style="height:30px;padding-top:5px;">
							<label>&nbsp;&nbsp;报表ID ：</label> <input class="fl inp_1 p_4" type="text" onkeyup="isNumber(this);" onafterpaste="isNumber(this);" name="itemIdOrg"  id="itemIdOrg" maxlength="16" value=""/>
						</div>
						<em class="clearfix">大小不超过10000kb的JPG、png格式的图片</em> -->
					</p>
					<p class="clearfix p_con" id="showImg">
						<img class="fl" id="preview" style="width: -1; height: -1;display:none" />
					</p>
					<input type="hidden" name="clientRptTypeId" value="${clientTypeId}"/>
					<%-- <input type="hidden" name="clientRptVsn" value="${clientVsn}"/> --%>
					<input type="hidden" name="classRptId" value="${classId}"/>
					<input type="hidden" name="reportRptId" id="reportRptId"/>
					<!-- <input type="file"  name="reportImage" id="reportImage" size="1" style='position:absolute;left:50px; height:26px;width:65px !important;; filter:alpha(opacity:0);opacity:0; '/> -->
					<!-- <button type="button" id="uploadButtom" class="p_btn1 fl">上传图片</button> -->
					<button type="button" id="addSaveRpt" class="p_btn2 fl">确认</button>
					
				</div>
		</div>
		</form>
		<span class="b_round"></span>
	</div>

	<div id="rename" class="plat_pop">
		<span class="t_round"></span>
		<div class="z_con">
			<h2>
				<a id="close_rename" class="close fr" href="javascript:void(-1)"></a>
				修改报表名称
			</h2>
			<div class="inp_con clearfix">
				<label class="fl">报表名称</label> <input id="rpt_rename_name"
					class="fl inp_1 p_4 input_name" type="text" value="" maxlength="16">
				<button class="fl n_btn" id="saveRpt">确定</button>
			</div>
		</div>
		<span class="b_round"></span>
	</div>
	<div class="plat_pop" id="viewImage">
		<div class="pop_img"><img src="" alt=""/><span class="close_2" id="close_image"></span></div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		var classtype = $("#classid_${classId}").attr("classtype");//获取报表类型的类型(1 报表 0 虚拟报表)
		$("#addreport_Rpt").bind("click", function() {
			if(classtype==1){
				$("#item_org_Id").show();
			}else{
				$("#item_org_Id").hide();
			}
			$("#add_report").find("h2 span").text("新增报表");
			$("#reportRptId").val("");
			$("#reptName").val("");
			$("#preview").attr("src","").css({"display":"none"});
			openPop("add_report","h2");
		});
		$("#close_report").bind("click", function() {
			closePop('add_report');
		});

		$("#close_rename").bind("click", function() {
			closePop('rename');
		});
		/**查看图片*/
		/* $("#previewImage").bind("click", function() {
			var imgObj = $("#viewImage").find("img");
			var imagePath =  base +"/servlet/reportImg?filename="+$("#imageFilename").val();
			imgObj.attr("src",imagePath);
			openPop("viewImage");
		}); */
		/* $("#close_image").bind("click", function() {
			closePop("viewImage");
		}); */
		/**修改图片*/
		/* $("#reUploadImg").bind("click", function() {
			$("#add_report").find("h2 span").text("修改图片");
			if($.trim($("#imageFilename").val()).length>1){
				var imagePath =  base +"/servlet/reportImg?filename="+$("#imageFilename").val();
				$("#preview").css({"width":"120px","height":"120px","display":"block"});
				$("#preview").attr("src",imagePath);
				
			}
			$("#item_org_Id").hide();
			$("#reptName").val($("#param_rpt_name").val());
			openPop("add_report","h2");
			$("#reportRptId").val($("#param_rpt_id").val());
		}); */
		
		/**新增报表*/
		$("#addSaveRpt").bind("click", function() {
			progressRpt();
			var reptName = $("#reptName").val();
			if($.trim(reptName).length<1){
				closeRpt();
				$.messager.alert("提示", "报表名称不能为空!", "error");
				return false;
			}
			var rptId = $("#reportRptId").val();
			var chk = false;
			$("#static_rpt_type").find("li").each(function(){
				if($.trim($(this).attr("rptname"))==$.trim(reptName)){
					if($(this).attr("rptid")!=null&&$.trim($(this).attr("rptid"))!=$.trim(rptId)){
						chk = true;
						return ;
					}
				}
			});
			if(chk){
				closeRpt();
				$.messager.alert("提示", "报表名称不能重复!", "error");
				return false;
			}
			
			var itemIdOrg = "";
			if(classtype==1&&$.trim($("#reportRptId").val()).length<1){
				itemIdOrg = $("#itemIdOrg").val();
				if($.trim(itemIdOrg).length<1){
					closeRpt();
					$.messager.alert("提示", "报表ID不能为空!", "error");
					return false;
				}
			}
			$('#rptImgForm').ajaxSubmit({
				url :base + '/manager/userbehavior/operate/saveReport.' + actionExt,
				dataType : "json",
				type:"POST",
				success: function (res) { 
					closeRpt();
					if(res.chk==1){
						$.messager.alert("提示", "报表名称不能为空!", "error");
						return false;
					}else if(res.chk==2){
						$.messager.alert("提示", "客户端不能为空!", "error");
						return false;
					}else if(res.chk==3){
						$.messager.alert("提示", "版本号不能为空!", "error");
						return false;
					}else if(res.chk==4){
						$.messager.alert("提示", "报表类型不能为空!", "error");
						return false;
					}else if(res.chk==5){
						$.messager.alert("提示", "图片不能为空!", "error");
						return false;
					}else if(res.chk==6){
						$.messager.alert("提示", "保存不成功!", "error");
						return false;
					}else if(res.chk==7){
						$.messager.alert("提示", "图片大小不能超过10000KB!", "error");
						return false;
					}else if(res.chk==8){
						$.messager.alert("提示", "报表ID重复,请重新填写报表ID!", "error");
						return false;
					}else{
						$("#rptidMenu").attr("imageFilename",res.filename);
						closePop('add_report');
						$.messager.alert("提示", "保存成功!", "info",function(){
							funOperateStype(${classId});
						});
					}
				} 
			});
			
		});
		
		/**点击重命名，弹出重命名对话框**/
		$("#veiw_rename").bind("click", function() {
			$("#rpt_rename_name").val($("#param_rpt_name").val());
			openPop("rename","h2");
		});
		
		/**点击重命名，弹出重命名对话框**/
		$("#delReport").bind("click", function() {
			$.messager.confirm("提示","确定删除?",function(result){
				if(result){
					var rptId = $("#param_rpt_id").val();
					jQuery.ajax({
						type : "POST",
						url : base + '/manager/userbehavior/operate/delReport.' + actionExt,
						dataType : 'html',
						data : {
							"rptId" : rptId,
							"clientTypeId" : "${clientTypeId}"//,
							//"clientVsn" : "${clientVsn}"
						},
						success : function(responseText) {
							var obj = eval("(" + responseText + ")");
							if (obj.chk == 0) {
								closePop('rename');
								$.messager.alert("提示", "删除成功!", "info",function(){
									funOperateStype(${classId});
								});
							} else if (obj.chk == 1) {
								$.messager.alert("提示", "删除失败!", "error");
							}else if (obj.chk == 2) {
								$.messager.alert("提示", "删除失败!", "error");
							}
						}
					});
				}
			});
		});

		var newflag = $("#versionid").find("option:selected").attr("newflag");
		if(newflag!=1){
			$("#addreport_Rpt").parent().css({"display":"block"});
			$("#static_rpt_type").find("li").each(function(index){
				/**鼠标进入报表按钮**/
				if($(this).attr("id")=="rptidMenu"){
					$(this).bind("mouseenter", function() {
						if($("#show_base").css("display")=="block"){
							$("#show_base").css({top : "0px",left :"0px","display":"none"});
							$("#show_base").attr("rptchk",0);
						}
						
						//if($(this).attr("itemcount")==1){
						//	$("#delReport").parent().css({width:"220px"});
						//	$("#delReport").css({"display":"block"});
						//}else{
						//	$("#delReport").parent().css({width:"180px"});
						//	$("#delReport").css({"display":"none"});
						//}
						
						if($(this).attr("itemcount")>0){
							$("#delReport").parent().css({width:"50px"});
							$("#delReport").css({"display":"none"});							
						}else{							
							$("#delReport").parent().css({width:"90px"});
							$("#delReport").css({"display":"block"});
						}
						
						$("#param_rpt_id").val($(this).attr("rptid"));
						$("#param_rpt_name").val($(this).attr("rptname"));
						$("#imageFilename").val($(this).attr("imageFilename"));
						var that = this;
						
						if($("#show_base").css("display")==""||$("#show_base").css("display")=="none"){
							$("#show_base").animate({
								height : "24px"
							}, 500, function() {
								if($("#show_base").attr("rptchk")==1&&$(that).attr("rptid")==$("#param_rpt_id").val()){
									$(this).css({
										top :($(that).position().top+32)+"px",
										left :$(that).position().left+"px"
									});
									$(this).animate({opacity: 'toggle'},500);
								}
							});
						}
						$("#show_base").attr("rptchk",1);
					});
					/**鼠标离开报表按钮**/
					$(this).bind("mouseleave", function() {
						var that = this;
						if($("#show_base").css("display")=="block"){
							$("#show_base").animate({height : "24px"},200,function(){
								if($("#show_base").attr("rptchk")==0&&$(that).attr("rptid")==$("#param_rpt_id").val()){
									$(this).animate({opacity: 'hide',height : "22px"},500,function(){
										$(this).css({
											top : "0px",
											left :"0px"
										});
									});
								}
							});
						}
						$("#show_base").attr("rptchk",0);
					});
				}
			});
		}
		
		/**鼠标进入报表按钮**/
		$("#show_base").bind("mouseenter", function() {
			$(this).attr("rptchk",1);
			$(this).css({"display":"block"});
		});
		/**鼠标离开报表按钮**/
		$("#show_base").bind("mouseleave", function() {
			if($(this).css("display")=="block"){
				$(this).animate({height : "24px"},200,function(){
					if($(this).attr("rptchk")==0){
						$(this).animate({opacity: 'hide',height : "22px"},500,function(){
							$(this).css({
								top : "0px",
								left :"0px"
							});
						});
					}
				});
			}
			$("#show_base").attr("rptchk",0);
		});
		/**点击确定按钮对报表名称进行重命名**/
		$("#saveRpt").bind("click", function() {
			var rptId = $("#param_rpt_id").val();
			var rptName = $("#rpt_rename_name").val();
			if(rptName==null||$.trim(rptName).length<1){
				closeRpt();
				$.messager.alert("提示", "报表名称不能为空!", "error");
				return false;
			}
			
			var chk = false;
			$("#static_rpt_type").find("li").each(function(){
				if($(this).attr("rptid")!=null&&$.trim($(this).attr("rptid"))!=$.trim(rptId)
						&&$.trim($(this).attr("rptname"))==$.trim(rptName)){
					chk = true;
					return ;
				}
			});
			if(chk){
				closeRpt();
				$.messager.alert("提示", "报表名称不能重复!", "error");
				return false;
			}
			
			jQuery.ajax({
				type : "POST",
				url : base + '/manager/userbehavior/operate/updateReport.' + actionExt,
				dataType : 'html',
				data : {
					"rptId" : rptId,
					"rptName" : rptName
				},
				success : function(responseText) {
					var obj = eval("(" + responseText + ")");
					if (obj.chk == 0) {
						closePop('rename');
						$.messager.alert("提示", "修改成功!", "info",function(){
							funOperateStype(${classId});
						});
					} else if (obj.chk == 1) {
						$.messager.alert("提示", "修改失败!", "error");
					}
				}
			});
			
		});
		searchRpt();
		$("#reportImage").imagePreview({width:120,height:120,imgDiv:"#preview",imgForm:"#rptImgForm"});//图片预览
	});

</script>
