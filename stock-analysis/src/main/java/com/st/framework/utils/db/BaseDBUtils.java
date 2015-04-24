package com.st.framework.utils.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;

import com.st.framework.business.impl.DStockManager;
import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.business.impl.PStockMapManager;
import com.st.framework.business.impl.dim.DDimManager;
import com.st.framework.business.impl.dim.DDimtypeManager;
import com.st.framework.business.impl.fact.FactProxyManager;
import com.st.framework.business.impl.factdate.FactDateHolidayManager;
import com.st.framework.utils.ConfigUtil;

public class BaseDBUtils {

	protected static ApplicationContext getHelper() {
		return ConfigUtil.getHelper();
	}
	//D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\factdata\cyb.js
	//D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\factdate\cyb.js (系统找不到指定的路径。)
	//protected static String baseRealPath = "D:/eclipse/eclipse-jee-helios-SR2-x86_64/workspace/stock-analysis/src/main/webapp";

	protected static FactProxyManager factProxyManager = (FactProxyManager) getHelper()
			.getBean("factProxyManager");

	protected static void destroyFactory() {
		ConfigUtil.destroyFactory();
	}

	protected static DDimtypeManager dDimtypeManager = (DDimtypeManager) getHelper()
			.getBean("dDimtypeManager");

	protected static DDimManager dDimManager = (DDimManager) getHelper()
			.getBean("dDimManager");

	protected static DStockManager dStockManager = (DStockManager) getHelper()
			.getBean("dStockManager");

	protected static PStockMapManager pStockMapManager = (PStockMapManager) getHelper()
			.getBean("pStockMapManager");
	
	protected static GDetailManager gDetailManager = (GDetailManager) getHelper()
	.getBean("gDetailManager");
	
	protected static GStockDayManager gStockDayManager = (GStockDayManager) getHelper()
	.getBean("gStockDayManager");
	
	protected static FactDateHolidayManager factDateHolidayManager = (FactDateHolidayManager) getHelper()
	.getBean("factDateHolidayManager");
	
	
	
	protected final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

}
