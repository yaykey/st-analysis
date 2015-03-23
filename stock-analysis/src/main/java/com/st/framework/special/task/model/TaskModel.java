package com.st.framework.special.task.model;

import java.util.Date;

import java.util.Calendar;

import com.st.framework.special.scheduler.SchedulerTask;
import com.st.framework.special.task.ScheduleNextImpl;

/**
 * 任务模型
 *
 * <p>类名：TaskModel.java</p>
 * <p>描述信息：</p>
 */
public class TaskModel
{

	public static int TASK_TYPE_CLASS = 1;

	public static int TASK_TYPE_COMMAND = 2;

	String taskKey = null;

	String taskName = null;

	int task_type = TASK_TYPE_CLASS;

	String className = null;

	String command = null;

	String seperate = null;

	int maxCount = -1;

	Date beginDate = null;

	int times = 1;

	public void setTaskKey(String key)
	{

		this.taskKey = key;
	}

	public String getTaskKey()
	{

		return taskKey;
	}

	public void setMaxCount(int count)
	{

		this.maxCount = count;
	}

	public int getMaxCount()
	{

		return maxCount;
	}

	public void setTaskType(int type)
	{

		task_type = type;
	}

	public int getTaskType()
	{

		return task_type;
	}

	public void setTaskName(String name)
	{

		taskName = name;
	}

	public String getTaskName()
	{

		return taskName;
	}

	public void setImplClass(String className)
	{

		task_type = TASK_TYPE_CLASS;
		this.className = className;
	}

	public void setCommand(String command)
	{

		task_type = TASK_TYPE_COMMAND;
		this.command = command;
	}

	public void setSeperate(String sep)
	{

		sep = sep.trim();
		if (sep.length() > 1)
		{
			seperate = sep.substring(sep.length() - 1);
			times = Integer.parseInt(sep.substring(0, sep.length() - 1));
		} else
			seperate = sep;
	}

	public String getSeperate()
	{

		return seperate;
	}

	public void setBeginTime(Date begin)
	{

		this.beginDate = begin;
	}

	public void setBeginTime(int year, int month, int day, int hour,
			int minute, int second)
	{

		Calendar cur = Calendar.getInstance();
		cur.set(Calendar.YEAR, year);
		cur.set(Calendar.MONTH, month);
		cur.set(Calendar.DAY_OF_MONTH, day);
		cur.set(Calendar.HOUR_OF_DAY, hour);
		cur.set(Calendar.MINUTE, minute);
		cur.set(Calendar.SECOND, second);
		beginDate = cur.getTime();
	}

	public Date getBeginTime()
	{

		return beginDate;
	}

	public ScheduleNextImpl getSchedulerNext()
	{

		ScheduleNextImpl sn = new ScheduleNextImpl(beginDate, seperate,
				maxCount, times);
		return sn;
	}

	public SchedulerTask getTask()
	{

		SchedulerTask ret = null;
		if (task_type == TASK_TYPE_COMMAND)
		{
			CommandTask sret = new CommandTask(taskName);
			sret.setCommand(command);
			ret = sret;
		} else
		{
			ClassTask eret = new ClassTask(taskName);
			eret.setClassName(className);
			ret = eret;
		}
		return ret;
	}
}
