package com.st.framework.business.impl;

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
			
			this.updateByPrimaryKey(gDetailFileError);
		}
	}

}
