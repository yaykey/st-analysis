package com.st.framework.special.task;
//package com.feinno.circle.framework.special.task;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Serializable;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.feinno.circle.Global;
//import com.feinno.circle.framework.business.impl.DepartmentManager;
//import com.feinno.circle.framework.business.impl.MemcacheReportConditionManager;
//import com.feinno.circle.framework.business.impl.MemcacheReportDateManager;
//import com.feinno.circle.framework.business.impl.MemcacheReportManager;
//import com.feinno.circle.framework.business.impl.RoleManager;
//import com.feinno.circle.framework.business.impl.UserManager;
//import com.feinno.circle.framework.exceptions.ApplicationBaseException;
//import com.feinno.circle.framework.module.Department;
//import com.feinno.circle.framework.module.MemcacheReport;
//import com.feinno.circle.framework.module.Role;
//import com.feinno.circle.framework.module.User;
////import com.feinno.circle.framework.module.example.DepartmentExample;
//import com.feinno.circle.framework.module.example.MemcacheReportExample;
//import com.feinno.circle.framework.module.example.RoleExample;
//import com.feinno.circle.framework.module.example.UserExample;
//import com.feinno.circle.framework.utils.cache.MemcachedUtils;
//import com.feinno.circle.framework.business.impl.WidgetManager;
//import com.feinno.circle.framework.module.Widget;
//import com.feinno.circle.framework.module.example.WidgetExample;
//import com.feinno.circle.framework.utils.DateUtil;
//
//
///**
// * 报表缓存定时刷新的任务
// *
// * <p>类名：MemCacheTask.java</p>
// */
//
//public class MemCacheTask extends TaskBase
//{
//
//	/** 常量 */
//	private static final Log logger = LogFactory.getLog(MemCacheTask.class);
//	
//
//	public void exec() throws ApplicationBaseException, MalformedURLException
//	{
//		if (logger.isDebugEnabled()) {
//			logger.debug("exec() - start");
//		}
//
//		MemCacheTask memCacheTask = new MemCacheTask();
//		logger.info("开始刷报表缓存!");
//		
//		// 取出所有角色
//		List<Role> roleManagerList=new ArrayList<Role>();
//		try {
//			roleManagerList =  memCacheTask.getRoleManagerBean().selectByExample(new RoleExample());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		// 取出所有部门
//		List<Department> departmentList=new ArrayList<Department>();
//		try {
//			departmentList =  memCacheTask.getDepartmentManagerBean().selectByExample(new DepartmentExample());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		int allReport = 0;
//		int errorReport = 0;
//		int allkey = 0;
//		
//		try{
////			String port_string = ":"+Global.TOMCAT_PORT;
//			
//			//取出所有的缓存报表
//			List<MemcacheReport> memCacheReportList = memCacheTask.getMemCacheReportManagerBean().
//					selectByExample(new MemcacheReportExample());
//			if(memCacheReportList != null && memCacheReportList.size()>0){
//				MemcacheReport memCacheReport = new MemcacheReport();
//				List<String> errorKeyList = new ArrayList<String>();
//				
//				for(int j=0;j<memCacheReportList.size();j++){     // 所有需要刷缓存的function
//					for(int m=0; m<roleManagerList.size(); m++){  // 所有角色
//						for(int n=0; n<departmentList.size(); n++){  // 所有部门
//							try{
//								memCacheReport = memCacheReportList.get(j);
//								
//								WidgetExample example = new WidgetExample();
//								WidgetExample.Criteria criteria = example.createCriteria();
//								criteria.andFunctionIdEqualTo(new Integer(memCacheReport.getReportId()));
//								criteria.andLevelEqualTo((short) 1);
//								example.setOrderByClause("sort");
//								List<Widget> widgetList = memCacheTask.getWidgetManagerBean().selectByExample(example);
//								
//								
//								StringBuffer buffer = new StringBuffer();
//								
//								UserExample userExample = new UserExample();
//					        	UserExample.Criteria criteria1 = userExample.createCriteria();
//								criteria1.andRoleIdEqualTo(new Integer(roleManagerList.get(m).getId()));
//								criteria1.andDepartmentIdEqualTo(departmentList.get(n).getId().toString());
//					        	List<User> userList=memCacheTask.getUserManagerBean().selectByExample(userExample);
//								if(userList.size()>0){
//									for(int i=0;i<widgetList.size();i++){
//										
////										BufferedReader is = null;
////										URLConnection conn = null;
////										InputStreamReader inputStreamReader = null;
////										URL urlOpen = null;
////										
////										String url = 
////												"http://localhost"
////												+port_string
////												+Global.WEB_CONTEXT_PATH
////												+widgetList.get(i).getActionUrl()+"&functionId="+memCacheReport.getReportId()+
////												"&widgetId="+widgetList.get(i).getId()+"&userId="+
////												userList.get(0).getId()+"&taskFlag=1";
////										urlOpen = new URL(url.replace(" ", ""));
////							            conn = urlOpen.openConnection();
////										
////							            inputStreamReader = new InputStreamReader(conn.getInputStream(),"UTF-8");
////							            is = new BufferedReader(inputStreamReader);   
////							           
////							            String str = "";
////							            while((str = is.readLine()) != null){
////							                buffer.append(str+"\n");
////							            }
////							           
////							            is.close();
////							            inputStreamReader.close();
//										
//										this.parseURLContent(buffer,
//												widgetList.get(i).getActionUrl(), memCacheReport.getReportId(), 
//												widgetList.get(i).getId(), userList.get(0).getId());
//									}
//									
////									String replaceBase = "http://localhost:8080";
////						            if (Global.TOMCAT_PORT != null) {
////						            	replaceBase = replaceBase + ":" + Global.TOMCAT_PORT;
////						            }
//						            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//						            String time = df.format(new Date());
//						            String key = "function_"+memCacheReport.getReportId()+
//		        		                    "_role_"+roleManagerList.get(m).getId()+
//		        		                    "_department_"+departmentList.get(n).getId()+
//		        		                    "_time_"+time;
//						            
//						            String yesterday = DateUtil.getYesterday(time);
//						            String yesterdayKey = "function_"+memCacheReport.getReportId()+
//		        		                    "_role_"+roleManagerList.get(m).getId()+
//		        		                    "_department_"+departmentList.get(n).getId()+
//		        		                    "_time_"+yesterday;
//						            
//						            MemcachedUtils.INSTANCE.remove(yesterdayKey); // 删除昨天的缓存
//						            String content = buffer.toString();
//						            
//						            if (content != null && !"".equals(content.trim())
//						            		&& content.indexOf("Exception") == -1) {
//						            	MemcachedUtils.INSTANCE.put(key,buffer.toString());
//						            } else {
//						            	errorKeyList.add(key);
//						            	logger.warn("获得数据失败->key=" + key);
//						            }
//						            
//						            
////						            MemcachedUtils.INSTANCE.put(key,buffer.toString().replaceAll("http://[\\w\\.]*[:]?[\\d]*", replaceBase));
//									
//									buffer = null;
//									allkey++;
//								}
//								
//							}catch(Exception ee){
//								logger.warn("exec() - exception ignored", ee);
//								logger.error("handExec() - exception ignored", ee);
//								logger.error("handExec() - exceptionfunctionid=" +memCacheReportList.get(j).getReportId()+
//										                   " roleid="+roleManagerList.get(m).getId()+
//										                   " departid="+departmentList.get(n).getId());
//								errorReport++;
//							}
//						}
//					}
//					memCacheReport = null;
//					allReport++;
//				}
//				
//				
//				logger.info("exec->errorKeyList=" + errorKeyList);
//			}
//			
//			memCacheReportList = null;
//		}catch(Exception e){
//			logger.warn("exec() - exception ignored", e);
//		}
//		memCacheTask = null;
//		logger.info("报表缓存自动刷新完毕!");
//		logger.info("报表总数量"+allReport);
//		logger.info("所有key的数目"+allkey);
//		logger.info("刷新错误的key数目"+errorReport);
//		
//	}
//	
//	/**
//	 * 手工刷报表缓存
//	 * @param memCacheIds 缓存表ID集合
//	 * @return	都刷成功返回0，失败返回1
//	 * @throws ApplicationBaseException
//	 */
//	public int handExec(String memCacheReportIds)
//	{
//		if (logger.isDebugEnabled()) {
//			logger.debug("handExec() - start");
//		}
//
//		int count=0;
//		MemCacheTask memCacheTask = new MemCacheTask();
//		logger.info("开始手动刷报表缓存!");
//		
//		// 取出所有角色
//		List<Role> roleManagerList=new ArrayList<Role>();
//		try {
//			roleManagerList =  memCacheTask.getRoleManagerBean().selectByExample(new RoleExample());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		// 取出所有部门
//		List<Department> departmentList=new ArrayList<Department>();
//		try {
//			departmentList =  memCacheTask.getDepartmentManagerBean().selectByExample(new DepartmentExample());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		int allReport = 0;
//		int allkey = 0;
//		int errorReport = 0;
//		try{
////			String port_string = ":"+Global.TOMCAT_PORT;
//			
//			//所有的缓存报表
//			
//			String[] memCacheIdStr = memCacheReportIds.split(";");
//			
//			List<String> errorKeyList = new ArrayList<String>();
//			
//			if(memCacheIdStr.length>0){
//				
//				for(int j=0;j<memCacheIdStr.length;j++){     // 所有需要刷缓存的function
//					for(int m=0; m<roleManagerList.size(); m++){  // 所有角色
//						for(int n=0; n<departmentList.size(); n++){  // 所有部门
//							try{
//								
//								WidgetExample example = new WidgetExample();
//								WidgetExample.Criteria criteria = example.createCriteria();
//								criteria.andFunctionIdEqualTo(new Integer(memCacheIdStr[j]));
//								criteria.andLevelEqualTo((short) 1);
//								example.setOrderByClause("sort");
//								List<Widget> widgetList = memCacheTask.getWidgetManagerBean().selectByExample(example);
//								
//								UserExample userExample = new UserExample();
//					        	UserExample.Criteria criteria1 = userExample.createCriteria();
//								criteria1.andRoleIdEqualTo(new Integer(roleManagerList.get(m).getId()));
//								criteria1.andDepartmentIdEqualTo(departmentList.get(n).getId().toString());
//					        	List<User> userList=memCacheTask.getUserManagerBean().selectByExample(userExample);
//					        	
//					        	StringBuffer buffer = new StringBuffer();
//								if(userList.size()>0){
//									allReport++;
//									for(int i=0;i<widgetList.size();i++){
//										this.parseURLContent(
//												buffer,
//												widgetList.get(i).getActionUrl(), memCacheIdStr[j], 
//												widgetList.get(i).getId(), userList.get(0).getId());
////										URL urlOpen = null;
////										BufferedReader is = null;
////										URLConnection conn = null;
////										InputStreamReader inputStreamReader = null;
////										
////										String url = 
////												"http://localhost"
////												+port_string
////												+Global.WEB_CONTEXT_PATH
////												+widgetList.get(i).getActionUrl()+"&functionId="+memCacheIdStr[j]+
////												"&widgetId="+widgetList.get(i).getId()+"&userId="+
////												userList.get(0).getId()+"&taskFlag=1";
////										urlOpen = new URL(url.replace(" ", ""));
////							            conn = urlOpen.openConnection();
////										
////							            inputStreamReader = new InputStreamReader(conn.getInputStream(),"UTF-8");
////							            is = new BufferedReader(inputStreamReader);   
////							           
////							            String str = "";
////							            while((str = is.readLine()) != null){
////							                buffer.append(str+"\n");
////							            }
////							            
////							            is.close();
////							            inputStreamReader.close();
//									}
//									
////									String replaceBase = "http://localhost:8080";
////						            if (Global.TOMCAT_PORT != null) {
////						            	replaceBase = replaceBase + ":" + Global.TOMCAT_PORT;
////						            }
//						            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//						            String time = df.format(new Date());
//						            String key = "function_"+memCacheIdStr[j]+
//		        		                    "_role_"+roleManagerList.get(m).getId()+
//		        		                    "_department_"+departmentList.get(n).getId()+
//		        		                    "_time_"+time;
//						            
//						            String yesterday = DateUtil.getYesterday(time);
//						            String yesterdayKey = "function_"+memCacheIdStr[j]+
//		        		                    "_role_"+roleManagerList.get(m).getId()+
//		        		                    "_department_"+departmentList.get(n).getId()+
//		        		                    "_time_"+yesterday;
//						            
//						            MemcachedUtils.INSTANCE.remove(yesterdayKey); // 删除昨天的缓存
//						            
//						            String content = buffer.toString();
//						            
//						            @SuppressWarnings("unused")
//									int r = content.indexOf("Exception");
//						            
//						            if (content != null && !"".equals(content.trim())
//						            		&& content.indexOf("Exception") == -1) {
//						            	MemcachedUtils.INSTANCE.put(key,buffer.toString());
//						            } else {
//						            	errorKeyList.add(key);
//						            	logger.warn("获得数据失败->key=" + key);
//						            }
//						            
//						            
////						            MemcachedUtils.INSTANCE.put(key,buffer.toString().replaceAll("http://[\\w\\.]*[:]?[\\d]*", replaceBase));
//									
//									buffer = null;
//									allkey++;
//								}
//								
//							}catch(Exception ee){
//								logger.warn("handExec() - exception ignored", ee);
//								logger.error("handExec() - exception ignored", ee);
//								logger.error("handExec() - exceptionfunctionid=" +memCacheIdStr[j]+
//										                   "roleid"+roleManagerList.get(m).getId()+
//										                   "departid="+departmentList.get(n).getId());
//								errorReport++;
//							}
//						}
//					}
//					count++;
//				}
//				
//				
//				logger.info("handExec->errorKeyList=" + errorKeyList);
//			}
//			
//		}catch(Exception e){
//			logger.warn("handExec() - exception ignored", e);
//		}
//		memCacheTask = null;
//		
//		
//		
//		logger.info("报表缓存手动刷新完毕!总数=="+ allReport);
//		logger.info("所有key的数目=="+ allkey);
//		logger.info("错误报表数=="+ errorReport);
//		
//		return memCacheReportIds.split(";").length-count;
//	}
//	
//	/**
//	 * 
//	 * getContent:
//	 * 
//	 * 获得报表内容
//	 *
//	 * @param actionUrl
//	 * @param functionId
//	 * @param widgetId
//	 * @param userId
//	 * @return  
//	 * @return StringBuffer  
//	 * @throws 
//	 * @since 　Ver 1.1
//	 * DATE: 2013-9-6
//	 */
//	private void parseURLContent (StringBuffer buffer, String actionUrl,Serializable functionId, Serializable widgetId, Serializable userId) {
//		if (logger.isInfoEnabled()) {
//			logger.info("getURLContent() - start");
//		}
//
//		
//		String port_string = ":" + Global.TOMCAT_PORT;
//		URL urlOpen = null;
//		BufferedReader is = null;
//		URLConnection conn = null;
//		InputStreamReader inputStreamReader = null;
//
//		String url = "http://localhost" + port_string + Global.WEB_CONTEXT_PATH
//				+ actionUrl + "&functionId=" + functionId + "&widgetId="
//				+ widgetId + "&userId=" + userId + "&taskFlag=1";
//		try {
//			url = url.replace(" ", "");
//			urlOpen = new URL(url);
//		}
//		catch (MalformedURLException e) {
//			logger.error("getURLContent()->MalformedURLException:", e);
//
//		}
//		
//
//		try {
//			conn = urlOpen.openConnection();
//			inputStreamReader = new InputStreamReader(conn.getInputStream(),
//					"UTF-8");
//			is = new BufferedReader(inputStreamReader);
//
//			String str = "";
//			while ((str = is.readLine()) != null) {
//				buffer.append(str + "\n");
//			}
//			
//			is.close();
//			inputStreamReader.close();
//			
//		}
//		catch (UnsupportedEncodingException e) {
//			logger.error("getURLContent()->UnsupportedEncodingException:", e);
//
//		}
//		catch (IOException e) {
//			logger.error("getURLContent()->IOException->url=" + url);
//			logger.error("getURLContent()->IOException", e);
//			
//		}
//		
//		if (logger.isInfoEnabled()) {
//			logger.info("getURLContent() - end");
//		}
//		
//	}
//	
////	public void exec() throws ApplicationBaseException
////	{
////		if (logger.isDebugEnabled()) {
////			logger.debug("exec() - start");
////		}
////
////		MemCacheTask memCacheTask = new MemCacheTask();
////		LoadReportMemUtil loadReportMemUtil = new LoadReportMemUtil();
////		logger.info("开始刷报表缓存!");
////		try{
////			String port_string = ":"+Global.TOMCAT_PORT;
////			if(Global.TOMCAT_PORT==80)
////				port_string = "";
////			
////			//取出所有的缓存报表
////			List memCacheReportList =  memCacheTask.getMemCacheReportManagerBean().
////					selectByExample(new MemcacheReportExample());
////			if(memCacheReportList != null && memCacheReportList.size()>0)
////			{
////				Iterator memCacheReportIt = memCacheReportList.iterator();
////				MemcacheReport memCacheReport = new MemcacheReport();
////				MemcacheReportDate memCacheReportDate = new MemcacheReportDate();
////				String url = "";
////				int count_ = 1;
////				while(memCacheReportIt.hasNext())
////				{
////					try{
////						memCacheReport = (MemcacheReport)memCacheReportIt.next();
////						
////						MemcacheReportDateExample memcacheReportDateExample = new MemcacheReportDateExample();
////						memcacheReportDateExample.createCriteria().andMemcacheReportIdEqualTo(memCacheReport.getId());
////						
////						List memCacheReportDateList =  memCacheTask.getMemCacheReportDateManagerBean().
////								selectByExample(memcacheReportDateExample);
////						if(memCacheReportDateList != null && memCacheReportDateList.size()>0)
////						{
////							
////							Iterator  memCacheReportDateIt = memCacheReportDateList.iterator();
////							//取出每一张缓存报表
////							while(memCacheReportDateIt.hasNext())
////							{
////								//组装每一个报表的URL
////								memCacheReportDate = (MemcacheReportDate)memCacheReportDateIt.next();
//////								url.delete(0, url.length());
////								if((memCacheReportDate.getReportType()==1 && memCacheReport.getDayRefleshFlag()==1) ||
////									(memCacheReportDate.getReportType()==4 && memCacheReport.getWeekRefleshFlag()==1)	||
////									(memCacheReportDate.getReportType()==2 && memCacheReport.getMonthRefleshFlag()==1)	)
////								{
////									if(memCacheReportDate != null)
////									{
////										url = "http://localhost"+port_string
////											+Global.WEB_CONTEXT_PATH
////											+memCacheReport.getReportModelPath()+"?";
////	//										+Global.NAMESPACE_REPORT
////	//										+"/commons/r-report-common.jsp?";
////										if(count_==1)//
////										{
////											for(int j=0; j<10 ; j++)
////											{
////												loadReportMemUtil.loadReportHtmlNotCache(""+memCacheReport.getReportId()
////														, ""+memCacheReportDate.getReportType(), memCacheReport.getReportRaqName(), url);
////											}
////										}
////										loadReportMemUtil.loadReportHtml(""+memCacheReport.getReportId()
////												, ""+memCacheReportDate.getReportType(), memCacheReport.getReportRaqName(), url);
////										
////										count_ += 1;
////									}
////								}
////							}
////							
////						}
////						memCacheReportDateList = null;
////						
////					}catch(Exception ee)
////					{
////						count_ += 1;
////						logger.warn("exec() - exception ignored", ee);
////						
////					}
////				}
////				memCacheReportDate = null;	
////				memCacheReport = null;
////				url = null;
////				
////			}
////			
////			memCacheReportList = null;
////		}catch(Exception e)
////		{
////			logger.warn("exec() - exception ignored", e);
////			
////		}
////		memCacheTask = null;
////		loadReportMemUtil = null;
//////		System.gc();
////		logger.info("报表缓存自动刷新完毕!");
////		
////	}
//	
////	/**
////	 * 手工刷报表缓存
////	 * @param memCacheIds 缓存表ID集合
////	 * @return	都刷成功返回0，失败返回1
////	 * @throws ApplicationBaseException
////	 */
////	public int handExec(String memCacheReportIds)
////	{
////		if (logger.isDebugEnabled()) {
////			logger.debug("exec() - start");
////		}
////		int returnInt = 0;
////		MemCacheTask memCacheTask = new MemCacheTask();
////		logger.info("开始手工刷报表缓存!");
////		try{
////			String port_string = ":"+Global.TOMCAT_PORT;
////			if(Global.TOMCAT_PORT==80)
////				port_string = "";
////			String[] memCacheIdStr = memCacheReportIds.split(";");
////			LoadReportMemUtil loadReportMemUtil = new LoadReportMemUtil();
////			if(memCacheIdStr != null && memCacheIdStr.length>0)
////			{
////				
////				MemcacheReport memCacheReport = new MemcacheReport();
////				MemcacheReportDate memCacheReportDate = new MemcacheReportDate();
////				String url = "";
////				for(int i=0 ; i<memCacheIdStr.length ; i++)
////				{
////					try{
//////						MemcacheReportExample memcacheReporExample = new MemcacheReportExample();
//////						memcacheReporExample.createCriteria().andIdEqualTo();
//////						
////						//根据ID取主表数据
////						memCacheReport = (MemcacheReport)memCacheTask.getMemCacheReportManagerBean().selectByPrimaryKey(Integer.parseInt(memCacheIdStr[i]));
////						
////						MemcacheReportDateExample memcacheReportDateExample = new MemcacheReportDateExample();
////						memcacheReportDateExample.createCriteria().andMemcacheReportIdEqualTo(memCacheReport.getId());
////						
////						//取子表
////						List memCacheReportDateList =  memCacheTask.getMemCacheReportDateManagerBean().
////								selectByExample(memcacheReportDateExample);
////						if(memCacheReportDateList != null && memCacheReportDateList.size()>0)
////						{
////							
////							Iterator  memCacheReportDateIt = memCacheReportDateList.iterator();
////							
////							//取出每一张缓存报表
////							while(memCacheReportDateIt.hasNext())
////							{
////								
////								//组装每一个报表的URL
////								memCacheReportDate = (MemcacheReportDate)memCacheReportDateIt.next();
////								if((memCacheReportDate.getReportType()==1 && memCacheReport.getDayRefleshFlag()==1) ||
////									(memCacheReportDate.getReportType()==4 && memCacheReport.getWeekRefleshFlag()==1)	||
////									(memCacheReportDate.getReportType()==2 && memCacheReport.getMonthRefleshFlag()==1)	)
////								{
////									if(memCacheReportDate != null)
////									{
////										
////										url = "http://localhost"
////											+port_string
////											+Global.WEB_CONTEXT_PATH
////											+memCacheReport.getReportModelPath()+"?";
////	//										+Global.NAMESPACE_REPORT
////	//										+"/commons/r-report-common.jsp?";
////										loadReportMemUtil.loadReportHtml(""+memCacheReport.getReportId()
////												, ""+memCacheReportDate.getReportType(), memCacheReport.getReportRaqName(), url);
////									}
////								}
////							}
////						}
////						memCacheReportDateList = null;
////						
////					}catch(Exception ee)
////					{
////						returnInt =  returnInt+1;;
////						logger.warn("exec() - exception ignored", ee);
////						
////					}
////					
////				}
////				memCacheReport = null;
////				memCacheReportDate = null;
////				url = null;
////			}
////			loadReportMemUtil = null;
////		}catch(Exception e)
////		{
////			logger.warn("exec() - exception ignored", e);
////			returnInt =  returnInt+1;
////		}
//////		System.gc();
////		logger.info("报表缓存手工刷新完毕!");
////		
////		return returnInt;
////	}
//	
//
//	/**
//	 * 获得缓存报表类的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public MemcacheReportManager getMemCacheReportManagerBean() throws Exception
//	{
//		return (MemcacheReportManager) Global._ctx.getBean("memcacheReportManager");
//	}
//	
//
//	/**
//	 * 获得缓存报表时间默认值类的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public MemcacheReportDateManager getMemCacheReportDateManagerBean() throws Exception
//	{
//
//		return (MemcacheReportDateManager) Global._ctx.getBean("memcacheReportDateManager");
//	}
//	
//
//	/**
//	 * 获得查询widget的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public WidgetManager getWidgetManagerBean() throws Exception
//	{
//
//		return (WidgetManager) Global._ctx.getBean("widgetManager");
//	}
//	
//	/**
//	 * 获得缓存报表查询条件默认值类的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public MemcacheReportConditionManager getMemCacheReportConditionManagerBean() throws Exception
//	{
//
//		return (MemcacheReportConditionManager) Global._ctx.getBean("memcacheReportConditionManager");
//	}
//	
//	
//	/**
//	 * 获得角色查询的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public RoleManager getRoleManagerBean() throws Exception
//	{
//
//		return (RoleManager) Global._ctx.getBean("roleManager");
//	}
//	
//	/**
//	 * 获得部门查询的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public DepartmentManager getDepartmentManagerBean() throws Exception
//	{
//
//		return (DepartmentManager) Global._ctx.getBean("departmentManager");
//	}
//	
//	
//	/**
//	 * 获得用户查询的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public UserManager getUserManagerBean() throws Exception
//	{
//
//		return (UserManager) Global._ctx.getBean("userManager");
//	}
//	
//	
//	
//	
//	public static void main(String[] args) throws ApplicationBaseException, MalformedURLException {
//		
//		MemCacheTask aa =new MemCacheTask();
//		aa.exec();
//	}
//	
//}
