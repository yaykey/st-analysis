package com.st.framework.business.impl.fact;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.module.stock.FactProxy;
import com.st.framework.module.stock.FactProxyKey;
import com.st.framework.module.stock.example.FactProxyExample;
import com.st.framework.persistence.mapper.stock.FactProxyMapper;
import com.st.framework.utils.db.route.DbContextHolder;
import com.st.framework.utils.page.Page;

@Component("factProxyManager")
public class FactProxyManager {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(FactProxyManager.class);

	@Autowired
	protected FactProxyMapper factProxyMapper;

	public int countByExample(FactProxyExample example) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.countByExample(example);
	}

	public int deleteByExample(FactProxyExample example) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(FactProxyKey key) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.deleteByPrimaryKey(key);
	}

	public int insert(FactProxy record) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.insert(record);
	}

	public int insertSelective(FactProxy record) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.insertSelective(record);
	}

	public List<FactProxy> selectByExample(FactProxyExample example) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.selectByExample(example);
	}

	public FactProxy selectByPrimaryKey(FactProxyKey key) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.selectByPrimaryKey(key);
	}

	public int updateByExampleSelective(FactProxy record,
			FactProxyExample example) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(FactProxy record, FactProxyExample example) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(FactProxy record) {							   
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(FactProxy record) {
		DbContextHolder.setDefaultDbType();
		return factProxyMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 
	 * 获得Page对象
	 * 
	 * @param example
	 * @param pageSize
	 * @return
	 */
	public Page selectPageByExample (FactProxyExample example, Integer pageSize) {
		DbContextHolder.setDefaultDbType();
		return new Page(this.countByExample(example), pageSize);
	}
	
	public void saveOrUpdate (FactProxy factProxy) {
		DbContextHolder.setDefaultDbType();
		if (factProxy == null) {
			return;
		}
		
		try {
			this.insert(factProxy);
		} catch (Exception ex) {
			
			logger.debug("saveOrUpdate(FactProxy) - Exception ex=" + ex.getMessage());
						
			factProxy.setCreateDate(null);
			this.updateByPrimaryKeySelective(factProxy);
		}
	}

}
