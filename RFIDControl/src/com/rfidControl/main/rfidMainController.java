/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

import com.rfidControl.abstractClasses.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class rfidMainController {

    private static rfidMainController instance = null;
    private List<Reader> readersCollection = null;

    private rfidMainController() {
        readersCollection = new ArrayList<Reader>();
    }

    public static rfidMainController getInstance() {
        if (instance == null) {
            instance = new rfidMainController();
        }
        return instance;
    }

    public boolean connectReader(Reader reader) {
        if (!reader.connect()) {
            return false;
            //TODO: loggear
        } else {
            readersCollection.add(reader);
            return true;
        }
    }

    public String readFromReader(Reader reader) {
        return reader.read();

    }

    public void persistReadersActive(Reader reader) {
    }

    public void deleteReaderInactive(Reader reader) {

        for (Reader readerActive : readersCollection) {
            if (readerActive.getName().equals(reader.getName())) {
                readersCollection.remove(reader);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

