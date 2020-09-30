package test.wssecfvt.wsstemplates;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-02-15T17:09:50.368-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "WSSTemplatesService1", 
                  wsdlLocation = "file:/C:/Liberty/com.ibm.ws.wssecurity_fat/test-applications/wsstemplates/resources/WEB-INF/WSSTemplatesTest.wsdl",
                  targetNamespace = "http://wsstemplates.wssecfvt.test") 
public class WSSTemplatesService1 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wsstemplates.wssecfvt.test", "WSSTemplatesService1");
    public final static QName WSSTemplate1 = new QName("http://wsstemplates.wssecfvt.test", "WSSTemplate1");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Liberty/com.ibm.ws.wssecurity_fat/test-applications/wsstemplates/resources/WEB-INF/WSSTemplatesTest.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSSTemplatesService1.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Liberty/com.ibm.ws.wssecurity_fat/test-applications/wsstemplates/resources/WEB-INF/WSSTemplatesTest.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSSTemplatesService1(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSSTemplatesService1(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSSTemplatesService1() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns WSSTemplates
     */
    @WebEndpoint(name = "WSSTemplate1")
    public WSSTemplates getWSSTemplate1() {
        return super.getPort(WSSTemplate1, WSSTemplates.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSSTemplates
     */
    @WebEndpoint(name = "WSSTemplate1")
    public WSSTemplates getWSSTemplate1(WebServiceFeature... features) {
        return super.getPort(WSSTemplate1, WSSTemplates.class, features);
    }

}
