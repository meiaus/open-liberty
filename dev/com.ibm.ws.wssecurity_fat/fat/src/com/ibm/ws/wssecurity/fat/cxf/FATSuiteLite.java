/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2012
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */

package com.ibm.ws.wssecurity.fat.cxf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.ws.wssecurity.fat.cxf.caller.CxfCallerUNTTests;
import com.ibm.ws.wssecurity.fat.cxf.nowssec.CxfNoWssecTests;
import com.ibm.ws.wssecurity.fat.cxf.sample.CxfSampleTests;
import com.ibm.ws.wssecurity.fat.cxf.sample.CxfSymSampleTests;
import com.ibm.ws.wssecurity.fat.cxf.usernametoken.CxfSSLUNTNonceTests;
import com.ibm.ws.wssecurity.fat.cxf.usernametoken.CxfSSLUNTNonceTimeOutTests;
import com.ibm.ws.wssecurity.fat.cxf.usernametoken.CxfUNTBasicTests;
import com.ibm.ws.wssecurity.fat.cxf.x509migtoken.CxfX509MigTests;

@RunWith(Suite.class)
@SuiteClasses({

               CxfUNTBasicTests.class,
               CxfSSLUNTNonceTests.class,
               CxfSSLUNTNonceTimeOutTests.class,
               CxfNoWssecTests.class,
               CxfX509MigTests.class,
               CxfCallerUNTTests.class,
               CxfSampleTests.class,
               CxfSymSampleTests.class
})
/**
 * Purpose: This suite collects and runs all known good test suites.
 */
public class FATSuiteLite {

}
