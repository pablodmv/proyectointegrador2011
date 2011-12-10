/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

import com.inout.util.converters;
import com.inout.util.diaSemana;

/**
 *
 * @author pablo
 */
public class horarioDTO {
    private Long Id;
    private Short diaSem;
    private String inicio;
    private String fin;
    private String salon;
    private personaDTO persona;
    private String observaciones;


    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public personaDTO getPersona() {
        return persona;
    }

    public void setPersona(personaDTO persona) {
        this.persona = persona;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Short getDiaSem() {
        return diaSem;
    }

    public void setDiaSem(Short diaSem) {
        this.diaSem = diaSem;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getDiaSemStr(){
        return converters.obtenerDia(diaSem).name();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }


    
    public horarioDTO() {
    }


    


}
