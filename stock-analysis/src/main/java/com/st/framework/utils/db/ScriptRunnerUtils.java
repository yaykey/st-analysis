package com.st.framework.utils.db;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ScriptRunnerUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ScriptRunnerUtils.class);

	public void Runner(InputStreamReader isr) {
		JDBCUtil jdbcutil = new JDBCUtil();
		Connection conn = null;
		ScriptRunner runner = null;

		conn = jdbcutil.getConnection();
		runner = new ScriptRunner(conn);
		// runner.runScript(new InputStreamReader(new FileInputStream(
		// "src/test123.sql"), "UTF-8"));

		try {
			conn.setAutoCommit(false);//手动提交
			runner.setLogWriter(null);//不打印日志;
			
			//runner.setErrorLogWriter();
			runner.setSendFullScript(false);
			runner.runScript(isr);
		} catch (MySQLIntegrityConstraintViolationException e) {
			logger.warn("Runner(InputStreamReader)", e);

			
		} catch (SQLException e1) {
			logger.error("Runner(InputStreamReader)", e1);

			try {
				conn.rollback(); //将事务回滚
			} catch (SQLException e) {
				logger.error("Runner(InputStreamReader)", e);

			} 
			
		} finally {
			if (isr != null) {
				try {
					isr.close();
					isr = null;
				} catch (IOException e) {
					logger.warn("Runner(InputStreamReader) - exception ignored", e);
					
				}
			}
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("Runner(InputStreamReader)", e);

		}
		
		runner.closeConnection();

	}
	
	public void Runner(File file, String charsetName) {
		if (file == null) {
			return;
		}
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream,
					charsetName);
			Runner(inputStreamReader);
		} catch (UnsupportedEncodingException e) {
			logger.warn("Runner(String, String) - exception ignored", e);

		} catch (FileNotFoundException e) {
			logger.warn("Runner(String, String) - exception ignored", e);

		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
					inputStreamReader = null;
				} catch (IOException e) {
					logger.warn("Runner(String, String) - exception ignored", e);
					
				}
			}
			
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
					fileInputStream = null;
				} catch (IOException e) {
					logger.warn("Runner(String, String) - exception ignored", e);
					
				}
			}
		}
	}

	public void Runner(String relativePath, String charsetName) {
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(relativePath);
			inputStreamReader = new InputStreamReader(fileInputStream,
					charsetName);
			Runner(inputStreamReader);
		} catch (UnsupportedEncodingException e) {
			logger.warn("Runner(String, String) - exception ignored", e);

		} catch (FileNotFoundException e) {
			logger.warn("Runner(String, String) - exception ignored", e);

		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
					inputStreamReader = null;
				} catch (IOException e) {
					logger.warn("Runner(String, String) - exception ignored", e);
					
				}
			}
			
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
					fileInputStream = null;
				} catch (IOException e) {
					logger.warn("Runner(String, String) - exception ignored", e);
					
				}
			}
		}
	}
	
	public static void main (String [] args) {
		String sqlPath = "D:/eclipse/git/st-analysis/stock-analysis/src/script/tt.sql";
//		String sqlPath = "src/script/tt.sql";
		new ScriptRunnerUtils().Runner(sqlPath, "utf-8");
	}

}
