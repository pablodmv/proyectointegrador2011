/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ati.manager.inout.beans;

import ati.manager.inout.excelGenerator.ExcelGenerator;
import ati.manager.inout.facade.Facade;
import com.inout.dto.horarioDTO;
import com.inout.dto.personaDTO;
import com.inout.ejb.ausenciasLocal;
import com.inout.util.converters;
import com.inout.util.diaSemana;
import com.oreilly.servlet.ServletUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
public class HorariosBean {

    private String Documento;
    private personaDTO persona;
    private String inicio = "";
    private String fin;
    private String salon;
    private String observaciones;
    private Short DiaSemana;
    private List<SelectItem> diaSemanaColection;
    private String msgSuccess;
    private Boolean refrescar = Boolean.FALSE;
    private List<horarioDTO> horarioSelectItems;
    private List<horarioDTO> horarioSelectItemsCache;
    private horarioDTO selectedHorario = new horarioDTO();
    private Boolean mostrarModal = Boolean.FALSE;

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

    public Short getDiaSemana() {
        return DiaSemana;
    }

    public void setDiaSemana(Short DiaSemana) {
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

    public Boolean getRefrescar() {
        return refrescar;

    }

    public void setRefrescar(Boolean refrescar) {
        this.refrescar = refrescar;
    }

    public horarioDTO getSelectedHorario() {
        horarioDTO auxHorario = selectedHorario;
        return selectedHorario;
    }

    public void setSelectedHorario(horarioDTO selectedHorario) {
        this.selectedHorario = selectedHorario;
        this.msgSuccess = "";
    }

    public void obtenerPersona() {
        this.msgSuccess = "";
        Facade f = Facade.getInstance();
        persona = f.searchPerson(Documento);
        System.out.println("");

    }

    public List<horarioDTO> getHorarioSelectItems() {
        return horarioSelectItems;
    }

    public void setHorarioSelectItems(List<horarioDTO> horarioSelectItems) {
        this.horarioSelectItems = horarioSelectItems;
    }

    public List<horarioDTO> getHorarioSelectItemsCache() {
        return horarioSelectItemsCache;
    }

    public void setHorarioSelectItemsCache(List<horarioDTO> horarioSelectItemsCache) {
        this.horarioSelectItemsCache = horarioSelectItemsCache;
    }

    public Boolean getMostrarModal() {
        return mostrarModal;
    }

    public void setMostrarModal(Boolean mostrarModal) {
        this.mostrarModal = mostrarModal;
    }

    @PostConstruct
    public void comboDiaSemana() {
        diaSemanaColection = new ArrayList<SelectItem>();
        diaSemanaColection.add(new SelectItem(diaSemana.Domingo.ordinal(), diaSemana.Domingo.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Lunes.ordinal(), diaSemana.Lunes.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Martes.ordinal(), diaSemana.Martes.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Miercoles.ordinal(), diaSemana.Miercoles.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Jueves.ordinal(), diaSemana.Jueves.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Viernes.ordinal(), diaSemana.Viernes.name()));
        diaSemanaColection.add(new SelectItem(diaSemana.Sabado.ordinal(), diaSemana.Sabado.name()));
    }

    public void guardar() {
        if (validar()) {


            Facade f = Facade.getInstance();
            horarioDTO horario = new horarioDTO();
            horario.setDiaSem(DiaSemana);
            horario.setFin(fin);
            horario.setInicio(inicio);
            horario.setPersona(persona);
            horario.setSalon(salon);
            horario.setObservaciones(observaciones);
            if (f.saveHorario(horario)) {
                msgSuccess = "Se guardo correctamente";
                limpiar();
                refrescar = Boolean.TRUE;
                searchHorarios();
            } else {
                msgSuccess = "Ocurrio un error";
            }
        }

    }

    private void limpiar() {
        this.DiaSemana = null;
        this.Documento = "";
        this.fin = "";
        this.inicio = "";
        this.persona = null;
        this.salon = "";
        this.observaciones = "";
    }

    private Boolean validar() {
        try {
            if (parserHora(inicio) == null) {
                msgSuccess = "Hora inicio invalida. Formato HH:mm";
                return false;
            }
            if (parserHora(fin) == null) {
                msgSuccess = "Hora final invalida. Formato HH:mm";
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;


    }

    private Boolean validarModal() {
        try {
            if (parserHora(selectedHorario.getInicio()) == null) {
                msgSuccess = "Hora inicio invalida. Formato HH:mm";
                return false;
            }
            if (parserHora(selectedHorario.getFin()) == null) {
                msgSuccess = "Hora final invalida. Formato HH:mm";
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HorariosBean.class.getName()).log(Level.SEVERE, null, ex);
            msgSuccess = "Error en la hora ingresada. El Formato es HH:mm";
        }


        return null;


    }

    public Date parserHora(String hora) {
        try {
            Date inicioDate = null;
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            inicioDate = sf.parse(hora);
            if (!(Integer.parseInt(hora.split(":")[0]) >= 0) || !(Integer.parseInt(hora.split(":")[0]) <= 24)
                    || !(Integer.parseInt(hora.split(":")[1]) >= 0) || !(Integer.parseInt(hora.split(":")[1]) <= 59)) {
                return null;
            }


            return inicioDate;


        } catch (ParseException ex) {
            return null;

        }
    }

    public void eliminar() {
        Facade f = Facade.getInstance();
        if (f.deleteHorario(selectedHorario)) {
            searchHorarios();
            msgSuccess = "Modificacion correcta";
        } else {
            msgSuccess = "Ocurrio un error";
        }

    }

    public List<horarioDTO> searchHorarios() {

        Facade f = Facade.getInstance();
        persona = f.searchPerson(Documento);
        horarioSelectItems = f.searchHorarios(persona);
        mostrarModal = Boolean.TRUE;
        return horarioSelectItems;
    }

    public Boolean modificar() {
        try {
            if (validarModal()) {


                refrescar = Boolean.FALSE;
                Facade f = Facade.getInstance();
                Boolean result = f.editHorario(selectedHorario);

                if (result) {
                    searchHorarios();
                    refrescar = Boolean.TRUE;
                    msgSuccess = "Modificacion correcta";
                    return true;

                } else {
                    searchHorarios();
                    msgSuccess = "Ocurrio un error";
                    return false;
                }
            } else {
                searchHorarios();
                return false;
            }
        } catch (Exception e) {
            searchHorarios();
            return false;

        }

    }

    public void xlsGenerator(ActionEvent event) {
        try {
            String fecha = converters.DateString(new Date(), "yyyyMMddHHmm");
            System.out.println("Paso por xlsGenerator");
            ExcelGenerator exGen = ExcelGenerator.getInstance();
            exGen.reportHorarioGenerator(horarioSelectItems);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "attachment; filename=excelHorariosReport_"+ fecha+".xls");
            response.setContentType("application/vnd.ms-excel");
            //ServletUtils.returnFile(System.getProperty("user.home") + "/excelReport.xls", response.getOutputStream());
            ServletUtils.returnFile(System.getProperty("user.home") + "/excelHorariosReport.xls", response.getOutputStream());
            FacesContext faces = FacesContext.getCurrentInstance();
            faces.responseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
