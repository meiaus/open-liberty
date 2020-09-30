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

/**
 * Listener interface for intercepting HTTP messages
 */
public interface TCPMonEvenListener {

    public static int TCPMON_LISTENER_PORT = 9999; // define a common TCPMON Listener port so, we can change it easily.

    /**
     * Listener notification that event has been received
     * 
     * @param isInbound message direction, returnes true when receiving response
     * @param event byte content of the HTTP payload, http headers are removed
     * @return message to send. a null means send what you have
     */
    public String receiveEvent(boolean isInbound, String event);
}
