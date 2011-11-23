/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.test;

import com.inout.dto.marcaDTO;
import com.inout.entities.Marca;
import java.util.List;
import java.util.Date;
import org.junit.Test;
import javax.naming.NamingException;
import com.inout.ejb.marcaLocal;
import javax.naming.InitialContext;
import javax.naming.Context;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author pablo
 */
public class marcaTest {

    public marcaTest() {
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
    public void testAltaMarca() throws Exception {
        System.out.println("AltaMarca");
        marcaLocal instance = lookupMarca();
        marcaDTO marca = new marcaDTO("23/11/2010", "20:55", 4,"PUERTA", "GENERICA");
        Boolean result1 = instance.altaMarca(marca);
        System.out.println("Resultado1 " + result1);
    }

  //  @Test
    public void testObtenerTodasMarcas() throws Exception {
        System.out.println("ObtenerTodasMarcas");
        marcaLocal instance = lookupMarca();
        List<marcaDTO> result1 = instance.obtenerTodasMarcas(new Date(),"Pablo");
        System.out.println("Resultado1 " + result1.size());
    }

    private marcaLocal lookupMarca() {
        try {
            Context c = new InitialContext();
            return (marcaLocal) c.lookup("marca");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}