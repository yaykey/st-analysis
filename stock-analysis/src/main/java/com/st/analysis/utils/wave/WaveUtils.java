package com.st.analysis.utils.wave;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import com.st.framework.module.stock.GDetail;
import com.st.framework.module.stock.example.GDetailExample;
import com.st.framework.utils.db.BaseDBUtils;

public class WaveUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(WaveUtils.class);

	// public static List<GDetail> getWave (List<GDetail> wave) {
	// // java 求一组数组的波峰与波谷
	// // 比如一组数【4,5,1,1,4,3,3,3,3,5,5,6,4,4,3,2】
	// // 求他的波形图的波峰与波谷；
	// // 结果应该是5,1,4,3,6 各
	// // 用数组，如果array[i+1]是波峰或波谷的话，array[i+1]-array[i] 与array[i+2]-array[i+1]异号
	// // 波峰 波谷
	// // Peaks and troughs
	//
	// // byte[] wave = new byte[] { 4, 5, 1, 1, 4, 3, 3, 5, 5, 6, 4, 4, 3,
	// // 2 };
	//
	// //结果应该是5,1,4,3,6 各
	// //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	// //4 5 1 1 4 3 3 3 3 5 5 6 4 4 3 2
	// // 5 4 6
	// // 1 3
	//
	// List<GDetail> list = new ArrayList<GDetail>();
	//
	// double f=wave.get(1).getPrice() - wave.get(0).getPrice();
	//
	// // String buffer = "";
	// // double p = 0;
	// for (int i=1; i<wave.size()-1; i++) {
	// // p = (wave.get(i+1).getPrice() - wave.get(i).getPrice());
	// if (f > 0) { //+
	// //if (wave[i+1] - wave[i] > 0) { //+
	// if (wave.get(i+1).getPrice() - wave.get(i).getPrice() > 0) { //+
	// // if (p > 0) { //+
	// continue;
	// // } else if (wave[i+1] - wave[i] < 0) { //-
	// } else if (wave.get(i+1).getPrice() - wave.get(i).getPrice() < 0) { //-
	// // } else if (p < 0) { //-
	// //buffer = buffer + i + ":" + wave[i] + "+  "; //+++++
	// list.add(wave.get(i));
	// //f = wave[i+1] - wave[i];//-
	// f = wave.get(i+1).getPrice() - wave.get(i).getPrice();//-
	// // f = p;//-
	// //} else if (wave[i+1] - wave[i] == 0) { //=
	// } else if (wave.get(i+1).getPrice() - wave.get(i).getPrice() == 0) { //=
	// for (int j=i; j<wave.size()-1; j++) {
	// // if (wave[j+1] - wave[j] > 0) { //+
	// if (wave.get(j+1).getPrice() - wave.get(j).getPrice() > 0) { //+
	// // if (p > 0) { //+
	// i=j;
	// break;
	// // } else if (wave[j+1] - wave[j] < 0) { //-
	// } else if (wave.get(j+1).getPrice() - wave.get(j).getPrice() < 0) { //-
	// // } else if (p < 0) { //-
	// //buffer = buffer + i + ":" + wave[i] + "+=  ";
	// // f = wave[j+1] - wave[j];
	// list.add(wave.get(i));
	// f = wave.get(j+1).getPrice() - wave.get(j).getPrice();
	// i=j;
	// break;
	// // } else if (wave[j+1] - wave[j] == 0) { //=
	// } else if ((wave.get(j+1).getPrice() - wave.get(j).getPrice()) == 0) {
	// //=
	// continue;
	// }
	// }
	// }
	// } else if (f < 0) { //-
	// // if (wave[i+1] - wave[i] > 0) { //+
	// if (wave.get(i+1).getPrice() - wave.get(i).getPrice() > 0) { //+
	// // buffer = buffer + i + ":" + wave[i] + "-  "; //+++++
	// list.add(wave.get(i)); //+++++
	// // f = wave[i+1] - wave[i];//+
	// f = wave.get(i+1).getPrice() - wave.get(i).getPrice();//+
	// // } else if (wave[i+1] - wave[i] < 0) { //-
	// } else if (wave.get(i+1).getPrice() - wave.get(i).getPrice() < 0) { //-
	// continue;
	// // } else if (wave[i+1] - wave[i] == 0) { //=
	// } else if ((wave.get(i+1).getPrice() - wave.get(i).getPrice()) == 0) {
	// //=
	// // for (int j=i; j<wave.length-1; j++) {
	// for (int j=i; j<wave.size()-1; j++) {
	// // if (wave[j+1] - wave[j] > 0) { //+
	// // buffer = buffer + i + ":" + wave[i] + "-=  ";
	// // f = wave[j+1] - wave[j];
	// if (wave.get(j+1).getPrice() - wave.get(j).getPrice() > 0) { //+
	// list.add(wave.get(i));
	// f = wave.get(j+1).getPrice() - wave.get(j).getPrice();
	// i=j;
	// break;
	// // } else if (wave[j+1] - wave[j] < 0) { //-
	// } else if (wave.get(j+1).getPrice() - wave.get(j).getPrice() < 0) { //-
	// i=j;
	// break;
	// // } else if (wave[j+1] - wave[j] == 0) { //=
	// } else if ((wave.get(j+1).getPrice() - wave.get(j).getPrice()) == 0) {
	// //=
	// continue;
	// }
	// }
	// }
	// } else if (f == 0) { //=
	// // if (wave[i+1] - wave[i] > 0) { //+
	// // f = wave[i+1] - wave[i];//+
	// // } else if (wave[i+1] - wave[i] < 0) { //-
	// // f = wave[i+1] - wave[i];//-
	// // } else if (wave[i+1] - wave[i] == 0) { //=
	// // continue;
	// // }
	// if (wave.get(i+1).getPrice() - wave.get(i).getPrice() > 0) { //+
	// f = wave.get(i+1).getPrice() - wave.get(i).getPrice();//+
	// } else if (wave.get(i+1).getPrice() - wave.get(i).getPrice() < 0) { //-
	// f = wave.get(i+1).getPrice() - wave.get(i).getPrice();//-
	// } else if ((wave.get(i+1).getPrice() - wave.get(i).getPrice()) == 0) {
	// //=
	// continue;
	// }
	// }
	// }
	//
	// //System.out.println();
	// //System.out.println(buffer);
	//
	// return list;
	// }

	public static List<GDetail> getWaveOptimize(List<GDetail> wave) {
		return getWaveOptimize(wave, 0.001);
	}

	public static void checkwave(int start, int pageSize) {
		String stockCode = "300002";

		GDetailExample example = new GDetailExample();

		example.setDistinct(true);
		example.setStockCode("sz" + stockCode);
		example.setOrderByClause("TIME_ID");

		example.setStart(start);
		example.setPageSize(pageSize);

		GDetailExample.Criteria c = example.createCriteria();
		c.andDateIdEqualTo(20141224);
		c.andTimeIdGreaterThanOrEqualTo("09:30:00");
		c.andTimeIdLessThanOrEqualTo("10:30:00");

		List<GDetail> stlist = gDetailManager.selectByExample(example);

		// List<GDetail> stlist = gDetailManager.selectWaveByExample(example);

		if (logger.isInfoEnabled()) {
			// logger.info("checkwave() - List<GDetail> stlist=" + stlist);
		}
		// for (int i = 0; i < stlist.size(); i++) {
		// GDetail gdetail = stlist.get(i);
		// // System.out.println(i + " " + gdetail.getTimeId() + " "
		// // + gdetail.getPrice());
		// }
		if (logger.isInfoEnabled()) {
			logger.info("stlist=" + stlist.size());
		}

		List<GDetail> list = getWaveOptimize(stlist, 0.002);
		// List<GDetail> list = getWave(stlist);

		// for (GDetail gdetail : list) {
		// System.out.println(gdetail.getTimeId() + " " + gdetail.getPrice()
		// + "\t" + gdetail.getWaveType());
		// }
		//
		// System.out.println("size=" + list.size());
	}

	public static List<GDetail> getWaveOptimize(List<GDetail> list,
			double oprimizeParam) {
		return getWaveOptimize(list, oprimizeParam, false);
	}

	public static List<GDetail> getWaveOptimize(List<GDetail> list,
			double oprimizeParam, boolean isRemove) {
		double dec = list.get(0).getPrice() * oprimizeParam;
		if (logger.isInfoEnabled()) {
			logger.info("double dec=" + dec);
		}

		List<GDetail> wave = new ArrayList<GDetail>();

		appendWave(list, 0, wave, 0);

		// 删除相邻类型;
		// removeSameWaveType(wave);

		if (isRemove == true) {
			// removeRangeWave(wave); // 删除峰谷之间的数据;
		}

		removeDecWave(wave, dec); // 删除相邻数据较小差异数据;

		// for (int i = 0; i < wave.size(); i++) {
		// System.out.println(wave.get(i).getWaveType() + " "
		// + wave.get(i).getPrice());
		// }

		return wave;
	}

	// public static List<GDetail> getWaveOptimize(List<GDetail> list,
	// double oprimizeParam, boolean isRemove) {
	//
	// int idxPm = -1;
	//
	// for (int i=0; i<list.size(); i++) {
	// if (list.get(i).getTimeId().compareTo("11:30:00") > 0) {
	// idxPm = i;
	// break;
	// }
	// }
	//
	// List <GDetail> listAm = new ArrayList<GDetail>(), listPm=new
	// ArrayList<GDetail>();
	// List <GDetail> waveAm = null, wavePm = null;
	//
	// if (idxPm != -1) {
	// //listAm = new ArrayList<GDetail>(list.subList(0, idxPm-1));
	//
	// // for (int i=0; i<idxPm; i++) {
	// // listAm.add(list.get(i));
	// // }
	//
	// // waveAm = getPrepWaveOptimize(listAm, oprimizeParam, isRemove);
	// waveAm = getPrepWaveOptimize(list.subList(0, idxPm-1), oprimizeParam,
	// isRemove);
	//
	// // for (int i=idxPm; i<list.size(); i++) {
	// // listPm.add(list.get(i));
	// // }
	// wavePm = getPrepWaveOptimize(list.subList(idxPm, list.size()),
	// oprimizeParam, isRemove);
	// waveAm.addAll(wavePm);
	//
	// //removeSameWaveType(wave);
	//
	// return waveAm;
	// }
	//
	//
	//
	// //getPrepWaveOptimize();
	//
	//
	// return null;
	// }

	private static void removeDecWave(List<GDetail> wave, double dec) {

		if (wave.size() < 2) {
			return;
		}

		int idx;
		List<Integer[]> rangeWave = new ArrayList<Integer[]>();

		for (int i = 0; i < wave.size(); i++) {
			idx = appendEqWave(wave, i, wave.get(i), dec);

			if (i != idx) {
				Integer[] tempArr = { i, idx };

				rangeWave.add(tempArr);
			}

			i = idx;
		}

		int mm;

		for (int i = rangeWave.size() - 1; i >= 0; i--) {
			Integer[] tempArr = rangeWave.get(i);

			Integer[] tempArrPm = null, tempArrAm = null;

			if (i < rangeWave.size() - 2) {
				tempArrPm = rangeWave.get(i + 1);
			}

			if (i - 1 > 0) {
				tempArrAm = rangeWave.get(i - 1);
			}

			// System.out.println("tempArr=" + tempArr[0] + "-" + tempArr[1] +
			// ";"
			// + wave.get(tempArr[0]).getTimeId() + "-"
			// + wave.get(tempArr[1]).getTimeId());

			if (i == rangeWave.size() - 1) {
				List<Integer> mms = new ArrayList<Integer>();

				if ("H".equalsIgnoreCase(wave.get(tempArr[0]).getWaveType())) {
					mm = getMaxWave(wave, tempArr[0], tempArr[1]);
					mms.add(mm);

					wave.get(mm).setWaveType("H");

					idx = getMinWave(wave, mm, tempArr[1]);
					mms.add(idx);

					idx = getMaxWave(wave, idx, tempArr[1]);
					mms.add(idx);

				} else {
					mm = getMinWave(wave, tempArr[0], tempArr[1]);
					mms.add(mm);

					wave.get(mm).setWaveType("L");

					idx = getMaxWave(wave, mm, tempArr[1]);
					mms.add(idx);

					idx = getMinWave(wave, idx, tempArr[1]);
					mms.add(idx);
				}

				for (int j = tempArr[1]; j >= tempArr[0]; j--) {
					if (mms.contains(j)) {
						continue;
					}

					// System.out.print(j + ",");

					wave.remove(j);
				}
			} else if (wave.get(tempArr[0]).getWaveType()
					.equalsIgnoreCase(wave.get(tempArr[1]).getWaveType())

			) {
				if ("H".equalsIgnoreCase(wave.get(tempArr[0]).getWaveType())) {
					mm = getMaxWave(wave, tempArr[0], tempArr[1]);
					wave.get(mm).setWaveType("H");
				} else {
					mm = getMinWave(wave, tempArr[0], tempArr[1]);
					wave.get(mm).setWaveType("L");
				}

				for (int j = tempArr[1]; j >= tempArr[0]; j--) {
					if (j == 0 || j == mm) {
						continue;
					}

					// System.out.print(j + ",");

					wave.remove(j);
				}
			} else if (tempArrPm != null
					&& wave.get(tempArr[1]).getTimeId().compareTo("11:30:00") <= 0
					&& wave.get(tempArrPm[0]).getTimeId().compareTo("13:00:00") >= 0) {// 上午最后一波

				List<Integer> mms = new ArrayList<Integer>();

				if ("H".equalsIgnoreCase(wave.get(tempArr[0]).getWaveType())) {
					mm = getMaxWave(wave, tempArr[0], tempArr[1]);
					mms.add(mm);

					wave.get(mm).setWaveType("H");

					idx = getMinWave(wave, mm, tempArr[1]);
					mms.add(idx);

					idx = getMaxWave(wave, idx, tempArr[1]);
					mms.add(idx);

				} else {
					mm = getMinWave(wave, tempArr[0], tempArr[1]);
					mms.add(mm);

					wave.get(mm).setWaveType("L");

					idx = getMaxWave(wave, mm, tempArr[1]);
					mms.add(idx);

					idx = getMinWave(wave, idx, tempArr[1]);
					mms.add(idx);
				}

				for (int j = tempArr[1]; j >= tempArr[0]; j--) {
					if (mms.contains(j)) {
						continue;
					}

					// System.out.print(j + ",");

					wave.remove(j);
				}
			} else if (tempArrAm != null
					&& wave.get(tempArrAm[1]).getTimeId().compareTo("11:30:00") <= 0
					&& wave.get(tempArr[0]).getTimeId().compareTo("13:00:00") >= 0) {// 下午第一波
				List<Integer> mms = new ArrayList<Integer>();

				if ("H".equalsIgnoreCase(wave.get(tempArr[0]).getWaveType())) {
//					mm = getMaxWave(wave, tempArr[0], tempArr[1]);					
//					wave.get(mm).setWaveType("H");
					
					mm = getMinWave(wave, tempArr[0], tempArr[1]);					
					wave.get(mm).setWaveType("L");
					
					mms.add(mm);

				} else {
//					mm = getMinWave(wave, tempArr[0], tempArr[1]);
//					wave.get(mm).setWaveType("L");
					mm = getMinWave(wave, tempArr[0], tempArr[1]);
					wave.get(mm).setWaveType("H");
					
					mms.add(mm);
				}

				for (int j = tempArr[1]; j >= tempArr[0]; j--) {
					if (mms.contains(j)) {
						continue;
					}

					// System.out.print(j + ",");

					wave.remove(j);
				}
			} else {
				for (int j = tempArr[1]; j >= tempArr[0]; j--) {
					if (j == 0) {
						continue;
					}

					// System.out.print(j + ",");

					wave.remove(j);
				}
			}

			// System.out.println();
		}

	}

	private static void removeRangeWave(List<GDetail> wave) {
		int idx = 0;
		String waveType = null;

		waveType = wave.get(0).getWaveType();

		List<Integer[]> rangeWave = new ArrayList<Integer[]>();

		int ln = wave.size();
		for (int i = 0; i < ln; i++) {

			idx = findMaxWaveIdx(wave, i, waveType);

			Integer[] tempArr = { i, idx };

			rangeWave.add(tempArr);

			i = idx;

			if ("H".equalsIgnoreCase(waveType)) {
				waveType = "L";
			} else {
				waveType = "H";
			}
		}

		for (int i = rangeWave.size() - 1; i >= 0; i--) {
			Integer[] tempArr = rangeWave.get(i);
			// if (logger.isInfoEnabled()) {
			// logger.info("tempArr=" + tempArr[0] + "-" + tempArr[1]);
			// }
			for (int j = tempArr[1] - 1; j >= tempArr[0]; j--) {
				if (j == 0) {
					continue;
				}

				// System.out.print(j + ",");

				wave.remove(j);
			}
			// System.out.println();
		}

	}

	private static void removeRangeWave(List<GDetail> wave,
			List<Integer[]> rangeWave) {
		for (int i = rangeWave.size() - 1; i >= 0; i--) {
			Integer[] tempArr = rangeWave.get(i);
			// if (logger.isInfoEnabled()) {
			// logger.info("tempArr=" + tempArr[0] + "-" + tempArr[1]);
			// }
			for (int j = tempArr[1] - 1; j >= tempArr[0]; j--) {
				if (j == 0) {
					continue;
				}

				// System.out.print(j + ",");

				wave.remove(j);
			}
			// System.out.println();
		}
	}

	private static void removeSameWaveType(List<GDetail> wave) {
		if (wave.size() > 1) {
			String waveType = wave.get(0).getWaveType();
			int ln = wave.size();
			for (int i = 1; i < ln; i++) {
				if (waveType.equalsIgnoreCase(wave.get(i).getWaveType())) {

					if ("H".equalsIgnoreCase(waveType)) {
						if (wave.get(i).getPrice() > wave.get(i - 1).getPrice()) {
							wave.remove(i - 1);
						} else {
							wave.remove(i);
						}

						ln = wave.size();
					} else if ("L".equalsIgnoreCase(waveType)) {
						if (wave.get(i).getPrice() < wave.get(i - 1).getPrice()) {
							wave.remove(i - 1);
						} else {
							wave.remove(i);
						}

						ln = wave.size();
					}

				}

				if (i < ln) {
					waveType = wave.get(i).getWaveType();
				}
			}
		}
	}

	private static int findMaxWaveIdx(List<GDetail> wave, int begin,
			String waveType) {
		int idx = begin;

		if (begin > wave.size() - 2) {
			return begin;
		}

		// String waveType = wave.get(begin+1).getWaveType();

		// if (logger.isInfoEnabled()) {
		// logger.info("String waveType=" + waveType);
		// }

		double maxPic = wave.get(begin).getPrice();

		for (int i = begin + 1; i < wave.size(); i++) {
			if ("H".equalsIgnoreCase(waveType)
					&& "L".equalsIgnoreCase(wave.get(i).getWaveType())) {

				if (wave.get(i).getPrice() < maxPic) {
					maxPic = wave.get(i).getPrice();
				} else {
					return i - 2;
				}

			} else if ("L".equalsIgnoreCase(waveType)
					&& "H".equalsIgnoreCase(wave.get(i).getWaveType())) {
				if (wave.get(i).getPrice() > maxPic) {
					maxPic = wave.get(i).getPrice();
				} else {
					return i - 2;
				}
			}
		}

		return idx;
	}

	private static void appendWave(List<GDetail> list, int i,
			List<GDetail> wave, double dec) {
		double f, p;
		int e;
		for (int j = i + 1; j < list.size(); j++) {

			// f = getFlag(list.get(j), list.get(j-1), dec);

			if (wave != null && wave.size() > 0) {
				f = getFlag(list.get(j), wave.get(wave.size() - 1), dec);
			} else {
				f = getFlag(list.get(j), list.get(j - 1), dec);
			}

			if (f == 0) {

				j = appendEqWave(list, j, wave, dec);

			} else if (f > 0) {
				e = appendHighWave(list, j, wave, dec);

				j = getMaxWave(list, j, e);

				for (int bk = j; bk > 0; bk--) {
					p = getFlag(list.get(bk), list.get(bk - 1), 0);

					if (p != 0) {
						j = bk;
						break;
					}
				}

				list.get(j).setWaveType("H");
				wave.add(list.get(j));

				// j = appendEqWave(list, j, wave, dec);
			} else if (f < 0) {
				e = appendLowWave(list, j, wave, dec);

				j = getMinWave(list, j, e);

				for (int bk = j; bk > 0; bk--) {
					p = getFlag(list.get(bk), list.get(bk - 1), 0);

					if (p != 0) {
						j = bk;
						break;
					}
				}

				list.get(j).setWaveType("L");

				wave.add(list.get(j));

				// j = appendEqWave(list, j, wave, dec);
			}

		}

	}

	private static int appendEqWave(List<GDetail> list, int i, double dec) {
		return appendEqWave(list, i, new ArrayList<GDetail>(), dec);
	}

	private static int appendEqWave(List<GDetail> list, int i, GDetail detail,
			double dec) {
		List<GDetail> wave = new ArrayList<GDetail>();
		wave.add(detail);
		return appendEqWave(list, i, wave, dec);
	}

	private static int appendEqWave(List<GDetail> list, int i,
			List<GDetail> wave, double dec) {
		double f;

		for (int j = i + 1; j < list.size(); j++) {
			// f = getFlag(list.get(j), list.get(j-1), dec);

			if (wave != null && wave.size() > 0) {
				f = getFlag(list.get(j), wave.get(wave.size() - 1), dec);
			} else {
				f = getFlag(list.get(j), list.get(j - 1), dec);
			}

			if (f == 0) {
				// System.out.println(list.get(i).getTimeId() + "-" +
				// list.get(j).getTimeId());
				if (list.get(i).getTimeId().compareTo("11:30:00") < 0
						&& list.get(j).getTimeId().compareTo("11:30:00") > 0) {
					return j - 1;
				}
				continue;
			} else {
				return j - 1;
			}
		}

		return list.size() - 1;
	}

	private static double getFlag(GDetail wave1, GDetail wave0, double dec) {
		double f;
		f = wave1.getPrice() - wave0.getPrice();

		if (Math.abs(f) <= dec) {
			f = 0;
		}

		return f;
	}

	private static int appendHighWave(List<GDetail> list, int i,
			List<GDetail> wave, double dec) {
		double f;

		for (int j = i + 1; j < list.size(); j++) {
			f = getFlag(list.get(j), list.get(j - 1), dec);

			// if (wave != null && wave.size() > 0) {
			// f = getFlag(list.get(j), wave.get(wave.size()-1), dec);
			// } else {
			// f = getFlag(list.get(j), list.get(j-1), dec);
			// }

			if (f > 0) {
				// System.out.println(list.get(i).getTimeId() + "-" +
				// list.get(j).getTimeId());
				if (list.get(i).getTimeId().compareTo("11:30:00") < 0
						&& list.get(j).getTimeId().compareTo("11:30:00") > 0) {
					return j - 1;
				}
				continue;
			} else if (f == 0) {
				j = appendEqWave(list, j, wave, dec);
			} else {
				return j - 1;
			}
		}

		return list.size() - 1;
	}

	private static int appendLowWave(List<GDetail> list, int i,
			List<GDetail> wave, double dec) {
		double f;

		for (int j = i + 1; j < list.size(); j++) {
			f = getFlag(list.get(j), list.get(j - 1), dec);

			// if (wave != null && wave.size() > 0) {
			// f = getFlag(list.get(j), wave.get(wave.size()-1), dec);
			// } else {
			// f = getFlag(list.get(j), list.get(j-1), dec);
			// }

			if (f < 0) {
				if (list.get(i).getTimeId().compareTo("11:30:00") < 0
						&& list.get(j).getTimeId().compareTo("11:30:00") > 0) {
					return j - 1;
				}
				continue;
			} else if (f == 0) {
				j = appendEqWave(list, j, wave, dec);
			} else {
				return j - 1;
			}
		}

		return list.size() - 1;
	}

	private static int getMaxWave(List<GDetail> list, int begin, int end) {
		int m = begin;

		double maxPrice;

		maxPrice = list.get(begin).getPrice();

		for (int i = begin; i <= end; i++) {
			if (list.get(i).getPrice() > maxPrice) {
				maxPrice = list.get(i).getPrice();
				m = i;
			}
		}

		return m;
	}

	private static int getMinWave(List<GDetail> list, int begin, int end) {
		int m = begin;

		double minPrice;

		minPrice = list.get(begin).getPrice();

		for (int i = begin; i <= end; i++) {
			if (list.get(i).getPrice() < minPrice) {
				minPrice = list.get(i).getPrice();
				m = i;
			}
		}

		return m;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// testData();

		// checkwave(110, 200);
		// checkwave(120, 200);
		// checkwave(130, 200);
		// checkwave(140, 200);
		// checkwave(150, 200);
		// checkwave(160, 200);
		// checkwave(170, 200);
		// checkwave(180, 200);
		checkwave(190, 20);

	}

}
