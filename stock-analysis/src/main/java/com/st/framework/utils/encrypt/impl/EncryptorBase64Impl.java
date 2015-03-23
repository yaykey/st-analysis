
package com.st.framework.utils.encrypt.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.st.framework.utils.encrypt.IEncryptor;

/**
 * 
 * ClassName:EncryptorBase64Impl
 * Reason:	 base64加密解密
 *
 * @author   yzy
 * @version  
 * @since    Ver 1.0.0
 * @Date	 2013	2013-4-17		上午11:09:15
 * @see
 */
public class EncryptorBase64Impl implements IEncryptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(EncryptorBase64Impl.class);

	private BASE64Encoder encoder = new BASE64Encoder();

	private BASE64Decoder decoder = new BASE64Decoder();

	/*
	 * (non-Jsdoc)
	 * 
	 * @see
	 * com.feinno.framework.utils.encrypt.IEncryptor#decrypt(java.lang.String)
	 */
	@Override
	public String decrypt(String inStr) {
		
		try {
			String returnString = new String(decoder.decodeBuffer(inStr));
			
			return returnString;
		}
		catch (IOException e) {
			logger.error("decrypt(String) - exception ", e);
			
		}

		return null;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see
	 * com.feinno.framework.utils.encrypt.IEncryptor#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String inStr) {
		
		try {
			String returnString = encoder.encode(inStr.getBytes());
			
			return returnString;
		}
		catch (Exception e) {
			logger.error("encrypt(String) - exception ", e);

		}
		return null;
	}

}
