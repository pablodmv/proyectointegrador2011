/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;


/**
 *
 * @author gustavo
 */
public class LoginBean {

    private String usuario;
    private String pwd;

    /** Creates a new instance of LoginBean */
    public LoginBean() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String loggin(){
        System.out.println(this.usuario + " - " + this.pwd);
        if(this.usuario.equals("admin") && this.pwd.equals("admin")){
            return "verMarcas";
        }else if(this.usuario.equals("user") && this.pwd.equals("user")){
            return "misMarcas";
        }
        return "";
    }

}
