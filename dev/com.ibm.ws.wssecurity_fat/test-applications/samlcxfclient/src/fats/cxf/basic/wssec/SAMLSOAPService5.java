package fats.cxf.basic.wssec;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2015-09-16T17:50:53.062-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "SAMLSOAPService5", 
                  wsdlLocation = "../../samltoken/resources/WEB-INF/SamlTokenWebSvc.wsdl",
                  targetNamespace = "http://wssec.basic.cxf.fats") 
public class SAMLSOAPService5 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wssec.basic.cxf.fats", "SAMLSOAPService5");
    public final static QName SAMLSoapPort5 = new QName("http://wssec.basic.cxf.fats", "SAMLSoapPort5");
    static {
        URL url = SAMLSOAPService5.class.getResource("../../samltoken/resources/WEB-INF/SamlTokenWebSvc.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(SAMLSOAPService5.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "../../samltoken/resources/WEB-INF/SamlTokenWebSvc.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SAMLSOAPService5(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SAMLSOAPService5(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SAMLSOAPService5() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns SamlTokenType
     */
    @WebEndpoint(name = "SAMLSoapPort5")
    public SamlTokenType getSAMLSoapPort5() {
        return super.getPort(SAMLSoapPort5, SamlTokenType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SamlTokenType
     */
    @WebEndpoint(name = "SAMLSoapPort5")
    public SamlTokenType getSAMLSoapPort5(WebServiceFeature... features) {
        return super.getPort(SAMLSoapPort5, SamlTokenType.class, features);
    }

}
