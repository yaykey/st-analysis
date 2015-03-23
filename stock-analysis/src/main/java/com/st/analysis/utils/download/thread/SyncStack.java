package com.st.analysis.utils.download.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.st.analysis.utils.download.DownloadFileBean;
import com.st.framework.module.stock.FactProxy;

//装馒头的框，栈结构
public class SyncStack {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SyncStack.class);

	/**
	 * 下载urlbean列表;
	 */
	private List<DownloadFileBean> downloadFileBeanList = null;

	/**
	 * 下载任务长度
	 */
	private int taskLen = 0;
	
	/**
	 * 代理服务器list
	 */
	private List<FactProxy> proxyList = null;
	
	private boolean isFast = false;
	
	
	
	public SyncStack() {

	}

	public SyncStack(List<DownloadFileBean> downloadFileBeanList) {
		this.downloadFileBeanList = downloadFileBeanList;
		this.taskLen = this.downloadFileBeanList.size();
	}

	/**
	 * 工作栈指针索引;
	 */
	private int index = 0;

	/**
	 * 工作栈容量
	 */
	private int capacity = 10;

	/**
	 * 
	 * 构造数组，相当工作栈，容量是 capacity
	 * 
	 */
	private DownloadFileBean[] stb = new DownloadFileBean[capacity];

	// 放入框中，相当于入栈
	public synchronized void push(DownloadFileBean sb) {
		while (index == stb.length) {// 筐满了，即栈满，
			try {
				this.wait();// 让当前线程等待
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		this.notify();// 唤醒在此对象监视器上等待的单个线程，即消费者线程
		stb[index] = sb;
		this.index++;
	}

	// 从框中拿出，相当于出栈
	public synchronized DownloadFileBean pop() {
		while (index == 0) {// 筐空了，即栈空
			try {
				this.wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		this.notify();
		this.index--;// push第n个之后，this.index++，使栈顶为n+1，故return之前要减一
		return stb[index];
	}

	public int getTaskLen() {
		return taskLen;
	}

	public void setTaskLen(int taskLen) {
		this.taskLen = taskLen;
	}

	public List<DownloadFileBean> getDownloadFileBeanList() {
		return downloadFileBeanList;
	}

	public void setDownloadFileBeanList(
			List<DownloadFileBean> downloadFileBeanList) {
		this.downloadFileBeanList = downloadFileBeanList;
	}

	public List<FactProxy> getProxyList() {
		return proxyList;
	}

	public void setProxyList(List<FactProxy> proxyList) {
		this.proxyList = proxyList;
	}

	public boolean getIsFast() {
		return isFast;
	}

	public void setIsFast(boolean isFast) {
		this.isFast = isFast;
	}
}
