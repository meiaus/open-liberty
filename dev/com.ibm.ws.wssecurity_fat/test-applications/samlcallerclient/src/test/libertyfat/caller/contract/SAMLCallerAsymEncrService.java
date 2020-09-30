package test.libertyfat.caller.contract;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2015-09-28T08:30:59.064-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "SAMLCallerAsymEncrService", 
                  wsdlLocation = "samlcallertoken.wsdl",
                  targetNamespace = "http://caller.libertyfat.test/contract") 
public class SAMLCallerAsymEncrService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://caller.libertyfat.test/contract", "SAMLCallerAsymEncrService");
    public final static QName SAMLCallerAsymEncrPort = new QName("http://caller.libertyfat.test/contract", "SAMLCallerAsymEncrPort");
    static {
        URL url = SAMLCallerAsymEncrService.class.getResource("samlcallertoken.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(SAMLCallerAsymEncrService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "samlcallertoken.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SAMLCallerAsymEncrService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SAMLCallerAsymEncrService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SAMLCallerAsymEncrService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns FVTVersionSamlC
     */
    @WebEndpoint(name = "SAMLCallerAsymEncrPort")
    public FVTVersionSamlC getSAMLCallerAsymEncrPort() {
        return super.getPort(SAMLCallerAsymEncrPort, FVTVersionSamlC.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FVTVersionSamlC
     */
    @WebEndpoint(name = "SAMLCallerAsymEncrPort")
    public FVTVersionSamlC getSAMLCallerAsymEncrPort(WebServiceFeature... features) {
        return super.getPort(SAMLCallerAsymEncrPort, FVTVersionSamlC.class, features);
    }

}
