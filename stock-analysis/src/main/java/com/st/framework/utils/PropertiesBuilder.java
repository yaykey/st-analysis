package com.st.framework.utils;
///**
// * @(#)IdsBuilder.java  2011-5-13
// */
//package com.feinno.circle.framework.utils;
//
//import java.util.Set;
//
//import com.feinno.framework.model.CommonObject;
//import com.feinno.framework.model.PersistentObject;
//
///**
// * 以字符串形式返回集合中相同的属性 类 <code>PropertiesBuilder</code>
// * 
// * @author wangwenyao@feinno.com
// * @version 2011-5-13
// */
//public class PropertiesBuilder {
//
//	public static final String COMMA = ",";
//	
//	public static final String LINE = "\n";
//
//	/**
//	 * 获取集合中每一个元素的identifier属性，返回以","分隔的identifier组成字符串
//	 * 
//	 * @param persistentObjects
//	 *            实现了PersistentObject接口的类
//	 * @return 以","分隔的identifier组成字符串
//	 */
//	public static String buildIds(Set<? extends PersistentObject> persistentObjects) {
//		return buildIds(persistentObjects, COMMA);
//	}
//
//	/**
//	 * 获取集合中每一个元素的identifier属性，返回以separator分隔的identifier组成字符串
//	 * 
//	 * @param persistentObjects
//	 *            实现了PersistentObject接口的类
//	 *@param separator
//	 *            分隔符 ，如果为null，则分隔符使用","
//	 * @return 以separator分隔的identifier组成字符串
//	 */
//	public static  String buildIds(Set<? extends PersistentObject> persistentObjects,
//			String separator) {
//		StringBuilder builder = new StringBuilder();
//		separator = (separator == null ? COMMA : separator);
//		for (PersistentObject persistentObject : persistentObjects) {
//			builder.append(persistentObject.getIdentifier()).append(COMMA);
//		}
//		String ids = null;
//		if (persistentObjects.size() > 0) {
//			ids = builder.substring(0, builder.length() - separator.length());
//		}
//		return ids;
//	}
//
//	/**
//	 * 获取集合中每一个元素的name属性，返回以","分隔的name组成字符串
//	 * 
//	 * @param commonObjects
//	 *            实现了CommonObject接口的类
//	 * @return 以","分隔的name组成字符串
//	 */
//	public static String buildNames(Set<? extends CommonObject> commonObjects) {
//		return buildNames(commonObjects, COMMA);
//	}
//
//	/**
//	 * 
//	 * 获取集合中每一个元素的name属性，返回以separator分隔的name组成字符串
//	 * 
//	 * @param commonObjects
//	 *            实现了CommonObject接口的类
//	 * @param separator
//	 *            分隔符 ，如果为null，则分隔符使用","
//	 * @return 以separator分隔的name组成字符串
//	 */
//	public static String buildNames(Set<? extends CommonObject> commonObjects,
//			String separator) {
//		StringBuilder builder = new StringBuilder();
//		separator = (separator == null ? COMMA : separator);
//		for (CommonObject commonObject : commonObjects) {
//			builder.append(commonObject.getName()).append(separator);
//		}
//		String names = null;
//		if (commonObjects.size() > 0) {
//			names = builder.substring(0, builder.length() - separator.length());
//		}
//		return names;
//	}
//
//}
