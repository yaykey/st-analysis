/**
 * MobileUtil.java
 * com.feinno.circle.framework.utils
 * Function： 
 *
 *   ver     date      author               department
 * ──────────────────────────────────————————————————————————————
 *   V1.0   2013-11-21    yzy		  DATA BUSINESS DEPARTMENT
 *
 * Copyright (c) 2013, Feinno Communication Tech All Rights Reserved.
 */

package com.st.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:MobileUtil Reason: 手机号码验证工具
 * 
 * @author yzy
 * @version
 * @since Ver 1.0.0
 * @Date 2013 2013-11-21 下午04:41:42
 * @see
 */
public class MobileUtil {

	/**
	 * 
	 * isMobileNO:
	 * 
	 * 是否是手机号
	 *
	 * @param mobiles
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-21
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * 
	 * isCMCCMobileNO:
	 * 
	 * 是否是移动手机号
	 *
	 * @param mobiles
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-21
	 */
	public static boolean isCMCCMobileNO(String mobiles) {
		Pattern p = Pattern
		.compile("^(134|135|136|137|138|139|150|151|152|157|158|159|182|183|187|188|147)\\d{8}$");

		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * 
	 * isCUMobileNO:
	 * 
	 * 是否是联动手机号
	 *
	 * @param mobiles
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-21
	 */
	public static boolean isCUMobileNO(String mobiles) {
		Pattern p = Pattern
		.compile("^(130|131|132|155|156|185|186|145)\\d{8}$");

		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * 
	 * isCTMobileNO:
	 * 
	 * 是否是电信手机号
	 *
	 * @param mobiles
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-11-21
	 */
	public static boolean isCTMobileNO(String mobiles) {
		Pattern p = Pattern
		.compile("^(133|1349|153|180|189)\\d{8}$");

		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

}
