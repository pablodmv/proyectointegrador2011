/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.facade;

import com.inout.dto.ausenciaDTO;
import com.inout.dto.horarioDTO;
import com.inout.dto.marcaDTO;
import com.inout.dto.motivoausenciaDTO;
import com.inout.dto.personaDTO;
import com.inout.dto.tarjetaDTO;
import com.inout.ejb.ausenciasLocal;
import com.inout.ejb.cierreLocal;
import com.inout.ejb.horariosLocal;
import com.inout.ejb.marcaLocal;
import com.inout.ejb.motivoAusenciaLocal;
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

    public Boolean saveHorario(horarioDTO horario){
        return this.lookuphorariosRemote().insertarHorario(horario);
    }

    public personaDTO searchPerson(String idPersona){
        return this.lookuppersonaRemote().ObtenerPersona(idPersona, "Gustavo");
    }
    public List<horarioDTO> searchHorarios(personaDTO Persona){
        return this.lookuphorariosRemote().obtenerHorarioPersona(Persona);
    }
     public List<ausenciaDTO> searchAusencias(personaDTO Persona,Date fecha){
        return this.lookupAusenciasRemote().obtenerAusenciaFechaPersona(Persona, fecha);
    }

      public List<motivoausenciaDTO> searchMotivoAusencias(){
        return this.lookupMotivoAusenciasRemote().obtenerMotivoAusencia();
    }
    public Boolean editMark(marcaDTO mDto){
        return this.lookupmarcaRemote().altaMarca(mDto);
    }

     public Boolean editHorario(horarioDTO horario){
        return this.lookuphorariosRemote().modificarHorarioPersona(horario);
    }
     public Boolean deleteHorario(horarioDTO horario){
        return this.lookuphorariosRemote().eliminarHorarioPersona(horario);
    }

    public List<marcaDTO> getAllMarks(Date date){
        return this.lookupmarcaRemote().obtenerTodasMarcas(date, "Gustavo");
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


    public List<marcaDTO> getMarkByDateRange(Date dateIni, Date dateFin){
        return this.lookupmarcaRemote().obtenerMarcasPorFecha(dateIni, dateFin, "Gustavo");
    }


    public Boolean cierreMes(Date desde, Date hasta){
        return this.lookupcierreRemote().cerrarMes(desde, hasta);
    }


    public List<marcaDTO> getMarkByPersonDate(personaDTO persona, Date dateIni, Date dateFin) {
        return this.lookupmarcaRemote().obtenerMarcaEntreFechaPersona(persona, dateIni, dateFin);
    }
    public  motivoausenciaDTO getMotivo(Long id){
        return this.lookupMotivoAusenciasRemote().obtenerMotivoAusencia(id);
    }

     public  horarioDTO getHorario(Long id){
        return this.lookuphorariosRemote().obtenerHorario(id);
    }

    public Boolean saveCard(tarjetaDTO tarjeta){
        return this.lookuptarjetaRemote().altaTarjeta(tarjeta, "Gustavo");
    }
    public Boolean saveAusencia(ausenciaDTO ausencia){
        return this.lookupAusenciasRemote().insertarAusencia(ausencia);
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

     private cierreLocal lookupcierreRemote() {
        try {
            Context c = new InitialContext();
            return (cierreLocal) c.lookup("java:comp/env/cierre");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

     private ausenciasLocal lookupAusenciasRemote() {
        try {
            Context c = new InitialContext();
            return (ausenciasLocal) c.lookup("java:comp/env/ausencias");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

     private motivoAusenciaLocal lookupMotivoAusenciasRemote() {
        try {
            Context c = new InitialContext();
            return (motivoAusenciaLocal) c.lookup("java:comp/env/motivoAusencia");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
