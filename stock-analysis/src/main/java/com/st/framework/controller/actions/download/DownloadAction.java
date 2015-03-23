package com.st.framework.controller.actions.download;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.st.analysis.utils.download.DownloadFileBean;
import com.st.analysis.utils.stock.DownloadSinaDataUtils;
import com.st.framework.business.impl.fact.FactProxyManager;
import com.st.framework.controller.actions.ConventionAction;
import com.st.framework.persistence.mapper.stock.FactDownloadFileConfigMapper;




@Namespace(value = "/download")
@InterceptorRef("common-params")
@Results({ 
	@Result(name = "index", location = "/manager/index.jsp")
})
public class DownloadAction extends ConventionAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455147238267823865L;

	
	
	@Action("download")
	public void download () {
		
		DownloadSinaDataUtils tb = new DownloadSinaDataUtils("2014-01-01", "2014-01-31", "sz300002");
		List<DownloadFileBean> list =  tb.getUrlList();
		
		
		
	}
}
