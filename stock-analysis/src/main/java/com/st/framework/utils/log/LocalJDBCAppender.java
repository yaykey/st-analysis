package com.st.framework.utils.log;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.jdbc.JDBCAppender;

/**
 * The Class LocalJDBCAppender.
 */
public class LocalJDBCAppender extends JDBCAppender {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.log4j.jdbc.JDBCAppender#closeConnection(java.sql.Connection)
	 */
	@Override
	protected void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.log4j.jdbc.JDBCAppender#getConnection()
	 */
	@Override
	protected Connection getConnection() {

		try {
			if(dataSource == null){
				Context ctx = new InitialContext();
				dataSource = (DataSource) ctx.lookup("java:comp/env/report");
			}			
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private  DataSource dataSource = null;

}
