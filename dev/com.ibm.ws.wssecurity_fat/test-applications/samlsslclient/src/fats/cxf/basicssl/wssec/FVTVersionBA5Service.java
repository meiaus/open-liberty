package fats.cxf.basicssl.wssec;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-11-09T22:47:08.575-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "FVTVersionBA5Service", 
                  wsdlLocation = "BasicPlcyBA.wsdl",
                  targetNamespace = "http://wssec.basicssl.cxf.fats") 
public class FVTVersionBA5Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wssec.basicssl.cxf.fats", "FVTVersionBA5Service");
    public final static QName UrnBasicPlcyBA5 = new QName("http://wssec.basicssl.cxf.fats", "UrnBasicPlcyBA5");
    static {
        URL url = FVTVersionBA5Service.class.getResource("BasicPlcyBA.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(FVTVersionBA5Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "BasicPlcyBA.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public FVTVersionBA5Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FVTVersionBA5Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FVTVersionBA5Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *     returns FVTVersionBA
     */
    @WebEndpoint(name = "UrnBasicPlcyBA5")
    public FVTVersionBA getUrnBasicPlcyBA5() {
        return super.getPort(UrnBasicPlcyBA5, FVTVersionBA.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FVTVersionBA
     */
    @WebEndpoint(name = "UrnBasicPlcyBA5")
    public FVTVersionBA getUrnBasicPlcyBA5(WebServiceFeature... features) {
        return super.getPort(UrnBasicPlcyBA5, FVTVersionBA.class, features);
    }

}
