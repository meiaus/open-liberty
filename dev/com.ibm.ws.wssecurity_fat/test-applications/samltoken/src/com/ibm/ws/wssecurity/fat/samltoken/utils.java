package com.ibm.ws.wssecurity.fat.samltoken;
import java.util.Iterator;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class utils {

    public static Boolean isSAMLAssertionInHeader(SOAPMessage request) {

        Boolean samlAssertionFound = false ; 
        try {
            for (Iterator<Node> i = request.getSOAPHeader().getChildElements() ; i.hasNext();) {
                Node n = i.next();
                System.out.println("this node: " + n.getNodeName() ) ;
                if (n.getNodeName().equals("wsse:Security")) {
                    NodeList nl = n.getChildNodes();
                    for (int j=0 ; j < nl.getLength(); j++) {
                        Node cn = nl.item(j) ;
                        String nodeName = cn.getNodeName() ;
                        System.out.println("child node: " + nodeName) ;
                        if (nodeName.contains("saml") && nodeName.contains(":Assertion")) {  // match saml2:Assertion or saml:Assertion
                            samlAssertionFound = true ;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return samlAssertionFound ; 
    }
}