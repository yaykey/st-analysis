
package com.st.framework.special.foss.serviceportal;

import javax.jws.WebService;

@WebService(serviceName = "AlertService_WS", targetNamespace = "http://www.feinno.com/foss/serviceportal", endpointInterface = "com.feinno.foss.serviceportal.AlertService_WSSoap")
public class AlertService_WSImpl
    implements AlertService_WSSoap
{
    public String sendSms(String clientId, String mobiles, String content) {
        throw new UnsupportedOperationException();
    }

}
