/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Local
public interface loggerJMSLocal {

    public void loggerMessage(String method, String user,String action);
    
}
