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
import com.ibm.websphere.simplicity.log.Log;

/*
 * Implementation of TCPMonEvenListener for intercepting HTTP messages.
 * Events without pay load are not considered.
 */
public class DebugListener implements TCPMonEvenListener {

    static private final Class<?> thisClass = DebugListener.class;

    /**
     * Listener notification that event has been received
     * 
     * @param isInbound message direction, returns true when receiving response
     * @param event byte content of the HTTP pay load, http headers are removed
     * @return message to send. a null means send what you have
     */
    public String receiveEvent(boolean isInbound, String event) {
        String thisMethod = "TcpmonMessage" + (isInbound ? "IN" : "OUT");

        Log.info(thisClass, thisMethod, event);

        return null;
    }

}
