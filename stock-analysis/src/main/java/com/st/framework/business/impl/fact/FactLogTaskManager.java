package com.st.framework.business.impl.fact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.module.stock.FactLogTask;
import com.st.framework.module.stock.example.FactLogTaskExample;
import com.st.framework.persistence.mapper.stock.FactLogTaskMapper;

@Component("factLogTaskManager")
public class FactLogTaskManager {

	@Autowired
	protected FactLogTaskMapper factLogTaskMapper;

	public int countByExample(FactLogTaskExample example) {

		return this.factLogTaskMapper.countByExample(example);
	}

	public int deleteByExample(FactLogTaskExample example) {

		return this.factLogTaskMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {

		return this.factLogTaskMapper.deleteByPrimaryKey(id);
	}

	public int insert(FactLogTask record) {

		return this.factLogTaskMapper.insert(record);
	}

	public int insertSelective(FactLogTask record) {

		return this.factLogTaskMapper.insertSelective(record);
	}

	public List<FactLogTask> selectByExample(FactLogTaskExample example) {

		return this.factLogTaskMapper.selectByExample(example);
	}

	public FactLogTask selectByPrimaryKey(Integer id) {

		return this.factLogTaskMapper.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(FactLogTask record,
			FactLogTaskExample example) {

		return this.factLogTaskMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(FactLogTask record, FactLogTaskExample example) {

		return this.factLogTaskMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactLogTask record) {

		return this.factLogTaskMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactLogTask record) {

		return this.factLogTaskMapper.updateByPrimaryKey(record);
	}

}
