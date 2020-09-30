package com.ibm.ws.wssecurity.fat.cxf;

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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(FATSuiteNotNeeded.class)
public class FATSuiteNotNeeded extends Suite {

    public FATSuiteNotNeeded(Class<?> setupClass) throws Exception {
        // Override suite method to include test classes dynamically
        super(setupClass, FATSuiteBuilder.suite());
    }
}
