package com.thread.pc4;

/**
 * 
 * 同步实现java多线程-生产者和消费者
 */

public class ConsumerProduct {

	public static void main(String[] args) {

		CharNormar char1 = new CharNormar();

		ConsumerNormer consumer = new ConsumerNormer("消费 thread-1:", char1);

		ProductNormar product = new ProductNormar("生产 thread-2:", char1);

		consumer.start();

		product.start();

	}

}

class ConsumerNormer extends Thread {

	private CharNormar char1;

	public ConsumerNormer(String name, CharNormar char1) {

		super(name);

		this.setChar1(char1);

	}

	@Override
	public void run() {

		char ch = 'a';

		while (ch <= 'z') {

			try {

				this.char1.getConsumer();

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			ch++;

		}

	}

	public void setChar1(CharNormar char1) {

		this.char1 = char1;

	}

	public CharNormar getChar1() {

		return char1;

	}

}

class ProductNormar extends Thread {

	private CharNormar char1;

	public ProductNormar(String name, CharNormar char1) {

		super(name);

		this.setChar1(char1);

	}

	@Override
	public void run() {

		char ch = 'a';

		while (ch <= 'z') {

			try {

				this.char1.setProdure(ch);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			ch++;

		}

	}

	public void setChar1(CharNormar char1) {

		this.char1 = char1;

	}

	public CharNormar getChar1() {

		return char1;

	}

}

class CharNormar {

	private char value;

	private boolean flag = false;

	/**
	 * 
	 * 消费
	 * 
	 * 
	 * 
	 * @throws InterruptedException
	 */

	public synchronized void getConsumer() throws InterruptedException {

		if (!flag) {

			wait();

		}

		System.out.println(Thread.currentThread().getName() + ",消费了:"
				+ this.getValue());

		flag = false;

		notify();

	}

	/***
	 * 
	 * 生产
	 * 
	 * 
	 * 
	 * @throws InterruptedException
	 */

	public synchronized void setProdure(char cha) throws InterruptedException {

		if (flag) {

			wait();

		}

		setValue(cha);

		System.out.println(Thread.currentThread().getName() + ",生产了:"
				+ getValue());

		flag = true;

		notify();

	}

	public void setValue(char value) {

		this.value = value;

	}

	public char getValue() {

		return value;

	}

}
