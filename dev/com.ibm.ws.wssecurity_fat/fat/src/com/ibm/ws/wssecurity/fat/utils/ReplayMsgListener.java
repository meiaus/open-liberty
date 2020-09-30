package com.ibm.ws.wssecurity.fat.utils;

/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * WLP Copyright IBM Corp. 2015
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */

import com.ibm.ws.wssecurity.fat.utils.tcpmon.TCPMonEvenListener;

/*
 * Implementation of TCPMonEvenListener for intercepting HTTP messages.
 * Events without pay load are not considered..  
 */
public class ReplayMsgListener implements TCPMonEvenListener {

    static String replayMessage = null;

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
        System.out.println(isInbound ? "Message in: " : "Message out: ");
        System.out.println(event);
        System.out.println("=================================");

        if (!isInbound) {
            String newEvent = null; // Init to send same msg
            if (replayMessage != null) {
                newEvent = replayMessage; // use old msg
            }
            else {
                replayMessage = event; // first invocation
            }

            System.out.println("=================================");
            System.out.println("New message out: ");
            System.out.println(newEvent);
            System.out.println("=================================");

            return newEvent;
        }

        // sent the message as-is
        return null;
    }

    public void clearReplayMessage() {
        replayMessage = null;
    }

}
