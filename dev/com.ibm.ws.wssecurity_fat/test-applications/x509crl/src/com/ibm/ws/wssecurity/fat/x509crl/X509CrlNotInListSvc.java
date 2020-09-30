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

package com.ibm.ws.wssecurity.fat.x509crl;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebService;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.ibm.ws.wssecurity.fat.utils.common.SharedTools;

@WebServiceProvider(targetNamespace="http://x509crl.wssecfvt.test",
    portName="X509CrlNotInList",
    serviceName="X509CrlNotInListService",
    wsdlLocation = "WEB-INF/X509Crl.wsdl"
)

@ServiceMode(value = Service.Mode.MESSAGE)

public class X509CrlNotInListSvc implements Provider<SOAPMessage> {

    public X509CrlNotInListSvc() {
        super();
        try {
            SharedTools.fixProviderOrder("X509CrlNotInListSvc");
        } catch (Exception e) {
            System.out.println("Failed to either view or update the Java provider list - this MAY cause issues later");
        }
    }    

    public SOAPMessage invoke(SOAPMessage request) {

        SOAPMessage response = null;

        try {

            System.out.println("Incoming Client Request as a SOAPMessage:");
            request.writeTo(System.out);

            String hdrText = request.getSOAPHeader().getTextContent();
            System.out.println("Incoming SOAP Header:" + hdrText);

            StringReader respMsg = new StringReader("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body xmlns=\"http://x509crl.wssecfvt.test/types\"><provider>This is X509CrlNotInListService Web Service.</provider></soapenv:Body></soapenv:Envelope>");

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

