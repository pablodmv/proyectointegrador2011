/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.test;

import java.text.SimpleDateFormat;
import com.inout.dto.personaDTO;
import com.inout.ejb.personaLocal;
import java.util.Date;
import java.text.DateFormat;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;
import com.inout.ejb.ausenciasLocal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pablo
 */
public class ausenciasTest {

    public ausenciasTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void chequearAusencias() {
     System.out.println("testObtenerMarcaEntreFechaPersona");
        ausenciasLocal instance = lookupAusencias();
        DateFormat formatter;
        Date date,date2;
        personaLocal instancePersona = lookuppersona();
        personaDTO persona = instancePersona.ObtenerPersona("42562072","System");
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-12-01";
        String str_date2 = "2011-12-31";
        instance.chequearAusencias(persona,new Date());
     
     
     
     
     
     
     
     }


      private ausenciasLocal lookupAusencias() {
        try {
            Context c = new InitialContext();
            return (ausenciasLocal) c.lookup("ausencias");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
 private personaLocal lookuppersona() {
        try {
            Context c = new InitialContext();
            return (personaLocal) c.lookup("persona");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}