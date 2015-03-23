//
//package com.st.framework.utils.cache;
//
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
////import com.st.framework.module.Function;
//
//
//
//public class RoleRightsMap {
//
////	private static final ConcurrentHashMap<Object,List<Function>> ROLE_RIGHTS_MAP = new ConcurrentHashMap<Object, List<Function>>() ;
//	
//	/**
//     * 获得Map中所有Obj的数量
//     *
//     * @return Map中所有Obj的数量
//     */
//    public static long getObjCount(){
//    	return ROLE_RIGHTS_MAP.size();
//    }
//
//    /**
//     * 向缓存中增加一个节点，如果该节点的Key已经存在则覆盖原来的节点
//     *
//     * @param key 键值
//     * @param value 值
//     */
//    public static synchronized void put(Object key, List<Function> value){
//    	ROLE_RIGHTS_MAP.put(key, value);
//    }
//
//    /**
//	 * 从缓存中获得一个节点，获得不到返回null。
//	 *
//	 * @param key 键值
//	 * @return 获得的节点或null
//	 */
//	public static List<Function> get(Object key){
//		return (List<Function>) ROLE_RIGHTS_MAP.get(key);
//	}
//
//	/**
//	 * 从缓存中删除一个节点
//	 *
//	 * @param key 键值
//	 */
//	public static synchronized void remove(Object key){
//		ROLE_RIGHTS_MAP.remove(key);
//	}
//
//	/**
//	 * 删除缓存的所有节点
//	 *
//	 */
//	public static synchronized void removeAll(){
//		ROLE_RIGHTS_MAP.clear() ;
//	}
//}
