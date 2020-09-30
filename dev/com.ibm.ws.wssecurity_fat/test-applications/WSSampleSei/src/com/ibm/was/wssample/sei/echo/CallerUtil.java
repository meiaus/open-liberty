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

package com.ibm.was.wssample.sei.echo;

import java.util.Set;

import com.ibm.websphere.security.auth.WSSubject;
import javax.security.auth.Subject;
import java.security.Principal;

public class CallerUtil {

    static public String getPrincipalUserID(){
        try{
            String caller_princ    =  WSSubject.getCallerPrincipal();
            Subject caller_subject =  WSSubject.getRunAsSubject();

            System.out.println("WSSubject.getCallerPrincipal():" + caller_princ + ":" + caller_subject);
            if( caller_princ == null ){
                Set caller_set = caller_subject.getPrincipals();
                Object[] objects = caller_set.toArray();
                if( objects.length > 0){
                    if( objects[0] instanceof Principal){
                        Principal principal = (Principal)objects[0];
                        caller_princ = principal.getName();
                    }
                } else{
                    caller_princ = objects[ 0].toString();                  
                }

            }
            return caller_princ;
        } catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }
}
