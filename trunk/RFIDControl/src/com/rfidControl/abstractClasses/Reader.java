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
public abstract class Reader {

    protected short port;
    protected String ip_addr;
    protected String name;
    protected String description;

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

    public abstract boolean connect();

    public abstract String read();

    public abstract boolean stop();
 
}
