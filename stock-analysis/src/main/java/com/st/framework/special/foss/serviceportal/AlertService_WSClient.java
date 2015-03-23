
package com.st.framework.special.foss.serviceportal;

import java.net.MalformedURLException;


import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class AlertService_WSClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public AlertService_WSClient() {
        create0();
        Endpoint AlertService_WSSoapEP = service0 .addEndpoint(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoap"), new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoap"), "http://192.168.249.43/ServicePortal/AlertService.asmx");
        endpoints.put(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoap"), AlertService_WSSoapEP);
        Endpoint AlertService_WSSoapLocalEndpointEP = service0 .addEndpoint(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoapLocalEndpoint"), new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoapLocalBinding"), "xfire.local://AlertService_WS");
        endpoints.put(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoapLocalEndpoint"), AlertService_WSSoapLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((AlertService_WSSoap.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoap"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoapLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public AlertService_WSSoap getAlertService_WSSoap() {
        return ((AlertService_WSSoap)(this).getEndpoint(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoap")));
    }

    public AlertService_WSSoap getAlertService_WSSoap(String url) {
        AlertService_WSSoap var = getAlertService_WSSoap();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public AlertService_WSSoap getAlertService_WSSoapLocalEndpoint() {
        return ((AlertService_WSSoap)(this).getEndpoint(new QName("http://www.feinno.com/foss/serviceportal", "AlertService_WSSoapLocalEndpoint")));
    }

    public AlertService_WSSoap getAlertService_WSSoapLocalEndpoint(String url) {
        AlertService_WSSoap var = getAlertService_WSSoapLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

}
