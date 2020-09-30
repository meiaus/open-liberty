/*
 * IBM Confidential OCO Source Material
 * 5639-D57, 5630-A36, 5630-A37, 5724-D18, 5724-J08 (C) COPYRIGHT International Business Machines Corp. 2006
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 *
 * Date      Author       Description
 * --------  -----------  ----------------------
 */

package test.libertyfat.caller;

import javax.xml.ws.WebServiceProvider;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.Service;

import test.libertyfat.caller.CallerUtil;

@WebServiceProvider(targetNamespace="http://caller.libertyfat.test/contract",
                    serviceName="FatBAC06Service", portName="UrnCallerToken06",
                    wsdlLocation="WEB-INF/callertoken.wsdl")

@ServiceMode(value = Service.Mode.MESSAGE)

/**
 */
public class Caller_bac06 implements javax.xml.ws.Provider<SOAPMessage> {

    /* (non-Javadoc)
     * @see javax.xml.ws.Provider#invoke(java.lang.Object)
     */
    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        String PrincipalUserID = CallerUtil.getPrincipalUserID();
        String respMsg = new String(
                               "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                               "<SOAP-ENV:Body><provider><message>Liberty Fat Caller bac06(" + PrincipalUserID + 
                               ")</message></provider></SOAP-ENV:Body>" + "</SOAP-ENV:Envelope>"  );
        return CallerUtil.invoke( request, respMsg, getClass().getName()  );
    }
}
