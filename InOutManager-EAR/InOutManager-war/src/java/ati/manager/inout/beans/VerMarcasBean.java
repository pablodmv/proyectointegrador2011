/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import ati.manager.inout.facade.Facade;
import com.inout.dto.marcaDTO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gustavo
 */
public class VerMarcasBean {

    private List<marcaDTO> markSelectItems = null;
    private marcaDTO selectedMark = new marcaDTO();

    /** Creates a new instance of VerMarcasBean */
    public VerMarcasBean() {
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            String str_date = "2011-11-21";
            date = (Date) formatter.parse(str_date);
            Facade f = Facade.getInstance();
            markSelectItems = f.getMarKByDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(VerMarcasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
