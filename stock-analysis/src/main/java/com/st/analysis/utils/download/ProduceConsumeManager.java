//package com.st.framework.utils.download;
//
//import java.util.List;
//
//import com.st.analysis.controller.vo.threads.DownloadFileBean;
//import com.st.framework.utils.LoadConfigUtils;
//
///**
// * 生产者消费者问题，涉及到几个类 第一，这个问题本身就是一个类，即主类 第二，既然是生产者、消费者，那么生产者类和消费者类就是必须的
// * 第三，生产什么，消费什么，所以物品类是必须的，这里是馒头类
// * 第四，既然是线程，那么就不是一对一的，也就是说不是生产一个消费一个，既然这样，多生产的往哪里放，
// * 现实中就是筐了，在计算机中也就是数据结构，筐在数据结构中最形象的就是栈了，因此还要一个栈类
// */
//
//public class ProduceConsumeManager {
//
//	public static void main(String[] args) {
//		
//		//String remoteFileUrl = "http://market.finance.sina.com.cn/downxls.php?date=2014-01-02&symbol=sz300002";
//		
//		
//		SyncStack ss = new SyncStack();// 建造一个装馒头的框
//		
//		Producer p = new Producer(ss);// 新建一个生产者，使之持有框
//		Consume c = new Consume(ss);// 新建一个消费者，使之持有同一个框
//		
//		Thread tp = new Thread(p);// 新建一个生产者线程
//		Thread tc = new Thread(c);// 新建一个消费者线程
//		
//		tp.start();// 启动生产者线程
//		tc.start();// 启动消费者线程
//	}
//	
//	{
//		
//	}
//
//}
//
////// 馒头类
////class DownloadFileBean {
////	int id;// 馒头编号
////	
////	private String url = "";
////	
////	private String savePath = "";
////
////	
////	SteamBread () {
////		
////	}
////	
////	SteamBread(int id) {
////		this.id = id;
////	}
////	
////	public SteamBread (String url, String savePath) {
////		this.url = url;
////		this.savePath = savePath;
////	}
////	
////	
////
////	public String toString() {
////		return "steamBread:" + id;
////	}
////
////	public String getUrl() {
////		return url;
////	}
////
////	public void setUrl(String url) {
////		this.url = url;
////	}
////
////	public String getSavePath() {
////		return savePath;
////	}
////
////	public void setSavePath(String savePath) {
////		this.savePath = savePath;
////	}
////
////	public int getId() {
////		return id;
////	}
////
////	public void setId(int id) {
////		this.id = id;
////	}
////	
////	
////}
//
//// 装馒头的框，栈结构
//class SyncStack {
//	
//	private List<DownloadFileBean> downloadFileBeanList = null;
//	
//	private int taskLen = 0;
//	
//	public SyncStack () {
//		
//	}
//	
//	public SyncStack (List<DownloadFileBean> downloadFileBeanList) {
//		this.downloadFileBeanList = downloadFileBeanList;
//		this.taskLen = this.downloadFileBeanList.size();
//	}
//	
//	int index = 0;
//	
//	int capacity=6;
//	
//	DownloadFileBean[] stb = new DownloadFileBean[capacity];// 构造馒头数组，相当于馒头筐，容量是6
//
//	// 放入框中，相当于入栈
//	public synchronized void push(DownloadFileBean sb) {
//		while (index == stb.length) {// 筐满了，即栈满，
//			try {
//				this.wait();// 让当前线程等待
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//		}
//		this.notify();// 唤醒在此对象监视器上等待的单个线程，即消费者线程
//		stb[index] = sb;
//		this.index++;
//	}
//
//	// 从框中拿出，相当于出栈
//	public synchronized DownloadFileBean pop() {
//		while (index == 0) {// 筐空了，即栈空
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//		}
//		this.notify();
//		this.index--;// push第n个之后，this.index++，使栈顶为n+1，故return之前要减一
//		return stb[index];
//	}
//
//	public int getTaskLen() {
//		return taskLen;
//	}
//
//	public void setTaskLen(int taskLen) {
//		this.taskLen = taskLen;
//	}
//
//	public List<DownloadFileBean> getDownloadFileBeanList() {
//		return downloadFileBeanList;
//	}
//
//	public void setDownloadFileBeanList(List<DownloadFileBean> downloadFileBeanList) {
//		this.downloadFileBeanList = downloadFileBeanList;
//	}
//
//
//}
//
//// 生产者类，实现了Runnable接口，以便于构造生产者线程
//class Producer implements Runnable {
//	SyncStack ss = null;
//
//	Producer(SyncStack ss) {
//		this.ss = ss;
//	}
//
//	@Override
//	public void run() {
//		// 开始生产馒头
//		for (int i = 0; i < ss.getTaskLen(); i++) {
//			DownloadFileBean stb = ss.getDownloadFileBeanList().remove(0);
//			ss.push(stb);
//			System.out.println("生产了" + stb);
////			try {
////				Thread.sleep(1);// 每生产一个馒头，睡觉10毫秒
////			} catch (InterruptedException e) {
////
////				e.printStackTrace();
////			}
//		}
//	}
//}
//
//// 消费者类，实现了Runnable接口，以便于构造消费者线程
//class Consume implements Runnable {
//	SyncStack ss = null;
//
//	private String baseSavePath = LoadConfigUtils.getInstance().getDownloadFilePath();
//	
//	public Consume(SyncStack ss) {
//		super();
//		this.ss = ss;
//	}
//
//	@Override
//	public void run() {
//
//		for (int i = 0; i < ss.getTaskLen(); i++) {// 开始消费馒头
//			DownloadFileBean stb = ss.pop();
//			System.out.println("消费了" + stb);
//			
//			DownloadThread downloadThread = new DownloadThread(stb.getRemoteFileUrl(), baseSavePath + "/" +stb.getSavePath());
//			
//			downloadThread.start();
//			
////			try {
////				Thread.sleep(100);// 每消费一个馒头，睡觉100毫秒。即生产多个，消费一个
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//		}
//	}
//}
//

