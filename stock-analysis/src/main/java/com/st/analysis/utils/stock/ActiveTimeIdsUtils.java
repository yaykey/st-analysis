package com.st.analysis.utils.stock;

import java.util.ArrayList;
import java.util.List;

import com.st.framework.business.impl.GDetailManager;
import com.st.framework.business.impl.fact.FactActiveDateIdIndexManager;
import com.st.framework.module.stock.FactActiveDateIdIndex;

public class ActiveTimeIdsUtils extends DetailUtils {

	@SuppressWarnings("unchecked")
	public static void findActimeIds2DB() {

		final String type = "sz";
		for (int i = 300001; i <= 300449; i++) {
//		for (int i = 300001; i <= 300100; i++) {
//			for (int i = 300101; i <= 300200; i++) {
//			for (int i = 300201 ; i <= 300300; i++) {
//				for (int i = 300301; i <= 300350; i++) {
//				for (int i = 300351; i <= 300400; i++) {
//				for (int i = 300401; i <= 300449; i++) {
			final int fi = i;
			// new Thread() {
			// public void run () {
			long d1 = System.currentTimeMillis();
			List<Integer> list = null;
			// GDetailManager gDetailManager =
			// (GDetailManager)getHelper().getBean("gDetailManager");
			// FactActiveDateIdIndexManager factActiveDateIdIndexManager =
			// (FactActiveDateIdIndexManager)getHelper().getBean("factActiveDateIdIndexManager");

			
			list = (List<Integer>) gDetailManager.selectDetailActiveDateId(
					"" + fi, type, 20100101, null);
			

			if (list != null && list.size() > 0) {
				List<FactActiveDateIdIndex> idxs = new ArrayList<FactActiveDateIdIndex>();

				for (Integer dateId : list) {
					FactActiveDateIdIndex index = new FactActiveDateIdIndex();
					index.setStockCode(fi);
					index.setDateId(dateId);

					idxs.add(index);
				}

				factActiveDateIdIndexManager.insertBatch(idxs);
			}

			System.out
					.println(fi + "\t耗时:" + (System.currentTimeMillis() - d1));
			// }
			// }.start();

		}

	}

	public static void main(String[] args) {
		findActimeIds2DB();
	}
}
