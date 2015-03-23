/**
 * 
 */
package com.st.framework.special.foss.serviceportal;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @author liqiang
 *
 */
public class WsClient {

	private static final Log logger = LogFactory.getLog(WsClient.class);
	/**
	 * @param 公用发送短信方法
	 */
	public static String sendSms(SendSms s) {
		if(null==s || "".equals(s.getClientId())){
			return "fail";
		}
		//返回字符串
		String result ="";
		try {
			//获得实例
			AlertService_WSClient service = InitWsClient.getInstance();
			//获得实现对象
			AlertService_WSSoap soap = service.getAlertService_WSSoap();
			//开始发送短信
			result = soap.sendSms(s.getClientId(), s.getMobiles(), s.getContent());
		} catch (Exception e) {
			result="fail";
			e.printStackTrace();
		}
		logger.info("date-->:"+new Date()+"mobile-->:"+s.getMobiles()+"--sendSms-result->:"+result);
		return result;
	}

}
