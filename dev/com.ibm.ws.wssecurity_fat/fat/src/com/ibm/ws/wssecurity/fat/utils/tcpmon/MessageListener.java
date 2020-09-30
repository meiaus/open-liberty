package com.ibm.ws.wssecurity.fat.utils.tcpmon;

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

/*
 * Implementation of TCPMonEvenListener for intercepting HTTP messages.
 * Events without pay load are not considered.
 */
public class MessageListener implements TCPMonEvenListener {

    String inMsg = null;
    String outMsg = null;

    /**
     * Listener notification that event has been received
     * 
     * @param isInbound message direction, returnes true when receiving response
     * @param event byte content of the HTTP payload, http headers are removed
     * @return message to send. a null means send what you have
     */
    @Override
    public String receiveEvent(boolean isInbound, String event) {
        System.out.println("=================================");
        if (isInbound) {
            System.out.println("MessageListener Message in: ");
            inMsg = new String(event);
        }
        else {
            System.out.println("MessageListener Message out: ");
            outMsg = new String(event);
        }

        System.out.println(event);
        System.out.println("=================================");

        return null;

    }

    public String getInboundMsg() {
        return inMsg;
    }

    public String getOutboundMsg() {
        return outMsg;
    }

}
