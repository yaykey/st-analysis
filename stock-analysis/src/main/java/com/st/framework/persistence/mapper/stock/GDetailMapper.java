package com.st.framework.persistence.mapper.stock;

import com.st.analysis.controller.vo.MMBean;
import com.st.analysis.controller.vo.range.RangeBean;
import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GStockDay;
import com.st.framework.module.stock.example.GDetailExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
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
	
//	List<RangeBean> selectAnalysisData (
//			@Param("stockCode") String stockCode
//			,@Param("timeDimtypeId") Integer timeDimtypeId
//			,@Param("volDimId") Integer volDimId
//			,@Param("volDimIdBegin") Integer volDimIdBegin
//			,@Param("volDimIdEnd") Integer volDimIdEnd
//	);
	
	void insertAnalysisData (
			@Param("rptId") Integer rptId
			,@Param("dateId") Integer dateId
			,@Param("indexIdMap") Map<String,Integer> indexIdMap
			,@Param("timeDimtypeId") Integer timeDimtypeId
			,@Param("volDimList") List<DDimRange> volDimList
			,@Param("stockType") String stockType
			,@Param("stockCode") String stockCode
	);

	@Delete("delete from G_DETAIL_${stockType}${stockCode} where DATE_ID=#{dateId}")
	void deleteByDateId(@Param("stockCode") String stockCode, @Param("stockType") String stockType,@Param("dateId") Integer dateId);
}


