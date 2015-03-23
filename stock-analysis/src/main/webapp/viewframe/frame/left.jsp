<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.net.URLEncoder"%>
<%@ page import="com.st.framework.module.*"%>
<%@ page import="com.st.Global"%>
<%@ page import="com.st.viewframe.controller.vo.FunctionReport"%>
<%@ page import="com.st.framework.business.impl.FunctionManager"%>
<%--@ page import="com.feinno.fdc.report.framework.controller.UserSessionInfo"--%>



<table id="nav" cellpadding="0" cellspadding="0" border="0" style="z-index: 9999;">
  <tr>
    <td>
    
		<div id="nav-tree" class="side">
			<%--@include file="/viewframe/frame/search.jsp"--%>
			<div class="nav-tree-main">
			<%
				//String pid = (String)request.getAttribute("pid");
				//String isLeaf = (String)request.getAttribute("isLeaf");
				//System.out.println("mid=" + mid + ";fid=" + fid + ";pid=" + pid + ";isLeaf=" + isLeaf);
				
				FunctionManager functionManager = (FunctionManager)Global._ctx.getBean("functionManager");
				
				List<Module> modules = (List<Module>) request.getAttribute("modules");
				
				
				
				if (modules != null && modules.size()>0) {
		
					for (Module m : modules) {
						//List<Function> fs = m.getFunctions();
						List<FunctionReport> fs = functionManager.selectFunctionByModuleIdAndUserId(m.getId(), userId);
						//List<FunctionReport> fs = new ArrayList();
			%>
			<dl class="nav-report-group" id="<%=m.getId()%>_m_dl" mid="<%=m.getId()%>" mtype="<%=m.getViewType()%>">
				
				<dt class="nav-report-group-title">
					<a href="javascript:void(0);" style="display:block;">
						<span class='<%=(m.getId().equals(mid))?("ope"):("shut")%>'><%=m.getName()%></span>
					</a>
				</dt>
				
				<dd class="nav-report-list" style='<%=(m.getId() .equals(mid))?(""):("display:none")%>'>
					<ul id="<%=m.getId()%>_m_ul" mid="<%=m.getId()%>">
						<%
							if (fs != null && fs.size()>0) {
								for (FunctionReport f : fs) {
									String url = f.getActionUrl();
									if (url != null) {
										//url = URLEncoder.encode(url,"UTF-8");
									} else {
										url = "";
									}
									
						%>
						
						<c:if test="<%= f.getFunctionType() != 23 %>">
							<li style='<%=(f.getParentId() == null || (f.getParentId() != null && f.getParentId()==0))?
										(""):("padding-left:50px;")%>
								<%=(f.getIsHide() != null && f.getIsHide() == true)?("display:none;"):""%>' 
								leaf="<%=f.getIsLeaf()%>" pid="<%=f.getParentId()%>" id="<%=f.getId()%>_f_li" 
								
								class='nv-li-report <%=(f.getId().equals(fid))?("on"):("")%>' fid="<%=f.getId()%>" url="<%=url%>">
								<%-- <a style="display:none;" href='${base}/main/index.feinno?fid=<%=f.getId()%>&mid=<%=m.getId()%>'>
									<%=f.getName()%>
								</a> --%>
								<form class="linkForm" action="${base}/main/index.feinno" method="POST">
									<input type="hidden" name="fid" id="fid" value="<%=f.getId()%>">
									<input type="hidden" name="mid" value="<%=m.getId()%>">
									<input type="hidden" name="fname" id="fname" value="<%=f.getName()%>">
									<%-- <input type="hidden" name="pid" value="<%=f.getParentId()%>">
									<input type="hidden" name="isLeaf" value="<%=f.getIsLeaf()%>"> --%>
									<input type="submit" value="" style="display:none;">
									<a class="nav-title" style="" href="javascript:void(0);">
										<%=f.getName()%>
									</a>
								</form>
							</li>
						</c:if>
						<c:if test="<%= f.getFunctionType() == 23 %>">
							<li class="nv-li-user-defined" style='<%=(f.getParentId() == null)?(""):("display__:none;padding-left:50px;")%>' 
								leaf="<%=f.getIsLeaf()%>" pid="<%=f.getParentId()%>" id="<%=f.getId()%>_f_li" 
								class='nv-li-report <%=(f.getId().equals(fid))?("on"):("")%>' fid="<%=f.getId()%>" 
								url='<%=url%>'>
								<input type="hidden" name="dfid" id="dfid" value="<%=f.getId()%>">
								<input type="hidden" name="dname" id="dname" value="<%=f.getName()%>">
								<%-- <input type="hidden" name="pid" value="<%=f.getParentId()%>">
								<input type="hidden" name="isLeaf" value="<%=f.getIsLeaf()%>"> --%>
								<input type="submit" value="" style="display:none;">
								<a class="nav-title" style="" href="javascript:void(0);">
									<%=f.getName()%>
								</a>
							</li>
						</c:if>
						
						<%
								}
							} else {
								%>
						<li>
							没数据
						</li>
								<%
							}
						%>
					</ul>
				</dd>
			</dl>
			<%
					}
				}
			%>
			</div>
		</div>
		<!-- //side -->
		
		<div id="nav-tree-mini" style="background:#c5c5c5;width:5px;display:none;">&nbsp;</div>
    </td>
    <td style="vertical-align: top; padding-top:4px;">
    	<div style="background : #d5d5d5; float: left;">
    		<div id="nav-tree-arrow">
    			<div class="arrow arrow-left"></div>
    		</div>
    	</div>
    </td>
  </tr>
</table>



<script language="JavaScript">
	$(document).ready(function() {
		
		//-----------菜单树事件--------------//
		
		//--------链接跳转----------//
		$("#nav-tree").find("li.nv-li-report").bind("click", function(){
			var linkForm = $(this).find("form");
			linkForm.submit();
		});
		
		//---------目录树显示----------//
		$("#nav-tree").find("dt.nav-report-group-title").bind("click", function(){
			//$(this).next("dd.nav-report-list").show("slow");
			
			//$(this).find("a").click();
			
			var showMode = "show";
			if ($(this).next("dd.nav-report-list")[0] && 
					$(this).next("dd.nav-report-list")[0].style.display != "none") {
				// 点击当前目录
				showMode = "hide";
			}
			
			//目录显示;
			$(this).next("dd.nav-report-list").effect("blind",{mode : showMode }, 500,function(){
				
			});
			
			var groupSpanTitle = $(this).find("span");
			
			if (showMode == "show") {
				// 修改目录"+-"图标
				groupSpanTitle.removeClass("shut");
				groupSpanTitle.addClass("ope");
				
				var dls = $(this).parents("dl.nav-report-group").siblings("dl");
				
				// 修改目录"+-"图标
				var oldSpan = dls.find("dt.nav-report-group-title").find("span");
				oldSpan.removeClass("ope");
				oldSpan.addClass("shut");
				
				// 目录隐藏;
				//dls.find("dd.nav-report-list:visible").hide();
				dls.find("dd.nav-report-list:visible").effect("blind",{ mode:"hide" }, 500,function(){
					
				});
			} else {
				groupSpanTitle.removeClass("ope");
				groupSpanTitle.addClass("shut");
			}
			
		});
		
		var linkLis = $("#nav-tree").find("dd.nav-report-list").find("li:not(.on)");
		
		linkLis.bind("mouseover", function(){
			$(this).addClass("on");
		});
		
		linkLis.bind("mouseout", function(){
			$(this).removeClass("on");
		});
		
		
		
		//----------自定义报表-------------//
		<%-- $("#nav-tree").find("li.nv-li-user-defined").bind("click", function(){
			$("#vf_nav").hide();
			$("#vf_content").hide();
			
			$("#vf_user_defined").show();
			
			var dfid = $(this).find('#dfid').val();
			var dname = encodeURI($(this).find('#dname').val());
			var ralname = encodeURI("<%=realname%>");
			
			$('#dnames').val(dname);
			$('#dfids').val(dfid);
			
			var url = $(this).attr("url");			
			url += '<%="?userId=" + userId + "&mobileNo=" + mobileNo + "&c=" + c %>' ;
			url += "&dfid=" + dfid + "&dname=" + dname;
			url += '<%="&mail=" + emailAddress%>' ;
			url += "&userName=" + ralname;
			url += '<%="&privRols=" + privRols%>' ;
			$("#frmUserDefined").attr("src", url);
		}); --%>
		
		
		//--------------菜单 箭头-------------------//
		
		$("#nav-tree-arrow").find(".arrow").bind("click", function(){
			var classname  = this.className;
			if (classname.indexOf("arrow-left") != -1) {
				treeHideHandler(this);
			} else {
				treeShowHandler(this);
			}
			
			/* try {
				changeChartHandler();
			} catch(ex){} */
		});
		
		function treeHideHandler (node) {
			$(node).removeClass("arrow-left");
			$(node).addClass("arrow-right");
			
			//--设置迷你菜单条高度
			$("#nav-tree-mini").height($("#nav-tree").height());
			
			/* $("#nav-tree").hide("slide", {
				"easing" : "easeInExpo"
			}, 300, function(){
				$("#nav-tree-mini").show(200);
			}); */
			
			$("#nav-tree").hide(300, function(){
				$("#nav-tree-mini").show(200);
				
				try {
					changeChartHandler();
				} catch(ex){}
			});
		}
		
		function treeShowHandler (node) {
			$(node).removeClass("arrow-right");
			$(node).addClass("arrow-left");
			
			/* $("#nav-tree").show("slide", {
				"easing" : "easeInExpo"
			}, 300, function(){
				$("#nav-tree-mini").hide(200);
			}); */
			
			$("#nav-tree").show(300, function(){
				$("#nav-tree-mini").hide(200);
				
				try {
					changeChartHandler();
				} catch(ex){}
			});
			
			
		}
		
		
		//--判断菜单是否需要浮动--//
		window.onresize= changeNavStatusHandler;
		
		function changeNavStatusHandler () {
			//浏览器可视区宽度
			var wh = $(window).width();
			
			//var wh = document.body.offsetWidth;
			
			if (wh < 1280) {
				$("#nav").css("position", "absolute");
				$("#frm-left-td").css("float", "left");
			} else {
				$("#nav").css("position", "static");
				$("#frm-left-td").css("float", "none");
			}
			
			//alert(document.body.offsetWidth);
		}		
		changeNavStatusHandler();
		
	});
</script>
