/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rfidControl.main;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        rfidMainController controller = rfidMainController.getInstance();
        controller.readFromReaders();
        //controller.readProperties();
    }

    
}
