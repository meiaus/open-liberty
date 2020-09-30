package fats.cxf.pwdigest.wssec;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-10-24T11:50:16.898-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "SOAPServicePWDigest", 
                  wsdlLocation = "UsrTokenPWDigestWebSvc.wsdl",
                  targetNamespace = "http://wssec.pwdigest.cxf.fats") 
public class SOAPServicePWDigest extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wssec.pwdigest.cxf.fats", "SOAPServicePWDigest");
    public final static QName SOAPPortPWDigest = new QName("http://wssec.pwdigest.cxf.fats", "SOAPPortPWDigest");
    static {
        URL url = SOAPServicePWDigest.class.getResource("UsrTokenPWDigestWebSvc.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(SOAPServicePWDigest.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "UsrTokenPWDigestWebSvc.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SOAPServicePWDigest(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SOAPServicePWDigest(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOAPServicePWDigest() {
        super(WSDL_LOCATION, SERVICE);
    }
  
    /*
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPServicePWDigest(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPServicePWDigest(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPServicePWDigest(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }
*/
    /**
     *
     * @return
     *     returns PWDigest
     */
    @WebEndpoint(name = "SOAPPortPWDigest")
    public PWDigest getSOAPPortPWDigest() {
        return super.getPort(SOAPPortPWDigest, PWDigest.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PWDigest
     */
    @WebEndpoint(name = "SOAPPortPWDigest")
    public PWDigest getSOAPPortPWDigest(WebServiceFeature... features) {
        return super.getPort(SOAPPortPWDigest, PWDigest.class, features);
    }

}
