package com.ibm.ws.wssecurity.fat.pwdigest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientPWDigestCallbackHandler implements CallbackHandler {
    
    private Map<String, String> passwords = 
        new HashMap<String, String>();
    
    public ClientPWDigestCallbackHandler() {
        passwords.put("user1", "security");
        passwords.put("user2", "security");
        passwords.put("user3", "badPW3");
        // user4 specifically excluded
        passwords.put("user5", "security");
        passwords.put("user6", "badPw6");
        passwords.put("user22", "security") ;
        passwords.put("user88", "security") ;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    	System.out.println("in Handle of client callback") ;
        for (int i = 0; i < callbacks.length; i++) {
        	System.out.println("Client Callback processing") ;
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
