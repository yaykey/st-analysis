package com.st.framework.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GDetailFileError;
import com.st.framework.module.stock.GDetailFileErrorKey;
import com.st.framework.module.stock.example.GDetailFileErrorExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GDetailFileErrorMapper;
import com.st.framework.utils.ConfigUtil;

@Component("gDetailFileErrorManager")
public class GDetailFileErrorManager extends
		BaseManager<GDetailFileErrorKey, GDetailFileError, GDetailFileErrorExample> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(GDetailFileErrorManager.class);

	@Autowired
	private GDetailFileErrorMapper gDetailFileErrorMapper;

	@Override
	public BaseMapper<GDetailFileErrorKey, GDetailFileError, GDetailFileErrorExample> getMapper() {

		return gDetailFileErrorMapper;
	}

	public void increaseBalance(GDetailFileError gDetailFileErrorKey) {
		GDetailFileError gDetailFileError = this.selectByPrimaryKey(gDetailFileErrorKey);
		
		if (gDetailFileError == null) {			
			this.insert(gDetailFileErrorKey);			
		} else {
			gDetailFileError.setBalance(gDetailFileError.getBalance()+1);
			
			if (gDetailFileError.getBalance() > 9) {
				gDetailFileError.setIsDeal(true);
			}
			
			this.updateByPrimaryKey(gDetailFileError);
		}
	}
	
	public List selectErrorDateIds (String stockCode, Integer yearDateId) {
		if (stockCode == null) {
			return null;
		}
		
		GDetailFileErrorExample example = new GDetailFileErrorExample();
		GDetailFileErrorExample.Criteria c = example.createCriteria();
		
		if (stockCode.toUpperCase().indexOf("SZ") != -1 ||
				stockCode.toUpperCase().indexOf("SH") != -1) {
			c.andStockCodeEqualTo(stockCode);
		} else {
			c.andStockCodeLike("%" + stockCode + "%");
		}
		
		c.andBalanceLessThanOrEqualTo(9);
		
		if (yearDateId != null) {
			c.andDateIdLike(yearDateId + "%");
		}
		
		//c.andIsDealEqualTo(false);
		
		List<GDetailFileError> list = this.selectByExample(example);
		
		List result = null;
		
		if (list != null && list.size() > 0) {
			result = new ArrayList();
			for (GDetailFileError error : list) {
				result.add(error.getDateId());
			}
		}
		
		return result;
	}
	
	public static void main (String [] args) {
		GDetailFileErrorManager gDetailFileErrorManager = 
				(GDetailFileErrorManager)ConfigUtil.getHelper().getBean("gDetailFileErrorManager");
		
		List list = gDetailFileErrorManager.selectErrorDateIds("300001", 2014);
		
		System.out.println(list);
	}

}
