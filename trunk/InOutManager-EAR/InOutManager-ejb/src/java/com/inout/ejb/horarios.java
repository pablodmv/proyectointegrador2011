/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.horarioDTO;
import com.inout.dto.personaDTO;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pablo
 */
@Stateless
public class horarios implements horariosLocal {

    @Override
    public Boolean insertarHorario(horarioDTO horario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<horarioDTO> obtenerHorarioPersona(personaDTO persona) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarHorarioPersona(horarioDTO horario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
