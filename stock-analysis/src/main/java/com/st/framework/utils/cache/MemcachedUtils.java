/*
 * 文件名： MemcahcedUtils.java
 * 
 * 创建日期： 2009-6-9
 *
 * Copyright(C) 2009, by zhangyan.
 *
 * 原始作者: <a href="mailto:lemom8000@gmail.com">zhangyan</a>
 *
 */
package com.st.framework.utils.cache;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//import org.ibmp.memcache.impl.MemcachedCacheManager;
//import org.ibmp.memcache.interfaces.ICache;
//import org.ibmp.memcache.interfaces.ICacheCluster;
//import org.ibmp.memcache.interfaces.ICacheManager;
//import org.ibmp.memcache.interfaces.IMemcachedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alisoft.xplatform.asf.cache.ICache;
import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

/**
 * memcached相关工具类
 * 
 */
public enum MemcachedUtils {
	/** 鉴权 */
	INSTANCE,
	/** 服务 */
	SERVCACHE,
	/** 短信提示语 */
	SMS_MSG,
	/** 短信指令集 */
	SMS_CMD,
	/** 短信用户信息 */
	SMS_USER_STAT,
	/** 省份信息 */
	PROVINCE;
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(MemcachedUtils.class);

	/**
	 * cache管理对象
	 */
	//private static ICacheManager<IMemcachedCache, ICacheCluster> manager;
	
	private static ICacheManager<IMemcachedCache> manager;
	
	/**
	 * 线程池
	 */
	private static ExecutorService pool = new ThreadPoolExecutor(1, 100, 0L, TimeUnit.NANOSECONDS,
			new LinkedBlockingQueue<Runnable>()) {
		@Override
		protected void afterExecute(Runnable runnable, Throwable throwable) {
			if (throwable != null) {
				if (throwable.getMessage() != null) {
					if (throwable.getMessage().indexOf("Timeout waiting for value") != -1)
						execute(runnable);
					else {
						if (logger.isErrorEnabled()) {
							logger.error("MemcahcedUtils threadpool task error !!!", throwable);
						}
					}
				}
			}
		}
	};

	static {
//		manager = new MemcachedCacheManager();
//		manager.start();
		
		manager = CacheUtil.getCacheManager(IMemcachedCache.class,
			MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached_cluster.xml");
		manager.start();
		
	}

	public void start() {
		manager.start();
	}

	public void stop() {
		manager.stop();
	}

	private ICache<String, Object> getCache() {
		switch (this) {
		
		case INSTANCE:
			return manager.getCache("mclient1");
		default:
			return manager.getCache("mclient1");
		}
		
//		case INSTANCE:
//			return manager.getCluster("cluster1");
//		case SERVCACHE:
//			return manager.getCluster("cluster2");
//		case SMS_MSG:
//			return manager.getCache("sms1");
//		case SMS_CMD:
//			return manager.getCache("sms2");
//		case SMS_USER_STAT:
//			return manager.getCache("sms3");
//		case PROVINCE:
//			return manager.getCluster("province");
//		default:
//			return manager.getCluster("cluster1");
//		}
	}

	/**
	 * 保存数据,默认为不过期-1
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void put(int key, Object value) {
		getCache().put(String.valueOf(key), value);
	}

	/**
	 * 保存数据,默认为不过期-1
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void put(String key, Object value) {
		getCache().put(key, value);
	}

	/**
	 * 异步存储数据
	 * 
	 * @param key
	 * @param value
	 */
	public void asyncPut(final String key, final Object value) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				put(key, value);
			}
		});
	}

	/**
	 * 保存有有效期的数据
	 * 
	 * @param key
	 * @param value
	 * @param expireDate
	 *        指定过期的日期
	 * @return
	 */
	public void put(String key, Object value, Date expireDate) {
		getCache().put(key, value, expireDate);
	}

	/**
	 * 异步存储数据
	 * 
	 * @param key
	 * @param value
	 * @param expireDate
	 *        指定过期的日期
	 */
	public void asyncPut(final String key, final Object value, final Date expireDate) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				put(key, value, expireDate);
			}
		});
	}

	/**
	 * 保存有有效期的数据
	 * 
	 * @param key
	 * @param value
	 * @param TTL
	 *        数据超时的秒数 指定多久以后过期 单位s(秒)
	 * @return
	 */
	public void put(String key, Object value, int TTL) {
		getCache().put(key, value, TTL);
	}

	/**
	 * 异步存储数据
	 * 
	 * @param key
	 * @param value
	 * @param TTL
	 *        数据超时的秒数 指定多久以后过期 单位s(秒)
	 */
	public void asyncPut(final String key, final Object value, final int TTL) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				put(key, value, TTL);
			}
		});
	}

	/**
	 * 返回数据
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		long start = System.currentTimeMillis();
		Object obj = null;
		try {
			obj = getCache().get(key);
		} catch (Exception e) {
			long end = System.currentTimeMillis();
//			if (logger.isErrorEnabled()) {
//				logger.error(LogMessageFormater.getStringMessage(key + " use time=" + (end - start), key, null, null,
//						OperName.GetMemcached), e);
//			}

			obj = getCache().get(key);
		}
		if (logger.isDebugEnabled()) {
			long end = System.currentTimeMillis();
			if ((end - start) > 100)
				logger.debug("time=" + (end - start));
		}
		return obj;
	}

	/**
	 * 返回数据
	 * 
	 * @param key
	 * @return
	 */
	public Object get(int key) {
		return get(String.valueOf(key));
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @author 
	 */
	public void remove(String key) {
		getCache().remove(key);
	}

	/**
	 * 删除
	 * 
	 * @param key
	 */
	public void remove(int key) {
		remove(String.valueOf(key));
	}

	/**
	 * 清空缓存内容
	 */
	public boolean clear() {
		return getCache().clear();
	}

	/**
	 * 获取当前put队列数。
	 * 
	 * @return
	 */
	public int getTask() {
		try {
			ThreadPoolExecutor p = (ThreadPoolExecutor) pool;
			return p.getQueue().size();
		} catch (Exception e) {
			return -1;
		}
	}
}
