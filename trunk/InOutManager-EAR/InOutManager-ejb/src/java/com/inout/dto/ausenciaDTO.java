    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.dto;

import com.inout.entities.Persona;
import com.inout.util.converters;
import java.util.Date;

/**
 *
 * @author pablo
 */
public class ausenciaDTO {

    private Long ID;
    private personaDTO persona;
    private Date fecha;
    private motivoausenciaDTO motivo;
    private String observacion;
    private horarioDTO horario;

    public ausenciaDTO(personaDTO persona, Date fecha, motivoausenciaDTO motivo) {
        this.persona = persona;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public ausenciaDTO() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public motivoausenciaDTO getMotivo() {
        return motivo;
    }

    public void setMotivo(motivoausenciaDTO motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public personaDTO getPersona() {
        return persona;
    }

    public void setPersona(personaDTO persona) {
        this.persona = persona;
    }

    public horarioDTO getHorario() {
        return horario;
    }

    public void setHorario(horarioDTO horario) {
        this.horario = horario;
    }

    public String getFechaStr() {
        return converters.DateString(fecha, "dd/MM/yyyy");
    }


}
