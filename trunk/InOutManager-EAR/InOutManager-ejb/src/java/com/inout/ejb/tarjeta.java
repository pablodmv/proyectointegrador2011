/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.tarjetaDTO;
import com.inout.entities.Tarjeta;
import javax.ejb.EJB;
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
    @EJB
    private loggerJMSLocal Logger;

    @Override
    public Boolean altaTarjeta(tarjetaDTO tarjeta, String userLogin) {

        try {
            em.persist(convertirDTOTarjeta(tarjeta, userLogin));
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar la tarjeta " + e.getMessage());
        }
        return false;
    }

    @Override
    public tarjetaDTO ObtenerTarjetaDTOID(String id, String userLogin) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta = em.find(Tarjeta.class, id);
        return new tarjetaDTO(tarjeta.getId(), tarjeta.getDescripcion(), tarjeta.getTipo(), tarjeta.getFechaEntrega(), tarjeta.getFechaDevolucion(), tarjeta.getActiva());

    }

    private Tarjeta ObtenerTarjetaID(String id, String userLogin) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta = em.find(Tarjeta.class, id);
        return tarjeta;

    }

    @Override
    public Boolean modificarTarjeta(tarjetaDTO TarjetaDTO, String userLogin) {
        try {
            Tarjeta tarjeta = ObtenerTarjetaID(TarjetaDTO.getId(), userLogin);
            tarjeta.setDescripcion(TarjetaDTO.getDescripcion());
            tarjeta.setActiva(TarjetaDTO.getActiva());
            tarjeta.setFechaDevolucion(TarjetaDTO.getFechaDevolucion());
            tarjeta.setTipo(TarjetaDTO.getTipo());
            em.merge(tarjeta);
            em.flush();
            Logger.loggerMessage("modificarTarjeta", userLogin, "IdTarjeta: " + TarjetaDTO.getId());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean eliminarTarjeta(tarjetaDTO TarjetaDTO, String userLogin) {

        try {
            em.remove(convertirDTOTarjeta(TarjetaDTO, userLogin));
            em.flush();
            Logger.loggerMessage("eliminarTarjeta", userLogin, "IdTarjeta: " + TarjetaDTO.getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Tarjeta convertirDTOTarjeta(tarjetaDTO TarjetaDTO, String userLogin) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setId(TarjetaDTO.getId());
        tarjeta.setDescripcion(TarjetaDTO.getDescripcion());
        tarjeta.setFechaEntrega(TarjetaDTO.getFechaEntrega());
        tarjeta.setFechaDevolucion(TarjetaDTO.getFechaDevolucion());
        tarjeta.setActiva(TarjetaDTO.getActiva());
        tarjeta.setTipo(TarjetaDTO.getTipo());
        return tarjeta;


    }
}
