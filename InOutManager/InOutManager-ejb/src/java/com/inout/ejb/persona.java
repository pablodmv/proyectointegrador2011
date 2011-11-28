/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.personaDTO;
import com.inout.dto.tarjetaDTO;
import com.inout.entities.Log;
import com.inout.entities.Persona;
import com.inout.entities.Tarjeta;
import com.inout.util.converters;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author pablo
 */
@Stateless
public class persona implements personaLocal {

    @PersistenceContext()
    private EntityManager em;
    @EJB
    private loggerJMSLocal Logger;

    @Override
    public Boolean altaPersona(personaDTO PersonaDTO, String userLogin) {
        try {
            em.persist(convertirDTOPersona(PersonaDTO));
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Alta persona:" + e.getMessage());
        }

        return false;
    }

    @Override
    public personaDTO ObtenerPersona(String idPersona, String userLogin) {
        try {
            Persona persona = new Persona();
            persona = em.find(Persona.class, idPersona);
            return new personaDTO(persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono1(), persona.getTelefono2(), persona.getIngreso(), persona.getNumEmpleado());
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public personaDTO ObtenerPersonaTarjeta(tarjetaDTO TarjetaDTO, String userLogin) {
        try {
            Tarjeta tarjeta = new Tarjeta();
            Persona persona = new Persona();
            tarjeta.setId(TarjetaDTO.getId());
            String jpl = "Select p FROM Persona p WHERE p.tarjeta=:tarjeta";
            Query q = em.createQuery(jpl);
            q.setParameter("tarjeta", tarjeta);
            persona = (Persona) q.getSingleResult();
            //Logueo
            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion("ObtenerPersonaTarjeta");
            bit.setUsuario(userLogin);
            //Logger.log(bit);

            return new personaDTO(persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono1(), persona.getTelefono2(), persona.getIngreso(), persona.getNumEmpleado());


        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PERSONA POR TARJETA " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean eliminarPersona(personaDTO PersonaDTO, String userLogin) {
        try {
            em.remove(convertirDTOPersona(PersonaDTO));
            em.flush();
            //Logueo
            Logger.loggerMessage("eliminarPersona", userLogin, "IdMarca: " + PersonaDTO.getDocumento());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean modificarPersona(personaDTO PersonaDTO, String userLogin) {
        try {
            em.merge(convertirDTOPersona(PersonaDTO));
            em.flush();
            //Logueo
            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion("modificarPersona");
            bit.setUsuario(userLogin);
            //Logger.log(bit);
            return true;
        } catch (Exception e) {
        }

        return false;




    }

    private Persona convertirDTOPersona(personaDTO PersonaDTO) {
        Persona persona = new Persona();
        persona.setDocumento(PersonaDTO.getDocumento());
        persona.setNombre(PersonaDTO.getNombre());
        persona.setApellido(PersonaDTO.getApellido());
        persona.setDireccion(PersonaDTO.getDireccion());
        persona.setTelefono1(PersonaDTO.getTelefono1());
        persona.setTelefono2(PersonaDTO.getTelefono2());
        persona.setIngreso(PersonaDTO.getIngreso());
        persona.setNumEmpleado(PersonaDTO.getNumEmpleado());
        return persona;
    }
}
