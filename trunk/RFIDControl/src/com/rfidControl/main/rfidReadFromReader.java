/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

import com.rfidControl.abstractClasses.Reader;
import com.rfidControl.rpc.rfidRCPClient;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author pablo
 */
public class rfidReadFromReader extends Thread {

    private final static Logger log = Logger.getLogger(Main.class);
    private Reader reader;

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public rfidReadFromReader(Reader reader) {
        this.reader = reader;
        PropertyConfigurator.configure("src/com/rfidControl/log4j/log4j.properties");
    }

    @Override
    public void run() {
        String lecture = "";

        while (true) {
            try {
                lecture = reader.read();
                
                rfidRCPClient rcpClient = rfidRCPClient.getInstance();
                if (lecture.length() > 50 ) {
                    lecture = rfidMainController.getInstance().convertStringToFormat(lecture, reader);
                    rcpClient.sendDataToServer(lecture);
                    System.out.println("LECTURA DESDE EL THREAD: " + lecture);
                    //Thread.sleep(3000);
                    //TODO: Controlar que no se envie caracter vacio
                }
                Thread.sleep(3000);

            } catch (InterruptedException ex) {
                log.error("Error thread: " + ex.getMessage());
                System.out.println("Error thread: " + ex.getMessage());
                break;
            } catch (Exception ex) {
                log.error("Error thread: " + ex.getMessage());
                System.out.println("Error thread: " + ex.getMessage());
                break;

            }

        }
    }
}
