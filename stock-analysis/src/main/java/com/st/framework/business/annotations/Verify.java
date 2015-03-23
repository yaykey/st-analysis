/**
 * @(#)IAuthoriseAware.java  2011-5-26
 */
package com.st.framework.business.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 用于识别需要权限校验的类或方法，默认需要校验
 * 
 * 注解 <code>Verify</code>
 * 
 * @author wangwenyao@feinno.com
 * @version 2011-5-26
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Verify {
	
	/**
	 * 
	 * value: true 需要验证;false不需要验证
	 *
	 * @return  
	 * @return boolean  
	 * @throws 
	 * @since 　Ver 1.1
	 * DATE: 2013-3-18
	 */
	public boolean value() default true;
	
}
