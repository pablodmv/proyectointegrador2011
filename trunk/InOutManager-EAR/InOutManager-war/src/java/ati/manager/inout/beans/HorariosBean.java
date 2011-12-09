/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import com.inout.dto.personaDTO;
import com.inout.util.diaSemana;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author pablo
 */
public class HorariosBean {

    private String Documento;
    private personaDTO persona;
    private String inicio;
    private String fin;
    private String salon;
    private String observaciones;
    private Integer DiaSemana;
    private List<SelectItem> diaSemanaColection;
    private String msgSuccess;

    /** Creates a new instance of HorariosMB */
    public HorariosBean() {
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Integer getDiaSemana() {
        return DiaSemana;
    }

    public void setDiaSemana(Integer DiaSemana) {
        this.DiaSemana = DiaSemana;
    }

    public List<SelectItem> getDiaSemanaColection() {
        return diaSemanaColection;
    }

    public void setDiaSemanaColection(List<SelectItem> diaSemanaColection) {
        this.diaSemanaColection = diaSemanaColection;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }







}
