package com.st.analysis.utils.download.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.analysis.utils.download.ReturnBean;



public abstract class BaseCallable implements Callable<List<ReturnBean>> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BaseCallable.class);

	/**
	 * 返回bean列表;
	 */
	protected List<ReturnBean> returnBeanList = new ArrayList<ReturnBean>();

	/**
	 * 工作栈
	 */
	protected SyncStack ss = null;
	
	public BaseCallable (SyncStack ss) {
		this.ss = ss;
	}
	
	public List<ReturnBean> getReturnBeanList() {
		return returnBeanList;
	}

	public void setReturnBeanList(List<ReturnBean> returnBeanList) {
		this.returnBeanList = returnBeanList;
	}

	public SyncStack getSs() {
		return ss;
	}

	public void setSs(SyncStack ss) {
		this.ss = ss;
	}
	
	
	
}

