package com.ibm.ws.wssecurity.fat.utils;

/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2011
 *
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */

import com.ibm.ws.wssecurity.fat.utils.tcpmon.TCPMonEvenListener;

/*
 * Implementation of TCPMonEvenListener for intercepting HTTP messages.
 * Events without pay load are not considered.
 * 
 */

public class RemPswdListener implements TCPMonEvenListener {

    /**
     * Listener notification that event has been received
     * 
     * @param isInbound message direction, returnes true when receiving response
     * @param event byte content of the HTTP payload, http headers are removed
     * @return message to send. a null means send what you have
     */
    public String receiveEvent(boolean isInbound, String event) {
        System.out.println("=================================");
        System.out.println(isInbound ? "Message in: " : "Message out: ");
        System.out.println(event);
        System.out.println("=================================");

        if (!isInbound) {
            event = removePassword(event);
            System.out.println("=================================");
            System.out.println("New message out: ");
            System.out.println(event);
            System.out.println("=================================");
            return event;
        }

        // sent the message as-is
        return null;
    }

    /**
     * This method intercepts the SOAP request, and removes password from
     * UsernameToken.
     */
    public String removePassword(String event) {

        String retVal = event;
        // int pwd_start = event.indexOf("<s:Password");
        int pwd_start = event.indexOf("#PasswordText\"");

        System.out.println("Inside removePassword...");

        if (pwd_start > 0) {

            pwd_start += (new String("#PasswordText\"")).length();
            retVal = (event.substring(0, pwd_start)).concat(">");

            int pwd_end = event.indexOf("</s:Password>");
            // skip Contents of <s:Password>...</s:Password> element
            retVal = retVal.concat(event.substring(pwd_end));
            System.out.println("Modified Message, with no password " + retVal);
        }

        else {
            System.out.println("Something wrong! <s:Password> not found");
        }

        return (retVal);

    }

}
