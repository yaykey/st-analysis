package com.st.framework.business.impl.dim;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.business.impl.PStockMapManager;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.DStockExample;
import com.st.framework.module.stock.example.PStockMapExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.DStockMapper;

@Component("dStockManager")
public class DStockManager extends BaseManager<String, DStock, DStockExample> {

	@Autowired
	private DStockMapper dStockMapper;

	@Autowired
	private PStockMapManager pStockMapManager;

	@Override
	public BaseMapper<String, DStock, DStockExample> getMapper() {

		return this.dStockMapper;
	}

	/**
	 * 
	 * 
	 * 
	 * @param dimId
	 * @param notInStockCodes
	 * @return
	 */
	public List<DStock> selectByDimAndNotIn(Integer dimId,
			List<String> notInStockCodes) {
		PStockMapExample pStockMapExample = new PStockMapExample();

		PStockMapExample.Criteria c = pStockMapExample.createCriteria();
		c.andDimIdEqualTo(dimId);

		if (notInStockCodes != null) {
			c.andStockCodeNotIn(notInStockCodes);
		}

		List<PStockMapKey> keyList = pStockMapManager
				.selectByExample(pStockMapExample);

		DStockExample dStockExample = new DStockExample();
		dStockExample.setOrderByClause("STOCK_CODE");

		DStockExample.Criteria dsc = dStockExample.createCriteria();

		List<String> stCodeList = new ArrayList<String>();

		for (PStockMapKey psk : keyList) {
			stCodeList.add(psk.getStockCode());
		}
		dsc.andStockCodeNotIn(notInStockCodes);
		dsc.andStockCodeIn(stCodeList);

		return this.selectByExample(dStockExample);
	}
	
	public List<DStock> selectByCYB () {
		
		DStockExample dStockExample = new DStockExample();
		dStockExample.setOrderByClause("STOCK_CODE");

		DStockExample.Criteria c = dStockExample.createCriteria();
		
		//STOCK_CODE>300000 and STOCK_CODE<399999
		c.andStockCodeBetween("300000", "399999");

		return this.selectByExample(dStockExample);
	}
	
	public String selectMaxStockCodeByCYB () {
		DStockExample dStockExample = new DStockExample();
		
		dStockExample.setStart(0);
		dStockExample.setPageSize(1);
		
		dStockExample.setOrderByClause("STOCK_CODE DESC");

		DStockExample.Criteria c = dStockExample.createCriteria();
		
		//STOCK_CODE>300000 and STOCK_CODE<399999
		c.andStockCodeBetween("300000", "399999");
		c.andStockStateIsNotNull();
		c.andStockStateEqualTo((byte)1);
		
		return this.selectByExample(dStockExample).get(0).getStockCode();
	}

}
