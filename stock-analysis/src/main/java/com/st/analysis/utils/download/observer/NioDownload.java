package com.st.analysis.utils.download.observer;

import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.st.framework.business.impl.GDetailSuspensionManager;
import com.st.framework.exceptions.DataNotGeneratedException;
import com.st.framework.module.stock.GDetailSuspension;
import com.st.framework.module.stock.GDetailSuspensionKey;
import com.st.framework.utils.ConfigUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioDownload {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NioDownload.class);

	protected String url, savePath; // 下载地址与保存路径

	protected String filename;

	protected String stockCode;

	protected String stockType;

	protected Integer dateId;

	protected boolean translateFlag = false;

	private int timeOutCount;

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	protected static GDetailSuspensionManager gDetailSuspensionManager = (GDetailSuspensionManager) ConfigUtil
			.getHelper().getBean("gDetailSuspensionManager");

	public NioDownload(String url, String savePath, String filename) {
		this.url = url;
		this.savePath = savePath;
		this.filename = filename;
	}

	public void start() {
		if (logger.isDebugEnabled()) {
			logger.debug("start() - start");
		}

		// 设置连接超时时间
		// Integer CONNECTION_TIMEOUT = 2 * 1000; // 设置请求超时2秒钟 根据业务调整
		// Integer SO_TIMEOUT = 2 * 1000; // 设置等待数据超时时间2秒钟 根据业务调整

		CloseableHttpClient client = HttpClientBuilder.create().build();

		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		RandomAccessFile randomAccessFile = null;

		try {

			// 发送基本的GET请求
			// 配置请求的超时设置

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(5000).setConnectTimeout(5000)
					.setSocketTimeout(5000).build();

			HttpGet request;
			// try {
			request = new HttpGet(new URI(this.url));

			request.setConfig(requestConfig);

			try {
				response = client.execute(request);
			} catch (SocketTimeoutException e1) {// Read timed out
				logger.warn("尝试一次->" + this.url);
				try {
					response = client.execute(request);
				} catch (SocketTimeoutException e2) {// Read timed out
					logger.warn("尝试二次失败");
					new RuntimeException("尝试二次失败", e2);
				}
			}

			// 获取HTTP响应的状态码
			int statusCode = response.getStatusLine().getStatusCode();
			// if (logger.isInfoEnabled()) {
			// logger.info("start() - int statusCode=" + statusCode);
			// }

			if (statusCode >= 200 && statusCode <= 400) {

				// // 获取响应的媒体类型
				// String contentMimeType = ContentType.getOrDefault(
				// response.getEntity()).getMimeType();
				// if (logger.isInfoEnabled()) {
				// logger.info("start() - String contentMimeType="
				// + contentMimeType);
				// }

				// 获取响应的BODY部分

				entity = response.getEntity();

				// ContentType contentType = ContentType.getOrDefault(entity);
				// Charset charset = contentType.getCharset();
				// if (logger.isInfoEnabled()) {
				// logger.info("start() - Charset charset=" + charset);
				// }

				// 获得名字;
				String filename = null;
				if (this.filename == null) {
					filename = getRemoteFilename(response);
				} else {
					filename = this.filename;
				}

				if (filename == null) {

					if (this.stockCode != null && this.dateId != null) {
						GDetailSuspensionKey gDetailSuspensionKey = new GDetailSuspensionKey();

						gDetailSuspensionKey.setStockCode(stockCode);
						gDetailSuspensionKey.setDateId(dateId);

						gDetailSuspensionManager
								.increaseBalance(gDetailSuspensionKey);
					}
					logger.info("数据未生成" + this.url);
					throw new DataNotGeneratedException("数据未生成->" + this.url);
				}

				this.filename = filename;

				logger.info(this.filename);

				// String bodyAsString = EntityUtils.toString(entity, "GBK");
				// if (logger.isInfoEnabled()) {
				// logger.info("start() - String bodyAsString="
				// + bodyAsString.substring(0, 100));
				// }

				File newPathFile = new File(savePath);

				if (!newPathFile.exists()) {
					newPathFile.mkdirs();
				}

				File newFile = new File(savePath + "/" + this.filename);

				// logger.info(savePath + "/" + this.filename);

				if (!newFile.exists()) {
					newFile.createNewFile();
				}

				randomAccessFile = new RandomAccessFile(newFile, "rw");
				randomAccessFile.seek(0);

				// randomAccessFile.write(EntityUtils.toString(entity,
				// "GBK").getBytes("gbk"));
				
				randomAccessFile.write(EntityUtils.toByteArray(entity));
				
				
				
				if (response != null) {
					try {
						// 会自动释放连接
						EntityUtils.consume(response.getEntity());
					} catch (IOException e) {
						logger.error("start()", e);

						e.printStackTrace();
					}
				}
				
				if (randomAccessFile.length() < 150) {
					if (response != null) {
						try {
							// 会自动释放连接
							EntityUtils.consume(response.getEntity());
						} catch (IOException e) {
							logger.error("start()", e);

							e.printStackTrace();
						}
					}

					if (randomAccessFile != null) {
						try {
							randomAccessFile.close();

							randomAccessFile = null;
						} catch (IOException e) {
							logger.warn("start() - exception ignored", e);

						}
					}
					newFile.delete();
					logger.warn("数据不存在" + this.url);
					throw new DataNotGeneratedException("数据未生成->" + this.url);
				}
				
////				InputStream input = new FileInputStream("D:\\接口.xls");
//				
//				InputStream input = new FileInputStream(newFile);
//								
//				POIFSFileSystem fs = new POIFSFileSystem(input);
//				HSSFWorkbook wb = new HSSFWorkbook(fs);
//				HSSFSheet sheet = wb.getSheetAt(0);
//				// Iterate over each row in the sheet
//				Iterator<Row> rows = sheet.rowIterator();
//
//				StringBuffer buffer = new StringBuffer();
//				
//				while (rows.hasNext()) {
//					HSSFRow row = (HSSFRow) rows.next();
////					System.out.println("Row #" + row.getRowNum());
//					// Iterate over each cell in the row and print out the
//					// cell"s
//					// content
//					Iterator<Cell> cells = row.cellIterator();
//					
//					while (cells.hasNext()) {
//						HSSFCell cell = (HSSFCell) cells.next();
////						System.out.println("Cell #" + cell.getCellNum());
////						System.out.println("Cell #" + cell.getCellNum());
//						
////						System.out.print(cell.getStringCellValue());
//						
//						switch (cell.getCellType()) {
//						case HSSFCell.CELL_TYPE_NUMERIC:
////							System.out.print(cell.getNumericCellValue());
////							System.out.print(cell.getStringCellValue());
//							System.out.print(cell.getCellFormula());
//							buffer.append(cell.getCellFormula());
//							break;
//						case HSSFCell.CELL_TYPE_STRING:
//							System.out.print(cell.getStringCellValue());
//							buffer.append(cell.getCellFormula());
//							break;
//						case HSSFCell.CELL_TYPE_BOOLEAN:
//							System.out.print(cell.getBooleanCellValue());
//							buffer.append(cell.getCellFormula());
//							break;
//						case HSSFCell.CELL_TYPE_FORMULA:
//							System.out.print(cell.getCellFormula());
//							buffer.append(cell.getCellFormula());
//							break;
//						default:
//							System.out.print("unsuported sell type");
//							break;
//						}
//						System.out.print(" ");
//						buffer.append(" ");
//					}
//					
//					System.out.println();
//				}

			}
			// 处理响应, 处理异常
		} catch (URISyntaxException e) {
			logger.error("start() - exception ignored", e);

		} catch (ClientProtocolException e) {
			logger.error("start() - exception ignored", e);
		} catch (DataNotGeneratedException e) {
			logger.warn("start() - exception ignored", e);

		} catch (SocketTimeoutException e) {
			logger.warn("start() - exception ignored", e);
			this.timeOutCount++;
			if (this.timeOutCount <= 3) {
				this.start();
			}
		} catch (IOException e) {
			logger.error("start() - exception ignored", e);

		} finally {
			if (response != null) {
				try {
					// 会自动释放连接
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					logger.error("start()", e);

					e.printStackTrace();
				}
			}

			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();

					randomAccessFile = null;
				} catch (IOException e) {
					logger.warn("start() - exception ignored", e);

				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("start() - end");
		}
	}

	private String getRemoteFilename(CloseableHttpResponse response) {
		String filename = null;
		Header[] headers = response.getHeaders("Content-Disposition");

		if (headers != null && headers.length > 0) {

			// logger.info(new String(header.toString().getBytes("iso8859-1"),
			// "GBK"));
			filename = headers[0].getValue();

			filename = filename.trim().replaceAll("^attachment; filename=\"",
					"");

			filename = filename.replaceAll("\"$", "");

			try {
				filename = new String(filename.getBytes("iso8859-1"), "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

		return filename;
	}

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean isTranslateFlag() {
		return translateFlag;
	}

	public void setTranslateFlag(boolean translateFlag) {
		this.translateFlag = translateFlag;
	}
}
