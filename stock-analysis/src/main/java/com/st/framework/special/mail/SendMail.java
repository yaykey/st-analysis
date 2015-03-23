package com.st.framework.special.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.framework.utils.LoadConfigUtils;




/**
 * 发送邮件
 *
 * <p>类名：SendMail.java</p>
 * <p>描述信息：</p>
 * @创建日期 2011-6-14
 */
public class SendMail
{

	// 操作日志对象
	private static final Log logger = LogFactory.getLog(SendMail.class);

	/** Mail常规属性 */
	// 发件人地址
	protected String from = null;
	// 默认发件人
	protected String defaultMailFrom = LoadConfigUtils.getInstance().getMailFrom();
	// Smtp服务器地址
	protected String smtpHost = null;
	// 默认Smtp服务器地址
	protected String defautlSmtpHost = LoadConfigUtils.getInstance().getSmtpHost();
	// 用户名
	protected String user = null;
	// 默认用户名
	protected String defautlSmtpUser = LoadConfigUtils.getInstance().getSmtpUser();
	// 密码
	protected String passwd = null;
	// 默认密码
	protected String defautlSmtpPassword = LoadConfigUtils.getInstance().getSmtpPassword();

	/** Mail其它属性 */
	// 发送集合
	protected ArrayList tos = new ArrayList();
	// 抄送集合
	protected ArrayList ccs = new ArrayList();
	// 标题
	protected String title = null;
	// 内容
	protected String content = null;
	// 类型
	protected String type = null;
	// 附件集合
	protected ArrayList files = new ArrayList();
	// 是否成功
	protected boolean success = false;

	/** Get,Set方法 */
	public ArrayList getCcs()
	{
		return ccs;
	}
	public void setCcs(ArrayList ccs)
	{
		this.ccs = ccs;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getDefaultMailFrom()
	{
		return defaultMailFrom;
	}

	public void setDefaultMailFrom(String defaultMailFrom)
	{
		this.defaultMailFrom = defaultMailFrom;
	}

	public String getDefautlSmtpHost()
	{
		return defautlSmtpHost;
	}

	public void setDefautlSmtpHost(String defautlSmtpHost)
	{
		this.defautlSmtpHost = defautlSmtpHost;
	}

	public String getDefautlSmtpPassword()
	{
		return defautlSmtpPassword;
	}

	public void setDefautlSmtpPassword(String defautlSmtpPassword)
	{
		this.defautlSmtpPassword = defautlSmtpPassword;
	}

	public String getDefautlSmtpUser()
	{
		return defautlSmtpUser;
	}

	public void setDefautlSmtpUser(String defautlSmtpUser)
	{
		this.defautlSmtpUser = defautlSmtpUser;
	}

	public ArrayList getFiles()
	{
		return files;
	}

	public void setFiles(ArrayList files)
	{
		this.files = files;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getPasswd()
	{
		return passwd;
	}

	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}
	public String getSmtpHost()
	{
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}
	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public ArrayList getTos()
	{
		return tos;
	}

	public void setTos(ArrayList tos)
	{
		this.tos = tos;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	/** 其它方法 */

	/**
	 * 添加一个收件人
	 * 
	 * @param to
	 */
	public void addTo(String to)
	{
		tos.add(to);
	}

	/**
	 * 添加一个抄送
	 * 
	 * @param cc
	 */
	public void addCC(String cc)
	{
		if (cc != null)
		{
			ccs.add(cc);
		}

	}

	/**
	 * 添加一个附件
	 * 
	 * @param fileName
	 */
	public void addFile(String fileName)
	{
		files.add(fileName);
	}

	/** 其它方法 */
	protected InternetAddress[] getInternetAddress(ArrayList addr)
	{
		int size = addr.size();
		InternetAddress[] ret = new InternetAddress[size];
		for (int i = 0; i < size; i++)
		{
			try
			{
				ret[i] = new InternetAddress((String) addr.get(i));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}
	protected String getFileName(String fileName)
	{
		int pos = fileName.lastIndexOf("/");
		if (pos >= 0)
			fileName = fileName.substring(pos + 1);
		pos = fileName.lastIndexOf("\\");
		if (pos >= 0)
			fileName = fileName.substring(pos + 1);
		return encodeString(fileName);
	}

	protected String encodeString(String str)
	{
		try
		{
			String rs = new String(str.getBytes("GB2312"), "ISO8859-1");
			return rs;
		} catch (Exception e)
		{
			return null;
		}
	}

	protected MimeMessage getMessage(Session session) throws Exception
	{
		try
		{
			MimeMessage message = new MimeMessage(session);

			// 给消息对象设置发件人/收件人/主题/发信时间

			InternetAddress ifrom = null;
			if (from == null)
			{
				ifrom = new InternetAddress(this.defaultMailFrom);
			} else
			{
				ifrom = new InternetAddress(this.from);
			}
			message.setFrom(ifrom);
			message.setRecipients(Message.RecipientType.TO, getInternetAddress(tos));
			message.setRecipients(Message.RecipientType.CC, getInternetAddress(ccs));
			message.setSubject(title);
			message.setSentDate(new Date());

			// 新建一个MimeMultipart对象用来存放多个BodyPart对象
			Multipart mm = new MimeMultipart();

			// 设置信件文本内容
			BodyPart mdp = new MimeBodyPart();
			if (type != null)
			{
				mdp.setContent(content, type);
			} else
			{
				mdp.setContent(content, null);
			}

			mm.addBodyPart(mdp);
			// 设置附件
			int size = files.size();
			for (int i = 0; i < size; i++)
			{
				String fileName = (String) files.get(i);
				mdp = new MimeBodyPart();
				if(fileName!= null)
				{
					FileDataSource fds = new FileDataSource(fileName);
					DataHandler dh = new DataHandler(fds);
					mdp.setFileName(getFileName(fileName));
					mdp.setDataHandler(dh);
					mm.addBodyPart(mdp);
				}
			}

			message.setContent(mm);
			message.saveChanges();

			return message;
		} catch (Exception e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * 发送邮件,尝试发3次
	 * 
	 * @throws Exception
	 */
	public void send() throws Exception
	{
		int MAX_TIMES = 3;
		success = false;
		for (int i = 0; i < MAX_TIMES; i++)
		{
			try
			{
				sendCore();
			} catch (Exception e)
			{
				if (i == MAX_TIMES - 1)
					throw e;
				continue;
			}
			this.success = true;
			break;
		}
		if (success)
		{
			logger.info("Send Mail Success !");
		} else
		{
			logger.info("Send Mail Error !");
		}
	}

	public void sendCore() throws Exception
	{
		try
		{
			// 创建一个Properties对象
			Properties props = new Properties();

			// 设置需要用户名,密码验证
			String smtpAuth = "true";

			// 设置smtpHost地址
			if (this.smtpHost == null)
			{
				props.put("mail.smtp.host", this.defautlSmtpHost);
			} else
			{
				props.put("mail.smtp.host", this.smtpHost);
			}

			// 设置需要验证
			props.put("mail.smtp.auth", "true");

			// 获得一个Session会话对象
			Session s = null;
			if ("true".equals(smtpAuth))
			{
				// smtp服务器需要验证，用MyAuthertiactor来创建mail session
				MailAuthenticator auth = null;
				if (this.user == null || this.passwd == null)
				{
					auth = new MailAuthenticator(this.defautlSmtpUser, this.defautlSmtpPassword);
				} else
				{
					// smtp服务器需要验证，用MyAuthertiactor来创建mail session
					auth = new MailAuthenticator(this.user, this.passwd);
				}
				s = Session.getInstance(props, auth);
			} else
			{
				s = Session.getInstance(props);
			}

			// 设置debug为false
			// s.setDebug(true);
			s.setDebug(false);

			// 创建一MimeMessage对象
			MimeMessage message = getMessage(s);

			Transport transport = s.getTransport("smtp");
			if (this.smtpHost == null)
			{
				transport.connect(this.defautlSmtpHost, user, passwd);
			} else
			{
				transport.connect(this.smtpHost, user, passwd);
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
	}
}
