/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.abstractClasses;

import java.net.Socket;

/**
 *
 * @author pablo
 */
public abstract class reader {
   
   
    
    public abstract Socket telnetConnection();
    public abstract Socket sshConnection();
    
}
