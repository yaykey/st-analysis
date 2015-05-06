package com.st.framework.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.st.framework.module.example.BaseExample;
import com.st.framework.module.stock.GDetail;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.utils.page.Page;

public abstract class BaseManager<K, T, E extends BaseExample> {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BaseManager.class);

//	@Autowired
//	public SqlSessionFactory sqlSessionFactory;
	
	
	public abstract BaseMapper<K, T, E> getMapper();

	public int countByExample(E example) {

		return this.getMapper().countByExample(example);
	}

	public int deleteByExample(E example) {

		return this.getMapper().deleteByExample(example);
	}

	public int deleteByPrimaryKey(K id) {

		return this.getMapper().deleteByPrimaryKey(id);
	}

	public int insert(T record) {

		return this.getMapper().insert(record);
	}

	public int insertSelective(T record) {

		return this.getMapper().insertSelective(record);
	}

	public List<T> selectByExample(E example) {

		return this.getMapper().selectByExample(example);
	}

	public T selectByPrimaryKey(K id) {
		return this.getMapper().selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(T record, E example) {

		return this.getMapper().updateByExampleSelective(record, example);
	}

	public int updateByExample(T record, E example) {

		return this.getMapper().updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(T record) {

		return this.getMapper().updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(T record) {

		return this.getMapper().updateByPrimaryKey(record);
	}

	/**
	 * 
	 * 获得Page对象
	 * 
	 * @param example
	 * @param pageSize
	 * @return
	 */
	public Page selectPageByExample(E example, Integer pageSize) {
		Page page = new Page(this.countByExample(example), pageSize);
		example.setPage(page);
		return page;
	}

	/**
	 * 
	 * 获得所有对象
	 * 
	 * @return
	 */
	public List<T> selectAll() {
		return this.selectByExample(null);
	}

	public void insertOrUpdate(T record) {
		if (record == null) {
			return;
		}

		try {
			this.insert(record);
		} catch (DuplicateKeyException ex) {
			this.updateByPrimaryKey(record);
		}
	}

	public void insertOrUpdateSelective(T record) {
		try {
			this.insertSelective(record);
		} catch (DuplicateKeyException ex) {
			logger.warn(ex);
			this.updateByPrimaryKeySelective(record);
		}
	}

	public void insertBatch(List<T> batchList) {
		this.getMapper().insertBatch(batchList);
	}

	private List<T> batchBufferList = null;
	private Integer maxBatchBufferSize = 0;

	public void insertBatch(T record) {
		synchronized (maxBatchBufferSize) {
			if (batchBufferList == null) {
				batchBufferList = new ArrayList<T>();
			}
			batchBufferList.add(record);
			maxBatchBufferSize++;

			if (maxBatchBufferSize > 1000) {
				try {
					this.insertBatch(batchBufferList);
				} catch (DuplicateKeyException e) {
					logger.debug(e);
				}

				batchBufferList.clear();
				batchBufferList = null;
				maxBatchBufferSize = 0;
			}

//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//			}
		}
	}

	public void insertFlushBatch() {
		synchronized (maxBatchBufferSize) {
			if (batchBufferList != null && batchBufferList.size() > 0) {
				try {
					this.insertBatch(batchBufferList);
				} catch (DuplicateKeyException e) {
					logger.debug(e);
				}
			}

			System.gc();
		}
	}

}
