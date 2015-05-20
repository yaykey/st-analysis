package com.st.framework.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.module.stock.example.PStockMapExample;
import com.st.framework.persistence.mapper.stock.PStockMapMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("pStockMapManager")
public class PStockMapManager {

	@Autowired
	private PStockMapMapper pStockMapMapper;
	
	
	public int countByExample(PStockMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.countByExample(example);
	}

	
	public int deleteByExample(PStockMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.deleteByExample(example);
	}

	
	public int deleteByPrimaryKey(PStockMapKey key) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.deleteByPrimaryKey(key);
	}

	
	public int insert(PStockMapKey record) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.insert(record);
	}

	
	public int insertSelective(PStockMapKey record) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.insertSelective(record);
	}

	
	public List<PStockMapKey> selectByExample(PStockMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.selectByExample(example);
	}

	
	public int updateByExampleSelective(PStockMapKey record,
			PStockMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.updateByExampleSelective(record, example);
	}

	
	public int updateByExample(PStockMapKey record, PStockMapExample example) {
		DbContextHolder.setDefaultDbType();
		return this.pStockMapMapper.updateByExample(record, example);
	}

}
