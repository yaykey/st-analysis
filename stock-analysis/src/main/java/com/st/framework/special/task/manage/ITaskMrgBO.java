package com.st.framework.special.task.manage;


/**
 * 任务管理的业务对象
 *
 * <p>类名：TaskMrgBO.java</p>
 * <p>描述信息：</p>
 */
public interface ITaskMrgBO
{ 
	

	/**
	 * 执行类方法
	 * 
	 * @param className
	 * @throws FetionBaseException
	 */
	public void execClass(String className) throws Exception;

	/**
	 * 执行命令
	 * 
	 * @param command
	 * @throws FetionBaseException
	 */
	public void execCommand(String command) throws Exception;
}
