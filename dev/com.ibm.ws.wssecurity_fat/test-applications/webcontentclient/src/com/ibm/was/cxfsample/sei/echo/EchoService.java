package com.ibm.was.cxfsample.sei.echo;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-02-27T11:59:38.705-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "EchoService",
                  wsdlLocation = "Echo.wsdl",
                  targetNamespace = "http://com/ibm/was/cxfsample/sei/echo/") 
public class EchoService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://com/ibm/was/cxfsample/sei/echo/", "EchoService");
    public final static QName EchoServicePort = new QName("http://com/ibm/was/cxfsample/sei/echo/", "EchoServicePort");
    static {
        URL url = EchoService.class.getResource("Echo.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(EchoService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Echo.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public EchoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EchoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EchoService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns EchoServicePortType
     */
    @WebEndpoint(name = "EchoServicePort")
    public EchoServicePortType getEchoServicePort() {
        return super.getPort(EchoServicePort, EchoServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EchoServicePortType
     */
    @WebEndpoint(name = "EchoServicePort")
    public EchoServicePortType getEchoServicePort(WebServiceFeature... features) {
        return super.getPort(EchoServicePort, EchoServicePortType.class, features);
    }

}
