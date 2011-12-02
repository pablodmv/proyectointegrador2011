/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Marca;
import com.inout.entities.Persona;
import com.inout.util.converters;
import java.util.ArrayList;
import java.util.Date;
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
public class marca implements marcaLocal {

    @PersistenceContext()
    private EntityManager em;
    @EJB
    private personaLocal personaEJB;
    @EJB
    private tarjetaLocal tarjeta;
    @EJB
    private loggerJMSLocal Logger;
    @EJB
    private marcaJMSLocal marcaJMS;
    @EJB
    private cierreLocal cierreEJB;

    @Override
    public Boolean altaMarca(marcaDTO marca) {
        String userLogin = "System";


        try {
            marcaJMS.persistir(convertirDTOMarca(marca, userLogin));
            //em.persist(convertirDTOMarca(marca, userLogin));
            //em.flush();

            return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar la marca" + e.getMessage());
        }
        return false;
    }

    @Override
    public List<marcaDTO> obtenerTodasMarcas(Date fecha, String userLogin) {
        try {


            Query marcasPorFecha = em.createNamedQuery("Marca.findByFecha");
            List<marcaDTO> marcaDTOList = new ArrayList<marcaDTO>();
            marcasPorFecha.setParameter("fecha", fecha);
            List<Marca> marcas = marcasPorFecha.getResultList();
            for (Marca marca : marcas) {
                marcaDTOList.add(convertirMarcaDTO(marca));
            }
            return marcaDTOList;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Marca obtenerMarca(Long idMarca) {

        try {
            return em.find(Marca.class, idMarca);
        } catch (Exception e) {
            System.out.println("No se pudo obtener la marca");
            return null;
        }

    }

    @Override
    public Boolean eliminarMarca(marcaDTO MarcaDTO, String userLogin) {
        try {
            em.remove(convertirDTOMarca(MarcaDTO, userLogin));
            em.flush();
            Logger.loggerMessage("eliminarMarca", userLogin, "IdMarca: " + MarcaDTO.getId());
            return true;
        } catch (Exception e) {
            Logger.loggerMessage("eliminarMarca", userLogin, e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean modificarMarca(marcaDTO MarcaDTO, String userLogin) {

        try {
            Marca marca = obtenerMarca(MarcaDTO.getId());
            marca.setFecha(MarcaDTO.getFecha());
            marca.setHora(MarcaDTO.getHora());
            marca.setIdDispositivo(MarcaDTO.getIdDispositivo());
            marca.setDispositivo(MarcaDTO.getDispositivo());
            marca.setCorreccionFecha(MarcaDTO.getCorreccionFecha());
            marca.setCorreccionHora(MarcaDTO.getCorreccionHora());
            marca.setIdDispositivo(MarcaDTO.getIdDispositivo());
            marca.setIdPareja(MarcaDTO.getIdPareja());
            em.merge(marca);
            em.flush();
            //Logueo
            //Logger.loggerMessage("modificarMarca", userLogin, "IdMarca: " + MarcaDTO.getId());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Logger.loggerMessage("modificarMarca", userLogin, e.getMessage());
            return false;
        }
    }

    @Override
    public List<marcaDTO> obtenerMarcaPorFechaPersona(personaDTO persona, Date fecha) {
        Persona personaEntity = personaEJB.convertirDTOPersona(persona);
        try {
            Query marcasPorFecha = em.createNamedQuery("Marca.findByFechaYPersona");
            List<marcaDTO> marcaDTOList = new ArrayList<marcaDTO>();
            marcasPorFecha.setParameter("fecha", fecha);
            marcasPorFecha.setParameter("persona", personaEntity);
            List<Marca> marcas = marcasPorFecha.getResultList();
            for (Marca marca : marcas) {
                marcaDTOList.add(convertirMarcaDTO(marca));
            }
            return marcaDTOList;
        } catch (Exception e) {
            System.out.println("No se pudo obtener las marcas por fecha y persona: " + e.getMessage());
            return null;
        }



    }

    private Marca convertirDTOMarca(marcaDTO MarcaDTO, String userLogin) throws Exception {
        Marca marca = new Marca();
        try {
            if (!MarcaDTO.getPersonaID().equals("")) {
                personaDTO personaAux = personaEJB.ObtenerPersona(MarcaDTO.getPersonaID(), userLogin);
                if (personaAux == null) {
                    personaAux = personaEJB.ObtenerPersonaTarjeta(tarjeta.ObtenerTarjetaDTOID(MarcaDTO.getPersonaID(), userLogin), userLogin);
                }
                Persona persona = new Persona();
                persona.setDocumento(personaAux.getDocumento());
                marca.setPersona(persona);
            }
            //TODO: FALTA CAMBIAR EL ID PERSONA O MANEJAR EL TAG
            marca.setFecha(converters.StringDate(converters.DateString(MarcaDTO.getFecha(), "dd/MM/yyyy"), "dd/MM/yyyy"));
            marca.setHora(MarcaDTO.getHora());
            marca.setIdDispositivo(MarcaDTO.getIdDispositivo());
            marca.setDispositivo(MarcaDTO.getDispositivo());

            marca.setObservaciones(MarcaDTO.getObservaciones());
            marca.setTiene_pareja(MarcaDTO.getTiene_pareja());
            marca.setCerrado(MarcaDTO.getCerrado());
            if (MarcaDTO.getCierre()!=null) {
            marca.setCierre(cierreEJB.convertirDTOCierre(MarcaDTO.getCierre()));
            }
            

            return marca;
        } catch (Exception e) {
            throw e;
        }

    }

    private marcaDTO convertirMarcaDTO(Marca marca) throws Exception {
        marcaDTO MarcaDTO = new marcaDTO();
        MarcaDTO.setId(marca.getId());
        MarcaDTO.setIdDispositivo(marca.getIdDispositivo());
        MarcaDTO.setIdPareja(marca.getIdPareja());
        MarcaDTO.setFecha(converters.StringDate(converters.DateString(marca.getFecha(), "dd/MM/yyyy"), "dd/MM/yyyy"));

        if(marca.getCorreccionFecha() !=null){
            MarcaDTO.setCorreccionFecha(marca.getCorreccionFecha());
        }else{
            MarcaDTO.setCorreccionFecha(marca.getFecha());
        }

        
        MarcaDTO.setDispositivo(marca.getDispositivo());
        MarcaDTO.setFechaStr(converters.DateString(MarcaDTO.getFecha(), "dd/MM/yyyy"));
        MarcaDTO.setHora(marca.getHora());
        if (marca.getCorreccionHora() != null) {
            MarcaDTO.setCorreccionHora(marca.getCorreccionHora());
        } else {
            MarcaDTO.setCorreccionHora("");
        }
//        if (marca.getCorreccionFecha()!=null) {
//            MarcaDTO.setCorreccionFechaStr(converters.DateString(marca.getCorreccionFecha(), "yyyy-MM-dd"));
//
//        }
            MarcaDTO.setObservaciones(marca.getObservaciones());
            MarcaDTO.setTiene_pareja(marca.getTiene_pareja());
            MarcaDTO.setCerrado(marca.getCerrado());
            if (marca.getCierre()!=null) {
            MarcaDTO.setCierre(cierreEJB.convertirCierreDTO(marca.getCierre()));
        }
            



        return MarcaDTO;
    }
}
