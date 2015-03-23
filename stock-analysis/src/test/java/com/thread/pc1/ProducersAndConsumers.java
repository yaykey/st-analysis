package com.thread.pc1;

public class ProducersAndConsumers {
	public static void main(String[] args) {
		Storage storage = new Storage();
		
		Thread consumer = new Thread(new Consumer(storage));
		consumer.setName("消费者");
		
		Thread producer = new Thread(new Producer(storage));
		producer.setName("生产者");
		
		consumer.start();
		producer.start();
	}
}
