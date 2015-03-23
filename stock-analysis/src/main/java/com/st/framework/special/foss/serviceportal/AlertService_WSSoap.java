
package com.st.framework.special.foss.serviceportal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "AlertService_WSSoap", targetNamespace = "http://www.feinno.com/foss/serviceportal")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AlertService_WSSoap {
    @WebMethod(operationName = "SendSms", action = "http://www.feinno.com/foss/serviceportal/SendSms")
    @WebResult(name = "SendSmsResult", targetNamespace = "http://www.feinno.com/foss/serviceportal")
    public String sendSms(
        @WebParam(name = "clientId", targetNamespace = "http://www.feinno.com/foss/serviceportal")
        String clientId,
        @WebParam(name = "mobiles", targetNamespace = "http://www.feinno.com/foss/serviceportal")
        String mobiles,
        @WebParam(name = "content", targetNamespace = "http://www.feinno.com/foss/serviceportal")
        String content);

}
