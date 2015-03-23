/*
 * 文件名： AppContextListener.java
 * 
 * 创建日期： 2011-4-14
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.controller.helper.listener;

import java.io.IOException; 

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import com.feinno.dcs.framework.business.ifaces.IQuartzJobManager;
//import com.feinno.dcs.framework.business.ifaces.ISysDictManager;
//import com.feinno.dcs.framework.cluster.ClusterServer;
//import com.feinno.dcs.framework.cluster.IMsgReceiver;
//import com.feinno.dcs.framework.cluster.IViewReceiver;
//import com.feinno.dcs.framework.cluster.fdc.FdcMsgReceiver;
//import com.feinno.dcs.framework.cluster.fdc.FdcViewReceiver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//import com.feinno.dcs.framework.business.impl.SysDictManager;
//import com.feinno.fdc.compute.business.interfaces.task.ITaskLogManager;
//import com.feinno.fdc.compute.utils.FDCUtils;
//import com.feinno.fdc.compute.utils.hadooputil.HadoopFsInfo;
//import com.feinno.circle.framework.business.impl.RoleManager;
import com.st.Global;
import com.st.framework.special.scheduler.SchedulerMrg;
import com.st.framework.special.task.util.TaskInitialize;
import com.st.framework.utils.DWRUtil;
import com.st.framework.utils.IPUtil;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.cache.RoleRightsUtils;


/**
 * Container关闭开启的Listener
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision: 1.2 $
 *
 * @Date 下午06:01:15
 *
 * @since 2011-4-14
 */
public class AppContextListener implements ServletContextListener{
	
	/**系统调度*/
	private static SchedulerMrg scheduler = null;
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AppContextListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		if(logger.isInfoEnabled()){
			logger.info("容器启动") ;
		}
		Global._ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		Global.WEB_CONTEXT_PATH = sce.getServletContext().getContextPath();
		Global.initPath(sce.getServletContext().getRealPath("/"));
		Global.initTomcatPort();
//		RoleManager roleManager = (RoleManager) Global._ctx.getBean("roleManager");
		try {
 			logger.info("进行权限初始化");
			
 			//RoleRightsUtils.updateRoleRightsMap();
 			
			logger.info("权限初始化完成");
			

		} catch (Exception e) {
			e.printStackTrace();
		}

        logger.info("initialize Report sub system information");
        initReport(sce);

//        logger.info("initialize DC sub system information");
//        initDc(sce);
//
//        logger.info("initialize Compute sub system information");
//        initCompute(sce);
        
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	*/
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		if(logger.isInfoEnabled()){
			logger.info("容器关闭") ;
//			RoleRightsMap.removeAll();
		}		
//		try {
//			FDCUtils.getHdfsFileSystem().close();
//			 Global.hadoopHa.close();
//		} catch (IOException e) {
//			logger.error("close hdfs file system：",e);
//		}
//
//        ClusterServer server=(ClusterServer)Global._ctx.getBean("clusterServer");
//        server.stop();
	}
	

	/**
	 * 
	 * 初始化调度任务
	 * 
	 * @param schedulerMrg
	 */
	private void initScheduler(SchedulerMrg schedulerMrg)
	{

		if (schedulerMrg == null)
		{
			schedulerMrg = SchedulerMrg.getInstance();
			schedulerMrg.start();
			logger.info("initScheduler SUCCESS");
		}
		TaskInitialize taskInitialize = (TaskInitialize)Global._ctx.getBean("taskInit");
		taskInitialize.init(schedulerMrg);
	}
	
//	/**
//	 * 初始化系统配置信息;
//	 */
	private void initSystemConfig () {
		/**
		 * 应用服务域名
		 */
		Global.APP_DOMAIN_NAME = LoadConfigUtils.getInstance().getAppDomainName();
		
		/**
		 * 应用Title
		 */
		Global.APP_TITLE = LoadConfigUtils.getInstance().getAppTitle();
		
		Global.TOMCAT_PORT = LoadConfigUtils.getInstance().getTomcatPort();
		
	}
	
	/**
	 * 初始化白名单IP地址
	 */
	private void initIp () {
		if(logger.isInfoEnabled()){
			logger.info("开始加载IP") ;
		}
		IPUtil.loadIp();
	}

    /**
     * 初始化报表子系统
     */
    private void initReport(ServletContextEvent sce){
//        initScheduler(scheduler);

        //初始化系统配置信息;
        initSystemConfig();

        //注册系统信息
        logger.info("Register host informations");
//        ISqlHostManager hostManager=(ISqlHostManager)Global._ctx.getBean("sqlHostManager");
//        hostManager.updateHostRegTime(new Date());
//
        //初始化Ip地址;
        //initIp();
        
      //初始化 dwr本地文件;
//        String basePath = "http://127.0.0.1:" + Global.TOMCAT_PORT + "/" + sce.getServletContext().getContextPath();		
//        DWRUtil.Servlet2File(basePath, Global.ROOT);
    }

//    /**
//     * 初始化计算子系统
//     */
//    private void initCompute(ServletContextEvent sce){
//        if("true".equals( Global.IS_NOTIFY)){
//            ITaskLogManager taskLogManager = (ITaskLogManager)Global._ctx.getBean("taskLogManager");
//            try{
//                taskLogManager.updateLog(null, ITaskLogManager.REBOOT,null);
//            }catch(Exception e){
//                logger.error("运行状态更新异常!",e);
//            }
//        }
//
//
//        String url =  Global.getProperties("ha.zookeeper.quorum");
//        if(logger.isInfoEnabled()){
//            logger.info("ha.zookeeper.quorum="+url);
//        }
//
//        if(null!=url && !"".equals(url.trim())){
//            Global.hadoopHa = new HadoopFsInfo(url.split(","));
//            Global.hadoopHa.changeNn();
//            Global.HDFS_FILE_SYSTEM = FDCUtils.getHdfsFileSystem();
//        }
//    }

//    /**
//     * 初始化调度子系统
//     */
//    private void initDc(ServletContextEvent sce){
//          /*
//        Scheduler sch=(Scheduler)Global._ctx.getBean("stdScheduler");
//
//        boolean started=false;
//        try{
//            if(sch.isStarted()){
//                logger.info("dcGlobalScheduler started...");
//                IQuartzJobManager quartzJobManager=(IQuartzJobManager)Global._ctx.getBean("quartzJobManager");
//                quartzJobManager.initQuartzJob();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.error("Scheduler is error");
//        }
//        logger.info("Scheduler status:"+started);
//*/
//
//        ISysDictManager dm=(SysDictManager)Global._ctx.getBean("sysDictManager");
//        try{
//            Global.DICT=dm.getDictMap();
//            Global.LANG_MAP=dm.getLangMap();
//            sce.getServletContext().setAttribute("_dict", Global.DICT);
//            sce.getServletContext().setAttribute("_langMap",Global.LANG_MAP);
//        }catch (Exception e){
//            logger.error("initializing Global ERROR!");
//        }
//
//        ClusterServer server=(ClusterServer)Global._ctx.getBean("clusterServer");
//        IMsgReceiver msgReceiver=(FdcMsgReceiver)Global._ctx.getBean("fdcMsgReceiver");
//        IViewReceiver viewReceiver=(FdcViewReceiver)Global._ctx.getBean("fdcViewReceiver");
//        server.registerViewReceiver(viewReceiver);
//        server.registerMsgReceiver(msgReceiver);
//        try{
//            server.start();
//        }catch (Exception e){
//            logger.error("***************cluster server start error***************");
//            e.printStackTrace();
//        }
//    }
}
