//
// Generated By:JAX-WS RI IBM 2.1.1 in JDK 6 (JAXB RI IBM JAXB 2.1.3 in JDK 1.6)
//


package com.ibm.was.wssample.sei.ping;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(name = "PingService12PortType", targetNamespace = "http://com/ibm/was/wssample/sei/ping/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PingService12PortType {


    /**
     * 
     * @param parameter
     */
    @WebMethod(action = "pingOperation")
    @Oneway
    public void pingOperation(
        @WebParam(name = "pingStringInput", targetNamespace = "http://com/ibm/was/wssample/sei/ping/", partName = "parameter")
        PingStringInput parameter);

}
