/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

import com.rfidControl.implementation.rfidControler;
import com.rfidControl.interfaces.rfidOperation;

/**
 *
 * @author pablo
 */
public class rfidControlMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rfidOperation operation = new rfidControler();
        operation.connection(null);
        
    }
}
