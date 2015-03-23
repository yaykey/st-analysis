package com.thread.pc1;

/**
 * 消费者
 */
class Consumer implements Runnable {
	private Storage storage;

	public Consumer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		
		for (int i=0; i<200; i++) {
			storage.pop();
			
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}*/
		}
		
		
	}
}
