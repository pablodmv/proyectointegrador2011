/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.horarioDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.HorarioSemana;
import com.inout.entities.Persona;
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
public class horarios implements horariosLocal {

    @EJB
    personaLocal persona;
    @PersistenceContext
    EntityManager em;

    @Override
    public Boolean insertarHorario(horarioDTO horario) {

        try {
            em.persist(convertirDTOHorario(horario));
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al guardar horario " + e.getLocalizedMessage());
        }
        return false;


    }

    @Override
    public List<horarioDTO> obtenerHorarioPersona(personaDTO personaParam) {

        try {
            Persona personaEnt = persona.convertirDTOPersona(personaParam);
            Query horario = em.createNamedQuery("HorarioSemana.findByPersona");
            List<horarioDTO> horarioDTOList = new ArrayList<horarioDTO>();
            horario.setParameter("persona", personaEnt);
            List<HorarioSemana> horarios = horario.getResultList();
            for (HorarioSemana horarioEnt : horarios) {
                horarioDTOList.add(convertirHorarioDTO(horarioEnt));
            }
            return horarioDTOList;
        } catch (Exception e) {
            return null;
        }



    }

    @Override
    public Boolean modificarHorarioPersona(horarioDTO horario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private HorarioSemana convertirDTOHorario(horarioDTO horario) {
        HorarioSemana horarioSem = new HorarioSemana();
        horarioSem.setDiaSemana(horario.getDiaSem());
        horarioSem.setFin(horario.getFin());
        horarioSem.setInicio(horario.getInicio());
        horarioSem.setObservaciones(horario.getObservaciones());
        horarioSem.setPersona(persona.convertirDTOPersona(horario.getPersona()));
        horarioSem.setSalon(horario.getSalon());
        return horarioSem;
    }

    private horarioDTO convertirHorarioDTO(HorarioSemana horario) {
        horarioDTO horarioSem = new horarioDTO();
        horarioSem.setDiaSem(horario.getDiaSemana());
        horarioSem.setFin(horario.getFin());
        horarioSem.setInicio(horario.getInicio());
        horarioSem.setObservaciones(horario.getObservaciones());
        horarioSem.setPersona(persona.convertirPersonaDTO(horario.getPersona()));
        horarioSem.setSalon(horario.getSalon());
        horarioSem.setId(horario.getId());
        return horarioSem;
    }
}
