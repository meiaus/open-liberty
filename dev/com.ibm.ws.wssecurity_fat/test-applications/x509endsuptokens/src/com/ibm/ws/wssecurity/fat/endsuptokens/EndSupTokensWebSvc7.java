/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2013
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */

package com.ibm.ws.wssecurity.fat.endsuptokens;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
// import javax.xml.soap.SOAPBody;
// import javax.xml.soap.SOAPPart;
// import javax.xml.soap.SOAPEnvelope;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebService;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

@WebServiceProvider(targetNamespace="http://endsuptokens.wssecfvt.test",
    portName="EndSupTokensUNTSignedEndorsingEncryptedPort",
    serviceName="EndSupTokensService7",
    wsdlLocation = "WEB-INF/EndSupTokens.wsdl"
)

@ServiceMode(value = Service.Mode.MESSAGE)

public class EndSupTokensWebSvc7 implements Provider<SOAPMessage> {


    public SOAPMessage invoke(SOAPMessage request) {

        SOAPMessage response = null;

        try {

            System.out.println("Incoming Client Request as a SOAPMessage:");
            request.writeTo(System.out);

            String hdrText = request.getSOAPHeader().getTextContent();
            System.out.println("Incoming SOAP Header:" + hdrText);

            StringReader respMsg = new StringReader("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body xmlns=\"http://endsuptokens.wssecfvt.test/types\"><provider>This is EndSupTokensWebSvc7 Web Service.</provider></soapenv:Body></soapenv:Envelope>");

            Source src = new StreamSource(respMsg);
            MessageFactory factory = MessageFactory.newInstance();
            response = factory.createMessage();
            response.getSOAPPart().setContent(src);
            response.saveChanges();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;

    }

}

