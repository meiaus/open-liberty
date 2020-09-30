package com.ibm.ws.wssecurity.fat.cxf.wss11sig;

//import java.io.File;

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
import componenttest.annotation.ExpectedFFDC;

public class CxfWss11SigTests extends CommonTests {

    static private final Class<?> thisClass = CxfWss11SigTests.class;
    static private UpdateWSDLPortNum newWsdl = null;
    static private String newClientWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.wss11sig";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, false,
                    "/wss11sigclient/CxfWss11SigSvcClient");
    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in both the request and response.
     * Signature confirmation is enabled on both client and server side
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientBasicSigConfirm() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientBasicSigConfirm",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "WSS11SigService1",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "WSS11Sig1",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is Wss11SigWebSvc1 Web Service.",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is NOT signed in either the request or response.
     * Signature confirmation is enabled on both client and server
     * side. The response should contain one SignatureConfirmation
     * element with no value.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientBasicSigConfirmNoSig() throws Exception {

        genericTest(
                    // test name for logging
                    "testCXFClientBasicSigConfirmNoSig",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "WSS11SigService2",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "WSS11Sig2",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is Wss11SigWebSvc2 Web Service.",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and response.
     * Signature confirmation is enabled in just the client policy.
     * The response does not contain a SignatureConfirmation but
     * the client is expecting it to be there.
     * This is a negative scenario.
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientBasicSigClConfSrvNoConf() throws Exception {

        String thisMethod = "testCXFClientBasicSigClConfSrvNoConf";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "WSS11Signature_reqSigMismatch.wsdl",
                                         defaultClientWsdlLoc + "WSS11Signature_reqSigMismatchUpdated.wsdl");
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
                    "WSS11SigService4",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "WSS11Sig4",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Check Signature confirmation: the stored signature values list is not empty",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and response.
     * Signature confirmation is enabled in just the server policy.
     * The response contains a SignatureConfirmation but when
     * the client is NOT expecting it to be there. It should be ignored.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientBasicSigClNoConfSrvConf() throws Exception {

        String thisMethod = "testCXFClientBasicSigClNoConfSrvConf";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "WSS11Signature_reqSigMismatch.wsdl",
                                         defaultClientWsdlLoc + "WSS11Signature_reqSigMismatchUpdated.wsdl");
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
                    "WSS11SigService5",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "WSS11Sig5",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is Wss11SigWebSvc5 Web Service.",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request, but not the response.
     * Signature confirmation is enabled in the client and server policy.
     * SignatureConfirmation is included in EncryptedElements in the
     * server policy only.
     * The response does not sign the body as the client expects.
     * This is a negative scenario.
     *
     */
    @Test
    @ExpectedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientBasicEncryptedElementMisMatch() throws Exception {

        String thisMethod = "testCXFClientBasicEncryptedElement";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "WSS11Signature_Elements.wsdl",
                                         defaultClientWsdlLoc + "WSS11Signature_ElementsUpdated.wsdl");
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
                    "WSS11SigService6",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "WSS11Sig6",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Body not SIGNED",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request, but not the response.
     * Signature confirmation is enabled in the client and server policy.
     * SignatureConfirmation is included in EncryptedElements in the
     * server policy only.
     * Server policy also uses X509 IncludeToken set to Never and
     * RequireKeyIdentifierReference in the InitiatorToken.
     * The response does not sign the body as the client expects.
     * This is a positive scenario.
     *
     */
    @Test
    public void testCXFClientBasicEncryptedElement() throws Exception {

        String thisMethod = "testCXFClientBasicEncryptedElement";
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_enchdr.xml");
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
                    "WSS11SigService6a",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "WSS11Sig6a",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is Wss11SigWebSvc6a Web Service.",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_orig.xml");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request, but not the response.
     * Signature confirmation is enabled in the client and server policy.
     * SignatureConfirmation is included in SignedElements in the server
     * policy only.
     * The body in the response is not signed as the client expects.
     * This is a negative scenario
     *
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCXFClientBasicSigSignedElementMisMatch() throws Exception {

        String thisMethod = "testCXFClientBasicSigSignedElement";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "WSS11Signature_Elements.wsdl",
                                         defaultClientWsdlLoc + "WSS11Signature_ElementsUpdated.wsdl");
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
                    "WSS11SigService7",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "WSS11Sig7",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Body not SIGNED",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is signed in the request and the response.
     * Signature confirmation is enabled in the client and server
     * policy. SignatureConfirmation is included in SignedElements
     * in both the client and server policy. The response is as
     * the client expects.
     * This is a positive scenario.
     */

    @Test
    public void testCXFClientBasicSigSignedElement() throws Exception {

        String thisMethod = "testCXFClientBasicSigSignedElement";
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
                    "WSS11SigService7a",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "WSS11Sig7a",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is Wss11SigWebSvc7a Web Service.",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     *
     * A CXF service client invokes a simple jax-ws CXF web service.
     * The SOAP Body is NOT signed in either the request or the response.
     * Signature confirmation is enabled in the client only. The
     * response is missing the signature confirmation that it expects.
     * This is a negative scenario.
     */
    @Test
    public void testCXFClientBasicSigClNoSignConfSrvNoSignNoConf() throws Exception {

        String thisMethod = "testCXFClientBasicSigClNoSignConfSrvNoSignNoConf";
        printMethodName(thisMethod, "Start Prep for " + thisMethod);
        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "WSS11Signature_sigConfMissingInServer.wsdl",
                                         defaultClientWsdlLoc + "WSS11Signature_sigConfMissingInServerUpdated.wsdl");
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
                    "WSS11SigService8",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "WSS11Sig8",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Wss11: Signature Confirmation policy validation failed",
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
