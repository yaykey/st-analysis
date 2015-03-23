/*
 * 文件名： SMSNotify.java
 * 
 * 创建日期： 2009-9-29
 *
 * Copyright(C) 2009, Ryoma.
 *
 * 原始作者: <a href="mailto:lemom8000@gmail.com">Ryoma</a>
 *
 */
package com.st.framework.utils.sms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送短息类
 * 
 * @author <a href="mailto:lemom8000@gmail.com">Ryoma</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 2009-9-29
 */
public class SMSNotify {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SMSNotify.class);

	public final static String SMS_URL = "http://192.168.249.43/SMSAlertService/SMSNotify.aspx";

	/**
	 * 内容格式为mobile1,mobile2...mobileN@smsbody
	 * 
	 * @param content
	 * @return
	 */
	public static boolean send(String content) {
		try {
			HttpChannel http = HttpChannel.valueOf(SMS_URL);
			String msg = http.sendPostRequest(content);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("send msg content=" + msg);
			}
		} catch (Exception e) {
			LOGGER.error("send(String)", e);
			return false;
		}
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(content);
		}
		return true;
	}

	/**
	 * 发送短息
	 * 
	 * @param smsbody
	 *            短息内容
	 * @param mobiles
	 *            手机号
	 * @return
	 */
	public static boolean send(String smsbody, String... mobiles) {

		if (smsbody == null || mobiles == null || mobiles.length == 0)
			return false;
		StringBuilder content = new StringBuilder();
		for (String mobile : mobiles) {
			content.append(mobile).append(",");
		}
		content.deleteCharAt(content.length() - 1).append("@").append(smsbody);

		// 测试
		LOGGER.error("发短信,smsbody:" + smsbody + ", content:"
				+ content.toString());

		// return true;

		return send(content.toString());

	}

	/**
	 * 发送短息
	 * 
	 * @param smsbody
	 *            短息内容
	 * @param mobiles
	 *            手机号
	 * @return
	 */
	public static boolean send(String smsbody, List<String> mobiles) {
		if (smsbody == null || mobiles == null || mobiles.size() == 0)
			return false;
		StringBuilder content = new StringBuilder();
		for (String mobile : mobiles) {
			content.append(mobile).append(",");
		}
		content.deleteCharAt(content.length() - 1).append("@").append(smsbody);
		return send(content.toString());
	}
}
