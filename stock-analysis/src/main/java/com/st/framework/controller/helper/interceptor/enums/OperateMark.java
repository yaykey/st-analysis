/*
 * 文件名： OperateMark.java
 * 
 * 创建日期： 2012-3-17
 *
 * 原始作者: <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 */
package com.st.framework.controller.helper.interceptor.enums;
/**
 * 操作日志枚举标识;
 * 
 *
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 * @version $Revision: 1.1 $
 *
 * @since 2012-3-17
 */
public enum OperateMark {
	/**默认值*/
	OPERATE_DEFAULT("default"),
	
	/*
	 * 默认功能;
	 */
	OPERATE_DEFAULT_LIST("default_list"),
	OPERATE_DEFAULT_ADD("default_add"),
	OPERATE_DEFAULT_REMOVE("default_remove"),
	OPERATE_DEFAULT_DELETE("default_delete"),
	OPERATE_DEFAULT_SAVE("default_save"),
	OPERATE_DEFAULT_INSERT("default_insert"),
	OPERATE_DEFAULT_UPDATE("default_update"),
	OPERATE_DEFAULT_SAVEORUPDATE("default_saveOrUpdate"),
	OPERATE_DEFAULT_VERIFY("default_verify"),
	
	/*
	 * IP功能;
	 */
	IP_LIST("ip_list"),
	IP_REMOVE("ip_remove"),
	IP_REFRESH("ip_refresh"),
	IP_SAVEORUPDATE("ip_saveOrUpdate"),
	
	/*
	 * 忘记密码;
	 */
	FORGET_PASSWORD_EMAIL("forget_password_email"),
	FORGET_PASSWORD_RESET("forget_password_reset"),
	FORGET_PASSWORD_MODIFY("forget_password_modify"),
	
	/*
	 * 登录;
	 */
	LOGIN_LOGIN("login_login"),
	LOGIN_VALIDATE("login_validate"),
	LOGIN_LOGOUT("login_logout"),
	LOGIN_SMS_VALIDATE("login_sms_validate"),
	LOGIN_SMS_ACQUIRE("login_sms_acquire"),
	
	/*
	 * 前台模块操作;
	 */
	VF_MODULE_UPDATE_STATUS("vf_module_updateStatus"),
	VF_MODULE_ADD("vf_module_add"),
	VF_MODULE_REMOVE("vf_module_remove"),
	VF_MODULE_MOVE("vf_module_move"),
	VF_MODULE_SORT("vf_module_sort"),
	
	/*
	 * 调度
	 */
	DC_JOB_DESIGN("dc_job_design"),
	DC_JOB_INS("dc_job_ins"),
	
	
	/**
	 * 用户访问报表行为.导航
	 */
	RPT_NAV_INDEX("rpt_nav_index"),
	
	/**
	 * 用户访问报表行为.下钻
	 */
	RPT_NAV_CHANGE("rpt_nav_change"),
	
	BLOCK_END("");
	
	
	private OperateMark(){};
	private OperateMark(String operate){
    	this.operate = operate;
    }
    
    private String operate;
    

    public String toString(){
    	return this.operate;
    }
}
