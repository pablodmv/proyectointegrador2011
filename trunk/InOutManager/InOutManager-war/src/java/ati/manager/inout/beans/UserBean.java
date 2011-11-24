/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author gustavo
 */
@Named(value="userBean")
@Dependent
public class UserBean {

    private String name;
    /** Creates a new instance of UserBean */
    public UserBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
