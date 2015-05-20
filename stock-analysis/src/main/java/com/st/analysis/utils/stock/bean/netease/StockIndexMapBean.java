package com.st.analysis.utils.stock.bean.netease;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.st.framework.module.PersistentObject;
import com.st.framework.module.stock.DStockIndex;

public class StockIndexMapBean extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2746241939135419094L;

	private Integer page;

	private Integer count;

	private Integer order;

	private Integer total;

	private Integer pagecount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date time;

	private String key;

	private List<StockIndexBean> list;

	public List<DStockIndex> getDSIndexList (String queryType) {
		List<DStockIndex> gdlist = null;
		
		if (this.list != null && this.list.size() > 0) {
			gdlist = new ArrayList<DStockIndex>();
			for (StockIndexBean idx : this.list) {
				gdlist.add(new DStockIndex(idx, queryType));
			}
		}
		
		return gdlist;
	};
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<StockIndexBean> getList() {
		return list;
	}

	public void setList(List<StockIndexBean> list) {
		this.list = list;
	}

}
