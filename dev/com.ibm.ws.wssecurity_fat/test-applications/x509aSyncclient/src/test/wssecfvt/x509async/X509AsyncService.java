package test.wssecfvt.x509async;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-04-19T11:51:44.951-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "X509AsyncService", 
                  wsdlLocation = "X509Async.wsdl",
                  targetNamespace = "http://x509async.wssecfvt.test") 
public class X509AsyncService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://x509async.wssecfvt.test", "X509AsyncService");
    public final static QName X509AsyncPort = new QName("http://x509async.wssecfvt.test", "X509AsyncPort");
    static {
        URL url = X509AsyncService.class.getResource("X509Async.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(X509AsyncService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "X509Async.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public X509AsyncService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public X509AsyncService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public X509AsyncService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns UrnX509AsyncType
     */
    @WebEndpoint(name = "X509AsyncPort")
    public UrnX509AsyncType getX509AsyncPort() {
        return super.getPort(X509AsyncPort, UrnX509AsyncType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UrnX509AsyncType
     */
    @WebEndpoint(name = "X509AsyncPort")
    public UrnX509AsyncType getX509AsyncPort(WebServiceFeature... features) {
        return super.getPort(X509AsyncPort, UrnX509AsyncType.class, features);
    }

}
