package com.st.analysis.utils.compress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.st.framework.utils.LoadConfigUtils;

public class CompressUtil {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CompressUtil.class);

	/**
	 * 
	 * 
	 * @param stock
	 *            sz300002 ...
	 * @param year
	 *            2010,2011 ...
	 */
	public static void Compress(String stockCode, String stockType, Integer year) {
		if (logger.isDebugEnabled()) {
			logger.debug("Compress(String, String, Integer) - start");
		}

		// 路径
		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(filePath + "/" + stockType + stockCode + "/" + year);
		if (!f.exists()) {
			System.out.println(filePath + " not exists");

			if (logger.isDebugEnabled()) {
				logger.debug("Compress(String, String, Integer) - end");
			}
			return;
		}

		File fs = null;
		File fa[] = f.listFiles();

		for (int i = 0; i < fa.length; i++) {
			fs = fa[i];
			if (f.isHidden() == false && !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {

					System.out.println(stockType + stockCode + "->"
							+ fs.getName() + "[目录]->");

					fs = null;

				} else {
					System.out.println(fs.getName());
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Compress(String, String, Integer) - end");
		}
		
		zip(f, stockCode + ".zip");
	}

	public static void zip(File inputFile, String zipFileName) {
		if (logger.isDebugEnabled()) {
			logger.debug("zip(File, String) - start");
		}

		long timeId = System.currentTimeMillis();
		
		try {
			// 创建文件输出对象out,提示:注意中文支持
			FileOutputStream out = new FileOutputStream(new String(
					zipFileName.getBytes("UTF-8")));
			// 將文件輸出ZIP输出流接起来
			ZipOutputStream zOut = new ZipOutputStream(out);
			logger.info("压缩-->开始");

			zip(zOut, inputFile, "");

			logger.info("压缩-->结束" + (System.currentTimeMillis()-timeId));
			zOut.close();

		} catch (Exception e) {
			logger.error("zip(File, String)", e);

			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("zip(File, String) - end");
		}
	}

	public static void zip(ZipOutputStream zOut, File file, String base) {
		if (logger.isDebugEnabled()) {
			logger.debug("zip(ZipOutputStream, File, String) - start");
		}

		try {

			// 如果文件句柄是目录
			if (file.isDirectory()) {
				// 获取目录下的文件
				File[] listFiles = file.listFiles();
				// 建立ZIP条目
				zOut.putNextEntry(new ZipEntry(base + "/"));
				logger.info("目录名:"+file.getName()+"|加入ZIP条目:"+base+"/");

				base = (base.length() == 0 ? "" : base + "/");

				// 遍历目录下文件
				for (int i = 0; i < listFiles.length; i++) {
					// 递归进入本方法
					zip(zOut, listFiles[i], base + listFiles[i].getName());
				}
			}
			// 如果文件句柄是文件
			else {
				if (base == "") {
					base = file.getName();
				}
				// 填入文件句柄
				zOut.putNextEntry(new ZipEntry(base));
//				logger.info("文件名:"+file.getName()+"|加入ZIP条目:"+base);

				// 开始压缩
				// 从文件入流读,写入ZIP 出流
				writeFile(zOut, file);
			}

		} catch (Exception e) {
			logger.error("zip(ZipOutputStream, File, String)", e);

			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("zip(ZipOutputStream, File, String) - end");
		}
	}

	public static void writeFile(ZipOutputStream zOut, File file)
			throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("writeFile(ZipOutputStream, File) - start");
		}

		//logger.info("开始压缩" + file.getName());
		FileInputStream in = new FileInputStream(file);
		int len;
		while ((len = in.read()) != -1)
			zOut.write(len);
		//logger.info("压缩结束" + file.getName());
		in.close();

		if (logger.isDebugEnabled()) {
			logger.debug("writeFile(ZipOutputStream, File) - end");
		}
	}

	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start");
		}

		Compress("300002", "sz", 2010);

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end");
		}
	}
}
