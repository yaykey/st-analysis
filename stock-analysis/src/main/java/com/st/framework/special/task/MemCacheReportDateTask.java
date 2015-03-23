package com.st.framework.special.task;
//package com.feinno.circle.framework.special.task;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.feinno.circle.Global;
//import com.feinno.circle.framework.business.impl.MemcacheReportConditionManager;
//import com.feinno.circle.framework.business.impl.MemcacheReportDateManager;
//import com.feinno.circle.framework.business.impl.MemcacheReportManager;
//import com.feinno.circle.framework.exceptions.ApplicationBaseException;
//import com.feinno.circle.framework.module.MemcacheReport;
//import com.feinno.circle.framework.module.MemcacheReportDate;
//import com.feinno.circle.framework.module.example.MemcacheReportDateExample;
//import com.feinno.circle.framework.module.example.MemcacheReportExample;
//import com.feinno.circle.framework.business.impl.PRptDateManager;
//import com.feinno.circle.framework.utils.DateUtil;
//
//
///**
// * 报表缓存使用的默认时间 定时刷新的任务
// *
// * <p>类名：MemCacheReportDateTask.java</p>
// */
//
//public class MemCacheReportDateTask extends TaskBase
//{
//
//		
//	/** 常量 */
//	private static final Log logger = LogFactory.getLog(MemCacheReportDateTask.class);
//
//	public void exec() throws ApplicationBaseException
//	{
//		
//		logger.info("开始刷报表的默认开始和结束日期(日,周,月)!");
//		MemCacheReportDateTask mt = new MemCacheReportDateTask();
//		try{
//			List memCacheReportList =  mt.getMemCacheReportManagerBean().selectByExample(new MemcacheReportExample());
//			MemcacheReport memCacheReport = null;
//			Iterator memCacheReportIt = memCacheReportList.iterator();
//			//循环缓存报表的个数
//			while(memCacheReportIt.hasNext())
//			{
//				memCacheReport = (MemcacheReport)memCacheReportIt.next();
//				
//				MemcacheReportDateExample memcacheReportDateExample = new MemcacheReportDateExample();
//				memcacheReportDateExample.createCriteria().andMemcacheReportIdEqualTo(memCacheReport.getId());
//				//取出每个报表的日期个数
//				List memCacheReportDateList =  mt.getMemCacheReportDateManagerBean().
//							selectByExample(memcacheReportDateExample);
//				
//				Iterator memCacheReportDateIt = memCacheReportDateList.iterator();
//				MemcacheReportDate memCacheReportDate = null;
//				
//				Integer reportId = null;
//				if(memCacheReportDateList == null || memCacheReportDateList.size()<=0)
//				{
//					memCacheReportDate = new MemcacheReportDate();
////					memCacheReportDate.setMemCacheReport(memCacheReport);
//					//刷日时间数据
//					mt.refreshDay(mt,memCacheReportDate,memCacheReport.getReportId());
//					
//					memCacheReportDate = null;
//					memCacheReportDate = new MemcacheReportDate();
////					memCacheReportDate.setMemCacheReport(memCacheReport);
//					//刷周时间数据
//					mt.refreshWeek(mt, memCacheReportDate, memCacheReport.getReportId());
//					
//					memCacheReportDate = null;
//					memCacheReportDate = new MemcacheReportDate();
////					memCacheReportDate.setMemCacheReport(memCacheReport);
//					//刷月时间数据
//					mt.refreshMonth(mt, memCacheReportDate, memCacheReport.getReportId());
//					memCacheReportDate = null;
//				}else{
//					
//					//刷新每个日期默认值
//					while(memCacheReportDateIt.hasNext())
//					{
//						
//						memCacheReportDate = (MemcacheReportDate)memCacheReportDateIt.next();
//						if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.DAY_REPORT))//刷日时间数据
//						{
//							mt.refreshDay(mt, memCacheReportDate,memCacheReport.getReportId());
//						}else if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.WEEK_REPORT))//刷周时间数据
//						{
//							mt.refreshWeek(mt, memCacheReportDate, memCacheReport.getReportId());
//						}else if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.MONTH_REPORT))//刷月时间数据
//						{
//							mt.refreshMonth(mt, memCacheReportDate, memCacheReport.getReportId());
//						}  
//						memCacheReportDate = null;
//					}
//				}
//				memCacheReportDateIt = null;
//			
//			}
//			memCacheReportIt = null;
//			memCacheReport  = null;
//		}catch(Exception e)
//		{
//			logger.warn("exec() - exception ignored", e); //$NON-NLS-1$
//			
//		}
//		
//		logger.info("报表默认开始和结束日期刷新完毕!");
//	}
//	
//	public int handExec(String memCacheReportIds)
//	{
//		
//		logger.info("开始手工刷报表的默认开始和结束日期(日,周,月)!");
//		String[] memCacheIdStr = memCacheReportIds.split(";");
//		int returnInt = 0;
//		if(memCacheIdStr != null && memCacheIdStr.length>0)
//		{
//			MemCacheReportDateTask mt = new MemCacheReportDateTask();
//			MemcacheReport memCacheReport = null;
//			for(int i=0 ; i<memCacheIdStr.length ; i++)
//			{
//				try{
//					
//					//根据ID取主表数据
//					memCacheReport = (MemcacheReport)mt.getMemCacheReportManagerBean().selectByPrimaryKey(Integer.parseInt(memCacheIdStr[i]));
//
//					MemcacheReportDateExample memcacheReportDateExample = new MemcacheReportDateExample();
//					memcacheReportDateExample.createCriteria().andMemcacheReportIdEqualTo(memCacheReport.getId());
//					//取出每个报表的日期个数
//					List memCacheReportDateList =  mt.getMemCacheReportDateManagerBean().
//								selectByExample(memcacheReportDateExample);
//					
//					Iterator memCacheReportDateIt = memCacheReportDateList.iterator();
//					MemcacheReportDate memCacheReportDate = null;
//					
//					Integer reportId = null;
//					if(memCacheReportDateList == null || memCacheReportDateList.size()<=0)
//					{
//
//						memCacheReportDate = new MemcacheReportDate();
////						memCacheReportDate.setMemCacheReport(memCacheReport);
//						//刷日时间数据
//						mt.refreshDay(mt,memCacheReportDate,memCacheReport.getReportId());
//						
//						memCacheReportDate = null;
//						memCacheReportDate = new MemcacheReportDate();
////						memCacheReportDate.setMemCacheReport(memCacheReport);
//						//刷周时间数据
//						mt.refreshWeek(mt, memCacheReportDate, memCacheReport.getReportId());
//						
//						memCacheReportDate = null;
//						memCacheReportDate = new MemcacheReportDate();
////						memCacheReportDate.setMemCacheReport(memCacheReport);
//						//刷月时间数据
//						mt.refreshMonth(mt, memCacheReportDate, memCacheReport.getReportId());
//						memCacheReportDate = null;
//					}else{
//						//刷新每个日期默认值
//						while(memCacheReportDateIt.hasNext())
//						{
//							
//							memCacheReportDate = (MemcacheReportDate)memCacheReportDateIt.next();
//							if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.DAY_REPORT))//刷日时间数据
//							{
//								mt.refreshDay(mt, memCacheReportDate,memCacheReport.getReportId());
//							}else if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.WEEK_REPORT))//刷周时间数据
//							{
//								mt.refreshWeek(mt, memCacheReportDate, memCacheReport.getReportId());
//							}else if(memCacheReportDate.getReportType() == Short.valueOf(""+Global.MONTH_REPORT))//刷月时间数据
//							{
//								mt.refreshMonth(mt, memCacheReportDate, memCacheReport.getReportId());
//							}  
//							memCacheReportDate = null;
//						}
//					}
//					memCacheReportDateIt = null;
//					memCacheReportDateList = null;
//					memCacheReport = null;
//				}catch(Exception e)
//				{
//					logger.warn("exec() - exception ignored", e); //$NON-NLS-1$
//					returnInt = returnInt+1;
//				}
//				
//			}
//		}
//		logger.info("报表默认开始和结束日期手工刷新完毕!");
//		return returnInt;
//	}
//	
//	/**
//	 * * 刷新日报表的开始和结束时间
//	 * @param mt 任务对象
//	 * @param memCacheReportDateSet 报表日期数据对象 
//	 * @param reportId   报表ID
//	 */
//	public static void  refreshDay(MemCacheReportDateTask mt , MemcacheReportDate memCacheReportDate ,Integer reportId)
//	{
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshDay(MemCacheReportDateTask mt=" + mt + ", MemCacheReportDate memCacheReportDate=" + memCacheReportDate + ", Integer reportId=" + reportId + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//		}
//		
//		if(memCacheReportDate != null
//			&& reportId != null)
//		{
//			try{
//				memCacheReportDate.setReportType(Short.valueOf(""+1));
//				memCacheReportDate.setTypeName("日报");
//				String startDate = memCacheReportDate.getStartDate(); //开始日期
//				String endDate = endDate = DateUtil.getYesterday().replaceAll("-", "");   //结束日期
//				String minDate = mt.getPRptDateBean().getMinDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最小日期
//				String maxDate = mt.getPRptDateBean().getMaxDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最大日期
//				
//				//结束日期默认为昨天
//				if(maxDate != null && !maxDate.equals("") )
//				{
//					if(Integer.parseInt(maxDate)<Integer.parseInt(endDate))
//					{
//						endDate = maxDate;
//					}
//				}else{
//					endDate = null;
//				}
//				
//				//开始日期为这个月的第一天
//				if(endDate != null && !endDate.equals(""))
//				{
//					startDate = DateUtil.getFristday(endDate,DateUtil.DATE_FMT_0);
//					if(minDate != null && !minDate.equals(""))
//					{
//						if(Integer.parseInt(minDate)>Integer.parseInt(startDate))
//						{
//							startDate = minDate;
//						}
//					}else{
//						startDate = null;
//					}
//				}else{
//					startDate = minDate;
//				}
//				
////				memCacheReportDate.setStartDate(startDate);
//				memCacheReportDate.setStartDate(endDate);
//				memCacheReportDate.setEndDate(endDate);
//				
//				//保存更新
//				mt.getMemCacheReportDateManagerBean().insertOrUpdate(memCacheReportDate);
//				
//			}catch(Exception e)
//			{
//				logger.warn("refreshDay(MemCacheReportDateTask, MemCacheReportDate, Integer) - exception ignored", e); //$NON-NLS-1$
//				
//			}
//			
//		}
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshDay(MemCacheReportDateTask, MemCacheReportDate, Integer) - end"); //$NON-NLS-1$
//		}
//	}
//	
//	/**
//	 * * 刷新周报表的开始和结束周
//	 * @param mt 任务对象
//	 * @param memCacheReportDate 报表日期数据对象 
//	 * @param reportId   报表ID
//	 */
//	public static void  refreshWeek(MemCacheReportDateTask mt , MemcacheReportDate memCacheReportDate ,Integer reportId)
//	{
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshWeek(MemCacheReportDateTask mt=" + mt + ", MemCacheReportDate memCacheReportDate=" + memCacheReportDate + ", Integer reportId=" + reportId + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//		}
//		
//		if(memCacheReportDate != null 
//			&& reportId != null)
//		{
//			try{
//				memCacheReportDate.setReportType(Short.valueOf(""+4));
//				memCacheReportDate.setTypeName("周报");
//				String startDate = memCacheReportDate.getStartDate(); //开始周
//				String endDate = DateUtil.getYesterweek().replaceAll("-", "");   //结束周
//				String minDate = mt.getPRptDateBean().getMinDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最小周
//				String maxDate = mt.getPRptDateBean().getMaxDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最大周
//				
//				//结束周默认为上一周
//				if(maxDate != null && !maxDate.equals("") )
//				{
////					if(Integer.parseInt(maxDate)<Integer.parseInt(endDate))
////					{
//					endDate = maxDate;
////					}
//				}else{
//					endDate = null;
//				}
//				
//				//开始周为今年的第一周
//				if(endDate != null && !endDate.equals(""))
//				{
//					startDate = DateUtil.getFristmonth(endDate,DateUtil.WEEK_FMT_0);
//					if(minDate != null && !minDate.equals(""))
//					{
//						if(Integer.parseInt(minDate)>Integer.parseInt(startDate))
//						{
//							startDate = minDate;
//						}
//					}else{
//						startDate = null;
//					}
//				}else{
//					startDate = minDate;
//				}
//				
////				memCacheReportDate.setStartDate(startDate);
//				memCacheReportDate.setStartDate(endDate);
//				memCacheReportDate.setEndDate(endDate);
//				
//				//保存更新
//				mt.getMemCacheReportDateManagerBean().insertOrUpdate(memCacheReportDate);
//				
//			}catch(Exception e)
//			{
//				logger.warn("refreshWeek(MemCacheReportDateTask, MemCacheReportDate, Integer) - exception ignored", e); //$NON-NLS-1$
//				
//			}
//			
//		}
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshWeek(MemCacheReportDateTask, MemCacheReportDate, Integer) - end"); //$NON-NLS-1$
//		}
//	}
//	
//	/**
//	 * * 刷新周报表的开始和结束月
//	 * @param mt 任务对象
//	 * @param memCacheReportDate 报表日期数据对象 
//	 * @param reportId   报表ID
//	 */
//	public static void  refreshMonth(MemCacheReportDateTask mt , MemcacheReportDate memCacheReportDate ,Integer reportId)
//	{
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshMonth(MemCacheReportDateTask mt=" + mt + ", MemCacheReportDate memCacheReportDate=" + memCacheReportDate + ", Integer reportId=" + reportId + ") - start"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
//		}
//		
//		if(memCacheReportDate != null 
//			&& reportId != null)
//		{
//			try{
//				memCacheReportDate.setReportType(Short.valueOf(""+2));
//				memCacheReportDate.setTypeName("月报");
//				String startDate = memCacheReportDate.getStartDate(); //开始月
//				String endDate = endDate = DateUtil.getYestermonth().replaceAll("-", "");   //结束月
//				String minDate = mt.getPRptDateBean().getMinDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最小月
//				String maxDate = mt.getPRptDateBean().getMaxDay(""+reportId, ""
//							+memCacheReportDate.getReportType()).replaceAll("-", "");   //最大月
//				
//				//结束周默认为上一个月
//				if(maxDate != null && !maxDate.equals("") )
//				{
//					if(Integer.parseInt(maxDate)<Integer.parseInt(endDate))
//					{
//						endDate = maxDate;
//					}
//				}else{
//					endDate = null;
//				}
//				
//				//开始周为今年的第一个月
//				if(endDate != null && !endDate.equals(""))
//				{
//					startDate = DateUtil.getFristmonth(endDate,DateUtil.MONTH_FMT_0);
//					if(minDate != null && !minDate.equals(""))
//					{
//						if(Integer.parseInt(minDate)>Integer.parseInt(startDate))
//						{
//							startDate = minDate;
//						}
//					}else{
//						startDate = null;
//					}
//				}else{
//					startDate = minDate;
//				}
//				
//				memCacheReportDate.setStartDate(startDate);
//				memCacheReportDate.setEndDate(endDate);
//				
//				//保存更新
//				mt.getMemCacheReportDateManagerBean().insertOrUpdate(memCacheReportDate);
//				
//			}catch(Exception e)
//			{
//				logger.warn("refreshMonth(MemCacheReportDateTask, MemCacheReportDate, Integer) - exception ignored", e); //$NON-NLS-1$
//				
//			}
//			
//		}
//		
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("refreshMonth(MemCacheReportDateTask, MemCacheReportDate, Integer) - end"); //$NON-NLS-1$
//		}
//	}
//	
//
////	/**
////	 * 获得GetAllReport类的业务对象
////	 * 
////	 * @return 
////	 * @throws Exception
////	 */
////	public IKpiReportManager getKpiReportManager() throws Exception
////	{
////		
////		return (IKpiReportManager) Global._ctx.getBean("kpiReportManager");
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
//
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
//	 * 获得缓存报表查询条件默认值类的业务对象
//	 * 
//	 * @return 
//	 * @throws Exception
//	 */
//	public PRptDateManager getPRptDateBean() throws Exception
//	{
//
//		return (PRptDateManager) Global._ctx.getBean("pRptDateManager");
//	}
//	
//	
//}
