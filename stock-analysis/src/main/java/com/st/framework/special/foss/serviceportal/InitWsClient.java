package com.st.framework.special.foss.serviceportal;

/**
 * @author majianwei
 * @since  2010-11-30
 * Singleton instance
 */
public class InitWsClient {

	/**
	 * @param args
	 */
	private static AlertService_WSClient instance = null;
	
	/**
	 * @param Singleton instance methods
	 */
	
	public static AlertService_WSClient getInstance() {
		if (instance == null) {
			synchronized(AlertService_WSClient.class){  
			      if (instance == null){
			    	  instance = new AlertService_WSClient();    
			      }    
			    }  
		}
		return instance;

	}

}
