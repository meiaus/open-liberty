package com.ibm.was.wssample.sei.echo;


import java.util.logging.Logger;

@javax.jws.WebService (endpointInterface="com.ibm.was.wssample.sei.echo.EchoServicePortType",
                       targetNamespace="http://com/ibm/was/wssample/sei/echo/", 
                       serviceName="Echo21Service", 
                       wsdlLocation = "WEB-INF/wsdl/EchoX509.wsdl",
                       portName="Echo21ServicePort")
public class Echo21SOAPImpl{

    private static final Logger LOG = Logger.getLogger(Echo21SOAPImpl.class.getName());

    public EchoStringResponse echoOperation(EchoStringInput parameter) {
        LOG.info("(Echo21SoapImpl)Executing operation echoOperation");
        String strInput = (parameter == null ? "input_is_null" : parameter.getEchoInput() );
        System.out.println("(WSSampleSei)Echo21SOAPImpl:"+parameter+":"+ strInput);
        try {
            com.ibm.was.wssample.sei.echo.EchoStringResponse _return = new EchoStringResponse();
            // Echo back
            _return.setEchoResponse( "Echo21SOAPImpl>>" + strInput );
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}