/**
 * @(#)ConventionAction.java  2011-4-27
 */
package com.st.framework.controller.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.st.framework.business.impl.fact.FactProxyManager;
import com.st.framework.controller.UserSessionInfo;
import com.st.framework.persistence.mapper.stock.FactDownloadFileConfigMapper;




/**
 * 
 * ClassName:ConventionAction
 * Reason:	 
 *
 * @author   yangzhenyu
 * @version  
 * @since    Ver 1.0.0
 * @Date	 2012	2012-12-27		下午03:19:41
 * @see
 */
@Namespace("/manager")
@InterceptorRef("common-params")
@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.Exception", result = "exception") })
@Results( {
		@Result(name = "error", location = "/manager/commons/error.jsp"),
		@Result(name = "invalid.token", location = "/manager/commons/error.jsp"),
		@Result(name = "exception", location = "/manager/commons/error.jsp"),
		@Result(name = "login", location = "/login.jsp"),
		@Result(name = "noauthority", location = "/manager/commons/noauthority.jsp"),
		@Result(name = "nosession", location = "/manager/commons/nosession.jsp")})
public class ConventionAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8718182728581398351L;

	@Autowired
	protected FactDownloadFileConfigMapper factDownloadFileConfigMapper;
	
	@Autowired
	protected FactProxyManager factProxyManager;
	
	
	/**
	 * ERROR 错误页面
	 */
	protected static final String ERROR = "error";

	/**
	 * EXCEPTION 异常页面
	 */
	protected static final String EXCEPTION = "exception";
	
	/**
	 * 功能主页面
	 */
	protected static final String EDIT = "edit";

	/**
	 * 功能主页面
	 */
	protected static final String MAIN = "main";
	
	
	/**
	 * 过期页面
	 */
	protected static final String NO_SESSION = "nosession";

	/**
	 * 无权限页面
	 */
	protected static final String NO_AUTHORITY = "noauthority";

	
	public void setUserSessionInfo (UserSessionInfo userSessionInfo) {
		getSession().setAttribute("userSessionInfo", userSessionInfo);
	}
	
	/**
	 * 用户session信息;
	 * 
	 * getUserSessionInfo:
	 *
	 * @return  
	 * @return UserSessionInfo  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-1-18
	 */
	public UserSessionInfo getUserSessionInfo () {
		return (UserSessionInfo)getSession().getAttribute("userSessionInfo");
	}
	
	/**
	 * 当前页;
	 */
	protected int page=1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * tip 提示消息
	 */
	private String tip;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
//	public String verifyFunctionAuthority (Integer fid) {
//		
//		if (fid == null) {
//			return null;
//		}
//		
//		if (this.getUserSessionInfo() == null) {
//			return NO_SESSION;
//		}
//		
//		//权限验证部分
//		List<Role> roles = this.getUserSessionInfo().getRoles();
//				
//		boolean verify = false;
//		if(roles != null && !roles.isEmpty()){
//			for(Role role: roles){
//				List<Function> functions = RoleRightsMap.get(role.getId());
//				if (functions != null) {
//					for (Function f : functions) {
//						if (f.getId().equals(fid)) {
//							verify = true;
//							break;
//						}
//					}
//				}
//			}
//			if (!verify) {
//				return NO_AUTHORITY;
//			}
//			
//		} else {
//			return NO_AUTHORITY;
//		}
//		
//		return null;
//	}
}
