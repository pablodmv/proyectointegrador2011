/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.marcaDTO;
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

    public Boolean alta(marcaDTO marca);
    public List<marcaDTO> obtenerTodasMarcas(Date fecha);

    
}
