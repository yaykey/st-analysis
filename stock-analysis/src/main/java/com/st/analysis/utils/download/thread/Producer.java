package com.st.analysis.utils.download.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.st.analysis.utils.download.DownloadFileBean;
import com.st.analysis.utils.download.ReturnBean;


/**
 * 
 * 生产者类，实现了Runnable接口，以便于构造生产者线程
 * 
 * @author yzy
 *
 */
public class Producer extends BaseCallable {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Producer.class);


	public Producer(SyncStack ss) {
		super(ss);
	}

	@Override
	public List<ReturnBean> call() {
		// 开始生产馒头
		for (int i = 0; i < ss.getTaskLen(); i++) {
			DownloadFileBean stb = ss.getDownloadFileBeanList().remove(0);
			ss.push(stb);
			//System.out.println("生产了" + stb);
			
			//logger.info("生产了" + stb);
			
			// try {
			// Thread.sleep(1);// 每生产一个馒头，睡觉10毫秒
			// } catch (InterruptedException e) {
			//
			// e.printStackTrace();
			// }
		}

		return returnBeanList;
	}
}
