package com.ibm.was.wssample.sei.echo;


import java.util.logging.Logger;

@javax.jws.WebService (endpointInterface="com.ibm.was.wssample.sei.echo.EchoServicePortType",
                       targetNamespace="http://com/ibm/was/wssample/sei/echo/", 
                       serviceName="Echo1Service", 
                       wsdlLocation = "WEB-INF/wsdl/Echo.wsdl",
                       portName="Echo1ServicePort")
public class Echo1SOAPImpl{

    private static final Logger LOG = Logger.getLogger(Echo1SOAPImpl.class.getName());

    public EchoStringResponse echoOperation(EchoStringInput parameter) {
        LOG.info("(Echo1SoapImpl)Executing operation echoOperation");
        String strInput = (parameter == null ? "input_is_null" : parameter.getEchoInput() );
        System.out.println("(WSSampleSei)Echo1SOAPImpl:"+parameter+":"+ strInput);
        try {
            com.ibm.was.wssample.sei.echo.EchoStringResponse _return = new EchoStringResponse();
            // Echo back
            _return.setEchoResponse( "Echo1SOAPImpl>>" + strInput );
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}