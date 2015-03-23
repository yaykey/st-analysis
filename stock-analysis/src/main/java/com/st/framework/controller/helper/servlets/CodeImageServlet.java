/**
 * @(#)CodeImageServlet.java  2011-4-20
 */
package com.st.framework.controller.helper.servlets;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.framework.utils.CodeImgGenerator;



/**
 * 类 <code>CodeImageServlet</code>
 * @author wangwenyao@feinno.com
 * @version 2011-4-20
 */
public class CodeImageServlet extends HttpServlet {

	private static final long serialVersionUID = -8934970589037148952L;

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			response.setContentType("image/jpeg");
			CodeImgGenerator cig = new CodeImgGenerator();
			ImageIO.write(cig.getImage(), "JPEG", response.getOutputStream());
			request.getSession().setAttribute("codeImage", cig.getCode());
		}catch (Exception e) {
		}
		

	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
