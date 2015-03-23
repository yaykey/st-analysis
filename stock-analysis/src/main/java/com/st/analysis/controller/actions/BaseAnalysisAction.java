package com.st.analysis.controller.actions;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.st.framework.business.impl.GDetailManager;
import com.st.framework.controller.actions.BaseAction;


public class BaseAnalysisAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8856073886278012592L;

	@Autowired
	protected GDetailManager gDetailManager;
}
