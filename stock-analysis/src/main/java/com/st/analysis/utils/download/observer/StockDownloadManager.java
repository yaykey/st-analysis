package com.st.analysis.utils.download.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.st.framework.module.stock.DStock;

public class StockDownloadManager<T> extends Observable {

	private String startDate;

	private String endDate;

	private List<T> stockList;

	private String remoteSinaDetailUrl;

	private String clazz;
	
	public StockDownloadManager() {
		
	}

	public StockDownloadManager(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;

		
	}

	public void startDown() {

		for (Object obj : stockList) {
			if (obj instanceof DStock) {
				this.clazz = DStock.class.getName();
				System.out.println((DStock) obj);
			}
		}

	}

	public static void main(String[] args) {
		String startDate = "2010-01-01";
		String endDate = "2014-11-27";

		List<DStock> list = new ArrayList<DStock>();
		DStock dstock = new DStock();
		dstock.setStockCode("300002");
		dstock.setStockTypeCode("sz");
		
		list.add(dstock);
		
		StockDownloadManager<DStock> downloadManager = new StockDownloadManager<DStock>(
				startDate, endDate);
		
		downloadManager.setStockList(list);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<T> getStockList() {
		return stockList;
	}

	public void setStockList(List<T> stockList) {
		
		super.setChanged(); // 设置变化点
		super.notifyObservers(stockList);//被改变
		
		this.stockList = stockList;
	}

	public String getRemoteSinaDetailUrl() {
		return remoteSinaDetailUrl;
	}

	public void setRemoteSinaDetailUrl(String remoteSinaDetailUrl) {
		this.remoteSinaDetailUrl = remoteSinaDetailUrl;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}
