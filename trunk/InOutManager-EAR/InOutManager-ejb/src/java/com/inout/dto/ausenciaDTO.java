/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

import com.inout.entities.MotivoAusencia;
import com.inout.entities.Persona;
import java.util.Date;

/**
 *
 * @author pablo
 */
public class ausenciaDTO {


    private Integer ID;
    private Persona persona;
    private Date fecha;
    private MotivoAusencia motivo;
    private String observacion;

    public ausenciaDTO(Persona persona, Date fecha, MotivoAusencia motivo) {
        this.persona = persona;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MotivoAusencia getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAusencia motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    



}
