package test.wssecfvt.x509enc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-01-03T17:08:16.976-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "X509XmlEncService2", 
                  wsdlLocation = "X509XmlEnc.wsdl",
                  targetNamespace = "http://x509enc.wssecfvt.test") 
public class X509XmlEncService2 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://x509enc.wssecfvt.test", "X509XmlEncService2");
    public final static QName UrnX509Enc2 = new QName("http://x509enc.wssecfvt.test", "UrnX509Enc2");
    static {
        URL url = X509XmlEncService2.class.getResource("X509XmlEnc.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(X509XmlEncService2.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "X509XmlEnc.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public X509XmlEncService2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public X509XmlEncService2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public X509XmlEncService2() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns XmlEnc1
     */
    @WebEndpoint(name = "UrnX509Enc2")
    public XmlEnc1 getUrnX509Enc2() {
        return super.getPort(UrnX509Enc2, XmlEnc1.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns XmlEnc1
     */
    @WebEndpoint(name = "UrnX509Enc2")
    public XmlEnc1 getUrnX509Enc2(WebServiceFeature... features) {
        return super.getPort(UrnX509Enc2, XmlEnc1.class, features);
    }

}
