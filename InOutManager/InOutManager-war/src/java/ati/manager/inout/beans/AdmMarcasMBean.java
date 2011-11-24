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
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author gustavo
 */
@Named(value="admMarcasMBean")
@Dependent
public class AdmMarcasMBean {

    private List<marcaDTO> markSelectItems = null;
    private int currentMarkIndex;
    private marcaDTO editedMark;
    private int page = 1;
    

    /** Creates a new instance of AdmMarcasMBean */
    public AdmMarcasMBean() {
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            String str_date = "2011-11-21";
            date = (Date) formatter.parse(str_date);
            Facade f = Facade.getInstance();
            markSelectItems = f.getMarKByDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(AdmMarcasMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove() {
        markSelectItems.remove(markSelectItems.get(currentMarkIndex));
    }

    public void store() {
        markSelectItems.add(currentMarkIndex, editedMark);
    }

    public List<marcaDTO> getMarkSelectItems() {

        return this.markSelectItems;
    }


    public int getCurrentMarkIndex() {
        return currentMarkIndex;
    }

    public void setCurrentMarkIndex(int currentMarkIndex) {
        this.currentMarkIndex = currentMarkIndex;
    }

    public marcaDTO getEditedMark() {

        return this.markSelectItems.get(this.currentMarkIndex);
    }

    public void setEditedMark(marcaDTO editedMark) {
        this.editedMark = editedMark;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
