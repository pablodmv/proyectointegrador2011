/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

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

    @Override
    public Boolean alta(String fecha, String hora, Integer id_dispositivo, String dispositivo, String id_persona) {

        Persona personaAux = persona.ObtenerPersona("42562072");
        //TODO: FALTA CAMBIAR EL ID PERSONA O MANEJAR EL TAG
        Marca marca = new Marca();
        marca.setFecha(converters.StringDate(fecha, "yyyy/MM/dd"));
        marca.setHora(hora);
        marca.setIdDispositivo(id_dispositivo);
        marca.setDispositivo(dispositivo);
        marca.setPersona(personaAux);
        try {
            em.persist(marca);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar la marca: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Marca> obtenerTodasMarcas(Date fecha) {
        Query marcasPorFecha = em.createNamedQuery("Marca.findByFecha");
        marcasPorFecha.setParameter("fecha", fecha);
        List<Marca> marcas = marcasPorFecha.getResultList();
        return marcas;

    }
}
