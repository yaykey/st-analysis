package com.thread.pc4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Java 5SE lock和condition实现多线程-生产者和消费者
 * 
 * 
 */

public class ConditionTest {

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(2);

		WareHouse4Condition house = new WareHouse4Condition();

		service.execute(new Product4Con(house));

		service.execute(new Consumer4Con(house));

		service.shutdown();

	}

}

class WareHouse4Condition {

	private Lock lock = new ReentrantLock();

	private Condition condition4product = lock.newCondition();

	private Condition condition4consumer = lock.newCondition();

	private String ch = null;

	public void product(String ch) {

		lock.lock();

		try {

			if (this.ch != null) {

				condition4product.await();

			}

			this.ch = ch;

			System.out.println("P..生产.." + this.ch);

			condition4consumer.signal();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			lock.unlock();

		}

	}

	public String consumer() {

		lock.lock();

		String temp = null;

		try {

			if (this.ch == null) {

				condition4consumer.await();

			}

			temp = this.ch;

			System.out.println("C..消费.." + temp);

			this.ch = null;

			condition4product.signal();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			lock.unlock();

		}

		return temp;

	}

}

class Product4Con implements Runnable {

	private WareHouse4Condition house;

	Product4Con(WareHouse4Condition house) {

		this.house = house;

	}

	public void run() {

		for (int i = 'a'; i <= 'z'; i++) {

			house.product("" + (char) i);

		}

	}

}

class Consumer4Con implements Runnable {

	private WareHouse4Condition house;

	Consumer4Con(WareHouse4Condition house) {

		this.house = house;

	}

	public void run() {

		for (int i = 'a'; i <= 'z'; i++) {

			this.house.consumer();

		}

	}

}