package com.st.analysis.controller.vo.lhb;

import java.util.List;

import com.st.framework.module.PersistentObject;
import com.st.framework.module.stock.GLHBTop5;

public class StatisticsBean extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363565160686349790L;

	private double totalBuyAmount;

	private double totalSellAmount;

	private double totalNetAmount;
	
	public StatisticsBean () {
		
	}
	
	public StatisticsBean (List<GLHBTop5> list) {
		if (list != null && list.size() > 0) {
			for (GLHBTop5 top : list) {
				// if ("buy".equalsIgnoreCase(top.type)) {
				this.setTotalBuyAmount(
						this.getTotalBuyAmount()
								+ top.getBuyAmount());
				// } else if ("sell".equalsIgnoreCase(top.type)) {
				this.setTotalSellAmount(
						this.getTotalSellAmount()
								+ top.getSellAmount());
				// }
			}

			this.setTotalNetAmount(
					this.getTotalBuyAmount()
							- this.getTotalSellAmount());
		}
	}

	public double getTotalBuyAmount() {
		return totalBuyAmount;
	}

	public void setTotalBuyAmount(double totalBuyAmount) {
		this.totalBuyAmount = totalBuyAmount;
	}

	public double getTotalSellAmount() {
		return totalSellAmount;
	}

	public void setTotalSellAmount(double totalSellAmount) {
		this.totalSellAmount = totalSellAmount;
	}

	public double getTotalNetAmount() {
		return totalNetAmount;
	}

	public void setTotalNetAmount(double totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}

}
