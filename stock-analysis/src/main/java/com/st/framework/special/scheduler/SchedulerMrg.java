package com.st.framework.special.scheduler;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 负责用户定义任务FetionTask的调度管理.单例模式
 * 
 * <p>类名：SchedulerMrg.java</p>
 * <p>描述信息：定时执行用户定义的任务(每天,每周,每月)</p>
 */
public class SchedulerMrg
{

	// 操作日志对象
	private static final Log logger = LogFactory.getLog(SchedulerMrg.class);

	// 实例对象
	private static SchedulerMrg scheduler = null;

	// 任务集合
	private ArrayList tasks = new ArrayList();

	// 获得实例对象
	public static SchedulerMrg getInstance()
	{

		if (scheduler == null)
		{
			scheduler = new SchedulerMrg();
			scheduler.init();
		}
		return scheduler;
	}

	/**
	 * 构造方法
	 */
	private SchedulerMrg() {

	}

	/**
	 * 系统初始化工作
	 */
	private void init()
	{

	}

	/**
	 * 添加一个任务
	 * 
	 * @param task
	 * @param next
	 */
	public synchronized void addTask(SchedulerTask task, SchedulerNext next)
	{

		Scheduler scheduler = new Scheduler(task, next);
		// 启动
		scheduler.start();
		tasks.add(scheduler);
	}

	public synchronized void removeTask(String taskName)
	{
		if (taskName == null)
			return;
		Iterator it = tasks.iterator();
		while (it.hasNext()) {
			Scheduler scheduler = (Scheduler)it.next();
			if (taskName.equals(scheduler.getTaskName())){
				scheduler.stop();
				it.remove();
			}
		}
	}

	public synchronized void stop()
	{

		int size = tasks.size();
		for (int i = 0; i < size; i++)
		{
			Scheduler scheduler = (Scheduler) tasks.get(i);
			scheduler.stop();
		}
	}

	public synchronized void start()
	{

		int size = tasks.size();
		for (int i = 0; i < size; i++)
		{
			Scheduler scheduler = (Scheduler) tasks.get(i);
			scheduler.start();
		}
	}
}
