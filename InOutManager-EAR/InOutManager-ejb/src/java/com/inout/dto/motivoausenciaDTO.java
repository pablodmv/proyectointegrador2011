/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

/**
 *
 * @author pablo
 */
public class motivoausenciaDTO {

    private Long ID;
    private String motivo;

    public motivoausenciaDTO(String motivo) {
        this.motivo = motivo;
    }

    public motivoausenciaDTO() {
        
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return motivo ;
    }


    

}
