/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import ati.manager.inout.facade.Facade;
import com.inout.dto.personaDTO;
import java.util.Date;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;


/**
 *
 * @author gustavo
 */
public class PersonaBean {

    private String nombre;
    private String apellido;
    private String doc;
    private String tel1;
    private String tel2;
    private String dir;
    private Date fechaIngreso;
    private long numEmpleado;
    private String idTarjeta;

    private Boolean nombreInputValid;
    private Boolean apellidoInputValid;
    private Boolean docInputValid;
    private Boolean tel1InputValid;
    private Boolean tel2InputValid;
    private Boolean dirInputValid;
    private Boolean fechaIngresoInputValid;
    private Boolean numEmpleadoInputValid;

    private String msgSuccess;

    private personaDTO selectedPerson = new personaDTO();

    /** Creates a new instance of PersonaBean */
    public PersonaBean() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(long numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public personaDTO getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(personaDTO selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }
    

    //******************Validations ******************************

    public Boolean getApellidoInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:apellido");

        this.apellidoInputValid =  input.isValid();

        return apellidoInputValid;
    }

    public void setApellidoInputValid(Boolean apellidoInputValid) {
        this.apellidoInputValid = apellidoInputValid;
    }

    public Boolean getDirInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:dir");
        this.dirInputValid =  input.isValid();
        return dirInputValid;
    }

    public void setDirInputValid(Boolean dirInputValid) {
        this.dirInputValid = dirInputValid;
    }

    public Boolean getDocInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:doc");
        this.docInputValid =  input.isValid();
        return docInputValid;
    }

    public void setDocInputValid(Boolean docInputValid) {
        this.docInputValid = docInputValid;
    }

    public Boolean getFechaIngresoInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:fechaIngreso");
        this.fechaIngresoInputValid =  input.isValid();
        return fechaIngresoInputValid;
    }

    public void setFechaIngresoInputValid(Boolean fechaIngresoInputValid) {
        this.fechaIngresoInputValid = fechaIngresoInputValid;
    }

    public Boolean getNombreInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:nombre");
        this.nombreInputValid =  input.isValid();
        return nombreInputValid;
    }

    public void setNombreInputValid(Boolean nombreInputValid) {
        this.nombreInputValid = nombreInputValid;
    }

    public Boolean getNumEmpleadoInputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:numEmpleado");
        this.numEmpleadoInputValid =  input.isValid();
        return numEmpleadoInputValid;
    }

    public void setNumEmpleadoInputValid(Boolean numEmpleadoInputValid) {
        this.numEmpleadoInputValid = numEmpleadoInputValid;
    }

    public Boolean getTel1InputValid() {
        UIInput input = (UIInput)FacesContext.getCurrentInstance().getViewRoot().findComponent("form:tel1");
        this.tel1InputValid =  input.isValid();
        return tel1InputValid;
    }

    public void setTel1InputValid(Boolean tel1InputValid) {
        this.tel1InputValid = tel1InputValid;
    }

    public Boolean getTel2InputValid() {
        return tel2InputValid;
    }

    public void setTel2InputValid(Boolean tel2InputValid) {
        this.tel2InputValid = tel2InputValid;
    }


    // *******************Actions*********************************

    public String prueba(){
        System.out.println("SUccess");
        return"";
    }

    public String save(){

        this.selectedPerson.setApellido(apellido);
        this.selectedPerson.setDireccion(dir);
        this.selectedPerson.setDocumento(doc);
        this.selectedPerson.setIngreso(fechaIngreso);
        this.selectedPerson.setNombre(nombre);
        this.selectedPerson.setNumEmpleado(numEmpleado);
        this.selectedPerson.setTelefono1(tel1);
        this.selectedPerson.setTelefono2(tel2);

        Facade f = Facade.getInstance();
        String result = "";
        if(f.savePerson(selectedPerson)){
         result = "Ingreso exitoso.";
        }else{
            result = "Hubo un error al ingresar la persona.";
        }
        this.msgSuccess = result;
        System.out.println(result);
        return "";
    }

    public String search(){
        
        Facade f = Facade.getInstance();
        this.selectedPerson = f.searchPerson(this.doc);

        this.setNombre(this.selectedPerson.getNombre());
        this.setApellido(this.selectedPerson.getApellido());
        this.setDir(this.selectedPerson.getDireccion());
        this.setFechaIngreso(this.selectedPerson.getIngreso());
        this.setNumEmpleado(this.selectedPerson.getNumEmpleado());
        this.setTel1(this.selectedPerson.getTelefono1());
        this.setTel2(this.selectedPerson.getTelefono2());

        System.out.println("Success Search");
        return "";
    }


}
