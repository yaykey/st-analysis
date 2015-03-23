package com.observable;

import java.util.Observable;
import java.util.Observer;

public class HousePriceObserver extends Observable implements Observer {

	/**
	 * 价钱
	 */
	private float price;

	private String name;

	public HousePriceObserver(String name) { // 设置每一个购房者的名字
		this.name = name;
	}

	public void update(Observable o, Object arg) {
		if (arg instanceof Float) {
			System.out.print(this.name + "观察到价格更改为：");
			System.out.println(((Float) arg).floatValue());
		}

		if (o instanceof House) {
			House h = (House) o;

			System.out.println(h.getPrice());
			
			this.setPrice(h.getPrice());
		}
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		super.setChanged(); // 设置变化点
		super.notifyObservers(price);// 价格被改变
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
};
