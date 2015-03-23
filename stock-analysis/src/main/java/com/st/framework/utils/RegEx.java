package com.st.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	/**
	 * 匹配正则表达式
	 * @param regEx 正则表达式
	 * @param str 字符串
	 * @return
	 */
	public static boolean chkRegEx(String regEx,String str){
		if(null==regEx||regEx.trim().length()<1||null==str||str.trim().length()<1)
			return true;
		Pattern pat = Pattern.compile(regEx);  
		Matcher mat = pat.matcher(str);  
		return mat.find();
	}
}
