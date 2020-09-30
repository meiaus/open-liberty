package com.ibm.was.wssample.sei.echo;


import java.util.logging.Logger;

@javax.jws.WebService (endpointInterface="com.ibm.was.wssample.sei.echo.EchoServicePortType",
                       targetNamespace="http://com/ibm/was/wssample/sei/echo/", 
                       serviceName="Echo23Service", 
                       wsdlLocation = "WEB-INF/wsdl/EchoX509.wsdl",
                       portName="Echo23ServicePort")
public class Echo23SOAPImpl{

    private static final Logger LOG = Logger.getLogger(Echo23SOAPImpl.class.getName());

    public EchoStringResponse echoOperation(EchoStringInput parameter) {
        LOG.info("(Echo23SoapImpl)Executing operation echoOperation");
        String strInput = (parameter == null ? "input_is_null" : parameter.getEchoInput() );
        System.out.println("(WSSampleSei)Echo23SOAPImpl:"+parameter+":"+ strInput);
        try {
            com.ibm.was.wssample.sei.echo.EchoStringResponse _return = new EchoStringResponse();
            // Echo back
            _return.setEchoResponse( "Echo23SOAPImpl>>" + strInput );
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}