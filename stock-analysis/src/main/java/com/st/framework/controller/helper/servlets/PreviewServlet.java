package com.st.framework.controller.helper.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.framework.utils.PreviewUtil;


public class PreviewServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String filename = request.getParameter("filename");
			if(filename!=null&&filename.trim().length()>0){
				PreviewUtil pu = new PreviewUtil(filename);
				pu.showImg(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
	}
}
