/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.dto;

import com.inout.entities.Cierre;
import com.inout.util.converters;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pablo
 */
public class marcaDTO implements Serializable {

    private Long id;
    private Date fecha;
    private String fechaStr="";
    private String hora="";
    private String idDispositivo;
    private String dispositivo;
    private long idPareja;
    private Date correccionFecha;
    private String correccionFechaStr="";
    private String correccionHora="";
    private String personaID="";
    private String observaciones;
    private Boolean tiene_pareja;
    private Boolean cerrado;
    private cierreDTO cierre;


    public marcaDTO(){
        
    }

    public marcaDTO(Date fecha, String hora, String idDispositivo, String dispositivo, String personaID) {
        this.fecha = fecha;
        this.hora = hora;
        this.idDispositivo = idDispositivo;
        this.dispositivo = dispositivo;
        this.personaID = personaID;
    }
     public marcaDTO(String fecha, String hora, String idDispositivo, String dispositivo, String personaID) {
        this.fecha = converters.StringDate(fecha, "yyyy/MM/dd");
        this.hora = hora;
        this.idDispositivo = idDispositivo;
        this.dispositivo = dispositivo;
        this.personaID = personaID;
    }

    public Date getCorreccionFecha() {
        return correccionFecha;
    }

    public void setCorreccionFecha(Date correccionFecha) {
        this.correccionFecha = correccionFecha;
        this.setCorreccionFechaStr(converters.DateString(this.correccionFecha, "yyyy-MM-dd"));
    }

    public String getCorreccionHora() {
        return correccionHora;
    }

    public void setCorreccionHora(String correccionHora) {
        this.correccionHora = correccionHora;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFechaStr() {
        this.fechaStr = converters.DateString(fecha, "dd/MM/yyyy");
        return this.fechaStr;
    }

    public void setFechaStr(String fecha) {
        this.fechaStr = fecha;
        //this.fecha= converters.StringDate(Fecha, "dd/MM/yyyy");
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public long getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(long idPareja) {
        this.idPareja = idPareja;
    }

    public String getPersonaID() {
        return personaID;
    }

    public void setPersonaID(String persona) {
        this.personaID = persona;
    }

    public String getCorreccionFechaStr() {
        return correccionFechaStr;
    }

    public void setCorreccionFechaStr(String correccionFechaStr) {
        this.correccionFechaStr = correccionFechaStr;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public cierreDTO getCierre() {
        return cierre;
    }

    public void setCierre(cierreDTO cierre) {
        this.cierre = cierre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getTiene_pareja() {
        return tiene_pareja;
    }

    public void setTiene_pareja(Boolean tiene_pareja) {
        this.tiene_pareja = tiene_pareja;
    }




}
