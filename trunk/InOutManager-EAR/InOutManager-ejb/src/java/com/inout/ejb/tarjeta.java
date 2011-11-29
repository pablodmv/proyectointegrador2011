/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.tarjetaDTO;
import com.inout.entities.Tarjeta;
import java.util.ArrayList;
import java.util.List;
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
public class tarjeta implements tarjetaLocal {

    @PersistenceContext()
    private EntityManager em;
    @EJB
    private loggerJMSLocal Logger;

    @Override
    public Boolean altaTarjeta(tarjetaDTO tarjeta, String userLogin) {

        try {
            em.persist(convertirDTOTarjeta(tarjeta));
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
            em.remove(convertirDTOTarjeta(TarjetaDTO));
            em.flush();
            Logger.loggerMessage("eliminarTarjeta", userLogin, "IdTarjeta: " + TarjetaDTO.getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }




    @Override
    public List<tarjetaDTO> ObtenerTarjetasActivasDTO() {
        Query tarjetas = em.createNamedQuery("Tarjeta.findByActiva");
        tarjetas.setParameter("activa", Boolean.FALSE);
        List<Tarjeta> obtenerTarjetas = tarjetas.getResultList();
        List<tarjetaDTO> tarjetasRetorno  = new ArrayList<tarjetaDTO>();
        for (Tarjeta tarjeta: obtenerTarjetas) {
            tarjetasRetorno.add(convertirTarjetaDTO(tarjeta));
        }
        return tarjetasRetorno;
    }


    @Override
     public Tarjeta convertirDTOTarjeta(tarjetaDTO TarjetaDTO) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setId(TarjetaDTO.getId());
        tarjeta.setDescripcion(TarjetaDTO.getDescripcion());
        tarjeta.setFechaEntrega(TarjetaDTO.getFechaEntrega());
        tarjeta.setFechaDevolucion(TarjetaDTO.getFechaDevolucion());
        tarjeta.setActiva(TarjetaDTO.getActiva());
        tarjeta.setTipo(TarjetaDTO.getTipo());
        return tarjeta;


    }

    @Override
     public tarjetaDTO convertirTarjetaDTO(Tarjeta tarjeta){
         tarjetaDTO tarjetaRetorno = new tarjetaDTO();
         tarjetaRetorno.setActiva(tarjeta.getActiva());
         tarjetaRetorno.setDescripcion(tarjeta.getDescripcion());
         tarjetaRetorno.setFechaDevolucion(tarjeta.getFechaDevolucion());
         tarjetaRetorno.setFechaEntrega(tarjeta.getFechaEntrega());
         tarjetaRetorno.setId(tarjeta.getId());
         tarjetaRetorno.setTipo(tarjeta.getTipo());
         return tarjetaRetorno;


     }






}
