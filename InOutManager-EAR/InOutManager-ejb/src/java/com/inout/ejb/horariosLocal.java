/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.horarioDTO;
import com.inout.dto.personaDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pablo
 */
@Local
public interface horariosLocal {
    public Boolean insertarHorario(horarioDTO horario);
    public List<horarioDTO> obtenerHorarioPersona(personaDTO persona);
    public Boolean modificarHorarioPersona(horarioDTO horario);



    
}
