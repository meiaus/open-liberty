package test.libertyfat.x509mig.contract;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-12-19T14:55:09.202-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "FatBAX16Service", 
                  wsdlLocation = "x509migtoken.wsdl",
                  targetNamespace = "http://x509mig.libertyfat.test/contract") 
public class FatBAX16Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://x509mig.libertyfat.test/contract", "FatBAX16Service");
    public final static QName UrnX509Token16 = new QName("http://x509mig.libertyfat.test/contract", "UrnX509Token16");
    static {
        URL url = FatBAX16Service.class.getResource("x509migtoken.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(FatBAX16Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "x509migtoken.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public FatBAX16Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FatBAX16Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FatBAX16Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns FVTVersionBAX
     */
    @WebEndpoint(name = "UrnX509Token16")
    public FVTVersionBAX getUrnX509Token16() {
        return super.getPort(UrnX509Token16, FVTVersionBAX.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FVTVersionBAX
     */
    @WebEndpoint(name = "UrnX509Token16")
    public FVTVersionBAX getUrnX509Token16(WebServiceFeature... features) {
        return super.getPort(UrnX509Token16, FVTVersionBAX.class, features);
    }

}
