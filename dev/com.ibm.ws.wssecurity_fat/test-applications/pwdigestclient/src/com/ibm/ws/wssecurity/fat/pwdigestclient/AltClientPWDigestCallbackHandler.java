package com.ibm.ws.wssecurity.fat.pwdigest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class AltClientPWDigestCallbackHandler implements CallbackHandler {
    
    private Map<String, String> passwords = 
        new HashMap<String, String>();
    
    public AltClientPWDigestCallbackHandler() {
        passwords.put("user4", "security");
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    	System.out.println("in Handle of alternate client callback") ;
        for (int i = 0; i < callbacks.length; i++) {
        	System.out.println("Alternate Client Callback processing") ;
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

            String pass = passwords.get(pc.getIdentifier());
            System.out.println("Will return : " + pass) ;
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
        
        throw new IOException();
    }
    
    // Add an alias/password pair to the callback mechanism.
    public void setAliasPassword(String alias, String password) {
        passwords.put(alias, password);
    }
}
