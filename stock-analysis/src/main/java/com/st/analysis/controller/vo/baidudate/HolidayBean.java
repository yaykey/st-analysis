package com.st.analysis.controller.vo.baidudate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.bean.MorphDynaBean;

import com.st.framework.module.stock.FactDateHoliday;

public class HolidayBean extends FactDateHoliday {

	private List<MorphDynaBean> list = new ArrayList<MorphDynaBean>();

	public List<MorphDynaBean> getList() {
		return list;
	}

	public void setList(List<MorphDynaBean> list) {
		this.list = list;
	}

}
