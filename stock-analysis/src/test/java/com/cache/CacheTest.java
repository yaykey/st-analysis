package com.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;
import com.st.framework.utils.cache.MemcachedUtils;

public class CacheTest {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CacheTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println("test start");
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
		System.out.println("test end");
		MemcachedUtils.INSTANCE.stop();
	}
	
	
	//@Test
	public void testMemcached() {
		if (logger.isDebugEnabled()) {
			logger.debug("testMemcached() - start");
		}

		MemcachedUtils.INSTANCE.put("key1", "v1");

		Assert.assertEquals(MemcachedUtils.INSTANCE.get("key1"), "v1");

		logger.info("key1=" + MemcachedUtils.INSTANCE.get("key1"));

		if (logger.isDebugEnabled()) {
			logger.debug("testMemcached() - end");
		}
	}

	@Test
	public void testAverage() {

		try {
			long totaltime = System.currentTimeMillis();

			int threadcount = 100;
			int count = 1000;
			List<Long> result = Collections
					.synchronizedList(new ArrayList<Long>());

			CountDownLatch startSignal = new CountDownLatch(1);
			CountDownLatch doneSignal = new CountDownLatch(threadcount);

			for (int i = 0; i < threadcount; i++) {
				new Thread(new Task(String.valueOf(i), result, count,
						startSignal, doneSignal)).start();
			}

			startSignal.countDown();

			doneSignal.await();

			if (result.size() == threadcount) {
				long total = 0;
				for (long l : result) {
					total += l;
				}

				System.out.println(new StringBuffer()
						.append("cache test consume: ").append(total)
						.append(", average boundle consume: ")
						.append(total / (long) result.size())
						.append(", average per request :")
						.append(total / (long) result.size() / (long) count));
			}

			totaltime = System.currentTimeMillis() - totaltime;

			System.out.println("total consume: " + totaltime);

			Thread.sleep(1000);

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	class Task implements java.lang.Runnable {

		String name;
		List<Long> result;
		int count;
		CountDownLatch start;
		CountDownLatch done;

		public Task(String n, List<Long> r, int c, CountDownLatch start,
				CountDownLatch done) {
			name = n;
			count = c;
			result = r;
			this.start = start;
			this.done = done;
		}

		public void run() {

			// IMemcachedCache cache = manager.getCache("mclient0");

			try {
				start.await();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			long time = System.currentTimeMillis();

			for (int i = 0; i < count; i++) {
				MemcachedUtils.INSTANCE.put(String.valueOf(i), i);

				org.junit.Assert.assertEquals(
						MemcachedUtils.INSTANCE.get(String.valueOf(i)), i);

				String nodename = new StringBuilder("node").append(name)
						.append(i).toString();

				Node node = new Node();
				node.setName(nodename);

				MemcachedUtils.INSTANCE.put(node.getName(), node);
				org.junit.Assert.assertEquals(((Node) MemcachedUtils.INSTANCE
						.get(node.getName())).getName(), nodename);

			}

			time = System.currentTimeMillis() - time;

			result.add(time);
			done.countDown();
		}

	}
}
