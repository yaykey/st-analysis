package com.st.framework.special.task.model;

import com.st.framework.special.scheduler.SchedulerTask;
import com.st.framework.special.task.manage.ITaskMrgBO;
import com.st.framework.special.task.manage.TaskMrgBO;



/**
 * 命令任务
 *
 * <p>类名：CommandTask.java</p>
 * <p>描述信息：</p>
 */
public class CommandTask extends SchedulerTask
{

	String command = null;

	public CommandTask(String command) {

		super(command);
	}

	public String getCommand()
	{

		return command;
	}

	public void setCommand(String command)
	{

		this.command = command;
	}

	public void run()
	{
		try
		{
			ITaskMrgBO taskMrg = new TaskMrgBO();
			taskMrg.execCommand(command);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
