package com.thread.pc1;

/**
 * 生产者
 */
class Producer implements Runnable {
	private Storage storage;

	public Producer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		
		
		for (int i=1; i<=100; i++) {
			Product product = new Product("" + (1000 + i), "电话" + (1000 + i));
			storage.push(product);
		}
		
	}

}
