package com.st.framework.special.task;
//package com.feinno.circle.framework.special.task;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import net.sf.json.JSONArray;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.feinno.circle.Global;
//import com.feinno.circle.framework.controller.actions.report.map.CallMessageInbound;
//import com.feinno.circle.framework.module.RptBmpOnline;
//import com.feinno.circle.framework.module.RptBmpOnlioneAnalysis;
//import com.feinno.circle.framework.persistence.mapper.RptBmpOnlineMapper;
//import com.feinno.circle.framework.persistence.mapper.RptBmpOnlioneAnalysisMapper;
//import com.feinno.circle.framework.utils.DateUtil;
//public class MapTask extends TaskBase{
//	private static final Logger logger = LoggerFactory.getLogger(MapTask.class);
//	public static String mapContent = "";
//	public static Map<String, Object> hourDataMap = null;
//	public void exec() throws NumberFormatException, Exception {
//		if (logger.isDebugEnabled()) {
//			logger.debug("地图定时任务开始");
//		}
//		Integer pvovinceArr[] ={901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931};
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		Map<String, Object> pvovinceDataMap = new HashMap<String, Object>();
//		Map<String, Object> clientDataMap = new HashMap<String, Object>();
//		List<Map<String,Object>> hourDataMapList= new ArrayList<Map<String,Object>>();
//		
//		// 取得开始结束时间
//		String nowTime = DateUtil.toStrDateFromUtilDateByFormat(new Date(), "yyyyMMddHHmm");
////		String nowTime = "201407062300";
//		// 取得时间段里面的数据，防止数据查询为空，所以往前推几分钟
//		String startTime =DateUtil.toDayMonthHHMM(nowTime, -10);
//		String endTime =DateUtil.toDayMonthHHMM(nowTime, -5);
//
//        // 取得某一分钟的分省分客户端的数据
//		List<RptBmpOnline> rptBmpOnlineList = this.getRptBmpOnlineMapperBean().selectMapOneMinuteData("RPT_BMP_ONLINE", new Long("21030501"),
//				startTime, endTime, "1", "-1", "108", null, "211,299",
//						"0,1000,2000,5000,6000", "110300101");
//		
//		// 取得历史最高值
//		List<RptBmpOnlioneAnalysis> rptBmpOnlineAnalysisList = this.getRptBmpOnlioneAnalysisMapperBean()
//				.selectMapMaxValue(new Long("21030502"), startTime, "1", "-1","130");
//		
//		// 全国所有客户端汇总数据
//		for(int j=0; j<rptBmpOnlineList.size();j++){
//			if(rptBmpOnlineList.get(j).getoDimId()==86 && rptBmpOnlineList.get(j).gettDimId()==0){
//				dataMap.put("total_value_data", rptBmpOnlineList.get(j).getoIndexValue().intValue());
//			}
//		}
//		
//		// 各省份数据
//		for (int i=0; i<pvovinceArr.length; i++) {
//		    clientDataMap = new HashMap<String, Object>();
//			for(int j=0; j<rptBmpOnlineList.size();j++){
//				RptBmpOnline rptBmpOnline =rptBmpOnlineList.get(j);
//				if(pvovinceArr[i].equals(rptBmpOnline.getoDimId())){
//					clientDataMap.put("0".equals(rptBmpOnline.gettDimId().toString())?"all":rptBmpOnline.gettDimId().toString(), 
//							rptBmpOnlineList.get(j).getoIndexValue().intValue());
//				}
//			}
//			pvovinceDataMap.put(pvovinceArr[i].toString(), clientDataMap);
//		}
//		dataMap.put("province_value_data", pvovinceDataMap);
//		
//		// 历史最高值
//		for (RptBmpOnlioneAnalysis rptBmpOnlioneAnalysis : rptBmpOnlineAnalysisList) {
//			dataMap.put("max_value_data", rptBmpOnlioneAnalysis.getoIndexValue().intValue());
//		}
//		
//		
//        // 取得24个小时整点的数据
//		if("00".equals(nowTime.substring(10)) || mapContent==null || mapContent.trim().length()<1){
//			hourDataMap = new HashMap<String, Object>();
//			String hourEndTime = DateUtil.toDayMonthHHMM(nowTime, -60);
//			String hourStartTime = DateUtil.toDayMonthHHMM(hourEndTime, -60*22).substring(0,10)+"00";
//			
//			// 24小时整点的数据
//			List<RptBmpOnline> hourDataList = this.getRptBmpOnlineMapperBean().select24HourData(
//					hourStartTime, hourEndTime,"0,1000,2000,5000,6000");
//			
//			// 做一个数组，有哪23个整点时刻
//			String hourArray[] = new String[23];
//			String minueTime = hourDataList.get(0).gettIndexValue().toString();
//			hourArray[0] = minueTime.substring(8);
//			for(int i=1; i<23; i++){
//				String minueTime_k = DateUtil.toDayMonthHHMM(minueTime, 60*i);
//				hourArray[i] = minueTime_k.substring(8);
//			}
//			
//			String hourAlldata = "";
//			for (int i=0;i<hourDataList.size(); i++) {
//				if(hourDataList.get(i).getoDimId()==86 && hourDataList.get(i).gettDimId()==0){
//					hourAlldata +=  hourDataList.get(i).getoIndexValue().intValue();
//					if(i!=hourDataList.size()-1){
//						hourAlldata += ",";
//					}
//				}
//			}
//			hourDataMap.put("hour_all_data", hourAlldata);
//			
//			for(int i=0; i<hourArray.length; i++){
//				Map<String, Object> pvovinceHourDataMap = new HashMap<String, Object>();
//				for(int j=0; j<pvovinceArr.length; j++){
//					clientDataMap = new HashMap<String, Object>();
//					for(int a=0; a<hourDataList.size(); a++){
//						RptBmpOnline rptBmpOnline = hourDataList.get(a);
//						String hourTime = rptBmpOnline.gettIndexValue().toString().substring(8);
//						if(hourArray[i].equals(hourTime) && pvovinceArr[j].equals(rptBmpOnline.getoDimId())){
//							clientDataMap.put("0".equals(rptBmpOnline.gettDimId().toString()) ? "all":rptBmpOnline.gettDimId().toString(), 
//									rptBmpOnline.getoIndexValue().intValue());
//						}
//					}
//					pvovinceHourDataMap.put(pvovinceArr[j].toString(), clientDataMap);
//				}
//				hourDataMapList.add(pvovinceHourDataMap);
//			}
//			
//			hourDataMap.put("hour_province_data", hourDataMapList);
//			dataMap.put("hour_data", hourDataMap);
//		}else{
//			dataMap.put("hour_data", hourDataMap);
//		}
//		
//		JSONArray array = JSONArray.fromObject(dataMap);
//		mapContent = array.toString();
//		logger.info("################"+mapContent);
//		
//		CallMessageInbound mp = new CallMessageInbound(2097152,2097152);
//		mp.broadcast(mapContent);
//		
//		if (logger.isDebugEnabled()) {
//			logger.debug("地图定时任务结束");
//		}
//	}
//
//	public RptBmpOnlineMapper getRptBmpOnlineMapperBean() throws Exception{
//
//		return (RptBmpOnlineMapper) Global._ctx.getBean("rptBmpOnlineMapper");
//	}
//	
//	
//	public RptBmpOnlioneAnalysisMapper getRptBmpOnlioneAnalysisMapperBean() throws Exception{
//
//		return (RptBmpOnlioneAnalysisMapper) Global._ctx.getBean("rptBmpOnlioneAnalysisMapper");
//	}
//}
