/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

/**
 *
 * @author pablo
 */
public class cierreDTO {

    private Long ID;
    private String mes;
    private Short ano;

    public cierreDTO(String mes, Short ano) {
        this.mes = mes;
        this.ano = ano;
    }

    public cierreDTO() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }




}
