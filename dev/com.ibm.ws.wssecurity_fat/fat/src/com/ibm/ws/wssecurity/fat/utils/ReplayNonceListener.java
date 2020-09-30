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
 */
public class ReplayNonceListener implements TCPMonEvenListener {

    static String nonceValue = null;

    /**
     * Listener notification that event has been received
     * 
     * @param isInbound message direction, returns true when receiving response
     * @param event byte content of the HTTP pay load, http headers are removed
     * @return message to send. a null means send what you have
     */
    @Override
    public String receiveEvent(boolean isInbound, String event) {
        System.out.println("=================================");
        System.out.println(isInbound ? "ReplayListener Message in: " : "ReplayListener Message out: ");
        System.out.println(event);
        System.out.println("=================================");

        if (!isInbound) {

            String newEvent = null;

            // Replace new nonce with old nonce to simulate replay attack
            if (nonceValue != null) {
                newEvent = replaceNonceInMsg(event);

                System.out.println("=================================");
                System.out.println("ReplayListener New message out: ");
                System.out.println(newEvent);
                System.out.println("=================================");
            }
            else {
                nonceValue = extractNonce(event);
                System.out.println("DEBUG: First time ReplayListener extratced nonce: " + nonceValue);
            }

            return newEvent;
        }

        // sent the message as-is
        return null;
    }

    public void clearNonceValue() {
        nonceValue = null;
    }

    // Replaces Nonce element in the new SOAP message with the previous Nonce value from nonceValue
    public String replaceNonceInMsg(String soapMsg) {

        String updatedMsg = soapMsg;
        int startNonce = soapMsg.indexOf("<s:Nonce");

        if (startNonce > 0) {

            int endNonce = soapMsg.indexOf("</s:Nonce>");
            int nonce_length = (new String("</s:Nonce>")).length();

            String before_nonce = soapMsg.substring(0, startNonce);
            String after_nonce = soapMsg.substring(endNonce + nonce_length);

            // String nonceElem = soapMsg.substring(startNonce, endNonce + nonce_length);

            System.out.println("DEBUG: Replacing with nonce: " + nonceValue);

            updatedMsg = before_nonce + nonceValue + after_nonce;

        }

        return updatedMsg;

    }

    // Extracts Nonce element from the SOAP message    
    public String extractNonce(String soapMsg) {

        String nonceElem = null;
        int startNonce = soapMsg.indexOf("<s:Nonce");

        if (startNonce > 0) {

            int endNonce = soapMsg.indexOf("</s:Nonce>");
            int nonce_length = (new String("</s:Nonce>")).length();

            nonceElem = soapMsg.substring(startNonce, endNonce + nonce_length);

        }

        System.out.println("DEBUG: ReplayListener Extracted nonce: " + nonceElem);

        return nonceElem;

    }

}
