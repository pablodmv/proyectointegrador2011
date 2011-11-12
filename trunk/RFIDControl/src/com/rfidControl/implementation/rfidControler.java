/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.implementation;

import com.rfidControl.abstractClasses.reader;
import com.rfidControl.interfaces.rfidOperation;
import java.net.Socket;

/**
 *
 * @author pablo
 */
public class rfidControler implements rfidOperation {

    @Override
    public Socket connection(reader param) {
        return param.telnetConnection();
    }

    @Override
    public String read() {

       String command = "t";
        out.write(command + "\n");
        out.flush();
        String returnVal = readFromReader(in);
        return returnVal;
    
    
    
    
    
    
    }

    @Override
    public void send(String command) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    
}
