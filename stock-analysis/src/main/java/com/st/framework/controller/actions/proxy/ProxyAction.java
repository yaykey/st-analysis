package com.st.framework.controller.actions.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.st.Global;
import com.st.analysis.utils.network.ProxyUtils;
import com.st.framework.controller.actions.ConventionAction;
import com.st.framework.module.stock.FactProxy;


@Namespace(value = "/proxy")
@InterceptorRef("common-params")
@Results({ 
	@Result(name = "index", location = "/manager/index.jsp")
})
public class ProxyAction extends ConventionAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ProxyAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915548383037798662L;

	
	@Action("findProxy")
	public void findProxy () {
		if (logger.isDebugEnabled()) {
			logger.debug("Proxy() - start");
		}
		
		Thread thread = new Thread (){
			@Override
			public void run() {
				if (logger.isDebugEnabled()) {
					logger.debug("$Thread.run() - start");
				}
				
				List<String> proxyList = ProxyUtils.findProxyListTest(1000, null);
				
				String [] tempProxy = null;
				
				FactProxy factProxy = null;
				
				for (String proxy : proxyList) {
					tempProxy = proxy.split(",");
					
					factProxy = new FactProxy();
					
					factProxy.setProxyIp(tempProxy[0]);
					factProxy.setProxyPort(Integer.parseInt(tempProxy[1]));
					factProxy.setLocal(tempProxy[2]);
					
					try {
						factProxy.setCreateDate(Global.df.parse(tempProxy[3]));
					} catch (ParseException e) {
						logger.error("$Thread.run()", e);

						e.printStackTrace();
					}
					
					factProxy.setTestDate(new Date());
					factProxy.setTestSpeed(Integer.parseInt(tempProxy[4]));
					
					try {
						factProxyManager.saveOrUpdate(factProxy);
					} catch (Exception ex) {
						logger.error("$Thread.run()", ex);

						ex.printStackTrace();
					}
				}
				

				if (logger.isDebugEnabled()) {
					logger.debug("$Thread.run() - end");
				}
			}			
		};
		thread.start();

		if (logger.isDebugEnabled()) {
			logger.debug("Proxy() - end");
		}
	}
}
