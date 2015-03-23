<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.net.URLEncoder"%>
<%@ page import="com.st.framework.module.*"%>
<%@ page import="com.st.Global"%>
<%@ page import="com.st.viewframe.controller.vo.FunctionReport"%>
<%@ page import="com.st.framework.business.impl.FunctionManager"%>
<%@ page import="com.st.framework.controller.UserSessionInfo"%>

			<li class="selected"><a
					href="javascript:void(0)" url ="/manager/report/loadReport.feinno" id="61" fflag="1">应用概况</a></li>
			<%
				FunctionManager functionManager = (FunctionManager)Global._ctx.getBean("functionManager");
				List<Module> modules = (List<Module>) request.getAttribute("modules");
				UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute("userSessionInfo");
				//Integer userId = userSessionInfo.getUserInfo().getId();
				Integer userId=3;
				if (modules != null && modules.size()>0) {
					for (Module m : modules) {
						List<FunctionReport> fs = functionManager.selectFunctionByModuleIdAndUserId(m.getId(), userId);
			%>
			<li class="">
				<a class="recordable open" mflag="1" href="javascript:void(0)" mid="<%=m.getId()%>"><%=m.getName()%></a>
				<ul class="nav-two">
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
							<li class="" style='<%=(f.getIsHide() != null && f.getIsHide() == true)?("display:none;"):""%>' 
								id="<%=f.getId()%>_f_li">
								<a href="javascript:void(0)" id="<%=f.getId()%>" fflag="1" url="<%=f.getActionUrl()%>"><%=f.getName()%></a><span
								class="normal">&nbsp;</span>
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
				</li>
			<%
					}
				}
			%>