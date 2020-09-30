package com.ibm.ws.wssecurity.fat.cxf.wsstemplates;

import org.junit.BeforeClass;

import com.ibm.ws.wssecurity.fat.cxf.usernametoken.CxfWssTemplatesTests;
import com.ibm.ws.wssecurity.fat.utils.common.UpdateWSDLPortNum;

public class CxfWssTemplatesTestsWithWSDL extends CxfWssTemplatesTests {

    static private UpdateWSDLPortNum newWsdl = null;
    static final private String serverName = "com.ibm.ws.wssecurity_fat.wsstemplates";

    @BeforeClass
    public static void setUp() throws Exception {
        commonSetUp(serverName, true, "/wsstemplatesclient/CxfWssTemplatesSvcClient");
    }

}
