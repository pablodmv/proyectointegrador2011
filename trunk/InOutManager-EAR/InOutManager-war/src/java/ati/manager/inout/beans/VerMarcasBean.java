/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import ati.manager.inout.excelGenerator.ExcelGenerator;
import ati.manager.inout.facade.Facade;
import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.oreilly.servlet.ServletUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gustavo
 */
public class VerMarcasBean {

    private List<marcaDTO> markSelectItems = null;
    private marcaDTO selectedMark = new marcaDTO();
    private String selectDocPerson = "";
    private Date selectDate = new Date();
    private Date selectDateFin = new Date();

    /** Creates a new instance of VerMarcasBean */
    public VerMarcasBean() {
        
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }

    public Date getSelectDateFin() {
        return selectDateFin;
    }

    public void setSelectDateFin(Date selectDateFin) {
        this.selectDateFin = selectDateFin;
    }

    public String getSelectDocPerson() {
        return selectDocPerson;
    }

    public void setSelectDocPerson(String selectDocPerson) {
        this.selectDocPerson = selectDocPerson;
    }

    
    public List<marcaDTO> getMarkSelectItems() {
        return markSelectItems;
    }

    public void setMarkSelectItems(List<marcaDTO> markSelectItems) {
        this.markSelectItems = markSelectItems;
    }

    public marcaDTO getSelectedMark() {
       // marcaDTO auxMarca = selectedMark;
        return selectedMark;
    }

    public void setSelectedMark(marcaDTO selectedMark) {
        this.selectedMark = selectedMark;
    }

    public List<marcaDTO> searchMarca(){
        Facade f = Facade.getInstance();
        if(!this.selectDocPerson.equals("") && this.selectDate != null && this.selectDateFin != null){
            personaDTO persona = f.searchPerson(selectDocPerson);
            markSelectItems = f.getMarkByPersonDate(persona,this.selectDate,this.selectDateFin);
        }else if(this.selectDate != null && this.selectDateFin != null){
            markSelectItems = f.getMarkByDateRange(selectDate, selectDateFin);
        }
        
        return markSelectItems;
    }

    public String editarMarca(){
        Facade f = Facade.getInstance();
        Boolean result = f.editMark(selectedMark);

        if(result){
            return "edit mark succesfull";
        }else{
            return "edit mark unsuccessfull";
        }
    }

    public void xlsGenerator(ActionEvent event){
        try {
            System.out.println("Paso por xlsGenerator");
            ExcelGenerator exGen = ExcelGenerator.getInstance();
            exGen.reportGenerator(markSelectItems);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "attachment; filename=excelReport.xls");
            response.setContentType("application/vnd.ms-excel");
            ServletUtils.returnFile(System.getProperty("user.home") + "/excelReport.xls", response.getOutputStream());

            FacesContext faces = FacesContext.getCurrentInstance();
            faces.responseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

}
