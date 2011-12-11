/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.horarioDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.HorarioSemana;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface horariosLocal {
    public Boolean insertarHorario(horarioDTO horario);
    public List<horarioDTO> obtenerHorarioPersona(personaDTO persona);
    public Boolean modificarHorarioPersona(horarioDTO horario);

    public java.lang.Boolean eliminarHorarioPersona(com.inout.dto.horarioDTO horario);




    
}
