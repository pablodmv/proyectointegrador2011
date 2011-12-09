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
    @EJB
    private tarjetaLocal tarjeta;

    @Override
    public Boolean altaPersona(personaDTO PersonaDTO, String userLogin) {
        try {
            personaDTO obtengoPersonaDTO = ObtenerPersona(PersonaDTO.getDocumento(), "System");

            if (obtengoPersonaDTO != null) {
                obtengoPersonaDTO.setApellido(PersonaDTO.getApellido());
                obtengoPersonaDTO.setDireccion(PersonaDTO.getDireccion());
                obtengoPersonaDTO.setIngreso(PersonaDTO.getIngreso());
                obtengoPersonaDTO.setNombre(PersonaDTO.getNombre());
                obtengoPersonaDTO.setNumEmpleado(PersonaDTO.getNumEmpleado());
                obtengoPersonaDTO.setTelefono1(PersonaDTO.getTelefono1());
                obtengoPersonaDTO.setTelefono2(PersonaDTO.getTelefono2());
                if (!obtengoPersonaDTO.getTarjeta().getId().equalsIgnoreCase(PersonaDTO.getTarjeta().getId())) {
                    //Actualizo la tarjeta anterior.
                    tarjetaDTO tarjetaOld = obtengoPersonaDTO.getTarjeta();
                    tarjetaOld.setActiva(Boolean.FALSE);
                    tarjetaOld.setFechaDevolucion(new Date());
                    tarjeta.modificarTarjeta(tarjetaOld, "System");
                    obtengoPersonaDTO.setTarjeta(tarjeta.ObtenerTarjetaDTOID(PersonaDTO.getTarjeta().getId(), "System"));
                    obtengoPersonaDTO.getTarjeta().setActiva(Boolean.TRUE);
                    obtengoPersonaDTO.getTarjeta().setFechaEntrega(new Date());
                    tarjeta.modificarTarjeta(obtengoPersonaDTO.getTarjeta(), "System");
                }
                em.merge(convertirDTOPersona(obtengoPersonaDTO));
                em.flush();
            } else {
                tarjetaDTO tarjetaAux = tarjeta.ObtenerTarjetaDTOID(PersonaDTO.getTarjeta().getId(), userLogin);
                tarjetaAux.setFechaEntrega(new Date());
                tarjetaAux.setActiva(Boolean.TRUE);
                PersonaDTO.setTarjeta(tarjetaAux);
                Tarjeta tarjetaEntity = tarjeta.convertirDTOTarjeta(tarjetaAux);
                em.merge(tarjetaEntity);
                em.persist(convertirDTOPersona(PersonaDTO));
                em.flush();
            }
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
            personaDTO PersonaDTO = new personaDTO(persona.getDocumento(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono1(), persona.getTelefono2(), persona.getIngreso(), persona.getNumEmpleado());
            PersonaDTO.setTarjeta(tarjeta.convertirTarjetaDTO(persona.getTarjeta()));
            return PersonaDTO;
        } catch (Exception e) {
            System.out.println("Error al obtener persona :" +e.getMessage());
            return null;
        }

    }

    @Override
    public personaDTO ObtenerPersonaTarjeta(tarjetaDTO TarjetaDTO, String userLogin) {
        try {
            Tarjeta tarjetaEnt = new Tarjeta();
            Persona persona = new Persona();
            tarjetaEnt.setId(TarjetaDTO.getId());
            String jpl = "Select p FROM Persona p WHERE p.tarjeta=:tarjeta";
            Query q = em.createQuery(jpl);
            q.setParameter("tarjeta", tarjetaEnt);
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

    @Override
    public Persona convertirDTOPersona(personaDTO PersonaDTO) {
        Persona persona = new Persona();
        persona.setDocumento(PersonaDTO.getDocumento());
        persona.setNombre(PersonaDTO.getNombre());
        persona.setApellido(PersonaDTO.getApellido());
        persona.setDireccion(PersonaDTO.getDireccion());
        persona.setTelefono1(PersonaDTO.getTelefono1());
        persona.setTelefono2(PersonaDTO.getTelefono2());
        persona.setIngreso(PersonaDTO.getIngreso());
        persona.setNumEmpleado(PersonaDTO.getNumEmpleado());
        if (PersonaDTO.getTarjeta()!=null) {
        persona.setTarjeta(tarjeta.convertirDTOTarjeta(PersonaDTO.getTarjeta()));
        }
        
        return persona;
    }

    @Override
    
    public personaDTO convertirPersonaDTO(Persona persona) {
        personaDTO PersonaDTO = new personaDTO();
        PersonaDTO.setDocumento(persona.getDocumento());
        PersonaDTO.setNombre(persona.getNombre());
        PersonaDTO.setApellido(persona.getApellido());
        PersonaDTO.setDireccion(persona.getDireccion());
        PersonaDTO.setTelefono1(persona.getTelefono1());
        PersonaDTO.setTelefono2(persona.getTelefono2());
        PersonaDTO.setIngreso(persona.getIngreso());
        PersonaDTO.setNumEmpleado(persona.getNumEmpleado());
        if (persona.getTarjeta()!=null) {
        PersonaDTO.setTarjeta(tarjeta.convertirTarjetaDTO(persona.getTarjeta()));
        }
        
        return PersonaDTO;
    }
}
