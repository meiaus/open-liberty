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
 * 2013-03-08T14:54:04.944-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "Sha2SigService7", 
                  wsdlLocation = "Sha2SigAlg.wsdl",
                  targetNamespace = "http://sha2sig.wssecfvt.test") 
public class Sha2SigService7 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sha2sig.wssecfvt.test", "Sha2SigService7");
    public final static QName UrnSha2Sig7 = new QName("http://sha2sig.wssecfvt.test", "UrnSha2Sig7");
    static {
        URL url = Sha2SigService7.class.getResource("Sha2SigAlg.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(Sha2SigService7.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Sha2SigAlg.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public Sha2SigService7(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Sha2SigService7(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sha2SigService7() {
        super(WSDL_LOCATION, SERVICE);
    }
    
          
    /**
     *
     * @return
     *     returns Sha2SigAlg
     */
    @WebEndpoint(name = "UrnSha2Sig7")
    public Sha2SigAlg getUrnSha2Sig7() {
        return super.getPort(UrnSha2Sig7, Sha2SigAlg.class);
    }
  
}
