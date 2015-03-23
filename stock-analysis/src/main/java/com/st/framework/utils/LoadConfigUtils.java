package com.st.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




/**
 * 读取系统的配置文件system_config.xml,单例模式.
 *
 * <p>类名:LoadConfigUtils.java</p>
 * <p>描述信息:</p>
 */
public class LoadConfigUtils
{

	// 操作日志对象
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(LoadConfigUtils.class);

	// 实例
	/** The load config. */
	public static LoadConfigUtils loadConfig = null;

	/** Mail属性. */
	/** SMTP服务器地址 */
	private String smtpHost = null;

	/** 登陆SMTP服务器用户名. */
	private String smtpUser = null;

	/** 登陆SMTP密码. */
	private String smtpPassword = null;

	/** 邮件发送人地址. */
	private String mailFrom = null;

	/** The root. */
	protected Element root;

	/** 本机节点名字. */
	private String nodeName = null;
	
	/** 应用服务域名. */
	private String appDomainName = null;
	
	/** 应用服Title */
	private String appTitle = null;
	
	
	/** 端口号. */
	private String tomcatPort = null;
	
//	/** 图片服务器地址. */
//	private String imageServer = null;
//	
//	/** 图片服务器端口. */
//	private String imageServerPort = null;
//	
//	/** 图片服务器存放路径. */
//	private String imagePath = null;
	
//	/**
//	 * 图片服务器的用户名
//	 */
//	private String ftpUserName = null;
	
	/** 上传文件夹. */
	private String downloadFilePath = null;
	
	
	/** 负载地址列表;. */
	private String[] loadBalancePath = null;
	
	
	
//	/**  solr zookeeper 地址. */
//	private String zookeeperUrl;
	
//	/** bmp定时任务启动服务器. */
//	private String bmpTimeServerPath;
	
	
//	public String getBmpTimeServerPath() {
//		return bmpTimeServerPath;
//	}
//
//	public void setBmpTimeServerPath(String bmpTimeServerPath) {
//		this.bmpTimeServerPath = bmpTimeServerPath;
//	}

	

	

	/**
	 * 获得实例的方法.
	 * 
	 * @return single instance of LoadConfigUtils
	 */
	public synchronized static LoadConfigUtils getInstance()
	{

		if (loadConfig == null)
		{
			loadConfig = new LoadConfigUtils();
			logger.info("Initializing system_config.xml" );
		}
		return loadConfig;
	}

	

	/**
	 * 私有的构造方法.
	 */
	private LoadConfigUtils() {

		load();
	}

	/**
	 * 从system_config.xml里加载数据
	 * 
	 */
	public void load()
	{

		// 构造一个SAXReader对象
		SAXReader reader = new SAXReader();
		try
		{
			// 获得xml文件的Document对象
			Document doc = reader.read(this.getClass().getResourceAsStream(
					"/system_config.xml"));
			// 获得Document对象的根节点
			root = doc.getRootElement();
			// 获得系统属性
			this.smtpHost = root.element("defaultSmtpHost").getTextTrim();
			logger.info("Initializing defaultSmtpHost : " + this.smtpHost);
			this.smtpUser = root.element("defaultSmtpUser").getTextTrim();
			logger.info("Initializing defaultSmtpUser : " + this.smtpUser);
			this.smtpPassword = root.element("defaultSmtpPassword")
					.getTextTrim();
			logger.info("Initializing defaultSmtpPassword : "
					+ this.smtpPassword);
			this.mailFrom = root.element("defaultMailFrom").getTextTrim();
			logger.info("Initializing defaultMailFrom : " + this.mailFrom);
			this.nodeName = root.element("nodeName").getTextTrim();
			logger.info("Initializing nodeName : " + this.nodeName);
			
			//应用服务域名 
			this.appDomainName = root.element("appDomainName").getTextTrim();
			logger.info("Initializing appDomainName : " + this.appDomainName);
			
			//应用Title 
			this.appTitle = root.element("appTitle").getTextTrim();
			logger.info("Initializing appTitle : " + this.appTitle);
			
			
			//端口号
			this.tomcatPort = root.element("tomcatPort").getTextTrim();
			logger.info("Initializing tomcatPort : " + this.tomcatPort);
			
//			this.imageServer = root.element("imageServer").getTextTrim();
//			logger.info("Initializing imageServer ：" +this.imageServer);
//			
//			this.imageServerPort = root.element("imageServerPort").getTextTrim();
//			logger.info("Initializing imageServerPort ：" +this.imageServerPort);
			
//			this.imagePath = root.element("imagePath").getTextTrim();
//			logger.info("Initializing imagePath ：" +this.imagePath);
			
//			this.ftpUserName = root.element("ftpUserName").getTextTrim();
//			
//			this.ftpPassword = root.element("ftpPassword").getTextTrim();
			
			this.downloadFilePath = root.element("downloadFilePath").getTextTrim();
			
			
			
			//负载地址列表
			String loadBalancePathStrs = root.element("loadBalancePath").getTextTrim();
			if (loadBalancePathStrs != null) {
				this.loadBalancePath = loadBalancePathStrs.split(",");
			}
			
			
			
		} catch (DocumentException e)
		{
			logger.error("Initializing system_config.xml  Error : "
					+ e.getMessage());
		}
	}

	/**
	 * 获得系统的邮件发送地址.
	 * 
	 * @return the 邮件发送人地址
	 */
	public String getMailFrom()
	{

		return mailFrom;
	}

	/**
	 * 设置系统的邮件发送地址.
	 * 
	 * @param mailFrom
	 *            the new 邮件发送人地址
	 */
	public void setMailFrom(String mailFrom)
	{

		this.mailFrom = mailFrom;
	}

	/**
	 * 获得SMTP服务器的地址.
	 * 
	 * @return the mail属性
	 */
	public String getSmtpHost()
	{

		return smtpHost;
	}

	/**
	 * 设置SMTP服务器的地址.
	 * 
	 * @param smtpHost
	 *            the new mail属性
	 */
	public void setSmtpHost(String smtpHost)
	{

		this.smtpHost = smtpHost;
	}

	/**
	 * 获得SMTP服务器的登陆用户密码.
	 * 
	 * @return the 登陆SMTP密码
	 */
	public String getSmtpPassword()
	{

		return smtpPassword;
	}

	/**
	 * 设置SMTP服务器的登陆用户密码.
	 * 
	 * @param smtpPassword
	 *            the new 登陆SMTP密码
	 */
	public void setSmtpPassword(String smtpPassword)
	{

		this.smtpPassword = smtpPassword;
	}

	/**
	 * 获得SMTP服务器的登陆用户名.
	 * 
	 * @return the 登陆SMTP服务器用户名
	 */
	public String getSmtpUser()
	{

		return smtpUser;
	}

	/**
	 * 设置SMTP服务器的登陆用户名.
	 * 
	 * @param smtpUser
	 *            the new 登陆SMTP服务器用户名
	 */
	public void setSmtpUser(String smtpUser)
	{

		this.smtpUser = smtpUser;
	}

	/**
	 * 测试方法.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args)
	{

		LoadConfigUtils.getInstance();
	}

	/**
	 * Gets the 本机节点名字.
	 * 
	 * @return the 本机节点名字
	 */
	public String getNodeName()
	{

		return nodeName;
	}

	/**
	 * Sets the 本机节点名字.
	 * 
	 * @param nodeName
	 *            the new 本机节点名字
	 */
	public void setNodeName(String nodeName)
	{

		this.nodeName = nodeName;
	}

	/**
	 * Gets the 应用服务域名.
	 * 
	 * @return the 应用服务域名
	 */
	public String getAppDomainName() {
		return appDomainName;
	}

	/**
	 * Sets the 应用服务域名.
	 * 
	 * @param appDomainName
	 *            the new 应用服务域名
	 */
	public void setAppDomainName(String appDomainName) {
		this.appDomainName = appDomainName;
	}

	/**
	 * Gets the 端口号.
	 * 
	 * @return the 端口号
	 */
	public String getTomcatPort() {
		return tomcatPort;
	}

	/**
	 * Sets the 端口号.
	 * 
	 * @param tomcatPort
	 *            the new 端口号
	 */
	public void setTomcatPort(String tomcatPort) {
		this.tomcatPort = tomcatPort;
	}

	

	
	/**
	 * Gets the 负载地址列表;.
	 * 
	 * @return the 负载地址列表;
	 */
	public String[] getLoadBalancePath() {
		return loadBalancePath;
	}

	/**
	 * Sets the 负载地址列表;.
	 * 
	 * @param loadBalancePath
	 *            the new 负载地址列表;
	 */
	public void setLoadBalancePath(String[] loadBalancePath) {
		this.loadBalancePath = loadBalancePath;
	}

	

	/**
	 * @return the appTitle
	 */
	public String getAppTitle() {
		return appTitle;
	}

	/**
	 * @param appTitle the appTitle to set
	 */
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}

	

	
	

	
}
