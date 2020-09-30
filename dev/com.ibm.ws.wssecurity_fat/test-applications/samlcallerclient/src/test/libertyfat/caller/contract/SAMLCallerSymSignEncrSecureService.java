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
 * 2015-09-28T08:30:59.082-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "SAMLCallerSymSignEncrSecureService", 
                  wsdlLocation = "samlcallertoken.wsdl",
                  targetNamespace = "http://caller.libertyfat.test/contract") 
public class SAMLCallerSymSignEncrSecureService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://caller.libertyfat.test/contract", "SAMLCallerSymSignEncrSecureService");
    public final static QName SAMLCallerSymSignSecureEncrPort = new QName("http://caller.libertyfat.test/contract", "SAMLCallerSymSignSecureEncrPort");
    static {
        URL url = SAMLCallerSymSignEncrSecureService.class.getResource("samlcallertoken.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(SAMLCallerSymSignEncrSecureService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "samlcallertoken.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SAMLCallerSymSignEncrSecureService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SAMLCallerSymSignEncrSecureService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SAMLCallerSymSignEncrSecureService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns FVTVersionSamlC
     */
    @WebEndpoint(name = "SAMLCallerSymSignSecureEncrPort")
    public FVTVersionSamlC getSAMLCallerSymSignSecureEncrPort() {
        return super.getPort(SAMLCallerSymSignSecureEncrPort, FVTVersionSamlC.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FVTVersionSamlC
     */
    @WebEndpoint(name = "SAMLCallerSymSignSecureEncrPort")
    public FVTVersionSamlC getSAMLCallerSymSignSecureEncrPort(WebServiceFeature... features) {
        return super.getPort(SAMLCallerSymSignSecureEncrPort, FVTVersionSamlC.class, features);
    }

}
