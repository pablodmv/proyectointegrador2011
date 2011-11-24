/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;


import ati.manager.inout.utils.StringUtils;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author gustavo
 */
@Named(value="menuMBean")
@Dependent
public class MenuMBean {

    /** Creates a new instance of MenuMBean */
    public MenuMBean() {
    }

    public String goVerMarcas(){
        return StringUtils.GO_VER_MARCAS;
    }

    public String goAltaDocente(){
        return StringUtils.GO_ALTA_DOCENTE;
    }

}
