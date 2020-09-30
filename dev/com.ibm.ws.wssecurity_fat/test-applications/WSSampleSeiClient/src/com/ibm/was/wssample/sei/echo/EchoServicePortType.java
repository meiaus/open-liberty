//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:27 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:17 AM(foreman)-)
//


package com.ibm.was.wssample.sei.echo;

import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

@WebService(name = "EchoServicePortType", targetNamespace = "http://com/ibm/was/wssample/sei/echo/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EchoServicePortType {


    /**
     * 
     * @param parameter
     * @return
     *     returns javax.xml.ws.Response<com.ibm.was.wssample.sei.echo.EchoStringResponse>
     */
    @WebMethod(operationName = "echoOperation", action = "echoOperation")
    public Response<EchoStringResponse> echoOperationAsync(
        @WebParam(name = "echoStringInput", targetNamespace = "http://com/ibm/was/wssample/sei/echo/", partName = "parameter")
        EchoStringInput parameter);

    /**
     * 
     * @param parameter
     * @param asyncHandler
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "echoOperation", action = "echoOperation")
    public Future<?> echoOperationAsync(
        @WebParam(name = "echoStringInput", targetNamespace = "http://com/ibm/was/wssample/sei/echo/", partName = "parameter")
        EchoStringInput parameter,
        @WebParam(name = "echoOperationResponse", targetNamespace = "", partName = "asyncHandler")
        AsyncHandler<EchoStringResponse> asyncHandler);

    /**
     * 
     * @param parameter
     * @return
     *     returns com.ibm.was.wssample.sei.echo.EchoStringResponse
     */
    @WebMethod(action = "echoOperation")
    @WebResult(name = "echoStringResponse", targetNamespace = "http://com/ibm/was/wssample/sei/echo/", partName = "parameter")
    public EchoStringResponse echoOperation(
        @WebParam(name = "echoStringInput", targetNamespace = "http://com/ibm/was/wssample/sei/echo/", partName = "parameter")
        EchoStringInput parameter);

}
