/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.test;

import java.util.Date;
import com.inout.dto.tarjetaDTO;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;
import com.inout.ejb.tarjetaLocal;
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
public class tarjetaTest {

    public tarjetaTest() {
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
    // @Test
    // public void hello() {}


       @Test
    public void testAgregarTarjeta() throws Exception {
        System.out.println("testAgregarTarjeta");
        tarjetaLocal instance = lookupTarjeta();
        String id = "GENERICA";
        Short tipo = 1;
        Date fechaEntrega = new Date();
        Boolean activa = Boolean.TRUE;
        tarjetaDTO tarjeta = new tarjetaDTO(id, tipo, fechaEntrega, activa);
        Boolean result1 = instance.altaTarjeta(tarjeta);

        System.out.println("Resultado1 " + result1);
    }

    private tarjetaLocal lookupTarjeta() {
        try {
            Context c = new InitialContext();
            return (tarjetaLocal) c.lookup("tarjeta");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}