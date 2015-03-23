/**
 * @(#)IEncryptor.java  2011-4-25
 */
package com.st.framework.utils.encrypt;

/**
 * 类 <code>IEncryptor</code>
 * 
 * @author wangwenyao@feinno.com
 * @version 2011-4-25
 */
public interface IEncryptor {

	/**
	 * 给指定字符串加密，并返回加密后的密文。
	 * 
	 * @param inStr
	 *            要被加密的字符串
	 * @return 加密后的字符串
	 */
	public String encrypt(String inStr);

	/**
	 * 给指定字符串解密，并返回解密后的原文。
	 * 
	 * @param inStr
	 *            要被解密的字符串
	 * @return 解密后的字符串
	 */
	public String decrypt(String inStr);

}
