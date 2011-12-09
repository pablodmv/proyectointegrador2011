/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import ati.manager.inout.facade.Facade;
import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author gustavo
 */
public class VerMarcasBean {

    private List<marcaDTO> markSelectItems = null;
    private marcaDTO selectedMark = new marcaDTO();
    private String selectDocPerson = "";
    private Date selectDate = new Date();

    /** Creates a new instance of VerMarcasBean */
    public VerMarcasBean() {
        
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
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
        personaDTO persona = f.searchPerson(selectDocPerson);
        markSelectItems = f.getMarKByPersonDate(persona,this.selectDate);
        
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


    public void onRowSelect(SelectEvent event){
        System.out.println("Fila seleccionada.");
    }

}
