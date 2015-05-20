package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.GDividendScheme;
import com.st.framework.module.stock.GDividendSchemeKey;
import com.st.framework.module.stock.example.GDividendSchemeExample;
import com.st.framework.persistence.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GDividendSchemeMapper extends BaseMapper<GDividendSchemeKey, GDividendScheme, GDividendSchemeExample> {

	@Select("SELECT distinct STOCK_CODE FROM G_DIVIDEND_SCHEME order by STOCK_CODE")
	public List<String> selectAllCode();
}