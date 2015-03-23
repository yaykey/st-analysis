package com.st.framework.special.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 调度实例对象
 *
 * <p>类名：Scheduler.java</p>
 * <p>描述信息：</p>
 */
public class Scheduler
{

	
	
	// 操作日志对象
	private static final Log logger = LogFactory.getLog(Scheduler.class);

	// 任务对象
	private SchedulerTask task = null;

	// 下一个执行周期
	private SchedulerNext snext = null;

	// Timer对象
	private Timer timer = null;

	// 是否开始
	private boolean start = false;

	// SchedulerTimerTask类
	class SchedulerTimerTask extends TimerTask
	{

		private SchedulerTask task;
		private SchedulerNext next;

		public SchedulerTimerTask(SchedulerTask task, SchedulerNext next) {

			this.task = task;
			this.next = next;
		}

		// 执行任务
		public void run()
		{
			task.run();
			reschedule(task, next);
		}
	}

	public Scheduler(SchedulerTask task, SchedulerNext next) {

		this.task = task;
		this.snext = next;
	}

	public String getTaskName()
	{
		if (task == null)
			return null;
		else
			return task.getName();
	}

	/**
	 * 停止
	 * 
	 */
	public void stop()
	{
		if (!start)
			return;
		start = false;
		timer.cancel();
		task.stop();
		timer = null;
	}

	/**
	 * 任务开始
	 * 
	 */
	public void start()
	{
		if (start)
			return;
		start = true;
		timer = new Timer();
		Date time = snext.next();
		if (time == null)
			task.stop();
		else
		{
			synchronized (task.lock)
			{
				if (task.state == SchedulerTask.SCHEDULED)
				{
					throw new IllegalStateException("Task already scheduled "
							+ "or cancelled");
				}
				task.state = SchedulerTask.SCHEDULED;
				task.timerTask = new SchedulerTimerTask(task, snext);
				timer.schedule(task.timerTask, time);
			}
		}
	}

	
	private void reschedule(SchedulerTask task, SchedulerNext snext)
	{

		Date time = snext.next();
		while (time != null && time.before(Calendar.getInstance().getTime()))
		{
			time = snext.next();
			// System.out.println(time.toString());
		}
		if (time == null)
			task.stop();
		else
		{
			synchronized (task.lock)
			{
				if (task.state != SchedulerTask.CANCELLED)
				{
					task.timerTask = new SchedulerTimerTask(task, snext);
					timer.schedule(task.timerTask, time);
				}
			}
		}
	}
}
