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
import javax.servlet.jsp.tagext.TryCatchFinally;

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
            em.persist(convertirDTOHorario(horario,false));
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al guardar horario " + e.getLocalizedMessage());
        }
        return false;
    }


    @Override
    public horarioDTO obtenerHorario(Long idHorario){

        try {
            convertirHorarioDTO(em.find(HorarioSemana.class, idHorario), Boolean.TRUE);
        } catch (Exception e) {
            System.out.println("Error al obtener horario " + e.getMessage());
        }
        return null;


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
                horarioDTOList.add(convertirHorarioDTO(horarioEnt,false));
            }
            return horarioDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Short> obtenerDiasSemana(personaDTO personaParam) {

        try {
            Persona personaEnt = persona.convertirDTOPersona(personaParam);
            Query horario = em.createNamedQuery("HorarioSemana.findDiasSemana");
            List<Short> diaSemana = new ArrayList<Short>();
            horario.setParameter("persona", personaEnt);
            diaSemana = horario.getResultList();
            return diaSemana;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean modificarHorarioPersona(horarioDTO horario) {

        try {
            HorarioSemana horarioEntity = em.find(HorarioSemana.class, horario.getId());
            horarioEntity.setDiaSemana(horario.getDiaSem());
            horarioEntity.setFin(horario.getFin());
            horarioEntity.setInicio(horario.getInicio());
            horarioEntity.setObservaciones(horario.getObservaciones());
            horarioEntity.setSalon(horario.getSalon());
            em.merge(horarioEntity);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al modificar el horario " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean eliminarHorarioPersona(horarioDTO horario) {

        try {
            HorarioSemana horarioEntity = em.find(HorarioSemana.class, horario.getId());
            em.remove(horarioEntity);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al modificar el horario " + e.getMessage());
        }
        return false;
    }

    @Override
    public HorarioSemana convertirDTOHorario(horarioDTO horario, Boolean esPersona) {
        HorarioSemana horarioSem = new HorarioSemana();
        horarioSem.setDiaSemana(horario.getDiaSem());
        horarioSem.setFin(horario.getFin());
        horarioSem.setInicio(horario.getInicio());
        horarioSem.setObservaciones(horario.getObservaciones());
        if (!esPersona) {
        horarioSem.setPersona(persona.convertirDTOPersona(horario.getPersona()));
        }
        
        horarioSem.setSalon(horario.getSalon());
        return horarioSem;
    }

    @Override
    public horarioDTO convertirHorarioDTO(HorarioSemana horario,Boolean esPersona) {
        horarioDTO horarioSem = new horarioDTO();
        horarioSem.setDiaSem(horario.getDiaSemana());
        horarioSem.setFin(horario.getFin());
        horarioSem.setInicio(horario.getInicio());
        horarioSem.setObservaciones(horario.getObservaciones());
        if (!esPersona) {
        horarioSem.setPersona(persona.convertirPersonaDTO(horario.getPersona()));
        }
        
        horarioSem.setSalon(horario.getSalon());
        horarioSem.setId(horario.getId());
        return horarioSem;
    }
}
