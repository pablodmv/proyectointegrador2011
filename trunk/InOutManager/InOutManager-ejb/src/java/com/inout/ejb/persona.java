/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.personaDTO;
import com.inout.dto.tarjetaDTO;
import com.inout.entities.Persona;
import com.inout.entities.Tarjeta;
import com.inout.util.converters;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pablo
 */
@Stateless
public class persona implements personaLocal {

    @PersistenceContext()
    private EntityManager em;

    @Override
    public String altaPersona(String documento, String nombre, String apellido, String direccion, String telefono1, String telefono2, String ingreso, Integer num_empleado) {

        Persona persona = new Persona();
        persona.setDocumento(documento);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDireccion(direccion);
        persona.setTelefono1(telefono1);
        persona.setTelefono2(telefono2);
        persona.setIngreso(converters.StringDate(ingreso, "dd/MM/yyyy"));
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

    @Override
    public personaDTO ObtenerPersona(String idPersona) {
        try {
        Persona persona = new Persona();
        persona= em.find(Persona.class, idPersona);
        return new personaDTO(persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono1(), persona.getTelefono2(), persona.getIngreso(), persona.getNumEmpleado());
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    public personaDTO ObtenerPersonaTarjeta(tarjetaDTO TarjetaDTO) {
        try {
            Tarjeta tarjeta = new Tarjeta();
            Persona persona = new Persona();
            tarjeta.setId(TarjetaDTO.getId());
            String jpl = "Select p FROM Persona p WHERE p.tarjeta=:tarjeta";
            Query q = em.createQuery(jpl);
            q.setParameter("tarjeta", tarjeta);
            persona = (Persona) q.getSingleResult();
            return new personaDTO(persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono1(), persona.getTelefono2(), persona.getIngreso(), persona.getNumEmpleado());


        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PERSONA POR TARJETA " + e.getMessage());
            ;
        }
        return null;
    }
}
