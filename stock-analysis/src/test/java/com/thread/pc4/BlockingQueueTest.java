package com.thread.pc4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*

 * 阻塞队列实现多线程-生产者和消费者

 *

 */

public class BlockingQueueTest {

	public static void main(String[] args) {

		Basket basket = new Basket();

		Product1 product1 = new Product1(basket);

		Consumer1 consumer1 = new Consumer1(basket);

		ExecutorService service = Executors.newCachedThreadPool();

		service.execute(product1);

		service.execute(consumer1);

		service.shutdown();

	}

}

class Basket {

	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(
			1);

	public String getConsumer() throws InterruptedException {

		String ggString = queue.take();

		System.out.println("2..消费了: " + ggString);

		return ggString;

	}

	public void setProduct(String str) throws InterruptedException {

		boolean flag = this.queue.offer(str, 10, TimeUnit.MILLISECONDS);

		if (flag)

			System.out.println("1..生产了: " + str + "--");

	}

}

class Product1 implements Runnable {

	private Basket basket;

	public Product1(Basket basket) {

		this.basket = basket;

	}

	public void run() {

		char ch = 'a';

		while (ch <= 'z') {

			try {

				this.basket.setProduct("" + ch);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			ch++;

		}

	}

}

class Consumer1 implements Runnable {

	private Basket basket;

	public Consumer1(Basket basket) {

		this.basket = basket;

	}

	public void run() {

		char ch = 'a';

		while (ch <= 'z') {

			try {

				this.basket.getConsumer();

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			ch++;

		}

	}

}
