/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.marcaDTO;
import com.inout.dto.personaDTO;
import com.inout.entities.Marca;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface marcaLocal {

    public Boolean altaMarca(marcaDTO marca);
    public List<marcaDTO> obtenerTodasMarcas(Date fecha, String usuario);
    public Boolean eliminarMarca( marcaDTO MarcaDTO, String usuario);
    public Boolean modificarMarca(marcaDTO MarcaDTO, String usuario) ;
    public Marca obtenerMarca(Long idMarca);
    public List<marcaDTO> obtenerMarcaPorFechaPersona(personaDTO persona, Date fecha);
    public void formarParejas(Marca marca);

    public List<marcaDTO> obtenerMarcasPorFecha(Date fechaDesde, Date fechaHasta, String userLogin);

    
}
