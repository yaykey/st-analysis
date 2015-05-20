package com.st.framework.utils.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.st.Global;
import com.st.framework.utils.ConfigUtil;

public class JDBCUtil {
	
	private static final Log logger = LogFactory.getLog(JDBCUtil.class);

	static {
		if (Global._ctx == null) {
			Global._ctx = ConfigUtil.getHelper();
		}
	}
	
//	// 数据源对象
	private static DataSource dataSource = (DataSource) Global._ctx
			.getBean("routingDS");
	
//	private SqlSession sqlSession = (SqlSession)Global._ctx.getBean("sqlSession");
	
	private Connection con = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	/**
	 * 从数据源中获得数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		
		//con = sqlSession.getConnection();
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 释放连接
	 * 
	 * @param con
	 */
	public void realeaseConnection() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null && !con.isClosed()) {

				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 执行查询语句的方法
	public ResultSet executeQuery(String sql) {
		Connection con = getConnection();
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);// 执行指定的数据查询语句
			logger.info(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rs;
	}
}
