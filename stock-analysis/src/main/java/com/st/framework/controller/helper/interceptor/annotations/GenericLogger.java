/*
 * 文件名： GenericLogger.java
 * 
 * 创建日期： 2012-3-17
 *
 * 原始作者: <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 */
package com.st.framework.controller.helper.interceptor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.st.framework.controller.helper.interceptor.enums.OperateMark;



/**
 * 相关日志记录使用到的注解类型;
 * 
 *
 * @author <a href="yangzhenyu@feinno.com">yangzhenyu</a>
 *
 * @version $Revision: 1.1 $
 *
 * @since 2012-3-17
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface GenericLogger {
	/*
	 * 描述
	 */
	String operateDescription() default "";
	
//	/*
//	 * 是否扩展
//	 */
//	boolean isExtend() default false;
//	/*
//	 * 是否存在继续记录日志;(当业务执行到某个逻辑的时候，存在逻辑否情况，不修改数据直接跳出方法)
//	 */
//	boolean isRollback() default false;
	
	/**
	 * 是否记录日志;
	 * 默认记录,当为false时不进行记录;
	 */
	boolean isGenericLogger() default true;
	
	/**
	 * 是否记录 client 端信息;
	 */
	boolean isUserAgent() default true;
	
	/**
	 * 是否记录批量操作数据记录数（条数）;
	 */
	boolean isOperateRecords() default true;
	
	/**
	 * 是否记录IP;
	 */
	boolean isRealIp() default true;
	
	/*
	 * 是否记录action 
	 */
	boolean isActionURL () default true;
	
	/**
	 * 是否记录class名称;
	 */
	boolean isClass () default true;
	
	/**
	 * 是否记录method名称;
	 */
	boolean isMethod () default true;
	
	
	/**
	 * 操作标识
	 */
	OperateMark operateMark() default OperateMark.OPERATE_DEFAULT;
}
