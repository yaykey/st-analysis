package com.st.framework.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.PStockMapExample;
import com.st.framework.persistence.mapper.stock.PStockMapMapper;

@Component("pStockMapManager")
public class PStockMapManager {

	@Autowired
	private PStockMapMapper pStockMapMapper;
	
	
	public int countByExample(PStockMapExample example) {
		
		return this.pStockMapMapper.countByExample(example);
	}

	
	public int deleteByExample(PStockMapExample example) {
		
		return this.pStockMapMapper.deleteByExample(example);
	}

	
	public int deleteByPrimaryKey(PStockMapKey key) {
		
		return this.pStockMapMapper.deleteByPrimaryKey(key);
	}

	
	public int insert(PStockMapKey record) {
		
		return this.pStockMapMapper.insert(record);
	}

	
	public int insertSelective(PStockMapKey record) {
		
		return this.pStockMapMapper.insertSelective(record);
	}

	
	public List<PStockMapKey> selectByExample(PStockMapExample example) {
		
		return this.pStockMapMapper.selectByExample(example);
	}

	
	public int updateByExampleSelective(PStockMapKey record,
			PStockMapExample example) {
		
		return this.pStockMapMapper.updateByExampleSelective(record, example);
	}

	
	public int updateByExample(PStockMapKey record, PStockMapExample example) {
		
		return this.pStockMapMapper.updateByExample(record, example);
	}

}
