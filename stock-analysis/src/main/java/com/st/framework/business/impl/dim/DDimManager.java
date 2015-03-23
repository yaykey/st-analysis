package com.st.framework.business.impl.dim;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.framework.business.BaseManager;
import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.DDimKey;
import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.example.DDimExample;
import com.st.framework.persistence.mapper.BaseMapper;
import com.st.framework.persistence.mapper.stock.DDimMapper;

@Component("dDimManager")
public class DDimManager extends BaseManager<DDimKey, DDim, DDimExample> {

	@Autowired
	private DDimMapper dDimMapper;

	@Override
	public BaseMapper<DDimKey, DDim, DDimExample> getMapper() {

		return this.dDimMapper;
	}

	/**
	 * 
	 * 根据dimId获得DDim对象.
	 * 
	 * dimId为唯一值.
	 * 
	 * @param dimId
	 * @return
	 */
	public DDim selectByDimId(Integer dimId) {
		DDimExample example = new DDimExample();

		DDimExample.Criteria c = example.createCriteria();

		c.andDimIdEqualTo(dimId);

		List<DDim> list = this.selectByExample(example);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 获得分类类型;
	 * 
	 * @param dimtypeId
	 * @return
	 */
	public List<DDim> selectByDimtypeId(Integer dimtypeId) {

		DDimExample example = new DDimExample();

		DDimExample.Criteria c = example.createCriteria();

		c.andDimtypeIdEqualTo(dimtypeId);

		return this.selectByExample(example);
	}
	
	/**
	 * 
	 * 获得未完成新浪si参数
	 * 
	 * @param dimtypeId
	 * @param dimIdNotInList
	 * @return
	 */
	public List<DDim> selectSinaSIDimAndDimIdNotIn(List<Integer> dimIdNotInList) {

		DDimExample example = new DDimExample();

		DDimExample.Criteria c = example.createCriteria();

		c.andDimtypeIdEqualTo(1001);
		c.andDimIdNotIn(dimIdNotInList);

		return this.selectByExample(example);
	}
	
	/**
	 * 
	 * 获得未完成新浪si参数
	 * 
	 * 获得已完成Sina数据
	 * 
	 * @return
	 */
	public List<DDim> selectSinaHasSi () {
		return this.dDimMapper.selectSinaHasSi();
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public List<DDim> selectSinaSIDimAndDimIdNotIn() {
		
		List<DDim> sinaHasSiList = selectSinaHasSi();
		
		List<Integer> dimIdNotInList = new ArrayList<Integer>();
		
		for (int i=0;i<(sinaHasSiList.size()-1); i++) {
			DDim dim = sinaHasSiList.get(i);
			dimIdNotInList.add(dim.getDimId());
		}
		
		DDimExample example = new DDimExample();

		DDimExample.Criteria c = example.createCriteria();

		c.andDimtypeIdEqualTo(1001);
		c.andDimIdNotIn(dimIdNotInList);

		return this.selectByExample(example);
	}

	public List<DDim> selectAddSinaSIDim(List<Integer> addList) {
		DDimExample example = new DDimExample();

		DDimExample.Criteria c = example.createCriteria();

		c.andDimtypeIdEqualTo(1001);
		c.andDimIdIn(addList);

		return this.selectByExample(example);
	}

}
