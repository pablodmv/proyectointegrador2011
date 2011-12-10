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



    public static Date StringDate(String date, String format){
        try {
            SimpleDateFormat simpleDate = new SimpleDateFormat(format);
            return simpleDate.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(converters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String DateString(Date date, String format){
        try {
            SimpleDateFormat simpleDate = new SimpleDateFormat(format);
            return simpleDate.format(date);
        } catch (Exception ex) {
            Logger.getLogger(converters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public static diaSemana obtenerDia(Short DiaSemana) {

        if (DiaSemana == 0) {
            return diaSemana.Domingo;
        } else if (DiaSemana == 1) {
            return diaSemana.Lunes;
        } else if (DiaSemana == 2) {
            return diaSemana.Martes;
        } else if (DiaSemana == 3) {
            return diaSemana.Miercoles;
        } else if (DiaSemana == 4) {
            return diaSemana.Jueves;
        } else if (DiaSemana == 5) {
            return diaSemana.Viernes;
        } else if (DiaSemana == 6) {
            return diaSemana.Sabado;
        }
        return null;

    }

}
