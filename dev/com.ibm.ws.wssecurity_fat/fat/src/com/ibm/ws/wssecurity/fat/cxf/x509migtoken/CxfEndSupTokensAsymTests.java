package com.ibm.ws.wssecurity.fat.cxf.x509migtoken;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.wssecurity.fat.utils.common.CommonTests;
import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

//import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

public class CxfEndSupTokensAsymTests extends CommonTests {

    private final static Class<?> thisClass = CxfEndSupTokensAsymTests.class;
    static private UpdateWSDLPortNum newWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.endsuptokens";
    static private String newClientWsdl = null;
//    static private boolean bConfigServer = false;

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, "server_asym.xml", true,
                    "/endsuptokensclient/CxfEndSupTokensSvcClient");
    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed and encrypted in the request and response.
     * X509 is specified in the EndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFEndSupTokens0() throws Exception {

        String thisMethod = "testCXFEndSupTokens0";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService0",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509EndorsingPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc0 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed and encrypted in the request and response.
     * X509 is specified in the EndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    //@Test
    public void testCXFEndSupTokens0AddEncrypted() throws Exception {

        String thisMethod = "testCXFEndSupTokens0";

        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "EndSupTokens/EndSupTokens0AddEncrypted.wsdl", defaultClientWsdlLoc
                                                                                                               + "EndSupTokens/EndSupTokens0AddEncryptedUpdated.wsdl");
        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService0",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    //"",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509EndorsingPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc0 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and response.
     * X509 is specified in the EndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFEndSupTokens0Body() throws Exception {

        String thisMethod = "testCXFEndSupTokens0Body";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService0Body",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509EndorsingBodyPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc0Body Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body and Element is signed in the request and response.
     * X509 is specified in the EndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFEndSupTokens0BodyElement() throws Exception {

        String thisMethod = "testCXFEndSupTokens0BodyElement";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService0BodyElement",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509EndorsingBodySignElementPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc0BodyElement Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * X509 and KeyValue is specified in the EndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    // Not supporting KeyValue at this time - this test doesn't work
    //@Test
    public void testCXFEndSupTokens0Key() throws Exception {

        String thisMethod = "testCXFEndSupTokens0Key";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService0Key",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509EndorsingKeyPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc0Key Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed and encrypted in the request and response.
     * X509 is specified in the SignedEndorsingSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFEndSupTokens1() throws Exception {

        String thisMethod = "testCXFEndSupTokens1";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpsUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService1",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509SignedEndorsingPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc1 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed and encrypted in the request and response.
     * X509 is specified in the SignedEndorsingEncryptedSupportingToken
     * in both the client and server policies.
     * This is a positive scenario.
     *
     */
    //@Test
    public void testCXFEndSupTokens3() throws Exception {

        String thisMethod = "testCXFEndSupTokens3";

        genericTest(
                    // test name for logging
                    thisMethod,
                    // Svc Client Url that generic test code should use
                    clientHttpsUrl,
                    // Port that svc client code should use
                    portNumberSecure,
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "EndSupTokensService3",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "EndSupTokensX509SignedEndorsingEncryptedPort",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is EndSupTokensWebSvc3 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    public String updateClientWsdl(String origClientWsdl,
                                   String updatedClientWsdl) {

        try {
            if (portNumber.equals(defaultHttpPort)) {
                Log.info(thisClass, "updateClientWsdl", "Test should use " + origClientWsdl + " as the client WSDL");
                return origClientWsdl;
            } else { // port number needs to be updated
                newWsdl = new UpdateWSDLPortNum(origClientWsdl, updatedClientWsdl);
                newWsdl.updatePortNum(defaultHttpPort, portNumber);
                Log.info(thisClass, "updateClientWsdl", "Test should use " + updatedClientWsdl + " as the client WSDL");

                return updatedClientWsdl;
            }
        } catch (Exception ex) {
            Log.info(thisClass, "updateClientWsdl",
                     "Failed updating the client wsdl try using the original");
            newWsdl = null;
            return origClientWsdl;
        }
    }

    @After
    public void endTest() throws Exception {
        try {
            if (newWsdl != null) {
                //newWsdl.removeWSDLFile();
                newWsdl = null;
                newClientWsdl = null;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
