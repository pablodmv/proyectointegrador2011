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

    private int ID;
    private String mes;
    private int ano;

    public cierreDTO(String mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }




}
