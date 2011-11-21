/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.test;

import com.inout.entities.Persona;
import com.inout.ejb.personaLocal;
import javax.ejb.EJB;
import com.inout.ejb.persona;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;
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
public class personaTest {


    public personaTest() {
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
    public void testAltaPersona() throws Exception {
        System.out.println("AltaPersona");
        personaLocal instance = lookuppersona();
        String result1 = instance.altaPersona("42562072", "Pablo", "Martinez", "Direccion cualquiera", "98376464", "8qw76e6w7", "13/06/2008", 29);
        String result2 = instance.altaPersona("123456", "Gustavo", "Leites", "Direccion cualquiera", "98376464", "8qw76e6w7", "13/06/2008", 29);
        System.out.println("Resultado1 " + result1);
        System.out.println("Resultado2 " + result2);
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