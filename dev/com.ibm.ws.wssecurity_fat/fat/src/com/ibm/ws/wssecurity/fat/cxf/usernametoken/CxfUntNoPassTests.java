package com.ibm.ws.wssecurity.fat.cxf.usernametoken;

/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2012
 *
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */
import static org.junit.Assert.assertTrue;

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

public class CxfUntNoPassTests {
    private static String serverName = "com.ibm.ws.wssecurity_fat";
    private static LibertyServer server = LibertyServerFactory.getLibertyServer(serverName);

    static private final Class<?> thisClass = CxfUntNoPassTests.class;

    private static String portNumber = "";
    private static String portNumberSecure = "";
    private static String nopassClientUrl = "";
    private static String nopassSSLClientUrl = "";

    static String hostName = "localhost";

    final static String badUsernameToken = "The security token could not be authenticated or authorized";
    final static String msgExpires = "The message has expired";
    final static String badHttpsToken = "HttpsToken could not be asserted";
    final static String badHttpsClientCert = "Could not send Message.";

    /**
     * Sets up any configuration required for running the OAuth tests.
     * Currently, it just starts the server, which should start the applications
     * in dropins.
     */
    @BeforeClass
    public static void setUp() throws Exception {

        String thisMethod = "setup";

        server.startServer();// check CWWKS0008I: The security service is ready.
        SharedTools.waitForMessageInLog(server, "CWWKS0008I");
        portNumber = "" + server.getHttpDefaultPort();
        portNumberSecure = "" + server.getHttpDefaultSecurePort();

        nopassClientUrl = "http://localhost:" + portNumber
                          + "/nopassclient/CxfNoPassSvcClient";

        return;

    }

    /**
     * TestDescription:
     * 
     * This test invokes a simple jax-ws cxf web service.
     * 
     */

    @Test
    public void testCxfNoPassService() throws Exception {
        String thisMethod = "testCxfNoPassService";

        try {
            testRoutine(
                        thisMethod, //String thisMethod,     
                        "positive", // Positive, positive-1, negative or negative-1... etc 
                        portNumber, //String portNumber,     
                        "", //String portNumberSecure
                        "NoPassService", //String strServiceName, 
                        "UrnNoPassUNT", //String strServicePort
                        "user1");
        } catch (Exception e) {
            throw e;
        }

        return;
    }

    /**
     * TestDescription:
     * 
     * This test invokes a simple jax-ws cxf web service.
     * 
     */

    @Test
    public void testCxfNoPassServiceBadUser() throws Exception {
        String thisMethod = "testCxfNoPassServiceBadUser";

        try {
            testRoutine(
                        thisMethod, //String thisMethod,     
                        "negative", // Positive, positive-1, negative or negative-1... etc 
                        portNumber, //String portNumber,     
                        "", //String portNumberSecure
                        "NoPassService", //String strServiceName, 
                        "UrnNoPassUNT", //String strServicePort
                        "noSuchUser");
        } catch (Exception e) {
            throw e;
        }

        return;
    }

    /**
     * TestDescription:
     * 
     * This test invokes a simple jax-ws cxf web service.
     * 
     */
    protected void testRoutine(
                               String thisMethod,
                               String testMode, // Positive, positive-1, negative or negative-1... etc 
                               String portNumber,
                               String portNumberSecure,
                               String strServiceName,
                               String strServicePort,
                               String strId) throws Exception {
        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            Log.info(thisClass, thisMethod, "Invoking: " + nopassClientUrl);
            request = new GetMethodWebRequest(nopassClientUrl);

            request.setParameter("serverName", serverName);
            request.setParameter("thisMethod", thisMethod);
            request.setParameter("testMode", testMode);
            request.setParameter("httpDefaultPort", portNumber);
            request.setParameter("httpSecureDefaultPort", portNumberSecure);
            request.setParameter("serviceName", strServiceName);
            request.setParameter("servicePort", strServicePort);
            request.setParameter("id", strId);

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            String respReceived = response.getText();
            Log.info(thisClass, thisMethod, "'" + respReceived + "'");
            assertTrue("Failed to get back the expected text. But :" + respReceived, respReceived.contains("<p>pass:true:"));
            assertTrue("Hmm... Strange! wrong testMethod back. But :" + respReceived, respReceived.contains(">m:" + thisMethod + "<"));
        } catch (Exception e) {
            Log.info(thisClass, thisMethod, "Exception occurred:");
            System.err.println("Exception: " + e);
            throw e;
        }

        return;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        try {
            printMethodName("tearDown");
            if (server != null && server.isStarted()) {
                server.stopServer();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void printMethodName(String strMethod) {
        Log.info(thisClass, strMethod, "*****************************"
                                       + strMethod);
        System.err.println("*****************************" + strMethod);
    }
}
