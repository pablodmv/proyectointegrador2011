/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import prueba.PruebaEJBLocal;

/**
 *
 * @author gustavo
 */
@Named(value="docenteMB")
@Dependent
public class DocenteMB {
    PruebaEJBLocal pruebaEJB = lookupPruebaEJBLocal();

    private String nombre;
    private String apellido;
    private String doc;
    private String tel1;
    private String tel2;
    private String dir;


    /** Creates a new instance of DocenteMB */
    public DocenteMB() {
        PruebaEJBLocal p = this.lookupPruebaEJBLocal();
        System.out.println(">>> " + p.pruebaMethod());
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    private PruebaEJBLocal lookupPruebaEJBLocal() {
        try {
            Context c = new InitialContext();
            return (PruebaEJBLocal) c.lookup("java:comp/env/PruebaEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    

}
