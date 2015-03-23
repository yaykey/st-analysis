package com.st.framework.controller.helper.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.st.framework.controller.UserSessionInfo;

public class ApplicationConstants {
//	 public static Map<String,HttpSession> SESSION_MAP =
//		        new HashMap<String,HttpSession>();//用来索引所有session
	
	public static List<HttpSession> ONLINE_USER_LIST = new ArrayList<HttpSession>();//用来索引所有session
		   
    public static int CURRENT_LOGIN_COUNT = 0;//当前登录用户总数
    public static int TOTAL_HISTORY_COUNT = 0;//历史访客总数
    public static int MAX_ONLINE_COUNT = 0;//最大在线访客数量
    public static Date START_DATE = new Date();//服务器启动时间
    public static Date MAX_ONLINE_COUNT_DATE = new Date();//达到最大访客量的日期
}
