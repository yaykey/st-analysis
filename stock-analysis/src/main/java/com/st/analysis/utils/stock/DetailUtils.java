package com.st.analysis.utils.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.st.Global;
import com.st.analysis.utils.download.observer.NioDownload;
import com.st.analysis.utils.stock.download.DownloadQQDataUtils;
import com.st.framework.business.impl.GDetailFileErrorManager;
import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.fact.FactActiveDateIdIndexManager;
import com.st.framework.business.impl.fact.FactDownloadFileConfigManager;
import com.st.framework.exceptions.FileParseErrorException;
import com.st.framework.module.stock.FactActiveDateIdIndex;
import com.st.framework.module.stock.FactDownloadFileConfig;
import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.GDetailFileError;
import com.st.framework.module.stock.example.FactDownloadFileConfigExample;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.db.ScriptRunnerUtils;

public class DetailUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DetailUtils.class);

	public static ThreadPoolExecutor threadPoolExecutor;
    public static BlockingQueue<Runnable> AnsyTaskQueue;

    static {
        AnsyTaskQueue = new ArrayBlockingQueue<Runnable>(100);
        // corePoolSize - 池中所保存的线程数，包括空闲线程。
        // maximumPoolSize - 池中允许的最大线程数。
        // keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
        // unit - keepAliveTime 参数的时间单位。
        // workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
        threadPoolExecutor = new ThreadPoolExecutor(1, 1, 3L, TimeUnit.SECONDS, AnsyTaskQueue, new ThreadPoolExecutor.CallerRunsPolicy());
    }
	
	@SuppressWarnings("unchecked")
	public  void DetailFile2DB(final String stockCode,
			final String stockType) {

		// 路径
		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(filePath + "/" + stockType + stockCode);
		if (!f.exists()) {
			System.out.println(filePath + " not exists");
			return;
		}

		// 成功ID
		// final List<String> successTimeIds = factDownloadFileConfigManager
		// .selectStSuccessTimeId(null, null, stockCode);

		// if (successTimeIds.size() > 0) {
		// successTimeIds.remove(successTimeIds.size() - 1);
		// }

		// if (logger.isInfoEnabled()) {
		// logger.info("DetailFile2DB(String) - List<String> successTimeIds="
		// + successTimeIds);
		// }

		gDetailManager.createGDetailTable(stockCode, stockType);

		File fs = null;
		File fa[] = f.listFiles();
		Date d1 = new Date(), d2 = null;
		for (int i = 0; i < fa.length; i++) {
			// for (int i = 0; i < 2; i++) {
			fs = fa[i];
			if (fs.isHidden() == false && !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {
					final File ffs = fs;
				
//					Global.threadPoolExecutor.execute(new Runnable() {
//
//						@Override
//						public void run() {
							Date d3 = new Date();
							String fsname = ffs.getName();
							String year = fsname.substring(fsname.length() - 4);
							List<String> successTimeIds = null;
							successTimeIds = (List<String>) factActiveDateIdIndexManager
									.selectActiveDateId(
											Integer.parseInt(stockCode),
											Integer.parseInt(year));

							List<String> parseFileErrorDateIds = gDetailFileErrorManager
									.selectErrorDateIds(stockCode,
											Integer.parseInt(year));

							if (successTimeIds != null) {
								if (parseFileErrorDateIds != null) {
									successTimeIds
											.addAll(parseFileErrorDateIds);
								}
							} else {
								if (parseFileErrorDateIds != null) {
									successTimeIds = parseFileErrorDateIds;
								}
							}

							DetailDirectoryFile2DB(ffs, successTimeIds);
							Date d4 = new Date();
							System.out.println(stockType + stockCode + "->"
									+ ffs.getName() + "[目录]->"
									+ (d4.getTime() - d3.getTime()));
							successTimeIds = null;
//						}
//					});


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
		System.out.println("stockCode[" + stockType + stockCode + "]->\t"
				+ (d2.getTime() - d1.getTime()));
		d2 = null;
		// System.gc();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}

	public  void DetailDirectoryFile2DB(final File directoryFile,
			final List<String> successTimeIds) {
		if (!directoryFile.exists()) {
			System.out.println(" not exists");
			return;
		}

		File[] fa = directoryFile.listFiles();

		if (fa == null || fa.length == 0) {
			directoryFile.delete();
			return;
		}

		File fs = null;

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

//						DetailXLX2DB(fs, successTimeIds);
						String fileName = fs.getName();
						String datetimeStr = fileName.substring(14, 24);
						Integer dateId = Integer.parseInt(datetimeStr
								.replaceAll("-", ""));
						
						if (successTimeIds != null && successTimeIds.size() > 0
								&& successTimeIds.contains(dateId)) {
							// System.out.println(fileName + "已存在");
							continue;
						} else {
							final File ffs = fs;
							threadPoolExecutor.execute(new Runnable() {
								@Override
								public void run() {
									DetailXLX2DBScript(ffs);
								}
							});
						}
						
						
						

						fs = null;

					}
				}
			}
		}

	}

	public  boolean isDetailXLXFile(String fileName) {

		Pattern pat = Pattern
				.compile("^[\\w]{2}[\\d]{6}\\_.*[\\d]{4}\\-[\\d]{2}\\-[\\d]{2}\\.xls$");
		Matcher mat = pat.matcher(fileName);

		return mat.find();
	}
	
	static String baseScriptPath = LoadConfigUtils.getInstance().getScriptFilePath();
	
	@SuppressWarnings("resource")
	public  void DetailXLX2DBScript(File xlsFile) {
		
				// /sz300001_成交明细_2010-01-07.xls
				// new StringBuilder();
				String fileName = xlsFile.getName();
				String year = fileName.substring(14, 18);
				String datetimeStr = fileName.substring(14, 24);
				Integer dateId = Integer.parseInt(datetimeStr
						.replaceAll("-", ""));

//				if (successTimeIds != null && successTimeIds.size() > 0
//						&& successTimeIds.contains(dateId)) {
//					// System.out.println(fileName + "已存在");
//					return;
//				}


				String stockCode = fileName.substring(2, 8).toUpperCase();
				String stockType = fileName.substring(0, 2).toUpperCase();

				// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\downloadFile\sz300001\2010\sz300001_成交明细_2010-01-07.xls
				String filePath = xlsFile.getAbsolutePath();
				filePath = filePath.substring(filePath.length() - 43,
						filePath.length() - 29);

				FactDownloadFileConfig factDownloadFileConfig = new FactDownloadFileConfig();
				factDownloadFileConfig.setStCode(stockCode.substring(2));

				factDownloadFileConfig.setFileName(fileName);

				factDownloadFileConfig.setFilePath(filePath.replace('\\', '/'));
				factDownloadFileConfig.setTimeId(fileName.substring(14, 24));

				FileInputStream fis = null;
				InputStreamReader isr = null;
				BufferedReader bw = null;

//				GDetail gDetail = null;

				String line = null;
				String[] temp = null;
//				List<GDetail> data = new ArrayList<GDetail>();

				try {
					fis = new FileInputStream(xlsFile);
					isr = new InputStreamReader(fis, "GBK");

					bw = new BufferedReader(isr);

					line = bw.readLine();

					if (line == null) {
						line = "";
					} else {
						line = line.trim();
					}

					if ("".equals(line) || "暂无数据".equals(line)
							|| line.indexOf("session quota") > 0) {
						try {
							if (bw != null) {
								bw.close();
							}
						} catch (IOException e) {
							logger.error(
									"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
									e);

							// e.printStackTrace();
						}
						try {
							if (isr != null) {
								isr.close();
							}
						} catch (IOException e) {
							logger.error(
									"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
									e);

						}

						try {
							if (fis != null) {
								fis.close();
							}
						} catch (IOException e) {
							logger.error(
									"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
									e);

						}

						xlsFile.delete();

						if (line.indexOf("session quota") > 0) {
							
							try {
								DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
								NioDownload nioDownload = new DownloadQQDataUtils()
										.createNioDownload(stockCode.substring(2),
												stockCode.substring(0, 2),
												DF_SIMPLE.parse("" + dateId));
								System.out.println(nioDownload);
								nioDownload.start();

							} catch (ParseException e) {
								e.printStackTrace();
							}
						}

					}//无数据;
					
					
					String newFileDirectoryPath = baseScriptPath + "/" + stockType + stockCode + 
							"/" + year;
					
					File newFileDirectory = new File(newFileDirectoryPath);
					if (newFileDirectory.exists() == false) {
						newFileDirectory.mkdirs();
					}
					
					String newFilePath =newFileDirectoryPath + "/" + stockType + stockCode + "-" + datetimeStr + ".sql";
					RandomAccessFile randomAccessFile = null;
					File newFile = null;
					try {
						newFile = new File(newFilePath);
						
						randomAccessFile = new RandomAccessFile(newFile, "rw");
						randomAccessFile.seek(0);
						StringBuffer buffer = new StringBuffer();
						while ((line = bw.readLine()) != null) {
							//buffer.setLength(0);
							
							
							line = line.trim();

							if ("".equals(line)) {
								continue;
							}

							if (line.length() > 50) {// 一行数据长度,判读是否存在异常数据;
								
								temp = null;
								throw new FileParseErrorException("文件格式错误->" + fileName
										+ "->" + line);
							}
							
							temp = line.split("" + (char) 9);

							if (temp == null || temp.length != 6
									|| "".equals(temp[1].trim())
									|| "".equals(temp[2].trim())) {
								
								temp = null;
								throw new FileParseErrorException("文件格式错误->" + fileName
										+ "->" + line);
							}
//							gDetail = new GDetail();
						
//							gDetail.setDateId(dateId);
//							gDetail.setTimeId(temp[0]);
//							gDetail.setPrice(Double.valueOf(temp[1]));
//insert into `G_DETAIL_SZ000001` (`DATE_ID`, `TIME_ID`, `PRICE`, `PRICE_CHANGES`, `VOL`, `AMO`, `NATURE`) values('20100104','09:25:07','24.52','24.52','408','1000416','b');
							
							buffer.append(
									"insert ignore into `G_DETAIL_"+ stockType.toUpperCase() + stockCode + 
									"` (`DATE_ID`, `TIME_ID`, `PRICE`, `PRICE_CHANGES`, `VOL`, `AMO`, `NATURE`) values(");
							
//							randomAccessFile.writeBytes(dateId + ",");
//							randomAccessFile.writeBytes(temp[0] + ",");
//							randomAccessFile.writeBytes(temp[1] + ",");
							buffer.append(dateId + ",");
							buffer.append("'" + temp[0] + "',");
							buffer.append(temp[1] + ",");
							
							if ("--".equals(temp[2])) {
								// newSqlBuffer.append("," + 0);
//								gDetail.setPriceChanges(0d);
								buffer.append(0 + ",");
							} else {
//								gDetail.setPriceChanges(Double.parseDouble(temp[2]));
								// newSqlBuffer.append("," + temp[2]);
								buffer.append(temp[2] + ",");
							}

//							gDetail.setVol(Integer.parseInt(temp[3]));
//							gDetail.setAmo(Integer.parseInt(temp[4]));
							
							buffer.append(temp[3] + ",");
							buffer.append(temp[4] + ",");

							temp[5] = temp[5].trim();
							//buffer.append(temp[5] + ",");
							if ("买盘".equals(temp[5])) {
//								gDetail.setNature("b");
								// newSqlBuffer.append(",'b'");
								buffer.append("'b'");
							} else if ("卖盘".equals(temp[5])) {
//								gDetail.setNature("s");
								// newSqlBuffer.append(",'s'");
								buffer.append("'s'");
							} else if ("中性盘".equals(temp[5])) {
//								gDetail.setNature("-");
								// newSqlBuffer.append(",'-'");
								buffer.append("'-'");
							} else {// 乱码纠错

								throw new FileParseErrorException("文件错误->" + fileName
										+ "->" + line);
								
							}
							
							buffer.append(
									");\r");
							
							
							
							
							//randomAccessFile.close();
							
//							data.add(gDetail);
						}// 结束循环;
						
						randomAccessFile.writeBytes(buffer.toString());
						//文件关闭;
						randomAccessFile.close();
						
						gDetailManager.deleteByDateId(stockCode, stockType, dateId);
						
						ScriptRunnerUtils scriptRunner = new ScriptRunnerUtils();
//						scriptRunner.Runner(newFilePath, "GBK");
						scriptRunner.Runner(newFile, "GBK");
						scriptRunner = null;

						
						
						FactActiveDateIdIndex idx = new FactActiveDateIdIndex();
						idx.setDateId(dateId);
						idx.setDateYearId(Integer.parseInt(year));
						idx.setStockCode(Integer.parseInt(stockCode));
						factActiveDateIdIndexManager.insertOrUpdate(idx);
						
						
//						gDetailManager.insertBatch(stockCode,stockType, data);
//						data.clear();
//						data = null;
						// gDetailManager.insertBatchAsynchronous(stockCode, data);
						// data.clear();
						// data = null;

						factDownloadFileConfig.setFail(false);
						factDownloadFileConfigManager
								.insertOrUpdate(factDownloadFileConfig);
						
					} catch (FileParseErrorException e) {

						if (randomAccessFile != null) {
							randomAccessFile.close();
						}
						
						if (newFile != null) {
							newFile.delete();
						}
						
						
						
						GDetailFileError gDetailFileError = new GDetailFileError();

						gDetailFileError.setDateId(dateId);
						gDetailFileError.setStockCode(stockCode);

						gDetailFileErrorManager.increaseBalance(gDetailFileError);

						logger.error(fileName + "->文件错误" + e.getMessage());

						// 删除数据;
						// gDetailManager.deleteErrorData(stockCode, dateId);
						factDownloadFileConfig.setFail(true);

						factDownloadFileConfigManager
								.insertOrUpdate(factDownloadFileConfig);
					} catch (Exception e) {

						if (randomAccessFile != null) {
							randomAccessFile.close();
						}
						
						if (newFile != null) {
							newFile.delete();
						}
						
						logger.error(fileName + "->文件错误", e);

						// 删除数据;
						// gDetailManager.deleteErrorData(stockCode, dateId);
						factDownloadFileConfig.setFail(true);

						factDownloadFileConfigManager
								.insertOrUpdate(factDownloadFileConfig);
					} finally {
						if (randomAccessFile != null) {
							randomAccessFile.close();
						}
						
						if (newFile != null) {
							newFile.delete();
						}
					}

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
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								ex);

					}
					try {
						if (isr != null) {
							isr.close();
						}
					} catch (IOException ex) {
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								ex);

					}

					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException ex) {
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								ex);

					}
				
					throw new RuntimeException(e);
				} finally {
					try {
						if (bw != null) {
							bw.close();
						}
					} catch (IOException e) {
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								e);

					}
					try {
						if (isr != null) {
							isr.close();
						}
					} catch (IOException e) {
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								e);
					}

					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						logger.error(
								"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
								e);

					}

				}

	}

	public  boolean DetailXLX2DB(File xlsFile,
			List<String> successTimeIds) {
		// if (logger.isDebugEnabled()) {
		// logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - start");
		// }

		// Date d1 = new Date();
		// /sz300001_成交明细_2010-01-07.xls
		// new StringBuilder();
		String fileName = xlsFile.getName();
		Integer dateId = Integer.parseInt(fileName.substring(14, 24)
				.replaceAll("-", ""));

		if (successTimeIds != null && successTimeIds.size() > 0
				&& successTimeIds.contains(dateId)) {
			// System.out.println(fileName + "已存在");

			// if (logger.isDebugEnabled()) {
			// logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - end");
			// }
			return false;
		}

		boolean res = false;

		String stockCode = fileName.substring(2, 8).toUpperCase();
		String stockType = fileName.substring(0, 2).toUpperCase();

		// D:\eclipse\eclipse-jee-helios-SR2-x86_64\workspace\stock-analysis\src\main\webapp\downloadFile\sz300001\2010\sz300001_成交明细_2010-01-07.xls
		String filePath = xlsFile.getAbsolutePath();
		filePath = filePath.substring(filePath.length() - 43,
				filePath.length() - 29);

		FactDownloadFileConfig factDownloadFileConfig = new FactDownloadFileConfig();
		factDownloadFileConfig.setStCode(stockCode.substring(2));

		factDownloadFileConfig.setFileName(fileName);

		factDownloadFileConfig.setFilePath(filePath.replace('\\', '/'));
		factDownloadFileConfig.setTimeId(fileName.substring(14, 24));

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bw = null;

		GDetail gDetail = null;

		String line = null;
		String[] temp = null;
		List<GDetail> data = new ArrayList<GDetail>();

		try {
			fis = new FileInputStream(xlsFile);
			isr = new InputStreamReader(fis, "GBK");

			bw = new BufferedReader(isr);

			// 因为不知道有几行数据，所以先存入list集合中

			line = bw.readLine();

			if (line == null) {
				line = "";
			} else {
				line = line.trim();
			}

			if ("".equals(line) || "暂无数据".equals(line)
					|| line.indexOf("session quota") > 0) {
				try {
					if (bw != null) {
						bw.close();
					}
				} catch (IOException e) {
					logger.error(
							"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
							e);

					// e.printStackTrace();
				}
				try {
					if (isr != null) {
						isr.close();
					}
				} catch (IOException e) {
					logger.error(
							"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
							e);

					// e.printStackTrace();
				}

				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					logger.error(
							"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
							e);

					// e.printStackTrace();
				}

				xlsFile.delete();

				if (line.indexOf("session quota") > 0) {
					// new
					// DownloadQQDataUtils().download(Integer.parseInt(stockCode.substring(2)),
					// stockCode.substring(0, 2));
					try {
						DateFormat DF_SIMPLE = new SimpleDateFormat("yyyyMMdd");
						NioDownload nioDownload = new DownloadQQDataUtils()
								.createNioDownload(stockCode.substring(2),
										stockCode.substring(0, 2),
										DF_SIMPLE.parse("" + dateId));
						System.out.println(nioDownload);
						nioDownload.start();

						return false;
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

			}

			try {
				int i = 0;
				while ((line = bw.readLine()) != null) {

					line = line.trim();

					if ("".equals(line)) {
						continue;
					}

					if (line.length() > 50) {// 一行数据长度,判读是否存在异常数据;
						// newSqlBuffer.setLength(0);
						// newSqlBuffer = null;
						temp = null;
						throw new FileParseErrorException("文件格式错误->" + fileName
								+ "->" + line);
					}
					// if (i != 0) {
					// newSqlBuffer.append(",");
					// }
					i++;

					// line = line.replaceAll("[\\t]*", " ");
					temp = line.split("" + (char) 9);

					if (temp == null || temp.length != 6
							|| "".equals(temp[1].trim())
							|| "".equals(temp[2].trim())) {
						// newSqlBuffer.setLength(0);
						// newSqlBuffer = null;
						temp = null;
						throw new FileParseErrorException("文件格式错误->" + fileName
								+ "->" + line);
					}
					gDetail = new GDetail();
					// newSqlBuffer.append("(");
					// newSqlBuffer.append(dateId);
					// newSqlBuffer.append(",'" + temp[0] + "'");
					// newSqlBuffer.append("," + temp[1]);
					gDetail.setDateId(dateId);
					gDetail.setTimeId(temp[0]);
					gDetail.setPrice(Double.valueOf(temp[1]));

					if ("--".equals(temp[2])) {
						// newSqlBuffer.append("," + 0);
						gDetail.setPriceChanges(0d);
					} else {
						gDetail.setPriceChanges(Double.parseDouble(temp[2]));
						// newSqlBuffer.append("," + temp[2]);
					}

					// newSqlBuffer.append("," + temp[3]);
					// newSqlBuffer.append("," + temp[4]);

					gDetail.setVol(Integer.parseInt(temp[3]));
					gDetail.setAmo(Integer.parseInt(temp[4]));

					temp[5] = temp[5].trim();
					if ("买盘".equals(temp[5])) {
						gDetail.setNature("b");
						// newSqlBuffer.append(",'b'");
					} else if ("卖盘".equals(temp[5])) {
						gDetail.setNature("s");
						// newSqlBuffer.append(",'s'");
					} else if ("中性盘".equals(temp[5])) {
						gDetail.setNature("-");
						// newSqlBuffer.append(",'-'");
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
						throw new FileParseErrorException("文件错误->" + fileName
								+ "->" + line);
						// }

					}
					// newSqlBuffer.append(")");

					// gDetailManager.insertBatch(stockCode, gDetail);
					data.add(gDetail);
				}
				// newSqlBuffer.append(";\r\n");
				// gDetailManager.insertFlushBatch(stockCode);

				// gDetailManager.insertStringBatch(sqlBuffer.toString());
				// sqlBuffer = null;
				// sqlBuffer.append(newSqlBuffer);
				// newSqlBuffer.setLength(0);
				// newSqlBuffer = null;

				gDetailManager.insertBatch(stockCode,stockType, data);
				data.clear();
				data = null;
				// gDetailManager.insertBatchAsynchronous(stockCode, data);
				// data.clear();
				// data = null;

				factDownloadFileConfig.setFail(false);
				factDownloadFileConfigManager
						.insertOrUpdate(factDownloadFileConfig);
				res = true;

			} catch (FileParseErrorException e) {

				GDetailFileError gDetailFileError = new GDetailFileError();

				gDetailFileError.setDateId(dateId);
				gDetailFileError.setStockCode(stockCode);

				gDetailFileErrorManager.increaseBalance(gDetailFileError);

				res = false;
				logger.error(fileName + "->文件错误" + e.getMessage());

				// 删除数据;
				// gDetailManager.deleteErrorData(stockCode, dateId);
				factDownloadFileConfig.setFail(true);

				factDownloadFileConfigManager
						.insertOrUpdate(factDownloadFileConfig);
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
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						ex);

				// e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException ex) {
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						ex);

				// e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						ex);

				// e.printStackTrace();
			}
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						e);

				// e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						e);

				// e.printStackTrace();
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.error(
						"DetailXLX2DB(File, List<String>, StringBuffer) - exception ignored",
						e);

				// e.printStackTrace();
			}

		}

		// Date d2 = new Date();
		// System.out.println(fileName + "\t" + (d2.getTime() - d1.getTime()));

		// if (logger.isDebugEnabled()) {
		// logger.debug("DetailXLX2DB(File, List<String>, StringBuffer) - end");
		// }
		return res;
	}

	public  void CheckFailDB() {
		// factDownloadFileConfigManager.selectPageByExample(example, 1000);

		FactDownloadFileConfigExample example = new FactDownloadFileConfigExample();

		example.setOrderByClause("ST_CODE TIME_ID");

		FactDownloadFileConfigExample.Criteria c = example.createCriteria();
		c.andFailEqualTo(true);

		// c.andStCodeLessThan("sz3000010");

		List<FactDownloadFileConfig> list = factDownloadFileConfigManager
				.selectByExample(example);

		logger.info(list.size());

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

		// for (int i = 300291; i <= 300295; i++) {
		// DetailFile2DB("sz" + i);
		// }
		// System.gc();
		// for (int i = 300001; i <= 300406; i++) {
		// DetailFile2DB("sz" + i);
		// }

		// DetailFile2DB("sz300001");
//		for (int i = 300002; i <= 300447; i++) {
//
//			try {
//				new DetailUtils().DetailFile2DB("" + i, "SZ");
//				System.gc();
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		// DetailUtils.DetailFile2DB("300448", "SZ");
		long d = System.currentTimeMillis();
//		new DetailUtils().DetailFile2DB("000024", "SZ");
//		new DetailUtils().DetailFile2DB("000025", "SZ");
//		new DetailUtils().DetailFile2DB("000026", "SZ");
//		new DetailUtils().DetailFile2DB("000027", "SZ");
//		new DetailUtils().DetailFile2DB("000028", "SZ");
//		new DetailUtils().DetailFile2DB("000029", "SZ");
//		new DetailUtils().DetailFile2DB("000030", "SZ");
//		new DetailUtils().DetailFile2DB("000035", "SZ");
		System.out.println("耗时:" + (System.currentTimeMillis() - d));
	}

	public static int maxFileNum = 200;
}
