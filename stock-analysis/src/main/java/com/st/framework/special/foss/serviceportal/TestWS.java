package com.st.framework.special.foss.serviceportal;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestWS extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TestWS() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=====������ŵ��Է���========");
		//���������Ϣ����
		SendSms sms = new SendSms();
		sms.setMobiles("15011337311");
		sms.setContent("�?ΰ����");
		//���Ͷ���
		String result = WsClient.sendSms(sms);
		System.out.println("smsResult===============:"+result);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
