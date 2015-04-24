package com.st.analysis.utils.stock;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.dao.DuplicateKeyException;

import com.st.framework.module.stock.DDim;
import com.st.framework.module.stock.DDimtype;
import com.st.framework.module.stock.DStock;
import com.st.framework.module.stock.PStockMapKey;
import com.st.framework.utils.db.BaseDBUtils;

public class FindStockTypeUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(FindStockTypeUtils.class);

	public static void parseStockTypeFile(String filePath, Integer[] dimIds) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

			BufferedReader bw = new BufferedReader(isr);
			String line = null;
			// 因为不知道有几行数据，所以先存入list集合中
			line = bw.readLine();

			while ((line = bw.readLine()) != null) {

				buffer.append(line);

			}

			bw.close();

			// logger.info(buffer.toString());

			JSONArray jsonarray = JSONArray.fromObject(buffer.toString());

			System.out.println(jsonarray.toString());

			DStock stock = null;

			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject obj = (JSONObject) jsonarray.get(i);

				System.out.println(obj.toString());

				String code = obj.getString("code");
				stock = dStockManager.selectByPrimaryKey(code);

				if (stock == null) {
					stock = new DStock();

					String stockType = obj.getString("symbol").substring(0, 2);

					String name = obj.getString("name");

					stock.setStockCode(code);
					stock.setStockName(name);
					stock.setStockTypeCode(stockType);

					System.out.println(stockType + ":" + code + ":" + name);

					dStockManager.insert(stock);
				}

				for (Integer dimId : dimIds) {
					PStockMapKey pStockMapper = new PStockMapKey();

					pStockMapper.setStockCode(code);
					pStockMapper.setDimId(dimId);

					try {
						pStockMapManager.insert(pStockMapper);
					} catch (DuplicateKeyException ex) {
						logger.warn(ex.getMessage());
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Integer[] dimIds = { 1004003, 1004005 };
		parseStockTypeFile("cyb.js", dimIds);
	}
}
