package com.st.framework.persistence.mapper.stock;


import java.util.List;

import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.DDimKey;
import com.st.framework.module.stock.example.DDimExample;
import com.st.framework.persistence.mapper.BaseMapper;

public interface DDimMapper extends BaseMapper<DDimKey, DDim, DDimExample>{
    
	/**
	 * 
	 * 获得已完成sina 参数
	 * 
	 * @return
	 */
	List<DDim> selectSinaHasSi();
	
}