package com.st.analysis.utils.download.thread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.st.analysis.utils.download.DownloadFileBean;
import com.st.analysis.utils.download.ReturnBean;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.module.stock.FactProxy;
import com.st.framework.utils.LoadConfigUtils;


/**
 * 消费者类，实现了Runnable接口，以便于构造消费者线程
 * 
 * @author yzy
 *
 */
public class Consume extends BaseCallable {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Consume.class);

	
	public static ExecutorService threadPool = new ThreadPoolExecutor(3, 30, 30L,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(30),
			new ThreadPoolExecutor.DiscardPolicy());
	
//	/SyncStack ss = null;

	private String baseSavePath = LoadConfigUtils.getInstance()
			.getDownloadFilePath();

	public Consume(SyncStack ss) {
		super(ss);
	}
	
	

	@Override
	public List<ReturnBean> call() {

		Random random = new Random();
		
		FactProxy proxy = null;
		ReturnBean returnBean = null;
		
		for (int i = 0; i < ss.getTaskLen(); i++) {// 开始消费馒头
			DownloadFileBean stb = ss.pop();
			//System.out.println("消费了" + stb);

			//logger.info("消费了" + stb);
			
			if (ss.getProxyList() != null && ss.getProxyList().size() > 0) {
				proxy = ss.getProxyList().get(random.nextInt(ss.getProxyList().size()));
				
				
				logger.debug("call() - FactProxy proxy=" + proxy);
				
				
				ProxyUtils.setProxy(proxy.getProxyIp(), "" + proxy.getProxyPort());
			}
			
			try {


				
//				try {
//					DownloadThread download = new DownloadThread(
//						stb.getRemoteFileUrl(), baseSavePath
//								+ stb.getSavePath());
//				
//					download.start();
//				} catch (Exception ex) {
//					logger.warn("尝试下载一次" + stb.getRemoteFileUrl());
//					DownloadThread download = new DownloadThread(
//							stb.getRemoteFileUrl(), baseSavePath
//									+ stb.getSavePath());
//					
//					download.start();
//				}
				
				if (ss.getIsFast() == true) {
					try {
						DownloadThread download = new DownloadThread(
							stb.getRemoteFileUrl(), baseSavePath
									+ stb.getSavePath());
					
						download.start();
					} catch (Exception ex) {
						logger.warn("尝试下载一次" + stb.getRemoteFileUrl());
						DownloadThread download = new DownloadThread(
								stb.getRemoteFileUrl(), baseSavePath
										+ stb.getSavePath());
						
						download.start();
					}
				} else {
					DownloadCallable download = new DownloadCallable(
					stb.getRemoteFileUrl(), baseSavePath
							+ stb.getSavePath());
					Future<ReturnBean> future = threadPool.submit(download);
					
					returnBean = future.get();
					returnBean.setDownloadFileBean(stb);
					logger.info(returnBean);
					
					returnBeanList.add(returnBean);
				}
				
				
								
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);// 每消费一个馒头，睡觉100毫秒。即生产多个，消费一个
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		threadPool.shutdown();

		return returnBeanList;
	}
}
