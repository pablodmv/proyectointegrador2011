/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.DTO;

/**
 *
 * @author pablo
 */
public class serverRCPDTO {
    private String ip;
    private String port;

    public serverRCPDTO() {
    }

    public serverRCPDTO(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    
    
}
