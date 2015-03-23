/**
 * @(#)EncryptorMD5Impl.java  2011-4-25
 */
package com.st.framework.utils.encrypt.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.st.framework.utils.encrypt.IEncryptor;


/**
 * 类 <code>EncryptorMD5Impl</code>
 * @author wangwenyao@feinno.com
 * @version 2011-4-25
 */
public class EncryptorMD5Impl implements IEncryptor {

	/* (non-Jsdoc)
	 * @see com.feinno.framework.utils.encrypt.IEncryptor#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String inStr) {

		return null;
	}

	/* (non-Jsdoc)
	 * @see com.feinno.framework.utils.encrypt.IEncryptor#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String inStr) {

		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
			byte[] digest = md.digest(inStr.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
			String str = "";
			String tempStr = "";
			for (int i = 1; i < digest.length; i++) {
				tempStr = (Integer.toHexString(digest[i] & 0xff));
				if (tempStr.length() == 1) {
					str = str + "0" + tempStr;
				} else {
					str = str + tempStr;
				}
			}
			outStr = str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return outStr;
	}

}
