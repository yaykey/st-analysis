package com.st.framework.special.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 鉴别用户名,密码,JavaMail辅助类
 * 
 * @author 
 * 
 */
public class MailAuthenticator extends Authenticator
{
	/** 用户名 */
	private String strUser;

	/** 密码 */
	private String strPwd;

	/**
	 * 构造方法
	 * 
	 * @param user
	 *            名字
	 * @param password
	 *            密码
	 */
	public MailAuthenticator(String user, String password)
	{
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(strUser, strPwd);
	}

}
