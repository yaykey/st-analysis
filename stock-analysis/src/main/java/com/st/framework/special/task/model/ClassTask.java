package com.st.framework.special.task.model;

import com.st.framework.special.scheduler.SchedulerTask;
import com.st.framework.special.task.manage.ITaskMrgBO;
import com.st.framework.special.task.manage.TaskMrgBO;



/**
 * 类任务,执行方法操作
 *
 * <p>类名：ClassTask.java</p>
 * <p>描述信息：</p>
 */
public class ClassTask extends SchedulerTask
{  

	String className = null;

	public ClassTask(String name) {

		super(name);
	}

	public void setClassName(String name)
	{

		className = name;
	}

	public void run()
	{

		try
		{
			ITaskMrgBO taskMrg = new TaskMrgBO();
			taskMrg.execClass(className);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
