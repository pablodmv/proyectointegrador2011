/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.interfaces;

import com.rfidControl.abstractClasses.reader;
import java.net.Socket;

/**
 *
 * @author pablo
 */
public interface rfidOperation {
    
    //Metodo para conectarse al lector RFID
    public Socket connection(reader param);
    
    //Metodo para leer los tags
    public String read();
    
    
    //Metodo para enviar las lecturas al soft de gestion
    public void send(String command);
    
    
    public void stop();
    
}
