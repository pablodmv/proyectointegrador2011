/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.facade;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.ejb.marcaLocal;
import com.inout.ejb.personaLocal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author gustavo
 */
public class Facade {
    personaLocal persona = lookuppersonaRemote();
    marcaLocal marca = lookupmarcaRemote();

    private static Facade instance;

    private Facade(){

    }

    public static Facade getInstance(){
        if(instance == null){
            instance = new Facade();
        }
        return instance;
    }


    public Boolean savePerson(personaDTO p){
        return this.lookuppersonaRemote().altaPersona(p, "Gustavo");
    }

    public personaDTO searchPerson(String idPersona){
        return this.lookuppersonaRemote().ObtenerPersona(idPersona, "Gustavo");
    }


    private marcaLocal lookupmarcaRemote() {
        try {
            Context c = new InitialContext();
            return (marcaLocal) c.lookup("java:comp/env/marca");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private personaLocal lookuppersonaRemote() {
        try {
            Context c = new InitialContext();
            return (personaLocal) c.lookup("java:comp/env/persona");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public List<marcaDTO> getMarKByDate(Date date) {
        return this.lookupmarcaRemote().obtenerTodasMarcas(date, "Gustavo");
    }

}
