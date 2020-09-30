package test.wssecfvt.x509sig;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-12-10T17:58:03.292-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "X509XmlStrService1", 
                  wsdlLocation = "X509StrTypes.wsdl",
                  targetNamespace = "http://x509sig.wssecfvt.test") 
public class X509XmlStrService1 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://x509sig.wssecfvt.test", "X509XmlStrService1");
    public final static QName UrnX509Str1 = new QName("http://x509sig.wssecfvt.test", "UrnX509Str1");
    static {
        URL url = X509XmlSigService5.class.getResource("X509StrTypes.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(X509XmlStrService1.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "X509StrTypes.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public X509XmlStrService1(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public X509XmlStrService1(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public X509XmlStrService1() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns X509XmlSig
     */
    @WebEndpoint(name = "UrnX509Str1")
    public X509XmlSig getUrnX509Str1() {
        return super.getPort(UrnX509Str1, X509XmlSig.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns X509XmlSig
     */
    @WebEndpoint(name = "UrnX509Str1")
    public X509XmlSig getUrnX509Str1(WebServiceFeature... features) {
        return super.getPort(UrnX509Str1, X509XmlSig.class, features);
    }

}

