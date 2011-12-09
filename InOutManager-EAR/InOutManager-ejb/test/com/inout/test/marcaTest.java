/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.test;

import com.inout.dto.personaDTO;
import com.inout.ejb.personaLocal;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import com.inout.dto.marcaDTO;
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
        marcaDTO marca = new marcaDTO("2010/11/23", "20:55", "4", "PUERTA", "123456");
        Boolean result1 = instance.altaMarca(marca);
        System.out.println("Resultado1 " + result1);
    }

    //@Test
    public void testObtenerTodasMarcas() throws Exception {
        System.out.println("ObtenerTodasMarcas");
        marcaLocal instance = lookupMarca();
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-11-21";
        date = (Date) formatter.parse(str_date);
        List<marcaDTO> result1 = instance.obtenerTodasMarcas(date, "Pablo");
        System.out.println("Resultado1 " + result1.size());
        for (marcaDTO dTO : result1) {
            System.out.println("La fecha es : " + dTO.getFechaStr());

        }
    }


     //@Test
    public void testEliminarMarca() throws Exception {
        System.out.println("ObtenerTodasMarcas");
        marcaLocal instance = lookupMarca();
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-11-21";
        date = (Date) formatter.parse(str_date);
        List<marcaDTO> result1 = instance.obtenerTodasMarcas(date, "Pablo");
        Boolean resultado = instance.eliminarMarca(result1.get(0), "Pablo");
        System.out.println("Resultado1 " + resultado);

    }

        // @Test
    public void testModificarMarca() throws Exception {
        System.out.println("ObtenerTodasMarcas");
        marcaLocal instance = lookupMarca();
        personaLocal instancePersona = lookupPersona();
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-11-26";
        date = (Date) formatter.parse(str_date);
        List<marcaDTO> result1 = instance.obtenerTodasMarcas(date, "Pablo");
        result1.get(0).setHora("pablo");
        result1.get(0).setPersona(instancePersona.ObtenerPersona("42562072", "System"));
        Boolean resultado = instance.modificarMarca(result1.get(0), "Pablo");
        System.out.println("Resultado1 " + resultado);

    }


    //@Test
    public void testObtenerMarcaPorFechaPersona() throws Exception {
        System.out.println("testObtenerMarcaPorFechaPersona");
        marcaLocal instance = lookupMarca();
        DateFormat formatter;
        Date date;
        personaLocal instancePersona = lookupPersona();
        personaDTO persona = instancePersona.ObtenerPersona("22221111","System");
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-12-06";
        date = (Date) formatter.parse(str_date);
        List<marcaDTO> result1 = instance.obtenerMarcaPorFechaPersona(persona, date);
        System.out.println("Resultado1 " + result1.size());

    }

    // @Test
    public void testCrearParejas() throws Exception {
        System.out.println("testCrearParejas");
        marcaLocal instance = lookupMarca();
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = "2011-11-27";
        date = (Date) formatter.parse(str_date);
        List<marcaDTO> marcas=instance.obtenerTodasMarcas(date, "System");

         for (marcaDTO marcaImprimo:marcas) {
             System.out.println("Dispositivo:" + marcaImprimo.getIdDispositivo()+" Fecha: "+marcaImprimo.getFechaStr()+" id: "+marcaImprimo.getIdPareja());

         }

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

     private personaLocal lookupPersona() {
        try {
            Context c = new InitialContext();
            return (personaLocal) c.lookup("persona");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
