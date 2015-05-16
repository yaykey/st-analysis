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

import com.st.analysis.utils.DateUtils;
import com.st.framework.utils.LoadConfigUtils;

public class CompressUtil {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CompressUtil.class);

	public static String downloadFilePath = LoadConfigUtils.getInstance().getDownloadFilePath();
	
	public static String zipFilePath = downloadFilePath + "/zip";
	
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
//		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(downloadFilePath + "/" + stockType + stockCode + "/" + year);
		if (!f.exists()) {
			System.out.println(downloadFilePath + "/" + stockType + stockCode + "/" + year + " not exists");

			if (logger.isDebugEnabled()) {
				logger.debug("Compress(String, String, Integer) - end");
			}
			return;
		}

		File fs = null;
		File fa[] = f.listFiles();

//		for (int i = 0; i < fa.length; i++) {
//			fs = fa[i];
//			if (f.isHidden() == false && !"CVS".equals(fs.getName())) {
//				if (fs.isDirectory()) {
//
////					System.out.println(stockType + stockCode + "->"
////							+ fs.getName() + "[目录]->");
//
//					fs = null;
//
//				} else {
////					System.out.println(fs.getName());
//				}
//			}
//		}

		if (logger.isDebugEnabled()) {
			logger.debug("Compress(String, String, Integer) - end");
		}
		
		zipData(f, stockType, stockCode,"" + year);
	}

	public static void zipData(File inputFile, String stockType, String stockCode, String year) {
		if (logger.isDebugEnabled()) {
			logger.debug("zip(File, String) - start");
		}
		
		long timeId = System.currentTimeMillis();
		
		String zipFileName = stockType + stockCode + "-" + year +".zip";
		String outFileDirectory = zipFilePath + "/" + stockType + stockCode;
		
		File zipFile = new File(outFileDirectory + "/"+ zipFileName);
		if (zipFile.exists() == true) {
			return;
		}
		
		File fileDirectory = new File(outFileDirectory);
		if (fileDirectory.exists() == false) {
			fileDirectory.mkdirs();
		}
		
		try {
			// 创建文件输出对象out,提示:注意中文支持
			FileOutputStream out = new FileOutputStream(
					outFileDirectory + "/" + 
					new String(
					zipFileName.getBytes("UTF-8")));
			// 將文件輸出ZIP输出流接起来
			ZipOutputStream zOut = new ZipOutputStream(out);
//			logger.info(stockCode + "压缩-->开始");

			zip(zOut, inputFile, year);

			logger.info(stockCode + "压缩-->结束:" + (System.currentTimeMillis()-timeId));
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
		
		for (int i=600; i<=650; i++) {
//		for (int i=300001; i<=300449; i++) {
			for (int y=2009; y<=2014; y++) {
				Compress(DateUtils.frontCompWithZore(i, 6), "sz", y);
			}
		}
		
//		try {
//			Runtime.getRuntime().exec("cmd /c Shutdown -t 10");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		//Compress("300002", "sz", 2009);

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end");
		}
	}
}
