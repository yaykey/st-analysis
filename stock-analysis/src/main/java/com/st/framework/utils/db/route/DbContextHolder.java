package com.st.framework.utils.db.route;

/**
 * 数据源切换工具类.
 * 
 * @author yangzhenyu
 *
 * @date 下午12:21:24
 * 
 * @since 2015年1月21日
 *
 */
public class DbContextHolder {
	
	/** The Constant contextHolder. */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


	/**
	 * Sets the db type.
	 *
	 * @param dbType the new db type
	 */
	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

//	/**
//	 * Sets the write db type.
//	 */
//	public static void setWriteDbType() {
//		contextHolder.set("write");
//	}
//
//	/**
//	 * Sets the read db type.
//	 */
//	public static void setReadDbType() {
//		contextHolder.set("read");
//	}
	
	public static void setDefaultDbType() {
		contextHolder.set("ds");
	}
	
	public static void setGSZDbType() {
		contextHolder.set("gsz");
	}
	
	public static void setGSHDbType() {
		contextHolder.set("gsh");
	}

	/**
	 * Gets the db type.
	 *
	 * @return the db type
	 */
	public static String getDbType() {
		String str = (String) contextHolder.get();
		if (null == str || "".equals(str) || "ds".equals(str)) {
			str = "ds";
		} else if ("gsz".equals(str)){
			str = "gsz";
		} else if ("gsh".equals(str)){
			str = "gsh";
		}
		return str;
	}

	/**
	 * Clear db type.
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
