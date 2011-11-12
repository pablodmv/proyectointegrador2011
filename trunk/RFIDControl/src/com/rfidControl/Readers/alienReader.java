/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.Readers;

import com.rfidControl.abstractClasses.reader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class alienReader extends reader {

    private short port;
    private String ip_addr;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp_addr() {
        return ip_addr;
    }

    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    

    public alienReader(String ipaddr) {
        this.ip_addr = ipaddr;
        this.port = 2000;
        this.name = "Reader default";
        this.description = "Defaul configuration";

    }

    public alienReader(short port, String ip_addr, String name, String description) {
        this.port = port;
        this.ip_addr = ip_addr;
        this.name = name;
        this.description = description;
    }

    @Override
    public Socket telnetConnection() {
        Socket connection = null;
        try {
            connection = new Socket(ip_addr, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(alienReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(alienReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
        
    }

    @Override
    public Socket sshConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }











}
