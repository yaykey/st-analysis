package com.st.analysis.controller.vo.charts;

import com.st.framework.module.stock.GStockDay;

public class CountBean {

	private int openPerUp;

	private int openPerDown;

	private int closePerUp;

	private int closePerDown;

	private int highPerUp;

	private int highPerDown;

	private int lowPerUp;

	private int lowPerDown;

	private double highPerAvg;

	private double highPerAvgUp;

	private double highPerAvgDown;
	
	private double lowPerAvg;

	private double lowPerAvgUp;

	private double lowPerAvgDown;
	
	private double closePerAvg;

	private double closePerAvgUp;

	private double closePerAvgDown;

	// double nextHighperAvg = 0.0;
	double maxHighperAvgUp = 0.0;
	double maxHighperAvgDown = 0.0;
	
	double maxLowperAvgUp = 0.0;
	double maxLowperAvgDown = 0.0;
	
	double maxCloseperAvgUp = 0.0;
	double maxCloseperAvgDown = 0.0;

	public void countInfo(GStockDay stockDay) {
		if (stockDay.getOpenPer() > 0) {
			this.openPerUp++;
		} else {
			this.openPerDown++;
		}

		if (stockDay.getClosePer() > 0) {
			this.maxCloseperAvgUp += stockDay.getClosePer();
			this.closePerUp++;
		} else {
			this.maxCloseperAvgDown += stockDay.getClosePer();
			this.closePerDown++;
		}

		if (stockDay.getHighPer() > 0) {
			this.highPerUp++;
			maxHighperAvgUp += stockDay.getHighPer();
		} else {
			this.highPerDown++;
			maxHighperAvgDown += stockDay.getHighPer();
		}

		if (stockDay.getLowPer() > 0) {
			this.maxLowperAvgUp += stockDay.getLowPer();
			this.lowPerUp++;
		} else {
			this.maxLowperAvgDown += stockDay.getLowPer();
			this.lowPerDown++;
		}
	}

	public int getOpenPerUp() {
		return openPerUp;
	}

	public void setOpenPerUp(int openPerUp) {
		this.openPerUp = openPerUp;
	}

	public int getOpenPerDown() {
		return openPerDown;
	}

	public void setOpenPerDown(int openPerDown) {
		this.openPerDown = openPerDown;
	}

	public int getClosePerUp() {
		return closePerUp;
	}

	public void setClosePerUp(int closePerUp) {
		this.closePerUp = closePerUp;
	}

	public int getClosePerDown() {
		return closePerDown;
	}

	public void setClosePerDown(int closePerDown) {
		this.closePerDown = closePerDown;
	}

	public int getHighPerUp() {
		return highPerUp;
	}

	public void setHighPerUp(int highPerUp) {
		this.highPerUp = highPerUp;
	}

	public int getHighPerDown() {
		return highPerDown;
	}

	public void setHighPerDown(int highPerDown) {
		this.highPerDown = highPerDown;
	}

	public int getLowPerUp() {
		return lowPerUp;
	}

	public void setLowPerUp(int lowPerUp) {
		this.lowPerUp = lowPerUp;
	}

	public int getLowPerDown() {
		return lowPerDown;
	}

	public void setLowPerDown(int lowPerDown) {
		this.lowPerDown = lowPerDown;
	}

	public double getHighPerAvg() {
		try {
			this.highPerAvg = (this.maxHighperAvgDown + this.maxHighperAvgUp)
					/ (this.highPerDown + this.highPerUp);
		} catch (Exception ex) {

		}
		return highPerAvg;
	}

	public void setHighPerAvg(double highPerAvg) {
		this.highPerAvg = highPerAvg;
	}

	public double getHighPerAvgUp() {
		try {
			this.highPerAvgUp = this.maxHighperAvgUp / this.highPerUp;
		} catch (Exception ex) {

		}
		return highPerAvgUp;
	}

	public void setHighPerAvgUp(double highPerAvgUp) {
		this.highPerAvgUp = highPerAvgUp;
	}

	public double getHighPerAvgDown() {
		try {
			this.highPerAvgDown = this.maxHighperAvgDown / this.highPerDown;
		} catch (Exception ex) {

		}
		return highPerAvgDown;
	}

	public void setHighPerAvgDown(double highPerAvgDown) {
		this.highPerAvgDown = highPerAvgDown;
	}

	public double getClosePerAvg() {
		try {
			this.closePerAvg = (this.maxCloseperAvgDown + this.maxCloseperAvgUp)
					/ (this.closePerDown + this.closePerUp);
		} catch (Exception ex) {

		}
		return closePerAvg;
	}

	public void setClosePerAvg(double closePerAvg) {
		this.closePerAvg = closePerAvg;
	}

	public double getClosePerAvgUp() {
		try {
			this.closePerAvgUp = this.maxCloseperAvgUp / this.closePerUp;
		} catch (Exception ex) {

		}
		return closePerAvgUp;
	}

	public void setClosePerAvgUp(double closePerAvgUp) {
		this.closePerAvgUp = closePerAvgUp;
	}

	public double getClosePerAvgDown() {
		try {
			this.closePerAvgDown = this.maxCloseperAvgDown / this.closePerDown;
		} catch (Exception ex) {

		}
		return closePerAvgDown;
	}

	public void setClosePerAvgDown(double closePerAvgDown) {
		this.closePerAvgDown = closePerAvgDown;
	}

	public double getMaxHighperAvgUp() {
		return maxHighperAvgUp;
	}

	public void setMaxHighperAvgUp(double maxHighperAvgUp) {
		this.maxHighperAvgUp = maxHighperAvgUp;
	}

	public double getMaxHighperAvgDown() {
		return maxHighperAvgDown;
	}

	public void setMaxHighperAvgDown(double maxHighperAvgDown) {
		this.maxHighperAvgDown = maxHighperAvgDown;
	}

	public double getMaxLowperAvgUp() {
		return maxLowperAvgUp;
	}

	public void setMaxLowperAvgUp(double maxLowperAvgUp) {
		this.maxLowperAvgUp = maxLowperAvgUp;
	}

	public double getMaxLowperAvgDown() {
		return maxLowperAvgDown;
	}

	public void setMaxLowperAvgDown(double maxLowperAvgDown) {
		this.maxLowperAvgDown = maxLowperAvgDown;
	}

	public double getMaxCloseperAvgUp() {
		return maxCloseperAvgUp;
	}

	public void setMaxCloseperAvgUp(double maxCloseperAvgUp) {
		this.maxCloseperAvgUp = maxCloseperAvgUp;
	}

	public double getMaxCloseperAvgDown() {
		return maxCloseperAvgDown;
	}

	public void setMaxCloseperAvgDown(double maxCloseperAvgDown) {
		this.maxCloseperAvgDown = maxCloseperAvgDown;
	}

	public double getLowPerAvg() {
		try {
			this.lowPerAvg = (this.maxLowperAvgDown + this.maxLowperAvgUp)
					/ (this.lowPerDown + this.lowPerUp);
		} catch (Exception ex) {

		}
		return lowPerAvg;
	}

	public void setLowPerAvg(double lowPerAvg) {
		this.lowPerAvg = lowPerAvg;
	}

	public double getLowPerAvgUp() {
		try {
			this.lowPerAvgUp = this.maxLowperAvgUp / this.lowPerUp;
		} catch (Exception ex) {

		}
		return lowPerAvgUp;
	}

	public void setLowPerAvgUp(double lowPerAvgUp) {
		this.lowPerAvgUp = lowPerAvgUp;
	}

	public double getLowPerAvgDown() {
		try {
			this.lowPerAvgDown = this.maxLowperAvgDown / this.lowPerDown;
		} catch (Exception ex) {

		}
		return lowPerAvgDown;
	}

	public void setLowPerAvgDown(double lowPerAvgDown) {
		this.lowPerAvgDown = lowPerAvgDown;
	}
}
