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

    public Boolean altaPersona(personaDTO PersonaDTO, String usuario);
    public personaDTO ObtenerPersona(String idPersona, String usuario);
    public personaDTO ObtenerPersonaTarjeta(tarjetaDTO Tarjeta, String usuario);
    public Boolean eliminarPersona(personaDTO PersonaDTO, String usuario);
    public Boolean modificarPersona(personaDTO PersonaDTO, String usuario);
    public Persona convertirDTOPersona(personaDTO PersonaDTO);
}
