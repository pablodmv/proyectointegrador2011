/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.dto;

import com.inout.entities.Tarjeta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pablo
 */
public class personaDTO implements Serializable {

    private String documento;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private Date ingreso;
    private Long numEmpleado;
    private tarjetaDTO tarjeta;
    private List<marcaDTO> marcaCollection;

    public personaDTO(){
        
    }

    public personaDTO(String documento, String nombre, String apellido, String direccion, String telefono1, String telefono2, Date ingreso, Long numEmpleado, tarjetaDTO tarjeta, List<marcaDTO> marcaCollection) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.ingreso = ingreso;
        this.numEmpleado = numEmpleado;
        this.tarjeta = tarjeta;
        this.marcaCollection = marcaCollection;
    }

    public personaDTO(String documento, String nombre, String apellido, String direccion, String telefono1, String telefono2, Date ingreso, Long numEmpleado) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.ingreso = ingreso;
        this.numEmpleado = numEmpleado;
    }



    public personaDTO(String documento, String nombre, String apellido, tarjetaDTO tarjeta) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tarjeta = tarjeta;
    }

    public personaDTO(String personaID) {
        this.documento=personaID;

    }

    public String getNombreCompleto() {
        this.nombreCompleto = this.nombre + " " + this.apellido;
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public List<marcaDTO> getMarcaCollection() {
        return marcaCollection;
    }

    public void setMarcaCollection(List<marcaDTO> marcaCollection) {
        this.marcaCollection = marcaCollection;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(Long numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public tarjetaDTO getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(tarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String nombreCompleto(){
        return this.nombre + " "+this.apellido;
    }


    




}
