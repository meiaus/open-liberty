package com.ibm.ws.wssecurity.fat.cxf.x509token;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.ws.wssecurity.fat.utils.common.CommonTests;

import componenttest.annotation.AllowedFFDC;

public class CxfX509CrlTests extends CommonTests {

    //static private final Class<?> thisClass = CxfX509CrlTests.class;
//    static private UpdateWSDLPortNum newWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.x509crl";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, false,
                    "/x509crlclient/CxfX509CrlSvcClient");
    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and response.
     * The server setup in server.xml specifies a crl list and the
     * cert used in this test is NOT in that list, so the request
     * should succeed.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientCRLPNotInList() throws Exception {

        String thisMethod = "testCXFClientCRLPNotInList";
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_certp.xml");
        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509CrlNotInListService",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "X509CrlNotInList",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509CrlNotInListService Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and response.
     * The server setup in server.xml specifies a crl list and the
     * cert used in this test IS in that list, so the request
     * should be rejected.
     * This is a negative scenario.
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientCRLNInList() throws Exception {

        String thisMethod = "testCXFClientCRLNInList";
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_certn.xml");
        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509CrlInListService",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "X509CrlInList",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "has been revoked",
                    // additional string to check for - chc SUN doesn't say what alias has been revoked
                    //"myx509certN",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

//    @After
//    public void testTearDown() throws Exception {
//        try {
//            if (newWsdl != null) {
//                //newWsdl.removeWSDLFile();
//                newWsdl = null;
//                //newClientWsdl = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//    }
}
