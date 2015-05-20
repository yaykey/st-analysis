package com.st.framework.business.impl.fact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.module.stock.FactLogTask;
import com.st.framework.module.stock.example.FactLogTaskExample;
import com.st.framework.persistence.mapper.stock.FactLogTaskMapper;
import com.st.framework.utils.db.route.DbContextHolder;

@Component("factLogTaskManager")
public class FactLogTaskManager {

	@Autowired
	protected FactLogTaskMapper factLogTaskMapper;

	public int countByExample(FactLogTaskExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.countByExample(example);
	}

	public int deleteByExample(FactLogTaskExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {

		return this.factLogTaskMapper.deleteByPrimaryKey(id);
	}

	public int insert(FactLogTask record) {

		return this.factLogTaskMapper.insert(record);
	}

	public int insertSelective(FactLogTask record) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.insertSelective(record);
	}

	public List<FactLogTask> selectByExample(FactLogTaskExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.selectByExample(example);
	}

	public FactLogTask selectByPrimaryKey(Integer id) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(FactLogTask record,
			FactLogTaskExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(FactLogTask record, FactLogTaskExample example) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactLogTask record) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactLogTask record) {
		DbContextHolder.setDefaultDbType();
		return this.factLogTaskMapper.updateByPrimaryKey(record);
	}

}
