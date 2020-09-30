package test.wssecfvt.endsuptokens;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-02-15T08:47:39.513-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "EndSupTokensService0", 
                  wsdlLocation = "EndSupTokens.wsdl",
                  targetNamespace = "http://endsuptokens.wssecfvt.test") 
public class EndSupTokensService0 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endsuptokens.wssecfvt.test", "EndSupTokensService0");
    public final static QName EndSupTokensX509EndorsingPort = new QName("http://endsuptokens.wssecfvt.test", "EndSupTokensX509EndorsingPort");
    static {
        URL url = EndSupTokensService0.class.getResource("EndSupTokens.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(EndSupTokensService0.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "EndSupTokens.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public EndSupTokensService0(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EndSupTokensService0(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EndSupTokensService0() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns EndSupTokensPortType
     */
    @WebEndpoint(name = "EndSupTokensX509EndorsingPort")
    public EndSupTokensPortType getEndSupTokensX509EndorsingPort() {
        return super.getPort(EndSupTokensX509EndorsingPort, EndSupTokensPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EndSupTokensPortType
     */
    @WebEndpoint(name = "EndSupTokensX509EndorsingPort")
    public EndSupTokensPortType getEndSupTokensX509EndorsingPort(WebServiceFeature... features) {
        return super.getPort(EndSupTokensX509EndorsingPort, EndSupTokensPortType.class, features);
    }

}
