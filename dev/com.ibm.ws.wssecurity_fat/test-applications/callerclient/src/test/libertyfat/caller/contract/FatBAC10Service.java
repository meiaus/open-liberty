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
 * 2013-01-31T16:42:43.100-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "FatBAC10Service", 
                  wsdlLocation = "calltoken.wsdl",
                  targetNamespace = "http://caller.libertyfat.test/contract") 
public class FatBAC10Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://caller.libertyfat.test/contract", "FatBAC10Service");
    public final static QName UrnCallerToken10 = new QName("http://caller.libertyfat.test/contract", "UrnCallerToken10");
    static {
        URL url = FatBAC10Service.class.getResource("calltoken.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(FatBAC10Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "calltoken.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public FatBAC10Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FatBAC10Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FatBAC10Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns FVTVersionBAC
     */
    @WebEndpoint(name = "UrnCallerToken10")
    public FVTVersionBAC getUrnCallerToken10() {
        return super.getPort(UrnCallerToken10, FVTVersionBAC.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FVTVersionBAC
     */
    @WebEndpoint(name = "UrnCallerToken10")
    public FVTVersionBAC getUrnCallerToken10(WebServiceFeature... features) {
        return super.getPort(UrnCallerToken10, FVTVersionBAC.class, features);
    }

}
