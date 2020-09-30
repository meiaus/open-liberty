//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:27 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:17 AM(foreman)-)
//


package com.ibm.was.wssample.sei.echo;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "Echo4Service", targetNamespace = "http://com/ibm/was/wssample/sei/echo/", wsdlLocation = "WEB-INF/wsdl/Echo.wsdl")
public class Echo4Service
    extends Service
{

    private final static URL ECHOSERVICE_WSDL_LOCATION;
    private final static WebServiceException ECHOSERVICE_EXCEPTION;
    private final static QName ECHOSERVICE_QNAME = new QName("http://com/ibm/was/wssample/sei/echo/", "Echo4Service");

    static {
            ECHOSERVICE_WSDL_LOCATION = com.ibm.was.wssample.sei.echo.Echo4Service.class.getResource("/WEB-INF/wsdl/Echo.wsdl");
        WebServiceException e = null;
        if (ECHOSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/Echo.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        ECHOSERVICE_EXCEPTION = e;
    }

    public Echo4Service() {
        super(__getWsdlLocation(), ECHOSERVICE_QNAME);
    }

    public Echo4Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ECHOSERVICE_QNAME, features);
    }

    public Echo4Service(URL wsdlLocation) {
        super(wsdlLocation, ECHOSERVICE_QNAME);
    }

    public Echo4Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ECHOSERVICE_QNAME, features);
    }

    public Echo4Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Echo4Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EchoServicePortType
     */
    @WebEndpoint(name = "Echo4ServicePort")
    public EchoServicePortType getEcho4ServicePort() {
        return super.getPort(new QName("http://com/ibm/was/wssample/sei/echo/", "Echo4ServicePort"), EchoServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EchoServicePortType
     */
    @WebEndpoint(name = "Echo4ServicePort")
    public EchoServicePortType getEcho4ServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://com/ibm/was/wssample/sei/echo/", "Echo4ServicePort"), EchoServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ECHOSERVICE_EXCEPTION!= null) {
            throw ECHOSERVICE_EXCEPTION;
        }
        return ECHOSERVICE_WSDL_LOCATION;
    }

}
