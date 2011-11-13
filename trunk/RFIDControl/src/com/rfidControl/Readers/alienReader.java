/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.Readers;

import com.rfidControl.abstractClasses.Reader;
import com.rfidControl.interfaces.rfidReaderOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class alienReader extends Reader implements rfidReaderOperation {

    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private Socket connection = null;
    private String user;
    private String password;

    public alienReader(String ipaddr,String Name) {
        this.ip_addr = ipaddr;
        this.port = 20000;
        this.name = Name;
        this.description = "Defaul configuration";
        this.user = "alien";
        this.password = "password";

    }

    public alienReader(short port, String ip_addr, String name, String description, String user, String password) {
        this.port = port;
        this.ip_addr = ip_addr;
        this.name = name;
        this.description = description;
        this.user = user;
        this.password = password;
                
        
    }

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    

    @Override
    public boolean connect() {
        try {
            connection = new Socket(ip_addr, port);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream());
            Thread.sleep(500);
            System.out.println(readFromReader(in));
            out.write(this.user+"\n");
            out.flush();
            System.out.println(readFromReader(in));
            Thread.sleep(500);
            out.write( this.password + "\n");
            out.flush();
            System.out.println(readFromReader(in));
             out.write(commandName() + "\n");
            out.flush();
            System.out.println(readFromReader(in));
            return true;
        } catch (InterruptedException ex) {
            //TODO:Loggear
            return false;
        } catch (UnknownHostException ex) {
            //TODO:Loggear
            return false;
        } catch (IOException ex) {
            //TODO:Loggear
            return false;
        }
    }

    @Override
    public String read() {
        try {
            String command = commandRead();
            out.write(command + "\n");
            out.flush();
            return readFromReader(in) + ":Name ";
        } catch (IOException ex) {
            Logger.getLogger(alienReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Ocurrio un error";
    }

    @Override
    public boolean stop() {
        try {
            out.write(commandStop());
            out.flush();
            connection.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(alienReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String commandRead() {
        return "t";
    }

    private String commandStop() {
        return "q";
    }
    private String commandName(){
        return "Get ReaderName";
    }

    private String readFromReader(BufferedReader inBuf) throws IOException {
        StringBuffer buf = new StringBuffer();
        int ch = inBuf.read();
        while ((char) ch != '\0') {
            buf.append((char) ch);
            ch = inBuf.read();
        }
        return buf.toString();
    }
}
