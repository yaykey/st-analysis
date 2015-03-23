package com.st.analysis.utils.stock;

import java.util.List;

import com.st.framework.module.stock.DStock;
import com.st.framework.utils.network.BaseDBUtils;

public class FindYahooDataUtils extends BaseDBUtils {

	public static void findYahooDayData () {
		 
//		http://table.finance.yahoo.com/table.csv?s=ibm&d=8&e=5&f=2013&g=d&a=11&b=16&c=1991&ignore=.csv
//		上面的链接可以抓取IBM股票从1991年11月16日起到2013年8月5日的数据。
//
//
//		http://table.finance.yahoo.com/table.csv?s=sohu&d=8&e=5&f=2013&g=d&a=11&b=16&c=2008&ignore=.csv
//		上面的链接可以抓取搜狐股票从2008年11月16日起到2013年8月5的数据。

		
//		http://table.finance.yahoo.com/table.csv?s=300002.sz&d=11&e=18&f=2014&g=d&a=11&b=1&c=2010
		
		
		List<DStock> list = dStockManager.selectByCYB();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		
	}

}
