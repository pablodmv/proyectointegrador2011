/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Log;
import com.inout.entities.Marca;
import com.inout.entities.Persona;
import com.inout.util.converters;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
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
    private loggerLocal LoggerBean;


    @Override
    public Boolean altaMarca(marcaDTO marca) {
        String userLogin = "System";

        
        try {
            em.persist(convertirDTOMarca(marca,userLogin));
            em.flush();
            //Logueo
            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion("altaMarca");
            bit.setUsuario(userLogin);
            LoggerBean.log(bit);
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar la marca: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<marcaDTO> obtenerTodasMarcas(Date fecha, String userLogin) {
        Query marcasPorFecha = em.createNamedQuery("Marca.findByFecha");
        marcasPorFecha.setParameter("fecha", fecha);
        List<marcaDTO> marcas = marcasPorFecha.getResultList();
            //Logueo
//            Log bit = new Log();
//            bit.setFechahora(new Date());
//            bit.setAccion("obtenerTodasMarcas");
//            bit.setUsuario(userLogin);
//            LoggerBean.log(bit);
        return marcas;

    }

    @Override
    public Boolean eliminarMarca(marcaDTO MarcaDTO, String userLogin) {
        try {
            em.remove(convertirDTOMarca(MarcaDTO, userLogin));
            em.flush();
            //Logueo
            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion("eliminarMarca");
            bit.setUsuario(userLogin);
            LoggerBean.log(bit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean modificarMarca(marcaDTO MarcaDTO, String userLogin) {

        try {
            em.merge(convertirDTOMarca(MarcaDTO, userLogin));
            em.flush();
            //Logueo
            Log bit = new Log();
            bit.setFechahora(new Date());
            bit.setAccion("modificarMarca");
            bit.setUsuario(userLogin);
            LoggerBean.log(bit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Marca convertirDTOMarca(marcaDTO MarcaDTO, String userLogin){
        personaDTO personaAux = personaEJB.ObtenerPersona(MarcaDTO.getPersonaID(), userLogin);
        if (personaAux==null) {
            personaAux = personaEJB.ObtenerPersonaTarjeta(tarjeta.ObtenerTarjetaID(MarcaDTO.getPersonaID(),userLogin),userLogin);
        }
        Persona persona = new Persona();
        persona.setDocumento(personaAux.getDocumento());
        //TODO: FALTA CAMBIAR EL ID PERSONA O MANEJAR EL TAG
        Marca marca = new Marca();
        marca.setFecha(MarcaDTO.getFecha());
        marca.setHora(MarcaDTO.getHora());
        marca.setIdDispositivo(MarcaDTO.getIdDispositivo());
        marca.setDispositivo(MarcaDTO.getDispositivo());
        marca.setPersona(persona);
        return marca;

    }
}
