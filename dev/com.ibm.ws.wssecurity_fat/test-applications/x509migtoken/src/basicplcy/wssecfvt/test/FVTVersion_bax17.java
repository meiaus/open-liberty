/*
 * IBM Confidential OCO Source Material
 * 5639-D57, 5630-A36, 5630-A37, 5724-D18, 5724-J08 (C) COPYRIGHT International Business Machines Corp. 2006
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 *
 * Date      Author       Description
 * --------  -----------  ----------------------
 * 06/12/06  Todd Roling  New File
 * 08/30/06  SLL          Used annotations
 */

package basicplcy.wssecfvt.test;

import javax.xml.ws.WebServiceProvider;

import java.io.*;
import javax.xml.ws.*;
import javax.xml.soap.*;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

@WebServiceProvider(targetNamespace="http://x509mig.libertyfat.test/contract",
                    serviceName="FatBAX17Service", portName="UrnX509Token17",
                    wsdlLocation="WEB-INF/x509migtoken.wsdl")

@ServiceMode(value = Service.Mode.MESSAGE)

/**
 * Server side implementation of Web Services Security tests.
 * Contains invoke method called by clients which returns
 * WssecfvtConst.TEST_STRING_OUT.
 * @author Todd Roling
 */
public class FVTVersion_bax17 implements javax.xml.ws.Provider<SOAPMessage> {

    /* (non-Javadoc)
     * @see javax.xml.ws.Provider#invoke(java.lang.Object)
     */
    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        SOAPMessage response = null;
        try {
            System.out.println("Incoming Client Request as a SOAPMessage(mig bax17)");
            //SOAPBody sb = request.getSOAPBody();
            //System.out.println("Incoming SOAPBody: " + sb);
            StringReader respMsg = new StringReader(
                               "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                               "<SOAP-ENV:Body><provider><message>LIBERTYFAT X509 bax17</message></provider></SOAP-ENV:Body>" +
                               "</SOAP-ENV:Envelope>"  );
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
