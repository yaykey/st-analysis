/*
 * File Name：RoleRightsUtils.java
 * Package Name：com.feinno.circle.framework.utils.cache
 *
 * Function： TODO 
 *
 *   ver     date      author               department
 * ──────────────────────────────────————————————————————————————
 *   V1.0   2013-3-13    yzy		  DATA BUSINESS DEPARTMENT
 *
 * Copyright (c) 2013, Feinno Communication Tech All Rights Reserved.
*/

package com.st.framework.utils.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

//import com.feinno.circle.framework.business.impl.RoleManager;
import com.st.Global;


/**
 * ClassName:RoleRightsUtils
 * Reason:	 TODO
 *
 * @author   yzy
 * @version  V1.0.0
 * @since    1.0
 * @Date	 2013-3-13		下午07:12:31
 *
 */
public class RoleRightsUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(RoleRightsUtils.class);

	
	/**
	 * 从数据库更新数据;
	 * 
	 * update:
	 *  
	 * @return void  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-3-13
	 */
	public static void updateRoleRightsMap () {
		
		logger.info("更新全局功能权限");
		
//		RoleManager roleManager = (RoleManager) Global._ctx.getBean("roleManager");
//		
//		try {
//			List<Role> roles = roleManager.selectAllRoleWithFunction();
//// 			List<Role> roles = roleManager.selectByExample(new RoleExample());
//			
//			RoleRightsMap.removeAll();
//			
//			for (Role role : roles) {
//				List<Function> functions = role.getFunctions();
//				
//				if (functions != null) {
//					RoleRightsMap.put(role.getId(),functions);
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		logger.info("更新全局功能权限结束");
	}
	
}

