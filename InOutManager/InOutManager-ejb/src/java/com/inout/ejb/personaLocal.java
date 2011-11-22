/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.personaDTO;
import com.inout.dto.tarjetaDTO;
import com.inout.entities.Persona;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface personaLocal {

    public String altaPersona(String documento, String nombre, String apellido, String direccion, String telefono1, String telefono2, String ingreso, Integer num_empleado);

    public personaDTO ObtenerPersona(String idPersona);

    public personaDTO ObtenerPersonaTarjeta(tarjetaDTO Tarjeta);
}
