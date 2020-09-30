package com.ibm.ws.wssecurity.fat;

/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2013
 *
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */

import java.io.File;
import java.security.Security;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.wssecurity.fat.utils.common.CommonTests;
import componenttest.annotation.AllowedFFDC;

public class SharedTools {

     public fixProviderOrder(String callerName)
     
     /* handle case where IBMCERT isn't in the list ... */
     
        int IBMCert = 0;
        int SUN = 0;
        System.out.println("Start printing " + callerName + " providers");
        java.security.Provider p[] = Security.getProviders();
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
            if (p[i].toString().contains("IBMCertPath")) {
                IBMCert = i;
            }
            if (p[i].toString().contains("SUN version")) {
                SUN = i;
            }
        }
        System.out.println("Finished printing " + callerName + "  providers");
        System.out.println("Start Updating Order");
        if (SUN == 0) {
            System.out.println("NO SUN included - no update needed");
        } else {
            if (SUN < IBMCert) {
                Security.removeProvider(p[IBMCert].getName());
                Security.insertProviderAt(p[IBMCert], SUN + 1);
                System.out.println("Finished Updating Order");

                System.out.println("Start printing updated " + callerName + "  providers");
                java.security.Provider pu[] = Security.getProviders();
                for (int j = 0; j < pu.length; j++) {
                    System.out.println(pu[j]);
                }
                System.out.println("Finished printing updated " + callerName + "  providers");
            } else {
                System.out.println("IBM Cert is already before SUN - no update needed");

            }
        }

    }


}
