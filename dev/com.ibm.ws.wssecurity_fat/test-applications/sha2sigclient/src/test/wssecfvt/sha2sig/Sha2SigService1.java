package test.wssecfvt.sha2sig;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-03-08T14:54:04.840-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "Sha2SigService1", 
                  wsdlLocation = "Sha2SigAlg.wsdl",
                  targetNamespace = "http://sha2sig.wssecfvt.test") 
public class Sha2SigService1 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sha2sig.wssecfvt.test", "Sha2SigService1");
    public final static QName UrnSha2Sig1 = new QName("http://sha2sig.wssecfvt.test", "UrnSha2Sig1");
    static {
        URL url = Sha2SigService1.class.getResource("Sha2SigAlg.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(Sha2SigService1.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Sha2SigAlg.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public Sha2SigService1(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Sha2SigService1(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sha2SigService1() {
        super(WSDL_LOCATION, SERVICE);
    }
        
    /**
     *
     * @return
     *     returns Sha2SigAlg
     */
    @WebEndpoint(name = "UrnSha2Sig1")
    public Sha2SigAlg getUrnSha2Sig1() {
        return super.getPort(UrnSha2Sig1, Sha2SigAlg.class);
    }
    
}
