package com.st.framework.business.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDetailSuspension;
import com.st.framework.module.stock.GDetailSuspensionKey;
import com.st.framework.module.stock.example.GDetailSuspensionExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDetailSuspensionMapper;

@Component("gDetailSuspensionManager")
public class GDetailSuspensionManager
		extends
		BaseManager<GDetailSuspensionKey, GDetailSuspension, GDetailSuspensionExample> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(GDetailSuspensionManager.class);

	@Autowired
	private GDetailSuspensionMapper gDetailSuspensionMapper;

	@Override
	public BaseMapper<GDetailSuspensionKey, GDetailSuspension, GDetailSuspensionExample> getMapper() {

		return gDetailSuspensionMapper;
	}

	public void increaseBalance(GDetailSuspensionKey gDetailSuspensionKey) {
		GDetailSuspension gDetailSuspension = this
				.selectByPrimaryKey(gDetailSuspensionKey);

		if (gDetailSuspension == null) {
			gDetailSuspension = new GDetailSuspension(gDetailSuspensionKey);

			this.insert(gDetailSuspension);

		}

		gDetailSuspension.setBalance(gDetailSuspension.getBalance() + 1);

		this.updateByPrimaryKey(gDetailSuspension);
	}

	/**
	 * 停盘日期
	 * 
	 * 2014-01-01
	 * 
	 * @param start
	 * @param end
	 * @param stockCode
	 * @return
	 */
	public List<String> selectSuspensionDays(String start, String end,
			String stockCode) {
		GDetailSuspensionExample example = new GDetailSuspensionExample();
		GDetailSuspensionExample.Criteria c = example.createCriteria();

		
		c.andStockCodeLike(stockCode);
		
		c.andDateIdBetween(Integer.parseInt(start.replaceAll("-", "")),
				Integer.parseInt(end.replaceAll("-", "")));
		c.andBalanceGreaterThan(3);

		List<GDetailSuspension> suspensionList = this.selectByExample(example);

		List<String> list = new ArrayList<String>();

		DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

		for (GDetailSuspension suspension : suspensionList) {
			try {
				list.add(df2.format(df1.parse("" + suspension.getDateId())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
