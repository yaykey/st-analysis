package com.htmlparser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.framework.utils.network.TelnetUtil;

public class ParserTest {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ParserTest.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//http://cn-proxy.com/
		
		String htmlUrl = "http://cn-proxy.com/";
		
		Document doc = Jsoup.connect(htmlUrl).get();
//		
//		File file = new File("proxy-2014-11-11.html");		
//		RandomAccessFile raf = new RandomAccessFile(file, "rw");		
//		raf.write(doc.toString().getBytes());
		
//		File file = new File("proxy-2014-11-11.html");
//				
//		Document doc = Jsoup.parse(file, "UTF-8", htmlUrl);

		
		doc.getElementsByTag("head").remove();
		doc.getElementsByTag("script").remove();
		doc.getElementById("header").remove();
		doc.getElementById("footer").remove();
		
		Element el = doc.getElementsByClass("sortable").get(0);
		
//		logger.info(el);
		
		Elements trs = el.getElementsByTag("tr");
		Element tr = null;
		Date date1 = null;
		Date date2 = null;
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		String mapVal = null;
		for (int i=1; i<trs.size(); i++) {
			tr = trs.get(i);
			
//			System.out.println(tr.child(0).text() + ":" + tr.child(1).text());
//			System.out.println(tr);
			
			try {
				date1 = new Date();
				TelnetUtil telnetTest = new TelnetUtil(tr.child(0).text(), Integer.parseInt(tr.child(1).text()), 100);
				date2 = new Date();
				telnetTest.disconnect();
				System.out.println(tr.text() + " " + (date2.getTime()-date1.getTime()));
				mapVal = tr.child(0).text() + ",";
				mapVal += tr.child(1).text() + ",";
				mapVal += tr.child(2).text() + ",";
				mapVal += tr.child(4).text();
				
				map.put(mapVal, "" + (date2.getTime()-date1.getTime()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		map = sortMapByValue(map);  
		for(Map.Entry<String, String> entry : map.entrySet()){   
		     System.out.println(entry.getKey()+"--->"+entry.getValue());   
		}   
		
		//el.text()
		
//		Strings els = doc.getAllElements();
//		
//		
//		Element el = null;
//		for (int i=0; i<els.size(); i++) {
//			el = els.get(i);
//			
//			System.out.println(el.tagName() + "\r\n" + el.toString());
//			
//			
//		}
//		
//		doc.getElementsMatchingText("\\<\\!\\-\\-.*\\-\\-\\>").remove();
//		
//		
//		if (logger.isInfoEnabled()) {
//			logger.info("main(String[]) - Document doc=" + doc);
//		}
		
		

	}

	/** 
     * 使用 Map按value进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, String> sortMapByValue(Map<String, String> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(map.entrySet());  
        Collections.sort(entryList, new MapValueComparator());  
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
        Map.Entry<String, String> tmpEntry = null;  
        while (iter.hasNext()) {  
            tmpEntry = iter.next();  
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
        }  
        return sortedMap;  
    }
    
  
}

//比较器类  
class MapValueComparator implements Comparator<Map.Entry<String, String>> {  
	@Override
	public int compare(Entry<String, String> me1, Entry<String, String> me2) {  
        return me1.getValue().compareTo(me2.getValue());  
    }
} 

