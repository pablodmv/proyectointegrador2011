/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.motivoausenciaDTO;
import com.inout.entities.MotivoAusencia;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pablo
 */
@Stateless
public class motivoAusencia implements motivoAusenciaLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<motivoausenciaDTO> obtenerMotivoAusencia() {

        try {
            Query query = em.createNamedQuery("MotivoAusencia.findAll");
            List<MotivoAusencia> lista = query.getResultList();
            List<motivoausenciaDTO> motivosDTO = new ArrayList<motivoausenciaDTO>();
            for (MotivoAusencia motivo : lista) {
                motivosDTO.add(convertirMotivoDTO(motivo));
            }
            return motivosDTO;
        } catch (Exception e) {
        }
        return null;
    }

    
    @Override
    public motivoausenciaDTO convertirMotivoDTO(MotivoAusencia motivo) {
        motivoausenciaDTO motivoDTO = new motivoausenciaDTO();
        motivoDTO.setID(motivo.getId());
        motivoDTO.setMotivo(motivo.getMotivo());
        return motivoDTO;


    }

    @Override
    public MotivoAusencia convertirDTOMotivo(motivoausenciaDTO motivo) {
       MotivoAusencia motivoAusencia = new MotivoAusencia();
       motivoAusencia.setMotivo(motivo.getMotivo());
       return motivoAusencia;
    }

    @Override
    public motivoausenciaDTO obtenerMotivoAusencia(Long id) {
        MotivoAusencia motivo = em.find(MotivoAusencia.class, id);
        return convertirMotivoDTO(motivo);



    }
}
