package com.st.analysis.utils.stock;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.st.framework.module.stock.DStock;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.network.HttpStackManager;

public class FindStockPinyinUtils extends BaseDBUtils {
//http://suggest3.sinajs.cn/suggest/type=&key=300011&name=suggestdata_1430377934261
	
	public static void main (String [] args) {
//		String res = findStockNameCode("3000");
//		System.out.println(res);
//		res = res.replaceFirst("^var suggestvalue=\"", "").replaceFirst("\";$", "");
//		System.out.println(res);
		
		for (int i=300011; i<300419; i++) {
			parseNameCode(findStockNameCode("" + i));
		}
		
	}
	
	public static void parseNameCode(String res) {
		
		if (res != null) {
			res = res.replaceFirst("^var suggestvalue=\"", "").replaceFirst("\";$", "");
			String [] stArr = res.split(";");
			String [] stTemp = null;
			for (String stRes : stArr) {
				stTemp = stRes.split(",");
				String stCode = stTemp[0];
				String nameCode = stTemp[4];
				String pinyinCode = stTemp[5];
				
				try {
					DStock dStock =dStockManager.selectByPrimaryKey(stCode);
//					dStock.setNameCode(nameCode);
					dStock.setPinyinCode(pinyinCode);
					
					dStockManager.updateByPrimaryKeySelective(dStock);
				} catch (Exception ex) {
					System.out.println(stRes);
					
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static String findStockNameCode (String key) {
		HttpClient httpClient = HttpStackManager.getInstance().getHttpclient();
		
//		client.execute(request)
		String url = "http://suggest3.sinajs.cn/suggest/type=&key=";
		url += key;
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response=null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			String res = EntityUtils.toString(entity, "utf-8");
			
			EntityUtils.consume(entity);
			
			return res;			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
