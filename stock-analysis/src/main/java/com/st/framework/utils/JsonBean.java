package com.st.framework.utils;

import net.sf.json.JSONObject;
/**
 * 获取json值
 * @author lilinbing
 *
 */
public class JsonBean {
	public static Long getLong(JSONObject info,String key){
		if(info.containsKey(key)&&info.getString(key)!=null&&info.getString(key).trim().length()>0)
			return info.getLong(key);
		else
			return null;
	}
	public static String getString(JSONObject info,String key){
		if(info.containsKey(key)&&info.getString(key)!=null)
			return info.getString(key);
		else
			return null;
	}
	public static Integer getInteger(JSONObject info,String key){
		if(info.containsKey(key)&&info.getString(key)!=null&&info.getString(key).trim().length()>0)
			return info.getInt(key);
		else
			return null;
	}
}
