/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.facade;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.dto.tarjetaDTO;
import com.inout.ejb.horariosLocal;
import com.inout.ejb.marcaLocal;
import com.inout.ejb.personaLocal;
import com.inout.ejb.tarjetaLocal;
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
    tarjetaLocal tarjeta = lookuptarjetaRemote();
    personaLocal persona = lookuppersonaRemote();
    marcaLocal marca = lookupmarcaRemote();
    horariosLocal horario = lookuphorariosRemote();

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

    public Boolean editMark(marcaDTO mDto){
        return this.lookupmarcaRemote().altaMarca(mDto);
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

    private horariosLocal lookuphorariosRemote() {
        try {
            Context c = new InitialContext();
            return (horariosLocal) c.lookup("java:comp/env/horarios");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }



    public List<marcaDTO> getMarKByPersonDate(personaDTO persona, Date date) {

        return this.lookupmarcaRemote().obtenerMarcaPorFechaPersona(persona, date);
    }

    public Boolean saveCard(tarjetaDTO tarjeta){
        return this.lookuptarjetaRemote().altaTarjeta(tarjeta, "Gustavo");
    }

    public List<tarjetaDTO> getTarjetasActivas(){
        return this.lookuptarjetaRemote().ObtenerTarjetasActivasDTO();
    }

    private tarjetaLocal lookuptarjetaRemote() {
        try {
            Context c = new InitialContext();
            return (tarjetaLocal) c.lookup("java:comp/env/tarjeta");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
