package com.ibm.ws.wssecurity.fat.cxf.x509token;

import java.io.File;

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
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.wssecurity.fat.utils.common.CommonTests;
import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

import componenttest.annotation.AllowedFFDC;

public class CxfX509SigTests extends CommonTests {

    static private final Class<?> thisClass = CxfX509SigTests.class;
    static private UpdateWSDLPortNum newWsdl = null;
    static private String newClientWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.x509sig";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, false,
                    "/x509sigclient/CxfX509SigSvcClient");
    }

    @Test
    public void testCxfClientSignSoapBody() throws Exception {

        genericTest(
                    // test name for logging
                    "testCxfClientSignSoapBody",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is WSSECFVT CXF Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfBodyNotSigned() throws Exception {

        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlSigNoClientSig.wsdl",
                                         defaultClientWsdlLoc + "X509XmlSigNoClientSigUpdated.wsdl");
        Log.info(thisClass, "testCxfBodyNotSigned", "Using " + newClientWsdl);
        genericTest(
                    // test name for logging
                    "testCxfBodyNotSigned",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Body not SIGNED",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfClientTimestampMissing() throws Exception {

        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlSigClientTimestamps.wsdl",
                                         defaultClientWsdlLoc + "X509XmlSigClientTimestampsUpdated.wsdl");
        Log.info(thisClass, "testCxfClientTimestampMissing", "Using " + newClientWsdl);
        genericTest(
                    // test name for logging
                    "testCxfClientTimestampMissing",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService3",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Sig3",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Received Timestamp does not match the requirements",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfClientPassTimestamp() throws Exception {

        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlSigClientTimestamps.wsdl",
                                         defaultClientWsdlLoc + "X509XmlSigClientTimestampsUpdated.wsdl");
        Log.info(thisClass, "testCxfClientPassTimestamp", "Using " + newClientWsdl);
        genericTest(
                    // test name for logging
                    "testCxfClientPassTimestamp",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Received Timestamp does not match the requirements",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    public void testCxfClientSignBodyUNTAndTs() throws Exception {

        genericTest(
                    // test name for logging
                    "testCxfClientSignBodyUNTAndTs",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService3",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Sig3",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Response: This is WSSECFVT CXF Web Service",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    public void testCxfClientSigBadUrl() throws Exception {

        newClientWsdl = updateClientWsdl(defaultClientWsdlLoc + "X509XmlSigBadUrl.wsdl",
                                         defaultClientWsdlLoc + "X509XmlSigBadUrlUpdated.wsdl");
        Log.info(thisClass, "testCxfClientSigBadUrl", "Using " + newClientWsdl);

        genericTest(
                    // test name for logging
                    "testCxfClientSigBadUrl",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    newClientWsdl,
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "Could not send Message",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfClientSignWithExpKey() throws Exception {

        // use server config with expired cert
        //reconfigServer(server.getInstallRoot().replace('\\', '/') + "/usr/servers/" + serverName + "/server_expcert.xml") ;
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_expcert.xml");

        genericTest(
                    // test name for logging
                    "testCxfClientSignWithExpKey",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService4",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Sig4",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "cannot create instance",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfClientBadClKeyStorePswd() throws Exception {

        // use server config with bad client pw
        //reconfigServer(server.getInstallRoot().replace('\\', '/') + "/usr/servers/" + serverName + "/server_badclpwd.xml") ;
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_badclpwd.xml");

        genericTest(
                    // test name for logging
                    "testCxfClientBadClKeyStorePswd",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "cannot create instance",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

        // restore original server config
        //reconfigServer(server.getInstallRoot().replace('\\', '/') + "/usr/servers/" + serverName + "/server_orig.xml") ;
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_orig.xml");

    }

    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfClientBadSrvKeyStorePswd() throws Exception {

        // use server config with bad server pw

        //reconfigServer(server.getInstallRoot().replace('\\', '/') + "/usr/servers/" + serverName + "/server_badsvrpwd.xml") ;
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_badsvrpwd.xml");

        genericTest(
                    // test name for logging
                    "testCxfClientBadSrvKeyStorePswd",
                    // Svc Client Url that generic test code should use
                    clientHttpUrl,
                    // Port that svc client code should use
                    "",
                    // user that svc client code should use
                    "user1",
                    // pw that svc client code should use
                    "security",
                    // wsdl sevice that svc client code should use
                    "X509XmlSigService1",
                    // wsdl that the svc client code should use
                    "",
                    // wsdl port that svc client code should use
                    "UrnX509Sig",
                    // msg to send from svc client to server
                    "",
                    // expected response from server
                    "cannot create instance",
                    // msg to issue if do NOT get the expected result
                    "The test expected a succesful message from the server.");

        // restore original server config
        //reconfigServer(server.getInstallRoot().replace('\\', '/') + "/usr/servers/" + serverName + "/server_orig.xml") ;
        reconfigServer(System.getProperty("user.dir") + File.separator + server.getPathToAutoFVTNamedServer() + "server_orig.xml");

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
