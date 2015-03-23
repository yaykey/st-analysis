package com.st.framework.special.task.manage;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.framework.special.task.TaskBase;

/**
 * 
 *
 * <p>类名：TaskMrgBO.java</p>
 * <p>描述信息：</p>
 */
public class TaskMrgBO implements ITaskMrgBO
{

	/**操作日志*/
	private static final Log logger = LogFactory.getLog(TaskMrgBO.class);

	public void execClass(String className) throws Exception
	{

		try
		{
			TaskBase tb = (TaskBase) Class.forName(className).newInstance();
			tb.exec();
		} catch (Exception e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}

	public void execCommand(String command) throws Exception
	{

		try
		{
			Runtime.getRuntime().exec(command);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}


}
