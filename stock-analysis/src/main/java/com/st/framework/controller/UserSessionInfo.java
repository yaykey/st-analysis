package com.st.framework.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.st.analysis.module.PubUser;
//import com.st.framework.module.Module;
//import com.st.framework.module.Role;

//import com.feinno.circle.framework.module.Department;
//import com.feinno.circle.framework.module.Module;
//import com.feinno.circle.framework.module.Role;
//import com.feinno.circle.framework.module.User;
//import com.feinno.circle.framework.module.DimProvince;






/**
 * ClassName:UserSessionInfo Reason: 用户session信息;.
 * 
 * @author yzy
 * @version
 * @since Ver 1.0.0
 * @Date 2013 2013-1-18 下午01:32:10
 * @see
 */
public class UserSessionInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 478840799736959781L;

	/** userInfo 用户信息. */
//	private PubUser userInfo;
	
	/** 用户登录唯一id. */
	private String loginId;
	
	/** 用户物理id. */
	private Integer userId;
	
//	/** 用户名;. */
//	private String name;

	/** roles 用户所拥有的所有角色. */
//	private List<Role> roles;
//
//	/** modules 用户角色所能访问的模块. */
//	private List<Module> modules;

//	/** currentRole 当前角色. */
//	private Role role;

//	/** 用户所属部门;. */
//	private Department department;
//
//	/** 用户所属省份;. */
//	private DimProvince dimProvince;
//	
//	/** 省份ID;. */
//	private Integer provinceId;
//	
//	/** 默认推送数据省份ID;. */
//	private Integer defaultDataProvinceId;

	/**
	 * Instantiates a new user session info.
	 */
	public UserSessionInfo() {
	}

//	/**
//	 * Instantiates a new user session info.
//	 * 
//	 * @param userInfo
//	 *            用户信息
//	 */
//	public UserSessionInfo(PubUser userInfo) {
//		setUserInfo(userInfo);
//	}
//
//	/**
//	 * 获取用户信息.
//	 * 
//	 * @return 用户信息
//	 */
//	public PubUser getUserInfo() {
//		return userInfo;
//	}
//
//	/**
//	 * 设置用户信息,默认取第一个用户组作为当前所属用户组.
//	 * 
//	 * @param userInfo
//	 *            用户信息
//	 */
//	public void setUserInfo(PubUser userInfo) {
//		if (userInfo != null) {
//			this.userInfo = userInfo;
//
////			this.loginId = this.userInfo.getLoginId();
////			this.userId = this.userInfo.getId();
////			this.name = this.userInfo.getName();
//			
//			this.loginId = this.userInfo.getUsername();
//			this.userId = this.userInfo.getId();
//			
//			
////			if (userInfo.getRole() != null) {
////				this.setRole(userInfo.getRole());
////			}
//
//			if (userInfo.getRoles() != null) {
//				this.setRoles(userInfo.getRoles());
//			}
//			
//			if (this.roles == null) {
//				this.roles = new ArrayList<Role>();
//			}
//			
////			if (this.role != null) {
////				boolean have = false;
////				for (Role r : this.roles) {
////					if (r.equals(this.role)) {
////						have = true;
////						break;
////					}
////				}
////				
////				if (have == false) {
////					this.roles.add(this.role);
////				}
////			}
//			
////			if (userInfo.getDepartmentId() != null) {
////				this.department = userInfo.getDepartment();
////			}
////			
////			if (userInfo.getProvinceId() != null) {
////				this.dimProvince = userInfo.getDimProvince();
////				this.provinceId = userInfo.getProvinceId();
////			}
//			
//			
//
//		}
//	}
//
////	/**
////	 * 获取当前角色.
////	 * 
////	 * @return 当前角色
////	 */
////	public Role getRole() {
////		return role;
////	}
////
////	/**
////	 * 设置当前角色.
////	 * 
////	 * @param role
////	 *            当前角色
////	 */
////	public void setRole(Role role) {
////		this.role = role;
////	}
//
//	/**
//	 * 获取用户拥有的所有角色.
//	 * 
//	 * @return the roles
//	 */
//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	/**
//	 * 设置用户拥有的所有角色，默认取第一个角色作为当前角色.
//	 * 
//	 * @param roles
//	 *            用户拥有的所有角色
//	 */
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
////		if (roles != null && !roles.isEmpty()) {
////			setRole(roles.get(0));
////		}
//	}
//
//	/**
//	 * Gets the modules 用户角色所能访问的模块.
//	 * 
//	 * @return the modules 用户角色所能访问的模块
//	 */
//	public List<Module> getModules() {
//		return modules;
//	}
//
//	/**
//	 * Sets the modules 用户角色所能访问的模块.
//	 * 
//	 * @param modules
//	 *            the new modules 用户角色所能访问的模块
//	 */
//	public void setModules(List<Module> modules) {
//		this.modules = modules;
//	}
////
////	/**
////	 * Gets the 用户所属部门;.
////	 * 
////	 * @return the 用户所属部门;
////	 */
////	public Department getDepartment() {
////		return department;
////	}
////
////	/**
////	 * Sets the 用户所属部门;.
////	 * 
////	 * @param department
////	 *            the new 用户所属部门;
////	 */
////	public void setDepartment(Department department) {
////		this.department = department;
////	}
////
////	/**
////	 * Gets the 用户所属省份;.
////	 * 
////	 * @return the 用户所属省份;
////	 */
////	public DimProvince getDimProvince() {
////		return dimProvince;
////	}
////
////	/**
////	 * Sets the 用户所属省份;.
////	 * 
////	 * @param dimProvince
////	 *            the new 用户所属省份;
////	 */
////	public void setDimProvince(DimProvince dimProvince) {
////		this.dimProvince = dimProvince;
////	}
//
//	/**
//	 * Gets the 用户登录唯一id.
//	 * 
//	 * @return the 用户登录唯一id
//	 */
//	public String getLoginId() {
//		return loginId;
//	}
//
//	/**
//	 * Sets the 用户登录唯一id.
//	 * 
//	 * @param loginId
//	 *            the new 用户登录唯一id
//	 */
//	public void setLoginId(String loginId) {
//		this.loginId = loginId;
//	}
//
//	/**
//	 * Gets the 用户物理id.
//	 * 
//	 * @return the 用户物理id
//	 */
//	public Integer getUserId() {
//		return userId;
//	}
//
//	/**
//	 * Sets the 用户物理id.
//	 * 
//	 * @param userId
//	 *            the new 用户物理id
//	 */
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
////	/**
////	 * Gets the 用户名;.
////	 * 
////	 * @return the 用户名;
////	 */
////	public String getName() {
////		return name;
////	}
////
////	/**
////	 * Sets the 用户名;.
////	 * 
////	 * @param name
////	 *            the new 用户名;
////	 */
////	public void setName(String name) {
////		this.name = name;
////	}
//
////	/**
////	 * Gets the 省份ID;.
////	 * 
////	 * @return the 省份ID;
////	 */
////	public Integer getProvinceId() {
////		return provinceId;
////	}
////
////	/**
////	 * Sets the 省份ID;.
////	 * 
////	 * @param provinceId
////	 *            the new 省份ID;
////	 */
////	public void setProvinceId(Integer provinceId) {
////		this.provinceId = provinceId;
////	}
////
////	
////	/**
////	 * Gets the 默认推送数据省份ID;.
////	 * 
////	 * @return the 默认推送数据省份ID;
////	 */
////	public Integer getDefaultDataProvinceId() {
////		return defaultDataProvinceId;
////	}
////
////	
////	/**
////	 * Sets the 默认推送数据省份ID;.
////	 * 
////	 * @param defaultDataProvinceId
////	 *            the new 默认推送数据省份ID;
////	 */
////	public void setDefaultDataProvinceId(Integer defaultDataProvinceId) {
////		this.defaultDataProvinceId = defaultDataProvinceId;
////	}

	

}
