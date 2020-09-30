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

import org.junit.Test;

import componenttest.annotation.AllowedFFDC;
import componenttest.annotation.ExpectedFFDC;

public class CxfSSLUNTNonceTests extends SSLTestCommon {

    static private final Class<?> thisClass = CxfSSLUNTNonceTests.class;

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding and Nonce are specified in the wsdl. A valid msg is sent
     * (nonce generated) The request should be successful.
     * 
     */
    @Test
    public void testCxfUntNonceOnlySSL() throws Exception {

        genericTest("testCxfUntNonceOnlySSL", untSSLClientUrl,
                    portNumberSecure, "user1", "security", "FVTVersionBA5Service",
                    "UrnBasicPlcyBA5", "", "",
                    "Response: WSSECFVT FVTVersion_ba05",
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding, Nonce and Created are specified in the wsdl. A valid
     * msg is sent (nonce/created generated) The request should be successful.
     * 
     */
    @Test
    public void testCxfUntNonceAndCreatedSSL() throws Exception {

        genericTest("testCxfUntNonceAndCreatedSSL", untSSLClientUrl,
                    portNumberSecure, "user1", "security", "FVTVersionBA4Service",
                    "UrnBasicPlcyBA4", "", "",
                    "Response: WSSECFVT FVTVersion_ba04",
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with NO username/password
     * in the username token. The client default should be used instead. The
     * call to the service client is made using https. The call to the server is
     * also made using https. TransportBinding, Nonce and Created are specified
     * in the wsdl. A valid msg is sent (nonce/created generated) The request
     * should be successful.
     * 
     */
    @Test
    public void testCxfUntNonceAndCreatedNoIdSSL() throws Exception {

        genericTest("testCxfUntNonceAndCreatedNoIdSSL", untSSLClientUrl,
                    portNumberSecure, null, null, "FVTVersionBA4Service",
                    "UrnBasicPlcyBA4", "", "",
                    "Response: WSSECFVT FVTVersion_ba04",
                    "The test expected a succesful message from the server.");

    }

    // need another server with NO id/pw info to really test behavior with no
    // id/pw

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding, Nonce and Created are specified in the wsdl. A
     * hard-coded message containing a timestamp that is expired. The request
     * should fail as the message has expired.
     * 
     */
    @Test
    @ExpectedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfUntExpiredMsgSSL() throws Exception {

        genericTest("testCxfUntExpiredMsgSSL", untSSLClientUrl, portNumberSecure,
                    "user1", "security", "FVTVersionBA4Service", "UrnBasicPlcyBA4",
                    "", "oldTimeMsg", msgExpires,
                    "The message has expired. It should have failed");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding, Nonce and Created are specified in the wsdl. A
     * hard-coded message containing a timestamp that is in the future. The
     * request should fail as the message is set in th future.
     * 
     */
    @Test
    @AllowedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfUntOldExtFutureTimestampSSL() throws Exception {

        genericTest(
                    "testSslCxfWebService07OldExtFutureTimestampNegative",
                    untSSLClientUrl,
                    portNumberSecure,
                    "user1",
                    "security",
                    "FVTVersionBA7Service",
                    "UrnBasicPlcyBA7",
                    "",
                    "futureTime",
                    "The message has expired (WSSecurityEngine: Invalid timestamp The security semantics of the message have expired)",
                    "A future time stamp in WS request did not fail");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding with Timestamp and Nonce are specified in the wsdl. Test
     * should pass as the Timestamp should be generated.
     * 
     */
    @Test
    public void testCxfUntReqTimestampSSL() throws Exception {

        genericTest("testCxfUntReqTimestampSSL", untSSLClientUrl,
                    portNumberSecure, "user1", "security", "FVTVersionBA5Service",
                    "UrnBasicPlcyBA5", "", "",
                    "Response: WSSECFVT FVTVersion_ba05",
                    "The test expected a succesful message from the server.");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding with Timestamp and Nonce are specified in the wsdl. Test
     * expects a failure as the timestamp content is missing.
     * 
     */
    @Test
    @ExpectedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfUntReqTimestampMissingSSL() throws Exception {

        genericTest(
                    "testCxfUntReqTimestampMissingSSL",
                    untSSLClientUrl,
                    portNumberSecure,
                    "user1",
                    "security",
                    "FVTVersionBA5Service",
                    "UrnBasicPlcyBA5",
                    "",
                    "missingTimestamp",
                    timestampReqButMissing,
                    "The test expected an exception from the server because the timestamp was missing.");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding and Nonce are specified in the wsdl. A hard-coded
     * message is passed twice. The request should fail on the second call with
     * a replay exception.
     * 
     */
    //Defect 88389 - uncomment next line when issue is resolved 
    //@Test
    public void testCxfUntReqTimestampHardcodedMsgTwiceSSL() throws Exception {

        genericTest("testCxfUntReqTimestampHardcodedMsgTwiceSSL",
                    untSSLClientUrl, portNumberSecure, "user1", "security",
                    "FVTVersionBA5Service", "UrnBasicPlcyBA5", "true", "replay",
                    replayAttack, "The CXF SSL Web service test failed");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding and Nonce are specified in the wsdl. A hard-coded
     * message is passed twice. The request should fail on the second call with
     * a replay exception.
     * 
     */
    //Defect 88389 - uncomment next line when issue is resolved 
    //@Test
    public void testCxfUntReqTimestampSendMsgTwiceSSL() throws Exception {

        genericTest("testCxfUntReqTimestampSendMsgTwiceSSL", untSSLClientUrl,
                    portNumberSecure, "user1", "security", "FVTVersionBA5Service",
                    "UrnBasicPlcyBA5", "true", "", replayAttack,
                    "The CXF SSL Web service test failed");

    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding and Nonce are specified in the wsdl. A generated message
     * is passed twice. The request should fail on the second call with a replay
     * exception.
     * 
     */
    @Test
    @ExpectedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfUntReplaySSL() throws Exception {
        genericTest("testCxfUntReplaySSL", untSSLClientUrl, portNumberSecure,
                    "user1", "security", "FVTVersionBA7Service", "UrnBasicPlcyBA7",
                    "true", "", replayAttack,
                    "Second call to FVTVersionBA7Service should have failed");
    }

    /**
     * TestDescription:
     * 
     * This test invokes a jax-ws cxf service client, with a valid
     * username/password in the username token. The call to the service client
     * is made using https. The call to the server is also made using https.
     * TransportBinding and Nonce are specified in the wsdl. A hard-coded
     * message is passed twice. The request should fail on the second call with
     * a replay exception.
     * 
     */
    @Test
    @ExpectedFFDC("org.apache.ws.security.WSSecurityException")
    public void testCxfUntHardcodedReplaySSL() throws Exception {
        genericTest("testCxfUntReplaySSL", untSSLClientUrl, portNumberSecure,
                    "user1", "security", "FVTVersionBA7Service", "UrnBasicPlcyBA7",
                    "true", "strReplayNonce", replayAttack,
                    "Second call to FVTVersionBA7Service should have failed");
    }

}
