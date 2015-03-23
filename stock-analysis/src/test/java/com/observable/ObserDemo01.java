package com.observable;

public class ObserDemo01 {
	public static void main(String args[]) {
		House h = new House(1000000);
		
		HousePriceObserver hpo1 = new HousePriceObserver("购房者A");
		HousePriceObserver hpo2 = new HousePriceObserver("购房者B");
		HousePriceObserver hpo3 = new HousePriceObserver("购房者C");
		
		h.addObserver(hpo1);
		h.addObserver(hpo2);
		h.addObserver(hpo3);
		
		HouseBuy buy = new HouseBuy();
		
		hpo1.addObserver(buy);
		
		System.out.println(h); // 输出房子价格
		h.setPrice(666666); // 修改房子价格
		System.out.println(h); // 输出房子价格
		
		h.setPrice(111);
	}
};
