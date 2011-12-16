/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.ausenciaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Ausencias;
import com.inout.entities.Persona;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author pablo
 */
@Remote
public interface ausenciasLocal {

    public List<ausenciaDTO> obtenerAusenciaPersona(personaDTO persona);

    public void chequearAusencias(personaDTO persona);

    public Ausencias obtenerAusenciaEntity(Date fecha, Persona persona);

    public Boolean insertarAusencia(ausenciaDTO ausencia);

    public List<ausenciaDTO> obtenerAusenciaFechaPersona(personaDTO persona, Date fecha);

    public Boolean modificarAusencia(ausenciaDTO ausencia);
    
}
