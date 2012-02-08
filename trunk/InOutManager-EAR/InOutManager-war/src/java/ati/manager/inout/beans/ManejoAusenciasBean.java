/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ati.manager.inout.beans;

import ati.manager.inout.excelGenerator.ExcelGenerator;
import ati.manager.inout.facade.Facade;
import com.inout.dto.ausenciaDTO;
import com.inout.dto.horarioDTO;
import com.inout.dto.motivoausenciaDTO;
import com.inout.dto.personaDTO;
import com.inout.util.converters;
import com.oreilly.servlet.ServletUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
public class ManejoAusenciasBean {

    private String Documento;
    private personaDTO persona;
    private String msgSuccess;
    private List<ausenciaDTO> ausencias;
    private Boolean refrescar = Boolean.FALSE;
    private Boolean mostrarModal = Boolean.FALSE;
    private Short mes;
    private Short anio;
    private String msgResultado;
    private ausenciaDTO selectedAusencia;
    private List<motivoausenciaDTO> motivoAusencia;
    private Integer idMotivo;
    private String observaciones;
    private List<horarioDTO> horarios;
    private Integer idHorario;
    private List<SelectItem> horariosItem;
    private List<SelectItem> motivosItem;

    /** Creates a new instance of HorariosMB */
    public ManejoAusenciasBean() {
    }

    public List<SelectItem> getHorariosItem() {
        return horariosItem;
    }

    public void setHorariosItem(List<SelectItem> horariosItem) {
        this.horariosItem = horariosItem;
    }

    public List<SelectItem> getMotivosItem() {
        return motivosItem;
    }

    public void setMotivosItem(List<SelectItem> motivosItem) {
        this.motivosItem = motivosItem;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<motivoausenciaDTO> getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(List<motivoausenciaDTO> motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    public ausenciaDTO getSelectedAusencia() {
        return selectedAusencia;
    }

    public void setSelectedAusencia(ausenciaDTO selectedAusencia) {
        this.selectedAusencia = selectedAusencia;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getMsgResultado() {
        return msgResultado;
    }

    public void setMsgResultado(String msgResultado) {
        this.msgResultado = msgResultado;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Short getMes() {
        return mes;
    }

    public void setMes(Short mes) {
        this.mes = mes;
    }

    public Boolean getMostrarModal() {
        return mostrarModal;
    }

    public void setMostrarModal(Boolean mostrarModal) {
        this.mostrarModal = mostrarModal;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }

    public personaDTO getPersona() {
        return persona;
    }

    public void setPersona(personaDTO persona) {
        this.persona = persona;
    }

    public Boolean getRefrescar() {
        return refrescar;
    }

    public void setRefrescar(Boolean refrescar) {
        this.refrescar = refrescar;
    }

    public List<ausenciaDTO> getAusencias() {
        return ausencias;
    }

    public void setAusencias(List<ausenciaDTO> ausencias) {
        this.ausencias = ausencias;
    }

    public List<horarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<horarioDTO> horarios) {
        this.horarios = horarios;
    }

    public void obtenerPersona() {
        this.msgSuccess = "";
        Facade f = Facade.getInstance();
        persona = f.searchPerson(Documento);

        System.out.println("");

    }

    private void limpiar() {
        this.Documento = "";
        this.persona = null;
    }

    public void searchAusencias() {
        msgResultado = "";
        Facade f = Facade.getInstance();
        persona = f.searchPerson(Documento);
        motivoAusencia = f.searchMotivoAusencias();

        mostrarModal = Boolean.TRUE;
        Calendar date = Calendar.getInstance();
        Calendar actual = Calendar.getInstance();
        Integer mesAux = 0;
        if (mes >= 0 && mes <= 12 && anio <= actual.get(Calendar.YEAR)) {
            mesAux = mes - 1; //Resto uno al mes para setear mes correcto en Calendar.
            date.set(Calendar.YEAR, anio);
            date.set(Calendar.MONTH, mesAux);
            date.set(Calendar.DAY_OF_MONTH, 1);
            if (date.get(Calendar.MONTH)>= date.getMinimum(Calendar.MONTH) && date.get(Calendar.MONTH) <= date.getMaximum(Calendar.MONTH)) {
                // if (dateFin.get(Calendar.MONTH)<actual.get(Calendar.MONTH) && dateFin.get(Calendar.YEAR)<=actual.get(Calendar.YEAR)) {
                ausencias = f.searchAusencias(persona, date.getTime());
                if (ausencias == null || ausencias.isEmpty()) {
                    msgResultado = "La persona seleccionada no tiene ausencias";
                }
                //}else{
                //msgResultado="No se puede cerrar el mes corriente";
                //    }
            } else {
                msgResultado = "Seleccione mes y año valido";
            }
        } else {
            msgResultado = "Seleccione mes y año valido";
        }

    }

    public void xlsgenerator() {
         try {
            String fecha = converters.DateString(new Date(), "yyyyMMddHHmm");
            System.out.println("Paso por xlsGenerator");
            ExcelGenerator exGen = ExcelGenerator.getInstance();
            exGen.reportAusenciaGenerator(ausencias);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "attachment; filename=excelAusenciasReport_"+ fecha+".xls");
            response.setContentType("application/vnd.ms-excel");
            //ServletUtils.returnFile(System.getProperty("user.home") + "/excelReport.xls", response.getOutputStream());
            ServletUtils.returnFile(System.getProperty("user.home") + "/excelAusenciasReport.xls", response.getOutputStream());
            FacesContext faces = FacesContext.getCurrentInstance();
            faces.responseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        Facade f = Facade.getInstance();
        selectedAusencia.setObservacion(observaciones);
        selectedAusencia.setMotivo(f.getMotivo(idMotivo.longValue()));
        selectedAusencia.setHorario(f.getHorario(idHorario.longValue()));

        if (f.saveAusencia(selectedAusencia)) {
            searchAusencias();
            msgResultado = "Se actualizo la falta correctamente";
        } else {
            msgResultado = "Error al actualizar la ausencia";
        }
    }

    public void eliminar() {
    }

    public void seleccionarFila() {
        cargarComboAusencias();
        cargarComboHorarios();
        observaciones = selectedAusencia.getObservacion();
        if (selectedAusencia.getMotivo()!=null) {
        idMotivo = selectedAusencia.getMotivo().getID().intValue();
        }
        if (selectedAusencia.getHorario()!=null) {
        horarios = selectedAusencia.getPersona().getHorariosCollection();
        }
        
        
    }

    public void cargarComboAusencias() {
        motivosItem = new ArrayList<SelectItem>();
        for (motivoausenciaDTO motivo : motivoAusencia) {
            SelectItem sItem = new SelectItem(motivo.getID(), motivo.getMotivo());
            motivosItem.add(sItem);
        }
    }

    public void cargarComboHorarios() {
        Facade f = Facade.getInstance();
        horariosItem = new ArrayList<SelectItem>();
        for (horarioDTO horaDTO : selectedAusencia.getPersona().getHorariosCollection()) {
            SelectItem sItem = new SelectItem(horaDTO.getId(), horaDTO.toString());
            horariosItem.add(sItem);
        }
    }
}
