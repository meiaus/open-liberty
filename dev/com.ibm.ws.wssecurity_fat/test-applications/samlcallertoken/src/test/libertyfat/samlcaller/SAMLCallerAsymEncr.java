/*
 * IBM Confidential OCO Source Material
 * 5639-D57, 5630-A36, 5630-A37, 5724-D18, 5724-J08 (C) COPYRIGHT International Business Machines Corp. 2015
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

import test.libertyfat.caller.SAMLCallerUtil;

@WebServiceProvider(targetNamespace="http://caller.libertyfat.test/contract",
                    serviceName="SAMLCallerAsymEncrService", portName="SAMLCallerAsymEncrPort",
                    wsdlLocation="WEB-INF/samlcallertoken.wsdl")

@ServiceMode(value = Service.Mode.MESSAGE)

/**
 */
public class SAMLCallerAsymEncr implements javax.xml.ws.Provider<SOAPMessage> {

    /* (non-Javadoc)
     * @see javax.xml.ws.Provider#invoke(java.lang.Object)
     */
    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        String PrincipalUserID = SAMLCallerUtil.getPrincipalUserID();
        String respMsg = new String(
                               "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                               "<SOAP-ENV:Body><provider><message>Liberty Fat SamlCaller SAMLCallerAsymEncr(" + PrincipalUserID +
                               ")</message></provider></SOAP-ENV:Body>" + "</SOAP-ENV:Envelope>"  );
        return SAMLCallerUtil.invoke( request, respMsg, getClass().getName() );
    }
}
