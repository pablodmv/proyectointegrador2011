/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.motivoausenciaDTO;
import com.inout.entities.MotivoAusencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pablo
 */
@Local
public interface motivoAusenciaLocal {

    public List<motivoausenciaDTO> obtenerMotivoAusencia();

    public motivoausenciaDTO convertirMotivoDTO(MotivoAusencia motivo);

    public MotivoAusencia convertirDTOMotivo(motivoausenciaDTO motivo);
    public motivoausenciaDTO obtenerMotivoAusencia(Long id);
}
