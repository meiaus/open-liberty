package com.ibm.ws.wssecurity.fat.cxf.x509token;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;

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

import org.junit.Test;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.wssecurity.fat.utils.common.CommonTests;
import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

import componenttest.annotation.AllowedFFDC;

public class CxfX509EncTests extends CommonTests {

    static private final Class<?> thisClass = CxfX509EncTests.class;
    static private UpdateWSDLPortNum newWsdl = null;
    static private String newClientWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.x509enc";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, false,
                    "/x509encclient/CxfX509EncSvcClient");
    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, username token is encrypted with Basic128Rsa15 algorithm suite.
     * There is no ws-security applied to the response message.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientEncryptUsernameToken() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientEncryptUsernameToken",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService1",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc1",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc1 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, username token is encrypted with Basic128Rsa15 algorithm suite.
     * There is no ws-security applied to the response message.
     * Uses XPath to define encryption
     * This is a positive scenario.
     *
     */
    //@Test
    public void testCXFClientEncryptUsernameTokenUsingXPath() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientEncryptUsernameTokenUsingXPath",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService1X",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc1X",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc1X Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, a plain username token is sent, but the Web service provider expects
     * the username token to be encrypted. The request is expected to be rejected with an appropriate
     * exception. This is a negative scenario.
     *
     */
    @Test
    public void testCXFClientUntNotEncrypted() throws Exception {

        String thisMethod = "testCXFClientUntNotEncrypted";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlEnc.wsdl",
                                         defaultClientWsdlLoc + "X509XmlEncUpdated.wsdl");
        Log.info(thisClass, thisMethod, "Using " + newClientWsdl);
        printMethodName(thisMethod, "End Prep for " + thisMethod);
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
                    "X509XmlEncService1",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Enc1",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "EncryptedSupportingTokens: The received token does not match the encrypted supporting token requirement",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, the SOAP body is signed and encrypted and the
     * username token is encrypted.
     * In the response message, the SOAP body is signed and encrypted.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientEncryptBodyAndUnt() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientEncryptBodyAndUnt",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService3",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc3",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc3 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, username token, with Nonce and Created, is
     * is encrypted.
     * There is no WS-Security security applied to the response message.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientEncryptUntNonce() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientEncryptUntNonce",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService4",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc4",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc4 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request and response messages, the SOAP Body is signed and the
     * <Signature> element is encrypted.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCxfClientEncryptSignature() throws Exception {

        genericTest(
                    // test name for logging
                    "testCxfClientEncryptSignature",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService5",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc5",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc5 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, username token is encrypted.
     * In the response message, SOAP body is encrypted.
     * In the provider's WSDL file, this scenario uses defferent WS-Security policy
     * for the request and response messages.
     * This is a positive scenario.
     *
     */
    //@Test
    public void testCXFClientEncryptUntBody() throws Exception {

        String thisMethod = "testCXFClientEncryptUntBody";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlEnc.wsdl",
                                         defaultClientWsdlLoc + "X509XmlEncUpdated.wsdl");
        Log.info(thisClass, thisMethod, "Using " + newClientWsdl);
        printMethodName(thisMethod, "End Prep for " + thisMethod);
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
                    "X509XmlEncService6",
                    // wsdl that the svc client code should use
                    //newClientWsdl,
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc6",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc6 Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, SOAP Body is encrypted, using Algorithm suite TripleDesRsa15, but
     * the Web Service provider requires the SOAP Body to be encrypted using Algorithm suite Basic128.
     * The client request is expected to be rejected with an appropriate exception.
     * This is a negative scenario.
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientWrongEncKeyAlgorithm() throws Exception {

        String thisMethod = "testCXFClientWrongEncKeyAlgorithm";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlEnc2.wsdl",
                                         defaultClientWsdlLoc + "X509XmlEnc2Updated.wsdl");
        Log.info(thisClass, thisMethod, "Using " + newClientWsdl);
        printMethodName(thisMethod, "End Prep for " + thisMethod);
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
                    "X509XmlEncService7",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Enc7",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "AsymmetricBinding: The Key transport method does not match the requirement",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, SOAP Body is encrypted, using Algorithm suite TripleDesRsa15, but
     * the Web Service provider requires the SOAP Body to be encrypted using Algorithm suite Basic128Rsa15.
     * The client request is expected to be rejected with an appropriate exception.
     * This is a negative scenario.
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientWrongDataEncAlgorithm() throws Exception {

        String thisMethod = "testCXFClientWrongDataEncAlgorithm";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlEnc2.wsdl",
                                         defaultClientWsdlLoc + "X509XmlEnc2Updated.wsdl");
        Log.info(thisClass, thisMethod, "Using " + newClientWsdl);
        printMethodName(thisMethod, "End Prep for " + thisMethod);
        genericTest(
                    // test name for logging
                    "testCXFClientWrongDataEncAlgorithm",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService8",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Enc8",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "AsymmetricBinding: The encryption algorithm does not match the requirement",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, SOAP Body is encrypted, using Alice's public key.
     * The Web Service provider does not have access to Alice's private key to decrypt the message.
     * The client request is expected to be rejected with an appropriate exception.
     * This is a negative scenario.
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientWrongEncryptionKey() throws Exception {

        String thisMethod = "testCXFClientWrongEncryptionKey";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_wrongEnc.xml");
        printMethodName(thisMethod, "End Prep for " + thisMethod);

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
                    "X509XmlEncService9",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc9",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "The signature or decryption was invalid",
                    // msg to issue if do NOT get the expected result
                    "The test expected an exception from the server.");

        printMethodName(thisMethod, "Start Cleanup for " + thisMethod);
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_orig.xml");
        printMethodName(thisMethod, "End Cleanup for " + thisMethod);

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * In the request message, SOAP Body is signed and encrypted, with Encryption done first before signing.
     * The WS-Security policy on the provider side is configured with the assertion <sp:EncryptBeforeSigning/>
     * This is a negative scenario.
     *
     */
    @Test
    public void testCXFClientEncryptionBeforeSign() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientEncryptionBeforeSign",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlEncService10",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Enc10",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is X509EncWebSvc10 Web Service",
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

    @Override
    @After
    public void endTest() throws Exception {
        try {
            if (newWsdl != null) {
                //newWsdl.removeWSDLFile();
                newWsdl = null;
                newClientWsdl = null;
            }
            restoreServer();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
