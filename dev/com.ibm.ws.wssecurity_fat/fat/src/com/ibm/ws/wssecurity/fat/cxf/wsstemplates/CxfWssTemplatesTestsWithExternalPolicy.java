package com.ibm.ws.wssecurity.fat.cxf.wsstemplates;

//import java.io.File;

import org.junit.BeforeClass;

import com.ibm.ws.wssecurity.fat.cxf.usernametoken.CxfWssTemplatesTests;
import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

public class CxfWssTemplatesTestsWithExternalPolicy extends CxfWssTemplatesTests {

    static private UpdateWSDLPortNum newWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.wsstemplateswithep";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, true, "/wsstemplatesclient/CxfWssTemplatesSvcClient");
    }

}
