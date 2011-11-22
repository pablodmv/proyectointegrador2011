/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Marca;
import com.inout.entities.Persona;
import com.inout.util.converters;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
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
public class marca implements marcaLocal {

    @PersistenceContext()
    private EntityManager em;
    @EJB
    private personaLocal persona;
    @EJB
    private tarjetaLocal tarjeta;

    @Override
    public Boolean alta(marcaDTO marca) {

        personaDTO personaAux = persona.ObtenerPersona(marca.getPersonaID());
        if (personaAux==null) {
            personaAux = persona.ObtenerPersonaTarjeta(tarjeta.ObtenerTarjetaID(marca.getPersonaID()));
        }
        Persona persona = new Persona();
        persona.setDocumento(personaAux.getDocumento());
        //TODO: FALTA CAMBIAR EL ID PERSONA O MANEJAR EL TAG
        Marca marcaEntity = new Marca();
        marcaEntity.setFecha(marca.getFecha());
        marcaEntity.setHora(marca.getHora());
        marcaEntity.setIdDispositivo(marca.getIdDispositivo());
        marcaEntity.setDispositivo(marca.getDispositivo());
        marcaEntity.setPersona(persona);
        try {
            em.persist(marcaEntity);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar la marca: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<marcaDTO> obtenerTodasMarcas(Date fecha) {
        Query marcasPorFecha = em.createNamedQuery("Marca.findByFecha");
        marcasPorFecha.setParameter("fecha", fecha);
        List<marcaDTO> marcas = marcasPorFecha.getResultList();
        return marcas;

    }

}
