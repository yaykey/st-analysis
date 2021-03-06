package com.st.analysis.utils.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.fact.FactDownloadFileConfigManager;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.db.BaseDBUtils;

public class Detail2Utils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Detail2Utils.class);

	private static GDetailManager gDetailManager = (GDetailManager) getHelper()
			.getBean("gDetailManager");

	protected static FactDownloadFileConfigManager factDownloadFileConfigManager = (FactDownloadFileConfigManager) getHelper()
			.getBean("factDownloadFileConfigManager");

	public static void DetailFile2DB(String stockCode) {

		// 路径
		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(filePath + "/" + stockCode);
		if (!f.exists()) {
			System.out.println(filePath + " not exists");
			return;
		}

		// 成功ID
		List<String> successTimeIds = factDownloadFileConfigManager
				.selectStSuccessTimeId(null, null, stockCode);

		// if (successTimeIds.size() > 0) {
		// successTimeIds.remove(successTimeIds.size() - 1);
		// }

//		if (logger.isInfoEnabled()) {
//			logger.info("DetailFile2DB(String) - List<String> successTimeIds="
//					+ successTimeIds);
//		}
		File fs = null;
		File fa[] = f.listFiles();
		Date d1 = new Date(), d2=null, d3,d4;
		for (int i = 0; i < fa.length; i++) {
			// for (int i = 0; i < 2; i++) {
			fs = fa[i];
			if (f.isHidden() == false && !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {
					d3 = new Date();
					DetailDirectoryFile2DB(fs, successTimeIds);
					d4 = new Date();
					System.out.println(stockCode + "->" + fs.getName() + "[目录]->" + (d4.getTime()-d3.getTime()));
					
					d3 = null; 
					d4=null;
					fs = null;

					System.gc();
				} else {
					System.out.println(fs.getName());
				}
			}
		}

		f = null;
		fa = null;
		fs = null;
		d2 = new Date();
		System.out.println("stockCode["+stockCode+"]->\t" + (d2.getTime()-d1.getTime()));
		d2 = null;
		System.gc();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public static void DetailDirectoryFile2DB(File directoryFile,
			List<String> successTimeIds) {
		if (!directoryFile.exists()) {
			System.out.println(" not exists");
			return;
		}

		//StringBuffer sqlBuffer = new StringBuffer();
//		StringBuilder sqlBuffer = new StringBuilder();
		
//		int fileNum = 0;

		File fa[] = directoryFile.listFiles();
//		String stockCode = "";
		File fs = null;
//		for (int i = 0; i < fa.length; i++) {
//			fs = fa[i];
//			if (directoryFile.isHidden() == false
//					&& !"CVS".equals(fs.getName())) {
//				if (fs.isDirectory()) {
//					System.out.println(fs.getName() + " [目录]");
//				} else {
//					stockCode = fs.getName().substring(0, 8).toUpperCase();
//					break;
//				}
//			}
//		}

//		sqlBuffer.append("insert ignore into G_DETAIL_" + stockCode);
//		sqlBuffer
//				.append(" (DATE_ID, TIME_ID, PRICE, PRICE_CHANGES, VOL, AMO, NATURE) values ");
		
//		Date d1 = null, d2 = null;
//		String sqlString = null;
		for (int i = 0; i < fa.length; i++) {
			// for (int i = 0; i < 2; i++) {

			fs = fa[i];
			if (directoryFile.isHidden() == false
					&& !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {
					System.out.println(fs.getName() + " [目录]");
				} else {
					// System.out.println(fs.getName());

					if (isDetailXLXFile(fs.getName())) {

						if (DetailXLX2DB(fs, successTimeIds) == true) {
							
							
							
							//if (fileNum != 0) {
//								sqlBuffer.append(",");								
							//}
//							logger.error("fileNum=" + fileNum);
//							logger.error(sqlBuffer.toString());
//							fileNum++;
						}

						fs = null;

////						if (fileNum >= 10) {
//							
//							// if (sqlBuffer.length() > 0) {
//							//d1 = new Date();
////							sqlString = sqlBuffer.toString().replaceFirst("values ,\\(", "values \\(").replaceAll(",,", ",").replaceAll(",$", "");
//							gDetailManager.insertStringBatch(sqlString);
//							
////							sqlString = null;
////							sqlBuffer.setLength(0);
//							
//							//d2 = new Date();
//
////							System.out.println(stockCode + "->[" + fileNum + "]->"
////									+ (d2.getTime() - d1.getTime()));
//							fileNum = 0;
//							//sqlBuffer = null;
//							//System.gc();
//							//sqlBuffer = new StringBuilder();
//							sqlBuffer.append("insert ignore into G_DETAIL_"
//									+ stockCode);
//							sqlBuffer
//									.append(" (DATE_ID, TIME_ID, PRICE, PRICE_CHANGES, VOL, AMO, NATURE) values ");
//
//							// }
//						}
					}
				}
			}
		}

//		if (fileNum > 0) {
//			//d1 = new Date();
//			sqlString = sqlBuffer.toString().replaceFirst("values ,\\(", "values \\(").replaceAll(",,", ",").replaceAll(",$", "");
//			gDetailManager.insertStringBatch(sqlString);
//			sqlString = null;
//			//d2 = new Date();
////			System.out.println(stockCode + "->结束->[" + fileNum + "]->"
////					+ (d2.getTime() - d1.getTime()));
//
//			sqlBuffer.setLength(0);
//			
//			
//		}
//		
//		sqlBuffer = null;
//		stockCode = null;
		

		// factDownloadFileConfigManager.flushInsertBatch();
	}

	public static boolean isDetailXLXFile(String fileName) {

		Pattern pat = Pattern
				.compile("^[\\w]{2}[\\d]{6}\\_.*[\\d]{4}\\-[\\d]{2}\\-[\\d]{2}\\.xls$");
		Matcher mat = pat.matcher(fileName);

		return mat.find();
	}

	public static boolean DetailXLX2DB(File xlsFile,
			List<String> successTimeIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - start");
		}

//		Date d1 = new Date();
		// /sz300001_成交明细_2010-01-07.xls
		//new StringBuilder();
		String fileName = xlsFile.getName();
		Integer dateId = Integer.parseInt(fileName.substring(14, 24)
				.replaceAll("-", ""));

		if (successTimeIds != null
				&& successTimeIds.contains(fileName.substring(14, 24))) {
			//System.out.println(fileName + "已存在");

			if (logger.isDebugEnabled()) {
				logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - end");
			}
			return false;
		}

		boolean res = false;

		String stockCode = fileName.substring(2, 8).toUpperCase();
		// String stockCode=fileName.substring(2, 8);
		String stockType = fileName.substring(0, 2).toUpperCase();
		// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\downloadFile\sz300001\2010\sz300001_成交明细_2010-01-07.xls
		String filePath = xlsFile.getAbsolutePath();
		filePath = filePath.substring(filePath.length() - 43,
				filePath.length() - 29);

		gDetailManager.createGDetailTable(stockCode, stockType);

		// gDetailManager.insertFlushBatch(stockCode);

		// GDetailExample example = new GDetailExample();
		// example.setStockCode(stockCode);
		// GDetailExample.Criteria c = example.createCriteria();
		// c.andDateIdEqualTo(dateId);
		//
		// gDetailManager.deleteByExample(example);

		// gDetailManager.deleteErrorData(stockCode, dateId);

		// System.out.println(":" + stockCode + ":" + dateId);

		FactDownloadFileConfig factDownloadFileConfig = new FactDownloadFileConfig();
		factDownloadFileConfig.setStCode(stockCode.toLowerCase());

		// factDownloadFileConfig.setFileName(new String(fileName.getBytes(),
		// "GBK"));
		factDownloadFileConfig.setFileName(fileName);

		factDownloadFileConfig.setFilePath(filePath.replace('\\', '/'));
		factDownloadFileConfig.setTimeId(fileName.substring(14, 24));

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bw = null;

		// GDetail gDetail = null;

		String line = null;
		String[] temp = null;

//		StringBuilder newSqlBuffer = new StringBuilder();
		// newSqlBuffer.append("insert ignore into G_DETAIL_" + stockCode);
		// newSqlBuffer.append(" (DATE_ID, TIME_ID, PRICE, PRICE_CHANGES, VOL, AMO, NATURE) values ");
		try {
			fis = new FileInputStream(xlsFile);
			isr = new InputStreamReader(fis, "GBK");

			bw = new BufferedReader(isr);

			// 因为不知道有几行数据，所以先存入list集合中
			line = bw.readLine();

			try {
//				int i = 0;
				GDetail gDetail = null;
				while ((line = bw.readLine()) != null) {

					line = line.trim();
										
					if ("".equals(line)) {
						continue;
					}
					
					if (line.length() > 40) {
//						newSqlBuffer.setLength(0);
//						newSqlBuffer = null;
						temp = null;
						throw new RuntimeException("文件格式错误->" + fileName + "->"
								+ line);
					}
//					if (i != 0) {
//						newSqlBuffer.append(",");
//					}
//					i++;

					// line = line.replaceAll("[\\t]*", " ");
					temp = line.split("" + (char) 9);

					if (temp == null || temp.length != 6
							|| "".equals(temp[1].trim())
							|| "".equals(temp[2].trim())
						) {
//						newSqlBuffer.setLength(0);
//						newSqlBuffer = null;
						temp = null;
						throw new RuntimeException("文件格式错误->" + fileName + "->"
								+ line);
					}
					
//					newSqlBuffer.append("(");
//					newSqlBuffer.append(dateId);
//					newSqlBuffer.append(",'" + temp[0] + "'");					
//					newSqlBuffer.append("," + temp[1]);
					
					gDetail = new GDetail();
					gDetail.setDateId(dateId);
					gDetail.setTimeId(temp[0]);
					gDetail.setPrice(Double.parseDouble(temp[1]));
					
					
					if ("--".equals(temp[2])) {
//						newSqlBuffer.append("," + 0);
						gDetail.setPriceChanges(0d);
					} else {
						gDetail.setPriceChanges(Double.parseDouble(temp[2]));
//						newSqlBuffer.append("," + temp[2]);
					}

//					newSqlBuffer.append("," + temp[3]);
//					newSqlBuffer.append("," + temp[4]);
					
					gDetail.setVol(Integer.parseInt(temp[3]));
					gDetail.setAmo(Integer.parseInt(temp[4]));

					temp[5] = temp[5].trim();
					if ("买盘".equals(temp[5])) {
						 gDetail.setNature("b");
//						newSqlBuffer.append(",'b'");
					} else if ("卖盘".equals(temp[5])) {
						 gDetail.setNature("s");
//						newSqlBuffer.append(",'s'");
					} else if ("中性盘".equals(temp[5])) {
						 gDetail.setNature("-");
//						newSqlBuffer.append(",'-'");
					} else {// 乱码纠错

						// if (temp[0]!=null && temp[0].indexOf("09:25:") > -1)
						// {
						// if (temp[5].length() > 0) {
						// byte [] nbyte = temp[5].getBytes();
						//
						// char c = (char)nbyte[0];
						//
						// if (nbyte[0] == -17) {
						// gDetail.setNature("b");
						// }
						// }
						// } else {
						res = false;
						throw new RuntimeException("文件错误->" + fileName + "->"
								+ line);
						// }

					}
//					newSqlBuffer.append(")");

					 gDetailManager.insertBatch(stockCode, stockType, gDetail);
					 
					 gDetail = null;
				}
				// newSqlBuffer.append(";\r\n");
				 gDetailManager.insertFlushBatch(stockCode, stockType);

				// gDetailManager.insertStringBatch(sqlBuffer.toString());
				// sqlBuffer = null;
//				sqlBuffer.append(newSqlBuffer);
//				newSqlBuffer.setLength(0);
//				newSqlBuffer = null;
				factDownloadFileConfig.setFail(false);
				factDownloadFileConfigManager
						.insertOrUpdate(factDownloadFileConfig);
				res = true;
			} catch (Exception e) {

				// System.out.println(line);
				res = false;
				logger.error(fileName + "->文件错误", e);

				// 删除数据;
				// gDetailManager.deleteErrorData(stockCode, dateId);
				factDownloadFileConfig.setFail(true);

				factDownloadFileConfigManager
						.insertOrUpdate(factDownloadFileConfig);
			}

			// gDetail = null;
			factDownloadFileConfig = null;
			line = null;
			temp = null;
			stockCode = null;

		} catch (IOException e) {
			logger.error("DetailXLX2DB(File, List<String>, StringBuffer)", e);

			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ex) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", ex);

				//e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException ex) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", ex);

				//e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", ex);

				//e.printStackTrace();
			}
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", e);

				//e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", e);

				//e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.error("DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored", e);

				//e.printStackTrace();
			}

		}

//		Date d2 = new Date();
//		System.out.println(fileName + "\t" + (d2.getTime() - d1.getTime()));

		if (logger.isDebugEnabled()) {
			logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - end");
		}
		return res;
	}

	public static void CheckFailDB() {
		// factDownloadFileConfigManager.selectPageByExample(example, 1000);

		FactDownloadFileConfigExample example = new FactDownloadFileConfigExample();

		example.setOrderByClause("ST_CODE TIME_ID");

		FactDownloadFileConfigExample.Criteria c = example.createCriteria();
		c.andFailEqualTo(true);

		//c.andStCodeLessThan("sz3000010");

		List<FactDownloadFileConfig> list = factDownloadFileConfigManager
				.selectByExample(example);

		logger.info(list.size());

	}

	public static void testSession() {

	}

	public static void main(String[] args) {

		// // 路径
		// String filePath =
		// LoadConfigUtils.getInstance().getDownloadFilePath();
		//
		// DetailDirectoryFile2DB(new File(filePath + "/sz300001/2014"));

		//
		// DetailFile2DB("sz300003");
		// DetailFile2DB("sz300004");
		// DetailFile2DB("sz300005");

		for (int i = 300100; i <= 300406; i++) {
			DetailFile2DB("sz" + i);
		}
//		System.gc();
//		for (int i = 300300; i <= 300305; i++) {
//			DetailFile2DB("sz" + i);
//		}

//		DetailFile2DB("sz300001");
	}
}
