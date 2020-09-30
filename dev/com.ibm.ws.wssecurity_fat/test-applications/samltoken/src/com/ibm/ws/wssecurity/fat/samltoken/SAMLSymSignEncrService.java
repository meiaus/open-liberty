/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2012
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */

package com.ibm.ws.wssecurity.fat.samltoken;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebService;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.util.Iterator;
import com.ibm.ws.wssecurity.fat.samltoken.utils ;

@WebServiceProvider(targetNamespace="http://wssec.basic.cxf.fats",
    portName="SAMLSymSignEncrPort",
    serviceName="SAMLSymSignEncrService",
    wsdlLocation = "WEB-INF/SamlTokenWebSvc.wsdl"
)

@ServiceMode(value = Service.Mode.MESSAGE)

public class SAMLSymSignEncrService implements Provider<SOAPMessage> {


    public SOAPMessage invoke(SOAPMessage request) {

        SOAPMessage response = null;
        Boolean samlAssertionFound = false ;

        try {
            String hdrText = request.getSOAPHeader().getTextContent();
            System.out.println("Incoming SOAP Header:" + hdrText);
             
//            for (Iterator<Node> i = request.getSOAPHeader().getChildElements() ; i.hasNext();) {
//                Node n = i.next();
//                System.out.println("this node: " + n.getNodeName() ) ;
//                if (n.getNodeName().equals("wsse:Security")) {
//                    NodeList nl = n.getChildNodes();
//                    for (int j=0 ; j < nl.getLength(); j++) {
//                        Node cn = nl.item(j) ;
//                        String nodeName = cn.getNodeName() ;
//                        System.out.println("child node: " + nodeName) ;
//                        if (nodeName.contains("saml") && nodeName.contains(":Assertion")) {  // match saml2:Assertion or saml:Assertion
//                            samlAssertionFound = true ;
//                        }
//                    }
//                }
//            }
            
            StringReader respMsg = null ; 
            if (com.ibm.ws.wssecurity.fat.samltoken.utils.isSAMLAssertionInHeader(request)) {
                System.out.println("SAML Assertion found in SOAP Request Header") ;
                respMsg = new StringReader("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body xmlns=\"http://wssec.basic.cxf.fats/types\"><provider>This is WSSECFVT CXF Symmetric Signature And Encryption Web Service (using SAML).</provider></soapenv:Body></soapenv:Envelope>");
            } else {
                System.out.println("SAML Assertion was NOT found in SOAP Request Header") ;
                respMsg = new StringReader("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body xmlns=\"http://wssec.basic.cxf.fats/types\"><provider>SAML Assertion Missing.</provider></soapenv:Body></soapenv:Envelope>");                
            }

            // SOAPBody sb = request.getSOAPBody();
            // System.out.println("Incoming SOAPBody: " + sb.getValue() );

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

