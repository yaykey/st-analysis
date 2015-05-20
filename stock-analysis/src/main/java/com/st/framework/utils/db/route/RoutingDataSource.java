package com.st.framework.utils.db.route;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 返回数据源路由
 * 
 * @author yangzhenyu
 *
 * @date 下午12:21:40
 * 
 * @since 2015年1月21日
 *
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		return DbContextHolder.getDbType();
	}

}
