package com.st.framework.persistence.mapper.stock;

import com.st.analysis.controller.vo.MMBean;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GDetailExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GDetailMapper {
	int countByExample(GDetailExample example);

	int deleteByExample(GDetailExample example);

	int insert(GDetail record);

	int insertSelective(GDetail record);

	List<GDetail> selectByExample(GDetailExample example);

	int updateByExampleSelective(@Param("record") GDetail record,
			@Param("example") GDetailExample example);

	int updateByExample(@Param("record") GDetail record,
			@Param("example") GDetailExample example);

	void createGDetailTable(@Param("stockCode") String stockCode);

	String selectDetailTableName(@Param("stockCode") String stockCode);

	void insertBatch(@Param("stockCode") String stockCode,
			@Param("list") List<GDetail> list);
	
	void insertStringBatch(@Param("sqlString") String sqlString);

	List<GStockDay> selectStockDay(@Param("stockCode") String stockCode,
			@Param("startDateId") Integer startDateId,
			@Param("endDateId") Integer endDateId);
	
	List<MMBean> selectMMBaseData(@Param("stockCode") String stockCode,
			@Param("dateIds") List dateIds);

	List<GDetail> selectWareByExample(GDetailExample example);

	List<Integer> selectDetailActiveDateId(@Param("stockCode") String stockCode,
			@Param("startDateId") Integer startDateId,
			@Param("endDateId") Integer endDateId, 
			@Param("dateIds") List<Integer> dateIds);
}