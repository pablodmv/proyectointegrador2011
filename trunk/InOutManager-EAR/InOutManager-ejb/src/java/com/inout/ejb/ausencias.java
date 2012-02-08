/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.ausenciaDTO;
import com.inout.dto.horarioDTO;
import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Ausencias;
import com.inout.entities.Persona;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ausencias implements ausenciasLocal {

    @EJB
    personaLocal personaEJB;
    @EJB
    motivoAusenciaLocal motivo;
    @EJB
    marcaLocal marca;
    @EJB
    horariosLocal horario;
    @PersistenceContext
    EntityManager em;

    @Override
    public Boolean insertarAusencia(ausenciaDTO ausencia) {
        try {
            Ausencias ausenciaEntity = new Ausencias();
            Persona persona = personaEJB.ObtenerPersonaEntidad(ausencia.getPersona().getDocumento(), null);

            ausenciaEntity = this.obtenerAusenciaEntity(ausencia.getFecha(), persona);
            ausenciaEntity.setFecha(ausencia.getFecha());
            ausenciaEntity.setObservacion(ausencia.getObservacion());
            ausenciaEntity.setPersona(persona);
            if (ausencia.getHorario()!=null) {
                ausenciaEntity.setHorarioSemana(horario.convertirDTOHorario(ausencia.getHorario(), Boolean.FALSE));
                ausenciaEntity.getHorarioSemana().setPersona(persona);
            }
            if (ausencia.getMotivo()!=null) {
                ausenciaEntity.setMotivoAusencia(motivo.convertirDTOMotivo(ausencia.getMotivo()));
            }


            //ausenciaEntity.setPersona(personaEJB.ObtenerPersona(persona.getDocumento(), null);

            if (ausenciaEntity.getId() != null) {
                em.merge(ausenciaEntity);
                em.flush();
                return true;
            } else {
                em.persist(ausenciaEntity);
                em.flush();
                return true;
            }
        } catch (Exception e) {
            System.out.println("No se pudo guardar la ausencia " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean modificarAusencia(ausenciaDTO ausencia){
        Ausencias ausenciaEntidad = new Ausencias();
        ausenciaEntidad.setFecha(ausencia.getFecha());
        ausenciaEntidad.setHorarioSemana(horario.convertirDTOHorario(ausencia.getHorario(),false));
        ausenciaEntidad.setMotivoAusencia(motivo.convertirDTOMotivo(ausencia.getMotivo()));
        ausenciaEntidad.setObservacion(ausencia.getObservacion());
        ausenciaEntidad.setPersona(personaEJB.convertirDTOPersona(ausencia.getPersona()));
        try {
            em.merge(ausenciaEntidad);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar la ausencia " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<ausenciaDTO> obtenerAusenciaPersona(personaDTO persona) {
        chequearAusencias(persona);
        Persona personaEntity = personaEJB.convertirDTOPersona(personaEJB.ObtenerPersona(persona.getDocumento(), null));
        try {
            Query ausenciasPorPersona = em.createNamedQuery("Ausencias.findByPersona");
            List<ausenciaDTO> ausenciaDTOList = new ArrayList<ausenciaDTO>();
            ausenciasPorPersona.setParameter("persona", personaEntity);
            List<Ausencias> lista = ausenciasPorPersona.getResultList();
            for (Ausencias ausenciasEntidad : lista) {
                ausenciaDTOList.add(convertirAusenciaDTO(ausenciasEntidad));
            }
            return ausenciaDTOList;
        } catch (Exception e) {
             System.out.println("No se pudo obtener la ausencia " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ausenciaDTO> obtenerAusenciaFechaPersona(personaDTO persona, Date fecha) {
        List<ausenciaDTO> ausenciasRetorno = new ArrayList<ausenciaDTO>() ;
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        Calendar fechaDesde = Calendar.getInstance();
        Calendar fechaHasta = Calendar.getInstance();
        fechaDesde.set(fechaCalendar.get(Calendar.YEAR), fechaCalendar.get(Calendar.MONTH), fechaCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        fechaHasta.set(fechaCalendar.get(Calendar.YEAR), fechaCalendar.get(Calendar.MONTH), fechaCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        try {
            Query query = em.createNamedQuery("Ausencias.findByEntreFechaPersona");
            query.setParameter("fechaDesde", fechaDesde.getTime());
            query.setParameter("fechaHasta", fechaHasta.getTime());
            query.setParameter("persona", personaEJB.convertirDTOPersona(persona));
            List<Ausencias> lista = query.getResultList();
            for (Ausencias ausencia : lista) {
                ausenciasRetorno.add(convertirAusenciaDTO(ausencia));
            }
            return ausenciasRetorno;
        } catch (Exception e) {
            System.out.println("No se pudo obtener la ausencia " + e.getMessage());
        }
        return null;
    }

    @Override
    public Ausencias obtenerAusenciaEntity(Date fecha, Persona persona) {

        try {
            Query query = em.createNamedQuery("Ausencias.findByFechaPersona");
            query.setParameter("fecha", fecha);
            query.setParameter("persona", persona);
            return (Ausencias) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al obtener la ausencia " + e.getMessage());
        }
        return null;
    }

    private ausenciaDTO convertirAusenciaDTO(Ausencias ausenciasEntidad) {
        ausenciaDTO ausencia = new ausenciaDTO();
        ausencia.setFecha(ausenciasEntidad.getFecha());
        ausencia.setID(ausenciasEntidad.getId());
        if (ausenciasEntidad.getMotivoAusencia()!=null) {
        ausencia.setMotivo(motivo.convertirMotivoDTO(ausenciasEntidad.getMotivoAusencia()));
        }
        
        ausencia.setObservacion(ausenciasEntidad.getObservacion());
        if (ausenciasEntidad.getPersona()!=null) {
        ausencia.setPersona(personaEJB.convertirPersonaDTO(ausenciasEntidad.getPersona()));
        }
        if (ausenciasEntidad.getHorarioSemana()!=null) {
            ausencia.setHorario(horario.convertirHorarioDTO(ausenciasEntidad.getHorarioSemana(), Boolean.FALSE));

        }
        
        return ausencia;
    }

    private Ausencias convertirDTOAusencias(ausenciaDTO Ausencia){
        Ausencias ausencias = new Ausencias();
        ausencias.setFecha(Ausencia.getFecha());
        ausencias.setMotivoAusencia(motivo.convertirDTOMotivo(Ausencia.getMotivo()));
        ausencias.setObservacion(Ausencia.getObservacion());
        ausencias.setPersona(personaEJB.convertirDTOPersona(Ausencia.getPersona()));
        return ausencias;

    }

    @Override
    public void chequearAusencias(personaDTO persona) {
        try {
            Calendar fecha = Calendar.getInstance();
            fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMinimum(Calendar.DAY_OF_MONTH));
            Calendar fechaHasta = Calendar.getInstance();
            fechaHasta.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
            List<String> fechaTrabajaList = new ArrayList<String>();
            List<String> fechaTrabajoList = new ArrayList<String>();
            List<Short> diaSemanaList = horario.obtenerDiasSemana(persona);
            fechaTrabajoList = marca.obtenerDiasTrabajados(fecha.getTime(), fechaHasta.getTime(), persona, null);
            for (Short diaSemana : diaSemanaList) {
                fechaTrabajaList.addAll(obtenerDia(fecha, diaSemana));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaTrabaja = null;
            ausenciaDTO ausencia = new ausenciaDTO();
            for (String fechaStr : fechaTrabajaList) {
                if (!fechaTrabajoList.contains(fechaStr)) {
                    ausencia.setFecha(sdf.parse(fechaStr));
                    ausencia.setPersona(persona);
                    Boolean insertarAusencia = insertarAusencia(ausencia);
                    System.out.println(insertarAusencia);
                }
            }
        } catch (ParseException ex) {
            System.out.println("No se pudo obtner la ausencia " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("No se pudo obtner la ausencia " + ex.getMessage());
        }
    }

    private List<String> obtenerDia(Calendar fecha, int dia) {
        List<String> retorno = new ArrayList<String>();
        Calendar fechaAux = Calendar.getInstance();
        fechaAux.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = fecha.getActualMinimum(Calendar.DAY_OF_MONTH); i < fecha.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
            if (fechaAux.get(Calendar.DAY_OF_WEEK) == dia) {
                retorno.add(sdf.format(fechaAux.getTime()));
            }
            fechaAux.add(Calendar.DAY_OF_MONTH, 1);
        }
        return retorno;
    }
}
