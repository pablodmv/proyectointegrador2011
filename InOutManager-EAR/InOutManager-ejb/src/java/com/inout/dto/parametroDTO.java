/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

/**
 *
 * @author pablo
 */
public class parametroDTO {
    private Long Id;
    private String Descripcion;
    private String valor;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public parametroDTO(String Descripcion, String valor) {
        this.Descripcion = Descripcion;
        this.valor = valor;
    }



}
