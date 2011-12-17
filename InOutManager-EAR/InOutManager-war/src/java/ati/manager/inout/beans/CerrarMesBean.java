package ati.manager.inout.beans;

import ati.manager.inout.facade.Facade;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pablo
 */
public class CerrarMesBean {

    private Integer mes;
    private Integer anio;
    private Boolean mostrarResultado;
    private String msgResultado;

    public CerrarMesBean() {
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Boolean getMostrarResultado() {
        return mostrarResultado;
    }

    public void setMostrarResultado(Boolean mostrarResultado) {
        this.mostrarResultado = mostrarResultado;
    }

    public String getMsgResultado() {
        return msgResultado;
    }

    public void setMsgResultado(String msgResultado) {
        this.msgResultado = msgResultado;
    }

    public void cerrarMes() {
        Facade f = Facade.getInstance();
        Calendar date = Calendar.getInstance();
        Calendar dateFin = Calendar.getInstance();
        Calendar actual = Calendar.getInstance();
        Integer mesAux=0;

        if (mes >= 0 &&  mes<=12 && anio <= actual.get(Calendar.YEAR)) {
            mesAux=mes-1; //Resto uno al mes para setear mes correcto en Calendar.
            date.set(Calendar.YEAR, anio);
            date.set(Calendar.MONTH, mesAux);
            date.set(Calendar.DAY_OF_MONTH, 1);
            dateFin.set(Calendar.YEAR, anio);
            dateFin.set(Calendar.MONTH, mesAux);
            dateFin.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
            if (date.get(Calendar.MONTH) <= actual.get(Calendar.MONTH)) {
                // if (dateFin.get(Calendar.MONTH)<actual.get(Calendar.MONTH) && dateFin.get(Calendar.YEAR)<=actual.get(Calendar.YEAR)) {
                mostrarResultado = f.cierreMes(date.getTime(), dateFin.getTime());
                if (mostrarResultado) {
                msgResultado = "Mes cerrado correctamente";
                anio=null;
                mesAux = null;
                mes=null;
                }else{
                    msgResultado = "El mes ya esta cerrado o no tiene datos.";
                }
                //}else{
                //msgResultado="No se puede cerrar el mes corriente";
                //    }
            } else {
                msgResultado = "Seleccione mes y año valido";
            }
        }else{
                msgResultado = "Seleccione mes y año valido";
        }
    }
}
