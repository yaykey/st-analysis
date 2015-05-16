package com.st.framework.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.Global;
import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.GLHBSecurities;
import com.st.framework.module.stock.GLHBTop5;
import com.st.framework.module.stock.GLHBTop5Key;
import com.st.framework.module.stock.example.GLHBTop5Example;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.GLHBTop5Mapper;
import com.st.framework.utils.ConfigUtil;

@Component("gLHBTop5Manager")
public class GLHBTop5Manager extends
		BaseManager<GLHBTop5Key, GLHBTop5, GLHBTop5Example> {

	@Autowired
	protected GLHBTop5Mapper gLHBReportMapper;

	@Override
	public BaseMapper<GLHBTop5Key, GLHBTop5, GLHBTop5Example> getMapper() {
		return gLHBReportMapper;
	}

	public void insertBatch(final List<GLHBTop5> batchList) {
		if (batchList != null && batchList.size() > 0) {
			gLHBReportMapper.insertBatch(batchList);

			Global.threadPoolExecutor.execute(new Thread() {
				public void run() {
					try {
						List<GLHBSecurities> secList = new ArrayList<GLHBSecurities>();
						for (GLHBTop5 top5 : batchList) {
							secList.add(new GLHBSecurities(top5.getSecId(),
									top5.getSecName()));
						}

						if (Global._ctx == null) {
							Global._ctx = ConfigUtil.getHelper();
						}

						GLHBSecuritiesManager gLHBSecuritiesManager = (GLHBSecuritiesManager) Global._ctx
								.getBean("gLHBSecuritiesManager");
						gLHBSecuritiesManager.insertBatch(secList);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}
	}

}
