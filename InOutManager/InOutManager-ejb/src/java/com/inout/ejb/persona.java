/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.entities.Persona;
import com.inout.util.converters;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pablo
 */
@Stateless
public class persona implements personaLocal {

     @PersistenceContext()
    private EntityManager em;


    @Override
    public Persona ObtenerPersona(String idPersona) {
        return em.find(Persona.class, idPersona);
    }

    @Override
    public String altaPersona(String documento, String nombre, String apellido, String direccion, String telefono1, String telefono2, String ingreso, Integer num_empleado) {

        Persona persona = new Persona();
        persona.setDocumento(documento);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDireccion(direccion);
        persona.setTelefono1(telefono1);
        persona.setTelefono2(telefono2);
        persona.setIngreso(converters.StringDate(ingreso,"dd/MM/yyyy"));
        persona.setNumEmpleado(Long.parseLong(num_empleado.toString()));

        try {
        em.persist(persona);
        em.flush();
        return "Success";
        } catch (Exception e) {
            System.out.println("Alta persona:" + e.getMessage());
        }

        return "Fail";
    }
    
 
}
