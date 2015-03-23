package com.st.framework.special.task.util;

import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.feinno.circle.framework.business.impl.TaskManager;
//import com.feinno.circle.framework.module.Task;
//import com.feinno.circle.framework.module.example.TaskExample;
import com.st.framework.special.scheduler.SchedulerMrg;
import com.st.framework.special.task.model.TaskModel;
import com.st.framework.utils.LoadConfigUtils;

/**
 * 调度任务初始化
 *
 * <p>类名：TaskInitialize.java</p>
 * <p>描述信息：</p>
 */
@Component("taskInit")
public class TaskInitialize
{

	
	
	/**操作日志对象*/
	private static final Log logger = LogFactory.getLog(TaskInitialize.class);

//	@Autowired
//	private TaskManager reportTaskManager;
	
//	@Autowired
//	private IUserManager userManager;

	/**
	 * 装载任务
	 * @param schedulermrg 任务装载器
	 */
	public void init(SchedulerMrg schedulermrg)
	{
//		 z

	}
	
//	/**
//	 * 任务模型转换
//	 * @param taskInfo 原始任务
//	 * @param tm 转换后用于执行的任务
//	 * @return 转换后用于执行的任务
//	 */
//	public TaskModel taskInfoToTaskModel(Task taskInfo,TaskModel tm)
//	{
//
//		if (taskInfo.getTaskType() == TaskModel.TASK_TYPE_CLASS)
//		{
//			tm.setTaskType(TaskModel.TASK_TYPE_CLASS);
//		} else if (taskInfo.getTaskType() == TaskModel.TASK_TYPE_COMMAND)
//		{
//			tm.setTaskType(TaskModel.TASK_TYPE_COMMAND);
//		}
//		tm.setImplClass(taskInfo.getTaskImpl());
//		tm.setBeginTime(taskInfo.getStartDate());
//		tm.setSeperate(taskInfo.getSeperate());
//		tm.setTaskName(taskInfo.getTaskName());
//		return tm ;
//		
//	}
	
}
