package com.ibm.ws.wssecurity.fat.cxf.usernametoken;

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
import componenttest.vulnerability.LeakedPasswordChecker;

public class CxfUNTBasicTests {

    private static LibertyServer server = LibertyServerFactory.getLibertyServer("com.ibm.ws.wssecurity_fat");
    private final Class<?> thisClass = CxfUNTBasicTests.class;

    private static String untClientUrl = "";

    private static String httpPortNumber = "";

    private static LeakedPasswordChecker leakedPasswordChecker = new LeakedPasswordChecker(server);

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

        untClientUrl = "http://localhost:" + httpPortNumber +
                       "/untclient/CxfUntSvcClient";

        return;

    }

    @Test
    public void testAlwaysRun() throws Exception {
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory();

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        Log.info(thisClass, "testAlwaysRun", "current heap size: " + heapSize);
        Log.info(thisClass, "testAlwaysRun", "maximum heap size: " + heapMaxSize);
        Log.info(thisClass, "testAlwaysRun", "free memory  size: " + heapFreeSize);

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, which invokes
     * a jax-ws cxf unt web service.
     * 
     */

    @Test
    public void testUntCxfSvcClient() throws Exception {

        String thisMethod = "testUntCxfSvcClient";
        String expectedResponse = "This is WSSECFVT CXF Web Service.";
        String respReceived = null;

        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            request = new GetMethodWebRequest(untClientUrl);

            request.setParameter("httpDefaultPort", httpPortNumber);
            request.setParameter("untClient", "cxf");

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            respReceived = response.getText();
            Log.info(thisClass, thisMethod, "Response from CXF UNT Service client: " + respReceived);

        } catch (Exception e) {

            Log.info(thisClass, thisMethod, "Exception occurred:");
            System.err.println("Exception: " + e);
            throw e;
        }

        assertTrue("The testUntSvcClient test failed",
                   respReceived.contains(expectedResponse));

        leakedPasswordChecker.checkForPasswordInTrace("security</wsse:Password>");

        return;
    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, which invokes
     * a jax-ws cxf unt web service.
     * 
     */

    @Test
    public void testUntWssecSvcClient() throws Exception {

        String thisMethod = "testUntWssecSvcClient";
        String expectedResponse = "This is WSSECFVT CXF Web Service.";
        String respReceived = null;

        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            request = new GetMethodWebRequest(untClientUrl);

            request.setParameter("httpDefaultPort", httpPortNumber);
            request.setParameter("untClient", "ibm");

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            respReceived = response.getText();
            Log.info(thisClass, thisMethod, "Response from IBM UNT Service client: " + respReceived);
        } catch (Exception e) {

            Log.info(thisClass, thisMethod, "Exception occurred:");
            Log.error(thisClass, thisMethod, e, "Exception: ");
            throw e;
        }

        assertTrue("The testUntWssecSvcClient test failed",
                   respReceived.contains(expectedResponse));

        return;

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with an invalid password
     * in username token. The client request is expected to be rejected with
     * an appropriate exception.
     * 
     */

    @Test
    public void testUntCxfBadPswd() {

        String thisMethod = "testUntCxfBadPswd";
        String expectedResponse = "The security token could not be authenticated or authorized";
        String respReceived = null;

        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            request = new GetMethodWebRequest(untClientUrl);

            request.setParameter("httpDefaultPort", httpPortNumber);
            request.setParameter("untClient", "cxfbadpswd");

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            respReceived = response.getText();
            Log.info(thisClass, thisMethod, "Response from CXF UNT Service client: " + respReceived);

        } catch (Exception ex) {

            respReceived = ex.getMessage();
            Log.info(thisClass, thisMethod, "Exception occurred:" + respReceived);
        }

        assertTrue("The testUntCxfBadPswd test failed",
                   respReceived.contains(expectedResponse));

        leakedPasswordChecker.checkForPasswordInTrace("badpswd123</wsse:Password>");

        return;

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with an invalid username
     * in username token. The client request is expected to be rejected with
     * an appropriate exception.
     * 
     */

    @Test
    public void testUntCxfBadUserID() {

        String thisMethod = "testUntCxfBaduserID";
        String expectedResponse = "The security token could not be authenticated or authorized";
        String respReceived = null;

        try {

            WebRequest request = null;
            WebResponse response = null;

            // Create the conversation object which will maintain state for us
            WebConversation wc = new WebConversation();

            // Invoke the service client - servlet
            request = new GetMethodWebRequest(untClientUrl);

            request.setParameter("httpDefaultPort", httpPortNumber);
            request.setParameter("untClient", "cxfbaduser");

            // Invoke the client
            response = wc.getResponse(request);

            // Read the response page from client jsp
            respReceived = response.getText();
            Log.info(thisClass, thisMethod, "Response from CXF UNT Service client: " + respReceived);

        } catch (Exception ex) {

            respReceived = ex.getMessage();
            Log.info(thisClass, thisMethod, "Exception occurred:" + respReceived);
        }

        assertTrue("The testUntCxfBadUserID test failed",
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
