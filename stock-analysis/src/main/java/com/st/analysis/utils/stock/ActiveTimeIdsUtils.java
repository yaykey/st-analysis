package com.st.analysis.utils.stock;

import java.util.List;

public class ActiveTimeIdsUtils extends DetailUtils {

	public static void findActimeIds2DB () {
		List<Integer> list = null;
		for (int i=300001; i<300499; i++) {
			list = (List<Integer>)gDetailManager.selectDetailActiveDateId("" + i, 20100101, null);
			
			if (list != null) {
				
			}
		}
		
	}
}
