package com.st.framework.utils.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.st.framework.business.impl.GDetailFileErrorManager;
import com.st.framework.business.impl.GDetailIndexManager;
import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.GDetailNoDataManager;
import com.st.framework.business.impl.GIpoManager;
import com.st.framework.business.impl.GLHBReportManager;
import com.st.framework.business.impl.GLHBSecuritiesManager;
import com.st.framework.business.impl.GLHBTop5Manager;
import com.st.framework.business.impl.GStockDayManager;
import com.st.framework.business.impl.PStockMapManager;
import com.st.framework.business.impl.dim.DDimManager;
import com.st.framework.business.impl.dim.DDimtypeManager;
import com.st.framework.business.impl.dim.DStockIndexManager;
import com.st.framework.business.impl.dim.DStockManager;
import com.st.framework.business.impl.fact.FactActiveDateIdIndexManager;
import com.st.framework.business.impl.fact.FactDownloadFileConfigManager;
import com.st.framework.business.impl.fact.FactProxyManager;
import com.st.framework.business.impl.factdate.FactDateHolidayListManager;
import com.st.framework.business.impl.factdate.FactDateHolidayManager;
import com.st.framework.utils.ConfigUtil;

public class BaseDBUtils {

	protected static ApplicationContext getHelper() {
		return ConfigUtil.getHelper();
	}

	// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\factdata\cyb.js
	// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\factdate\cyb.js
	// (系统找不到指定的路径。)
	// protected static String baseRealPath =
	// "D:/eclipse/eclipse-jee-helios-SR2-x86_64/workspace/stock-analysis/src/main/webapp";

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

	protected static GStockDayManager gStockDayManager = (GStockDayManager) getHelper()
			.getBean("gStockDayManager");

	protected static FactDateHolidayManager factDateHolidayManager = (FactDateHolidayManager) getHelper()
			.getBean("factDateHolidayManager");

	protected static FactDateHolidayListManager factDateHolidayListManager = (FactDateHolidayListManager) getHelper()
			.getBean("factDateHolidayListManager");

	protected static GDetailManager gDetailManager = (GDetailManager) getHelper()
			.getBean("gDetailManager");

	protected static FactDownloadFileConfigManager factDownloadFileConfigManager = (FactDownloadFileConfigManager) getHelper()
			.getBean("factDownloadFileConfigManager");

	protected static GDetailFileErrorManager gDetailFileErrorManager = (GDetailFileErrorManager) getHelper()
			.getBean("gDetailFileErrorManager");

	protected static GDetailNoDataManager gDetailNoDataManager = (GDetailNoDataManager) getHelper()
			.getBean("gDetailNoDataManager");

	protected static FactActiveDateIdIndexManager factActiveDateIdIndexManager = (FactActiveDateIdIndexManager) getHelper()
			.getBean("factActiveDateIdIndexManager");

	protected static GLHBReportManager gLHBReportManager = (GLHBReportManager) getHelper()
			.getBean("gLHBReportManager");

	protected static GLHBSecuritiesManager gLHBSecuritiesManager = (GLHBSecuritiesManager) getHelper()
			.getBean("gLHBSecuritiesManager");

	protected static GLHBTop5Manager gLHBTop5Manager = (GLHBTop5Manager) getHelper()
			.getBean("gLHBTop5Manager");
	
	protected static GIpoManager gIpoManager = (GIpoManager) getHelper()
			.getBean("gIpoManager");
	
	protected static GDetailIndexManager gDetailIndexManager = (GDetailIndexManager) getHelper()
			.getBean("gDetailIndexManager");
	
	protected static DStockIndexManager dStockIndexManager = (DStockIndexManager) getHelper()
			.getBean("dStockIndexManager");

	protected static DateFormat df_simple = new SimpleDateFormat("yyyyMMdd");

	protected final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static void print (List<?> list) {
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				System.out.println(obj);
			}
		}
	}
}
