/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class converters {



    public static Date StringDate(String date){
        try {
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDate.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(converters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



}
