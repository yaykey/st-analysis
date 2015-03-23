package com.observable;

import java.util.Observable;
import java.util.Observer;

public class HouseBuy extends Observable implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof HousePriceObserver) {
			HousePriceObserver hp = (HousePriceObserver) o;

			System.out.println(hp.getName() + "->买->" + hp.getPrice());
			
			
		}
		
	}

}
