package com.ibm.ws.wssecurity.fat.cxf.nowssec;

/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2011
 *
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service.Mode;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.wssecurity.fat.utils.common.SharedTools;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;

//import java.io.StringReader;
//import java.net.URL;
//
//import javax.xml.namespace.QName;
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.SOAPMessage;
//import javax.xml.transform.Source;
//import javax.xml.transform.stream.StreamSource;
//import javax.xml.ws.Dispatch;
//import javax.xml.ws.Service.Mode;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.ibm.websphere.simplicity.log.Log;
//import com.ibm.ws.wssecurity.fat.utils.common.SharedTools;
//import com.meterware.httpunit.GetMethodWebRequest;
//import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebRequest;
//import com.meterware.httpunit.WebResponse;
//import componenttest.topology.impl.LibertyServer;
//import componenttest.topology.impl.LibertyServerFactory;

import fats.cxf.basic.wssec.SOAPService1;

public class CxfNoWssecTests {

    private static LibertyServer server = LibertyServerFactory.getLibertyServer("com.ibm.ws.wssecurity_fat");
    private final Class<?> thisClass = CxfNoWssecTests.class;

    private static final String SERVICE_NS = "http://wssec.basic.cxf.fats";

    private static final String wsdlLocation = "http://localhost:" +
                                               server.getHttpDefaultPort() +
                                               "/nowssec/SOAPService1?wsdl";

    private static String serviceClientUrl = "";
    private static String httpPortNumber = "";
    private static final StringReader reqMsg = new StringReader("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body xmlns=\"http://wssec.basic.cxf.fats/types\"><invoke>WSSECFVT Version: 2.0</invoke></soapenv:Body></soapenv:Envelope>");

    /**
     * Sets up any configuration required for running the OAuth tests.
     * Currently, it just starts the server, which should start the
     * applications in dropins.
     */
    @BeforeClass
    public static void setUp() throws Exception {

        server.startServer();// check CWWKS0008I: The security service is ready.
        SharedTools.waitForMessageInLog(server, "CWWKS0008I");
        httpPortNumber = "" + server.getHttpDefaultPort();

        server.waitForStringInLog("port " + httpPortNumber);

        serviceClientUrl = "http://localhost:" + httpPortNumber +
                           "/cxfclient/CxfNoWssecSvcClient";

        return;

    }

    /**
     * TestDescription:
     *
     * This test invokes a simple jax-ws cxf web service.
     * There is no ws-security policy included in the request message.
     *
     */

    @Test
    public void testCxfClientNoWssec() throws Exception {

        String thisMethod = "testCxfClientNoWssec";
        String expectedResponse = "This is WSSECFVT CXF Web Service.";

        URL wsdlURL = new URL(wsdlLocation);

        QName serviceName1 = new QName(SERVICE_NS, "SOAPService1");
        QName portName1 = new QName(SERVICE_NS, "SoapPort1");

        SOAPService1 service1 = new SOAPService1(wsdlURL, serviceName1);

        Source src = new StreamSource(reqMsg);
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage soapReq1 = factory.createMessage();
        soapReq1.getSOAPPart().setContent(src);
        soapReq1.saveChanges();

        Dispatch<SOAPMessage> dispSOAPMsg = service1.createDispatch(portName1,
                                                                    SOAPMessage.class, Mode.MESSAGE);

        Log.info(thisClass, thisMethod, "Invoking server through Dispatch interface using SOAPMessage");

        SOAPMessage soapResp = dispSOAPMsg.invoke(soapReq1);
        Log.info(thisClass, thisMethod, "CXF Client Response SOAP Body contents: " + soapResp.getSOAPBody().getTextContent());

        String respText = soapResp.getSOAPBody().getTextContent();

        assertTrue("The testCxfClientToCxfWebSvc test failed",
                   respText.contains(expectedResponse));

        return;

    }

    /**
     * TestDescription:
     *
     * This test invokes a jax-ws cxf service client, which invokes
     * a simple jax-ws cxf we b service.
     *
     */

    @Test
    public void testNoWssecCxfSvcClient() throws Exception {

        String thisMethod = "testNoWssecCxfSvcClient";
        String expectedResponse = "This is WSSECFVT CXF Web Service.";
        String respReceived = null;

        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            request = new GetMethodWebRequest(serviceClientUrl);

            request.setParameter("httpDefaultPort", httpPortNumber);
            // request.setParameter("targetWSName", "NoWssecWebSvc");

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            respReceived = response.getText();
            Log.info(thisClass, thisMethod, "Response from CXF Service client: " + respReceived);

        } catch (Exception e) {

            Log.info(thisClass, thisMethod, "Exception occurred:");
            System.err.println("Exception: " + e);
            throw e;
        }

        assertTrue("The testNoWssecCxfSvcClient test failed",
                   respReceived.contains(expectedResponse));

        return;

    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (server != null && server.isStarted()) {
            server.stopServer();
        }
    }

}
