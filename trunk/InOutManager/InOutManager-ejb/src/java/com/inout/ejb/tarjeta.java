/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.tarjetaDTO;
import com.inout.entities.Tarjeta;
import java.lang.annotation.Target;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pablo
 */
@Stateless
public class tarjeta implements tarjetaLocal {

    @PersistenceContext()
    private EntityManager em;

    @Override
    public Boolean altaTarjeta(tarjetaDTO tarjeta) {
        Tarjeta tarj = new Tarjeta();

        tarj.setId(tarjeta.getId());
        tarj.setDescripcion(tarjeta.getDescripcion());
        tarj.setFechaEntrega(tarjeta.getFechaEntrega());
        tarj.setFechaDevolucion(tarjeta.getFechaDevolucion());
        tarj.setActiva(tarjeta.getActiva());
        tarj.setTipo(tarjeta.getTipo());

        try {
        em.persist(tarj);
        em.flush();
        return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar la tarjeta " + e.getMessage());
        }
        return false;
    }

    @Override
    public tarjetaDTO ObtenerTarjetaID(String id) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta = em.find(Tarjeta.class, id);
        return new tarjetaDTO(tarjeta.getId(), tarjeta.getDescripcion(), tarjeta.getTipo(), tarjeta.getFechaEntrega(), tarjeta.getFechaDevolucion(), tarjeta.getActiva());

    }
}
