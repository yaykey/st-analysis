package com.st.analysis.utils.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.st.Global;
import com.st.analysis.utils.DateUtils;
import com.st.framework.module.stock.DDimRange;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.utils.db.BaseDBUtils;

public class TaskAnalysisUtils extends BaseDBUtils {

	@SuppressWarnings("deprecation")
	private final static Date defBeginDate = new Date("2010/01/02");
	
	public void initData() {
		int[][] dateRanges = { { 9, 30, 59 }, { 10, 0, 59 }, { 11, 0, 30 },
				{ 13, 0, 59 }, { 14, 0, 59 }, { 15, 0, 31 } };
		int[][] intervals = { { 1, 3101 }, { 5, 3105 }, { 15, 3115 },
				{ 30, 3130 } };
		int[] idx = { 3001101, 3001102, 3001103 };

		int rptId = 3001001;

		Calendar cal = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("HHmm");
		
		String p = "", cur = "";
		for (int[] interval : intervals) {
			for (int[] dateRange : dateRanges) {
				
				int h = dateRange[0];
				for (int b = dateRange[1]; b <= dateRange[2];) {

					cur = DateUtils.frontCompWithZore(h, 2) + ""
							+ DateUtils.frontCompWithZore(b, 2);
					System.out.print(p + "-" + cur + "\t");
					try {
						Date bd = df.parse(p);
						Date ed = df.parse(cur);
						
						cal.setTime(bd);
						cal.add(Calendar.MINUTE, interval[0]);
						
						if (cal.getTime().compareTo(ed) == 0) {
							DDimRange dim = new DDimRange();
							dim.setDimtypeName("时间区间-" + interval[0] + "分");
							dim.setDimtypeId(interval[1]);
							dim.setDimId(Integer.parseInt(interval[1] + "" + p));
							dim.setDimIdBegin(Integer.parseInt(p));
							dim.setDimIdEnd(Integer.parseInt(cur));
							dim.setDimName(p);
							
							dDimRangeManager.insert(dim);
							
							System.out.println(dim);
						} else {
							System.out.println();
						}
						
					} catch (Exception ex) {
						System.out.println();
					}

					p = cur;
					b += interval[0];
				}
			}
			System.out.println("-----------");
		}
	}
	
	public void taskRangeAnalysis (String stockCode, String stockType) {
		Date begin=null, end=null;
		
		if (begin == null) {
			// begin = new Date("2010/01/02");
			DStock dstock = dStockManager.selectByPrimaryKey(stockCode);
			if (dstock != null) {
				if (dstock.getListingDate() != null) {
					begin = dstock.getListingDate();
					
					if (begin.compareTo(defBeginDate) < 0) {
						begin = defBeginDate;
					}
				} else {
					begin = defBeginDate;
				}
			}
		}

		if (begin == null) {
			return;
		}

		if (end == null) {
			end = new Date();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);

		if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			cal.add(Calendar.DAY_OF_MONTH, 2);
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		begin = cal.getTime();
		
		while (begin.compareTo(end) <= 0) {
			
			cal.setTime(begin);
			cal.add(Calendar.DAY_OF_YEAR, 100);
			
			final Date fbegin = begin;
			final Date fend = cal.getTime();
			
			analysisData(stockCode, stockType, fbegin, fend);
			
			begin = cal.getTime();
		}
	}
	
	private void analysisData(String stockCode, String stockType, Date fbegin,
			Date fend) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fbegin);
		
		while (fbegin.compareTo(fend) <= 0) {
			GDetailExample example = new GDetailExample();
			
			example.setStockCode(stockType.toUpperCase() + stockCode);
			
			example.setOrderByClause("VOL");
			
			GDetailExample.Criteria c = example.createCriteria();
			c.andDateIdEqualTo(Integer.parseInt(Global.DF_SIMPLE().format(fbegin)));
			
			List list = this.gDetailManager.selectByExample(example);
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fbegin = cal.getTime();
		}
		
		
	}
	
	public void insertAnalysisData() {
		String stockType = "SZ";
		String stockCode = "300002";
		//Integer dateId = 20150505;
		
		Date beginDate = new Date("2015/05/04");		
		Date endDate = new Date("2015/05/28");
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(beginDate);
		
		while (beginDate.compareTo(endDate) <= 0) {
			DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
			final Integer dateId = Integer.parseInt(DF_SIMPLE.format(beginDate));
			
			insertAnalysisData(stockType, stockCode, dateId);
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
			
			if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			beginDate = cal.getTime();
		}
		
	}
	
	public void insertAnalysisData (String stockType, String stockCode, Integer dateId) {
		List<DDimRange> volDimList = dDimRangeManager.selectByDimtypeId(3001);
		Integer rptId = 3001001;
//		Integer dateId = 20150525;
		Integer timeDimtypeId = 3101;
		
//		String stockType="SZ";
//		String stockCode="300002";
		
		Map<String, Integer> indexIdMap = new HashMap<String, Integer>(); 
		indexIdMap.put("b", 3001101);
		indexIdMap.put("s", 3001102);
		indexIdMap.put("n", 3001103);
		
		gDetailManager.insertAnalysisData(rptId, dateId, indexIdMap, 
				timeDimtypeId, volDimList, 
				stockType, stockCode);
		
		
	}

	public static void main (String [] args) {
		new TaskAnalysisUtils().insertAnalysisData();
	}
	
}
