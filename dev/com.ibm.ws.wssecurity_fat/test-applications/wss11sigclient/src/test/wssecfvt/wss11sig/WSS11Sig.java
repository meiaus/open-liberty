package test.wssecfvt.wss11sig;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-01-13T17:16:55.050-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebService(targetNamespace = "http://wss11sig.wssecfvt.test", name = "WSS11Sig")
@XmlSeeAlso({test.wssecfvt.wss11sig.types.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WSS11Sig {

    @WebResult(name = "getVerResponse", targetNamespace = "http://wss11sig.wssecfvt.test/types", partName = "out")
    @WebMethod
    public test.wssecfvt.wss11sig.types.GetVerResponse invoke(
        @WebParam(partName = "in", name = "getVer", targetNamespace = "http://wss11sig.wssecfvt.test/types")
        test.wssecfvt.wss11sig.types.GetVer in
    );
}
