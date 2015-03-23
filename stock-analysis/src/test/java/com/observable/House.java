package com.observable;

import java.util.Observable;

public class House extends Observable { // 表示房子可以被观察
	private float price;// 价钱

	public House(float price) {
		this.price = price;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		// 每一次修改的时候都应该引起观察者的注意
		super.setChanged(); // 设置变化点
		super.notifyObservers(price);// 价格被改变
		this.price = price;
	}

	public String toString() {
		return "房子价格为：" + this.price;
	}
};
