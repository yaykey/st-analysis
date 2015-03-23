
package com.st.framework.special.scheduler;

import java.util.Date;

/**
 * 任务周期接口,要实现next()方法
 * 
 * @author Winson
 * 
 */
public interface SchedulerNext
{
	public static String PER_YEAR = "Y";

	public static String PER_MONTH = "T";

	public static String PER_WEEK = "W";

	public static String PER_DAY = "D";

	public static String PER_HOUR = "H";

	public static String PER_MINUTE = "M";

	public static String PER_SECOND = "S";
	
	public static String PER_HALF_MINUTE = "L";

	public Date next();
}
