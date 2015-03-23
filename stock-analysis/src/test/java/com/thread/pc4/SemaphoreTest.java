package com.thread.pc4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*

 * 信号量实现java多线程-生产者和消费者

 * @author Administrator

 *

 */

public class SemaphoreTest {

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(2);

		WareHouse house = new WareHouse();

		service.execute(new Product4Sem(house));

		service.execute(new Consumer4Sem(house));

		service.shutdown();

	}

}

class WareHouse {

	/**
	 * 
	 * 生产,或者是2，生产了两个在消费
	 */

	private final Semaphore pSemaphore = new Semaphore(1);

	/**
	 * 
	 * 消费
	 */

	private final Semaphore cCemaphore = new Semaphore(0);

	/**
	 * 
	 * 核心锁
	 */

	private final Semaphore mutex = new Semaphore(1);

	private final List<String> list = new ArrayList<String>(12);

	/**
	 * 产品
	 * 
	 * @param str
	 */
	public void product(String str) {

		try {

			pSemaphore.acquire(1);

			/**
			 * 
			 * 生产者和消费者不冲突
			 */

			mutex.acquire(1);

			list.add(str);

			System.out.println("P..生产了.." + str);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			mutex.release(1);

			cCemaphore.release(1);

		}

	}

	/**
	 * 消费者
	 * @return
	 */
	public String consumer() {

		try {

			cCemaphore.acquire(1);

			mutex.acquire(1);

			String str = list.remove(0);

			Thread.sleep(100);
			
			System.out.println("C..消费了.." + str);

			return str;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		} finally {

			mutex.release(1);

			pSemaphore.release(1);

		}

	}

}

class Product4Sem implements Runnable {

	private WareHouse house;

	public Product4Sem(WareHouse house) {

		this.house = house;

	}

	public void run() {

//		for (int i = 'a'; i <= 'z'; i++) {
		for (int i = 1; i <= 100; i++) {

			house.product("" +  i);

		}

	}

}

class Consumer4Sem implements Runnable {

	private WareHouse house;

	public Consumer4Sem(WareHouse house) {

		this.house = house;

	}

	public void run() {

//		for (int i = 'a'; i <= 'z'; i++) {
		for (int i = 1; i <= 100; i++) {

			house.consumer();

		}

	}

}