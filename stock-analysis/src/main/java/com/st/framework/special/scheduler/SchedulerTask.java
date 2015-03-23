package com.st.framework.special.scheduler;

import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统中调度的任务接口
 *
 * <p>类名：SchedulerTask.java</p>
 * <p>描述信息：管理用户定义的任务：运行周期、任务执行</p>
 */
public abstract class SchedulerTask implements Runnable
{

	// 操作日志对象
	private static final Log logger = LogFactory.getLog(SchedulerTask.class);

	// 任务的名字
	String name = null;

	// TimerTask对象
	TimerTask timerTask;

	// 锁,做多线程控制的
	final Object lock = new Object();

	// 状态
	int state = VIRGIN;

	static final int VIRGIN = 0;

	static final int SCHEDULED = 1;

	static final int CANCELLED = 2;

	/**
	 * 构造方法
	 * 
	 * @param name
	 */
	public SchedulerTask(String name) {

		this.name = name;
	}

	/**
	 * 返回任务名字
	 * 
	 * @return
	 */
	public String getName()
	{

		return name;
	}

	/**
	 * 任务停止
	 * 
	 * @return
	 */
	public boolean stop()
	{

		synchronized (lock)
		{
			if (timerTask != null)
				timerTask.cancel();
			boolean result = (state == SCHEDULED);
			state = CANCELLED;
			return result;
		}
	}

	/**
	 * 返回调度的执行时间
	 * 
	 * @return
	 */
	public long scheduledExecutionTime()
	{

		synchronized (lock)
		{
			return timerTask == null ? 0 : timerTask.scheduledExecutionTime();
		}
	}

	/**
	 * 抽象方法,任务的执行工程在里面实现.
	 */
	public abstract void run();
}
