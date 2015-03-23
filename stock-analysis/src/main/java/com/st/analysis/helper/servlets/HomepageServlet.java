package com.st.analysis.helper.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.st.framework.controller.helper.servlets.BaseServlet;

public class HomepageServlet  extends BaseServlet {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(HomepageServlet.class);
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -4494552393160263245L;

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start");
		}
		
		//String homepagePath = this.getBasePath(request) + "/homepage/jsp/index.jsp";
		String homepagePath = "/homepage/jsp/index.jsp";
		if (logger.isInfoEnabled()) {
			logger.info("doPost(HttpServletRequest, HttpServletResponse) - String homepagePath=" + homepagePath);
		}
		
		request.getRequestDispatcher(homepagePath).forward(request, response);
		

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end");
		}
		
		
	}
}
