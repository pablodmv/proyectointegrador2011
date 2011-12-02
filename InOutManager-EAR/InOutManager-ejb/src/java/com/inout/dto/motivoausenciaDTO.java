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

    private int ID;
    private String motivo;

    public motivoausenciaDTO(String motivo) {
        this.motivo = motivo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    

}
