package com.st.analysis.utils.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.st.Global;
import com.st.framework.business.impl.dim.DStockManager;
import com.st.framework.utils.LoadConfigUtils;
import com.st.framework.utils.db.BaseDBUtils;

public class TaskCheckFailFileUtils extends BaseDBUtils {

//	private static DStockManager dStockManager = (DStockManager) getHelper()
//			.getBean("dStockManager");

	private static String remoteServiceUrl = "http://market.finance.sina.com.cn/downxls.php";
	
	public static void main(String[] args) {
		
		for (int i=300001; i<=300419; i++) {
			CheckFailFile(i, "sz");
		}
		
	}

	public static void CheckFailFile(Integer stockCode, String stockType) {
		// 路径
		String filePath = LoadConfigUtils.getInstance().getDownloadFilePath();

		File f = new File(filePath + "/" + stockType + stockCode);
		if (!f.exists()) {
			System.out.println(filePath + " not exists");
			return;
		}

		File fs = null;
		File fa[] = f.listFiles();
		long d1 = System.currentTimeMillis();
		for (int i = 0; i < fa.length; i++) {
			fs = fa[i];
			if (fs.isHidden() == false && !"CVS".equals(fs.getName())) {
				if (fs.isDirectory()) {
					System.out.println(fs.getName());

					for (File ffs : fs.listFiles()) {
						

						FileInputStream fis = null;
						try {
							fis = new FileInputStream(ffs);
							long s = fis.available();
							
							if (s == 0) {
								System.out.println(ffs.getName());
								//sz300417_成交明细_2015-02-16.xls
								
								String fileName = ffs.getName();
								String stockTypeCode = fileName.substring(0, 2);
								
							}
							
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
				} else {
					System.out.println(fs.getName());
				}
			}
		}

		f = null;
		fa = null;
		fs = null;

		System.out.println("stockCode[" + stockType + stockCode + "]->\t"
				+ (System.currentTimeMillis() - d1));

	}
}
